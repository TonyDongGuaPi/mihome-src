package com.xiaomi.smarthome.operation.my;

import android.text.TextUtils;

public class TabItem extends Item {

    /* renamed from: a  reason: collision with root package name */
    public String f21148a;
    public String b;

    public boolean b() {
        return false;
    }

    public boolean a() {
        return !b() && this.i != null && !TextUtils.isEmpty(this.f21148a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TabItem tabItem = (TabItem) obj;
        if (this.f21148a == null ? tabItem.f21148a != null : !this.f21148a.equals(tabItem.f21148a)) {
            return false;
        }
        if (this.b == null ? tabItem.b != null : !this.b.equals(tabItem.b)) {
            return false;
        }
        if (this.j != null) {
            return this.j.equals(tabItem.j);
        }
        if (tabItem.j == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.f21148a != null ? this.f21148a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31;
        if (this.j != null) {
            i = this.j.hashCode();
        }
        return hashCode + i;
    }
}
