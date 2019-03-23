package com.example.mujtaba.odeez2;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.rowland.cartcounter.view.CartCounterActionView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

//https://www.androidhive.info/2017/12/android-working-with-bottom-sheet/
//https://aatul.me/tag/passing-array-between-activities-in-android-using-intent-and-bundle/
//https://www.androidhive.info/2017/09/android-recyclerview-swipe-delete-undo-using-itemtouchhelper/

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private final String URL_vari2 = "https://api.myjson.com/bins/13r7ja";
    private final String URL_vari= "https://api.myjson.com/bins/tazik";

    private RecyclerView recyclerView,brandRecyclerView,recyclerView3,Fabrecyclerview;
    private RecyclerView.Adapter adapter,adapter2;
    private List<items> LA,LA1;
    ViewPager viewPager;
    private TextView navigationname, navigationphone;
    private RequestQueue mQueue;
    public static int cartCount=0;
    public static float TotalPrice=0;
    FloatingActionButton fab;
    private MaterialSearchView materialSearchView;
    private ImageView NavigationButton,CartButton,Logout;

    public static String username,useraddress,usercity,userphone,useremail;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        returner();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth= FirebaseAuth.getInstance();


        NavigationButton =(ImageView)findViewById(R.id.menubutton);
        CartButton = (ImageView)findViewById(R.id.shoppingCart);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Logout = (ImageView) findViewById(R.id.logout);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, ActivityCheckout.class);
                startActivity(i);
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        navigationname= (TextView) headerView.findViewById(R.id.navigation_name);
        navigationphone= (TextView)headerView.findViewById(R.id.navigation_phone);

        ViewPagerAdapter c = new ViewPagerAdapter(this);
        viewPager.setAdapter(c);
        NavigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);

            }
        });
        CartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.hotselling);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.setNestedScrollingEnabled(false);

        mQueue = Volley.newRequestQueue(this);
        LA = new ArrayList<>();
        LA1 = new ArrayList<>();

        brandRecyclerView = (RecyclerView)findViewById(R.id.brands);
        brandRecyclerView.setHasFixedSize(true);
        brandRecyclerView.setLayoutManager(new LinearLayoutManager(this,0,false));
        brandRecyclerView.setAdapter(new CustomGrid(this));

        Fabrecyclerview= (RecyclerView)findViewById(R.id.fabrecyclerveiw);
        Fabrecyclerview.setHasFixedSize(true);
        Fabrecyclerview.setLayoutManager(new LinearLayoutManager(this,0,false));
        Fabrecyclerview.setAdapter(new FABadapter(this));

        recyclerView3 = (RecyclerView)findViewById(R.id.newarrival);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        recyclerView3.setNestedScrollingEnabled(false);
        LoadData2();
        LoadData1();
        LoadDatabase();

    }


    public class Mytimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (materialSearchView.isSearchOpen()) {
            materialSearchView.closeSearch();
        }
        else {
            AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are You Sure You Want To Exit the Application ??").setCancelable(false)
                    .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setPositiveButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.search_bar);
        materialSearchView.setMenuItem(item);
//        return super.onCreateOptionsMenu(menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.cart) {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            return true;
        }else if (id==R.id.search_bar){
            mAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem itemData = menu.findItem(R.id.cart);
        CartCounterActionView actionView = (CartCounterActionView) itemData.getActionView();
        actionView.setItemData(menu, itemData);
        actionView.setCount(cartCount);
        return super.onPrepareOptionsMenu(menu);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i = new Intent(this, Product_Activity.class);
        if (id == R.id.toy1) {
            i.putExtra("check", 1);
        } else if (id == R.id.toy2) {
            i.putExtra("check", 2);
        } else if (id == R.id.toy3) {
            i.putExtra("check", 3);
        } else if (id == R.id.toy4) {
            i.putExtra("check", 4);
        } else if (id == R.id.toy5) {
            i.putExtra("check", 5);
        } else if (id == R.id.toy6) {
            i.putExtra("check", 6);
        } else if (id == R.id.toy7) {
            i.putExtra("check", 7);
        } else if (id == R.id.toy8) {
            i.putExtra("check", 8);
        } else if (id == R.id.toy9) {
            i.putExtra("check", 9);
        } else if (id == R.id.toy10) {
            i.putExtra("check", 10);
        } else if (id == R.id.toy11) {
            i.putExtra("check", 11);
        } else if (id == R.id.toy12) {
            i.putExtra("check", 12);
        } else if (id == R.id.toy13) {
            i.putExtra("check", 13);
        } else if (id == R.id.toy14) {
            i.putExtra("check", 14);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        startActivity(i);
        return true;
    }
    private void LoadData1(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("LOADING ......");
        progressDialog.show();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, URL_vari, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
//                    JSONObject reader = new JSONObject(response);
                    JSONArray array = response.getJSONArray("hotselling");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject obj = array.getJSONObject(i);
//                        items x = new items(i,obj.getString("name"), obj.getString("price"), obj.getString("image"),"");
                        items x = new items(i,obj.getString("name"), obj.getInt("price"), obj.getString("image"),obj.getInt("disc"),obj.getString("desc"));
                        LA.add(x);

                    }
                    adapter = new myAdapter(LA, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                retryFunc();
                error.printStackTrace();
            }
        });
        mQueue.add(stringRequest);
    }
    private void LoadData2(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("LOADING ......");
        progressDialog.show();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, URL_vari, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
                    JSONArray array = response.getJSONArray("hotselling");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        items x = new items(i,obj.getString("name"), obj.getInt("price"), obj.getString("image"),obj.getInt("disc"),obj.getString("desc"));
                        LA1.add(x);
                    }
                    adapter2 = new myAdapter(LA1, getApplicationContext());
                    recyclerView3.setAdapter(adapter2);

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                retryFunc();
                error.printStackTrace();
            }
        });
        mQueue.add(stringRequest);
    }
    public void retryFunc(){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("No Internet Connection!").setMessage("To Proceed Please Connect Internet or Enable Data and Try Again").setCancelable(false)
                .setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        LoadData1();
                    }
                }).setPositiveButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void returner(){
        database db = new database(this);
        List<testitems> newlist = new ArrayList<>();
        newlist = db.getCarts(getApplicationContext());
        if(newlist.size()!=0){
            for(int i = 0 ; i < newlist.size();i++){
                TotalPrice += Float.parseFloat(String.valueOf(((testitems)newlist.get(i)).getProductPrice()))*
                        Float.parseFloat(((testitems)newlist.get(i)).getProductQuantity());
            }
        }
    }
    private void LoadDatabase() {
        DatabaseReference userInfo=mDatabase.child("customer").child(mAuth.getCurrentUser().getUid());
        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username=dataSnapshot.child("name").getValue(String.class);
                useraddress =dataSnapshot.child("address").getValue(String.class);
                usercity=dataSnapshot.child("city").getValue(String.class);
                userphone=dataSnapshot.child("mobile").getValue(String.class);
                useremail=dataSnapshot.child("email").getValue(String.class);

                navigationname.setText(username);
//                navigationphone.setText(useremail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
