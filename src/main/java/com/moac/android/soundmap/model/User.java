package com.moac.android.soundmap.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username") protected String mUsername;
    @SerializedName("avatar_url") protected String mAvatarUrl;
    @SerializedName("uri") protected String mUri;

    public String getUsername() { return mUsername; }
    public String getAvatarUrl() { return mAvatarUrl; }
    public String getUri() { return mUri; }
}
