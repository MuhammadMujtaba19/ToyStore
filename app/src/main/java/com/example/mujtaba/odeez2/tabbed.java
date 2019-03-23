package com.example.mujtaba.odeez2;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

public class tabbed extends AppCompatActivity implements fragment2.Interface {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public String output;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(tabbed.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Gmail sender = new Gmail("youremail", "yourpassword");
                    sender.sendMail("EmailSender App",
                            "This is the message body",
                            "youremail",
                            "your recipient's email");
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendData(String t) {
        String tag = "android:switcher:" + R.id.container + ":" + 2;
        fragment3 f = (fragment3) getSupportFragmentManager().findFragmentByTag(tag);
        if(f==null)Toast.makeText(tabbed.this,t,Toast.LENGTH_LONG).show();
        else f.displayReceivedData(t);


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    //  Toast.makeText(tabbed.this,position,Toast.LENGTH_LONG).show();
                    fragment1 f1=new fragment1();
                    return f1;
                case 1:
                    //Toast.makeText(tabbed.this,position,Toast.LENGTH_LONG).show();
                    fragment2 f2=new fragment2();
                    return f2;
                case 2:
                    //Toast.makeText(tabbed.this,position,Toast.LENGTH_LONG).show();
                    fragment3 f3=new fragment3();
                    return f3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "CONFIRMATION";
                case 1:
                    return "ADDRESS VARIFICATION";
                case 2:
                    return "PAYMENT METHOD";
            }
            return null;
        }
    }
}