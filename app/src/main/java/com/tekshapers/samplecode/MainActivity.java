package com.tekshapers.samplecode;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.tekshapers.samplecode.adapters.InstagramPhotoAdapter;
import com.tekshapers.samplecode.http.ImageRequestFactory;
import com.tekshapers.samplecode.http.InstagramHashtagRequest;
import com.tekshapers.samplecode.model.InstaPhotoList;
import com.tekshapers.samplecode.model.Photo;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private SpiceManager spiceManager = new SpiceManager(
            JacksonSpringAndroidSpiceService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        handleIntent(getIntent());
    }

    private void performRequest(String hashtag) {
        MainActivity.this.setSupportProgressBarIndeterminateVisibility(true);

        InstagramHashtagRequest request = new InstagramHashtagRequest(hashtag);
        String lastRequestCacheKey = request.createCacheKey();
        spiceManager.execute(request, lastRequestCacheKey,
                DurationInMillis.ONE_MINUTE, new InstaPhotoRequestListener());
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            performRequest(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            ListView list = (ListView) rootView.findViewById(R.id.fragment_main_listview);

            ImageRequestFactory imageRequestFactory = new ImageRequestFactory(this.getActivity());
            imageRequestFactory.setPhotoFormat(ImageRequestFactory.LARGE_THUMB_SQUARE);

            InstagramPhotoAdapter adapter = new InstagramPhotoAdapter(this.getActivity(),
                    R.layout.item_listview_photo, R.id.image, new ArrayList<Photo>(),
                    imageRequestFactory);

            list.setAdapter(adapter);
            list.setOnScrollListener(adapter);

            return rootView;
        }
    }

    private class InstaPhotoRequestListener implements RequestListener<InstaPhotoList>{

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(MainActivity.this,
                    getResources().getText(R.string.request_error) +
                            spiceException.getLocalizedMessage(), Toast.LENGTH_LONG)
                    .show();
            MainActivity.this.setSupportProgressBarIndeterminateVisibility(false);
        }

        @Override
        public void onRequestSuccess(InstaPhotoList instaPhotos) {

            Log.d("eh nois", "eh nois");
        }
    }
}
