package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import br.com.rmso.jokesandroid.JokesActivity;

/**
 * Created by Raquel on 23/09/2018.
 */

public class EndpointsAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
    private static MyApi myApiService = null;
    private MainActivityFragment mainActivityFragment;

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

       // context = params[0];
//        String name = params[0].second;

        try {
//            return myApiService.sayHi(name).execute().getData();
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

//        Intent intent = new Intent(context, JokesActivity.class);
//        intent.putExtra("joke", result);
//        context.startActivity(intent);

        mainActivityFragment.load = result;
        mainActivityFragment.showJokesActivity();

    }
}
