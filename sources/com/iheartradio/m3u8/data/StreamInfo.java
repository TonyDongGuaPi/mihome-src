package com.iheartradio.m3u8.data;

import java.util.List;
import java.util.Objects;

public class StreamInfo implements IStreamInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6054a = -1;
    private final int b;
    private final int c;
    private final List<String> d;
    private final Resolution e;
    private final float f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;

    private StreamInfo(int i2, int i3, List<String> list, Resolution resolution, float f2, String str, String str2, String str3, String str4) {
        this.b = i2;
        this.c = i3;
        this.d = list;
        this.e = resolution;
        this.f = f2;
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = str4;
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

    public boolean l() {
        return this.g != null;
    }

    public String m() {
        return this.g;
    }

    public boolean j() {
        return this.h != null;
    }

    public String k() {
        return this.h;
    }

    public boolean n() {
        return this.i != null;
    }

    public String o() {
        return this.i;
    }

    public boolean p() {
        return this.j != null;
    }

    public String q() {
        return this.j;
    }

    public Builder r() {
        return new Builder(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), this.d, this.e, Float.valueOf(this.f), this.g, this.h, this.i, this.j});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StreamInfo)) {
            return false;
        }
        StreamInfo streamInfo = (StreamInfo) obj;
        if (this.b != streamInfo.b || this.c != streamInfo.c || !Objects.equals(this.d, streamInfo.d) || !Objects.equals(this.e, streamInfo.e) || !Objects.equals(Float.valueOf(this.f), Float.valueOf(streamInfo.f)) || !Objects.equals(this.g, streamInfo.g) || !Objects.equals(this.h, streamInfo.h) || !Objects.equals(this.i, streamInfo.i) || !Objects.equals(this.j, streamInfo.j)) {
            return false;
        }
        return true;
    }

    public static class Builder implements StreamInfoBuilder {

        /* renamed from: a  reason: collision with root package name */
        private int f6055a;
        private int b;
        private List<String> c;
        private Resolution d;
        private float e;
        private String f;
        private String g;
        private String h;
        private String i;

        public Builder() {
            this.f6055a = -1;
            this.b = -1;
            this.e = Float.NaN;
        }

        private Builder(int i2, int i3, List<String> list, Resolution resolution, float f2, String str, String str2, String str3, String str4) {
            this.f6055a = -1;
            this.b = -1;
            this.e = Float.NaN;
            this.f6055a = i2;
            this.b = i3;
            this.c = list;
            this.d = resolution;
            this.e = f2;
            this.f = str;
            this.g = str2;
            this.h = str3;
            this.i = str4;
        }

        /* renamed from: a */
        public Builder d(int i2) {
            this.f6055a = i2;
            return this;
        }

        /* renamed from: b */
        public Builder c(int i2) {
            this.b = i2;
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

        public Builder a(String str) {
            this.f = str;
            return this;
        }

        /* renamed from: b */
        public Builder c(String str) {
            this.g = str;
            return this;
        }

        public Builder d(String str) {
            this.h = str;
            return this;
        }

        public Builder e(String str) {
            this.i = str;
            return this;
        }

        public StreamInfo a() {
            return new StreamInfo(this.f6055a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
        }
    }
}
