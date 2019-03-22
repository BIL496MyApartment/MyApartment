package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {

    private Button btnYes;
    private Button btnNo;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference refSurvey = database.getReference("Surveys");
    Surveys surveys = new Surveys();;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String userID = firebaseUser.getUid();
    String surveyID;

    public SurveyActivity(){

    }

    public SurveyActivity(String surveyID) {
        this.surveyID = surveyID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        btnYes = (Button)findViewById(R.id.YesButton);
        btnNo = (Button)findViewById(R.id.NoButton);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesPressed();
                Intent loginIntent = new Intent(SurveyActivity.this,MainActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noPressed();
                Intent loginIntent = new Intent(SurveyActivity.this,MainActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

    private void yesPressed(){
        DatabaseReference refSurvey = FirebaseDatabase.getInstance().getReference();
        Surveys surveys = new Surveys();
        Query query = refSurvey.child("Surveys").orderByChild("surveyID");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //surveys.setResponse(1);
        //surveys.setVoterID(userID);
        surveys.setSurveyText("mantolama yapilsin mi");
        //  surveys.setSurveyID(generateSurveyID());
        refSurvey.child("Surveys").push().setValue(surveys);
    }

    private void noPressed(){
        DatabaseReference refSurvey = FirebaseDatabase.getInstance().getReference();
        Query query = refSurvey.child("Surveys").orderByChild("surveyID");
        /*query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        String key = refSurvey.push().getKey();
        DatabaseReference refSurveyKeyli = database.getReference("Surveys/"+key);
        Surveys surveys = new Surveys();
        List<Integer> l1 = new ArrayList<>();
        l1.add(0);
        List<String> l2 = new ArrayList<>();
        l2.add(userID);
        surveys.setResponse(l1);
        surveys.setVoterID(l2);
        surveys.setSurveyText("mantolama yapilsin mi");
        surveys.setSurveyID(key);
        refSurveyKeyli.setValue(surveys);
    }

    /*private int generateSurveyID(){
        Random r = new Random();
        int x = r.nextInt(1000000);
        //control id
        return x;
    }*/
}
