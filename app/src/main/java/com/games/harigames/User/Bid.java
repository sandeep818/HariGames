package com.games.harigames.User;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.games.harigames.R;
import com.games.harigames.market.Market_model;
import com.games.harigames.market.User_market;
import com.games.harigames.market.User_market_adapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Bid extends Fragment {

    private RecyclerView recyclerView;
    User_market_adapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase;

    public Bid() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.place_bet_home, container, false);
        mbase
                = FirebaseDatabase.getInstance().getReference("Market");

        recyclerView = view.findViewById(R.id.recycler_view_user);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));
        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<User_market> options
                = new FirebaseRecyclerOptions.Builder<User_market>()
                .setQuery(mbase, User_market.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new User_market_adapter(options,getContext());
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);




        return view;
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}
