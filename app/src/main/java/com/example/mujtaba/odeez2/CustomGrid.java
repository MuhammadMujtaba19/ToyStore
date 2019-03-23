package com.example.mujtaba.odeez2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomGrid extends RecyclerView.Adapter<CustomGrid.CustomViewHolder>{
     private Integer [] imageid = {R.drawable.playmobil,R.drawable.hotwheels,R.drawable.lego,R.drawable.fisher,R.drawable.cryola,R.drawable.revell};

    private Context context;

    public CustomGrid(Context context) {
        this.context=context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_single,parent,false);
        return new CustomGrid.CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomGrid.CustomViewHolder holder, final int position) {
        holder.imageView.setImageResource(imageid[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                check (position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageid.length;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
//        public TextView Tview1,Tview2;
        public ImageView imageView;
//        public LinearLayout rl ;
        public CustomViewHolder(View itemView) {
            super(itemView);
//            Tview1 = (TextView)itemView.findViewById(R.id.txt1);
//            Tview2 = (TextView)itemView.findViewById(R.id.txt2);
            imageView = (ImageView)itemView.findViewById(R.id.grid_img);
//            rl = (LinearLayout)itemView.findViewById(R.id.l1);
        }
    }
    void check(int c){
        Intent i = new Intent(context, Product_Activity.class);
        if (c==0)i.putExtra("check",1);
        else if (c==1) i.putExtra("check",12);
            else if (c==2)i.putExtra("check",7);
            else if (c==3)i.putExtra("check",9);
            else if (c==4)i.putExtra("check",5);
            else if (c==5)i.putExtra("check",4);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//| Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(i);
//        Toast.makeText(context,"q113",Toast.LENGTH_SHORT).show();

    }
}
