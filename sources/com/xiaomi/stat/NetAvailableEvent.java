package com.xiaomi.stat;

public class NetAvailableEvent {
    public static final int RESULT_TYPE_0 = 0;
    public static final int RESULT_TYPE_1 = 1;
    public static final int RESULT_TYPE_2 = 2;

    /* renamed from: a  reason: collision with root package name */
    private String f22995a;
    private int b;
    private int c;
    private String d;
    private int e;
    private long f;
    private int g;
    private String h;

    public String getFlag() {
        return this.f22995a;
    }

    public int getResponseCode() {
        return this.b;
    }

    public int getStatusCode() {
        return this.c;
    }

    public String getException() {
        return this.d;
    }

    public int getResultType() {
        return this.e;
    }

    public long getRequestStartTime() {
        return this.f;
    }

    public int getRetryCount() {
        return this.g;
    }

    public String getExt() {
        return this.h;
    }

    private NetAvailableEvent(Builder builder) {
        this.f22995a = builder.f22996a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
    }

    public static final class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f22996a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public long f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public String h;

        public Builder flag(String str) {
            this.f22996a = str;
            return this;
        }

        public Builder responseCode(int i) {
            this.b = i;
            return this;
        }

        public Builder statusCode(int i) {
            this.c = i;
            return this;
        }

        public Builder exception(String str) {
            this.d = str;
            return this;
        }

        public Builder resultType(int i) {
            this.e = i;
            return this;
        }

        public Builder requestStartTime(long j) {
            this.f = j;
            return this;
        }

        public Builder retryCount(int i) {
            this.g = i;
            return this;
        }

        public Builder ext(String str) {
            this.h = str;
            return this;
        }

        public NetAvailableEvent build() {
            return new NetAvailableEvent(this);
        }
    }
}
