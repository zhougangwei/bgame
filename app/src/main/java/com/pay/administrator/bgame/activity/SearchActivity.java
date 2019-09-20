package com.pay.administrator.bgame.activity;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.et_search)
    EditText     mEtSearch;
    @BindView(R.id.tv_cancel)
    TextView     mTvCancel;
    @BindView(R.id.rv)
    RecyclerView mRv;

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {


        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String search = mEtSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(search)) {
                        return false;
                    }
                    SearchRecord record = new SearchRecord();
                    record.setName(search);
                    record.setType(SearchRecordManager.RecordType.SearchUser);
                    searchRecords.clear();
                    searchRecords.addAll(SearchRecordManager.getInstance().insertOrReplace(record));
                    showSearchRecord();
                    mHistoryAdapter.notifyDataSetChanged();

                    startSearch();

                }
                return false;
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void hideSoftInput() {
        // 先隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick(R.id.tv_cancel)
    public void onClick() {
    }
}
