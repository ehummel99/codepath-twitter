package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    Button btnSend;
    EditText etTweet;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etTweet = findViewById(R.id.etTweetBody);
        btnSend = findViewById(R.id.btnPost);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });

        client = TwitterApp.getRestClient(this);
    }

    public void sendTweet() {
        client.sendTweet(etTweet.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject responseJson = new JSONObject((new String(responseBody)));
                    Tweet resultTweet = Tweet.fromJson(responseJson);
                    Intent intent = new Intent();
                    intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(resultTweet));
                    intent.putExtra(User.class.getSimpleName(), Parcels.wrap(resultTweet.user));
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (JSONException e) {
                    Log.e("ComposeActivity", "failed to parse JSON");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("ComposeActivity", "failed to send tweet");
            }
        });
    }
}
