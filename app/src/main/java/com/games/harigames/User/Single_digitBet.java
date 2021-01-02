package com.games.harigames.User;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;




import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.MainActivity;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class Single_digitBet extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText single_zero,single_one,single_two,single_three,single_four,single_five,single_six,single_seven,single_eight,single_nine,single_total;
    RadioButton single_open,single_close;
    Button placebet_single,reset_single;
    DatabaseReference databaseReference;
    RadioGroup radioGroup;
    TextView date_Time;
    String time;
    RadioButton radioButton;
    String type;


    String date1;
    Location location;
    double describeContents;
    List<Address> addresses;
    Geocoder geocoder;
    int answer;
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    long times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_digit_bet);
        final String opent=getIntent().getStringExtra("open");
        final String closet=getIntent().getStringExtra("close");
        final String marketN=getIntent().getStringExtra("market");
        date_Time = (TextView)findViewById(R.id.date_Time);

      //  setSettingsAutomaticDateTimeIfNeeded();
//if (isTimeAutomatic(getApplicationContext())){
//
//   try {
//       if (Settings.Global.getInt(getContentResolver(),Settings.Global.AUTO_TIME_ZONE)!=1){
//           startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
//           Toast.makeText(getApplication().getApplicationContext(), " Otherwise unable to place bet", Toast.LENGTH_LONG).show();
//       }
//   }catch (Exception e){
//
//   }
//
//}else {
//    Toast.makeText(getApplication().getBaseContext(), "Please select Automatic date and time", Toast.LENGTH_LONG).show();
//    startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
////    Toast.makeText(this, " Otherwise unable to place bet", Toast.LENGTH_SHORT).show();
//    return;
//}


//        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
//            LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//            time = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime();
//        }
//
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
//                PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
//                        PackageManager.PERMISSION_GRANTED) {
//
//
//            // Permission already Granted
//            //Do your work here
//           LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
////           final long time = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime();
////            location = locMan
////                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
////
////            geocoder= new Geocoder(this);
////            times =location.getTime();
////            Date resultdate = new Date(times);
////            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
////
////            date1 = sdf.format(resultdate);
////            Date date = new Date(times);
////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
////
////            time = simpleDateFormat.format(date);
////            date_Time.setText(date1 +" " +time);
//
////            times =System.currentTimeMillis();
////            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
////            Date resultdate = new Date(times);
////            String date1 = sdf.format(resultdate);
////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
////            Date date = new Date(times);
////            String time = simpleDateFormat.format(date);
//            //Perform operations here only which requires permission
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//        }










