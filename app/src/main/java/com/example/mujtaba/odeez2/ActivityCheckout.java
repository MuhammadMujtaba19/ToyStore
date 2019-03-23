package com.example.mujtaba.odeez2;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.mujtaba.odeez2.MainActivity.useraddress;
import static com.example.mujtaba.odeez2.fragment2.SM;
import static com.example.mujtaba.odeez2.fragment2.address;
import static com.example.mujtaba.odeez2.fragment2.city;
import static com.example.mujtaba.odeez2.fragment2.email;
import static com.example.mujtaba.odeez2.fragment2.name;
import static com.example.mujtaba.odeez2.fragment2.phone;

public class ActivityCheckout extends AppCompatActivity implements fragment2.Interface{
    a[] m = new a[]{a.SHIPPING, a.PAYMENT, a.CONFIRMATION};
    private View n;
    private View o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private int v = 0;
    private MaterialRippleLayout next,pre;

    private FirebaseAuth obj;
    private DatabaseReference mDatabase;
public static String txt;

    private enum a {
        SHIPPING,
        PAYMENT,
        CONFIRMATION
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcheckout);
        toolbar.setNavigationIcon((int) R.drawable.ic_action_navigation_close);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        obj= FirebaseAuth.getInstance();

        this.n = findViewById(R.id.line_first);
        this.o = findViewById(R.id.line_second);
        this.p = (ImageView) findViewById(R.id.image_shipping);
        this.q = (ImageView) findViewById(R.id.image_payment);
        this.r = (ImageView) findViewById(R.id.image_confirm);
        this.s = (TextView) findViewById(R.id.tv_shipping);
        this.t = (TextView) findViewById(R.id.tv_payment);
        this.u = (TextView) findViewById(R.id.tv_confirm);
        next = (MaterialRippleLayout)findViewById(R.id.lyt_next);
        pre = (MaterialRippleLayout)findViewById(R.id.lyt_previous);
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder,new fragment1()).commit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (v != m.length - 1) {
                    v = v + 1;
                   if(v==2){
                       txt = name.getText().toString().trim() + "7" + email.getText().toString().trim() +
                               "\n" + phone.getText().toString().trim() + "\n" + city.getText().toString().trim() + "\n" +
                               address.getText().toString().trim();
                       if(!(test.isValid(email.getText().toString()))||(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || city.getText().toString().isEmpty() || address.getText().toString().isEmpty())) {
                           //Toast.makeText(getActivity(), t, Toast.LENGTH_LONG).show();
                           if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || city.getText().toString().isEmpty() || address.getText().toString().isEmpty())
                               Toast.makeText(ActivityCheckout.this, "Please Fill All Necessary Fields", Toast.LENGTH_LONG).show();
                           else if (!test.isValid(email.getText().toString()))
                               Toast.makeText(ActivityCheckout.this, "Invalid Email Address\nPlease Enter Valid Email Address", Toast.LENGTH_LONG).show();
                       v--;
                       }
                       else {
                           SM.sendData(txt);
                           nextFragment(2);
                       }
                   }
                   else {
                       nextFragment(v);
                   }
               }else {
                   Toast.makeText(ActivityCheckout.this, "hello", Toast.LENGTH_SHORT).show();
                   updateOrder();
                   ConfirmOrder();
               }
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v >= 1) {
                    v = v - 1;
                    preFragment(v);
                }else finish();
            }
        });
    }

        @Override
        public void sendData(String t){
            String tag = "android:switcher:" + R.id.container + ":" + 2;
            fragment3 f = (fragment3) getSupportFragmentManager().findFragmentByTag(tag);
            if (f == null) Toast.makeText(this, t, Toast.LENGTH_LONG).show();
            else f.displayReceivedData(t);
        }
    public void nextFragment(int index){
        Fragment f =null;

        if(index==0){
            f= new fragment1();
            s.setTextColor(getResources().getColor(R.color.grey_90));
            p.clearColorFilter();

        }

        else if(index==1) {
            f= new fragment2();
            this.n.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            this.p.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            this.q.clearColorFilter();
            t.setTextColor(getResources().getColor(R.color.grey_90));


        }
        else if(index==2){
            f= new fragment3();
            this.o.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            this.q.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            this.r.clearColorFilter();
            u.setTextColor(getResources().getColor(R.color.grey_90));

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder,f).commit();

    }
    public void preFragment(int index){
        Fragment f =null;
if(index==0){
            f= new fragment1();
            s.setTextColor(getResources().getColor(R.color.grey_90));
            p.clearColorFilter();
            this.n.setBackgroundColor(getResources().getColor(R.color.random));

        }

        else if(index==1) {
            f= new fragment2();
//            this.p.clearColorFilter();
            this.q.clearColorFilter();
            this.o.setBackgroundColor(getResources().getColor(R.color.random));

        }
        else if(index==2){
            f= new fragment3();
            this.q.clearColorFilter();
            this.r.clearColorFilter();

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder,f).commit();
    }
    public void ConfirmOrder(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.confirmation_dialog);
        dialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -2;
        layoutParams.height = -2;
        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Toast.makeText(ActivityCheckout.this, "Back to Home", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

    }
    public void updateOrder(){
        zorder a = new zorder(obj.getCurrentUser().getUid(),useraddress, "Pending","Cash on Delivery","None");
        mDatabase.child("order").push().setValue(a);
    }


    }
