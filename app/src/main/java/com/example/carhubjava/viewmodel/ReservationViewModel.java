package com.example.carhubjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.carhubjava.model.Reservation;
import com.example.carhubjava.repository.ReservationRepository;

public class ReservationViewModel extends AndroidViewModel {

    private final ReservationRepository reservationRepository;

    public ReservationViewModel(@NonNull Application application) {
        super(application);
        reservationRepository = new ReservationRepository(application);
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.saveReservation(reservation);
    }
}
