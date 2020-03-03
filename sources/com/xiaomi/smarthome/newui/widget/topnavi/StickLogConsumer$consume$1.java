package com.xiaomi.smarthome.newui.widget.topnavi;

import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
final class StickLogConsumer$consume$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ArrayList f20917a;

    StickLogConsumer$consume$1(ArrayList arrayList) {
        this.f20917a = arrayList;
    }

    public final void run() {
        for (StickEvent a2 : this.f20917a) {
            try {
                a2.a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
