package com.example.carhubjava.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

    // Método de registro de usuário
    public LiveData<Boolean> registerUser(User user, String password) {
        MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();

        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = firebaseAuth.getCurrentUser().getUid();
                        user.setUserId(userId);

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

    // Método de login
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
}
