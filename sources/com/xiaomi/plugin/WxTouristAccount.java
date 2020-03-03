package com.xiaomi.plugin;

import com.taobao.weex.el.parse.Operators;

public class WxTouristAccount {
    private String avatarUrl;
    private String city;
    private String nickname;
    private String openId;
    private String province;
    private String sex;
    private String unionIdSign;

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.avatarUrl = str;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public String getUnionIdSign() {
        return this.unionIdSign;
    }

    public void setUnionIdSign(String str) {
        this.unionIdSign = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String toString() {
        return "WxTouristAccount{province='" + this.province + Operators.SINGLE_QUOTE + ", city='" + this.city + Operators.SINGLE_QUOTE + ", avatarUrl='" + this.avatarUrl + Operators.SINGLE_QUOTE + ", openId='" + this.openId + Operators.SINGLE_QUOTE + ", unionIdSign='" + this.unionIdSign + Operators.SINGLE_QUOTE + ", sex='" + this.sex + Operators.SINGLE_QUOTE + ", nickname='" + this.nickname + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
