package com.assignment.moveinsyncassignment.database.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    private static final String TAG = "Product";

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String urlString;
    @SerializedName("price")
    private int price;

    public Product() {
    }

    public Product(String name, String description, String urlString, int price) {
        this.name = name;
        this.description = description;
        this.urlString = urlString;
        this.price = price;
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

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
