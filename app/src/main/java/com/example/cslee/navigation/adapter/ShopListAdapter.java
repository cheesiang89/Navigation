package com.example.cslee.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.cslee.navigation.models.Shop;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CSLee on 23/12/2017.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListHolder> {
    List<Shop> shopArray;
    Shop data;
    Context context;
    public ShopListAdapter(List<Shop> shopArray,Context context) {
        this.shopArray = shopArray;
        this.context=context;
    }

    @Override
    public ShopListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ShopListHolder shopListHolder = new ShopListHolder(view);
        return shopListHolder;
    }

    @Override
    public void onBindViewHolder(ShopListHolder holder, int position) {
        data = shopArray.get(position);
        holder.shopName.setText(data.getShopname());
        //Glide.with(context).load(data.getShopImageUrl()).into(holder.shopImage);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
