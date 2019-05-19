package com.sandbox.android_news_sandbox.di;

import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.sandbox.android_news_sandbox.data.database.AppDatabase;
import com.sandbox.android_news_sandbox.data.database.NewsEntity;
import com.sandbox.android_news_sandbox.data.mapper.Mapper;
import com.sandbox.android_news_sandbox.data.mapper.NewsEntityToNewsMapper;
import com.sandbox.android_news_sandbox.data.mapper.NewsToNewsEntityMapper;
import com.sandbox.android_news_sandbox.data.network.repository.NewsRepository;
import com.sandbox.android_news_sandbox.data.network.repository.NewsRepositoryImpl;
import com.sandbox.android_news_sandbox.model.News;
import com.sandbox.android_news_sandbox.model.NewsInteractor;
import com.sandbox.android_news_sandbox.model.NewsInteractorImpl;
import com.sandbox.android_news_sandbox.presentation.presenter.NewsPresenter;
import com.sandbox.android_news_sandbox.presentation.presenter.Presenter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;


@Module
public class NewsModule {

    private Context applicationContext;

    public NewsModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    public Presenter providePresenter(NewsInteractor newsInteractor) {
        return new NewsPresenter(newsInteractor);
    }

    @Provides
    public NewsInteractor provideNewsInteractor(NewsRepository newsRepository, AppDatabase appDatabase,
                                                Mapper<List<NewsEntity>, List<News>> newsToNewsEntityMapper, Mapper<List<News>, List<NewsEntity>>  newsEntityToNewsMapper) {
        return new NewsInteractorImpl(newsRepository, appDatabase, newsToNewsEntityMapper, newsEntityToNewsMapper);
    }

    @Provides
    public NewsRepository provideNewsRepository() {
        return new NewsRepositoryImpl();
    }

    @Provides
    public AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(applicationContext,
                AppDatabase.class, "sandbox-db").build();
    }

    @Provides
    public Mapper<List<NewsEntity>, List<News>> provideNewsToNewsEntityMapper() {
        return new NewsToNewsEntityMapper();
    }

    @Provides
    public Mapper<List<News>, List<NewsEntity>>  provideNewsEntityToNewsMapper() {
        return new NewsEntityToNewsMapper();
    }
}
