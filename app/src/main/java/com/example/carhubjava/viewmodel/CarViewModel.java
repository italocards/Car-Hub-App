package com.example.carhubjava.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carhubjava.model.Car;
import com.example.carhubjava.model.Reservation;
import com.example.carhubjava.repository.CarRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private final CarRepository carRepository;
    private final MutableLiveData<List<Car>> carListLiveData;

    public CarViewModel(Application application) {
        super(application);
        carRepository = new CarRepository(application);
        carListLiveData = new MutableLiveData<>();
    }

    /**
     * Retorna a lista de carros.
     */
    public LiveData<List<Car>> getCarList() {
        return carListLiveData;
    }

    /**
     * Carrega a lista de carros do repositório.
     */
    public void loadCars() {
        List<Car> cars = carRepository.getCars();
        if (cars != null) {
            carListLiveData.setValue(cars);
        } else {
            carListLiveData.setValue(new ArrayList<>()); // Evita null pointer
        }
    }

    /**
     * Cria uma reserva e salva no repositório.
     *
     * @param vehicleId   ID do veículo
     * @param vehicleName Nome do veículo
     * @param pickupDate  Data de retirada
     * @param returnDate  Data de devolução
     * @param userId      ID do usuário
     * @param pricePerDay Preço diário do veículo
     */
    public void createReservation(String vehicleId, String pickupDate, String returnDate, String userId, String pricePerDay, String vehicleName) {
        Reservation reservation = new Reservation(userId, vehicleId, vehicleName, pickupDate, returnDate, "Pending", 0.0);

        // Calcular o preço total
        int numberOfDays = calculateDaysBetween(pickupDate, returnDate); // Método para calcular dias entre as datas
        reservation.calculateTotalPrice(pricePerDay, numberOfDays);

        // Salvar a reserva no repositório
        carRepository.saveReservation(reservation);
    }


    /**
     * Calcula a diferença em dias entre duas datas.
     *
     * @param startDate Data inicial (formato: dd/MM/yyyy)
     * @param endDate   Data final (formato: dd/MM/yyyy)
     * @return Número de dias entre as datas
     */
    public int calculateDaysBetween(String startDate, String endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato usado nas datas
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            long differenceInMillis = end.getTime() - start.getTime();
            return (int) (differenceInMillis / (1000 * 60 * 60 * 24)); // Converte milissegundos para dias
        } catch (ParseException e) {
            e.printStackTrace();
            return 0; // Retorna 0 em caso de erro
        }
    }
}
