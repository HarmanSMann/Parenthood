package com.humber.parenthood.eat_in_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.humber.parenthood.R;

import java.util.ArrayList;

public class ItemAdaptor extends RecyclerView.Adapter <ItemAdaptor.ViewHolder> {
    private ArrayList<ItemModel> modelArrayList;
    private ItemClickListener itemClickListener;

    public ItemAdaptor(ArrayList<ItemModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // view instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdaptor.ViewHolder holder, int position) {
        ItemModel model = modelArrayList.get(position);
        holder.name.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener != null) {
                    itemClickListener.onClick(model);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(itemClickListener != null) {
                    itemClickListener.onLongClick(model);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
        }
    }

}

