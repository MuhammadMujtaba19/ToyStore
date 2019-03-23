package com.example.mujtaba.odeez2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.widget.PullRefreshLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.rowland.cartcounter.view.CartCounterActionView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.example.mujtaba.odeez2.MainActivity.cartCount;


public class Product_Activity extends AppCompatActivity {

    private final String URL_vari = "https://api.myjson.com/bins/lt4cq";
    private String datakiska;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter,tempAdapter;
    private List<items> LA,found,temp;
    private RequestQueue mQueue;
    String url = "";

    MaterialSearchView materialSearchView;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Product_Activity.this, ActivityCheckout.class);
                startActivity(x);
            }
        });

//
//        PullRefreshLayout layout = (PullRefreshLayout)findViewById(R.id.refreshLayout);
//        layout.setRefreshStyle(2);
//        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(Product_Activity.this,"here",Toast.LENGTH_SHORT).show();
//            }
//        });
//        layout.setRefreshing(false);


            int val = getIntent().getExtras().getInt("check");
            switch (val) {
                case 1:
                    
                setTitle("Collectibles");
                    datakiska = "artsandcraft";
                    url = "https://www.iknowmytoys.com/categories/collectibles.html";
                    break;
                case 2:
                    setTitle("Action Figures/ Characters");
                    datakiska = "babyrockers";
                    url = "https://www.iknowmytoys.com/categories/action-figures-characters.html";
                    break;
                case 3:
                    setTitle("Arts and Craft");
                    datakiska = "bestseller";
                    url = "https://www.iknowmytoys.com/categories/art-crafts.html";
                    break;
                case 4:
                    setTitle("Baby Toddler");
                    datakiska = "bornbabytoys";
                    url = "https://www.iknowmytoys.com/categories/baby-toddlers.html";
                    break;
                case 5:
//                txt.setText("Building Sets");
                    setTitle("Board Games");
                    datakiska = "buildingsets";
                    url = "https://www.iknowmytoys.com/categories/board-games.html";
                    break;
                case 6:
//                txt.setText("Creative Toys");
                    setTitle("Building Blocks");
                    datakiska = "creative";
                    url = "https://www.iknowmytoys.com/categories/create-build/building-blocks.html";
                    break;
                case 7:
                    setTitle("Building Kits");
                    datakiska = "crea";
                    url = "https://www.iknowmytoys.com/categories/create-build/building-kits.html";

                    break;
                case 8:
                    setTitle("Car / Train / Plane");
                    datakiska = "earlychildhoodtoys";
                    url = "https://www.iknowmytoys.com/categories/cars-planes-tracks.html";
                    break;
                case 9:
                    setTitle("Dolls and Doll house");
                    datakiska = "flashcards";
                    url = "https://www.iknowmytoys.com/categories/dolls-and-doll-houses.html";
                    break;
                case 10:
                    setTitle("Drone");
                    datakiska = "learningtoys";
                    url = "https://www.iknowmytoys.com/categories/drones.html";
                    break;

                case 11:
                    setTitle("Educational Toys");
                    datakiska = "pretendplay";
                    url = "https://www.iknowmytoys.com/categories/educational-sets.html";
                    break;
                case 12:
                    setTitle("Garden and Planet");
                    datakiska = "primarykids";
                    url = "https://www.iknowmytoys.com/categories/garden-and-plants.html";
                    break;
                case 13:
                    setTitle("Movie DVDs");
                    datakiska = "science&technology";
                    url = "https://www.iknowmytoys.com/categories/movies-dvd.html";
                    break;
                case 14:
                    setTitle("Toy Weapon");
                    datakiska = "teenandabove";
                    url = "https://www.iknowmytoys.com/categories/toy-weapons.html";
                    break;
            }
            recyclerView = (RecyclerView) findViewById(R.id.prodview);
            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new GridLayoutManager(Product_Activity.this, 2));
            recyclerView.setNestedScrollingEnabled(false);

            materialSearchView = (MaterialSearchView) findViewById(R.id.search_bar_here);
            mQueue = Volley.newRequestQueue(this);
            LA = new ArrayList<>();
            temp = new ArrayList<>();
            found = new ArrayList<items>();

        temp =LA;
        new Loadtoys().execute();

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                found.clear();
                //temp=LA;
                if (newText!=null && !newText.isEmpty()) {
                    for (int i = 0; i < LA.size(); i++) {
//                        Toast.makeText(Product_Activity.this,i+"",Toast.LENGTH_SHORT).show();
                        if (LA.get(i).getName().toLowerCase().contains(newText) || LA.get(i).getName().contains(newText)) {
                            found.add(LA.get(i));
                        }
                    }
//                    temp = found;
                    tempAdapter = new myAdapter(found,getApplicationContext());
                    recyclerView.setAdapter(tempAdapter);

                }else if (newText==null && newText.isEmpty()){
                        temp  = found;
                }else
                    {
                    adapter = new myAdapter(LA, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
                return true;
            }
        });
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                tempAdapter = new myAdapter(found,getApplicationContext());
                recyclerView.setAdapter(tempAdapter);

            }

            @Override
            public void onSearchViewClosed() {
                adapter = new myAdapter(LA, getApplicationContext());
                recyclerView.setAdapter(adapter);
//                tempAdapter = new myAdapter(found,getApplicationContext());
//                recyclerView.setAdapter(tempAdapter);

            }
        });

    }

    private void LoadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING ......");
        progressDialog.show();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, URL_vari, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
