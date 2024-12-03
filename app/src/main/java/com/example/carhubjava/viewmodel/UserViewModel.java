package com.example.carhubjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.carhubjava.model.User;
import com.example.carhubjava.repository.UserRepository;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;

    public UserViewModel() {
        this.userRepository = new UserRepository();
    }

    // Método de registro
    public LiveData<Boolean> registerUser(User user, String password) {
        return userRepository.registerUser(user, password);
    }

    // Método de login
    public LiveData<Boolean> login(String email, String password) {
        return userRepository.login(email, password);
    }
}
