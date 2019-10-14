package com.pay.administrator.bgame.pay;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.pay.administrator.bgame.R;


public class DialogCircleProgress extends Dialog {

    public DialogCircleProgress(Context context) {
        super(context, R.style.dialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progress);

        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }
}
