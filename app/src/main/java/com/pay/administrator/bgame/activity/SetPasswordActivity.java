package com.pay.administrator.bgame.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.UserRegisterBean;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.GsonUtil;
import com.pay.administrator.bgame.utils.ResultUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SetPasswordActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_pwd)
    EditText  mEtPwd;
    @BindView(R.id.tv_submit)
    TextView  mTvSubmit;
    @BindView(R.id.tv_rule)
    TextView  mTvRule;
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
        return R.layout.activity_set_password;
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

    @SuppressLint("CheckResult")
    public void startSurePassword() {
        String psw = mEtPwd.getText().toString();

        UserRegisterBean userRegisterBean = new UserRegisterBean();
        userRegisterBean.setCountry(country);
        userRegisterBean.setCountry_code(country_code);
        userRegisterBean.setTelephone(telephone);
        userRegisterBean.setPassword(psw);
        userRegisterBean.setRecommender_id(recommender_id);
        userRegisterBean.setRegister_device(Build.MODEL);
        userRegisterBean.setMsg_code(msg_code);



        RetrofitFactory.getInstance().userRegister(ProxyPostHttpRequest.getJsonInstance().userRegister(
                GsonUtil.parseObjectToJson(userRegisterBean)
        )).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (ResultUtils.cheekSuccess(baseBean)) {

                        }

                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
