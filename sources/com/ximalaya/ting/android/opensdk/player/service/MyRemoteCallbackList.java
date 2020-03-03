package com.ximalaya.ting.android.opensdk.player.service;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.util.Log;

public class MyRemoteCallbackList<T extends IInterface> extends RemoteCallbackList<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2197a = "XmPlayerService";

    public void onCallbackDied(T t, Object obj) {
        super.onCallbackDied(t, obj);
        unregister(t);
        if (obj != null && (obj instanceof ProcessCookie)) {
            Log.e(f2197a, "Process " + ((ProcessCookie) obj).a() + " died");
        }
    }

    static class ProcessCookie {

        /* renamed from: a  reason: collision with root package name */
        private int f2198a;
        private int b;

        public ProcessCookie(int i, int i2) {
            this.f2198a = i;
            this.b = i2;
        }

        public int a() {
            return this.f2198a;
        }

        public void a(int i) {
            this.f2198a = i;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }
    }
}
