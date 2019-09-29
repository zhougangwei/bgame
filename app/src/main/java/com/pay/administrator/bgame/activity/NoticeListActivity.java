package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.NoticeAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.NoticeBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    public  List<NoticeBean.DataBean.ListBean> dataList   =new ArrayList<>();
    private String                 TAG        ="NoticeListActivity";

    private NoticeAdapter noticeAdapter;

    @Override
    protected void initView() {
        tvTitle.setText("Notice");
        ivBack.setVisibility(View.GONE);
        noticeAdapter = new NoticeAdapter(R.layout.item_notice,dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(noticeAdapter);
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
        noticeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "wo ");
                getData(false);
            }
        }, rv);
        noticeAdapter.setPreLoadNumber(5);
        noticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(NoticeListActivity.this, NoticeDetailActivity.class);
                intent.putExtra("noticeItem", dataList.get(position));

                startActivity(intent);
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
                .subscribe(new BaseCosumer<NoticeBean>() {
                    @Override
                    public void onGetData(NoticeBean tagbean) {
                        if (!ResultUtils.cheekSuccess(tagbean)) {
                            noticeAdapter.loadMoreFail();
                            return;
                        }
                        if (tagbean.getData()==null||tagbean.getData().getList().size()==0) {
                            noticeAdapter.loadMoreEnd();
                            isLoadMore = false;
                        } else {
                            noticeAdapter.loadMoreComplete();
                        }
                        if (isRefresh) {
                            dataList.clear();
                        }
                        dataList.addAll(tagbean.getData().getList());
                        noticeAdapter.notifyDataSetChanged();
                        noticeAdapter.disableLoadMoreIfNotFullPage(rv);

                    }
                });
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
