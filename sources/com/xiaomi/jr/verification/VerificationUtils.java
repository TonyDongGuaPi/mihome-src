package com.xiaomi.jr.verification;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import com.miui.supportlite.app.ProgressDialog;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.dialog.DialogManager;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VerificationUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11043a = "VerificationUtils";
    private static Executor b = Executors.newCachedThreadPool();
    private static ProgressDialog c;

    public static void a(Runnable runnable) {
        b.execute(runnable);
    }

    public static void a(Context context, String str) {
        if (c == null) {
            c = new ProgressDialog();
            c.setCancelable(false);
            c.a((CharSequence) str);
            MifiLog.c("TestDialog", "show " + c.hashCode());
            DialogManager.a((DialogFragment) c, context, "progress dialog");
        }
    }

    public static void a() {
        if (c != null) {
            MifiLog.c("TestDialog", "dismiss " + c.hashCode());
            DialogManager.a((DialogFragment) c);
            c = null;
        }
    }

    public static void a(Context context, String str, boolean z, int i, int i2) {
        String a2 = a(str, z, i, i2);
        MifiLog.b(f11043a, "goto system result: " + a2);
        VerificationUserEnvironment.a().a(context, a2, context.getString(R.string.system_result_title));
    }

    private static String a(String str, boolean z, int i, int i2) {
        return UrlUtils.a(UrlUtils.a(UrlUtils.a(str, "success", String.valueOf(z)), "status", String.valueOf(i)), "code", String.valueOf(i2));
    }
}
