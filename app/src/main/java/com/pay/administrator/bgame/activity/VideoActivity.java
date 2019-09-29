package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.VideoBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.CacheDataSourceFactory;
import com.pay.administrator.bgame.utils.GsonUtil;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToastUtils;
import com.pay.administrator.bgame.utils.ToolUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

;

public class VideoActivity extends BaseActivity {

    private static final String TAG = "VideoActivity";
    @BindView(R.id.player)
    SimpleExoPlayerView playerView;
    @BindView(R.id.iv_like)
    ImageView           mIvLike;
    @BindView(R.id.textView12)
    TextView            mTextView12;
    @BindView(R.id.iv_report)
    ImageView           mIvReport;
    @BindView(R.id.iv_share)
    ImageView           mIvShare;
    @BindView(R.id.tv_title)
    TextView            mTvTitle;
    @BindView(R.id.tv_look_times)
    TextView            mTvLookTimes;
    @BindView(R.id.textView10)
    TextView            mTextView10;
    @BindView(R.id.textView11)
    TextView            mTextView11;
    @BindView(R.id.textView13)
    TextView            mTextView13;

    private String               vid;
    private SimpleExoPlayer      exoPlayer;
    private ExtractorMediaSource videoSource;
    private LoopingMediaSource   curVideoSource;

    public static void startVideo(Context context, int vid) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("vid", vid + "");
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        RetrofitFactory.getInstance().getVideoDetail(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<VideoBean>() {
                    @Override
                    public void onGetData(VideoBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {

                            VideoBean.DataBean data = baseBean.getData();
                            mTvLookTimes.setText(data.getPlayCount()+"times");
                            if (data.isLikeFlag()){
                                mIvLike.setSelected(true);
                            }else{
                                mIvLike.setSelected(false);
                            }
                            String content="";
                            switch (ToolUtils.getLanguage()) {
                                case Contact.LANGUAGE_CHINA:
                                    content=data.getTitleZh();
                                    break;
                                case Contact.LANGUAGE_ENGLISH:
                                    content=data.getTitleEn();
                                    break;
                                case Contact.LANGUAGE_AR:
                                    content=data.getTitleAl();
                                    break;
                                default:
                                    content=data.getTitleEn();
                                    break;
                            }
                            mTvTitle.setText(content);
                            getMediaSource(data.getUrl());
                        }else if(Contact.REPONSE_CODE_NO_VIDEO_TIMES==baseBean.getCode()){
                            ToastUtils.showToast(VideoActivity.this,"you have no free times!");
                        }
                    }
                });
    }

    private void getMediaSource(String url) {
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        CacheDataSourceFactory cacheDataSourceFactory = new CacheDataSourceFactory(this, 200 * 1024 * 1024, 20 *
                1024 * 1024);
        videoSource = new ExtractorMediaSource(Uri.parse(url),
                cacheDataSourceFactory, extractorsFactory, null, null);
        curVideoSource = new LoopingMediaSource(videoSource);
        exoPlayer.prepare(curVideoSource);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        initIntent();
        initExoplayer();
        initExo();
    }

    private void initIntent() {
        Intent intent = getIntent();
        vid = intent.getStringExtra("vid");
    }


    private void addLike() {
        RetrofitFactory.getInstance().addVideoLike(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {
                        Log.e(TAG, "onGetData: "+ GsonUtil.parseObjectToJson(tagbean));
                       
                        if (ResultUtils.cheekSuccess(tagbean)) {
                            ToastUtils.showToast(VideoActivity.this,"like success!");
                        }
                    }
                });
    }

    private void addShare() {
        RetrofitFactory.getInstance().addShare(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {
                        ToastUtils.showToast(VideoActivity.this,"share success!");
                    }
                });
    }


    private void initExoplayer() {

        /*用来接收  滑动到哪儿了 但是 然后延迟一秒再去拿数据
         * throttleLast 操作符
         * */
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        RenderersFactory renderersFactory = new DefaultRenderersFactory(this);
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
        exoPlayer.setVolume(0);
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);


        exoPlayer.setPlayWhenReady(true);

    }

    private void initExo() {

        SurfaceView videoSurfaceView = (SurfaceView) playerView.getVideoSurfaceView();

        //        ViewGroup.LayoutParams layoutParams = playerView.getLayoutParams();
        //  layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        //  layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        //   layoutParams.gravity= Gravity.CENTER;
        //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        //        playerView.setLayoutParams(layoutParams);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);

        if (videoSurfaceView != null) {
            videoSurfaceView.setZOrderOnTop(true);
            videoSurfaceView.setZOrderMediaOverlay(true);
            SurfaceHolder videoSurfaceViewHolder = videoSurfaceView.getHolder();
            videoSurfaceViewHolder.setFormat(PixelFormat.TRANSPARENT);
        }
        playerView.setPlayer(exoPlayer);
        playerView.setUseController(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_like, R.id.iv_report, R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                addLike();
                break;
            case R.id.iv_report:
                ToastUtils.showToast(this,"Report success!");
                break;
            case R.id.iv_share:
                addShare();
                break;
        }
    }
}
