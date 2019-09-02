package com.pay.administrator.bgame.activity;

import android.content.Intent;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SpalshActivity extends BaseActivity{



    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                    }
                });
    }
}
