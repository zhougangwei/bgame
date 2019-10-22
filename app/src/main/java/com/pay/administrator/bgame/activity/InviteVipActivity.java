package com.pay.administrator.bgame.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.UserInfo;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InviteVipActivity extends BaseActivity {


    @BindView(R.id.tv_invi_code)
    TextView tvInviCode;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    List<ImageView> viewList;
    @Override
    protected void initData() {
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

                            for (ImageView imageView : viewList) {
                                imageView.setImageResource(R.drawable.tab_icon_me0);
                            }
                            for (int num = 0; num<invitedNum; num++) {
                                viewList.get(num).setImageResource(R.drawable.tab_icon_me1);
                            }
                        }
                    }
                });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vip_invite;
    }

    @Override
    protected void initView() {
        tvTitle.setText(R.string.open_vip);
        viewList=new ArrayList<>();
        viewList.add(iv1);
        viewList.add(iv2);
        viewList.add(iv3);
        viewList.add(iv4);
        viewList.add(iv5);
        initData();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
    }
}
