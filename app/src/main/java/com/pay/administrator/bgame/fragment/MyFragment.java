package com.pay.administrator.bgame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.activity.MylikeActivity;
import com.pay.administrator.bgame.activity.NoticeListActivity;
import com.pay.administrator.bgame.activity.SettingActivity;
import com.pay.administrator.bgame.base.BaseFragment;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.UserInfo;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyFragment extends BaseFragment {


    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_crown)
    ImageView ivCrown;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.tv_like)
    TextView tvLike;
    @BindView(R.id.tv_like_content)
    TextView tvLikeContent;
    @BindView(R.id.rl_like)
    RelativeLayout rlLike;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.tv_vip_content)
    TextView tvVipContent;
    @BindView(R.id.rl_vip)
    RelativeLayout rlVip;
    @BindView(R.id.iv_invite)
    ImageView ivInvite;
    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.tv_invite_content)
    TextView tvInviteContent;
    @BindView(R.id.rl_invite)
    RelativeLayout rlInvite;
    Unbinder unbinder;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmeng_my;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        getLike();
        getUserInfo();
    }

    private void getLike() {



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_setting, R.id.rl_like, R.id.rl_vip, R.id.rl_invite,R.id.tv_feedback, R.id.tv_notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_like:
                startActivity(new Intent(getActivity(), MylikeActivity.class));
                break;
            case R.id.rl_vip:
                break;
            case R.id.rl_invite:
                break;
            case R.id.tv_feedback:
                break;
            case R.id.tv_notice:
                startActivity(new Intent(getActivity(), NoticeListActivity.class));
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
                            int remanidCount = data.getRemanidCount();
                            tvName.setText(name);
                            tvCount.setText(remanidCount+"/5");
                        }
                    }
                });
    }
}
