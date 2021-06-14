package com.example.baicanhan_nguyenphuongdong.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.baicanhan_nguyenphuongdong.MainActivity;
import com.example.baicanhan_nguyenphuongdong.R;
import com.example.baicanhan_nguyenphuongdong.model.Clothes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MenProductFragment extends Fragment {
    private RecyclerViewClothesAdapter adapter;
    private RecyclerView rev;
    public MenProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rev = view.findViewById(R.id.revMen);
        adapter = new RecyclerViewClothesAdapter((MainActivity) getActivity());
        adapter.setData(getAllClothes());
        rev.setAdapter(adapter);
        rev.setLayoutManager(new GridLayoutManager(view.getContext(), 3) );
    }

    private List<Clothes> getAllClothes() {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes(R.drawable.cracken, "GRADIENT", "420.000", "Kỹ thuật “WASH” đặc biệt cùng phương pháp chuyển màu có 1-0-2\n[GRADIENT WASHED TEE]"));
        list.add(new Clothes(R.drawable.fire, "FIRE", "350.000", "Chất liệu : 100% cotton\nHình in không bong tróc khi giặt máy"));
        list.add(new Clothes(R.drawable.tobaco, "TOBACO", "290.000", "Chất liệu: 100% cotton\nMàu sắc: Cream\nKích thước tee:\nM: 76x57\nL: 78x59\nXL: 80x61"));
        list.add(new Clothes(R.drawable.green, "GREEN", "320.000", "Chất liệu: 100% cotton\n"+
                "Màu sắc: Xanh\nSỐ lượng còn: 04"));
        list.add(new Clothes(R.drawable.thaww, "THAW", "150.000", "Chất liệu cotton 100% cao cấp\n"+
                "Hình in sắc nét, đẹp, bao giặt máy thoải mái\n"+
                "Full tem tag chính hãng\n" +
                "Giá rẻ hợp lý"));
        list.add(new Clothes(R.drawable.happy, "HAPPY", "200.000", "Chất liệu: 100% cotton\nMàu sắc: Cream"));
        return list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_men_product, container, false);
    }
}