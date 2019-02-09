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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Toolbar actionbarLogin;
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    public static FirebaseAuth auth;
    public static FirebaseUser currentUser;
    TextView forgot_password;

    public void init(){

        actionbarLogin =  (Toolbar)findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giris Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        currentUser=auth.getCurrentUser();// current user ilerde lazim olabilir

        txtEmail = (EditText)findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText)findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
    }

    public void multipleInit(){
        auth= FirebaseAuth.getInstance();

        currentUser = auth.getCurrentUser();// current user ilerde lazim olabilir

        txtEmail = (EditText)findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText)findViewById(R.id.txtPasswordLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });


    }

    private void loginUser() {

        final String email =  txtEmail.getText().toString();
        String password = txtPassword.getText().toString();


        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"Lutfen E-mail alanini doldurun",Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Lutfen bir sifre giriniz",Toast.LENGTH_LONG).show();
        }else{

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        Toast.makeText(LoginActivity.this,"Giris basarili olmustur. Hosgeldiniz " + email,Toast.LENGTH_LONG).show();

                        Intent mainIntent= new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(mainIntent);
                        //finish();

                    }else{
                        Toast.makeText(LoginActivity.this,"Giris basarisiz. Lutfen e-mail ve sifrenizi dogru girdiginizden ve internete bagli oldugunuzdan emin olunuz!",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}
