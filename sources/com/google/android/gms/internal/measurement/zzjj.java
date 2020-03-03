package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;

final class zzjj extends zzem {
    private final /* synthetic */ zzjh zzapx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjj(zzjh zzjh, zzhi zzhi) {
        super(zzhi);
        this.zzapx = zzjh;
    }

    @WorkerThread
    public final void run() {
        this.zzapx.zzkk();
    }
}
