package com.humber.parenthood.eat_in_layout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.humber.parenthood.R;

import java.util.ArrayList;

public class IngredientPicker extends Fragment {
    private ItemAdaptor adapter;
    private ArrayList<ItemModel> modelArrayList;
    private RecyclerView recyclerView;

    private ArrayList<String> fridgeItems = KitchenItems.getItems();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredient_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        modelArrayList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.itemsRV);
        setItems(modelArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager (view.getContext(), 4));
        adapter = new ItemAdaptor(modelArrayList, view.getContext());

        // Set the listener on the adapter
        adapter.setItemClickListener(itemClickListener);

        recyclerView.setAdapter(adapter);
    }

    private void setItems(ArrayList<ItemModel> modelArrayList) {
        for (int i = 0; i < fridgeItems.size(); i++) {
            modelArrayList.add(new ItemModel(fridgeItems.get(i)));
        }
    }

    private final ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onClick(ItemModel items) {
            Log.d("@Harman", "onClick: item select");
        }

        @Override
        public void onLongClick(ItemModel items) {
            Log.d("@Harman", "onLongClick: item fav");
            int position = modelArrayList.indexOf(items);
            if (position != RecyclerView.NO_POSITION) {
                ItemModel item = modelArrayList.get(position);
                item.setFavourite(!item.getFavourite());
                adapter.notifyItemChanged(position);
            }
        }
    };

}