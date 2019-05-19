package com.sandbox.android_news_sandbox.data.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ArticleDto {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;

    @SerializedName("url")
    @Expose
    private String urlArticale;

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
    private SourceDto source;

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

    public SourceDto getSource() {
        return source;
    }

    public String getUrlArticale() {
        return urlArticale;
    }
}
