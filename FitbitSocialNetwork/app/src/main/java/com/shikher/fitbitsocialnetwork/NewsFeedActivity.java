package com.shikher.fitbitsocialnetwork;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shikher.fitbitsocialnetwork.fragment.NewsFeedFragment;
import com.shikher.fitbitsocialnetwork.fragment.RankingFragment;
import com.shikher.fitbitsocialnetwork.fragment.SearchFragment;
import com.shikher.fitbitsocialnetwork.fragment.friends.ConfirmFriendsFragment;
import com.shikher.fitbitsocialnetwork.fragment.friends.FriendSuggestionsFragment;
import com.shikher.fitbitsocialnetwork.fragment.friends.FriendsFragment;
import com.shikher.fitbitsocialnetwork.fragment.profile.BadgesFragment;
import com.shikher.fitbitsocialnetwork.fragment.profile.DetailedStatsFragment;
import com.shikher.fitbitsocialnetwork.fragment.profile.ProfileFragment;
import com.shikher.fitbitsocialnetwork.fragment.stats.LifetimeStatsFragment;
import com.shikher.fitbitsocialnetwork.fragment.stats.TodayStatsFragment;
import com.shikher.fitbitsocialnetwork.model.auth.LoginRequest;
import com.shikher.fitbitsocialnetwork.model.auth.LoginResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.Newsfeed;
import com.shikher.fitbitsocialnetwork.network.AuthService;
import com.shikher.fitbitsocialnetwork.network.NewsFeedService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActionBar actionBar;
    String userEmail;
    String userToken;
    FragmentManager fragmentManager;
    FrameLayout contentNewsFeedFrameLayout;
    NFService myBoundService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        /**
         * Set  image loader configuration
         */
        Utility.setImageLoaderConfiguration(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //get fragment manager and fragment container
        fragmentManager = getSupportFragmentManager();
        contentNewsFeedFrameLayout = (FrameLayout) findViewById(R.id.content_news_feed);

        //get email and token
        SharedPreferences sharedPreferences = Utility.getSharedPreferences(NewsFeedActivity.this);
        userEmail = sharedPreferences.getString("userEmail", "none");
        userToken = sharedPreferences.getString("userToken", "none");

        //initialize widgets
        initializeWidgets(sharedPreferences, navigationView);

        //start service
        Intent intent = new Intent(this, NFService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Utility.setImageLoaderConfiguration(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();

            new AlertDialog.Builder(this)
                    .setTitle("LOGOUT")
                    .setMessage("Do you want to Logout?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_friend_suggestions) {
            actionBar.setTitle("Friend Suggestions");
            addFragment(FriendSuggestionsFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_confirm_friends) {
            actionBar.setTitle("Confirm Friends");
            addFragment(ConfirmFriendsFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_friends) {
            actionBar.setTitle("Friends");
            addFragment(FriendsFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_profile) {
            actionBar.setTitle("Profile");
            addFragment(ProfileFragment.newInstance(userEmail, userToken, false));
        } else if (id == R.id.nav_search) {
            actionBar.setTitle("Search");
            addFragment(SearchFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_ligetime_stats) {
            actionBar.setTitle("Lifetime Stats");
            addFragment(LifetimeStatsFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_today_stats) {
            actionBar.setTitle("Today Stats");
            addFragment(TodayStatsFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_newsfeed) {
            actionBar.setTitle("News Feed");
            addFragment(NewsFeedFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_badges) {
            actionBar.setTitle("Badges");
            addFragment(BadgesFragment.newInstance(userEmail, userToken));
        } else if (id == R.id.nav_logout) {
            //logout - go back to login activity
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initializeWidgets(SharedPreferences sharedPreferences, NavigationView navigationView) {
        View header = navigationView.getHeaderView(0);
        TextView navHeaderEmailTextView = (TextView) header.findViewById(R.id.navHeaderEmailTextView);
        navHeaderEmailTextView.setText(sharedPreferences.getString("userEmail", "none"));

        //adding default news feed fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_news_feed);

        if (fragment == null) {
            fragment = NewsFeedFragment.newInstance(userEmail, userToken);
            fragmentManager.beginTransaction().add(R.id.content_news_feed, fragment).commit();
        }
    }

    public void addFragment(Fragment fragment) {
        //get existing fragment
        Fragment existingFragment = fragmentManager.findFragmentById(R.id.content_news_feed);

        if (existingFragment == null) {
            fragmentManager.beginTransaction().add(R.id.content_news_feed, fragment).commit();
        } else {
            fragmentManager.beginTransaction().remove(existingFragment).commit();
            fragmentManager.beginTransaction().add(R.id.content_news_feed, fragment).commit();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NFService.NewsFeedServiceBinder binder = (NFService.NewsFeedServiceBinder) service;
            myBoundService = binder.getService();
            myBoundService.checkNewsFeed();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }


}
