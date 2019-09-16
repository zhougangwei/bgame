package com.pay.administrator.bgame.http;

import android.content.Context;
import android.text.TextUtils;

import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.utils.SPUtil;
import com.pay.administrator.bgame.utils.ToolUtils;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddInfoInterceptor implements Interceptor {
    private Context context;



    public AddInfoInterceptor(Context context) {
        super();
        this.context = context;
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Content-Type", "application/json");
       /* String token = SPUtil.getInstance().getString(Contact.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("Authorization","Bearer "+ token);
        }*/
        builder.addHeader("Authorization","Bearer "+ "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJuYW1vbCIsImp0aSI6IjhjYjY2Y2U5MmY2OTQ3Y2Y4NDM0YWYyZjFiYmUyODVjMTU2ODEwNzg2NzQwOSIsImlhdCI6MTU2ODEwNzg2Nywic3ViIjoie1widXNlcklkXCI6NH0iLCJleHAiOjE1NzcxMDc4Njd9.NK7UsAvZ1YRaVwCfwfPZnnC18LaHD2lo1F8DEVdIOBY");
        builder.addHeader("Accept-Language", ToolUtils.getLanguage());
        return chain.proceed(builder.build());
    }
}