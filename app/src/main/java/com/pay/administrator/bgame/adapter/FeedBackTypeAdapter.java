package com.pay.administrator.bgame.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.bean.FeedBackTypeBean;

import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class FeedBackTypeAdapter extends BaseQuickAdapter<FeedBackTypeBean, BaseViewHolder> {

    public FeedBackTypeAdapter(int layoutIds, List<FeedBackTypeBean> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedBackTypeBean item) {
        helper.setText(R.id.tv_content,item.getContent());
        if(item.isCheck()){
            helper.setTextColor(R.id.tv_content, Color.BLACK);
            helper.setBackgroundRes(R.id.tv_content,R.drawable.bg_rec_feedback_type_choose);
        }else{
            helper.setTextColor(R.id.tv_content, Color.parseColor("#888888"));
            helper.setBackgroundRes(R.id.tv_content,R.drawable.bg_rec_feedback_type);
        }
    }
}
