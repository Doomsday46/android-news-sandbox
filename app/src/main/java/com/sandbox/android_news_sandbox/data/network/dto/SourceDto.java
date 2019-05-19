package com.sandbox.android_news_sandbox.data.network.dto;

import com.google.gson.annotations.Expose;

public class SourceDto {

    @Expose
    private String id;

    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
