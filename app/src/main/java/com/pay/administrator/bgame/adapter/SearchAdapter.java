package com.pay.administrator.bgame.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.VideoListBean;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class SearchAdapter extends BaseQuickAdapter<VideoListBean.DataBean, BaseViewHolder> {


    public SearchAdapter(int layoutIds, List<VideoListBean.DataBean> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoListBean.DataBean item) {
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

        helper.setText(R.id.tv_name,content);
        helper.setText(R.id.tv_num,item.getPlayCount()+"");
        ImageView ivBg = (ImageView) helper.getView(R.id.bg);
        Glide.with(mContext).load(item.getLogo())
                .apply(RequestOptions.placeholderOf(ivBg.getDrawable())).into(ivBg);

    }
}
