package com.xiaomi.mishopsdk.io.entity;

import com.taobao.weex.el.parse.Operators;

public class ShopApiBaseResult {
    public String mCloseLink;
    public long mCode;
    public String mDescription;
    public String mInfo;
    public StringBuilder mJsonData;

    public ShopApiBaseResult(Builder builder) {
        this.mInfo = builder.mInfo;
        this.mCode = builder.mCode;
        this.mDescription = builder.mDescription;
        this.mCloseLink = builder.mCloseLink;
        this.mJsonData = builder.mJsonData;
    }

    public boolean isApiOk(boolean z) {
        return this.mCode == 0 && (z || (this.mJsonData != null && this.mJsonData.length() > 0));
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String mCloseLink;
        /* access modifiers changed from: private */
        public long mCode;
        /* access modifiers changed from: private */
        public String mDescription;
        /* access modifiers changed from: private */
        public String mInfo;
        /* access modifiers changed from: private */
        public StringBuilder mJsonData;

        public Builder setCode(long j) {
            this.mCode = j;
            return this;
        }

        public Builder setInfo(String str) {
            this.mInfo = str;
            return this;
        }

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder setCloseLink(String str) {
            this.mCloseLink = str;
            return this;
        }

        public Builder setJsonData(StringBuilder sb) {
            this.mJsonData = sb;
            return this;
        }

        public ShopApiBaseResult build() {
            return new ShopApiBaseResult(this);
        }
    }

    public String toString() {
        return "ShopApiBaseResult{mCloseLink='" + this.mCloseLink + Operators.SINGLE_QUOTE + ", mDescription='" + this.mDescription + Operators.SINGLE_QUOTE + ", mInfo='" + this.mInfo + Operators.SINGLE_QUOTE + ", mCode=" + this.mCode + ",mJsonData=" + this.mJsonData + Operators.BLOCK_END;
    }
}
