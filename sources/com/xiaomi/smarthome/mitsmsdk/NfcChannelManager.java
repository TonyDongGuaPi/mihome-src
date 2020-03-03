package com.xiaomi.smarthome.mitsmsdk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.xm.bt.profile.nfc.ApduResponse;
import cn.com.xm.bt.profile.nfc.HMAccessInfo;
import cn.com.xm.bt.profile.nfc.HMAidInfo;
import cn.com.xm.bt.sdk.HMBleDevice;
import com.coloros.mcssdk.c.a;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.miui.tsmclient.Facade;
import com.miui.tsmclient.entity.CardConfigManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.EnvironmentConfig;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.terminal.TerminalManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.BluetoothStateHelper;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.setting.LoginConstant;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class NfcChannelManager {
    /* access modifiers changed from: private */
    public static List<CardInfo> F = new ArrayList();
    private static List<String> J = new ArrayList();
    private static final String O = "sp_nfc_band4";
    private static final String P = "band_cloud_signature";
    private static final String Q = "band_local_signature";
    /* access modifiers changed from: private */
    public static List<String> R = Arrays.asList(new String[]{"XMSH06HM"});

    /* renamed from: a  reason: collision with root package name */
    public static final int f20060a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 0;
    public static final int f = -1;
    public static final int g = -2;
    public static final int h = -3;
    public static final int i = -4;
    public static final int j = -5;
    public static final int k = -6;
    public static final int l = -7;
    public static final int m = -8;
    public static final int n = -9;
    public static final int o = -10;
    public static final int p = 5;
    private static final String r = "NfcChannelManager";
    private static NfcChannelManager s;
    private static Object t = new Object();
    /* access modifiers changed from: private */
    public static boolean z;
    /* access modifiers changed from: private */
    public boolean A;
    /* access modifiers changed from: private */
    public boolean B;
    /* access modifiers changed from: private */
    public HMBleDevice C;
    /* access modifiers changed from: private */
    public String D = "";
    private CompositeSubscription E;
    /* access modifiers changed from: private */
    public CountDownLatch G;
    /* access modifiers changed from: private */
    public Facade H;
    /* access modifiers changed from: private */
    public boolean I;
    /* access modifiers changed from: private */
    public int K;
    /* access modifiers changed from: private */
    public int L = 3;
    /* access modifiers changed from: private */
    public int M;
    /* access modifiers changed from: private */
    public int N;
    boolean q;
    /* access modifiers changed from: private */
    public Context u;
    /* access modifiers changed from: private */
    public String v;
    private String w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public String y;

    static /* synthetic */ int j(NfcChannelManager nfcChannelManager) {
        int i2 = nfcChannelManager.M;
        nfcChannelManager.M = i2 + 1;
        return i2;
    }

    static /* synthetic */ int p(NfcChannelManager nfcChannelManager) {
        int i2 = nfcChannelManager.N;
        nfcChannelManager.N = i2 + 1;
        return i2;
    }

    static {
        J.add("lumi.lock.mcn01");
        J.add("loock.lock.v5");
    }

    public static NfcChannelManager a() {
        if (s == null) {
            synchronized (t) {
                if (s == null) {
                    s = new NfcChannelManager();
                }
            }
        }
        return s;
    }

    private NfcChannelManager() {
    }

    /* access modifiers changed from: private */
    public boolean j() {
        return "XMSH06HM".equals(this.v);
    }

    public void a(Context context, String str, String str2, @NonNull Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager init");
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            callback.onFailure(-1, "init meets params error!");
            return;
        }
        this.u = context;
        this.H = Facade.getFacade(this.u);
        this.L = 3;
        this.K = -100;
        this.v = str;
        this.w = str2;
        this.E = new CompositeSubscription();
        EnvironmentConfig.initialize(SHApplication.getAppContext());
        TerminalManager.getInstance().init(SHApplication.getAppContext());
        CardConfigManager.getInstance().init(SHApplication.getAppContext());
        this.y = CoreApi.a().s();
        this.B = false;
        k();
        MiServiceTokenInfo a2 = CoreApi.a().a(LoginConstant.n);
        if (a2 != null) {
            this.x = a2.c + "," + a2.d;
            callback.onSuccess(true);
            return;
        }
        a(callback);
    }

    private void k() {
        for (Device device : SmartHomeDeviceManager.a().d()) {
            if (J.contains(device.model)) {
                this.I = true;
            }
        }
    }

    public void a(@NonNull final Callback<Boolean> callback) {
        StoreApiProvider b2 = StoreApiManager.a().b();
        if (b2 != null) {
            b2.a(LoginConstant.n, (StoreBaseCallback<com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo>) new StoreBaseCallback<com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo>() {
                /* renamed from: a */
                public void onSuccess(com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo miServiceTokenInfo) {
                    if (miServiceTokenInfo == null) {
                        LogUtils.e("get no serviceToken!");
                        callback.onFailure(-2, "get no serviceToken!");
                        return;
                    }
                    NfcChannelManager nfcChannelManager = NfcChannelManager.this;
                    String unused = nfcChannelManager.x = miServiceTokenInfo.c + "," + miServiceTokenInfo.d;
                    MiServiceTokenInfo miServiceTokenInfo2 = new MiServiceTokenInfo();
                    miServiceTokenInfo2.f = miServiceTokenInfo.f;
                    miServiceTokenInfo2.c = miServiceTokenInfo.c;
                    miServiceTokenInfo2.f23514a = miServiceTokenInfo.f23799a;
                    miServiceTokenInfo2.d = miServiceTokenInfo.d;
                    miServiceTokenInfo2.e = miServiceTokenInfo.e;
                    CoreApi.a().y().a(miServiceTokenInfo2);
                    callback.onSuccess(true);
                }

                public void onFail(int i, String str) {
                    LogUtils.e("refresh servicetoken failed!");
                    callback.onFailure(-2, "get SID_TSM_AUTH serviceToken failed!");
                }
            });
            return;
        }
        LogUtils.e("refresh servicetoken failed as provider is null!");
        callback.onFailure(-2, "get SID_TSM_AUTH serviceToken failed!");
    }

    public void a(final String str, final Callback<Integer> callback) {
        LogUtils.d("NfcChannelManager connectBracelet");
        if (this.u == null || TextUtils.isEmpty(str)) {
            callback.onFailure(-1, "connectBracelet meets params error!");
            return;
        }
        if (this.C != null) {
            try {
                this.C.disconnect();
            } catch (Exception unused) {
            }
        }
        this.G = new CountDownLatch(1);
        HMBleDevice.enableLog(false);
        this.A = false;
        this.B = false;
        this.K = -100;
        this.D = SharePrefsManager.c(SHApplication.getAppContext(), O, P, "");
        LogUtils.d("HMBleDevice CloudSignature = " + this.D);
        if (TextUtils.isEmpty(this.D)) {
            LogUtils.d("need bind!");
            this.A = true;
        }
        if (!BluetoothUtils.b()) {
            BluetoothStateHelper.a((BleResponse) new BleResponse() {
                public void a(int i, Object obj) {
                    if (i == 0) {
                        NfcChannelManager.this.a(NfcChannelManager.this.A, str, (Callback<Integer>) callback);
                    }
                }
            });
        } else {
            a(this.A, str, callback);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (R.contains(this.v)) {
            SharePrefsManager.a(SHApplication.getAppContext(), O, Q, str);
        } else {
            SharePrefsManager.a(SHApplication.getAppContext(), O, P, str);
        }
    }

    /* access modifiers changed from: private */
    public void a(final boolean z2, final String str, final Callback<Integer> callback) {
        LogUtils.d("NfcChannelManager connect");
        this.E.add(Observable.fromCallable(new Callable<Boolean>() {
            /* JADX WARNING: Can't wrap try/catch for region: R(5:20|(2:22|23)|24|25|26) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00fe */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Boolean call() throws java.lang.Exception {
                /*
                    r7 = this;
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r0 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r1 = new cn.com.xm.bt.sdk.HMBleDevice
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r2 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    android.content.Context r2 = r2.u
                    java.lang.String r3 = r3
                    r1.<init>(r2, r3)
                    cn.com.xm.bt.sdk.HMBleDevice unused = r0.C = r1
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r0 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r0 = r0.C
                    boolean r1 = r2
                    r0.setPair(r1)
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r0 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r0 = r0.C
                    r1 = 0
                    r0.setAutoConnect(r1)
                    java.util.List r0 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.R
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r2 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    java.lang.String r2 = r2.v
                    boolean r0 = r0.contains(r2)
                    r2 = 1
                    if (r0 == 0) goto L_0x0087
                    android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r3 = "sp_nfc_band4"
                    java.lang.String r4 = "band_local_signature"
                    java.lang.String r5 = ""
                    java.lang.String r0 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.c(r0, r3, r4, r5)
                    boolean r3 = android.text.TextUtils.isEmpty(r0)
                    if (r3 == 0) goto L_0x005e
                    java.util.UUID r0 = java.util.UUID.randomUUID()
                    java.lang.String r0 = r0.toString()
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r3 = r3.C
                    r3.setPair(r2)
                    goto L_0x0067
                L_0x005e:
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r3 = r3.C
                    r3.setPair(r1)
                L_0x0067:
                    java.lang.String r3 = "NfcChannelManager"
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "HMBleDevice read local  key="
                    r4.append(r5)
                    r4.append(r0)
                    java.lang.String r4 = r4.toString()
                    com.miui.tsmclient.util.LogUtils.d(r3, r4)
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r3 = r3.C
                    r3.setKey(r0)
                    goto L_0x009a
                L_0x0087:
                    boolean r0 = r2
                    if (r0 != 0) goto L_0x009a
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r0 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r0 = r0.C
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    java.lang.String r3 = r3.D
                    r0.setKey(r3)
                L_0x009a:
                    boolean unused = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.z = r2
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r0 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this
                    cn.com.xm.bt.sdk.HMBleDevice r0 = r0.C
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager$4$1 r3 = new com.xiaomi.smarthome.mitsmsdk.NfcChannelManager$4$1
                    r3.<init>()
                    r0.connect(r3)
                    r0 = -7
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this     // Catch:{ Exception -> 0x010a }
                    java.util.concurrent.CountDownLatch r3 = r3.G     // Catch:{ Exception -> 0x010a }
                    r4 = 120(0x78, double:5.93E-322)
                    java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x010a }
                    r3.await(r4, r6)     // Catch:{ Exception -> 0x010a }
                    java.lang.String r3 = "countDownLatch open!"
                    com.miui.tsmclient.util.LogUtils.e(r3)     // Catch:{ Exception -> 0x010a }
                    boolean unused = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.z = r1     // Catch:{ Exception -> 0x010a }
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this     // Catch:{ Exception -> 0x010a }
                    int r3 = r3.K     // Catch:{ Exception -> 0x010a }
                    r4 = 4
                    if (r3 != r4) goto L_0x00d7
                    com.xiaomi.smarthome.device.api.Callback r2 = r4     // Catch:{ Exception -> 0x010a }
                    r3 = 5
                    java.lang.String r4 = "auth failed!"
                    r2.onFailure(r3, r4)     // Catch:{ Exception -> 0x010a }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x010a }
                    return r2
                L_0x00d7:
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r3 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this     // Catch:{ Exception -> 0x010a }
                    boolean r3 = r3.B     // Catch:{ Exception -> 0x010a }
                    if (r3 == 0) goto L_0x00ed
                    com.xiaomi.smarthome.device.api.Callback r3 = r4     // Catch:{ Exception -> 0x010a }
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x010a }
                    r3.onSuccess(r4)     // Catch:{ Exception -> 0x010a }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x010a }
                    return r2
                L_0x00ed:
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r2 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this     // Catch:{ Exception -> 0x010a }
                    cn.com.xm.bt.sdk.HMBleDevice r2 = r2.C     // Catch:{ Exception -> 0x010a }
                    if (r2 == 0) goto L_0x00fe
                    com.xiaomi.smarthome.mitsmsdk.NfcChannelManager r2 = com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.this     // Catch:{ Exception -> 0x00fe }
                    cn.com.xm.bt.sdk.HMBleDevice r2 = r2.C     // Catch:{ Exception -> 0x00fe }
                    r2.disconnect()     // Catch:{ Exception -> 0x00fe }
                L_0x00fe:
                    com.xiaomi.smarthome.device.api.Callback r2 = r4     // Catch:{ Exception -> 0x010a }
                    java.lang.String r3 = "connect failed!"
                    r2.onFailure(r0, r3)     // Catch:{ Exception -> 0x010a }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x010a }
                    return r2
                L_0x010a:
                    com.xiaomi.smarthome.device.api.Callback r2 = r4
                    java.lang.String r3 = "connect failed!"
                    r2.onFailure(r0, r3)
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.mitsmsdk.NfcChannelManager.AnonymousClass4.call():java.lang.Boolean");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<Boolean>("connect complete", "connect error occurred") {
            /* renamed from: a */
            public void onNext(Boolean bool) {
            }
        }));
    }

    public void b(@NonNull final Callback<String> callback) {
        LogUtils.d("NfcChannelManager getAllCards");
        if (this.u == null) {
            LogUtils.e("getAllCards failed as context is null!");
            callback.onFailure(-3, "context is null!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            this.E.add(Observable.fromCallable(new Callable<List<CardInfo>>() {
                /* renamed from: a */
                public List<CardInfo> call() throws Exception {
                    return NfcChannelManager.this.H.fetchCards(CardInfoFactory.makeCardInfo(CardInfo.CARD_TYPE_MIFARE, (JSONObject) null));
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<List<CardInfo>>("fetchCards complete", "fetchCards error occurred") {
                /* renamed from: a */
                public void onNext(List<CardInfo> list) {
                    if (list == null || list.size() == 0) {
                        LogUtils.e("get none card!");
                        callback.onFailure(-4, "get none card!");
                        return;
                    }
                    NfcChannelManager.F.clear();
                    NfcChannelManager.F.addAll(list);
                    int unused = NfcChannelManager.this.M = 0;
                    WritableArray createArray = Arguments.createArray();
                    for (CardInfo next : list) {
                        if (next instanceof MifareCardInfo) {
                            MifareCardInfo mifareCardInfo = (MifareCardInfo) next;
                            createArray.pushMap(NfcChannelManager.a(mifareCardInfo, mifareCardInfo.getProductId()));
                            if ("66666-00211".equals(mifareCardInfo.getProductId())) {
                                NfcChannelManager.j(NfcChannelManager.this);
                            }
                        }
                    }
                    callback.onSuccess(createArray.toString());
                }
            }));
        }
    }

    public void c(@NonNull Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager issueDoorCard");
        if (this.u == null || this.C == null) {
            LogUtils.e("issueDoorCard failed!");
            callback.onFailure(-3, "context is null or device is not connect");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            final MifareCardInfo mifareCardInfo = new MifareCardInfo();
            mifareCardInfo.mMifareCardType = 2;
            final Bundle bundle = new Bundle();
            bundle.putString(Constants.EXTRA_DOOR_CARD_PRODUCT_ID, "66666-00211");
            final Callback<Boolean> callback2 = callback;
            this.E.add(Observable.fromCallable(new Callable<BaseResponse>() {
                /* renamed from: a */
                public BaseResponse call() throws Exception {
                    return NfcChannelManager.this.H.issue(mifareCardInfo, bundle);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<BaseResponse>("issue complete", "issue error occurred") {
                /* renamed from: a */
                public void onNext(BaseResponse baseResponse) {
                    if (baseResponse.isSuccess()) {
                        NfcChannelManager.this.C.openApduChannel();
                        byte[] j = NfcChannelManager.j(mifareCardInfo.mAid);
                        if (!NfcChannelManager.this.C.setAidInfoSync(new HMAidInfo(1, 2, j.length, j))) {
                            LogUtils.e("setAidInfoSync failed!");
                            callback2.onFailure(-5, "setAidInfoSync failed!");
                            return;
                        }
                        NfcChannelManager.this.C.closeApduChannel();
                        callback2.onSuccess(true);
                        return;
                    }
                    LogUtils.e("issueDoorCard failed! error = " + baseResponse.mResultCode);
                    callback2.onFailure(baseResponse.mResultCode, "issueDoorCard failed!");
                }
            }));
        }
    }

    public void b(String str, @NonNull Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager deleteCard");
        if (this.u == null || this.C == null) {
            LogUtils.e("deleteCard failed!");
            callback.onFailure(-3, "context is null or device is not connect!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            final MifareCardInfo k2 = k(str);
            if (k2 == null) {
                LogUtils.e("deleteCard failed as no card found!");
                callback.onFailure(-4, "no card found!");
                return;
            }
            final Callback<Boolean> callback2 = callback;
            this.E.add(Observable.fromCallable(new Callable<BaseResponse>() {
                /* renamed from: a */
                public BaseResponse call() throws Exception {
                    return NfcChannelManager.this.H.deleteCard(k2);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<BaseResponse>("deleteCard complete", "deleteCard error occurred") {
                /* renamed from: a */
                public void onNext(BaseResponse baseResponse) {
                    if (baseResponse.isSuccess()) {
                        NfcChannelManager.this.C.openApduChannel();
                        byte[] j = NfcChannelManager.j(k2.mAid);
                        if (!NfcChannelManager.this.C.setAidInfoSync(new HMAidInfo(2, 2, j.length, j))) {
                            LogUtils.e("setAidInfoSync failed!");
                            callback2.onFailure(-5, "setAidInfoSync failed!");
                            return;
                        }
                        NfcChannelManager.this.C.closeApduChannel();
                        callback2.onSuccess(true);
                        return;
                    }
                    LogUtils.e("deleteCard failed finally!");
                    callback2.onFailure(baseResponse.mResultCode, baseResponse.mMsg);
                }
            }));
        }
    }

    public void c(String str, @NonNull Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager setDefaultCard");
        if (this.u == null || this.C == null) {
            LogUtils.e("setDefaultCard failed!");
            callback.onFailure(-3, "context is null or device is not connect!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            final MifareCardInfo k2 = k(str);
            if (k2 == null) {
                LogUtils.e("setDefaultCard failed as no card found!");
                callback.onFailure(-4, "no card found!");
                return;
            }
            final Callback<Boolean> callback2 = callback;
            this.E.add(Observable.fromCallable(new Callable<Boolean>() {
                /* renamed from: a */
                public Boolean call() throws Exception {
                    return Boolean.valueOf(SysUtils.activateCard(NfcChannelManager.this.u, k2));
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<Boolean>("setDefaultCard complete", "setDefaultCard error occurred") {
                /* renamed from: a */
                public void onNext(Boolean bool) {
                    if (bool.booleanValue()) {
                        NfcChannelManager.this.C.openApduChannel();
                        byte[] j = NfcChannelManager.j(k2.mAid);
                        if (!NfcChannelManager.this.C.setAidInfoSync(new HMAidInfo(3, 2, j.length, j))) {
                            LogUtils.e("setAidInfoSync failed!");
                            callback2.onFailure(-5, "setAidInfoSync failed!");
                            return;
                        }
                        NfcChannelManager.this.C.closeApduChannel();
                        callback2.onSuccess(true);
                        return;
                    }
                    LogUtils.e("setDefaultCard failed finally!");
                    callback2.onFailure(-8, "setDefaultCard failed!");
                }
            }));
        }
    }

    public void d(String str, @NonNull Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager updateCard + " + str);
        if (this.u == null || this.C == null) {
            LogUtils.e("updateCard failed!");
            callback.onFailure(-3, "context is null or device is not connect!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            final MifareCardInfo b2 = b(str);
            if (b2 == null) {
                LogUtils.e("updateCard failed as no card found!");
                callback.onFailure(-4, "no card found!");
                return;
            }
            final Callback<Boolean> callback2 = callback;
            this.E.add(Observable.fromCallable(new Callable<BaseResponse>() {
                /* renamed from: a */
                public BaseResponse call() throws Exception {
                    return NfcChannelManager.this.H.updateCard(b2);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<BaseResponse>("updateCard complete", "updateCard error occurred") {
                /* renamed from: a */
                public void onNext(BaseResponse baseResponse) {
                    if (baseResponse.isSuccess()) {
                        NfcChannelManager.this.C.openApduChannel();
                        byte[] j = NfcChannelManager.j(b2.mAid);
                        int length = j.length;
                        LogUtils.d("NfcChannelManager setAccessInfoSync name = " + b2.mCardName);
                        if (!NfcChannelManager.this.C.setAccessInfoSync(new HMAccessInfo(b2.mCardName, length, j))) {
                            LogUtils.e("setAccessInfoSync failed!");
                            callback2.onFailure(-6, "setAccessInfoSync failed!");
                            return;
                        }
                        NfcChannelManager.this.C.closeApduChannel();
                        callback2.onSuccess(true);
                        return;
                    }
                    LogUtils.e("updateCard failed finally!");
                    callback2.onFailure(-9, "updateCard failed!");
                }
            }));
        }
    }

    public void e(final String str, @NonNull final Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager checkDefaultCard");
        if (this.u == null || this.C == null) {
            LogUtils.e("checkDefaultCard failed!");
            callback.onFailure(-3, "context is null or device is not connect!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            this.E.add(Observable.fromCallable(new Callable<Boolean>() {
                /* renamed from: a */
                public Boolean call() throws Exception {
                    NfcChannelManager.this.C.openApduChannel();
                    byte[] j = NfcChannelManager.j(str);
                    int length = j.length;
                    byte[] a2 = NfcChannelManager.this.b(length, j);
                    ApduResponse sendApduData = NfcChannelManager.this.C.sendApduData(a2, a2.length, false);
                    LogUtils.d(NfcChannelManager.r, " responseCRS" + sendApduData);
                    byte[] b2 = NfcChannelManager.this.a(length, j);
                    ApduResponse sendApduData2 = NfcChannelManager.this.C.sendApduData(b2, b2.length, false);
                    LogUtils.d(NfcChannelManager.r, "responseStatus:" + sendApduData2);
                    NfcChannelManager.this.C.closeApduChannel();
                    return Boolean.valueOf(NfcChannelManager.this.a(b2));
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<Boolean>("checkDefaultCard complete", "checkDefaultCard error occurred") {
                /* renamed from: a */
                public void onNext(Boolean bool) {
                    callback.onSuccess(bool);
                }
            }));
        }
    }

    /* access modifiers changed from: private */
    public boolean a(byte[] bArr) {
        if (bArr == null || bArr.length < 7) {
            return false;
        }
        int i2 = 0;
        while (i2 < bArr.length - 5) {
            if ((bArr[i2] & 255) != 159 || (bArr[i2 + 1] & 255) != 112) {
                i2++;
            } else if ((bArr[i2 + 4] & 255) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void f(final String str, @NonNull final Callback<Boolean> callback) {
        LogUtils.d("NfcChannelManager checkActivateCard");
        if (this.u == null || this.C == null) {
            LogUtils.e("checkActivateCard failed!");
            callback.onFailure(-3, "context is null or device is not connect!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            this.E.add(Observable.fromCallable(new Callable<Boolean>() {
                /* renamed from: a */
                public Boolean call() throws Exception {
                    NfcChannelManager.this.C.openApduChannel();
                    byte[] j = NfcChannelManager.j(str);
                    byte[] c = NfcChannelManager.this.c(j.length, j);
                    ApduResponse sendApduData = NfcChannelManager.this.C.sendApduData(c, c.length, false);
                    LogUtils.d(NfcChannelManager.r, " responseCRS" + sendApduData);
                    NfcChannelManager.this.C.closeApduChannel();
                    return Boolean.valueOf(NfcChannelManager.this.b(c));
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<Boolean>("checkActivateCard complete", "checkActivateCard error occurred") {
                /* renamed from: a */
                public void onNext(Boolean bool) {
                    callback.onSuccess(bool);
                }
            }));
        }
    }

    /* access modifiers changed from: private */
    public boolean b(byte[] bArr) {
        if (bArr == null || bArr.length < 7) {
            return false;
        }
        int i2 = 0;
        while (i2 < bArr.length - 5) {
            if ((bArr[i2] & 255) != 159 || (bArr[i2 + 1] & 255) != 12) {
                i2++;
            } else if ((bArr[i2 + 3] & 255) == 7) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void d(@NonNull Callback<WritableArray> callback) {
        LogUtils.d("NfcChannelManager getDefaultCardAndActivateInfo");
        if (this.u == null || this.C == null || F.size() == 0) {
            LogUtils.e("getDefaultCardAndActivateInfo failed!");
            callback.onFailure(-3, "context is null or device is not connect!");
        } else if (this.L != 0) {
            LogUtils.e("device is not connected!");
            callback.onFailure(-7, "device is not connected!");
        } else {
            final WritableArray createArray = Arguments.createArray();
            final Callback<WritableArray> callback2 = callback;
            this.E.add(Observable.fromCallable(new Callable<Boolean>() {
                /* renamed from: a */
                public Boolean call() throws Exception {
                    NfcChannelManager.this.C.openApduChannel();
                    int unused = NfcChannelManager.this.N = 0;
                    for (int i = 0; i < NfcChannelManager.F.size(); i++) {
                        String str = ((CardInfo) NfcChannelManager.F.get(i)).mAid;
                        byte[] j = NfcChannelManager.j(str);
                        int length = j.length;
                        byte[] c = NfcChannelManager.this.c(length, j);
                        ApduResponse sendApduData = NfcChannelManager.this.C.sendApduData(c, c.length, false);
                        LogUtils.d(NfcChannelManager.r, " responseCRS" + sendApduData);
                        if (sendApduData == null) {
                            return false;
                        }
                        boolean b2 = NfcChannelManager.this.b(sendApduData.getData());
                        if (b2) {
                            NfcChannelManager.p(NfcChannelManager.this);
                        }
                        byte[] a2 = NfcChannelManager.this.b(length, j);
                        ApduResponse sendApduData2 = NfcChannelManager.this.C.sendApduData(a2, a2.length, false);
                        LogUtils.d(NfcChannelManager.r, " responseCRS" + sendApduData2);
                        byte[] b3 = NfcChannelManager.this.a(length, j);
                        ApduResponse sendApduData3 = NfcChannelManager.this.C.sendApduData(b3, b3.length, false);
                        LogUtils.d(NfcChannelManager.r, "responseStatus:" + sendApduData3);
                        if (sendApduData3 == null) {
                            return false;
                        }
                        boolean a3 = NfcChannelManager.this.a(sendApduData3.getData());
                        WritableMap createMap = Arguments.createMap();
                        createMap.putString("aid", str);
                        createMap.putBoolean("isDefault", a3);
                        createMap.putBoolean("isActivate", b2);
                        createArray.pushMap(createMap);
                    }
                    NfcChannelManager.this.C.closeApduChannel();
                    return true;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Facade.SimpleSubscriber<Boolean>("getDefaultCardAndActivateInfo complete", "getDefaultCardAndActivateInfo error occurred") {
                /* renamed from: a */
                public void onNext(Boolean bool) {
                    if (bool.booleanValue()) {
                        STAT.i.a(NfcChannelManager.this.y, NfcChannelManager.this.b(), NfcChannelManager.this.M, NfcChannelManager.this.N, NfcChannelManager.this.I);
                        callback2.onSuccess(createArray);
                        return;
                    }
                    callback2.onFailure(-7, "band is not connected!");
                }
            }));
        }
    }

    private static MifareCardInfo k(String str) {
        for (int i2 = 0; i2 < F.size(); i2++) {
            if (TextUtils.equals(str, F.get(i2).mAid) && (F.get(i2) instanceof MifareCardInfo)) {
                return (MifareCardInfo) F.get(i2);
            }
        }
        return null;
    }

    public static WritableMap a(MifareCardInfo mifareCardInfo, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("cardArt", mifareCardInfo.mCardArt);
        createMap.putInt("cardFace", mifareCardInfo.mCardFace);
        createMap.putInt("fingerFlag", mifareCardInfo.mFingerAuthFlag);
        createMap.putInt("mifareCardType", mifareCardInfo.mMifareCardType);
        createMap.putInt("vcStatus", mifareCardInfo.mVCStatus);
        createMap.putString("aid", mifareCardInfo.mAid);
        createMap.putInt("cardBalance", mifareCardInfo.mCardBalance);
        createMap.putInt("cardGroupId", mifareCardInfo.mCardGroupId);
        createMap.putString("name", mifareCardInfo.mCardName);
        createMap.putString("cardType", mifareCardInfo.mCardType);
        createMap.putInt("groupType", mifareCardInfo.mGroupType);
        createMap.putBoolean("hasIssue", mifareCardInfo.mHasIssue);
        createMap.putBoolean("isDefault", mifareCardInfo.mIsDefault);
        createMap.putBoolean("isPack", mifareCardInfo.mIsPack);
        createMap.putBoolean("isReadSECorrectly", mifareCardInfo.mIsReadSECorrectly);
        createMap.putInt(CardInfo.KEY_ISSUEFEE, mifareCardInfo.mIssueFee);
        createMap.putString(MifareCardInfo.KEY_PRODUCT_ID, str);
        return createMap;
    }

    public static MifareCardInfo b(String str) {
        MifareCardInfo mifareCardInfo = null;
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("NativeMap");
            if (optJSONObject == null) {
                return null;
            }
            MifareCardInfo k2 = k(optJSONObject.optString("aid"));
            if (k2 == null) {
                try {
                    LogUtils.d("NfcChannelManager no card found!");
                    return null;
                } catch (JSONException e2) {
                    e = e2;
                    mifareCardInfo = k2;
                    e.printStackTrace();
                    return mifareCardInfo;
                }
            } else {
                k2.mCardArt = optJSONObject.optString("cardArt", k2.mCardArt);
                k2.mCardFace = optJSONObject.optInt("cardFace", k2.mCardFace);
                k2.mFingerAuthFlag = optJSONObject.optInt("fingerFlag", k2.mFingerAuthFlag);
                k2.mMifareCardType = optJSONObject.optInt("mifareCardType", k2.mMifareCardType);
                k2.mVCStatus = optJSONObject.optInt("vcStatus", k2.mVCStatus);
                k2.mAid = optJSONObject.optString("aid", k2.mAid);
                k2.mCardBalance = optJSONObject.optInt("cardBalance", k2.mCardBalance);
                k2.mCardGroupId = optJSONObject.optInt("cardGroupId", k2.mCardGroupId);
                k2.mCardName = optJSONObject.optString("name", k2.mCardName);
                k2.mCardType = optJSONObject.optString("cardType", k2.mCardType);
                k2.mGroupType = optJSONObject.optInt("groupType", k2.mGroupType);
                k2.mHasIssue = optJSONObject.optBoolean("hasIssue", k2.mHasIssue);
                k2.mIsDefault = optJSONObject.optBoolean("isDefault", k2.mIsDefault);
                k2.mIsPack = optJSONObject.optBoolean("isPack", k2.mIsPack);
                k2.mIsReadSECorrectly = optJSONObject.optBoolean("isReadSECorrectly", k2.mIsReadSECorrectly);
                k2.mIssueFee = optJSONObject.optInt(CardInfo.KEY_ISSUEFEE, k2.mIssueFee);
                return k2;
            }
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            return mifareCardInfo;
        }
    }

    public static WritableArray c(String str) {
        WritableArray createArray = Arguments.createArray();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    createArray.pushMap(d(jSONObject.toString()));
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return createArray;
    }

    public static WritableMap d(String str) {
        WritableMap createMap = Arguments.createMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            createMap.putString("cardArt", jSONObject.optString("cardArt"));
            createMap.putInt("cardFace", jSONObject.optInt("cardFace"));
            createMap.putInt("fingerFlag", jSONObject.optInt("fingerFlag"));
            createMap.putInt("mifareCardType", jSONObject.optInt("mifareCardType"));
            createMap.putInt("vcStatus", jSONObject.optInt("vcStatus"));
            createMap.putString("aid", jSONObject.optString("aid"));
            createMap.putInt("cardBalance", jSONObject.optInt("cardBalance"));
            createMap.putInt("cardGroupId", jSONObject.optInt("cardGroupId"));
            createMap.putString("name", jSONObject.optString("name"));
            createMap.putString("cardType", jSONObject.optString("cardType"));
            createMap.putInt("groupType", jSONObject.optInt("groupType"));
            createMap.putBoolean("hasIssue", jSONObject.optBoolean("hasIssue"));
            createMap.putBoolean("isDefault", jSONObject.optBoolean("isDefault"));
            createMap.putBoolean("isPack", jSONObject.optBoolean("isPack"));
            createMap.putBoolean("isReadSECorrectly", jSONObject.optBoolean("isReadSECorrectly"));
            createMap.putInt(CardInfo.KEY_ISSUEFEE, jSONObject.optInt(CardInfo.KEY_ISSUEFEE));
            createMap.putString(MifareCardInfo.KEY_PRODUCT_ID, jSONObject.optString(MifareCardInfo.KEY_PRODUCT_ID));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return createMap;
    }

    public static WritableArray e(String str) {
        WritableArray createArray = Arguments.createArray();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("aid", jSONObject.getString("aid"));
                    createMap.putBoolean("isDefault", jSONObject.getBoolean("isDefault"));
                    createMap.putBoolean("isActivate", jSONObject.getBoolean("isActivate"));
                    createArray.pushMap(createMap);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return createArray;
    }

    public String b() {
        return this.v;
    }

    public void f(String str) {
        this.v = str;
    }

    public String c() {
        return this.w;
    }

    public void g(String str) {
        this.w = str;
    }

    public String d() {
        return this.x;
    }

    public void h(String str) {
        this.x = str;
    }

    public String e() {
        return this.y;
    }

    public void i(String str) {
        this.y = str;
    }

    public HMBleDevice f() {
        return this.C;
    }

    public void a(HMBleDevice hMBleDevice) {
        this.C = hMBleDevice;
    }

    public void g() {
        LogUtils.d("NfcChannelManager deInit");
        if (this.C != null) {
            try {
                this.C.disconnect();
            } catch (Exception unused) {
            }
        }
        if (this.H != null) {
            this.H.clearData();
            this.H.release();
        }
    }

    public static byte[] j(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) (a(charArray[i3 + 1]) | (a(charArray[i3]) << 4));
        }
        return bArr;
    }

    private static int a(char c2) {
        return (byte) a.f.indexOf(c2);
    }

    /* access modifiers changed from: private */
    public byte[] a(int i2, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.MIN_VALUE);
        arrayList.add((byte) -14);
        arrayList.add((byte) 64);
        arrayList.add((byte) 2);
        arrayList.add(Byte.valueOf((byte) (i2 + 2)));
        arrayList.add(Byte.valueOf(Constants.TagName.CP_NO));
        arrayList.add(Byte.valueOf((byte) i2));
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(Byte.valueOf(bArr[i3]));
        }
        arrayList.add((byte) 0);
        byte[] bArr2 = new byte[arrayList.size()];
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            bArr2[i4] = ((Byte) arrayList.get(i4)).byteValue();
        }
        return bArr2;
    }

    /* access modifiers changed from: private */
    public byte[] b(int i2, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add((byte) 0);
        arrayList.add(Byte.valueOf(ScriptToolsConst.TagName.CommandMultiple));
        arrayList.add((byte) 4);
        arrayList.add((byte) 0);
        arrayList.add((byte) 9);
        arrayList.add(Byte.valueOf(ScriptToolsConst.TagName.CommandSingle));
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 1);
        arrayList.add(Byte.valueOf(Constants.TagName.TERMINAL_BACK_MAIN_ID));
        arrayList.add(Byte.valueOf(Constants.TagName.TERMINAL_BACK_INFO_TYPE));
        arrayList.add(Byte.valueOf(Constants.TagName.TERMINAL_BACK_QUESTION_FLAG));
        arrayList.add((byte) 83);
        arrayList.add((byte) 0);
        byte[] bArr2 = new byte[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            bArr2[i3] = ((Byte) arrayList.get(i3)).byteValue();
        }
        return bArr2;
    }

    /* access modifiers changed from: private */
    public byte[] c(int i2, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add((byte) 0);
        arrayList.add(Byte.valueOf(ScriptToolsConst.TagName.CommandMultiple));
        arrayList.add((byte) 4);
        arrayList.add((byte) 0);
        arrayList.add(Byte.valueOf((byte) i2));
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(Byte.valueOf(bArr[i3]));
        }
        byte[] bArr2 = new byte[arrayList.size()];
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            bArr2[i4] = ((Byte) arrayList.get(i4)).byteValue();
        }
        return bArr2;
    }

    public byte[] a(String str, String str2) {
        LogUtils.d(r, "getSignature.");
        this.q = true;
        DeviceApi.getInstance().getHMBandSignature(this.u, str, str2, 0, 10001, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                LogUtils.d(NfcChannelManager.r, "result = " + jSONObject);
                if (jSONObject != null) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject != null) {
                        String unused = NfcChannelManager.this.D = optJSONObject.optString("signature", "");
                        NfcChannelManager.this.q = false;
                        return;
                    }
                    return;
                }
                LogUtils.d(NfcChannelManager.r, "getSignature result is null!");
            }

            public void onFailure(Error error) {
                NfcChannelManager.this.q = false;
            }
        });
        while (this.q) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        LogUtils.d(r, "signature0 = " + this.D);
        return Base64.decode(this.D, 2);
    }
}
