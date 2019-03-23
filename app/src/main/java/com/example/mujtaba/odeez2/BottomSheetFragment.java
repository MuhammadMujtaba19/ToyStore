package com.example.mujtaba.odeez2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.example.mujtaba.odeez2.MainActivity.TotalPrice;

public class BottomSheetFragment extends BottomSheetDialogFragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{
    private RecyclerView recyclerView;
    private List<testitems> cart = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private Context context;
    private LinearLayout cartitems,emptycart;
    private TextView t;
    public BottomSheetFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        cartitems= (LinearLayout) view.findViewById(R.id.abc);
        emptycart = (LinearLayout) view.findViewById(R.id.frag_bg);
         recyclerView = (RecyclerView) view.findViewById(R.id.listCart22);
        t=(TextView)view.findViewById(R.id.total);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        t.setText(TotalPrice+"");

        context = recyclerView.getContext();//;c=context;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        load(context);
        checkCart();
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }
    public void checkCart(){
        if (cart.isEmpty()){
        emptycart.setVisibility(View.VISIBLE);
        cartitems.setVisibility(View.GONE);
    }else{
            emptycart.setVisibility(View.GONE);
            cartitems.setVisibility(View.VISIBLE);
    }}
    private void load(Context context){
        database x = new database(context);
        cart = x.getCarts(context);
        adapter = new CartAdapter(cart,context);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void deletefrombsf (CartAdapter.CartViewHolder x){
        database c = new database(getActivity().getBaseContext());
        c.deletefrom(x.txt_cart_name.getText().toString());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        TotalPrice = TotalPrice - CalcPrice((CartAdapter.CartViewHolder) viewHolder);
        t.setText(TotalPrice+"");
        deletefrombsf((CartAdapter.CartViewHolder) viewHolder);
        load(context);
        checkCart();
    }
    public float CalcPrice(CartAdapter.CartViewHolder x){
        String s = x.txt_price.getText().toString();
        s=s.substring(s.indexOf('.')+1);
        float price = Float.parseFloat(s);
        int quan = Integer.parseInt(x.txt_quantity.getText().toString());
        price = price * quan;
        Toast.makeText(getContext(),"kai scene\n"+price,Toast.LENGTH_SHORT);
        return price;

    }
    }
