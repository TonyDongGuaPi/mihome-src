package com.xiaomi.smarthome.library.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListCache<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18688a = 16;
    private int b = 16;
    private ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.WriteLock d = this.c.writeLock();
    private ReentrantReadWriteLock.ReadLock e = this.c.readLock();
    private final ArrayList<T> f;
    private int g = 0;

    public ListCache(int i) {
        i = i <= 4 ? 4 : i;
        this.b = i;
        this.f = new ArrayList<>(Collections.nCopies(i, (Object) null));
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.f.size();
    }

    public boolean c() {
        return this.f.isEmpty();
    }

    public boolean a(Object obj) {
        return this.f.contains(obj);
    }

    public boolean b(T t) {
        this.d.lock();
        try {
            int i = this.g % this.b;
            this.f.set(i, t);
            this.g = (i + 1) % this.b;
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            this.d.unlock();
            throw th;
        }
        this.d.unlock();
        return false;
    }

    public void d() {
        this.d.lock();
        try {
            this.f.clear();
            this.g = 0;
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            this.d.unlock();
            throw th;
        }
        this.d.unlock();
    }

    public T a(int i) {
        this.e.lock();
        try {
            this.f.get(i);
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            this.e.unlock();
            throw th;
        }
        this.e.unlock();
        return null;
    }

    public List<T> e() {
        ArrayList arrayList = new ArrayList();
        this.e.lock();
        int i = 0;
        while (i < this.b) {
            try {
                T t = this.f.get((this.g + i) % this.b);
                if (t != null) {
                    arrayList.add(t);
                }
                i++;
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                this.e.unlock();
                throw th;
            }
        }
        this.e.unlock();
        return arrayList;
    }
}
