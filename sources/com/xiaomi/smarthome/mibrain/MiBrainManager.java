package com.xiaomi.smarthome.mibrain;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.AuthCode;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.mibrain.model.AiDeviceTipsInfo;
import com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo;
import com.xiaomi.smarthome.mibrain.model.aitips.AiDevice;
import com.xiaomi.smarthome.mibrain.model.aitips.AiTipsDevice;
import com.xiaomi.smarthome.mibrain.model.aitips.Tips;
import com.xiaomi.smarthome.mibrain.viewutil.floatview.MiBrainFloatManager;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miuipub.content.ExtraIntent;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiBrainManager {
    private static volatile MiBrainManager d = null;
    private static final String e = "MiBrainManager";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<MiBrainConfigInfo> f10562a = new ArrayList();
    /* access modifiers changed from: private */
    public List<MiBrainConfigInfo> b = new ArrayList();
    /* access modifiers changed from: private */
    public boolean c = false;
    private MiBrainFloatManager f;
    private SoundPool g;
    private int h = -1;
    private boolean i = true;
    /* access modifiers changed from: private */
    public Set<String> j = new HashSet();
    /* access modifiers changed from: private */
    public Map<String, AiDeviceTipsInfo> k = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Set<String> l = new HashSet();

    public static MiBrainManager a() {
        if (d == null) {
            synchronized (MiBrainManager.class) {
                if (d == null) {
                    d = new MiBrainManager();
                }
            }
        }
        return d;
    }

    public List<MiBrainConfigInfo> b() {
        return this.f10562a;
    }

    public MiBrainFloatManager a(Activity activity) {
        if (d.f == null || d.f.a() == null || d.f.a().isFinishing()) {
            d.f = new MiBrainFloatManager(activity);
        }
        return this.f;
    }

    public void c() {
        if (this.f != null) {
            this.f.d();
        }
        d = null;
    }

    public boolean d() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void e() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
            jSONObject.put("name", "android_common_config");
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Request request = null;
        try {
            request = new Request.Builder().a("GET").b(a(jSONObject)).a();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (request != null) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processFailure(Call call, IOException iOException) {
                }

                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: org.json.JSONObject} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void processResponse(okhttp3.Response r3) {
                    /*
                        r2 = this;
                        okhttp3.ResponseBody r3 = r3.body()     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.lang.String r3 = r3.string()     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        r0.<init>(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.lang.String r3 = "result"
                        boolean r3 = r0.isNull(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r3 == 0) goto L_0x0016
                        return
                    L_0x0016:
                        java.lang.String r3 = "result"
                        org.json.JSONObject r3 = r0.optJSONObject(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.lang.String r0 = "content"
                        boolean r0 = r3.isNull(r0)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r0 == 0) goto L_0x0025
                        return
                    L_0x0025:
                        r0 = 0
                        java.lang.String r1 = "content"
                        java.lang.Object r3 = r3.get(r1)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        boolean r1 = r3 instanceof org.json.JSONObject     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r1 == 0) goto L_0x0034
                        r0 = r3
                        org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        goto L_0x003f
                    L_0x0034:
                        boolean r1 = r3 instanceof java.lang.String     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r1 == 0) goto L_0x003f
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        r0.<init>(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                    L_0x003f:
                        java.lang.String r3 = "android_audio_brain_config"
                        boolean r3 = r0.isNull(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r3 != 0) goto L_0x0058
                        java.lang.String r3 = "android_audio_brain_config"
                        org.json.JSONArray r3 = r0.optJSONArray(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r3 == 0) goto L_0x0058
                        com.xiaomi.smarthome.mibrain.MiBrainManager r1 = com.xiaomi.smarthome.mibrain.MiBrainManager.this     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.util.List r3 = com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo.a(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.util.List unused = r1.f10562a = r3     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                    L_0x0058:
                        java.lang.String r3 = "android_audio_brain_setting_config"
                        boolean r3 = r0.isNull(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r3 != 0) goto L_0x007b
                        java.lang.String r3 = "android_audio_brain_setting_config"
                        org.json.JSONArray r3 = r0.optJSONArray(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        if (r3 == 0) goto L_0x007b
                        com.xiaomi.smarthome.mibrain.MiBrainManager r0 = com.xiaomi.smarthome.mibrain.MiBrainManager.this     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.util.List r3 = com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo.a(r3)     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        java.util.List unused = r0.b = r3     // Catch:{ IOException -> 0x0077, JSONException -> 0x0072 }
                        goto L_0x007b
                    L_0x0072:
                        r3 = move-exception
                        r3.printStackTrace()
                        goto L_0x007b
                    L_0x0077:
                        r3 = move-exception
                        r3.printStackTrace()
                    L_0x007b:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.mibrain.MiBrainManager.AnonymousClass1.processResponse(okhttp3.Response):void");
                }
            });
        }
    }

    @NonNull
    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public void a(final boolean z, final AsyncCallback asyncCallback) {
        UserConfig userConfig = new UserConfig();
        userConfig.B = 0;
        userConfig.C = "8";
        userConfig.D = new ArrayList<>();
        ArrayList<UserConfig.UserConfigAttr> arrayList = userConfig.D;
        arrayList.add(new UserConfig.UserConfigAttr("value", z + ""));
        UserConfigApi.a().a(SHApplication.getAppContext(), userConfig, (AsyncCallback<Void, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<Void, com.xiaomi.smarthome.frame.Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
                boolean unused = MiBrainManager.this.c = z;
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void a(final AsyncCallback asyncCallback) {
        UserConfig userConfig = new UserConfig();
        userConfig.B = 0;
        userConfig.C = "8";
        UserConfigApi.a().a(SHApplication.getAppContext(), 0, new String[]{"8"}, (AsyncCallback<ArrayList<UserConfig>, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<ArrayList<UserConfig>, com.xiaomi.smarthome.frame.Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<UserConfig> arrayList) {
                UserConfig userConfig;
                String str;
                if (arrayList != null && arrayList.size() > 0 && (userConfig = arrayList.get(0)) != null && userConfig.D != null && userConfig.D.size() > 0 && (str = userConfig.D.get(0).b) != null) {
                    if (str.equalsIgnoreCase("true")) {
                        boolean unused = MiBrainManager.this.c = true;
                    } else {
                        boolean unused2 = MiBrainManager.this.c = false;
                    }
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(Boolean.valueOf(MiBrainManager.this.c));
                    }
                }
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public boolean f() {
        return this.c;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public boolean g() {
        if (!CoreApi.a().q() || CoreApi.a().D()) {
            return false;
        }
        if (!h() && !this.c) {
            return false;
        }
        return true;
    }

    public boolean h() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 == null || d2.size() == 0) {
            return false;
        }
        this.b = AndroidCommonConfigManager.a().c();
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            MiBrainConfigInfo miBrainConfigInfo = this.b.get(i2);
            if (miBrainConfigInfo != null && !TextUtils.isEmpty(miBrainConfigInfo.b)) {
                for (int i3 = 0; i3 < d2.size(); i3++) {
                    Device device = d2.get(i3);
                    if (device != null && !TextUtils.isEmpty(device.model) && device.model.equalsIgnoreCase(miBrainConfigInfo.b)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public void i() {
        if (this.g == null) {
            if (Build.VERSION.SDK_INT >= 21) {
                this.g = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).build();
            } else {
                this.g = new SoundPool(1, 3, 10);
            }
            this.h = this.g.load(SHApplication.getAppContext(), R.raw.mi_brain_music, 1);
        }
        if (this.h == -1) {
            this.h = this.g.load(SHApplication.getAppContext(), R.raw.mi_brain_music, 1);
        }
        this.g.play(this.h, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public void j() {
        if (this.g == null) {
            if (Build.VERSION.SDK_INT >= 21) {
                this.g = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).build();
            } else {
                this.g = new SoundPool(1, 3, 10);
            }
            this.h = this.g.load(SHApplication.getAppContext(), R.raw.mi_brain_music, 1);
        }
        if (this.h == -1) {
            this.h = this.g.load(SHApplication.getAppContext(), R.raw.mi_brain_music, 1);
        }
    }

    public String a(int i2) {
        switch (i2) {
            case -120:
            case AuthCode.n /*-113*/:
            case AuthCode.m /*-112*/:
            case AuthCode.p /*-111*/:
            case -108:
            case -102:
            case -101:
                return SHApplication.getAppContext().getResources().getString(R.string.mi_brain_asr_net_can_not_use);
            case -107:
            case -106:
                return SHApplication.getAppContext().getResources().getString(R.string.mi_brain_asr_not_hear);
            default:
                return SHApplication.getAppContext().getResources().getString(R.string.mi_brain_asr_sorry_record_fail);
        }
    }

    public String b(int i2) {
        if (i2 != 1001) {
            return i2 != 1005 ? SHApplication.getAppContext().getResources().getString(R.string.mi_brain_nlp_not_hear) : "抱歉，暂不支持设备";
        }
        return "抱歉，我还没有这项技能";
    }

    public void k() {
        MiBrainManager miBrainManager = d;
        this.j.clear();
        this.k.clear();
        d = new MiBrainManager();
        d.i = miBrainManager.i;
        d.j();
    }

    public boolean a(String str) {
        Device b2;
        AiDeviceTipsInfo aiDeviceTipsInfo;
        AiTipsDevice b3;
        List<Tips> h2;
        List<String> b4;
        if (HomeManager.A() || (b2 = SmartHomeDeviceManager.a().b(str)) == null || (aiDeviceTipsInfo = this.k.get(b2.model)) == null || (b3 = aiDeviceTipsInfo.b()) == null || (h2 = b3.h()) == null || h2.isEmpty() || (b4 = h2.get(0).b()) == null || b4.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<String> b(String str) {
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 == null) {
            return new ArrayList();
        }
        return a(this.k.get(b2.model));
    }

    public String c(String str) {
        AiDeviceTipsInfo aiDeviceTipsInfo;
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 == null || (aiDeviceTipsInfo = this.k.get(b2.model)) == null) {
            return null;
        }
        return aiDeviceTipsInfo.c();
    }

    public List<AiDevice> d(String str) {
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 == null) {
            return new ArrayList();
        }
        AiDeviceTipsInfo aiDeviceTipsInfo = this.k.get(b2.model);
        if (aiDeviceTipsInfo == null || aiDeviceTipsInfo.a() == null) {
            return new ArrayList();
        }
        return aiDeviceTipsInfo.a();
    }

    private List<String> a(AiDeviceTipsInfo aiDeviceTipsInfo) {
        List<AiDevice> a2;
        List<Tips> h2;
        ArrayList arrayList = new ArrayList();
        if (aiDeviceTipsInfo == null || (a2 = aiDeviceTipsInfo.a()) == null || a2.isEmpty()) {
            return arrayList;
        }
        for (AiDevice c2 : a2) {
            AiTipsDevice c3 = c2.c();
            if (!(c3 == null || (h2 = c3.h()) == null)) {
                for (Tips b2 : h2) {
                    List<String> b3 = b2.b();
                    if (b3 != null) {
                        arrayList.addAll(b3);
                    }
                }
            }
        }
        return arrayList;
    }

    public void a(final String str, boolean z, final AsyncCallback asyncCallback) {
        if (HomeManager.A()) {
            if (asyncCallback != null) {
                asyncCallback.onSuccess(new Object());
            }
        } else if (!this.l.contains(str) || z) {
            final Device b2 = SmartHomeDeviceManager.a().b(str);
            if (b2 != null) {
                try {
                    XiaoAiGoodRecommendApi.a().b();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str);
                    a((List<String>) arrayList).flatMap(new Function<Set<String>, ObservableSource<AiDeviceTipsInfo>>() {
                        /* renamed from: a */
                        public ObservableSource<AiDeviceTipsInfo> apply(Set<String> set) throws Exception {
                            Set unused = MiBrainManager.this.j = set;
                            if (!set.contains(str)) {
                                return Observable.empty();
                            }
                            return MiBrainManager.this.f(str);
                        }
                    }).map(new Function<AiDeviceTipsInfo, Object>() {
                        /* renamed from: a */
                        public Object apply(AiDeviceTipsInfo aiDeviceTipsInfo) throws Exception {
                            MiBrainManager.this.l.add(str);
                            MiBrainManager.this.k.put(b2.model, aiDeviceTipsInfo);
                            return new Object();
                        }
                    }).subscribe(new Observer<Object>() {
                        public void onNext(Object obj) {
                        }

                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onError(Throwable th) {
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(new com.xiaomi.smarthome.frame.Error(-1, th.getMessage()));
                            }
                        }

                        public void onComplete() {
                            if (asyncCallback != null) {
                                asyncCallback.onSuccess(null);
                            }
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(new com.xiaomi.smarthome.frame.Error(-1, e2.getMessage()));
                    }
                }
            } else if (asyncCallback != null) {
                asyncCallback.onSuccess(new Object());
            }
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess(new Object());
        }
    }

    private Observable<Set<String>> a(final List<String> list) {
        if (SHApplication.getStateNotifier().a() != 4) {
            return Observable.empty();
        }
        return Observable.create(new ObservableOnSubscribe<Set<String>>() {
            public void subscribe(final ObservableEmitter<Set<String>> observableEmitter) throws Exception {
                RemoteFamilyApi.a().f((List<String>) list, (AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject == null) {
                            observableEmitter.onComplete();
                            return;
                        }
                        try {
                            Object obj = jSONObject.get("list");
                            if (obj != null) {
                                if (obj instanceof JSONArray) {
                                    HashSet hashSet = new HashSet();
                                    for (int i = 0; i < ((JSONArray) obj).length(); i++) {
                                        Object obj2 = ((JSONArray) obj).get(i);
                                        if (obj2 != null) {
                                            if (obj2 instanceof JSONObject) {
                                                Object obj3 = ((JSONObject) obj2).get("did");
                                                if (obj3 != null) {
                                                    if (obj3 instanceof String) {
                                                        hashSet.add((String) obj3);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    observableEmitter.onNext(hashSet);
                                    observableEmitter.onComplete();
                                    observableEmitter.onComplete();
                                    return;
                                }
                            }
                            observableEmitter.onComplete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                        LogUtilGrey.a(MiBrainManager.e, "updateCtrlDid error:" + error);
                        observableEmitter.onComplete();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public List<String> e(String str) {
        ArrayList arrayList = new ArrayList();
        List<Device> r = HomeManager.a().r();
        if (r == null || r.isEmpty()) {
            return arrayList;
        }
        for (int i2 = 0; i2 < r.size(); i2++) {
            Device device = r.get(i2);
            if (device != null && device.voiceCtrl == 2) {
                arrayList.add(device.did);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public Observable<AiDeviceTipsInfo> f(final String str) {
        if (SHApplication.getStateNotifier().a() != 4) {
            return Observable.empty();
        }
        return Observable.just(str).flatMap(new Function<String, ObservableSource<AiDeviceTipsInfo>>() {
            /* renamed from: a */
            public ObservableSource<AiDeviceTipsInfo> apply(final String str) throws Exception {
                return Observable.create(new ObservableOnSubscribe<AiDeviceTipsInfo>() {
                    public void subscribe(final ObservableEmitter<AiDeviceTipsInfo> observableEmitter) throws Exception {
                        RemoteFamilyApi.a().a(str, (List<String>) MiBrainManager.this.e(str), (AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                observableEmitter.onNext(MiBrainManager.this.b(jSONObject));
                                observableEmitter.onComplete();
                            }

                            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                                LogUtilGrey.a(MiBrainManager.e, "updateMiBrainTips error:" + error);
                                observableEmitter.onComplete();
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public AiDeviceTipsInfo b(JSONObject jSONObject) {
        return AiDeviceTipsInfo.a(jSONObject);
    }

    private List<String> c(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            Object obj = jSONObject.get("device");
            if (obj != null) {
                if (obj instanceof JSONObject) {
                    Object obj2 = ((JSONObject) obj).get("tips");
                    if (obj2 != null) {
                        if (obj2 instanceof JSONArray) {
                            for (int i2 = 0; i2 < ((JSONArray) obj2).length(); i2++) {
                                Object obj3 = ((JSONArray) obj2).get(i2);
                                if (obj3 != null) {
                                    if (obj3 instanceof JSONObject) {
                                        Object obj4 = ((JSONObject) obj3).get("contents");
                                        if (obj4 == null) {
                                            continue;
                                        } else if (obj4 instanceof JSONArray) {
                                            int i3 = 0;
                                            while (true) {
                                                if (i3 >= ((JSONArray) obj4).length()) {
                                                    break;
                                                }
                                                Object obj5 = ((JSONArray) obj4).get(i3);
                                                if (obj5 != null) {
                                                    if (obj5 instanceof String) {
                                                        arrayList.add((String) obj5);
                                                        if (arrayList.size() >= 3) {
                                                            break;
                                                        }
                                                    }
                                                }
                                                i3++;
                                            }
                                            if (arrayList.size() >= 3) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            return arrayList;
                        }
                    }
                    return arrayList;
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean l() {
        if (HomeManager.A() || !SystemApi.c()) {
            return false;
        }
        try {
            String b2 = AccountManagerUtil.b(SHApplication.getApplication());
            if (!TextUtils.isEmpty(b2) && TextUtils.equals(b2, CoreApi.a().s()) && ((long) SHApplication.getAppContext().getPackageManager().getPackageInfo("com.miui.voiceassist", 0).versionCode) > 300000000) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static void m() {
        Intent intent = new Intent(ExtraIntent.J);
        intent.putExtra("voice_assist_start_from_key", "miotapp");
        intent.setClassName("com.miui.voiceassist", "com.xiaomi.voiceassistant.VoiceService");
        if (Build.VERSION.SDK_INT >= 26) {
            SHApplication.getAppContext().startForegroundService(intent);
        } else {
            SHApplication.getAppContext().startService(intent);
        }
    }
}
