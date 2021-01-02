package com.games.harigames.User;

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
import com.games.harigames.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Double_patti_bet extends AppCompatActivity {
    List<String> Select_Panna_0=new ArrayList<>(Arrays.asList("118", "244", "226", "299", "334", "488", "550", "668", "677"));
    List<String>Select_Panna_1=new ArrayList<>(Arrays.asList("100", "119", "155", "227", "335", "344", "399", "588", "669"));
    List<String>Select_Panna_2=new ArrayList<>(Arrays.asList("110", "200", "228", "255", "336", "499", "688", "778", "660"));
    List<String>Select_Panna_3=new ArrayList<>(Arrays.asList("166", "229", "300", "337", "355", "445", "599", "779", "788"));
    List<String>Select_Panna_4=new ArrayList<>(Arrays.asList("112", "220", "266", "338", "400", "446", "455", "699", "770"));
    List<String>Select_Panna_5=new ArrayList<>(Arrays.asList("113", "122", "177", "339", "366", "447", "500", "799", "889"));
    List<String>Select_Panna_6=new ArrayList<>(Arrays.asList("114", "277", "330", "448", "466", "556", "600", "880", "899"));
    List<String>Select_Panna_7=new ArrayList<>(Arrays.asList("115", "133", "188", "223", "377", "449", "557", "566", "700"));
    List<String>Select_Panna_8=new ArrayList<>(Arrays.asList("116", "224", "233", "288", "440", "477", "558", "800", "990"));
    List<String>Select_Panna_9=new ArrayList<>(Arrays.asList("117", "144", "199", "225", "388", "559", "577", "667", "900"));
    List<String> betNumber=new ArrayList<>();
    GridView gridView,gridView1,gridView2,gridView3,gridView4,gridView5,gridView6,gridView7,gridView8,gridView9;
    EditText enter_amount,total_amount;
    GridLayout grid_bet_selected;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutParams;
    int bet_count=0;
    Map<Integer,String> betNum_array = new HashMap<Integer,String>();
    Button placebet_double_patti;
    TextView click_toShow,click_toShow1,click_toShow2,click_toShow3,click_toShow4,click_toShow5,click_toShow6,click_toShow7,click_toShow8,click_toShow9;
    Boolean istrue=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_patti_bet);

        placebet_double_patti= (Button)findViewById(R.id.placebet_double_patti);
        gridView= (GridView) findViewById(R.id.grid_viewnum0_double_patti);
        gridView1= (GridView) findViewById(R.id.grid_viewnum1_double_patti);
        gridView2= (GridView) findViewById(R.id.grid_viewnum2_double_patti);
        gridView3= (GridView) findViewById(R.id.grid_viewnum3_double_patti);
        gridView4= (GridView) findViewById(R.id.grid_viewnum4_double_patti);
        gridView5= (GridView) findViewById(R.id.grid_viewnum5_double_patti);
        gridView6= (GridView) findViewById(R.id.grid_viewnum6_double_patti);
        gridView7= (GridView) findViewById(R.id.grid_viewnum7_double_patti);
        gridView8= (GridView) findViewById(R.id.grid_viewnum8_double_patti);
        gridView9= (GridView) findViewById(R.id.grid_viewnum9_double_patti);
        total_amount= (EditText)findViewById(R.id.total_amount_double_patti);
        enter_amount= (EditText)findViewById(R.id.enter_amount_double_patti);
        click_toShow= (TextView) findViewById(R.id.click_toShow0_double_patti);
        click_toShow1= (TextView) findViewById(R.id.click_toShow1_double_patti);
        click_toShow2= (TextView) findViewById(R.id.click_toShow2_double_patti);
        click_toShow3= (TextView) findViewById(R.id.click_toShow3_double_patti);
        click_toShow4= (TextView) findViewById(R.id.click_toShow4_double_patti);
        click_toShow5= (TextView) findViewById(R.id.click_toShow5_double_patti);
        click_toShow6= (TextView) findViewById(R.id.click_toShow6_double_patti);
        click_toShow7= (TextView) findViewById(R.id.click_toShow7_double_patti);
        click_toShow8= (TextView) findViewById(R.id.click_toShow8_double_patti);
        click_toShow9= (TextView) findViewById(R.id.click_toShow9_double_patti);
        grid_bet_selected= (GridLayout) findViewById(R.id.grid_bet_selected0_double_patti);

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

        placebet_double_patti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(enter_amount.getText())){
                    Toast.makeText(Double_patti_bet.this, "Please enter Amount", Toast.LENGTH_SHORT).show();
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


    private void item_Clicked(int positions,List betlist) {
        if (bet_count==10){
            Toast.makeText(Double_patti_bet.this, "Only 10 Bet will Place", Toast.LENGTH_SHORT).show();
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
            grid_bet_selected= (GridLayout) findViewById(R.id.grid_bet_selected0_double_patti);
            linearLayout =(LinearLayout)findViewById(R.id.bet_select_layout);


            // Log.d("TAGd", "onCreate: "+betNumber.length);
            MainGAdapter Adapter = new MainGAdapter(Double_patti_bet.this,list);
            gridView1.setAdapter(Adapter);
        }
    }
    TextWatcher textWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Toast.makeText(Double_patti_bet.this, " Amount should be greater then 10 ", Toast.LENGTH_SHORT).show();

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


                Toast.makeText(Double_patti_bet.this, ""+bet_number.getTag(), Toast.LENGTH_SHORT).show();
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