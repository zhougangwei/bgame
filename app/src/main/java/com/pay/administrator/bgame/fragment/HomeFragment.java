package com.pay.administrator.bgame.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.GlideImageLoader;
import com.pay.administrator.bgame.adapter.HotCallAdapter;
import com.pay.administrator.bgame.base.BaseFragment;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.wight.banner.Banner;
import com.pay.administrator.bgame.wight.banner.BannerConfig;
import com.pay.administrator.bgame.wight.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private HotCallAdapter tagAdapter;
    private int page;
    private boolean isLoadMore=true;

    public List<TagBean.DataBean> dataList=new ArrayList<>();
    private List<String> imagesList=new ArrayList<>();
    @Override
    protected void initView() {

        tagAdapter = new HotCallAdapter(R.layout.item_home_detail,dataList);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(tagAdapter);
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
        imagesList.add( "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        imagesList.add( "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        imagesList.add( "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
        imagesList.add( "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561969870482&di=5c1c1ce287af862e3165902039c59cbd&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160522%2F29bb43e8e4d44c94846ae13520d15f88_th.jpg");
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
        RetrofitFactory.getInstance().getTagList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<TagBean>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        tagAdapter.loadMoreFail();
                    }
                    @Override
                    public void onGetData(TagBean tagbean) {
                        if (!ResultUtils.cheekSuccess(tagbean)) {
                            tagAdapter.loadMoreFail();
                            return;
                        }
                        if (tagbean.getData()==null||tagbean.getData().size()==0) {
                            tagAdapter.loadMoreEnd();
                            isLoadMore = false;
                        } else {
                            tagAdapter.loadMoreComplete();
                        }
                        if (isRefresh) {
                            dataList.clear();
                        }
                        dataList.addAll(tagbean.getData());
                        tagAdapter.notifyDataSetChanged();
                        tagAdapter.disableLoadMoreIfNotFullPage(rv);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
