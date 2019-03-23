package com.example.mujtaba.odeez2;

/**
 * Created by Mujtaba on 3/11/2018.
 */

    public class items {
    private int ID;
        private String name;
        private String price;
        private String imageUrl;
        private String desc;

    private int pri;
    private int disc;

    public int getPri() {
        return pri;
    }

    public int getDisc() {
        return disc;
    }

    public String getDesc() {
        return desc;
    }


    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImageUrl() {
            return imageUrl;
        }

        public String getName() {
            return name;

        }

        public String getPrice() {
            return price;
        }
    public items(int ID,String Name, String price,String imageUrl,String x) {
        this.ID= ID;
        this.name = Name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.desc = x;
    }

    public items(int ID,String name, int price , String imageUrl, int Disc,String desc) {
        this.ID= ID;
        this.name = name;
        this.pri = price;
        this.imageUrl = imageUrl;
        this.disc = Disc;
        this.desc = desc;
    }
}


