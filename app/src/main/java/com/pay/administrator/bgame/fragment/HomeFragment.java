package com.pay.administrator.bgame.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.HotCallAdapter;
import com.pay.administrator.bgame.base.BaseFragment;
import com.pay.administrator.bgame.wight.banner.Banner;


public class HomeFragment extends BaseFragment {

    Banner banner;
    private HotCallAdapter hotCallAdapter;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmeng_home;
    }

    @Override
    protected void initData() {
       /* hotCallAdapter = new HotCallAdapter(R.layout.item_hot_list, mHotOnetoOneList);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(hotCallAdapter);
        View headView = View.inflate(getActivity(), R.layout.item_hotfragment_header, null);
        banner=headView.findViewById(R.id.banner);
        hotCallAdapter.addHeaderView(headView,4);
        hotCallAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AnchorsActivity.startAnchorAc(getActivity(), mHotOnetoOneList.get(position).getAnchorId(), mHotOnetoOneList.get(position).getMemberId());
            }
        });

        swrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = true;
                swrl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(true);
                        swrl.setRefreshing(false);
                    }
                }, 200);
            }
        });
        hotCallAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "wo ");
                getData(false);
            }
        }, rv);
        getBannerData();
        hotCallAdapter.setPreLoadNumber(5);*/
    }

}
