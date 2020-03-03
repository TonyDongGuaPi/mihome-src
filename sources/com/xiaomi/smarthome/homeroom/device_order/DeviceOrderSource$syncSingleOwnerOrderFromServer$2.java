package com.xiaomi.smarthome.homeroom.device_order;

import com.xiaomi.smarthome.homeroom.device_order.OrderCompat;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/ArrayList;", "it", "", "apply"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$syncSingleOwnerOrderFromServer$2<T, R> implements Function<Throwable, ArrayList<HomeOrder>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ OrderCompat.HomeParam f18241a;

    DeviceOrderSource$syncSingleOwnerOrderFromServer$2(OrderCompat.HomeParam homeParam) {
        this.f18241a = homeParam;
    }

    @NotNull
    /* renamed from: a */
    public final ArrayList<HomeOrder> apply(@NotNull Throwable th) {
        Intrinsics.f(th, "it");
        th.printStackTrace();
        Collection arrayList = new ArrayList();
        for (String str : this.f18241a.b()) {
            HomeOrder homeOrder = (HomeOrder) DeviceOrderSource.b.c().get(str);
            if (homeOrder == null) {
                homeOrder = new HomeOrder(str, true, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 60, (DefaultConstructorMarker) null);
            }
            arrayList.add(homeOrder);
        }
        return (ArrayList) arrayList;
    }
}
