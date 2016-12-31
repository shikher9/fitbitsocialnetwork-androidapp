package com.shikher.fitbitsocialnetwork.fragment.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.adapter.BadgeAdapter;
import com.shikher.fitbitsocialnetwork.model.profile.ProfileResponse;
import com.shikher.fitbitsocialnetwork.model.profile.TopBadge;
import com.shikher.fitbitsocialnetwork.network.ProfileService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BadgesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //variables
    List<TopBadge> badgesList;
    BadgeAdapter badgeAdapter;
    private static final String TAG = "BADGES FRAGMENT";


    public BadgesFragment() {
        // Required empty public constructor
        badgesList = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BadgesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BadgesFragment newInstance(String param1, String param2) {
        BadgesFragment fragment = new BadgesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_badges, container, false);

        //configure recycler view
        badgeAdapter = new BadgeAdapter(badgesList, container.getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.badgesRecyclerView);
        recyclerView.setAdapter(badgeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        getBadges(mParam2);
    }

    public void getBadges(String userToken) {
        ProfileService profileService = Utility.retrofit.create(ProfileService.class);
        Call<ProfileResponse> profileResponseCall = profileService.getProfile(userToken);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                badgesList.clear();
                badgesList.addAll(profileResponse.getTopBadges());
                badgeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e(TAG, "Profile Fetch Failed");
            }
        });

    }

}
