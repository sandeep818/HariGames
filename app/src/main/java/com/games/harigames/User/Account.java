package com.games.harigames.User;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.games.harigames.Login_Ragister.Validation;
import com.games.harigames.Place_bet.Plece_bet;
import com.games.harigames.R;
import com.games.harigames.getAllUser.UserAllDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {
   public ArrayList<Plece_bet> downloadData = new ArrayList<Plece_bet>();
    TextView placed_bet_amount,placed_bet_num,user_Name,total_betplaced,total_betamount,balance,cr_balance;


    public Account() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.user_home, container, false);

        placed_bet_amount = view.findViewById(R.id.placed_bet_amount);
        placed_bet_num = view.findViewById(R.id.placed_bet_num);
        user_Name = view.findViewById(R.id.user_Name);
        total_betplaced = view.findViewById(R.id.total_betplaced);
        total_betamount = view.findViewById(R.id.total_betamount);
        balance = view.findViewById(R.id.balance);
        cr_balance = view.findViewById(R.id.cr_balance);


        SharedPreferences preferences = getContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        String username= preferences.getString("username","user");
        user_Name.setText(username);

        final Validation validation =new Validation();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Place_bet");
        ref.orderByChild("username").equalTo(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int amount=0;
                int lifetime_bet=0;
                int lifetime_bet_ammount=0;
                int bets=0;
                ArrayList<Plece_bet> userAllDataModels = new ArrayList<Plece_bet>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {

                    Plece_bet allDataModel = userSnapshot.getValue(Plece_bet.class);
                    String s = validation.getDate();

                    if (allDataModel.getDate().contains(s)){
                        amount=amount+Integer.parseInt(allDataModel.getAmount().toString());
                        bets=bets+1;
                    }
                    userAllDataModels.add(allDataModel);
                    downloadData.add(allDataModel);
                    lifetime_bet_ammount=lifetime_bet_ammount+Integer.parseInt(allDataModel.getAmount().toString());



                }
                lifetime_bet=userAllDataModels.size();
                placed_bet_num.setText(String.valueOf(bets));
                placed_bet_amount.setText("₹ "+String.valueOf(amount));
                total_betplaced.setText(""+ lifetime_bet);
                total_betamount.setText("₹ "+lifetime_bet_ammount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("User");
        ref1.orderByChild("userName").equalTo(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int balance1=0;
                int balance_cr=0;
                ArrayList<UserAllDataModel> userAllDataModels = new ArrayList<UserAllDataModel>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {

                    UserAllDataModel allDataModel = userSnapshot.getValue(UserAllDataModel.class);
                    userAllDataModels.add(allDataModel);
//                    downloadData.add(allDataModel);
                    balance1=Integer.parseInt(allDataModel.getBalance());
                    balance_cr=Integer.parseInt(allDataModel.getCredit_ref());


                }
                balance.setText("₹ "+balance1);
                cr_balance.setText("₹ "+balance_cr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

















        return view;




    }

}
