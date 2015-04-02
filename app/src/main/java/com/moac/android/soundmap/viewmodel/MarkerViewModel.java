package com.moac.android.soundmap.viewmodel;

import android.graphics.Bitmap;

import com.moac.android.soundmap.model.Track;

public class MarkerViewModel {

    private final Track track;
    private Bitmap avatarBitmap;

    public MarkerViewModel(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public Bitmap getAvatarBitmap() {
        return avatarBitmap;
    }

    public void setAvatarBitmap(Bitmap avatarBitmap) {
        this.avatarBitmap = avatarBitmap;
    }
}
