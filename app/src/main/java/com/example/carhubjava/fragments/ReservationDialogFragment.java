package com.example.carhubjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.carhubjava.R;
import com.example.carhubjava.model.Car;
import com.example.carhubjava.viewmodel.CarViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class ReservationDialogFragment extends DialogFragment {

    private CarViewModel carViewModel;
    private Car selectedCar;

    public static ReservationDialogFragment newInstance(Car car) {
        ReservationDialogFragment fragment = new ReservationDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("car", car);  // Usando Parcelable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reservation, container, false);

        // Inicializando o ViewModel
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        // Recuperando o carro selecionado
        if (getArguments() != null) {
            selectedCar = getArguments().getParcelable("car");
        }

        if (selectedCar == null) {
            dismiss();
            return null;
        }

        // Inicializando a interface
        TextView carNameTextView = view.findViewById(R.id.carName);
        TextView carTypeTextView = view.findViewById(R.id.carType);
        TextView carPriceTextView = view.findViewById(R.id.carPrice);
        DatePicker pickupDatePicker = view.findViewById(R.id.pickupDate);
        DatePicker returnDatePicker = view.findViewById(R.id.returnDate);
        Button confirmReservationButton = view.findViewById(R.id.confirmReservationButton);

        carNameTextView.setText(selectedCar.getName());
        carTypeTextView.setText(selectedCar.getType());
        carPriceTextView.setText(selectedCar.getPricePerDay());

        confirmReservationButton.setOnClickListener(v -> {
            // Pegando as datas selecionadas
            String pickupDate = pickupDatePicker.getDayOfMonth() + "/" + (pickupDatePicker.getMonth() + 1) + "/" + pickupDatePicker.getYear();
            String returnDate = returnDatePicker.getDayOfMonth() + "/" + (returnDatePicker.getMonth() + 1) + "/" + returnDatePicker.getYear();

            // Obtendo o ID do usuário autenticado
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Obtém o ID do usuário autenticado

            // Obtenha o preço por dia do carro selecionado
            String pricePerDay = selectedCar.getPricePerDay();

            // Criar a reserva com o nome do veículo
            carViewModel.createReservation(selectedCar.getId(), pickupDate, returnDate, userId, pricePerDay, selectedCar.getName());

            dismiss();  // Fechar o diálogo
        });


        return view;
    }
}
