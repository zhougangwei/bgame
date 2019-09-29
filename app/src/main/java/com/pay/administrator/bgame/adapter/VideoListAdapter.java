package com.pay.administrator.bgame.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.fragment.FeedbackFragment;
import com.pay.administrator.bgame.fragment.FeedbackListFragment;
import com.pay.administrator.bgame.fragment.VideolistFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kiddo on 2017/11/28.
 */

public class VideoListAdapter extends FragmentPagerAdapter{

    private VideolistFragment currentFragment;
    private List<TagBean.DataBean> tagList =new ArrayList<>();


    public VideoListAdapter(FragmentManager fm, List<TagBean.DataBean> tagList) {
        super(fm);
        this.tagList = tagList;
    }

    @Override
    public Fragment getItem(int position) {
        VideolistFragment videolistFragment = new VideolistFragment();
        return videolistFragment;
    }

    @Override
    public int getCount() {
        return tagList.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((VideolistFragment) object);
        }
        if (tagList.size()!=0){
            currentFragment.setData(tagList.get(position).getId());
        }
        super.setPrimaryItem(container, position, object);
    }




    public Fragment getCurrentFragment() {
        return currentFragment;
    }





}
