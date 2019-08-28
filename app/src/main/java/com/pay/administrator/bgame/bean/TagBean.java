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
         * bestMovie : []
         * type : {"id":16,"nameZh":"社会","nameEn":"Sociology","nameAl":"Sociology","createTime":null}
         * recMovie : []
         */

        @SerializedName("type")
        private TypeBean type;
        @SerializedName("bestMovie")
        private List<MovieBean> bestMovie;
        @SerializedName("recMovie")
        private List<MovieBean> recMovie;

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public List<MovieBean> getBestMovie() {
            return bestMovie;
        }

        public void setBestMovie(List<MovieBean> bestMovie) {
            this.bestMovie = bestMovie;
        }

        public List<MovieBean> getRecMovie() {
            return recMovie;
        }

        public void setRecMovie(List<MovieBean> recMovie) {
            this.recMovie = recMovie;
        }

        public static class TypeBean {
            /**
             * id : 16
             * nameZh : 社会
             * nameEn : Sociology
             * nameAl : Sociology
             * createTime : null
             */

            @SerializedName("id")
            private int id;
            @SerializedName("nameZh")
            private String nameZh;
            @SerializedName("nameEn")
            private String nameEn;
            @SerializedName("nameAl")
            private String nameAl;
            @SerializedName("createTime")
            private Object createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNameZh() {
                return nameZh;
            }

            public void setNameZh(String nameZh) {
                this.nameZh = nameZh;
            }

            public String getNameEn() {
                return nameEn;
            }

            public void setNameEn(String nameEn) {
                this.nameEn = nameEn;
            }

            public String getNameAl() {
                return nameAl;
            }

            public void setNameAl(String nameAl) {
                this.nameAl = nameAl;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }
        }

        public static  class MovieBean{

            /**
             * id : 100019
             * titleZh : Linux和Unix的关系
             * titleEn :
             * titleAl :
             * logo : http://263d6t1992.wicp.vip/document/images\02893cc49f404da7b10db004efc070ce1566728127493.jpg
             * score : null
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
            private Object score;
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

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
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
}
