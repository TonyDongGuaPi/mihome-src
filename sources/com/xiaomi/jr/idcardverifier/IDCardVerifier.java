package com.xiaomi.jr.idcardverifier;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.megvii.idcardquality.IDCardQualityLicenseManager;
import com.megvii.licensemanager.ILicenseManager;
import com.megvii.licensemanager.Manager;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.account.XiaomiServices;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.idcardverifier.IDCardVerifier;
import com.xiaomi.jr.idcardverifier.http.SimpleHttpRequest;
import com.xiaomi.jr.idcardverifier.utils.VerifyConstants;
import com.xiaomi.jr.idcardverifier.utils.VerifyStatUtils;
import com.xiaomi.jr.idcardverifier.utils.VerifyUtils;
import java.util.HashMap;
import java.util.Map;

public class IDCardVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10852a = 0;
    public static final int b = -1;
    public static final int c = 10;
    public static final int d = 11;
    public static final int e = 12;
    public static final int f = 13;
    private static final String g = "IDCardVerifier";
    private static Handler h = new Handler(Looper.getMainLooper());

    public interface OnStartListener {
        void onStartFinished(boolean z);
    }

    public static class Starter {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Bundle f10853a = new Bundle();
        private String b;
        private Map<String, String> c;
        private IAccountProvider d;
        /* access modifiers changed from: private */
        public Activity e;
        /* access modifiers changed from: private */
        public Fragment f;
        /* access modifiers changed from: private */
        public android.support.v4.app.Fragment g;
        /* access modifiers changed from: private */
        public int h;
        /* access modifiers changed from: private */
        public OnStartListener i;
        private boolean j;

        public Starter a(String str) {
            this.f10853a.putString("partnerId", str);
            return this;
        }

        public Starter b(String str) {
            this.f10853a.putString(VerifyConstants.i, str);
            return this;
        }

        public Starter c(String str) {
            this.f10853a.putString(VerifyConstants.j, str);
            return this;
        }

        public Starter a(boolean z) {
            this.f10853a.putBoolean(VerifyConstants.k, z);
            return this;
        }

        public Starter d(String str) {
            this.f10853a.putString("processId", str);
            return this;
        }

        public Starter b(boolean z) {
            this.f10853a.putBoolean(VerifyConstants.o, z);
            return this;
        }

        public Starter c(boolean z) {
            this.f10853a.putBoolean(VerifyConstants.p, z);
            return this;
        }

        public Starter a(int i2) {
            this.f10853a.putInt(VerifyConstants.m, i2);
            return this;
        }

        public Starter e(String str) {
            this.f10853a.putString("sign", str);
            return this;
        }

        public Starter f(String str) {
            this.f10853a.putString(VerifyConstants.r, str);
            return this;
        }

        public Starter d(boolean z) {
            this.j = z;
            return this;
        }

        public Starter g(String str) {
            this.b = str;
            return this;
        }

        public Starter a(Map<String, String> map) {
            this.c = map;
            return this;
        }

        public Starter a(Activity activity) {
            this.e = activity;
            return this;
        }

        public Starter a(IAccountProvider iAccountProvider) {
            this.d = iAccountProvider;
            return this;
        }

        public Starter a(Fragment fragment) {
            this.f = fragment;
            return this;
        }

        public Starter a(android.support.v4.app.Fragment fragment) {
            this.g = fragment;
            return this;
        }

        public Starter b(int i2) {
            this.h = i2;
            return this;
        }

        public Starter a(OnStartListener onStartListener) {
            this.i = onStartListener;
            return this;
        }

        public void a() {
            Utils.a();
            if (this.e == null && this.f == null && this.g == null) {
                throw new IllegalArgumentException("activity is null and fragment is null");
            }
            Activity a2 = IDCardVerifier.d(this);
            if (ActivityChecker.a(a2)) {
                String string = this.f10853a.getString("partnerId");
                String string2 = this.f10853a.getString("sign");
                String string3 = this.f10853a.getString(VerifyConstants.r);
                String string4 = this.f10853a.getString(VerifyConstants.j);
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(this.b) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3) || TextUtils.isEmpty(string4)) {
                    VerifyUtils.a(a2.getApplicationContext(), (CharSequence) "invalid params");
                    if (this.i != null) {
                        this.i.onStartFinished(false);
                        return;
                    }
                    return;
                }
                VerifyConstants.a(this.j);
                if (this.d != null) {
                    IDCardVerifier.b(this.d);
                }
                SimpleHttpRequest.a();
                HashMap hashMap = new HashMap();
                hashMap.put("partnerId", string);
                hashMap.put("from", this.b);
                if (this.c != null) {
                    hashMap.putAll(this.c);
                }
                VerifyStatUtils.a(a2.getApplicationContext(), (Map<String, String>) hashMap);
                IDCardVerifier.c(this);
            } else if (this.i != null) {
                this.i.onStartFinished(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(IAccountProvider iAccountProvider) {
        XiaomiServices.a(VerifyConstants.b, VerifyConstants.c, (String) null);
        XiaomiAccountManager.a(iAccountProvider);
    }

    /* access modifiers changed from: private */
    public static void c(Starter starter) {
        Activity d2 = d(starter);
        new Thread(new Runnable(d2.getApplicationContext(), d2, starter) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ Activity f$1;
            private final /* synthetic */ IDCardVerifier.Starter f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                IDCardVerifier.a(this.f$0, this.f$1, this.f$2);
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, Activity activity, Starter starter) {
        Manager manager = new Manager(context);
        IDCardQualityLicenseManager iDCardQualityLicenseManager = new IDCardQualityLicenseManager(context);
        manager.a((ILicenseManager) iDCardQualityLicenseManager);
        manager.c(Client.b(context));
        if (iDCardQualityLicenseManager.checkCachedLicense() > 0) {
            h.post(new Runnable(activity, starter, context) {
                private final /* synthetic */ Activity f$0;
                private final /* synthetic */ IDCardVerifier.Starter f$1;
                private final /* synthetic */ Context f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    IDCardVerifier.a(this.f$0, this.f$1, this.f$2);
                }
            });
        } else {
            h.post(new Runnable(context, starter) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ IDCardVerifier.Starter f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void run() {
                    IDCardVerifier.a(this.f$0, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity, Starter starter, Context context) {
        boolean a2 = ActivityChecker.a(activity);
        if (a2) {
            Intent intent = new Intent(activity, IDCardVerifyActivity.class);
            intent.putExtras(starter.f10853a);
            Utils.a((Context) activity, intent);
            if (starter.g != null) {
                starter.g.startActivityForResult(intent, starter.h);
            } else if (starter.f != null) {
                starter.f.startActivityForResult(intent, starter.h);
            } else {
                starter.e.startActivityForResult(intent, starter.h);
            }
        }
        if (!a2) {
            h.post(new Runnable(context) {
                private final /* synthetic */ Context f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    VerifyUtils.a(this.f$0, R.string.detector_init_fail);
                }
            });
        }
        if (starter.i != null) {
            starter.i.onStartFinished(a2);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, Starter starter) {
        VerifyUtils.a(context, R.string.detector_init_fail);
        if (starter.i != null) {
            starter.i.onStartFinished(false);
        }
    }

    /* access modifiers changed from: private */
    public static Activity d(Starter starter) {
        if (starter.e != null) {
            return starter.e;
        }
        if (starter.g != null) {
            return starter.g.getActivity();
        }
        if (starter.f != null) {
            return starter.f.getActivity();
        }
        return null;
    }
}
