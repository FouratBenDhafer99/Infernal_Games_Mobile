package com.infernalgames.entities;

import java.time.LocalDate;
/**
 *
 * @author Fourat
 */
public class Newsletter {

    private int id;
    private String titleIntro, contentIntro;
    private String titleF, contentF, imageF;
    private String titleS, contentS, imageS;
    private String titleT, contentT, imageT;
    private LocalDate date;
    private boolean sent;
    private Utilisateur author;

    public Newsletter(){}

    public Newsletter(int id, String titleIntro, String contentIntro, String titleF, String contentF, String imageF, String titleS, String contentS, String imageS, String titleT, String contentT, String imageT, LocalDate date, boolean sent, Utilisateur author) {
        this.id = id;
        this.titleIntro = titleIntro;
        this.contentIntro = contentIntro;
        this.titleF = titleF;
        this.contentF = contentF;
        this.imageF = imageF;
        this.titleS = titleS;
        this.contentS = contentS;
        this.imageS = imageS;
        this.titleT = titleT;
        this.contentT = contentT;
        this.imageT = imageT;
        this.date = date;
        this.sent = sent;
        this.author = author;
    }

    public Newsletter(int id, String titleIntro, String contentIntro, String titleF, String contentF, String imageF, String titleS, String contentS, String imageS, String titleT, String contentT, String imageT, boolean sent, Utilisateur author) {
        this.id = id;
        this.titleIntro = titleIntro;
        this.contentIntro = contentIntro;
        this.titleF = titleF;
        this.contentF = contentF;
        this.imageF = imageF;
        this.titleS = titleS;
        this.contentS = contentS;
        this.imageS = imageS;
        this.titleT = titleT;
        this.contentT = contentT;
        this.imageT = imageT;
        this.sent = sent;
        this.author = author;
    }

    public Newsletter(String titleIntro, String contentIntro, String titleF, String contentF, String imageF, String titleS, String contentS, String imageS, String titleT, String contentT, String imageT, boolean sent, Utilisateur author) {
        this.titleIntro = titleIntro;
        this.contentIntro = contentIntro;
        this.titleF = titleF;
        this.contentF = contentF;
        this.imageF = imageF;
        this.titleS = titleS;
        this.contentS = contentS;
        this.imageS = imageS;
        this.titleT = titleT;
        this.contentT = contentT;
        this.imageT = imageT;
        this.sent = sent;
        this.author = author;
    }

    @Override
    public String toString() {
        return id+" "+titleIntro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleIntro() {
        return titleIntro;
    }

    public void setTitleIntro(String titleIntro) {
        this.titleIntro = titleIntro;
    }

    public String getContentIntro() {
        return contentIntro;
    }

    public void setContentIntro(String contentIntro) {
        this.contentIntro = contentIntro;
    }

    public String getTitleF() {
        return titleF;
    }

    public void setTitleF(String titleF) {
        this.titleF = titleF;
    }

    public String getContentF() {
        return contentF;
    }

    public void setContentF(String contentF) {
        this.contentF = contentF;
    }

    public String getImageF() {
        return imageF;
    }

    public void setImageF(String imageF) {
        this.imageF = imageF;
    }

    public String getTitleS() {
        return titleS;
    }

    public void setTitleS(String titleS) {
        this.titleS = titleS;
    }

    public String getContentS() {
        return contentS;
    }

    public void setContentS(String contentS) {
        this.contentS = contentS;
    }

    public String getImageS() {
        return imageS;
    }

    public void setImageS(String imageS) {
        this.imageS = imageS;
    }

    public String getTitleT() {
        return titleT;
    }

    public void setTitleT(String titleT) {
        this.titleT = titleT;
    }

    public String getContentT() {
        return contentT;
    }

    public void setContentT(String contentT) {
        this.contentT = contentT;
    }

    public String getImageT() {
        return imageT;
    }

    public void setImageT(String imageT) {
        this.imageT = imageT;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public Utilisateur getAuthor() {
        return author;
    }

    public void setAuthor(Utilisateur author) {
        this.author = author;
    }
}
