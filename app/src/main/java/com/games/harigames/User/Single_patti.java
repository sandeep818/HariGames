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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.Place_bet.MainGAdapter;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Single_patti extends AppCompatActivity {
    List<String>Select_Panna_0=new ArrayList<>(Arrays.asList("127", "136", "145", "190", "235", "280", "370", "389", "460", "479", "569", "578"));
    List<String>Select_Panna_1=new ArrayList<>(Arrays.asList("128", "137", "146", "236", "245", "290", "380", "470", "489", "560","579", "678"));
    List<String>Select_Panna_2=new ArrayList<>(Arrays.asList("129", "138", "147", "156", "237", "246", "345", "390","480", "570", "589", "679"));
    List<String>Select_Panna_3=new ArrayList<>(Arrays.asList("120", "139", "148", "157", "238", "247", "256", "346", "490", "580", "670", "689"));
    List<String>Select_Panna_4=new ArrayList<>(Arrays.asList("130", "149", "158", "167", "239", "248", "257", "347", "356", "590", "789", "680"));
    List<String>Select_Panna_5=new ArrayList<>(Arrays.asList("140", "159", "168", "230", "249", "258", "267", "348", "357", "456", "690", "780"));
    List<String>Select_Panna_6=new ArrayList<>(Arrays.asList("123", "150", "169", "178", "240", "259", "268", "349", "358", "367", "457", "790"));
    List<String>Select_Panna_7=new ArrayList<>(Arrays.asList("124", "160", "179", "250", "269", "278", "340", "359", "368", "458", "467", "890"));
    List<String>Select_Panna_8=new ArrayList<>(Arrays.asList("125", "134", "170", "189", "260", "279", "350", "369", "378", "459", "468", "567"));
    List<String>Select_Panna_9=new ArrayList<>(Arrays.asList("126", "135", "180", "234", "270", "289", "360", "379", "450", "469", "478", "568"));
    List<String> betNumber=new ArrayList<>();
    GridView gridView,gridView1,gridView2,gridView3,gridView4,gridView5,gridView6,gridView7,gridView8,gridView9;
    EditText enter_amount,total_amount;
    GridLayout grid_bet_selected;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutParams;
    int bet_count=0;
    Map<Integer,String> betNum_array = new HashMap<Integer,String>();
    Button placebet_single_patti;
    TextView click_toShow,click_toShow1,click_toShow2,click_toShow3,click_toShow4,click_toShow5,click_toShow6,click_toShow7,click_toShow8,click_toShow9;
    Boolean istrue=false;
    RadioGroup radioGroup;
    RadioButton single_patti_open,single_patti_close;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_patti);
        final String market_name= getIntent().getStringExtra("market");
        final String opening_time= getIntent().getStringExtra("open");
        final String closing_time= getIntent().getStringExtra("close");

        placebet_single_patti= (Button)findViewById(R.id.placebet_single_patti);
        radioGroup = (RadioGroup)findViewById(R.id.Radiobutton1_single_patti);
        single_patti_open =(RadioButton) findViewById(R.id.open_single_patti);
        single_patti_close =(RadioButton) findViewById(R.id.close_single_patti);
        gridView= (GridView) findViewById(R.id.grid_viewnum0);
        gridView1= (GridView) findViewById(R.id.grid_viewnum1);
        gridView2= (GridView) findViewById(R.id.grid_viewnum2);
        gridView3= (GridView) findViewById(R.id.grid_viewnum3);
        gridView4= (GridView) findViewById(R.id.grid_viewnum4);
        gridView5= (GridView) findViewById(R.id.grid_viewnum5);
        gridView6= (GridView) findViewById(R.id.grid_viewnum6);
        gridView7= (GridView) findViewById(R.id.grid_viewnum7);
        gridView8= (GridView) findViewById(R.id.grid_viewnum8);
        gridView9= (GridView) findViewById(R.id.grid_viewnum9);
        total_amount= (EditText)findViewById(R.id.total_amount_single_patti);
        enter_amount= (EditText)findViewById(R.id.enter_amount_single_patti);
        click_toShow= (TextView) findViewById(R.id.click_toShow0);
        click_toShow1= (TextView) findViewById(R.id.click_toShow1);
        click_toShow2= (TextView) findViewById(R.id.click_toShow2);
        click_toShow3= (TextView) findViewById(R.id.click_toShow3);
        click_toShow4= (TextView) findViewById(R.id.click_toShow4);
        click_toShow5= (TextView) findViewById(R.id.click_toShow5);
        click_toShow6= (TextView) findViewById(R.id.click_toShow6);
        click_toShow7= (TextView) findViewById(R.id.click_toShow7);
        click_toShow8= (TextView) findViewById(R.id.click_toShow8);
        click_toShow9= (TextView) findViewById(R.id.click_toShow9);
        grid_bet_selected= (GridLayout) findViewById(R.id.grid_bet_selected0);

        enter_amount.addTextChangedListener(textWatcher);
        gridView.setVisibility(View.GONE);
        gridView1.setVisibility(View.GONE);
        gridView2.setVisibility(View.GONE);
        gridView3.setVisibility(View.GONE);
        gridView4.setVisibility(View.GONE);
        gridView5.setVisibility(View.GONE);
        gridView6.setVisibility(View.GONE);
        gridView7.setVisibility(View.GONE);
        gridView8.setVisibility(View.GONE);
        gridView9.setVisibility(View.GONE);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.open_single_patti:
                        type= "Open";
                        break;
                    case R.id.close_single_patti:
                        type="Close";
                        break;
                    default:type="";
                }
            }
        });
        click_toShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView,Select_Panna_0);

            }
        });
        click_toShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView1,Select_Panna_1);


            }
        });
        click_toShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView2,Select_Panna_2);


            }
        });
        click_toShow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView3,Select_Panna_3);


            }
        });
        click_toShow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView4,Select_Panna_4);


            }
        });
        click_toShow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView5,Select_Panna_5);


            }
        });
        click_toShow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView6,Select_Panna_6);


            }
        });
        click_toShow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView7,Select_Panna_7);


            }
        });
        click_toShow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView8,Select_Panna_8);


            }
        });
        click_toShow9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectadapter(false,gridView9,Select_Panna_9);


            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_0);

            }


        });
      gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              item_Clicked(position,Select_Panna_1);

          }


      });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_2);

            }


        });
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_3);

            }


        });
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_4);

            }


        });
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_5);

            }


        });
        gridView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_6);

            }


        });
        gridView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_7);

            }


        });
        gridView8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_8);

            }


        });
        gridView9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item_Clicked(position,Select_Panna_9);

            }


        });

        placebet_single_patti.setOnClickListener(new View.OnClickListener() {
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

                            Intent intent = new Intent(Single_patti.this,Home_user.class);
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
                    Intent intent = new Intent(Single_patti.this,Home_user.class);
                    startActivity(intent);
                    finish();

                }

                int button= radioGroup.getCheckedRadioButtonId();

                if (button ==R.id.open_single_patti || button ==R.id.close_single_patti){

                }else {
                    Toast.makeText(Single_patti.this, "Please Select Bet Type", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(enter_amount.getText())){
                    Toast.makeText(Single_patti.this, "Please enter Amount", Toast.LENGTH_SHORT).show();
                    return;
                }


                SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
                String userName= preferences.getString("username","user");
                if (betNum_array.size()<1){
                    return;
                }

                for (Map.Entry<Integer,String> Num_array:betNum_array.entrySet() ){
                    if (!Num_array.getValue().isEmpty()){
                        int k = Num_array.getKey();
                        String val = Num_array.getValue();
                        Log.d("array", "onClick:  key"+ k +" value - " +val );

                        long currentDate = Calendar.getInstance().getTimeInMillis();
                        Date date=new Date(currentDate);
                        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy");
                        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                        String new_date=sfd.format(date);

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mms");
                        Date resultdate = new Date(currentDate);
                        String new_time = sdf.format(resultdate);
                        int bet_amount=Integer.parseInt(enter_amount.getText().toString().trim());

                        Plece_bet plece_bet=new Plece_bet(""+new_time,""+String.valueOf(k),type,market_name,"Single Patti",userName,new_date,String.valueOf(bet_amount),"pending",false,opening_time,closing_time);
                        FirebaseDatabase.getInstance().getReference("Place_bet").push().setValue(plece_bet).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Single_patti.this, " Bet Placed Successfully ", Toast.LENGTH_SHORT).show();


                            }
                        });
                    }
                    Intent intent = new Intent(Single_patti.this,Bet_optionActivity.class);
                    startActivity(intent);
                    finish();


                }


            }
        });



    }

    private void item_Clicked(int positions,List betlist) {
        if (bet_count==10){
            Toast.makeText(Single_patti.this, "Only 10 Bet will Place", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "you clicked" +positions, Toast.LENGTH_SHORT).show();
        add_Bet_view(positions,betlist);


        if (!TextUtils.isEmpty(enter_amount.getText())){
            getamount();
        }
    }


    public void selectadapter(boolean b,GridView gridView1,List list){
        if (istrue==true){
            gridView1.setVisibility(View.GONE);
            istrue=false;
        }else {
            gridView1.setVisibility(View.VISIBLE);
            istrue=true;
            grid_bet_selected= (GridLayout) findViewById(R.id.grid_bet_selected0);
            linearLayout =(LinearLayout)findViewById(R.id.bet_select_layout);


            // Log.d("TAGd", "onCreate: "+betNumber.length);
            MainGAdapter Adapter = new MainGAdapter(Single_patti.this,list);
            gridView1.setAdapter(Adapter);
        }
    }
    TextWatcher textWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Toast.makeText(Single_patti.this, " Amount should be greater then 10 ", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
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

    public void add_Bet_view(final int position, final List betList) {
        final View betview= getLayoutInflater().inflate(R.layout.jodi_selected_number,null,false);
        final TextView bet_number =(TextView) betview.findViewById(R.id.bet_number);
        if (betNum_array.size()>0){
            for (Map.Entry<Integer,String> Num_array:betNum_array.entrySet() ){
                if (Num_array.getKey()==Integer.parseInt(betList.get(position).toString())){
                    Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();

                    return;
                }
            }
        }



            bet_number.setText(betList.get(position).toString());
            betNum_array.put(Integer.parseInt(betList.get(position).toString()),""+betList.get(position).toString());
           final int num = Integer.parseInt(betList.get(position).toString());

        bet_count++;
        bet_number.setTag(betList.get(position).toString());
        ImageView remove_bet =(ImageView) betview.findViewById(R.id.remove_bet);
        remove_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_Bet_view(betview,num);


                Toast.makeText(Single_patti.this, ""+bet_number.getTag(), Toast.LENGTH_SHORT).show();
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