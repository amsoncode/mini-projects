package com.sushihouse.uassushihouse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sushihouse.uassushihouse.FragmentMbanking;
import com.sushihouse.uassushihouse.FragmentQRIS;
import com.sushihouse.uassushihouse.FragmentTunai;
import com.sushihouse.uassushihouse.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Ambil referensi dari checkbox dan tombol
        CheckBox checkTunai = findViewById(R.id.checkTunai);
        CheckBox checkMbanking = findViewById(R.id.checkMbanking);
        CheckBox checkQRIS = findViewById(R.id.checkQRIS);
        Button buttonBayar = findViewById(R.id.buttonBayar);

        // Array untuk mempermudah pengaturan eksklusivitas
        CheckBox[] checkBoxes = {checkTunai, checkMbanking, checkQRIS};

        // Tambahkan listener untuk memastikan hanya satu checkbox yang dipilih
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Nonaktifkan checkbox lainnya
                    for (CheckBox otherCheckBox : checkBoxes) {
                        if (otherCheckBox != checkBox) {
                            otherCheckBox.setChecked(false);
                        }
                    }
                }
            });
        }

        // Tambahkan listener untuk tombol BAYAR
        buttonBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = null;

                if (checkTunai.isChecked()) {
                    selectedFragment = new FragmentTunai();
                } else if (checkMbanking.isChecked()) {
                    selectedFragment = new FragmentMbanking();
                } else if (checkQRIS.isChecked()) {
                    selectedFragment = new FragmentQRIS();
                }

                // Ganti fragment jika ada yang dipilih
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .addToBackStack(null) // Menyimpan fragment sebelumnya di backstack
                            .commit();
                }
            }
        });
    }
}