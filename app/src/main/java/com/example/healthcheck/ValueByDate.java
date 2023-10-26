package com.example.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ValueByDate extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_date);
        EditText name = findViewById(R.id.SearchByDate);
        ListView LV = findViewById(R.id.ListByDate);
        Button btn = findViewById(R.id.find_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ValueByDate.this);
                Toast.makeText(ValueByDate.this,name.getText().toString(),Toast.LENGTH_LONG).show();
                List <Customer> every = db.getAllByName(name.getText().toString());

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(ValueByDate.this, android.R.layout.simple_list_item_1,every);
                LV.setAdapter(customerArrayAdapter);
            }
        });
    }
}
