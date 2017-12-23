package com.example.cslee.navigation.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.cslee.navigation.CSListAdapter;
import com.example.cslee.navigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListingFragment extends Fragment {


    public ShopListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_shop_listing, container, false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.listRecyclerView);

        //TODO: To use diff Adapter
        CSListAdapter listAdapter = new CSListAdapter(this.getContext());
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Test",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
