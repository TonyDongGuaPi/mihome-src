package com.xiaomi.smarthome.homeroom.device_order;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/json/JSONObject;", "cateOrder", "Lcom/xiaomi/smarthome/homeroom/device_order/CateOrder;", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1 extends Lambda implements Function1<CateOrder, JSONObject> {
    public static final DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1 INSTANCE = new DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1();

    DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1() {
        super(1);
    }

    @NotNull
    public final JSONObject invoke(@NotNull CateOrder cateOrder) {
        Intrinsics.f(cateOrder, "cateOrder");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cate_id", cateOrder.a());
        jSONObject.put("parent_id", cateOrder.c());
        jSONObject.put("did", UtilKt.a(cateOrder.b(), DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1$1$1.INSTANCE));
        return jSONObject;
    }
}
