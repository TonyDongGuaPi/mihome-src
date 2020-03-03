package com.xiaomi.smarthome.homeroom.device_order;

import com.xiaomi.smarthome.frame.JsonParser;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a,\u0012\u0004\u0012\u00020\u0002 \u0004*\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u00030\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "it", "Lorg/json/JSONObject;", "parse"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$syncSingleOwnerOrderFromServer$1<T> implements JsonParser<T> {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceOrderSource$syncSingleOwnerOrderFromServer$1 f18240a = new DeviceOrderSource$syncSingleOwnerOrderFromServer$1();

    DeviceOrderSource$syncSingleOwnerOrderFromServer$1() {
    }

    /* renamed from: a */
    public final ArrayList<HomeOrder> parse(JSONObject jSONObject) {
        DeviceOrderSource deviceOrderSource = DeviceOrderSource.b;
        String jSONObject2 = jSONObject.toString();
        Intrinsics.b(jSONObject2, "it.toString()");
        deviceOrderSource.a("syncSingleOwnerOrderFromServer", jSONObject2);
        return (ArrayList) DeviceOrderSource.n.parse(jSONObject);
    }
}
