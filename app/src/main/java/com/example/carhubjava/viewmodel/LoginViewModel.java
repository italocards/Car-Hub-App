package com.example.carhubjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carhubjava.repository.UserRepository;

public class LoginViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerStatus = new MutableLiveData<>();

    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public LiveData<Boolean> getRegisterStatus() {
        return registerStatus;
    }

    public void login(String email, String password) {
        userRepository.login(email, password).observeForever(isSuccess -> loginStatus.setValue(isSuccess));
    }

    public void register(String name, String nickname, String birthDate, String cnh, String email, String password) {
        userRepository.registerUser(name, nickname, birthDate, cnh, email, password)
                .observeForever(isSuccess -> registerStatus.setValue(isSuccess));
    }
}
