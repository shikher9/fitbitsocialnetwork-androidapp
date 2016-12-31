package com.shikher.fitbitsocialnetwork.fragment.friends;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shikher.fitbitsocialnetwork.R;
import com.shikher.fitbitsocialnetwork.adapter.FriendAdapter;
import com.shikher.fitbitsocialnetwork.adapter.FriendSuggestionAdapter;
import com.shikher.fitbitsocialnetwork.model.friend.FriendResponse;
import com.shikher.fitbitsocialnetwork.model.friend.Suggestlist;
import com.shikher.fitbitsocialnetwork.network.FriendService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FriendsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //variables
    private FriendAdapter friendAdapter;
    private static final String tag = "FriendFragment";
    private List<Suggestlist> friendList;


    public FriendsFragment() {
        friendList = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
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
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        friendAdapter = new FriendAdapter(friendList, container.getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.friendsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(friendAdapter);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        getFriends(mParam2);
    }

    @Override
    public void onResume() {
        super.onResume();
        friendList.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void getFriends(String userToken) {
        FriendService friendService = Utility.retrofit.create(FriendService.class);
        Call<FriendResponse> friendResponseCall = friendService.getFriends(userToken);

        friendResponseCall.enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                FriendResponse friendResponse = response.body();
                friendList.addAll(friendResponse.getSuggestlist());
                friendAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {
                Log.e(tag, "Friends Fetch Failed" + t.getMessage());
            }
        });
    }

}
