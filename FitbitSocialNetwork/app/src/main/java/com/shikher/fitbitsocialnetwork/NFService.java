package com.shikher.fitbitsocialnetwork;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;
import com.shikher.fitbitsocialnetwork.network.NewsFeedService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Service which checks for newsfeed updates
 */
public class NFService extends Service {

    private final IBinder newsFeedBinder = new NewsFeedServiceBinder();
    private static int lastTotalNewsFeedItems = 0;
    private String userToken = null;
    private static final String TAG = "NF SERVICE";
    public static boolean result = false;
    public static boolean checkNewsFeed = true;


    public NFService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return newsFeedBinder;
    }

    public void checkNewsFeed() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true && checkNewsFeed) {
                        checkNewsFeedUpdate();
                        Thread.sleep(6 * 1000);
                    }
                } catch (InterruptedException ex) {
                    Log.d(TAG, "Newsfeed Update Thread interrupted");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void checkNewsFeedUpdate() {

        Log.d(TAG, "Checking for newsfeed update : " + new Date().toString());
        userToken = Utility.getSharedPreferences(this).getString("userToken", "");
        NewsFeedService newsFeedService = Utility.retrofit.create(NewsFeedService.class);
        Call<NewsFeedResponse> newsFeedResponseCall = newsFeedService.getNewsFeed(1, userToken);
        newsFeedResponseCall.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(Call<NewsFeedResponse> call, Response<NewsFeedResponse> response) {
                int totalFeedItems = response.body().getNewsFeedEntries();
                if (totalFeedItems > lastTotalNewsFeedItems) {
                    lastTotalNewsFeedItems = totalFeedItems;
                    result = true;
                    Toast.makeText(NFService.this, "Newsfeed Updates Available", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Newsfeed Updates Available");
                } else {
                    result = false;
                    Log.d(TAG, "No Newsfeed Updates Available");
                }
            }

            @Override
            public void onFailure(Call<NewsFeedResponse> call, Throwable t) {
                Log.d(TAG, "NEWSFEED FETCH FAILED");
            }
        });

    }


    public class NewsFeedServiceBinder extends Binder {

        NFService getService() {
            return NFService.this;
        }
    }
}
