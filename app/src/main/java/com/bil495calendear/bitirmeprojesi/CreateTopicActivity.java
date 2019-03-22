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




public class CreateTopicActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference refSurvey = database.getReference("Surveys");
    Surveys surveys = new Surveys();;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String userID = firebaseUser.getUid();
    String surveyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_topic);
        button = (Button)findViewById(R.id.button6);
        editText = (EditText)findViewById(R.id.txvTopic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
                Intent loginIntent = new Intent(CreateTopicActivity.this,MainActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });



    }





    private void create(){
        DatabaseReference refTopic = FirebaseDatabase.getInstance().getReference();

        String key = refTopic.push().getKey();
        DatabaseReference refTopicKey = database.getReference("Topics/"+key);
        Topic topic = new Topic();

        topic.setTopicText(editText.getText().toString());
        topic.setTopicID(key);
        topic.setUserID(userID);
        refTopicKey.setValue(topic);
    }




}
