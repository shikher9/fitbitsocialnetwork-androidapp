package com.shikher.fitbitsocialnetwork.network;


import com.shikher.fitbitsocialnetwork.model.auth.LoginRequest;
import com.shikher.fitbitsocialnetwork.model.auth.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by shikh on 23-Dec-16.
 */

public interface AuthService {

    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

}
