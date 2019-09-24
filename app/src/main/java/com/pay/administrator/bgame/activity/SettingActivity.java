package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.FileUtils;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_account_management)
    LinearLayout llAccountManagement;
    @BindView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @BindView(R.id.ll_version)
    LinearLayout llVersion;
    @BindView(R.id.ll_user_agreement)
    LinearLayout llUserAgreement;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_back, R.id.ll_account_management, R.id.ll_clear_cache, R.id.ll_version, R.id.ll_user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_account_management:
                startActivity(new Intent(this,AccountManagementActivity.class));
                break;
            case R.id.ll_clear_cache:
                break;
            case R.id.ll_version:
                break;
            case R.id.ll_user_agreement:
                break;
        }
    }
}
