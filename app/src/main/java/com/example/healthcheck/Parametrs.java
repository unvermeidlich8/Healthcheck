package com.example.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Parametrs extends AppCompatActivity {

    private EditText diastolicPressureEditText;
    private EditText pulseEditText;
    private EditText glucoseLevelEditText;
    private EditText cholesterolLevelEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrs_set);

        // Находим все поля ввода на экране

        diastolicPressureEditText = findViewById(R.id.bpInput);
        pulseEditText = findViewById(R.id.pulseInput);
        glucoseLevelEditText = findViewById(R.id.glucoseInput);
        cholesterolLevelEditText = findViewById(R.id.cholesterolInput);

        // Находим кнопку "Сохранить" на экране и задаем обработчик нажатия
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем значения из полей ввода
                String date = getDateFromPicker();
                String diastolicPressure = diastolicPressureEditText.getText().toString();
                String pulse = pulseEditText.getText().toString();
                String glucoseLevel = glucoseLevelEditText.getText().toString();
                String cholesterolLevel = cholesterolLevelEditText.getText().toString();

                // Сохраняем данные в базу данных SQLite
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.insertData(date, diastolicPressure, pulse, glucoseLevel, cholesterolLevel);
                // Очищаем поля ввода
                clearFields();
                Toast.makeText(Parametrs.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getDateFromPicker() {
        DatePicker datePicker = findViewById(R.id.datePicker);

        int year = datePicker.getYear();
        int month = datePicker.getMonth() + 1; // Месяцы в DatePicker начинаются с 0
        int day = datePicker.getDayOfMonth();

        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
    }

    private void clearFields() {

        diastolicPressureEditText.setText("");
        pulseEditText.setText("");
        glucoseLevelEditText.setText("");
        cholesterolLevelEditText.setText("");
    }
}
