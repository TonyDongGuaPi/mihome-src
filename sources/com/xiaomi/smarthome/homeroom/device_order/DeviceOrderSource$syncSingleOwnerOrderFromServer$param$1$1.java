package com.xiaomi.smarthome.homeroom.device_order;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Long;"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$syncSingleOwnerOrderFromServer$param$1$1 extends Lambda implements Function1<String, Long> {
    public static final DeviceOrderSource$syncSingleOwnerOrderFromServer$param$1$1 INSTANCE = new DeviceOrderSource$syncSingleOwnerOrderFromServer$param$1$1();

    DeviceOrderSource$syncSingleOwnerOrderFromServer$param$1$1() {
        super(1);
    }

    @Nullable
    public final Long invoke(@NotNull String str) {
        Intrinsics.f(str, "it");
        return StringsKt.i(str);
    }
}
