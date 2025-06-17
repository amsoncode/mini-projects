 package com.sushihouse.uassushihouse;

import com.sushihouse.uassushihouse.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat insetsController =
                new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        insetsController.hide(WindowInsetsCompat.Type.statusBars());
        insetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, 0);
                boolean logged = sharedPreferences.getBoolean("masuk", false);

                if (logged) {
                    startActivity(new Intent(getApplicationContext(), Page2.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                finish();
            }
        }, 3000);
    }
}
