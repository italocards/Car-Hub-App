package com.example.carhubjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carhubjava.R;
import com.example.carhubjava.ui.adapters.CarAdapter;
import com.example.carhubjava.viewmodel.CarViewModel;
import com.example.carhubjava.model.Car;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private CarViewModel carViewModel;
    private CarAdapter carAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Inicializando o ViewModel
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCars);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializando o adaptador com uma lista vazia inicialmente
        carAdapter = new CarAdapter(new ArrayList<>(), this::onCarClick);
        recyclerView.setAdapter(carAdapter);

        // Carregando os dados
        carViewModel.loadCars();

        // Observando as mudanças na lista de carros
        carViewModel.getCarList().observe(getViewLifecycleOwner(), carList -> {
            // Atualizando a lista de carros no adaptador
            carAdapter.setCarList(carList);
        });

        return view;
    }

    // Ação ao clicar no carro
    private void onCarClick(Car car) {
        ReservationDialogFragment dialog = ReservationDialogFragment.newInstance(car);
        dialog.show(getChildFragmentManager(), "ReservationDialog");
    }
}
