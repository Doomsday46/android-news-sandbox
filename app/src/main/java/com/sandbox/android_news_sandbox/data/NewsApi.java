package com.sandbox.android_news_sandbox.data;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

        @GET("top-headlines")
        Observable<NewsDto> getTopNews(@Query("apiKey") String key);

        @GET("top-headlines")
        Observable<NewsDto> getTopNews(@Query("category") String category,
                                       @Query("apiKey") String key);

        @GET("top-headlines")
        Observable<NewsDto> getTopNews(@Query("country") String country,
                                       @Query("category") String category,
                                       @Query("apiKey") String key);

        @GET("everything")
        Observable<NewsDto> getEverythingNews(@Query("apiKey") String key);

        @GET("everything")
        Observable<NewsDto> getEverythingNews(@Query("category") String category,
                                       @Query("apiKey") String key);

        @GET("everything")
        Observable<NewsDto> getEverythingNews(@Query("country") String country,
                                       @Query("category") String category,
                                       @Query("apiKey") String key);

}
