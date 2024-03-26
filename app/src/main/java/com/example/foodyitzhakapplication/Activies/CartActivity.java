package com.example.foodyitzhakapplication.Activies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyitzhakapplication.Adapter.CartAdapter;
import com.example.foodyitzhakapplication.Helper.ChangeNumberItemsListener;
import com.example.foodyitzhakapplication.Helper.ManagmentCart;
import com.example.foodyitzhakapplication.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);


        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.cartView.setLayoutManager(linearLayoutManager);
        RecyclerView.Adapter<CartAdapter.ViewHolder> adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        binding.cartView.setAdapter(adapter);
    }
    @SuppressLint("SetTextI18n")
    private void calculateCart() {
        double percentTax = 0.02 ; //  percent 2% tax
        double delivery = 10 ;//10 Dollar

        double tax = (double) Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;

        double total =  (double) Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) /100;
        double itemTotal =  (double) Math.round(managmentCart.getTotalFee() * 100) /100;

        binding.totalFeeTxt.setText("$" +itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" +delivery);
        binding.totalTxt.setText("$" +total);

    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> {
            Log.d("CartActivity", "binding.backBtn: " + binding.backBtn);
            finish();
        });
    }


/*

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }

*/
}