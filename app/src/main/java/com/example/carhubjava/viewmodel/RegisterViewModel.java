package com.example.carhubjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.carhubjava.model.User;
import com.example.carhubjava.repository.UserRepository;

public class RegisterViewModel extends ViewModel {

    private final UserRepository userRepository;

    public RegisterViewModel() {
        this.userRepository = new UserRepository();
    }

    public LiveData<Boolean> registerUser(User user, String password) {
        return userRepository.registerUser(user, password);
    }
}
