package com.games.harigames.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.R;

public class Bet_optionActivity extends AppCompatActivity {

    TextView singledigit,namemarket,timeopen,timeclose,jodi_text,sangam_btn,single_patti,double_patti;
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
        sangam_btn = findViewById(R.id.sangam_btn);
        single_patti = findViewById(R.id.single_patti);
        double_patti = findViewById(R.id.double_patti);


        namemarket.setText(market_name);
        timeopen.setText("Open - "+opening_time);
        timeclose.setText("Close - "+closing_time);

        Validation validation =new Validation();

        if (validation.isTimeAutomatic(getApplicationContext())){

            try {
                if (Settings.Global.getInt(getContentResolver(),Settings.Global.AUTO_TIME_ZONE)!=1){

                    validation.showDialog(this,"please select device date and time to Automatic","Date&Time",new Intent(Settings.ACTION_DATE_SETTINGS),false);
//                    startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
                    Toast.makeText(getApplication().getApplicationContext(), " Otherwise unable to place bet", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){

            }

        }else {
            validation.showDialog(this,"please select device date and time to Automatic","Date&Time",new Intent(Settings.ACTION_DATE_SETTINGS),false);
            Toast.makeText(getApplication().getBaseContext(), "Please select Automatic date and time", Toast.LENGTH_LONG).show();
           // startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
//    Toast.makeText(this, " Otherwise unable to place bet", Toast.LENGTH_SHORT).show();

        }





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
               intent.putExtra("open",opening_time);
                intent.putExtra("close",closing_time);
                intent.putExtra("market",market_name);
                startActivity(intent);
            }
        });
        sangam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bet_optionActivity.this,Sangam_bet_placed.class);
              intent.putExtra("open",opening_time);
                intent.putExtra("close",closing_time);
                intent.putExtra("market",market_name);
                startActivity(intent);
            }
        });
        single_patti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bet_optionActivity.this,Single_patti.class);
               intent.putExtra("open",opening_time);intent.putExtra("close",closing_time);
                intent.putExtra("market",market_name);
                startActivity(intent);
            }
        });
        double_patti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bet_optionActivity.this,Double_patti_bet.class);
                intent.putExtra("open",opening_time);
               intent.putExtra("close",closing_time);
                intent.putExtra("market",market_name);
                startActivity(intent);
            }
        });




    }
static final int AUTO_TIME_ZONE=1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Validation validation = new Validation();
        if (requestCode==AUTO_TIME_ZONE){
            Toast.makeText(this, "timt  =======", Toast.LENGTH_SHORT).show();

            if (validation.isTimeAutomatic(getApplicationContext())){


             finish();
             startActivity(getIntent());

            }else {
                validation.showDialog(this,"please select device date and time to Automatic","Date&Time",new Intent(Settings.ACTION_DATE_SETTINGS),false);
                Toast.makeText(getApplication().getBaseContext(), "Please select Automatic date and time", Toast.LENGTH_LONG).show();
                // startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
//    Toast.makeText(this, " Otherwise unable to place bet", Toast.LENGTH_SHORT).show();

            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onn", "onResume: *****************************************************");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onn", "onRestart: 8************************************************");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onn", "onStart: ###################################################### ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onn", "onPause: ***********************************");
    }
}