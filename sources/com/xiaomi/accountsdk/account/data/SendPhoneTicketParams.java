package com.xiaomi.accountsdk.account.data;

import android.app.Application;
import com.xiaomi.accountsdk.account.XMPassportSettings;

public class SendPhoneTicketParams {
    public final ActivatorPhoneInfo activatorPhoneInfo;
    public final String activatorToken;
    public final String captCode;
    public final String captIck;
    public final String deviceId;
    public final String phone;
    public final String phoneHash;
    public final String region;
    public final String serviceId;

    private SendPhoneTicketParams(Builder builder) {
        this.phone = builder.phone;
        this.activatorPhoneInfo = builder.activatorPhoneInfo;
        String str = null;
        this.phoneHash = this.activatorPhoneInfo != null ? this.activatorPhoneInfo.phoneHash : null;
        this.activatorToken = this.activatorPhoneInfo != null ? this.activatorPhoneInfo.activatorToken : str;
        this.deviceId = builder.deviceId;
        this.serviceId = builder.serviceId;
        this.captCode = builder.captCode;
        this.captIck = builder.captIck;
        this.region = builder.region;
    }

    public static Builder copyFrom(SendPhoneTicketParams sendPhoneTicketParams) {
        if (sendPhoneTicketParams == null) {
            return null;
        }
        return new Builder().phone(sendPhoneTicketParams.phone).phoneHashActivatorToken(sendPhoneTicketParams.activatorPhoneInfo).serviceId(sendPhoneTicketParams.serviceId).deviceId(sendPhoneTicketParams.deviceId).captchaCode(sendPhoneTicketParams.captCode, sendPhoneTicketParams.captIck).region(sendPhoneTicketParams.region);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public ActivatorPhoneInfo activatorPhoneInfo;
        /* access modifiers changed from: private */
        public String captCode;
        /* access modifiers changed from: private */
        public String captIck;
        /* access modifiers changed from: private */
        public String deviceId;
        /* access modifiers changed from: private */
        public String phone;
        /* access modifiers changed from: private */
        public String region;
        /* access modifiers changed from: private */
        public String serviceId;

        public Builder phone(String str) {
            this.phone = str;
            return this;
        }

        public Builder phoneHashActivatorToken(ActivatorPhoneInfo activatorPhoneInfo2) {
            this.activatorPhoneInfo = activatorPhoneInfo2;
            return this;
        }

        public Builder deviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder serviceId(String str) {
            this.serviceId = str;
            return this;
        }

        public Builder captchaCode(String str, String str2) {
            this.captCode = str;
            this.captIck = str2;
            return this;
        }

        public Builder region(String str) {
            this.region = str;
            return this;
        }

        public Builder application(Application application) {
            XMPassportSettings.setApplicationContext(application);
            return this;
        }

        public SendPhoneTicketParams build() {
            return new SendPhoneTicketParams(this);
        }
    }
}
