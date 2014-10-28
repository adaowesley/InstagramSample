package com.tekshapers.samplecode.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by wesley on 28/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstaPhotoList{

    private List<InstagramResult> data;

    public List<InstagramResult> getData() {
        return data;
    }

    public void setData(List<InstagramResult> data) {
        this.data = data;
    }
}
