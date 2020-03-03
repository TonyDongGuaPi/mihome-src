package com.xiaomi.accountsdk.account.data;

public class PassTokenLoginParams {
    public final String deviceId;
    public final boolean isGetPhoneTicketLoginMetaData;
    public final String loginRequestUrl;
    public final String passToken;
    public final boolean returnStsUrl;
    public final String serviceId;
    public final String userId;

    private PassTokenLoginParams(Builder builder) {
        this.userId = builder.userId;
        this.passToken = builder.passToken;
        this.serviceId = builder.serviceId;
        this.loginRequestUrl = builder.loginRequestUrl;
        this.deviceId = builder.deviceId;
        this.returnStsUrl = builder.returnStsUrl;
        this.isGetPhoneTicketLoginMetaData = builder.isGetPhoneTicketLoginMetaData;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String deviceId;
        /* access modifiers changed from: private */
        public boolean isGetPhoneTicketLoginMetaData = false;
        /* access modifiers changed from: private */
        public String loginRequestUrl;
        /* access modifiers changed from: private */
        public final String passToken;
        /* access modifiers changed from: private */
        public boolean returnStsUrl = false;
        /* access modifiers changed from: private */
        public final String serviceId;
        /* access modifiers changed from: private */
        public final String userId;

        public Builder(String str, String str2, String str3) {
            this.userId = str;
            this.passToken = str2;
            this.serviceId = str3;
        }

        public Builder loginRequestUrl(String str) {
            this.loginRequestUrl = str;
            return this;
        }

        public Builder deviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder isReturnStsUrl(boolean z) {
            this.returnStsUrl = z;
            return this;
        }

        public Builder isGetPhoneTicketLoginMetaData(boolean z) {
            this.isGetPhoneTicketLoginMetaData = z;
            return this;
        }

        public PassTokenLoginParams build() {
            return new PassTokenLoginParams(this);
        }
    }
}
