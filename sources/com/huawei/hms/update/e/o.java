package com.huawei.hms.update.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import com.huawei.android.hms.base.R;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.miui.tsmclient.util.Constants;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class o extends a implements com.huawei.hms.activity.a {

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<Activity> f5930a;
    private BroadcastReceiver b;
    private Handler c = new Handler();
    private b d;
    private boolean e = false;
    private com.huawei.hms.activity.a f;
    private Handler g = new p(this);

    public void a(Activity activity, boolean z) {
        this.f5930a = new WeakReference<>(activity);
        a(activity);
    }

    private void a(Activity activity) {
        Intent intent = new Intent("com.huawei.appmarket.intent.action.ThirdUpdateAction");
        intent.setPackage("com.huawei.appmarket");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.KEY_PACKAGE_NAME, HuaweiApiAvailability.SERVICES_PACKAGE);
            jSONObject.put("versioncode", 20502300);
            jSONArray.put(jSONObject);
            intent.putExtra("params", jSONArray.toString());
            intent.putExtra("isHmsOrApkUpgrade", true);
            intent.putExtra("buttonDlgY", activity.getString(R.string.hms_install));
            intent.putExtra("buttonDlgN", activity.getString(R.string.hms_cancel));
            intent.putExtra("upgradeDlgContent", activity.getString(R.string.hms_update_message_new, new Object[]{"%P"}));
            try {
                activity.startActivityForResult(intent, 2000);
            } catch (ActivityNotFoundException unused) {
                com.huawei.hms.support.log.a.d("SilentUpdateWizard", "ActivityNotFoundException");
            }
        } catch (JSONException e2) {
            com.huawei.hms.support.log.a.d("SilentUpdateWizard", "create hmsJsonObject fail" + e2.getMessage());
        }
    }

    public void a() {
        this.f5930a = null;
        this.c.removeCallbacksAndMessages((Object) null);
        e();
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        if (!this.e || this.f == null) {
            com.huawei.hms.update.c.a.a((Class<?>) null);
        } else {
            this.f.a();
        }
    }

    public boolean a(int i, int i2, Intent intent) {
        if (this.e && this.f != null) {
            return this.f.a(i, i2, intent);
        }
        if (i != 2000) {
            return false;
        }
        if (i2 == 0) {
            d();
            a(20000);
            return true;
        } else if (i2 == 4) {
            c(13);
            return true;
        } else {
            a(i2, 0);
            a(true);
            return true;
        }
    }

    public void b() {
        if (this.e && this.f != null) {
            this.f.b();
        } else if (this.d != null) {
            Class<?> cls = this.d.getClass();
            this.d.c();
            this.d = null;
            a((Class<? extends b>) cls);
        }
    }

    private void a(boolean z) {
        Activity c2 = c();
        if (c2 != null) {
            if (this.f == null) {
                b(c2);
            }
            if (this.f != null) {
                this.e = true;
                com.huawei.hms.update.c.a.a(this.f.getClass());
                this.f.a(c2, z);
            }
        }
    }

    private void b(Activity activity) {
        String stringExtra;
        Intent intent = activity.getIntent();
        if (intent != null && (stringExtra = intent.getStringExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_EX_NAME)) != null) {
            try {
                this.f = (com.huawei.hms.activity.a) Class.forName(stringExtra).asSubclass(com.huawei.hms.activity.a.class).newInstance();
            } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
                com.huawei.hms.support.log.a.d("SilentUpdateWizard", "getBridgeActivityDelegate error" + e2.getMessage());
            }
        }
    }

    private void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.appmarket.service.downloadservice.Receiver");
        intentFilter.addAction("com.huawei.appmarket.service.downloadservice.progress.Receiver");
        intentFilter.addAction("com.huawei.appmarket.service.installerservice.Receiver");
        this.b = new com.huawei.hms.update.d.a(this.g);
        Activity c2 = c();
        if (c2 != null) {
            c2.registerReceiver(this.b, intentFilter);
        }
    }

    private void e() {
        Activity c2 = c();
        if (c2 != null && this.b != null) {
            c2.unregisterReceiver(this.b);
            this.b = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        com.huawei.hms.support.log.a.a("SilentUpdateWizard", "on SilentUpdate cancelled");
    }

    /* access modifiers changed from: package-private */
    public void b(b bVar) {
        com.huawei.hms.support.log.a.a("SilentUpdateWizard", "on SilentUpdate dowork");
    }

    /* access modifiers changed from: package-private */
    public Activity c() {
        if (this.f5930a == null) {
            return null;
        }
        return (Activity) this.f5930a.get();
    }

    private void a(int i) {
        this.c.removeCallbacksAndMessages((Object) null);
        this.c.postDelayed(new a(this, (p) null), (long) i);
    }

    private class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(o oVar, p pVar) {
            this();
        }

        public void run() {
            o.this.b(14);
        }
    }

    /* access modifiers changed from: private */
    public void b(int i) {
        this.c.removeCallbacksAndMessages((Object) null);
        e();
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        a(i, 0);
        a(false);
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        String string = bundle.containsKey("UpgradePkgName") ? bundle.getString("UpgradePkgName") : null;
        if (string != null && HuaweiApiAvailability.SERVICES_PACKAGE.equals(string) && bundle.containsKey("downloadtask.status")) {
            int i = bundle.getInt("downloadtask.status");
            if (i == 3 || i == 5 || i == 6 || i == 8) {
                b(i);
            } else if (i == 4) {
                a(60000);
            } else {
                a(20000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(Bundle bundle) {
        String string = bundle.containsKey("UpgradePkgName") ? bundle.getString("UpgradePkgName") : null;
        if (string != null && HuaweiApiAvailability.SERVICES_PACKAGE.equals(string) && bundle.containsKey("UpgradeDownloadProgress") && bundle.containsKey("UpgradeAppName")) {
            int i = bundle.getInt("UpgradeDownloadProgress");
            a(20000);
            if (i >= 99) {
                i = 99;
            }
            if (this.d == null) {
                a((Class<? extends b>) l.class);
            }
            if (this.d != null) {
                ((l) this.d).a(i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(Bundle bundle) {
        if (bundle.containsKey("packagename") && bundle.containsKey("status")) {
            String string = bundle.getString("packagename");
            int i = bundle.getInt("status");
            if (string != null && HuaweiApiAvailability.SERVICES_PACKAGE.equals(string)) {
                if (i == 2) {
                    this.c.removeCallbacksAndMessages((Object) null);
                    if (this.d != null) {
                        ((l) this.d).a(100);
                    }
                    c(0);
                } else if (i == -1 || i == -2) {
                    b(i);
                } else {
                    a(60000);
                }
            }
        }
    }

    private void a(Class<? extends b> cls) {
        try {
            b bVar = (b) cls.newInstance();
            bVar.a((a) this);
            this.d = bVar;
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e2) {
            com.huawei.hms.support.log.a.d("SilentUpdateWizard", "In showDialog, Failed to show the dialog." + e2.getMessage());
        }
    }

    private void c(int i) {
        Activity c2 = c();
        if (c2 != null && !c2.isFinishing()) {
            a(i, 0);
            Intent intent = new Intent();
            intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, getClass().getName());
            intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
            c2.setResult(-1, intent);
            c2.finish();
        }
    }

    public void a(int i, KeyEvent keyEvent) {
        if (this.e && this.f != null) {
            this.f.a(i, keyEvent);
        }
    }
}
