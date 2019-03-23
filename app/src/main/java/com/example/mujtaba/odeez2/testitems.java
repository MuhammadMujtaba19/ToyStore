package com.example.mujtaba.odeez2;

/**
 * Created by Mujtaba on 3/22/2018.
 */

public class testitems {
    private String ProductName;
    private String ProductURL;
    private int ProductPrice;
    public String ProductQuantity;

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public testitems(String productName, int x) {
        ProductName = productName;
        ProductPrice = x;
    }

    public testitems(String productName, int productPrice, String productQuantity, String productURL) {
        ProductName = productName;
        ProductPrice = productPrice;
        ProductURL = productURL;
        ProductQuantity = productQuantity;

    }

    public int getProductPrice() {
        return ProductPrice;
    }
    public String getProductQuantity() {
        return ProductQuantity;
    }


    public String getProductName() {
        return ProductName;
    }

    public String getProductURL() {
        return ProductURL;
    }


}
