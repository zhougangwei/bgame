package com.pay.administrator.bgame.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class TagItemVideoAdapter extends BaseQuickAdapter<TagBean.DataBean.MovieBean, BaseViewHolder> {


    public TagItemVideoAdapter(int layoutIds, List<TagBean.DataBean.MovieBean> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagBean.DataBean.MovieBean item) {
        helper.setText(R.id.tv_num,item.getPlayCount()+"");
        String content="";
        switch (ToolUtils.getLanguage()) {
            case Contact.LANGUAGE_CHINA:
                content=item.getTitleZh();
                break;
            case Contact.LANGUAGE_ENGLISH:
                content=item.getTitleEn();
                break;
            case Contact.LANGUAGE_AR:
                content=item.getTitleAl();
                break;
             default:
                 content=item.getTitleEn();
                 break;
        }
        helper.setText(R.id.tv_content,content);
        Glide.with(mContext).load(item.getLogo()).into((ImageView) helper.getView(R.id.iv));

    }
}
