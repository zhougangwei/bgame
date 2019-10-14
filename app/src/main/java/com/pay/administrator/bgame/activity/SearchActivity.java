package com.pay.administrator.bgame.activity;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.FeedBackTypeAdapter;
import com.pay.administrator.bgame.adapter.SearchAdapter;
import com.pay.administrator.bgame.adapter.SearchHisAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.FeedBackTypeBean;
import com.pay.administrator.bgame.bean.VideoListBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.SPUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.rv_his)
    RecyclerView mRvHis;
    @BindView(R.id.imageView5)
    ImageView    mImageView5;
    @BindView(R.id.textView16)
    TextView     mTextView16;
    @BindView(R.id.iv_his_del)
    ImageView    mIvHisDel;
    @BindView(R.id.gp_his_result)
    Group        mGpHisResult;
    @BindView(R.id.iv_his_empty)
    ImageView    mIvHisEmpty;
    @BindView(R.id.tv_no_his)
    TextView     mTvNoHis;
    private String content;
    private int    page;
    private boolean                      isLoadMore = true;
    public  List<VideoListBean.DataBean> dataList   = new ArrayList<>();
    private String                       TAG        = "SearchFragment";
    private SearchAdapter searchAdapter;

    private List<String> hisList = new ArrayList<>();
    private SearchHisAdapter mHisAdapter;
    private Paint mPaint;

    @Override
    protected void initData() {
        backData();
    }
    private void backData() {
        try {
            String searchHis = SPUtil.getInstance().getSearchHis();
            if (!TextUtils.isEmpty(searchHis)) {
                String[] split = searchHis.split(",,");
                List<String> strings = Arrays.asList(split);
                if (strings!=null&&strings.size()>0){
                    hisList.addAll(strings);
                }
            }
            if (hisList.size()!=0){
                mGpHisResult.setVisibility(View.VISIBLE);
                mIvHisEmpty.setVisibility(View.GONE);
                mTvNoHis.setVisibility(View.GONE);
                mHisAdapter.notifyDataSetChanged();
            }else{
                mGpHisResult.setVisibility(View.GONE);
                mIvHisEmpty.setVisibility(View.VISIBLE);
                mTvNoHis.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(SizeUtils.sp2px(SearchActivity.this, 14));
        mPaint.setStyle(Paint.Style.FILL);
        searchAdapter = new SearchAdapter(R.layout.item_search_video, dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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
                VideoActivity.startVideo(SearchActivity.this, dataList.get(position).getId());
            }
        });
        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //mHistoryAdapter.notifyDataSetChanged();
                    content = mEtSearch.getText().toString();
                    if ((!TextUtils.isEmpty(content.trim()))){
                        SPUtil.getInstance().setSearchHis(content.trim());
                        getData(true);
                    }
                }
                return false;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 100);
        mRvHis.setLayoutManager(gridLayoutManager);
        mHisAdapter = new SearchHisAdapter(R.layout.item_feedback_type, hisList);
        mRvHis.setAdapter(mHisAdapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int width = ScreenUtils.getScreenWidth(SearchActivity.this) - SizeUtils.dp2px(SearchActivity.this, 44);
                int itemWidth = getTextWidth(mPaint, hisList.get(position)) + SizeUtils.dp2px(SearchActivity.this, 34);
                return Math.min(100, itemWidth * 100 / width + 1);
            }
        });


        mHisAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dataBean = hisList.get(position);
                mEtSearch.setText(dataBean);
                content = mEtSearch.getText().toString();
                getData(true);
                mHisAdapter.notifyDataSetChanged();
            }
        });
    }

    private int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }
    public void hideSoftInput() {
        // 先隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick({R.id.tv_cancel,R.id.iv_his_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                content = "";mEtSearch.setText("");
                getData(true);
                break;
            case R.id.iv_his_del:
                SPUtil.getInstance().clearSearchHis();
                break;

        }


    }


    private void getData(final boolean isRefresh) {
        hideSoftInput();
        mIvHisEmpty.setVisibility(View.GONE);
        mTvNoHis.setVisibility(View.GONE);
        mGpHisResult.setVisibility(View.GONE);
        if (!isLoadMore) {
            return;
        }
        if (isRefresh) {
            page = 0;
        } else {
            page++;
        }
        RetrofitFactory.getInstance().searchVideo(page, Contact.PAGE_SIZE, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<VideoListBean>() {
                    @Override
                    public void onGetData(VideoListBean tagbean) {
                        if (!ResultUtils.cheekSuccess(tagbean)) {
                            searchAdapter.loadMoreFail();
                            return;
                        }
                        if (tagbean.getData() == null || tagbean.getData().size() == 0) {
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
