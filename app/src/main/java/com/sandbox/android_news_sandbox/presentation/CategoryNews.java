package com.sandbox.android_news_sandbox.presentation;

public enum CategoryNews {
    SPORTS,
    SCIENCE,
    TECHNOLOGY,
    BUSINESS,
    UNDEFINED;

    public String toString() {
        return name().toLowerCase();
    }



}
