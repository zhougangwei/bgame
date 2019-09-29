package com.pay.administrator.bgame.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wjy on 2019/9/12/012.
 */
public class HomeMovieBean extends BaseBean {
    @SerializedName("data")
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * recommendMovie : [{"id":100048,"titleZh":"同步容器类 ConcurrentHashMap","titleEn":"ConcurrentHashMap","titleAl":"ConcurrentHashMap","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/3060c6549b8548e98abb146f761709291568045300338.jpg","score":9,"playCount":0},{"id":100049,"titleZh":"原子变量与 CAS 算法","titleEn":"CAS ","titleAl":"CAS ","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/b67e40db3ffc4b4bbde705819822c1e81568045281275.jpg","score":9,"playCount":0},{"id":100033,"titleZh":"同步容器类 ConcurrentHashMap mm","titleEn":"ConcurrentHashMap mm","titleAl":"ConcurrentHashMap mm","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/d4bea05e8b6b4e71adaabc82b44815251568045666545.jpg","score":9,"playCount":694},{"id":100034,"titleZh":"原子变量与 CAS 算法csd","titleEn":"CAScsd","titleAl":"CAScsd","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/d3c6ede41c4a444f886ef9ce0d9faa541568045644734.jpg","score":9,"playCount":1041},{"id":100044,"titleZh":"ForkJoinPool 分支合并框架","titleEn":"ForkJoinPool","titleAl":"ForkJoinPool","logo":"http://263d6t1992.wicp.vip/document/images/namol2019SEPTEMBER10/9a29e634e33846328367f7470f77973f1568045387733.jpg","score":9,"playCount":0}]
         * type : {"id":16,"nameZh":"无码","nameEn":"No pixelation","nameAl":"بدون فسيفساء"}
         */

        @SerializedName("type")
        private TypeBean type;
        @SerializedName("recommendMovie")
        private List<VideoListBean.DataBean> recommendMovie;

        private int dataType;

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public List<VideoListBean.DataBean> getRecommendMovie() {
            return recommendMovie;
        }

        public void setRecommendMovie(List<VideoListBean.DataBean> recommendMovie) {
            this.recommendMovie = recommendMovie;
        }

        @Override
        public int getItemType() {
            return dataType;
        }

        public static class TypeBean {
            /**
             * id : 16
             * nameZh : 无码
             * nameEn : No pixelation
             * nameAl : بدون فسيفساء
             */

            @SerializedName("id")
            private int id;
            @SerializedName("nameZh")
            private String nameZh;
            @SerializedName("nameEn")
            private String nameEn;
            @SerializedName("nameAl")
            private String nameAl;

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
        }


    }
}
