package com.example.carhubjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carhubjava.R;

public class ReservationsFragment extends Fragment {

    public ReservationsFragment() {
        // Construtor vazio obrigatório
        super(R.layout.fragment_reservations);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate o layout do fragment
        return inflater.inflate(R.layout.fragment_reservations, container, false);
    }
}
