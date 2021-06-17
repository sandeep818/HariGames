package com.games.harigames.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.Place_bet.Result_Declare_model;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Bet_result extends AppCompatActivity {

    TextView result_market_name,result_market_date,opning_result_num,closing_result_num,opning_result_num1,jodi_result_num,closing_result_num1,open_single_result,
            close_single_result;
    EditText opning_result,closing_result;
    Button result_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_result);

        Intent intent1= getIntent();
        final String market= intent1.getStringExtra("marketName");
        result_market_name = findViewById(R.id.result_market_name);
        result_market_name.setText(market);
        result_market_date = findViewById(R.id.result_market_date);
        opning_result =findViewById(R.id.opning_result);
        closing_result = findViewById(R.id.closing_result);
        result_save = findViewById(R.id.result_save);
        //if (open_single_result.)

        long currentDate = Calendar.getInstance().getTimeInMillis();
        Date date=new Date(currentDate);
        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        final String new_date=sfd.format(date);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mms",Locale.US);
        Date resultdate = new Date(currentDate);
        String new_time = sdf.format(resultdate);
        result_market_date.setText(new_date);

//        result_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Result_Declare_model result_declare_model =new Result_Declare_model(market,new_date,)
//                FirebaseDatabase.getInstance().getReference("Market")
//                        .child(market+"-"+new_date)
//                        .setValue(addMarketModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(Add_market.this, "Market Created", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//        });

    }
}