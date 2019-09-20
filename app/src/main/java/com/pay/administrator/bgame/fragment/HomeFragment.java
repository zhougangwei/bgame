package com.pay.administrator.bgame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.activity.SearchActivity;
import com.pay.administrator.bgame.adapter.GlideImageLoader;
import com.pay.administrator.bgame.adapter.HotCallAdapter;
import com.pay.administrator.bgame.base.BaseFragment;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.wight.banner.Banner;
import com.pay.administrator.bgame.wight.banner.BannerConfig;
import com.pay.administrator.bgame.wight.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends BaseFragment {


    private static final String TAG = "HomeFragment";
    Banner banner;
    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.swrl)
    SwipeRefreshLayout swrl;
    Unbinder unbinder;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    Unbinder unbinder1;
    private HotCallAdapter tagAdapter;
    private int            page;
    private boolean isLoadMore = true;

    public  List<HomeMovieBean.DataBean> dataList   = new ArrayList<>();
    private List<String>                 imagesList = new ArrayList<>();

    @Override
    protected void initView() {

        tagAdapter = new HotCallAdapter(dataList);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        rv.setAdapter(tagAdapter);
        rv.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 4;
                }
                if (position > 8) {
                    return 4;
                }
                return 1;
            }
        });


        View headView = View.inflate(getActivity(), R.layout.item_hotfragment_header, null);
        banner = headView.findViewById(R.id.banner);
        tagAdapter.addHeaderView(headView, 0);

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
        tagAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "wo ");
                getData(false);
            }
        }, rv);
        getBannerData();
        tagAdapter.setPreLoadNumber(5);
    }

    private void getBannerData() {
        imagesList.clear();
        imagesList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        imagesList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        imagesList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        imagesList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        banner();
    }

    private void banner() {
        banner.setImages(imagesList)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                .start();
        banner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmeng_home;
    }

    @Override
    protected void initData() {
        getData(true);
    }

    private void getData(final boolean isRefresh) {
        if (!isLoadMore) {
            return;
        }
        if (isRefresh) {
            page = 0;
        } else {
            page++;
        }
        RetrofitFactory.getInstance().getHomeMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<HomeMovieBean>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        tagAdapter.loadMoreFail();
                    }

                    @Override
                    public void onGetData(HomeMovieBean homeMovieBean) {
                        if (!ResultUtils.cheekSuccess(homeMovieBean)) {
                            tagAdapter.loadMoreFail();
                            return;
                        }
                        if (homeMovieBean.getData() == null || homeMovieBean.getData().size() == 0) {
                            tagAdapter.loadMoreEnd();
                            isLoadMore = false;
                        } else {
                            tagAdapter.loadMoreComplete();
                        }
                        if (isRefresh) {
                            dataList.clear();
                        }
                        dataList.addAll(homeMovieBean.getData());
                        HomeMovieBean.DataBean dataBean = new HomeMovieBean.DataBean();
                        HomeMovieBean.DataBean.TypeBean typeBean = new HomeMovieBean.DataBean.TypeBean();
                        typeBean.setNameEn("All");
                        typeBean.setNameAl("All");
                        typeBean.setNameZh("All");
                        dataBean.setType(typeBean);
                        dataList.add(7, dataBean);
                        for (int i = 0; i < dataList.size(); i++) {
                            if (i < 8) {
                                dataList.get(i).setDataType(HotCallAdapter.TOP_EIGHT);
                            } else {
                                dataList.get(i).setDataType(HotCallAdapter.BOTTOM_ELSE);
                            }

                        }


                        tagAdapter.notifyDataSetChanged();
                        tagAdapter.disableLoadMoreIfNotFullPage(rv);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}
