package com.example.baicanhan_nguyenphuongdong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.baicanhan_nguyenphuongdong.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AllProductActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView rev;
    private RecyclerViewAdapter adapter;
    private SQLiteProductHelper sqLite;
    private List<Product> productList;
    private List<Product> searchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        initView();
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
                e.printStackTrace();
        }
        rev.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        productList = sqLite.getAll();
        adapter.setData(productList);
        rev.setLayoutManager(new GridLayoutManager(this, 2));
        rev.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllProductActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = sqLite.search(newText);
                adapter.setData(searchList);
                rev.setAdapter(adapter);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        fab = findViewById(R.id.fab);
        rev = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(AllProductActivity.this);
        sqLite = new SQLiteProductHelper(this);
        productList = new ArrayList<>();
        searchList = new ArrayList<>();
    }
}