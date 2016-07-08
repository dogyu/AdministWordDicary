package com.jsfkorea.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbstractAsyncActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonJson = (Button) findViewById(R.id.btn_search);
        buttonJson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DownloadStateTask().execute(MediaType.APPLICATION_JSON);
            }
        });
    }

    private void showState(Words words) {

        if (words != null) {

            if(words.getWords().size() == 0){
                Toast.makeText(this, "검색결과가 없습니다", Toast.LENGTH_LONG).show();
            }

            Intent intent = new Intent(this, ResultListActivity.class);
            intent.putExtra("words", words);
            startActivity(intent);

        } else {
            Toast.makeText(this, "No state found with that abbreviation!", Toast.LENGTH_LONG).show();
        }
    }

    private class DownloadStateTask extends AsyncTask<MediaType, Void, Words> {

        private String search_word;

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();

            EditText editText = (EditText) findViewById(R.id.edt_search_word);

            search_word = editText.getText().toString();
        }

        @Override
        protected Words doInBackground(MediaType... params) {
            try {
                if (params.length <= 0) {
                    return null;
                }

                MediaType mediaType = params[0];

                final String url = getString(R.string.base_uri) + "/word/{search_word}";

                HttpAuthentication authHeader = new HttpBasicAuthentication("remote", "remote");

                // Set the Accept header for "application/json" or "application/xml"
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);

                List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
                acceptableMediaTypes.add(mediaType);
                requestHeaders.setAccept(acceptableMediaTypes);

                // Populate the headers in an HttpEntity object to use for the request
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();
                if (mediaType.equals(MediaType.APPLICATION_JSON)) {
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                } else if (mediaType.equals(MediaType.APPLICATION_XML)) {
                    restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
                }

                // Perform the HTTP GET request
                ResponseEntity<Words> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                        Words.class, search_word);

                //listWords(responseEntity.getBody());

                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Words word) {
            dismissProgressDialog();

            showState(word);
        }

        private  void listWords(Words words) {
            for (Word word: words.getWords()) {
                System.out.println(word);
            }
            System.out.println("");
        }
    }
}
