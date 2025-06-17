package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.sushihouse.uassushihouse.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class Page2 extends AppCompatActivity {

    public static final String SHARED_PREF_NAME = "myPref";
    private SharedPreferences sharedPreferences;
    private DataBaseLogin db;
    private ImageButton btnLogout;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        btnLogout = findViewById(R.id.btnLogout);

        db = new DataBaseLogin(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        boolean checksession = db.checksession("ada");

        if (checksession == false){
            Intent login = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(login);
            finish();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean updatesession = db.upgradeSession("kosong", 1);
                if (updatesession == true){
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("masuk", false);
                    editor.apply();

                    Intent keluar = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(keluar);
                    finish();
                }
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SushiFragment());
        fragments.add(new MinumanFragment());
        fragments.add(new DessertFragment());

        adapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Sushi");
                    break;
                case 1:
                    tab.setText("Minuman");
                    break;
                case 2:
                    tab.setText("Dessert");
                    break;
            }
        }).attach();
    }
}