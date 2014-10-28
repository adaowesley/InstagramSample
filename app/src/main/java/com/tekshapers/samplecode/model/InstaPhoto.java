package com.tekshapers.samplecode.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wesley on 28/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstaPhoto {

    @JsonProperty("low_resolution")
    private Photo lowResolution;

    @JsonProperty("thumbnail")
    private Photo thumbnail;

    @JsonProperty("standard_resolution")
    private Photo standardResolution;

    public Photo getLowResolution() {
        return lowResolution;
    }

    public void setLowResolution(Photo lowResolution) {
        this.lowResolution = lowResolution;
    }

    public Photo getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Photo thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Photo getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(Photo standardResolution) {
        this.standardResolution = standardResolution;
    }
}
