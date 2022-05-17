/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infernalgames.entities;

/**
 *
 * @author eya dhaouadi
 */
public class Ad {
    
    private int id ;
    private String description, nom ,image,type ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ad{" + "id=" + id + ", description=" + description + ", nom=" + nom + ", image=" + image + ", type=" + type + '}';
    }

    public Ad() {
    }

    public Ad(int id, String description, String nom, String image, String type) {
        this.id = id;
        this.description = description;
        this.nom = nom;
        this.image = image;
        this.type = type;
    }

    public Ad(String description, String nom, String image, String type) {
        this.description = description;
        this.nom = nom;
        this.image = image;
        this.type = type;
    }
    
    
    
    
}
