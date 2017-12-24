package com.example.cslee.navigation.models;

import com.example.cslee.navigation.R;
import com.google.firebase.database.IgnoreExtraProperties;

    // [START comment_class]
    @IgnoreExtraProperties
    public class Shops {


        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getShopImageUrl() {
            return shopImageUrl;
        }

        public void setShopImageUrl(String shopImageUrl) {
            this.shopImageUrl = shopImageUrl;
        }

        public String shopname;
        public  String shopImageUrl;

        public Shops() {
            // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
        }

        public Shops(String shopname, String shopImageUrl) {
            this.shopname = shopname;
            this.shopImageUrl = shopImageUrl;
                }

    }
// [END comment_class]

