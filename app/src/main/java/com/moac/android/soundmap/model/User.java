package com.moac.android.soundmap.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("uri")
    private String uri;
    @SerializedName("country")
    private String country;
    @SerializedName("city")
    private String city;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUri() {
        return uri;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
