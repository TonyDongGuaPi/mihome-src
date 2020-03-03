package com.xiaomi.youpin;

import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.plugin.RedpointManager;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.thread.DefaultExecutorSupplier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RedPointManager4MessageCenter {
    private static volatile RedPointManager4MessageCenter c;

    /* renamed from: a  reason: collision with root package name */
    private List<RedpointManager.BadgeUpdateListener> f23167a = new ArrayList();
    private List<Point> b = new ArrayList();

    static RedPointManager4MessageCenter a() {
        if (c == null) {
            synchronized (RedPointManager4MessageCenter.class) {
                if (c == null) {
                    c = new RedPointManager4MessageCenter();
                }
            }
        }
        return c;
    }

    private RedPointManager4MessageCenter() {
    }

    /* access modifiers changed from: package-private */
    public void a(final String str, final String str2, final int i) {
        if (e()) {
            b(str, str2, i);
        } else {
            DefaultExecutorSupplier.a().d().execute(new Runnable() {
                public void run() {
                    RedPointManager4MessageCenter.this.b(str, str2, i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(String str, String str2, int i) {
        if (i == 0) {
            b(str, str2);
        } else if (i > 0) {
            Point point = null;
            for (Point next : this.b) {
                if (next.a(str, str2)) {
                    next.c = i;
                    point = next;
                }
            }
            if (point == null) {
                Point point2 = new Point();
                point2.f23169a = str;
                point2.b = str2;
                point2.c = i;
                this.b.add(point2);
            }
        } else {
            return;
        }
        d();
    }

    private void b(String str, String str2) {
        Iterator<Point> it = this.b.iterator();
        while (it.hasNext()) {
            Point next = it.next();
            if ((str2 == null && str != null && str.equals(next.f23169a)) || next.a(str, str2)) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        e();
        this.b.clear();
        d();
    }

    /* access modifiers changed from: package-private */
    public int a(String str, String str2) {
        if (str == null) {
            return -1;
        }
        e();
        int i = 0;
        for (Point next : this.b) {
            if (str2 == null ? str.equals(next.f23169a) : next.a(str, str2)) {
                i += next.c;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int a(String str) {
        return a(str, (String) null);
    }

    /* access modifiers changed from: package-private */
    public int c() {
        e();
        int i = 0;
        for (Point point : this.b) {
            i += point.c;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void a(RedpointManager.BadgeUpdateListener badgeUpdateListener) {
        e();
        if (badgeUpdateListener != null) {
            badgeUpdateListener.onUpdate();
            this.f23167a.add(badgeUpdateListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RedpointManager.BadgeUpdateListener badgeUpdateListener) {
        e();
        if (badgeUpdateListener != null) {
            this.f23167a.remove(badgeUpdateListener);
        }
    }

    private void d() {
        for (RedpointManager.BadgeUpdateListener next : this.f23167a) {
            if (next != null) {
                next.onUpdate();
            }
        }
    }

    private static boolean e() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread() && XmPluginHostApi.instance().isDevMode()) {
            LogUtils.w(a().getClass().getName(), "只能在主线程调用!!!");
        }
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    private static class Point {

        /* renamed from: a  reason: collision with root package name */
        String f23169a;
        String b;
        int c;

        private Point() {
        }

        public boolean a(String str, String str2) {
            return TextUtils.equals(this.f23169a, str) && TextUtils.equals(this.b, str2);
        }
    }
}
