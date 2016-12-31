package com.shikher.fitbitsocialnetwork.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.model.newsfeed.LikeUnlikeResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.Newsfeed;
import com.shikher.fitbitsocialnetwork.network.NewsFeedService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shikh on 24-Dec-16.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder> {

    private List<Newsfeed> newsfeedList;
    private Context context;
    private static final String TAG = "NewsFeed Adapter";

    public NewsFeedAdapter(List<Newsfeed> newsfeedList, Context context) {
        this.newsfeedList = newsfeedList;
        this.context = context;
    }

    @Override
    public NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_item, parent, false);
        NewsFeedViewHolder newsFeedViewHolder = new NewsFeedViewHolder(view);
        return newsFeedViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsFeedViewHolder holder, int position) {

        Newsfeed newsfeed = newsfeedList.get(position);
        final int newsFeedId = newsfeed.getId();
        String userToken = Utility.getSharedPreferences(context).getString("userToken", "");
        Utility.getImageLoader(context).displayImage(newsfeed.getProfileImageUrl(), holder.newsFeedUserImageView);
        holder.newsFeedUserName.setText(newsfeed.getUsername());
        holder.newsFeedDescription.setText(newsfeed.getDescription());
        holder.newsFeedDate.setText(newsfeed.getNfDateTime());
        holder.newsFeedLikeImageButton.setImageResource(R.drawable.like);
        holder.newsFeedLikeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do like or unlike
                doLikeUnlike(v, newsFeedId);
            }
        });
        holder.newsFeedLikeCountTextView.setText(newsfeed.getLikecount() + " Likes");
        holder.newsFeedCommentCountTextView.setText(newsfeed.getUsercomments().size() + " Comments");

        //set like status
        setLikeStatus(newsFeedId, userToken, holder.newsFeedLikeImageButton);
    }

    @Override
    public int getItemCount() {
        return newsfeedList.size();
    }

    public void doLikeUnlike(View view, int newsFeedId) {
        ImageButton imageButton = (ImageButton) view;
        String currentTag = imageButton.getTag().toString();
        String userToken = Utility.getSharedPreferences(context).getString("userToken", "");

        if (currentTag.equals("like")) {
            doLike(newsFeedId, userToken, imageButton);
            Log.d(TAG, "Liking post");
        } else {
            imageButton.setTag("like");
            doUnlike(newsFeedId, userToken, imageButton);
            Log.d(TAG, "UnLiking post");
        }
    }


    public void doLike(final int newsFeedId, String userToken, final ImageButton imageButton) {
        NewsFeedService newsFeedService = Utility.retrofit.create(NewsFeedService.class);
        Call<LikeUnlikeResponse> likeUnlikeResponseCall = newsFeedService.doLike(newsFeedId, userToken);
        likeUnlikeResponseCall.enqueue(new Callback<LikeUnlikeResponse>() {
            @Override
            public void onResponse(Call<LikeUnlikeResponse> call, Response<LikeUnlikeResponse> response) {
                imageButton.setTag("unlike");
                imageButton.setImageResource(R.drawable.unlike);
            }

            @Override
            public void onFailure(Call<LikeUnlikeResponse> call, Throwable t) {
                Log.d(TAG, "Unable to like newsfeed with id : " + newsFeedId);
            }
        });
    }

    public void doUnlike(final int newsFeedId, String userToken, final ImageButton imageButton) {

        NewsFeedService newsFeedService = Utility.retrofit.create(NewsFeedService.class);
        Call<LikeUnlikeResponse> likeUnlikeResponseCall = newsFeedService.doUnLike(newsFeedId, userToken);
        likeUnlikeResponseCall.enqueue(new Callback<LikeUnlikeResponse>() {
            @Override
            public void onResponse(Call<LikeUnlikeResponse> call, Response<LikeUnlikeResponse> response) {
                imageButton.setTag("like");
                imageButton.setImageResource(R.drawable.like);
            }

            @Override
            public void onFailure(Call<LikeUnlikeResponse> call, Throwable t) {
                Log.d(TAG, "Unable to unlike newsfeed with id : " + newsFeedId);
            }
        });
    }

    public void setLikeStatus(final int newsFeedId, String userToken, final ImageButton imageButton) {
        NewsFeedService newsFeedService = Utility.retrofit.create(NewsFeedService.class);
        Call<LikeUnlikeResponse> likeUnlikeResponseCall = newsFeedService.getLikes(newsFeedId, userToken);
        likeUnlikeResponseCall.enqueue(new Callback<LikeUnlikeResponse>() {
            @Override
            public void onResponse(Call<LikeUnlikeResponse> call, Response<LikeUnlikeResponse> response) {
                List<String> likesList = response.body().getEmailList();
                String userEmail = Utility.getSharedPreferences(context).getString("userEmail", "");

                if (userEmail.length() > 0 && likesList.contains(userEmail)) {
                    imageButton.setImageResource(R.drawable.unlike);
                    imageButton.setTag("unlike");
                }
            }

            @Override
            public void onFailure(Call<LikeUnlikeResponse> call, Throwable t) {
                Log.e(TAG, "Unable to fetch likes list for a newsfeed with id : " + newsFeedId);
            }
        });
    }



    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        ImageView newsFeedUserImageView;
        TextView newsFeedUserName;
        TextView newsFeedDescription;
        TextView newsFeedDate;
        ImageButton newsFeedLikeImageButton;
        TextView newsFeedLikeCountTextView;
        TextView newsFeedCommentCountTextView;


        public NewsFeedViewHolder(View itemView) {

            super(itemView);
            newsFeedUserImageView = (ImageView) itemView.findViewById(R.id.newsFeedUserImageView);
            newsFeedUserName = (TextView) itemView.findViewById(R.id.newsFeedUserName);
            newsFeedDescription = (TextView) itemView.findViewById(R.id.newsFeedDescription);
            newsFeedDate = (TextView) itemView.findViewById(R.id.newsFeedDate);
            newsFeedLikeImageButton = (ImageButton) itemView.findViewById(R.id.newsFeedLikeImageButton);
            newsFeedLikeCountTextView = (TextView) itemView.findViewById(R.id.newsFeedLikeCountTextView);
            newsFeedCommentCountTextView = (TextView) itemView.findViewById(R.id.newsFeedCommentCountTextView);
        }
    }
}
