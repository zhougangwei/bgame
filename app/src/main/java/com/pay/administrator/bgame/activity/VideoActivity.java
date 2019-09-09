package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.player)
    SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;
    private String vid;
    private SimpleExoPlayer exoPlayer;
    private ExtractorMediaSource videoSource;
    private LoopingMediaSource curVideoSource;




    public  static void startVideo(Context context,int vid){
        Intent intent = new Intent(context,VideoActivity.class);
        intent.putExtra("vid",vid);
        context.startActivity(intent);

    }
    @Override
    protected void initData() {
        RetrofitFactory.getInstance().getVideoDetail(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            getMediaSource();
                        }
                    }
                });
    }

    private void getMediaSource() {
       // Uri uri = Uri.parse(getString(R.string.media_url_mp4));
       // MediaSource mediaSource = buildMediaSource(uri);
       // player.prepare(mediaSource, true,

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        initIntent(); int []arr =new int[]{};
        initExoplayer();
        initExo();
    }




    private void initIntent() {
        Intent intent = getIntent();
        vid = intent.getStringExtra("vid");
    }


    private void addLike(){
        RetrofitFactory.getInstance().addVideoLike(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {

                    }
                });
    }
    private void addShare(){
        RetrofitFactory.getInstance().addShare(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {

                    }
                });
    }




    private void initExoplayer() {
        try {
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
            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.loginvideo));
            final RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(this);
            rawResourceDataSource.open(dataSpec);


            DataSource.Factory factory = new DataSource.Factory() {
                @Override
                public DataSource createDataSource() {
                    return rawResourceDataSource;
                }
            };


            videoSource = new ExtractorMediaSource(rawResourceDataSource.getUri(),
                    factory, extractorsFactory, null, null);
            curVideoSource = new LoopingMediaSource(videoSource);
            exoPlayer.prepare(curVideoSource);

            exoPlayer.setPlayWhenReady(true);
        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
        }
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
}
