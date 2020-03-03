package com.xiaomi.assemble.control;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.coloros.mcssdk.callback.PushCallback;
import com.coloros.mcssdk.mode.SubscribeResult;
import com.xiaomi.mipush.sdk.AbstractPushManager;
import com.xiaomi.mipush.sdk.COSPushHelper;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class COSPushManager implements AbstractPushManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9991a = "COS_PUSH";
    private static final String b = "87Vf3cnJD7wo0ckc0C8c4S0wK";
    private static final String c = "73be687Ba2DA2f18b767773d55029707";
    private static final int d = 2000;
    private static final int[] e = {2000, 4000, 8000};
    private COSPushCallback f;
    private RetryWorker g = null;
    private Context h;

    private COSPushManager(Context context) {
        this.h = context;
        this.f = new COSPushCallback(new WeakReference(this));
    }

    public static COSPushManager newInstance(Context context) {
        return new COSPushManager(context);
    }

    public static boolean isSupportPush(Context context) {
        return PushManager.isSupportPush(context);
    }

    public void a() {
        a(this.h, b, c);
    }

    private void a(Context context, String str, String str2) {
        try {
            PushManager.getInstance().register(context, str, str2, this.f);
        } catch (Exception e2) {
            Log.e(f9991a, e2.getMessage() + "   in doRegister");
        }
    }

    public void b() {
        c();
    }

    private void c() {
        try {
            PushManager.getInstance().unRegister();
        } catch (Exception e2) {
            Log.e(f9991a, e2.getMessage() + "in doUnRegister");
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, String str) {
        if (COSPushHelper.a(this.h)) {
            if (this.g == null) {
                this.g = new RetryWorker(e, new Runnable() {
                    public void run() {
                        COSPushManager.this.a();
                    }
                });
            }
            this.g.b();
            return;
        }
        COSPushHelper.a(true);
    }

    /* access modifiers changed from: private */
    public void b(int i, String str) {
        if (this.g != null) {
            this.g.c();
        }
        COSPushHelper.a(false);
        Log.i(f9991a, "registerId = " + str);
        a(str);
    }

    private void a(String str) {
        Log.i(f9991a, "begin upload cos token ");
        if (this.h != null) {
            COSPushHelper.a(this.h, str);
        }
    }

    private static class COSPushCallback implements PushCallback {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<COSPushManager> f9993a;

        public void onGetAliases(int i, List<SubscribeResult> list) {
        }

        public void onGetNotificationStatus(int i, int i2) {
        }

        public void onGetPushStatus(int i, int i2) {
        }

        public void onGetTags(int i, List<SubscribeResult> list) {
        }

        public void onGetUserAccounts(int i, List<SubscribeResult> list) {
        }

        public void onSetAliases(int i, List<SubscribeResult> list) {
        }

        public void onSetPushTime(int i, String str) {
        }

        public void onSetTags(int i, List<SubscribeResult> list) {
        }

        public void onSetUserAccounts(int i, List<SubscribeResult> list) {
        }

        public void onUnsetAliases(int i, List<SubscribeResult> list) {
        }

        public void onUnsetTags(int i, List<SubscribeResult> list) {
        }

        public void onUnsetUserAccounts(int i, List<SubscribeResult> list) {
        }

        private COSPushCallback(WeakReference<COSPushManager> weakReference) {
            this.f9993a = weakReference;
        }

        public void onRegister(int i, String str) {
            COSPushManager cOSPushManager;
            COSPushManager cOSPushManager2;
            if (i != 0 || TextUtils.isEmpty(str)) {
                Log.i(COSPushManager.f9991a, "register fail , code == " + i + " msg == " + str);
                if (this.f9993a != null && (cOSPushManager = (COSPushManager) this.f9993a.get()) != null) {
                    cOSPushManager.a(i, str);
                    return;
                }
                return;
            }
            Log.i(COSPushManager.f9991a, "register success  && registerId = " + str);
            if (this.f9993a != null && (cOSPushManager2 = (COSPushManager) this.f9993a.get()) != null) {
                cOSPushManager2.b(i, str);
            }
        }

        public void onUnRegister(int i) {
            if (i == 0) {
                Log.i(COSPushManager.f9991a, "UnRegister success");
            } else {
                Log.i(COSPushManager.f9991a, "UnRegister fail");
            }
        }
    }

    private static class RetryWorker {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f9994a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int[] c;
        /* access modifiers changed from: private */
        public Runnable d;
        /* access modifiers changed from: private */
        public volatile boolean e;
        private Executor f;

        static /* synthetic */ int h(RetryWorker retryWorker) {
            int i = retryWorker.b;
            retryWorker.b = i + 1;
            return i;
        }

        private RetryWorker(int[] iArr, Runnable runnable) {
            this.b = 0;
            this.e = false;
            if (iArr == null || iArr.length <= 0) {
                throw new IllegalArgumentException("error argument！");
            }
            this.f9994a = iArr.length;
            this.c = iArr;
            this.d = runnable;
            this.f = Executors.newSingleThreadExecutor();
        }

        /* access modifiers changed from: private */
        public void b() {
            if (this.b < this.f9994a && !this.e) {
                this.f.execute(new Runnable() {
                    public void run() {
                        Log.v(COSPushManager.f9991a, "try " + RetryWorker.this.b + "　 register");
                        if (RetryWorker.this.b >= RetryWorker.this.f9994a || RetryWorker.this.e) {
                            Log.v(COSPushManager.f9991a, "retry failed, and maximum retrial number has been reached ");
                            return;
                        }
                        try {
                            Thread.sleep((long) RetryWorker.this.c[RetryWorker.this.b]);
                        } catch (InterruptedException unused) {
                        }
                        if (RetryWorker.this.d != null && !RetryWorker.this.e) {
                            RetryWorker.this.d.run();
                            RetryWorker.h(RetryWorker.this);
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public void c() {
            this.e = true;
            this.b = 0;
        }

        public boolean a() {
            return this.e;
        }
    }
}
