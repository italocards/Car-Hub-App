package com.example.carhubjava.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carhubjava.R;
import com.example.carhubjava.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservations;

    // Construtor
    public ReservationAdapter() {
        this.reservations = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);

        holder.vehicleNameTextView.setText("Veículo: " + reservation.getVehicleName()); // Agora, você pode acessar o nome do veículo
        holder.pickupDateTextView.setText("Retirada: " + reservation.getPickupDate());
        holder.returnDateTextView.setText("Devolução: " + reservation.getReturnDate());
        holder.totalPriceTextView.setText("Preço Total: $" + reservation.getTotalPrice());
        holder.statusTextView.setText("Status: " + reservation.getStatus());

        // Configurar ações dos botões
        holder.completeButton.setOnClickListener(v -> {
            // Lógica para concluir a reserva
        });

        holder.cancelButton.setOnClickListener(v -> {
            // Lógica para cancelar a reserva
        });
    }


    @Override
    public int getItemCount() {
        return reservations.size();
    }

    // Método para atualizar a lista de reservas
    public void updateReservations(List<Reservation> newReservations) {
        reservations.clear();
        reservations.addAll(newReservations);
        notifyDataSetChanged();
    }

    // ViewHolder para a reserva
    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleNameTextView, pickupDateTextView, returnDateTextView, totalPriceTextView, statusTextView;
        Button completeButton, cancelButton;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleNameTextView = itemView.findViewById(R.id.vehicleNameTextView);
            pickupDateTextView = itemView.findViewById(R.id.pickupDateTextView);
            returnDateTextView = itemView.findViewById(R.id.returnDateTextView);
            totalPriceTextView = itemView.findViewById(R.id.totalPriceTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            completeButton = itemView.findViewById(R.id.completeButton);
            cancelButton = itemView.findViewById(R.id.cancelButton);
        }
    }
}
