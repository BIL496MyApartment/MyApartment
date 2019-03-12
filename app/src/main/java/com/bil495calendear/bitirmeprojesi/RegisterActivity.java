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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar actionbarRegister;

    private EditText txtUsername, txtEmail, txtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;
    private DatabaseReference refUser;
    private FirebaseDatabase database;
    private Users users;

    DatabaseReference reference;

    public void init(){
        actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Olustur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth= FirebaseAuth.getInstance();
        txtUsername = (EditText) findViewById(R.id.txtUsernameRegister);
        txtEmail = (EditText) findViewById(R.id.txtEmailRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        btnRegister = (Button)findViewById(R.id.btnHesapOlustur);
        database = FirebaseDatabase.getInstance();
        refUser = database.getReference("Users");
        users = new Users();
    }

    private void createNewAccount() {

        final String username = txtUsername.getText().toString();
        String email= txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"Lutfen E-mail alanini doldurun.",Toast.LENGTH_LONG).show();

        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Lutfen bir sifre giriniz.",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Lutfen bir kullanici adi giriniz.",Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        assert firebaseUser != null;
                        String userid = firebaseUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("id",userid);
                        hashMap.put("username",username);
                        hashMap.put("imageURL","default");

                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "My Apartment'a hosgeldiniz! Hesabiniz basarili bir sekilde olusturuldu! Lutfen giris yapiniz", Toast.LENGTH_SHORT).show();
                                    Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(loginIntent);
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this, "Beklenmeyen bir hata olustu! Lutfen daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });
    }
}
