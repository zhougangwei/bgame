package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.NoticeBean;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wjy on 2019/9/23/023.
 */
public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        NoticeBean.DataBean.ListBean item = (NoticeBean.DataBean.ListBean) intent.getSerializableExtra("noticeItem");
        tvTitle.setText("Notice details");
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
        tvContent.setText(content);
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
        tvTitle.setText(title);
        tvTime.setText(TimeUtils.date2String(TimeUtils.string2Date("2019-09-23 20:01:00"),new SimpleDateFormat("yyyy-MM-dd")));
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
