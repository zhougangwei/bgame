package com.pay.administrator.bgame.bean;

/**
 * @ 创建者   zhou
 * @ 创建时间   2019/9/22 0022 22:01
 * @ 描述    ${TODO}
 * @ 更新者  $AUTHOR$
 * @ 更新时间    2019/9/22 0022$
 * @ 更新描述  ${TODO}
 */
public class UserRegisterBean {
    /**
     * country_code : cn
     * country : 中国
     * telephone :
     * password : 123456
     * recommender_id :
     * register_device : iphoneX
     * msg_code :
     */

    private String country_code;
    private String country;
    private String telephone;
    private String password;
    private String recommender_id;
    private String register_device;
    private String msg_code;

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecommender_id() {
        return recommender_id;
    }

    public void setRecommender_id(String recommender_id) {
        this.recommender_id = recommender_id;
    }

    public String getRegister_device() {
        return register_device;
    }

    public void setRegister_device(String register_device) {
        this.register_device = register_device;
    }

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }
}