//                    JSONObject reader = new JSONObject(response);
                    JSONArray array = response.getJSONArray(datakiska);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject obj = array.getJSONObject(i);
                        items x = new items(i,obj.getString("name"), obj.getInt("price"), obj.getString("image"),obj.getInt("disc"),obj.getString("desc"));
                        LA.add(x);
                        count++;
                    }
                    adapter = new myAdapter(LA, getApplicationContext());
                    recyclerView.setAdapter(adapter);
//                    Toast.makeText(Product_Activity.this,"total Data:-  "+count,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                retryFunc();
                error.printStackTrace();
            }
        });
        mQueue.add(stringRequest);
    }
    public void retryFunc(){
        AlertDialog.Builder builder =new AlertDialog.Builder(Product_Activity.this);
        builder.setTitle("No Internet Connection! ").setMessage("To Proceed Please Connect Internet or Enable Data and Try Again").setCancelable(false)
                .setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        LoadData();
                    }
                }).setPositiveButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();}
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
    @Override
    public void onBackPressed() {
        if (materialSearchView.isSearchOpen()) {
            materialSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    public class Loadtoys extends AsyncTask<Void,Void,Void> {
        private ProgressBar pb;
        Document mBlogDocument;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... params) {
            Document mBlogDocument = null;
            try {
                mBlogDocument = Jsoup.connect(url).get();

            Elements mElementDataSize = mBlogDocument.select("ul[class=productList]").select("li[class=product]");
                for (int i = 0; i < mElementDataSize.size(); i++) {
                    Elements tempitem = mBlogDocument.select("ul[class=productList]").select("li[class=product]").eq(i);

                    Elements name = tempitem.select("article").select("div[class=listItem-body]").select("div[class=listItem-details]").select("h4[class=listItem-title]");
                    String a = name.text();

                    Elements detail = tempitem.select("article").select("div[class=listItem-body]").select("div[class=listItem-details]").select("p");
                    String b = detail.text();

                    Elements wasprice = tempitem.select("article").select("div[class=listItem-body]").select("div[class=listItem-actions]").select("div[class=listItem-price]").select("div").eq(2);
                    String c = wasprice.text();


                    Elements nowprice = tempitem.select("article").select("div[class=listItem-body]").select("div[class=listItem-actions]").select("div[class=listItem-price]").select("div").eq(3);
                    String price = nowprice.text();
                    price = price.substring(8);
                    price = price.replace(".00","");
                    int saleprice = Integer.parseInt(price.replaceAll(",", ""));

                    Element image = tempitem.select("article").select("figure[class=listItem-figure]").select("img").first();
                    String e = image.attr("data-src");
                    items x = new items(i,a, saleprice,e, 0, b);
                    LA.add(x);
                    count++;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            adapter = new myAdapter(LA, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }
    }
    }

