package com.xiaomi.smarthome.homeroom.device_order;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001aB\u0012\u001a\b\u0001\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004* \u0012\u001a\b\u0001\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "kotlin.jvm.PlatformType", "it", "", "apply", "(Ljava/lang/Integer;)Lio/reactivex/Observable;"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$getHomeOrders$1<T, R> implements Function<T, ObservableSource<? extends R>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f18228a;
    final /* synthetic */ List b;

    DeviceOrderSource$getHomeOrders$1(boolean z, List list) {
        this.f18228a = z;
        this.b = list;
    }

    /* renamed from: a */
    public final Observable<? extends List<HomeOrder>> apply(@NotNull Integer num) {
        Intrinsics.f(num, "it");
        DeviceOrderSource.b.a("getLatestHomeOrder flatMap");
        Observable defer = Observable.defer(new DeviceOrderSource$getHomeOrders$1$cache$1(this));
        Intrinsics.b(defer, "Observable.defer {\n     …                        }");
        Observable<? extends List<HomeOrder>> onErrorResumeNext = DeviceOrderSource.a(DeviceOrderSource.b, this.b, false, false, false, 14, (Object) null).observeOn(DeviceOrderSource.j).doOnNext(DeviceOrderSource$getHomeOrders$1$syncFromServer$1.f18230a).onErrorResumeNext(defer.map(DeviceOrderSource$getHomeOrders$1$syncFromServer$2.f18231a));
        return this.f18228a ? Observable.concat(defer, (Observable) onErrorResumeNext) : onErrorResumeNext;
    }
}
