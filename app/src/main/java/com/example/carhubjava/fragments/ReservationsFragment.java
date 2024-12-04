package com.example.carhubjava.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carhubjava.R;
import com.example.carhubjava.ui.adapters.ReservationAdapter;
import com.example.carhubjava.viewmodel.ReservationViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ReservationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReservationAdapter reservationAdapter;
    private ReservationViewModel reservationViewModel;
    private String userId;

    public ReservationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservations, container, false);

        // Inicializando o ViewModel
        reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);

        // Recuperando o userId do Firebase ou de outra fonte
        userId = FirebaseAuth.getInstance().getCurrentUser() != null
                ? FirebaseAuth.getInstance().getCurrentUser().getUid()
                : null;

        if (userId == null) {
            Log.e("ReservationsFragment", "Usuário não autenticado.");
            return view;  // Não continuar se o usuário não estiver autenticado
        }

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reservationAdapter = new ReservationAdapter();
        recyclerView.setAdapter(reservationAdapter);

        // Carregar as reservas do usuário logado
        reservationViewModel.loadUserReservations(userId);

        // Observando as reservas
        reservationViewModel.getReservations().observe(getViewLifecycleOwner(), reservations -> {
            if (reservations != null && !reservations.isEmpty()) {
                reservationAdapter.updateReservations(reservations);
            } else {
                Log.e("ReservationsFragment", "Nenhuma reserva encontrada.");
            }
        });

        return view;
    }
}
