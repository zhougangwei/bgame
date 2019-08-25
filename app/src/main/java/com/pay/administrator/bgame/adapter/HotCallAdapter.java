package com.pay.administrator.bgame.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class HotCallAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public HotCallAdapter(int layoutIds, List<String> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
      
    }
}
