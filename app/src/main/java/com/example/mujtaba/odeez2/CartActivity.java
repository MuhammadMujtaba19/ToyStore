package com.example.mujtaba.odeez2;

import android.content.ClipData;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Canvas;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;


public class CartActivity extends AppCompatActivity {
    private TextView title,price;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<testitems> cart = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    private RelativeLayout coordinatorLayout;
    private Button clearcart;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
    }
}
//        clearcart = (Button)findViewById(R.id.clearAll);
//        price = (TextView)findViewById(R.id.total);
//        recyclerView = (RecyclerView)findViewById(R.id.listCart);
//
//        recyclerView.setHasFixedSize(true);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//
//        coordinatorLayout =(RelativeLayout) findViewById(R.id.RL);
//
//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                deletefrom((CartAdapter.CartViewHolder) viewHolder);
//                load();
//                Snackbar snackbar = Snackbar.make(coordinatorLayout," Removed from cart!", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }).setActionTextColor(Color.YELLOW);
//                snackbar.show();
//            }
//        };
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
//
//
//        load();
//        clearcart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                delete();
//                load();
//            }
//        }
//        );
//    }
//
//    private void load(){
//        database x = new database(this);
//        cart = x.getCarts(getApplicationContext());
//        adapter = new CartAdapter(cart,this);
//        recyclerView.setAdapter(adapter);
//
//    }
//    public void delete() {
//        database c = new database(getBaseContext());
//        c.delete();
//    }
//    public void deletefrom (CartAdapter.CartViewHolder x){
//        database c = new database(getBaseContext());
//        c.deletefrom(x.txt_cart_name.getText().toString());
//    }
//
//
//    }

