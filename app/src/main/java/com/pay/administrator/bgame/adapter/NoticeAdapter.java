package com.pay.administrator.bgame.adapter;

import android.widget.ImageView;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.NoticeBean;
import com.pay.administrator.bgame.bean.VideoListBean;
import com.pay.administrator.bgame.utils.TimeUtil;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeBean.DataBean.ListBean, BaseViewHolder> {


    public NoticeAdapter(int layoutIds, List<NoticeBean.DataBean.ListBean> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean.DataBean.ListBean item) {
        String content="";
        switch (ToolUtils.getLanguage()) {
            case Contact.LANGUAGE_CHINA:
                content=item.getContent();
                break;
            case Contact.LANGUAGE_ENGLISH:
                content=item.getContentEn();
                break;
            case Contact.LANGUAGE_AR:
                content=item.getContentAl();
                break;
            default:
                content=item.getTitleEn();
                break;
        }

        String title="";
        switch (ToolUtils.getLanguage()) {
            case Contact.LANGUAGE_CHINA:
                title=item.getTitle();
                break;
            case Contact.LANGUAGE_ENGLISH:
                title=item.getTitleEn();
                break;
            case Contact.LANGUAGE_AR:
                title=item.getTitleAl();
                break;
            default:
                title=item.getTitleEn();
                break;
        }
        helper.setText(R.id.tv_title,title);
        helper.setText(R.id.tv_content,content);
        helper.setText(R.id.tv_time, TimeUtils.date2String(TimeUtils.string2Date("2019-09-23 20:01:00"),new SimpleDateFormat("yyyy-MM-dd")));
    }
}
