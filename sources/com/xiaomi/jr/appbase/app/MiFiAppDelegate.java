package com.xiaomi.jr.appbase.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import com.xiaomi.jr.appbase.BaseActivity;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.appbase.utils.DialogHelper;
import com.xiaomi.jr.base.IAppDelegate;
import com.xiaomi.jr.base.IDialogDelegate;
import com.xiaomi.jr.base.IUserPreference;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import com.xiaomi.jr.deeplink.DeeplinkUtils;

public class MiFiAppDelegate implements IAppDelegate {
    private static MiFiAppDelegate b = new MiFiAppDelegate();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f1395a;
    private IDialogDelegate c = new IDialogDelegate() {
        public void a(Context context, String str, String str2) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).postForeground(new Runnable(context, str, str2) {
                    private final /* synthetic */ Context f$0;
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ String f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        DialogHelper.a(this.f$0, this.f$1, this.f$2);
                    }
                });
            } else {
                DialogHelper.a(context, str, str2);
            }
        }

        public void a(Context context, String str, String str2, boolean z, String str3, String str4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, String str5) {
            Context context2 = context;
            if (context2 instanceof BaseActivity) {
                ((BaseActivity) context2).postForeground(new Runnable(context, str, str2, z, str3, str4, onClickListener, onClickListener2, str5) {
                    private final /* synthetic */ Context f$0;
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ boolean f$3;
                    private final /* synthetic */ String f$4;
                    private final /* synthetic */ String f$5;
                    private final /* synthetic */ DialogInterface.OnClickListener f$6;
                    private final /* synthetic */ DialogInterface.OnClickListener f$7;
                    private final /* synthetic */ String f$8;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                        this.f$5 = r6;
                        this.f$6 = r7;
                        this.f$7 = r8;
                        this.f$8 = r9;
                    }

                    public final void run() {
                        DialogHelper.a(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
                    }
                });
            } else {
                DialogHelper.a(context, str, str2, z, str3, str4, onClickListener, onClickListener2, str5);
            }
        }
    };
    private IUserPreference d = new IUserPreference() {
        public boolean a(String str, boolean z) {
            return PreferenceUtils.b(MiFiAppDelegate.this.f1395a, AppConstants.m, str, z);
        }

        public void b(String str, boolean z) {
            PreferenceUtils.a(MiFiAppDelegate.this.f1395a, AppConstants.m, str, z);
        }

        public String a(String str) {
            return PreferenceUtils.d(MiFiAppDelegate.this.f1395a, AppConstants.m, str);
        }

        public void a(String str, String str2) {
            PreferenceUtils.a(MiFiAppDelegate.this.f1395a, AppConstants.m, str, str2);
        }

        public void b(String str) {
            PreferenceUtils.b(MiFiAppDelegate.this.f1395a, AppConstants.m, str);
        }
    };

    public static MiFiAppDelegate a() {
        return b;
    }

    public static void a(Context context) {
        a().f1395a = context;
    }

    public void b() {
        MiFiAppController.a().a();
    }

    public void c() {
        MiFiAppController.a().b();
    }

    public void d() {
        MiFiAppController.a().c();
    }

    public void a(Activity activity) {
        DeeplinkUtils.openDeeplink(activity, (String) null, AppConstants.B);
    }

    public IDialogDelegate e() {
        return this.c;
    }

    public IUserPreference f() {
        return this.d;
    }
}
