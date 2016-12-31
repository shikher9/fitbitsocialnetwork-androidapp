
package com.shikher.fitbitsocialnetwork.model.lifetimestats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Best {

    @SerializedName("total")
    @Expose
    private Total total;
    @SerializedName("tracker")
    @Expose
    private Tracker tracker;

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

}
