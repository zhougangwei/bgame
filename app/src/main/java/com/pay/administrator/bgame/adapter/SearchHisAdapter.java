package com.pay.administrator.bgame.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;


import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class SearchHisAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchHisAdapter(int layoutIds, List<String> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content,item);
    }
}
