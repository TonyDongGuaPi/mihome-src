package com.xiaomi.infrared.bean;

import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.xiaomi.infrared.utils.CommUtil;
import java.util.List;
import org.json.JSONObject;

public class MjSingleMatchNodesV2 {

    /* renamed from: a  reason: collision with root package name */
    private MJCategoryEntity f10237a;
    private MJCategoryEntity b;
    private MJCategoryEntity c;
    private MJBrandEntity d;
    private String e;
    private List<MJKeysEntity> f;
    private List<MJNodesEntity> g;

    public MJCategoryEntity a() {
        return this.f10237a;
    }

    public void a(MJCategoryEntity mJCategoryEntity) {
        this.f10237a = mJCategoryEntity;
    }

    public void b(MJCategoryEntity mJCategoryEntity) {
        this.c = mJCategoryEntity;
    }

    public MJCategoryEntity b() {
        return this.b;
    }

    public void c(MJCategoryEntity mJCategoryEntity) {
        this.b = mJCategoryEntity;
    }

    public void a(MJBrandEntity mJBrandEntity) {
        this.d = mJBrandEntity;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(List<MJKeysEntity> list) {
        this.f = list;
    }

    public void b(List<MJNodesEntity> list) {
        this.g = list;
    }

    public MJCategoryEntity c() {
        return this.c;
    }

    public MJBrandEntity d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public List<MJKeysEntity> f() {
        return this.f;
    }

    public List<MJNodesEntity> g() {
        return this.g;
    }

    public static MjSingleMatchNodesV2 a(JSONObject jSONObject) {
        MjSingleMatchNodesV2 mjSingleMatchNodesV2 = new MjSingleMatchNodesV2();
        try {
            mjSingleMatchNodesV2.f10237a = MJCategoryEntity.a(jSONObject.optJSONObject("city"));
            mjSingleMatchNodesV2.b = MJCategoryEntity.a(jSONObject.optJSONObject("sp"));
            mjSingleMatchNodesV2.c = MJCategoryEntity.a(jSONObject.optJSONObject("category"));
            mjSingleMatchNodesV2.d = MJBrandEntity.a(jSONObject.optJSONObject("brand"));
            mjSingleMatchNodesV2.e = CommUtil.a(jSONObject, "version");
            mjSingleMatchNodesV2.f = MJKeysEntity.a(jSONObject.optJSONArray(QuickTimeAtomTypes.h));
            mjSingleMatchNodesV2.g = MJNodesEntity.a(jSONObject.optJSONArray("nodes"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return mjSingleMatchNodesV2;
    }
}
