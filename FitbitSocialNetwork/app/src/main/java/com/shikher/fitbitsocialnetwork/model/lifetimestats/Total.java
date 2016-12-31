
package com.shikher.fitbitsocialnetwork.model.lifetimestats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total {

    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("steps")
    @Expose
    private Steps steps;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Steps getSteps() {
        return steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

}
