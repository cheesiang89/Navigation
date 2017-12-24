package com.example.cslee.navigation.viewholder;

/**
 * Created by CSLee on 23/12/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cslee.navigation.R;
import com.example.cslee.navigation.models.Shop;
import com.squareup.picasso.Picasso;

//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

public class ShopListHolder extends RecyclerView.ViewHolder{
    private static final String TAG = ShopListHolder.class.getSimpleName();
    public TextView shopName;
    public ImageView shopImage;
    public Button btnChat;

    public ShopListHolder(View itemView) {
        super(itemView);
        shopName = (TextView)itemView.findViewById(R.id.itemText);
        shopImage = (ImageView)itemView.findViewById(R.id.itemImage);
        btnChat = (Button)itemView.findViewById(R.id.btnChat);
    }
    public void bindToList(Shop shop,View.OnClickListener chatClickListener) {
        setShopName(shop.getShopname());
        setImage(shop.getShopImageUrl());
        btnChat.setOnClickListener(chatClickListener);
    }
    public void setShopName(String title)
    {

        shopName.setText(title);
    }

    public void setImage(String imageUrl)
    {

        Picasso.with(itemView.getContext())
                .load(imageUrl)
                .into(shopImage);
    }
}
