package com.games.harigames.Place_bet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.games.harigames.R;
import com.games.harigames.getAllUser.FilterHelper;
import com.games.harigames.getAllUser.UserAllDataModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Bet_adapter extends BaseAdapter implements View.OnClickListener {
    public ArrayList<Plece_bet> displayData;
    Context mcontext;
    public ArrayList<Plece_bet> currentUserData;
    FilterHelper filterHelper;

    public Bet_adapter(ArrayList<Plece_bet> displayData, Context mcontext) {

        this.displayData = displayData;
        this.mcontext = mcontext;
        this.currentUserData=displayData;
    }

    @Override
    public int getCount() {
        return displayData.size();
    }

    @Override
    public Object getItem(int position) {
        return displayData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.bet_history,parent,false);
        }
        Animation animation = AnimationUtils.loadAnimation(mcontext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        convertView.startAnimation(animation);
        lastPosition = position;
        TextView bet_num = (TextView) convertView.findViewById(R.id.bet_num);
        TextView bet_market_name = (TextView) convertView.findViewById(R.id.bet_market);
        TextView bet_game_type = (TextView) convertView.findViewById(R.id.bet_game_type);
        TextView bet_game_cat = (TextView) convertView.findViewById(R.id.bet_game_cat);
        TextView Place_bet_time = (TextView) convertView.findViewById(R.id.Place_bet_time);
        TextView bet_amount = (TextView) convertView.findViewById(R.id.bet_amount);
        TextView bet_status = (TextView) convertView.findViewById(R.id.bet_status);

        ImageView usertype = (ImageView) convertView.findViewById(R.id.user_type);

        final Plece_bet dataModel = (Plece_bet) this.getItem(position);
        String s = String.valueOf(dataModel.getBet_time());
        long g=Long.parseLong(s);


        Date date=new Date(g);
        SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy hh:mma");

        sfd.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String time= sfd.format(date);

        bet_num.setText(dataModel.getBet_number());
        bet_market_name.setText(dataModel.getMarket_name());
        bet_game_type.setText(dataModel.getBet_type() +"   [ "+ dataModel.getMarket_open_time()+" - "+dataModel.getMarket_close_time() +" ]");
        bet_game_cat.setText(dataModel.getMarket_type());
        Place_bet_time.setText(time);
        bet_amount.setText(dataModel.getAmount());







        return convertView;
    }



    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ////////////////////////////////////////////////////////////////////

        UserAllDataModel dataModel=(UserAllDataModel) object;

        switch (v.getId())
        {
            case R.id.user_type:
                Snackbar.make(v, "oky.... " +dataModel.getName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }


    }

    public void setUserList(ArrayList<Plece_bet> filteredUserList ){
//        Log.d("serach", "performFiltering: "+filteredUserList.get(0).getName() );
        this.displayData=filteredUserList;

    }


//    @Override
//    public Filter getFilter() {
//        if (filterHelper==null){
//            filterHelper = new FilterHelper(this.currentUserData,this,mcontext);
//        }
//        return filterHelper;
//    }

    public void refresh(){
        notifyDataSetChanged();
    }
}
