package com.xiaomi.smarthome.homeroom.device_order;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\"\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001j\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "order", "", "apply"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$getHomeOrders$1$syncFromServer$2<T, R> implements Function<T, R> {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceOrderSource$getHomeOrders$1$syncFromServer$2 f18231a = new DeviceOrderSource$getHomeOrders$1$syncFromServer$2();

    DeviceOrderSource$getHomeOrders$1$syncFromServer$2() {
    }

    @NotNull
    /* renamed from: a */
    public final ArrayList<HomeOrder> apply(@NotNull List<HomeOrder> list) {
        Intrinsics.f(list, "order");
        return new ArrayList<>(list);
    }
}
