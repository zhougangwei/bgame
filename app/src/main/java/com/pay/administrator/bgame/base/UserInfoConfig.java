package com.pay.administrator.bgame.base;

import com.pay.administrator.bgame.utils.SPUtil;

/**
 * @author wjy on 2019/9/23/023.
 */
public class UserInfoConfig {

    public static int getUserId() {
        return SPUtil.getInstance().getUserId();
    }
    public static void setUserId(int uId) {
        SPUtil.getInstance().setUserId(uId);
    }

    public static void clearData() {
        SPUtil.getInstance().setToken("");
        setUserId(0);
    }
}
