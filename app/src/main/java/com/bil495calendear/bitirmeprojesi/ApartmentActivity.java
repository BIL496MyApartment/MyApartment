package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class ApartmentActivity extends AppCompatActivity {

    private Toolbar actionbarApartment;

    private EditText txtApartmentname;
    private Button btnRegisterApartment;
    private Button btnJoinApartment;
    private Button btnMyApartments;

    FirebaseDatabase db;

    public void init() {

        db=FirebaseDatabase.getInstance();



        actionbarApartment = (Toolbar) findViewById(R.id.action_barApartment);
        setSupportActionBar(actionbarApartment);
        getSupportActionBar().setTitle("Manage Apartments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegisterApartment = (Button) findViewById(R.id.btnRegisterApartment);
        btnJoinApartment = (Button) findViewById(R.id.btnJoinApartment);
        btnMyApartments = (Button) findViewById(R.id.btnMyApartments);

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);
        init();

        btnRegisterApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(ApartmentActivity.this, CreateNewApartmentActivity.class);
                startActivity(intentRegister);
                //finish();
            }
        });

        btnJoinApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentJoin = new Intent(ApartmentActivity.this, JoinApartmentActivity.class);
                startActivity(intentJoin);
                //finish();
            }
        });

        btnMyApartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMyApartments = new Intent(ApartmentActivity.this,MyApartmentsListActivity.class);
                startActivity(intentMyApartments);
                //finish();
            }
        });

    }
}
