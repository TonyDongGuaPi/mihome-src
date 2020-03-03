package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

class ImageLoaderEngine {

    /* renamed from: a  reason: collision with root package name */
    final ImageLoaderConfiguration f8465a;
    /* access modifiers changed from: private */
    public Executor b;
    /* access modifiers changed from: private */
    public Executor c;
    private Executor d;
    private final Map<Integer, String> e = Collections.synchronizedMap(new HashMap());
    private final Map<String, ReentrantLock> f = new WeakHashMap();
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final AtomicBoolean h = new AtomicBoolean(false);
    private final AtomicBoolean i = new AtomicBoolean(false);
    private final Object j = new Object();

    ImageLoaderEngine(ImageLoaderConfiguration imageLoaderConfiguration) {
        this.f8465a = imageLoaderConfiguration;
        this.b = imageLoaderConfiguration.g;
        this.c = imageLoaderConfiguration.h;
        this.d = DefaultConfigurationFactory.a();
    }

    /* access modifiers changed from: package-private */
    public void a(final LoadAndDisplayImageTask loadAndDisplayImageTask) {
        this.d.execute(new Runnable() {
            public void run() {
                File a2 = ImageLoaderEngine.this.f8465a.o.a(loadAndDisplayImageTask.a());
                boolean z = a2 != null && a2.exists();
                ImageLoaderEngine.this.h();
                if (z) {
                    ImageLoaderEngine.this.c.execute(loadAndDisplayImageTask);
                } else {
                    ImageLoaderEngine.this.b.execute(loadAndDisplayImageTask);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(ProcessAndDisplayImageTask processAndDisplayImageTask) {
        h();
        this.c.execute(processAndDisplayImageTask);
    }

    /* access modifiers changed from: private */
    public void h() {
        if (!this.f8465a.i && ((ExecutorService) this.b).isShutdown()) {
            this.b = i();
        }
        if (!this.f8465a.j && ((ExecutorService) this.c).isShutdown()) {
            this.c = i();
        }
    }

    private Executor i() {
        return DefaultConfigurationFactory.a(this.f8465a.k, this.f8465a.l, this.f8465a.m);
    }

    /* access modifiers changed from: package-private */
    public String a(ImageAware imageAware) {
        return this.e.get(Integer.valueOf(imageAware.f()));
    }

    /* access modifiers changed from: package-private */
    public void a(ImageAware imageAware, String str) {
        this.e.put(Integer.valueOf(imageAware.f()), str);
    }

    /* access modifiers changed from: package-private */
    public void b(ImageAware imageAware) {
        this.e.remove(Integer.valueOf(imageAware.f()));
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.h.set(z);
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        this.i.set(z);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.g.set(true);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.g.set(false);
        synchronized (this.j) {
            this.j.notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (!this.f8465a.i) {
            ((ExecutorService) this.b).shutdownNow();
        }
        if (!this.f8465a.j) {
            ((ExecutorService) this.c).shutdownNow();
        }
        this.e.clear();
        this.f.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(Runnable runnable) {
        this.d.execute(runnable);
    }

    /* access modifiers changed from: package-private */
    public ReentrantLock a(String str) {
        ReentrantLock reentrantLock = this.f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        ReentrantLock reentrantLock2 = new ReentrantLock();
        this.f.put(str, reentrantLock2);
        return reentrantLock2;
    }

    /* access modifiers changed from: package-private */
    public AtomicBoolean d() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public Object e() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.h.get();
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.i.get();
    }
}
