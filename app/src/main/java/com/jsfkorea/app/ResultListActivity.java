package com.jsfkorea.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Do-Gyu on 2016-07-07.
 */
public class ResultListActivity extends AbstractAsyncListActivity {

    protected static final String TAG = ResultListActivity.class.getSimpleName();

    // ***************************************
    // Activity methods
    // ***************************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Words words =  (Words)this.getIntent().getExtras().get("words");

        WordsListAdapter adapter = new WordsListAdapter(this, words.getWords());
        setListAdapter(adapter);
    }

    // ***************************************
    // Private methods
    // ***************************************
    private void refreshStates(List<Word> words) {
        if (words == null) {
            return;
        }

        WordsListAdapter adapter = new WordsListAdapter(this, words);
        setListAdapter(adapter);
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class DownloadStatesTask extends AsyncTask<Void, Void,  Words > {

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected Words doInBackground(Void... params) {
            try {
                // The URL for making the GET request
                final String url = getString(R.string.base_uri);

                HttpAuthentication authHeader = new HttpBasicAuthentication("remote", "remote");

                // Set the Accept header for "application/json"
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);

                List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
                acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
                requestHeaders.setAccept(acceptableMediaTypes);

                // Populate the headers in an HttpEntity object to use for the request
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                // Perform the HTTP GET request
                ResponseEntity<Words> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                        Words.class);

                listWords(responseEntity.getBody());
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }

            return null;
        }

        private  void listWords(Words words) {
            for (Word word: words.getWords()) {
                System.out.println(word);
            }
            System.out.println("");
        }

        @Override
        protected void onPostExecute(Words  result) {
            dismissProgressDialog();
            refreshStates(result.getWords());
        }

    }
}
