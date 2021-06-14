package com.example.baicanhan_nguyenphuongdong;

import android.content.Context;
import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baicanhan_nguyenphuongdong.fragment.RecyclerViewClothesAdapter;
import com.example.baicanhan_nguyenphuongdong.model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ProductViewHolder>{
    private List<Product> mlist;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mlist = new ArrayList<>();
    }
    public void setData(List<Product> mlist){
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ProductViewHolder holder, int position) {
        Product product = mlist.get(position);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
            byte[] image = product.getPhoto();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.img.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.nameid.setText(product.getName()+" - "+product.getId());
        holder.price.setText(product.getPrice() +" ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
                    field.setAccessible(true);
                    field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
                    Intent intent = new Intent(mContext, UpdateDeleteProductActivity.class);
                    Product product1 = mlist.get(position);
                    intent.putExtra("products", product1);
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Kích thước ảnh quá lớn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nameid, price;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            nameid = itemView.findViewById(R.id.nameid);
            price = itemView.findViewById(R.id.price);
        }
    }
}
