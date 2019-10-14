package com.pay.administrator.bgame.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.LikeAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.UserInfoConfig;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.LikeBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MylikeActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView    ivBack;
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.tv_edit)
    TextView     tvEdit;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swrl)
    SwipeRefreshLayout swrl;

    private LikeAdapter likeAdapter;
    private List<LikeBean.DataBean> datas = new ArrayList<>();
    private boolean edit=false;
    private int page;
    private boolean isLoadMore=true;

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
        RetrofitFactory.getInstance().getLikeVideo(UserInfoConfig.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<LikeBean>() {
                    @Override
                    public void onGetData(LikeBean tagbean) {
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
                LikeBean.DataBean dataBean = datas.get(position);
                dataBean.setSelectEdit(!dataBean.isSelectEdit());
                adapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.tv_delete, R.id.tv_select_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                selectEdit();
                break;
            case R.id.tv_delete:
                deleteSelect();
                break;
            case R.id.tv_select_all:
                selectAll();
                break;
        }
    }
    private void selectAll() {
        for (int i = 0; i < datas.size(); i++) {
            LikeBean.DataBean dataBean = datas.get(i);
            dataBean.setSelectEdit(true);
        }
        likeAdapter.notifyDataSetChanged();
    }
    private void deleteSelect() {
        StringBuilder sb =new StringBuilder();
        for (int i = 0; i < datas.size(); i++) {
            LikeBean.DataBean dataBean = datas.get(i);
            if (dataBean.isSelectEdit()){
                sb.append(dataBean.getMovieId()).append(",");
            }
        }
        if(sb.length()==0){
            ToastUtils.showToast(this,"Please Choose!");
            return;
        }
        RetrofitFactory.getInstance().deleteLike(ProxyPostHttpRequest.getInstance().deleteLike(
                sb.toString()
        )).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            ToastUtils.showToast(MylikeActivity.this,"Delete Success!");
                        }
                    }
                });
    }
    private void selectEdit() {
        edit = !edit;
        if (edit) {
            for (LikeBean.DataBean data : datas) {
                data.setEdit(true);
            }
            likeAdapter.notifyDataSetChanged();
        }
    }

}
