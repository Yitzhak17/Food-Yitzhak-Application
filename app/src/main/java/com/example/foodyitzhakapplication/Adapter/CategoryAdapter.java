package com.example.foodyitzhakapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodyitzhakapplication.Activies.ListFoodActivity;
import com.example.foodyitzhakapplication.Domain.Category;
import com.example.foodyitzhakapplication.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_catagory,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());

        switch (position){
            case 0:{
                holder.pic.setBackgroundResource(R.drawable.cat0_background);
                break;
            }
            case 1:{
                holder.pic.setBackgroundResource(R.drawable.cat1_background);
                break;
            }
            case 2:{
                holder.pic.setBackgroundResource(R.drawable.cat2_background);
                break;
            }
            case 3:{
                holder.pic.setBackgroundResource(R.drawable.cat3_background);
                break;
            }
            case 4:{
                holder.pic.setBackgroundResource(R.drawable.cat4_background);
                break;
            }
            case 5:{
                holder.pic.setBackgroundResource(R.drawable.cat5_background);
                break;
            }
            case 6:{
                holder.pic.setBackgroundResource(R.drawable.cat6_background);
                break;
            }
            case 7:{
                holder.pic.setBackgroundResource(R.drawable.cat7_background);
                break;
            }

        }
        @SuppressLint("DiscouragedApi") int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath()
                ,"drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent= new Intent(context, ListFoodActivity.class);
            intent.putExtra("CategoryId",items.get(position).getId());
            intent.putExtra("CategoryName",items.get(position).getName());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);

        }
    }
}
