package com.moac.android.soundmap.model;

import com.google.gson.annotations.SerializedName;

public class Track {

    /**
     * I like these Android style field names and GSON doesn't support these out of
     * the box with the existing FieldNamingPolicy strategies.
     *
     * A solution would be to write a FieldNamingStrategy to handle the "m"
     * and then enforce camel case from the remainer of the String and configure
     * the GSON builder to use this.
     *
     * The policy would be a bit like -
     * http://code.google.com/p/google-gson/source/browse/trunk/gson/src/main/java/com/google/gson/ModifyFirstLetterNamingPolicy.java?r=1031
     *
     * This would allow GSON to deserialise without annotations of the model classes.
     *
     * This is all well and good, but I prefer to be able to explicitly see the corresponding
     * JSON field name anyway, even at the cost of an annotation dependency and reduced
     * deserialisation flexibility (by hardcoding the fields you lose the ability to
     * configure the deserialisation policy of the GSON instance .. or do you?)
     */

    @SerializedName("id") protected String mId;
    @SerializedName("title") protected String mTitle;
    @SerializedName("user") protected User mUser;
    @SerializedName("artwork_url") protected String mArtworkUrl;
    @SerializedName("waveform_url") protected String mWaveformUrl;
    @SerializedName("permalink_url") protected String mPermalinkUrl;
    @SerializedName("tag_list") protected GeoLocation mGeoLocation;

    public String getId() { return mId; }

    public String getTitle() { return mTitle; }

    public User getUser() { return mUser; }

    public String getArtworkUrl() { return mArtworkUrl; }

    public String getWaveformUrl() { return mWaveformUrl; }

    public String getPermalinkUrl() { return mPermalinkUrl; }

    public GeoLocation getGeoLocation() { return mGeoLocation;}
}
