package com.xiaomi.mipush.sdk;

import android.text.TextUtils;

class z {

    /* renamed from: a  reason: collision with root package name */
    int f11569a = 0;
    String b = "";

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return !TextUtils.isEmpty(zVar.b) && zVar.b.equals(this.b);
    }
}
