package com.example.cslee.navigation.models;

import com.google.firebase.database.IgnoreExtraProperties;

    // [START comment_class]
    @IgnoreExtraProperties
    public class ShopListing {

        public String id;
        public String name;
        public String address;
      

        public ShopListing() {
            // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
        }

        public ShopListing(String id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

    }
// [END comment_class]

