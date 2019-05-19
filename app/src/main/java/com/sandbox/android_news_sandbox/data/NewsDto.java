package com.sandbox.android_news_sandbox.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsDto {

    @Expose
    private int totalResults;

    @SerializedName("articles")
    @Expose
    private List<ArticleDto> articleDtoList;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleDto> getArticleDtoList() {
        return articleDtoList;
    }

    public void setArticleDtoList(List<ArticleDto> articleDtoList) {
        this.articleDtoList = articleDtoList;
    }

}
