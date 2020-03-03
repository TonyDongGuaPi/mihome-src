package com.xiaomi.accountsdk.account.data;

public class QueryPhoneInfoParams {
    public final ActivatorPhoneInfo activatorPhoneInfo;
    public final String activatorToken;
    public final String deviceId;
    public final String phone;
    public final String phoneHash;
    public final String ticket;

    private QueryPhoneInfoParams(Builder builder) {
        this.phone = builder.phone;
        this.deviceId = builder.deviceId;
        this.ticket = builder.ticket;
        this.activatorToken = builder.activatorToken;
        this.phoneHash = builder.phoneHash;
        this.activatorPhoneInfo = builder.activatorPhoneInfo;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public ActivatorPhoneInfo activatorPhoneInfo;
        /* access modifiers changed from: private */
        public String activatorToken;
        /* access modifiers changed from: private */
        public String deviceId;
        /* access modifiers changed from: private */
        public String phone;
        /* access modifiers changed from: private */
        public String phoneHash;
        /* access modifiers changed from: private */
        public String ticket;

        public Builder deviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder phoneTicket(String str, String str2) {
            this.phone = str;
            this.ticket = str2;
            return this;
        }

        public Builder phoneHashActivatorToken(ActivatorPhoneInfo activatorPhoneInfo2) {
            this.activatorPhoneInfo = activatorPhoneInfo2;
            if (activatorPhoneInfo2 != null) {
                this.phoneHash = activatorPhoneInfo2.phoneHash;
                this.activatorToken = activatorPhoneInfo2.activatorToken;
            }
            return this;
        }

        public QueryPhoneInfoParams build() {
            return new QueryPhoneInfoParams(this);
        }
    }
}
