package com.example.mujtaba.odeez2;

import java.io.Serializable;

/**
 * Created by Mujtaba on 3/10/2019.
 */

public class zitem implements Serializable {
    public String orderID, itemID,itemName;
    public int price, quantity;

    public zitem(String orderID, String itemName, int price, int quantity) {
        this.orderID = orderID;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public zitem() {
    }
}
