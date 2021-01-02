package com.games.harigames.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class Sangam_bet_placed extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText sangam_zero,sangam_one,sangam_two,sangam_three,sangam_four,sangam_five,sangam_six,
            sangam_seven,sangam_eight,sangam_nine,sangam_total;

    Button placebet_sangam,reset_sangam;
    int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sangam_bet_placed);
        final String market_name= getIntent().getStringExtra("market");
        final String opening_time= getIntent().getStringExtra("open");
        final String closing_time= getIntent().getStringExtra("close");



        sangam_zero =(EditText)findViewById(R.id.sangam_zero);
        sangam_one =(EditText)findViewById(R.id.sangam_one);
        sangam_two =(EditText)findViewById(R.id.sangam_two);
        sangam_three =(EditText)findViewById(R.id.sangam_three);
        sangam_four =(EditText)findViewById(R.id.sangam_four);
        sangam_five =(EditText)findViewById(R.id.sangam_five);
        sangam_six =(EditText)findViewById(R.id.sangam_six);
        sangam_seven =(EditText)findViewById(R.id.sangam_seven);
        sangam_eight =(EditText)findViewById(R.id.sangam_eight);
        sangam_nine =(EditText)findViewById(R.id.sangam_nine);

        placebet_sangam =(Button) findViewById(R.id.placebet_sangam);
        reset_sangam =(Button) findViewById(R.id.reset_sangam);
        sangam_total = (EditText)findViewById(R.id.sangam_total);


        sangam_zero.setOnFocusChangeListener(this);
        sangam_one.setOnFocusChangeListener(this);
        sangam_two.setOnFocusChangeListener(this);
        sangam_three.setOnFocusChangeListener(this);
        sangam_four.setOnFocusChangeListener(this);
        sangam_five.setOnFocusChangeListener(this);
        sangam_six.setOnFocusChangeListener(this);
        sangam_seven.setOnFocusChangeListener(this);
        sangam_eight.setOnFocusChangeListener(this);
        sangam_nine.setOnFocusChangeListener(this);
        //
        sangam_zero.addTextChangedListener(textWatcher);
        sangam_one.addTextChangedListener(textWatcher);
        sangam_two.addTextChangedListener(textWatcher);
        sangam_three.addTextChangedListener(textWatcher);
        sangam_four.addTextChangedListener(textWatcher);
        sangam_five.addTextChangedListener(textWatcher);
        sangam_six.addTextChangedListener(textWatcher);
        sangam_seven.addTextChangedListener(textWatcher);
        sangam_eight.addTextChangedListener(textWatcher);
        sangam_nine.addTextChangedListener(textWatcher);


        placebet_sangam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation validation =new Validation();

                if (validation.isTimeAutomatic(getApplicationContext())){

                    try {
                        if (Settings.Global.getInt(getContentResolver(),Settings.Global.AUTO_TIME_ZONE)!=1){

                            //       validation.showDialog(this,"please select device date and time to Automatic","Date&Time",new Intent(Settings.ACTION_DATE_SETTINGS),false);
//                    startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
                            Toast.makeText(getApplication().getApplicationContext(), " Select Automatic date Otherwise unable to place bet",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Sangam_bet_placed.this,Home_user.class);
                            startActivity(intent);
                            finish();
                        }
                    }catch (Exception e){

                    }

                }else {

                    // validation.showDialog(this,"please select device date and time to Automatic","Date&Time",new Intent(Settings.ACTION_DATE_SETTINGS),false);
                    Toast.makeText(getApplication().getBaseContext(), "Please select Automatic date and time", Toast.LENGTH_LONG).show();
                    // startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
//    Toast.makeText(this, " Otherwise unable to place bet", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sangam_bet_placed.this,Home_user.class);
                    startActivity(intent);
                    finish();

                }


                if (TextUtils.isEmpty(sangam_total.getText())){
                    Toast.makeText(Sangam_bet_placed.this, "At least Select One Number And Amount ", Toast.LENGTH_SHORT).show();
                }


                HashMap<String,String> numbers= new HashMap<String, String>();
                ArrayList<String> values = new ArrayList<String>();
                int[] ids = new int[]{R.id.sangam_zero,R.id.sangam_one,R.id.sangam_two,R.id.sangam_three,R.id.sangam_four,R.id.sangam_five,
                        R.id.sangam_six,R.id.sangam_seven,R.id.sangam_eight,R.id.sangam_nine };//and so on

                for(int id : ids){
                    EditText t = (EditText) findViewById(id);

                   // values.add(Integer.parseInt(t.getTag().toString()),t.getText().toString());
                    numbers.put(t.getTag().toString(),t.getText().toString());

                }



                SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
                String userName= preferences.getString("username","user");

                for (Map.Entry<String,String> entry:numbers.entrySet()){
                    if (!entry.getValue().isEmpty()){
                        String k = entry.getKey();
                        String val = entry.getValue();
                        Log.d("array", "onClick:  key"+ k +" value - " +val +" time");

                        long currentDate = Calendar.getInstance().getTimeInMillis();
                        Date date=new Date(currentDate);
                        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                        String new_date=sfd.format(date);

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mms",Locale.US);
                        Date resultdate = new Date(currentDate);
                        String new_time = sdf.format(resultdate);

                        Plece_bet plece_bet=new Plece_bet(""+new_time,""+String.valueOf(k),"Sangam",market_name,"Sangam",userName,new_date,String.valueOf(val),"pending",false,opening_time,closing_time);
                        FirebaseDatabase.getInstance().getReference("Place_bet").push().setValue(plece_bet).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Sangam_bet_placed.this, " Bet Placed Successfully ", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }









            }
        });





    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.single_zero:
                if (!sangam_zero.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_zero.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_one:
                if (!sangam_one.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_one.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_two:
                if (!sangam_two.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_two.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_three:
                if (!sangam_three.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_three.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_four:
                if (!sangam_four.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_four.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_five:
                if (!sangam_five.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_five.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_six:
                if (!sangam_six.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_six.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_seven:
                if (!sangam_seven.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_seven.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_eight:
                if (!sangam_eight.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_eight.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_nine:
                if (!sangam_nine.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(sangam_nine.getText().toString().trim()) < 10) {
                        Toast.makeText(Sangam_bet_placed.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }

    }

    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {



        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(sangam_zero.getText().toString().trim()) ||
                    !TextUtils.isEmpty(sangam_one.getText().toString().trim()) ||
                    !TextUtils.isEmpty(sangam_two.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_three.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_four.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_five.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_six.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_seven.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_eight.getText().toString().trim())||
                    !TextUtils.isEmpty(sangam_nine.getText().toString().trim())){
                int firstValue = TextUtils.isEmpty(sangam_zero.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_zero.getText().toString().trim());
                int secondValue = TextUtils.isEmpty(sangam_one.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_one.getText().toString().trim());
                int thirdValue = TextUtils.isEmpty(sangam_two.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_two.getText().toString().trim());
                int fourValue = TextUtils.isEmpty(sangam_three.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_three.getText().toString().trim());
                int fiveValue = TextUtils.isEmpty(sangam_four.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_four.getText().toString().trim());
                int sixValue = TextUtils.isEmpty(sangam_five.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_five.getText().toString().trim());
                int sevenValue = TextUtils.isEmpty(sangam_six.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_six.getText().toString().trim());
                int eightValue = TextUtils.isEmpty(sangam_seven.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_seven.getText().toString().trim());
                int nineValue = TextUtils.isEmpty(sangam_eight.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_eight.getText().toString().trim());
                int tenValue = TextUtils.isEmpty(sangam_nine.getText().toString().trim()) ? 0 : Integer.parseInt(sangam_nine.getText().toString().trim());

                answer = firstValue + secondValue + thirdValue + fourValue + fiveValue + sixValue + sevenValue + eightValue + nineValue + tenValue;
                sangam_total.setText("₹ "+String.valueOf(answer));

            }else {
                sangam_total.setText("");
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}