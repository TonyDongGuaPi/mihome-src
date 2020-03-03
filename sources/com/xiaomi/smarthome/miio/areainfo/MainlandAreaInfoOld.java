package com.xiaomi.smarthome.miio.areainfo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.location.LocationApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainlandAreaInfoOld extends AreaInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11909a = "last_time";
    public static final String b = "location_id";
    public static final String c = "latitude";
    public static final String d = "longitude";
    public static final String e = "location_title";
    public static final String f = "location_subtitle";
    public static final String g = "location_province";
    public static final String h = "location_city";
    public static final String i = "location_district";
    public static final String j = "area_info";
    public static final String k = "location_info";
    public static final String l = "location_selected";
    public static TreeMap<String, String> m = new TreeMap<>();
    public static TreeMap<String, String> n = new TreeMap<>();
    public static Set<String> o = new HashSet();
    private String A;
    private List<Integer> B = new ArrayList();
    private XQProgressDialog C;
    private Map<String, Map<String, Map<String, String>>> p = new HashMap();
    /* access modifiers changed from: private */
    public boolean q = false;
    /* access modifiers changed from: private */
    public long r = -1;
    private String s;
    private Location t;
    private boolean u;
    /* access modifiers changed from: private */
    public AreaPropInfo v;
    private String w;
    private String x;
    private String y;
    private String z;

    static {
        o.add("北京");
        o.add("上海");
        o.add("天津");
        o.add("重庆");
        m.put("广西壮族自治区", "广西");
        m.put("宁夏回族自治区", "宁夏");
        m.put("内蒙古自治区", "内蒙古");
        m.put("西藏自治区", "西藏");
        m.put("新疆维吾尔自治区", "新疆");
        m.put("澳门特别行政区", "澳门");
        m.put("香港特别行政区", "香港");
        n.put("西双版纳傣族自治州", "西双版纳");
        n.put("延边朝鲜族自治州", "延边");
        n.put("大兴安岭地区", "大兴安岭");
        n.put("恩施土家族苗族自治州", "恩施");
        n.put("神农架", "神农架");
        n.put("湘西土家族苗族自治州", "湘西");
        n.put("阿坝藏族羌族自治州", "阿坝");
        n.put("甘孜藏族自治州", "甘孜");
        n.put("凉山彝族自治州", "凉山");
        n.put("黔西南布依族苗族自治州", "黔西南");
        n.put("毕节地区", "毕节");
        n.put("黔东南苗族侗族自治州", "黔东");
        n.put("黔南布依族苗族自治州", "黔南");
        n.put("楚雄彝族自治州", "楚雄");
        n.put("红河哈尼族彝族自治州", "红河");
        n.put("文山壮族苗族自治州", "文山");
        n.put("大理白族自治州", "大理");
        n.put("德宏傣族景颇族自治州", "德宏");
        n.put("怒江傈僳族自治州", "怒江");
        n.put("迪庆藏族自治州", "迪庆");
        n.put("临夏回族自治州", "临夏");
        n.put("甘南藏族自治州", "甘南");
        n.put("海东地区", "海东");
        n.put("海北藏族自治州", "海北");
        n.put("黄南藏族自治州", "黄南");
        n.put("海南藏族自治州", "海南");
        n.put("果洛藏族自治州", "果洛");
        n.put("玉树藏族自治州", "玉树");
        n.put("海西蒙古族藏族自治州", "海西");
        n.put("吐鲁番地区", "吐鲁番");
        n.put("哈密地区", "哈密");
        n.put("昌吉回族自治州", "昌吉");
        n.put("博尔塔拉蒙古自治州", "博尔塔拉");
        n.put("巴音郭楞蒙古自治州", "巴音郭楞");
        n.put("阿克苏地区", "阿克苏");
        n.put("克孜勒苏柯尔克孜自治州", "克孜勒苏");
        n.put("喀什地区", "喀什");
        n.put("和田地区", "和田");
        n.put("伊犁哈萨克自治州", "伊犁");
        n.put("塔城地区", "塔城");
        n.put("阿勒泰地区", "阿勒泰");
        n.put("青龙满族自治县", "青龙");
        n.put("峰峰矿区", "峰峰");
        n.put("鹰手营子矿区", "鹰手营子");
        n.put("丰宁满族自治县", "丰宁");
        n.put("宽城满族自治县", "宽城");
        n.put("围场满族蒙古族自治县", "围场");
        n.put("孟村回族自治县", "孟村");
        n.put("大厂回族自治县", "大厂");
        n.put("澳门特别行政区", "澳门");
        n.put("香港特别行政区", "香港");
    }

    private class ProcessData {

        /* renamed from: a  reason: collision with root package name */
        public long f11917a;
        public String b;
        public Location c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public boolean i;
        public AreaPropInfo j;

        private ProcessData() {
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.f11917a = jSONObject.optLong("last_time");
                this.b = jSONObject.optString("location_id");
                double optDouble = jSONObject.optDouble("latitude", Double.MAX_VALUE);
                double optDouble2 = jSONObject.optDouble("longitude", Double.MAX_VALUE);
                if (!(optDouble == Double.MAX_VALUE || optDouble2 == Double.MAX_VALUE)) {
                    this.c = new Location("");
                    this.c.setLatitude(optDouble);
                    this.c.setLongitude(optDouble2);
                }
                this.d = jSONObject.optString("location_title");
                this.e = jSONObject.optString("location_subtitle");
                this.f = jSONObject.optString("location_province");
                this.g = jSONObject.optString("location_city");
                this.h = jSONObject.optString("location_district");
                this.i = jSONObject.optBoolean("location_selected");
                JSONObject optJSONObject = jSONObject.optJSONObject("area_info");
                if (optJSONObject != null) {
                    this.j = new AreaPropInfo();
                    this.j.a(optJSONObject);
                }
            }
        }
    }

    public MainlandAreaInfoOld() {
        this.B.add(0);
        this.B.add(1);
        this.B.add(2);
    }

    public void a(Context context, boolean z2) {
        if (!this.q) {
            this.q = true;
            if (TextUtils.isEmpty(this.s)) {
                c(context, z2);
                return;
            }
            a(context, this.s, this.t, z2, this.u);
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z2) {
        this.q = false;
    }

    /* access modifiers changed from: protected */
    public void b(boolean z2) {
        this.q = false;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, Address address, Location location, boolean z2, boolean z3) {
        LocationData a2 = a(context, address);
        if (a2 != null) {
            this.w = a2.f11898a;
            this.x = a2.b;
            this.y = a2.c;
            this.z = a2.d;
            this.A = a2.e;
            boolean z4 = (StringUtil.c(this.s) || StringUtil.c(a2.f) || this.s.equals(a2.f)) ? z2 : true;
            this.s = a2.f;
            this.t = location;
            a(context, this.s, this.t, z4, false);
            return;
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(AreaInfoManager.c));
        b(context, z2);
        if (!z3) {
            ToastUtil.a((int) R.string.area_auto_locate_failed);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.smarthome.miio.areainfo.LocationData a(android.content.Context r8, android.location.Address r9) {
        /*
            r7 = this;
            r0 = 0
            if (r9 == 0) goto L_0x0087
            java.lang.String r2 = r9.getCountryCode()
            java.util.TreeMap<java.lang.String, java.lang.String> r1 = m
            java.lang.String r3 = r9.getAdminArea()
            java.lang.String r3 = r7.a((java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r3)
            java.util.TreeMap<java.lang.String, java.lang.String> r1 = n
            java.lang.String r4 = r9.getLocality()
            java.lang.String r1 = r7.a((java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r4)
            java.util.TreeMap<java.lang.String, java.lang.String> r4 = n
            java.lang.String r5 = r9.getSubLocality()
            java.lang.String r4 = r7.a((java.util.Map<java.lang.String, java.lang.String>) r4, (java.lang.String) r5)
            boolean r5 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r4)
            if (r5 == 0) goto L_0x003a
            boolean r5 = r7.a((java.lang.String) r3)
            if (r5 == 0) goto L_0x003a
            boolean r5 = r3.equals(r1)
            if (r5 != 0) goto L_0x003a
            r5 = r1
            r4 = r3
            goto L_0x003c
        L_0x003a:
            r5 = r4
            r4 = r1
        L_0x003c:
            java.lang.String r6 = r9.getThoroughfare()
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r9 = r7.p
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x004b
            r7.b((android.content.Context) r8)
        L_0x004b:
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r8 = r7.p
            java.lang.Object r8 = r8.get(r3)
            java.util.Map r8 = (java.util.Map) r8
            if (r8 == 0) goto L_0x0077
            java.lang.Object r8 = r8.get(r4)
            java.util.Map r8 = (java.util.Map) r8
            if (r8 == 0) goto L_0x0077
            boolean r9 = r8.containsKey(r5)
            if (r9 == 0) goto L_0x006a
            java.lang.Object r8 = r8.get(r5)
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0078
        L_0x006a:
            boolean r9 = r8.containsKey(r4)
            if (r9 == 0) goto L_0x0077
            java.lang.Object r8 = r8.get(r4)
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0078
        L_0x0077:
            r8 = r0
        L_0x0078:
            boolean r9 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r8)
            if (r9 != 0) goto L_0x0087
            com.xiaomi.smarthome.miio.areainfo.LocationData r9 = new com.xiaomi.smarthome.miio.areainfo.LocationData
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r9.f = r8
            return r9
        L_0x0087:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.areainfo.MainlandAreaInfoOld.a(android.content.Context, android.location.Address):com.xiaomi.smarthome.miio.areainfo.LocationData");
    }

    private String a(Map<String, String> map, String str) {
        if (StringUtil.c(str)) {
            return str;
        }
        if (map.get(str) != null) {
            return map.get(str);
        }
        return str.substring(0, str.length() - 1);
    }

    private boolean a(String str) {
        return o.contains(str);
    }

    private void b(Context context) {
        try {
            InputStream open = context.getAssets().open(LanguageUtil.d());
            InputStreamReader inputStreamReader = new InputStreamReader(open);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = str + readLine;
            }
            bufferedReader.close();
            inputStreamReader.close();
            open.close();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                if (optJSONObject != null) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("child");
                    if (optJSONArray != null) {
                        if (optJSONArray.length() > 0) {
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                                if (optJSONObject2 != null) {
                                    String optString = optJSONObject2.optString("name");
                                    if (!StringUtil.c(optString)) {
                                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("child");
                                        if (optJSONArray2 != null) {
                                            if (optJSONArray2.length() > 0) {
                                                HashMap hashMap2 = new HashMap();
                                                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                                    JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i3);
                                                    String optString2 = optJSONObject3.optString("name");
                                                    if (!StringUtil.c(optString2)) {
                                                        String optString3 = optJSONObject3.optString("id");
                                                        if (!StringUtil.c(optString3)) {
                                                            hashMap2.put(optString2, optString3);
                                                        }
                                                    }
                                                }
                                                if (!hashMap2.isEmpty()) {
                                                    hashMap.put(optString, hashMap2);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!hashMap.isEmpty()) {
                                this.p.put(next, hashMap);
                            }
                        }
                    }
                }
            }
        } catch (IOException | JSONException unused) {
        }
    }

    private void b(Context context, boolean z2) {
        if (this.v == null || StringUtil.c(this.s)) {
            this.w = "";
            this.x = context.getString(R.string.area_not_located);
            this.s = SmartGroupConstants.b;
            this.y = context.getString(R.string.area_default_subtitle);
            this.z = context.getString(R.string.area_default_subtitle);
            this.A = context.getString(R.string.area_default_subtitle);
            a(context, this.s, (Location) null, z2, false);
            return;
        }
        a(context, this.s, (Location) null, z2, this.u);
    }

    private void c(final Context context, final boolean z2) {
        if (this.v != null) {
            this.q = false;
        } else {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, ProcessData>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public ProcessData doInBackground(Object... objArr) {
                    String string = SHApplication.getAppContext().getSharedPreferences(AreaInfoManager.d, 0).getString("location_info", (String) null);
                    if (TextUtils.isEmpty(string)) {
                        return null;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        ProcessData processData = new ProcessData();
                        processData.a(jSONObject);
                        return processData;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ProcessData processData) {
                    MainlandAreaInfoOld.this.a(context, z2, processData);
                }
            }, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, boolean z2, ProcessData processData) {
        if (this.v != null) {
            this.q = false;
        } else if (processData != null) {
            this.q = false;
            this.r = processData.f11917a;
            this.s = processData.b;
            this.t = processData.c;
            this.w = processData.d;
            this.x = processData.e;
            this.y = processData.f;
            this.z = processData.g;
            this.A = processData.h;
            this.u = processData.i;
            if (processData.j != null) {
                this.v = processData.j;
                B();
            }
            if (this.u) {
                a(context, this.s, this.t, z2, this.u);
            } else {
                a(context, z2, true);
            }
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(AreaInfoManager.f11897a));
        } else {
            a(context, z2, true);
        }
    }

    /* access modifiers changed from: private */
    public void B() {
        if (this.v != null && this.v.F != null && !this.v.F.isEmpty()) {
            this.B.clear();
            if (this.v.F.containsKey(AreaPropInfo.h) && this.v.F.get(AreaPropInfo.h).booleanValue()) {
                this.B.add(0);
            }
            if (this.v.F.containsKey("prop.aqi") && this.v.F.get("prop.aqi").booleanValue()) {
                this.B.add(1);
            }
            if (this.v.F.containsKey("prop.tds") && this.v.F.get("prop.tds").booleanValue()) {
                this.B.add(2);
            }
            if (this.v.F.containsKey("houseKeeping") && this.v.F.get("houseKeeping").booleanValue()) {
                this.B.add(3);
            }
        }
    }

    private void a(final Context context, String str, Location location, boolean z2, final boolean z3) {
        if (!TextUtils.equals(str, this.s) || z2 || this.r == -1 || System.currentTimeMillis() - this.r >= 3600000) {
            LocationApi.a().a(context, str, location, (AsyncCallback<AreaPropInfo, Error>) new AsyncCallback<AreaPropInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(AreaPropInfo areaPropInfo) {
                    AreaPropInfo unused = MainlandAreaInfoOld.this.v = areaPropInfo;
                    MainlandAreaInfoOld.this.B();
                    long unused2 = MainlandAreaInfoOld.this.r = System.currentTimeMillis();
                    boolean unused3 = MainlandAreaInfoOld.this.q = false;
                    MainlandAreaInfoOld.this.C();
                    MainlandAreaInfoOld.this.d(z3);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(AreaInfoManager.f11897a));
                }

                public void onFailure(Error error) {
                    boolean unused = MainlandAreaInfoOld.this.q = false;
                    MainlandAreaInfoOld.this.C();
                }
            });
            return;
        }
        this.q = false;
        C();
    }

    /* access modifiers changed from: private */
    public void b(Context context, String str) {
        C();
        this.C = new XQProgressDialog(context);
        this.C.setCancelable(false);
        this.C.setMessage(str);
        this.C.show();
    }

    /* access modifiers changed from: private */
    public void C() {
        if (this.C != null) {
            this.C.dismiss();
            this.C = null;
        }
    }

    /* access modifiers changed from: private */
    public void d(boolean z2) {
        if (this.v != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("last_time", this.r);
                jSONObject.put("location_id", this.s);
                if (this.t != null) {
                    jSONObject.put("latitude", this.t.getLatitude());
                    jSONObject.put("longitude", this.t.getLongitude());
                } else {
                    jSONObject.put("latitude", Double.MAX_VALUE);
                    jSONObject.put("longitude", Double.MAX_VALUE);
                }
                jSONObject.put("location_title", this.w);
                jSONObject.put("location_subtitle", this.x);
                if (!StringUtil.c(this.y)) {
                    jSONObject.put("location_province", this.y);
                }
                if (!StringUtil.c(this.z)) {
                    jSONObject.put("location_city", this.z);
                }
                if (!StringUtil.c(this.A)) {
                    jSONObject.put("location_district", this.A);
                }
                jSONObject.put("area_info", this.v.a());
                jSONObject.put("location_selected", z2);
                final String jSONObject2 = jSONObject.toString();
                AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                    /* access modifiers changed from: protected */
                    public Object doInBackground(Object... objArr) {
                        SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(AreaInfoManager.d, 0).edit();
                        edit.putString("location_info", jSONObject2);
                        edit.apply();
                        return null;
                    }
                }, new Object[0]);
            } catch (JSONException unused) {
            }
        }
    }

    public void a(final Context context) {
        if (this.p.isEmpty()) {
            b(context);
        }
        List<String> a2 = a(this.p.keySet().iterator());
        a2.add(0, context.getString(R.string.area_auto_locate));
        final String[] strArr = new String[a2.size()];
        a2.toArray(strArr);
        MLAlertDialog d2 = new MLAlertDialog.Builder(context).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    MainlandAreaInfoOld.this.b(context, context.getString(R.string.area_info_loading));
                    MainlandAreaInfoOld.this.a(context, true, false);
                } else if (i < strArr.length) {
                    MainlandAreaInfoOld.this.a(context, strArr[i]);
                }
            }
        }).d();
        if (strArr.length > 10) {
            d2.setContentPanelHeight((context.getResources().getDisplayMetrics().heightPixels * 2) / 3);
        }
    }

    public void a(Context context, String str) {
        final Map map = this.p.get(str);
        if (map != null && !map.isEmpty()) {
            List<String> a2 = a((Iterator<String>) map.keySet().iterator());
            String[] strArr = new String[a2.size()];
            a2.toArray(strArr);
            final String[] strArr2 = strArr;
            final Context context2 = context;
            final String str2 = str;
            MLAlertDialog d2 = new MLAlertDialog.Builder(context).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i < strArr2.length) {
                        String str = strArr2[i];
                        MainlandAreaInfoOld.this.a(context2, str2, str, (Map<String, String>) (Map) map.get(str));
                    }
                }
            }).d();
            if (strArr.length > 10) {
                d2.setContentPanelHeight((context.getResources().getDisplayMetrics().heightPixels * 2) / 3);
            }
        }
    }

    public void a(Context context, String str, String str2, Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            List<String> a2 = a(map.keySet().iterator());
            String[] strArr = new String[a2.size()];
            a2.toArray(strArr);
            final String[] strArr2 = strArr;
            final Context context2 = context;
            final String str3 = str;
            final String str4 = str2;
            final Map<String, String> map2 = map;
            MLAlertDialog d2 = new MLAlertDialog.Builder(context).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i < strArr2.length) {
                        MainlandAreaInfoOld.this.b(context2, context2.getString(R.string.area_info_loading));
                        String str = strArr2[i];
                        MainlandAreaInfoOld.this.a(context2, str3, str4, str, (String) map2.get(str));
                    }
                }
            }).d();
            if (strArr.length > 10) {
                d2.setContentPanelHeight((context.getResources().getDisplayMetrics().heightPixels * 2) / 3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, String str, String str2, String str3, String str4) {
        if (!StringUtil.c(str4)) {
            this.w = "";
            this.x = str;
            if (!str.equals(str2)) {
                this.x += context.getString(R.string.area_center_dot) + str2;
            } else if (!str.equals(str3)) {
                this.x += context.getString(R.string.area_center_dot) + str3;
            }
            this.s = str4;
            this.y = str;
            this.z = str2;
            this.A = str3;
            a(context, this.s, (Location) null, true, true);
        }
    }

    private List<String> a(Iterator<String> it) {
        ArrayList<Pair> arrayList = new ArrayList<>();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(new Pair(next, XMStringUtils.a(next, false)));
        }
        Collections.sort(arrayList, new Comparator<Pair<String, String>>() {
            /* renamed from: a */
            public int compare(Pair<String, String> pair, Pair<String, String> pair2) {
                return ((String) pair.second).compareTo((String) pair2.second);
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Pair pair : arrayList) {
            arrayList2.add(pair.first);
        }
        return arrayList2;
    }

    public int b() {
        if (this.v == null || this.B == null) {
            return 0;
        }
        return this.B.size();
    }

    public String c() {
        return this.s;
    }

    public int a(int i2) {
        if (i2 < 0 || i2 >= this.B.size()) {
            return -1;
        }
        return this.B.get(i2).intValue();
    }

    public List<Integer> f() {
        return this.B;
    }

    public boolean b(int i2) {
        if (this.B == null || this.B.isEmpty() || i2 != this.B.get(0).intValue()) {
            return false;
        }
        return true;
    }

    public boolean c(int i2) {
        if (this.B == null || this.B.isEmpty() || i2 != this.B.get(this.B.size() - 1).intValue()) {
            return false;
        }
        return true;
    }

    public boolean g() {
        return !this.q || this.v != null;
    }

    public String h() {
        if (StringUtil.c(this.w)) {
            return this.q ? SHApplication.getAppContext().getString(R.string.area_positioning) : "";
        }
        return this.w;
    }

    public String i() {
        if (StringUtil.c(this.x)) {
            return "-";
        }
        return this.x;
    }

    public String j() {
        return XMStringUtils.i(this.y);
    }

    public String k() {
        return XMStringUtils.i(this.z);
    }

    public String l() {
        return XMStringUtils.i(this.A);
    }

    public String m() {
        if (this.v == null) {
            return "-";
        }
        return this.v.q;
    }

    public String n() {
        if (this.v == null) {
            return "-";
        }
        return this.v.r;
    }

    public String o() {
        if (this.v == null || StringUtil.c(this.v.t)) {
            return SHApplication.getAppContext().getString(R.string.air_desc_detail_empty);
        }
        return SHApplication.getAppContext().getResources().getQuantityString(R.plurals.air_desc_detail, Integer.parseInt(this.v.t), new Object[]{this.v.t});
    }

    public String p() {
        if (this.v == null) {
            return "-";
        }
        return this.v.u;
    }

    public String q() {
        if (this.v == null) {
            return "-";
        }
        return this.v.v;
    }

    public String r() {
        if (this.v == null || StringUtil.c(this.v.w)) {
            return SHApplication.getAppContext().getString(R.string.air_desc_detail_empty);
        }
        return SHApplication.getAppContext().getResources().getQuantityString(R.plurals.air_desc_detail, Integer.parseInt(this.v.w), new Object[]{this.v.w});
    }

    public String s() {
        if (this.v == null) {
            return "-";
        }
        return this.v.x;
    }

    public String t() {
        if (this.v == null) {
            return "-";
        }
        return this.v.B;
    }

    public String u() {
        if (this.v == null) {
            return "-";
        }
        return this.v.C;
    }

    public String v() {
        if (this.v == null) {
            return "-";
        }
        return this.v.D;
    }

    public String w() {
        if (this.v == null) {
            return "-";
        }
        return this.v.E;
    }

    public String x() {
        if (this.v == null) {
            return "-";
        }
        return this.v.y;
    }

    public String y() {
        if (this.v == null) {
            return "-";
        }
        return this.v.z;
    }

    public int z() {
        if (this.v == null) {
            return -1;
        }
        try {
            return Integer.valueOf(this.v.s).intValue();
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public int c(boolean z2) {
        if (this.v == null) {
            return -1;
        }
        StringUtil.c(this.v.x);
        return -1;
    }

    public AreaPropInfo A() {
        return this.v;
    }
}
