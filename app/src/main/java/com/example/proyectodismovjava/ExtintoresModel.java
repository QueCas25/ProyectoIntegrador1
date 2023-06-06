package com.example.proyectodismovjava;

public class ExtintoresModel {

    String extintorName;
    String extintorType;
    String extintorPrice;
    int image;


    public ExtintoresModel(String extintorName,String extintorType, String extintorPrice, int image) {
        this.extintorName = extintorName;
        this.extintorType = extintorType;
        this.extintorPrice = extintorPrice;
        this.image = image;
    }


    public String getExtintorName() {
        return extintorName;
    }

    public String getExtintorPrice() {
        return extintorPrice;
    }

    public int getImage() {
        return image;
    }

    public String getExtintorType() {
        return extintorType;
    }
}
