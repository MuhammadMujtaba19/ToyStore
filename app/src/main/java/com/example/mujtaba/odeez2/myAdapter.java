package com.example.mujtaba.odeez2;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<items> listItems;
    private Context context;
    private int index;
    private ArrayList<String> Namearray;
    private ArrayList<Integer> Pricearray,Discountarray;
    private ArrayList<String> URLarray;
    private ArrayList<String> Descarray;
    public myAdapter(List<items> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        Namearray = new ArrayList<String>();
        Pricearray = new ArrayList<>();
        URLarray = new ArrayList<String>();
        Descarray = new ArrayList<String>();
        Discountarray= new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final myAdapter.ViewHolder holder, int position) {
        Namearray.clear();Pricearray.clear();URLarray.clear();Descarray.clear();Discountarray.clear();
        index=0;

        while (true){
         if (listItems.size()==index)break;
         listItems.get(index).setID(index);
         Namearray.add(listItems.get(index).getName());
         Pricearray.add(listItems.get(index).getPri());
         Discountarray.add(listItems.get(index).getDisc());
            URLarray.add(listItems.get(index).getImageUrl());
            Descarray.add(listItems.get(index).getDesc());

            index++;
        }
//            Toast.makeText(context,listItems.get(index).getName(),Toast.LENGTH_SHORT).show();



        final items listItem=listItems.get(position);

        holder.Tview1.setText(listItem.getName());
        holder.Tview2.setText("Rs."+listItem.getPri());
        if (listItem.getDisc()==0)holder.Tview3.setVisibility(View.INVISIBLE);

        else {
            holder.Tview2.setPaintFlags(holder.Tview2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.Tview3.setText("Rs."+listItem.getDisc());
        }

        Picasso.with(context).load(listItem.getImageUrl()).into(holder.imageView);
//        Picasso.with(context).load("https://cdn11.bigcommerce.com/s-73a20/images/stencil/1280x1280/products/2477/5559/l5197__46872.1454583041.jpg").into(holder.imageView);
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,ProductDetails.class);
                    i.putExtra("ID",listItem.getID());
                    i.putExtra("Sizeof",index);
                    i.putExtra("array1",Namearray);
                    i.putExtra("array2",URLarray);
                    i.putExtra("array3",Pricearray);
                    i.putExtra("array4",Descarray);
                    i.putExtra("array5",Discountarray);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                  context.startActivity(i);

                }
            });
    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Tview1,Tview2,Tview3;
        public ImageView imageView;
        public LinearLayout rl ;
        public ViewHolder(View itemView) {
            super(itemView);
            Tview1 = (TextView)itemView.findViewById(R.id.txt1);
            Tview2 = (TextView)itemView.findViewById(R.id.txt2);
            Tview3 = (TextView)itemView.findViewById(R.id.txt3);
            imageView = (ImageView)itemView.findViewById(R.id.img);
            rl = (LinearLayout)itemView.findViewById(R.id.l1);
        }
    }
}