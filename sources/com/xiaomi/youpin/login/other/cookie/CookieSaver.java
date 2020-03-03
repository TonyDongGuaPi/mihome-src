package com.xiaomi.youpin.login.other.cookie;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

class CookieSaver {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23578a = 1;
    private static final int b = 30000;
    private final Handler c = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            CookieSaver.this.b();
            return true;
        }
    });
    private boolean d;
    private PersistCookiesAsyncTask e;
    private CookieManager f;

    public CookieSaver(boolean z, CookieManager cookieManager) {
        this.d = z;
        this.f = cookieManager;
        this.e = new PersistCookiesAsyncTask(z, cookieManager);
    }

    public void a() {
        if (this.d) {
            this.c.sendEmptyMessageDelayed(1, 30000);
        }
    }

    public void b() {
        this.c.removeMessages(1);
        new PersistCookiesAsyncTask(this.d, this.f).execute(new Void[0]);
    }

    private static class PersistCookiesAsyncTask extends AsyncTask<Void, Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private boolean f23580a;
        private CookieManager b;

        public PersistCookiesAsyncTask(boolean z, CookieManager cookieManager) {
            this.f23580a = z;
            this.b = cookieManager;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            if (this.f23580a) {
                CookieSyncManager.getInstance().sync();
                return null;
            }
            a();
            return null;
        }

        @TargetApi(21)
        private void a() {
            if (this.b != null) {
                this.b.flush();
            }
        }
    }
}
