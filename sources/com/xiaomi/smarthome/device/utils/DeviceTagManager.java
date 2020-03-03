package com.xiaomi.smarthome.device.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.RoomConfig;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.lite.LiteDeviceManager;
import com.xiaomi.smarthome.miio.page.devicetag.DeviceTagChild;
import com.xiaomi.smarthome.miio.page.devicetag.DeviceTagGroup;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceTagManager implements DeviceTagInterface<Device> {
    public static boolean W = true;
    public static final String X = "DeviceTagManager";
    public static final int[] Y = {R.string.tag_recommend_bedroom, R.string.tag_recommend_livingroom, R.string.tag_recommend_kitchen, R.string.tag_recommend_washroom, R.string.tag_recommend_office};
    public static final HashMap<String, String> Z = new HashMap<>();
    private static final String aI = "category_pref_category_data_key";
    private static final String aJ = "category_pref_category_locale_key";
    private static final String aK = "category_pref_category_time_key";
    public static Map<String, Integer> aa = new HashMap();
    private static final int ac = 0;
    private static final int ad = 1;
    private static final int ae = 2;
    private static final int af = 3;
    private static final int ag = 4;
    private static final String ah = "sp_room_list_config";
    private static final int ai = 17;
    private static final int aj = 1800000;
    private Map<Integer, Map<String, Set<String>>> aA = new ConcurrentHashMap();
    private List<Integer> aB;
    private Map<Integer, List<String>> aC;
    private String aD;
    private Map<String, String> aE;
    /* access modifiers changed from: private */
    public List<DeviceTagInterface.IDeviceTagListener> aF = new ArrayList();
    /* access modifiers changed from: private */
    public Handler aG = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public volatile boolean aH = false;
    /* access modifiers changed from: private */
    public Map<String, DeviceTagInterface.Category> aL = new HashMap();
    private Map<String, DeviceTagInterface.Category> aM = new HashMap();
    SmartHomeDeviceManager.IClientDeviceListener ab = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                boolean unused = DeviceTagManager.this.aH = true;
                DeviceTagManager.this.O();
            }
        }
    };
    private long ak = 0;
    /* access modifiers changed from: private */
    public int al = 0;
    /* access modifiers changed from: private */
    public boolean am = false;
    private int an = -1;
    private String ao;
    private String ap;
    private List<Device> aq;
    private Map<String, RouterTagInfo> ar = new ConcurrentHashMap();
    private Map<String, String> as = new ConcurrentHashMap();
    private long at = 0;
    /* access modifiers changed from: private */
    public long au = 0;
    /* access modifiers changed from: private */
    public boolean av = false;
    /* access modifiers changed from: private */
    public boolean aw = false;
    /* access modifiers changed from: private */
    public boolean ax = false;
    /* access modifiers changed from: private */
    public boolean ay = false;
    private Set<Integer> az;

    static {
        aa.put("switch", Integer.valueOf(R.string.tag_capability_switch));
    }

    public static void v() {
        Resources resources = SHApplication.getAppContext().getResources();
        Z.put(resources.getString(R.string.tag_recommend_bedroom), "bedroom_1");
        Z.put(resources.getString(R.string.tag_recommend_livingroom), "livingroom_1");
        Z.put(resources.getString(R.string.tag_recommend_kitchen), "kitchen_1");
        Z.put(resources.getString(R.string.tag_recommend_washroom), "washroom_1");
        Z.put(resources.getString(R.string.tag_recommend_office), "office_1");
    }

    public static class LocationInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f15467a;
        public String b;
        public String c;
        public String d;
        public String e;

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("province", this.f15467a);
                jSONObject.put("city", this.b);
                jSONObject.put("township", this.d);
                jSONObject.put("addr", this.e);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.f15467a = jSONObject.optString("province");
                this.b = jSONObject.optString("city");
                this.c = jSONObject.optString("district");
                this.d = jSONObject.optString("township");
                this.e = jSONObject.optString("addr");
            }
        }

        public String b() {
            if (!StringUtil.c(this.d)) {
                return this.d;
            }
            if (!StringUtil.c(this.c)) {
                return this.c;
            }
            return this.f15467a;
        }
    }

    public static class RouterTagInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f15470a;
        public String b;
        public String c;
        public String d;
        public LocationInfo e;
        public int f;

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("bssid", this.f15470a);
                if (!StringUtil.c(this.b)) {
                    jSONObject.put(DeviceTagInterface.d, this.b);
                }
                jSONObject.put(DeviceTagInterface.e, this.c);
                if (!StringUtil.c(this.d)) {
                    jSONObject.put(DeviceTagInterface.f, this.d);
                }
                if (this.e != null) {
                    jSONObject.put("location", this.e.a());
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public void a(JSONObject jSONObject) {
            if (StringUtil.c(this.f15470a)) {
                this.f15470a = jSONObject.optString("bssid");
            }
            if (StringUtil.c(this.b)) {
                this.b = jSONObject.optString(DeviceTagInterface.d);
            }
            if (StringUtil.c(this.c)) {
                this.c = jSONObject.optString(DeviceTagInterface.e);
            }
            this.d = jSONObject.optString(DeviceTagInterface.f);
            if (this.e == null) {
                this.e = new LocationInfo();
            }
            this.e.a(jSONObject.optJSONObject("location"));
        }

        public void a(RouterTagInfo routerTagInfo) {
            if (TextUtils.isEmpty(this.f15470a)) {
                this.f15470a = routerTagInfo.f15470a;
            }
            if (TextUtils.isEmpty(this.b)) {
                this.b = routerTagInfo.b;
            }
            if (TextUtils.isEmpty(this.c)) {
                this.c = routerTagInfo.c;
            }
            this.d = routerTagInfo.d;
            this.e = routerTagInfo.e;
        }

        public String b() {
            if (this.e == null) {
                return null;
            }
            return this.e.b();
        }
    }

    private class ParseData {

        /* renamed from: a  reason: collision with root package name */
        int f15469a;
        long b;
        long c;
        Map<String, RouterTagInfo> d;
        Map<String, Set<String>> e;
        Set<Integer> f;
        List<Integer> g;
        Map<Integer, List<String>> h;

        private ParseData() {
        }
    }

    private class ParseConfigData {

        /* renamed from: a  reason: collision with root package name */
        int f15468a;
        long b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;

        private ParseConfigData() {
        }
    }

    public synchronized void h() {
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar.clear();
        this.as.clear();
        this.at = 0;
        this.au = 0;
        this.av = false;
        this.aw = false;
        this.ax = false;
        this.ay = false;
        this.az = null;
        this.aA = new HashMap();
        this.aB = null;
        this.aC = null;
        this.al = 0;
        this.aH = false;
        SmartHomeDeviceManager.a().c(this.ab);
        this.aL.clear();
        this.aM.clear();
        f(this.ao, this.ap);
        if (HomeManager.a().h()) {
            HomeManager.a().v();
        }
    }

    private void f(String str, String str2) {
        Intent intent = new Intent("device_tag_updated_action");
        if (!StringUtil.c(str)) {
            intent.putExtra("device_tag_param", str);
        }
        if (!StringUtil.c(str2)) {
            intent.putExtra("router_bssid_param", str2);
        }
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
    }

    public boolean a(int i, String str, String str2) {
        if (i == this.an && TextUtils.equals(str, this.ao) && TextUtils.equals(str2, this.ap)) {
            return false;
        }
        this.an = i;
        this.ao = str;
        this.ap = str2;
        return true;
    }

    public List<Device> r() {
        if (HomeManager.a().h()) {
            return HomeManager.a().y();
        }
        return this.aq;
    }

    /* access modifiers changed from: private */
    public String B() {
        if (!CoreApi.a().q()) {
            return "device_tag_shared_prefs";
        }
        return MD5.d(CoreApi.a().s()) + JSMethod.NOT_SET + "device_tag_shared_prefs";
    }

    public synchronized int f() {
        if (HomeManager.a().h()) {
            return HomeManager.a().z();
        } else if (this.an == -1) {
            return 0;
        } else {
            if (this.aq == null) {
                return 0;
            }
            return this.aq.size();
        }
    }

    public String b() {
        if (HomeManager.a().h()) {
            return HomeManager.a().w();
        }
        if (this.an == -1) {
            return SHApplication.getAppContext().getString(R.string.tag_all_devices);
        }
        return this.ao;
    }

    public String u() {
        if (HomeManager.a().h()) {
            return HomeManager.a().x();
        }
        if (this.an == -1) {
            return "";
        }
        if (this.an == 2) {
            return this.ap;
        }
        return this.ao;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void e() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x003f }
            boolean r0 = r0.q()     // Catch:{ all -> 0x003f }
            if (r0 != 0) goto L_0x000d
            monitor-exit(r2)
            return
        L_0x000d:
            boolean r0 = r2.ay     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0013
            monitor-exit(r2)
            return
        L_0x0013:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ all -> 0x003f }
            com.xiaomi.smarthome.device.SmartHomeDeviceManager$IClientDeviceListener r1 = r2.ab     // Catch:{ all -> 0x003f }
            r0.a((com.xiaomi.smarthome.device.SmartHomeDeviceManager.IClientDeviceListener) r1)     // Catch:{ all -> 0x003f }
            r0 = 1
            r2.ay = r0     // Catch:{ all -> 0x003f }
            r2.N()     // Catch:{ all -> 0x003f }
            boolean r0 = r2.ax     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0032
            r2.D()     // Catch:{ all -> 0x003f }
            com.xiaomi.smarthome.lite.LiteDeviceManager r0 = com.xiaomi.smarthome.lite.LiteDeviceManager.a()     // Catch:{ all -> 0x003f }
            r1 = 0
            r0.b((com.xiaomi.smarthome.device.api.Callback<java.lang.Void>) r1)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0032:
            com.xiaomi.smarthome.device.utils.DeviceTagManager$2 r0 = new com.xiaomi.smarthome.device.utils.DeviceTagManager$2     // Catch:{ all -> 0x003f }
            r0.<init>()     // Catch:{ all -> 0x003f }
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x003f }
            com.xiaomi.smarthome.library.common.util.AsyncTaskUtils.a(r0, r1)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r2)
            return
        L_0x003f:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.e():void");
    }

    /* access modifiers changed from: private */
    public synchronized void a(ParseData parseData) {
        if (parseData == null) {
            try {
                D();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            b(parseData);
            D();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b(ParseData parseData) {
        if (parseData != null) {
            if (parseData.b > 0) {
                this.at = parseData.b;
            }
            if (parseData.c > 0) {
                this.au = parseData.c;
            }
            this.al = parseData.f15469a;
            if (parseData.d != null && !parseData.d.isEmpty()) {
                for (String next : parseData.d.keySet()) {
                    RouterTagInfo routerTagInfo = parseData.d.get(next);
                    RouterTagInfo routerTagInfo2 = this.ar.get(a(next, routerTagInfo.c, 3));
                    if (routerTagInfo2 == null) {
                        this.ar.put(next, routerTagInfo);
                    } else {
                        routerTagInfo2.a(routerTagInfo);
                    }
                }
            }
            if (parseData.e != null) {
                this.aA.put(4, parseData.e);
            }
            this.az = parseData.f;
            this.aB = parseData.g;
            this.aC = parseData.h;
            m(DeviceTagInterface.x);
        }
    }

    /* access modifiers changed from: private */
    public ParseData l(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ParseData parseData = new ParseData();
            JSONObject jSONObject = new JSONObject(str);
            parseData.b = jSONObject.optLong(DeviceTagInterface.b);
            e(parseData, jSONObject.optJSONArray(DeviceTagInterface.f15434a));
            a(parseData, jSONObject.optJSONArray(DeviceTagInterface.i));
            a(parseData, jSONObject.optJSONObject("order"));
            parseData.c = jSONObject.optLong(DeviceTagInterface.l);
            return parseData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public ParseData a(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ParseData parseData = new ParseData();
            JSONObject jSONObject = new JSONObject(str);
            parseData.f15469a = jSONObject.optInt(DeviceTagInterface.m);
            parseData.c = jSONObject.optLong(DeviceTagInterface.l);
            a(str2, parseData);
            b(str3, parseData);
            c(str4, parseData);
            return parseData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void m(String str) {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(str));
    }

    private void a(ParseData parseData, JSONArray jSONArray) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    b(parseData, optJSONObject);
                }
            }
        }
    }

    private void a(ParseData parseData, JSONObject jSONObject) {
        if (jSONObject != null) {
            b(parseData, jSONObject.optJSONArray("dt"));
            c(parseData, jSONObject.optJSONArray(DeviceTagInterface.p));
            d(parseData, jSONObject.optJSONArray(DeviceTagInterface.q));
        }
    }

    private synchronized void b(ParseData parseData, JSONArray jSONArray) {
        if (jSONArray != null) {
            parseData.f = new HashSet();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    parseData.f.add(Integer.valueOf(optJSONObject.optInt("t")));
                }
            }
        }
    }

    private void c(ParseData parseData, JSONArray jSONArray) {
        if (jSONArray != null) {
            parseData.g = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    parseData.g.add(Integer.valueOf(optJSONObject.optInt("t")));
                }
            }
        }
    }

    private void d(ParseData parseData, JSONArray jSONArray) {
        JSONArray optJSONArray;
        if (jSONArray != null) {
            parseData.h = new ConcurrentHashMap();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                int optInt = optJSONObject.optInt("t");
                if (!(optJSONObject == null || (optJSONArray = optJSONObject.optJSONArray(DeviceTagInterface.q)) == null)) {
                    ArrayList arrayList = new ArrayList();
                    int length2 = optJSONArray.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            String optString = optJSONObject2.optString("tag");
                            if (!TextUtils.isEmpty(optString)) {
                                arrayList.add(optString);
                            }
                        }
                    }
                    parseData.h.put(Integer.valueOf(optInt), arrayList);
                }
            }
        }
    }

    private void b(ParseData parseData, JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("did");
            JSONArray optJSONArray = jSONObject.optJSONArray("tag");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    String optString2 = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString2)) {
                        if (parseData.e == null) {
                            parseData.e = new ConcurrentHashMap();
                        }
                        Set set = parseData.e.get(optString2);
                        if (set == null) {
                            set = new HashSet();
                            parseData.e.put(optString2, set);
                        }
                        if (!TextUtils.isEmpty(optString)) {
                            set.add(optString);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void C() {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                UserConfigApi.a().a(SHApplication.getAppContext(), 0, new String[]{"2"}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(ArrayList<UserConfig> arrayList) {
                        DeviceTagManager.this.a(arrayList);
                    }

                    public void onFailure(Error error) {
                        DeviceTagManager.this.aG.post(new Runnable() {
                            public void run() {
                                boolean unused = DeviceTagManager.this.ay = false;
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final ParseConfigData parseConfigData) {
        if (parseConfigData != null) {
            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    JSONArray jSONArray = new JSONArray();
                    DeviceTagManager.this.a(parseConfigData.c, parseConfigData.d, jSONArray);
                    DeviceTagManager.this.a(parseConfigData.e, parseConfigData.f, jSONArray);
                    DeviceTagManager.this.a(parseConfigData.g, parseConfigData.h, jSONArray);
                    UserConfigApi.a().a(SHApplication.getAppContext(), 0, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                            DeviceTagManager.this.a(parseConfigData, map);
                            for (int i = 0; i < DeviceTagManager.this.aF.size(); i++) {
                                if (DeviceTagManager.this.aF.get(i) != null) {
                                    ((DeviceTagInterface.IDeviceTagListener) DeviceTagManager.this.aF.get(i)).a();
                                }
                            }
                        }

                        public void onFailure(Error error) {
                            boolean unused = DeviceTagManager.this.ay = false;
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, int i2, JSONArray jSONArray) {
        if (i2 > 0 && i > 0) {
            for (int i3 = i; i3 < i + i2; i3++) {
                jSONArray.put(i3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(ParseConfigData parseConfigData, Map<Integer, UserConfig.UserConfigData> map) {
        if (map == null || map.isEmpty()) {
            x();
            return;
        }
        String a2 = a(parseConfigData.c, parseConfigData.d, map);
        String a3 = a(parseConfigData.e, parseConfigData.f, map);
        String a4 = a(parseConfigData.g, parseConfigData.h, map);
        final ParseData parseData = new ParseData();
        a(a2, parseData);
        b(a3, parseData);
        c(a4, parseData);
        parseData.c = parseConfigData.b;
        parseData.f15469a = parseConfigData.f15468a;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DeviceTagInterface.m, parseConfigData.f15468a);
            jSONObject.put(DeviceTagInterface.l, parseConfigData.b);
            SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(B(), 0).edit();
            edit.putString(DeviceTagInterface.u, jSONObject.toString());
            edit.putString(DeviceTagInterface.w, a2);
            edit.putString(DeviceTagInterface.i, a3);
            edit.putString(DeviceTagInterface.v, a4);
            edit.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.aG.post(new Runnable() {
            public void run() {
                DeviceTagManager.this.b(parseData);
                boolean unused = DeviceTagManager.this.aw = true;
                boolean unused2 = DeviceTagManager.this.ay = false;
                DeviceTagManager.this.x();
            }
        });
    }

    private String a(int i, int i2, Map<Integer, UserConfig.UserConfigData> map) {
        String str = "";
        int i3 = i2 + i;
        while (i < i3) {
            UserConfig.UserConfigData userConfigData = map.get(Integer.valueOf(i));
            if (userConfigData == null) {
                return null;
            }
            str = str + userConfigData.c;
            i++;
        }
        return str;
    }

    private void D() {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(1000);
                jSONArray.put(3001);
                jSONArray.put(1001);
                jSONArray.put(2001);
                UserConfigApi.a().a(SHApplication.getAppContext(), 0, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                        if (map == null || map.size() <= 0) {
                            DeviceTagManager.this.C();
                        } else {
                            DeviceTagManager.this.a(map);
                        }
                    }

                    public void onFailure(Error error) {
                        DeviceTagManager.this.aG.post(new Runnable() {
                            public void run() {
                                boolean unused = DeviceTagManager.this.ay = false;
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Map<Integer, UserConfig.UserConfigData> map) {
        if (map != null && !map.isEmpty()) {
            try {
                final ParseConfigData parseConfigData = new ParseConfigData();
                UserConfig.UserConfigData userConfigData = map.get(1000);
                if (userConfigData != null && !TextUtils.isEmpty(userConfigData.c)) {
                    JSONObject jSONObject = new JSONObject(userConfigData.c);
                    parseConfigData.f15468a = jSONObject.optInt(DeviceTagInterface.m);
                    parseConfigData.b = jSONObject.optLong(DeviceTagInterface.l);
                }
                UserConfig.UserConfigData userConfigData2 = map.get(3001);
                if (userConfigData2 != null && !TextUtils.isEmpty(userConfigData2.c)) {
                    JSONObject jSONObject2 = new JSONObject(userConfigData2.c);
                    parseConfigData.c = jSONObject2.optInt("ts");
                    parseConfigData.d = jSONObject2.optInt("tc");
                }
                UserConfig.UserConfigData userConfigData3 = map.get(1001);
                if (userConfigData3 != null && !TextUtils.isEmpty(userConfigData3.c)) {
                    JSONObject jSONObject3 = new JSONObject(userConfigData3.c);
                    parseConfigData.e = jSONObject3.optInt("ts");
                    parseConfigData.f = jSONObject3.optInt("tc");
                }
                UserConfig.UserConfigData userConfigData4 = map.get(2001);
                if (userConfigData4 != null && !TextUtils.isEmpty(userConfigData4.c)) {
                    JSONObject jSONObject4 = new JSONObject(userConfigData4.c);
                    parseConfigData.g = jSONObject4.optInt("ts");
                    parseConfigData.h = jSONObject4.optInt("tc");
                }
                this.aG.post(new Runnable() {
                    public void run() {
                        if (DeviceTagManager.this.al < parseConfigData.f15468a || (DeviceTagManager.this.al == parseConfigData.f15468a && DeviceTagManager.this.au < parseConfigData.b)) {
                            DeviceTagManager.this.a(parseConfigData);
                            return;
                        }
                        boolean unused = DeviceTagManager.this.aw = true;
                        boolean unused2 = DeviceTagManager.this.ay = false;
                        DeviceTagManager.this.x();
                        for (int i = 0; i < DeviceTagManager.this.aF.size(); i++) {
                            if (DeviceTagManager.this.aF.get(i) != null) {
                                ((DeviceTagInterface.IDeviceTagListener) DeviceTagManager.this.aF.get(i)).a();
                            }
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                this.ay = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(ArrayList<UserConfig> arrayList) {
        final ParseData parseData;
        UserConfig userConfig;
        if (arrayList != null && arrayList.size() > 0 && (userConfig = arrayList.get(0)) != null && userConfig.D != null && userConfig.D.size() > 0 && DeviceTagInterface.f15434a.equals(userConfig.D.get(0).f16437a)) {
            String str = userConfig.D.get(0).b;
            if (!StringUtil.c(str)) {
                parseData = l(str);
                if (parseData == null) {
                    return;
                }
                this.aG.post(new Runnable() {
                    public void run() {
                        boolean unused = DeviceTagManager.this.ay = false;
                        boolean unused2 = DeviceTagManager.this.aw = true;
                        if (parseData != null) {
                            if (!DeviceTagManager.this.aw || parseData.c <= 0 || parseData.c > DeviceTagManager.this.au) {
                                DeviceTagManager.this.b(parseData);
                                DeviceTagManager.this.c(1);
                            } else {
                                DeviceTagManager.this.c(1);
                                DeviceTagManager.this.x();
                                return;
                            }
                        }
                        DeviceTagManager.this.x();
                    }
                });
            }
        }
        parseData = null;
        this.aG.post(new Runnable() {
            public void run() {
                boolean unused = DeviceTagManager.this.ay = false;
                boolean unused2 = DeviceTagManager.this.aw = true;
                if (parseData != null) {
                    if (!DeviceTagManager.this.aw || parseData.c <= 0 || parseData.c > DeviceTagManager.this.au) {
                        DeviceTagManager.this.b(parseData);
                        DeviceTagManager.this.c(1);
                    } else {
                        DeviceTagManager.this.c(1);
                        DeviceTagManager.this.x();
                        return;
                    }
                }
                DeviceTagManager.this.x();
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void c(int i) {
        if (this.aw) {
            JSONObject E = E();
            JSONObject F = F();
            JSONObject G = G();
            this.al = i;
            a(E, F, G);
            a(E, F, G, i);
        }
    }

    private synchronized JSONObject E() {
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        for (String str : this.ar.keySet()) {
            jSONArray.put(this.ar.get(str).a());
        }
        jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceTagInterface.f15434a, jSONArray);
            jSONObject.put(DeviceTagInterface.b, this.at);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void a(String str, ParseData parseData) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray optJSONArray = jSONObject.optJSONArray(DeviceTagInterface.f15434a);
                if (optJSONArray != null) {
                    parseData.b = jSONObject.optLong(DeviceTagInterface.b);
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            RouterTagInfo routerTagInfo = new RouterTagInfo();
                            routerTagInfo.a(optJSONObject);
                            if (o(routerTagInfo.f15470a)) {
                                if (parseData.d == null) {
                                    parseData.d = new ConcurrentHashMap();
                                }
                                parseData.d.put(routerTagInfo.f15470a, routerTagInfo);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject F() {
        JSONArray jSONArray = new JSONArray();
        Map<String, Set<String>> a2 = a(4);
        if (a2 != null && !a2.isEmpty()) {
            for (String next : a2.keySet()) {
                jSONArray.put(b(next, a2.get(next)));
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tag", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject b(String str, Set<String> set) {
        JSONArray jSONArray = new JSONArray();
        if (set != null && set.size() > 0) {
            for (String put : set) {
                jSONArray.put(put);
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void b(String str, ParseData parseData) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray optJSONArray = new JSONObject(str).optJSONArray("tag");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            Iterator<String> keys = optJSONObject.keys();
                            if (keys.hasNext()) {
                                String next = keys.next();
                                if (!TextUtils.isEmpty(next)) {
                                    Set<String> a2 = a(optJSONObject.optJSONArray(next));
                                    if (parseData.e == null) {
                                        parseData.e = new ConcurrentHashMap();
                                    }
                                    parseData.e.put(next, a2);
                                }
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> a(JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        if (jSONArray == null) {
            return hashSet;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                hashSet.add(optString);
            }
        }
        return hashSet;
    }

    private synchronized JSONObject G() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        try {
            if (this.az != null) {
                JSONArray jSONArray = new JSONArray();
                for (Integer put : this.az) {
                    jSONArray.put(put);
                }
                jSONObject.put("dt", jSONArray);
            }
            if (this.aB != null && !this.aB.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (Integer put2 : this.aB) {
                    jSONArray2.put(put2);
                }
                jSONObject.put(DeviceTagInterface.p, jSONArray2);
            }
            if (this.aC != null && !this.aC.isEmpty()) {
                JSONArray jSONArray3 = new JSONArray();
                for (Integer intValue : this.aC.keySet()) {
                    int intValue2 = intValue.intValue();
                    List<String> list = this.aC.get(Integer.valueOf(intValue2));
                    if (list != null && !list.isEmpty()) {
                        JSONArray jSONArray4 = new JSONArray();
                        for (String put3 : list) {
                            jSONArray4.put(put3);
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("t", intValue2);
                        jSONObject2.put(DeviceTagInterface.q, jSONArray4);
                        jSONArray3.put(jSONObject2);
                    }
                }
                jSONObject.put(DeviceTagInterface.q, jSONArray3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void c(String str, ParseData parseData) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray optJSONArray = jSONObject.optJSONArray("dt");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        int optInt = optJSONArray.optInt(i);
                        if (parseData.f == null) {
                            parseData.f = new HashSet();
                        }
                        parseData.f.add(Integer.valueOf(optInt));
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray(DeviceTagInterface.p);
                if (optJSONArray2 != null) {
                    int length2 = optJSONArray2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        int optInt2 = optJSONArray2.optInt(i2);
                        if (parseData.g == null) {
                            parseData.g = new ArrayList();
                        }
                        parseData.g.add(Integer.valueOf(optInt2));
                    }
                }
                JSONArray optJSONArray3 = jSONObject.optJSONArray(DeviceTagInterface.q);
                if (optJSONArray3 != null) {
                    int length3 = optJSONArray3.length();
                    for (int i3 = 0; i3 < length3; i3++) {
                        JSONObject optJSONObject = optJSONArray3.optJSONObject(i3);
                        if (optJSONObject != null) {
                            int optInt3 = optJSONObject.optInt("t");
                            JSONArray optJSONArray4 = optJSONObject.optJSONArray(DeviceTagInterface.q);
                            if (optJSONArray4 != null) {
                                if (parseData.h == null) {
                                    parseData.h = new ConcurrentHashMap();
                                }
                                parseData.h.put(Integer.valueOf(optInt3), b(optJSONArray4));
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> b(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    private void a(final JSONObject jSONObject, final JSONObject jSONObject2, final JSONObject jSONObject3) {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                try {
                    JSONObject i = DeviceTagManager.this.H();
                    SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(DeviceTagManager.this.B(), 0).edit();
                    if (i != null) {
                        edit.putString(DeviceTagInterface.u, i.toString());
                    }
                    if (jSONObject != null) {
                        edit.putString(DeviceTagInterface.w, jSONObject.toString());
                    }
                    if (jSONObject2 != null) {
                        edit.putString(DeviceTagInterface.i, jSONObject2.toString());
                    }
                    if (jSONObject3 != null) {
                        edit.putString(DeviceTagInterface.v, jSONObject3.toString());
                    }
                    edit.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized JSONObject H() throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        int i = 1;
        if (this.al >= 1) {
            i = this.al;
        }
        jSONObject.put(DeviceTagInterface.m, i);
        jSONObject.put(DeviceTagInterface.l, this.au);
        return jSONObject;
    }

    private void a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, int i) {
        final JSONObject jSONObject4 = jSONObject;
        final JSONObject jSONObject5 = jSONObject2;
        final JSONObject jSONObject6 = jSONObject3;
        final int i2 = i;
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    DeviceTagManager.this.a(jSONObject4, 3001, 1000, 2048, jSONArray);
                    DeviceTagManager.this.a(jSONObject5, 1001, 1000, 2048, jSONArray);
                    DeviceTagManager.this.a(jSONObject6, 2001, 1000, 2048, jSONArray);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(DeviceTagInterface.m, i2);
                    jSONObject.put(DeviceTagInterface.l, DeviceTagManager.this.au);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("component_id", 0);
                    jSONObject2.put("key", Tags.LuckyShake.VALUE_SUCCESS_CODE);
                    jSONObject2.put("data", jSONObject);
                    jSONArray.put(jSONObject2);
                    UserConfigApi.a().a(SHApplication.getAppContext(), jSONArray, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject, int i, int i2, int i3, JSONArray jSONArray) throws JSONException {
        int i4;
        String jSONObject2 = jSONObject.toString();
        int length = jSONObject2.length();
        if (length > i3) {
            int i5 = length / i3;
            i4 = length % i3 != 0 ? i5 + 1 : i5;
        } else {
            i4 = 1;
        }
        if (i4 + 1 <= i2) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("component_id", 0);
            jSONObject3.put("key", "" + i);
            int i6 = i + 1;
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("ts", i6);
            jSONObject4.put("tc", i4);
            jSONObject3.put("data", jSONObject4.toString());
            jSONArray.put(jSONObject3);
            int i7 = i6;
            int i8 = 0;
            for (int i9 = 0; i9 < i4; i9++) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("component_id", 0);
                jSONObject5.put("key", "" + i7);
                if (i9 == i4 - 1) {
                    jSONObject5.put("data", jSONObject2.substring(i8));
                } else {
                    jSONObject5.put("data", jSONObject2.substring(i8, i8 + i3));
                }
                jSONArray.put(jSONObject5);
                i8 += i3;
                i7++;
            }
        }
    }

    public boolean a() {
        if (HomeManager.a().h()) {
            return HomeManager.a().u();
        }
        return this.an != -1;
    }

    public synchronized void a(List<Device> list) {
        if (this.aA != null) {
            this.aA.remove(2);
            this.aA.remove(0);
        }
        for (Device b : list) {
            b(b);
        }
        e(list);
    }

    public synchronized void b(List<Device> list) {
        if (list != null) {
            for (Device b : list) {
                b(b);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00dc, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void e(java.util.List<com.xiaomi.smarthome.device.Device> r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x00dd }
            boolean r0 = r0.q()     // Catch:{ all -> 0x00dd }
            if (r0 != 0) goto L_0x000d
            monitor-exit(r7)
            return
        L_0x000d:
            int r0 = r7.an     // Catch:{ all -> 0x00dd }
            r1 = -1
            if (r0 == r1) goto L_0x00db
            java.lang.String r0 = r7.ao     // Catch:{ all -> 0x00dd }
            boolean r0 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r0)     // Catch:{ all -> 0x00dd }
            if (r0 != 0) goto L_0x00db
            java.lang.String r0 = r7.ao     // Catch:{ all -> 0x00dd }
            android.content.Context r2 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ all -> 0x00dd }
            r3 = 2131499523(0x7f0c1a03, float:1.8622698E38)
            java.lang.String r2 = r2.getString(r3)     // Catch:{ all -> 0x00dd }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x00dd }
            if (r0 == 0) goto L_0x002f
            goto L_0x00db
        L_0x002f:
            java.lang.String r0 = r7.ao     // Catch:{ all -> 0x00dd }
            int r2 = r7.an     // Catch:{ all -> 0x00dd }
            r3 = 2
            if (r2 != r3) goto L_0x0038
            java.lang.String r0 = r7.ap     // Catch:{ all -> 0x00dd }
        L_0x0038:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r2 = r7.aA     // Catch:{ all -> 0x00dd }
            int r4 = r7.an     // Catch:{ all -> 0x00dd }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00dd }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ all -> 0x00dd }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x00dd }
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x0086
            boolean r6 = r2.isEmpty()     // Catch:{ all -> 0x00dd }
            if (r6 != 0) goto L_0x0086
            boolean r6 = r2.containsKey(r0)     // Catch:{ all -> 0x00dd }
            if (r6 == 0) goto L_0x0086
            java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x00dd }
            java.util.Set r0 = (java.util.Set) r0     // Catch:{ all -> 0x00dd }
            if (r0 == 0) goto L_0x0083
            boolean r2 = r0.isEmpty()     // Catch:{ all -> 0x00dd }
            if (r2 != 0) goto L_0x0083
            java.util.Set r8 = r7.f((java.util.List<com.xiaomi.smarthome.device.Device>) r8)     // Catch:{ all -> 0x00dd }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00dd }
        L_0x006c:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x00dd }
            if (r2 == 0) goto L_0x0081
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x00dd }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x00dd }
            boolean r2 = r8.contains(r2)     // Catch:{ all -> 0x00dd }
            if (r2 == 0) goto L_0x006c
            int r4 = r4 + 1
            goto L_0x006c
        L_0x0081:
            r8 = r4
            goto L_0x0084
        L_0x0083:
            r8 = 0
        L_0x0084:
            r4 = 1
            goto L_0x0087
        L_0x0086:
            r8 = 0
        L_0x0087:
            if (r4 != 0) goto L_0x0092
            r7.an = r1     // Catch:{ all -> 0x00dd }
            r0 = 0
            r7.ao = r0     // Catch:{ all -> 0x00dd }
            r7.ap = r0     // Catch:{ all -> 0x00dd }
            r7.aq = r0     // Catch:{ all -> 0x00dd }
        L_0x0092:
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x00dd }
            java.lang.String r1 = "device_tag_selected_action"
            r0.<init>(r1)     // Catch:{ all -> 0x00dd }
            java.lang.String r1 = "param_device_tag_type"
            int r2 = r7.an     // Catch:{ all -> 0x00dd }
            r0.putExtra(r1, r2)     // Catch:{ all -> 0x00dd }
            java.lang.String r1 = "tag_selected_param"
            r0.putExtra(r1, r5)     // Catch:{ all -> 0x00dd }
            java.lang.String r1 = r7.ao     // Catch:{ all -> 0x00dd }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00dd }
            if (r1 != 0) goto L_0x00b4
            java.lang.String r1 = "device_tag_param"
            java.lang.String r2 = r7.ao     // Catch:{ all -> 0x00dd }
            r0.putExtra(r1, r2)     // Catch:{ all -> 0x00dd }
        L_0x00b4:
            int r1 = r7.an     // Catch:{ all -> 0x00dd }
            if (r1 != r3) goto L_0x00c7
            java.lang.String r1 = r7.ap     // Catch:{ all -> 0x00dd }
            boolean r1 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r1)     // Catch:{ all -> 0x00dd }
            if (r1 != 0) goto L_0x00c7
            java.lang.String r1 = "router_bssid_param"
            java.lang.String r2 = r7.ap     // Catch:{ all -> 0x00dd }
            r0.putExtra(r1, r2)     // Catch:{ all -> 0x00dd }
        L_0x00c7:
            if (r8 <= 0) goto L_0x00ce
            java.lang.String r1 = "param_device_count"
            r0.putExtra(r1, r8)     // Catch:{ all -> 0x00dd }
        L_0x00ce:
            android.content.Context r8 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ all -> 0x00dd }
            android.support.v4.content.LocalBroadcastManager r8 = android.support.v4.content.LocalBroadcastManager.getInstance(r8)     // Catch:{ all -> 0x00dd }
            r8.sendBroadcast(r0)     // Catch:{ all -> 0x00dd }
            monitor-exit(r7)
            return
        L_0x00db:
            monitor-exit(r7)
            return
        L_0x00dd:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.e(java.util.List):void");
    }

    private Set<String> f(List<Device> list) {
        HashSet hashSet = new HashSet();
        if (list != null && !list.isEmpty()) {
            for (Device next : list) {
                if (!TextUtils.isEmpty(next.did)) {
                    hashSet.add(next.did);
                }
            }
        }
        return hashSet;
    }

    private synchronized void b(Device device) {
        PluginDeviceInfo c;
        PluginRecord d = CoreApi.a().d(device.model);
        if (!(d == null || (c = d.c()) == null)) {
            String x = c.x();
            if (!TextUtils.isEmpty(x)) {
                c(0, x, device.did);
            }
        }
        String str = device.bssid;
        String str2 = device.ssid;
        int i = !device.isOnline;
        if (TextUtils.isEmpty(str2) && (device instanceof RouterDevice)) {
            str2 = device.name;
            i = 2;
        }
        if (!StringUtil.c(str) && !StringUtil.c(str2)) {
            String upperCase = str.toUpperCase();
            if (o(upperCase)) {
                c(2, a(upperCase, str2, i), device.did);
            }
        }
    }

    private synchronized void c(int i, String str, String str2) {
        Map map = this.aA.get(Integer.valueOf(i));
        if (map == null) {
            map = new HashMap();
            this.aA.put(Integer.valueOf(i), map);
        }
        Set set = (Set) map.get(str);
        if (set == null) {
            set = new HashSet();
            map.put(str, set);
        }
        if (!TextUtils.isEmpty(str2)) {
            set.add(str2);
        }
    }

    public synchronized void a(String str, Set<String> set) {
        if (!TextUtils.isEmpty(str)) {
            Map map = this.aA.get(4);
            if (map == null) {
                map = new HashMap();
                this.aA.put(4, map);
            }
            if (!map.containsKey(str)) {
                map.put(str, set);
                if (this.aC == null) {
                    this.aC = new HashMap();
                }
                List list = this.aC.get(4);
                if (list == null) {
                    list = new ArrayList();
                    this.aC.put(4, list);
                }
                list.add(str);
            } else if (set != null && !set.isEmpty()) {
                Set set2 = (Set) map.get(str);
                if (set2 != null) {
                    set2.addAll(set);
                } else {
                    map.put(str, set);
                }
            }
            m(DeviceTagInterface.y);
            K();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:67:0x011b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.util.Set<java.lang.String> r7, java.lang.String r8, boolean r9, com.xiaomi.smarthome.homeroom.HomeManager.IHomeOperationCallback r10) {
        /*
            r6 = this;
            monitor-enter(r6)
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x011c }
            boolean r0 = r0.h()     // Catch:{ all -> 0x011c }
            if (r0 == 0) goto L_0x0014
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x011c }
            r0.a((java.util.Set<java.lang.String>) r7, (java.lang.String) r8, (boolean) r9, (com.xiaomi.smarthome.homeroom.HomeManager.IHomeOperationCallback) r10)     // Catch:{ all -> 0x011c }
            monitor-exit(r6)
            return
        L_0x0014:
            if (r7 == 0) goto L_0x011a
            boolean r10 = r7.isEmpty()     // Catch:{ all -> 0x011c }
            if (r10 == 0) goto L_0x001e
            goto L_0x011a
        L_0x001e:
            java.util.Iterator r10 = r7.iterator()     // Catch:{ all -> 0x011c }
            java.lang.Object r10 = r10.next()     // Catch:{ all -> 0x011c }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x011c }
            boolean r0 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x011c }
            if (r0 == 0) goto L_0x0030
            monitor-exit(r6)
            return
        L_0x0030:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r0 = r6.aA     // Catch:{ all -> 0x011c }
            r1 = 4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x011c }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x011c }
            if (r0 != 0) goto L_0x0052
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x011c }
            r0.<init>()     // Catch:{ all -> 0x011c }
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r2 = r6.aA     // Catch:{ all -> 0x011c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            r2.put(r3, r0)     // Catch:{ all -> 0x011c }
            if (r9 == 0) goto L_0x00b4
            r6.aD = r10     // Catch:{ all -> 0x011c }
            goto L_0x00b4
        L_0x0052:
            boolean r2 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x011c }
            if (r2 != 0) goto L_0x00b4
            r2 = 0
            java.util.Set r3 = r0.entrySet()     // Catch:{ all -> 0x011c }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x011c }
        L_0x0061:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x011c }
            if (r4 == 0) goto L_0x00ae
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x011c }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x011c }
            java.lang.Object r5 = r4.getKey()     // Catch:{ all -> 0x011c }
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ all -> 0x011c }
            boolean r5 = android.text.TextUtils.equals(r5, r10)     // Catch:{ all -> 0x011c }
            if (r5 == 0) goto L_0x0092
            java.lang.Object r5 = r4.getValue()     // Catch:{ all -> 0x011c }
            if (r5 == 0) goto L_0x008e
            java.lang.Object r5 = r4.getValue()     // Catch:{ all -> 0x011c }
            java.util.Set r5 = (java.util.Set) r5     // Catch:{ all -> 0x011c }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x011c }
            if (r5 == 0) goto L_0x008c
            goto L_0x008e
        L_0x008c:
            r2 = 1
            goto L_0x0092
        L_0x008e:
            if (r9 == 0) goto L_0x0092
            r6.aD = r10     // Catch:{ all -> 0x011c }
        L_0x0092:
            java.lang.Object r5 = r4.getValue()     // Catch:{ all -> 0x011c }
            if (r5 == 0) goto L_0x0061
            java.lang.Object r5 = r4.getValue()     // Catch:{ all -> 0x011c }
            java.util.Set r5 = (java.util.Set) r5     // Catch:{ all -> 0x011c }
            boolean r5 = r5.contains(r8)     // Catch:{ all -> 0x011c }
            if (r5 == 0) goto L_0x0061
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x011c }
            java.util.Set r4 = (java.util.Set) r4     // Catch:{ all -> 0x011c }
            r4.remove(r8)     // Catch:{ all -> 0x011c }
            goto L_0x0061
        L_0x00ae:
            if (r2 != 0) goto L_0x00b4
            if (r9 == 0) goto L_0x00b4
            r6.aD = r10     // Catch:{ all -> 0x011c }
        L_0x00b4:
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x011c }
        L_0x00b8:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x011c }
            if (r9 == 0) goto L_0x0110
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x011c }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x011c }
            r10 = 0
            boolean r2 = r0.containsKey(r9)     // Catch:{ all -> 0x011c }
            if (r2 == 0) goto L_0x00d2
            java.lang.Object r10 = r0.get(r9)     // Catch:{ all -> 0x011c }
            java.util.Set r10 = (java.util.Set) r10     // Catch:{ all -> 0x011c }
            goto L_0x00fc
        L_0x00d2:
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r2 = r6.aC     // Catch:{ all -> 0x011c }
            if (r2 != 0) goto L_0x00dd
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x011c }
            r2.<init>()     // Catch:{ all -> 0x011c }
            r6.aC = r2     // Catch:{ all -> 0x011c }
        L_0x00dd:
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r2 = r6.aC     // Catch:{ all -> 0x011c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x011c }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x011c }
            if (r2 != 0) goto L_0x00f9
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x011c }
            r2.<init>()     // Catch:{ all -> 0x011c }
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r3 = r6.aC     // Catch:{ all -> 0x011c }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            r3.put(r4, r2)     // Catch:{ all -> 0x011c }
        L_0x00f9:
            r2.add(r9)     // Catch:{ all -> 0x011c }
        L_0x00fc:
            if (r10 != 0) goto L_0x0106
            java.util.HashSet r10 = new java.util.HashSet     // Catch:{ all -> 0x011c }
            r10.<init>()     // Catch:{ all -> 0x011c }
            r0.put(r9, r10)     // Catch:{ all -> 0x011c }
        L_0x0106:
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x011c }
            if (r9 != 0) goto L_0x00b8
            r10.add(r8)     // Catch:{ all -> 0x011c }
            goto L_0x00b8
        L_0x0110:
            java.lang.String r7 = "tag_info_edited_new_action"
            r6.m(r7)     // Catch:{ all -> 0x011c }
            r6.K()     // Catch:{ all -> 0x011c }
            monitor-exit(r6)
            return
        L_0x011a:
            monitor-exit(r6)
            return
        L_0x011c:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.a(java.util.Set, java.lang.String, boolean, com.xiaomi.smarthome.homeroom.HomeManager$IHomeOperationCallback):void");
    }

    public synchronized boolean a(int i, String str) {
        Map map = this.aA.get(Integer.valueOf(i));
        if (map != null) {
            if (!map.isEmpty()) {
                return map.containsKey(str);
            }
        }
        return false;
    }

    public synchronized void a(String str, String str2, Set<String> set) {
        if (!TextUtils.isEmpty(str2)) {
            LiteDeviceManager.a().b(str, str2);
            Map map = this.aA.get(4);
            if (map != null) {
                map.remove(str);
            } else {
                map = new HashMap();
                this.aA.put(4, map);
            }
            if (!map.containsKey(str2)) {
                map.put(str2, set);
            } else if (set != null && !set.isEmpty()) {
                Set set2 = (Set) map.get(str2);
                if (set2 != null) {
                    set2.addAll(set);
                } else {
                    map.put(str2, set);
                }
            }
            if (!TextUtils.equals(str, str2)) {
                if (this.aC != null) {
                    List list = this.aC.get(4);
                    if (list != null) {
                        int size = list.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                break;
                            } else if (TextUtils.equals((CharSequence) list.get(i), str)) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (i < size) {
                            list.remove(i);
                            list.add(i, str2);
                        } else {
                            list.add(str2);
                        }
                    }
                } else {
                    this.aC = new HashMap();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str2);
                    this.aC.put(4, arrayList);
                }
            }
            if (this.an == 4 && TextUtils.equals(this.ao, str)) {
                this.ao = str2;
            }
            m(DeviceTagInterface.y);
            K();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x0072 }
            boolean r0 = r0.h()     // Catch:{ all -> 0x0072 }
            if (r0 == 0) goto L_0x0014
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x0072 }
            r0.u(r4)     // Catch:{ all -> 0x0072 }
            monitor-exit(r3)
            return
        L_0x0014:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r0 = r3.aA     // Catch:{ all -> 0x0072 }
            r1 = 4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0072 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0072 }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x0072 }
            if (r0 == 0) goto L_0x0070
            r0.remove(r4)     // Catch:{ all -> 0x0072 }
            com.xiaomi.smarthome.lite.LiteDeviceManager r0 = com.xiaomi.smarthome.lite.LiteDeviceManager.a()     // Catch:{ all -> 0x0072 }
            r0.a((java.lang.String) r4)     // Catch:{ all -> 0x0072 }
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r0 = r3.aC     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x0038
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0072 }
            r0.<init>()     // Catch:{ all -> 0x0072 }
            r3.aC = r0     // Catch:{ all -> 0x0072 }
        L_0x0038:
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r0 = r3.aC     // Catch:{ all -> 0x0072 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0072 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0072 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x0054
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0072 }
            r0.<init>()     // Catch:{ all -> 0x0072 }
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r2 = r3.aC     // Catch:{ all -> 0x0072 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0072 }
            r2.put(r1, r0)     // Catch:{ all -> 0x0072 }
        L_0x0054:
            r0.remove(r4)     // Catch:{ all -> 0x0072 }
            java.lang.String r0 = r3.ao     // Catch:{ all -> 0x0072 }
            boolean r4 = android.text.TextUtils.equals(r4, r0)     // Catch:{ all -> 0x0072 }
            if (r4 == 0) goto L_0x0068
            r4 = 0
            r3.ao = r4     // Catch:{ all -> 0x0072 }
            r0 = -1
            r3.an = r0     // Catch:{ all -> 0x0072 }
            r3.f((java.lang.String) r4, (java.lang.String) r4)     // Catch:{ all -> 0x0072 }
        L_0x0068:
            java.lang.String r4 = "tag_info_edited_action"
            r3.m(r4)     // Catch:{ all -> 0x0072 }
            r3.K()     // Catch:{ all -> 0x0072 }
        L_0x0070:
            monitor-exit(r3)
            return
        L_0x0072:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.a(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x0045
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x000e
            goto L_0x0045
        L_0x000e:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r0 = r2.aA     // Catch:{ all -> 0x0047 }
            r1 = 4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0047 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0047 }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0043
            boolean r1 = r0.isEmpty()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0043
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0047 }
            java.util.Set r0 = (java.util.Set) r0     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0043
            boolean r1 = r0.contains(r4)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0043
            r0.remove(r4)     // Catch:{ all -> 0x0047 }
            com.xiaomi.smarthome.lite.LiteDeviceManager r0 = com.xiaomi.smarthome.lite.LiteDeviceManager.a()     // Catch:{ all -> 0x0047 }
            r0.a((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "tag_info_edited_action"
            r2.m(r3)     // Catch:{ all -> 0x0047 }
            r2.K()     // Catch:{ all -> 0x0047 }
        L_0x0043:
            monitor-exit(r2)
            return
        L_0x0045:
            monitor-exit(r2)
            return
        L_0x0047:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.c(java.lang.String, java.lang.String):void");
    }

    public Map<String, Set<String>> a(int i) {
        HashSet hashSet;
        HashMap hashMap = new HashMap();
        Map map = this.aA.get(Integer.valueOf(i));
        if (map != null && map.size() > 0) {
            for (String str : map.keySet()) {
                try {
                    Set set = (Set) map.get(str);
                    if (set == null) {
                        hashSet = new HashSet();
                    } else {
                        hashSet = new HashSet(set);
                    }
                    hashMap.put(str, hashSet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return (i != 4 || !HomeManager.a().h()) ? hashMap : HomeManager.a().t();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c8, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.Map<java.lang.String, java.util.List<java.lang.String>> j(java.lang.String r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap     // Catch:{ all -> 0x00ef }
            r0.<init>()     // Catch:{ all -> 0x00ef }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00ef }
            r1.<init>()     // Catch:{ all -> 0x00ef }
            boolean r2 = W     // Catch:{ all -> 0x00ef }
            r3 = 0
            if (r2 == 0) goto L_0x001f
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r2 = r9.aA     // Catch:{ all -> 0x00ef }
            r4 = 8
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00ef }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ all -> 0x00ef }
        L_0x001c:
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x00ef }
            goto L_0x002a
        L_0x001f:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r2 = r9.aA     // Catch:{ all -> 0x00ef }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00ef }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ all -> 0x00ef }
            goto L_0x001c
        L_0x002a:
            if (r2 == 0) goto L_0x00cd
            int r4 = r2.size()     // Catch:{ all -> 0x00ef }
            if (r4 <= 0) goto L_0x00cd
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Exception -> 0x00c9 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x00c9 }
            com.xiaomi.smarthome.homeroom.HomeManager r2 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ Exception -> 0x00c9 }
            com.xiaomi.smarthome.homeroom.model.Home r2 = r2.j((java.lang.String) r10)     // Catch:{ Exception -> 0x00c9 }
            com.xiaomi.smarthome.homeroom.HomeManager r5 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ Exception -> 0x00c9 }
            boolean[] r3 = new boolean[r3]     // Catch:{ Exception -> 0x00c9 }
            java.util.List r10 = r5.a((java.lang.String) r10, (boolean[]) r3)     // Catch:{ Exception -> 0x00c9 }
            if (r2 == 0) goto L_0x00c7
            if (r10 == 0) goto L_0x00c7
            boolean r2 = r10.isEmpty()     // Catch:{ Exception -> 0x00c9 }
            if (r2 == 0) goto L_0x0055
            goto L_0x00c7
        L_0x0055:
            java.util.Set r2 = r4.entrySet()     // Catch:{ Exception -> 0x00c9 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x00c9 }
        L_0x005d:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x00c9 }
            if (r3 == 0) goto L_0x00cd
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x00c9 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ Exception -> 0x00c9 }
            java.lang.Object r4 = r3.getKey()     // Catch:{ Exception -> 0x00c9 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x00c9 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ Exception -> 0x00c9 }
            java.util.Set r3 = (java.util.Set) r3     // Catch:{ Exception -> 0x00c9 }
            if (r3 != 0) goto L_0x007d
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ Exception -> 0x00c9 }
            r3.<init>()     // Catch:{ Exception -> 0x00c9 }
            goto L_0x0083
        L_0x007d:
            java.util.HashSet r5 = new java.util.HashSet     // Catch:{ Exception -> 0x00c9 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x00c9 }
            r3 = r5
        L_0x0083:
            java.util.LinkedList r5 = new java.util.LinkedList     // Catch:{ Exception -> 0x00c9 }
            r5.<init>()     // Catch:{ Exception -> 0x00c9 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x00c9 }
        L_0x008c:
            boolean r6 = r3.hasNext()     // Catch:{ Exception -> 0x00c9 }
            if (r6 == 0) goto L_0x00b9
            java.lang.Object r6 = r3.next()     // Catch:{ Exception -> 0x00c9 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x00c9 }
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r7 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ Exception -> 0x00c9 }
            com.xiaomi.smarthome.device.Device r7 = r7.b((java.lang.String) r6)     // Catch:{ Exception -> 0x00c9 }
            if (r7 == 0) goto L_0x008c
            boolean r8 = r10.contains(r6)     // Catch:{ Exception -> 0x00c9 }
            if (r8 == 0) goto L_0x008c
            boolean r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.e((com.xiaomi.smarthome.device.Device) r7)     // Catch:{ Exception -> 0x00c9 }
            if (r8 == 0) goto L_0x00b5
            boolean r7 = r7.isHomeShared()     // Catch:{ Exception -> 0x00c9 }
            if (r7 != 0) goto L_0x00b5
            goto L_0x008c
        L_0x00b5:
            r5.add(r6)     // Catch:{ Exception -> 0x00c9 }
            goto L_0x008c
        L_0x00b9:
            boolean r3 = r5.isEmpty()     // Catch:{ Exception -> 0x00c9 }
            if (r3 != 0) goto L_0x005d
            android.util.Pair r3 = android.util.Pair.create(r4, r5)     // Catch:{ Exception -> 0x00c9 }
            r1.add(r3)     // Catch:{ Exception -> 0x00c9 }
            goto L_0x005d
        L_0x00c7:
            monitor-exit(r9)
            return r0
        L_0x00c9:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x00ef }
        L_0x00cd:
            com.xiaomi.smarthome.device.utils.DeviceTagManager$11 r10 = new com.xiaomi.smarthome.device.utils.DeviceTagManager$11     // Catch:{ all -> 0x00ef }
            r10.<init>()     // Catch:{ all -> 0x00ef }
            java.util.Collections.sort(r1, r10)     // Catch:{ all -> 0x00ef }
            java.util.Iterator r10 = r1.iterator()     // Catch:{ all -> 0x00ef }
        L_0x00d9:
            boolean r1 = r10.hasNext()     // Catch:{ all -> 0x00ef }
            if (r1 == 0) goto L_0x00ed
            java.lang.Object r1 = r10.next()     // Catch:{ all -> 0x00ef }
            android.util.Pair r1 = (android.util.Pair) r1     // Catch:{ all -> 0x00ef }
            java.lang.Object r2 = r1.first     // Catch:{ all -> 0x00ef }
            java.lang.Object r1 = r1.second     // Catch:{ all -> 0x00ef }
            r0.put(r2, r1)     // Catch:{ all -> 0x00ef }
            goto L_0x00d9
        L_0x00ed:
            monitor-exit(r9)
            return r0
        L_0x00ef:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.j(java.lang.String):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0093, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.Set<java.lang.String> e(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x009a }
            r0.<init>()     // Catch:{ all -> 0x009a }
            boolean r1 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0094 }
            if (r1 == 0) goto L_0x0013
            java.util.HashSet r5 = new java.util.HashSet     // Catch:{ Exception -> 0x0094 }
            r5.<init>()     // Catch:{ Exception -> 0x0094 }
            monitor-exit(r4)
            return r5
        L_0x0013:
            boolean r1 = W     // Catch:{ Exception -> 0x0094 }
            r2 = 0
            if (r1 == 0) goto L_0x0027
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r1 = r4.aA     // Catch:{ Exception -> 0x0094 }
            r3 = 8
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0094 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ Exception -> 0x0094 }
        L_0x0024:
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ Exception -> 0x0094 }
            goto L_0x0032
        L_0x0027:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r1 = r4.aA     // Catch:{ Exception -> 0x0094 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0094 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ Exception -> 0x0094 }
            goto L_0x0024
        L_0x0032:
            if (r1 == 0) goto L_0x0098
            int r3 = r1.size()     // Catch:{ Exception -> 0x0094 }
            if (r3 <= 0) goto L_0x0098
            java.lang.Object r6 = r1.get(r6)     // Catch:{ Exception -> 0x0094 }
            java.util.Set r6 = (java.util.Set) r6     // Catch:{ Exception -> 0x0094 }
            if (r6 != 0) goto L_0x0048
            java.util.HashSet r6 = new java.util.HashSet     // Catch:{ Exception -> 0x0094 }
            r6.<init>()     // Catch:{ Exception -> 0x0094 }
            goto L_0x004e
        L_0x0048:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ Exception -> 0x0094 }
            r1.<init>(r6)     // Catch:{ Exception -> 0x0094 }
            r6 = r1
        L_0x004e:
            com.xiaomi.smarthome.homeroom.HomeManager r1 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ Exception -> 0x0094 }
            boolean[] r2 = new boolean[r2]     // Catch:{ Exception -> 0x0094 }
            java.util.List r5 = r1.a((java.lang.String) r5, (boolean[]) r2)     // Catch:{ Exception -> 0x0094 }
            if (r5 == 0) goto L_0x0092
            boolean r1 = r5.isEmpty()     // Catch:{ Exception -> 0x0094 }
            if (r1 == 0) goto L_0x0061
            goto L_0x0092
        L_0x0061:
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x0094 }
        L_0x0065:
            boolean r1 = r6.hasNext()     // Catch:{ Exception -> 0x0094 }
            if (r1 == 0) goto L_0x0098
            java.lang.Object r1 = r6.next()     // Catch:{ Exception -> 0x0094 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0094 }
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ Exception -> 0x0094 }
            com.xiaomi.smarthome.device.Device r2 = r2.b((java.lang.String) r1)     // Catch:{ Exception -> 0x0094 }
            if (r2 == 0) goto L_0x0065
            boolean r3 = r5.contains(r1)     // Catch:{ Exception -> 0x0094 }
            if (r3 == 0) goto L_0x0065
            boolean r3 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.e((com.xiaomi.smarthome.device.Device) r2)     // Catch:{ Exception -> 0x0094 }
            if (r3 == 0) goto L_0x008e
            boolean r2 = r2.isHomeShared()     // Catch:{ Exception -> 0x0094 }
            if (r2 != 0) goto L_0x008e
            goto L_0x0065
        L_0x008e:
            r0.add(r1)     // Catch:{ Exception -> 0x0094 }
            goto L_0x0065
        L_0x0092:
            monitor-exit(r4)
            return r0
        L_0x0094:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x009a }
        L_0x0098:
            monitor-exit(r4)
            return r0
        L_0x009a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.e(java.lang.String, java.lang.String):java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0034, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.Set<java.lang.String> b(int r3, java.lang.String r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0035 }
            r1 = 0
            if (r0 == 0) goto L_0x000a
            monitor-exit(r2)
            return r1
        L_0x000a:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r0 = r2.aA     // Catch:{ all -> 0x0035 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0035 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x0035 }
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x0033
            int r0 = r3.size()     // Catch:{ all -> 0x0035 }
            if (r0 <= 0) goto L_0x0033
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0035 }
            java.util.Set r3 = (java.util.Set) r3     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x0033
            int r4 = r3.size()     // Catch:{ all -> 0x0035 }
            if (r4 <= 0) goto L_0x0033
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ all -> 0x0035 }
            r4.<init>(r3)     // Catch:{ all -> 0x0035 }
            monitor-exit(r2)
            return r4
        L_0x0033:
            monitor-exit(r2)
            return r1
        L_0x0035:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.b(int, java.lang.String):java.util.Set");
    }

    public synchronized Set<String> w() {
        HashSet hashSet;
        hashSet = new HashSet();
        Map map = this.aA.get(4);
        if (map != null && map.size() > 0) {
            for (Map.Entry entry : map.entrySet()) {
                if (entry.getValue() != null && !((Set) entry.getValue()).isEmpty()) {
                    hashSet.addAll((Collection) entry.getValue());
                }
            }
        }
        return hashSet;
    }

    public synchronized List<String> o() {
        ArrayList arrayList;
        List<Device> a2 = SmartHomeDeviceHelper.a().a(SmartHomeDeviceManager.a().d());
        arrayList = new ArrayList();
        for (Device next : a2) {
            if (f(next.did).isEmpty()) {
                arrayList.add(next.did);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.lang.String a(java.lang.String r3, java.lang.String r4, int r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo> r0 = r2.ar     // Catch:{ all -> 0x003a }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x003a }
            com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo r0 = (com.xiaomi.smarthome.device.utils.DeviceTagManager.RouterTagInfo) r0     // Catch:{ all -> 0x003a }
            if (r0 != 0) goto L_0x0035
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.as     // Catch:{ all -> 0x003a }
            boolean r0 = r0.containsKey(r3)     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x002f
            java.util.Map<java.lang.String, java.lang.String> r0 = r2.as     // Catch:{ all -> 0x003a }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x003a }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x003a }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo> r0 = r2.ar     // Catch:{ all -> 0x003a }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x003a }
            com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo r0 = (com.xiaomi.smarthome.device.utils.DeviceTagManager.RouterTagInfo) r0     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x002d
            int r1 = r0.f     // Catch:{ all -> 0x003a }
            if (r5 > r1) goto L_0x002d
            r0.c = r4     // Catch:{ all -> 0x003a }
            r0.f = r5     // Catch:{ all -> 0x003a }
        L_0x002d:
            monitor-exit(r2)
            return r3
        L_0x002f:
            java.lang.String r3 = r2.b((java.lang.String) r3, (java.lang.String) r4, (int) r5)     // Catch:{ all -> 0x003a }
            monitor-exit(r2)
            return r3
        L_0x0035:
            r2.c((java.lang.String) r3, (java.lang.String) r4, (int) r5)     // Catch:{ all -> 0x003a }
            monitor-exit(r2)
            return r3
        L_0x003a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.a(java.lang.String, java.lang.String, int):java.lang.String");
    }

    private synchronized String b(String str, String str2, int i) {
        String b = b(str, 1);
        RouterTagInfo routerTagInfo = this.ar.get(b);
        if (routerTagInfo != null) {
            routerTagInfo.b = str;
            if (!TextUtils.isEmpty(str2)) {
                routerTagInfo.c = str2;
            }
            routerTagInfo.f = i;
            this.as.put(str, b);
            return b;
        }
        String a2 = a(str, -1);
        if (a2 != null) {
            return a2;
        }
        String a3 = a(str, 2);
        if (a3 != null) {
            return a3;
        }
        String a4 = a(str, -2);
        if (a4 != null) {
            return a4;
        }
        c(str, str2, i);
        return str;
    }

    private String a(String str, int i) {
        String b = b(str, i);
        RouterTagInfo routerTagInfo = this.ar.get(b);
        if (routerTagInfo == null) {
            return null;
        }
        routerTagInfo.b = str;
        this.as.put(str, b);
        return routerTagInfo.f15470a;
    }

    private String b(String str, int i) {
        String substring = str.substring(0, str.length() - 2);
        Integer valueOf = Integer.valueOf(Integer.valueOf(str.substring(str.length() - 2), 16).intValue() + i);
        return substring + String.format("%2X", new Object[]{valueOf});
    }

    public synchronized String e(String str) {
        String str2 = this.as.containsKey(str) ? this.as.get(str) : str;
        if (this.ar.containsKey(str2)) {
            RouterTagInfo routerTagInfo = this.ar.get(str2);
            String str3 = routerTagInfo.c;
            if (!StringUtil.c(routerTagInfo.d)) {
                str = str3 + " (" + routerTagInfo.d + Operators.BRACKET_END_STR;
            } else if (!StringUtil.c(routerTagInfo.b())) {
                str = str3 + " (" + routerTagInfo.b() + Operators.BRACKET_END_STR;
            } else {
                str = str3;
            }
        }
        return str;
    }

    private synchronized String n(String str) {
        if (!this.as.containsKey(str)) {
            return str;
        }
        return this.as.get(str);
    }

    public synchronized String k(String str) {
        String str2 = this.as.containsKey(str) ? this.as.get(str) : str;
        if (this.ar.containsKey(str2)) {
            RouterTagInfo routerTagInfo = this.ar.get(str2);
            String str3 = routerTagInfo.c;
            if (!StringUtil.c(routerTagInfo.b())) {
                str = str3 + " (" + routerTagInfo.b() + Operators.BRACKET_END_STR;
            } else {
                str = str3;
            }
        }
        return str;
    }

    public synchronized String b(String str) {
        RouterTagInfo routerTagInfo = this.ar.get(this.as.containsKey(str) ? this.as.get(str) : str);
        if (routerTagInfo != null) {
            str = routerTagInfo.c;
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String b(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map<java.lang.String, java.lang.String> r0 = r1.as     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.containsKey(r2)     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0011
            java.util.Map<java.lang.String, java.lang.String> r0 = r1.as     // Catch:{ all -> 0x0029 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0029 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0029 }
        L_0x0011:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo> r0 = r1.ar     // Catch:{ all -> 0x0029 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0029 }
            com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceTagManager.RouterTagInfo) r2     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x0027
            java.lang.String r0 = r2.d     // Catch:{ all -> 0x0029 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0027
            java.lang.String r2 = r2.d     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)
            return r2
        L_0x0027:
            monitor-exit(r1)
            return r3
        L_0x0029:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.b(java.lang.String, java.lang.String):java.lang.String");
    }

    public synchronized void a(String str, String str2) {
        if (this.as.containsKey(str)) {
            str = this.as.get(str);
        }
        RouterTagInfo routerTagInfo = this.ar.get(str);
        if (routerTagInfo != null && !TextUtils.equals(str2, routerTagInfo.d)) {
            routerTagInfo.d = str2;
            J();
            p(str);
            I();
        }
    }

    private synchronized void c(String str, String str2, int i) {
        RouterTagInfo routerTagInfo = this.ar.get(str);
        if (routerTagInfo == null) {
            routerTagInfo = new RouterTagInfo();
            routerTagInfo.f15470a = str;
            routerTagInfo.c = str2;
            routerTagInfo.f = i;
            this.ar.put(str, routerTagInfo);
        }
        if (i <= routerTagInfo.f) {
            if (!this.as.containsKey(b(str, -1))) {
                routerTagInfo.f = i;
                routerTagInfo.c = str2;
            }
        }
    }

    private void e(ParseData parseData, JSONArray jSONArray) {
        if (jSONArray != null) {
            parseData.d = new ConcurrentHashMap();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("bssid");
                String optString2 = optJSONObject.optString(DeviceTagInterface.e);
                if (!StringUtil.c(optString) && !StringUtil.c(optString2)) {
                    RouterTagInfo routerTagInfo = new RouterTagInfo();
                    routerTagInfo.a(optJSONObject);
                    if (o(routerTagInfo.f15470a)) {
                        parseData.d.put(optString, routerTagInfo);
                    }
                }
            }
        }
    }

    private boolean o(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}").matcher(str).matches();
    }

    private void p(String str) {
        if (TextUtils.equals(this.ap, str)) {
            Intent intent = new Intent("device_tag_updated_action");
            intent.putExtra("device_tag_param", e(str));
            intent.putExtra("router_bssid_param", str);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        }
    }

    private void I() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceTagInterface.h));
    }

    public synchronized void x() {
        boolean z = this.av;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(org.json.JSONObject r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L_0x0005
            monitor-exit(r4)
            return
        L_0x0005:
            java.lang.String r0 = "result"
            org.json.JSONArray r5 = r5.optJSONArray(r0)     // Catch:{ all -> 0x0063 }
            if (r5 == 0) goto L_0x0061
            int r0 = r5.length()     // Catch:{ all -> 0x0063 }
            if (r0 > 0) goto L_0x0014
            goto L_0x0061
        L_0x0014:
            r0 = 0
        L_0x0015:
            int r1 = r5.length()     // Catch:{ all -> 0x0063 }
            if (r0 >= r1) goto L_0x0053
            org.json.JSONObject r1 = r5.optJSONObject(r0)     // Catch:{ all -> 0x0063 }
            if (r1 != 0) goto L_0x0022
            goto L_0x0050
        L_0x0022:
            java.lang.String r2 = "bssid"
            java.lang.String r2 = r1.optString(r2)     // Catch:{ all -> 0x0063 }
            boolean r3 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r2)     // Catch:{ all -> 0x0063 }
            if (r3 == 0) goto L_0x002f
            goto L_0x0050
        L_0x002f:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo> r3 = r4.ar     // Catch:{ all -> 0x0063 }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x0063 }
            com.xiaomi.smarthome.device.utils.DeviceTagManager$RouterTagInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceTagManager.RouterTagInfo) r2     // Catch:{ all -> 0x0063 }
            if (r2 != 0) goto L_0x003a
            goto L_0x0050
        L_0x003a:
            com.xiaomi.smarthome.device.utils.DeviceTagManager$LocationInfo r3 = r2.e     // Catch:{ all -> 0x0063 }
            if (r3 != 0) goto L_0x0045
            com.xiaomi.smarthome.device.utils.DeviceTagManager$LocationInfo r3 = new com.xiaomi.smarthome.device.utils.DeviceTagManager$LocationInfo     // Catch:{ all -> 0x0063 }
            r3.<init>()     // Catch:{ all -> 0x0063 }
            r2.e = r3     // Catch:{ all -> 0x0063 }
        L_0x0045:
            com.xiaomi.smarthome.device.utils.DeviceTagManager$LocationInfo r2 = r2.e     // Catch:{ all -> 0x0063 }
            java.lang.String r3 = "locality"
            org.json.JSONObject r1 = r1.optJSONObject(r3)     // Catch:{ all -> 0x0063 }
            r2.a(r1)     // Catch:{ all -> 0x0063 }
        L_0x0050:
            int r0 = r0 + 1
            goto L_0x0015
        L_0x0053:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0063 }
            r4.at = r0     // Catch:{ all -> 0x0063 }
            r4.J()     // Catch:{ all -> 0x0063 }
            r4.I()     // Catch:{ all -> 0x0063 }
            monitor-exit(r4)
            return
        L_0x0061:
            monitor-exit(r4)
            return
        L_0x0063:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.a(org.json.JSONObject):void");
    }

    private void J() {
        final JSONObject E = E();
        this.au = System.currentTimeMillis();
        b(E);
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    DeviceTagManager.this.a(E, 3001, 1000, 2048, jSONArray);
                    JSONObject jSONObject = new JSONObject();
                    JSONObject i = DeviceTagManager.this.H();
                    jSONObject.put("component_id", 0);
                    jSONObject.put("key", Tags.LuckyShake.VALUE_SUCCESS_CODE);
                    jSONObject.put("data", i);
                    jSONArray.put(jSONObject);
                    UserConfigApi.a().a(SHApplication.getAppContext(), jSONArray, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void b(final JSONObject jSONObject) {
        if (jSONObject != null) {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    try {
                        JSONObject i = DeviceTagManager.this.H();
                        SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(DeviceTagManager.this.B(), 0).edit();
                        edit.putString(DeviceTagInterface.u, i.toString());
                        edit.putString(DeviceTagInterface.w, jSONObject.toString());
                        edit.apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private synchronized void K() {
        this.am = true;
        this.aG.postDelayed(new Runnable() {
            public void run() {
                if (DeviceTagManager.this.am) {
                    DeviceTagManager.this.L();
                    boolean unused = DeviceTagManager.this.am = false;
                }
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    public void L() {
        final JSONObject F = F();
        final JSONObject G = G();
        this.au = System.currentTimeMillis();
        a(F, G);
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    DeviceTagManager.this.a(F, 1001, 1000, 2048, jSONArray);
                    DeviceTagManager.this.a(G, 2001, 1000, 2048, jSONArray);
                    JSONObject jSONObject = new JSONObject();
                    JSONObject i = DeviceTagManager.this.H();
                    jSONObject.put("component_id", 0);
                    jSONObject.put("key", Tags.LuckyShake.VALUE_SUCCESS_CODE);
                    jSONObject.put("data", i);
                    jSONArray.put(jSONObject);
                    UserConfigApi.a().a(SHApplication.getAppContext(), jSONArray, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void a(final JSONObject jSONObject, final JSONObject jSONObject2) {
        if (jSONObject != null) {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    try {
                        JSONObject i = DeviceTagManager.this.H();
                        SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(DeviceTagManager.this.B(), 0).edit();
                        edit.putString(DeviceTagInterface.u, i.toString());
                        edit.putString(DeviceTagInterface.i, jSONObject.toString());
                        if (jSONObject2 != null) {
                            edit.putString(DeviceTagInterface.v, jSONObject2.toString());
                        }
                        edit.apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void c(List<Device> list) {
        String str = this.ao;
        if (!StringUtil.c(this.ap)) {
            str = this.ap;
        }
        this.aq = a(str, list);
        try {
            HomeManager a2 = HomeManager.a();
            if (a2.h()) {
                a2.a(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Set<String>> q() {
        return this.aA.get(2);
    }

    public Map<String, Set<String>> d() {
        return this.aA.get(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008a, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008c, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.xiaomi.smarthome.device.Device> a(java.lang.String r4, java.util.List<com.xiaomi.smarthome.device.Device> r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            int r0 = r3.an     // Catch:{ all -> 0x008d }
            r1 = -1
            if (r0 == r1) goto L_0x008b
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x008d }
            if (r0 != 0) goto L_0x008b
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ all -> 0x008d }
            r1 = 2131499523(0x7f0c1a03, float:1.8622698E38)
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x008d }
            boolean r0 = android.text.TextUtils.equals(r4, r0)     // Catch:{ all -> 0x008d }
            if (r0 != 0) goto L_0x008b
            if (r5 == 0) goto L_0x008b
            int r0 = r5.size()     // Catch:{ all -> 0x008d }
            if (r0 > 0) goto L_0x0026
            goto L_0x008b
        L_0x0026:
            int r0 = r3.an     // Catch:{ all -> 0x008d }
            r1 = 6
            if (r0 != r1) goto L_0x0035
            com.xiaomi.smarthome.homeroom.HomeManager r4 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x008d }
            java.util.List r4 = r4.j()     // Catch:{ all -> 0x008d }
            monitor-exit(r3)
            return r4
        L_0x0035:
            int r0 = r3.an     // Catch:{ all -> 0x008d }
            r1 = 7
            if (r0 != r1) goto L_0x0044
            com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager r4 = com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager.a()     // Catch:{ all -> 0x008d }
            java.util.List r4 = r4.e()     // Catch:{ all -> 0x008d }
            monitor-exit(r3)
            return r4
        L_0x0044:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r0 = r3.aA     // Catch:{ all -> 0x008d }
            int r1 = r3.an     // Catch:{ all -> 0x008d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x008d }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x008d }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x008d }
            r1 = 0
            if (r0 != 0) goto L_0x0057
            monitor-exit(r3)
            return r1
        L_0x0057:
            java.lang.Object r4 = r0.get(r4)     // Catch:{ all -> 0x008d }
            java.util.Set r4 = (java.util.Set) r4     // Catch:{ all -> 0x008d }
            if (r4 == 0) goto L_0x0089
            boolean r0 = r4.isEmpty()     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0066
            goto L_0x0089
        L_0x0066:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x008d }
            r0.<init>()     // Catch:{ all -> 0x008d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x008d }
        L_0x006f:
            boolean r1 = r5.hasNext()     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x0087
            java.lang.Object r1 = r5.next()     // Catch:{ all -> 0x008d }
            com.xiaomi.smarthome.device.Device r1 = (com.xiaomi.smarthome.device.Device) r1     // Catch:{ all -> 0x008d }
            java.lang.String r2 = r1.did     // Catch:{ all -> 0x008d }
            boolean r2 = r4.contains(r2)     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x006f
            r0.add(r1)     // Catch:{ all -> 0x008d }
            goto L_0x006f
        L_0x0087:
            monitor-exit(r3)
            return r0
        L_0x0089:
            monitor-exit(r3)
            return r1
        L_0x008b:
            monitor-exit(r3)
            return r5
        L_0x008d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.a(java.lang.String, java.util.List):java.util.List");
    }

    public void i() {
        h();
    }

    public synchronized Set<Integer> y() {
        if (this.az == null) {
            this.az = new HashSet();
            this.az.add(4);
            this.az.add(0);
            this.az.add(2);
        }
        return new HashSet(this.az);
    }

    public Set<Integer> p() {
        HashSet hashSet = new HashSet();
        hashSet.add(4);
        hashSet.add(0);
        hashSet.add(2);
        return hashSet;
    }

    public synchronized List<Integer> l() {
        if (this.aB == null || this.aB.isEmpty()) {
            this.aB = new ArrayList();
            this.aB.add(4);
            this.aB.add(0);
            this.aB.add(2);
        }
        Set<Integer> p = p();
        if (this.aB.size() < p.size()) {
            for (Integer remove : this.aB) {
                p.remove(remove);
            }
            this.aB.addAll(p);
        }
        return new ArrayList(this.aB);
    }

    public synchronized List<Integer> m() {
        Set<Integer> y = y();
        if (y.isEmpty()) {
            return null;
        }
        List<Integer> l = l();
        ArrayList arrayList = new ArrayList();
        for (Integer next : l) {
            if (y.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public synchronized List<String> b(int i) {
        List list;
        if (this.aC == null || (list = this.aC.get(Integer.valueOf(i))) == null || list.size() <= 0) {
            return null;
        }
        return new ArrayList(list);
    }

    public void d(List<DeviceTagGroup> list) {
        for (DeviceTagGroup next : list) {
            if (next.t == -1) {
                this.az.clear();
                if (next.w != null && next.w.size() > 0) {
                    this.aB = new ArrayList();
                    for (DeviceTagChild next2 : next.w) {
                        if (next2.h) {
                            this.az.add(Integer.valueOf(next2.f));
                        }
                        this.aB.add(Integer.valueOf(next2.f));
                    }
                }
            } else {
                ArrayList arrayList = new ArrayList();
                if (!(next.w == null || next.w.size() <= 0 || next.s == 7)) {
                    for (DeviceTagChild next3 : next.w) {
                        if (next.t == 2) {
                            arrayList.add(next3.e);
                        } else {
                            arrayList.add(next3.d);
                        }
                    }
                }
                if (this.aC == null) {
                    this.aC = new HashMap();
                }
                this.aC.put(Integer.valueOf(next.t), arrayList);
            }
        }
        m(DeviceTagInterface.x);
        K();
    }

    public void s() {
        Intent intent = new Intent("device_tag_selected_action");
        intent.putExtra(DeviceTagInterface.B, -1);
        intent.putExtra("tag_selected_param", true);
        intent.putExtra("device_tag_param", SHApplication.getAppContext().getString(R.string.tag_all_devices));
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
    }

    public synchronized List<String> c(int i, String str) {
        if (i == 4) {
            if (HomeManager.a().h()) {
                return HomeManager.a().v(str);
            }
        }
        Map<String, Set<String>> a2 = a(i);
        if (!TextUtils.isEmpty(str) && a2 != null) {
            if (!a2.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (String next : a2.keySet()) {
                    Set set = a2.get(next);
                    if (set != null && !set.isEmpty() && set.contains(str)) {
                        arrayList.add(next);
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList();
        for (int string : Y) {
            arrayList.add(SHApplication.getAppContext().getString(string));
        }
        return arrayList;
    }

    public List<String> a(Device device) {
        Map<String, Set<String>> a2;
        List<String> c = c();
        if (device == null || TextUtils.isEmpty(device.bssid) || TextUtils.isEmpty(device.did) || (a2 = a(4)) == null || a2.isEmpty()) {
            return c;
        }
        ArrayList arrayList = new ArrayList();
        String n = n(device.bssid);
        Map<String, Set<String>> a3 = a(2);
        Set<String> set = a3.get(n);
        List<String> b = b(4);
        if (a3 != null && !a3.isEmpty() && set != null && !set.isEmpty()) {
            for (String c2 : set) {
                List<String> c3 = c(4, c2);
                if (c3 != null && !c3.isEmpty()) {
                    for (String next : c3) {
                        if (a2.containsKey(next)) {
                            arrayList.add(next);
                            a2.remove(next);
                        }
                    }
                }
            }
        }
        if (b != null && !b.isEmpty()) {
            for (String next2 : b) {
                if (a2.containsKey(next2)) {
                    arrayList.add(next2);
                    a2.remove(next2);
                }
            }
        }
        if (!a2.isEmpty()) {
            arrayList.addAll(a2.keySet());
        }
        HashSet hashSet = new HashSet();
        hashSet.addAll(arrayList);
        for (String next3 : c) {
            if (!hashSet.contains(next3)) {
                arrayList.add(next3);
            }
        }
        return arrayList;
    }

    /* renamed from: z */
    public synchronized String g() {
        return this.aD;
    }

    public synchronized void t() {
        this.aD = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007e, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String b(int r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x007f }
            boolean r0 = r0.h()     // Catch:{ all -> 0x007f }
            if (r0 == 0) goto L_0x0015
            com.xiaomi.smarthome.homeroom.HomeManager r6 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x007f }
            java.lang.String r6 = r6.b((java.lang.String) r7, (java.lang.String) r8)     // Catch:{ all -> 0x007f }
            monitor-exit(r5)
            return r6
        L_0x0015:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x007f }
            r8.<init>()     // Catch:{ all -> 0x007f }
            boolean r0 = r5.a((int) r6, (java.lang.String) r7)     // Catch:{ all -> 0x007f }
            if (r0 == 0) goto L_0x0042
            java.util.Map r6 = r5.a((int) r6)     // Catch:{ all -> 0x007f }
            java.util.Set r6 = r6.keySet()     // Catch:{ all -> 0x007f }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x007f }
        L_0x002c:
            boolean r0 = r6.hasNext()     // Catch:{ all -> 0x007f }
            if (r0 == 0) goto L_0x0042
            java.lang.Object r0 = r6.next()     // Catch:{ all -> 0x007f }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x007f }
            boolean r1 = r0.contains(r7)     // Catch:{ all -> 0x007f }
            if (r1 == 0) goto L_0x002c
            r8.add(r0)     // Catch:{ all -> 0x007f }
            goto L_0x002c
        L_0x0042:
            boolean r6 = r8.isEmpty()     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x007d
            r6 = 1
            r0 = 1
        L_0x004a:
            r1 = 0
            int r2 = r8.size()     // Catch:{ all -> 0x007f }
            int r2 = r2 + r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            r3.<init>()     // Catch:{ all -> 0x007f }
            r3.append(r7)     // Catch:{ all -> 0x007f }
            r3.append(r2)     // Catch:{ all -> 0x007f }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x007f }
            java.util.Iterator r3 = r8.iterator()     // Catch:{ all -> 0x007f }
        L_0x0063:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0077
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x007f }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x007f }
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0063
            r1 = 1
            goto L_0x0063
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            int r0 = r0 + 1
            goto L_0x004a
        L_0x007c:
            r7 = r2
        L_0x007d:
            monitor-exit(r5)
            return r7
        L_0x007f:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.b(int, java.lang.String, java.lang.String):java.lang.String");
    }

    public String f(String str) {
        Map map = this.aA.get(4);
        if (map == null || map.size() <= 0) {
            return "";
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() != null && !((Set) entry.getValue()).isEmpty() && ((Set) entry.getValue()).contains(str)) {
                return (String) entry.getKey();
            }
        }
        return "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0063, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0065, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void j() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.aE     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x0064
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.aE     // Catch:{ all -> 0x0066 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x000e
            goto L_0x0064
        L_0x000e:
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.aE     // Catch:{ all -> 0x0066 }
            java.util.Set r0 = r0.keySet()     // Catch:{ all -> 0x0066 }
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r1 = r6.aA     // Catch:{ all -> 0x0066 }
            r2 = 4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0066 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0066 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x0062
            boolean r2 = r1.isEmpty()     // Catch:{ all -> 0x0066 }
            if (r2 != 0) goto L_0x0062
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0066 }
        L_0x002d:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0066 }
            if (r2 == 0) goto L_0x005a
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0066 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0066 }
            java.util.Map<java.lang.String, java.lang.String> r3 = r6.aE     // Catch:{ all -> 0x0066 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r1.get(r3)     // Catch:{ all -> 0x0066 }
            java.util.Set r4 = (java.util.Set) r4     // Catch:{ all -> 0x0066 }
            if (r4 == 0) goto L_0x002d
            boolean r5 = r4.contains(r2)     // Catch:{ all -> 0x0066 }
            if (r5 == 0) goto L_0x002d
            r4.remove(r2)     // Catch:{ all -> 0x0066 }
            com.xiaomi.smarthome.lite.LiteDeviceManager r4 = com.xiaomi.smarthome.lite.LiteDeviceManager.a()     // Catch:{ all -> 0x0066 }
            r4.a((java.lang.String) r3, (java.lang.String) r2)     // Catch:{ all -> 0x0066 }
            goto L_0x002d
        L_0x005a:
            java.lang.String r0 = "tag_info_edited_action"
            r6.m(r0)     // Catch:{ all -> 0x0066 }
            r6.K()     // Catch:{ all -> 0x0066 }
        L_0x0062:
            monitor-exit(r6)
            return
        L_0x0064:
            monitor-exit(r6)
            return
        L_0x0066:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.j():void");
    }

    public void d(String str, String str2) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
            if (this.aE == null) {
                this.aE = new HashMap();
            }
            this.aE.put(str, str2);
        }
    }

    public void g(String str) {
        if (!TextUtils.isEmpty(str) && this.aE != null && !this.aE.isEmpty()) {
            this.aE.remove(str);
        }
    }

    public void k() {
        if (this.aE != null) {
            this.aE.clear();
        }
    }

    public void a(final DeviceTagInterface.IRoomConfigListener iRoomConfigListener) {
        if (Math.abs(System.currentTimeMillis() - this.ak) >= 1800000) {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
                StringBuilder sb = new StringBuilder();
                sb.append("roomconfig");
                sb.append(GlobalSetting.E ? "_preview" : "");
                jSONObject.put("name", sb.toString());
                jSONObject.put("version", "5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            Request request = null;
            try {
                request = new Request.Builder().a("GET").b(c(jSONObject)).a();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            if (request != null) {
                HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                    public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                    }

                    public void onSuccess(Object obj, Response response) {
                    }

                    public void processFailure(Call call, IOException iOException) {
                    }

                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: org.json.JSONObject} */
                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void processResponse(okhttp3.Response r3) {
                        /*
                            r2 = this;
                            okhttp3.ResponseBody r3 = r3.body()     // Catch:{ Exception -> 0x005e }
                            java.lang.String r3 = r3.string()     // Catch:{ Exception -> 0x005e }
                            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x005e }
                            r0.<init>(r3)     // Catch:{ Exception -> 0x005e }
                            java.lang.String r3 = "result"
                            boolean r3 = r0.isNull(r3)     // Catch:{ Exception -> 0x005e }
                            if (r3 == 0) goto L_0x0016
                            return
                        L_0x0016:
                            java.lang.String r3 = "result"
                            org.json.JSONObject r3 = r0.optJSONObject(r3)     // Catch:{ Exception -> 0x005e }
                            java.lang.String r0 = "content"
                            boolean r0 = r3.isNull(r0)     // Catch:{ Exception -> 0x005e }
                            if (r0 == 0) goto L_0x0025
                            return
                        L_0x0025:
                            r0 = 0
                            java.lang.String r1 = "content"
                            java.lang.Object r3 = r3.get(r1)     // Catch:{ Exception -> 0x005e }
                            boolean r1 = r3 instanceof org.json.JSONObject     // Catch:{ Exception -> 0x005e }
                            if (r1 == 0) goto L_0x0034
                            r0 = r3
                            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ Exception -> 0x005e }
                            goto L_0x003f
                        L_0x0034:
                            boolean r1 = r3 instanceof java.lang.String     // Catch:{ Exception -> 0x005e }
                            if (r1 == 0) goto L_0x003f
                            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x005e }
                            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x005e }
                            r0.<init>(r3)     // Catch:{ Exception -> 0x005e }
                        L_0x003f:
                            if (r0 == 0) goto L_0x0062
                            java.lang.String r3 = "result"
                            boolean r3 = r0.isNull(r3)     // Catch:{ Exception -> 0x005e }
                            if (r3 != 0) goto L_0x0062
                            java.lang.String r3 = "result"
                            org.json.JSONArray r3 = r0.optJSONArray(r3)     // Catch:{ Exception -> 0x005e }
                            com.xiaomi.smarthome.device.utils.DeviceTagManager r0 = com.xiaomi.smarthome.device.utils.DeviceTagManager.this     // Catch:{ Exception -> 0x005e }
                            r0.c((org.json.JSONArray) r3)     // Catch:{ Exception -> 0x005e }
                            com.xiaomi.smarthome.device.utils.DeviceTagInterface$IRoomConfigListener r3 = r6     // Catch:{ Exception -> 0x005e }
                            if (r3 == 0) goto L_0x0062
                            com.xiaomi.smarthome.device.utils.DeviceTagInterface$IRoomConfigListener r3 = r6     // Catch:{ Exception -> 0x005e }
                            r3.a()     // Catch:{ Exception -> 0x005e }
                            goto L_0x0062
                        L_0x005e:
                            r3 = move-exception
                            r3.printStackTrace()
                        L_0x0062:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.AnonymousClass18.processResponse(okhttp3.Response):void");
                    }
                });
            }
        }
    }

    @NonNull
    private String c(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    /* access modifiers changed from: private */
    public void c(JSONArray jSONArray) {
        SHApplication.getAppContext().getSharedPreferences(ah, 0).edit().putString("roomconfig", jSONArray.toString()).commit();
    }

    private List<RoomConfig> M() {
        ArrayList arrayList = new ArrayList();
        String string = SHApplication.getAppContext().getSharedPreferences(ah, 0).getString("roomconfig", "");
        if (TextUtils.isEmpty(string)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(RoomConfig.a(jSONArray.optJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public List<RoomConfig> A() {
        List<RoomConfig> M = M();
        if (M == null) {
            M = new ArrayList<>();
        }
        if (M.isEmpty()) {
            try {
                String[] stringArray = SHApplication.getAppContext().getResources().getStringArray(R.array.default_room_config);
                String[] stringArray2 = SHApplication.getAppContext().getResources().getStringArray(R.array.default_room_config_icon);
                for (int i = 0; i < stringArray.length; i++) {
                    RoomConfig roomConfig = new RoomConfig();
                    HashMap hashMap = new HashMap();
                    for (int i2 = 0; i2 < stringArray.length; i2++) {
                        hashMap.put("en_US", stringArray[i]);
                        hashMap.put("es_ES", stringArray[i]);
                        hashMap.put("ko_KR", stringArray[i]);
                        hashMap.put("ru_RU", stringArray[i]);
                        hashMap.put("zh_CN", stringArray[i]);
                        hashMap.put("zh_HK", stringArray[i]);
                        hashMap.put("zh_TW", stringArray[i]);
                        hashMap.put("fr_FR", stringArray[i]);
                        hashMap.put("it_IT", stringArray[i]);
                        hashMap.put("de_DE", stringArray[i]);
                        hashMap.put("in_ID", stringArray[i]);
                        hashMap.put("pl_PL", stringArray[i]);
                        hashMap.put("vi_VN", stringArray[i]);
                        hashMap.put("th_TH", stringArray[i]);
                        hashMap.put("ja_JP", stringArray[i]);
                    }
                    roomConfig.a((HashMap<String, String>) hashMap);
                    roomConfig.a(stringArray2[i]);
                    M.add(roomConfig);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return M;
    }

    public void a(DeviceTagInterface.IDeviceTagListener iDeviceTagListener) {
        this.aF.add(iDeviceTagListener);
    }

    public void b(DeviceTagInterface.IDeviceTagListener iDeviceTagListener) {
        this.aF.remove(iDeviceTagListener);
    }

    private void N() {
        if (this.aL.isEmpty()) {
            q(SHConfig.a().c(aI));
        }
        DevicelibApi.getDeviceCategory(SHApplication.getAppContext(), new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(final JSONObject jSONObject) {
                SHApplication.getThreadExecutor().submit(new Runnable() {
                    public void run() {
                        String optString = jSONObject.optString("catgory_info");
                        if (!TextUtils.isEmpty(optString)) {
                            try {
                                if (DeviceTagManager.this.d(new JSONArray(optString))) {
                                    SHConfig.a().a(DeviceTagManager.aI, jSONObject.toString());
                                    Locale I = CoreApi.a().I();
                                    if (I == null) {
                                        I = Locale.getDefault();
                                    }
                                    SHConfig.a().a(DeviceTagManager.aJ, LocaleUtil.a(I));
                                    SHConfig.a().a(DeviceTagManager.aK, System.currentTimeMillis());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            public void onFailure(Error error) {
                DeviceTagManager.this.q(SHConfig.a().c(DeviceTagManager.aI));
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:3|4|5|6|7|8|9|(3:12|13|20)(1:19)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0015 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001d A[SYNTHETIC, Splitter:B:12:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void q(java.lang.String r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0025
            int r0 = r4.length()
            if (r0 <= 0) goto L_0x0025
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0015 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0015 }
            java.lang.String r2 = "catgory_info"
            org.json.JSONArray r1 = r1.getJSONArray(r2)     // Catch:{ Exception -> 0x0015 }
            r0 = r1
        L_0x0015:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x001b }
            r1.<init>(r4)     // Catch:{ Exception -> 0x001b }
            r0 = r1
        L_0x001b:
            if (r0 == 0) goto L_0x0025
            r3.d((org.json.JSONArray) r0)     // Catch:{ Exception -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0025:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.q(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0129, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x012b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void O() {
        /*
            r10 = this;
            monitor-enter(r10)
            boolean r0 = r10.aH     // Catch:{ all -> 0x012c }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r10)
            return
        L_0x0007:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r0 = r10.aL     // Catch:{ all -> 0x012c }
            if (r0 == 0) goto L_0x012a
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x012c }
            if (r0 == 0) goto L_0x0013
            goto L_0x012a
        L_0x0013:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ all -> 0x012c }
            java.util.List r0 = r0.f()     // Catch:{ all -> 0x012c }
            if (r0 != 0) goto L_0x001f
            monitor-exit(r10)
            return
        L_0x001f:
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x012c }
            r1.<init>()     // Catch:{ all -> 0x012c }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x012c }
            r2.<init>()     // Catch:{ all -> 0x012c }
            r3 = 0
            r4 = 0
        L_0x002b:
            r5 = 0
        L_0x002c:
            int r6 = r0.size()     // Catch:{ Exception -> 0x011f }
            if (r5 >= r6) goto L_0x0105
            java.lang.Object r6 = r0.get(r5)     // Catch:{ Exception -> 0x011f }
            com.xiaomi.smarthome.device.Device r6 = (com.xiaomi.smarthome.device.Device) r6     // Catch:{ Exception -> 0x011f }
            if (r6 == 0) goto L_0x0101
            boolean r7 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.e((com.xiaomi.smarthome.device.Device) r6)     // Catch:{ Exception -> 0x011f }
            if (r7 == 0) goto L_0x0048
            boolean r7 = r6.isHomeShared()     // Catch:{ Exception -> 0x011f }
            if (r7 != 0) goto L_0x0048
            goto L_0x0101
        L_0x0048:
            com.xiaomi.smarthome.frame.core.CoreApi r7 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ Exception -> 0x011f }
            java.lang.String r8 = r6.model     // Catch:{ Exception -> 0x011f }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r7 = r7.d((java.lang.String) r8)     // Catch:{ Exception -> 0x011f }
            if (r7 != 0) goto L_0x0056
            goto L_0x0101
        L_0x0056:
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r7 = r7.c()     // Catch:{ Exception -> 0x011f }
            if (r7 != 0) goto L_0x005e
            goto L_0x0101
        L_0x005e:
            int r7 = r7.K()     // Catch:{ Exception -> 0x011f }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r8 = r10.aM     // Catch:{ Exception -> 0x011f }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x011f }
            r9.<init>()     // Catch:{ Exception -> 0x011f }
            r9.append(r7)     // Catch:{ Exception -> 0x011f }
            java.lang.String r7 = ""
            r9.append(r7)     // Catch:{ Exception -> 0x011f }
            java.lang.String r7 = r9.toString()     // Catch:{ Exception -> 0x011f }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ Exception -> 0x011f }
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r7 = (com.xiaomi.smarthome.device.utils.DeviceTagInterface.Category) r7     // Catch:{ Exception -> 0x011f }
            if (r7 != 0) goto L_0x0096
            java.lang.String r7 = "DeviceTagManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x011f }
            r8.<init>()     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = "cannot find the category for "
            r8.append(r9)     // Catch:{ Exception -> 0x011f }
            java.lang.String r6 = r6.model     // Catch:{ Exception -> 0x011f }
            r8.append(r6)     // Catch:{ Exception -> 0x011f }
            java.lang.String r6 = r8.toString()     // Catch:{ Exception -> 0x011f }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r7, r6)     // Catch:{ Exception -> 0x011f }
            goto L_0x0101
        L_0x0096:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r8 = r10.aL     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = r7.b     // Catch:{ Exception -> 0x011f }
            java.lang.Object r8 = r8.get(r9)     // Catch:{ Exception -> 0x011f }
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r8 = (com.xiaomi.smarthome.device.utils.DeviceTagInterface.Category) r8     // Catch:{ Exception -> 0x011f }
            if (r8 != 0) goto L_0x00cf
            java.lang.String r6 = "DeviceTagManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x011f }
            r8.<init>()     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = "cannot find the parent category for "
            r8.append(r9)     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = r7.d     // Catch:{ Exception -> 0x011f }
            r8.append(r9)     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = ", id="
            r8.append(r9)     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = r7.f15435a     // Catch:{ Exception -> 0x011f }
            r8.append(r9)     // Catch:{ Exception -> 0x011f }
            java.lang.String r9 = ",parentid="
            r8.append(r9)     // Catch:{ Exception -> 0x011f }
            java.lang.String r7 = r7.b     // Catch:{ Exception -> 0x011f }
            r8.append(r7)     // Catch:{ Exception -> 0x011f }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x011f }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r6, r7)     // Catch:{ Exception -> 0x011f }
            goto L_0x0101
        L_0x00cf:
            java.lang.String r9 = r8.d     // Catch:{ Exception -> 0x011f }
            java.lang.Object r9 = r1.get(r9)     // Catch:{ Exception -> 0x011f }
            java.util.Set r9 = (java.util.Set) r9     // Catch:{ Exception -> 0x011f }
            if (r9 != 0) goto L_0x00e3
            java.util.HashSet r9 = new java.util.HashSet     // Catch:{ Exception -> 0x011f }
            r9.<init>()     // Catch:{ Exception -> 0x011f }
            java.lang.String r8 = r8.d     // Catch:{ Exception -> 0x011f }
            r1.put(r8, r9)     // Catch:{ Exception -> 0x011f }
        L_0x00e3:
            java.lang.String r8 = r6.did     // Catch:{ Exception -> 0x011f }
            r9.add(r8)     // Catch:{ Exception -> 0x011f }
            java.lang.String r8 = r7.d     // Catch:{ Exception -> 0x011f }
            java.lang.Object r8 = r2.get(r8)     // Catch:{ Exception -> 0x011f }
            java.util.Set r8 = (java.util.Set) r8     // Catch:{ Exception -> 0x011f }
            if (r8 != 0) goto L_0x00fc
            java.util.HashSet r8 = new java.util.HashSet     // Catch:{ Exception -> 0x011f }
            r8.<init>()     // Catch:{ Exception -> 0x011f }
            java.lang.String r7 = r7.d     // Catch:{ Exception -> 0x011f }
            r2.put(r7, r8)     // Catch:{ Exception -> 0x011f }
        L_0x00fc:
            java.lang.String r6 = r6.did     // Catch:{ Exception -> 0x011f }
            r8.add(r6)     // Catch:{ Exception -> 0x011f }
        L_0x0101:
            int r5 = r5 + 1
            goto L_0x002c
        L_0x0105:
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r5 = r10.aA     // Catch:{ Exception -> 0x011f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x011f }
            r5.put(r6, r1)     // Catch:{ Exception -> 0x011f }
            java.util.Map<java.lang.Integer, java.util.Map<java.lang.String, java.util.Set<java.lang.String>>> r5 = r10.aA     // Catch:{ Exception -> 0x011f }
            r6 = 8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x011f }
            r5.put(r6, r2)     // Catch:{ Exception -> 0x011f }
            java.lang.String r5 = "category_info_updated_action"
            r10.m(r5)     // Catch:{ Exception -> 0x011f }
            goto L_0x0128
        L_0x011f:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x012c }
            int r4 = r4 + 1
            r5 = 3
            if (r4 < r5) goto L_0x002b
        L_0x0128:
            monitor-exit(r10)
            return
        L_0x012a:
            monitor-exit(r10)
            return
        L_0x012c:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.O():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean d(org.json.JSONArray r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            r0 = 0
            if (r13 != 0) goto L_0x0006
            monitor-exit(r12)
            return r0
        L_0x0006:
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x00a1 }
            r1.<init>()     // Catch:{ all -> 0x00a1 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x00a1 }
            r2.<init>()     // Catch:{ all -> 0x00a1 }
            r3 = 1
            r4 = 0
            r5 = 1
        L_0x0013:
            int r6 = r13.length()     // Catch:{ Exception -> 0x0080 }
            if (r4 >= r6) goto L_0x0085
            org.json.JSONObject r6 = r13.getJSONObject(r4)     // Catch:{ Exception -> 0x0080 }
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r7 = new com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category     // Catch:{ Exception -> 0x0080 }
            r7.<init>()     // Catch:{ Exception -> 0x0080 }
            java.lang.String r8 = "id"
            java.lang.String r8 = r6.optString(r8)     // Catch:{ Exception -> 0x0080 }
            r7.f15435a = r8     // Catch:{ Exception -> 0x0080 }
            java.lang.String r8 = "rank"
            int r8 = r6.optInt(r8)     // Catch:{ Exception -> 0x0080 }
            r7.c = r8     // Catch:{ Exception -> 0x0080 }
            java.lang.String r8 = "name"
            java.lang.String r8 = r6.optString(r8)     // Catch:{ Exception -> 0x0080 }
            r7.d = r8     // Catch:{ Exception -> 0x0080 }
            java.lang.String r8 = r7.f15435a     // Catch:{ Exception -> 0x0080 }
            r1.put(r8, r7)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r8 = "subcategory"
            org.json.JSONArray r6 = r6.optJSONArray(r8)     // Catch:{ Exception -> 0x0080 }
            if (r6 != 0) goto L_0x0049
            r5 = 0
            goto L_0x007d
        L_0x0049:
            r8 = 0
        L_0x004a:
            int r9 = r6.length()     // Catch:{ Exception -> 0x0080 }
            if (r8 >= r9) goto L_0x007d
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r9 = new com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category     // Catch:{ Exception -> 0x0080 }
            r9.<init>()     // Catch:{ Exception -> 0x0080 }
            org.json.JSONObject r10 = r6.optJSONObject(r8)     // Catch:{ Exception -> 0x0080 }
            java.lang.String r11 = "id"
            java.lang.String r11 = r10.optString(r11)     // Catch:{ Exception -> 0x0080 }
            r9.f15435a = r11     // Catch:{ Exception -> 0x0080 }
            java.lang.String r11 = "rank"
            int r11 = r10.optInt(r11)     // Catch:{ Exception -> 0x0080 }
            r9.c = r11     // Catch:{ Exception -> 0x0080 }
            java.lang.String r11 = "name"
            java.lang.String r10 = r10.optString(r11)     // Catch:{ Exception -> 0x0080 }
            r9.d = r10     // Catch:{ Exception -> 0x0080 }
            java.lang.String r10 = r7.f15435a     // Catch:{ Exception -> 0x0080 }
            r9.b = r10     // Catch:{ Exception -> 0x0080 }
            java.lang.String r10 = r9.f15435a     // Catch:{ Exception -> 0x0080 }
            r2.put(r10, r9)     // Catch:{ Exception -> 0x0080 }
            int r8 = r8 + 1
            goto L_0x004a
        L_0x007d:
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0080:
            r13 = move-exception
            r13.printStackTrace()     // Catch:{ all -> 0x00a1 }
            r5 = 0
        L_0x0085:
            r12.aL = r1     // Catch:{ all -> 0x00a1 }
            r12.aM = r2     // Catch:{ all -> 0x00a1 }
            r12.O()     // Catch:{ all -> 0x00a1 }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r13 = r12.aL     // Catch:{ all -> 0x00a1 }
            int r13 = r13.size()     // Catch:{ all -> 0x00a1 }
            if (r13 <= 0) goto L_0x009f
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r13 = r12.aM     // Catch:{ all -> 0x00a1 }
            int r13 = r13.size()     // Catch:{ all -> 0x00a1 }
            if (r13 <= 0) goto L_0x009f
            if (r5 == 0) goto L_0x009f
            r0 = 1
        L_0x009f:
            monitor-exit(r12)
            return r0
        L_0x00a1:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.d(org.json.JSONArray):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0054, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.xiaomi.smarthome.device.utils.DeviceTagInterface.Category c(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0055 }
            r1 = 0
            if (r0 == 0) goto L_0x000a
            monitor-exit(r3)
            return r1
        L_0x000a:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x0055 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r4 = r0.d((java.lang.String) r4)     // Catch:{ all -> 0x0055 }
            if (r4 != 0) goto L_0x0016
            monitor-exit(r3)
            return r1
        L_0x0016:
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r4 = r4.c()     // Catch:{ all -> 0x0055 }
            if (r4 != 0) goto L_0x001e
            monitor-exit(r3)
            return r1
        L_0x001e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
            r0.<init>()     // Catch:{ all -> 0x0055 }
            int r4 = r4.K()     // Catch:{ all -> 0x0055 }
            r0.append(r4)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = ""
            r0.append(r4)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0055 }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r0 = r3.aM     // Catch:{ all -> 0x0055 }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ all -> 0x0055 }
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r4 = (com.xiaomi.smarthome.device.utils.DeviceTagInterface.Category) r4     // Catch:{ all -> 0x0055 }
            if (r4 != 0) goto L_0x003f
            monitor-exit(r3)
            return r1
        L_0x003f:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category> r0 = r3.aL     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = r4.b     // Catch:{ all -> 0x0055 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0055 }
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r0 = (com.xiaomi.smarthome.device.utils.DeviceTagInterface.Category) r0     // Catch:{ all -> 0x0055 }
            if (r0 != 0) goto L_0x004d
            monitor-exit(r3)
            return r1
        L_0x004d:
            boolean r1 = W     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r4 = r0
        L_0x0053:
            monitor-exit(r3)
            return r4
        L_0x0055:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.DeviceTagManager.c(java.lang.String):com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category");
    }

    public synchronized Collection<DeviceTagInterface.Category> n() {
        return (W ? this.aM : this.aL).values();
    }

    public synchronized DeviceTagInterface.Category h(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (DeviceTagInterface.Category) (W ? this.aM.get(str) : this.aL.get(str));
    }

    public synchronized DeviceTagInterface.Category i(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.aM.get(str);
    }

    public synchronized DeviceTagInterface.Category d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map map = W ? this.aM : this.aL;
        if (map == null) {
            map = new HashMap();
        }
        int i = 0;
        do {
            try {
                for (Map.Entry entry : map.entrySet()) {
                    if (entry != null) {
                        DeviceTagInterface.Category category = (DeviceTagInterface.Category) entry.getValue();
                        if (category != null) {
                            if (TextUtils.equals(str, category.d)) {
                                return category;
                            }
                        }
                    }
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                i++;
                if (i >= 3) {
                    return null;
                }
            }
        } while (i >= 3);
        return null;
    }
}
