package com.example.healthcheck;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class HealthDataAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public HealthDataAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Создаем макет элемента списка
        return inflater.inflate(R.layout.health_data_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Получаем ссылки на элементы макета элемента списка
        TextView dateTextView = view.findViewById(R.id.date_text_view);
        TextView weightTextView = view.findViewById(R.id.weight_text_view);
        TextView bloodPressureTextView = view.findViewById(R.id.blood_pressure_text_view);

        // Извлекаем данные из курсора
        String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATE));
        float weight = cursor.getFloat(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_weight));
        String bloodPressure = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_blood_pressure));

        // Заполняем элементы макета данными
        dateTextView.setText(date);
        weightTextView.setText(String.format(Locale.getDefault(), "%.1f", weight));
        bloodPressureTextView.setText(bloodPressure);
    }
}