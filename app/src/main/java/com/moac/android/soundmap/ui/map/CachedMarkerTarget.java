package com.moac.android.soundmap.ui.map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * @author Peter Tackage
 * @since 16/04/15
 *
 * This is a wonderful abuse of the caching behavior in Picasso to display images inside a Google Maps
 * InfoWindow.
 * When an image is loading into a Google Maps InfoWindow from the network - i.e. asynchronously, then the ImageView will not
 * automatically be updated. The image MUST be loaded synchronously to achieve for the ImageView to
 * be updated.
 *
 * So by forcing a re-render after receiving a cache miss, then we get a cache hit - which is loaded
 * synchronously and hence the image is displayed.
 *
 * Very bad things could happen if the image loading never manages to return a cached response; this
 * will loop infinitely.
 */
public class CachedMarkerTarget implements Target {

    private final Marker marker;
    private final ImageView imageView;

    public CachedMarkerTarget(Marker marker, ImageView imageView) {
        this.marker = marker;
        this.imageView = imageView;
    }

    @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        if (from != Picasso.LoadedFrom.NETWORK) {
            this.imageView.setImageBitmap(bitmap);
        } else if (marker.isInfoWindowShown()) {
            marker.showInfoWindow(); // update rendered marker
        }
    }

    @Override public void onBitmapFailed(Drawable errorDrawable) {
        this.imageView.setImageDrawable(errorDrawable);
    }

    @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
        this.imageView.setImageDrawable(placeHolderDrawable);
    }
}
