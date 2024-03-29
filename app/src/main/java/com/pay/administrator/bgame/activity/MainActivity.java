package com.pay.administrator.bgame.activity;

import android.view.View;
import android.widget.ImageView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.adapter.TabAdapter;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.base.UserInfoConfig;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.dialog.CommonDialog;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.SPUtil;
import com.pay.administrator.bgame.wight.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.vp)
    NoScrollViewPager vp;
    @BindView(R.id.iv_home)
    ImageView         ivHome;
    @BindView(R.id.iv_msg)
    ImageView         ivMsg;
    @BindView(R.id.iv_my)
    ImageView         ivMy;
    private long       oldTime;
    private TabAdapter mTabAdapter;

    List<View> viewList = new ArrayList<>();




    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }




    @Override
    protected void initView() {

        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        vp.setAdapter(mTabAdapter);
        vp.setOffscreenPageLimit(5);
        vp.setCurrentItem(0, false);
        viewList.add(ivHome);
        viewList.add(ivMsg);
        viewList.add(ivMy);
        setClicked(ivHome);
        judgeFirst();
    }

    private void judgeFirst() {
        if (SPUtil.getInstance().isFirstTime()){
            RetrofitFactory.getInstance().appInstall()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseCosumer<BaseBean>() {
                        @Override
                        public void onGetData(BaseBean baseBean) {
                            if (ResultUtils.cheekSuccess(baseBean)) {
                                SPUtil.getInstance().setFirstTime();
                            }
                        }
                    });
        }
        RetrofitFactory.getInstance().active()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                        }
                    }
                });
    }

    @Override
    protected void initData() {

    }
    @Override
    public void onBackPressed() {
        long newTime = System.currentTimeMillis();
        if (newTime - oldTime < 3000) {


            finish();
        } else {
            new CommonDialog(this)
                    .setMsg(getString(R.string.want_to_log_out))
                    .setListener(new CommonDialog.OnClickListener() {
                        @Override
                        public void onClickConfirm() {
                            UserInfoConfig.clearData();

                            finish();
                        }
                        @Override
                        public void onClickCancel() {
                        }
                    })
                    .show();
        }
        oldTime = newTime;

    }


    @OnClick({R.id.iv_home, R.id.iv_msg, R.id.iv_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                setClicked(ivHome);
                break;
            case R.id.iv_msg:
                setClicked(ivMsg);
                break;
            case R.id.iv_my:
                setClicked(ivMy);
                break;
        }
    }

    private void setClicked(ImageView clickView) {
        for (int i = 0; i < viewList.size(); i++) {
            View view = viewList.get(i);
            if (view != clickView) {
                view.setSelected(false);
            } else {
                view.setSelected(true);
                vp.setCurrentItem(i, false);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}


