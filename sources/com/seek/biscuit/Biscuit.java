package com.seek.biscuit;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Biscuit {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8804a = 0;
    public static final int b = 1;
    private static final String d = "Biscuit";
    Dispatcher c;
    private Executor e;
    private String f;
    private boolean g;
    private int h;
    private int i;
    private boolean j;
    private long k;
    private ArrayList<CompressListener> l = new ArrayList<>();
    private ArrayList<String> m;
    private CompressResult n;
    private OnCompressCompletedListener o;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CompressType {
    }

    Biscuit(ArrayList<String> arrayList, String str, boolean z, int i2, int i3, boolean z2, boolean z3, long j2, CompressListener compressListener, OnCompressCompletedListener onCompressCompletedListener, Executor executor) {
        Utils.p = z3;
        this.e = executor;
        a(compressListener);
        this.m = new ArrayList<>();
        if (arrayList != null) {
            this.m.addAll(arrayList);
        }
        this.f = str;
        this.g = z;
        this.h = i2;
        this.i = i3;
        this.j = z2;
        this.k = j2;
        this.o = onCompressCompletedListener;
    }

    public void a() {
        j();
        this.n = new CompressResult();
        Iterator<String> it = this.m.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (Utils.a(next)) {
                this.e.a(new ImageCompressor(next, this.f, this.h, this.i, this.g, this.j, this.k, this));
            } else {
                it.remove();
                Utils.a(d, "can not recognize the path : " + next);
            }
        }
    }

    private void j() {
        if (this.e == null) {
            this.e = new HandlerExecutor();
        }
        if (this.c == null) {
            this.c = new Dispatcher();
        }
    }

    public ArrayList<String> b() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = this.m.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (Utils.a(next)) {
                ImageCompressor imageCompressor = new ImageCompressor(next, this.f, this.h, this.i, this.g, this.j, this.k, (Biscuit) null);
                if (imageCompressor.a()) {
                    next = imageCompressor.f8810a;
                }
                arrayList.add(next);
            } else {
                Utils.a(d, "can not recognize the path : " + next);
            }
            it.remove();
        }
        return arrayList;
    }

    public void a(CompressListener compressListener) {
        if (compressListener != null) {
            this.l.add(compressListener);
        }
    }

    public void b(CompressListener compressListener) {
        this.l.remove(compressListener);
    }

    public void a(OnCompressCompletedListener onCompressCompletedListener) {
        this.o = onCompressCompletedListener;
    }

    public String c() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public boolean d() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public int e() {
        return this.h;
    }

    public void a(int i2) {
        if (i2 < 0 || i2 > 100) {
            throw new IllegalArgumentException("quality must be 0..100");
        }
        this.h = i2;
    }

    public int f() {
        return this.i;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public boolean g() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public long h() {
        return this.k;
    }

    public void a(long j2) {
        this.k = j2;
    }

    public ArrayList<String> i() {
        return this.m;
    }

    public void a(ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            this.m.addAll(arrayList);
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.m.add(str);
        }
    }

    public static Builder a(Context context) {
        return new Builder(context);
    }

    public static void b(Context context) {
        Utils.b(context);
    }

    public static void c(String str) {
        Utils.b(str);
    }

    /* access modifiers changed from: package-private */
    public void d(String str) {
        Iterator<CompressListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onSuccess(str);
        }
        this.n.f8807a.add(str);
        k();
    }

    private void k() {
        if (this.n.b.size() + this.n.f8807a.size() == this.m.size()) {
            this.m.clear();
            if (this.o != null) {
                this.o.onCompressCompleted(this.n);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(CompressException compressException) {
        Iterator<CompressListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onError(compressException);
        }
        this.n.b.add(compressException.originalPath);
        k();
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private ArrayList<String> f8805a = new ArrayList<>();
        private String b;
        private boolean c = false;
        private int d;
        private int e = 0;
        private boolean f = false;
        private CompressListener g;
        private Context h;
        private Executor i;
        private boolean j = true;
        private long k = -1;
        private OnCompressCompletedListener l;

        public Builder(Context context) {
            this.h = context.getApplicationContext();
            this.d = Utils.c(context);
        }

        public Builder a(String str) {
            if (TextUtils.isEmpty(str) || str.substring(str.length() - 1, str.length()).equals(File.separator)) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("targetDir must be end with " + File.separator);
        }

        public Builder a(boolean z) {
            this.c = z;
            return this;
        }

        public Builder a(long j2) {
            this.k = j2;
            return this;
        }

        public Builder b(boolean z) {
            this.f = z;
            return this;
        }

        public Builder a(int i2) {
            this.e = i2;
            return this;
        }

        public Builder c(boolean z) {
            this.j = z;
            return this;
        }

        public Builder a(Executor executor) {
            this.i = executor;
            return this;
        }

        public Builder b(int i2) {
            if (i2 < 0 || i2 > 100) {
                throw new IllegalArgumentException("quality must be 0..100");
            }
            this.d = i2;
            return this;
        }

        public Builder a(CompressListener compressListener) {
            this.g = compressListener;
            return this;
        }

        public Builder b(String str) {
            this.f8805a.add(str);
            return this;
        }

        public Builder a(List<String> list) {
            this.f8805a.addAll(list);
            return this;
        }

        public Builder a(OnCompressCompletedListener onCompressCompletedListener) {
            this.l = onCompressCompletedListener;
            return this;
        }

        public Biscuit a() {
            if (TextUtils.isEmpty(this.b)) {
                this.b = Utils.a(this.h) + File.separator;
            }
            return new Biscuit(this.f8805a, this.b, this.c, this.d, this.e, this.f, this.j, this.k, this.g, this.l, this.i);
        }
    }
}
