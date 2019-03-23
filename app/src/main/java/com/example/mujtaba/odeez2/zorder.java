package com.example.mujtaba.odeez2;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Mujtaba on 3/10/2019.
 */
@IgnoreExtraProperties

public class zorder implements Serializable {
   public String custID, shipment, status,payment, comment;

    public zorder(String custID, String shipment, String status, String payment, String comment) {
        this.custID = custID;
        this.shipment = shipment;
        this.status = status;
        this.payment = payment;
        this.comment = comment;
    }

    public zorder() {
    }
}
