package com.example.gastrocs;

public class Gericht {
    private String name;
    private String preis;
    private int id;
    private byte[] img;

    public Gericht(String name, String preis, int id, byte[] img) {
        this.name = name;
        this.preis = preis;
        this.id = id;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}


