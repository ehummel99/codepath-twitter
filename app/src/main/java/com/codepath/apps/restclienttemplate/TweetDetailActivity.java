package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetDetailActivity extends AppCompatActivity {

    Tweet tweet;
    ImageView ivUserProfile;
    TextView tvUserName;
    TextView tvTweetBody;
    TextView tvDate;
    TextView tvTag;
    ImageView ivMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        ivUserProfile = (ImageView) findViewById(R.id.ivUserProfile);
        tvUserName = (TextView) findViewById(R.id.tvUser);
        tvTweetBody = (TextView) findViewById(R.id.tvTweetText);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTag = (TextView) findViewById(R.id.tvTag);
        ivMedia = (ImageView) findViewById(R.id.ivMedia);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvUserName.setText(tweet.user.name);
        tvTweetBody.setText(tweet.body);
        tvDate.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
        tvTag.setText("@" + tweet.user.screenName);


        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(this, 10, 0))
                .into(ivUserProfile);

        Glide.with(this)
                .load(tweet.entities.media_url)
                .bitmapTransform(new RoundedCornersTransformation(this, 10, 0))
                .into(ivMedia);
    }
}
