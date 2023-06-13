package com.example.healthcheck;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private final Context context;
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id_users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    public static final String COLUMN_weight = "weight";
    private static final String COLUMN_height = "height";
    private static final String COLUMN_age = "age";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_blood_pressure = "blood_pressure";
    public static final String COLUMN_pulse = "pulse";
    public static final String COLUMN_blood_sugar = "blood_sugar";
    public static final String COLUMN_blood_cholesterol = "blood_cholesterol";

    // артериальное давление, пульс, уровень сахара в крови, уровень холестерина в крови,
    // blood pressure, pulse, blood sugar, blood cholesterol


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASS + " TEXT,"
                + COLUMN_weight + " TEXT,"
                + COLUMN_age + " TEXT,"
                + COLUMN_blood_pressure + " TEXT,"
                + COLUMN_pulse + " TEXT,"
                + COLUMN_blood_sugar + " TEXT,"
                + COLUMN_blood_cholesterol + " TEXT,"
                + COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASS, password);

        return db.insert(TABLE_NAME, null, cv);
    }

    public Boolean getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + "=?";
        return db.rawQuery(query, new String[] { email }).moveToFirst();
    }

    public Boolean getPass(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + COLUMN_PASS + " FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + "=?";
        Cursor cursor = db.rawQuery(query, new String[] { email });

        String dbPassword = "";
        if (cursor.moveToFirst()) {
            int passIndex = cursor.getColumnIndex(COLUMN_PASS);
            dbPassword = cursor.getString(passIndex);
        }
        return pass.equals(dbPassword);
    }


    public long insertData(String date, String diastolicPressure, String pulse, String glucoseLevel, String cholesterolLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_blood_pressure, diastolicPressure);
        cv.put(COLUMN_blood_sugar, glucoseLevel);
        cv.put(COLUMN_blood_cholesterol, cholesterolLevel);

        return db.insert(TABLE_NAME, null, cv);
    }

    public Cursor getHealthDataByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE + "=?";
        return db.rawQuery(query, new String[] { date });
    }

    public void getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                System.out.println("ID: " + id +
                        ", Name: " + name +
                        ", Email: " + email +
                        ", Password: " + password);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}
