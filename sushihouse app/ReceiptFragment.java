package com.sushihouse.uassushihouse;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ReceiptFragment extends Fragment {

    private EditText etUlasan;
    private Button btnSubmit;
    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);

        etUlasan = view.findViewById(R.id.etUlasan);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        ratingBar = view.findViewById(R.id.ratingBar);

        changeRatingBarColor(ratingBar);

        btnSubmit.setOnClickListener(v -> {
            String ulasan = etUlasan.getText().toString().trim();
            float rating = ratingBar.getRating();


            if (!ulasan.isEmpty()) {
                Toast.makeText(getActivity(), "Rating: " + rating + "\nUlasan: " + ulasan, Toast.LENGTH_SHORT).show();

                ratingBar.setRating(0);
                etUlasan.setText("");
            } else {
                Toast.makeText(getActivity(), "Silakan masukkan ulasan!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void changeRatingBarColor(RatingBar ratingBar) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(0xFFFFD700, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(0xFFFFD700, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(0xFFD3D3D3, PorterDuff.Mode.SRC_ATOP);
    }
}