package com.example.carhubjava.repository;

import android.content.Context;
import android.util.Log;

import com.example.carhubjava.model.Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReservationRepository {

    private final DatabaseReference databaseReference;

    public ReservationRepository(Context context) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference("reservations");
    }

    public void saveReservation(Reservation reservation) {
        String reservationId = databaseReference.push().getKey();
        reservation.setReservationId(reservationId);
        databaseReference.child(reservationId).setValue(reservation).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("ReservationRepository", "Reserva salva com sucesso! ID: " + reservationId);
            } else {
                Log.e("ReservationRepository", "Erro ao salvar reserva.", task.getException());
            }
        });
    }

}
