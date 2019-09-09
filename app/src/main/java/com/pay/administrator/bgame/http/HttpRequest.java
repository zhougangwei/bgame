package com.pay.administrator.bgame.http;


import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.LoginBean;
import com.pay.administrator.bgame.bean.TagBean;

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
    Observable<TagBean> getTagList(
    );

    @GET("/namol/api/app/v1/movie/{vid}")
    Observable<BaseBean> getVideoDetail(@Path("vid")String vid);

    /**
     * @param num which page
     */
    @GET("/namol/api/app/v1/search")
    Observable<BaseBean> searchVideo(@Query("num") int num,
    @Query("tag")int tag,@Query("play")boolean play,@Query("newest")boolean newest,@Query("like")boolean like
    );

    @GET("/namol/api/app/v1/find/{page}")
    Observable<BaseBean> getFindVideos(@Path("page") int page);

    @POST("/namol/api/app/v1/movie/like/{vid}")
    Observable<BaseBean> addVideoLike(@Path("vid")String vid);

    @POST("/namol/api/app/v1/movie/share/{vid}")
    Observable<BaseBean> addShare(@Path("vid")String vid);
}
