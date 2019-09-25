package com.pay.administrator.bgame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.http.BaseCosumer;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.utils.ResultUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EnterSmsActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv1)
    TextView     mTv1;
    @BindView(R.id.ll_register_code)
    LinearLayout mLlRegisterCode;

    @BindView(R.id.tv_send_again)
    TextView     mTvsendAgain;
    private List<EditText> editTextList;
    @BindView(R.id.et_code_1)
    EditText etCode1;
    @BindView(R.id.et_code_2)
    EditText etCode2;
    @BindView(R.id.et_code_3)
    EditText etCode3;
    @BindView(R.id.et_code_4)
    EditText etCode4;
    @BindView(R.id.et_code_5)
    EditText etCode5;
    @BindView(R.id.et_code_6)
    EditText etCode6;
    @BindView(R.id.tv_register_code_next)
    TextView tvRegisterCodeNext;
    private int currentPosition = 1;
    private String country;
    private String country_code;
    private String telephone;
    private String msg_code;



    @Override
    protected void initData() {

        getData();
    }

    private void getData() {
        RetrofitFactory.getInstance().sendMsgCode(telephone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean tagbean) {

                    }
                });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_enter_sms;
    }

    @Override
    protected void initView() {
        mTvTitle.setTextSize(15);
        tvRegisterCodeNext.setText("Next");
        mTvTitle.setText("6-digit verification code has been sent");
        Intent intent = getIntent();
        country= intent.getStringExtra("country");
        country_code= intent.getStringExtra("country_code");
        telephone= intent.getStringExtra("telephone");

        editTextList = new ArrayList<>();
        editTextList.add(etCode1);
        editTextList.add(etCode2);
        editTextList.add(etCode3);
        editTextList.add(etCode4);
        editTextList.add(etCode5);
        editTextList.add(etCode6);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(etCode1, 0);
            }
        }, 100);

    }

    @OnTextChanged(value = R.id.et_code_1, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged1(CharSequence s, int start, int before, int count) {
        requestFocus();
    }

    @OnTextChanged(value = R.id.et_code_2, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged2(CharSequence s, int start, int before, int count) {
        requestFocus();
    }

    @OnTextChanged(value = R.id.et_code_3, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged3(CharSequence s, int start, int before, int count) {
        requestFocus();
    }

    @OnTextChanged(value = R.id.et_code_4, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged4(CharSequence s, int start, int before, int count) {
        requestFocus();
    }

    @OnTextChanged(value = R.id.et_code_5, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged5(CharSequence s, int start, int before, int count) {
        requestFocus();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {

            int cur = -1;
            for (int i = editTextList.size() - 1; i >= 0; i--) {
                if (editTextList.get(i).getText().toString().length() > 0) {
                    cur = i;
                    break;
                }
            }
            if (cur != -1) {
                editTextList.get(cur).setText("");
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @OnTextChanged(value = R.id.et_code_6, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged6(CharSequence s, int start, int before, int count) {
        if (before == 1 && start == 0) {
            tvRegisterCodeNext.setSelected(false);
            tvRegisterCodeNext.setEnabled(false);
        } else if (before == 0 && start == 0) {
            tvRegisterCodeNext.setSelected(true);
            tvRegisterCodeNext.setEnabled(true);
        }
        requestFocus();
    }

    private void requestFocus() {
        currentPosition = 6;
        for (int i = 0; i < editTextList.size(); i++) {
            if (editTextList.get(i).getText().toString().length() == 0) {
                currentPosition = i + 1;
                break;
            }
        }
        editTextList.get(currentPosition - 1).requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @OnClick({R.id.iv_back,  R.id.tv_register_code_next, R.id.tv_send_again})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_register_code_next:
               startInvi();
                break;
            case R.id.tv_send_again:
                getData();
                break;
        }
    }
    private void startInvi() {
        msg_code  = etCode1.getText().toString() + etCode2.getText().toString() + etCode3.getText().toString() +
                etCode4.getText().toString() + etCode5.getText().toString() + etCode6.getText().toString();
        RetrofitFactory.getInstance().verSmsCode(ProxyPostHttpRequest.getJsonInstance().verSmsCode(telephone,msg_code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCosumer<BaseBean>() {
                    @Override
                    public void onGetData(BaseBean baseBean) {
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            Intent intent = new Intent(EnterSmsActivity.this, SetPasswordActivity.class);
                            intent.putExtra("country", country);
                            intent.putExtra("country_code", country_code);
                            intent.putExtra("telephone", telephone);
                            intent.putExtra("msg_code", msg_code);
                            startActivity(intent);
                        }
                    }
                });
    }
}
