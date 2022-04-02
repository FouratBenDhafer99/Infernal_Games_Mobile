package com.infernalgames.entities;

/**
 *
 * @author milin
 */
public class Product {
    private int id;
    private String name, description, brand, category, picture;
    private float price;
    private int quantity;

    public Product () {
    }

    public Product (String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Product (int id,String name, String description, String brand, String category, String picture, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.picture = picture;
        this.price = price;
    }

    public Product(String name, String description, String brand, String category, String picture, float price) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.picture = picture;
        this.price = price;
    }

    public Product(String name, String description, String brand, String category, String picture, float price, int quantity) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.picture = picture;
        this.price = price;
        this.quantity = quantity;
    }

    public Product (int id, String name, String description, String brand, String category, String picture, float price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.picture = picture;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        return "Product {" + "id=" + id + ", name=" + name +" ,description="+ description+ "  ,brand="+ brand+ "  ,category="+ category+ ""
                + " ,picture="+ picture+ ""
                + " ,price="+ price+ ""
                + " ,quantity="+ quantity+ " \n";
    }


}
