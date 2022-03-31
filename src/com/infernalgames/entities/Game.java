/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infernalgames.entities;

/**
 *
 * @author milin
 */
public class Game {
    private int id;
    private String name, description, trailer_Url, category, picture;
    private float price,rating ;

    public Game() {
    }

    public Game(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Game(String name, String description, String trailer_Url, String category, String picture, float price) {
        this.name = name;
        this.description = description;
        this.trailer_Url = trailer_Url;
        this.category = category;
        this.picture = picture;
        this.price = price;
    }

    public Game(int id, String name, String description, String trailer_Url, String category, String picture, float price, float rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.trailer_Url = trailer_Url;
        this.category = category;
        this.picture = picture;
        this.price = price;
        this.rating = rating;
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

    public String getTrailer_Url() {
        return trailer_Url;
    }

    public void setTrailer_Url(String trailer_Url) {
        this.trailer_Url = trailer_Url;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    public String toString() {
        return "Game{" + "id=" + id + ", name=" + name +" ,description="+ description+ "  ,trailer_Url="+ trailer_Url+ "  ,category="+ category+ ""
                + " ,picture="+ picture+ ""
                + " ,price="+ price+ ""
                + " ,rating="+ rating+ " \n";
    }


}
