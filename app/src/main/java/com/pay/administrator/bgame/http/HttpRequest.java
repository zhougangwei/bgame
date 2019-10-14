package com.pay.administrator.bgame.http;


import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.bean.LikeBean;
import com.pay.administrator.bgame.bean.LoginBean;
import com.pay.administrator.bgame.bean.NoticeBean;
import com.pay.administrator.bgame.bean.OrderBean;
import com.pay.administrator.bgame.bean.PicBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.bean.VideoListBean;
import com.pay.administrator.bgame.bean.UserInfo;
import com.pay.administrator.bgame.bean.VideoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kiddo on 2017/11/29.
 */

public interface HttpRequest {

    @GET("/namol/api/app/v1/movie")
    Observable<HomeMovieBean> getHomeMovie(
    );

    @GET("/namol/api/app/v1/movie/{vid}")
    Observable<VideoBean> getVideoDetail(@Path("vid")String vid);


    @GET("/namol/api/app/v1/search")
    Observable<VideoListBean> searchVideo(@Query("page")int page, @Query("size")int size, @Query("content")String content
    );
    @GET("/namol/api/app/v1/search")
    Observable<VideoListBean> searchVideo(@Query("page")int page, @Query("size")int size, @Query("tag")int tag
    );

    @GET("/namol/api/app/v1/find")
    Observable<VideoListBean> getFindVideos(@Query("page") int page, @Query("size")int pagesize);

    @POST("/namol/api/app/v1/movie/like/{vid}")
    Observable<BaseBean> addVideoLike(@Path("vid")String vid);

    @POST("/namol/api/app/v1/movie/share/{vid}")
    Observable<BaseBean> addShare(@Path("vid")String vid);

    @POST("/namol/api/app/v1/login")
    Observable<LoginBean> loginPhone(@Body RequestBody requestBody);

    RequestBody loginPhone(@Query("telephone")String telephone,@Query("password")String password
    );


    @POST("/namol/api/app/v1/sendMsgCode")
    Observable<BaseBean> sendMsgCode(@Query("telephone") String telephone);

    @POST("/namol/api/app/v1/userRegister")
    Observable<BaseBean> userRegister(@Body RequestBody requestBody);

    RequestBody userRegister(@JsonQuery String dataJson
    );
    @POST("/namol/api/app/v1/checkMsgCode")
    Observable<BaseBean> verSmsCode(@Body RequestBody requestBody);

    RequestBody verSmsCode(@Query("telephone")String telephone,@Query("code")String code
    );

    @POST("/namol/api/app/v1/appInstall")
    Observable<BaseBean> appInstall();

    @GET("/namol/api/app/v1/userInfo")
    Observable<UserInfo> getuserInfo();

    @GET("/namol/api/app/v1/noticeList")
    Observable<NoticeBean> getNoticeList(@Query("pageNum") int page, @Query("user_id")int user_id);

    @GET("/namol/api/app/v1/collect")
    Observable<LikeBean> getLikeVideo(@Query("user_id")int userId);

    @POST("/namol/api/app/v1/feedBack")
    Observable<BaseBean> feedBack(@Body RequestBody requestBody);
    RequestBody feedBack(@JsonQuery String dataJson
    );

    @Multipart
    @POST("/namol/api/app/v1/upload?type=FEEDBACK")
    Observable<PicBean> uploadPic(
            @Part MultipartBody.Part... files);
    @GET("/namol/api/app/v1/feedBackList")
    Observable<VideoListBean> getFeedbackList(@Query("pageNum")int pageNum);

    @GET("/namol/api/app/v1/perActive")
    Observable<BaseBean> active();

    @GET("/namol/api/app/v1/tag")
    Observable<TagBean> getTagList();

    @POST("/namol/api/app/v1/delCollect")
    Observable<BaseBean> deleteLike(@Body RequestBody requestBody);
    RequestBody deleteLike( @Query("collect_ids")String collect_ids
    );

    @POST("/namol/api/app/v1/uploadPhoto")
    Observable<BaseBean> changeHeadUrl(@Body RequestBody requestBody);
    RequestBody changeHeadUrl(@Query("user_id")int userId, @Query("url")String url
    );

    @POST("/namol/api/app/v1/updateTel")
    Observable<BaseBean> updateTel(@Body RequestBody requestBody);
    RequestBody updateTel(@Query("new_telephone")String new_telephone, @Query("msg_code")String msg_code
    );


    @POST("/namol/api/app/v1/resetPwd")
    Observable<BaseBean> resetPwd(@Body RequestBody requestBody);
    RequestBody resetPwd(@Query("telephone")String telephone, @Query("new_telephone")String new_telephone,@Query("msg_code")String msg_code
    );


    @POST("/namol/api/app/v1/googlePay")
    Observable<BaseBean> googleUpdateProduct(@Body RequestBody requestBody);
    RequestBody googleUpdateProduct( @Query("purchase_token")String purchase_token,@Query("product_id") String product_id, @Query("order_no")String order_no);




    @POST("/namol/api/app/v1/addOrder")
    Observable<OrderBean> addProduct(@Body RequestBody requestBody);
    RequestBody addProduct(@Query("product_id")String product_id
    );
}
