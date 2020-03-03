package com.xiaomi.smarthome.homeroom.device_order;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$updateCategoryOrder$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $cateId;
    final /* synthetic */ String $homeId;
    final /* synthetic */ List $orderList;
    final /* synthetic */ String $parent_id;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceOrderSource$updateCategoryOrder$1(String str, String str2, String str3, List list) {
        super(0);
        this.$homeId = str;
        this.$cateId = str2;
        this.$parent_id = str3;
        this.$orderList = list;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.xiaomi.smarthome.homeroom.device_order.CateOrder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke() {
        /*
            r5 = this;
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r0 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.HashMap r0 = r0.c()
            java.lang.String r1 = r5.$homeId
            java.lang.Object r0 = r0.get(r1)
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r0 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r0
            r1 = 0
            if (r0 == 0) goto L_0x0039
            java.util.HashSet r0 = r0.f()
            if (r0 == 0) goto L_0x0039
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x001d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0037
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.xiaomi.smarthome.homeroom.device_order.CateOrder r3 = (com.xiaomi.smarthome.homeroom.device_order.CateOrder) r3
            java.lang.String r3 = r3.a()
            java.lang.String r4 = r5.$cateId
            boolean r3 = kotlin.jvm.internal.Intrinsics.a((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x001d
            r1 = r2
        L_0x0037:
            com.xiaomi.smarthome.homeroom.device_order.CateOrder r1 = (com.xiaomi.smarthome.homeroom.device_order.CateOrder) r1
        L_0x0039:
            if (r1 != 0) goto L_0x0063
            java.lang.String r0 = r5.$cateId
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = r5.$parent_id
            com.xiaomi.smarthome.homeroom.device_order.CateOrder r3 = new com.xiaomi.smarthome.homeroom.device_order.CateOrder
            r3.<init>(r0, r1, r2)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r0 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.HashMap r0 = r0.c()
            java.lang.String r1 = r5.$homeId
            java.lang.Object r0 = r0.get(r1)
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r0 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r0
            if (r0 == 0) goto L_0x0062
            java.util.HashSet r0 = r0.f()
            if (r0 == 0) goto L_0x0062
            r0.add(r3)
        L_0x0062:
            r1 = r3
        L_0x0063:
            java.util.ArrayList r0 = r1.b()
            r0.clear()
            java.util.ArrayList r0 = r1.b()
            java.util.List r2 = r5.$orderList
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.m(r2)
            java.util.Collection r2 = (java.util.Collection) r2
            r0.addAll(r2)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r0 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.lang.String r2 = r5.$homeId
            com.xiaomi.smarthome.homeroom.device_order.Order r1 = (com.xiaomi.smarthome.homeroom.device_order.Order) r1
            r0.a((java.lang.String) r2, (com.xiaomi.smarthome.homeroom.device_order.Order) r1)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r0 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.lang.String r1 = r5.$homeId
            r0.c((java.lang.String) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$updateCategoryOrder$1.invoke():void");
    }
}
