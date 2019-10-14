package com.pay.administrator.bgame.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.tv_forever)
    TextView tvForever;
    @BindView(R.id.textView15)
    TextView textView15;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void initView() {
        tvTitle.setText("Open VIP");
    }


    @OnClick({R.id.iv_back, R.id.tv_month, R.id.tv_forever})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_month:

                break;
            case R.id.tv_forever:
                break;
        }
    }
}
