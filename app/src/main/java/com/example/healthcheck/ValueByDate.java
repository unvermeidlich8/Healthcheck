package com.example.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ValueByDate extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_date);
        EditText date = findViewById(R.id.SearchByDate);
        ListView LV = findViewById(R.id.ListByDate);
        Button btn = findViewById(R.id.find_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ValueByDate.this);
                List <Customer> every = db.getAllByDate(date.getText().toString());
                ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(ValueByDate.this, android.R.layout.simple_list_item_1,every);
                LV.setAdapter(customerArrayAdapter);
            }
        });
    }
}
