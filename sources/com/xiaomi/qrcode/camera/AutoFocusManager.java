package com.xiaomi.qrcode.camera;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

final class AutoFocusManager implements Camera.AutoFocusCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12986a = "AutoFocusManager";
    private static final long b = 2000;
    private static final Collection<String> c = new ArrayList(2);
    private boolean d;
    private boolean e;
    private final boolean f;
    private final Camera g;
    private AsyncTask<?, ?, ?> h;

    static {
        c.add("auto");
        c.add("macro");
    }

    AutoFocusManager(Context context, Camera camera) {
        this.g = camera;
        String focusMode = camera.getParameters().getFocusMode();
        this.f = c.contains(focusMode);
        String str = f12986a;
        Log.i(str, "Current focus mode '" + focusMode + "'; use auto focus? " + this.f);
        a();
    }

    public synchronized void onAutoFocus(boolean z, Camera camera) {
        this.e = false;
        c();
    }

    private synchronized void c() {
        if (!this.d && this.h == null) {
            AutoFocusTask autoFocusTask = new AutoFocusTask();
            try {
                autoFocusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                this.h = autoFocusTask;
            } catch (RejectedExecutionException e2) {
                Log.w(f12986a, "Could not request auto focus", e2);
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        if (this.f) {
            this.h = null;
            if (!this.d && !this.e) {
                try {
                    this.g.autoFocus(this);
                    this.e = true;
                } catch (RuntimeException e2) {
                    Log.w(f12986a, "Unexpected exception while focusing", e2);
                    c();
                }
            }
        }
        return;
    }

    private synchronized void d() {
        if (this.h != null) {
            if (this.h.getStatus() != AsyncTask.Status.FINISHED) {
                this.h.cancel(true);
            }
            this.h = null;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        this.d = true;
        if (this.f) {
            d();
            try {
                this.g.cancelAutoFocus();
            } catch (RuntimeException e2) {
                Log.w(f12986a, "Unexpected exception while cancelling focusing", e2);
            }
        }
        return;
    }

    private final class AutoFocusTask extends AsyncTask<Object, Object, Object> {
        private AutoFocusTask() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException unused) {
            }
            AutoFocusManager.this.a();
            return null;
        }
    }
}
