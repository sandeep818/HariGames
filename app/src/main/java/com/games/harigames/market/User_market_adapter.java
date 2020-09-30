package com.games.harigames.market;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.games.harigames.R;
import com.games.harigames.User.Bet_optionActivity;
import com.games.harigames.admin.Market_details;

public class User_market_adapter extends FirebaseRecyclerAdapter<User_market,User_market_adapter.marketViewholder>{
Context context ;

    public User_market_adapter(@NonNull FirebaseRecyclerOptions<User_market> options, Context context) {
        super(options);
        this.context = context;
    }

    public User_market_adapter(
            @NonNull FirebaseRecyclerOptions<User_market> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull marketViewholder holder,
                     int position, @NonNull final User_market model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.marketname.setText(model.getMarket_name());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.opentime.setText(model.getOpen_time());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
       holder.closetime.setText(model.getClose_time());

       holder.user_marketPic.setImageResource(R.drawable.market);
       holder.setItemClickListener(new ItemClickListener() {
           @Override
           public void onItemClickListener(View view, int position) {

               Intent intent =new Intent(context, Bet_optionActivity.class);
               intent.putExtra("market",model.getMarket_name());
               intent.putExtra("open",model.open_time);
               intent.putExtra("close",model.close_time);
               context.startActivities(new Intent[]{intent});
           }
       });

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public marketViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_market
                        , parent, false);
        return new User_market_adapter.marketViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class marketViewholder
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView marketname, opentime, closetime;
        ImageView user_marketPic;
        ItemClickListener itemClickListener;
        public marketViewholder(@NonNull View itemView)
        {
            super(itemView);

            this.marketname
                    = itemView.findViewById(R.id.marketTxt1);
            this.opentime = itemView.findViewById(R.id.time_start);
            this.closetime = itemView.findViewById(R.id.time_close);
            this.user_marketPic= itemView.findViewById(R.id.usermarketpic);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v,getLayoutPosition());

        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }
    }
}
