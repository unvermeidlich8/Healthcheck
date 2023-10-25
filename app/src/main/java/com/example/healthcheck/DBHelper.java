package com.example.healthcheck;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.example.healthcheck.Customer;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private final Context context;
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String TABLE_NAME2 = "analysis";
    private static final String COLUMN_ID = "id_users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";

    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_blood_pressure = "blood_pressure";
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
                + COLUMN_PASS + " TEXT" + ")";

        String CREATE_TABLE_Analysis = "CREATE TABLE " + TABLE_NAME2 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_blood_cholesterol + " TEXT,"
                + COLUMN_blood_pressure + " TEXT,"
                + COLUMN_blood_sugar + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_Analysis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
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


    public long insertDataByDate(String date,String pressure, String sugar,String cholesterol,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_blood_cholesterol,cholesterol);
        cv.put(COLUMN_blood_pressure,pressure);
        cv.put(COLUMN_blood_sugar,sugar);
        Log.d("add table","add");

        return db.insert(TABLE_NAME2,null,cv);

    }


//    public String getAnalysisByDate(String date) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE date=" + date;
//    }

    public List<Customer> getAll() {
        List <Customer> returnList= new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String CustomerDate = cursor.getString(2);
                String CustomerChol = cursor.getString(3);
                String Customerpres = cursor.getString(4);
                String CustomerSugar = cursor.getString(5);

                Customer customer = new Customer(customerID,customerName,CustomerDate,CustomerChol,Customerpres,CustomerSugar);
                returnList.add(customer);
            } while (cursor.moveToNext());
        }else {

        }
        cursor.close();
        db.close();
        return returnList;

    }

    public List<Customer> getAllByDate(String date) {
        List <Customer> returnList= new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE " + COLUMN_DATE + " = " + date ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String CustomerDate = cursor.getString(2);
                String CustomerChol = cursor.getString(3);
                String Customerpres = cursor.getString(4);
                String CustomerSugar = cursor.getString(5);

                Customer customer = new Customer(customerID,customerName,CustomerDate,CustomerChol,Customerpres,CustomerSugar);
                returnList.add(customer);
            } while (cursor.moveToNext());
        }else {

        }
        cursor.close();
        db.close();
        return returnList;

    }



    public boolean deleteOne(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE " + COLUMN_ID + " = " + customer.getCustomerID();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return true;
        }else {
            return false;
        }

    }



}
