package com.xiaomi.smarthome.homeroom.device_order;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpgradeOrderCompat {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18247a = "common_used_device_sort_value";
    private static final String b = "home_room_manager_sp_";
    private static final String c = "home_room_content";
    private static final String d = "sp_upgrade_order_compat";
    private static final String e = "sp_key_is_old_cached_valid";
    private static UpgradeOrderCompat h;
    private ArrayList<String> f = new ArrayList<>();
    private HashMap<String, HomeOrder> g = new HashMap<>();

    static UpgradeOrderCompat a() {
        if (h == null) {
            synchronized (UpgradeOrderCompat.class) {
                if (h == null) {
                    h = new UpgradeOrderCompat();
                }
            }
        }
        return h;
    }

    private UpgradeOrderCompat() {
        f();
    }

    public void b() {
        SharePrefsManager.a(SHApplication.getAppContext(), d, e, false);
    }

    public boolean c() {
        if (!SharePrefsManager.b(SHApplication.getAppContext(), d, e, true) || this.f.isEmpty() || this.g.isEmpty()) {
            return false;
        }
        return true;
    }

    public ArrayList<String> d() {
        return this.f;
    }

    public HashMap<String, HomeOrder> e() {
        return this.g;
    }

    private void f() {
        if (c()) {
            Map<String, List<String>> g2 = g();
            Map<String, Home> h2 = h();
            for (Map.Entry next : g2.entrySet()) {
                try {
                    Home home = h2.get(next.getKey());
                    if (home != null) {
                        this.f.add(home.j());
                        FrontOrder frontOrder = new FrontOrder(new ArrayList((Collection) next.getValue()));
                        ArrayList arrayList = new ArrayList();
                        List<Room> d2 = home.d();
                        if (d2 != null) {
                            for (Room next2 : d2) {
                                arrayList.add(new RoomOrder(next2.d(), new ArrayList(next2.h())));
                            }
                        }
                        this.g.put(home.j(), new HomeOrder(home.j(), true, false, frontOrder, arrayList, new HashSet()));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static Map<String, List<String>> g() {
        JSONArray optJSONArray;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        try {
            Context appContext = SHApplication.getAppContext();
            String c2 = SharePrefsManager.c(appContext, "home_room_manager_sp_" + MD5Util.a(CoreApi.a().s()), f18247a, "");
            if (TextUtils.isEmpty(c2)) {
                Context appContext2 = SHApplication.getAppContext();
                c2 = SharePrefsManager.c(appContext2, "home_room_manager_sp_" + CoreApi.a().s(), f18247a, "");
            }
            JSONObject jSONObject = new JSONObject(c2);
            if (!jSONObject.isNull("value") && (optJSONArray = jSONObject.optJSONArray("value")) != null) {
                if (optJSONArray.length() != 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        String optString = optJSONObject.optString("home_id");
                        if (!TextUtils.isEmpty(optString)) {
                            ArrayList arrayList = new ArrayList();
                            concurrentHashMap.put(optString, arrayList);
                            if (!optJSONObject.isNull(DTransferConstants.D)) {
                                JSONArray optJSONArray2 = optJSONObject.optJSONArray(DTransferConstants.D);
                                if (optJSONArray2 != null) {
                                    if (optJSONArray2.length() != 0) {
                                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                            if (optJSONObject2 != null) {
                                                if (!optJSONObject2.isNull("did")) {
                                                    String optString2 = optJSONObject2.optString("did");
                                                    if (!TextUtils.isEmpty(optString2)) {
                                                        arrayList.add(optString2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return concurrentHashMap;
                }
            }
            return concurrentHashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            concurrentHashMap.clear();
        }
    }

    private static Map<String, Home> h() {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(SharePrefsManager.c(SHApplication.getAppContext(), "home_room_manager_sp_", c, "{}"));
            if (!jSONObject.isNull("homelist")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("homelist");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Home a2 = Home.a(optJSONArray.optJSONObject(i));
                    if (a2.l() == 0) {
                        if (!TextUtils.isEmpty(a2.j())) {
                            hashMap.put(a2.j(), a2);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }
}
