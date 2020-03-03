package com.xiaomi.smarthome.homeroom.device_order;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$runOnOrderThread$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function0 f18236a;

    DeviceOrderSource$runOnOrderThread$1(Function0 function0) {
        this.f18236a = function0;
    }

    public final void run() {
        try {
            this.f18236a.invoke();
        } catch (Exception unused) {
        }
    }
}
