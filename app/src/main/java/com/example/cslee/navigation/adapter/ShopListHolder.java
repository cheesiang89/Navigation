package com.example.cslee.navigation;

/**
 * Created by CSLee on 23/12/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

public class ShopListHolder extends RecyclerView.ViewHolder{
    private static final String TAG = ShopListHolder.class.getSimpleName();
    public TextView shopName;
    public ImageView shopImage;
    public ShopListHolder(View itemView) {
        super(itemView);
        shopName = (TextView)itemView.findViewById(R.id.itemText);
        shopImage = (ImageView)itemView.findViewById(R.id.itemImage);
    }
    public void setTitle(String title)
    {
        shopName.setText(title);
    }

    public void setImage(String image)
    {
        Picasso.with(itemView.getContext())
                .load(image)
                .into(shopImage);
    }
}
