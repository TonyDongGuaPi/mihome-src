package com.xiaomi.payment.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.account.AccountUtils;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.PermissionUtils;
import com.mibi.common.decorator.LoginHelper;
import com.xiaomi.payment.data.MibiConstants;

public class BaseEntryHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12441a = "BaseEntryHandler";
    private static final String b = "is_request_login";
    private static final int c = 1000000;
    private static final int d = 1000001;
    /* access modifiers changed from: private */
    public EntryResultCallback e;
    private Activity f;
    /* access modifiers changed from: private */
    public boolean g = false;
    private boolean h = false;

    public BaseEntryHandler(EntryResultCallback entryResultCallback, Activity activity) {
        this.e = entryResultCallback;
        this.f = activity;
    }

    public void a(Bundle bundle, boolean z) {
        this.h = z;
        if (bundle != null) {
            this.g = bundle.getBoolean(b, false);
            if (this.g) {
                AccountLoader a2 = AccountUtils.a(this.f, "system");
                if (a2 != null) {
                    Log.d(f12441a, "login success");
                    this.e.onEntrySuccess(a2);
                    this.g = false;
                    return;
                }
                this.e.onEntryFailed(4, "login failed");
                return;
            }
            return;
        }
        AccountUtils.d(this.f);
        if (MibiLicenseActivity.needShowLicense(this.f.getApplicationContext())) {
            this.f.startActivityForResult(new Intent(this.f, MibiLicenseActivity.class), 1000000);
            return;
        }
        a(PermissionUtils.f7535a);
    }

    public void a(Bundle bundle) {
        bundle.putBoolean(b, this.g);
    }

    /* access modifiers changed from: protected */
    public EntryResultCallback a() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void a(String... strArr) {
        String[] b2 = PermissionUtils.b(this.f, strArr);
        if (b2 != null) {
            ActivityCompat.requestPermissions(this.f, b2, 1000001);
        } else {
            b();
        }
    }

    public void a(int i, String[] strArr, int[] iArr) {
        if (CommonConstants.b) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                Log.d(f12441a, "permission result: " + strArr[i2] + " " + iArr[i2]);
            }
        }
        if (PermissionUtils.a(iArr)) {
            b();
        } else {
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        Log.d(f12441a, "user granted permissions");
        MistatisticUtils.a(this.f.getApplicationContext(), MibiConstants.cC, MibiConstants.cD);
        this.g = true;
        LoginHelper.a(this.f, this.h, new LoginHelper.LoginWithResult() {
            public void a(AccountLoader accountLoader) {
                Log.d(BaseEntryHandler.f12441a, "login success");
                boolean unused = BaseEntryHandler.this.g = false;
                BaseEntryHandler.this.e.onEntrySuccess(accountLoader);
            }

            public void a(int i, String str) {
                Log.d(BaseEntryHandler.f12441a, "login failed error:" + i + "message:" + str);
                BaseEntryHandler.this.e.onEntryFailed(i, str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void c() {
        Log.d(f12441a, "user not granted permissions");
        this.e.onEntryFailed(0, "user not granted permissions");
    }

    public void a(int i, int i2, Intent intent) {
        if (i != 1000000) {
            return;
        }
        if (i2 == -1) {
            a(PermissionUtils.f7535a);
        } else {
            this.e.onEntryFailed(2, "user canceled");
        }
    }
}
