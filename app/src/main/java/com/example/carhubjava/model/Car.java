package com.example.carhubjava.model;

import java.io.Serializable;

public class Car implements Serializable {
    private String id;
    private String name;
    private String type; // SUV, Hatch, Sedan
    private String imageUrl;
    private String pricePerDay;  // Preço por dia

    public Car(String id, String name, String type, String imageUrl, String pricePerDay) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.pricePerDay = pricePerDay;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
