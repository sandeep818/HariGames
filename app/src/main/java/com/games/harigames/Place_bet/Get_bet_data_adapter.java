package com.games.harigames.Place_bet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.games.harigames.R;

import java.util.ArrayList;

public class Get_bet_data_adapter extends RecyclerView.Adapter<Get_bet_data_adapter.ViewHolder> {

    private ArrayList<Get_bet_data_model> itemList;
    Context context;

    public Get_bet_data_adapter( Context context,ArrayList<Get_bet_data_model> itemList) {
        this.itemList = itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.result_bet_num.setText(itemList.get(position).getBet_number());
        holder.bet_num_amount.setText(itemList.get(position).getTotel_bet_amount());
        holder.bet_num_count.setText(itemList.get(position).getBet_count());


    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView result_bet_num,bet_num_count,bet_num_amount,bet_num_profit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            result_bet_num =(TextView) itemView.findViewById(R.id.result_bet_num);
            bet_num_count =(TextView) itemView.findViewById(R.id.bet_num_count);
            bet_num_amount =(TextView) itemView.findViewById(R.id.bet_num_amount);
            bet_num_profit =(TextView) itemView.findViewById(R.id.bet_num_profit);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
