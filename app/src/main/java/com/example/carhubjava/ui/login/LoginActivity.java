package com.example.carhubjava.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import com.example.carhubjava.R;
import com.example.carhubjava.ui.main.MainActivity;
import com.example.carhubjava.viewmodel.LoginViewModel;
import com.example.carhubjava.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Inicializar componentes da interface
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Observar o estado de login e exibir a resposta
        loginViewModel.getLoginStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if (isSuccess != null && isSuccess) {
                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "Transição para MainActivity iniciada");

                    // Redirecionar para a MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish(); // Finaliza a LoginActivity para que o usuário não possa voltar
                } else {
                    Toast.makeText(LoginActivity.this, "Erro no login. Verifique suas credenciais.", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "Login falhou");
                }
            }
        });

        // Configurar o botão de login
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(email, password);
        });

        // Configurar o botão de registro
        registerButton.setOnClickListener(v -> {
            // Criando a Intent para abrir a RegisterActivity
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
