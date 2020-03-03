package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerInteractionController;

public class RNGestureHandlerInteractionManager implements GestureHandlerInteractionController {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8888a = "waitFor";
    private static final String b = "simultaneousHandlers";
    private SparseArray<int[]> c = new SparseArray<>();
    private SparseArray<int[]> d = new SparseArray<>();

    public boolean b(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    public boolean d(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    public void a(int i) {
        this.c.remove(i);
        this.d.remove(i);
    }

    private int[] a(ReadableMap readableMap, String str) {
        ReadableArray array = readableMap.getArray(str);
        int[] iArr = new int[array.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = array.getInt(i);
        }
        return iArr;
    }

    public void a(GestureHandler gestureHandler, ReadableMap readableMap) {
        gestureHandler.a((GestureHandlerInteractionController) this);
        if (readableMap.hasKey(f8888a)) {
            this.c.put(gestureHandler.d(), a(readableMap, f8888a));
        }
        if (readableMap.hasKey(b)) {
            this.d.put(gestureHandler.d(), a(readableMap, b));
        }
    }

    public boolean a(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        int[] iArr = this.c.get(gestureHandler.d());
        if (iArr != null) {
            for (int i : iArr) {
                if (i == gestureHandler2.d()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean c(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        int[] iArr = this.d.get(gestureHandler.d());
        if (iArr != null) {
            for (int i : iArr) {
                if (i == gestureHandler2.d()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void a() {
        this.c.clear();
        this.d.clear();
    }
}
