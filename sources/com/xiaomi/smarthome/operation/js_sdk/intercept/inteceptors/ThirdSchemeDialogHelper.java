package com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors;

import android.content.Context;
import android.content.DialogInterface;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class ThirdSchemeDialogHelper {

    /* renamed from: a  reason: collision with root package name */
    private final Context f21079a;
    private MLAlertDialog b;

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface, int i) {
    }

    public ThirdSchemeDialogHelper(Context context) {
        this.f21079a = context;
    }

    public void a(Runnable runnable) {
        if (this.b != null) {
            this.b.dismiss();
        }
        this.b = new MLAlertDialog.Builder(this.f21079a).b((int) R.string.requset_open_third_app).a(true).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$ThirdSchemeDialogHelper$Vit8mUtKu5Cv6jQl2XsDcILhlM.INSTANCE).a((int) R.string.ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(runnable) {
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.run();
            }
        }).b();
        this.b.show();
    }

    public void a() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
    }

    public boolean b() {
        return this.b != null && this.b.isShowing();
    }
}
