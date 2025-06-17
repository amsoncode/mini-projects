package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    //shared pref
    public static final String SHARED_PREF_NAME = "myPref";
    private SharedPreferences sharedPreferences;
    private EditText etEmail, etPassword;
    private Button btnSubmit;
    private ImageView iconHelpCenter;
    private TextView tvMenujuRegis;

    private DataBaseLogin db;
    private boolean isFragmentVisible = false;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnSubmit = findViewById(R.id.tomboltampil);
        iconHelpCenter = findViewById(R.id.btnHelpCenter);
        tvMenujuRegis = findViewById(R.id.menujuregist);

        tvMenujuRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, resgistrasi.class);
                startActivity(intent);
            }
        });

        db = new DataBaseLogin(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = etEmail.getText().toString();
                String getpassword = etPassword.getText().toString();

                if (getemail.isEmpty() && getpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email/Password Salah", Toast.LENGTH_LONG).show();
                } else{
                    Boolean masuk = db.checklogin(getemail, getpassword);
                    if (masuk == true){
                        Boolean updateSession = db.upgradeSession("ada", 1);
                        if (updateSession == true){
                            Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_LONG).show();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("masuk", true);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), Page2.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Login, Silahkan Daftarkan Diri!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        iconHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFragment();
            }
        });
    }

    private void toggleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_helpcenter);

        if (isFragmentVisible && fragment != null) {
            transaction.remove(fragment);
            isFragmentVisible = false;
        } else {
            transaction.replace(R.id.fragment_helpcenter, new helpcenter());
            transaction.addToBackStack(null);
            isFragmentVisible = true;
        }
        transaction.commit();
    }
}
