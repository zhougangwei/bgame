package com.pay.administrator.bgame.base;

import android.os.Bundle;

import com.pay.administrator.bgame.rxbus.RxBus;
import com.pay.administrator.bgame.utils.ActivityManagerUtil;
import com.pay.administrator.bgame.utils.StatusbarUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends RxAppCompatActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity栈
        ActivityManagerUtil.getActivityManager().pushActivity2Stack(this);
        StatusbarUtils.setBlackTextBar(this);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract int getContentViewId();


    protected abstract void initView();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getIntanceBus().unSubscribe(this);
        ActivityManagerUtil.getActivityManager().popActivityStack(this);

    }





}
