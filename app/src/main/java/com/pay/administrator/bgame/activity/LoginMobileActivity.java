package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.http.RetrofitFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginMobileActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_mobile_num)
    EditText etMobileNum;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_rule)
    TextView tvRule;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_mobile;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.iv_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                gotoSubmit();

                break;
        }
    }

    private void gotoSubmit() {

        final String psw = etPwd.getText().toString();
        final String mobileNumber = "+86"+etMobileNum.getText().toString();
        RetrofitFactory.getInstance()
                .loginPhone()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
               /* .subscribe(new BaseCosumer<LoginBean>() {
                    @Override
                    public void onGetData(LoginBean baseBean) {
                        if (Contact.REPONSE_CODE_SUCCESS != baseBean.getCode()) {
                            return;
                        }
                        String action = baseBean.getData().getAction();
                        if (Contact.SIGN_UP.equals(action)) {
                            Intent intent = new Intent(LoginPhoneActivity.this, FillUserActivity.class);
                            intent.putExtra("mobileNumber", mobileNumber);
                            intent.putExtra("code", code);
                            intent.putExtra("type", FillUserActivity.FROM_PHONE);
                            startActivityForResult(intent,REQUEST_PHONE);
                        } else if (Contact.SIGN_IN.equals(action)) {
                            AvchatInfo.saveBaseData(baseBean.getData().getMember(),LoginPhoneActivity.this,true);
                            Intent intent = new Intent(LoginPhoneActivity.this,MainActivity.class);
                            startActivity(intent);
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });*/
    }
}
