package com.shikher.fitbitsocialnetwork.fragment.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.model.profile.Newsfeed_;
import com.shikher.fitbitsocialnetwork.model.profile.ProfileResponse;
import com.shikher.fitbitsocialnetwork.network.ProfileService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean mParam3;

    //widgets
    ImageView profileImageView;
    TextView profileNameTextView;
    TextView profileDescriptionTextView;
    TextView profileLocationTextView;
    TextView profileAVGDailyStepsTextView;
    LinearLayout profileFeedLinearLayout;

    //variables
    private static final String TAG = "PROFILE FRAGMENT";


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     * @Param param3 Parameter 3.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2, boolean param3) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putBoolean(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getBoolean(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileImageView = (ImageView) view.findViewById(R.id.profileImageView);
        profileNameTextView = (TextView) view.findViewById(R.id.profileNameTextView);
        profileDescriptionTextView = (TextView) view.findViewById(R.id.profileDescriptionTextView);
        profileLocationTextView = (TextView) view.findViewById(R.id.profileLocationTextView);
        profileAVGDailyStepsTextView = (TextView) view.findViewById(R.id.profileAVGDailyStepsTextView);
        profileFeedLinearLayout = (LinearLayout) view.findViewById(R.id.profileFeedLinearLayout);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        getProfile(mParam2);
    }

    public void getProfile(String userToken) {
        ProfileService profileService = Utility.retrofit.create(ProfileService.class);
        Call<ProfileResponse> profileResponseCall = profileService.getProfile(userToken);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                Utility.getImageLoader(getActivity()).displayImage(profileResponse.getProfileImageUrl(), profileImageView);
                profileNameTextView.setText(profileResponse.getUsername());
                profileDescriptionTextView.setText(profileResponse.getDescription());
                profileLocationTextView.setText(profileResponse.getAddress());
                profileAVGDailyStepsTextView.setText("Average Daily Steps " + String.valueOf(profileResponse.getAverageDailySteps()));
                List<Newsfeed_> profileFeedList = profileResponse.getNewsfeed().getNewsfeed();


                //loop though profileFeedList,create view from each and add it to the linear layout

                for (Newsfeed_ feedItem : profileFeedList) {
                    LinearLayout feedItemLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.profile_feed_item, null);
                    ImageView newsFeedUserImageView = (ImageView) ((LinearLayout) feedItemLinearLayout.getChildAt(0)).getChildAt(0);
                    TextView newsFeedUserName = (TextView) ((LinearLayout) feedItemLinearLayout.getChildAt(0)).getChildAt(1);
                    TextView newsFeedDescription = (TextView) feedItemLinearLayout.getChildAt(1);
                    TextView newsFeedDate = (TextView) feedItemLinearLayout.getChildAt(2);
                    TextView newsFeedLikeCountTextView = (TextView) ((LinearLayout) feedItemLinearLayout.getChildAt(3)).getChildAt(0);
                    TextView newsFeedCommentCountTextView = (TextView) ((LinearLayout) feedItemLinearLayout.getChildAt(3)).getChildAt(1);

                    Utility.getImageLoader(getActivity()).displayImage(feedItem.getProfileImageUrl(), newsFeedUserImageView);
                    newsFeedUserName.setText(feedItem.getUsername());
                    newsFeedDescription.setText(feedItem.getDescription());
                    newsFeedDate.setText(feedItem.getNfDateTime());
                    newsFeedLikeCountTextView.setText(String.valueOf(feedItem.getLikecount()) + " Likes");
                    newsFeedCommentCountTextView.setText(String.valueOf(feedItem.getUsercomments().size()) + " Comments");
                    newsFeedUserName.setText(feedItem.getUsername());


                    //adding to linear layout
                    profileFeedLinearLayout.addView(feedItemLinearLayout);
                }

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e(TAG, "Profile Fetch Failed");
            }
        });

    }
}
