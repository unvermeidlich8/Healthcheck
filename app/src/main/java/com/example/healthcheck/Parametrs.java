package com.example.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;

public class Parametrs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrs_set);

        EditText sugar = findViewById(R.id.sugar_value);
        EditText cholesterol = findViewById(R.id.cholesterol_value);
        EditText pressure = findViewById(R.id.pressure_value);
        EditText date = findViewById(R.id.date_analysis);
        EditText name = findViewById(R.id.NameByDate);


        Button saveButton = findViewById(R.id.save_by_date);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper(Parametrs.this);
                    long a = dbHelper.insertDataByDate(date.getText().toString(),pressure.getText().toString(),
                            sugar.getText().toString(),cholesterol.getText().toString(),name.getText().toString());


            }
        });
    }


}
