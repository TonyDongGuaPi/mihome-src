package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MJKeysEntity {

    /* renamed from: a  reason: collision with root package name */
    private String f10233a;
    private String b;
    private String c;
    private String d;
    private boolean e;

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public void d(String str) {
        this.f10233a = str;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public String d() {
        return this.f10233a;
    }

    public boolean e() {
        return this.e;
    }

    public static List<MJKeysEntity> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                MJKeysEntity mJKeysEntity = new MJKeysEntity();
                mJKeysEntity.f10233a = CommUtil.a(optJSONObject, "ac_key");
                mJKeysEntity.b = CommUtil.a(optJSONObject, "id");
                mJKeysEntity.c = CommUtil.a(optJSONObject, "name");
                mJKeysEntity.d = CommUtil.a(optJSONObject, "display_name");
                mJKeysEntity.e = optJSONObject.optBoolean("must_match");
                arrayList.add(mJKeysEntity);
                i++;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }
}
