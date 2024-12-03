package com.example.carhubjava.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.Car;
import com.example.carhubjava.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private CarRepository carRepository;
    private MutableLiveData<List<Car>> carListLiveData;

    public CarViewModel(Application application) {
        super(application);
        carRepository = new CarRepository(application);
        carListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Car>> getCarList() {
        return carListLiveData;
    }

    public void loadCars() {
        // Carregar carros e garantir que a lista não seja nula
        List<Car> cars = carRepository.getCars();
        if (cars != null) {
            carListLiveData.setValue(cars);
        } else {
            carListLiveData.setValue(new ArrayList<>()); // Evitar null pointer
        }
    }
}
