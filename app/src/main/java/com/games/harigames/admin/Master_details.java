package com.games.harigames.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.games.harigames.Login_Ragister.Ragister_Master_Model;
import com.games.harigames.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Master_details extends AppCompatActivity {
   TextView master_name,master_status,master_email, master_mobile,master_user;
    String email;
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_details);
        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String status = intent.getStringExtra("status");
        email = intent.getStringExtra("email");
        String mobile = intent.getStringExtra("mobile");


        master_name=findViewById(R.id.master_name);
        master_status=findViewById(R.id.master_status);
        master_email=findViewById(R.id.master_email);
        master_mobile=findViewById(R.id.master_mobile);
        master_user=findViewById(R.id.master_users);

        master_name.setText(name);
        master_status.setText(status);
        master_email.setText("email : "+email);
        master_mobile.setText("Mobile : "+mobile);
        usersNum();
        master_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num!=0){
                    Intent intent1= new Intent(Master_details.this,Master_usersDetail.class);
                    intent1.putExtra("email",email);
                    startActivity(intent1);
                }else{
                    Toast.makeText(Master_details.this, " No Users ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void usersNum(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Ragister_Master_Model> marketDataModels = new ArrayList<Ragister_Master_Model>();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Ragister_Master_Model allDataModel = userSnapshot.getValue(Ragister_Master_Model.class);
                    if (allDataModel.created_by.contains(email)){
                        Toast.makeText(Master_details.this, "found", Toast.LENGTH_SHORT).show();

                        num = num+1;

                    }
                  //  marketDataModels.add(allDataModel);
                   master_user.setText(""+num);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

    }



}