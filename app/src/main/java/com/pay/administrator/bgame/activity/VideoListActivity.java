package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.SizeUtils;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.VideoListAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToolUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wjy on 2019/9/29/029.
 */
public class VideoListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager vp;

    List<TagBean.DataBean> tagList = new ArrayList<>();
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private CommonNavigatorAdapter commonNavigatorAdapter;
    private VideoListAdapter homeAdapter;
    private int chooseTagId;

    @Override
    protected void initData() {
        RetrofitFactory.getInstance().getTagList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<TagBean>() {
                    @Override
                    public void onGetData(TagBean tagBean) {
                        if (ResultUtils.cheekSuccess(tagBean)) {
                            List<TagBean.DataBean> data = tagBean.getData();
                            if (data != null) {
                                tagList.addAll(data);
                            }
                            commonNavigatorAdapter.notifyDataSetChanged();
                            homeAdapter.notifyDataSetChanged();
                            for (int i = 0; i < tagList.size(); i++) {
                                if (chooseTagId == tagList.get(i).getId()) {
                                    vp.setCurrentItem(i);
                                }
                            }
                        }
                    }
                });


    }

    @Override
    protected int getContentViewId() {
        return R.layout.activit_video_list;
    }

    @Override
    protected void initView() {
        tvTitle.setText("Celebrity");
        Intent intent = getIntent();
        chooseTagId = intent.getIntExtra("tagId", 0);

        magicIndicator.setBackgroundColor(Color.parseColor("#FF1D1D1D"));
        homeAdapter = new VideoListAdapter(getSupportFragmentManager(), tagList);
        vp.setAdapter(homeAdapter);

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tagList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                TagBean.DataBean dataBean = tagList.get(index);
                String content;
                switch (ToolUtils.getLanguage()) {
                    case Contact.LANGUAGE_CHINA:
                        content = dataBean.getNameZh();
                        break;
                    case Contact.LANGUAGE_ENGLISH:
                        content = dataBean.getNameEn();
                        break;
                    case Contact.LANGUAGE_AR:
                        content = dataBean.getNameAl();
                        break;
                    default:
                        content = dataBean.getNameEn();
                        break;
                }
                clipPagerTitleView.setTextSize(SizeUtils.sp2px(VideoListActivity.this,15));
                clipPagerTitleView.setText(content);
                clipPagerTitleView.setTextColor(Color.parseColor("#FF888888"));
                clipPagerTitleView.setClipColor(Color.BLACK);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = SizeUtils.dp2px(VideoListActivity.this, 25);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
                indicator.setYOffset(UIUtil.dip2px(context, 5));
                indicator.setColors(Color.parseColor("#FFFFC937"));
                return indicator;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_search, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }
}
