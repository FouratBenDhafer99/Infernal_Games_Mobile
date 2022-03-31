package com.infernalgames.entities;

public class Stream {

    private int id;
    private String title;
    private String description;
    private StreamRating rating;
    private StreamCategory category;
    private StreamData accessData;
    private boolean state;

    public Stream(){}

    public Stream(int id, String title, String description, StreamRating rating, StreamCategory category, StreamData accessData, boolean state){
        this.id= id;
        this.title= title;
        this.description= description;
        this.rating= rating;
        this.category= category;
        this.accessData= accessData;
        this.state= state;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StreamRating getRating() {
        return rating;
    }

    public void setRating(StreamRating rating) {
        this.rating = rating;
    }

    public StreamCategory getCategory() {
        return category;
    }

    public void setCategory(StreamCategory category) {
        this.category = category;
    }

    public StreamData getAccessData() {
        return accessData;
    }

    public void setAccessData(StreamData accessData) {
        this.accessData = accessData;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
