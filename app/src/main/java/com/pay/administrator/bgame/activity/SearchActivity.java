package com.pay.administrator.bgame.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.SearchAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.VideoListBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.et_search)
    EditText     mEtSearch;
    @BindView(R.id.tv_cancel)
    TextView     mTvCancel;
    @BindView(R.id.rv)
    RecyclerView mRv;
    private String content;
    private int page;
    private boolean isLoadMore=true;
    public List<VideoListBean.DataBean> dataList=new ArrayList<>();
    private String TAG="SearchFragment";
    private SearchAdapter searchAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchAdapter = new SearchAdapter(R.layout.item_search_video,dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(searchAdapter);

        searchAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "wo ");
                getData(false);
            }
        }, mRv);
        searchAdapter.setPreLoadNumber(5);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startVideo(SearchActivity.this,dataList.get(position).getId());
            }
        });
        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //mHistoryAdapter.notifyDataSetChanged();
                    content=mEtSearch.getText().toString();
                    getData(true);
                }
                return false;
            }
        });
    }
    public void hideSoftInput() {
        // 先隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick(R.id.tv_cancel)
    public void onClick() {
        content="";
        getData(true);
    }



    private void getData(final boolean isRefresh) {
        hideSoftInput();
        if (!isLoadMore) {
            return;
        }
        if (isRefresh) {
            page = 0;
        } else {
            page++;
        }
        RetrofitFactory.getInstance().searchVideo(page, Contact.PAGE_SIZE,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<VideoListBean>() {
                    @Override
                    public void onGetData(VideoListBean tagbean) {
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
                        searchAdapter.disableLoadMoreIfNotFullPage(mRv);
                    }
                });
    }


}
