package com.xiaomi.smarthome.newui.widget.topnavi;

import com.xiaomi.smarthome.application.SHApplication;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/xiaomi/smarthome/newui/widget/topnavi/StickLogConsumer;", "", "()V", "isDarkMode", "", "()Z", "setDarkMode", "(Z)V", "value", "isDataReady", "setDataReady", "mStickEventsQueue", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/newui/widget/topnavi/StickEvent;", "Lkotlin/collections/ArrayList;", "clearAllEvents", "", "consume", "postStickEvent", "stickEvent", "setIsDarkMode", "isDark", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class StickLogConsumer {

    /* renamed from: a  reason: collision with root package name */
    public static final StickLogConsumer f20916a = new StickLogConsumer();
    private static boolean b;
    private static final ArrayList<StickEvent> c = new ArrayList<>();
    private static boolean d;

    private StickLogConsumer() {
    }

    public final void a(boolean z) {
        b = z;
    }

    public final boolean a() {
        return b;
    }

    public final void b(boolean z) {
        b = z;
    }

    public final boolean b() {
        return d;
    }

    public final void c(boolean z) {
        d = z;
        if (z) {
            c();
        } else {
            d();
        }
    }

    public final void a(@NotNull StickEvent stickEvent) {
        Intrinsics.f(stickEvent, "stickEvent");
        c.add(stickEvent);
        if (d) {
            c();
        }
    }

    private final void c() {
        ArrayList arrayList = new ArrayList(c);
        c.clear();
        SHApplication.getGlobalWorkerHandler().post(new StickLogConsumer$consume$1(arrayList));
    }

    private final void d() {
        c.clear();
    }
}
