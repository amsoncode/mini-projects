package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sushihouse.uassushihouse.CartActivity;
import com.sushihouse.uassushihouse.R;

public class SushiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sushi, container, false);

        ImageButton keranjang1 = view.findViewById(R.id.keranjang1);
        ImageButton keranjang2 = view.findViewById(R.id.keranjang2);
        ImageButton keranjang3 = view.findViewById(R.id.keranjang3);
        ImageButton keranjang4 = view.findViewById(R.id.keranjang4);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        };

        keranjang1.setOnClickListener(listener);
        keranjang2.setOnClickListener(listener);
        keranjang3.setOnClickListener(listener);
        keranjang4.setOnClickListener(listener);

        return view;
    }
}