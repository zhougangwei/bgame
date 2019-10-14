package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.UserInfo;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ResetUserinfoActivity extends BaseActivity {

    int type;
    public static int CHANGE_PSW  = 1;
    public static int CHANGE_HEAD = 2;
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv_select_contry)
    TextView     mTvSelectContry;
    @BindView(R.id.ll1)
    LinearLayout mLl1;
    @BindView(R.id.tv_get_vcode)
    TextView     mTvGetVcode;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.et_mobile)
    EditText     mEtMobile;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.et_code)
    EditText etCode;

    public static void startChangeUserInfoActivity(Context context, int type) {
        Intent intent = new Intent(context, ResetUserinfoActivity.class);
        intent.putExtra("type", type);
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
        submit.setText("Confirm");
    }


    @OnClick({R.id.submit,R.id.iv_back, R.id.tv_get_vcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                gotoChange(type);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_vcode:
                getCode();
                break;
        }
    }

    private void gotoChange(int type) {
        String code = etCode.getText().toString();
        String telephone=mEtMobile.getText().toString();
        RetrofitFactory.getInstance().updateTel(ProxyPostHttpRequest.getInstance().updateTel(telephone,code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean baseCosumer) {
                        if (ResultUtils.cheekSuccess(baseCosumer)) {
                            ToastUtils.showToast(ResetUserinfoActivity.this,"Send Success!");
                        }
                    }
                });

    }

    private void getCode() {
        RetrofitFactory.getInstance().getuserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<UserInfo>() {
                    @Override
                    public void onGetData(UserInfo baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            UserInfo.DataBean data = baseBean.getData();
                            String telephone = data.getTelephone();
                            if(TextUtils.isEmpty(telephone)){
                                telephone=mEtMobile.getText().toString();
                            }
                            RetrofitFactory.getInstance().sendMsgCode(telephone)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new BaseCosumer<BaseBean>() {
                                        @Override
                                        public void onGetData(BaseBean tagbean) {
                                            if (ResultUtils.cheekSuccess(tagbean)) {
                                                ToastUtils.showToast(ResetUserinfoActivity.this,"Send Success!");
                                            }
                                        }
                                    });
                        }
                    }
                });
    }




}
