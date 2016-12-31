package com.shikher.fitbitsocialnetwork.fragment.stats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.model.lifetimestats.LifetimeStatsResponse;
import com.shikher.fitbitsocialnetwork.network.StatsService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LifetimeStatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //widgets
    TextView caloriesTotalTextView;
    TextView distanceTotalTextView;
    TextView stepsTotalTextView;
    TextView distanceTextView;
    TextView stepsTextView;

    //variables
    public static final String TAG = "LifetimeStatsFragment";


    public LifetimeStatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LifetimeStatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LifetimeStatsFragment newInstance(String param1, String param2) {
        LifetimeStatsFragment fragment = new LifetimeStatsFragment();
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
        View view = inflater.inflate(R.layout.fragment_lifetime_stats, container, false);
        caloriesTotalTextView = (TextView) view.findViewById(R.id.caloriesTotalTextView);
        distanceTotalTextView = (TextView) view.findViewById(R.id.distanceTotalTextView);
        stepsTotalTextView = (TextView) view.findViewById(R.id.stepsTotalTextView);
        distanceTextView = (TextView) view.findViewById(R.id.distanceTextView);
        stepsTextView = (TextView) view.findViewById(R.id.stepsTextView);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLifetimeStats(mParam2);
    }

    public void getLifetimeStats(String userToken) {

        StatsService statsService = Utility.retrofit.create(StatsService.class);
        Call<LifetimeStatsResponse> lifetimeStatsResponseCall = statsService.getLifetimeStats(userToken);

        lifetimeStatsResponseCall.enqueue(new Callback<LifetimeStatsResponse>() {
            @Override
            public void onResponse(Call<LifetimeStatsResponse> call, Response<LifetimeStatsResponse> response) {
                LifetimeStatsResponse lifetimeStatsResponse = response.body();
                caloriesTotalTextView.setText("Calories Out " + String.valueOf(lifetimeStatsResponse.getLifetime().getTotal().getCaloriesOut()));
                distanceTotalTextView.setText("Distance " + String.valueOf(lifetimeStatsResponse.getLifetime().getTotal().getDistance()));
                stepsTotalTextView.setText("Steps " + String.valueOf(lifetimeStatsResponse.getLifetime().getTotal().getSteps()));
                distanceTextView.setText("Distance " + String.valueOf(lifetimeStatsResponse.getBest().getTotal().getDistance().getValue()));
                stepsTextView.setText("Steps " + String.valueOf(lifetimeStatsResponse.getBest().getTotal().getSteps().getValue()));
            }


            @Override
            public void onFailure(Call<LifetimeStatsResponse> call, Throwable t) {
                Log.e(TAG, "LifetimeStats Fetch Failed" + t.getMessage());
            }
        });


    }

}
