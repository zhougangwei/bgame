package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.utils.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.iv_facevook)
    ImageView ivFacevook;
    @BindView(R.id.iv_google)
    ImageView ivGoogle;
    @BindView(R.id.tv_rule)
    TextView tvRule;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_phone, R.id.iv_facevook, R.id.iv_google, R.id.tv_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_phone:

                startActivity(new Intent(this,LoginMobileActivity.class));
                break;
            case R.id.iv_facevook:
                break;
            case R.id.iv_google:
                break;
            case R.id.tv_rule:
                break;
        }
    }
}
