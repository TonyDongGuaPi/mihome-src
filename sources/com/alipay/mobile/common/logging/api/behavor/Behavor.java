package com.alipay.mobile.common.logging.api.behavor;

import com.alipay.mobile.common.logging.api.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class Behavor {

    /* renamed from: a  reason: collision with root package name */
    private String f948a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q = "u";
    private String r = "c";
    private Map<String, String> s = new HashMap();

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final Behavor f949a = new Behavor();

        public Builder(String str) {
            this.f949a.setUserCaseID(str);
        }

        @Deprecated
        public Builder setAppID(String str) {
            this.f949a.setAppID(str);
            return this;
        }

        @Deprecated
        public Builder setViewID(String str) {
            this.f949a.setViewID(str);
            return this;
        }

        @Deprecated
        public Builder setRefViewID(String str) {
            this.f949a.setRefViewID(str);
            return this;
        }

        public Builder setSeedID(String str) {
            this.f949a.setSeedID(str);
            return this;
        }

        public Builder setParam1(String str) {
            this.f949a.setParam1(str);
            return this;
        }

        public Builder setParam2(String str) {
            this.f949a.setParam2(str);
            return this;
        }

        public Builder setParam3(String str) {
            this.f949a.setParam3(str);
            return this;
        }

        public Builder addExtParam(String str, String str2) {
            this.f949a.addExtParam(str, str2);
            return this;
        }

        public Behavor build() {
            return this.f949a;
        }

        public void click() {
            LoggerFactory.getBehavorLogger().click(this.f949a);
        }

        public void openPage() {
            LoggerFactory.getBehavorLogger().openPage(this.f949a);
        }

        public void longClick() {
            LoggerFactory.getBehavorLogger().longClick(this.f949a);
        }

        public void submit() {
            LoggerFactory.getBehavorLogger().submit(this.f949a);
        }

        public void slide() {
            LoggerFactory.getBehavorLogger().slide(this.f949a);
        }

        public void autoOpenPage() {
            LoggerFactory.getBehavorLogger().autoOpenPage(this.f949a);
        }
    }

    public String getUserCaseID() {
        return this.f948a;
    }

    public void setUserCaseID(String str) {
        this.f948a = str;
    }

    public String getAppID() {
        return this.b;
    }

    @Deprecated
    public void setAppID(String str) {
        this.b = str;
    }

    public String getViewID() {
        return this.d;
    }

    @Deprecated
    public void setViewID(String str) {
        this.d = str;
    }

    public String getRefViewID() {
        return this.e;
    }

    @Deprecated
    public void setRefViewID(String str) {
        this.e = str;
    }

    public String getSeedID() {
        return this.f;
    }

    public void setSeedID(String str) {
        this.f = str;
    }

    public String getParam1() {
        return this.g;
    }

    public void setParam1(String str) {
        this.g = str;
    }

    public String getParam2() {
        return this.h;
    }

    public void setParam2(String str) {
        this.h = str;
    }

    public String getParam3() {
        return this.i;
    }

    public void setParam3(String str) {
        this.i = str;
    }

    @Deprecated
    public String getLegacyParam() {
        return this.j;
    }

    @Deprecated
    public void setLegacyParam(String str) {
        this.j = str;
    }

    public String getTrackId() {
        return this.k;
    }

    public void setTrackId(String str) {
        this.k = str;
    }

    public String getTrackToken() {
        return this.l;
    }

    public void setTrackToken(String str) {
        this.l = str;
    }

    public String getTrackDesc() {
        return this.m;
    }

    public void setTrackDesc(String str) {
        this.m = str;
    }

    public Map<String, String> getExtParams() {
        return this.s;
    }

    public void addExtParam(String str, String str2) {
        this.s.put(str, str2);
    }

    public void removeExtParam(String str) {
        this.s.remove(str);
    }

    public String getStatus() {
        return this.n;
    }

    @Deprecated
    public void setStatus(String str) {
        this.n = str;
    }

    public String getStatusMsg() {
        return this.o;
    }

    @Deprecated
    public void setStatusMsg(String str) {
        this.o = str;
    }

    public String getUrl() {
        return this.p;
    }

    @Deprecated
    public void setUrl(String str) {
        this.p = str;
    }

    public String getBehaviourPro() {
        return this.q;
    }

    @Deprecated
    public void setBehaviourPro(String str) {
        this.q = str;
    }

    public String getLogPro() {
        return this.r;
    }

    @Deprecated
    public void setLogPro(String str) {
        this.r = str;
    }

    public String getAppVersion() {
        return this.c;
    }

    @Deprecated
    public void setAppVersion(String str) {
        this.c = str;
    }
}
