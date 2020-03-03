package com.xiaomi.smarthome.homeroom.device_order;

import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/lang/Thread;", "it", "Ljava/lang/Runnable;", "kotlin.jvm.PlatformType", "newThread"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$mOrderExecutor$1 implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceOrderSource$mOrderExecutor$1 f18234a = new DeviceOrderSource$mOrderExecutor$1();

    DeviceOrderSource$mOrderExecutor$1() {
    }

    @NotNull
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("DeviceOrderDebug");
        DeviceOrderSource.h = thread.getId();
        return thread;
    }
}
