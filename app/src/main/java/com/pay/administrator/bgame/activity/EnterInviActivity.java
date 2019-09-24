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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class EnterInviActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView    mIvBack;
    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.tv1)
    TextView     mTv1;
    @BindView(R.id.ll_register_code)
    LinearLayout mLlRegisterCode;
    @BindView(R.id.tv_submit)
    TextView     mTvSubmit;
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

    private String country;
    private String country_code;
    private String telephone;
    private String msg_code;
    private int currentPosition = 1;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_enter_invi;
    }

    @Override
    protected void initView() {
        mTvTitle.setText("Enter invitation code");
        mTvTitle.setTextSize(15);
        Intent intent = getIntent();
        country= intent.getStringExtra("country");
        country_code= intent.getStringExtra("country_code");
        telephone= intent.getStringExtra("telephone");
        msg_code= intent.getStringExtra("msg_code");

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
            mTvSubmit.setSelected(false);
            mTvSubmit.setEnabled(false);
        } else if (before == 0 && start == 0) {
            mTvSubmit.setSelected(true);
            mTvSubmit.setEnabled(true);
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

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.tv_skip, R.id.tv_send_again})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                gotoNext();
                break;
            case R.id.tv_send_again:
                break;
            case R.id.tv_skip:
                break;
        }
    }

    private void gotoNext() {
        String inviCode = etCode1.getText().toString() + etCode2.getText().toString() + etCode3.getText().toString() +
                etCode4.getText().toString() + etCode5.getText().toString() + etCode6.getText().toString();
        gotoSetPsw(inviCode);
    }

    private void gotoSetPsw(String inviCode) {
        Intent intent = new Intent(this, SetPasswordActivity.class);
        intent.putExtra("country", country);
        intent.putExtra("country_code", country_code);
        intent.putExtra("telephone", telephone);
        intent.putExtra("recommender_id", inviCode);
        intent.putExtra("msg_code", msg_code);
        startActivity(intent);
    }
}
