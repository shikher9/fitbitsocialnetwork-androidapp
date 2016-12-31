package com.shikher.fitbitsocialnetwork.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.adapter.NewsFeedAdapter;
import com.shikher.fitbitsocialnetwork.adapter.search.FeedListAdapter;
import com.shikher.fitbitsocialnetwork.adapter.search.UserListAdapter;
import com.shikher.fitbitsocialnetwork.model.newsfeed.NewsFeedResponse;
import com.shikher.fitbitsocialnetwork.model.search.Feedlist;
import com.shikher.fitbitsocialnetwork.model.search.SearchResponse;
import com.shikher.fitbitsocialnetwork.model.search.Userlist;
import com.shikher.fitbitsocialnetwork.network.NewsFeedService;
import com.shikher.fitbitsocialnetwork.network.SearchService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //widgets
    EditText searchEditText;
    ImageButton searchButton;

    //variables
    List<Userlist> userList;
    List<Feedlist> feedList;
    UserListAdapter userListAdapter;
    FeedListAdapter feedListAdapter;
    private static final String TAG = "SEARCH FRAGMENT";


    public SearchFragment() {
        // Required empty public constructor
        userList = new ArrayList<>();
        feedList = new ArrayList<>();
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //adapters
        userListAdapter = new UserListAdapter(userList, container.getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.userListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userListAdapter);

        feedListAdapter = new FeedListAdapter(feedList, container.getContext());
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.feedListRecyclerView);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setAdapter(feedListAdapter);

        //widgets
        searchEditText = (EditText) view.findViewById(R.id.searchEditText);
        searchButton = (ImageButton) view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString();
                getSearchResults(searchText, mParam2);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getSearchResults(String searchText, String userToken) {
        SearchService searchService = Utility.retrofit.create(SearchService.class);
        Call<SearchResponse> searchResponseCall = searchService.getSearchResult(searchText, userToken);

        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();

                //clear existing list data
                userList.clear();
                feedList.clear();

                //get userlist
                userList.addAll(searchResponse.getUserlist());
                //get feedlist
                feedList.addAll(searchResponse.getFeedlist());

                //notify adapter
                userListAdapter.notifyDataSetChanged();
                feedListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e(TAG, "Search Fetch Failed" + t.getMessage());
            }
        });
    }


}
