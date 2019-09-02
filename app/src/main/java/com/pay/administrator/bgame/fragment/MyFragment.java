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
import com.pay.administrator.bgame.activity.SettingActivity;
import com.pay.administrator.bgame.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindView(R.id.textView4)
    TextView textView4;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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
                break;
            case R.id.rl_vip:
                break;
            case R.id.rl_invite:
                break;
            case R.id.tv_feedback:
                break;
            case R.id.tv_notice:
                break;
        }
    }

}
