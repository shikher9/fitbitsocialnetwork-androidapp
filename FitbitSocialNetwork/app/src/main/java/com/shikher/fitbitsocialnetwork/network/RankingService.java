package com.shikher.fitbitsocialnetwork.network;

import com.shikher.fitbitsocialnetwork.model.newsfeed.RankingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shikh on 26-Dec-16.
 */

public interface RankingService {

    @GET("rank")
    Call<List<RankingResponse>> getRanking(@Query("userToken") String userToken);
}
