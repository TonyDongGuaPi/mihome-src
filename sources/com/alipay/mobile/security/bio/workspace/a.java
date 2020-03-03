package com.alipay.mobile.security.bio.workspace;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.alipay.mobile.security.bio.api.BioParameter;
import com.alipay.mobile.security.bio.service.BioAppDescription;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.SignHelper;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.stat.d;
import java.io.InputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class a {

    /* renamed from: a  reason: collision with root package name */
    final BioTransfer f1031a;
    protected final Context b;

    public abstract BioAppDescription toBioApp(BioParameter bioParameter);

    a(Context context, BioTransfer bioTransfer) {
        this.f1031a = bioTransfer;
        this.b = context;
    }

    /* access modifiers changed from: protected */
    public InputStream a() {
        return this.b.getAssets().open(BioServiceManager.getEnv().publicKeyAssetsName);
    }

    public static String getUniqueTag() {
        UUID randomUUID = UUID.randomUUID();
        return SignHelper.MD5(System.currentTimeMillis() + JSMethod.NOT_SET + (Math.random() * 10000.0d) + randomUUID.toString());
    }

    /* access modifiers changed from: package-private */
    public String a(JSONObject jSONObject, boolean z) {
        String string = jSONObject.getString("paperGuideUrl");
        String string2 = jSONObject.getString("showPaperGuide");
        String string3 = jSONObject.getString("certType");
        int intValue = jSONObject.getIntValue("paperStartPage");
        int intValue2 = jSONObject.getIntValue("paperTotalPage");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("showPaperGuide", (Object) string2);
        jSONObject2.put("paperGuideUrl", (Object) string);
        jSONObject2.put("certType", (Object) string3);
        jSONObject2.put("paperStartPage", (Object) Integer.valueOf(intValue));
        jSONObject2.put("paperTotalPage", (Object) Integer.valueOf(intValue2));
        jSONObject2.put("fcToken", (Object) this.f1031a.fcToken);
        this.f1031a.mFcSpecialData = jSONObject2;
        if (z) {
            return jSONObject.getString("papersCfg");
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("content", (Object) jSONObject.getString("papersCfg"));
        return jSONObject3.toString();
    }

    /* access modifiers changed from: package-private */
    public String b(JSONObject jSONObject, boolean z) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("f", (Object) d.ak);
        jSONObject2.put("fcToken", (Object) this.f1031a.fcToken);
        this.f1031a.mFcSpecialData = jSONObject2;
        if (z) {
            return jSONObject.getString("faceCfg");
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("content", (Object) jSONObject.getString("faceCfg"));
        return jSONObject3.toString();
    }

    protected static int a(String str) {
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                Matcher matcher = Pattern.compile("(?<=\\\"ui\\\":\\s?\"?)(\\d+)(?=\"?,)").matcher(str);
                if (matcher.find()) {
                    String group = matcher.group();
                    BioLog.i("uiStr=" + group);
                    i = Integer.parseInt(group) + 1000;
                }
            } catch (Throwable th) {
                BioLog.w(th);
            }
        }
        BioLog.i("ui=" + i);
        return i;
    }
}
