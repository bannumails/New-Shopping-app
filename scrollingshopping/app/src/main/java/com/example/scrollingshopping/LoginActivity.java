package com.example.scrollingshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnLinkToRegister;
    private TextInputLayout inputName, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = findViewById(R.id.edit_email);
        inputPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.button_login);
        btnLinkToRegister = findViewById(R.id.button_register);

        init();
    }

    private void init() {
        // Login button Click Event
        btnLogin.setOnClickListener(view -> {

            String name = Objects.requireNonNull(inputName.getEditText()).getText().toString().trim();
            String password = Objects.requireNonNull(inputPassword.getEditText()).getText().toString().trim();

            // Check for empty data in the form
            if (!name.isEmpty() && !password.isEmpty()) {
                loginProcess(name, password);
            } else {
                // Prompt user to enter credentials
                Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
            }
        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
            finish();
        });

    }

    private void loginProcess(String name, String password) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("name", "");
        String userPassword = preferences.getString("password", "");
        if (userName != null && !userName.equalsIgnoreCase("") && userPassword != null && !userPassword.equalsIgnoreCase("")) {
            if (name.equalsIgnoreCase(userName) && password.equalsIgnoreCase(userPassword)) {
                Intent i = new Intent(LoginActivity.this, ShoppingMenuActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please register first!", Toast.LENGTH_LONG).show();
        }
    }
}
