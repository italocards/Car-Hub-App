package com.example.carhubjava.repository;

import android.content.Context;

import com.example.carhubjava.model.Car;
import com.example.carhubjava.model.Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private Context context;
    private DatabaseReference databaseReference;

    public CarRepository(Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    // Método para obter a lista de carros
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();

        // Aqui você pode adicionar carros fictícios ou buscar do banco de dados
        cars.add(new Car("1", "Golf GTI", "Hatch", "https://www.pngplay.com/wp-content/uploads/13/Volkswagen-Golf-GTI-PNG-Photos.png", "$89/day"));
        cars.add(new Car("2", "BMW 320i", "Sedan", "https://production.autoforce.com/uploads/version/profile_image/9812/comprar-330e-m-sport_733122c039.png", "$172/day"));
        cars.add(new Car("3", "Ferrari Purosangue", "SUV", "https://visioncomex.com/wp-content/uploads/2024/04/ferrari-purosangue-2024-nero-opaco2.png", "$223/day"));

        return cars;
    }

    // Método para salvar a reserva
    public void saveReservation(Reservation reservation) {
        String reservationId = databaseReference.child("reservations").push().getKey();
        reservation.setReservationId(reservationId);
        databaseReference.child("reservations").child(reservationId).setValue(reservation);
    }
}
