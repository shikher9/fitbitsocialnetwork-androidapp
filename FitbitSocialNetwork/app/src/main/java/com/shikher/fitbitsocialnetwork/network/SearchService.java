package com.shikher.fitbitsocialnetwork.network;

import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;
import com.shikher.fitbitsocialnetwork.model.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shikh on 26-Dec-16.
 */

public interface SearchService {

    @GET("search/{searchTerm}")
    Call<SearchResponse> getSearchResult(@Path("searchTerm") String searchTerm, @Query("userToken") String userToken);

}
