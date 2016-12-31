
package com.shikher.fitbitsocialnetwork.model.friend;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendResponse {

    @SerializedName("suggestlist")
    @Expose
    private List<Suggestlist> suggestlist = null;

    public List<Suggestlist> getSuggestlist() {
        return suggestlist;
    }

    public void setSuggestlist(List<Suggestlist> suggestlist) {
        this.suggestlist = suggestlist;
    }

}
