package com.example.demofocuslast.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.demofocuslast.fragment.PlantsFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<PlantsFragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(PlantsFragment plantsFragment){
        this.fragmentArrayList.add(plantsFragment);
    }

    public void updateUi(int position){
        for (int i = 0;i<fragmentArrayList.size();i++){
            if (i != position){
                fragmentArrayList.get(i).updateUi();
            }
        }
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

}
