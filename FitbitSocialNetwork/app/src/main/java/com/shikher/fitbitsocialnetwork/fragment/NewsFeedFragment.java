package com.shikher.fitbitsocialnetwork.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.adapter.NewsFeedAdapter;
import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;
import com.shikher.fitbitsocialnetwork.model.newsfeed.Newsfeed;
import com.shikher.fitbitsocialnetwork.network.NewsFeedService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //adapter and view
    private NewsFeedAdapter newsFeedAdapter;
    private static final String tag = "NewsFeedFragment";
    private static int count = 1;
    private List<Newsfeed> newsfeedList;

    public NewsFeedFragment() {
        // Required empty public constructor
        newsfeedList = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFeedFragment newInstance(String param1, String param2) {
        NewsFeedFragment fragment = new NewsFeedFragment();
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
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        newsFeedAdapter = new NewsFeedAdapter(newsfeedList, container.getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.newsfeedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsFeedAdapter);


        Button button = (Button) view.findViewById(R.id.moreNewsFeed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewsFeed(count++, mParam2);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //get initial news feed
        getNewsFeed(count, mParam2);
    }

    @Override
    public void onResume() {
        super.onResume();
        newsfeedList.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void getNewsFeed(int pagenumber, String userToken) {

        NewsFeedService newsFeedService = Utility.retrofit.create(NewsFeedService.class);
        Call<NewsFeedResponse> newsFeedResponseCall = newsFeedService.getNewsFeed(pagenumber, userToken);

        newsFeedResponseCall.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(Call<NewsFeedResponse> call, Response<NewsFeedResponse> response) {
                NewsFeedResponse newsFeedResponse = response.body();
                newsfeedList.addAll(newsFeedResponse.getNewsfeed());
                newsFeedAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<NewsFeedResponse> call, Throwable t) {
                Log.e(tag, "Newsfeed Fetch Failed" + t.getMessage());
            }
        });


    }
}
