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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private Toolbar actionbarMain;
    private Toolbar mTopToolbar;
    private Button btnMyApartmentChat;
    private static boolean isAdmin = false;
    String userID  = currentUser.getUid();
    public void init(){
        btnMyApartmentChat = (Button) findViewById(R.id.button2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlForAdmin();
        init();
        btnMyApartmentChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMyApartmentChat = new Intent( MainActivity.this,ChatActivity.class);
                startActivity(intentMyApartmentChat);
                //finish();
            }
        });

        actionbarMain = (Toolbar) findViewById(R.id.action_barMain);
        setSupportActionBar(actionbarMain);

        Button mButton = (Button) findViewById(R.id.button3);//gunceldurumlar buttoni aktiflestirme
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isAdmin == true){
                    Intent intentLogin = new Intent(MainActivity.this, SurveyAdminActivity.class);
                    startActivity(intentLogin);
                }
                else {
                    Intent intentLogin = new Intent(MainActivity.this, SurveyActivity.class);
                    startActivity(intentLogin);
                }
            }
        });



        Button discussionButton = (Button) findViewById(R.id.button1);
        discussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, DiscussionPageActivity.class);
                startActivity(intentLogin);
            }
        });

        Button apartmentOperationButton = (Button) findViewById(R.id.button4);
        apartmentOperationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, ApartmentActivity.class);
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

    public boolean controlForAdmin(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child("Apartments").orderByChild("adminID");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Apartment apartment = issue.getValue(Apartment.class);
                        if(apartment.getAdminID().equals(userID)) {
                            isAdmin = true;
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(isAdmin){
            return true;
        }
        return false;
    }

}
