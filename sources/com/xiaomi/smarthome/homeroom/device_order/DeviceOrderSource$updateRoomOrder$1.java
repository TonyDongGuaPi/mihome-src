package com.xiaomi.smarthome.homeroom.device_order;

import com.xiaomi.smarthome.homeroom.model.Room;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$updateRoomOrder$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $homeId;
    final /* synthetic */ List $orderList;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceOrderSource$updateRoomOrder$1(String str, List list) {
        super(0);
        this.$homeId = str;
        this.$orderList = list;
    }

    public final void invoke() {
        HomeOrder homeOrder = (HomeOrder) DeviceOrderSource.b.c().get(this.$homeId);
        if (homeOrder == null) {
            homeOrder = new HomeOrder(this.$homeId, false, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 60, (DefaultConstructorMarker) null);
            DeviceOrderSource.b.c().put(this.$homeId, homeOrder);
        }
        homeOrder.e().clear();
        ArrayList<RoomOrder> e = homeOrder.e();
        Iterable<Room> m = CollectionsKt.m(this.$orderList);
        Collection arrayList = new ArrayList(CollectionsKt.a(m, 10));
        for (Room room : m) {
            String d = room.d();
            Intrinsics.b(d, "it.id");
            List<String> h = room.h();
            Intrinsics.b(h, "it.dids");
            arrayList.add(new RoomOrder(d, new ArrayList(CollectionsKt.m(h))));
        }
        e.addAll((List) arrayList);
        DeviceOrderSource.b.a(this.$homeId, (Order) homeOrder);
        DeviceOrderSource.b.c(this.$homeId);
    }
}
