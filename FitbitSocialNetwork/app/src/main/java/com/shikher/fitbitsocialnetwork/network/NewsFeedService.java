package com.shikher.fitbitsocialnetwork.network;

import com.shikher.fitbitsocialnetwork.model.auth.LoginRequest;
import com.shikher.fitbitsocialnetwork.model.auth.LoginResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.LikeUnlikeResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shikh on 24-Dec-16.
 */

public interface NewsFeedService {

    @GET("newsfeed/{pagenumber}")
    Call<NewsFeedResponse> getNewsFeed(@Path("pagenumber") int pagenumber, @Query("userToken") String userToken);

    @GET("newsfeed/{id}/like")
    Call<LikeUnlikeResponse> doLike(@Path("id") int newsFeedId, @Query("userToken") String userToken);

    @GET("newsfeed/{id}/unlike")
    Call<LikeUnlikeResponse> doUnLike(@Path("id") int newsFeedId, @Query("userToken") String userToken);

    @GET("newsfeed/{id}/getlikes")
    Call<LikeUnlikeResponse> getLikes(@Path("id") int newsFeedId, @Query("userToken") String userToken);
}
