package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailActivity extends AppCompatActivity {

    Tweet tweet;
    ImageView ivUserProfile;
    TextView tvUserName;
    TextView tvTweetBody;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        ivUserProfile = (ImageView) findViewById(R.id.ivUserProfile);
        tvUserName = (TextView) findViewById(R.id.tvUser);
        tvTweetBody = (TextView) findViewById(R.id.tvTweetText);
        tvDate = (TextView) findViewById(R.id.tvDate);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvUserName.setText(tweet.user.name);
        tvTweetBody.setText(tweet.body);
        tvDate.setText(tweet.getRelativeTimeAgo(tweet.createdAt));

    }
}
