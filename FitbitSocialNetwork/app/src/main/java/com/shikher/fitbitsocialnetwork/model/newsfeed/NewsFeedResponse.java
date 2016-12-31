
package com.shikher.fitbitsocialnetwork.model.newsfeed;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsFeedResponse {

    @SerializedName("newsfeed")
    @Expose
    private List<Newsfeed> newsfeed = null;
    @SerializedName("newsFeedEntries")
    @Expose
    private Integer newsFeedEntries;

    public List<Newsfeed> getNewsfeed() {
        return newsfeed;
    }

    public void setNewsfeed(List<Newsfeed> newsfeed) {
        this.newsfeed = newsfeed;
    }

    public Integer getNewsFeedEntries() {
        return newsFeedEntries;
    }

    public void setNewsFeedEntries(Integer newsFeedEntries) {
        this.newsFeedEntries = newsFeedEntries;
    }

}
