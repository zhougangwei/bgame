package com.pay.administrator.bgame.adapter;

import android.nfc.Tag;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiddo on 2018/1/9.
 */

public class HotCallAdapter extends BaseMultiItemQuickAdapter<HomeMovieBean.DataBean, BaseViewHolder> {

    public final static int TOP_EIGHT = 1;
    public final static int BOTTOM_ELSE = 2;
    public int[] resIds= {R.drawable.class_bg1,
            R.drawable.class_bg2,
            R.drawable.class_bg3,
            R.drawable.class_bg4,
            R.drawable.class_bg5,
            R.drawable.class_bg6,
            R.drawable.class_bg7,
            R.drawable.class_bg8,
    };
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HotCallAdapter(List<HomeMovieBean.DataBean> data) {
        super(data);
        addItemType(TOP_EIGHT, R.layout.item_home_eight);
        addItemType(BOTTOM_ELSE, R.layout.item_home_detail);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMovieBean.DataBean item) {
        if(item.getType()==null){
            return;
        }
        String content;
        switch (ToolUtils.getLanguage()) {
            case Contact.LANGUAGE_CHINA:
                content=item.getType().getNameZh();
                break;
            case Contact.LANGUAGE_ENGLISH:
                content=item.getType().getNameEn();
                break;
            case Contact.LANGUAGE_AR:
                content=item.getType().getNameAl();
                break;
            default:
                content=item.getType().getNameEn();
                break;
        }
        switch (helper.getItemViewType()) {
            case TOP_EIGHT:
                helper.setText(R.id.tv_content,content);
                helper.setImageResource(R.id.iv,resIds[helper.getAdapterPosition()-1]);
                break;
            case BOTTOM_ELSE:
                List<TagBean.DataBean> recommendMovie = item.getRecommendMovie();
                List<TagBean.DataBean> data = new ArrayList<>();
                if (recommendMovie != null) {
                    data.addAll(recommendMovie);
                }
                helper.setText(R.id.tv_title,content);
                RecyclerView rv = helper.getView(R.id.rv);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                rv.setLayoutManager(gridLayoutManager);
                TagItemVideoAdapter tagItemVideoAdapter = new TagItemVideoAdapter(R.layout.item_home_video, data);
                rv.setAdapter(tagItemVideoAdapter);
                break;
        }



    }


}
