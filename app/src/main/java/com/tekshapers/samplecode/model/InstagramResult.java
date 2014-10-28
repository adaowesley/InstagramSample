package com.tekshapers.samplecode.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by wesley on 28/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramResult {

    private InstaPhoto images;

    public InstaPhoto getImages() {
        return images;
    }

    public void setImages(InstaPhoto images) {
        this.images = images;
    }
}
