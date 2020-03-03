package com.mi.mistatistic.sdk.controller;

import java.io.Serializable;
import java.util.ArrayList;

public class MiStatOptions implements Serializable {
    /* access modifiers changed from: private */
    public String appId;
    /* access modifiers changed from: private */
    public String channel;
    /* access modifiers changed from: private */
    public boolean disableStat;
    /* access modifiers changed from: private */
    public boolean enableLog;
    /* access modifiers changed from: private */
    public String gaId;
    /* access modifiers changed from: private */
    public boolean isForSdk = false;
    /* access modifiers changed from: private */
    public ArrayList<String> needUploadPackageNameList = null;
    /* access modifiers changed from: private */
    public boolean openBlockCanary;
    /* access modifiers changed from: private */
    public boolean selfStat;
    /* access modifiers changed from: private */
    public boolean serverCn = false;
    /* access modifiers changed from: private */
    public boolean serverIndia = false;
    /* access modifiers changed from: private */
    public boolean serverRussia = false;
    /* access modifiers changed from: private */
    public boolean test;
    /* access modifiers changed from: private */
    public int uploadInteval = 0;
    /* access modifiers changed from: private */
    public int uploadPolicy = 0;
    /* access modifiers changed from: private */
    public String userId;
    /* access modifiers changed from: private */
    public String versionSpan;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getVersionSpan() {
        return this.versionSpan;
    }

    public void setVersionSpan(String str) {
        this.versionSpan = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getGaId() {
        return this.gaId;
    }

    public boolean isTest() {
        return this.test;
    }

    public void setTest(boolean z) {
        this.test = z;
    }

    public boolean isSelfStat() {
        return this.selfStat;
    }

    public void setSelfStat(boolean z) {
        this.selfStat = z;
    }

    public boolean isServerCn() {
        return this.serverCn;
    }

    public void setServerCn(boolean z) {
        this.serverCn = z;
    }

    public boolean isServerIndia() {
        return this.serverIndia;
    }

    public void setServerIndia(boolean z) {
        this.serverIndia = z;
    }

    public boolean isServerRussia() {
        return this.serverRussia;
    }

    public void setServerRussia(boolean z) {
        this.serverRussia = z;
    }

    public int getUploadPolicy() {
        return this.uploadPolicy;
    }

    public void setUploadPolicy(int i) {
        this.uploadPolicy = i;
    }

    public int getUploadInteval() {
        return this.uploadInteval;
    }

    public void setUploadInteval(int i) {
        this.uploadInteval = i;
    }

    public boolean isForSdk() {
        return this.isForSdk;
    }

    public ArrayList<String> getNeedUploadPackageNameList() {
        return this.needUploadPackageNameList;
    }

    public void setNeedUploadPackageNameList(ArrayList<String> arrayList) {
        this.needUploadPackageNameList = arrayList;
    }

    public boolean isEnableLog() {
        return this.enableLog;
    }

    public void setEnableLog(boolean z) {
        this.enableLog = z;
    }

    public boolean isDisableStat() {
        return this.disableStat;
    }

    public void setDisableStat(boolean z) {
        this.disableStat = z;
    }

    public boolean isOpenBlockCanary() {
        return this.openBlockCanary;
    }

    public void setOpenBlockCanary(boolean z) {
        this.openBlockCanary = z;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private MiStatOptions f7334a = new MiStatOptions();

        public Builder a(String str) {
            String unused = this.f7334a.appId = str;
            return this;
        }

        public Builder b(String str) {
            String unused = this.f7334a.channel = str;
            return this;
        }

        public Builder c(String str) {
            String unused = this.f7334a.versionSpan = str;
            return this;
        }

        public Builder a(boolean z) {
            boolean unused = this.f7334a.test = z;
            return this;
        }

        public Builder d(String str) {
            String unused = this.f7334a.userId = str;
            return this;
        }

        public Builder e(String str) {
            String unused = this.f7334a.gaId = str;
            return this;
        }

        public Builder a(ArrayList<String> arrayList) {
            ArrayList unused = this.f7334a.needUploadPackageNameList = arrayList;
            return this;
        }

        public Builder b(boolean z) {
            boolean unused = this.f7334a.selfStat = z;
            return this;
        }

        public Builder c(boolean z) {
            boolean unused = this.f7334a.serverCn = z;
            return this;
        }

        public Builder d(boolean z) {
            boolean unused = this.f7334a.isForSdk = z;
            return this;
        }

        public Builder e(boolean z) {
            boolean unused = this.f7334a.serverIndia = z;
            return this;
        }

        public Builder f(boolean z) {
            boolean unused = this.f7334a.serverRussia = z;
            return this;
        }

        public Builder a(int i, int i2) {
            int unused = this.f7334a.uploadPolicy = i;
            int unused2 = this.f7334a.uploadInteval = i2;
            return this;
        }

        public Builder g(boolean z) {
            boolean unused = this.f7334a.enableLog = z;
            return this;
        }

        public Builder h(boolean z) {
            boolean unused = this.f7334a.disableStat = z;
            return this;
        }

        public Builder i(boolean z) {
            boolean unused = this.f7334a.openBlockCanary = z;
            return this;
        }

        public MiStatOptions a() {
            return this.f7334a;
        }
    }
}
