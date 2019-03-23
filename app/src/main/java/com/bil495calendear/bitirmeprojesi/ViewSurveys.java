package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class ViewSurveys extends AppCompatActivity {
    private DatabaseReference databaseReferenceSurveys;
    private FirebaseUser firebaseUser;
    public List<Surveys> surveys;
    LinearLayout linearLayout;

    @Override
    protected void onStart() {
        super.onStart();
        linearLayout = null;
        surveys.clear();
        databaseReferenceSurveys.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                linearLayout = null;
                surveys.clear();
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Surveys survey = postSnapshot.getValue(Surveys.class);
                    surveys.add(survey);
                }
                linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

                for (int i = 0; i < surveys.size(); i++) {
                    TextView textView = new TextView(ViewSurveys.this);
                    textView.setText(surveys.get(i).getSurveyText() + "\n\n");
                    textView.setBackgroundResource(R.drawable.textview_border);
                    final String id = surveys.get(i).getSurveyID();
                    final String txt = surveys.get(i).getSurveyText();
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SurveyActivity.text = txt;
                            SurveyActivity.surveyID = id;
                            Intent intentLogin = new Intent(ViewSurveys.this, SurveyActivity.class);
                            startActivity(intentLogin);
                        }
                    });


                    linearLayout.addView(textView);

                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                linearLayout = null;
                surveys.clear();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_surveys);
        databaseReferenceSurveys = FirebaseDatabase.getInstance().getReference("Surveys");
        surveys = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

}
