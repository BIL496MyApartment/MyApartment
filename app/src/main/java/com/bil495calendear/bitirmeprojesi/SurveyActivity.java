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
    Surveys surveys = new Surveys();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String userID = firebaseUser.getUid();
    static String surveyID = "";
    DatabaseReference ref;

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
        final List<Integer> responseList = new ArrayList<>();
        final List<String> voterIDs = new ArrayList<>();
        final String[] text = new String[1];

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Surveys s = dataSnapshot.getValue(Surveys.class);
                        System.out.println("asdaS:DASDa " + s.getSurveyID());
                        if(surveyID.equals(s.getSurveyID())) {
                            for (int i = 0; i < s.getResponse().size(); i++) {
                                responseList.add(s.getResponse().get(i));
                            }
                            for (int i = 0; i < s.getVoterID().size(); i++) {
                                voterIDs.add(s.getVoterID().get(i));
                            }
                            text[0] =s.getSurveyText();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        responseList.add(1);
        boolean isAvailable = true;
        for (int i= 0; i < voterIDs.size(); i++){
            if(voterIDs.get(i).equals(userID)){
                isAvailable = false;
            }
        }

        if(isAvailable){
            voterIDs.add(userID);
        }
        else{
            return;
        }

        surveys.setSurveyText(text[0]);
        surveys.setSurveyID(surveyID);
        refSurvey.child("Surveys").push().setValue(surveys);
    }

    private void noPressed(){
        DatabaseReference refSurvey = FirebaseDatabase.getInstance().getReference();
        Surveys surveys = new Surveys();
        final Query query = refSurvey.child("Surveys").orderByChild("surveyID");
        final List<Integer> responseList = new ArrayList<>();
        final List<String> voterIDs = new ArrayList<>();
        final String[] text = new String[1];

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Surveys s = dataSnapshot.getValue(Surveys.class);
                        System.out.println("AS:DSA:D:AS:DAS:D:ASD:AS:1 " + surveyID);
                        System.out.println("AS:DSA:D:AS:DAS:D:ASD:AS:2 " + dataSnapshot.getKey());
                        System.out.println("AS:DSA:D:AS:DAS:D:ASD:AS:3 " + surveyID.equals(s.getSurveyID()));
                        if(surveyID.equals(dataSnapshot.getKey())) {
                            for (int i = 0; i < s.getResponse().size(); i++) {
                                responseList.add(s.getResponse().get(i));
                            }
                            for (int i = 0; i < s.getVoterID().size(); i++) {
                                voterIDs.add(s.getVoterID().get(i));
                            }
                            text[0] =s.getSurveyText();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        responseList.add(0);
        boolean isAvailable = true;
        for (int i= 0; i < voterIDs.size(); i++){
            if(voterIDs.get(i).equals(userID)){
                isAvailable = false;
            }
        }

        if(isAvailable){
            voterIDs.add(userID);
        }
        else{
            return;
        }

        surveys.setSurveyText(text[0]);
        surveys.setSurveyID(surveyID);
        refSurvey.child("Surveys").child(surveyID).setValue(surveys);
    }

}
