package com.example.carhubjava.repository;

import android.content.Context;

import com.example.carhubjava.model.Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    private Context context;
    private DatabaseReference databaseReference;

    public ReservationRepository(Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    // Método que busca as reservas de um usuário específico
    public void getUserReservations(String userId, ReservationsCallback callback) {
        databaseReference.child("reservations")
                .orderByChild("userId")
                .equalTo(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Reservation> reservations = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Reservation reservation = snapshot.getValue(Reservation.class);
                            if (reservation != null) {
                                reservations.add(reservation);
                            }
                        }
                        callback.onSuccess(reservations);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onFailure(databaseError.getMessage());
                    }
                });
    }

    // Interface de callback para retornar os dados
    public interface ReservationsCallback {
        void onSuccess(List<Reservation> reservations);
        void onFailure(String error);
    }
}
