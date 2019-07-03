package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //get data according to position
        Tweet tweet = tweets.get(i);
        //populate views according to data
        viewHolder.tvUsername.setText(tweet.user.name);
        viewHolder.tvBody.setText(tweet.body);
        viewHolder.tvRelativeTimestamp.setText(tweet.getRelativeTimeAgo(tweet.createdAt));

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 10, 0))
                .into(viewHolder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //for each row pass ViewHolder class

    //bind values based on position of element

    //create ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvRelativeTimestamp;

        public ViewHolder(View view) {
            super(view);

            //perform findViewById lookups
            ivProfileImage = (ImageView) view.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) view.findViewById(R.id.tvUserName);
            tvBody = (TextView) view.findViewById(R.id.tvBody);
            tvRelativeTimestamp = (TextView) view.findViewById(R.id.tvRelativeTimestamp);
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
