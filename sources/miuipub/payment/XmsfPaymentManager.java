package miuipub.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class XmsfPaymentManager implements IXmsfPayment {
    private static final String D = "com.xiaomi.xmsf.permission.PAYMENT";
    private static final int E = 1;
    private static final int F = 2;
    private static final int G = 6;

    /* renamed from: a  reason: collision with root package name */
    public static final String f3004a = "com.xiaomi.payment";
    public static final String b = "payment_payment_result";
    public static final String c = "com.xiaomi.xmsf.action.PAYMENT";
    public static final String d = "miuipub.intent.action.PAYMENT";
    public static final String e = "payment_trade_balance";
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 5;
    public static final int k = 6;
    public static final int l = 7;
    public static final int m = 8;
    public static final int n = 9;
    public static final int o = 10;
    public static final int p = 11;
    public static final int q = 12;
    public static final int r = 13;
    public static final int s = 14;
    public static final int t = 15;
    public static final int u = 16;
    private static volatile XmsfPaymentManager v;
    private boolean A;
    private boolean B;
    private boolean C;
    private final Context w;
    private IXmsfPayment x;
    private XmsfLocalPaymentAdapter y;
    private XmsfSystemPaymentAdapter z;

    private XmsfPaymentManager(Context context) {
        this.w = context.getApplicationContext();
        this.C = b(context);
        this.A = c(context);
        a(1);
    }

    public boolean a() {
        return this.A;
    }

    public boolean b() {
        return this.B;
    }

    public void c() {
        a(1);
    }

    public void d() {
        a(2);
    }

    private void a(int i2) {
        boolean z2;
        switch (i2) {
            case 1:
                z2 = this.A;
                break;
            case 2:
                z2 = false;
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (z2) {
            if (this.z == null) {
                this.z = new XmsfSystemPaymentAdapter(this.w);
            }
            this.x = this.z;
            this.B = true;
            return;
        }
        if (this.y == null) {
            this.y = new XmsfLocalPaymentAdapter(this.w);
        }
        this.x = this.y;
        this.B = false;
    }

    private int e() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return Integer.parseInt((String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.miui.ui.version.code", "0"}));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private boolean b(Context context) {
        return e() >= 6;
    }

    private boolean c(Context context) {
        if (!this.C) {
            return false;
        }
        try {
            if (!((context.getPackageManager().getPackageInfo("com.xiaomi.payment", 0).applicationInfo.flags & 1) != 0)) {
                return false;
            }
            if (context.getPackageManager().queryIntentServices(new Intent(c), 0).isEmpty()) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static XmsfPaymentManager a(Context context) {
        if (context != null) {
            if (v == null) {
                synchronized (XmsfPaymentManager.class) {
                    if (v == null) {
                        v = new XmsfPaymentManager(context);
                    }
                }
            }
            return v;
        }
        throw new IllegalArgumentException("context is null");
    }

    public void a(Activity activity, String str, String str2, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener) {
        this.x.a(activity, str, str2, bundle, iXmsfPaymentListener);
    }

    public void a(Activity activity, String str, String str2, String str3, boolean z2, boolean z3, boolean z4, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener) {
        this.x.a(activity, str, str2, str3, z2, z3, z4, bundle, iXmsfPaymentListener);
    }

    public void a(Activity activity, String str, String str2, String str3) {
        this.x.a(activity, str, str2, str3);
    }

    public void a(Activity activity) {
        this.x.a(activity);
    }

    public void a(Activity activity, String str, String str2, String str3, IXmsfPaymentListener iXmsfPaymentListener) {
        this.x.a(activity, str, str2, str3, iXmsfPaymentListener);
    }

    public void a(Activity activity, String str, String str2, Bundle bundle) {
        this.x.a(activity, str, str2, bundle);
    }

    public void b(Activity activity, String str, String str2, Bundle bundle) {
        this.x.b(activity, str, str2, bundle);
    }
}
