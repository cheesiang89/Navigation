package com.example.cslee.navigation.models;

import com.example.cslee.navigation.R;
import com.google.firebase.database.IgnoreExtraProperties;

    // [START comment_class]
    @IgnoreExtraProperties
    public class Shop {



        private String shopName;
        private  String shopImageUrl;

        public Shop() {
            // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
        }

        public Shop(String shopname, String shopImageUrl) {
            this.shopName= shopname;
            this.shopImageUrl = shopImageUrl;
                }

        public String getShopname() {
            return shopName;
        }

        public void setShopname(String shopname) {
            this.shopName = shopname;
        }

        public String getShopImageUrl() {
            return shopImageUrl;
        }

        public void setShopImageUrl(String shopImageUrl) {
            this.shopImageUrl = shopImageUrl;
        }
    }
// [END comment_class]

