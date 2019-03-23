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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {

    private Button btnYes;
    private Button btnNo;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String userID = firebaseUser.getUid();
    static String surveyID = "";
    static String text = "";

    /////////////////////////edit text olustur adini degistir


    DatabaseReference ref;
    static List<Integer> responseList = new ArrayList<>();
    static List<String> voterIDs = new ArrayList<>();

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
        ref = FirebaseDatabase.getInstance().getReference("SurveyID");

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
        final Query query = refSurvey.child("Surveys").orderByChild("surveyID");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Surveys s = issue.getValue(Surveys.class);
                        Surveys surveys = new Surveys();
                        DatabaseReference refSurvey = database.getReference("Surveys");
                        if(surveyID.equals(issue.getKey())) {
                            for (int i = 0; i < s.getResponse().size(); i++) {
                                responseList.add(s.getResponse().get(i));
                            }
                            for (int i = 0; i < s.getVoterID().size(); i++) {
                                voterIDs.add(s.getVoterID().get(i));
                            }
                            boolean isAvailable = true;
                            for (int i= 0; i < voterIDs.size(); i++){
                                if(voterIDs.get(i).equals(userID)){
                                    isAvailable = false;
                                }
                            }
                            if(isAvailable){
                                responseList.add(1);
                                voterIDs.add(userID);
                                surveys.setResponse(responseList);
                                surveys.setVoterID(voterIDs);
                                refSurvey.child(surveyID).child("response").setValue(surveys.getResponse());
                                refSurvey.child(surveyID).child("voterID").setValue(surveys.getVoterID());
                                break;
                            }
                            else {
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void noPressed(){
        DatabaseReference refSurvey = FirebaseDatabase.getInstance().getReference();
        Surveys surveys = new Surveys();
        final Query query = refSurvey.child("Surveys").orderByChild("surveyID");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Surveys s = issue.getValue(Surveys.class);
                        Surveys surveys = new Surveys();
                        DatabaseReference refSurvey = database.getReference("Surveys");
                        if(surveyID.equals(issue.getKey())) {
                            for (int i = 0; i < s.getResponse().size(); i++) {
                                responseList.add(s.getResponse().get(i));
                            }
                            for (int i = 0; i < s.getVoterID().size(); i++) {
                                voterIDs.add(s.getVoterID().get(i));
                            }
                            boolean isAvailable = true;
                            for (int i= 0; i < voterIDs.size(); i++){
                                if(voterIDs.get(i).equals(userID)){
                                    isAvailable = false;
                                }
                            }

                            if(isAvailable){
                                responseList.add(0);
                                voterIDs.add(userID);
                                surveys.setResponse(responseList);
                                surveys.setVoterID(voterIDs);
                                refSurvey.child(surveyID).child("response").setValue(surveys.getResponse());
                                refSurvey.child(surveyID).child("voterID").setValue(surveys.getVoterID());
                                break;
                            }
                            else {
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
