package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateSurveyActivity extends AppCompatActivity {
    private Button button;
    private EditText edit;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference refSurvey = database.getReference("Surveys");
    Surveys surveys = new Surveys();;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String userID = firebaseUser.getUid();
    String surveyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);
        button = (Button)findViewById(R.id.CreateSurveyButton);
        edit = (EditText)findViewById(R.id.SurveyText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
                Intent loginIntent = new Intent(CreateSurveyActivity.this,MainActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });

    }
    private void create(){
        DatabaseReference refSurvey = FirebaseDatabase.getInstance().getReference();
        String key = refSurvey.push().getKey();
        DatabaseReference refSurveyKeyli = database.getReference("Surveys/"+key);
        Surveys surveys = new Surveys();
        List<Integer> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add(2);
        l2.add("0");
        surveys.setResponse(l1);
        surveys.setVoterID(l2);
        surveys.setSurveyText(edit.getText().toString());
        surveys.setSurveyID(key);
        refSurveyKeyli.setValue(surveys);
    }
}
