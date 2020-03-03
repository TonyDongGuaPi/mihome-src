package com.xiaomi.smarthome.homeroom.device_order;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/xiaomi/smarthome/homeroom/device_order/CateOrder;", "orderJson", "", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$parseHomeOrder$3 extends Lambda implements Function1<Object, CateOrder> {
    public static final DeviceOrderSource$parseHomeOrder$3 INSTANCE = new DeviceOrderSource$parseHomeOrder$3();

    DeviceOrderSource$parseHomeOrder$3() {
        super(1);
    }

    @NotNull
    public final CateOrder invoke(@NotNull Object obj) {
        ArrayList arrayList;
        Intrinsics.f(obj, "orderJson");
        JSONObject jSONObject = (JSONObject) obj;
        JSONArray optJSONArray = jSONObject.optJSONArray("did_order");
        if (optJSONArray == null || (arrayList = UtilKt.a(optJSONArray, new ArrayList(), DeviceOrderSource$parseHomeOrder$3$orders$1.INSTANCE)) == null) {
            arrayList = new ArrayList();
        }
        String string = jSONObject.getString("cate_id");
        Intrinsics.b(string, "orderJson.getString(\"cate_id\")");
        String optString = jSONObject.optString("parent_id", "");
        Intrinsics.b(optString, "orderJson.optString(\"parent_id\",\"\")");
        return new CateOrder(string, arrayList, optString);
    }
}
