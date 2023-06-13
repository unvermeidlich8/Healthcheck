package com.example.healthcheck;

import static com.example.healthcheck.DBHelper.COLUMN_blood_cholesterol;
import static com.example.healthcheck.DBHelper.COLUMN_blood_pressure;
import static com.example.healthcheck.DBHelper.COLUMN_blood_sugar;
import static com.example.healthcheck.DBHelper.COLUMN_pulse;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class Journal extends AppCompatActivity {
    private DBHelper dbHelper;
    private ListView listView;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        dbHelper = new DBHelper(this);
        emptyTextView = findViewById(R.id.empty_text_view);

        // Добавляем DatePicker
        DatePicker datePicker = findViewById(R.id.date_picker);
        datePicker.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                null);

        Button showButton = findViewById(R.id.show_button);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
                Toast.makeText(Journal.this, date, Toast.LENGTH_SHORT).show();





    }
        });
    }
}







