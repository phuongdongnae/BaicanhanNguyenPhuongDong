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


public class KidProductFragment extends Fragment {
    private RecyclerViewClothesAdapter adapter;
    private RecyclerView rev;
    public KidProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rev = view.findViewById(R.id.revKid);
        adapter = new RecyclerViewClothesAdapter((MainActivity) getActivity());
        adapter.setData(getAllClothes());
        rev.setAdapter(adapter);
        rev.setLayoutManager(new GridLayoutManager(view.getContext(), 2) );
    }

    private List<Clothes> getAllClothes() {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes(R.drawable.kid1, "ÁO PHÔNG TRẺ EM UNISEX HÌNH IN MICKEY", "169.000", "HÌNH IN BẢN QUYỀN DISNEY GỒM 2 PHIÊN BẢN\nĐây là phiên bản lấy cảm hứng từ bức tranh Đông Hồ nổi tiếng “Mục đồng thổi sáo”\nBức tranh thể hiện tinh thần vui tươi lạc quan, gần gũi với thiên nhiên và con người Việt Nam."));
        list.add(new Clothes(R.drawable.kid2, "ÁO POLO BÉ TRAI COTTON USA IN HÌNH", "179.000", "-Áo polo cotton USA in kẻ và hình\n-Dáng regular, cổ bẻ, tay cộc."));
        list.add(new Clothes(R.drawable.kid5, "ÁO PHÔNG BÉ TRAI MICKEY", "150.000", "-Chất liệu cotton 100% cao cấp\n"+
                "-Hình in sắc nét, đẹp, bao giặt máy thoải mái\n"+
                "-Full tem tag chính hãng\n" +
                "-Giá rẻ hợp lý"));
        list.add(new Clothes(R.drawable.kid6, "QUẦN JEAN BÉ TRAI COTTON USA CẠP CHUN", "249.000", "-Quần jean chất liệu cotton USA\n" +
                "-Dáng regular, túi chéo, cạp chun luồn dây dệt."));
        list.add(new Clothes(R.drawable.kid3, "ÁO POLO BÉ TRAI KẺ NGANG", "229.000", "-Áo polo chất liệu cotton pha hoạ tiết kẻ\n"+"-Dáng regular, cổ bẻ, tay cộc."));
        list.add(new Clothes(R.drawable.kid4, "ÁO SƠ MI BÉ TRAI COTTON USA DÀI TAY", "199.000", "-Áo sơ mi chất liệu cotton USA\n" + "-Dáng regular, cổ đức, tay dài."));
        return list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kid_product, container, false);
    }
}