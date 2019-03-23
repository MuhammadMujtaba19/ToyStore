package com.example.mujtaba.odeez2;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mujtaba on 2/12/2019.
 */

public class FABadapter extends RecyclerView.Adapter<FABadapter.CustomFABview>{


    private Integer [] imageid = {R.drawable.ic_baby,R.drawable.ic_arts,R.drawable.ic_board_game,R.drawable.ic_lego,R.drawable.ic_car,R.drawable.ic_gun,R.drawable.ic_plant,R.drawable.ic_drone,R.drawable.ic_doll};
    private String [] string = {"Baby & Toddler", "Arts & Craft","BoardGame","Building Blocks","Car/Plane Toys","Toy Weapon","Garden & Plants","Drone","Dolls"};
    private Integer[] color = {R.color.aqua,R.color.purple,R.color.sea,R.color.orange,R.color.aliza,R.color.cyan,R.color.river,R.color.bluebell,R.color.river};
    private Context context;

    public FABadapter(Context context) {
        this.context=context;
    }

    @Override
    public CustomFABview onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fab_card,parent,false);
        return new FABadapter.CustomFABview(v);
    }

    @Override
    public void onBindViewHolder(FABadapter.CustomFABview holder, final int position) {
        holder.fab.setImageResource(imageid[position]);
        holder.tv.setText(string[position]);
        holder.fab.setBackgroundTintList(ColorStateList.valueOf(color[position]));

        holder.fab.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageid.length;
    }

    public static class CustomFABview extends RecyclerView.ViewHolder{
        //        public TextView Tview1,Tview2;
        public FloatingActionButton fab ;
        public TextView tv;
        //        public LinearLayout rl ;
        public CustomFABview(View itemView)
        {
            super(itemView);
            fab= (FloatingActionButton) itemView.findViewById(R.id.fabid);
            tv = (TextView)itemView.findViewById(R.id.textid);
//            rl = (LinearLayout)itemView.findViewById(R.id.l1);
        }
    }
    void check(int c){
        Intent i = new Intent(context, Product_Activity.class);
        if (c==0)i.putExtra("check",1);
        else if (c==1) i.putExtra("check",2);
        else if (c==2)i.putExtra("check",3);
        else if (c==3)i.putExtra("check",4);
        else if (c==4)i.putExtra("check",5);
        else if (c==5)i.putExtra("check",6);
        else if (c==6)i.putExtra("check",7);
        else if (c==7)i.putExtra("check",8);
        else if (c==8)i.putExtra("check",9);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//| Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(i);
//        Toast.makeText(context,"q113",Toast.LENGTH_SHORT).show();

    }

}
