package com.example.carhubjava.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String nickname;
    private String birthDate;
    private String cnh;
    private String email;
    private List<Reservation> reservationHistory; // Histórico de reservas

    // Construtor vazio necessário para Firebase
    public User() {
        this.reservationHistory = new ArrayList<>();
    }

    // Construtor com parâmetros
    public User(String userId, String name, String nickname, String birthDate, String cnh, String email) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.cnh = cnh;
        this.email = email;
        this.reservationHistory = new ArrayList<>();
    }

    // Getters e Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reservation> getReservationHistory() {
        return reservationHistory;
    }

    public void setReservationHistory(List<Reservation> reservationHistory) {
        this.reservationHistory = reservationHistory;
    }

    public void addReservation(Reservation reservation) {
        this.reservationHistory.add(reservation);
    }
}
