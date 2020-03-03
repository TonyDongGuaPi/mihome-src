package com.iheartradio.m3u8.data;

import java.util.Objects;

public class Resolution {

    /* renamed from: a  reason: collision with root package name */
    public final int f6051a;
    public final int b;

    public Resolution(int i, int i2) {
        this.f6051a = i;
        this.b = i2;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.f6051a)});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Resolution)) {
            return false;
        }
        Resolution resolution = (Resolution) obj;
        if (this.f6051a == resolution.f6051a && this.b == resolution.b) {
            return true;
        }
        return false;
    }
}
