package com.shikher.fitbitsocialnetwork.adapter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.adapter.NewsFeedAdapter;
import com.shikher.fitbitsocialnetwork.model.friend.Suggestlist;
import com.shikher.fitbitsocialnetwork.model.newsfeed.Newsfeed;
import com.shikher.fitbitsocialnetwork.model.search.Feedlist;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.List;

/**
 * Created by shikh on 24-Dec-16.
 */

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder> {

    private List<Feedlist> feedList;
    private Context context;

    public FeedListAdapter(List<Feedlist> feedList, Context context) {
        this.feedList = feedList;
        this.context = context;
    }

    @Override
    public FeedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_feed_item, parent, false);
        FeedListViewHolder feedListViewHolder = new FeedListViewHolder(view);
        return feedListViewHolder;
    }

    @Override
    public void onBindViewHolder(FeedListViewHolder holder, int position) {

        Feedlist feed = feedList.get(position);
        Utility.getImageLoader(context).displayImage(feed.getProfileImageUrl(), holder.newsFeedUserImageView);
        holder.newsFeedUserName.setText(feed.getUsername());
        holder.newsFeedDescription.setText(feed.getDescription());
        holder.newsFeedDate.setText(feed.getNfDateTime());
        holder.newsFeedLikeCountTextView.setText(feed.getLikecount() + " Likes");
        holder.newsFeedCommentCountTextView.setText(feed.getUsercomments().size() + " Comments");
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class FeedListViewHolder extends RecyclerView.ViewHolder {

        ImageView newsFeedUserImageView;
        TextView newsFeedUserName;
        TextView newsFeedDescription;
        TextView newsFeedDate;
        TextView newsFeedLikeCountTextView;
        TextView newsFeedCommentCountTextView;


        public FeedListViewHolder(View itemView) {

            super(itemView);
            newsFeedUserImageView = (ImageView) itemView.findViewById(R.id.newsFeedUserImageView);
            newsFeedUserName = (TextView) itemView.findViewById(R.id.newsFeedUserName);
            newsFeedDescription = (TextView) itemView.findViewById(R.id.newsFeedDescription);
            newsFeedDate = (TextView) itemView.findViewById(R.id.newsFeedDate);
            newsFeedLikeCountTextView = (TextView) itemView.findViewById(R.id.newsFeedLikeCountTextView);
            newsFeedCommentCountTextView = (TextView) itemView.findViewById(R.id.newsFeedCommentCountTextView);
        }
    }
}
