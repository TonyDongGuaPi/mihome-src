package com.xiaomi.jr.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.xiaomi.jr.common.lifecycle.LifecycledObjects;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.dialog.DialogManager;
import java.lang.ref.WeakReference;

public class DialogActivity extends FragmentActivity {
    public static String KEY_DIALOG_INFO_ID = "dialog_info_id";

    /* renamed from: a  reason: collision with root package name */
    private static final String f10377a = "DialogActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DialogManager.DialogInfo dialogInfo = (DialogManager.DialogInfo) LifecycledObjects.b(Integer.valueOf(getIntent().getIntExtra(KEY_DIALOG_INFO_ID, 0)));
        if (dialogInfo != null) {
            dialogInfo.f10379a = new WeakReference<>(this);
            if (dialogInfo.b != null) {
                DialogManager.a(dialogInfo);
            } else {
                finish();
            }
        } else {
            MifiLog.d(f10377a, "Dialog info has lost due to caller activity being destroyed");
            finish();
        }
    }
}
