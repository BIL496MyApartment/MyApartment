package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
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




    public class ViewTopicActivity extends AppCompatActivity {
        private DatabaseReference databaseReferenceTopics;
        private FirebaseUser firebaseUser;
        public List<Topic> topics;
        LinearLayout linearLayout;




        @Override
        protected void onStart() {
            super.onStart();
            databaseReferenceTopics.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    linearLayout = null;
                    topics.clear();
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Topic topic = postSnapshot.getValue(Topic.class);
                        topics.add(topic);
                    }
                    linearLayout = (LinearLayout) findViewById(R.id.topiclayout);

                    for (int i = 0; i < topics.size(); i++) {
                        TextView textView = new TextView(ViewTopicActivity.this);
                        textView.setText(topics.get(i).getTopicText() + "\n\n");
                        textView.setBackgroundResource(R.drawable.textview_border);
                        linearLayout.addView(textView);
                    }





                }

                @Override
                public void onCancelled(DatabaseError databaseError) {



                }
            });


        }




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_topic);
            databaseReferenceTopics = FirebaseDatabase.getInstance().getReference("Topics");
            topics = new ArrayList<>();
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        }





    }


