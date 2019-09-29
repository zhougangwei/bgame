package com.pay.administrator.bgame.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wjy on 2019/9/29/029.
 */
public class TagBean extends BaseBean {
    /**
     * data : [{"id":16,"nameZh":"无码","nameEn":"No pixelation","nameAl":"بدون فسيفساء"},{"id":15,"nameZh":"欧美","nameEn":"European","nameAl":"أروبية وأمريكية"},{"id":14,"nameZh":"巨乳","nameEn":"Big boobs","nameAl":"ثدي كبيرة"},{"id":13,"nameZh":"人妻","nameEn":"Milf","nameAl":"زوجة"},{"id":12,"nameZh":"清纯","nameEn":"Pure","nameAl":"نقية"},{"id":11,"nameZh":"动漫","nameEn":"Anime","nameAl":"رسوم متحركة"},{"id":10,"nameZh":"自慰","nameEn":"Masturbation","nameAl":"العادة السريعة"},{"id":9,"nameZh":"今日HOT","nameEn":"Today's hot","nameAl":"شائعة اليوم"},{"id":7,"nameZh":"角色扮演","nameEn":"Role play","nameAl":"لعب الأدوار"},{"id":8,"nameZh":"肥臀","nameEn":"Big ass","nameAl":"حمارة كبيرة"},{"id":19,"nameZh":"日本","nameEn":"Japanese","nameAl":"يابان"},{"id":20,"nameZh":"多人","nameEn":"Group","nameAl":"كثير من الأشخاص"},{"id":21,"nameZh":"SM","nameEn":"SM","nameAl":"SM"},{"id":22,"nameZh":"小胸","nameEn":"Small boobs","nameAl":"ثدي صغيرة"},{"id":23,"nameZh":"文艺片","nameEn":"Literary film","nameAl":"أفلام أدبية"}]
     * userid : null
     */

    @SerializedName("userid")
    private Object userid;
    @SerializedName("data")
    private List<DataBean> data;

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
