package com.xiaomi.smarthome;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppStateNotifier extends ApplicationLifeCycle {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1496a = "app_state_changed";
    public static final String b = "current_state";
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    private static final String h = "AppStateNotifier";
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 4;
    private static final int l = 9;
    private static final int m = 8;
    private static final int n = 5;
    private static final int o = 7;
    private static final int p = 6;
    private static final HashMap<Integer, int[]> q = new HashMap<>();
    private volatile int r = 0;
    private List<LoginCallback> s = new ArrayList();
    /* access modifiers changed from: private */
    public Handler t = new Handler() {
        public void handleMessage(Message message) {
            AppStateNotifier.this.a(message.what);
        }
    };

    public interface LoginCallback {
        void a();

        void b();
    }

    static {
        q.put(1, new int[]{0, 1});
        q.put(8, new int[]{1, 2});
        q.put(5, new int[]{1, 3});
        q.put(2, new int[]{2, 4});
        q.put(7, new int[]{2, 3});
        q.put(6, new int[]{4, 3});
        q.put(4, new int[]{1, 2});
        q.put(9, new int[]{3, 2});
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        int[] iArr = q.get(Integer.valueOf(i2));
        if (iArr != null && this.r != iArr[1]) {
            this.r = iArr[1];
            b(this.r);
            Log.e(h, "change state to " + this.r);
            LogUtilGrey.a(h, "change state to " + this.r);
        }
    }

    public int a() {
        return this.r;
    }

    public void a(LoginCallback loginCallback) {
        if (loginCallback != null) {
            if (this.r == 4) {
                loginCallback.a();
            } else if (this.r == 3) {
                loginCallback.b();
            } else {
                synchronized (this.s) {
                    if (this.r == 4) {
                        loginCallback.a();
                    } else if (this.r == 3) {
                        loginCallback.b();
                    } else {
                        this.s.add(loginCallback);
                    }
                }
            }
        }
    }

    private void b(int i2) {
        if (i2 == 4 || i2 == 3) {
            LogUtilGrey.a("login", "notifyLoginCallbackList state=" + i2);
            synchronized (this.s) {
                int i3 = 0;
                while (i3 < this.s.size()) {
                    try {
                        LoginCallback loginCallback = this.s.get(i3);
                        if (loginCallback != null) {
                            if (i2 == 4) {
                                loginCallback.a();
                            } else if (i2 == 3) {
                                loginCallback.b();
                            }
                        }
                        i3++;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                this.s.clear();
            }
        }
    }

    public void b() {
        a(1);
        LogUtilGrey.a("login", "AppStateNotifier onStart");
        CoreApi.a().a(CommonApplication.getAppContext(), (CoreApi.IsAccountReadyCallback) new CoreApi.IsAccountReadyCallback() {
            public void a(boolean z, String str) {
                if (z) {
                    if (GlobalSetting.u) {
                        LogUtilGrey.a("login", "AppStateNotifier CoreApi isAccountReady isMiLoggedIn=" + z);
                    }
                    AppStateNotifier.this.a(2);
                    return;
                }
                AppStateNotifier.this.t.sendEmptyMessageDelayed(5, 500);
            }
        });
    }

    public void c() {
        if (GlobalSetting.u) {
            LogUtilGrey.a("login", "AppStateNotifier listenLogin MESSAGE_LOGIN_SUCCESS");
        }
        a(2);
    }

    public void d() {
        a(8);
        a(9);
    }

    public void e() {
        a(9);
    }

    public void f() {
        a(5);
    }

    public void g() {
        this.t.sendEmptyMessage(7);
    }

    public void h() {
        a(6);
    }
}
