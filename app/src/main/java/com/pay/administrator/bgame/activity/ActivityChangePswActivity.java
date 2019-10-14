package com.pay.administrator.bgame.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActivityChangePswActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.et_old)
    EditText     mEtOld;
    @BindView(R.id.ll1)
    LinearLayout mLl1;
    @BindView(R.id.et_new_sure)
    EditText     mEtNewSure;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.et_new)
    EditText     mEtNew;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.submit)
    TextView submit;
    private String code;
    @Override
    protected void initData() {
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_psw;
    }

    @Override
    protected void initView() {
        submit.setText("Confirm");
        mTvTitle.setText("Change Password");
         code = getIntent().getStringExtra("code");
    }

    @OnClick({R.id.iv_back, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.submit:
                gotoChange();
                break;
        }
    }

    private void gotoChange() {
        String oldpsw = mEtOld.getText().toString();
        String newpsw = mEtNew.getText().toString();
        String newpswsure = mEtNewSure.getText().toString();
        if(android.text.TextUtils.isEmpty(oldpsw)||android.text.TextUtils.isEmpty(newpsw)||android.text.TextUtils.isEmpty(newpswsure)){
            ToastUtils.showToast(this,"Password can't be null!");
            return;
        }
        if(newpsw.equals(newpswsure)){
            ToastUtils.showToast(this,"Please enter the same new passwords!");
            return;
        }
        RetrofitFactory.getInstance().resetPwd(ProxyPostHttpRequest.getInstance().resetPwd(oldpsw,newpsw, code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {
                        if (ResultUtils.cheekSuccess(tagbean)) {
                        }
                    }
                });
    }
}