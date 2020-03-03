package com.iheartradio.m3u8.data;

import java.util.List;
import java.util.Objects;

public class IFrameStreamInfo implements IStreamInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6039a = -1;
    private final int b;
    private final int c;
    private final List<String> d;
    private final Resolution e;
    private final float f;
    private final String g;
    private final String h;

    private IFrameStreamInfo(int i, int i2, List<String> list, Resolution resolution, float f2, String str, String str2) {
        this.b = i;
        this.c = i2;
        this.d = list;
        this.e = resolution;
        this.f = f2;
        this.g = str;
        this.h = str2;
    }

    public int a() {
        return this.b;
    }

    public boolean b() {
        return this.c != -1;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.d != null;
    }

    public List<String> e() {
        return this.d;
    }

    public boolean f() {
        return this.e != null;
    }

    public Resolution g() {
        return this.e;
    }

    public boolean h() {
        return !Float.isNaN(this.f);
    }

    public float i() {
        return this.f;
    }

    public boolean j() {
        return this.g != null;
    }

    public String k() {
        return this.g;
    }

    public String l() {
        return this.h;
    }

    public Builder m() {
        return new Builder(this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), this.d, this.e, Float.valueOf(this.f), this.g, this.h});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IFrameStreamInfo)) {
            return false;
        }
        IFrameStreamInfo iFrameStreamInfo = (IFrameStreamInfo) obj;
        if (this.b != iFrameStreamInfo.b || this.c != iFrameStreamInfo.c || !Objects.equals(this.d, iFrameStreamInfo.d) || !Objects.equals(this.e, iFrameStreamInfo.e) || !Objects.equals(Float.valueOf(this.f), Float.valueOf(iFrameStreamInfo.f)) || !Objects.equals(this.g, iFrameStreamInfo.g) || !Objects.equals(this.h, iFrameStreamInfo.h)) {
            return false;
        }
        return true;
    }

    public static class Builder implements StreamInfoBuilder {

        /* renamed from: a  reason: collision with root package name */
        private int f6040a;
        private int b;
        private List<String> c;
        private Resolution d;
        private float e;
        private String f;
        private String g;

        public Builder() {
            this.f6040a = -1;
            this.b = -1;
            this.e = Float.NaN;
        }

        private Builder(int i, int i2, List<String> list, Resolution resolution, float f2, String str, String str2) {
            this.f6040a = -1;
            this.b = -1;
            this.e = Float.NaN;
            this.f6040a = i;
            this.b = i2;
            this.c = list;
            this.d = resolution;
            this.e = f2;
            this.f = str;
            this.g = str2;
        }

        /* renamed from: a */
        public Builder d(int i) {
            this.f6040a = i;
            return this;
        }

        /* renamed from: b */
        public Builder c(int i) {
            this.b = i;
            return this;
        }

        /* renamed from: a */
        public Builder b(List<String> list) {
            this.c = list;
            return this;
        }

        /* renamed from: a */
        public Builder b(Resolution resolution) {
            this.d = resolution;
            return this;
        }

        /* renamed from: a */
        public Builder b(float f2) {
            this.e = f2;
            return this;
        }

        /* renamed from: a */
        public Builder c(String str) {
            this.f = str;
            return this;
        }

        public Builder b(String str) {
            this.g = str;
            return this;
        }

        public IFrameStreamInfo a() {
            return new IFrameStreamInfo(this.f6040a, this.b, this.c, this.d, this.e, this.f, this.g);
        }
    }
}
