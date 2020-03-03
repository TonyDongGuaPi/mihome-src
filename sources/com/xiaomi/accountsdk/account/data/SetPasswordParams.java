package com.xiaomi.accountsdk.account.data;

public class SetPasswordParams {
    public final String deviceId;
    public final MiuiActivatorInfo miuiActivatorInfo;
    public final String passToken;
    public final PassportInfo passportApiInfo;
    public final String pwd;
    public final String serviceId;
    public final String ticket;
    public final String userId;

    public SetPasswordParams(Builder builder) {
        this.userId = builder.userId;
        this.passportApiInfo = builder.passportApiInfo;
        this.pwd = builder.pwd;
        this.passToken = builder.passToken;
        this.ticket = builder.ticket;
        this.serviceId = builder.serviceId;
        this.deviceId = builder.deviceId;
        this.miuiActivatorInfo = builder.miuiActivatorInfo;
    }

    public static Builder copyFrom(SetPasswordParams setPasswordParams) {
        if (setPasswordParams == null) {
            return null;
        }
        return new Builder(setPasswordParams.userId).passportApiInfo(setPasswordParams.passportApiInfo).password(setPasswordParams.pwd).passToken(setPasswordParams.passToken).ticket(setPasswordParams.ticket).serviceId(setPasswordParams.serviceId).deviceId(setPasswordParams.deviceId).miuiActivatorInfo(setPasswordParams.miuiActivatorInfo);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public String deviceId;
        /* access modifiers changed from: private */
        public MiuiActivatorInfo miuiActivatorInfo;
        /* access modifiers changed from: private */
        public String passToken;
        /* access modifiers changed from: private */
        public PassportInfo passportApiInfo;
        /* access modifiers changed from: private */
        public String pwd;
        /* access modifiers changed from: private */
        public String serviceId;
        /* access modifiers changed from: private */
        public String ticket;
        /* access modifiers changed from: private */
        public final String userId;

        public Builder(String str) {
            this.userId = str;
        }

        public Builder password(String str) {
            this.pwd = str;
            return this;
        }

        public Builder passToken(String str) {
            this.passToken = str;
            return this;
        }

        public Builder serviceId(String str) {
            this.serviceId = str;
            return this;
        }

        public Builder deviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder ticket(String str) {
            this.ticket = str;
            return this;
        }

        public Builder passportApiInfo(PassportInfo passportInfo) {
            this.passportApiInfo = passportInfo;
            return this;
        }

        public Builder miuiActivatorInfo(MiuiActivatorInfo miuiActivatorInfo2) {
            this.miuiActivatorInfo = miuiActivatorInfo2;
            return this;
        }

        public SetPasswordParams build() {
            return new SetPasswordParams(this);
        }
    }
}
