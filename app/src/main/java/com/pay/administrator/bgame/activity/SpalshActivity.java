package com.pay.administrator.bgame.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.SPUtil;

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
        return R.layout.activity_spalsh;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {



        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if(TextUtils.isEmpty(SPUtil.getInstance().getToken())){
                            startActivity(new Intent(SpalshActivity.this,LoginActivity.class));
                        }else {
                            startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                        }

                    }
                });
    }
}
