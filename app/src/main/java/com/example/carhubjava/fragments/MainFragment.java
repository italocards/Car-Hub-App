package com.example.carhubjava.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carhubjava.R;
import com.example.carhubjava.ui.adapters.CarAdapter;
import com.example.carhubjava.viewmodel.CarViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private CarViewModel carViewModel;
    private CarAdapter carAdapter;  // Defina carAdapter como um campo da classe

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
        carAdapter = new CarAdapter(new ArrayList<>(), car -> {
            // Implementar a ação quando o carro for clicado
        });
        recyclerView.setAdapter(carAdapter);

        // Carregando os dados
        carViewModel.loadCars();

        // Observando as mudanças na lista de carros
        carViewModel.getCarList().observe(getViewLifecycleOwner(), carList -> {
            // Verifica se a lista de carros não é nula
            if (carList != null) {
                // Atualizando a lista no adaptador
                carAdapter.updateCarList(carList); // Atualize a lista de carros no adaptador
            }
        });

        return view;
    }
}
