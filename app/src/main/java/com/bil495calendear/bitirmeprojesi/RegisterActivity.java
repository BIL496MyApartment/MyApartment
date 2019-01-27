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

public class RegisterActivity extends AppCompatActivity {

    private Toolbar actionbarRegister;

    private EditText txtUsername, txtEmail, txtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;

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



    }

    private void createNewAccount() {

        String username = txtUsername.getText().toString();
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
