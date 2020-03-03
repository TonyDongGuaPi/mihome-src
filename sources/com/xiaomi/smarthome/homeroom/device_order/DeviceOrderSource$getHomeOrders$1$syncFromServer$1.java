package com.xiaomi.smarthome.homeroom.device_order;

import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004 \u0006*\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00050\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "orderList", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$getHomeOrders$1$syncFromServer$1<T> implements Consumer<ArrayList<HomeOrder>> {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceOrderSource$getHomeOrders$1$syncFromServer$1 f18230a = new DeviceOrderSource$getHomeOrders$1$syncFromServer$1();

    DeviceOrderSource$getHomeOrders$1$syncFromServer$1() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x013f A[LOOP:3: B:32:0x0139->B:34:0x013f, LOOP_END] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void accept(java.util.ArrayList<com.xiaomi.smarthome.homeroom.device_order.HomeOrder> r11) {
        /*
            r10 = this;
            java.lang.String r0 = "orderList"
            kotlin.jvm.internal.Intrinsics.b(r11, r0)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r1 = r11.iterator()
        L_0x0012:
            boolean r2 = r1.hasNext()
            r3 = 1
            if (r2 == 0) goto L_0x0031
            java.lang.Object r2 = r1.next()
            r4 = r2
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r4 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r4
            java.lang.String r4 = r4.a()
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            r3 = r3 ^ r4
            if (r3 == 0) goto L_0x0012
            r0.add(r2)
            goto L_0x0012
        L_0x0031:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0039:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0163
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r1 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r1
            boolean r2 = r1.b()
            if (r2 != 0) goto L_0x0039
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r2 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.lang.String r4 = "getLatestHomeOrder flatMap save"
            r2.a((java.lang.String) r4)
            com.xiaomi.smarthome.homeroom.device_order.FrontOrder r2 = r1.d()
            java.util.ArrayList r2 = r2.a()
            int r4 = r2.size()
            r5 = 0
            if (r4 != r3) goto L_0x0096
            r4 = r2
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r4 = r4.iterator()
        L_0x006f:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x008c
            java.lang.Object r7 = r4.next()
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            java.lang.String r9 = "-"
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            boolean r8 = android.text.TextUtils.equals(r8, r9)
            if (r8 != 0) goto L_0x006f
            r6.add(r7)
            goto L_0x006f
        L_0x008c:
            java.util.List r6 = (java.util.List) r6
            boolean r4 = r6.isEmpty()
            if (r4 == 0) goto L_0x0096
            r4 = 1
            goto L_0x0097
        L_0x0096:
            r4 = 0
        L_0x0097:
            if (r4 == 0) goto L_0x0101
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r4 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.HashMap r4 = r4.c()
            java.lang.String r6 = r1.a()
            java.lang.Object r4 = r4.get(r6)
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r4 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r4
            if (r4 == 0) goto L_0x0101
            com.xiaomi.smarthome.homeroom.device_order.FrontOrder r6 = r4.d()
            java.util.ArrayList r6 = r6.a()
            int r6 = r6.size()
            if (r6 != r3) goto L_0x00cf
            com.xiaomi.smarthome.homeroom.device_order.FrontOrder r6 = r4.d()
            java.util.ArrayList r6 = r6.a()
            java.lang.Object r5 = r6.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "-"
            boolean r5 = kotlin.jvm.internal.Intrinsics.a((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 != 0) goto L_0x0101
        L_0x00cf:
            r1.a(r3)
            r1.b(r3)
            r2.clear()
            com.xiaomi.smarthome.homeroom.device_order.FrontOrder r4 = r4.d()
            java.util.ArrayList r4 = r4.a()
            java.util.Collection r4 = (java.util.Collection) r4
            r2.addAll(r4)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r2 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.lang.String r4 = "getHomeOrders"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "replace empty front order homeId: "
            r5.append(r6)
            java.lang.String r6 = r1.a()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r2.a((java.lang.String) r4, (java.lang.String) r5)
        L_0x0101:
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r2 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.HashMap r2 = r2.c()
            java.util.Map r2 = (java.util.Map) r2
            java.lang.String r4 = r1.a()
            r2.put(r4, r1)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r2 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.lang.String r1 = r1.a()
            r2.c((java.lang.String) r1)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r1 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.ArrayList r1 = r1.b()
            r1.clear()
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r1 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.ArrayList r1 = r1.b()
            java.util.ArrayList r2 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.a(r11, (int) r4)
            r2.<init>(r4)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r4 = r11.iterator()
        L_0x0139:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x014d
            java.lang.Object r5 = r4.next()
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r5 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r5
            java.lang.String r5 = r5.a()
            r2.add(r5)
            goto L_0x0139
        L_0x014d:
            java.util.List r2 = (java.util.List) r2
            java.util.Collection r2 = (java.util.Collection) r2
            r1.addAll(r2)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r1 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource r2 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.b
            java.util.ArrayList r2 = r2.b()
            java.util.List r2 = (java.util.List) r2
            r1.a((java.util.List<java.lang.String>) r2)
            goto L_0x0039
        L_0x0163:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$getHomeOrders$1$syncFromServer$1.accept(java.util.ArrayList):void");
    }
}
