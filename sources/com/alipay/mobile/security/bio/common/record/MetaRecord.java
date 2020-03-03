package com.alipay.mobile.security.bio.common.record;

import java.util.Map;

public class MetaRecord {
    public static final String BIZ_TYPE = "Biometrics";
    public static final String DEFAULT_LOG_CLASSIFIERS = "1#2";
    public static final String LOG_SEPARATOR = "#";
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = 3;
    public static final int PRIORITY_MIDDLE = 2;

    /* renamed from: a  reason: collision with root package name */
    private String f975a = BIZ_TYPE;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private boolean g = true;
    private String h = "";
    private String i = "";
    private String j = "";
    private Map<String, String> k;
    private int l = 2;
    private String m = "1";

    public MetaRecord() {
    }

    public MetaRecord(String str, String str2, String str3, String str4) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
    }

    public MetaRecord(String str, String str2, String str3, String str4, String str5) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.m = str5;
    }

    public MetaRecord(String str, String str2, String str3, String str4, int i2) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.l = i2;
    }

    public MetaRecord(String str, String str2, String str3, String str4, int i2, String str5) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.l = i2;
        this.m = str5;
    }

    public MetaRecord(String str, String str2, String str3, String str4, boolean z) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = z;
    }

    public MetaRecord(String str, String str2, String str3, String str4, boolean z, int i2) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = z;
        this.l = i2;
    }

    public String getCaseID() {
        return this.b;
    }

    public void setCaseID(String str) {
        this.b = str;
    }

    public String getActionID() {
        return this.c;
    }

    public void setActionID(String str) {
        this.c = str;
    }

    public String getAppID() {
        return this.d;
    }

    public void setAppID(String str) {
        this.d = str;
    }

    public String getSeedID() {
        return this.e;
    }

    public void setSeedID(String str) {
        this.e = str;
    }

    public int getSequenceId() {
        return this.f;
    }

    public void setSequenceId(int i2) {
        this.f = i2;
    }

    public String getParam1() {
        return this.h;
    }

    public void setParam1(String str) {
        this.h = str;
    }

    public String getParam2() {
        return this.i;
    }

    public void setParam2(String str) {
        this.i = str;
    }

    public String getParam3() {
        return this.j;
    }

    public void setParam3(String str) {
        this.j = str;
    }

    public Map<String, String> getParam4() {
        return this.k;
    }

    public void setParam4(Map<String, String> map) {
        this.k = map;
    }

    public boolean isEnableSequence() {
        return this.g;
    }

    public void setEnableSequence(boolean z) {
        this.g = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("caseID:" + this.b + "#");
        sb.append("actionID:" + this.c + "#");
        sb.append("appID:" + this.d + "#");
        sb.append("seedID:" + this.e + "#");
        sb.append("bizType:" + this.f975a + "#");
        sb.append("priority:" + this.l + "#");
        sb.append("classifier:" + this.m + "#");
        sb.append("param1:" + this.h + "#");
        sb.append("param2:" + this.i + "#");
        sb.append("param3:" + this.j + "#");
        sb.append("param4:");
        if (this.k != null) {
            for (Map.Entry next : this.k.entrySet()) {
                Object key = next.getKey();
                Object value = next.getValue();
                sb.append("@" + key + "=" + value);
            }
        }
        return sb.toString();
    }

    public String getBizType() {
        return this.f975a;
    }

    public void setBizType(String str) {
        this.f975a = str;
    }

    public int getPriority() {
        return this.l;
    }

    public void setPriority(int i2) {
        this.l = i2;
    }

    public String getClassifier() {
        return this.m;
    }

    public void setClassifier(String str) {
        this.m = str;
    }
}
