package com.pay.administrator.bgame.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.bean.TagBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class SearchAdapter extends BaseQuickAdapter<TagBean.DataBean, BaseViewHolder> {


    public SearchAdapter(int layoutIds, List<TagBean.DataBean> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagBean.DataBean item) {
        List<TagBean.DataBean.MovieBean> bestMovie = item.getBestMovie();
        List<TagBean.DataBean.MovieBean> recMovie = item.getRecMovie();
        List<TagBean.DataBean.MovieBean> data=new ArrayList<>();
        if (bestMovie!=null) {
            data.addAll(bestMovie);
        }
        if (recMovie!=null) {
            data.addAll(recMovie);
        }
        RecyclerView rv = helper.getView(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        rv.setLayoutManager(gridLayoutManager);
        TagItemVideoAdapter tagItemVideoAdapter = new TagItemVideoAdapter(R.layout.item_home_video, data);
        rv.setAdapter(tagItemVideoAdapter);
    }
}
