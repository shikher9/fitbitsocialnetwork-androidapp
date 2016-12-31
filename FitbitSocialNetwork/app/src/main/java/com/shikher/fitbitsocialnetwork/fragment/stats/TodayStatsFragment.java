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
import com.shikher.fitbitsocialnetwork.model.todaystats.TodayStatsResponse;
import com.shikher.fitbitsocialnetwork.network.StatsService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayStatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayStatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //widgets
    TextView caloriesOutTextView;
    TextView caloriesGoalTextView;
    TextView waterConsumedTextView;
    TextView waterGoalTextView;
    TextView stepsTextView;

    //variables
    public static final String TAG = "TODAY STATS FRAGMENT";

    public TodayStatsFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayStatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayStatsFragment newInstance(String param1, String param2) {
        TodayStatsFragment fragment = new TodayStatsFragment();
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
        View view = inflater.inflate(R.layout.fragment_today_stats, container, false);
        caloriesOutTextView = (TextView) view.findViewById(R.id.caloriesOutTextView);
        caloriesGoalTextView = (TextView) view.findViewById(R.id.caloriesGoalTextView);
        waterConsumedTextView = (TextView) view.findViewById(R.id.waterConsumedTextView);
        waterGoalTextView = (TextView) view.findViewById(R.id.waterGoalTextView);
        stepsTextView = (TextView) view.findViewById(R.id.todayStepsTextView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getTodayStats(mParam2);
    }

    public void getTodayStats(String userToken) {

        StatsService statsService = Utility.retrofit.create(StatsService.class);
        Call<TodayStatsResponse> todayStatsResponseCall = statsService.getTodayStats(userToken);

        todayStatsResponseCall.enqueue(new Callback<TodayStatsResponse>() {
            @Override
            public void onResponse(Call<TodayStatsResponse> call, Response<TodayStatsResponse> response) {
                TodayStatsResponse todayStatsResponse = response.body();
                caloriesOutTextView.setText("Calories Out " + String.valueOf(todayStatsResponse.getCaloriesBurnt()));
                caloriesGoalTextView.setText("Calories Goal " + String.valueOf(todayStatsResponse.getCaloriesGoal()));
                waterConsumedTextView.setText("Water Consumed " + String.valueOf(todayStatsResponse.getWaterConsumed()));
                waterGoalTextView.setText("Water Goal " + String.valueOf(todayStatsResponse.getWaterGoal()));
                stepsTextView.setText("Steps " + String.valueOf(todayStatsResponse.getStepCount()));
            }


            @Override
            public void onFailure(Call<TodayStatsResponse> call, Throwable t) {
                Log.e(TAG, "Today Stats Fetch Failed" + t.getMessage());
            }
        });


    }

}
