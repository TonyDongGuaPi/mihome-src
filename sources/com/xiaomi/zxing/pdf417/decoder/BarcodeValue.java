package com.xiaomi.zxing.pdf417.decoder;

import com.xiaomi.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

final class BarcodeValue {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Integer, Integer> f1734a = new HashMap();

    BarcodeValue() {
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        Integer num = this.f1734a.get(Integer.valueOf(i));
        if (num == null) {
            num = 0;
        }
        this.f1734a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    /* access modifiers changed from: package-private */
    public int[] a() {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (Map.Entry next : this.f1734a.entrySet()) {
            if (((Integer) next.getValue()).intValue() > i) {
                i = ((Integer) next.getValue()).intValue();
                arrayList.clear();
                arrayList.add(next.getKey());
            } else if (((Integer) next.getValue()).intValue() == i) {
                arrayList.add(next.getKey());
            }
        }
        return PDF417Common.a((Collection<Integer>) arrayList);
    }

    public Integer b(int i) {
        return this.f1734a.get(Integer.valueOf(i));
    }
}
