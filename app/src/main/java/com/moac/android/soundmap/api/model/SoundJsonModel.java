package com.moac.android.soundmap.api.model;

import com.google.gson.annotations.SerializedName;
import com.moac.android.soundmap.model.GeoLocation;

import java.util.List;
import java.util.Map;

public final class SoundJsonModel {

    @SerializedName("id") long id; // The sound’s unique identifier.
    @SerializedName("url") String url; // The URI for this sound on the Freesound website.
    @SerializedName("name") String name; // The name user gave to the sound.
    @SerializedName("tags") List<String> tags; // An array of tags the user gave to the sound.
    @SerializedName("description") String description; // The description the user gave to the sound.
    @SerializedName("geotag") GeoLocation geotag; // Latitude and longitude of the geotag separated by spaces (e.g. “41.0082325664 28.9731252193”, only for sounds that have been geotagged).
    @SerializedName("username") String username; // The username of the uploader of the sound.
    @SerializedName("images") Map<SoundImageFormat, String> images; // Dictionary including the URIs for spectrogram and waveform visualizations of the sound. The dictionary includes the fields waveform_l and waveform_m (for large and medium waveform images respectively), and spectral_l and spectral_m (for large and medium spectrogram images respectively).

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public GeoLocation getGeotag() {
        return geotag;
    }

    public Map<SoundImageFormat, String> getImages() {
        return images;
    }

    public String getUsername() {
        return username;
    }
}
