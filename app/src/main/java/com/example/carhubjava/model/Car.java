package com.example.carhubjava.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
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

    // Implementação do Parcelable
    protected Car(Parcel in) {
        id = in.readString();
        name = in.readString();
        type = in.readString();
        imageUrl = in.readString();
        pricePerDay = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(imageUrl);
        dest.writeString(pricePerDay);
    }
}
