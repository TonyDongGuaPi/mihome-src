package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.mi.global.shop.base.request.HostManager;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecAction;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.device.api.spec.operation.ActionParam;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyParam;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.newui.card.CardAbstractManager;
import com.xiaomi.smarthome.newui.card.DeviceCardApi;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import com.xiaomi.smarthome.newui.card.spec.SpecCard;
import com.xiaomi.smarthome.newui.card.spec.SpecCardSelector;
import com.xiaomi.smarthome.newui.card.spec.SpecUnit;
import com.xiaomi.smarthome.newui.card.spec.SpecUtils;
import com.xiaomi.smarthome.newui.card.spec.instance.SpecDeviceCodec;
import com.xiaomi.smarthome.service.CardActive;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiotSpecCardManager extends CardAbstractManager {
    private static final int c = 300;
    private static final int d = 11;
    private static final String e = "CardItemIcons_new3x";
    private static MiotSpecCardManager g;
    private final AppConfigHelper f;
    /* access modifiers changed from: private */
    public final SpecCache h;
    /* access modifiers changed from: private */
    public Map<String, Map<String, String>> i = new HashMap();
    private Map<String, SpecUnit> j = new HashMap();
    private Map<String, List<PropItem.PropExtraItem>> k = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, SpecCardSelector> l = new HashMap();
    /* access modifiers changed from: private */
    public Handler m;
    /* access modifiers changed from: private */
    public boolean n = false;
    private DevicePropSubscriber o;

    private MiotSpecCardManager() {
        HandlerThread handlerThread = new HandlerThread("MiotSpecCardManager");
        handlerThread.start();
        this.h = new SpecCache();
        this.m = new Handler(handlerThread.getLooper());
        Context appContext = CommonApplication.getAppContext();
        this.f = new AppConfigHelper(appContext);
        if (appContext != null) {
            new IntentFilter().addAction("action_on_login_success");
            SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) new SmartHomeDeviceManager.IClientDeviceListener() {

                /* renamed from: a  reason: collision with root package name */
                Runnable f20509a = new Runnable() {
                    public final void run() {
                        MiotSpecCardManager.AnonymousClass1.this.a();
                    }
                };

                public void a(int i, Device device) {
                }

                public void b(int i) {
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    MiotSpecCardManager.this.h.a(SmartHomeDeviceManager.a().f(), (Runnable) new Runnable() {
                        public final void run() {
                            MiotSpecCardManager.AnonymousClass1.this.b();
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void b() {
                    MiotSpecCardManager.this.a(true, (AsyncCallback<Object, Error>) null);
                }

                public void a(int i) {
                    if (i == 3 && ServiceApplication.getStateNotifier().a() == 4) {
                        MiotSpecCardManager.this.m.postDelayed(this.f20509a, 2000);
                    }
                }
            });
        }
        LogUtil.c("mijia-card", "MiotSpecCardManager SpecCardVersion:11");
    }

    public String d() {
        return super.d() + CardItemUtils.a(e) + File.separator;
    }

    /* access modifiers changed from: private */
    public void i() {
        this.f.a("card_default_resource", "1", "en", "cardControl/cardLanguageConfig.json", new AppConfigHelper.OnCacheHandler<JSONObject>() {
            public boolean a(JSONObject jSONObject) throws Exception {
                boolean a2 = MiotSpecCardManager.this.b(jSONObject);
                LogUtil.c("mijia-card", "MiotSpecCardManager.loadLanguageConfig processCache mLanguageMap.size:" + MiotSpecCardManager.this.i.size());
                return a2;
            }

            public boolean b(JSONObject jSONObject) throws Exception {
                boolean a2 = MiotSpecCardManager.this.b(jSONObject);
                LogUtil.c("mijia-card", "MiotSpecCardManager.loadLanguageConfig processAssets mLanguageMap.size:" + MiotSpecCardManager.this.i.size());
                return a2;
            }
        }, new AppConfigHelper.JsonAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject, Response response) {
                boolean unused = MiotSpecCardManager.this.b(jSONObject);
                MiotSpecCardManager.this.e();
                LogUtil.c("mijia-card", "MiotSpecCardManager.loadLanguageConfig onSuccess mLanguageMap.size:" + MiotSpecCardManager.this.i.size());
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                LogUtil.b("mijia-card", "MiotSpecCardManager.loadLanguageConfig onFailure");
            }
        });
    }

    public static MiotSpecCardManager f() {
        if (g == null) {
            synchronized (MiotSpecCardManager.class) {
                if (g == null) {
                    g = new MiotSpecCardManager();
                }
            }
        }
        return g;
    }

    public void g() {
        this.m.post(new Runnable() {
            public void run() {
                MiotSpecCardManager.this.j();
                MiotSpecCardManager.this.i();
                MiotSpecCardManager.this.a();
                MiotSpecCardManager.this.h.a((Runnable) new Runnable() {
                    public final void run() {
                        MiotSpecCardManager.AnonymousClass4.this.a();
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a() {
                MiotSpecCardManager.this.a(true, (AsyncCallback<Object, Error>) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void j() {
        this.f.a("card_control_miotspec_config", "1", "zh_cn", "cardControl/miotSpecConfigDes.json", new AppConfigHelper.OnCacheHandler<JSONObject>() {
            public boolean a(JSONObject jSONObject) throws Exception {
                String optString = jSONObject.optJSONObject("result").optString("content");
                if (!AppConfigHelper.a(optString)) {
                    return false;
                }
                Map a2 = MiotSpecCardManager.this.a(new JSONArray(optString));
                if (a2.size() == 0) {
                    return false;
                }
                MiotSpecCardManager.this.l.clear();
                MiotSpecCardManager.this.l.putAll(a2);
                LogUtil.c("mijia-card", "MiotSpecCardManager.loadConfigFromServer processCache mAllCardInfo.size:" + MiotSpecCardManager.this.l.size());
                return true;
            }

            public boolean b(JSONObject jSONObject) throws Exception {
                return a(jSONObject);
            }
        }, new AppConfigHelper.JsonAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject, Response response) {
                try {
                    String optString = jSONObject.optJSONObject("result").optString("content");
                    if (AppConfigHelper.a(optString)) {
                        Map a2 = MiotSpecCardManager.this.a(new JSONArray(optString));
                        if (a2.size() != 0) {
                            MiotSpecCardManager.this.l.clear();
                            MiotSpecCardManager.this.l.putAll(a2);
                            LogUtil.c("mijia-card", "MiotSpecCardManager.loadConfigFromServer onSuccess mAllCardInfo.size:" + MiotSpecCardManager.this.l.size());
                        }
                    }
                    MiotSpecCardManager.this.e();
                } catch (Exception e) {
                    LogUtil.b("mijia-card", Log.getStackTraceString(e));
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                LogUtil.b("mijia-card", "MiotSpecCardManager.loadConfigFromServer onFailure");
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean b(JSONObject jSONObject) {
        boolean z;
        JSONObject jSONObject2;
        try {
            String optString = jSONObject.optJSONObject("result").optString("content");
            if (!AppConfigHelper.a(optString)) {
                return false;
            }
            JSONObject jSONObject3 = new JSONObject(optString);
            HashMap hashMap = new HashMap();
            JSONObject optJSONObject = jSONObject3.optJSONObject("names");
            if (optJSONObject != null) {
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(next);
                    if (optJSONObject2 != null) {
                        ArrayMap arrayMap = new ArrayMap();
                        hashMap.put(a(next), arrayMap);
                        Iterator<String> keys2 = optJSONObject2.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            arrayMap.put(CardItemUtils.a(next2), optJSONObject2.optString(next2, ""));
                        }
                    }
                }
            }
            HashMap hashMap2 = new HashMap();
            JSONObject optJSONObject3 = jSONObject3.optJSONObject("units");
            if (optJSONObject3 != null) {
                Iterator<String> keys3 = optJSONObject3.keys();
                while (keys3.hasNext()) {
                    String next3 = keys3.next();
                    JSONObject optJSONObject4 = optJSONObject3.optJSONObject(next3);
                    if (optJSONObject4 != null) {
                        double optDouble = optJSONObject4.optDouble(HostManager.Parameters.Keys.DEVICE_RATIO, 1.0d);
                        ArrayMap arrayMap2 = new ArrayMap();
                        hashMap2.put(a(next3), new SpecUnit(optDouble, arrayMap2));
                        JSONObject optJSONObject5 = optJSONObject4.optJSONObject("name");
                        if (optJSONObject5 != null) {
                            Iterator<String> keys4 = optJSONObject5.keys();
                            while (keys4.hasNext()) {
                                String next4 = keys4.next();
                                arrayMap2.put(CardItemUtils.a(next4), optJSONObject5.optString(next4, ""));
                            }
                        }
                    }
                }
            }
            HashMap hashMap3 = new HashMap();
            JSONObject optJSONObject6 = jSONObject3.optJSONObject("prop_extras");
            if (optJSONObject6 != null) {
                Iterator<String> keys5 = optJSONObject6.keys();
                while (keys5.hasNext()) {
                    String next5 = keys5.next();
                    JSONArray optJSONArray = optJSONObject6.optJSONArray(next5);
                    if (optJSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            arrayList.add(new PropItem.PropExtraItem(optJSONArray.optJSONObject(i2)));
                        }
                        hashMap3.put(a(next5), arrayList);
                    }
                }
            }
            if (hashMap.size() != 0) {
                this.i.clear();
                this.i.putAll(hashMap);
                z = true;
            } else {
                z = false;
            }
            if (hashMap2.size() != 0) {
                this.j.clear();
                this.j.putAll(hashMap2);
            } else {
                z = false;
            }
            if (hashMap3.size() != 0) {
                this.k.clear();
                this.k.putAll(hashMap3);
            } else {
                z = false;
            }
            JSONObject optJSONObject7 = jSONObject3.optJSONObject("download_url");
            if (optJSONObject7 == null) {
                jSONObject2 = null;
            } else {
                jSONObject2 = optJSONObject7.optJSONObject(e);
            }
            if (jSONObject2 == null) {
                return false;
            }
            a(this.m, jSONObject2);
            return z;
        } catch (Exception e2) {
            LogUtil.b("mijia-card", Log.getStackTraceString(e2));
            return false;
        }
    }

    /* access modifiers changed from: private */
    public Map<String, SpecCardSelector> a(JSONArray jSONArray) {
        HashMap hashMap = new HashMap();
        if (jSONArray == null || jSONArray.length() == 0) {
            return hashMap;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            SpecCardSelector specCardSelector = new SpecCardSelector();
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("type");
                JSONArray optJSONArray = optJSONObject.optJSONArray("card_items");
                HashMap hashMap2 = new HashMap();
                if (optJSONArray != null) {
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        SpecCardType a2 = SpecCardType.a(optJSONArray.optJSONObject(i3));
                        hashMap2.put(a2.c, a2);
                    }
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("card_instance");
                if (optJSONArray2 != null) {
                    for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i4);
                        SpecCard specCard = new SpecCard(optJSONObject2, hashMap2);
                        if (optJSONObject2.optInt("min_support_version", 1) <= 11) {
                            int optInt = optJSONObject2.optInt("instance_type");
                            if (optInt == 0) {
                                specCardSelector.f20593a = specCard;
                            } else if (optInt == 1) {
                                if (specCardSelector.b == null) {
                                    specCardSelector.b = new ArrayList<>();
                                }
                                specCardSelector.b.add(specCard);
                            } else if (optInt == 2) {
                                if (optJSONObject2.optJSONArray("type") != null) {
                                    HashSet hashSet = new HashSet();
                                    JSONArray optJSONArray3 = optJSONObject2.optJSONArray("type");
                                    for (int i5 = 0; i5 < optJSONArray3.length(); i5++) {
                                        hashSet.add(optJSONArray3.optString(i5));
                                    }
                                    if (specCardSelector.c == null) {
                                        specCardSelector.c = new ArrayMap();
                                    }
                                    specCardSelector.c.put(hashSet, specCard);
                                }
                            } else if (optInt == 5) {
                                if (specCardSelector.d == null) {
                                    specCardSelector.d = new ArrayList<>();
                                }
                                specCardSelector.d.add(specCard);
                            }
                        }
                    }
                }
                if (specCardSelector.d != null) {
                    specCardSelector.d.trimToSize();
                }
                if (specCardSelector.b != null) {
                    specCardSelector.b.trimToSize();
                }
                hashMap.put(optString, specCardSelector);
            }
        }
        return hashMap;
    }

    public SpecDevice a(Device device) {
        return this.h.b(device);
    }

    public Map<String, Map<String, String>> b(Device device) {
        return this.h.a(device);
    }

    public SpecCard c(Device device) {
        SpecDevice a2 = a(device);
        SpecCardSelector a3 = a(a2);
        if (a3 != null) {
            return a3.a(a2);
        }
        LogUtil.c("mijia-card", "SpecCardSelector is null device:" + device + " SpecDevice:" + a2);
        return null;
    }

    public SpecCardSelector a(SpecDevice specDevice) {
        if (specDevice == null) {
            return null;
        }
        try {
            return this.l.get(specDevice.getTypeName());
        } catch (Exception e2) {
            LogUtil.b("mijia-card", Log.getStackTraceString(e2));
            return null;
        }
    }

    public Pair<SpecService, ? extends Spec.SpecItem>[] a(Device device, int i2) {
        Pair<SpecService, ? extends Spec.SpecItem>[][] b;
        MiotSpecCardManager f2 = f();
        SpecDevice a2 = f2.a(device);
        SpecCardSelector a3 = f2.a(a2);
        if (a3 == null || (b = a3.b(a2)) == null || b.length <= i2) {
            return null;
        }
        return b[i2];
    }

    public SpecCard d(Device device) {
        String d2 = d();
        if (!FileUtils.f(d2)) {
            LogUtil.b("mijia-card", "MiotSpecCardManager.chooseSpecCardInstance notFind. mijia error icon file not exist.:" + d2);
            return null;
        }
        SpecDevice a2 = a(device);
        SpecCardSelector a3 = a(a2);
        if (a3 == null) {
            LogUtil.b("mijia-card", "MiotSpecCardManager.chooseSpecCardInstance notFind. null card model:" + device.model + " null card config urn:" + a2);
            return null;
        }
        LogUtil.b("mijia-card", "MiotSpecCardManager.chooseSpecCardInstance prematch. model:" + device.model + " did" + device.did + " urn:" + a2.getType());
        return a3.d(a2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0051, code lost:
        r11 = a(r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r21, com.xiaomi.smarthome.frame.AsyncCallback<java.lang.Object, com.xiaomi.smarthome.frame.Error> r22) {
        /*
            r20 = this;
            r6 = r20
            r7 = r22
            boolean r0 = r6.n
            if (r0 == 0) goto L_0x0016
            if (r7 == 0) goto L_0x0015
            com.xiaomi.smarthome.frame.Error r0 = new com.xiaomi.smarthome.frame.Error
            r1 = -1
            java.lang.String r2 = "is refreshing"
            r0.<init>(r1, r2)
            r7.onFailure(r0)
        L_0x0015:
            return
        L_0x0016:
            r0 = 1
            r6.n = r0
            long r0 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r8 = r4.f()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r8)
            com.xiaomi.smarthome.newui.card.ControlCardInfoManager r5 = com.xiaomi.smarthome.newui.card.ControlCardInfoManager.f()
            java.util.Iterator r4 = r4.iterator()
        L_0x003c:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x0122
            java.lang.Object r9 = r4.next()
            com.xiaomi.smarthome.device.Device r9 = (com.xiaomi.smarthome.device.Device) r9
            java.lang.String r11 = r9.model
            com.xiaomi.smarthome.newui.card.profile.ProfileCard r11 = r5.e((java.lang.String) r11)
            if (r11 == 0) goto L_0x0051
            goto L_0x003c
        L_0x0051:
            com.xiaomi.smarthome.device.api.spec.instance.SpecDevice r11 = r6.a((com.xiaomi.smarthome.device.Device) r9)
            com.xiaomi.smarthome.newui.card.spec.SpecCardSelector r12 = r6.a((com.xiaomi.smarthome.device.api.spec.instance.SpecDevice) r11)
            if (r12 != 0) goto L_0x005c
            goto L_0x003c
        L_0x005c:
            android.util.Pair r11 = r12.c(r11)
            if (r11 == 0) goto L_0x0118
            java.lang.Object r12 = r11.first
            if (r12 == 0) goto L_0x0118
            java.lang.Object r12 = r11.first
            com.xiaomi.smarthome.newui.card.spec.SpecCard r12 = (com.xiaomi.smarthome.newui.card.spec.SpecCard) r12
            java.lang.Object r11 = r11.second
            android.util.Pair[][] r11 = (android.util.Pair[][]) r11
            java.util.List r12 = r12.a()
            r13 = r3
            r3 = 0
        L_0x0074:
            int r14 = r12.size()
            if (r3 >= r14) goto L_0x0112
            java.lang.Object r14 = r12.get(r3)
            com.xiaomi.smarthome.newui.card.Card$CardType r14 = (com.xiaomi.smarthome.newui.card.Card.CardType) r14
            r15 = r11[r3]
            if (r15 != 0) goto L_0x00ad
            java.lang.String r15 = "mijia-card"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r16 = r4
            java.lang.String r4 = "getAllDevicePropFromServer not contain layout"
            r10.append(r4)
            java.lang.String r4 = r14.c
            r10.append(r4)
            java.lang.String r4 = " model:"
            r10.append(r4)
            java.lang.String r4 = r9.model
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            android.util.Log.e(r15, r4)
        L_0x00a8:
            r18 = r5
            r19 = r9
            goto L_0x0108
        L_0x00ad:
            r16 = r4
            int r4 = r15.length
            r10 = 0
        L_0x00b1:
            if (r10 >= r4) goto L_0x00a8
            r14 = r15[r10]
            r17 = r4
            java.lang.Object r4 = r14.second
            boolean r4 = r4 instanceof com.xiaomi.smarthome.device.api.spec.instance.SpecProperty
            if (r4 == 0) goto L_0x00fb
            java.lang.Object r4 = r14.second
            com.xiaomi.smarthome.device.api.spec.instance.SpecProperty r4 = (com.xiaomi.smarthome.device.api.spec.instance.SpecProperty) r4
            com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition r4 = r4.getPropertyDefinition()
            boolean r4 = r4.readable()
            if (r4 == 0) goto L_0x00fb
            com.xiaomi.smarthome.device.api.spec.operation.PropertyParam r4 = new com.xiaomi.smarthome.device.api.spec.operation.PropertyParam
            r18 = r5
            java.lang.String r5 = r9.did
            r19 = r9
            java.lang.Object r9 = r14.first
            com.xiaomi.smarthome.device.api.spec.instance.SpecService r9 = (com.xiaomi.smarthome.device.api.spec.instance.SpecService) r9
            int r9 = r9.getIid()
            java.lang.Object r14 = r14.second
            com.xiaomi.smarthome.device.api.spec.instance.Spec$SpecItem r14 = (com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem) r14
            int r14 = r14.getIid()
            r4.<init>(r5, r9, r14)
            r13.add(r4)
            int r4 = r13.size()
            r5 = 300(0x12c, float:4.2E-43)
            if (r4 < r5) goto L_0x00ff
            r2.add(r13)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r13 = r4
            goto L_0x00ff
        L_0x00fb:
            r18 = r5
            r19 = r9
        L_0x00ff:
            int r10 = r10 + 1
            r4 = r17
            r5 = r18
            r9 = r19
            goto L_0x00b1
        L_0x0108:
            int r3 = r3 + 1
            r4 = r16
            r5 = r18
            r9 = r19
            goto L_0x0074
        L_0x0112:
            r16 = r4
            r18 = r5
            r3 = r13
            goto L_0x011c
        L_0x0118:
            r16 = r4
            r18 = r5
        L_0x011c:
            r4 = r16
            r5 = r18
            goto L_0x003c
        L_0x0122:
            java.lang.String r4 = "mijia-card"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r9 = "MiotSpecCardManager.getAllDevicePropFromServer operation.size:"
            r5.append(r9)
            int r9 = r3.size()
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r4, r5)
            int r4 = r3.size()
            if (r4 != 0) goto L_0x0152
            int r4 = r2.size()
            if (r4 != 0) goto L_0x0152
            if (r7 == 0) goto L_0x014e
            r0 = 0
            r7.onSuccess(r0)
        L_0x014e:
            r0 = 0
            r6.n = r0
            return
        L_0x0152:
            int r4 = r3.size()
            if (r4 <= 0) goto L_0x015b
            r2.add(r3)
        L_0x015b:
            java.util.concurrent.atomic.AtomicInteger r9 = new java.util.concurrent.atomic.AtomicInteger
            int r3 = r2.size()
            r9.<init>(r3)
            java.lang.String r3 = "mijia-card"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "MiotSpecCardManager.getAllDevicePropFromServer rquest size:"
            r4.append(r5)
            int r5 = r9.get()
            r4.append(r5)
            java.lang.String r5 = " spend time:"
            r4.append(r5)
            long r10 = java.lang.System.currentTimeMillis()
            long r10 = r10 - r0
            r4.append(r10)
            java.lang.String r0 = r4.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r3, r0)
            java.util.Iterator r10 = r2.iterator()
        L_0x018f:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x01b2
            java.lang.Object r0 = r10.next()
            r11 = r0
            java.util.List r11 = (java.util.List) r11
            android.content.Context r12 = com.xiaomi.smarthome.application.CommonApplication.getAppContext()
            com.xiaomi.smarthome.newui.card.MiotSpecCardManager$7 r13 = new com.xiaomi.smarthome.newui.card.MiotSpecCardManager$7
            r0 = r13
            r1 = r20
            r2 = r9
            r3 = r22
            r4 = r21
            r5 = r8
            r0.<init>(r2, r3, r4, r5)
            com.xiaomi.smarthome.newui.card.DeviceCardApi.a(r12, r11, r13)
            goto L_0x018f
        L_0x01b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.a(boolean, com.xiaomi.smarthome.frame.AsyncCallback):void");
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list) {
        if (this.o == null) {
            this.o = new DevicePropSubscriber();
        }
        ArrayList<Device> arrayList = new ArrayList<>(list);
        ControlCardInfoManager f2 = ControlCardInfoManager.f();
        ArrayList arrayList2 = new ArrayList();
        for (Device device : arrayList) {
            if (DeviceUtils.d(device) != null && f2.e(device.model) == null) {
                arrayList2.add(device);
            }
        }
        this.o.a((List<Device>) arrayList2, (DevicePropSubscriber.DeviceSubscriberInterface) new CardAbstractManager.CardDeviceSubscriberInterface(this));
    }

    public void a(final String str, List<Pair<SpecService, ? extends Spec.SpecItem>[]> list, final AsyncCallback<Object, Error> asyncCallback) {
        if (list != null && list.size() != 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<Pair<SpecService, ? extends Spec.SpecItem>[]> it = list.iterator();
            while (it.hasNext()) {
                Pair[] pairArr = (Pair[]) it.next();
                if (pairArr != null) {
                    for (Pair pair : pairArr) {
                        if (pair != null && (pair.second instanceof SpecProperty) && ((SpecProperty) pair.second).getPropertyDefinition().readable()) {
                            arrayList.add(new PropertyParam(str, ((SpecService) pair.first).getIid(), ((Spec.SpecItem) pair.second).getIid()));
                        }
                    }
                }
            }
            LogUtil.c("mijia-card", "MiotSpecCardManager.getDevicePropFromServer request.size:" + arrayList);
            if (arrayList.size() != 0) {
                DeviceCardApi.b(CommonApplication.getAppContext(), arrayList, new AsyncCallback<List<PropertyParam>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(List<PropertyParam> list) {
                        if (list == null || list.size() == 0) {
                            LogUtil.c("mijia-card", "MiotSpecCardManager.getDevicePropFromServer result is null");
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(null);
                                return;
                            }
                            return;
                        }
                        LogUtil.c("mijia-card", "MiotSpecCardManager.getDevicePropFromServer result.size:" + list);
                        for (PropertyParam next : list) {
                            String a2 = SpecUtils.a(next.getSiid(), next.getPiid());
                            if (next.getResultCode() == 0) {
                                MiotSpecCardManager.this.a(str, a2, next.getValue());
                                if (next.getTimestamp() > 0) {
                                    MiotSpecCardManager.this.a(str, a2, next.getTimestamp());
                                }
                            } else {
                                Map<String, Object> b2 = MiotSpecCardManager.this.b(str);
                                if (b2 == null || b2.get(a2) == null) {
                                    MiotSpecCardManager.this.a(str, a2, next.getValue());
                                }
                            }
                        }
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(null);
                        }
                    }

                    public void onFailure(Error error) {
                        LogUtil.b("mijia-card", "MiotSpecCardManager.getDevicePropFromServer onFailure" + error);
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
            }
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "no chosen cardInstance"));
        }
    }

    public void a(String str, SpecService specService, SpecProperty specProperty, Object obj, AsyncCallback<JSONObject, Error> asyncCallback) {
        String str2 = str;
        SpecService specService2 = specService;
        SpecProperty specProperty2 = specProperty;
        Object obj2 = obj;
        AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
        if (specService2 == null || specProperty2 == null || obj2 == null) {
            LogUtil.b("mijia-card", "MiotSpecCardManager.setDeviceProp service:" + specService2 + "  property:" + specProperty2 + " value:" + obj2);
            if (asyncCallback2 != null) {
                asyncCallback2.onFailure(new Error(-1, "not property or no value"));
                return;
            }
            return;
        }
        PropertyDefinition propertyDefinition = specProperty.getPropertyDefinition();
        if (propertyDefinition.writable()) {
            PropertyParam propertyParam = new PropertyParam(str, specService.getIid(), specProperty.getIid());
            propertyParam.setValue(obj2);
            String a2 = SpecUtils.a(specService.getIid(), specProperty.getIid());
            Object a3 = a(str, a2, obj2);
            if (!obj2.equals(a3) || !propertyDefinition.readable()) {
                LogUtil.c("mijia-card", "setDeviceProp request" + propertyParam);
                final AsyncCallback<JSONObject, Error> asyncCallback3 = asyncCallback;
                final String str3 = str;
                final String str4 = a2;
                final Object obj3 = a3;
                final SpecService specService3 = specService;
                final SpecProperty specProperty3 = specProperty;
                DeviceCardApi.SpecPropertyApi.instance.setDeviceSpecProp(CommonApplication.getAppContext(), propertyParam, new AsyncCallback<PropertyParam, Error>() {
                    /* renamed from: a */
                    public void onSuccess(PropertyParam propertyParam) {
                        LogUtil.c("mijia-card", "setDeviceProp onSuccess" + propertyParam);
                        if (propertyParam != null && propertyParam.getResultCode() == 0) {
                            MiotSpecCardManager.this.a(str3, str4, System.currentTimeMillis() / 1000);
                            if (asyncCallback3 != null) {
                                asyncCallback3.onSuccess(null);
                            }
                        } else if (asyncCallback3 != null) {
                            asyncCallback3.onFailure(null);
                        }
                    }

                    public void onFailure(Error error) {
                        LogUtil.b("mijia-card", "setDeviceProp onFailure" + error);
                        if (obj3 != null) {
                            MiotSpecCardManager.this.a(str3, SpecUtils.a(specService3.getIid(), specProperty3.getIid()), obj3);
                        }
                        if (asyncCallback3 != null) {
                            asyncCallback3.onFailure(error);
                        }
                    }
                });
                return;
            }
            LogUtil.b("mijia-card", "MiotSpecCardManager.setDeviceProp no change value:" + obj2);
            if (asyncCallback2 != null) {
                asyncCallback2.onSuccess(null);
                return;
            }
            return;
        }
        if (asyncCallback2 != null) {
            asyncCallback2.onFailure(new Error(-1, "cannot writable"));
        }
    }

    public void a(String str, Pair<SpecService, SpecAction> pair, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr, AsyncCallback<JSONObject, Error> asyncCallback) {
        ActionParam actionParam = new ActionParam();
        actionParam.setDid(str);
        actionParam.setSiid(((SpecService) pair.first).getIid());
        actionParam.setAiid(((SpecAction) pair.second).getIid());
        LogUtil.c("mijia-card", "setDeviceSpecAction request" + actionParam);
        final Object a2 = (pairArr == null || pairArr.length > 0) ? null : a(str, pairArr[0]);
        final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
        final String str2 = str;
        final ActionParam actionParam2 = actionParam;
        final Pair<SpecService, ? extends Spec.SpecItem>[] pairArr2 = pairArr;
        DeviceCardApi.SpecActionApi.instance.setDeviceSpecAction(CommonApplication.getAppContext(), actionParam, new AsyncCallback<ActionParam, Error>() {
            /* renamed from: a */
            public void onSuccess(ActionParam actionParam) {
                LogUtil.c("mijia-card", "setDeviceSpecAction onSuccess" + actionParam);
                if (actionParam != null && actionParam.getResultCode() == 0) {
                    MiotSpecCardManager.this.a(str2, SpecUtils.b(actionParam2.getSiid(), actionParam2.getAiid()), System.currentTimeMillis() / 1000);
                    if (asyncCallback2 != null) {
                        asyncCallback2.onSuccess(null);
                    }
                    final ArrayList arrayList = new ArrayList();
                    arrayList.add(pairArr2);
                    MiotSpecCardManager.this.a(str2, arrayList, new AsyncCallback<Object, Error>() {
                        public void onSuccess(Object obj) {
                            Object a2 = (pairArr2 == null || pairArr2.length > 0) ? null : MiotSpecCardManager.this.a(str2, (Pair<SpecService, ? extends Spec.SpecItem>) pairArr2[0]);
                            if (a2 == null || a2.equals(a2)) {
                                MiotSpecCardManager.this.m.postDelayed(
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0044: INVOKE  
                                      (wrap: android.os.Handler : 0x0033: INVOKE  (r4v6 android.os.Handler) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager : 0x0031: IGET  (r4v5 com.xiaomi.smarthome.newui.card.MiotSpecCardManager) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 : 0x002f: IGET  (r4v4 com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.b com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.f com.xiaomi.smarthome.newui.card.MiotSpecCardManager)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.a(com.xiaomi.smarthome.newui.card.MiotSpecCardManager):android.os.Handler type: STATIC)
                                      (wrap: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk : 0x003f: CONSTRUCTOR  (r2v0 com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                      (wrap: java.lang.String : 0x0039: IGET  (r0v1 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 : 0x0037: IGET  (r0v0 com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.b com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.b java.lang.String)
                                      (wrap: java.util.ArrayList : 0x003b: IGET  (r1v0 java.util.ArrayList) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.a java.util.ArrayList)
                                     call: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk.<init>(com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1, java.lang.String, java.util.ArrayList):void type: CONSTRUCTOR)
                                      (1000 long)
                                     android.os.Handler.postDelayed(java.lang.Runnable, long):boolean type: VIRTUAL in method: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.onSuccess(java.lang.Object):void, dex: classes9.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003f: CONSTRUCTOR  (r2v0 com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                      (wrap: java.lang.String : 0x0039: IGET  (r0v1 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 : 0x0037: IGET  (r0v0 com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.b com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.b java.lang.String)
                                      (wrap: java.util.ArrayList : 0x003b: IGET  (r1v0 java.util.ArrayList) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.a java.util.ArrayList)
                                     call: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk.<init>(com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1, java.lang.String, java.util.ArrayList):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.onSuccess(java.lang.Object):void, dex: classes9.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	... 105 more
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 111 more
                                    */
                                /*
                                    this = this;
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    android.util.Pair[] r4 = r5
                                    if (r4 == 0) goto L_0x0022
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    android.util.Pair[] r4 = r5
                                    int r4 = r4.length
                                    if (r4 <= 0) goto L_0x000e
                                    goto L_0x0022
                                L_0x000e:
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.this
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r0 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    java.lang.String r0 = r3
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r1 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    android.util.Pair[] r1 = r5
                                    r2 = 0
                                    r1 = r1[r2]
                                    java.lang.Object r4 = r4.a((java.lang.String) r0, (android.util.Pair<com.xiaomi.smarthome.device.api.spec.instance.SpecService, ? extends com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem>) r1)
                                    goto L_0x0023
                                L_0x0022:
                                    r4 = 0
                                L_0x0023:
                                    if (r4 == 0) goto L_0x002f
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r0 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    java.lang.Object r0 = r6
                                    boolean r4 = r4.equals(r0)
                                    if (r4 == 0) goto L_0x0047
                                L_0x002f:
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.this
                                    android.os.Handler r4 = r4.m
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r0 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    java.lang.String r0 = r3
                                    java.util.ArrayList r1 = r8
                                    com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk r2 = new com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$WdiB3DJDJDGjiY652qmA0ty8UKk
                                    r2.<init>(r3, r0, r1)
                                    r0 = 1000(0x3e8, double:4.94E-321)
                                    r4.postDelayed(r2, r0)
                                L_0x0047:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.AnonymousClass1.onSuccess(java.lang.Object):void");
                            }

                            /* access modifiers changed from: private */
                            public /* synthetic */ void b(String str, ArrayList arrayList) {
                                MiotSpecCardManager.this.a(str, arrayList, (AsyncCallback<Object, Error>) null);
                            }

                            /* access modifiers changed from: private */
                            public /* synthetic */ void a(String str, ArrayList arrayList) {
                                MiotSpecCardManager.this.a(str, arrayList, (AsyncCallback<Object, Error>) null);
                            }

                            public void onFailure(Error error) {
                                MiotSpecCardManager.this.m.postDelayed(
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: INVOKE  
                                      (wrap: android.os.Handler : 0x0004: INVOKE  (r4v3 android.os.Handler) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager : 0x0002: IGET  (r4v2 com.xiaomi.smarthome.newui.card.MiotSpecCardManager) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 : 0x0000: IGET  (r4v1 com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.b com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.f com.xiaomi.smarthome.newui.card.MiotSpecCardManager)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.a(com.xiaomi.smarthome.newui.card.MiotSpecCardManager):android.os.Handler type: STATIC)
                                      (wrap: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA : 0x0010: CONSTRUCTOR  (r2v0 com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                      (wrap: java.lang.String : 0x000a: IGET  (r0v1 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 : 0x0008: IGET  (r0v0 com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.b com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.b java.lang.String)
                                      (wrap: java.util.ArrayList : 0x000c: IGET  (r1v0 java.util.ArrayList) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.a java.util.ArrayList)
                                     call: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA.<init>(com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1, java.lang.String, java.util.ArrayList):void type: CONSTRUCTOR)
                                      (1000 long)
                                     android.os.Handler.postDelayed(java.lang.Runnable, long):boolean type: VIRTUAL in method: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.onFailure(com.xiaomi.smarthome.frame.Error):void, dex: classes9.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0010: CONSTRUCTOR  (r2v0 com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                      (wrap: java.lang.String : 0x000a: IGET  (r0v1 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 : 0x0008: IGET  (r0v0 com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.b com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10)
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.b java.lang.String)
                                      (wrap: java.util.ArrayList : 0x000c: IGET  (r1v0 java.util.ArrayList) = 
                                      (r3v0 'this' com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1 A[THIS])
                                     com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.a java.util.ArrayList)
                                     call: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA.<init>(com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10$1, java.lang.String, java.util.ArrayList):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.10.1.onFailure(com.xiaomi.smarthome.frame.Error):void, dex: classes9.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	... 98 more
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 104 more
                                    */
                                /*
                                    this = this;
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager r4 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.this
                                    android.os.Handler r4 = r4.m
                                    com.xiaomi.smarthome.newui.card.MiotSpecCardManager$10 r0 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.this
                                    java.lang.String r0 = r3
                                    java.util.ArrayList r1 = r8
                                    com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA r2 = new com.xiaomi.smarthome.newui.card.-$$Lambda$MiotSpecCardManager$10$1$4WvEyYXtK8-EHL4aIGEQjN6_pDA
                                    r2.<init>(r3, r0, r1)
                                    r0 = 1000(0x3e8, double:4.94E-321)
                                    r4.postDelayed(r2, r0)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.MiotSpecCardManager.AnonymousClass10.AnonymousClass1.onFailure(com.xiaomi.smarthome.frame.Error):void");
                            }
                        });
                    } else if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(null);
                    }
                }

                public void onFailure(Error error) {
                    LogUtil.b("mijia-card", "setDeviceSpecAction onFailure" + error);
                    if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(error);
                    }
                }
            });
        }

        public Map<String, String> e(String str) {
            return this.i.get(a(str));
        }

        public SpecUnit f(String str) {
            return this.j.get(a(str));
        }

        public List<PropItem.PropExtraItem> g(String str) {
            return this.k.get(a(str));
        }

        public Object a(String str, Pair<SpecService, ? extends Spec.SpecItem> pair) {
            Map<String, Object> b = b(str);
            if (b == null || pair == null || pair.first == null || pair.second == null) {
                return null;
            }
            return b.get(SpecUtils.a((SpecService) pair.first, (Spec.SpecItem) pair.second));
        }

        public String h(String str) {
            SpecDevice a2 = a(SmartHomeDeviceManager.a().b(str));
            if (a2 == null) {
                return "";
            }
            return SpecDeviceCodec.a(a2).toString();
        }

        public String i(String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", 0);
                jSONObject.put("message", "");
            } catch (JSONException e2) {
                LogUtil.b("miot-spec", e2.toString());
            }
            Map<String, Object> b = b(str);
            if (b == null) {
                try {
                    jSONObject.put("result", "");
                } catch (JSONException e3) {
                    LogUtil.b("miot-spec", e3.toString());
                }
                return jSONObject.toString();
            }
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : b.entrySet()) {
                String str2 = (String) next.getKey();
                Object value = next.getValue();
                if (!TextUtils.isEmpty(str2)) {
                    String[] split = str2.split("\\.");
                    if (split.length == 2) {
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put("did", str);
                            jSONObject2.put(DeviceTagInterface.e, split[0]);
                            jSONObject2.put("ppid", split[1]);
                            jSONObject2.put("code", value);
                            arrayList.add(jSONObject2);
                        } catch (JSONException e4) {
                            LogUtil.b("miot-spec", e4.toString());
                        }
                    }
                }
            }
            try {
                jSONObject.put("result", arrayList);
            } catch (JSONException e5) {
                LogUtil.b("miot-spec", e5.toString());
            }
            return jSONObject.toString();
        }

        public Pair<SpecService, SpecProperty> e(Device device) {
            SpecDevice a2 = a(device);
            if (a2 == null || UserAvatarUpdateActivity.CAMERA.equals(a2.getTypeName())) {
                return null;
            }
            int i2 = 0;
            Pair<SpecService, SpecProperty> pair = null;
            for (SpecService next : a2.getServices().values()) {
                for (SpecProperty next2 : next.getProperties().values()) {
                    if (next2.getPropertyDefinition().getTypeName().equals("on")) {
                        i2++;
                        pair = new Pair<>(next, next2);
                    }
                }
            }
            if (i2 == 1) {
                return pair;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public File c() {
            if (!CoreApi.a().q()) {
                return null;
            }
            File file = new File(FileUtils.a(CommonApplication.getAppContext()) + File.separator + CoreApi.a().s() + "_control_props_spec");
            if (!file.exists()) {
                file.getParentFile().mkdir();
            }
            return file;
        }

        public void h() {
            if (CommonApplication.getAppContext() != null && !CardActive.instance.isActive()) {
                this.m.post(new Runnable() {
                    public final void run() {
                        MiotSpecCardManager.this.b();
                    }
                });
                if (this.o != null) {
                    this.o.a();
                    this.o = null;
                }
            }
        }
    }
