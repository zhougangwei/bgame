package com.pay.administrator.bgame.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.pay.GooglePayUi;

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

    public static final int PAY_MONTH = 1;
    public static final int PAY_FOREVER = 2;
    private GooglePayUi googlePayUi;


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
        googlePayUi = new GooglePayUi();
        googlePayUi.init(this);
    }


    @OnClick({R.id.iv_back, R.id.tv_month, R.id.tv_forever})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_month:
                gotoCharge(PAY_MONTH);
                break;
            case R.id.tv_forever:
                gotoCharge(PAY_FOREVER);
                break;
        }
    }

    private void gotoCharge(int type) {
        switch (type) {
            case PAY_MONTH:
                googlePayUi.charge(GooglePayUi.NAMOL_199);
                break;
            case PAY_FOREVER:
                googlePayUi.charge(GooglePayUi.NAMOL_1999);
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        googlePayUi.onDestroy();
    }

}
