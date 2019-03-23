package com.example.mujtaba.odeez2;

/**
 * Created by Mujtaba on 4/30/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mujtaba on 3/22/2018.
 */
public class Frag1Adapter extends RecyclerView.Adapter<Frag1Adapter.CartViewHolder> {
    private List<testitems> listdata ;
    private Context context;

    public Frag1Adapter(List<testitems> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
//holder.setTxt_cart_name(listdata.get(position).getProductName());
        final testitems t = listdata.get(position);
        holder.txt_cart_name.setText(t.getProductName());
        Picasso.with(context).load(t.getProductURL()).into(holder.img_cart);
        holder.txt_price.setText("Rs."+t.getProductPrice());
        holder.txt_quantity.setText("Quantity:- "+t.getProductQuantity());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txt_cart_name, txt_price,txt_quantity,minus,plus,total;
        public ImageView img_cart;
        public RelativeLayout br1,viewForeground;

        public CartViewHolder(View itemView) {
            super(itemView);
            txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
            txt_price = (TextView) itemView.findViewById(R.id.cart_price);
            txt_quantity = (TextView) itemView.findViewById(R.id.quantity);
            img_cart = (ImageView)itemView.findViewById(R.id.mmm);
            total = (TextView)itemView.findViewById(R.id.total);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"Hello JEE",Toast.LENGTH_SHORT).show();
        }
    }
}



