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
import com.shikher.fitbitsocialnetwork.model.profile.TopBadge;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.List;

/**
 * Created by shikh on 24-Dec-16.
 */

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {

    private List<TopBadge> badgesList;
    private Context context;

    public BadgeAdapter(List<TopBadge> badgesList, Context context) {
        this.badgesList = badgesList;
        this.context = context;
    }

    @Override
    public BadgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.badge_item, parent, false);
        BadgeViewHolder badgeViewHolder = new BadgeViewHolder(view);
        return badgeViewHolder;
    }

    @Override
    public void onBindViewHolder(BadgeViewHolder holder, int position) {

        TopBadge badge = badgesList.get(position);
        Utility.getImageLoader(context).displayImage(badge.getImage125px(), holder.badgeImageView);
        holder.badgeCategoryTextView.setText(badge.getCategory());
        holder.badgeDescriptionTextView.setText(badge.getDescription());
    }

    @Override
    public int getItemCount() {
        return badgesList.size();
    }

    public static class BadgeViewHolder extends RecyclerView.ViewHolder {

        ImageView badgeImageView;
        TextView badgeCategoryTextView;
        TextView badgeDescriptionTextView;


        public BadgeViewHolder(View itemView) {

            super(itemView);
            badgeImageView = (ImageView) itemView.findViewById(R.id.badgeImageView);
            badgeCategoryTextView = (TextView) itemView.findViewById(R.id.badgeCategoryTextView);
            badgeDescriptionTextView = (TextView) itemView.findViewById(R.id.badgeDescriptionTextView);
        }
    }
}
