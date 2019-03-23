package com.example.mujtaba.odeez2;

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
import static com.example.mujtaba.odeez2.MainActivity.TotalPrice;
/**
 * Created by Mujtaba on 3/22/2018.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<testitems> listdata ;
    private Context context;
    private testitems t,w;
    private database x;
    public CartAdapter(List<testitems> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (context== MainActivity){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
//holder.setTxt_cart_name(listdata.get(position).getProductName());
        t = listdata.get(position);
        x=new database(context);
        holder.txt_cart_name.setText(t.getProductName());
        Picasso.with(context).load(t.getProductURL()).into(holder.img_cart);
        holder.url.setText(t.getProductURL());
        holder.txt_price.setText("Rs."+t.getProductPrice());
        holder.txt_quantity.setText(t.getProductQuantity());
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(String.valueOf(holder.txt_quantity.getText()));
                if (num==1){Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();}
                else{
                    num--;
                    holder.txt_quantity.setText(String.valueOf(num));
                    t.setProductQuantity(String.valueOf(num));
//                    w= new testitems(holder.txt_cart_name.getText().toString(),Integer.parseInt(holder.txt_price.getText().toString()), holder.txt_quantity.getText().toString(),holder.url.getText().toString());
//                    x.deletefrom(holder.txt_cart_name.getText().toString());
//                    x.addtocart(w,context);
//                    x.deletefrom(t.getProductName());
//                    x.addtocart(t,context);
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int num = Integer.parseInt(String.valueOf(holder.txt_quantity.getText()));
                num++;
                holder.txt_quantity.setText(String.valueOf(num));
                t.setProductQuantity(String.valueOf(num));
                String the=holder.txt_price.getText().toString();the = the.substring(3);
                int number =Integer.parseInt(the);
//                w= new testitems(holder.txt_cart_name.getText().toString(),number, holder.txt_quantity.getText().toString(),holder.url.getText().toString());
//                x.deletefrom(holder.txt_cart_name.getText().toString());
//                x.addtocart(w,context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txt_cart_name, txt_price,txt_quantity,minus,plus,total,url;
        public ImageView img_cart;
        public RelativeLayout br1,viewForeground;

        public CartViewHolder(View itemView) {
            super(itemView);
            txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
            txt_price = (TextView) itemView.findViewById(R.id.cart_price);
            txt_quantity = (TextView) itemView.findViewById(R.id.quantity);
            url = (TextView)itemView.findViewById(R.id.vari);
            img_cart = (ImageView)itemView.findViewById(R.id.mmm);
            minus = (TextView) itemView.findViewById(R.id.remove_item);
            plus = (TextView) itemView.findViewById(R.id.add_item);
            br1= (RelativeLayout)itemView.findViewById(R.id.view_background);
            viewForeground= (RelativeLayout)itemView.findViewById(R.id.view_foreground);
            total = (TextView)itemView.findViewById(R.id.total);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"Hello JEE",Toast.LENGTH_SHORT).show();
        }
    }
}


