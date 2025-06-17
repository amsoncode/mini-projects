package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class resgistrasi extends AppCompatActivity {

    private DataBaseLogin db;
    private EditText email, password, nama, notelepon;
    private Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistrasi);

        // Link UI components
        // Use correct IDs for EditText fields
        email = findViewById(R.id.label_email);
        password = findViewById(R.id.label_password);
        nama = findViewById(R.id.nama);
        notelepon = findViewById(R.id.notelepon);
        daftar = findViewById(R.id.btnRegister);

        // Initialize database
        db = new DataBaseLogin(this);

        // Register button logic
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inEmail = email.getText().toString().trim();
                String inPassword = password.getText().toString().trim();
                String inNama = nama.getText().toString().trim();
                String inNotelepon = notelepon.getText().toString().trim();

                // Validate input
                if (inEmail.isEmpty() || inPassword.isEmpty() || inNama.isEmpty() || inNotelepon.isEmpty()) {
                    Toast.makeText(resgistrasi.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean success = db.insertuser(inEmail, inPassword, inNama, inNotelepon);

                if (success) {
                    Toast.makeText(resgistrasi.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(resgistrasi.this, Page2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(resgistrasi.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
