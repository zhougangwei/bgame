package com.pay.administrator.bgame.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.EditHeadAdapter;
import com.pay.administrator.bgame.adapter.FeedBackTypeAdapter;
import com.pay.administrator.bgame.base.BaseFragment;
import com.pay.administrator.bgame.base.UserInfoConfig;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.FeedBackTypeBean;
import com.pay.administrator.bgame.bean.PicBean;
import com.pay.administrator.bgame.bean.upbean.UpFeedBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.GsonUtil;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToastUtils;
import com.pay.administrator.bgame.wight.ChoosePicManager;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class FeedbackFragment extends BaseFragment {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.rv_feed_type)
    RecyclerView rvFeedType;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_pic)
    RecyclerView rcPic;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;


    private List<FeedBackTypeBean> datas = new ArrayList<>();
    private FeedBackTypeAdapter feedBackTypeAdapter;
    private Paint mPaint;
    private List<EditHeadAdapter.UserHead> mList;
    private              List<String> uriList             = new ArrayList<>();
    private String mediaurl;
    private String upUrl;
    private EditHeadAdapter mAdapter;
    private String TAG= "FeedbackFragment";

    @Override
    protected void initView() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(SizeUtils.sp2px(getActivity(), 14));
        mPaint.setStyle(Paint.Style.FILL);
        mList=new ArrayList<>();
        mList.add(new EditHeadAdapter.UserHead("", true));
        mAdapter = new EditHeadAdapter(mList);
        mAdapter.setEnableLoadMore(false);
        rcPic.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        rcPic.setItemAnimator(new DefaultItemAnimator());
        rcPic.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mList.get(position).isAdd()) {
                    ChoosePicManager.choosePic(FeedbackFragment.this, 1);
                }
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 100);
        rvFeedType.setLayoutManager(gridLayoutManager);
        feedBackTypeAdapter = new FeedBackTypeAdapter(R.layout.item_feedback_type, datas);
        rvFeedType.setAdapter(feedBackTypeAdapter);
        feedBackTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FeedBackTypeBean dataBean = datas.get(position);
                dataBean.setCheck(!dataBean.isCheck());
                feedBackTypeAdapter.notifyDataSetChanged();
            }
        });
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int width = ScreenUtils.getScreenWidth(getActivity()) - SizeUtils.dp2px(getActivity(), 44);
                int itemWidth = getTextWidth(mPaint, datas.get(position).getContent()) + SizeUtils.dp2px(getActivity(), 50);
                return Math.min(100, itemWidth * 100 / width + 1);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmeng_feedback;
    }

    @Override
    protected void initData() {

        datas.add(new FeedBackTypeBean("Unable to play"));
        datas.add(new FeedBackTypeBean("play card"));
        datas.add(new FeedBackTypeBean("incomplete video"));
        datas.add(new FeedBackTypeBean("Inaccurate search"));
        datas.add(new FeedBackTypeBean("Other"));
        feedBackTypeAdapter.notifyDataSetChanged();

    }

    private int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    @OnClick({R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if(TextUtils.isEmpty(mediaurl)){
                    submitPic();
                }else{
                    gotoSubmit();
                }

                //
                break;


        }
    }

    private void submitPic() {
        File file = new File(mediaurl);
        RequestBody requestFile = RequestBody.create(MediaType.parse("text/plain"), file);
        MultipartBody.Part coverBody = MultipartBody.Part.createFormData("multipartFile", null,
                requestFile);
        RetrofitFactory.getInstance().uploadPic(coverBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<PicBean>() {
                    @Override
                    public void onGetData(PicBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            upUrl=baseBean.getData();
                            gotoSubmit();
                        }
                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ChoosePicManager.REQUEST_CODE_CHOOSE) {
            List<String> strings = Matisse.obtainPathResult(data);
            if (strings != null && strings.size() > 0) {
                uriList.clear();
                uriList.addAll(strings);
                for (String s : uriList) {
                    mList.add(new EditHeadAdapter.UserHead(s, false));
                }
                mediaurl = uriList.get(0);
                mAdapter.notifyDataSetChanged();
            }
        }

    }


    private void gotoSubmit() {
        StringBuilder content=new StringBuilder();
        for (FeedBackTypeBean data : datas) {
            content.append(data.getContent()).append(",");
        }
        UpFeedBean upFeedBean = new UpFeedBean();
        upFeedBean.setContent(content.toString());
        upFeedBean.setType(1);
       upFeedBean.setUrl(upUrl);
        upFeedBean.setUserId(UserInfoConfig.getUserId());
        RetrofitFactory.getInstance().feedBack(ProxyPostHttpRequest.getJsonInstance().feedBack(
                GsonUtil.parseObjectToJson(upFeedBean)
        )).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            ToastUtils.showToast(getActivity(),"反馈成功!");
                        }
                    }
                });
    }
}
