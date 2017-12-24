package com.example.cslee.navigation.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.cslee.navigation.FirebaseClient;
import com.example.cslee.navigation.R;
import com.example.cslee.navigation.adapter.ShopListAdapter;
import com.example.cslee.navigation.adapter.ShopListHolder;
import com.example.cslee.navigation.models.Shop;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

//Uses in class RecycleViewAdapter + ViewHolder (separate class)

public class ShopListingFragment2 extends Fragment {
    //This constant is for easy referencing for Log purposes
    private static final String TAG = ShopListingFragment.class.getSimpleName();

    //Layout
    private RecyclerView rvShopList;
    private LinearLayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Shop, ShopListHolder> mFirebaseAdapter;

    //Firebase variables
    private DatabaseReference mDatabase;
    private ChildEventListener mShopListener; //not used

    //Model
    Shop shop;

    //Empty constructor
    public ShopListingFragment2() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_shop_listing, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        rvShopList = rootView.findViewById(R.id.shopListRecyclerView);
        rvShopList.setHasFixedSize(true);

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Set up Layout Manager, reverse layout
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rvShopList.setLayoutManager(layoutManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query shopQuery = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Shop>()
                .setQuery(shopQuery, Shop.class)
                .build();

        //Configure adapter
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Shop, ShopListHolder>(options) {

            @Override
            public ShopListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new ShopListHolder(inflater.inflate(R.layout.list_item, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(ShopListHolder viewHolder, int position, final Shop model) {
                //TODO: Method to be added later: To show store details
                //final DatabaseReference shopRef = getRef(position);

                // Set click listener for the shop view
/*
                final String shopKey = shopRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                        intent.putExtra(ShopDetailActivity.EXTRA_SHOP_KEY, shopKey);
                        startActivity(intent);
                    }
                });
*/
                viewHolder.bindToList(model);
            }
        };
        rvShopList.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    //TODO: Method necessary to tweak query in later stage (i.e. make this abstract class)
   // public abstract Query getQuery(DatabaseReference databaseReference);

    //Method can be placed in inherited class later on
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_store_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentStoreQuery = databaseReference.child("shops")
                .limitToFirst(100);
        // [END recent_store_query]

        return recentStoreQuery;
    }
}
