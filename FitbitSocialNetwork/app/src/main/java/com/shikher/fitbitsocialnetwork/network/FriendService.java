package com.shikher.fitbitsocialnetwork.network;

import com.shikher.fitbitsocialnetwork.model.Result;
import com.shikher.fitbitsocialnetwork.model.friend.FriendResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shikh on 25-Dec-16.
 */

public interface FriendService {


    @GET("getFriendSuggestions")
    Call<FriendResponse> getFriendSuggestions(@Query("userToken") String userToken);

    @GET("getFriendRequests")
    Call<FriendResponse> getFriendRequests(@Query("userToken") String userToken);

    @GET("getFriends")
    Call<FriendResponse> getFriends(@Query("userToken") String userToken);

    @POST("addFriend")
    Call<Result> addFriend(@Query("userToken") String userToken, @Query("friendEmail") String friendEmail);

    @POST("confirmFriend")
    Call<Result> confirmFriend(@Query("friendEmail") String friendEmail, @Query("actiontaken") boolean actiontaken, @Query("userToken") String userToken);

}
