package com.xiaomi.smarthome.miio.areainfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MainlandAreaInfo extends AreaInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11899a = "last_time";
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
    private Runnable A = new Runnable() {
        public void run() {
            boolean unused = MainlandAreaInfo.this.m = false;
        }
    };
    /* access modifiers changed from: private */
    public volatile boolean m = false;
    /* access modifiers changed from: private */
    public long n = -1;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public String p;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public Location r;
    private boolean s;
    /* access modifiers changed from: private */
    public AreaPropInfo t;
    /* access modifiers changed from: private */
    public String u;
    /* access modifiers changed from: private */
    public String v;
    /* access modifiers changed from: private */
    public String w;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public String y;
    private List<Integer> z = new ArrayList();

    /* access modifiers changed from: private */
    public void a(Context context, String str, Location location, boolean z2, boolean z3) {
    }

    public void B() {
    }

    private class ProcessData {

        /* renamed from: a  reason: collision with root package name */
        public long f11908a;
        public String b;
        public String c;
        public String d;
        public Location e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public boolean k;
        public AreaPropInfo l;

        private ProcessData() {
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.f11908a = jSONObject.optLong("last_time");
                this.d = jSONObject.optString("location_id");
                double optDouble = jSONObject.optDouble("latitude", Double.MAX_VALUE);
                double optDouble2 = jSONObject.optDouble("longitude", Double.MAX_VALUE);
                if (!(optDouble == Double.MAX_VALUE || optDouble2 == Double.MAX_VALUE)) {
                    this.e = new Location("");
                    this.e.setLatitude(optDouble);
                    this.e.setLongitude(optDouble2);
                    this.b = optDouble + "";
                    this.c = optDouble2 + "";
                }
                this.f = jSONObject.optString("location_title");
                this.g = jSONObject.optString("location_subtitle");
                this.h = jSONObject.optString("location_province");
                this.i = jSONObject.optString("location_city");
                this.j = jSONObject.optString("location_district");
                this.k = jSONObject.optBoolean("location_selected");
                JSONObject optJSONObject = jSONObject.optJSONObject("area_info");
                if (optJSONObject != null) {
                    this.l = new AreaPropInfo();
                    this.l.a(optJSONObject);
                }
            }
        }
    }

    public MainlandAreaInfo() {
        this.z.add(0);
        this.z.add(1);
        this.z.add(2);
    }

    public void a(Context context, boolean z2) {
        if (!this.m) {
            SHApplication.getGlobalHandler().removeCallbacks(this.A);
            this.m = true;
            SHApplication.getGlobalHandler().postDelayed(this.A, 10000);
            Home m2 = HomeManager.a().m();
            if (!HomeManager.a().h() || m2 == null) {
                b(context, z2);
            } else {
                a(context, z2, m2);
            }
        }
    }

    private void b(Context context, boolean z2) {
        if (TextUtils.isEmpty(this.o)) {
            d(context, z2);
            return;
        }
        a(context, this.o, this.r, z2, this.s);
    }

    private void a(Context context, boolean z2, Home home) {
        boolean z3;
        boolean z4;
        Location location;
        String a2 = home.a();
        boolean z5 = false;
        if (!TextUtils.isEmpty(a2) || !TextUtils.isEmpty(this.o)) {
            z3 = !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(this.o) && TextUtils.equals(a2, this.o) && !TextUtils.equals("0", a2);
        } else {
            z3 = Home.a(this.p, this.q, home.b(), home.c());
        }
        if (TextUtils.isEmpty(a2)) {
            home.c(this.o);
            z4 = !TextUtils.isEmpty(this.o);
            HomeManager.a().H();
        } else if ((!Home.j(home.b()) || !Home.j(home.c())) && Home.j(this.p) && Home.j(this.q)) {
            home.a(this.p);
            home.b(this.q);
            z4 = true;
        } else {
            z4 = false;
        }
        if (this.r != null) {
            double latitude = this.r.getLatitude();
            double longitude = this.r.getLongitude();
            String b2 = home.b();
            String c2 = home.c();
            if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(c2) || TextUtils.equals("0", b2) || TextUtils.equals("0", c2)) {
                home.a(latitude + "");
                home.b(longitude + "");
                location = this.r;
                z4 = true;
            } else {
                Location location2 = new Location("");
                location2.setLatitude(Double.parseDouble(b2));
                location2.setLongitude(Double.parseDouble(c2));
                location = location2;
            }
        } else {
            location = null;
        }
        if (z4) {
            HomeManager.a().b(home, (HomeManager.IHomeOperationCallback) null);
        }
        if (!z3) {
            AreaPropInfo areaPropInfo = this.t;
            this.t = null;
            if (areaPropInfo != null) {
                z5 = true;
            }
            b(context, z2, z5);
            return;
        }
        a(context, this.o, location, z2, this.s);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z2) {
        this.m = false;
    }

    /* access modifiers changed from: protected */
    public void b(boolean z2) {
        this.m = false;
    }

    /* access modifiers changed from: protected */
    public void b(Context context, boolean z2, boolean z3, Location location) {
        this.m = false;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, Address address, Location location, boolean z2, boolean z3) {
        LocationData a2 = ShowProvinceHelper.a(context, address);
        this.m = false;
        if (a2 != null) {
            this.u = a2.f11898a;
            this.v = a2.b;
            this.w = a2.c;
            this.x = a2.d;
            this.y = a2.e;
            this.p = location.getLatitude() + "";
            this.q = location.getLongitude() + "";
            boolean z4 = (StringUtil.c(this.o) || StringUtil.c(a2.f) || TextUtils.equals(a2.f, this.o)) ? z2 : true;
            this.o = a2.f;
            this.r = location;
            Home m2 = HomeManager.a().m();
            if (HomeManager.a().h() && m2 != null) {
                m2.c(this.o);
                m2.a(this.r.getLatitude() + "");
                m2.b(this.r.getLongitude() + "");
                HomeManager.a().b(m2, (HomeManager.IHomeOperationCallback) null);
                HomeManager.a().H();
            }
            a(context, this.o, this.r, z4, false);
            return;
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(AreaInfoManager.c));
        if (!z3) {
            ToastUtil.a((int) R.string.area_auto_locate_failed);
        }
    }

    private void c(Context context, boolean z2) {
        if (this.t == null || StringUtil.c(this.o)) {
            this.u = "";
            this.v = context.getString(R.string.area_not_located);
            this.o = SmartGroupConstants.b;
            this.w = context.getString(R.string.area_default_subtitle);
            this.x = context.getString(R.string.area_default_subtitle);
            this.y = context.getString(R.string.area_default_subtitle);
            a(context, this.o, (Location) null, z2, false);
            return;
        }
        a(context, this.o, (Location) null, z2, this.s);
    }

    private void d(final Context context, final boolean z2) {
        if (this.t != null) {
            this.m = false;
        } else {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, ProcessData>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ProcessData processData) {
                }

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
                        MainlandAreaInfo.this.a(context, z2, processData);
                        return processData;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }, new Object[0]);
        }
    }

    private void b(final Context context, final boolean z2, boolean z3) {
        if (this.t != null) {
            this.m = false;
        } else if (z3) {
            a(context, z2, (ProcessData) null);
        } else {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, ProcessData>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ProcessData processData) {
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public ProcessData doInBackground(Object... objArr) {
                    Home m = HomeManager.a().m();
                    if (m == null) {
                        return null;
                    }
                    if (m != null && (TextUtils.isEmpty(m.b()) || TextUtils.isEmpty(m.c()) || TextUtils.equals("0", m.b()) || TextUtils.equals("0", m.c()))) {
                        return null;
                    }
                    String string = SHApplication.getAppContext().getSharedPreferences(AreaInfoManager.d, 0).getString("location_info", (String) null);
                    if (TextUtils.isEmpty(string)) {
                        return null;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        ProcessData processData = new ProcessData();
                        processData.a(jSONObject);
                        if (!TextUtils.isEmpty(processData.b)) {
                            if (!TextUtils.isEmpty(processData.c)) {
                                double parseDouble = Double.parseDouble(m.b());
                                double parseDouble2 = Double.parseDouble(m.c());
                                double parseDouble3 = Double.parseDouble(processData.b);
                                double parseDouble4 = Double.parseDouble(processData.c);
                                if (TextUtils.isEmpty(processData.d)) {
                                    if (Math.abs(parseDouble3 - parseDouble) >= 0.01d || Math.abs(parseDouble4 - parseDouble2) >= 0.01d) {
                                        return null;
                                    }
                                    MainlandAreaInfo.this.a(context, z2, processData);
                                    return processData;
                                } else if (!TextUtils.equals(processData.d, m.a())) {
                                    return null;
                                } else {
                                    MainlandAreaInfo.this.a(context, z2, processData);
                                    return processData;
                                }
                            }
                        }
                        return null;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, boolean z2, ProcessData processData) {
        if (this.t != null) {
            this.m = false;
        } else if (processData != null) {
            this.m = false;
            this.n = processData.f11908a;
            this.o = processData.d;
            this.p = processData.b;
            this.q = processData.c;
            this.r = processData.e;
            this.u = processData.f;
            this.v = processData.g;
            this.w = processData.h;
            this.x = processData.i;
            this.y = processData.j;
            this.s = processData.k;
            if (processData.l != null) {
                this.t = processData.l;
                C();
            }
            a(context, this.o, this.r, z2, this.s);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(AreaInfoManager.f11897a));
        } else {
            a(context, z2, true);
        }
    }

    /* access modifiers changed from: protected */
    public void a(final Context context, final boolean z2, final boolean z3) {
        Home m2 = HomeManager.a().m();
        if (m2 == null) {
            super.a(context, z2, z3);
        } else if (TextUtils.isEmpty(m2.a()) || TextUtils.equals("0", m2.a())) {
            String b2 = m2.b();
            String c2 = m2.c();
            if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(c2) || TextUtils.equals("0", b2) || TextUtils.equals("0", c2)) {
                super.a(context, z2, z3);
            } else {
                SHLocationManager.a().a(Double.parseDouble(b2), Double.parseDouble(c2), (SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                    public void onSucceed(String str, Location location) {
                        MainlandAreaInfo.this.a(context, z2, z3, location);
                    }
                });
            }
        } else {
            a(context, m2.a(), z2, z3);
        }
    }

    public static String a(Context context, String str) {
        String[] b2 = ShowProvinceHelper.b(context, str);
        return (b2 == null || b2.length < 3) ? "" : a(context, b2[0], b2[1], b2[2]);
    }

    public static String a(Context context, String str, String str2, String str3) {
        if (!TextUtils.equals(str, str2)) {
            if (TextUtils.equals(str2, str3)) {
                return str3;
            }
            return str2 + context.getString(R.string.area_center_dot) + str3;
        } else if (TextUtils.equals(str, str3) || TextUtils.equals(str2, str3)) {
            return str3;
        } else {
            return str2 + context.getString(R.string.area_center_dot) + str3;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Context context, String str, boolean z2, boolean z3) {
        ShowProvinceHelper.a(context, str, z2, z3, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
            public void a(Context context, String str, String str2, String str3, String str4) {
                if (context == null) {
                    context = SHApplication.getAppContext();
                }
                Context context2 = context;
                if (context2 != null) {
                    boolean unused = MainlandAreaInfo.this.m = false;
                    String unused2 = MainlandAreaInfo.this.u = "";
                    String unused3 = MainlandAreaInfo.this.v = MainlandAreaInfo.a(context2, str, str2, str3);
                    String unused4 = MainlandAreaInfo.this.w = str;
                    String unused5 = MainlandAreaInfo.this.x = str2;
                    String unused6 = MainlandAreaInfo.this.y = str3;
                    String unused7 = MainlandAreaInfo.this.o = str4;
                    MainlandAreaInfo.this.a(context2, MainlandAreaInfo.this.o, MainlandAreaInfo.this.r, true, false);
                    if (HomeManager.a().m() != null) {
                    }
                }
            }

            public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                boolean unused = MainlandAreaInfo.this.m = false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void C() {
        if (this.t != null && this.t.F != null && !this.t.F.isEmpty()) {
            this.z.clear();
            if (this.t.F.containsKey(AreaPropInfo.h) && this.t.F.get(AreaPropInfo.h).booleanValue()) {
                this.z.add(0);
            }
            if (this.t.F.containsKey("prop.aqi") && this.t.F.get("prop.aqi").booleanValue()) {
                this.z.add(1);
            }
            if (this.t.F.containsKey("prop.tds") && this.t.F.get("prop.tds").booleanValue()) {
                this.z.add(2);
            }
            if (this.t.F.containsKey("houseKeeping") && this.t.F.get("houseKeeping").booleanValue()) {
                this.z.add(3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void D() {
        ShowProvinceHelper.c();
    }

    /* access modifiers changed from: private */
    public void d(boolean z2) {
        if (this.t != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("last_time", this.n);
                jSONObject.put("location_id", this.o);
                if (this.r != null) {
                    jSONObject.put("latitude", this.r.getLatitude());
                    jSONObject.put("longitude", this.r.getLongitude());
                } else {
                    jSONObject.put("latitude", Double.MAX_VALUE);
                    jSONObject.put("longitude", Double.MAX_VALUE);
                }
                jSONObject.put("location_title", this.u);
                jSONObject.put("location_subtitle", this.v);
                if (!StringUtil.c(this.w)) {
                    jSONObject.put("location_province", this.w);
                }
                if (!StringUtil.c(this.x)) {
                    jSONObject.put("location_city", this.x);
                }
                if (!StringUtil.c(this.y)) {
                    jSONObject.put("location_district", this.y);
                }
                jSONObject.put("area_info", this.t.a());
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

    public void a(Context context) {
        ShowProvinceHelper.a((Activity) context, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
            public void a(Context context, String str, String str2, String str3, String str4) {
                MainlandAreaInfo.this.a(context, str, str2, str3, str4);
            }

            public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                LocationData a2 = ShowProvinceHelper.a(context, address);
                if (a2 != null) {
                    String unused = MainlandAreaInfo.this.u = a2.f11898a;
                    String unused2 = MainlandAreaInfo.this.v = a2.b;
                    String unused3 = MainlandAreaInfo.this.w = a2.c;
                    String unused4 = MainlandAreaInfo.this.x = a2.d;
                    String unused5 = MainlandAreaInfo.this.y = a2.e;
                    MainlandAreaInfo mainlandAreaInfo = MainlandAreaInfo.this;
                    String unused6 = mainlandAreaInfo.p = location.getLatitude() + "";
                    MainlandAreaInfo mainlandAreaInfo2 = MainlandAreaInfo.this;
                    String unused7 = mainlandAreaInfo2.q = location.getLongitude() + "";
                    boolean z3 = (StringUtil.c(MainlandAreaInfo.this.o) || StringUtil.c(a2.f) || TextUtils.equals(MainlandAreaInfo.this.o, a2.f)) ? z : true;
                    String unused8 = MainlandAreaInfo.this.o = a2.f;
                    Location unused9 = MainlandAreaInfo.this.r = location;
                    Home m = HomeManager.a().m();
                    if (HomeManager.a().h() && m != null) {
                        m.c(MainlandAreaInfo.this.o);
                        m.a(MainlandAreaInfo.this.r.getLatitude() + "");
                        m.b(MainlandAreaInfo.this.r.getLongitude() + "");
                        HomeManager.a().b(m, (HomeManager.IHomeOperationCallback) null);
                        HomeManager.a().H();
                    }
                    MainlandAreaInfo.this.a(context, MainlandAreaInfo.this.o, MainlandAreaInfo.this.r, z3, false);
                    return;
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(AreaInfoManager.c));
                if (!z2) {
                    ToastUtil.a((int) R.string.area_auto_locate_failed);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Context context, String str, String str2, String str3, String str4) {
        if (!StringUtil.c(str4)) {
            this.u = "";
            this.v = a(context, str, str2, str3);
            this.o = str4;
            this.w = str;
            this.x = str2;
            this.y = str3;
            this.p = "0";
            this.q = "0";
            a(context, this.o, (Location) null, true, true);
            Home m2 = HomeManager.a().m();
            if (m2 != null) {
                m2.b(this.q);
                m2.a(this.p);
                m2.c(this.o);
                HomeManager.a().b(m2, (HomeManager.IHomeOperationCallback) null);
                HomeManager.a().H();
            }
        }
    }

    public int b() {
        if (this.t == null || this.z == null) {
            return 0;
        }
        return this.z.size();
    }

    public String c() {
        return this.o;
    }

    public String d() {
        return this.p;
    }

    public String e() {
        return this.q;
    }

    public int a(int i2) {
        if (i2 < 0 || i2 >= this.z.size()) {
            return -1;
        }
        return this.z.get(i2).intValue();
    }

    public List<Integer> f() {
        return this.z;
    }

    public boolean b(int i2) {
        if (this.z == null || this.z.isEmpty() || i2 != this.z.get(0).intValue()) {
            return false;
        }
        return true;
    }

    public boolean c(int i2) {
        if (this.z == null || this.z.isEmpty() || i2 != this.z.get(this.z.size() - 1).intValue()) {
            return false;
        }
        return true;
    }

    public boolean g() {
        return !this.m || this.t != null;
    }

    public String h() {
        if (StringUtil.c(this.u)) {
            return this.m ? SHApplication.getAppContext().getString(R.string.area_positioning) : "";
        }
        return this.u;
    }

    public String i() {
        if (StringUtil.c(this.v)) {
            return "-";
        }
        return this.v;
    }

    public String j() {
        return XMStringUtils.i(this.w);
    }

    public String k() {
        return XMStringUtils.i(this.x);
    }

    public String l() {
        return XMStringUtils.i(this.y);
    }

    public String m() {
        if (this.t == null) {
            return "-";
        }
        return this.t.q;
    }

    public String n() {
        if (this.t == null) {
            return "-";
        }
        return this.t.r;
    }

    public String o() {
        if (this.t == null || StringUtil.c(this.t.t)) {
            return SHApplication.getAppContext().getString(R.string.air_desc_detail_empty);
        }
        return SHApplication.getAppContext().getResources().getQuantityString(R.plurals.air_desc_detail, Integer.parseInt(this.t.t));
    }

    public String p() {
        if (this.t == null) {
            return "-";
        }
        return this.t.u;
    }

    public String q() {
        if (this.t == null) {
            return "-";
        }
        return this.t.v;
    }

    public String r() {
        if (this.t == null || StringUtil.c(this.t.w)) {
            return SHApplication.getAppContext().getString(R.string.air_desc_detail_empty);
        }
        return SHApplication.getAppContext().getResources().getQuantityString(R.plurals.air_desc_detail, Integer.parseInt(this.t.w), new Object[]{this.t.w});
    }

    public String s() {
        if (this.t == null) {
            return "-";
        }
        return this.t.x;
    }

    public String t() {
        if (this.t == null) {
            return "-";
        }
        return this.t.B;
    }

    public String u() {
        if (this.t == null) {
            return "-";
        }
        return this.t.C;
    }

    public String v() {
        if (this.t == null) {
            return "-";
        }
        return this.t.D;
    }

    public String w() {
        if (this.t == null) {
            return "-";
        }
        return this.t.E;
    }

    public String x() {
        if (this.t == null) {
            return "-";
        }
        return this.t.y;
    }

    public String y() {
        if (this.t == null) {
            return "-";
        }
        return this.t.z;
    }

    public int z() {
        if (this.t == null) {
            return -1;
        }
        try {
            return Integer.valueOf(this.t.s).intValue();
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public int c(boolean z2) {
        return ShowProvinceHelper.a(z2, this.t);
    }

    public AreaPropInfo A() {
        return this.t;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, boolean z2, boolean z3, Location location) {
        super.a(context, z2, z3, location);
        this.m = false;
    }
}
