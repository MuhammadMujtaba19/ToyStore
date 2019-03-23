package com.example.mujtaba.odeez2;

/**
 * Created by Mujtaba on 3/26/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.mujtaba.odeez2.MainActivity.useraddress;
import static com.example.mujtaba.odeez2.MainActivity.usercity;
import static com.example.mujtaba.odeez2.MainActivity.useremail;
import static com.example.mujtaba.odeez2.MainActivity.username;
import static com.example.mujtaba.odeez2.MainActivity.userphone;

/**
 * Created by users12 on 3/21/2018.
 */

public class fragment2 extends Fragment {
    public static Interface SM;

    protected Button button;
    public static EditText name, email, phone, address, city;
    public TextView textView1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        button = (Button) view.findViewById(R.id.frag2btn);


        name = (EditText) view.findViewById(R.id.f2name);
        email = (EditText) view.findViewById(R.id.f2email);
        phone = (EditText) view.findViewById(R.id.f2phone);
        city = (EditText) view.findViewById(R.id.f2town);
        address = (EditText) view.findViewById(R.id.f2add);
        // textView1=(TextView)view.findViewById(R.id.tv1);

        loadData();

    }

    public interface Interface {
        void sendData(String t);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity)context;
        try {
            SM = (Interface) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    private void loadData() {


                name.setText(username);
                address.setText(useraddress);
                city.setText(usercity);
                phone.setText(userphone);
                email.setText(useremail);
            }



}