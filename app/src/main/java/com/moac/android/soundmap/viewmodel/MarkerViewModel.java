package com.moac.android.soundmap.viewmodel;

import com.moac.android.soundmap.api.model.SoundImageFormat;
import com.moac.android.soundmap.api.model.SoundJsonModel;

public final class MarkerViewModel {

    private final SoundJsonModel sound;
    private final SoundImageFormat displayedImageFormat;

    public MarkerViewModel(SoundJsonModel sound, SoundImageFormat displayedImageFormat) {
        this.sound = sound;
        this.displayedImageFormat = displayedImageFormat;
    }

    public SoundJsonModel getSound() {
        return sound;
    }

    public String getImageUrl() {
        return sound.getImages().get(displayedImageFormat);
    }

    public SoundImageFormat getDisplayedImageFormat() {
        return displayedImageFormat;
    }
}
