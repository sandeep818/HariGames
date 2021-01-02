package com.games.harigames.Place_bet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.games.harigames.R;

import java.util.List;

public class MainGAdapter extends BaseAdapter {
    Context context;
    List<String> betnum;
    LayoutInflater inflater;
    TextView text_num;

    public MainGAdapter(Context context, List betnum) {
        this.context = context;
        this.betnum = betnum;
    }

    @Override
    public int getCount() {
        return betnum.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater==null){
            inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView ==null){
            convertView = inflater.inflate(R.layout.jodi_gridnumber,null);

        }
         text_num = (TextView) convertView.findViewById(R.id.num_Text);
        text_num.setText(betnum.get(position));
        text_num.setTag(betnum.get(position));
        return convertView;
    }
}
