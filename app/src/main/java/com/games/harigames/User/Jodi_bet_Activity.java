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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.Place_bet.MainGAdapter;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Jodi_bet_Activity extends AppCompatActivity {
List<String> betNumber=new ArrayList<>();
    GridView gridView;
    EditText enter_amount,total_amount;
    GridLayout grid_bet_selected;
    LinearLayout  linearLayout;
    LinearLayout.LayoutParams layoutParams;
    int bet_count=0;
    Map<Integer,String> betNum_array = new HashMap<Integer,String>();
    Button placebet_jodi;
    TextView click_toShow;
    Boolean istrue=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jodi_bet_);

        final String market_name= getIntent().getStringExtra("market");
        final String opening_time= getIntent().getStringExtra("open");
        final String closing_time= getIntent().getStringExtra("close");

        placebet_jodi= (Button)findViewById(R.id.placebet_jodi);
        gridView= (GridView) findViewById(R.id.grid_viewnum);
        total_amount= (EditText)findViewById(R.id.total_amount);
        enter_amount= (EditText)findViewById(R.id.enter_amount);
        click_toShow= (TextView) findViewById(R.id.click_toShow);
        enter_amount.addTextChangedListener(textWatcher);
        gridView.setVisibility(View.GONE);
        click_toShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (istrue==true){
                    gridView.setVisibility(View.GONE);
                    istrue=false;
                }else {
                    gridView.setVisibility(View.VISIBLE);
                    istrue=true;
                    grid_bet_selected= (GridLayout) findViewById(R.id.grid_bet_selected);
                    linearLayout =(LinearLayout)findViewById(R.id.bet_select_layout);

                    for(int i=1; i<100;i++){

                        if (i<10){
                            betNumber.add("0"+String.valueOf(i));
                        }else {

                            if (i==11||i==22||i==33||i==44||i==55||i==66||i==77||i==88||i==99){
                                Log.d("number", "onClick: "+i+1);
                            }else {
                                betNumber.add(String.valueOf(i));
                            }
                        }

                    }
                    // Log.d("TAGd", "onCreate: "+betNumber.length);
                    MainGAdapter Adapter = new MainGAdapter(Jodi_bet_Activity.this,betNumber);
                    gridView.setAdapter(Adapter);
                }


            }
        });




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





                if (bet_count==10){
                    Toast.makeText(Jodi_bet_Activity.this, "Only 10 Bet will Place", Toast.LENGTH_SHORT).show();
                    return;
                }
               // Toast.makeText(getApplicationContext(), "you clicked" +position, Toast.LENGTH_SHORT).show();
                add_Bet_view(position);

               if (!TextUtils.isEmpty(enter_amount.getText())){
                   getamount();
               }

            }
        });

        placebet_jodi.setOnClickListener(new View.OnClickListener() {
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

                            Intent intent = new Intent(Jodi_bet_Activity.this,Home_user.class);
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
                    Intent intent = new Intent(Jodi_bet_Activity.this,Home_user.class);
                    startActivity(intent);
                    finish();

                }
                if (TextUtils.isEmpty(enter_amount.getText())){
                    Toast.makeText(Jodi_bet_Activity.this, "Please enter Amount", Toast.LENGTH_SHORT).show();
                    return;
                }


                SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
                String userName= preferences.getString("username","user");
                if (betNum_array.size()<1){
                    return;
                }



                for (Map.Entry<Integer,String> entry:betNum_array.entrySet()){
                    if (!entry.getValue().isEmpty()){
                        int k = entry.getKey();
                        String val = entry.getValue();
                        Log.d("array", "onClick:  key"+ k +" value - " +val );


//                        String s = String.valueOf(map.get("timestamp"));
//                        String ss = type;
//                        long t =Long.parseLong(s);
//                       Date date=new Date(t);
//        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy");
//
//        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
//        String time= sfd.format(date);
                        long currentDate = Calendar.getInstance().getTimeInMillis();
                        Date date=new Date(currentDate);
                        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy");
                        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                        String new_date=sfd.format(date);

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mms");
                        Date resultdate = new Date(currentDate);
                        String new_time = sdf.format(resultdate);
                        int bet_amount=Integer.parseInt(enter_amount.getText().toString().trim());

                        Plece_bet plece_bet=new Plece_bet(""+new_time,""+String.valueOf(k),"Jodi",market_name,"Jodi",userName,new_date,String.valueOf(bet_amount),"pending",false,opening_time,closing_time);
                        FirebaseDatabase.getInstance().getReference("Place_bet").push().setValue(plece_bet).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Jodi_bet_Activity.this, " Bet Placed Successfully ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Jodi_bet_Activity.this,Bet_optionActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                    }


                }

            }
        });


    }
    TextWatcher textWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (betNum_array.size()<1){
                Toast.makeText(Jodi_bet_Activity.this, "Please Select Number To Place Bet", Toast.LENGTH_SHORT).show();
            }
            getamount();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void getamount() {
        if (!TextUtils.isEmpty(enter_amount.getText().toString().trim())){
            total_amount.setText("₹ "+Integer.parseInt(enter_amount.getText().toString().trim())*betNum_array.size());
        }else {
            total_amount.setText("₹ "+0.00);
        }

    }

    public void add_Bet_view(final int position) {
        final View betview= getLayoutInflater().inflate(R.layout.jodi_selected_number,null,false);
        final TextView bet_number =(TextView) betview.findViewById(R.id.bet_number);
        if (betNum_array.size()>0){
            for (Map.Entry<Integer,String> Num_array:betNum_array.entrySet() ){
                if (Num_array.getKey()==position+1){
                    Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();

                    return;
                }
            }
        }

        if (position<9){
            bet_number.setText(""+betNumber.get(position));
            betNum_array.put(position+1,""+position);

        }else {
            bet_number.setText(betNumber.get(position));
            betNum_array.put(position+1,""+position);
        }

        bet_count++;
        bet_number.setTag(betNumber.get(position));
        ImageView remove_bet =(ImageView) betview.findViewById(R.id.remove_bet);
        remove_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_Bet_view(betview,position);


                Toast.makeText(Jodi_bet_Activity.this, ""+bet_number.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
        grid_bet_selected.addView(betview);

      //  linearLayout.addView(betview);
    }
    public void remove_Bet_view(View view,int p) {
      //  linearLayout.removeView(view);
        grid_bet_selected.removeView(view);
        bet_count--;
        betNum_array.remove(p+1);
        if (!TextUtils.isEmpty(enter_amount.getText())){
            getamount();
        }

    //    Log.d("bet", "remove_Bet_view: "+betNum_array.get(p+1));
    }

}