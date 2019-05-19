package com.sandbox.android_news_sandbox.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    Single<List<NewsEntity>> getAll();

    @Insert
    void insertAll(NewsEntity... users);

    @Delete
    void delete(NewsEntity user);
}
