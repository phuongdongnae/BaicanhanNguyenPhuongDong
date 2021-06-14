package com.example.baicanhan_nguyenphuongdong.model;
//ảnh là int
public class Clothes {
    private int photo;
    private String name, price, des;

    public Clothes() {
    }

    public Clothes(int photo, String name, String price, String des) {
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
