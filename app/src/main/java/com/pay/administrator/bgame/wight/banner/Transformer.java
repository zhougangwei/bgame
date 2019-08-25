package com.pay.administrator.bgame.wight.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.pay.administrator.bgame.wight.banner.transformer.AccordionTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.BackgroundToForegroundTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.CubeInTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.CubeOutTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.DefaultTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.DepthPageTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.FlipHorizontalTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.FlipVerticalTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.ForegroundToBackgroundTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.RotateDownTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.RotateUpTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.ScaleInOutTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.StackTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.TabletTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.ZoomInTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.ZoomOutSlideTransformer;
import com.pay.administrator.bgame.wight.banner.transformer.ZoomOutTranformer;



public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
