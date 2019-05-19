package com.sandbox.android_news_sandbox.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;

import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.presentation.fragments.NewsFragment;
import com.sandbox.android_news_sandbox.presentation.fragments.TopFragment;



public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, NewsFragment.OnRefreshFinishedListener  {

    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;
    private ShareActionProvider shareActionProvider;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar progressBar;
    private CategoryNews categoryNews;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.titles);

        progressBar = findViewById(R.id.progressBar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(
        Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        initDrawerToggle();
        initDrawerList();
        saveInstance(savedInstanceState);
        initBackStackChangedListener();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    private void initBackStackChangedListener(){
        getFragmentManager().addOnBackStackChangedListener(
                () -> {
                    FragmentManager fragMan = getFragmentManager();
                    Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                    if (fragment instanceof TopFragment) {
                        currentPosition = 0;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.SPORTS)) {
                        currentPosition = 1;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.BUSINESS)) {
                        currentPosition = 2;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.TECHNOLOGY)) {
                        currentPosition = 3;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.SCIENCE)) {
                        currentPosition = 4;
                    }
                    drawerList.setItemChecked(currentPosition, true);
                    setTitlesToBar(titles);
                }
        );
    }

    private void saveInstance(Bundle savedInstanceState){
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
            categoryNews = CategoryNews.valueOf(savedInstanceState.getString("category").toUpperCase());
            setTitlesToBar(titles);
            selectItem(currentPosition);
        } else {
            selectItem(0);
        }
    }

    private void initDrawerToggle(){
        drawerLayout = findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void initDrawerList(){
        drawerList = findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        //menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
        outState.putString("category", categoryNews.toString());
    }

    @Override
    public void onRefresh() {
        if (fragment instanceof NewsFragment)
            ((NewsFragment) fragment).update();
    }

    @Override
    public void onRefreshFinished() {
        new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
        }, 0);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    };

    private void selectItem(int position) {
        currentPosition = position;
        switch(position) {
            case 1:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.SPORTS);
                categoryNews = CategoryNews.SPORTS;
                break;
            case 2:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.BUSINESS);
                categoryNews = CategoryNews.BUSINESS;
                break;
            case 3:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.TECHNOLOGY);
                categoryNews = CategoryNews.TECHNOLOGY;
                break;
            case 4:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.SCIENCE);
                categoryNews = CategoryNews.SCIENCE;
                break;
            default:
                fragment = new TopFragment();
                ((TopFragment) fragment).setCategoryNews(CategoryNews.UNDEFINED);
                categoryNews = CategoryNews.UNDEFINED;
        }
        if (fragment instanceof NewsFragment) {
            ((NewsFragment) fragment).setOnRefreshFinishedListener(this);
            mSwipeRefreshLayout.setEnabled(true);
        }
        else mSwipeRefreshLayout.setEnabled(false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        setTitlesToBar(titles);

        drawerLayout.closeDrawer(drawerList);
    }

    private void setTitlesToBar(String[] titles) {
        if (currentPosition < titles.length) {
            String title = titles[currentPosition];
            getActionBar().setTitle(title);
        } else {
            getActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }
}
