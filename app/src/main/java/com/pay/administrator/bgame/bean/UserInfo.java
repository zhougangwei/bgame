package com.pay.administrator.bgame.bean;

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
     * data : {"id":22,"account":"NL1848700682","password":"e10adc3949ba59abbe56e057f20f883e","name":"Namol_dESPel","countryCode":"cn","country":"中国","telephone":"18368095279","icon":"http://263d6t1992.wicp.vip/document/photos/6633a08d96914493a0320d5422d672961566728224875.jpg","money":0,"level":0,"score":0,"createTime":1569307702160,"registerDevice":"huawei p30","passTime":null,"type":1,"status":1,"lastLoginTime":1569327466472,"lastAppVersion":null,"recommenderId":1,"remanidCount":3}
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
         * id : 22
         * account : NL1848700682
         * password : e10adc3949ba59abbe56e057f20f883e
         * name : Namol_dESPel
         * countryCode : cn
         * country : 中国
         * telephone : 18368095279
         * icon : http://263d6t1992.wicp.vip/document/photos/6633a08d96914493a0320d5422d672961566728224875.jpg
         * money : 0.0
         * level : 0
         * score : 0.0
         * createTime : 1569307702160
         * registerDevice : huawei p30
         * passTime : null
         * type : 1
         * status : 1
         * lastLoginTime : 1569327466472
         * lastAppVersion : null
         * recommenderId : 1
         * remanidCount : 3
         */

        private int id;
        private String account;
        private String password;
        private String name;
        private String countryCode;
        private String country;
        private String telephone;
        private String icon;
        private double money;
        private int    level;
        private double score;
        private long   createTime;
        private String registerDevice;
        private Object passTime;
        private int    type;
        private int    status;
        private long   lastLoginTime;
        private Object lastAppVersion;
        private int    recommenderId;
        private int    remanidCount;

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

        public Object getPassTime() {
            return passTime;
        }

        public void setPassTime(Object passTime) {
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

        public int getRecommenderId() {
            return recommenderId;
        }

        public void setRecommenderId(int recommenderId) {
            this.recommenderId = recommenderId;
        }

        public int getRemanidCount() {
            return remanidCount;
        }

        public void setRemanidCount(int remanidCount) {
            this.remanidCount = remanidCount;
        }
    }
}
