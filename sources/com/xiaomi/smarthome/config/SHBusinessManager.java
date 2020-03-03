package com.xiaomi.smarthome.config;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.android.exoplayer2.C;
import com.mi.global.shop.model.Tags;
import com.miui.tsmclient.util.Constants;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.webview.CommonWebViewActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SHBusinessManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13942a = "action_business_data_ready";
    public static final long b = 1;
    public static final long c = 2;
    public static final long d = 3;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    private static final String i = "SHBusinessManager";
    private static final String j = "smart_home_business_config";
    private static final String k = "business_content_";
    private static volatile SHBusinessManager l;
    /* access modifiers changed from: private */
    public Map<Long, List<BusinessContent>> m = new HashMap();

    public static class BusinessContent {

        /* renamed from: a  reason: collision with root package name */
        public long f13950a;
        public String b;
        public String c;
        public long d;
        public long e;
        public long f;
        public long g;
        public int h;
        public boolean i;
        public BusinessTarget j;
    }

    public static class BusinessTarget {

        /* renamed from: a  reason: collision with root package name */
        public int f13951a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public Map<String, String> g;
        public Map<String, Boolean> h;
        public Map<String, Integer> i;
        public Map<String, Long> j;
        public Map<String, Double> k;
        public Map<String, Float> l;
    }

    private SHBusinessManager() {
    }

    public static SHBusinessManager a() {
        if (l == null) {
            synchronized (SHBusinessManager.class) {
                if (l == null) {
                    l = new SHBusinessManager();
                }
            }
        }
        return l;
    }

    private void e() {
        try {
            a("[{\"business_id\":2,\"business_content\":[{\"content_id\":1,\"image_url\":\"https://cdn.cnbj0.fds.api.mi-img.com/miio.files/commonfile_jpg_764c02ef34f8c5e9398db703dc3e89ae.jpg\",\"start_date\":1513665000,\"end_date\":1550592000,\"start_time\":480,\"end_time\":860,\"start_version_code\":50000,\"end_version_code\":60712,\"frequency\":1,\"target\":{\"type\":3,\"url\":\"https://home.mi.com/app/shop/content?id=m6da1d039f1601ca1\",\"class_name\":\"com.xiaomi.smarthome.ReportWebViewAct\",\"params_string\":{\"report_url\":\"https://home.mi.com/report/index.html\",\"report_text\":\"米家陪我一起，智变2017./n我是年度\\\"*&*\\\",你呢？\"},\"params_bool\":{\"key2\":true},\"params_int\":{\"key3\":2,\"key4\":3},\"params_long\":{\"key5\":23414523451241},\"params_double\":{\"key6\":234.234242}}}]}]");
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(f13942a));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        if (!CoreApi.a().D() && SHApplication.getStateNotifier().a() == 4) {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("preview", GlobalSetting.E);
            } catch (JSONException unused) {
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/service/getactivityconfig").b((List<KeyValuePair>) arrayList).a();
            AnonymousClass1 r6 = new AsyncCallback<String, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            SHBusinessManager.this.a(str);
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(SHBusinessManager.f13942a));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Map unused = SHBusinessManager.this.m = new HashMap();
                    }
                }
            };
            CoreApi.a().a(SHApplication.getAppContext(), a2, new JsonParser<String>() {
                /* renamed from: a */
                public String parse(JSONObject jSONObject) throws JSONException {
                    LogUtil.a(SHBusinessManager.i, "response = " + jSONObject.toString());
                    JSONObject optJSONObject = jSONObject.optJSONObject("content");
                    return optJSONObject != null ? optJSONObject.optString("business_config") : "";
                }
            }, Crypto.RC4, r6);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        if (optJSONObject != null) {
                            long j2 = -1;
                            long optLong = optJSONObject.optLong(Constants.EXTRA_BUSINESS_ID, -1);
                            JSONArray optJSONArray = optJSONObject.optJSONArray("business_content");
                            if (!(optLong == -1 || optJSONArray == null || optJSONArray.length() <= 0)) {
                                ArrayList arrayList = new ArrayList();
                                int i3 = 0;
                                while (i3 < optJSONArray.length()) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                                    if (optJSONObject2 != null) {
                                        long optLong2 = optJSONObject2.optLong("content_id", j2);
                                        int optInt = optJSONObject2.optInt("start_version_code", -1);
                                        int optInt2 = optJSONObject2.optInt("end_version_code", -1);
                                        int c2 = c();
                                        if (optLong2 != j2 && ((optInt == -1 || optInt <= c2) && (optInt2 == -1 || c2 <= optInt2))) {
                                            BusinessContent businessContent = new BusinessContent();
                                            businessContent.f13950a = optLong2;
                                            businessContent.b = optJSONObject2.optString("image_url");
                                            businessContent.c = optJSONObject2.optString("press_image_url");
                                            businessContent.d = optJSONObject2.optLong("start_date", -1);
                                            businessContent.e = optJSONObject2.optLong("end_date", -1);
                                            businessContent.f = optJSONObject2.optLong(SmartConfigDataProvider.F, -1);
                                            businessContent.g = optJSONObject2.optLong(Tags.ReserveOrder.END_TIME, -1);
                                            businessContent.h = optJSONObject2.optInt(com.xiaomi.verificationsdk.internal.Constants.Q, 0);
                                            businessContent.i = optJSONObject2.optBoolean("permanent", false);
                                            JSONObject optJSONObject3 = optJSONObject2.optJSONObject(TouchesHelper.TARGET_KEY);
                                            if (optJSONObject3 != null) {
                                                BusinessTarget businessTarget = new BusinessTarget();
                                                businessTarget.f13951a = optJSONObject3.optInt("type", -1);
                                                businessTarget.b = optJSONObject3.optString("title");
                                                businessTarget.c = optJSONObject3.optString("url");
                                                businessTarget.d = optJSONObject3.optString("package_name");
                                                businessTarget.e = optJSONObject3.optString("action");
                                                businessTarget.f = optJSONObject3.optString("class_name");
                                                JSONObject optJSONObject4 = optJSONObject3.optJSONObject("params_string");
                                                if (optJSONObject4 != null) {
                                                    HashMap hashMap2 = new HashMap();
                                                    Iterator<String> keys = optJSONObject4.keys();
                                                    while (keys.hasNext()) {
                                                        String next = keys.next();
                                                        String optString = optJSONObject4.optString(next);
                                                        if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(optString)) {
                                                            hashMap2.put(next, optString);
                                                        }
                                                    }
                                                    businessTarget.g = hashMap2;
                                                }
                                                JSONObject optJSONObject5 = optJSONObject3.optJSONObject("params_bool");
                                                if (optJSONObject5 != null) {
                                                    HashMap hashMap3 = new HashMap();
                                                    Iterator<String> keys2 = optJSONObject5.keys();
                                                    while (keys2.hasNext()) {
                                                        String next2 = keys2.next();
                                                        boolean optBoolean = optJSONObject5.optBoolean(next2);
                                                        if (!TextUtils.isEmpty(next2)) {
                                                            hashMap3.put(next2, Boolean.valueOf(optBoolean));
                                                        }
                                                    }
                                                    businessTarget.h = hashMap3;
                                                }
                                                JSONObject optJSONObject6 = optJSONObject3.optJSONObject("params_int");
                                                if (optJSONObject6 != null) {
                                                    HashMap hashMap4 = new HashMap();
                                                    Iterator<String> keys3 = optJSONObject6.keys();
                                                    while (keys3.hasNext()) {
                                                        String next3 = keys3.next();
                                                        int optInt3 = optJSONObject6.optInt(next3);
                                                        if (!TextUtils.isEmpty(next3)) {
                                                            hashMap4.put(next3, Integer.valueOf(optInt3));
                                                        }
                                                    }
                                                    businessTarget.i = hashMap4;
                                                }
                                                JSONObject optJSONObject7 = optJSONObject3.optJSONObject("params_long");
                                                if (optJSONObject7 != null) {
                                                    HashMap hashMap5 = new HashMap();
                                                    Iterator<String> keys4 = optJSONObject7.keys();
                                                    while (keys4.hasNext()) {
                                                        String next4 = keys4.next();
                                                        long optLong3 = optJSONObject7.optLong(next4);
                                                        if (!TextUtils.isEmpty(next4)) {
                                                            hashMap5.put(next4, Long.valueOf(optLong3));
                                                        }
                                                    }
                                                    businessTarget.j = hashMap5;
                                                }
                                                JSONObject optJSONObject8 = optJSONObject3.optJSONObject("params_double");
                                                if (optJSONObject8 != null) {
                                                    HashMap hashMap6 = new HashMap();
                                                    Iterator<String> keys5 = optJSONObject8.keys();
                                                    while (keys5.hasNext()) {
                                                        String next5 = keys5.next();
                                                        double optDouble = optJSONObject8.optDouble(next5);
                                                        if (!TextUtils.isEmpty(next5)) {
                                                            hashMap6.put(next5, Double.valueOf(optDouble));
                                                        }
                                                    }
                                                    businessTarget.k = hashMap6;
                                                }
                                                JSONObject optJSONObject9 = optJSONObject3.optJSONObject("params_float");
                                                if (optJSONObject9 != null) {
                                                    HashMap hashMap7 = new HashMap();
                                                    Iterator<String> keys6 = optJSONObject9.keys();
                                                    while (keys6.hasNext()) {
                                                        String next6 = keys6.next();
                                                        String optString2 = optJSONObject9.optString(next6);
                                                        if (!TextUtils.isEmpty(next6) && !TextUtils.isEmpty(optString2)) {
                                                            try {
                                                                hashMap7.put(next6, Float.valueOf(Float.valueOf(optString2).floatValue()));
                                                            } catch (Exception unused) {
                                                            }
                                                        }
                                                    }
                                                    businessTarget.l = hashMap7;
                                                }
                                                businessContent.j = businessTarget;
                                            }
                                            arrayList.add(businessContent);
                                        }
                                    }
                                    i3++;
                                    j2 = -1;
                                }
                                hashMap.put(Long.valueOf(optLong), arrayList);
                            }
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        this.m = hashMap;
    }

    public int c() {
        return GlobalSetting.n;
    }

    public List<BusinessContent> a(long j2) {
        return this.m.get(Long.valueOf(j2));
    }

    private String b(long j2, long j3) {
        return k + j2 + JSMethod.NOT_SET + j3;
    }

    public void a(long j2, long j3, long j4) {
        SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(j, 0);
        sharedPreferences.edit().putLong(b(j2, j3), j4).apply();
    }

    public long a(long j2, long j3) {
        return SHApplication.getAppContext().getSharedPreferences(j, 0).getLong(b(j2, j3), 0);
    }

    public boolean a(BusinessContent businessContent) {
        if (businessContent == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if ((businessContent.d == -1 || businessContent.d <= currentTimeMillis) && (businessContent.e == -1 || currentTimeMillis <= businessContent.e)) {
            Calendar instance = Calendar.getInstance();
            long j2 = (long) ((instance.get(11) * 60) + instance.get(12));
            if ((businessContent.f == -1 || businessContent.f <= j2) && (businessContent.g == -1 || j2 <= businessContent.g)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(long j2, BusinessContent businessContent) {
        if (businessContent == null || businessContent.f13950a < 0) {
            return false;
        }
        if (businessContent.i) {
            return true;
        }
        long a2 = a().a(j2, businessContent.f13950a);
        if (a2 == 0) {
            return true;
        }
        if (businessContent.h > 0 && DateUtils.j(new Date(a2), new Date()) >= businessContent.h) {
            return true;
        }
        return false;
    }

    public void b(BusinessContent businessContent) {
        String str;
        if (businessContent != null && businessContent.j != null) {
            BusinessTarget businessTarget = businessContent.j;
            switch (businessTarget.f13951a) {
                case 1:
                    if (!TextUtils.isEmpty(businessTarget.c)) {
                        try {
                            UrlDispatchManger.a().c(businessTarget.c);
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                case 2:
                    if (!TextUtils.isEmpty(businessTarget.c)) {
                        Intent intent = new Intent(SHApplication.getAppContext(), CommonWebViewActivity.class);
                        intent.putExtra("url", businessTarget.c);
                        intent.putExtra("title", businessTarget.b);
                        intent.setFlags(C.ENCODING_PCM_MU_LAW);
                        a(businessTarget, intent);
                        try {
                            SHApplication.getAppContext().startActivity(intent);
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                case 3:
                    Intent intent2 = new Intent();
                    if (!TextUtils.isEmpty(businessTarget.d)) {
                        str = businessTarget.d;
                    } else {
                        str = SHApplication.getAppContext().getPackageName();
                    }
                    intent2.setPackage(str);
                    if (!TextUtils.isEmpty(businessTarget.e)) {
                        intent2.setAction(businessTarget.e);
                    }
                    if (!TextUtils.isEmpty(businessTarget.f)) {
                        intent2.setClassName(str, businessTarget.f);
                    }
                    a(businessTarget, intent2);
                    intent2.setFlags(C.ENCODING_PCM_MU_LAW);
                    try {
                        SHApplication.getAppContext().startActivity(intent2);
                        return;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                case 4:
                    if (!TextUtils.isEmpty(businessTarget.c)) {
                        Intent intent3 = new Intent(SHApplication.getAppContext(), OperationCommonWebViewActivity.class);
                        intent3.putExtra("url", businessTarget.c);
                        intent3.putExtra("title", businessTarget.b);
                        intent3.setFlags(C.ENCODING_PCM_MU_LAW);
                        a(businessTarget, intent3);
                        try {
                            SHApplication.getAppContext().startActivity(intent3);
                            return;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void a(BusinessTarget businessTarget, Intent intent) {
        if (businessTarget.g != null && businessTarget.g.size() > 0) {
            for (Map.Entry next : businessTarget.g.entrySet()) {
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    intent.putExtra(str, str2);
                }
            }
        }
        if (businessTarget.h != null && businessTarget.h.size() > 0) {
            for (Map.Entry next2 : businessTarget.h.entrySet()) {
                String str3 = (String) next2.getKey();
                Boolean bool = (Boolean) next2.getValue();
                if (!TextUtils.isEmpty(str3) && bool != null) {
                    intent.putExtra(str3, bool);
                }
            }
        }
        if (businessTarget.i != null && businessTarget.i.size() > 0) {
            for (Map.Entry next3 : businessTarget.i.entrySet()) {
                String str4 = (String) next3.getKey();
                Integer num = (Integer) next3.getValue();
                if (!TextUtils.isEmpty(str4) && num != null) {
                    intent.putExtra(str4, num);
                }
            }
        }
        if (businessTarget.j != null && businessTarget.j.size() > 0) {
            for (Map.Entry next4 : businessTarget.j.entrySet()) {
                String str5 = (String) next4.getKey();
                Long l2 = (Long) next4.getValue();
                if (!TextUtils.isEmpty(str5) && l2 != null) {
                    intent.putExtra(str5, l2);
                }
            }
        }
        if (businessTarget.k != null && businessTarget.k.size() > 0) {
            for (Map.Entry next5 : businessTarget.k.entrySet()) {
                String str6 = (String) next5.getKey();
                Double d2 = (Double) next5.getValue();
                if (!TextUtils.isEmpty(str6) && d2 != null) {
                    intent.putExtra(str6, d2);
                }
            }
        }
        if (businessTarget.l != null && businessTarget.l.size() > 0) {
            for (Map.Entry next6 : businessTarget.l.entrySet()) {
                String str7 = (String) next6.getKey();
                Float f2 = (Float) next6.getValue();
                if (!TextUtils.isEmpty(str7) && f2 != null) {
                    intent.putExtra(str7, f2);
                }
            }
        }
    }

    public void a(Uri uri, final AsyncCallback<Boolean, Error> asyncCallback) {
        if (uri != null && asyncCallback != null) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            if (imagePipeline.isInBitmapMemoryCache(uri)) {
                asyncCallback.onSuccess(true);
            } else {
                imagePipeline.isInDiskCache(uri).subscribe(new BaseDataSubscriber<Boolean>() {
                    /* access modifiers changed from: protected */
                    public void onNewResultImpl(DataSource<Boolean> dataSource) {
                        if (dataSource == null || !dataSource.isFinished()) {
                            SHApplication.getGlobalHandler().post(new Runnable() {
                                public void run() {
                                    asyncCallback.onFailure(new Error(-1, "dataSource finished"));
                                }
                            });
                        }
                        final boolean booleanValue = dataSource.getResult().booleanValue();
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncCallback.onSuccess(Boolean.valueOf(booleanValue));
                            }
                        });
                    }

                    /* access modifiers changed from: protected */
                    public void onFailureImpl(DataSource<Boolean> dataSource) {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncCallback.onFailure(new Error(-1, "dataSource FailureImpl"));
                            }
                        });
                    }
                }, CallerThreadExecutor.getInstance());
            }
        }
    }

    public void d() {
        this.m.clear();
    }

    public void a(Uri uri, final boolean z) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build(), SHApplication.getAppContext()).subscribe(new BaseBitmapDataSubscriber() {
            /* access modifiers changed from: protected */
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            }

            /* access modifiers changed from: protected */
            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                if (z) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(SHBusinessManager.f13942a));
                }
            }
        }, CallerThreadExecutor.getInstance());
    }
}
