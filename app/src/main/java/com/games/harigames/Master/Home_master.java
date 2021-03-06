package com.games.harigames.Master;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.games.harigames.Login_Ragister.Login;
import com.games.harigames.Login_Ragister.Ragister_Master;
import com.games.harigames.Login_Ragister.Ragister_user;
import com.games.harigames.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Home_master extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView greetings;
    TextView username;
    ImageView bgimage;
    BottomNavigationView navigationView;
    ScrollView myScrollView;
    private DrawerLayout mdrawerLayout;
    Dialog dialog;
    NavigationView mynavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
       // myScrollView=findViewById(R.id.myScrollView);
        //hide scrollbar from ScrollView
      //  myScrollView.setVerticalScrollBarEnabled(false);
      //  myScrollView.setHorizontalScrollBarEnabled(false);
        dialog = new Dialog(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.animate().alpha(1).setDuration(300).setStartDelay(1600);
        setSupportActionBar(toolbar);
        mdrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle barDrawerToggle = new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,R.string.navigaation_drawer_open,R.string.navigaation_drawer_close);
        mdrawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();



        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.account);

        //draweer navigation item select
        mynavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mynavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               if (menuItem.getItemId()==R.id.add_user){
                  showPopup();
               }

               switch (menuItem.getItemId()){
                   case R.id.add_marketbtn:
                      // Intent M_intent =new Intent(Home_master.this, Add_market.class);
                     //  startActivity(M_intent);

                       break;
                   case R.id.view_market:
                       Intent v_intent =new Intent(Home_master.this, View_market.class);
                       startActivity(v_intent);

                       break;
                   case R.id.logout:
               //        logout_user();
//                       SharedPreferences pref = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
//                       SharedPreferences.Editor editor= pref.edit();
//                       editor.putBoolean("isLogin",false);
//                       editor.clear();
//                        editor.apply();
                       FirebaseAuth.getInstance().signOut();
                       Intent intent = new Intent(Home_master.this, Login.class);
                       startActivity(intent);
                       finish();
                       break;
               }

                return true;
            }
        });


        greetings = findViewById(R.id.greeting);
        username=findViewById(R.id.name);
        bgimage = findViewById(R.id.imagebg);

//        bgimage.animate().translationY(-2100).setDuration(800).setStartDelay(800);
//        greetings.animate().translationY(-1300).translationX(-180).setDuration(800).setStartDelay(800);
//        username.animate().translationY(-1350).translationX(-180).setDuration(800).setStartDelay(800);




//        //Get the time of day
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//
//        //Set greeting
//        String greeting = null;
//        if(hour>= 12 && hour < 17){
//            greeting = "Good Afternoon";
//        } else if(hour >= 17 && hour < 21){
//            greeting = "Good Evening";
//        } else if(hour >= 21 && hour < 24){
//            greeting = "Good Night";
//        } else {
//            greeting = "Good Morning";
//        }


        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        Calendar cal =   df.getCalendar();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        //Set greeting
        String greeting = null;
        if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 24){
            greeting = "Good Night";
        } else {
            greeting = "Good Morning";
        }

        greetings.setText(greeting);

        username.setText(getIntent().getStringExtra("user"));

//        Account account_fragment = new Account();
//        account_fragment.setArguments(bundle);

      //  InternetConnection internetConnection= new InternetConnection();
//        if (internetConnection.haveNetwork()){
//            Toast.makeText(Home.this, "connected", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(Home.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
//        }

    }

    private void logout_user() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logout Successful !", Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        editor.putBoolean("isLogin",false);
        Intent intent = new Intent(Home_master.this,Login.class);
        startActivity(intent);
        finish();
    }


    Bid bid_fragment= new Bid();
    Details_master details_admin_fragment = new Details_master();
    Support support_fragment = new Support();
    Wallet wallet_fragment = new Wallet();
    Account account_fragment = new Account();





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
                case R.id.account:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,account_fragment).commit();
                return true;
            case R.id.bid:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,bid_fragment).commit();
                return true;


            case R.id.message:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,support_fragment).commit();
                return true;


        }
        return false;
    }
    boolean doubleBackToExitPressedOnce = false;
    //close opened navigation drawer via back button
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);


}


    public void showPopup(){
        TextView closeText;
        TextView master_ragister;
        TextView user_ragister;
        TextView agent_ragister;

        dialog.setContentView(R.layout.add_user_popup);
        closeText = (TextView) dialog.findViewById(R.id.close_text);
        master_ragister=(TextView) dialog.findViewById(R.id.master_ragister);
        user_ragister = (TextView) dialog.findViewById(R.id.user_ragister) ;
        agent_ragister = (TextView)dialog.findViewById(R.id.agent_ragister);
        master_ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_master.this, "You are not Athuorized", Toast.LENGTH_SHORT).show();
            }
        });
        user_ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_master.this, Ragister_user.class);
                startActivity(intent);
                dialog.dismiss();
                //*****************animation when witch activity*********************
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
        closeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mdrawerLayout.closeDrawer(GravityCompat.START);
        dialog.show();
    }
}
