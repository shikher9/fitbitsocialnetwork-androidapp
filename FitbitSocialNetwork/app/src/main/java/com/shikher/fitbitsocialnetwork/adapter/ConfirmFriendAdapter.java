package com.shikher.fitbitsocialnetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.model.Result;
import com.shikher.fitbitsocialnetwork.model.friend.Suggestlist;
import com.shikher.fitbitsocialnetwork.model.newsfeed.Newsfeed;
import com.shikher.fitbitsocialnetwork.network.FriendService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shikh on 24-Dec-16.
 */

public class ConfirmFriendAdapter extends RecyclerView.Adapter<ConfirmFriendAdapter.ConfirmFriendViewHolder> {

    private List<Suggestlist> confirmFriendsList;
    private Context context;
    private static final String TAG = "ConfirmFriend Adapter";

    public ConfirmFriendAdapter(List<Suggestlist> confirmFriendsList, Context context) {
        this.confirmFriendsList = confirmFriendsList;
        this.context = context;
    }

    @Override
    public ConfirmFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirm_friend_item, parent, false);
        ConfirmFriendViewHolder confirmFriendViewHolder = new ConfirmFriendViewHolder(view);
        return confirmFriendViewHolder;
    }

    @Override
    public void onBindViewHolder(ConfirmFriendViewHolder holder, int position) {

        final Suggestlist confirmFriend = confirmFriendsList.get(position);
        Utility.getImageLoader(context).displayImage(confirmFriend.getProfileImageUrl(), holder.userImageView);
        holder.usernameTextView.setText(confirmFriend.getName());

        final String userToken = Utility.getSharedPreferences(context).getString("userToken", "");
        final String friendEmail = confirmFriend.getEmail();
        holder.confirmFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFriend(friendEmail, true, userToken);
            }
        });

        holder.rejectFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFriend(friendEmail, false, userToken);
            }
        });
    }

    public void confirmFriend(String friendEmail, final boolean action, String userToken) {
        FriendService friendService = Utility.retrofit.create(FriendService.class);
        Call<Result> resultCall = friendService.confirmFriend(friendEmail, action, userToken);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (action) {
                    Utility.showShortToast("Accepted Friend Request", context);
                } else {
                    Utility.showShortToast("Rejected Friend Request", context);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Utility.showShortToast("Unable to perform requested operation", context);
                Log.e(TAG, "Unable to perform confirm friend operation");
            }
        });
    }

    @Override
    public int getItemCount() {
        return confirmFriendsList.size();
    }

    public static class ConfirmFriendViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView usernameTextView;
        Button confirmFriendButton;
        Button rejectFriendButton;

        public ConfirmFriendViewHolder(View itemView) {

            super(itemView);
            userImageView = (ImageView) itemView.findViewById(R.id.userImageView);
            usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
            confirmFriendButton = (Button) itemView.findViewById(R.id.confirmFriendButton);
            rejectFriendButton = (Button) itemView.findViewById(R.id.rejectFriendButton);
        }
    }
}
