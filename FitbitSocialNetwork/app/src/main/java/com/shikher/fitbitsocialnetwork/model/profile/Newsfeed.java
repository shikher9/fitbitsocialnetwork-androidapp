
package com.shikher.fitbitsocialnetwork.model.profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newsfeed {

    @SerializedName("newsfeed")
    @Expose
    private List<Newsfeed_> newsfeed = null;
    @SerializedName("newsFeedEntries")
    @Expose
    private Integer newsFeedEntries;

    public List<Newsfeed_> getNewsfeed() {
        return newsfeed;
    }

    public void setNewsfeed(List<Newsfeed_> newsfeed) {
        this.newsfeed = newsfeed;
    }

    public Integer getNewsFeedEntries() {
        return newsFeedEntries;
    }

    public void setNewsFeedEntries(Integer newsFeedEntries) {
        this.newsFeedEntries = newsFeedEntries;
    }

}
