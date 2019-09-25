package com.pay.administrator.bgame.bean.upbean;

import com.google.gson.annotations.SerializedName;

/**
 * @author wjy on 2019/9/25/025.
 */
public class UpFeedBean {
    /**
     * user_id : 1
     * type : 1
     * content :
     * url :
     */

    @SerializedName("user_id")
    private int userId;
    @SerializedName("type")
    private int type;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
