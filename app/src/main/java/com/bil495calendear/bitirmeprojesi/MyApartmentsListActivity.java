package com.bil495calendear.bitirmeprojesi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;
import java.util.List;

public class MyApartmentsListActivity extends AppCompatActivity implements ItemClickListener {


    private DatabaseReference databaseReferenceApartments;
    private List<Apartment> apartments;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyApartmentsAdapter myApartmentsAdapter;

    private FirebaseUser firebaseUser;
    private String userid;

    private List<String>userList;

    public static final String APARTMENT_NAME = "apartmentname";
    public static final String APARTMENT_ADDRESS = "Address";


    private Toolbar actionbarMyApartments;

    public void init(){

        actionbarMyApartments =(Toolbar) findViewById(R.id.action_barApartment);
        setSupportActionBar(actionbarMyApartments);
        getSupportActionBar().setTitle("My Apartments List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
        protected void onStart() {
        super.onStart();
        databaseReferenceApartments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                apartments.clear();
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                userid =firebaseUser.getUid();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Apartment apartment = postSnapshot.getValue(Apartment.class);

                    userList=apartment.getUserIDList();

                    if(!userList.isEmpty()) {

                        for(int i = 0; i<userList.size(); i++)
                        {
                            if(userid.equals(userList.get(i))){
                                apartments.add(apartment);
                                break;
                            }
                            //System.out.println("İndis:"+i+" Değer:"+userList.get(i));
                        }

                    }
                }


                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(MyApartmentsListActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                myApartmentsAdapter = new MyApartmentsAdapter(MyApartmentsListActivity.this, apartments);
                myApartmentsAdapter.setClickListener(MyApartmentsListActivity.this);
                mRecyclerView.setAdapter(myApartmentsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apartments_list);
        init();

        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview);

        databaseReferenceApartments = FirebaseDatabase.getInstance().getReference("Apartments");

        apartments = new ArrayList<>();




    }




    public void onClick(View view, int position) {
        Apartment apartment = apartments.get(position);

        Intent intent = new Intent(getApplicationContext(), ApartmentDetailActivity.class);
        intent.putExtra(APARTMENT_ADDRESS, apartment.getAdress());
        intent.putExtra(APARTMENT_NAME, apartment.getApartmentName());
        startActivity(intent);
    }



}


