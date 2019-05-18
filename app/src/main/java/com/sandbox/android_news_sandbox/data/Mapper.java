package com.sandbox.android_news_sandbox.data;

public interface Mapper<T, R> {
    T map(R object);
}
