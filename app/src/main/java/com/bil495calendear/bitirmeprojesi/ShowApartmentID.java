package com.bil495calendear.bitirmeprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowApartmentID extends AppCompatActivity {


    private Toolbar actionbarShow;

    private TextView txtVApartmentID;
    private String ApartmentID;

    public void init(){
        actionbarShow = (Toolbar) findViewById(R.id.action_barShow);
        setSupportActionBar(actionbarShow);
        getSupportActionBar().setTitle("Code");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        ApartmentID = extras.getString("send_string");

        txtVApartmentID = (TextView)findViewById(R.id.txtApartmentID);

        System.out.println("ApartmentID=========="+ApartmentID);
        txtVApartmentID.setText(ApartmentID);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_apartment_id);
        init();


    }




}
