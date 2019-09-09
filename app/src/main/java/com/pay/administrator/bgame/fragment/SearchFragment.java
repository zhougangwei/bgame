package com.pay.administrator.bgame.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.activity.VideoActivity;
import com.pay.administrator.bgame.adapter.SearchAdapter;
import com.pay.administrator.bgame.base.BaseFragment;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SearchFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swrl)
    SwipeRefreshLayout swrl;

    private int page;
    private boolean isLoadMore=true;
    public List<TagBean.DataBean> dataList=new ArrayList<>();
    private String TAG="SearchFragment";

    @Override
    protected void initView() {
        tvTitle.setText("Find");
        SearchAdapter searchAdapter = new SearchAdapter(R.layout.item_search_video,dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(searchAdapter);
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
        searchAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "wo ");
                getData(false);
            }
        }, rv);
        searchAdapter.setPreLoadNumber(5);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startVideo(getActivity(),dataList.get(position).get);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmeng_search;
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
        RetrofitFactory.getInstance().getFindVideos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {

                    }
                });
    }

    public void search() {
        RetrofitFactory.getInstance().searchVideo(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {

                    @Override
                    public void onGetData(BaseBean tagbean) {

                    }
                });
    }




    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_search:
                break;
        }
    }
}
