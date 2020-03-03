package com.unionpay.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.mi.UPTsmAddon;
import com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.b;
import com.unionpay.utils.j;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private Context f9545a;
    /* access modifiers changed from: private */
    public UPQuerySEPayInfoCallback b;
    private UPTsmAddon c;
    /* access modifiers changed from: private */
    public String d = "";
    /* access modifiers changed from: private */
    public String e = "";
    private boolean f = false;
    private QueryVendorPayStatusRequestParams g;
    private final Handler.Callback h = new f(this);
    /* access modifiers changed from: private */
    public final Handler i = new Handler(this.h);
    private final UPTsmAddon.UPTsmConnectionListener j = new g(this);

    public e(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback, boolean z) {
        this.f9545a = context;
        this.b = uPQuerySEPayInfoCallback;
        this.f = z;
        if (this.f) {
            System.loadLibrary("entryexpro");
            String a2 = UPUtils.a(this.f9545a, "mode");
            a2 = a2 == null ? "" : a2;
            try {
                Integer.decode(!b.e(a2) ? "02" : a2).intValue();
            } catch (Exception unused) {
            }
        }
    }

    static /* synthetic */ void a(e eVar, int i2, String str) {
        if (i2 == 4000) {
            eVar.a(eVar.d, eVar.e, UPSEInfoResp.ERROR_NOT_SUPPORT, str);
        }
    }

    static /* synthetic */ void a(e eVar, Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        eVar.d = bundle.getString("vendorPayName");
        eVar.e = bundle.getString("vendorPayAliasType");
        int i2 = bundle.getInt("vendorPayStatus");
        String string = bundle.getString("errorDesc");
        int i3 = bundle.getInt("cardNumber", 0);
        if (!TextUtils.isEmpty(eVar.e) && eVar.f9545a != null) {
            UPUtils.a(eVar.f9545a, eVar.e, "se_type");
        }
        switch (i2) {
            case 0:
                if (i3 <= 0) {
                    str = eVar.d;
                    str2 = eVar.e;
                    str3 = UPSEInfoResp.ERROR_NOT_READY;
                    str4 = "card number 0";
                    break;
                } else {
                    String str5 = eVar.d;
                    String str6 = eVar.e;
                    eVar.c();
                    if (eVar.f) {
                        String[] strArr = {"name", "seType", "cardNumbers"};
                        String[] strArr2 = {str5, str6, String.valueOf(i3)};
                    }
                    if (eVar.b != null) {
                        eVar.b.onResult(str5, str6, i3, bundle);
                        return;
                    }
                    return;
                }
            case 1:
                str = eVar.d;
                str2 = eVar.e;
                str3 = UPSEInfoResp.ERROR_NOT_READY;
                str4 = "not ready";
                break;
            case 2:
            case 3:
            case 4:
                eVar.a(eVar.d, eVar.e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
                return;
            default:
                return;
        }
        eVar.a(str, str2, str3, str4);
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        c();
        if (this.f) {
            String[] strArr = {"name", "seType", "errorCode", "errorDesp"};
            String[] strArr2 = {str, str2, str3, str4};
        }
        if (this.b != null) {
            this.b.onError(str, str2, str3, str4);
        }
    }

    private boolean a(String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.f9545a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
        }
        if (packageInfo == null) {
            return false;
        }
        j.a("tsm-client", "tsm version code=" + packageInfo.versionCode);
        return packageInfo.versionCode >= 8;
    }

    private void c() {
        if (this.c != null) {
            this.c.removeConnectionListener(this.j);
            this.c.unbind();
        }
    }

    public final int a() {
        String str;
        String str2;
        String str3;
        String str4;
        if (this.f9545a == null || this.b == null) {
            return UPSEInfoResp.PARAM_ERROR;
        }
        if (a("com.unionpay.tsmservice.mi")) {
            this.c = UPTsmAddon.getInstance(this.f9545a);
            this.c.addConnectionListener(this.j);
            j.b("uppay-spay", "type se  bind service");
            if (this.c == null || this.c.isConnected()) {
                if (this.c != null && this.c.isConnected()) {
                    j.b("uppay", "tsm service already connected");
                    b();
                }
                return UPSEInfoResp.SUCCESS;
            }
            j.b("uppay", "bind service");
            if (!this.c.bind()) {
                str4 = this.d;
                str3 = this.e;
                str2 = UPSEInfoResp.ERROR_NONE;
                str = "Tsm service bind fail";
            }
            return UPSEInfoResp.SUCCESS;
        } else if (!b.d(this.f9545a, "com.unionpay.tsmservice.mi")) {
            str4 = this.d;
            str3 = this.e;
            str2 = UPSEInfoResp.ERROR_TSM_UNINSTALLED;
            str = "Mi Tsm service apk is not installed";
        } else {
            str4 = this.d;
            str3 = this.e;
            str2 = UPSEInfoResp.ERROR_NOT_SUPPORT;
            str = "Mi Tsm service apk version is low";
        }
        a(str4, str3, str2, str);
        return UPSEInfoResp.SUCCESS;
    }

    public final boolean b() {
        try {
            j.b("uppay", "getVendorPayStatus()");
            if (this.g == null) {
                this.g = new QueryVendorPayStatusRequestParams();
            }
            if (this.c.queryVendorPayStatus(this.g, new h(this.i)) != 0) {
                j.b("uppay", "ret != 0");
                a(this.d, this.e, UPSEInfoResp.ERROR_NOT_SUPPORT, "Mi Tsm service apk version is low");
                return false;
            }
            this.i.sendMessageDelayed(Message.obtain(this.i, 4, 4000, 0, ""), 5000);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
