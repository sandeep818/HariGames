package com.games.harigames.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.games.harigames.R;
import com.games.harigames.getAllUser.UserAllDataModel;
import com.games.harigames.getAllUser.UserlistAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Master_usersDetail extends AppCompatActivity {
    ArrayList<UserAllDataModel> dataModels;
    ListView listView;
    ProgressBar myProgressBar;
    SearchView searchView;
    String Uemail;
    private static UserlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_users_detail);

        final Intent intent = getIntent();

        Uemail = intent.getStringExtra("email");


        listView=(ListView)findViewById(R.id.list);
        myProgressBar=(ProgressBar)findViewById(R.id.myprogressBar);

        searchView = (SearchView)findViewById(R.id.mysearchView);

        //int api

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("User");





//        dataModels.add(new UserAllDataModel("1","raj","master","inactive","5000+"));


        searchView.setIconified(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        //  dataModels=new UserApi(getContext()).retrive(listView,myProgressBar);
        dataModels =getit(myProgressBar);
        Log.d("oks", "onCreateView: "+dataModels);
        adapter=new UserlistAdapter(dataModels,this);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserAllDataModel dataModel= dataModels.get(position);

                // Snackbar.make(view, dataModel.getName()+"\n"+" API: "+dataModel.getStatus(), Snackbar.LENGTH_LONG)
                //         .setAction("No action", null).show();

                Intent intent = new Intent(Master_usersDetail.this,Master_details.class);
//                intent.putExtra("id",dataModel.getId());
                intent.putExtra("name",dataModel.getName());
                intent.putExtra("role",dataModel.getRole());
                intent.putExtra("email",dataModel.getUserName());
                intent.putExtra("status",dataModel.getStatus());
                intent.putExtra("credit",dataModel.getCredit());
                intent.putExtra("mobile",dataModel.getMobile());
//                intent.putExtra("username",dataModel.getUsername());
                startActivity(intent);
                //*****************animation when switch activity*********************
                //     getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
    }
    public ArrayList<UserAllDataModel> getit (final ProgressBar progressBar){
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        final ArrayList<UserAllDataModel> downloadData=new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(DataSnapshot dataSnapshot) {
                                                   ArrayList<UserAllDataModel> userAllDataModels = new ArrayList<UserAllDataModel>();
                                                   for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {

                                                       UserAllDataModel allDataModel = userSnapshot.getValue(UserAllDataModel.class);
                                                       if (allDataModel.getCreated_by().contains(Uemail)){
                                                           userAllDataModels.add(allDataModel);
                                                           downloadData.add(allDataModel);
                                                       }

                                                       Log.d("allu", "onDataChange: "+downloadData.toString());

                                                   }
                                                   progressBar.setVisibility(View.GONE);

                                                   System.out.println(downloadData.toString());
                                               }

                                               @Override
                                               public void onCancelled(DatabaseError databaseError) {
                                                   throw databaseError.toException();
                                               }
                                           }
        );
        return downloadData;


    }
}