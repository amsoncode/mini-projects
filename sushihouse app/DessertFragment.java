package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class DessertFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dessert, container, false);
        ImageButton keranjang1 = view.findViewById(R.id.keranjang1);
        ImageButton keranjang2 = view.findViewById(R.id.keranjang2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        };

        keranjang1.setOnClickListener(listener);
        keranjang2.setOnClickListener(listener);

        return view;
    }
}