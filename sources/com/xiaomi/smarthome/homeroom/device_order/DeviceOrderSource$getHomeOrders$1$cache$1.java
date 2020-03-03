package com.xiaomi.smarthome.homeroom.device_order;

import com.xiaomi.smarthome.homeroom.device_order.OrderCompat;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001aj\u0012.\u0012,\u0012\u0004\u0012\u00020\u0003 \u0005*\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00040\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004 \u0005*4\u0012.\u0012,\u0012\u0004\u0012\u00020\u0003 \u0005*\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00040\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "call"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$getHomeOrders$1$cache$1<V> implements Callable<ObservableSource<? extends T>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DeviceOrderSource$getHomeOrders$1 f18229a;

    DeviceOrderSource$getHomeOrders$1$cache$1(DeviceOrderSource$getHomeOrders$1 deviceOrderSource$getHomeOrders$1) {
        this.f18229a = deviceOrderSource$getHomeOrders$1;
    }

    /* renamed from: a */
    public final Observable<ArrayList<HomeOrder>> call() {
        if (!this.f18229a.f18228a || !(!DeviceOrderSource.b.b().isEmpty())) {
            return Observable.empty();
        }
        ArrayList arrayList = new ArrayList();
        for (OrderCompat.HomeParam b : this.f18229a.b) {
            Iterable<String> b2 = b.b();
            Collection arrayList2 = new ArrayList(CollectionsKt.a(b2, 10));
            for (String str : b2) {
                HomeOrder homeOrder = (HomeOrder) DeviceOrderSource.b.c().get(str);
                if (homeOrder == null) {
                    homeOrder = new HomeOrder(str, true, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 60, (DefaultConstructorMarker) null);
                }
                arrayList2.add(homeOrder);
            }
            arrayList.addAll((List) arrayList2);
        }
        return Observable.just(arrayList);
    }
}
