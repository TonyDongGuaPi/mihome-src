package com.xiaomi.jr.hybrid;

import android.util.Log;
import android.util.SparseArray;
import com.xiaomi.jr.hybrid.NativeInterface;

public class HybridCallbackManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1440a = "HybridCallbackManager";
    private static SparseArray<NativeInterface.Callback> b = new SparseArray<>();

    public static void a(NativeInterface.Callback callback) {
        if (callback == null) {
            return;
        }
        if (b.indexOfKey(callback.a()) < 0) {
            b.put(callback.a(), callback);
        } else if (HybridUtils.f1442a) {
            Log.e(f1440a, "Duplicate callback instance! Each event firing must hold an individual callback");
        }
    }

    static int b(NativeInterface.Callback callback) {
        int hashCode = (callback.hashCode() % 60000) + 5000;
        if (b.indexOfKey(hashCode) < 0) {
            return hashCode;
        }
        for (int i = 5000; i < 65000; i++) {
            if (b.indexOfKey(i) < 0) {
                return i;
            }
        }
        Log.w(f1440a, "Can't resolve hash conflict, almost never come here");
        return 0;
    }

    public static void a(NativeInterface.Callback callback, int i) {
        if (callback == null) {
            return;
        }
        if (b.indexOfKey(i) < 0) {
            b.put(i, callback);
        } else if (HybridUtils.f1442a) {
            Log.e(f1440a, "Duplicate callback instance! Each event firing must hold an individual callback");
        }
    }

    public static void c(NativeInterface.Callback callback) {
        if (callback != null && b.indexOfKey(callback.a()) >= 0) {
            b.remove(callback.a());
        }
    }

    public static void a(int i) {
        if (b.indexOfKey(i) >= 0) {
            b.remove(i);
        }
    }

    public static NativeInterface.Callback b(int i) {
        return b.get(i);
    }
}
