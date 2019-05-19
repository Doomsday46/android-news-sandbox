package com.sandbox.android_news_sandbox.di;

import com.sandbox.android_news_sandbox.presentation.fragments.NewsFragment;
import com.sandbox.android_news_sandbox.presentation.fragments.TopFragment;

import dagger.Component;

@Component(modules = NewsModule.class)
public interface MainComponent {
    void inject(NewsFragment newsFragment);
    void inject(TopFragment topFragment);
}
