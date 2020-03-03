package com.xiaomi.push;

import android.content.Context;
import java.io.File;

final class w extends v {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Runnable f12949a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    w(Context context, File file, Runnable runnable) {
        super(context, file, (w) null);
        this.f12949a = runnable;
    }

    /* access modifiers changed from: protected */
    public void a(Context context) {
        if (this.f12949a != null) {
            this.f12949a.run();
        }
    }
}
