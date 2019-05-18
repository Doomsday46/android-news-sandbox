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
import android.widget.ShareActionProvider;

import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.presentation.fragments.NewsFragment;
import com.sandbox.android_news_sandbox.presentation.fragments.TopFragment;



public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener  {

    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;
    private ShareActionProvider shareActionProvider;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.titles);


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

    private void initBackStackChangedListener(){
        getFragmentManager().addOnBackStackChangedListener(
                () -> {
                    FragmentManager fragMan = getFragmentManager();
                    Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                    if (fragment instanceof TopFragment) {
                        currentPosition = 4;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.SPORTS)) {
                        currentPosition = 0;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.BUSINESS)) {
                        currentPosition = 1;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.TECHNOLOGY)) {
                        currentPosition = 2;
                    }
                    if (fragment instanceof NewsFragment && ((NewsFragment) fragment).getCategoryNews().equals(CategoryNews.SCIENCE)) {
                        currentPosition = 3;
                    }
                    drawerList.setItemChecked(currentPosition, true);
                }
        );
    }

    private void saveInstance(Bundle savedInstanceState){
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
        } else {
            selectItem(4);
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
    }



    @Override
    public void onRefresh() {
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
        Fragment fragment;
        switch(position) {
            case 0:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.SPORTS);
                break;
            case 1:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.BUSINESS);
                break;
            case 2:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.TECHNOLOGY);
                break;
            case 3:
                fragment = new NewsFragment();
                ((NewsFragment) fragment).setCategoryNews(CategoryNews.SCIENCE);
                break;
            default:
                fragment = new TopFragment();
                ((TopFragment) fragment).setCategoryNews(CategoryNews.UNDEFINED);
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        drawerLayout.closeDrawer(drawerList);
    }
}
