
package com.shikher.fitbitsocialnetwork.model.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("userlist")
    @Expose
    private List<Userlist> userlist = null;
    @SerializedName("feedlist")
    @Expose
    private List<Feedlist> feedlist = null;

    public List<Userlist> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<Userlist> userlist) {
        this.userlist = userlist;
    }

    public List<Feedlist> getFeedlist() {
        return feedlist;
    }

    public void setFeedlist(List<Feedlist> feedlist) {
        this.feedlist = feedlist;
    }

}
