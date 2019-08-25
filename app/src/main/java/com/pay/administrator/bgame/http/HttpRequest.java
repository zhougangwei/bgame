package com.pay.administrator.bgame.http;


import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kiddo on 2017/11/29.
 */

public interface HttpRequest {

    @POST("/account/signin_or_signup")
    Observable<LoginBean> login(@Body RequestBody requestBody
    );

    RequestBody login(@Query("code") String code, @Query("mobileNumber") String mobileNumber
    );

    @POST("/account/fb_signin_or_signup")
    Observable<LoginBean> loginFacebook(@Body RequestBody requestBody
    );

    RequestBody loginFacebook(@Query("fbToken") String fbToken
    );


    @POST("/account/mobile_signup")
    Observable<BaseBean> regist(@Body RequestBody requestBody
    );

    RequestBody regist(@Query("avatarUrl") String avatarUrl, @Query("username") String username,
                       @Query("gender") String gender, @Query("birthDate") String birthDate, @Query("facebookId") String facebookId
    );

    RequestBody regist(@Query("code") String code, @Query("avatarUrl") String avatarUrl, @Query("username") String username,
                       @Query("gender") String gender, @Query("birthDate") String birthDate, @Query("mobileNumber") String mobileNumber
    );

    RequestBody changeUserInfo(@Query("avatarUrl") String avatarUrl, @Query("username") String username,
                               @Query("gender") String gender, @Query("birthDate") String birthDate, @Query("mobileNumber") String mobileNumber
    );

    @POST("/member")
    Observable<BaseBean> changeUserInfo(@Body RequestBody requestBody
    );

}
