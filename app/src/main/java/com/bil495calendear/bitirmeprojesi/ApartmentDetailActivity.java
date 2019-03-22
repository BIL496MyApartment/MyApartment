package com.bil495calendear.bitirmeprojesi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bil495calendear.bitirmeprojesi.MyApartmentsListActivity;
import com.bil495calendear.bitirmeprojesi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.mobilhanem.androidcrudexample.adapter.OrderAdapter;
//import com.mobilhanem.androidcrudexample.model.Order;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDetailActivity extends AppCompatActivity {


    private TextView apartmentName;
    private TextView apartmentAddress;
    private Toolbar actionbarDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_detail);

        actionbarDetail = (Toolbar) findViewById(R.id.action_barDetail);
        setSupportActionBar(actionbarDetail);
        getSupportActionBar().setTitle("Apartment Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();


        apartmentName = (TextView)findViewById(R.id.txtApartmentName);
        apartmentName.setText(intent.getStringExtra(MyApartmentsListActivity.APARTMENT_NAME));

        apartmentAddress = (TextView)findViewById(R.id.txtApartmentAdress);
        apartmentAddress.setText(intent.getStringExtra(MyApartmentsListActivity.APARTMENT_ADDRESS));


    }
}
