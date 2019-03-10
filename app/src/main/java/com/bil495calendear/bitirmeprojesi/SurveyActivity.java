package com.bil495calendear.bitirmeprojesi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.annotation.NonNull;
public class SurveyActivity extends AppCompatActivity {

    private Button btnYes,btnNo;
    private FirebaseDatabase database;
    private DatabaseReference refSurvey;
    private DatabaseReference refUser;
    Surveys surveys;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        btnYes = (Button)findViewById(R.id.YesButton);
        btnNo = (Button)findViewById(R.id.NoButton);
        database = FirebaseDatabase.getInstance();
        refSurvey = database.getReference("Surveys");
        refUser = database.getReference("Users");
        surveys = new Surveys();
        users = new Users();
    }

    public void YesButton(View view){
        refSurvey.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                refSurvey.child("kmTv36W0CZLqIJkhomah").setValue(surveys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
