package com.pay.administrator.bgame.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.bean.LikeBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class LikeAdapter extends BaseQuickAdapter<LikeBean.DataBean, BaseViewHolder> {


    public LikeAdapter(int layoutIds, List<LikeBean.DataBean> datas) {
        super(layoutIds, datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, LikeBean.DataBean item) {
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
        if(item.isSelectEdit()){
            helper.setImageResource(R.id.iv_check,R.drawable.btn_checkbox1);
        }else{
            helper.setImageResource(R.id.iv_check,R.drawable.btn_checkbox0);
        }

        if(item.getEdit()){
            helper.setVisible(R.id.iv_check,true);
        }else{
            helper.setVisible(R.id.iv_check,false);
        }
        helper.addOnClickListener(R.id.iv_check);
        Glide.with(mContext).load(item.getLogo()).into((ImageView) helper.getView(R.id.iv));

    }
}
