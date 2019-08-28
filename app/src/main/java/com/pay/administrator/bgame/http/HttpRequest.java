package com.pay.administrator.bgame.http;


import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.LoginBean;
import com.pay.administrator.bgame.bean.TagBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kiddo on 2017/11/29.
 */

public interface HttpRequest {

    @GET("/namol/api/app/v1/tag")
    Observable<TagBean> getTagList(
    );


}
