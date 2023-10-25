package com.example.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Journal extends AppCompatActivity {
    private DBHelper db;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        ListView LV = findViewById(R.id.customerList);
        Button btn = findViewById(R.id.btn_for_all);

        db = new DBHelper(Journal.this); ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(Journal.this, android.R.layout.simple_list_item_1,db.getAll());
        LV.setAdapter(customerArrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(Journal.this);
                List <Customer> everyone = db.getAll();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(Journal.this, android.R.layout.simple_list_item_1,everyone);
                LV.setAdapter(customerArrayAdapter);
            }
        });
    }
}







