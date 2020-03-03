package com.mics.core.business;

import android.text.TextUtils;
import com.mics.util.Logger;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatDataSource {

    /* renamed from: a  reason: collision with root package name */
    private final Set<String> f7627a = new HashSet();
    private final List<Data> b = new Vector();
    private volatile boolean c = true;
    private volatile boolean d = true;

    public interface OnDataUpdateListener {
        void a(Data data);

        void a(String str);

        void a(List<Data> list);

        void b(String str);

        void b(List<Data> list);
    }

    ChatDataSource() {
    }

    /* access modifiers changed from: package-private */
    public List<Data> a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.c = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(boolean z) {
        this.d = z;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Data data) {
        synchronized (this.b) {
            if (this.b.size() != 0) {
                if (!TextUtils.isEmpty(data.d()) || data.e() != 0) {
                    if (this.f7627a.contains(data.d())) {
                        Logger.a("MSG cannot add data uMsgId= %s;", data.d());
                        return false;
                    }
                    int size = this.b.size() - 1;
                    while (size >= 0) {
                        long e = this.b.get(size).e();
                        if (e <= 0 || data.e() <= e) {
                            Logger.a("MSG cannot add data uMsgId= %s; MsgId = %s; msgId(%s) = %s", data.d(), Long.valueOf(data.e()), Integer.valueOf(size), Long.valueOf(e));
                            if (data.e() == this.b.get(size).e()) {
                                return false;
                            }
                            size--;
                        } else {
                            Logger.a("MSG can add data uMsgId = %s; MsgId = %s; msgId(%s) = %s", data.d(), Long.valueOf(data.e()), Integer.valueOf(size), Long.valueOf(e));
                            return true;
                        }
                    }
                    return false;
                }
            }
            Logger.a("MSG can add data uMsgId= %s; MsgId = %s", data.d(), Long.valueOf(data.e()));
            return true;
        }
    }

    public List<Data> d() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            for (Data next : this.b) {
                if (next.f() == 2) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f7627a.add(str);
        }
    }

    public boolean b(Data data) {
        this.b.add(data);
        a(data.d());
        return false;
    }

    public void a(int i, Data data) {
        this.b.add(i, data);
    }

    public void a(List<Data> list) {
        this.b.addAll(list);
    }

    public void a(int i, List<Data> list) {
        this.b.addAll(i, list);
    }

    public void b(String str) {
        synchronized (this.b) {
            Iterator<Data> it = this.b.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(str, it.next().a())) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public void e() {
        synchronized (this.b) {
            Iterator<Data> it = this.b.iterator();
            while (it.hasNext()) {
                if (it.next().n()) {
                    it.remove();
                }
            }
        }
    }

    public void f() {
        this.b.clear();
    }

    public static class Service {

        /* renamed from: a  reason: collision with root package name */
        private String f7629a;
        private String b;
        private String c;

        public String a() {
            return this.f7629a;
        }

        public void a(String str) {
            this.f7629a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }
    }

    public static class Data {

        /* renamed from: a  reason: collision with root package name */
        public static final int f7628a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;
        private static AtomicInteger j = new AtomicInteger();
        private long A;
        private String k = ("ID:" + Long.toString(System.currentTimeMillis(), 32) + "@" + String.valueOf(r()));
        private String l;
        private String m;
        private String n;
        private long o;
        private int p;
        private int q;
        private String r;
        private String s;
        private String t;
        private String u;
        private boolean v = true;
        private float w;
        private String x;
        private boolean y = false;
        private int z;

        public String a() {
            return this.k;
        }

        public String b() {
            return this.l;
        }

        public void a(String str) {
            this.l = str;
        }

        public String c() {
            return this.m;
        }

        public void b(String str) {
            this.m = str;
        }

        public String d() {
            return this.n;
        }

        public void c(String str) {
            this.n = str;
        }

        public long e() {
            return this.o;
        }

        public void a(long j2) {
            this.o = j2;
        }

        public int f() {
            return this.p;
        }

        public void a(int i2) {
            this.p = i2;
        }

        public int g() {
            return this.q;
        }

        public void b(int i2) {
            this.q = i2;
        }

        public String h() {
            return this.r;
        }

        public void d(String str) {
            this.r = str;
        }

        public String i() {
            return this.s;
        }

        public void e(String str) {
            this.s = str;
        }

        public String j() {
            return this.t;
        }

        public void f(String str) {
            this.t = str;
        }

        public String k() {
            return this.u;
        }

        public void g(String str) {
            this.u = str;
        }

        public boolean l() {
            return this.v;
        }

        public void a(boolean z2) {
            this.v = z2;
        }

        public float m() {
            return this.w;
        }

        /* access modifiers changed from: package-private */
        public void a(float f2) {
            this.w = f2;
        }

        /* access modifiers changed from: package-private */
        public boolean n() {
            return this.y;
        }

        /* access modifiers changed from: package-private */
        public void b(boolean z2) {
            this.y = z2;
        }

        public int o() {
            return this.z;
        }

        public void c(int i2) {
            this.z = i2;
        }

        public String p() {
            return this.x;
        }

        public void h(String str) {
            this.x = str;
        }

        public long q() {
            return this.A;
        }

        public void b(long j2) {
            this.A = j2;
        }

        private static int r() {
            int i2;
            int i3;
            do {
                i2 = j.get();
                i3 = i2 + 1;
                if (i3 > 16777215) {
                    i3 = 1;
                }
            } while (!j.compareAndSet(i2, i3));
            return i2;
        }

        public String toString() {
            return "Data{id='" + this.k + Operators.SINGLE_QUOTE + ", sessionId='" + this.l + Operators.SINGLE_QUOTE + ", roomId='" + this.m + Operators.SINGLE_QUOTE + ", uMsgId='" + this.n + Operators.SINGLE_QUOTE + ", msgId=" + this.o + ", type=" + this.p + ", state=" + this.q + ", text='" + this.r + Operators.SINGLE_QUOTE + ", url='" + this.s + Operators.SINGLE_QUOTE + ", avatar='" + this.t + Operators.SINGLE_QUOTE + ", name='" + this.u + Operators.SINGLE_QUOTE + ", isYourselfSend=" + this.v + ", progress=" + this.w + ", extra='" + this.x + Operators.SINGLE_QUOTE + ", justUI=" + this.y + ", singleItemTypeId=" + this.z + ", time=" + this.A + Operators.BLOCK_END;
        }
    }
}
