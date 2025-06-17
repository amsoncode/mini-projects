package com.sushihouse.uassushihouse;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import android.widget.Toast;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {
    private TextView totalPriceTextView;
    private final Map<Integer, Integer> prices = new HashMap<>();
    private final Map<EditText, Integer> quantityFields = new HashMap<>();

    private EditText etNoMeja;

    private void kirimDataKeGoogleSheet() {
        String url = "https://script.google.com/macros/s/AKfycbzFqJ4LSUdMSJ3LfhECJJvfc4i_WeYpuhy3GFBTg8JBg01j-x-56O3V_fOjbWs0h3ZX/exec";
        String noMeja = etNoMeja.getText().toString();

        Map<String, String> params = new HashMap<>();
        params.put("no_meja", noMeja);

        for (Map.Entry<EditText, Integer> entry : quantityFields.entrySet()) {
            int id = entry.getKey().getId();
            String itemName = getResources().getResourceEntryName(id).replace("qty_", ""); // Misal: qty_nigiri → nigiri
            String quantity = entry.getKey().getText().toString();
            params.put(itemName, quantity);
        }

        String totalText = totalPriceTextView.getText().toString().replace("Total: Rp. ", "").replace(".", "");
        params.put("total", totalText);

        JSONObject jsonObject = new JSONObject(params);

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> Toast.makeText(getContext(), "Pesanan berhasil dikirim!", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getContext(), "Gagal kirim: " + error.getMessage(), Toast.LENGTH_LONG).show()
        );

        queue.add(request);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        etNoMeja = view.findViewById(R.id.et_no_meja);
        totalPriceTextView = view.findViewById(R.id.totalPrice);
        Button buttonPilihMetode = view.findViewById(R.id.button_pilih_metode);

        prices.put(R.id.qty_california, 30000);
        prices.put(R.id.qty_nigiri, 30000);
        prices.put(R.id.qty_mix, 50000);
        prices.put(R.id.qty_tamago, 25000);
        prices.put(R.id.qty_ocha, 10000);
        prices.put(R.id.qty_lemon_tea, 15000);
        prices.put(R.id.qty_ice_cream, 18000);
        prices.put(R.id.qty_pudding, 15000);

        for (Map.Entry<Integer, Integer> entry : prices.entrySet()) {
            EditText qtyField = view.findViewById(entry.getKey());
            quantityFields.put(qtyField, entry.getValue());
            qtyField.addTextChangedListener(new QuantityTextWatcher(qtyField));
        }

        buttonPilihMetode.setOnClickListener(v -> {
            String noMeja = etNoMeja.getText().toString();
            if (noMeja.isEmpty()) {
                Toast.makeText(getContext(), "Nomor meja wajib diisi!", Toast.LENGTH_SHORT).show();
                return;
            }
            kirimDataKeGoogleSheet();
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            startActivity(intent);
        });


        return view;
    }



    private void updateTotalPrice() {
        int total = 0;
        for (Map.Entry<EditText, Integer> entry : quantityFields.entrySet()) {
            EditText qtyField = entry.getKey();
            int price = entry.getValue();
            int quantity = 0;

            try {
                quantity = Integer.parseInt(qtyField.getText().toString());
            } catch (NumberFormatException ignored) {}

            total += quantity * price;
        }
        totalPriceTextView.setText("Total: Rp. " + total);
    }

    private class QuantityTextWatcher implements TextWatcher {
        private final EditText editText;

        QuantityTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateTotalPrice();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
}