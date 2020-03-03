package com.xiaomi.smarthome.lite.scene;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;

public class LiteSceneRefreshTask {
    private static final int e = 100;

    /* renamed from: a  reason: collision with root package name */
    HandlerThread f19398a = new HandlerThread("lite_scene_refresh");
    Handler b;
    boolean c = true;
    private Callback d;

    public interface Callback {
        void a(HashMap<Integer, Boolean> hashMap);
    }

    private void b() {
    }

    public void a() {
    }

    public void a(Callback callback) {
    }
}
