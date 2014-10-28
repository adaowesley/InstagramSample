package com.tekshapers.samplecode.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wesley on 28/10/14.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    @JsonProperty("url")
    private String imageURL;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
