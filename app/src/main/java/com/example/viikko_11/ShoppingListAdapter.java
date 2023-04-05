package com.example.viikko_11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<ShoppingItem> shoppingItems;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onEditClick(ShoppingItem item, int position);
        void onDeleteClick(int position);
    }

    public ShoppingListAdapter(List<ShoppingItem> shoppingItems, OnItemClickListener itemClickListener) {
        this.shoppingItems = shoppingItems;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingItem item = shoppingItems.get(position);
        holder.itemName.setText(item.getName());
        holder.editButton.setOnClickListener(v -> itemClickListener.onEditClick(item, position));
        holder.deleteButton.setOnClickListener(v -> itemClickListener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageButton editButton;
        ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            editButton = itemView.findViewById(R.id.edit_item);
            deleteButton = itemView.findViewById(R.id.delete_item);
        }
    }
}

