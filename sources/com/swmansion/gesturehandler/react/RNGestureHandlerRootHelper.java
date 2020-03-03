package com.swmansion.gesturehandler.react;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerOrchestrator;

public class RNGestureHandlerRootHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final float f8892a = 0.1f;
    private final ReactContext b;
    private final GestureHandlerOrchestrator c;
    private final GestureHandler d;
    /* access modifiers changed from: private */
    public final ReactRootView e;
    /* access modifiers changed from: private */
    public boolean f = false;
    private boolean g = false;

    private static ReactRootView a(ViewGroup viewGroup) {
        UiThreadUtil.assertOnUiThread();
        ViewParent viewParent = viewGroup;
        while (viewParent != null && !(viewParent instanceof ReactRootView)) {
            viewParent = viewParent.getParent();
        }
        if (viewParent != null) {
            return (ReactRootView) viewParent;
        }
        throw new IllegalStateException("View " + viewGroup + " has not been mounted under ReactRootView");
    }

    public RNGestureHandlerRootHelper(ReactContext reactContext, ViewGroup viewGroup) {
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        if (id >= 1) {
            RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) reactContext.getNativeModule(RNGestureHandlerModule.class);
            RNGestureHandlerRegistry registry = rNGestureHandlerModule.getRegistry();
            this.e = a(viewGroup);
            Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Initialize gesture handler for root view " + this.e);
            this.b = reactContext;
            this.c = new GestureHandlerOrchestrator(viewGroup, registry, new RNViewConfigurationHelper());
            this.c.a(0.1f);
            this.d = new RootViewGestureHandler();
            this.d.c(-id);
            registry.a(this.d);
            registry.a(this.d.d(), id);
            rNGestureHandlerModule.registerRootHelper(this);
            return;
        }
        throw new IllegalStateException("Expect view tag to be set for " + viewGroup);
    }

    public void a() {
        Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Tearing down gesture handler registered for root view " + this.e);
        RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) this.b.getNativeModule(RNGestureHandlerModule.class);
        rNGestureHandlerModule.getRegistry().b(this.d.d());
        rNGestureHandlerModule.unregisterRootHelper(this);
    }

    public ReactRootView b() {
        return this.e;
    }

    private class RootViewGestureHandler extends GestureHandler {
        private RootViewGestureHandler() {
        }

        /* access modifiers changed from: protected */
        public void a(MotionEvent motionEvent) {
            if (k() == 0) {
                o();
                boolean unused = RNGestureHandlerRootHelper.this.f = false;
            }
            if (motionEvent.getActionMasked() == 1) {
                p();
            }
        }

        /* access modifiers changed from: protected */
        public void a() {
            boolean unused = RNGestureHandlerRootHelper.this.f = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setAction(3);
            RNGestureHandlerRootHelper.this.e.onChildStartedNativeGesture(obtain);
        }
    }

    public void a(boolean z) {
        if (this.c != null && !this.g) {
            c();
        }
    }

    public boolean a(MotionEvent motionEvent) {
        this.g = true;
        this.c.a(motionEvent);
        this.g = false;
        if (this.f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.d != null && this.d.k() == 2) {
            this.d.n();
            this.d.p();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i, boolean z) {
        if (z) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    RNGestureHandlerRootHelper.this.c();
                }
            });
        }
    }
}
