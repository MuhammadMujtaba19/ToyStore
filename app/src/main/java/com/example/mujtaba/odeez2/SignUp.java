package com.example.mujtaba.odeez2;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button register ;TextView signin;
    private FirebaseAuth obj;
    private DatabaseReference mDatabase;
    String id, pass,name, age, address, city , country , phone;

    EditText username, useremail, userpassword, userage, userphone,usercountry, usercity,useraddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        obj=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        username = (EditText)findViewById(R.id.username);
        userpassword = (EditText)findViewById(R.id.userpassword) ;
        register = (Button)findViewById(R.id.register);
        userage = (EditText)findViewById(R.id.userage) ;
        userphone = (EditText)findViewById(R.id.userphone) ;
        useraddress = (EditText)findViewById(R.id.useraddress);
        useremail = (EditText)findViewById(R.id.useremail);
        usercity = (EditText)findViewById(R.id.usercity);
        usercountry = (EditText)findViewById(R.id.usercountry);
        signin = (TextView)findViewById(R.id.signIn1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = useremail.getText().toString();
                pass = userpassword.getText().toString();
                name = username.getText().toString();
                age = userage.getText().toString();
                address = useraddress.getText().toString();
                city = usercity.getText().toString();
                country = usercountry.getText().toString();
                phone = userphone.getText().toString();
                signUp (id, pass);
                startActivity(new Intent(SignUp.this, MainActivity.class));
                finish();

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void ConfirmDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.confirm_phonenumber_dialog);
//        dialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -2;
        layoutParams.height = -2;
        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

    }
    public void signUp(String id, String pass){
        obj.createUserWithEmailAndPassword(id,pass);
        updateDatabase();

    }
    public void updateDatabase(){
        zcustomer a = new zcustomer(obj.getCurrentUser().getUid(), name, id, age, address, city, country, phone,1);
        mDatabase.child("customer").child(obj.getCurrentUser().getUid()).setValue(a);
    }

}
