package com.iheartradio.m3u8.data;

import java.util.Objects;

public class StartData {

    /* renamed from: a  reason: collision with root package name */
    private final float f6052a;
    private final boolean b;

    public StartData(float f, boolean z) {
        this.f6052a = f;
        this.b = z;
    }

    public float a() {
        return this.f6052a;
    }

    public boolean b() {
        return this.b;
    }

    public Builder c() {
        return new Builder(this.f6052a, this.b);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.b), Float.valueOf(this.f6052a)});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StartData)) {
            return false;
        }
        StartData startData = (StartData) obj;
        if (this.b == startData.b && this.f6052a == startData.f6052a) {
            return true;
        }
        return false;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private float f6053a;
        private boolean b;

        public Builder() {
            this.f6053a = Float.NaN;
        }

        private Builder(float f, boolean z) {
            this.f6053a = Float.NaN;
            this.f6053a = f;
            this.b = z;
        }

        public Builder a(float f) {
            this.f6053a = f;
            return this;
        }

        public Builder a(boolean z) {
            this.b = z;
            return this;
        }

        public StartData a() {
            return new StartData(this.f6053a, this.b);
        }
    }
}
