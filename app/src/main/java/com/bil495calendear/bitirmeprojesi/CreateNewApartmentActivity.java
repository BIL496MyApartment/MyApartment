package com.bil495calendear.bitirmeprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNewApartmentActivity extends AppCompatActivity {

    private Toolbar actionbarApartment;

    private EditText txtApartmentname,txtCityname,txtAdress;

    private Button btnCreateApartment;

    FirebaseDatabase db;

    private FirebaseAuth auth;

    public String uid;

    FirebaseUser firebaseUser;

    public void init() {

        auth = FirebaseAuth.getInstance();

        db=FirebaseDatabase.getInstance();

        actionbarApartment = (Toolbar) findViewById(R.id.action_barApartment);
        setSupportActionBar(actionbarApartment);
        getSupportActionBar().setTitle("Create New Apartment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtApartmentname = (EditText) findViewById(R.id.txtApartmentRegisterName);

        txtCityname = (EditText) findViewById(R.id.txtCityNameRegister);

        txtAdress = (EditText) findViewById(R.id.txtAdressRegister);

        btnCreateApartment = (Button) findViewById(R.id.btnCreateApartment);



        btnCreateApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createNewApartment();
                Toast.makeText(CreateNewApartmentActivity.this, "Apartman oluşturma işlemi başarılı olmuştur.", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(CreateNewApartmentActivity.this,ApartmentActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });

    }



    private void createNewApartment(){

        String apartmentname = txtApartmentname.getText().toString();
        String cityname = txtCityname.getText().toString();
        String adress = txtAdress.getText().toString();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();

        Toast.makeText(this, "" + firebaseUser.getUid(), Toast.LENGTH_SHORT).show();


        uid = userid;


        if(TextUtils.isEmpty(apartmentname)){
            Toast.makeText(this,"Apartman alanı boş olamaz !",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(cityname)){
            Toast.makeText(this,"Şehir Alanı Boş olamaz !",Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(adress)){
            Toast.makeText(this,"Adress Alanı Boş olamaz !",Toast.LENGTH_LONG).show();
        }else {

            registerApartment(apartmentname,cityname,adress,uid);

        }


    }

    private void registerApartment(String apartmentname,String cityname,String adress,String uid){



        DatabaseReference dbRef = db.getReference("Apartments");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKeyli = db.getReference("Apartments/"+key);

        dbRefKeyli.setValue(new Apartment(apartmentname,cityname,adress,uid,key));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_apartment);
        init();
    }
}
