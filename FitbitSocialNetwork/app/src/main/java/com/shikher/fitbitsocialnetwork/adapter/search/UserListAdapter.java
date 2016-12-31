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
import com.shikher.fitbitsocialnetwork.model.friend.Suggestlist;
import com.shikher.fitbitsocialnetwork.model.search.Userlist;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.List;

/**
 * Created by shikh on 24-Dec-16.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    private List<Userlist> userList;
    private Context context;

    public UserListAdapter(List<Userlist> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        UserListViewHolder userListViewHolder = new UserListViewHolder(view);
        return userListViewHolder;
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {

        Userlist user = userList.get(position);
        Utility.getImageLoader(context).displayImage(user.getProfileImageUrl(), holder.userImageView);
        holder.usernameTextView.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserListViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView usernameTextView;


        public UserListViewHolder(View itemView) {

            super(itemView);
            userImageView = (ImageView) itemView.findViewById(R.id.userImageView);
            usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
        }
    }
}
