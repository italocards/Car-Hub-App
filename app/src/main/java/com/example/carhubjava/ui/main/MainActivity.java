package com.example.carhubjava.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.carhubjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração da BottomNavigationView e NavController
        try {
            // Inicializa a BottomNavigationView
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

            // Recupera o NavHostFragment
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment);

            if (navHostFragment == null) {
                throw new IllegalStateException("NavHostFragment não encontrado no layout.");
            }

            // Inicializa o NavController
            NavController navController = navHostFragment.getNavController();

            // Configura a BottomNavigationView com o NavController
            NavigationUI.setupWithNavController(bottomNavigationView, navController);

            Log.d(TAG, "NavController configurado com sucesso.");
        } catch (Exception e) {
            Log.e(TAG, "Erro ao configurar NavController", e);
        }
    }
}
