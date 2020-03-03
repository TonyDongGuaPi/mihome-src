package com.mics.core.data.request;

import com.google.gson.annotations.SerializedName;
import com.mics.core.MiCS;

public class SessionCreate extends Session {
    private String channel = MiCS.a().i();
    @SerializedName("gids")
    private String gid;
    private String ip;
    private String locale = "zh_CN";
    private String merchantId;
    private String referer = MiCS.a().j();
    private String timeZone = "GMT+8";
    private String userName = MiCS.a().o();

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String str) {
        this.ip = str;
    }
}
