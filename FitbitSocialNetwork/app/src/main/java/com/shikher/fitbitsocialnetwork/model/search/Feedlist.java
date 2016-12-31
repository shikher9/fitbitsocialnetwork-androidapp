
package com.shikher.fitbitsocialnetwork.model.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feedlist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("nfDateTime")
    @Expose
    private String nfDateTime;
    @SerializedName("likecount")
    @Expose
    private Integer likecount;
    @SerializedName("usercomments")
    @Expose
    private List<Usercomment> usercomments = null;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("imagesUrls")
    @Expose
    private List<Object> imagesUrls = null;
    @SerializedName("profileImageUrl")
    @Expose
    private String profileImageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNfDateTime() {
        return nfDateTime;
    }

    public void setNfDateTime(String nfDateTime) {
        this.nfDateTime = nfDateTime;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public List<Usercomment> getUsercomments() {
        return usercomments;
    }

    public void setUsercomments(List<Usercomment> usercomments) {
        this.usercomments = usercomments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Object> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(List<Object> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}
