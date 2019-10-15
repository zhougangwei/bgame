package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.UserInfoConfig;
import com.pay.administrator.bgame.bean.UserInfo;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AccountManagementActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView    ivBack;
    @BindView(R.id.iv_head)
    ImageView    ivHead;
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.tv_avatar)
    TextView     tvAvatar;
    @BindView(R.id.ll_account_management)
    LinearLayout llAccountManagement;
    @BindView(R.id.tv_nick_name)
    TextView     tvNickName;
    @BindView(R.id.ll_nick_name)
    LinearLayout llNickName;
    @BindView(R.id.tv_mobile)
    TextView     tvMobile;
    @BindView(R.id.ll_mobile)
    LinearLayout llMobile;
    @BindView(R.id.ll_change_psw)
    LinearLayout llChangePsw;


    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_account_management;
    }

    @Override
    protected void initView() {
        getUserInfo();
        tvTitle.setText("Account management");
    }

    @OnClick({R.id.tv_exit, R.id.iv_back, R.id.ll_account_management, R.id.ll_nick_name, R.id.ll_mobile, R.id.ll_change_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_exit:
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_account_management:
                break;
            case R.id.ll_nick_name:

                break;
            case R.id.ll_mobile:
                ResetUserinfoActivity.startChangeUserInfoActivity(this,ResetUserinfoActivity.CHANGE_MOBILE);
                break;
            case R.id.ll_change_psw:
                startActivity(new Intent(this, ActivityChangePswActivity.class));
                break;
        }
    }

    public void getUserInfo() {
        RetrofitFactory.getInstance().getuserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<UserInfo>() {
                    @Override
                    public void onGetData(UserInfo baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            UserInfo.DataBean data = baseBean.getData();
                            String name = data.getName();
                            tvAvatar.setText(name);
                            Glide.with(AccountManagementActivity.this).load(data.getIcon())
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(ivHead.getDrawable())).into(ivHead);
                            tvMobile.setText(data.getTelephone());
                        }
                    }
                });
    }
}
