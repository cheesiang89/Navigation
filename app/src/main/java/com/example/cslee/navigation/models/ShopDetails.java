package com.example.cslee.navigation.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START comment_class]
@IgnoreExtraProperties
public class ShopDetails {

    public String id;
    public String name;
    public String address;

    public ShopDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public ShopDetails(String id, String description, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

}
// [END comment_class]

