package com.pay.administrator.bgame.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.CountryAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.CountryBean;
import com.pay.administrator.bgame.utils.AssetUtils;
import com.pay.administrator.bgame.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by kiddo on 2018/1/9.
 */

public class ChooseCountryActivity extends BaseActivity {



    @BindView(R.id.tv_cancel)
    TextView     mTvCancel;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.rv_country)
    RecyclerView rvCountry;
    private CountryAdapter adapter;

    List<CountryBean> countryDatas = new ArrayList<>();


    private void initDatas() {
        String json = AssetUtils.getJson(this, "country.json");
        List<CountryBean> objects = GsonUtil.parseJsonToList(json, new TypeToken<List<CountryBean>>() {
        }.getType());
        countryDatas.addAll(objects);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_choose_country;
    }



    @Override
    protected void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCountry.setLayoutManager(layoutManager);
        adapter = new CountryAdapter(R.layout.item_country, countryDatas);
        rvCountry.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("countryName", countryDatas.get(position).getCountryName());
                intent.putExtra("countryCode", Integer.parseInt(countryDatas.get(position).getCountryCode()));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        initDatas();
    }



    @OnClick({R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;

        }
    }
}
