package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = (Button) findViewById(R.id.button3);//gunceldurumlar buttoni aktiflestirme
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        Button discussionButton = (Button) findViewById(R.id.button1);
        discussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, DiscussionActivity.class);
                startActivity(intentLogin);
            }
        });
        
    }

    @Override
    protected void onStart() {

        if(currentUser==null) { // kullanici aktif olmadiginda (giris yapmadiginda welcome activity e gitsin)S

            //Intent welcomeIntent = new Intent(MainActivity.this, WelcomeActivity.class);
            //startActivity(welcomeIntent);
            //finish();
        }


        super.onStart();
    }
}
