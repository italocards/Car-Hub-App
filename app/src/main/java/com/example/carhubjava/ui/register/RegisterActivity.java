package com.example.carhubjava.ui.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.example.carhubjava.R;
import com.example.carhubjava.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, nicknameEditText, birthDateEditText, cnhEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

            // Registrar o usuário
            registerUser(name, nickname, birthDate, cnh, email, password);
        });
    }

    private void registerUser(String name, String nickname, String birthDate, String cnh, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Se o registro for bem-sucedido, salve os dados no Realtime Database
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            // Criar um novo objeto de usuário com todos os dados
                            User userProfile = new User(user.getUid(), name, nickname, birthDate, cnh, email);

                            // Salve todos os dados do usuário no Realtime Database
                            mDatabase.child("users").child(user.getUid()).setValue(userProfile)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(RegisterActivity.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();
                                        finish(); // Fecha a tela de registro e vai para a tela principal
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(RegisterActivity.this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Erro no registro. Verifique os dados inseridos.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
