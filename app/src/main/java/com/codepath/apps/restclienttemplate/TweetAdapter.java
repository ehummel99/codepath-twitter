package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    List<Tweet> tweets;
    Context context;


    //pass tweets array to constructor
    public TweetAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //get data according to position
        Tweet tweet = tweets.get(i);
        //populate views according to data
        viewHolder.tvUsername.setText(tweet.user.name);
        viewHolder.tvBody.setText(tweet.body);
        viewHolder.tvRelativeTimestamp.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
        viewHolder.tvUserTag.setText("@" + tweet.user.screenName);

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 100, 0))
                .into(viewHolder.ivProfileImage);

        if(tweet.entities.has_entity) {
            Glide.with(context)
                    .load(tweet.entities.media_url)
                    .bitmapTransform(new RoundedCornersTransformation(context, 10, 0))
                    .into(viewHolder.ivMediaImage);
        } else {
            viewHolder.ivMediaImage.setVisibility(View.GONE);
        }

        viewHolder.ibReply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onReplyTweet(i);
            }
        });
    }

    public void onReplyTweet(int position) {
        Intent intent = new Intent(context, ComposeActivity.class);
        intent.putExtra("Reply", "true");
        intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweets.get(position).user));
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }



    //for each row pass ViewHolder class

    //bind values based on position of element

    //create ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvRelativeTimestamp;
        public TextView tvUserTag;
        public ImageView ivMediaImage;
        public ImageButton ibReply;

        public ViewHolder(View view) {
            super(view);

            //perform findViewById lookups
            ivProfileImage = (ImageView) view.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) view.findViewById(R.id.tvUserName);
            tvBody = (TextView) view.findViewById(R.id.tvBody);
            tvUserTag = (TextView) view.findViewById(R.id.tvUserTag);
            tvRelativeTimestamp = (TextView) view.findViewById(R.id.tvRelativeTimestamp);
            ivMediaImage = (ImageView) view.findViewById(R.id.ivMediaImage);
            ibReply = (ImageButton) view.findViewById(R.id.ibReply);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //get position of movie clicked
            int position = getAdapterPosition();
            //get movie at the position
            Tweet tweet = tweets.get(position);
            //create new intent
            Intent intent = new Intent(context, TweetDetailActivity.class);
            //pass movie
            intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
            //show the activity
            context.startActivity(intent);
        }

    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

}
