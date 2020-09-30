package com.games.harigames.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.games.harigames.Place_bet.MainGAdapter;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jodi_bet_Activity extends AppCompatActivity {
String[] betNumber;
    GridView gridView;
    EditText enter_amount,total_amount;
    GridLayout grid_bet_selected;
    LinearLayout  linearLayout;
    LinearLayout.LayoutParams layoutParams;
    int bet_count=0;
    Map<Integer,String> betNum_array = new HashMap<Integer,String>();
    Button placebet_jodi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jodi_bet_);
        placebet_jodi= (Button)findViewById(R.id.placebet_jodi);
        gridView= (GridView) findViewById(R.id.grid_viewnum);
        total_amount= (EditText)findViewById(R.id.total_amount);
        enter_amount= (EditText)findViewById(R.id.enter_amount);
        enter_amount.addTextChangedListener(textWatcher);
        grid_bet_selected= (GridLayout) findViewById(R.id.grid_bet_selected);
        linearLayout =(LinearLayout)findViewById(R.id.bet_select_layout);
        betNumber = new String[99];
        for(int i=0; i<99;i++){

            if (i<9){
                betNumber[i]="0"+String.valueOf(1+i);
            }else {
                betNumber[i]=String.valueOf(1+i);
            }

        }
       // Log.d("TAGd", "onCreate: "+betNumber.length);
        MainGAdapter Adapter = new MainGAdapter(Jodi_bet_Activity.this,betNumber);
        gridView.setAdapter(Adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bet_count==10){
                    Toast.makeText(Jodi_bet_Activity.this, "Only 10 Bet will Place", Toast.LENGTH_SHORT).show();
                    return;
                }
               // Toast.makeText(getApplicationContext(), "you clicked" +position, Toast.LENGTH_SHORT).show();
                add_Bet_view(position);
                bet_count++;
               if (!TextUtils.isEmpty(enter_amount.getText())){
                   getamount();
               }

            }
        });

        placebet_jodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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


//                        Plece_bet plece_bet=new Plece_bet(times,String.valueOf(k),type,marketN,"we",userName,"12-12-12",String.valueOf(val),"pending",false);
//                        FirebaseDatabase.getInstance().getReference("Place_bet").push().setValue(plece_bet).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(Jodi_bet_Activity.this, " Bet Placed Successfully ", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
                    }


                }


            }
        });


    }
    TextWatcher textWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Toast.makeText(Jodi_bet_Activity.this, " Amount should be greater then 10 ", Toast.LENGTH_SHORT).show();

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
        total_amount.setText("₹ "+Integer.parseInt(enter_amount.getText().toString().trim())*betNum_array.size());
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
            bet_number.setText("0"+String.valueOf(position+1));
            betNum_array.put(position+1,""+position);

        }else {
            bet_number.setText(String.valueOf(position+1));
            betNum_array.put(position+1,""+position);
        }

        bet_number.setTag(position+1);
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