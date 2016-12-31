
package com.shikher.fitbitsocialnetwork.model.profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopBadge {

    @SerializedName("badgeGradientEndColor")
    @Expose
    private String badgeGradientEndColor;
    @SerializedName("badgeGradientStartColor")
    @Expose
    private String badgeGradientStartColor;
    @SerializedName("badgeType")
    @Expose
    private String badgeType;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cheers")
    @Expose
    private List<Object> cheers = null;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("earnedMessage")
    @Expose
    private String earnedMessage;
    @SerializedName("encodedId")
    @Expose
    private String encodedId;
    @SerializedName("image100px")
    @Expose
    private String image100px;
    @SerializedName("image125px")
    @Expose
    private String image125px;
    @SerializedName("image300px")
    @Expose
    private String image300px;
    @SerializedName("image50px")
    @Expose
    private String image50px;
    @SerializedName("image75px")
    @Expose
    private String image75px;
    @SerializedName("marketingDescription")
    @Expose
    private String marketingDescription;
    @SerializedName("mobileDescription")
    @Expose
    private String mobileDescription;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shareImage640px")
    @Expose
    private String shareImage640px;
    @SerializedName("shareText")
    @Expose
    private String shareText;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("timesAchieved")
    @Expose
    private Integer timesAchieved;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getBadgeGradientEndColor() {
        return badgeGradientEndColor;
    }

    public void setBadgeGradientEndColor(String badgeGradientEndColor) {
        this.badgeGradientEndColor = badgeGradientEndColor;
    }

    public String getBadgeGradientStartColor() {
        return badgeGradientStartColor;
    }

    public void setBadgeGradientStartColor(String badgeGradientStartColor) {
        this.badgeGradientStartColor = badgeGradientStartColor;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Object> getCheers() {
        return cheers;
    }

    public void setCheers(List<Object> cheers) {
        this.cheers = cheers;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEarnedMessage() {
        return earnedMessage;
    }

    public void setEarnedMessage(String earnedMessage) {
        this.earnedMessage = earnedMessage;
    }

    public String getEncodedId() {
        return encodedId;
    }

    public void setEncodedId(String encodedId) {
        this.encodedId = encodedId;
    }

    public String getImage100px() {
        return image100px;
    }

    public void setImage100px(String image100px) {
        this.image100px = image100px;
    }

    public String getImage125px() {
        return image125px;
    }

    public void setImage125px(String image125px) {
        this.image125px = image125px;
    }

    public String getImage300px() {
        return image300px;
    }

    public void setImage300px(String image300px) {
        this.image300px = image300px;
    }

    public String getImage50px() {
        return image50px;
    }

    public void setImage50px(String image50px) {
        this.image50px = image50px;
    }

    public String getImage75px() {
        return image75px;
    }

    public void setImage75px(String image75px) {
        this.image75px = image75px;
    }

    public String getMarketingDescription() {
        return marketingDescription;
    }

    public void setMarketingDescription(String marketingDescription) {
        this.marketingDescription = marketingDescription;
    }

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShareImage640px() {
        return shareImage640px;
    }

    public void setShareImage640px(String shareImage640px) {
        this.shareImage640px = shareImage640px;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getTimesAchieved() {
        return timesAchieved;
    }

    public void setTimesAchieved(Integer timesAchieved) {
        this.timesAchieved = timesAchieved;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
