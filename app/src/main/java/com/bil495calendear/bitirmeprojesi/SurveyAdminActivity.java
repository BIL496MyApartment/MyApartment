package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SurveyAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_admin);

        Button viewSurveys = (Button) findViewById(R.id.btnViewSurveys);//gunceldurumlar buttoni aktiflestirme
        viewSurveys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(SurveyAdminActivity.this, SurveyActivity.class);
                startActivity(intentLogin);

            }
        });
        /*Button deleteSurvey = (Button) findViewById(R.id.btnDeleteSurvey);//gunceldurumlar buttoni aktiflestirme
        viewSurveys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(SurveyAdminActivity.this, SurveyActivity.class);
                startActivity(intentLogin);

            }
        });*/
        Button createSurvey = (Button) findViewById(R.id.btnCreateSurvey);//gunceldurumlar buttoni aktiflestirme
        createSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(SurveyAdminActivity.this, CreateSurveyActivity.class);
                startActivity(intentLogin);

            }
        });

    }




}
