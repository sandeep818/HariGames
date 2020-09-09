package com.games.harigames.Login_Ragister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.games.harigames.Master.Home_master;
import com.games.harigames.R;
import com.games.harigames.admin.Home_admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText username;
    EditText password;
    Button login;
    String user_name;
    String user_pass;
    RelativeLayout prelativeLayout;
    ProgressBar progressBar;
    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prelativeLayout = findViewById(R.id.progress_bg);
        progressBar = findViewById(R.id.login_progress);


        progressBar.setVisibility(View.GONE);
        prelativeLayout.setVisibility(View.GONE);


        Intent intent = new Intent(Login.this, Home_admin.class);
        intent.putExtra("user", "sasas");


        startActivity(intent);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            final String emails = user.getEmail();


            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child("User");

// Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                  for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                      User value = snapshot.getValue(User.class);
                      Log.d("databse", "Value is: " + value.role.toString());
                      if (value.userName.equals(emails.toString())) {
                          if (value.role.contains("Master")){
                              Toast.makeText(getApplicationContext(), "Master Login success", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(Login.this, Home_master.class);
                              intent.putExtra("user", emails);


                              startActivity(intent);

                              return;
                          }
                          if (value.role.contains("Admin")){
                              Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(Login.this, Home_admin.class);
                              intent.putExtra("user", emails);


                              startActivity(intent);

                              return;
                          }

                          Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(Login.this, Home_admin.class);
                          intent.putExtra("user", emails);


                          startActivity(intent);
                          return;
                      }



                      } progressBar.setVisibility(View.GONE);
                      prelativeLayout.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.d("database", "Failed to read value.", error.toException());
                }
            });


        }


        if (restorePrefData()){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
            String userName= preferences.getString("username","user");
            String user_role= preferences.getString("role","NaN");
            Toast.makeText(this, " "+restorePrefData(), Toast.LENGTH_SHORT).show();
           if (user_role.equals("admin")){
//               Intent intent = new Intent(Login.this, Home_admin.class);
//               intent.putExtra("user",userName);
//               startActivity(intent);
//                                //*****************animation when witch activity*********************
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
               finish();
           }else if (user_role.equals("Master")){
//               Intent intent = new Intent(Login.this, Master_home.class);
//               intent.putExtra("user",userName);
//               startActivity(intent);
//                                //*****************animation when witch activity*********************
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
               finish();

           }
        }

        //int api


        login=findViewById(R.id.btnLogin);
       username=findViewById(R.id.loginEmail);
       password =findViewById(R.id.loginPassword);
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    login.performClick();
                }


                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name= username.getText().toString();
                user_pass= password.getText().toString();

                       if (user_name.equals("")) {
                           Toast.makeText(Login.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                           return;
                       }

                if (user_pass.equals("")) {
                    Toast.makeText(Login.this, "Please Fill Password Fields", Toast.LENGTH_SHORT).show();
                    return;
                }



                mAuth.signInWithEmailAndPassword(user_name, user_pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "SignUp Successful !",
                                            Toast.LENGTH_SHORT).show();

                                    FirebaseUser userr = mAuth.getCurrentUser();


                              Intent intent = new Intent(Login.this, Home_admin.class);
                                intent.putExtra("user",userr);

                              startActivity(intent);
                               //*****************animation when witch activity*********************
                             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                              finish();

                                } else {
                                    // If sign in fails, display a message to the user.

//                                    Toast.makeText(Login.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();

                                    Toast.makeText(Login.this, "Authentication failed."+task.getException().toString(),
                                            Toast.LENGTH_SHORT).show();
                                    Log.d("login", "onComplete: "+task.getException().toString());

                                }

                                // ...
                            }
                        });







         //       if (user_name.equals("") || user_pass.equals("")){
          //          Toast.makeText(Login.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
 //               }else{
//                    ParseUser.logInInBackground(user_name,user_pass, new LogInCallback() {
//                        @Override
//                        public void done(ParseUser user, ParseException e) {
//                            if (user!=null){
//                                Intent intent = new Intent(Login.this,Home.class);
////                                intent.putExtra("user",user_name);
//
//                                startActivity(intent);
//                                //*****************animation when witch activity*********************
//                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                                finish();
//                            }else{
//                                Toast.makeText(Login.this, "Wrong Password "+e, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
            //    }
            }
        });


      //  ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("User_Tbl");



        //Create Object and refer to layout view
   
    }



    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        Boolean isIntroOpenedBefore= preferences.getBoolean("isLogin",false);
        return isIntroOpenedBefore;
    }

    private void savePrefsData(String username,String password,String role, String fullname) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        editor.putBoolean("isLogin",true);
        editor.putString("username",username);
        editor.putString("fullname",fullname);
        editor.putString("password",password);
        editor.putString("role",role);
        editor.commit();
    }
}
