package com.pay.administrator.bgame.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountManagementActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_avatar)
    TextView tvAvatar;
    @BindView(R.id.ll_account_management)
    LinearLayout llAccountManagement;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.ll_nick_name)
    LinearLayout llNickName;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.ll_mobile)
    LinearLayout llMobile;
    @BindView(R.id.ll_change_psw)
    LinearLayout llChangePsw;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_account_management;
    }

    @Override
    protected void initView() {

    }



    @OnClick({R.id.iv_back, R.id.ll_account_management, R.id.ll_nick_name, R.id.ll_mobile, R.id.ll_change_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_account_management:
                break;
            case R.id.ll_nick_name:
                break;
            case R.id.ll_mobile:
                break;
            case R.id.ll_change_psw:
                break;
        }
    }
}
