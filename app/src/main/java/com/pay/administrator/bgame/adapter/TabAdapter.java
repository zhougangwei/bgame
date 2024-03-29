package com.pay.administrator.bgame.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.pay.administrator.bgame.fragment.HomeFragment;
import com.pay.administrator.bgame.fragment.MyFragment;
import com.pay.administrator.bgame.fragment.SearchFragment;

import java.util.ArrayList;



/**
 * Created by kiddo on 2017/11/28.
 */

public class TabAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;


    public TabAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new MyFragment());


    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }





}
