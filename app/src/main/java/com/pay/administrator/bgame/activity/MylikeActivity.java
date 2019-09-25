package com.pay.administrator.bgame.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.LikeAdapter;
import com.pay.administrator.bgame.adapter.SearchAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MylikeActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swrl)
    SwipeRefreshLayout swrl;

    private LikeAdapter likeAdapter;
    private List<TagBean.DataBean> datas = new ArrayList<>();
    private boolean edit=false;
    private int page;
    private boolean isLoadMore=true;

    @Override
    protected void initData() {
        RetrofitFactory.getInstance().getLikeVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<TagBean>() {
                    @Override
                    public void onGetData(TagBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            if (baseBean.getData()!=null) {
                                datas.clear();
                                datas.addAll(baseBean.getData());
                            }
                            likeAdapter.notifyDataSetChanged();
                        }
                    }
                });


    }

    private void getData() {
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
        RetrofitFactory.getInstance().getMyLikeList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<TagBean>() {
                    @Override
                    public void onGetData(TagBean tagbean) {
                        if (!ResultUtils.cheekSuccess(tagbean)) {
                            likeAdapter.loadMoreFail();
                            return;
                        }
                        if (tagbean.getData()==null||tagbean.getData().size()==0) {
                            likeAdapter.loadMoreEnd();
                            isLoadMore = false;
                        } else {
                            likeAdapter.loadMoreComplete();
                        }
                        if (isRefresh) {
                            datas.clear();
                        }
                        datas.addAll(tagbean.getData());
                        likeAdapter.notifyDataSetChanged();
                        likeAdapter.disableLoadMoreIfNotFullPage(rv);

                    }
                });
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_mylike;
    }

    @Override
    protected void initView() {
        tvTitle.setText("My Like");
        tvEdit.setText("Edit");
        likeAdapter = new LikeAdapter(R.layout.item_like, datas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(likeAdapter);
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


        likeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                getData(false);
            }
        }, rv);

        likeAdapter.setPreLoadNumber(5);
        likeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TagBean.DataBean dataBean = datas.get(position);
                dataBean.setSelectEdit(!dataBean.isSelectEdit());
                adapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.iv_back,R.id.tv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
             selectEdit();
                break;
        }
    }

    private void selectEdit() {
        edit=!edit;
        if(edit){
            for (TagBean.DataBean data : datas) {
                data.setEdit(true);
            }
            likeAdapter.notifyDataSetChanged();
        }
    }

}
