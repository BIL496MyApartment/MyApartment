package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class DiscussionPageActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_page);



        Button createTopic = (Button) findViewById(R.id.CreateTopic);//gunceldurumlar buttoni aktiflestirme
        createTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(DiscussionPageActivity.this, CreateTopicActivity.class);
                startActivity(intentLogin);
            }
        });



        Button viewTopic = (Button) findViewById(R.id.viewTopic);//gunceldurumlar buttoni aktiflestirme
        viewTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(DiscussionPageActivity.this, ViewTopicActivity.class);
                startActivity(intentLogin);
            }
        });

    }





}
