package com.huawei.hms.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import com.huawei.hms.a.a;
import com.huawei.hms.support.log.a;
import com.xiaomi.mishopsdk.util.Constants;
import java.lang.reflect.InvocationTargetException;

public class BridgeActivity extends Activity {
    public static final String EXTRA_DELEGATE_CLASS_EX_NAME = "intent.extra.DELEGATE_CLASS_OBJECT_EX";
    public static final String EXTRA_DELEGATE_CLASS_NAME = "intent.extra.DELEGATE_CLASS_OBJECT";
    public static final String EXTRA_RESULT = "intent.extra.RESULT";

    /* renamed from: a  reason: collision with root package name */
    private a f5843a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        if (!b()) {
            setResult(1, (Intent) null);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f5843a != null) {
            this.f5843a.a();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f5843a != null) {
            this.f5843a.b();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.f5843a != null && !this.f5843a.a(i, i2, intent) && !isFinishing()) {
            setResult(i2, intent);
            finish();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f5843a != null) {
            this.f5843a.a(i, keyEvent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void finish() {
        a.b("BridgeActivity", "Enter finish.");
        super.finish();
    }

    private void a() {
        requestWindowFeature(1);
        if (a.C0050a.f5842a >= 9) {
            Window window = getWindow();
            window.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            a(window, true);
        }
    }

    private boolean b() {
        Intent intent = getIntent();
        if (intent == null) {
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Must not pass in a null intent.");
            return false;
        }
        String stringExtra = intent.getStringExtra(EXTRA_DELEGATE_CLASS_NAME);
        if (stringExtra == null) {
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Must not pass in a null or non class object.");
            return false;
        }
        try {
            this.f5843a = (a) Class.forName(stringExtra).asSubclass(a.class).newInstance();
            this.f5843a.a((Activity) this, true);
            return true;
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            com.huawei.hms.support.log.a.d("BridgeActivity", "In initialize, Failed to create 'IUpdateWizard' instance." + e.getMessage());
            return false;
        }
    }

    private static void a(Window window, boolean z) {
        try {
            window.getClass().getMethod("setHwFloating", new Class[]{Boolean.TYPE}).invoke(window, new Object[]{Boolean.valueOf(z)});
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            com.huawei.hms.support.log.a.d("BridgeActivity", "In setHwFloating, Failed to call Window.setHwFloating()." + e.getMessage());
        }
    }
}
