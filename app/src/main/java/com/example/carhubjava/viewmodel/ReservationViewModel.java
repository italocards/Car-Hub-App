package com.example.carhubjava.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.Reservation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReservationViewModel extends AndroidViewModel {

    private final DatabaseReference databaseReference;
    private final MutableLiveData<List<Reservation>> reservations;

    public ReservationViewModel(@NonNull Application application) {
        super(application);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        reservations = new MutableLiveData<>();
    }

    public LiveData<List<Reservation>> getReservations() {
        return reservations;
    }

    public void loadUserReservations(String userId) {
        databaseReference.child("reservations")
                .orderByChild("userId")
                .equalTo(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Reservation> reservationList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Reservation reservation = dataSnapshot.getValue(Reservation.class);
                            if (reservation != null) {
                                reservationList.add(reservation);
                            }
                        }
                        // Atualize os dados no LiveData
                        reservations.setValue(reservationList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Erro ao carregar reservas: " + error.getMessage());
                    }
                });
    }
}
