package com.example.carhubjava.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carhubjava.R;
import com.example.carhubjava.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private TextView nameValue, nicknameValue, birthDateValue, cnhValue, emailValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar TextViews
        nameValue = view.findViewById(R.id.nameValue);
        nicknameValue = view.findViewById(R.id.nicknameValue);
        birthDateValue = view.findViewById(R.id.birthDateValue);
        cnhValue = view.findViewById(R.id.cnhValue);
        emailValue = view.findViewById(R.id.emailValue);

        loadUserData();

        return view;
    }

    private void loadUserData() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Log.e("ProfileFragment", "Nenhum usuário autenticado.");
            return;
        }

        String userId = auth.getCurrentUser().getUid();
        Log.d("ProfileFragment", "User ID: " + userId);

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    Log.d("ProfileFragment", "Dados do usuário carregados com sucesso.");
                    nameValue.setText(user.getName());
                    nicknameValue.setText(user.getNickname());
                    birthDateValue.setText(user.getBirthDate());
                    cnhValue.setText(user.getCnh());
                    emailValue.setText(user.getEmail());
                } else {
                    Log.e("ProfileFragment", "Dados do usuário estão nulos.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ProfileFragment", "Erro ao carregar dados do usuário: " + error.getMessage());
            }
        });
    }

}
