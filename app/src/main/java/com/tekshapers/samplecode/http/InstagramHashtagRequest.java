package com.tekshapers.samplecode.http;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.tekshapers.samplecode.common.Constants;
import com.tekshapers.samplecode.model.InstaPhotoList;

/**
 * Created by wesley on 28/10/14.
 */
public class InstagramHashtagRequest extends SpringAndroidSpiceRequest<InstaPhotoList> {

    private String hashtag;

    public InstagramHashtagRequest(String hashtag){
        super(InstaPhotoList.class);
        this.hashtag = hashtag;
    }

    @Override
    public InstaPhotoList loadDataFromNetwork() throws Exception {
        String url = String.format("https://api.instagram.com/v1/tags/%s/media/recent?access_token=%s",
                this.hashtag, Constants.TOKEN);
        return getRestTemplate().getForObject(url, InstaPhotoList.class);
    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     * @return
     */
    public String createCacheKey() {
        return "hashtag." + hashtag + System.currentTimeMillis();
    }
}
