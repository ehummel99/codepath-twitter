package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    TextView tvCharacterCounter;
    String isReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        isReply = (String) getIntent().getStringExtra("Reply");
        User user = (User) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        etTweet = findViewById(R.id.etTweetBody);
        btnSend = findViewById(R.id.btnPost);
        tvCharacterCounter = findViewById(R.id.tvCharacterCounter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });

        etTweet.addTextChangedListener(mTextEditorWatcher);

        client = TwitterApp.getRestClient(this);

        if(isReply.equals("true")) {
            etTweet.setText("@" + user.screenName);
        }
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCharacterCounter.setText(String.valueOf(s.length()) + "/280");
        }

        public void afterTextChanged(Editable s) {
        }
    };

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
