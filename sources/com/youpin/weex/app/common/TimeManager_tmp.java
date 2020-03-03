package com.youpin.weex.app.common;

import com.xiaomi.youpin.log.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class TimeManager_tmp {

    /* renamed from: a  reason: collision with root package name */
    private long f2502a;
    private long b;
    private ArrayList<String> c;
    private volatile boolean d = false;
    private String e;

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static TimeManager_tmp f2503a = new TimeManager_tmp();

        private Holder() {
        }
    }

    public static TimeManager_tmp a() {
        return Holder.f2503a;
    }

    public void a(String str) {
        this.e = str;
        this.c = new ArrayList<>();
        this.f2502a = System.currentTimeMillis();
        this.b = System.currentTimeMillis();
        this.d = true;
    }

    public void b(String str) {
        if (this.d) {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList<String> arrayList = this.c;
            arrayList.add(str + ": " + (currentTimeMillis - this.b) + " ms");
            this.b = currentTimeMillis;
        }
    }

    public void b() {
        if (this.c != null && this.d) {
            this.d = false;
            long currentTimeMillis = System.currentTimeMillis() - this.f2502a;
            long currentTimeMillis2 = System.currentTimeMillis() - this.b;
            StringBuilder sb = new StringBuilder("\n");
            sb.append(this.e);
            sb.append("\n");
            Iterator<String> it = this.c.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("\n");
            }
            sb.append("最后一次间隔时间:");
            sb.append(currentTimeMillis2);
            sb.append("\n");
            sb.append("整体运行时间:");
            sb.append(currentTimeMillis);
            LogUtils.d("TimeManager Result:", sb);
            this.c.clear();
            this.c = null;
            this.e = null;
        }
    }
}
