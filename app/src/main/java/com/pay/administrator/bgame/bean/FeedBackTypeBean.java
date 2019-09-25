package com.pay.administrator.bgame.bean;

/**
 * @author wjy on 2019/9/25/025.
 */
public class FeedBackTypeBean {
    String content;
    boolean check;


    public FeedBackTypeBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
