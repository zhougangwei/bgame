package com.pay.administrator.bgame.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.LikeAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.bean.TagBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MylikeActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.rv)
    RecyclerView rv;
    private LikeAdapter likeAdapter;
    private List<TagBean.DataBean> datas = new ArrayList<>();
    private boolean edit=false;

    @Override
    protected void initData() {
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
