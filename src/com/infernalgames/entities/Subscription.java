package com.infernalgames.entities;

public class Subscription {

    private int id;
    private boolean status;
    private Utilisateur user;

    public Subscription(){}
    public Subscription(boolean status, Utilisateur user) {
        this.status = status;
        this.user = user;
    }

    public Subscription(int id, boolean status, Utilisateur user) {
        this.id = id;
        this.status = status;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
