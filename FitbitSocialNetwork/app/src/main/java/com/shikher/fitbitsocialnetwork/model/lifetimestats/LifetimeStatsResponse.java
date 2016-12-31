
package com.shikher.fitbitsocialnetwork.model.lifetimestats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifetimeStatsResponse {

    @SerializedName("best")
    @Expose
    private Best best;
    @SerializedName("lifetime")
    @Expose
    private Lifetime lifetime;

    public Best getBest() {
        return best;
    }

    public void setBest(Best best) {
        this.best = best;
    }

    public Lifetime getLifetime() {
        return lifetime;
    }

    public void setLifetime(Lifetime lifetime) {
        this.lifetime = lifetime;
    }

}
