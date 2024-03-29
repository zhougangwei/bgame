package com.pay.administrator.bgame.pay;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by zhougangwei on 2018/10/29.
 */

public interface GooglePayUiPrecneter {



    void init(Activity context);

    boolean onActivityResult(int requestCode, int resultCode, Intent data);


     //跟随生命周期 ondestroy记得调用
     void onDestroy();


    /**
     * 支付回调 有时候会用到
     * @param constumCallBack
     */
     void addConstumCallBack(ConstumCallBack constumCallBack);

    void charge(String productid);
    void charge(String productid, String price);

    interface ConstumCallBack{
         void constumSuccess();
    }

}
