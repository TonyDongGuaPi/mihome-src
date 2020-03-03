package com.xiaomi.smarthome.mibrain.activity;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
final class MiBrainCardStyleStatelessActivity$animHide$maskAnim$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ MiBrainCardStyleStatelessActivity f10616a;

    MiBrainCardStyleStatelessActivity$animHide$maskAnim$1(MiBrainCardStyleStatelessActivity miBrainCardStyleStatelessActivity) {
        this.f10616a = miBrainCardStyleStatelessActivity;
    }

    public final void run() {
        this.f10616a.finish();
        this.f10616a.overridePendingTransition(0, 0);
    }
}
