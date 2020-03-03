package com.amap.api.services.a;

import android.content.Context;
import com.adobe.xmp.XMPConst;

abstract class ag<T, V> extends k<T, V> {
    public ag(Context context, T t) {
        super(context, t);
    }

    /* access modifiers changed from: protected */
    public boolean e(String str) {
        return str == null || str.equals("") || str.equals(XMPConst.ai);
    }
}
