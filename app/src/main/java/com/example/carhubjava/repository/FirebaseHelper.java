package com.example.carhubjava.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private final FirebaseAuth firebaseAuth;
    private final DatabaseReference databaseReference;

    public FirebaseHelper() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public LiveData<Boolean> registerUserInDatabase(User user) {
        MutableLiveData<Boolean> status = new MutableLiveData<>();
        databaseReference.child(user.getUserId()).setValue(user)
                .addOnSuccessListener(unused -> status.setValue(true))
                .addOnFailureListener(e -> status.setValue(false));
        return status;
    }

    public LiveData<Boolean> createUserWithEmail(String email, String password) {
        MutableLiveData<Boolean> status = new MutableLiveData<>();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> status.setValue(task.isSuccessful()));
        return status;
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
