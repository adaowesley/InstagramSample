package com.tekshapers.samplecode.http;

import android.content.Context;
import android.graphics.Bitmap;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.simple.BitmapRequest;
import com.tekshapers.samplecode.model.Photo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import roboguice.util.temp.Ln;

/**
 * Created by wesley on 28/10/14.
 */
public class ImageRequestFactory {

    private final Context context;

    private int targetWidth = -1;
    private int targetHeight = -1;
    private String photoSizeSuffix;

    public static final String SMALL_THUMB_SQUARE = "s";
    public static final String LARGE_THUMB_SQUARE = "q";
    public static final String THUMBNAIL = "t";
    public static final String SMALL_240 = "m";
    public static final String SMALL_320 = "n";
    public static final String MEDIUM_500 = "-";
    public static final String MEDIUM_640 = "z";
    public static final String MEDIUM_800 = "c";
    public static final String LARGE_1024 = "b";
    public static final String ORIGINAL = "o";

    public ImageRequestFactory(Context context) {
        this.context = context;
    }

    public ImageRequestFactory setSampleSize(int height, int width) {
        targetWidth = width;
        targetHeight = height;
        return this;
    }

    public ImageRequestFactory setPhotoFormat(String format) {
        photoSizeSuffix = format;
        return this;
    }

    public CachedSpiceRequest<Bitmap> create(Photo photoSource) {
        File cacheFile = null;
        String filename = null;
        try {
            filename = URLEncoder.encode(photoSource.getImageURL(), "UTF-8");
            cacheFile = new File(context.getCacheDir(), filename);
        } catch (UnsupportedEncodingException e) {
            Ln.e(e);
        }

        BitmapRequest request = new BitmapRequest(photoSource.getImageURL(), targetWidth, targetHeight, cacheFile);
        return new CachedSpiceRequest<Bitmap>(request, filename, DurationInMillis.ONE_MINUTE * 10);
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public int getTargetWidth() {
        return targetWidth;
    }
}
