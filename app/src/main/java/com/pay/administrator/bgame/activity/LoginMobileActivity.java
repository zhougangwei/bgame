package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.UserInfoConfig;
import com.pay.administrator.bgame.bean.LoginBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.SPUtil;
import com.pay.administrator.bgame.utils.ToastUtils;

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

    @BindView(R.id.tv_edit)
    TextView tvEdit;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_mobile;
    }

    @Override
    protected void initView() {
        tvSubmit.setText("Login");
        tvTitle.setText("Login");
        tvEdit.setText("register");
    }


    @OnClick({R.id.iv_back, R.id.tv_submit,R.id.tv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                gotoSubmit();
                break;
            case R.id.tv_edit:
                startActivity(new Intent(this,LoginSmsActivity.class));
                break;
        }
    }

    private void gotoSubmit() {
        final String psw = etPwd.getText().toString();
        final String mobileNumber = etMobileNum.getText().toString();
        RetrofitFactory.getInstance()
                .loginPhone(ProxyPostHttpRequest.getInstance().loginPhone(mobileNumber,psw))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new BaseCosumer<LoginBean>() {
                    @Override
                    public void onGetData(LoginBean baseBean) {
                        if (!ResultUtils.cheekSuccess(baseBean)) {
                            return;
                        }
                        UserInfoConfig.setUserId(baseBean.getUserid());
                        SPUtil.getInstance().setToken(baseBean.getData());
                        ToastUtils.showToast(LoginMobileActivity.this,"success!");
                        startActivity(new Intent(LoginMobileActivity.this,MainActivity.class));
                    }
                });
    }
}
