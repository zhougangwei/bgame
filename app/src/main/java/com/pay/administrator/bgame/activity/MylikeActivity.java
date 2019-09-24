package com.pay.administrator.bgame.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.LikeAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.LikeBean;
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
    ImageView    ivBack;
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.tv_edit)
    TextView     tvEdit;
    @BindView(R.id.rv)
    RecyclerView rv;
    private LikeAdapter likeAdapter;
    private List<LikeBean.DataBean> datas = new ArrayList<>();
    private boolean                edit  = false;

    @Override
    protected void initData() {
        RetrofitFactory.getInstance().getLikeVideo(22)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<LikeBean>() {
                    @Override
                    public void onGetData(LikeBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            if (baseBean.getData() != null) {
                                datas.clear();
                                datas.addAll(baseBean.getData());
                            }
                            likeAdapter.notifyDataSetChanged();
                        }
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

    }
    private void deleteSelect() {

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
