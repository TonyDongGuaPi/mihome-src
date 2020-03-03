package com.xiaomi.smarthome.miniprogram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.AuthManager;
import com.xiaomi.smarthome.auth.model.AuthInfo4Get;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.entity.WXDeviceShareLinkResult;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.miniprogram.model.MyMiniProgramDevice;
import com.xiaomi.smarthome.miniprogram.model.SupportWechatAppInfos;
import com.xiaomi.smarthome.miniprogram.model.TokenCountInfo;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiniProgramManager {

    /* renamed from: a  reason: collision with root package name */
    public static String f20008a = "2882303761517625813";
    public static String b = "com.miot.xiaomi.WeChat-miniAPP";
    public static String c = "75EF37F7110FFBE8C3B60E5D312F68BF";
    private static MiniProgramManager h = null;
    private static String i = "https://openapp.io.mi.com";
    private static String j = "https://wx.api.home.mi.com/wx/v1/";
    private static String k = "https://wx.api.home.mi.com/wx/v2/";
    /* access modifiers changed from: private */
    public static int l = 20;
    List<MyMiniProgramDevice> d = new ArrayList();
    Map<String, Integer> e = new HashMap();
    Map<String, Bitmap> f = new HashMap();
    Map<String, String> g = new HashMap();
    /* access modifiers changed from: private */
    public List<String> m = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, AuthInfo4Get.AuthDetail> n = new HashMap();
    /* access modifiers changed from: private */
    public OnGettingMiniProgram o;
    /* access modifiers changed from: private */
    public String p = null;
    /* access modifiers changed from: private */
    public boolean q = true;
    private int r = 0;
    /* access modifiers changed from: private */
    public boolean s = true;
    /* access modifiers changed from: private */
    public boolean t = true;
    private String u;
    /* access modifiers changed from: private */
    public SmartHomeDeviceManager.IClientDeviceListener v = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            SmartHomeDeviceManager.a().c(MiniProgramManager.this.v);
            MiniProgramManager.this.a(false);
        }

        public void b(int i) {
            SmartHomeDeviceManager.a().c(MiniProgramManager.this.v);
        }
    };
    /* access modifiers changed from: private */
    public WXShareCallbackImp w;
    /* access modifiers changed from: private */
    public int x = 0;
    /* access modifiers changed from: private */
    public int y = 0;

    public interface WXShareCallback {
        void a();

        void a(String str);
    }

    static /* synthetic */ int j(MiniProgramManager miniProgramManager) {
        int i2 = miniProgramManager.x;
        miniProgramManager.x = i2 + 1;
        return i2;
    }

    private MiniProgramManager() {
        AuthManager.h().a(f20008a);
        AuthManager.h().c(c);
        AuthManager.h().b(b);
    }

    public static MiniProgramManager a() {
        if (h == null) {
            h = new MiniProgramManager();
        }
        return h;
    }

    public void a(OnGettingMiniProgram onGettingMiniProgram) {
        this.o = onGettingMiniProgram;
    }

    public void a(boolean z) {
        final List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 != null && d2.size() != 0) {
            HashSet hashSet = new HashSet();
            for (Device next : d2) {
                if (next.isOwner()) {
                    hashSet.add(next.model);
                }
            }
            a((HashSet<String>) hashSet, (AsyncCallback<SupportWechatAppInfos, Error>) new AsyncCallback<SupportWechatAppInfos, Error>() {
                /* renamed from: a */
                public void onSuccess(SupportWechatAppInfos supportWechatAppInfos) {
                    if (supportWechatAppInfos != null && supportWechatAppInfos.f20052a != null && supportWechatAppInfos.f20052a.size() > 0) {
                        MiniProgramManager.this.d = MiniProgramManager.this.a((List<Device>) d2, supportWechatAppInfos.f20052a);
                        List unused = MiniProgramManager.this.m = MiniProgramManager.this.a(supportWechatAppInfos.f20052a);
                        if (MiniProgramManager.this.m != null && MiniProgramManager.this.m.size() > 0) {
                            MiniProgramManager.this.b((List<String>) MiniProgramManager.this.m);
                        }
                        if (d2 != null && d2.size() > 0) {
                            MiniProgramManager.this.l();
                        } else if (MiniProgramManager.this.o != null) {
                            MiniProgramManager.this.o.onError(false);
                        }
                    } else if (MiniProgramManager.this.o != null) {
                        MiniProgramManager.this.o.onError(false);
                    }
                }

                public void onFailure(Error error) {
                    if (MiniProgramManager.this.o != null) {
                        MiniProgramManager.this.o.onError(false);
                    }
                }
            });
        } else if (z) {
            SmartHomeDeviceManager.a().a(this.v);
            SmartHomeDeviceManager.a().p();
        }
    }

    /* access modifiers changed from: private */
    public List<String> a(List<SupportWechatAppInfos.DetailInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (SupportWechatAppInfos.DetailInfo next : list) {
            if (next.c) {
                arrayList.add(next.f20053a);
            }
        }
        return arrayList;
    }

    public void a(MyMiniProgramDevice myMiniProgramDevice) {
        T t2 = myMiniProgramDevice.c;
        if (t2 == null) {
            if (this.o != null) {
                this.o.onError(false);
            }
        } else if (t2.isOwner()) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(t2.model)) {
                arrayList.add(t2.model);
            }
            b((List<String>) arrayList);
            this.d.add(myMiniProgramDevice);
            if (this.o != null) {
                this.o.onSuccess(this.d, 0, false);
            }
        } else if (this.o != null) {
            this.o.onError(false);
        }
    }

    public void b() {
        if (TextUtils.isEmpty(this.p)) {
            UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
                public void a(int i) {
                }

                public void a(int i, Object obj) {
                }

                public void a(ShareUserRecord shareUserRecord) {
                    String unused = MiniProgramManager.this.p = shareUserRecord.nickName;
                }
            });
        }
    }

    public void a(MyMiniProgramDevice myMiniProgramDevice, String str, boolean z, boolean z2, WXShareCallbackImp wXShareCallbackImp) {
        this.w = wXShareCallbackImp;
        String str2 = this.g.get(myMiniProgramDevice.c.model);
        if (this.f.get(str2) == null || this.f.get(str2).isRecycled()) {
            a(str2);
        }
        if (this.n == null || this.n.size() <= 0 || !this.n.containsKey(myMiniProgramDevice.c.did) || TextUtils.isEmpty(this.n.get(myMiniProgramDevice.c.did).c) || !a(this.n.get(myMiniProgramDevice.c.did).d)) {
            a((Device) myMiniProgramDevice.c, "state", str, z, z2);
            return;
        }
        b(myMiniProgramDevice.c, this.n.get(myMiniProgramDevice.c.did).c, str, z, z2);
    }

    /* access modifiers changed from: private */
    public boolean a(long j2) {
        return j2 >= System.currentTimeMillis() / 1000;
    }

    public void a(List<String> list, AsyncCallback asyncCallback) {
        AuthManager.h().a(list, asyncCallback);
    }

    public void c() {
        this.f.clear();
        d();
        AuthManager.h().l();
        if (h != null) {
            h = null;
        }
    }

    public void d() {
        this.r = 0;
        this.q = true;
        this.s = true;
        this.t = false;
        this.d.clear();
        this.n.clear();
        this.e.clear();
    }

    public void e() {
        a(this.r);
    }

    public boolean f() {
        return this.s;
    }

    public boolean g() {
        return this.q;
    }

    public boolean h() {
        return this.t;
    }

    /* access modifiers changed from: private */
    public List<MyMiniProgramDevice> a(List<Device> list, List<SupportWechatAppInfos.DetailInfo> list2) {
        if (list == null || list.size() == 0) {
            return this.d;
        }
        if (list2 == null || list2.size() == 0) {
            return this.d;
        }
        HashSet hashSet = new HashSet();
        for (SupportWechatAppInfos.DetailInfo next : list2) {
            if (next.c) {
                hashSet.add(next.f20053a);
            }
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            if (hashSet.contains(list.get(size).model) && list.get(size).isOwner()) {
                this.d.add(new MyMiniProgramDevice(list.get(size)));
            }
        }
        return this.d;
    }

    public AsyncHandle a(HashSet<String> hashSet, AsyncCallback<SupportWechatAppInfos, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("models", jSONArray);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/product/infomation").b((List<KeyValuePair>) arrayList).a(), new JsonParser<SupportWechatAppInfos>() {
            /* renamed from: a */
            public SupportWechatAppInfos parse(JSONObject jSONObject) throws JSONException {
                return SupportWechatAppInfos.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    private void d(List<String> list, AsyncCallback<TokenCountInfo, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dids", jSONArray);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/home/gettokencount").b((List<KeyValuePair>) arrayList).a(), new JsonParser<TokenCountInfo>() {
            /* renamed from: a */
            public TokenCountInfo parse(JSONObject jSONObject) throws JSONException {
                return TokenCountInfo.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.n != null && this.n.size() > 0) {
            for (int i2 = this.r * l; i2 < this.d.size(); i2++) {
                if (this.n.containsKey(this.d.get(i2).c.did) && !TextUtils.isEmpty(this.n.get(this.d.get(i2).c.did).c)) {
                    this.d.get(i2).f20051a = true;
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        if (this.e != null && this.e.size() > 0) {
            for (int i3 = this.r * l; i3 < this.d.size(); i3++) {
                if (this.e.get(this.d.get(i3).c.did) != null) {
                    this.d.get(i3).b = this.e.get(this.d.get(i3).c.did).intValue();
                    arrayList.add(this.d.get(i3));
                }
            }
        }
        if (this.o != null) {
            this.o.onSuccess(arrayList, this.r, this.q);
        }
        this.r++;
        this.s = false;
    }

    private void a(Device device, String str, String str2, boolean z, boolean z2) {
        final Device device2 = device;
        final String str3 = str2;
        final boolean z3 = z;
        final boolean z4 = z2;
        AuthManager.h().a("token", device2.did, "7", i, str, new AsyncCallback() {
            public void onSuccess(Object obj) {
                if (MiniProgramManager.this.w != null && TextUtils.equals(MiniProgramManager.this.w.b, device2.did)) {
                    MiniProgramManager.this.w.a("");
                }
            }

            public void onFailure(Error error) {
                if (error.a() != 404 || TextUtils.isEmpty(error.c())) {
                    MiniProgramManager.this.b(device2, "", str3, z3, z4);
                    return;
                }
                String c2 = error.c();
                if (c2.contains("access_token")) {
                    String[] split = c2.split(a.b);
                    for (int i = 0; i < split.length; i++) {
                        if (split[i].contains("access_token")) {
                            String substring = c2.substring(c2.indexOf("access_token=") + 13, split[i].length());
                            AuthInfo4Get.AuthDetail authDetail = new AuthInfo4Get.AuthDetail();
                            authDetail.b = device2.did;
                            authDetail.c = URLDecoder.decode(substring);
                            authDetail.f13922a = 7;
                            authDetail.d = (System.currentTimeMillis() / 1000) + 352800;
                            MiniProgramManager.this.n.put(device2.did, authDetail);
                            MiniProgramManager.this.b(device2, authDetail.c, str3, z3, z4);
                            return;
                        }
                    }
                }
                MiniProgramManager.this.b(device2, "", str3, z3, z4);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(Device device, String str, String str2, boolean z, boolean z2) {
        Device device2 = device;
        if (device2 != null && !TextUtils.isEmpty(str)) {
            String str3 = System.currentTimeMillis() + "";
            final Device device3 = device;
            final String str4 = str;
            final String str5 = str3;
            final boolean z3 = z2;
            final String str6 = str2;
            a(z, z2, device2.did, device2.name, str, str6, this.p, str3, new AsyncCallback() {
                public void onSuccess(Object obj) {
                    Bitmap bitmap;
                    if (MiniProgramManager.this.w != null && TextUtils.equals(MiniProgramManager.this.w.b, device3.did)) {
                        MiniProgramManager.this.w.a();
                    }
                    IWXAPI iwxapi = SHApplication.getIWXAPI();
                    WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
                    wXMiniProgramObject.miniprogramType = 0;
                    wXMiniProgramObject.webpageUrl = AppConstants.K;
                    wXMiniProgramObject.userName = AppConstants.H;
                    StringBuilder sb = new StringBuilder(AppConstants.I);
                    sb.append("?token=");
                    sb.append(str4);
                    if (!TextUtils.isEmpty(MiniProgramManager.this.p)) {
                        sb.append("&userId=");
                        sb.append(MiniProgramManager.this.p);
                    }
                    sb.append("&did=");
                    sb.append(device3.did);
                    if (!TextUtils.isEmpty(device3.name)) {
                        sb.append("&name=");
                        sb.append(device3.name);
                    }
                    sb.append("&sign=");
                    sb.append(str5);
                    sb.append("&isSupportPermissionCtrl=");
                    sb.append(z3 ? "1" : "0");
                    if (!TextUtils.isEmpty(str6)) {
                        sb.append("&shareKey=");
                        sb.append(str6);
                    }
                    if (MiniProgramManager.this.g.containsKey(device3.model) && !TextUtils.isEmpty(MiniProgramManager.this.g.get(device3.model))) {
                        sb.append("&icon=");
                        sb.append(MiniProgramManager.this.g.get(device3.model));
                    }
                    wXMiniProgramObject.path = sb.toString();
                    LogUtil.b("wxmini", wXMiniProgramObject.path);
                    WXMediaMessage wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
                    wXMediaMessage.title = SHApplication.getAppContext().getString(R.string.share_wx_mini_title);
                    wXMediaMessage.description = "";
                    if (MiniProgramManager.this.f.get(MiniProgramManager.this.g.get(device3.model)) != null && !MiniProgramManager.this.f.get(MiniProgramManager.this.g.get(device3.model)).isRecycled()) {
                        bitmap = MiniProgramManager.this.f.get(MiniProgramManager.this.g.get(device3.model));
                    } else if (MiniProgramManager.this.f.get("default") == null || MiniProgramManager.this.f.get("default").isRecycled()) {
                        bitmap = MiniProgramManager.this.a(BitmapFactory.decodeResource(SHApplication.getAppContext().getResources(), R.drawable.device_list_phone_no));
                    } else {
                        bitmap = MiniProgramManager.this.f.get("default");
                    }
                    wXMediaMessage.setThumbImage(bitmap);
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wXMediaMessage;
                    req.scene = 0;
                    iwxapi.sendReq(req);
                }

                public void onFailure(Error error) {
                    if (MiniProgramManager.this.w != null && TextUtils.equals(MiniProgramManager.this.w.b, device3.did)) {
                        MiniProgramManager.this.w.a("");
                    }
                    if (!TextUtils.isEmpty(error.b())) {
                        Log.e("share", error.b());
                    }
                }
            });
        } else if (this.w != null && TextUtils.equals(this.w.b, device2.did)) {
            this.w.a("");
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        AuthManager.h().c((AsyncCallback) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AuthInfo4Get authInfo4Get;
                try {
                    authInfo4Get = AuthInfo4Get.a(jSONObject);
                } catch (Exception unused) {
                    authInfo4Get = null;
                }
                if (authInfo4Get != null) {
                    if (!(authInfo4Get == null || authInfo4Get.f13921a == null || authInfo4Get.f13921a.size() <= 0)) {
                        for (AuthInfo4Get.AuthDetail next : authInfo4Get.f13921a) {
                            if (next.f13922a == 7 && MiniProgramManager.this.a(next.d)) {
                                MiniProgramManager.this.n.put(next.b, next);
                            }
                        }
                    }
                    boolean unused2 = MiniProgramManager.this.s = false;
                    MiniProgramManager.this.a(0);
                    return;
                }
                MiniProgramManager.this.a(0);
            }

            public void onFailure(Error error) {
                if (MiniProgramManager.this.o != null) {
                    MiniProgramManager.this.o.onError(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = l * i2;
        while (i3 < this.d.size() && i3 < (l * i2) + l) {
            arrayList.add(this.d.get(i3).c.did);
            i3++;
        }
        this.s = true;
        d(arrayList, new AsyncCallback<TokenCountInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(TokenCountInfo tokenCountInfo) {
                if (!(tokenCountInfo == null || tokenCountInfo.f20054a == null)) {
                    for (int i = 0; i < tokenCountInfo.f20054a.size(); i++) {
                        MiniProgramManager.this.e.put(tokenCountInfo.f20054a.get(i).f20055a, Integer.valueOf(tokenCountInfo.f20054a.get(i).b));
                    }
                }
                if ((i2 + 1) * MiniProgramManager.l >= MiniProgramManager.this.d.size()) {
                    boolean unused = MiniProgramManager.this.q = false;
                    boolean unused2 = MiniProgramManager.this.t = false;
                } else {
                    boolean unused3 = MiniProgramManager.this.t = true;
                }
                MiniProgramManager.this.k();
            }

            public void onFailure(Error error) {
                boolean unused = MiniProgramManager.this.s = false;
                boolean unused2 = MiniProgramManager.this.t = false;
                if (MiniProgramManager.this.o != null) {
                    MiniProgramManager.this.o.onError(MiniProgramManager.this.q);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str), SHApplication.getAppContext()).subscribe(new BaseBitmapDataSubscriber() {
            /* access modifiers changed from: protected */
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            }

            /* access modifiers changed from: protected */
            public void onNewResultImpl(@Nullable final Bitmap bitmap) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        MiniProgramManager.this.f.put(str, MiniProgramManager.this.a(bitmap));
                    }
                });
            }
        }, Executors.newCachedThreadPool());
    }

    /* access modifiers changed from: private */
    public void b(List<String> list) {
        if (this.f.get("default") == null || this.f.get("default").isRecycled()) {
            this.f.put("default", a(BitmapFactory.decodeResource(SHApplication.getAppContext().getResources(), R.drawable.device_list_phone_no)));
        }
        StringBuilder sb = new StringBuilder();
        for (String append : list) {
            sb.append(append);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("models", sb.toString());
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/public/get_product_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.NONE, new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                JSONArray optJSONArray;
                if (!jSONObject.isNull("configs") && (optJSONArray = jSONObject.optJSONArray("configs")) != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            String str = "";
                            if (!jSONObject2.isNull("model")) {
                                str = jSONObject2.optString("model");
                            }
                            if (!jSONObject2.isNull("neg_screen") && !jSONObject2.optJSONObject("neg_screen").isNull("neg_480")) {
                                String optString = jSONObject2.optJSONObject("neg_screen").optString("neg_480");
                                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(str)) {
                                    MiniProgramManager.this.g.put(str, optString);
                                    MiniProgramManager.this.a(optString);
                                }
                            }
                        } catch (JSONException unused) {
                        }
                    }
                }
            }
        });
    }

    public void a(boolean z, boolean z2, String str, String str2, String str3, String str4, String str5, String str6, final AsyncCallback asyncCallback) {
        String str7;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("did", str));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("name", str2));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("token", str3));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("shareKey", str4));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("userId", str5));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("sign", str6));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("isSupportWxShare", z ? "1" : "0"));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("isSupportPermissionCtrl", z2 ? "1" : "0"));
        if (TextUtils.isEmpty(str4)) {
            str7 = j;
        } else {
            str7 = k;
        }
        Request.Builder a2 = new Request.Builder().a("POST");
        HttpApi.a(a2.b(str7 + "authorize").a((List<com.xiaomi.smarthome.library.http.KeyValuePair>) arrayList).a(), (AsyncHandler) new AsyncHandler() {
            public void onSuccess(Object obj, Response response) {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
                r0 = e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
                r6 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0054, code lost:
                r6.printStackTrace();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x006c, code lost:
                r6 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
                r6.printStackTrace();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0053 A[ExcHandler: Exception (r6v5 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x006c A[ExcHandler: IOException (r6v1 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void processResponse(okhttp3.Response r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = ""
                    okhttp3.ResponseBody r6 = r6.body()     // Catch:{ IOException -> 0x006c, JSONException -> 0x0058, Exception -> 0x0053 }
                    java.lang.String r6 = r6.string()     // Catch:{ IOException -> 0x006c, JSONException -> 0x0058, Exception -> 0x0053 }
                    org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r0.<init>(r6)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    java.lang.String r1 = "code"
                    boolean r1 = r0.isNull(r1)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    if (r1 != 0) goto L_0x0044
                    java.lang.String r1 = "code"
                    int r1 = r0.optInt(r1)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    java.lang.String r2 = "message"
                    java.lang.String r0 = r0.optString(r2)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r2 = 1
                    if (r1 == r2) goto L_0x0037
                    r2 = 2
                    if (r1 != r2) goto L_0x002a
                    goto L_0x0037
                L_0x002a:
                    android.os.Handler r2 = com.xiaomi.smarthome.application.SHApplication.getGlobalHandler()     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$2 r3 = new com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$2     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r3.<init>(r1, r0)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r2.post(r3)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    goto L_0x0043
                L_0x0037:
                    android.os.Handler r1 = com.xiaomi.smarthome.application.SHApplication.getGlobalHandler()     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$1 r2 = new com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$1     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r2.<init>(r0)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r1.post(r2)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                L_0x0043:
                    return
                L_0x0044:
                    android.os.Handler r0 = com.xiaomi.smarthome.application.SHApplication.getGlobalHandler()     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$3 r1 = new com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$3     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r1.<init>()     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    r0.post(r1)     // Catch:{ IOException -> 0x006c, JSONException -> 0x0051, Exception -> 0x0053 }
                    goto L_0x0070
                L_0x0051:
                    r0 = move-exception
                    goto L_0x005c
                L_0x0053:
                    r6 = move-exception
                    r6.printStackTrace()
                    goto L_0x0070
                L_0x0058:
                    r6 = move-exception
                    r4 = r0
                    r0 = r6
                    r6 = r4
                L_0x005c:
                    r0.printStackTrace()
                    android.os.Handler r0 = com.xiaomi.smarthome.application.SHApplication.getGlobalHandler()
                    com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$4 r1 = new com.xiaomi.smarthome.miniprogram.MiniProgramManager$13$4
                    r1.<init>(r6)
                    r0.post(r1)
                    goto L_0x0070
                L_0x006c:
                    r6 = move-exception
                    r6.printStackTrace()
                L_0x0070:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miniprogram.MiniProgramManager.AnonymousClass13.processResponse(okhttp3.Response):void");
            }

            public void processFailure(Call call, IOException iOException) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        asyncCallback.onFailure(new Error(-999, "network error"));
                    }
                });
            }

            public void onFailure(final com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        asyncCallback.onFailure(new Error(error.a(), error.b()));
                    }
                });
            }
        });
    }

    public void b(List<String> list, final AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("dids", list.get(i2)));
        }
        Request.Builder a2 = new Request.Builder().a("POST");
        HttpApi.a(a2.b(j + "unauthorize").a((List<com.xiaomi.smarthome.library.http.KeyValuePair>) arrayList).a(), (AsyncHandler) new AsyncHandler() {
            public void onSuccess(Object obj, Response response) {
            }

            public void processResponse(Response response) {
                try {
                    final JSONObject jSONObject = new JSONObject(response.body().string());
                    if (!jSONObject.isNull("code")) {
                        final int optInt = jSONObject.optInt("code");
                        final String optString = jSONObject.optString("message");
                        LogUtil.b("wxmini", "code= " + optInt + " , message= " + optString);
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncCallback.onFailure(new Error(optInt, optString));
                            }
                        });
                    } else if (jSONObject.isNull("warnings")) {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncCallback.onFailure(new Error(-999, "unknown error"));
                            }
                        });
                    } else if (jSONObject.optInt("warnings") == 0) {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncCallback.onSuccess(jSONObject);
                            }
                        });
                    } else {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncCallback.onFailure(new Error(-999, "warnings is not 0"));
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

            public void processFailure(Call call, IOException iOException) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        asyncCallback.onFailure(new Error(-999, "network error"));
                    }
                });
            }

            public void onFailure(final com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        asyncCallback.onFailure(new Error(error.a(), error.b()));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public Bitmap a(Bitmap bitmap) {
        double width = (double) bitmap.getWidth();
        Double.isNaN(width);
        int i2 = (int) (width * 1.25d);
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(i2, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawRect(0.0f, 0.0f, (float) i2, (float) height, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        canvas.drawBitmap(bitmap, ((float) (i2 - bitmap.getWidth())) * 0.5f, ((float) (height - bitmap.getHeight())) * 0.5f, paint2);
        return createBitmap;
    }

    public void c(List<String> list, final AsyncCallback<JSONObject, Error> asyncCallback) {
        int i2 = 0;
        this.x = 0;
        this.y = 0;
        if (list != null && list.size() != 0) {
            this.y = list.size() / 50;
            if (list.size() % 50 != 0) {
                this.y++;
            }
            while (i2 < this.y) {
                JSONArray jSONArray = new JSONArray();
                int i3 = this.y * i2;
                while (i3 < list.size() && i3 < (this.y * i2) + 50) {
                    jSONArray.put(list.get(i3));
                    i3++;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("dids", jSONArray);
                } catch (JSONException unused) {
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/home/cleartokencount").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                    /* renamed from: a */
                    public JSONObject parse(JSONObject jSONObject) throws JSONException {
                        return jSONObject;
                    }
                }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject.has("code")) {
                            if (jSONObject.optInt("code") == 0) {
                                MiniProgramManager.j(MiniProgramManager.this);
                            }
                            if (MiniProgramManager.this.x == MiniProgramManager.this.y && asyncCallback != null) {
                                asyncCallback.onSuccess(jSONObject);
                            }
                        } else if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(-999, "code is null"));
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
                i2++;
            }
        }
    }

    public AsyncHandle a(String str, AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("GET").b("/v2/share/get_wechatshare_key").b((List<KeyValuePair>) arrayList).a(), new JsonParser<WXDeviceShareLinkResult>() {
            /* renamed from: a */
            public WXDeviceShareLinkResult parse(JSONObject jSONObject) throws JSONException {
                return WXDeviceShareLinkResult.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void a(String str, String str2, String str3, AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put(ApiConst.l, str2);
            jSONObject.put("union_id", str3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("GET").b("/share/accept_wechatshare").b((List<KeyValuePair>) arrayList).a(), new JsonParser<WXDeviceShareLinkResult>() {
            /* renamed from: a */
            public WXDeviceShareLinkResult parse(JSONObject jSONObject) throws JSONException {
                return WXDeviceShareLinkResult.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static abstract class WXShareCallbackImp implements WXShareCallback {
        String b;

        public WXShareCallbackImp(String str) {
            this.b = str;
        }
    }

    public void i() {
        this.w = null;
    }
}
