package com.mokshithvoodarla.tinovationsecurityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Button signup;
    EditText name;
    EditText password;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        signup = (Button)findViewById(R.id.btn_signup);
        name = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = name.getText().toString();
                String userpass = password.getText().toString();

                user = user.replace('.', '|');
                System.out.println(user);
                Map<String, Object> highermap = new HashMap<String, Object>();
                Map<String, Object> lowermap = new HashMap<String, Object>();

                highermap.put(user, "");
                lowermap.put("password", userpass);
                lowermap.put("Stream", "");

                root.updateChildren(highermap);
                root.child(user).updateChildren(lowermap);

                Map<String, Object> streammap = new HashMap<String, Object>();
                Map<String, Object> streammap2 = new HashMap<String, Object>();

                streammap.put("pm1640", "");
                streammap2.put("event", "Account Created");
                root.child(user).child("Stream").updateChildren(streammap);
                root.child(user).child("Stream").child("pm1640").updateChildren(streammap2);
                
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("Username", user);
                startActivity(i);
            }
        });
    }
    public void launchLoginActivity(View v){
        finish();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
