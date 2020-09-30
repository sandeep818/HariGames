package com.games.harigames.Master;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.games.harigames.Login_Ragister.Ragister_Master_Model;
import com.games.harigames.R;
import com.games.harigames.admin.Master_details;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {
int num=0;
 String username;
TextView user_number;

    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.master_home, container, false);
        SharedPreferences preferences = getContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        username= preferences.getString("username","user");

       user_number = (TextView) view.findViewById(R.id.user_Value);
       Home_master homeMaster = new Home_master();


        usersNum();



    return view;
    }
    private void usersNum(){


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Ragister_Master_Model> marketDataModels = new ArrayList<Ragister_Master_Model>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Ragister_Master_Model allDataModel = userSnapshot.getValue(Ragister_Master_Model.class);
                    if (allDataModel.created_by.contains(username)){
                        Toast.makeText(getContext(), "found", Toast.LENGTH_SHORT).show();

                        num = num+1;

                    }
                    //  marketDataModels.add(allDataModel);
                    user_number.setText(""+num);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

    }

}
