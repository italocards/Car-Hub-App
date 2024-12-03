package com.example.carhubjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carhubjava.repository.UserRepository;

public class LoginViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<Boolean> loginStatus;

    public LoginViewModel() {
        this.userRepository = new UserRepository();
        this.loginStatus = new MutableLiveData<>();
    }

    public LiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public void login(String email, String password) {
        userRepository.login(email, password).observeForever(isSuccess -> loginStatus.setValue(isSuccess));
    }
}
