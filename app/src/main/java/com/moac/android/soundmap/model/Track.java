package com.moac.android.soundmap.model;

import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("user")
    private User user;
    @SerializedName("artwork_url")
    private String artworkUrl;
    @SerializedName("waveform_url")
    private String waveformUrl;
    @SerializedName("permalink_url")
    private String permalinkUrl;
    @SerializedName("tag_list")
    private GeoLocationTag geoLocationTag;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public GeoLocationTag getGeoLocationTag() {
        return geoLocationTag;
    }

}
