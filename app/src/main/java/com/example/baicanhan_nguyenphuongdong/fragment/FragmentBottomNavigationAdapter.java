package com.example.baicanhan_nguyenphuongdong.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.baicanhan_nguyenphuongdong.MainActivity;

public class FragmentBottomNavigationAdapter extends FragmentStatePagerAdapter {
    private int numPage=3;
    public FragmentBottomNavigationAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new WomenProductFragment();
            case 1: return new MenProductFragment();
            case 2: return new KidProductFragment();
            default: return new WomenProductFragment();

        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
