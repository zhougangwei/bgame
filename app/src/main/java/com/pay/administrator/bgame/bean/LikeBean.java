package com.pay.administrator.bgame.bean;

import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2019/9/24 0024 23:56
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2019/9/24 0024$
 * @ 更新描述  ${TODO}
 */
public class LikeBean  extends BaseBean{
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 22
         * movieId : 100051
         * titleZh : 同步锁 Lock
         * titleEn : Lock
         * titleAl : Lock
         * logo : http://namol.cn:8888\document\images\namol2019SEPTEMBER22\0d8498be35c94d5694eb9d7cf881490a1569119486192.jpg
         * state : 1
         */

        private int userId;
        private int    movieId;
        private String titleZh;
        private String titleEn;
        private String titleAl;
        private String logo;
        private int    state;

        //编辑用
        private boolean edit;
        private boolean selectEdit;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
        public void setEdit(boolean edit) {
            this.edit = edit;
        }

        public boolean getEdit() {
            return edit;
        }

        public boolean isSelectEdit() {
            return selectEdit;
        }

        public void setSelectEdit(boolean selectEdit) {
            this.selectEdit = selectEdit;
        }
    }
}
