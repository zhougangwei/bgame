package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.content.Intent;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

public class ResetUserinfoActivity extends BaseActivity {

    int type;
    public static int CHANGE_PSW=1;
    public static int CHANGE_HEAD=2;

    public  static void startChangeUserInfoActivity(Context context, int type){
        Intent intent = new Intent(context, ResetUserinfoActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reset_psw;
    }

    @Override
    protected void initView() {

    }
}
