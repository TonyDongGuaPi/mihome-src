package com.xiaomi.smarthome.homeroom.device_order;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "it", "apply"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$getHomeOrders$2<T, R> implements Function<T, R> {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceOrderSource$getHomeOrders$2 f18232a = new DeviceOrderSource$getHomeOrders$2();

    DeviceOrderSource$getHomeOrders$2() {
    }

    @NotNull
    /* renamed from: a */
    public final List<HomeOrder> apply(@NotNull List<HomeOrder> list) {
        Intrinsics.f(list, "it");
        Iterable<HomeOrder> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.a(iterable, 10));
        for (HomeOrder a2 : iterable) {
            arrayList.add(HomeOrder.a(a2, (String) null, false, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 63, (Object) null));
        }
        return (List) arrayList;
    }
}
