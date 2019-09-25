package com.pay.administrator.bgame.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.http.RetrofitFactory;

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
        tvTitle.setText("Notice details");
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
