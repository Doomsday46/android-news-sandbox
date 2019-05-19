package com.sandbox.android_news_sandbox;

import android.app.Application;

import com.sandbox.android_news_sandbox.di.DaggerMainComponent;
import com.sandbox.android_news_sandbox.di.MainComponent;
import com.sandbox.android_news_sandbox.di.NewsModule;

public class App extends Application {

    private static MainComponent mainComponent;

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder().newsModule(new NewsModule(this)).build();
    }


}
