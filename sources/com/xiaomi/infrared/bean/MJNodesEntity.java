package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MJNodesEntity {

    /* renamed from: a  reason: collision with root package name */
    private String f10234a;
    private MJKeyEntity b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private int h;

    public int a() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int b() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public void a(String str) {
        this.f10234a = str;
    }

    public void a(MJKeyEntity mJKeyEntity) {
        this.b = mJKeyEntity;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(int i) {
        this.f = i;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public String c() {
        return this.f10234a;
    }

    public MJKeyEntity d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public int f() {
        return this.f;
    }

    public String g() {
        return this.d;
    }

    public String h() {
        return this.e;
    }

    public static List<MJNodesEntity> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                MJNodesEntity mJNodesEntity = new MJNodesEntity();
                mJNodesEntity.f10234a = CommUtil.a(optJSONObject, "id");
                mJNodesEntity.b = MJKeyEntity.a(optJSONObject.optJSONObject("key"));
                mJNodesEntity.c = CommUtil.a(optJSONObject, "controller_id");
                mJNodesEntity.d = CommUtil.a(optJSONObject, "matched");
                mJNodesEntity.e = CommUtil.a(optJSONObject, "mismatched");
                mJNodesEntity.f = optJSONObject.optInt("count");
                mJNodesEntity.g = optJSONObject.optInt("cursor");
                mJNodesEntity.h = optJSONObject.optInt("total");
                arrayList.add(mJNodesEntity);
                i++;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }
}
