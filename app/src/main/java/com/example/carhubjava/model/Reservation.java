package com.example.carhubjava.model;

import com.google.firebase.database.FirebaseDatabase;

public class Reservation {
    private String reservationId;
    private String userId;  // ID do usuário que está fazendo a reserva
    private String vehicleId;
    private String vehicleName;  // Nome do veículo
    private String pickupDate;
    private String returnDate;
    private String status;
    private double totalPrice;  // Preço total da reserva

    // Construtor vazio para Firebase
    public Reservation() {
    }

    // Construtor com parâmetros
    public Reservation(String userId, String vehicleId, String vehicleName, String pickupDate, String returnDate, String status, double totalPrice) {
        this.reservationId = FirebaseDatabase.getInstance().getReference("reservations").push().getKey(); // Gera automaticamente o ID
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // Getters e Setters
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Método para calcular o preço total com base na duração da reserva
    public void calculateTotalPrice(String pricePerDay, int numberOfDays) {
        double price = Double.parseDouble(pricePerDay.replace("$", "").replace("/day", ""));
        double total = price * numberOfDays;
        this.totalPrice = total;
    }
}
