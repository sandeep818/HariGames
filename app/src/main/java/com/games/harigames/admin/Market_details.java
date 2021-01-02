package com.games.harigames.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Market_details extends AppCompatActivity {
    TextView marketName,today_bet,today_bet_amount,lifetime_bet,lifetime_Bet_Amount,market_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_details);
        ActionBar actionBar =getSupportActionBar();
        today_bet=findViewById(R.id.today_bet);
        today_bet_amount=findViewById(R.id.today_bet_amount);
        lifetime_bet=findViewById(R.id.lifetime_bet);
        lifetime_Bet_Amount=findViewById(R.id.lifetime_Bet_Amount);
        market_report=findViewById(R.id.market_report);
        Intent intent= getIntent();

        final String market= intent.getStringExtra("market");
        market_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Market_details.this,Market_report.class);
                intent.putExtra("market_name",market);
                startActivity(intent);
            }
        });


        marketName = findViewById(R.id.name_market);

       marketName.setText(market);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Place_bet");
        ref.orderByChild("market_name").equalTo(market).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int amount=0;
                Validation validation= new Validation();
                int lifetime_bet_num=0;
                int lifetime_bet_ammount=0;
                int bets=0;
                ArrayList<Plece_bet> userAllDataModels = new ArrayList<Plece_bet>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {

                    Plece_bet allDataModel = userSnapshot.getValue(Plece_bet.class);
                    String s = validation.getDate();

                    if (allDataModel.getDate().contains(s)){
                        amount=amount+Integer.parseInt(allDataModel.getAmount().toString());
                        bets=bets+1;
                    }
                    userAllDataModels.add(allDataModel);

                    lifetime_bet_ammount=lifetime_bet_ammount+Integer.parseInt(allDataModel.getAmount().toString());
                    lifetime_bet_num= userAllDataModels.size();



                }
                today_bet.setText(""+bets);
                today_bet_amount.setText("₹ "+amount);
                lifetime_bet.setText(""+lifetime_bet_num);
                lifetime_Bet_Amount.setText("₹ "+lifetime_bet_ammount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}