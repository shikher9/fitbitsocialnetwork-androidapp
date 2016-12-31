package com.shikher.fitbitsocialnetwork.network;

import com.shikher.fitbitsocialnetwork.model.profile.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shikh on 26-Dec-16.
 */

public interface ProfileService {

    @GET("profile")
    Call<ProfileResponse> getProfile(@Query("userToken") String userToken);
}
