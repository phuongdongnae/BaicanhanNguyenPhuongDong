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

import java.util.ArrayList;
import java.util.List;


public class WomenProductFragment extends Fragment {
    private RecyclerViewClothesAdapter adapter;
    private RecyclerView rev;
    public WomenProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rev = view.findViewById(R.id.revWomen);
        adapter = new RecyclerViewClothesAdapter((MainActivity) getActivity());
        adapter.setData(getAllClothes());
        rev.setAdapter(adapter);
        rev.setLayoutManager(new GridLayoutManager(view.getContext(), 2) );
    }

    private List<Clothes> getAllClothes() {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes(R.drawable.damsuong, "Đầm suông cổ đức", "258.000", "Đầm suông cổ đức 20AGAIN DXA1221 được thiết kế thích hợp cho mọi vóc dáng. Đầm Suông DXA1233 có thiết kế phần trên váy ôm eo, phần dưới xòe vừa phải tạo hình dáng giống với chữ A. "));
        list.add(new Clothes(R.drawable.bongnoco, "Đầm ngắn tay bồng", "392.500", "Chất liệu : 100% cotton\nHình in không bong tróc khi giặt\nSản phẩm được thiết kế độc quyền bởi thương hiệu 20AGAIN -Thương hiệu Thời trang thiết kế đa phong cách cho phái đẹp với hệ thống hơn 30 showrooms trên toàn quốc từ Bắc vào Nam."));
        list.add(new Clothes(R.drawable.covuong, "Đầm cổ vuông tay lỡ cổ tim", "322.000", "Đầm cổ vuông tay lỡ cổ tim ôm body Chất liệu: Tuýt Si. Đây là chất vải có độ bền cao; bề mặt vải mềm, mịn, ít co giãn nên thoải mái khi mặc; định hình Form dáng tốt, ít bị nhăn. Cách bảo quản: Khi giặt: nên dùng loại xà phòng trung tính, hạn chế sử dụng chất tẩy rửa...."));
        list.add(new Clothes(R.drawable.vuongtaylo, "Đầm cổ vuông tay lỡ cổ tim ôm body", "322.000", "Đầm cổ vuông tay lỡ cổ tim ôm body Chất liệu: Tuýt Si. Đây là chất vải có độ bền cao; bề mặt vải mềm, mịn, ít co giãn nên thoải mái khi mặc; định hình Form dáng tốt, ít bị nhăn. Cách bảo quản: Khi giặt: nên dùng loại xà phòng trung tính, hạn chế sử dụng chất tẩy rửa...."));
        list.add(new Clothes(R.drawable.xoeno, "Đầm xòe nơ ngực trễ vai", "342.500", "Đầm xòe nơ ngực trễ vai\nChất liệu: Lụa tafta mềm . loại vải cao cấp, có bề mặt đẹp, mịn và những đặc tính riêng biệt như : sắc nét, độ sáng bóng, trơn nhẵn, lên form chuẩn đẹp, phù hợp với các thiết kế đầm tiệc,.."));
        list.add(new Clothes(R.drawable.daytrevai, "Đầm dây trễ vai vạt áo chồng", "376.500", "Đầm dây trễ vai vạt chồng\nChất liệu: tuýt si mềm\n- Màu sắc: Đen/Trắng/Đỏ\nĐầm xòe nơ ngực trễ vai 20AGAIN DEA1315 được thiết kế ôm sát vòng eo tôn nét đẹp cho người mặc."));
        return list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_women_product, container, false);
    }
}