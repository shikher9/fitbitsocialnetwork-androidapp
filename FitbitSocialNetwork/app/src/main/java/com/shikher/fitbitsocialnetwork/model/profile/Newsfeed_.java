
package com.shikher.fitbitsocialnetwork.model.profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newsfeed_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nfDateTime")
    @Expose
    private String nfDateTime;
    @SerializedName("likecount")
    @Expose
    private Integer likecount;
    @SerializedName("profileImageUrl")
    @Expose
    private String profileImageUrl;
    @SerializedName("usercomments")
    @Expose
    private List<Usercomment> usercomments = null;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("imagesUrls")
    @Expose
    private List<Object> imagesUrls = null;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
