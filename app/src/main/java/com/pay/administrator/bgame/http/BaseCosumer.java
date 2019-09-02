package com.pay.administrator.bgame.http;

import com.blankj.utilcode.utils.LogUtils;
import com.pay.administrator.bgame.bean.BaseBean;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public  abstract class BaseCosumer< T extends BaseBean> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        try {
            onGetData(t);
        }catch (Exception e){
            onError(e);
        }
    }

    public abstract void onGetData(T t);


    @Override
    public void onError(Throwable e) {
        try{
            LogUtils.e("BaseCosumer", e.getMessage());
        }catch (Exception e2){
            e2.printStackTrace();
            LogUtils.e("BaseCosumer", e2.getMessage()+"11");
        }
    }

    @Override
    public void onComplete() {

    }

}
