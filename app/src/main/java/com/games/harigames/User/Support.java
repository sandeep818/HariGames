package com.games.harigames.User;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.games.harigames.Place_bet.Bet_adapter;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.games.harigames.getAllUser.UserAllDataModel;
import com.games.harigames.getAllUser.UserlistAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Support extends Fragment {

    ArrayList<Plece_bet> dataModels;
    ListView listView;
    ProgressBar myProgressBar;
    SearchView searchView;
    private static Bet_adapter adapter;


    public Support() {
        // Required empty public constructor
    }
//public class UserApi{
//     Context context;
//
//    public UserApi(Context context) {
//        this.context = context;
//    }
//
//
//    // call api and get in array
//    public ArrayList<UserAllDataModel> retrive(final ListView listView, final ProgressBar progressBar){
////        final ArrayList<UserAllDataModel> downloadData=new ArrayList<>();
//        progressBar.setIndeterminate(true);
//        progressBar.setVisibility(View.VISIBLE);
//
//
//
//
////        compositeDisposable.add(myAPI.getAllUser()
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////
////                .subscribe(new Consumer<String>() {
////                    @Override
////                    public void accept(String s) throws Throwable {
////                        JSONArray arr = new JSONArray(s);
////                     UserAllDataModel dataModel;
////                      try {
////                          for(int i =0;i<arr.length(); i++){
////                              JSONObject jObj = arr.getJSONObject(i);
////                              String id= jObj.getString("id");
////                              String username = jObj.getString("username");
////                              String name = jObj.getString("fullname");
////                              String credit = jObj.getString("creditReference");
////                              String role = jObj.getString("role");
////                              String email = jObj.getString("email");
////                              String balance = jObj.getString("balance");
////                              String exposer = jObj.getString("exposerLimit");
////                              String status = jObj.getString("status");
////
//////                          dataModels.add(new UserAllDataModel("1","sagar","master","active","5000+"));
////                              dataModel=new UserAllDataModel(id,name,role,status,credit,email,exposer,username,balance);
////                              downloadData.add(dataModel);
////
////
////                          }
////                          Toast.makeText(getActivity(), "You Have "+arr.length()+" Masters", Toast.LENGTH_SHORT).show();
////                          progressBar.setVisibility(View.GONE);
////                      }catch (JSONException e){
////                          progressBar.setVisibility(View.GONE);
////                      }
////
////                        progressBar.setVisibility(View.GONE);
////
////
////
////                    }
////
////                }));
//
//
//
//
////        Log.d("oks", "accept123: "+downloadData.size());
//      return  getit();
//    }
//}
//////////////////////////////////////////////////////////////////////////////////////

public ArrayList<Plece_bet> getit (final ProgressBar progressBar){
    progressBar.setIndeterminate(true);
    progressBar.setVisibility(View.VISIBLE);
    final ArrayList<Plece_bet> downloadData=new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Place_bet");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(DataSnapshot dataSnapshot) {
                                                   ArrayList<Plece_bet> userAllDataModels = new ArrayList<Plece_bet>();
                                                   for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                                                       Plece_bet allDataModel =userSnapshot.getValue(Plece_bet.class);
                                                       userAllDataModels.add(allDataModel);
                                                       downloadData.add(allDataModel);
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


//////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_support, container, false);
        listView=(ListView)view.findViewById(R.id.list);
        myProgressBar=(ProgressBar)view.findViewById(R.id.myprogressBar);

        searchView = (SearchView) view.findViewById(R.id.mysearchView);

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
//                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);

                return false;
            }
        });

      //  dataModels=new UserApi(getContext()).retrive(listView,myProgressBar);
        dataModels =getit(myProgressBar);
        Log.d("oks", "onCreateView: "+dataModels);
        adapter=new Bet_adapter(dataModels,getContext());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Plece_bet dataModel= dataModels.get(position);

               // Snackbar.make(view, dataModel.getName()+"\n"+" API: "+dataModel.getStatus(), Snackbar.LENGTH_LONG)
               //         .setAction("No action", null).show();

//                Intent intent = new Intent(getApplicationContext(),UserAction.class);
//                intent.putExtra("id",dataModel.getId());
//                intent.putExtra("name",dataModel.getName());
//                intent.putExtra("role",dataModel.getRole());
//                intent.putExtra("email",dataModel.getEmail());
//                intent.putExtra("status",dataModel.getStatus());
//                intent.putExtra("credit",dataModel.getCredit());
//                intent.putExtra("balance",dataModel.getBalance());
//                intent.putExtra("username",dataModel.getUsername());
//                startActivity(intent);
                     //*****************animation when witch activity*********************
            //    getApplicationContext().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });


        return view;
    }





}
