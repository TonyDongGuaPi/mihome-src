package com.xiaomi.smarthome.homeroom.model;

import android.util.SparseArray;
import com.mi.global.shop.model.Tags;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeInviteInfo {

    /* renamed from: a  reason: collision with root package name */
    public static int f18314a = 0;
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;
    private long e;
    private long f;
    private long g;
    private long h;
    private int i;
    private long j;
    private long k;
    private String l;
    private String m;
    private String n;
    private String o;

    public String a() {
        return this.l;
    }

    public void a(String str) {
        this.l = str;
    }

    public String b() {
        return this.m;
    }

    public void b(String str) {
        this.m = str;
    }

    public String c() {
        return this.n;
    }

    public void c(String str) {
        this.n = str;
    }

    public String d() {
        return this.o;
    }

    public void d(String str) {
        this.o = str;
    }

    public static HomeInviteInfo a(JSONObject jSONObject) {
        JSONObject optJSONObject;
        HomeInviteInfo homeInviteInfo = new HomeInviteInfo();
        try {
            if (!jSONObject.isNull("owner_uid")) {
                homeInviteInfo.e = jSONObject.optLong("owner_uid");
            }
            if (!jSONObject.isNull("share_to_uid")) {
                homeInviteInfo.f = jSONObject.optLong("share_to_uid");
            }
            if (!jSONObject.isNull("home_id")) {
                homeInviteInfo.g = jSONObject.optLong("home_id");
                homeInviteInfo.l = jSONObject.optString("home_id");
            }
            if (!jSONObject.isNull("message_id")) {
                homeInviteInfo.h = jSONObject.optLong("message_id");
            }
            if (!jSONObject.isNull("status")) {
                homeInviteInfo.i = jSONObject.optInt("status");
            }
            if (!jSONObject.isNull(Tags.RepairProgress.UPDATE_TIME)) {
                homeInviteInfo.j = (long) jSONObject.optInt(Tags.RepairProgress.UPDATE_TIME);
            }
            if (!jSONObject.isNull("expire_time")) {
                homeInviteInfo.k = jSONObject.optLong("expire_time");
            }
            if (!jSONObject.isNull("home_detail") && (optJSONObject = jSONObject.optJSONObject("home_detail")) != null) {
                if (!optJSONObject.isNull("name")) {
                    homeInviteInfo.l = optJSONObject.optString("name");
                }
                if (!optJSONObject.isNull("longitude")) {
                    homeInviteInfo.m = optJSONObject.optString("longitude");
                }
                if (!optJSONObject.isNull("latitude")) {
                    homeInviteInfo.n = optJSONObject.optString("latitude");
                }
                if (!optJSONObject.isNull("city_id")) {
                    homeInviteInfo.o = optJSONObject.optString("city_id");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return homeInviteInfo;
    }

    public static SparseArray<List<HomeInviteInfo>> a(JSONArray jSONArray, Set<Long> set) {
        SparseArray<List<HomeInviteInfo>> sparseArray = new SparseArray<>();
        if (jSONArray == null) {
            return sparseArray;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                HomeInviteInfo a2 = a(optJSONObject);
                List list = sparseArray.get(a2.i());
                if (list == null) {
                    list = new ArrayList();
                    sparseArray.put(a2.i, list);
                }
                list.add(a2);
                if (set != null) {
                    set.add(Long.valueOf(a2.e));
                    set.add(Long.valueOf(a2.f));
                }
            }
        }
        return sparseArray;
    }

    public long e() {
        return this.e;
    }

    public void a(long j2) {
        this.e = j2;
    }

    public long f() {
        return this.f;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public long g() {
        return this.g;
    }

    public void c(long j2) {
        this.g = j2;
    }

    public long h() {
        return this.h;
    }

    public void d(long j2) {
        this.h = j2;
    }

    public int i() {
        return this.i;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public long j() {
        return this.j;
    }

    public void e(long j2) {
        this.j = j2;
    }

    public long k() {
        return this.k;
    }

    public void f(long j2) {
        this.k = j2;
    }
}
