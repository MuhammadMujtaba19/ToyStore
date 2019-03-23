package com.example.mujtaba.odeez2;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.master.glideimageview.GlideImageView;
import com.onurciner.oxswipe.OXSwipe;
import com.rowland.cartcounter.view.CartCounterActionView;
import com.squareup.picasso.Picasso;
//import com.stfalcon.frescoimageviewer.ImageViewer;


import java.util.ArrayList;


import static com.example.mujtaba.odeez2.MainActivity.TotalPrice;
import static com.example.mujtaba.odeez2.MainActivity.cartCount;
import static com.example.mujtaba.odeez2.R.id.product_image;

public class ProductDetails extends AppCompatActivity{ //implements SimpleGestureFilter.SimpleGestureListener{
    public static float total =0;
//    private GlideImageView itemImage;
private ImageView itemImage;

    private TextView itemSellPrice,itemDiscount;
    private TextView itemName;
    private TextView quanitity;
    private TextView itemdescription;
    private Button addtocart;
    private int counter,sizeof;
    public DataBaseHelper db;
    public boolean itemadded = false;
    private ArrayList<String> arrayName,arrayURL,arrayDesc;
    private ArrayList<Integer> arrayPrice,arrayDiscount;
    private int value=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_details);

        View view = findViewById(R.id.myView2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        counter = getIntent().getExtras().getInt("ID");
        sizeof = getIntent().getExtras().getInt("Sizeof");
//        counter = 2;

        itemSellPrice = ((TextView) findViewById(R.id.category_discount));
        quanitity = ((TextView) findViewById(R.id.iteam_amount));
        itemName = ((TextView) findViewById(R.id.product_name));
        itemdescription = ((TextView) findViewById(R.id.product_description));
        itemImage = (ImageView) findViewById(product_image);
        itemDiscount = (TextView)findViewById(R.id.discount);
        addtocart = (Button) findViewById(R.id.addtocart);


        db = new DataBaseHelper(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        arrayName = bundle.getStringArrayList("array1");
        arrayURL =  bundle.getStringArrayList("array2");
        arrayPrice  = bundle.getIntegerArrayList("array3");
        arrayDesc = bundle.getStringArrayList("array4");
        arrayDiscount = bundle.getIntegerArrayList("array5");
        fillProductData(counter);
        view.setOnTouchListener(new OXSwipe(){
            @Override
            public void rightSwipe() {
                itemadded=false;
                if ( counter==0 ){
                Toast.makeText(ProductDetails.this, "Error! Swipe the other direction to View Products ", Toast.LENGTH_SHORT).show();
            }else {
                counter--;
                fillProductData(counter);
            }


            }
            @Override
            public void leftSwipe() {
                itemadded=false;
                if (counter==sizeof-1){
                    Toast.makeText(ProductDetails.this, "Error! Swipe the other direction to View Products ", Toast.LENGTH_SHORT).show();
                }else {
//                    str = "Swipe Right";
                    counter++;
                    fillProductData(counter);
                }
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetocart(counter);
            }
        });
}
    public void fillProductData(int c ) {
        this.itemSellPrice.setText("Rs. "+this.arrayPrice.get(c));
        if((Integer)this.arrayDiscount.get(c).intValue()==0){
            itemDiscount.setVisibility(View.GONE);
            itemSellPrice.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            itemSellPrice.setPaintFlags(itemDiscount.getPaintFlags() | 128);
        }else{
            itemDiscount.setVisibility(View.VISIBLE);
            itemSellPrice.setPaintFlags(itemDiscount.getPaintFlags() | 16);
            itemSellPrice.setTextColor(SupportMenu.CATEGORY_MASK);
            itemDiscount.setText("RS. "+arrayDiscount.get(c));
        }
        itemName.setText(arrayName.get(c));
        quanitity.setText("1");
        itemdescription.setText(arrayDesc.get(c));
        Picasso.with(getApplicationContext()).load(arrayURL.get(c)).fit().into(itemImage);
//        itemImage.loadImageUrl(arrayURL.get(c));
    }

    public void add(View view) {
        value = Integer.parseInt(quanitity.getText().toString());
        value++;
        quanitity.setText(String.valueOf(value));

    }

    public void remove(View view) {
         value = Integer.parseInt(quanitity.getText().toString());
        if (value == 1) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        } else {
            value--;
            quanitity.setText(value + "");
        }
    }

    public float CalcPrice(int index){
        String s;
        if(arrayDiscount.get(index) ==0){
            s= arrayPrice.get(index).toString();
        }else{
            s= arrayPrice.get(index).toString();
        }
        return Float.parseFloat(s.substring(s.indexOf(46)+1)) * ((float)Integer.parseInt(quanitity.getText().toString()));
    }

    public void savetocart(int index) {
        if (itemadded == true) {
            Toast.makeText(getApplicationContext(), "Item Already Added to Cart !!", Toast.LENGTH_SHORT).show();
        } else {itemadded = true;
            MainActivity.TotalPrice += CalcPrice(index);
            testitems t = new testitems(arrayName.get(index),arrayPrice.get(index),value+"",arrayURL.get(index));
            database c = new database(getBaseContext());
            c.addtocart(t, this);
            Toast.makeText(this, "Added to Cart !", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return super.onCreateOptionsMenu(menu);
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
        }if(id == R.id.whatsapp){
            Intent i= new Intent();
            i.setData(Uri.parse("email"));
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT,"Product Name:- "+arrayName.get(counter)+"\nProduct Price:- "+arrayPrice.get(counter)+"\nProduct Image:- "+arrayURL.get(counter));
            Intent choser = Intent.createChooser(i , "Send Via ");
            startActivity(choser);
//            finish();
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
}
