package com.example.moneyapp.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.moneyapp.ui.frament.RevenueFragment;
import com.example.moneyapp.ui.frament.RevenueSourceFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RevenueFragment();
            case 1:
                return new RevenueSourceFragment();
            default:
                return new RevenueFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Revenue";
                break;
            case 1:
                title = "RevenueSource";
                break;
        }
        return title;
    }
}
