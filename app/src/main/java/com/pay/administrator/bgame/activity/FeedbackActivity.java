package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class FeedbackActivity extends BaseActivity{


    private String[] titles;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView() {
        titles = new String[]{"Feedback","MyFeedback"};
        CommonNavigator commonNavigator7 = new CommonNavigator(this);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setNormalColor(Color.parseColor("#323333"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#353535"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });

                simplePagerTitleView.setTypeface(Typeface.DEFAULT , Typeface.BOLD );
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 7));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                //   indicator.setColors(getResources().getColor(R.color.linepager_indicator_color1),getResources().getColor(R.color.linepager_indicator_color2));
                indicator.setColors(Color.parseColor("#FF4066"));
                return indicator;
            }
        });
        mTab.setNavigator(commonNavigator7);
    }
}
