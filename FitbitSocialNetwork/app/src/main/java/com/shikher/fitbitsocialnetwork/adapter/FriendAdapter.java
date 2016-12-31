package com.shikher.fitbitsocialnetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.model.friend.Suggestlist;
import com.shikher.fitbitsocialnetwork.model.newsfeed.Newsfeed;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.List;

/**
 * Created by shikh on 24-Dec-16.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private List<Suggestlist> friendList;
    private Context context;

    public FriendAdapter(List<Suggestlist> friendList, Context context) {
        this.friendList = friendList;
        this.context = context;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        FriendViewHolder friendViewHolder = new FriendViewHolder(view);
        return friendViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {

        Suggestlist friend = friendList.get(position);
        Utility.getImageLoader(context).displayImage(friend.getProfileImageUrl(), holder.userImageView);
        holder.usernameTextView.setText(friend.getName());
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView usernameTextView;

        public FriendViewHolder(View itemView) {

            super(itemView);
            userImageView = (ImageView) itemView.findViewById(R.id.userImageView);
            usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
        }
    }
}
