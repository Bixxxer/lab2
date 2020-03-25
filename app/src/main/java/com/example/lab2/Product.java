package com.example.lab2;
public class Product {
    private String graphic;
    private String name;
    private String helptext;

    public Product(String image, String name, String price) {
        this.graphic = image;
        this.name = name;
        this.helptext = price;
    }

    public String getImage() {
        return "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + graphic;
    }

    public void setImage(String image) {
        this.graphic = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return helptext;
    }

}
