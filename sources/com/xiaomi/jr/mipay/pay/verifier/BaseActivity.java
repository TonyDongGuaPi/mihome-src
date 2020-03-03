package com.xiaomi.jr.mipay.pay.verifier;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.miui.supportlite.app.AlertDialog;
import com.xiaomi.jr.mipay.common.SessionManager;
import com.xiaomi.jr.mipay.common.util.PermissionHelper;
import com.xiaomi.jr.permission.Request;

public class BaseActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PermissionHelper.a(this, (Request.Callback) null);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!SessionManager.b()) {
            return;
        }
        if (SessionManager.c() == SessionManager.FinishCode.b) {
            new AlertDialog.Builder(this).b(R.string.jr_mipay_error_account_changed).a(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BaseActivity.this.a(dialogInterface, i);
                }
            }).a(false).a().show(getSupportFragmentManager(), "account_changed");
        } else {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        finish();
    }
}
