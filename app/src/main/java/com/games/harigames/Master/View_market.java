package com.games.harigames.Master;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.games.harigames.R;
import com.games.harigames.admin.Add_market_model;
import com.games.harigames.market.Market_cardview_adapter;
import com.games.harigames.market.Market_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class View_market extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Market_cardview_adapter cardview_adapter;


    ArrayList<Add_market_model> marketModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_market);
        mRecyclerView=findViewById(R.id.market_recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));



        try {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Market");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(DataSnapshot dataSnapshot) {
                                                       ArrayList<Add_market_model> marketDataModels = new ArrayList<Add_market_model>();
                                                       for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                                                           Add_market_model allDataModel = userSnapshot.getValue(Add_market_model.class);
                                                           marketDataModels.add(allDataModel);
                                                           marketModels.add(allDataModel);
                                                           Log.d("allu", "onDataChange: "+marketModels.toString());

                                                       }

                                                       getMarket(marketDataModels);
                                                       System.out.println(marketModels.toString());
                                                   }

                                                   @Override
                                                   public void onCancelled(DatabaseError databaseError) {
                                                       throw databaseError.toException();
                                                   }
                                               }
            );

        }catch (Exception e){
            Log.d("market", "onCreate: "+e);
        }

    }
//    private ArrayList<Market_model> listdata(){
//        ArrayList<Market_model> market_models = new ArrayList<>();
//        Market_model model = new Market_model("ass","asas","46","asas","asa");
//        market_models.add(model);
//        Market_model model1 = new Market_model("ass","asas","46","asas","asa");
//        market_models.add(model1);
//
//        marketModels = new MarketApi(getApplicationContext()).retrive();
//        Log.d("oks", "listdata: "+marketModels.toString());
//        Log.d("oks", "another OBJ: "+market_models.toString());
//        return marketModels;
//
//    }


//    public class MarketApi{
//        Context context;
//
//        public MarketApi(Context context) {
//            this.context = context;
//        }
//
//
//        // call api and get in array
//        public ArrayList<Market_model> retrive(){
//            ArrayList<Market_model> mdownloadData= new ArrayList<>();
//            final  ArrayList<Market_model> mData=new ArrayList<>();
//
//            Retrofit retrofit = RetrofitClient.getInstance();
//            myAPI = retrofit.create(Get_market.class);
//            compositeDisposable.add(myAPI.getMarket()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//
//                    .subscribe(new Consumer<String>() {
//                        @Override
//                        public void accept(String s) throws Throwable {
//                            JSONArray arr = new JSONArray(s);
//                            Market_model mdataModel;
//                            try {
//                                for(int i =0;i<arr.length(); i++){
//                                    JSONObject jObj = arr.getJSONObject(i);
//                                    String id= jObj.getString("id");
//                                    String marketName = jObj.getString("market");
//                                    String startTime = jObj.getString("opning_time");
//                                    String endTime = jObj.getString("closing_time");
//
//                                    String status = jObj.getString("status");
//
//                                   // mdataModel=new Market_model("1","sagar","master","active","5000+");
//                                    mdataModel=new Market_model(marketName,status,id,startTime,endTime);
//                                    mdownloadData.add(mdataModel);
//                                    Toast.makeText(View_market.this, "You Have "+arr.length()+" Markets", Toast.LENGTH_SHORT).show();
//
//                                }
//
//
//                            }catch (JSONException e){
//                                Toast.makeText(View_market.this, "Y444444444444 Have "+arr.length()+" Markets", Toast.LENGTH_LONG).show();
//                            }
//
//
//                            Log.d("oks", "accept: "+mdownloadData);
//                            getMarket(mdownloadData);
//
//
//
//                        }
//
//                    }));
//
//
//            return mdownloadData;
//
//        }
//    }

public void getMarket(ArrayList<Add_market_model> market_models){
        cardview_adapter = new Market_cardview_adapter(this,market_models);
        mRecyclerView.setAdapter(cardview_adapter);

    }
}

