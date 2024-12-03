package com.example.carhubjava.ui.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.carhubjava.R;
import com.example.carhubjava.model.User;
import com.example.carhubjava.viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, nicknameEditText, birthDateEditText, cnhEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Inicializar componentes da interface
        nameEditText = findViewById(R.id.nameEditText);
        nicknameEditText = findViewById(R.id.nicknameEditText);
        birthDateEditText = findViewById(R.id.birthDateEditText);
        cnhEditText = findViewById(R.id.cnhEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        // Configurar o botão de registro
        registerButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String nickname = nicknameEditText.getText().toString().trim();
            String birthDate = birthDateEditText.getText().toString().trim();
            String cnh = cnhEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(nickname) || TextUtils.isEmpty(birthDate) ||
                    TextUtils.isEmpty(cnh) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(null, name, nickname, birthDate, cnh, email);
            userViewModel.registerUser(user, password).observe(this, isSuccess -> {
                if (isSuccess) {
                    Toast.makeText(RegisterActivity.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Erro no registro. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
