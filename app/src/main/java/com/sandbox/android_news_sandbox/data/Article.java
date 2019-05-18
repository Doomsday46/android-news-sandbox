package com.sandbox.android_news_sandbox.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Article {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;

    @SerializedName("content")
    @Expose
    private String text;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("publishedAt")
    @Expose
    private Date date;

    @SerializedName("source")
    @Expose
    private Source source;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Source getSource() {
        return source;
    }
}
