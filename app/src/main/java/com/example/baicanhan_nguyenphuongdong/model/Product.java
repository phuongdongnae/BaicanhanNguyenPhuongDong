package com.example.baicanhan_nguyenphuongdong.model;

import java.io.Serializable;
//ảnh byte để lưu vào SQLite
public class Product implements Serializable {
    private int id;
    private byte[] photo;
    private String name, description;
    private Double price;
    private String date;


    public Product() {
    }

    public Product(int id, byte[] photo, String name, String description, Double price, String date) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public Product(byte[] photo, String name, String description, Double price, String date) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
