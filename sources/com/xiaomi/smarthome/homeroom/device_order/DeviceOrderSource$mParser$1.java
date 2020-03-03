package com.xiaomi.smarthome.homeroom.device_order;

import com.xiaomi.smarthome.frame.JsonParser;
import java.util.ArrayList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/ArrayList;", "response", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "parse"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$mParser$1<T> implements JsonParser<ArrayList<HomeOrder>> {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceOrderSource$mParser$1 f18235a = new DeviceOrderSource$mParser$1();

    DeviceOrderSource$mParser$1() {
    }

    @NotNull
    /* renamed from: a */
    public final ArrayList<HomeOrder> parse(JSONObject jSONObject) {
        ArrayList<HomeOrder> arrayList = new ArrayList<>();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("info");
            if (optJSONArray != null) {
                UtilKt.a(optJSONArray, arrayList, AnonymousClass1.INSTANCE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
