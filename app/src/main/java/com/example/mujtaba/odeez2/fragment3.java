package com.example.mujtaba.odeez2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.recyclerview.R.attr.layoutManager;
import static com.example.mujtaba.odeez2.ActivityCheckout.txt;
import static com.example.mujtaba.odeez2.MainActivity.TotalPrice;


public class fragment3 extends Fragment {
    Button btp;
    public TextView textView1,textView2,textView3,textView4;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<testitems> cart = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    public String text,email,phone,add,town,body,Delivery_info;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag3,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.frag3recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loader();

        textView1=(TextView)view.findViewById(R.id.tv1);
        textView2=(TextView)view.findViewById(R.id.tv2);
        textView3=(TextView)view.findViewById(R.id.tfprice);
        textView4=(TextView)view.findViewById(R.id.lastfinal);

        textView3.setText(""+TotalPrice);
        textView4.setText(""+(TotalPrice+150));
        displayReceivedData("");

    }
    private void sendMessage(String rmail,String body,String subject) {
        final String email="wekafi.solutions@gmail.com",password="magars+throjans",recipt=rmail,rsubject=subject,rbody=body;
        final ProgressDialog dialog = new ProgressDialog(getActivity().getApplicationContext());
        dialog.setTitle("Confirming Order");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Gmail sender = new Gmail(email, password);
                    sender.sendMail(rsubject, rbody, email, recipt);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();

    }
    public void load(){
        FileInputStream fis=null;
        String fname="noice.txt";
        try {
            fis=getActivity().openFileInput(fname);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br =new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();

            text=br.readLine();
            email=br.readLine();
            phone=br.readLine();
            add=br.readLine();
            town=br.readLine();
            textView2.setText(text);
            textView1.setText(""+add+"\n"+town+"\n"+phone+"\n"+email);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //

    private void loader(){
        database x = new database(getActivity().getBaseContext());
        cart = x.getCarts(getActivity().getBaseContext());
        adapter = new Frag2Adapter(cart,getActivity().getBaseContext());
        recyclerView.setAdapter(adapter);
    }

    public void displayReceivedData(String t) {
        String the[]=txt.split("7",2);
        // Toast.makeText(getActivity(),the[0]+"\n"+the[1],Toast.LENGTH_LONG).show();
        textView2.setText(""+the[0]);
        textView1.setText(""+the[1]);
        Delivery_info=the[0]+"\n"+the[1];
//        btp.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        btp.setEnabled(true);
    }

}