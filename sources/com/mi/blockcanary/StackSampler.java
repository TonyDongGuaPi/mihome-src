package com.mi.blockcanary;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;

class StackSampler extends AbstractSampler {
    private static final int c = 100;
    private static final LinkedHashMap<Long, String> d = new LinkedHashMap<>();
    private int e;
    private Thread f;

    public StackSampler(Thread thread, long j) {
        this(thread, 100, j);
    }

    public StackSampler(Thread thread, int i, long j) {
        super(j);
        this.e = 100;
        this.f = thread;
        this.e = i;
    }

    public ArrayList<String> a(long j, long j2) {
        ArrayList<String> arrayList = new ArrayList<>();
        synchronized (d) {
            for (Long next : d.keySet()) {
                if (j < next.longValue() && next.longValue() < j2) {
                    arrayList.add(d.get(next) + "\r\n");
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void c() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : this.f.getStackTrace()) {
            if (!TextUtils.isEmpty(stackTraceElement.toString())) {
                sb.append(stackTraceElement.toString());
                sb.append("\r\n");
            }
        }
        synchronized (d) {
            if (d.size() == this.e && this.e > 0) {
                d.remove(d.keySet().iterator().next());
            }
            d.put(Long.valueOf(System.currentTimeMillis()), sb.toString());
        }
    }
}
