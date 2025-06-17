package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMbanking extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mbanking, container, false);

        // Ambil referensi tombol
        Button buttonBayar = view.findViewById(R.id.bayartunai);

        // Tambahkan event klik untuk pindah ke RatingActivity
        buttonBayar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RatingActivity.class);
            startActivity(intent);
        });

        return view;
    }
}