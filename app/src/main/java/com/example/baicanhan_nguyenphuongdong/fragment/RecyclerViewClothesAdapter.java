package com.example.baicanhan_nguyenphuongdong.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baicanhan_nguyenphuongdong.MainActivity;
import com.example.baicanhan_nguyenphuongdong.R;
import com.example.baicanhan_nguyenphuongdong.model.Clothes;

import java.util.List;

public class RecyclerViewClothesAdapter extends RecyclerView.Adapter<RecyclerViewClothesAdapter.ClothesViewHolder>{
    private List<Clothes> mlist;
    private MainActivity mainActivity;

    public RecyclerViewClothesAdapter(MainActivity m) {
        mainActivity = m;
    }
    public RecyclerViewClothesAdapter() {
    }

    public void setData(List<Clothes> list){
        this.mlist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ClothesViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_clothes, parent, false);
        return new ClothesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewClothesAdapter.ClothesViewHolder holder, int position) {
        Clothes clothes = mlist.get(position);
        holder.photo.setImageResource(clothes.getPhoto());
        holder.name.setText(clothes.getName());
        holder.price.setText(clothes.getPrice());
        holder.des.setText(clothes.getDes());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, des;
        private ImageView photo;
        public ClothesViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imagecl);
            name = itemView.findViewById(R.id.namecl);
            price = itemView.findViewById(R.id.pricecl);
            des = itemView.findViewById(R.id.descriptioncl);
        }
    }
}
