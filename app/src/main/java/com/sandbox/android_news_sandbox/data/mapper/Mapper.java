package com.sandbox.android_news_sandbox.data.mapper;

public interface Mapper<T, R> {
    T map(R object);
}
