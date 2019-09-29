package com.pay.administrator.bgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginSmsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView  mTvTitle;
    @BindView(R.id.tv_choose_country)
    TextView  mTvChooseCountry;
    @BindView(R.id.et_phonenum)
    EditText  mEtPhoneNum;
    @BindView(R.id.tv_submit)
    TextView  mTvSubmit;
    @BindView(R.id.tv_forget)
    TextView  mTvForget;
    private String phoneNum;

    private String country;
    private String country_code;
    private String telephone;
    private String msg_code;
    private String mCountryCode;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_sms;
    }

    @Override
    protected void initView() {
        mTvTitle.setText("Please enter Mobile number");
        mTvSubmit.setText("Send the verification code   ");

    }


    @OnClick({R.id.iv_back, R.id.tv_submit,  R.id.tv_choose_country})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                startSendSms();
                break;
            case R.id.tv_choose_country:
                chooseCountry();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode== Contact.REQUEST_CHOOSE_COUNTRY) {
                mCountryCode = data.getStringExtra("countryCode");
                country = data.getStringExtra("countryName");
                mTvChooseCountry.setText(country+"/"+mCountryCode);
            }
        }
    }


    private void chooseCountry() {
        startActivityForResult(new Intent(this, ChooseCountryActivity.class),Contact.REQUEST_CHOOSE_COUNTRY);
    }


    private void startSendSms() {
        Intent intent = new Intent(this, EnterSmsActivity.class);
        intent.putExtra("country", country);
        intent.putExtra("country_code", country_code);
        intent.putExtra("telephone", telephone);
        startActivity(intent);


    }


}
