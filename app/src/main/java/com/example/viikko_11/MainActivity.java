package com.example.viikko_11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;
    private List<ShoppingItem> shoppingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingItems = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingListAdapter(shoppingItems, new ShoppingListAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(ShoppingItem item, int position) {
                showEditDialog(item, position);
            }

            @Override
            public void onDeleteClick(int position) {
                shoppingItems.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton addItemFab = findViewById(R.id.add_item_fab);
        addItemFab.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_item, null);
        EditText itemNameEditText = view.findViewById(R.id.item_name_edit_text);
        builder.setView(view);
        builder.setTitle("Add Item");
        builder.setPositiveButton("Add", (dialog, which) -> {
            String itemName = itemNameEditText.getText().toString();
            shoppingItems.add(new ShoppingItem(itemName));
            adapter.notifyItemInserted(shoppingItems.size() - 1);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    private void showEditDialog(ShoppingItem item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_item, null);
        EditText itemNameEditText = view.findViewById(R.id.item_name_edit_text);
        itemNameEditText.setText(item.getName());
        builder.setView(view);
        builder.setTitle("Edit Item");
        builder.setPositiveButton("Save", (dialog, which) -> {
            String itemName = itemNameEditText.getText().toString();
            item.setName(itemName);
            adapter.notifyItemChanged(position);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}
