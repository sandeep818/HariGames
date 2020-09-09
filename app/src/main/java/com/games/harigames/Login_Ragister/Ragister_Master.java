package com.games.harigames.Login_Ragister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.games.harigames.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Ragister_Master extends AppCompatActivity {
        DatabaseReference databaseReference;
        FirebaseDatabase firebaseDatabase;
        FirebaseAuth firebaseAuth;
    Button ragister;
    EditText name,username,password,email,mobile,credit_ref,exposer_limit,upline,downline;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister__master);
        ragister = findViewById(R.id.ragister);
        name = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        mobile = findViewById(R.id.mobile);
        role = "Master";
        credit_ref = findViewById(R.id.credit_ref);
        exposer_limit = findViewById(R.id.exposer_limit);
        upline = findViewById(R.id.upline);
        upline.setText("100");
      upline.setEnabled(false);

        downline = findViewById(R.id.downline);
        downline.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        final String userName= preferences.getString("username","user");

        //int api
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();


        ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String m_Name=name.getText().toString();
                final String m_Username=username.getText().toString();
                final String m_Password=password.getText().toString();

                final String m_Mobile=mobile.getText().toString();
                final String m_Role=role;
                final String m_CreditRef=credit_ref.getText().toString();
                final String m_ExposerLimit=exposer_limit.getText().toString();
                final String m_Upline=upline.getText().toString();
                final String m_Downline=downline.getText().toString();
                final String m_created_by=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                final String m_status="Active";



                if(TextUtils.isEmpty(m_Name)){
                    Toast.makeText(Ragister_Master.this, " Please enter Name" , Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(m_Username)){
                    Toast.makeText(Ragister_Master.this, " Please enter Username" , Toast.LENGTH_SHORT).show();
                }
                              if(TextUtils.isEmpty(m_Password)){
                    Toast.makeText(Ragister_Master.this, " Please enter Password" , Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(m_Mobile)){
                    Toast.makeText(Ragister_Master.this, " Please enter Mobile" , Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(m_CreditRef)){
                    Toast.makeText(Ragister_Master.this, " Please enter Credit Reffrence" , Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(m_ExposerLimit)){
                    Toast.makeText(Ragister_Master.this, " Please enter Exposer Limit" , Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(m_Upline)){
                    Toast.makeText(Ragister_Master.this, " Please enter Upline" , Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(m_Downline)){
                    Toast.makeText(Ragister_Master.this, " Please enter Downline" , Toast.LENGTH_SHORT).show();
                }




                firebaseAuth.createUserWithEmailAndPassword(m_Username, m_Password)
                        .addOnCompleteListener(Ragister_Master.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Ragister_Master_Model master_model=new Ragister_Master_Model(m_status,m_created_by,m_Name,m_Username,m_Password,m_Mobile,m_Role,m_CreditRef,m_ExposerLimit,m_Upline,m_Downline);
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(master_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(Ragister_Master.this, "  Successful sign in !", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                } else {
                                    Toast.makeText(Ragister_Master.this, "  "+task, Toast.LENGTH_SHORT).show();
                                    Log.d("oks", "onComplete: "+task.getException());

                                }

                                // ...
                            }
                        });



            }
        });
    }
}
