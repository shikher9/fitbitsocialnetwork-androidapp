
package com.shikher.fitbitsocialnetwork.model.newsfeed;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeUnlikeResponse {

    @SerializedName("likeCount")
    @Expose
    private Integer likeCount;
    @SerializedName("likestatus")
    @Expose
    private Boolean likestatus;
    @SerializedName("emailList")
    @Expose
    private List<String> emailList = null;

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Boolean getLikestatus() {
        return likestatus;
    }

    public void setLikestatus(Boolean likestatus) {
        this.likestatus = likestatus;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

}
