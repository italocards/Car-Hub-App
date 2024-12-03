package com.example.carhubjava.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.Car;
import com.example.carhubjava.model.Reservation;
import com.example.carhubjava.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        List<Car> cars = carRepository.getCars();
        if (cars != null) {
            carListLiveData.setValue(cars);
        } else {
            carListLiveData.setValue(new ArrayList<>()); // Evitar null pointer
        }
    }

    // Método para criar a reserva
    public void createReservation(String vehicleId, String pickupDate, String returnDate, String userId, String pricePerDay) {
        Reservation reservation = new Reservation(userId, vehicleId, pickupDate, returnDate, "Pending", "");

        // Calcular o preço total
        int numberOfDays = calculateDaysBetween(pickupDate, returnDate); // Método para calcular dias entre as datas
        reservation.calculateTotalPrice(pricePerDay, numberOfDays);

        // Salvar a reserva no repositório
        carRepository.saveReservation(reservation);
    }

    public int calculateDaysBetween(String startDate, String endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato usado nas datas
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            long differenceInMillis = end.getTime() - start.getTime();
            return (int) (differenceInMillis / (1000 * 60 * 60 * 24)); // Converte milissegundos para dias
        } catch (ParseException e) {
            e.printStackTrace();
            return 0; // 0 em caso de erro
        }
    }


}
