
package com.shikher.fitbitsocialnetwork.model.lifetimestats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lifetime {

    @SerializedName("total")
    @Expose
    private Total_ total;
    @SerializedName("tracker")
    @Expose
    private Tracker_ tracker;

    public Total_ getTotal() {
        return total;
    }

    public void setTotal(Total_ total) {
        this.total = total;
    }

    public Tracker_ getTracker() {
        return tracker;
    }

    public void setTracker(Tracker_ tracker) {
        this.tracker = tracker;
    }

}
