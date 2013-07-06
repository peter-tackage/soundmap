package com.moac.android.soundmap.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id") protected String mId;
    @SerializedName("username") protected String mUsername;
    @SerializedName("avatar_url") protected String mAvatarUrl;
    @SerializedName("uri") protected String mUri;
    @SerializedName("country") protected String mCountry;
    @SerializedName("city") protected String mCity;

    public String getId() { return mId; }
    public String getUsername() { return mUsername; }
    public String getAvatarUrl() { return mAvatarUrl; }
    public String getUri() { return mUri; }
    public String getCountry() { return mCountry; }
    public String getCity() { return mCity;}
}
