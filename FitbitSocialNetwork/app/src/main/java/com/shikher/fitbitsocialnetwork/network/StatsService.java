package com.shikher.fitbitsocialnetwork.network;

import com.shikher.fitbitsocialnetwork.model.lifetimestats.LifetimeStatsResponse;
import com.shikher.fitbitsocialnetwork.model.todaystats.TodayStatsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shikh on 25-Dec-16.
 */

public interface StatsService {

    @GET("profile/lifetimeStats")
    Call<LifetimeStatsResponse> getLifetimeStats(@Query("userToken") String userToken);

    @GET("dashboard")
    Call<TodayStatsResponse> getTodayStats(@Query("userToken") String userToken);
}
