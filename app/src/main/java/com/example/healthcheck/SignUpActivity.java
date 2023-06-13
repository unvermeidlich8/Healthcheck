package com.example.healthcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private Button signUp;
    private EditText fieldName, fieldEmail, fieldPassword;
    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        signUp = findViewById(R.id.buttonSignUp);
        fieldName = findViewById(R.id.editTextName);
        fieldEmail = findViewById(R.id.editTextEmail);
        fieldPassword = findViewById(R.id.editTextPassword);
        DB = new DBHelper(this);
    }
    public void onBack(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSignUp(View view) {
        String name = fieldName.getText().toString();
        String email = fieldEmail.getText().toString();
        String pass = fieldPassword.getText().toString();

        if(name.equals("")||email.equals("")|| pass.equals(""))
            Toast.makeText(this, "Введите все поля!", Toast.LENGTH_SHORT).show();
        else if (DB.getUser(email)){
            Toast.makeText(this, "Пользователь с таким логином уже зарегистрирован!", Toast.LENGTH_SHORT).show();
        } else {
            if (DB.addUser(name, email, pass) != -1) {
                Intent intent = new Intent();
                Toast.makeText(SignUpActivity.this, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show();
                intent.putExtra("name", name)
                        .putExtra("email", email)
                        .putExtra("password", pass);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(SignUpActivity.this, "Что-то пошло не так!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}