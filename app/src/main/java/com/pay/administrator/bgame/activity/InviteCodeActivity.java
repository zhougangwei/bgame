package com.pay.administrator.bgame.activity;

import android.content.Intent;

import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.R;
/**
 * @ 创建者   zhou
 * @ 创建时间   2019/10/17 0017 20:44
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2019/10/17 0017$
 * @ 更新描述  ${TODO}
 */
public class InviteCodeActivity extends BaseActivity
{
    private String country;
    private String country_code;
    private String telephone;
    private String recommender_id;
    private String msg_code;
    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_invite_code;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        country= intent.getStringExtra("country");
        country_code= intent.getStringExtra("country_code");
        telephone= intent.getStringExtra("telephone");
        recommender_id= intent.getStringExtra("recommender_id");
        msg_code= intent.getStringExtra("msg_code");
    }
}
