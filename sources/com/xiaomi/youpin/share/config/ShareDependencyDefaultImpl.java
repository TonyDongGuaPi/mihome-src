package com.xiaomi.youpin.share.config;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

public abstract class ShareDependencyDefaultImpl implements ShareDependency {
    public void a(Context context) {
    }

    public void a(String str, String str2, String str3) {
    }

    public void a(String str, String str2, String str3, int i) {
    }

    public void b() {
    }

    public void c() {
    }

    @NonNull
    public Dialog a(Context context, String str) {
        return ProgressDialog.show(context, str, "");
    }
}
