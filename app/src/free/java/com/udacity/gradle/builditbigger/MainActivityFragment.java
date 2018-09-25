package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import br.com.rmso.jokesandroid.JokesActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public String load = null;
    AdView mAdView = null;
    InterstitialAd interstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = root.findViewById(R.id.button);
        mAdView = root.findViewById(R.id.adView);

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                tellJoke();

                requestAd();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                requestAd();
            }
        });

        requestAd();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }else {
                    tellJoke();
                }
            }
        });

        return root;
    }

    private void requestAd(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);
    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(this);
    }

    public void showJokesActivity() {
        Context context = getContext();
        Intent intent = new Intent(context, JokesActivity.class);
        intent.putExtra("jokes", load);
        context.startActivity(intent);
    }
}