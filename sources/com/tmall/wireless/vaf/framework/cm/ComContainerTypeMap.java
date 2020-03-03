package com.tmall.wireless.vaf.framework.cm;

import android.support.v4.util.ArrayMap;

public class ComContainerTypeMap {

    /* renamed from: a  reason: collision with root package name */
    private ArrayMap<String, Integer> f9363a = new ArrayMap<>();

    public void a(String str, int i) {
        if (str != null && i > -1) {
            this.f9363a.put(str, Integer.valueOf(i));
        }
    }

    public int a(String str) {
        Integer num = this.f9363a.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
