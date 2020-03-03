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
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.j;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private Context f9542a;
    /* access modifiers changed from: private */
    public UPQuerySEPayInfoCallback b;
    private UPTsmAddon c;
    /* access modifiers changed from: private */
    public String d = "";
    /* access modifiers changed from: private */
    public String e = "";
    private boolean f = false;
    private QueryVendorPayStatusRequestParams g;
    private final Handler.Callback h = new c(this);
    /* access modifiers changed from: private */
    public final Handler i = new Handler(this.h);
    private final UPTsmAddon.UPTsmConnectionListener j = new d(this);

    public b(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback, boolean z) {
        this.f9542a = context;
        this.b = uPQuerySEPayInfoCallback;
        this.f = z;
        if (this.f) {
            System.loadLibrary("entryexpro");
            String a2 = UPUtils.a(this.f9542a, "mode");
            a2 = a2 == null ? "" : a2;
            try {
                Integer.decode(!com.unionpay.utils.b.e(a2) ? "02" : a2).intValue();
            } catch (Exception unused) {
            }
        }
    }

    static /* synthetic */ void a(b bVar, int i2, String str) {
        if (i2 == 4000) {
            bVar.a(bVar.d, bVar.e, UPSEInfoResp.ERROR_NOT_SUPPORT, str);
        }
    }

    static /* synthetic */ void a(b bVar, Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        bVar.d = bundle.getString("vendorPayName");
        bVar.e = bundle.getString("vendorPayAliasType");
        int i2 = bundle.getInt("vendorPayStatus");
        String string = bundle.getString("errorDesc");
        int i3 = bundle.getInt("cardNumber", 0);
        if (!TextUtils.isEmpty(bVar.e) && bVar.f9542a != null) {
            UPUtils.a(bVar.f9542a, bVar.e, "se_type");
        }
        switch (i2) {
            case 0:
                if (i3 <= 0) {
                    str = bVar.d;
                    str2 = bVar.e;
                    str3 = UPSEInfoResp.ERROR_NOT_READY;
                    str4 = "card number 0";
                    break;
                } else {
                    String str5 = bVar.d;
                    String str6 = bVar.e;
                    bVar.c();
                    if (bVar.f) {
                        String[] strArr = {"name", "seType", "cardNumbers"};
                        String[] strArr2 = {str5, str6, String.valueOf(i3)};
                    }
                    if (bVar.b != null) {
                        bVar.b.onResult(str5, str6, i3, bundle);
                        return;
                    }
                    return;
                }
            case 1:
                str = bVar.d;
                str2 = bVar.e;
                str3 = UPSEInfoResp.ERROR_NOT_READY;
                str4 = "not ready";
                break;
            case 2:
            case 3:
            case 4:
                bVar.a(bVar.d, bVar.e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
                return;
            default:
                return;
        }
        bVar.a(str, str2, str3, str4);
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
            packageInfo = this.f9542a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
        }
        if (packageInfo == null) {
            return false;
        }
        j.a("tsm-client", "tsm version code=" + packageInfo.versionCode);
        return packageInfo.versionCode >= 18;
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
        if (this.f9542a == null || this.b == null) {
            return UPSEInfoResp.PARAM_ERROR;
        }
        if (a("com.unionpay.tsmservice")) {
            this.c = UPTsmAddon.getInstance(this.f9542a);
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
        } else if (!com.unionpay.utils.b.d(this.f9542a, "com.unionpay.tsmservice")) {
            str4 = this.d;
            str3 = this.e;
            str2 = UPSEInfoResp.ERROR_TSM_UNINSTALLED;
            str = "Tsm service apk is not installed";
        } else {
            str4 = this.d;
            str3 = this.e;
            str2 = UPSEInfoResp.ERROR_NOT_SUPPORT;
            str = "Tsm service apk version is low";
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
            if (this.c.queryVendorPayStatus(this.g, new a(this.i)) != 0) {
                j.b("uppay", "ret != 0");
                a(this.d, this.e, UPSEInfoResp.ERROR_NOT_SUPPORT, "Tsm service apk version is low");
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
