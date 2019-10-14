package com.pay.administrator.bgame.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author wjy on 2019/10/14/014.
 */
public class OrderBean extends BaseBean {
    /**
     * data : NL2019101415073700001
     * userid : 23
     */

    @SerializedName("data")
    private String data;
    @SerializedName("userid")
    private int userid;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
