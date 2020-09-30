package com.games.harigames.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.games.harigames.R;

public class Bet_optionActivity extends AppCompatActivity {

    TextView singledigit,namemarket,timeopen,timeclose,jodi_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_option);
        final String market_name= getIntent().getStringExtra("market");
        final String opening_time= getIntent().getStringExtra("open");
        final String closing_time= getIntent().getStringExtra("close");

        singledigit = findViewById(R.id.singledigit);
        namemarket = findViewById(R.id.marketname);
        timeopen = findViewById(R.id.timeopen);
        timeclose = findViewById(R.id.timeclose);
        jodi_text = findViewById(R.id.jodi_text);


        namemarket.setText(market_name);
        timeopen.setText("Open - "+opening_time);
        timeclose.setText("Close - "+closing_time);





        singledigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bet_optionActivity.this,Single_digitBet.class);
                intent.putExtra("open",opening_time);
                intent.putExtra("close",closing_time);
                intent.putExtra("market",market_name);
                startActivity(intent);
            }
        });
        jodi_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bet_optionActivity.this,Jodi_bet_Activity.class);
//                intent.putExtra("open",opening_time);
//                intent.putExtra("close",closing_time);
                intent.putExtra("market",market_name);
                startActivity(intent);
            }
        });



    }
}