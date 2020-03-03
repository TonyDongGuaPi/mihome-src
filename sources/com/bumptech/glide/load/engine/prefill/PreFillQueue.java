package com.bumptech.glide.load.engine.prefill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class PreFillQueue {

    /* renamed from: a  reason: collision with root package name */
    private final Map<PreFillType, Integer> f4929a;
    private final List<PreFillType> b;
    private int c;
    private int d;

    public PreFillQueue(Map<PreFillType, Integer> map) {
        this.f4929a = map;
        this.b = new ArrayList(map.keySet());
        for (Integer intValue : map.values()) {
            this.c += intValue.intValue();
        }
    }

    public PreFillType a() {
        PreFillType preFillType = this.b.get(this.d);
        Integer num = this.f4929a.get(preFillType);
        if (num.intValue() == 1) {
            this.f4929a.remove(preFillType);
            this.b.remove(this.d);
        } else {
            this.f4929a.put(preFillType, Integer.valueOf(num.intValue() - 1));
        }
        this.c--;
        this.d = this.b.isEmpty() ? 0 : (this.d + 1) % this.b.size();
        return preFillType;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.c == 0;
    }
}
