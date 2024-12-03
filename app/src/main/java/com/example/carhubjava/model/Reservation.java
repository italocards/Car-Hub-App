package com.example.carhubjava.model;

public class Reservation {
    private String reservationId;
    private String vehicleId;
    private String pickupDate;
    private String returnDate;
    private String status;

    // Construtor vazio para Firebase
    public Reservation() {
    }

    // Construtor com parâmetros
    public Reservation(String reservationId, String vehicleId, String pickupDate, String returnDate, String status) {
        this.reservationId = reservationId;
        this.vehicleId = vehicleId;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters e Setters
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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
}
