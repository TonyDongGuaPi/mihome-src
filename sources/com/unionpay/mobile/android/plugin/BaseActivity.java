package com.unionpay.mobile.android.plugin;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.m;
import java.util.ArrayList;

public abstract class BaseActivity extends Activity implements a, b {
    public static IntentFilter[] FILTERS;
    public static String[][] TECHLISTS;
    private static int f;

    /* renamed from: a  reason: collision with root package name */
    private l f9665a = null;
    protected ArrayList<b> b = null;
    protected NfcAdapter c;
    private a d = null;
    private m e = null;
    private PendingIntent g;

    private class a {

        /* renamed from: a  reason: collision with root package name */
        public com.unionpay.mobile.android.model.b f9666a;
        public UPPayEngine b;

        public a(UPPayEngine uPPayEngine) {
            this.f9666a = null;
            this.b = null;
            this.f9666a = new com.unionpay.mobile.android.model.b();
            this.b = uPPayEngine;
            this.b.a(this.f9666a);
        }
    }

    static {
        try {
            TECHLISTS = new String[][]{new String[]{IsoDep.class.getName()}, new String[]{NfcV.class.getName()}, new String[]{NfcF.class.getName()}};
            FILTERS = new IntentFilter[]{new IntentFilter("android.nfc.action.TECH_DISCOVERED", "*/*")};
        } catch (Exception unused) {
        }
    }

    public Object a(String str) {
        if (str == null) {
            return this.d.f9666a;
        }
        if (str.equalsIgnoreCase(UPPayEngine.class.toString())) {
            return this.d.b;
        }
        if (str.equalsIgnoreCase(m.class.toString())) {
            return this.e;
        }
        return null;
    }

    public final void a(int i) {
        if (this.b != null) {
            int size = this.b.size() - 1;
            this.b.get(size);
            while (size >= 0) {
                b bVar = this.b.get(size);
                if (bVar.g() == i) {
                    setContentView(bVar);
                    return;
                } else {
                    this.b.remove(size);
                    size--;
                }
            }
        }
    }

    public final void a(b bVar) {
        if (this.b != null) {
            int size = this.b.size();
            if (size > 0) {
                this.b.get(size - 1);
            }
            this.b.add(bVar);
            setContentView(bVar);
        }
    }

    public boolean a() {
        return false;
    }

    public final void b() {
        int size;
        if (this.b != null && (size = this.b.size()) > 0) {
            int i = size - 1;
            this.b.get(i);
            this.b.get(i);
            this.b.remove(i);
            if (this.b.size() != 0) {
                this.b.get(this.b.size() - 1);
                setContentView(this.b.get(this.b.size() - 1));
            }
        }
    }

    public final String c() {
        return this.d.f9666a.f9576a;
    }

    public Resources getResources() {
        try {
            Resources resources = super.getResources();
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            return resources;
        } catch (Exception unused) {
            return super.getResources();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.f9665a != null) {
            this.f9665a.u();
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        k.a("uppay", "PayActivityEx.onCreate() +++");
        c.a();
        com.unionpay.mobile.android.global.a.a(this);
        this.b = new ArrayList<>(1);
        this.d = new a(d());
        this.e = new m(this);
        requestWindowFeature(1);
        super.onCreate(bundle);
        this.f9665a = (l) a(1, (e) null);
        setContentView(this.f9665a);
        getWindow().addFlags(8192);
        f++;
        k.a("uppay", "PayActivityEx.onCreate() ---");
        if (a()) {
            this.c = NfcAdapter.getDefaultAdapter(this);
            this.g = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            onNewIntent(getIntent());
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.b != null) {
            this.b.clear();
        }
        if (this.f9665a != null) {
            this.f9665a.x();
        }
        this.f9665a = null;
        com.unionpay.mobile.android.model.b.bl = false;
        com.unionpay.mobile.android.model.b.bb = null;
        com.unionpay.mobile.android.model.b.bm = false;
        int i = f - 1;
        f = i;
        if (i == 0) {
            com.unionpay.mobile.android.resource.c.a(this).a();
        }
        this.e.c();
        this.e = null;
        this.d.b = null;
        this.d.f9666a = null;
        this.d = null;
        ((ViewGroup) getWindow().getDecorView().findViewById(16908290)).removeAllViews();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.b != null && this.b.size() > 0) {
            this.b.get(this.b.size() - 1).k();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (a() && this.c != null) {
            this.c.disableForegroundDispatch(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.e.a()) {
            this.e.b();
        }
        if (a() && this.c != null) {
            this.c.enableForegroundDispatch(this, this.g, FILTERS, TECHLISTS);
        }
    }
}
