package com.sandbox.android_news_sandbox.model;

import java.util.Date;

public class News {

    private String nameResource;
    private String author;
    private String title;
    private String urlImage;
    private String content;
    private Date date;

    public News(String nameResource, String title, String content, Date date) {
        this.nameResource = nameResource;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = "";
        this.urlImage = "";
    }

    public News(String nameResource, String title, String image, String content, Date date) {
        this.nameResource = nameResource;
        this.title = title;
        this.urlImage = image;
        this.content = content;
        this.date = date;
        this.author = "";
    }

    public News(String nameResource, String author, String title, String image, String content, Date date) {
        this.nameResource = nameResource;
        this.author = author;
        this.title = title;
        this.urlImage = image;
        this.content = content;
        this.date = date;
    }

    public String getNameResource() {
        return nameResource;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
