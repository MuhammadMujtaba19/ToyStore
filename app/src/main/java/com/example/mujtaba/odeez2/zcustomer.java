package com.example.mujtaba.odeez2;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Mujtaba on 11/24/2018.
 */
@IgnoreExtraProperties
public class zcustomer implements Serializable{
    public String userid, name , email, age, address, city , country , phone;
    int date;

    public zcustomer(String userid, String name, String email, String age, String address, String city, String country, String phone, int date) {
      this.userid = userid;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.date = date;
    }

    public zcustomer() {
    }
}
