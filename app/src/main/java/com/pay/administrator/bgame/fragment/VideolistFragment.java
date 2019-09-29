package com.pay.administrator.bgame.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.activity.VideoActivity;
import com.pay.administrator.bgame.adapter.VideoListItemAdapter;
import com.pay.administrator.bgame.base.BaseFragment;
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


public class VideolistFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;

    private int page;
    private boolean isLoadMore=true;
    public List<VideoListBean.DataBean> dataList=new ArrayList<>();
    private String TAG="VideolistFragment";

    private VideoListItemAdapter videoListItemAdapter;
    private int tagId;


    @Override
    protected void initView() {
        videoListItemAdapter = new VideoListItemAdapter(R.layout.item_videolist,dataList);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(videoListItemAdapter);
        videoListItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        }, rv);
        videoListItemAdapter.setPreLoadNumber(5);
        videoListItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startVideo(getActivity(),dataList.get(position).getId());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmeng_videolist;
    }

    @Override
    protected void initData() {
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
        RetrofitFactory.getInstance().searchVideo(page, Contact.PAGE_SIZE,tagId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<VideoListBean>() {
                    @Override
                    public void onGetData(VideoListBean tagbean) {
                        if (!ResultUtils.cheekSuccess(tagbean)) {
                            videoListItemAdapter.loadMoreFail();
                            return;
                        }
                        if (tagbean.getData()==null||tagbean.getData().size()==0) {
                            videoListItemAdapter.loadMoreEnd();
                            isLoadMore = false;
                        } else {
                            videoListItemAdapter.loadMoreComplete();
                        }
                        if (isRefresh) {
                            dataList.clear();
                        }
                        dataList.addAll(tagbean.getData());
                        videoListItemAdapter.notifyDataSetChanged();
                        videoListItemAdapter.disableLoadMoreIfNotFullPage(rv);

                    }
                });
    }


    public void setData(int id) {
        if(tagId==0){
            tagId=id;
            getData(true);
        }
        tagId=id;
    }
}
