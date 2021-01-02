package com.games.harigames.Login_Ragister;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Validation extends Application {


    public boolean isConnected(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
           return connected = true;
        }
        else{
            return   connected = false;
        }

    }
    public  boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }
    public Dialog dialog;
    public void showDialog(final Activity activity, String msg, String heading, final Intent intent, final boolean b) {

        dialog = new Dialog(activity,android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        dialog.setContentView(R.layout.network_error);
       // dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView textmsg=dialog.findViewById(R.id.dialog_resion);
        TextView texthead=dialog.findViewById(R.id.dialog_heading);
        if (!msg.isEmpty()){
            textmsg.setText(msg);
            texthead.setText(heading);
        }

        Button mDialogNo = dialog.findViewById(R.id.buttonOk);
        mDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent!=null){
                    activity.startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS),1);
                }
//                Toast.makeText(activity,"Please Connect Internet" ,Toast.LENGTH_SHORT).show();
               if (b){
                   if (isNetworkAvailable(activity)){
                       dialog.dismiss();
                   }else {
//                    dialog.dismiss();

                       android.os.Process.killProcess(android.os.Process.myPid());
                       System.exit(1);

                   }

               }
            }
        });

//        FrameLayout mDialogOk = dialog.findViewById(R.id.frmOk);
//        mDialogOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });

        dialog.show();
    }
    public void closeDialog(){
        dialog.dismiss();
    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
      return isConnected;
    }

    public String getDate(){
        long currentDate = Calendar.getInstance().getTimeInMillis();
        Date date=new Date(currentDate);
        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy");
        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String new_date=sfd.format(date);


        return new_date;
    }
    public String getTime(){
        long currentDate = Calendar.getInstance().getTimeInMillis();


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mms");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        Date resultdate = new Date(currentDate);
        String new_time = sdf.format(resultdate);
        return new_time;
    }
}
