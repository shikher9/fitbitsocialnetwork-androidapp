
package com.shikher.fitbitsocialnetwork.model.todaystats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayStatsResponse {

    @SerializedName("recdate")
    @Expose
    private String recdate;
    @SerializedName("stepCount")
    @Expose
    private Integer stepCount;
    @SerializedName("caloriesBurnt")
    @Expose
    private Integer caloriesBurnt;
    @SerializedName("caloriesGoal")
    @Expose
    private Integer caloriesGoal;
    @SerializedName("waterConsumed")
    @Expose
    private Double waterConsumed;
    @SerializedName("waterGoal")
    @Expose
    private Integer waterGoal;

    public String getRecdate() {
        return recdate;
    }

    public void setRecdate(String recdate) {
        this.recdate = recdate;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public Integer getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(Integer caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public Integer getCaloriesGoal() {
        return caloriesGoal;
    }

    public void setCaloriesGoal(Integer caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }

    public Double getWaterConsumed() {
        return waterConsumed;
    }

    public void setWaterConsumed(Double waterConsumed) {
        this.waterConsumed = waterConsumed;
    }

    public Integer getWaterGoal() {
        return waterGoal;
    }

    public void setWaterGoal(Integer waterGoal) {
        this.waterGoal = waterGoal;
    }

}
