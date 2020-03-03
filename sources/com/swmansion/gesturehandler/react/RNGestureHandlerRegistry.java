package com.swmansion.gesturehandler.react;

import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerRegistry;
import java.util.ArrayList;

public class RNGestureHandlerRegistry implements GestureHandlerRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final SparseArray<GestureHandler> f8891a = new SparseArray<>();
    private final SparseArray<Integer> b = new SparseArray<>();
    private final SparseArray<ArrayList<GestureHandler>> c = new SparseArray<>();

    public synchronized void a(GestureHandler gestureHandler) {
        this.f8891a.put(gestureHandler.d(), gestureHandler);
    }

    @Nullable
    public synchronized GestureHandler a(int i) {
        return this.f8891a.get(i);
    }

    public synchronized boolean a(int i, int i2) {
        GestureHandler gestureHandler = this.f8891a.get(i);
        if (gestureHandler == null) {
            return false;
        }
        b(gestureHandler);
        a(i2, gestureHandler);
        return true;
    }

    private synchronized void a(int i, GestureHandler gestureHandler) {
        if (this.b.get(gestureHandler.d()) == null) {
            this.b.put(gestureHandler.d(), Integer.valueOf(i));
            ArrayList arrayList = this.c.get(i);
            if (arrayList == null) {
                ArrayList arrayList2 = new ArrayList(1);
                arrayList2.add(gestureHandler);
                this.c.put(i, arrayList2);
            } else {
                arrayList.add(gestureHandler);
            }
        } else {
            throw new IllegalStateException("Handler " + gestureHandler + " already attached");
        }
    }

    private synchronized void b(GestureHandler gestureHandler) {
        Integer num = this.b.get(gestureHandler.d());
        if (num != null) {
            this.b.remove(gestureHandler.d());
            ArrayList arrayList = this.c.get(num.intValue());
            if (arrayList != null) {
                arrayList.remove(gestureHandler);
                if (arrayList.size() == 0) {
                    this.c.remove(num.intValue());
                }
            }
        }
        if (gestureHandler.e() != null) {
            gestureHandler.l();
        }
    }

    public synchronized void b(int i) {
        GestureHandler gestureHandler = this.f8891a.get(i);
        if (gestureHandler != null) {
            b(gestureHandler);
            this.f8891a.remove(i);
        }
    }

    public synchronized void a() {
        this.f8891a.clear();
        this.b.clear();
        this.c.clear();
    }

    public synchronized ArrayList<GestureHandler> c(int i) {
        return this.c.get(i);
    }

    public synchronized ArrayList<GestureHandler> a(View view) {
        return c(view.getId());
    }
}
