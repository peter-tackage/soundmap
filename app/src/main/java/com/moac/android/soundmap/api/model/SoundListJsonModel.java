package com.moac.android.soundmap.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class SoundListJsonModel {

    @SerializedName("count") int count;
    @SerializedName("next") String next;
    @SerializedName("results") List<SoundJsonModel> results;
    @SerializedName("previous") String previous;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<SoundJsonModel> getResults() {
        return results;
    }

    public String getPrevious() {
        return previous;
    }
}
