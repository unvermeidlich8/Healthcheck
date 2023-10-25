package com.example.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_page);

        ListView LV = findViewById(R.id.ListForDelete);
        db = new DBHelper(DeleteActivity.this);
        ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(DeleteActivity.this, android.R.layout.simple_list_item_1,db.getAll());
        LV.setAdapter(customerArrayAdapter);

        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer clicked = (Customer) parent.getItemAtPosition(position);
                db.deleteOne(clicked);
                ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(DeleteActivity.this, android.R.layout.simple_list_item_1,db.getAll());
                LV.setAdapter(customerArrayAdapter);
            }
        });

    }


}
