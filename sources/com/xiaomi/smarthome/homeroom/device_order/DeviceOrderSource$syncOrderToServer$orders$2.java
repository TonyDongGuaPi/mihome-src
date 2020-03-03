package com.xiaomi.smarthome.homeroom.device_order;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/json/JSONObject;", "roomOrder", "Lcom/xiaomi/smarthome/homeroom/device_order/RoomOrder;", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$syncOrderToServer$orders$2 extends Lambda implements Function1<RoomOrder, JSONObject> {
    public static final DeviceOrderSource$syncOrderToServer$orders$2 INSTANCE = new DeviceOrderSource$syncOrderToServer$orders$2();

    DeviceOrderSource$syncOrderToServer$orders$2() {
        super(1);
    }

    @NotNull
    public final JSONObject invoke(@NotNull RoomOrder roomOrder) {
        Intrinsics.f(roomOrder, "roomOrder");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", roomOrder.a());
        jSONObject.put("value", UtilKt.a(roomOrder.b(), DeviceOrderSource$syncOrderToServer$orders$2$1$1.INSTANCE));
        return jSONObject;
    }
}
