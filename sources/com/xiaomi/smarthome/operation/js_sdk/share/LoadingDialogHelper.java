package com.xiaomi.smarthome.operation.js_sdk.share;

import android.content.Context;
import android.content.DialogInterface;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;

public class LoadingDialogHelper {

    /* renamed from: a  reason: collision with root package name */
    private final Context f21106a;
    private XQProgressDialog b;
    private boolean c = false;

    public LoadingDialogHelper(Context context) {
        this.f21106a = context;
    }

    public void a() {
        this.c = false;
        if (this.b == null) {
            this.b = new XQProgressDialog(this.f21106a);
            this.b.setMessage(this.f21106a.getString(R.string.loading));
            this.b.setCancelable(true);
            this.b.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public final void onCancel(DialogInterface dialogInterface) {
                    LoadingDialogHelper.this.a(dialogInterface);
                }
            });
        }
        this.b.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface) {
        this.c = true;
    }

    public boolean b() {
        return this.b != null && this.b.isShowing();
    }

    public void c() {
        if (this.b != null) {
            this.b.dismiss();
        }
        this.c = false;
    }

    public boolean d() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
