package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class JoinApartmentActivity extends AppCompatActivity {

    private Toolbar actionbarJoin;

    private EditText txtCode;

    private Button btnRegisterApartment;

    private FirebaseUser firebaseUser;

    private String userid;

    private List<String> userList;

    private DatabaseReference databaseReferenceApartments;

    private Apartment updateApartment;

    private boolean sonuc;
    public void init(){

        actionbarJoin =(Toolbar) findViewById(R.id.action_barJoin);
        setSupportActionBar(actionbarJoin);
        getSupportActionBar().setTitle("Join An Apartment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtCode= (EditText) findViewById(R.id.txtCodeId);

        btnRegisterApartment = (Button) findViewById(R.id.btnJoinAnApartment);

        databaseReferenceApartments = FirebaseDatabase.getInstance().getReference("Apartments");

        btnRegisterApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sonuc=false;
                joinApartment();
                if(sonuc==true) {
                    Intent loginIntent = new Intent(JoinApartmentActivity.this, ApartmentActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });



    }

    public void joinApartment(){


        final String code = txtCode.getText().toString();

        if(TextUtils.isEmpty(code)){
            Toast.makeText(this,"Code alanı boş olamaz !",Toast.LENGTH_LONG).show();
        }else {

            databaseReferenceApartments.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    userid = firebaseUser.getUid();
                    int countforcode = 0;
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Apartment apartment = postSnapshot.getValue(Apartment.class);

                        if (apartment.getApartmentID().equals(code)) {
                            countforcode++;
                            userList = apartment.getUserIDList();
                            updateApartment = apartment;
                            if (!userList.isEmpty()) {
                                int count = 0;
                                for (int i = 0; i < userList.size(); i++) {
                                    if (userid.equals(userList.get(i))) {
                                        count++;


                                    }

                                    //System.out.println("İndis:"+i+" Değer:"+userList.get(i));
                                }
                                if (count == 0) {

                                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Apartments").child(updateApartment.getApartmentID());
                                    Apartment newapartment = new Apartment(updateApartment.getApartmentName(),
                                            updateApartment.getCity(),updateApartment.getAdress(),updateApartment.getUserIDList(),updateApartment.getAdminID(),userid, updateApartment.getApartmentID());
                                    dR.setValue(newapartment);


                                    //apartment.getUserIDList().add(userid);
                                }
                            } else {

                                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Apartments").child(updateApartment.getApartmentID());
                                Apartment newapartment = new Apartment(updateApartment.getApartmentName(),
                                        updateApartment.getCity(),updateApartment.getAdress(),userid, updateApartment.getApartmentID());
                                dR.setValue(newapartment);

                                //apartment.setAdminID(userid);
                                //apartment.getUserIDList().add(userid);
                            }
                        }
                    }

                    if (countforcode > 0) {
                        sonuc = true;
                        Toast.makeText(JoinApartmentActivity.this, "Apartmana kayıt işlemi başarılı olmuştur.", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(JoinApartmentActivity.this, "Eşleşen herhangi bir apartman bulunamamıştır. Lütfen tekrar kodunuzu eksiksiz bir biçimde giriniz.", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_apartment);
        init();




    }
}
