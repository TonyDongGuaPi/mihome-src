package com.xiaomi.market.sdk.silentupdate;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.xiaomi.market.IAppDownloadManager;
import com.xiaomi.market.sdk.AppGlobal;
import com.xiaomi.market.sdk.Client;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;
import com.xiaomi.market.sdk.silentupdate.UpdateResultReceiver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SilentUpdater {
    private static final int L = 1;
    private static final int M = 2;
    private static final int N = 3;
    private static final int O = 4;

    /* renamed from: a  reason: collision with root package name */
    public static final String f11122a = "com.xiaomi.discover";
    public static final String b = "com.xiaomi.mipicks";
    public static final String c = "com.xiaomi.market";
    public static final int d = -1;
    public static final int e = -2;
    public static final int f = -3;
    public static final int g = -4;
    public static final int h = -5;
    public static final int i = -6;
    public static final int j = -7;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 3;
    public static final int n = 4;
    public static final int o = 5;
    public static final int p = 19;
    public static final int q = 0;
    public static final int r = -1;
    public static final int s = -2;
    public static final int t = -3;
    public static final int u = -4;
    public static final int v = -5;
    public static final int w = -100;
    private static final String x = "com.xiaomi.market.service.AppDownloadService";
    private static final String y = "selfupdatesdk_";
    /* access modifiers changed from: private */
    public String A;
    /* access modifiers changed from: private */
    public String B;
    /* access modifiers changed from: private */
    public int C;
    /* access modifiers changed from: private */
    public UpdateCallback D;
    /* access modifiers changed from: private */
    public Bundle E;
    /* access modifiers changed from: private */
    public boolean F;
    /* access modifiers changed from: private */
    public boolean G;
    /* access modifiers changed from: private */
    public boolean H;
    /* access modifiers changed from: private */
    public boolean I;
    /* access modifiers changed from: private */
    public IAppDownloadManager J;
    private List<Integer> K = new ArrayList();
    /* access modifiers changed from: private */
    public IBinder.DeathRecipient P = new IBinder.DeathRecipient() {
        public void binderDied() {
            IAppDownloadManager unused = SilentUpdater.this.J = null;
        }
    };
    private final ServiceConnection Q = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IAppDownloadManager unused = SilentUpdater.this.J = IAppDownloadManager.Stub.asInterface(iBinder);
            try {
                SilentUpdater.this.J.asBinder().linkToDeath(SilentUpdater.this.P, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            UpdateResultReceiver.get().register(SilentUpdater.this.R);
            SilentUpdater.this.j();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            IAppDownloadManager b = SilentUpdater.this.J;
            if (b != null) {
                b.asBinder().unlinkToDeath(SilentUpdater.this.P, 0);
            }
            IAppDownloadManager unused = SilentUpdater.this.J = null;
        }
    };
    /* access modifiers changed from: private */
    public final UpdateResultReceiver.Callback R = new UpdateResultReceiver.Callback() {
        private int b = 0;
        private int c = 0;
        private int d = -1;

        public void a(Bundle bundle) {
            if (SilentUpdater.this.D != null && bundle != null) {
                int i = bundle.getInt("errorCode");
                if (this.b != i) {
                    this.b = i;
                    if (i < 0 || i == 4) {
                        UpdateResultReceiver.get().unregister();
                        SilentUpdater.this.p();
                    }
                    if (i < 0) {
                        SilentUpdater.this.D.a(i, bundle.getInt("reason"));
                    } else {
                        SilentUpdater.this.D.c(this.b);
                    }
                }
                if (i == 5) {
                    int i2 = bundle.getInt("status");
                    int i3 = bundle.getInt(NotificationCompat.CATEGORY_PROGRESS);
                    if (this.c != i2) {
                        SilentUpdater.this.D.a(i2);
                        this.c = i2;
                    }
                    if (this.d != i3) {
                        SilentUpdater.this.D.b(i3);
                        this.d = i3;
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public String z;

    public interface UpdateCallback {
        void a(int i);

        void a(int i, int i2);

        void b(int i);

        void c(int i);
    }

    public void a() {
        b(1);
    }

    public void b() {
        this.F = true;
        a();
    }

    public void c() {
        b(2);
    }

    public void d() {
        a(1);
        a(2);
        a(3);
        b(4);
    }

    public void e() {
        b(3);
    }

    private synchronized void a(int i2) {
        Iterator<Integer> it = this.K.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() == i2) {
                it.remove();
            }
        }
    }

    private synchronized void b(int i2) {
        this.K.add(Integer.valueOf(i2));
        if (this.J == null) {
            o();
        } else {
            j();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void j() {
        for (Integer intValue : new ArrayList(this.K)) {
            switch (intValue.intValue()) {
                case 1:
                    n();
                    break;
                case 2:
                    m();
                    break;
                case 3:
                    l();
                    break;
                case 4:
                    k();
                    break;
            }
        }
        this.K.clear();
    }

    private void k() {
        try {
            this.J.cancel(AppGlobal.a().getPackageName(), AppGlobal.a().getPackageName());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void l() {
        try {
            this.J.resume(AppGlobal.a().getPackageName(), AppGlobal.a().getPackageName());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void m() {
        try {
            this.J.pause(AppGlobal.a().getPackageName(), AppGlobal.a().getPackageName());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void n() {
        Context a2 = AppGlobal.a();
        String a3 = SigGenerator.a();
        String str = y + a2.getPackageName();
        try {
            String a4 = SigGenerator.a(a3, this.A, a2.getPackageName(), str, this.B);
            Bundle bundle = new Bundle();
            bundle.putString("packageName", a2.getPackageName());
            bundle.putString("senderPackageName", a2.getPackageName());
            bundle.putString("ref", str);
            bundle.putString("nonce", a3);
            bundle.putString("appSignature", a4);
            bundle.putString("appClientId", this.A);
            bundle.putInt("ext_targetVersionCode", this.C);
            bundle.putBoolean("show_cta", this.I);
            bundle.putInt(Constants.J, XiaomiUpdateAgent.d().ordinal());
            if (this.F) {
                bundle.putBoolean("marketClientControlParam_force_update", true);
            }
            if (this.H) {
                bundle.putBoolean("marketClientControlParam_hide_download", true);
            }
            if (this.G) {
                bundle.putBoolean("marketClientControlParam_download_wifi_only", true);
            }
            if (this.E != null) {
                for (String remove : bundle.keySet()) {
                    this.E.remove(remove);
                }
                bundle.putAll(this.E);
            }
            this.J.download(bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void o() {
        if (a(this.z)) {
            Intent intent = new Intent(x);
            intent.setPackage(this.z);
            AppGlobal.a().bindService(intent, this.Q, 1);
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: private */
    public void p() {
        if (this.J != null) {
            try {
                this.J.asBinder().unlinkToDeath(this.P, 0);
                AppGlobal.a().unbindService(this.Q);
                this.J = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean f() {
        if (Client.s()) {
            return b(b, -1) || b(f11122a, -1);
        }
        return b("com.xiaomi.market", -1);
    }

    public static boolean g() {
        if (Client.s()) {
            return b(b, 1914114) || b(f11122a, 1914114);
        }
        return b("com.xiaomi.market", 1914114);
    }

    public static boolean h() {
        return g();
    }

    public static boolean i() {
        return g();
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return f();
        }
        return b(str, -1);
    }

    /* access modifiers changed from: private */
    public static boolean b(String str, int i2) {
        if (i2 > 0) {
            try {
                if (AppGlobal.b().getPackageInfo(str, 0).versionCode < i2) {
                    return false;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        Intent intent = new Intent(x);
        intent.setPackage(str);
        List<ResolveInfo> queryIntentServices = AppGlobal.b().queryIntentServices(intent, 0);
        if (queryIntentServices != null) {
            if (!queryIntentServices.isEmpty()) {
                return queryIntentServices.get(0).serviceInfo.isEnabled();
            }
        }
        return false;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f11126a = (!SilentUpdater.class.desiredAssertionStatus());
        private String b;
        private String c;
        private String d;
        private int e = -1;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private Bundle j;
        private UpdateCallback k;

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder c(String str) {
            this.d = str;
            return this;
        }

        public Builder a(int i2) {
            this.e = i2;
            return this;
        }

        public Builder a(UpdateCallback updateCallback) {
            this.k = updateCallback;
            return this;
        }

        public Builder a(Bundle bundle) {
            this.j = new Bundle(bundle);
            return this;
        }

        public Builder a(boolean z) {
            this.f = z;
            return this;
        }

        public Builder b(boolean z) {
            this.i = z;
            return this;
        }

        public Builder c(boolean z) {
            this.g = z;
            return this;
        }

        public Builder d(boolean z) {
            this.h = z;
            return this;
        }

        public SilentUpdater a() {
            SilentUpdater b2 = b();
            b2.a();
            return b2;
        }

        public SilentUpdater b() {
            if (!f11126a && TextUtils.isEmpty(this.c)) {
                throw new AssertionError();
            } else if (!f11126a && TextUtils.isEmpty(this.d)) {
                throw new AssertionError();
            } else if (f11126a || this.e >= 0) {
                SilentUpdater silentUpdater = new SilentUpdater();
                String unused = silentUpdater.A = this.c;
                String unused2 = silentUpdater.B = this.d;
                int unused3 = silentUpdater.C = this.e;
                if (TextUtils.isEmpty(this.b)) {
                    if (Client.s()) {
                        this.b = SilentUpdater.b(SilentUpdater.b, -1) ? SilentUpdater.b : SilentUpdater.f11122a;
                    } else {
                        this.b = "com.xiaomi.market";
                    }
                }
                String unused4 = silentUpdater.z = this.b;
                UpdateCallback unused5 = silentUpdater.D = this.k;
                Bundle unused6 = silentUpdater.E = this.j;
                boolean unused7 = silentUpdater.F = this.f;
                boolean unused8 = silentUpdater.G = this.g;
                boolean unused9 = silentUpdater.H = this.h;
                boolean unused10 = silentUpdater.I = this.i;
                return silentUpdater;
            } else {
                throw new AssertionError();
            }
        }
    }
}
