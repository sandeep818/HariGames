package com.games.harigames.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.Place_bet.Get_bet_data_adapter;
import com.games.harigames.Place_bet.Get_bet_data_model;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.games.harigames.User.Home_user;
import com.games.harigames.User.Single_digitBet;
import com.games.harigames.market.Market_cardview_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Market_report extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText input_date;
    TextView result_date ,m_name,m_result_date;
    DatePickerDialog datepicker;
    Switch aSwitch;
    Button result_button;
    FirebaseDatabase database;
    ArrayList<Get_bet_data_model> bet_data_models= new ArrayList<>();
   // ArrayList <Get_bet_data_model> get_bet_data_model= new ArrayList<>();
    String mtype,new_date,custom_date;
    Get_bet_data_adapter get_bet_data_adapter;
    RecyclerView market_report_view;
    String[] years = {"Single","Jodi","Sangam","Single Patti","Double Patti","Triple Patti"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_report);
        result_date= findViewById(R.id.result_date);
        m_name = findViewById(R.id.m_name);
        result_button =findViewById(R.id.result_button);
        market_report_view =findViewById(R.id.market_report_view);
        aSwitch= findViewById(R.id.switch1);
        input_date = findViewById(R.id.time_input);
        input_date.setInputType(InputType.TYPE_NULL);
        Intent intentt= getIntent();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        market_report_view.setLayoutManager(llm);

        final String market= intentt.getStringExtra("market_name");
        m_name.setText("Market Name : "+market);




        Validation validation =new Validation();

        if (validation.isTimeAutomatic(getApplicationContext())){

            try {
                if (Settings.Global.getInt(getContentResolver(),Settings.Global.AUTO_TIME_ZONE)!=1){

                    //       validation.showDialog(this,"please select device date and time to Automatic","Date&Time",new Intent(Settings.ACTION_DATE_SETTINGS),false);
//                    startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),0);
                    Toast.makeText(getApplication().getApplicationContext(), " Select Automatic date Otherwise unable to place bet",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Market_report.this, Home_admin.class);
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
            Intent intent = new Intent(Market_report.this,Home_admin.class);
            startActivity(intent);
            finish();

        }


        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(Market_report.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                result_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                input_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                custom_date=(monthOfYear + 1)+ "/"  + dayOfMonth +  "/" + year;
                            }
                        }, year, month, day);
                datepicker.show();


            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    aSwitch.setText("Close");

                }else {
                    aSwitch.setText("Open");
                }
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.codeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.select_font_size,R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        mtype = spinner.getSelectedItem().toString();


        long currentDate = Calendar.getInstance().getTimeInMillis();
        Date date=new Date(currentDate);
        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy");
        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        new_date=sfd.format(date);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mms");
        Date resultdate = new Date(currentDate);
        String new_time = sdf.format(resultdate);
result_date.setText("Date : "+new_date);

        get_data(market,new_date,mtype,aSwitch.getText().toString());


        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("getit", "onClick: "+mtype +"-- "+new_date+"  --"+custom_date);
              bet_data_models.clear();
                get_data(market,custom_date,mtype,aSwitch.getText().toString());

            }
        });



    }
    public void get_data(final String market_name, final String date, final String market_type, final String market_time){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Place_bet");
        ref.orderByChild("date").equalTo(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Plece_bet> userAllDataModels = new ArrayList<Plece_bet>();
                    String datee = date;
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {

                    Plece_bet allDataModel = userSnapshot.getValue(Plece_bet.class);
                    if (allDataModel.getMarket_name().contains(market_name )&& allDataModel.getMarket_type().contains(market_type)&&allDataModel.getDate().contains(date)) {

                        if ( bet_data_models.size()>0){

                            for (int i=0;i<bet_data_models.size();i++){
                                if (bet_data_models.get(i).getBet_number().contains(allDataModel.getBet_number())){
                                    int total=Integer.parseInt(bet_data_models.get(i).getTotel_bet_amount())+Integer.parseInt(allDataModel.getAmount());
                                    int count =Integer.parseInt(bet_data_models.get(i).getBet_count())+1;
                                    bet_data_models.get(i).setTotel_bet_amount(""+total);
                                    bet_data_models.get(i).setBet_count(""+count);
                                    break;
                                }else {

                                    userAllDataModels.add(allDataModel);
                                    Get_bet_data_model  get_bet_data_model = new Get_bet_data_model(allDataModel.getBet_number(), "1", allDataModel.getAmount(), allDataModel.getMarket_type(), allDataModel.getBet_type());
                                    bet_data_models.add(get_bet_data_model);
                                    break;



                                }
                            }

                        }else {
                            userAllDataModels.add(allDataModel);
                            Get_bet_data_model get_bet_data_model = new Get_bet_data_model(allDataModel.getBet_number(), "1", allDataModel.getAmount(), allDataModel.getMarket_type(), allDataModel.getBet_type());

                            bet_data_models.add(get_bet_data_model);
                        }

                        if (bet_data_models.size()<1){
                            Toast.makeText(Market_report.this, " No Bet Found" , Toast.LENGTH_LONG).show();
                        }

                    }


                }
                getMarket(bet_data_models);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void getMarket(ArrayList<Get_bet_data_model> bet_data_models){
        get_bet_data_adapter = new Get_bet_data_adapter(this,bet_data_models);
        market_report_view.setAdapter(get_bet_data_adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mtype = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), mtype +" "+new_date+" "+input_date, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}