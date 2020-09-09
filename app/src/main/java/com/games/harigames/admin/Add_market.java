package com.games.harigames.admin;


import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.games.harigames.Login_Ragister.Ragister_Master;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class Add_market extends AppCompatActivity {
 public     TextView opningTime,closingTime,show_time1,show_time2;
  public   EditText marketName;
  int t1Hour,t1Minute,t2Hour,t2Minute;
   public Button submitMarkett;


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_market);
        marketName = (EditText) findViewById(R.id.market_name);
        opningTime = (TextView) findViewById(R.id.open_time);
        closingTime = (TextView) findViewById(R.id.close_time);
        show_time1 = (TextView) findViewById(R.id.show_time);
        show_time2 = (TextView) findViewById(R.id.show_time1);
        submitMarkett = findViewById(R.id.market_submitt);

        //int api
        opningTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Add_market.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute = minute;
                        Calendar calendar= Calendar.getInstance();
                        calendar.set(0,0,0,t1Hour,t1Minute);
                        opningTime.setText(DateFormat.format("hh:mm aa",calendar));
                        opningTime.setTextColor(Color.GREEN);
                        show_time1.setAlpha(1);
                    }
                },12,0,false);

                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });

        closingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Add_market.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t2Hour = hourOfDay;
                        t2Minute = minute;
                        Calendar calendar= Calendar.getInstance();
                        calendar.set(0,0,0,t2Hour,t2Minute);
                        closingTime.setText(DateFormat.format("hh:mm aa",calendar));
                        closingTime.setTextColor(Color.RED);
                        show_time2.setAlpha(1);

                    }
                },12,0,false);

                timePickerDialog.updateTime(t2Hour,t2Minute);
                timePickerDialog.show();
            }
        });



        submitMarkett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_market_model addMarketModel=new Add_market_model(marketName.getText().toString(), opningTime.getText().toString(),closingTime.getText().toString());

                FirebaseDatabase.getInstance().getReference("Market")
                        .child(marketName.getText().toString())
                        .setValue(addMarketModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Add_market.this, "Market Created", Toast.LENGTH_SHORT).show();

                    }
                });
                  finish();
//                Toast.makeText(Add_market.this, "working", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
