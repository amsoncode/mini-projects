package com.sushihouse.uassushihouse;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;



public class CartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new CartFragment());
            transaction.commit();

        }
    }
}
