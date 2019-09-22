package com.pay.administrator.bgame.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.bean.CountryBean;

import java.util.List;



/**
 * Created by kiddo on 2018/1/9.
 */

public class CountryAdapter extends BaseQuickAdapter<CountryBean, BaseViewHolder> {




    public CountryAdapter(int layoutIds, List<CountryBean> countryDatas) {
        super(layoutIds, countryDatas);
    }

    @Override
    protected void convert(BaseViewHolder helper, CountryBean item) {
        helper.setText(R.id.tv_country_code, item.getCountryCode());
        helper.setText(R.id.tv_country_name, item.getCountryName());


    }
}
