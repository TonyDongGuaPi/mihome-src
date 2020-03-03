package com.xiaomi.smarthome.homeroom.device_order;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00060\u0001J+\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"com/xiaomi/smarthome/homeroom/device_order/DeviceOrderSource$syncOrderFromServer$1", "Lio/reactivex/functions/Function;", "", "", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/ArrayList;", "apply", "t", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class DeviceOrderSource$syncOrderFromServer$1 implements Function<Object[], ArrayList<HomeOrder>> {
    DeviceOrderSource$syncOrderFromServer$1() {
    }

    @NotNull
    /* renamed from: a */
    public ArrayList<HomeOrder> apply(@NotNull Object[] objArr) {
        Intrinsics.f(objArr, "t");
        ArrayList<HomeOrder> arrayList = new ArrayList<>();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Collection collection = objArr[i];
            if (collection != null) {
                arrayList.addAll(collection);
                i++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Collection<com.xiaomi.smarthome.homeroom.device_order.HomeOrder>");
            }
        }
        return arrayList;
    }
}
