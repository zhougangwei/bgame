package com.pay.administrator.bgame.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.blankj.utilcode.utils.LogUtils;
import com.facebook.FacebookSdk;
import com.pay.administrator.bgame.activity.LoginActivity;
import com.pay.administrator.bgame.utils.SPUtil;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.util.Locale;


/**
 * Created by oneki on 2017/8/24.
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 */


public class App extends MultiDexApplication {

    private static final String TAG = "Application";

    private static App application;
    public static Context sContext;
    private int count;

    @Override
    public void onCreate() {
        super.onCreate();

        if (ToolUtils.isMainProcess(this)) {
            application = this;
            sContext = this;
            LogUtils.init(this, true, false, 'v', "Hala");
            FacebookSdk.setApplicationId("306102296576531");
            FacebookSdk.setAutoLogAppEventsEnabled(true);
            FacebookSdk.sdkInitialize(getApplicationContext());
            SPUtil.setContext(this);

        }

    }




    //用来阿拉伯语 本地使用
    private void initLanguage() {

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        res.updateConfiguration(config, dm);
    }


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    /**
     * 获取上下文
     */
    public static App getApplication() {
        return application;
    }

    public static void goLogin() {
        Intent intent = new Intent(application, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }



}
