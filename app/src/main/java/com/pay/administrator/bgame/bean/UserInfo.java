package com.pay.administrator.bgame.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @ 创建者   zhou
 * @ 创建时间   2019/9/24 0024 22:17
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2019/9/24 0024$
 * @ 更新描述  ${TODO}
 */
public class UserInfo extends BaseBean {

    /**
     * data : {"id":22,"account":"NL1848700682","password":"","name":"Namol_dESPel","countryCode":"cn","country":"中国","telephone":"18368095279","icon":"http://263d6t1992.wicp.vip/document/photos/6633a08d96914493a0320d5422d672961566728224875.jpg","money":0,"level":3,"score":0,"createTime":1569307702160,"registerDevice":"huawei p30","passTime":"2019-10-25 18:02:57","type":1,"status":1,"lastLoginTime":1569563282461,"lastAppVersion":null,"recommenderId":"","regType":0,"invCode":null,"remanidCount":999999,"invitedNum":0}
     */

    @SerializedName("data")
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 22
         * account : NL1848700682
         * password :
         * name : Namol_dESPel
         * countryCode : cn
         * country : 中国
         * telephone : 18368095279
         * icon : http://263d6t1992.wicp.vip/document/photos/6633a08d96914493a0320d5422d672961566728224875.jpg
         * money : 0.0
         * level : 3
         * score : 0.0
         * createTime : 1569307702160
         * registerDevice : huawei p30
         * passTime : 2019-10-25 18:02:57
         * type : 1
         * status : 1
         * lastLoginTime : 1569563282461
         * lastAppVersion : null
         * recommenderId :
         * regType : 0
         * invCode : null
         * remanidCount : 999999
         * invitedNum : 0
         */

        @SerializedName("id")
        private int id;
        @SerializedName("account")
        private String account;
        @SerializedName("password")
        private String password;
        @SerializedName("name")
        private String name;
        @SerializedName("countryCode")
        private String countryCode;
        @SerializedName("country")
        private String country;
        @SerializedName("telephone")
        private String telephone;
        @SerializedName("icon")
        private String icon;
        @SerializedName("money")
        private double money;
        @SerializedName("level")
        private int level;
        @SerializedName("score")
        private double score;
        @SerializedName("createTime")
        private long createTime;
        @SerializedName("registerDevice")
        private String registerDevice;
        @SerializedName("passTime")
        private String passTime;
        @SerializedName("type")
        private int type;
        @SerializedName("status")
        private int status;
        @SerializedName("lastLoginTime")
        private long lastLoginTime;
        @SerializedName("lastAppVersion")
        private Object lastAppVersion;
        @SerializedName("recommenderId")
        private String recommenderId;
        @SerializedName("regType")
        private int regType;
        @SerializedName("invCode")
        private String invCode;
        @SerializedName("remanidCount")
        private int remanidCount;
        @SerializedName("invitedNum")
        private int invitedNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getRegisterDevice() {
            return registerDevice;
        }

        public void setRegisterDevice(String registerDevice) {
            this.registerDevice = registerDevice;
        }

        public String getPassTime() {
            return passTime;
        }

        public void setPassTime(String passTime) {
            this.passTime = passTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public Object getLastAppVersion() {
            return lastAppVersion;
        }

        public void setLastAppVersion(Object lastAppVersion) {
            this.lastAppVersion = lastAppVersion;
        }

        public String getRecommenderId() {
            return recommenderId;
        }

        public void setRecommenderId(String recommenderId) {
            this.recommenderId = recommenderId;
        }

        public int getRegType() {
            return regType;
        }

        public void setRegType(int regType) {
            this.regType = regType;
        }

        public String getInvCode() {
            return invCode;
        }

        public void setInvCode(String invCode) {
            this.invCode = invCode;
        }

        public int getRemanidCount() {
            return remanidCount;
        }

        public void setRemanidCount(int remanidCount) {
            this.remanidCount = remanidCount;
        }

        public int getInvitedNum() {
            return invitedNum;
        }

        public void setInvitedNum(int invitedNum) {
            this.invitedNum = invitedNum;
        }
    }
}
