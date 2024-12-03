package com.example.carhubjava.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.carhubjava.R;
import com.example.carhubjava.model.Car;
import com.example.carhubjava.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservationDialogFragment extends DialogFragment {

    private Car selectedCar;
    private EditText pickupDateEditText, returnDateEditText;
    private TextView totalPriceTextView;
    private Calendar pickupDate, returnDate;

    public static ReservationDialogFragment newInstance(Car car) {
        ReservationDialogFragment fragment = new ReservationDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("car", car);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reservation, container, false);

        // Inicializando as views
        pickupDateEditText = view.findViewById(R.id.pickupDate);
        returnDateEditText = view.findViewById(R.id.returnDate);
        totalPriceTextView = view.findViewById(R.id.totalPrice);
        Button confirmButton = view.findViewById(R.id.confirmReservationButton);

        // Recebendo o Carro selecionado
        selectedCar = (Car) getArguments().getSerializable("car");

        // Exibindo informações do veículo
        TextView carName = view.findViewById(R.id.carName);
        carName.setText(selectedCar.getName());
        TextView carType = view.findViewById(R.id.carType);
        carType.setText(selectedCar.getType());
        TextView carPrice = view.findViewById(R.id.carPrice);
        carPrice.setText(selectedCar.getPricePerDay());

        // Calculando o preço total
        totalPriceTextView.setText("Total Price: $0");

        // Configurando o DatePicker para as datas
        pickupDateEditText.setOnClickListener(v -> showDatePickerDialog(pickupDateEditText));
        returnDateEditText.setOnClickListener(v -> showDatePickerDialog(returnDateEditText));

        // Confirmando a reserva
        confirmButton.setOnClickListener(v -> confirmReservation());

        return view;
    }

    private void showDatePickerDialog(final EditText dateEditText) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    dateEditText.setText(sdf.format(calendar.getTime()));
                    if (dateEditText == pickupDateEditText) {
                        pickupDate = calendar;
                    } else {
                        returnDate = calendar;
                    }
                    updateTotalPrice();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateTotalPrice() {
        if (pickupDate != null && returnDate != null) {
            long diffInMillis = returnDate.getTimeInMillis() - pickupDate.getTimeInMillis();
            if (diffInMillis > 0) {
                long diffInDays = diffInMillis / (24 * 60 * 60 * 1000);
                double pricePerDay = Double.parseDouble(selectedCar.getPricePerDay().replace("$", "").replace("/day", ""));
                double totalPrice = diffInDays * pricePerDay;
                totalPriceTextView.setText("Total Price: $" + totalPrice);
            }
        }
    }

    private void confirmReservation() {
        if (pickupDate != null && returnDate != null && !TextUtils.isEmpty(pickupDateEditText.getText()) && !TextUtils.isEmpty(returnDateEditText.getText())) {
            // Gerar a reserva e adicionar ao Firebase ou outro repositório
            String reservationId = "reservation_" + System.currentTimeMillis();
            Reservation reservation = new Reservation(reservationId, selectedCar.getId(), pickupDateEditText.getText().toString(), returnDateEditText.getText().toString(), "Pending");

            // Adicionar lógica de salvar no repositório ou Firebase
            dismiss();
        }
    }
}
