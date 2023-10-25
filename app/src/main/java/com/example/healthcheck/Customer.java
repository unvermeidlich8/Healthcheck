package com.example.healthcheck;

public class Customer {
    int customerID;
    String customerName;
    String CustomerDate;
    String CustomerChol;
    String Customerpres;
    String CustomerSugar;

    @Override
    public String toString() {
        return
                "Name='" + customerName + '\n' +
                "Date='" + CustomerDate + '\n' +
                "Chol='" + CustomerChol + '\n' +
                "Pres='" + Customerpres + '\n' +
                "Sugar='" + CustomerSugar;
    }

    public Customer(int id, String name, String date, String chol, String pres, String sugar){
        this.customerID = id;
        this.CustomerDate = date;
        this.customerName = name;
        this.CustomerChol = chol;
        this.Customerpres = pres;
        this.CustomerSugar = sugar;
    }

    public int getCustomerID() {
        return customerID;
    }
}
