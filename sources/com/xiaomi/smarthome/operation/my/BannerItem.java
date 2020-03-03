package com.xiaomi.smarthome.operation.my;

import android.text.TextUtils;

public class BannerItem extends Item {

    /* renamed from: a  reason: collision with root package name */
    public String f21134a;
    public String b;
    public String c;
    public long d;
    public long e;
    public int f;
    public String g;
    public int h;

    public boolean a() {
        return !b() && this.i != null && this.h != -1 && !TextUtils.isEmpty(this.f21134a);
    }

    public boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis < this.d || currentTimeMillis > this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BannerItem bannerItem = (BannerItem) obj;
        if (this.d != bannerItem.d || this.e != bannerItem.e || this.f != bannerItem.f || this.h != bannerItem.h) {
            return false;
        }
        if (this.f21134a == null ? bannerItem.f21134a != null : !this.f21134a.equals(bannerItem.f21134a)) {
            return false;
        }
        if (this.b == null ? bannerItem.b != null : !this.b.equals(bannerItem.b)) {
            return false;
        }
        if (this.c == null ? bannerItem.c != null : !this.c.equals(bannerItem.c)) {
            return false;
        }
        if (this.g == null ? bannerItem.g != null : !this.g.equals(bannerItem.g)) {
            return false;
        }
        if (this.j != null) {
            return this.j.equals(bannerItem.j);
        }
        if (bannerItem.j == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((this.f21134a != null ? this.f21134a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + ((int) (this.e ^ (this.e >>> 32)))) * 31) + this.f) * 31) + (this.g != null ? this.g.hashCode() : 0)) * 31) + this.h) * 31;
        if (this.j != null) {
            i = this.j.hashCode();
        }
        return hashCode + i;
    }
}
