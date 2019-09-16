package com.pay.administrator.bgame.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wjy on 2019/8/28/028.
 */
public class TagBean extends BaseBean implements MultiItemEntity {

    public static final int TAGBEAN_MID_TYPE=0;
    public static final int TAGBEAN_DETAIL=1;
    @SerializedName("data")
    private List<DataBean> data;

    @Override
    public int getItemType() {
        return 0;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 100057
         * titleZh : ReadWriteLock 读写锁
         * titleEn : ReadWriteLock
         * titleAl : ReadWriteLock
         * logo : http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3ba59e0c49f041668d3b28f3e82b5c661568045117814.jpg
         * score : 9.0
         * playCount : 0
         */

        @SerializedName("id")
        private int id;
        @SerializedName("titleZh")
        private String titleZh;
        @SerializedName("titleEn")
        private String titleEn;
        @SerializedName("titleAl")
        private String titleAl;
        @SerializedName("logo")
        private String logo;
        @SerializedName("score")
        private double score;
        @SerializedName("playCount")
        private int playCount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitleZh() {
            return titleZh;
        }

        public void setTitleZh(String titleZh) {
            this.titleZh = titleZh;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public String getTitleAl() {
            return titleAl;
        }

        public void setTitleAl(String titleAl) {
            this.titleAl = titleAl;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }
    }
}
