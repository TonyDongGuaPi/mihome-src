package com.alipay.mobile.common.logging.api.monitor;

import com.alipay.mobile.common.logging.api.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class Performance {

    /* renamed from: a  reason: collision with root package name */
    private String f951a;
    private String b;
    private String c;
    private String d;
    private Map<String, String> e = new HashMap();

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final Performance f952a = new Performance();

        public Builder setSubType(String str) {
            this.f952a.setSubType(str);
            return this;
        }

        public Builder setParam1(String str) {
            this.f952a.setParam1(str);
            return this;
        }

        public Builder setParam2(String str) {
            this.f952a.setParam2(str);
            return this;
        }

        public Builder setParam3(String str) {
            this.f952a.setParam3(str);
            return this;
        }

        public Builder addExtParam(String str, String str2) {
            this.f952a.addExtParam(str, str2);
            return this;
        }

        public Performance build() {
            return this.f952a;
        }

        public void performance(PerformanceID performanceID) {
            LoggerFactory.getMonitorLogger().performance(performanceID, this.f952a);
        }
    }

    public String getSubType() {
        return this.f951a;
    }

    public void setSubType(String str) {
        this.f951a = str;
    }

    public String getParam1() {
        return this.b;
    }

    public void setParam1(String str) {
        this.b = str;
    }

    public String getParam2() {
        return this.c;
    }

    public void setParam2(String str) {
        this.c = str;
    }

    public String getParam3() {
        return this.d;
    }

    public void setParam3(String str) {
        this.d = str;
    }

    public Map<String, String> getExtPramas() {
        return this.e;
    }

    public void addExtParam(String str, String str2) {
        this.e.put(str, str2);
    }

    public void removeExtParam(String str) {
        this.e.remove(str);
    }
}
