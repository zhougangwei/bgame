package com.pay.administrator.bgame.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.SearchAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NoticeListActivity extends BaseActivity{


    @BindView(R.id.iv_back)
    ImageView          ivBack;
    @BindView(R.id.tv_title)
    TextView           tvTitle;
    @BindView(R.id.rv)
    RecyclerView       rv;
    @BindView(R.id.swrl)
    SwipeRefreshLayout swrl;

    private int page;
    private boolean                isLoadMore =true;
    public  List<TagBean.DataBean> dataList   =new ArrayList<>();
    private String                 TAG        ="NoticeListActivity";

    private SearchAdapter searchAdapter;

    @Override
    protected void initView() {
        tvTitle.setText("Find");
        ivBack.setVisibility(View.GONE);
        searchAdapter = new SearchAdapter(R.layout.item_search_video,dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
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
                VideoActivity.startVideo(NoticeListActivity.this,dataList.get(position).getId());
            }
        });
    }



    @Override
    protected void initData() {
        getData(true);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notice_list;
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
        RetrofitFactory.getInstance().getNoticeList(page, Contact.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<TagBean>() {
                    @Override
                    public void onGetData(TagBean tagbean) {
                        if (!ResultUtils.cheekSuccess(tagbean)) {
                            searchAdapter.loadMoreFail();
                            return;
                        }
                        if (tagbean.getData()==null||tagbean.getData().size()==0) {
                            searchAdapter.loadMoreEnd();
                            isLoadMore = false;
                        } else {
                            searchAdapter.loadMoreComplete();
                        }
                        if (isRefresh) {
                            dataList.clear();
                        }
                        dataList.addAll(tagbean.getData());
                        searchAdapter.notifyDataSetChanged();
                        searchAdapter.disableLoadMoreIfNotFullPage(rv);

                    }
                });
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
        }
    }
}
