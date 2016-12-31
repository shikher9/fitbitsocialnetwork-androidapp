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

public class FriendSuggestionAdapter extends RecyclerView.Adapter<FriendSuggestionAdapter.FriendSuggestionsViewHolder> {

    private List<Suggestlist> friendSuggestionsList;
    private Context context;
    private static final String TAG = "FriendSug Adapter";

    public FriendSuggestionAdapter(List<Suggestlist> friendSuggestionsList, Context context) {
        this.friendSuggestionsList = friendSuggestionsList;
        this.context = context;
    }

    @Override
    public FriendSuggestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_friend_item, parent, false);
        FriendSuggestionsViewHolder friendSuggestionsViewHolder = new FriendSuggestionsViewHolder(view);
        return friendSuggestionsViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendSuggestionsViewHolder holder, int position) {

        Suggestlist friend = friendSuggestionsList.get(position);
        Utility.getImageLoader(context).displayImage(friend.getProfileImageUrl(), holder.userImageView);
        holder.usernameTextView.setText(friend.getName());
        final String userToken = Utility.getSharedPreferences(context).getString("userToken", "");
        final String friendEmail = friend.getEmail();
        holder.addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend(userToken, friendEmail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendSuggestionsList.size();
    }

    public void addFriend(String userToken, String friendEmail) {
        FriendService friendService = Utility.retrofit.create(FriendService.class);
        Call<Result> resultCall = friendService.addFriend(userToken, friendEmail);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                String message;
                if (response.code() == 200) {
                    message = "Friend Request Sent";
                } else {
                    message = "Unable to send Friend Request";
                }

                Utility.showShortToast(message, context);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Utility.showShortToast("Unable to send Friend Request", context);
                Log.e(TAG, "Unable to add friend " + t.getMessage());
            }
        });
    }

    public static class FriendSuggestionsViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView usernameTextView;
        Button addFriendButton;

        public FriendSuggestionsViewHolder(View itemView) {

            super(itemView);
            userImageView = (ImageView) itemView.findViewById(R.id.userImageView);
            usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
            addFriendButton = (Button) itemView.findViewById(R.id.addFriendButton);

        }
    }
}
