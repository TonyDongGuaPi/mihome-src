package com.xiaomi.smarthome.newui.onekey_delete;

import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.international.ApiErrorWrapper;
import com.xiaomi.smarthome.international.RxApi;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import io.reactivex.Observable;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteAllPrivacyApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20696a = "DeleteAllPrivacyApi";
    private static DeleteAllPrivacyApi b = null;
    private static final String c = "/v2/user/remove_privacy_data";

    private DeleteAllPrivacyApi() {
    }

    public static DeleteAllPrivacyApi a() {
        if (b == null) {
            synchronized (DeleteAllPrivacyApi.class) {
                if (b == null) {
                    b = new DeleteAllPrivacyApi();
                }
            }
        }
        return b;
    }

    static class DeleteResult {
        static final int d = -4005001;

        /* renamed from: a  reason: collision with root package name */
        final int f20698a;
        final String b;
        final boolean c;

        DeleteResult(int i, String str, boolean z) {
            this.f20698a = i;
            this.b = str;
            this.c = z;
        }
    }

    public Observable<DeleteResult> a(String str) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("identityToken", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RxApi.a(new NetRequest.Builder().a("GET").b(c).b((List<KeyValuePair>) Collections.singletonList(new KeyValuePair("data", jSONObject.toString()))).a(), new JsonParser<DeleteResult>() {
            /* renamed from: a */
            public DeleteResult parse(JSONObject jSONObject) throws JSONException {
                LogUtil.a(DeleteAllPrivacyApi.f20696a, "deleteAllData param: " + jSONObject + " ; response: " + jSONObject);
                return new DeleteResult(jSONObject.getInt("code"), jSONObject.getString("message"), jSONObject.getBoolean("result"));
            }
        }).onErrorReturn($$Lambda$DeleteAllPrivacyApi$oRaEastqCPKjL6wNxBjzG4eLppg.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeleteResult a(Throwable th) throws Exception {
        LogUtil.a(f20696a, "deleteAllData error: " + th);
        if (th instanceof ApiErrorWrapper) {
            ApiErrorWrapper apiErrorWrapper = (ApiErrorWrapper) th;
            if (apiErrorWrapper.code == -4005001) {
                LogUtil.a(f20696a, "deleteAllData return: map to auth: " + apiErrorWrapper.detail);
                return new DeleteResult(apiErrorWrapper.code, apiErrorWrapper.detail, false);
            }
        }
        throw new Exception(th);
    }
}
