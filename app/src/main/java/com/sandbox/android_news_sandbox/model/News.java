package com.sandbox.android_news_sandbox.model;

import java.util.Date;

public class News {

    private String nameResource;
    private String author;
    private String title;
    private String urlImage;
    private String urlArticle;
    private String content;
    private String description;
    private Date date;

    public News(String nameResource, String author, String title, String urlImage, String urlArticle, String content, String description, Date date) {
        this.nameResource = nameResource;
        this.author = author;
        this.title = title;
        this.urlImage = urlImage;
        this.urlArticle = urlArticle;
        this.content = content;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getUrlArticle() {
        return urlArticle;
    }
}
