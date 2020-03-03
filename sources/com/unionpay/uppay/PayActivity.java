package com.unionpay.uppay;

import android.content.Intent;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.unionpay.mobile.android.hce.f;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.pboctransaction.nfc.b;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.pro.pboc.engine.b;
import com.unionpay.mobile.android.pro.views.a;
import com.unionpay.mobile.android.pro.views.j;
import com.unionpay.mobile.android.pro.views.k;
import com.unionpay.mobile.android.pro.views.v;
import com.unionpay.mobile.android.pro.views.x;
import com.unionpay.mobile.android.utils.n;

public final class PayActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    public static String f9843a = "";
    private b d = null;
    private f e = null;
    private n f;
    private k g = null;

    static {
        System.loadLibrary("entryexpro");
    }

    public final com.unionpay.mobile.android.nocard.views.b a(int i, e eVar) {
        switch (i) {
            case 1:
                j jVar = new j(this);
                jVar.a(a());
                return jVar;
            case 2:
                return new v(this, eVar);
            case 6:
                return new x(this, eVar);
            case 17:
                k kVar = new k(this, eVar, (UPPayEngine) a(UPPayEngine.class.toString()));
                this.g = kVar;
                this.g.r = c();
                this.g.a(this.c);
                return kVar;
            case 18:
                return new a(this, eVar, (UPPayEngine) a(UPPayEngine.class.toString()));
            default:
                return null;
        }
    }

    public final Object a(String str) {
        if (b.class.toString().equalsIgnoreCase(str)) {
            if (this.d == null) {
                this.d = new b(this, c());
            }
            return this.d;
        } else if (!f.class.toString().equalsIgnoreCase(str)) {
            return super.a(str);
        } else {
            if (this.e == null) {
                this.e = new f(this);
            }
            return this.e;
        }
    }

    public final boolean a() {
        if (Build.VERSION.SDK_INT < 10) {
            return false;
        }
        return (getPackageManager().checkPermission("android.permission.NFC", com.unionpay.mobile.android.utils.f.b(this)) == 0) && ((NfcManager) getSystemService(AnalyticManager.CATEGORY_NFC)).getDefaultAdapter() != null;
    }

    public final UPPayEngine d() {
        this.f = new n(this);
        return this.f;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.g != null && this.g.getParent() != null) {
            this.g.a(this.c);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        super.onDestroy();
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        this.f.h();
        this.f = null;
        this.g = null;
        f9843a = "";
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Parcelable parcelableExtra = intent.getParcelableExtra("android.nfc.extra.TAG");
        if (parcelableExtra != null) {
            Log.d("NFCTAG", intent.getAction());
            b.C0075b bVar = new b.C0075b(IsoDep.get((Tag) parcelableExtra));
            bVar.a();
            com.unionpay.mobile.android.pboctransaction.nfc.a aVar = new com.unionpay.mobile.android.pboctransaction.nfc.a((com.unionpay.mobile.android.fully.a) a(UPPayEngine.class.toString()), bVar);
            if (this.g != null && this.g.getParent() != null) {
                this.g.a(aVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onResume() {
        super.onResume();
        if (this.g != null && this.g.getParent() != null) {
            this.g.a(this.c);
        }
    }
}
