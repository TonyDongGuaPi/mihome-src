package com.xiaomi.smarthome.frame;

public class SDKSetting {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1524a;

    private SDKSetting(Builder builder) {
        f1524a = builder.f1525a;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        boolean f1525a;
        boolean b = false;

        public Builder a(boolean z) {
            this.f1525a = z;
            this.b = true;
            return this;
        }

        public SDKSetting a() {
            if (this.b) {
                return new SDKSetting(this);
            }
            throw new RuntimeException("CoreServiceWhiteList not set");
        }
    }
}
