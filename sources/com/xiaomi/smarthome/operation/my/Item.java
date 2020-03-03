package com.xiaomi.smarthome.operation.my;

import android.graphics.Bitmap;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.bridge.WXBridgeManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

abstract class Item {
    public Bitmap i;
    public String j;
    public boolean k;

    public abstract boolean a();

    public abstract boolean b();

    Item() {
    }

    public static List<Item> a(String str, boolean z) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            JSONArray jSONArray = jSONObject.getJSONObject(WXBridgeManager.MODULE).getJSONArray("items");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                TabItem tabItem = new TabItem();
                arrayList.add(tabItem);
                tabItem.k = z;
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                tabItem.j = jSONObject2.getString("img");
                tabItem.f21148a = jSONObject2.getString("pageUrl");
                tabItem.b = jSONObject2.getString("title");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("banners");
            if (optJSONArray != null) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    BannerItem bannerItem = new BannerItem();
                    arrayList.add(bannerItem);
                    bannerItem.k = z;
                    JSONObject jSONObject3 = optJSONArray.getJSONObject(i3);
                    bannerItem.j = jSONObject3.getString(Tags.PaidService.IMG_URL);
                    bannerItem.f21134a = jSONObject3.getString("h5Url");
                    bannerItem.b = jSONObject3.getString("shareImgUrl");
                    bannerItem.c = jSONObject3.getString("name");
                    bannerItem.g = jSONObject3.getString("shareDesc");
                    bannerItem.d = jSONObject3.getLong(Tags.Coupon.BEGIN_TIME);
                    bannerItem.e = jSONObject3.getLong("endTime");
                    bannerItem.f = jSONObject3.getInt("position");
                    bannerItem.h = jSONObject3.getInt("jump");
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
