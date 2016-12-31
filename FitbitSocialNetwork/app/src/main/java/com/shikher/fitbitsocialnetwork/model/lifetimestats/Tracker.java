
package com.shikher.fitbitsocialnetwork.model.lifetimestats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracker {

    @SerializedName("distance")
    @Expose
    private Distance_ distance;
    @SerializedName("steps")
    @Expose
    private Steps_ steps;

    public Distance_ getDistance() {
        return distance;
    }

    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    public Steps_ getSteps() {
        return steps;
    }

    public void setSteps(Steps_ steps) {
        this.steps = steps;
    }

}
