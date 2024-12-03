package com.example.carhubjava.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.Reservation;
import com.example.carhubjava.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

    // Método para registrar um novo usuário
    public LiveData<Boolean> registerUser(String name, String nickname, String birthDate, String cnh, String email, String password) {
        MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = firebaseAuth.getCurrentUser().getUid();
                        User user = new User(userId, name, nickname, birthDate, cnh, email);
                        databaseReference.child(userId).setValue(user)
                                .addOnCompleteListener(databaseTask -> {
                                    registrationStatus.setValue(databaseTask.isSuccessful());
                                });
                    } else {
                        registrationStatus.setValue(false);
                    }
                });

        return registrationStatus;
    }

    // Método para fazer login
    public LiveData<Boolean> login(String email, String password) {
        MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginStatus.setValue(true);
                    } else {
                        loginStatus.setValue(false);
                    }
                });

        return loginStatus;
    }

    // Método para adicionar uma reserva no histórico do usuário
    public LiveData<Boolean> addReservation(String userId, Reservation reservation) {
        MutableLiveData<Boolean> addReservationStatus = new MutableLiveData<>();

        databaseReference.child(userId).child("reservationHistory").push().setValue(reservation)
                .addOnCompleteListener(task -> addReservationStatus.setValue(task.isSuccessful()));

        return addReservationStatus;
    }
}
