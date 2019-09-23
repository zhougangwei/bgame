package com.pay.administrator.bgame.http;


import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.HomeMovieBean;
import com.pay.administrator.bgame.bean.LoginBean;
import com.pay.administrator.bgame.bean.NoticeBean;
import com.pay.administrator.bgame.bean.TagBean;
import com.pay.administrator.bgame.bean.VideoBean;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Observable<BaseBean> searchVideo(@Query("page")int page,@Query("size")int size,@Query("content")String content
    );

    @GET("/namol/api/app/v1/find")
    Observable<TagBean> getFindVideos(@Query("page") int page,@Query("size")int pagesize);

    @POST("/namol/api/app/v1/movie/like/{vid}")
    Observable<BaseBean> addVideoLike(@Path("vid")String vid);

    @POST("/namol/api/app/v1/movie/share/{vid}")
    Observable<BaseBean> addShare(@Path("vid")String vid);

    @GET("/namol/api/app/v1/login")
    Observable<BaseBean> loginPhone();

    @POST("/namol/api/app/v1/sendMsgCode")
    Observable<BaseBean> sendMsgCode(@Query("telephone") String telephone);

    @POST("/namol/api/app/v1/userRegister")
    Observable<BaseBean> userRegister(@Body RequestBody requestBody);

    RequestBody userRegister(@JsonQuery String dataJson
    );

    @GET("/namol/api/app/v1/collect")
    Observable<TagBean> getMyLikeList(@Query("user_id")int userid);

    @GET("/namol/api/app/v1/noticeList")
    Observable<TagBean> getNoticeList(@Query("user_id")int userid,@Query("pageNum")int pageNum);

    @GET("/namol/api/app/v1/feedBackList")
    Observable<TagBean> getFeedbackList(@Query("user_id")int userid,@Query("pageNum")int pageNum);
}
