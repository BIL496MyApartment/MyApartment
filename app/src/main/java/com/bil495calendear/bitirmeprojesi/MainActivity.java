package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser currentUser;
    private Toolbar mTopToolbar;
    private Button btnMyApartmentChat;

    public void init(){
        btnMyApartmentChat = (Button) findViewById(R.id.button2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        btnMyApartmentChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMyApartmentChat = new Intent( MainActivity.this,ChatActivity.class);
                startActivity(intentMyApartmentChat);
                //finish();
            }
        });

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);

        Button mButton = (Button) findViewById(R.id.button3);//gunceldurumlar buttoni aktiflestirme
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, SurveyActivity.class);
                startActivity(intentLogin);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.mainLogout){

            LoginActivity.auth.signOut();
                //logAct.multipleInit();
            Intent loginIntent = new Intent (MainActivity.this , LoginActivity.class);
            startActivity(loginIntent);
            finish();

        }

        return true;
    }
}