//        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
//       }
//
//
//        location = locationManager
//                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//        geocoder= new Geocoder(this);















        DatabaseReference offsetRef = FirebaseDatabase.getInstance().getReference(".info/serverTimeOffset");
        offsetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                long offset = snapshot.getValue(Long.class);
                long estimatedServerTimeMs = System.currentTimeMillis() + offset;
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date resultdate = new Date(estimatedServerTimeMs);
                Log.d("Tag11","date from firebase  "+sdf.format(resultdate).toString());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });








        radioGroup = (RadioGroup)findViewById(R.id.Radiobutton1);
        single_zero =(EditText)findViewById(R.id.single_zero);
        single_one =(EditText)findViewById(R.id.single_one);
        single_two =(EditText)findViewById(R.id.single_two);
        single_three =(EditText)findViewById(R.id.single_three);
        single_four =(EditText)findViewById(R.id.single_four);
        single_five =(EditText)findViewById(R.id.single_five);
        single_six =(EditText)findViewById(R.id.single_six);
        single_seven =(EditText)findViewById(R.id.single_seven);
        single_eight =(EditText)findViewById(R.id.single_eight);
        single_nine =(EditText)findViewById(R.id.single_nine);
        single_open =(RadioButton) findViewById(R.id.single_open0);
        single_close =(RadioButton) findViewById(R.id.single_close);
        placebet_single =(Button) findViewById(R.id.placebet_single);
        reset_single =(Button) findViewById(R.id.reset_single);
        single_total = (EditText)findViewById(R.id.single_total);


        single_open.setText("Open - "+opent);
        single_close.setText("Close - "+closet);



        single_zero.setOnFocusChangeListener(this);
        single_one.setOnFocusChangeListener(this);
        single_two.setOnFocusChangeListener(this);
        single_three.setOnFocusChangeListener(this);
        single_four.setOnFocusChangeListener(this);
        single_five.setOnFocusChangeListener(this);
        single_six.setOnFocusChangeListener(this);
        single_seven.setOnFocusChangeListener(this);
        single_eight.setOnFocusChangeListener(this);
        single_nine.setOnFocusChangeListener(this);
        //
        single_zero.addTextChangedListener(textWatcher);
        single_one.addTextChangedListener(textWatcher);
        single_two.addTextChangedListener(textWatcher);
        single_three.addTextChangedListener(textWatcher);
        single_four.addTextChangedListener(textWatcher);
        single_five.addTextChangedListener(textWatcher);
        single_six.addTextChangedListener(textWatcher);
        single_seven.addTextChangedListener(textWatcher);
        single_eight.addTextChangedListener(textWatcher);
        single_nine.addTextChangedListener(textWatcher);


      reset_single.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              switch (checkedId){
                  case R.id.single_open0:
                      type= "Open";
                      break;
                  case R.id.single_close:
                      type="Close";
                      break;
                  default:type="";
              }
          }
      });

        placebet_single.setOnClickListener(new View.OnClickListener() {
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

                            Intent intent = new Intent(Single_digitBet.this,Home_user.class);
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
                    Intent intent = new Intent(Single_digitBet.this,Home_user.class);
                    startActivity(intent);
                    finish();

                }


           if (TextUtils.isEmpty(single_total.getText())){
               Toast.makeText(Single_digitBet.this, "At least Select One Number And Amount ", Toast.LENGTH_SHORT).show();
           }




                HashMap<Integer,String> numbers= new HashMap<Integer, String>();
                ArrayList<String> values = new ArrayList<String>();
                int[] ids = new int[]{R.id.single_zero,R.id.single_one,R.id.single_two,R.id.single_three,R.id.single_four,R.id.single_five,
                        R.id.single_six,R.id.single_seven,R.id.single_eight,R.id.single_nine };//and so on

                for(int id : ids){
                    EditText t = (EditText) findViewById(id);

                        values.add(Integer.parseInt(t.getTag().toString()),t.getText().toString());
                        numbers.put(Integer.parseInt(t.getTag().toString()),t.getText().toString());

                }


                int button= radioGroup.getCheckedRadioButtonId();

                if (button ==R.id.single_open0 || button ==R.id.single_close){

                }else {
                    Toast.makeText(Single_digitBet.this, "Please Select Bet Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
                String userName= preferences.getString("username","user");

                for (Map.Entry<Integer,String> entry:numbers.entrySet()){
                    if (!entry.getValue().isEmpty()){
                        int k = entry.getKey();
                        String val = entry.getValue();
                        Log.d("array", "onClick:  key"+ k +" value - " +val +" time" + times + " type -" +type);
                        Map map = new HashMap();
                        map.put("timestamp", ServerValue.TIMESTAMP);

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

                        Plece_bet plece_bet=new Plece_bet(""+new_time,""+String.valueOf(k),type,marketN,"Single",userName,new_date,String.valueOf(val),"pending",false,opent,closet);
                        FirebaseDatabase.getInstance().getReference("Place_bet").push().setValue(plece_bet).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Single_digitBet.this, " Bet Placed Successfully ", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }









            }
        });

    }





    public static boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }













    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(single_zero.getText().toString().trim()) ||
                    !TextUtils.isEmpty(single_one.getText().toString().trim()) ||
                    !TextUtils.isEmpty(single_two.getText().toString().trim())||
                    !TextUtils.isEmpty(single_three.getText().toString().trim())||
                    !TextUtils.isEmpty(single_four.getText().toString().trim())||
                    !TextUtils.isEmpty(single_five.getText().toString().trim())||
                    !TextUtils.isEmpty(single_six.getText().toString().trim())||
                    !TextUtils.isEmpty(single_seven.getText().toString().trim())||
                    !TextUtils.isEmpty(single_eight.getText().toString().trim())||
                    !TextUtils.isEmpty(single_nine.getText().toString().trim())){
                int firstValue = TextUtils.isEmpty(single_zero.getText().toString().trim()) ? 0 : Integer.parseInt(single_zero.getText().toString().trim());
                int secondValue = TextUtils.isEmpty(single_one.getText().toString().trim()) ? 0 : Integer.parseInt(single_one.getText().toString().trim());
                int thirdValue = TextUtils.isEmpty(single_two.getText().toString().trim()) ? 0 : Integer.parseInt(single_two.getText().toString().trim());
                int fourValue = TextUtils.isEmpty(single_three.getText().toString().trim()) ? 0 : Integer.parseInt(single_three.getText().toString().trim());
                int fiveValue = TextUtils.isEmpty(single_four.getText().toString().trim()) ? 0 : Integer.parseInt(single_four.getText().toString().trim());
                int sixValue = TextUtils.isEmpty(single_five.getText().toString().trim()) ? 0 : Integer.parseInt(single_five.getText().toString().trim());
                int sevenValue = TextUtils.isEmpty(single_six.getText().toString().trim()) ? 0 : Integer.parseInt(single_six.getText().toString().trim());
                int eightValue = TextUtils.isEmpty(single_seven.getText().toString().trim()) ? 0 : Integer.parseInt(single_seven.getText().toString().trim());
                int nineValue = TextUtils.isEmpty(single_eight.getText().toString().trim()) ? 0 : Integer.parseInt(single_eight.getText().toString().trim());
                int tenValue = TextUtils.isEmpty(single_nine.getText().toString().trim()) ? 0 : Integer.parseInt(single_nine.getText().toString().trim());

                answer = firstValue + secondValue + thirdValue + fourValue + fiveValue + sixValue + sevenValue + eightValue + nineValue + tenValue;
                single_total.setText("₹ "+String.valueOf(answer));

            }else {
                single_total.setText("");
            }

        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.single_zero:
                if (!single_zero.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_zero.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_one:
                if (!single_one.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_one.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_two:
                if (!single_two.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_two.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_three:
                if (!single_three.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_three.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_four:
                if (!single_four.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_four.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_five:
                if (!single_five.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_five.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_six:
                if (!single_six.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_six.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_seven:
                if (!single_seven.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_seven.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_eight:
                if (!single_eight.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_eight.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.single_nine:
                if (!single_nine.getText().toString().trim().equals("")) {
                    if (!hasFocus && Integer.parseInt(single_nine.getText().toString().trim()) < 10) {
                        Toast.makeText(Single_digitBet.this, "Amount should be greater then 10", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    @RequiresPermission("android.permission.WRITE_SETTINGS")
    public void setSettingsAutomaticDateTimeIfNeeded() {
        String timeSettings = android.provider.Settings.Global.getString(
                this.getContentResolver(),
                android.provider.Settings.Global.AUTO_TIME);
        if (timeSettings.contentEquals("0")) {
//            android.provider.Settings.Global.putString(
//                    this.getContentResolver(),
//                    android.provider.Settings.Global.AUTO_TIME, "1");

            Toast.makeText(this, " please set automatic", Toast.LENGTH_SHORT).show();
        }
    }


}
