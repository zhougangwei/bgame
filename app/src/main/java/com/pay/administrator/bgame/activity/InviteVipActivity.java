package com.pay.administrator.bgame.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.UserInfo;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InviteVipActivity extends BaseActivity {


    @BindView(R.id.tv_invi_code)
    TextView tvInviCode;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vip_invite;
    }

    @Override
    protected void initView() {


        RetrofitFactory.getInstance().getuserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<UserInfo>() {
                    @Override
                    public void onGetData(UserInfo baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            UserInfo.DataBean data = baseBean.getData();
                            tvInviCode.setText(data.getInvCode());
                            int invitedNum = data.getInvitedNum();
                            //数目


                        }
                    }
                });

    }

}
