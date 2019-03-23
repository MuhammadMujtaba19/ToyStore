package com.example.mujtaba.odeez2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Mujtaba on 2/10/2019.
 */

public class ImageAdapter extends PagerAdapter {
    private Context context ;
    private LayoutInflater layoutInflater;
    private Integer [] img = {R.drawable.barbie,R.drawable.playmobil,R.drawable.hotwheels,R.drawable.playmobil,R.drawable.lego,R.drawable.fisher,R.drawable.cryola,R.drawable.plantoys,R.drawable.revell};

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(img[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
