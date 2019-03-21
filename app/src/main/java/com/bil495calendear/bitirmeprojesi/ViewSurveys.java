package com.bil495calendear.bitirmeprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewSurveys extends AppCompatActivity {
    private DatabaseReference databaseReferenceApartments;

    private FirebaseUser firebaseUser;

    private List<Surveys> surveys;
    private RecyclerView myRecyclerView;

    @Override
    protected void onStart() {
        super.onStart();
        databaseReferenceApartments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                surveys.clear();
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Surveys survey = postSnapshot.getValue(Surveys.class);
                    surveys.add(survey);
                }


              /*  myRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(MyApartmentsListActivity.this);
                myRecyclerView.setLayoutManager(mLayoutManager);

                myApartmentsAdapter = new MyApartmentsAdapter(MyApartmentsListActivity.this, apartments);
                //myApartmentsAdapter.setClickListener(MyApartmentsListActivity.this);
                mRecyclerView.setAdapter(myApartmentsAdapter);*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_surveys);

        myRecyclerView = (RecyclerView) findViewById(R.id.viewSurveys);

        databaseReferenceApartments = FirebaseDatabase.getInstance().getReference("Surveys");

        surveys = new ArrayList<>();
    }

}
