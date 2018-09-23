package br.com.rmso.jokesandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    private TextView mJokeTextView;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        mJokeTextView = findViewById(R.id.tv_joke);

        Intent intent = getIntent();
        joke = intent.getStringExtra("joke");

        if (joke != null){
            mJokeTextView.setText(joke);
        }
    }
}
