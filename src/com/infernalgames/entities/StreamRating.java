package com.infernalgames.entities;

public class StreamRating {

    private int id;
    private String label;

    public StreamRating(){}

    public StreamRating(int id, String label){
        this.id= id;
        this.label= label;
    }

    public StreamRating(String label) {
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
