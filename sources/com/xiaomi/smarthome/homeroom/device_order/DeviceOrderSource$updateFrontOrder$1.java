package com.xiaomi.smarthome.homeroom.device_order;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$updateFrontOrder$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $homeId;
    final /* synthetic */ List $orderList;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceOrderSource$updateFrontOrder$1(String str, List list) {
        super(0);
        this.$homeId = str;
        this.$orderList = list;
    }

    public final void invoke() {
        FrontOrder d;
        HomeOrder homeOrder = (HomeOrder) DeviceOrderSource.b.c().get(this.$homeId);
        if (!(homeOrder == null || (d = homeOrder.d()) == null)) {
            d.a().clear();
            d.a().addAll(CollectionsKt.m(this.$orderList));
            DeviceOrderSource.b.a(this.$homeId, (Order) d);
        }
        DeviceOrderSource.b.c(this.$homeId);
    }
}
