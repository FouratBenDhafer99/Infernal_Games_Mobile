package com.infernalgames.entities;
/**
 *
 * @author Fourat
 */
public class StreamCategory {

    private int id;
    private String label;

    public StreamCategory(){}

    public StreamCategory(int id, String label){
        this.id= id;
        this.label= label;
    }

    public StreamCategory(String label) {
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
