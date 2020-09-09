package com.games.harigames.Master;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.games.harigames.R;


public class Market_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView marketName;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_details);
        ActionBar actionBar =getSupportActionBar();

        marketName = findViewById(R.id.name_market);


        Intent intent= getIntent();
       String market= intent.getStringExtra("market");
       marketName.setText(market);



    }
}