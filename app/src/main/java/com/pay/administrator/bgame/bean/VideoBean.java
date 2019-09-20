package com.pay.administrator.bgame.bean;

import java.util.List;

/**
 * @ 创建者   zhou
 * @ 创建时间   2019/9/20 0020 22:19
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2019/9/20 0020$
 * @ 更新描述  ${TODO}
 */
public class VideoBean  extends BaseBean{
    /**
     * data : {"id":100048,"titleZh":"同步容器类 ConcurrentHashMap","titleEn":"ConcurrentHashMap","titleAl":"ConcurrentHashMap","infoZh":"同步容器类 ConcurrentHashMap","infoEn":"ConcurrentHashMap","infoAl":"ConcurrentHashMap","url":"http://263d6t1992.wicp.vip/document/videos/namol2019SEPTEMBER9/f6f4d01d61c140ecae8b83ac650ff19f1568044076159.avi","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3060c6549b8548e98abb146f761709291568045300338.jpg","score":9,"playCount":694,"aboutRecommend":[{"id":100057,"titleZh":"ReadWriteLock 读写锁","titleEn":"ReadWriteLock ","titleAl":"ReadWriteLock ","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3ba59e0c49f041668d3b28f3e82b5c661568045117814.jpg","score":9,"playCount":2776},{"id":100033,"titleZh":"同步容器类 ConcurrentHashMap mm","titleEn":"ConcurrentHashMap mm","titleAl":"ConcurrentHashMap mm","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/d4bea05e8b6b4e71adaabc82b44815251568045666545.jpg","score":9,"playCount":4164}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 100048
         * titleZh : 同步容器类 ConcurrentHashMap
         * titleEn : ConcurrentHashMap
         * titleAl : ConcurrentHashMap
         * infoZh : 同步容器类 ConcurrentHashMap
         * infoEn : ConcurrentHashMap
         * infoAl : ConcurrentHashMap
         * url : http://263d6t1992.wicp.vip/document/videos/namol2019SEPTEMBER9/f6f4d01d61c140ecae8b83ac650ff19f1568044076159.avi
         * logo : http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3060c6549b8548e98abb146f761709291568045300338.jpg
         * score : 9.0
         * playCount : 694
         * aboutRecommend : [{"id":100057,"titleZh":"ReadWriteLock 读写锁","titleEn":"ReadWriteLock ","titleAl":"ReadWriteLock ","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3ba59e0c49f041668d3b28f3e82b5c661568045117814.jpg","score":9,"playCount":2776},{"id":100033,"titleZh":"同步容器类 ConcurrentHashMap mm","titleEn":"ConcurrentHashMap mm","titleAl":"ConcurrentHashMap mm","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/d4bea05e8b6b4e71adaabc82b44815251568045666545.jpg","score":9,"playCount":4164}]
         */

        private int id;
        private String                   titleZh;
        private String                   titleEn;
        private String                   titleAl;
        private String                   infoZh;
        private String                   infoEn;
        private String                   infoAl;
        private String                   url;
        private String                   logo;
        private double                   score;
        private int                      playCount;
        private List<AboutRecommendBean> aboutRecommend;

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

        public String getInfoZh() {
            return infoZh;
        }

        public void setInfoZh(String infoZh) {
            this.infoZh = infoZh;
        }

        public String getInfoEn() {
            return infoEn;
        }

        public void setInfoEn(String infoEn) {
            this.infoEn = infoEn;
        }

        public String getInfoAl() {
            return infoAl;
        }

        public void setInfoAl(String infoAl) {
            this.infoAl = infoAl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public List<AboutRecommendBean> getAboutRecommend() {
            return aboutRecommend;
        }

        public void setAboutRecommend(List<AboutRecommendBean> aboutRecommend) {
            this.aboutRecommend = aboutRecommend;
        }

        public static class AboutRecommendBean {
            /**
             * id : 100057
             * titleZh : ReadWriteLock 读写锁
             * titleEn : ReadWriteLock
             * titleAl : ReadWriteLock
             * logo : http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3ba59e0c49f041668d3b28f3e82b5c661568045117814.jpg
             * score : 9.0
             * playCount : 2776
             */

            private int id;
            private String titleZh;
            private String titleEn;
            private String titleAl;
            private String logo;
            private double score;
            private int    playCount;

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
}
