package com.xiaomi.smarthome.miio.areainfo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.location.LocationPermissionDialogHelper;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowProvinceHelper {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Map<String, Map<String, String>>> f11918a = new LinkedHashMap();
    private static TreeMap<String, String> b = new TreeMap<>();
    private static TreeMap<String, String> c = new TreeMap<>();
    private static TreeMap<String, Pair<Integer, Integer>> d = new TreeMap<>();
    private static Set<String> e = new HashSet();
    private static volatile boolean f = false;
    private static XQProgressDialog g;

    public interface IUpdateLocationCallback {
        void a(Context context, Address address, Location location, boolean z, boolean z2);

        void a(Context context, String str, String str2, String str3, String str4);
    }

    public static void a() {
        e.add("北京");
        e.add("上海");
        e.add("天津");
        e.add("重庆");
        b.put("广西壮族自治区", "广西");
        b.put("宁夏回族自治区", "宁夏");
        b.put("内蒙古自治区", "内蒙古");
        b.put("西藏自治区", "西藏");
        b.put("新疆维吾尔自治区", "新疆");
        b.put("澳门特别行政区", "澳门");
        b.put("香港特别行政区", "香港");
        c.put("西双版纳傣族自治州", "西双版纳");
        c.put("延边朝鲜族自治州", "延边");
        c.put("大兴安岭地区", "大兴安岭");
        c.put("恩施土家族苗族自治州", "恩施");
        c.put("神农架", "神农架");
        c.put("湘西土家族苗族自治州", "湘西");
        c.put("阿坝藏族羌族自治州", "阿坝");
        c.put("甘孜藏族自治州", "甘孜");
        c.put("凉山彝族自治州", "凉山");
        c.put("黔西南布依族苗族自治州", "黔西南");
        c.put("毕节地区", "毕节");
        c.put("黔东南苗族侗族自治州", "黔东");
        c.put("黔南布依族苗族自治州", "黔南");
        c.put("楚雄彝族自治州", "楚雄");
        c.put("红河哈尼族彝族自治州", "红河");
        c.put("文山壮族苗族自治州", "文山");
        c.put("大理白族自治州", "大理");
        c.put("德宏傣族景颇族自治州", "德宏");
        c.put("怒江傈僳族自治州", "怒江");
        c.put("迪庆藏族自治州", "迪庆");
        c.put("临夏回族自治州", "临夏");
        c.put("甘南藏族自治州", "甘南");
        c.put("海东地区", "海东");
        c.put("海北藏族自治州", "海北");
        c.put("黄南藏族自治州", "黄南");
        c.put("海南藏族自治州", "海南");
        c.put("果洛藏族自治州", "果洛");
        c.put("玉树藏族自治州", "玉树");
        c.put("海西蒙古族藏族自治州", "海西");
        c.put("吐鲁番地区", "吐鲁番");
        c.put("哈密地区", "哈密");
        c.put("昌吉回族自治州", "昌吉");
        c.put("博尔塔拉蒙古自治州", "博尔塔拉");
        c.put("巴音郭楞蒙古自治州", "巴音郭楞");
        c.put("阿克苏地区", "阿克苏");
        c.put("克孜勒苏柯尔克孜自治州", "克孜勒苏");
        c.put("喀什地区", "喀什");
        c.put("和田地区", "和田");
        c.put("伊犁哈萨克自治州", "伊犁");
        c.put("塔城地区", "塔城");
        c.put("阿勒泰地区", "阿勒泰");
        c.put("青龙满族自治县", "青龙");
        c.put("峰峰矿区", "峰峰");
        c.put("鹰手营子矿区", "鹰手营子");
        c.put("丰宁满族自治县", "丰宁");
        c.put("宽城满族自治县", "宽城");
        c.put("围场满族蒙古族自治县", "围场");
        c.put("孟村回族自治县", "孟村");
        c.put("大厂回族自治县", "大厂");
        c.put("澳门特别行政区", "澳门");
        c.put("香港特别行政区", "香港");
        f = true;
    }

    public static void b() {
        f = false;
        f11918a.clear();
        b.clear();
        c.clear();
        d.clear();
        e.clear();
    }

    public static void a(Activity activity, final IUpdateLocationCallback iUpdateLocationCallback) {
        if (!NetworkUtils.c()) {
            ToastUtil.a((Context) activity, (int) R.string.popup_select_loc_no_network);
            return;
        }
        if (f11918a.isEmpty()) {
            a((Context) activity);
        }
        if (!f) {
            a();
        }
        List<String> a2 = a(f11918a.keySet().iterator());
        if (LanguageUtil.a(Locale.CHINA, ServerCompact.c((Context) activity))) {
            a2.add(0, activity.getString(R.string.area_auto_locate));
        }
        final String[] strArr = new String[a2.size()];
        a2.toArray(strArr);
        final WeakReference weakReference = new WeakReference(activity);
        MLAlertDialog d2 = new MLAlertDialog.Builder(activity).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity activity = (Activity) weakReference.get();
                if (i == 0) {
                    if (!LanguageUtil.a(Locale.CHINA, ServerCompact.c(SHApplication.getAppContext()))) {
                        ShowProvinceHelper.a(activity, strArr[i], iUpdateLocationCallback);
                    } else if (LocationPermissionDialogHelper.a(activity)) {
                        ShowProvinceHelper.a((Context) activity, activity.getString(R.string.area_info_loading));
                        ShowProvinceHelper.a(activity, true, false, iUpdateLocationCallback);
                    }
                } else if (i < strArr.length) {
                    ShowProvinceHelper.a(activity, strArr[i], iUpdateLocationCallback);
                }
            }
        }).d();
        if (strArr.length > 10) {
            d2.setContentPanelHeight((activity.getResources().getDisplayMetrics().heightPixels * 2) / 3);
        }
        MultipleChoiceDialogHelper.a(activity, d2);
    }

    public static void a(final Context context, final boolean z, final boolean z2, final IUpdateLocationCallback iUpdateLocationCallback) {
        if (!f) {
            a();
        }
        SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
            public void onGetLatLngSucceed(String str, Location location) {
            }

            public void onSucceed(String str, Location location) {
                Bundle extras = location.getExtras();
                if (extras != null) {
                    Address address = (Address) extras.getParcelable("address");
                    if (iUpdateLocationCallback != null) {
                        iUpdateLocationCallback.a(context, address, location, z, z2);
                    }
                    if (z && location != null && address != null && address.getAdminArea() != null) {
                        ToastUtil.a((int) R.string.area_location_suc);
                    } else if (z) {
                        ToastUtil.a((int) R.string.area_auto_locate_failed);
                    }
                    ShowProvinceHelper.c();
                }
            }

            public void onFailure(String str) {
                if (z) {
                    ToastUtil.a((int) R.string.area_auto_locate_failed);
                }
            }

            public void onTimeout(String str) {
                if (z) {
                    ToastUtil.a((int) R.string.area_auto_locate_failed);
                }
            }
        });
    }

    public static void a(Context context, String str, IUpdateLocationCallback iUpdateLocationCallback) {
        if (!f) {
            a();
        }
        final Map map = f11918a.get(str);
        if (map != null && !map.isEmpty()) {
            List<String> a2 = a((Iterator<String>) map.keySet().iterator());
            String[] strArr = new String[a2.size()];
            a2.toArray(strArr);
            final WeakReference weakReference = new WeakReference(context);
            final String[] strArr2 = strArr;
            final Context context2 = context;
            final String str2 = str;
            final IUpdateLocationCallback iUpdateLocationCallback2 = iUpdateLocationCallback;
            MLAlertDialog d2 = new MLAlertDialog.Builder(context).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (((Context) weakReference.get()) != null && i < strArr2.length) {
                        String str = strArr2[i];
                        ShowProvinceHelper.a(context2, str2, str, (Map<String, String>) (Map) map.get(str), iUpdateLocationCallback2);
                    }
                }
            }).d();
            if (strArr.length > 10) {
                d2.setContentPanelHeight((context.getResources().getDisplayMetrics().heightPixels * 2) / 3);
            }
            MultipleChoiceDialogHelper.a(context, d2);
        }
    }

    public static void a(Context context, String str, String str2, Map<String, String> map, IUpdateLocationCallback iUpdateLocationCallback) {
        if (!f) {
            a();
        }
        if (map != null && !map.isEmpty()) {
            List<String> a2 = a(map.keySet().iterator());
            String[] strArr = new String[a2.size()];
            a2.toArray(strArr);
            final WeakReference weakReference = new WeakReference(context);
            final String[] strArr2 = strArr;
            final IUpdateLocationCallback iUpdateLocationCallback2 = iUpdateLocationCallback;
            final String str3 = str;
            final String str4 = str2;
            final Map<String, String> map2 = map;
            MLAlertDialog d2 = new MLAlertDialog.Builder(context).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Context context = (Context) weakReference.get();
                    if (context != null && i < strArr2.length) {
                        ShowProvinceHelper.a(context, context.getString(R.string.area_info_loading));
                        String str = strArr2[i];
                        if (iUpdateLocationCallback2 != null) {
                            iUpdateLocationCallback2.a(context, str3, str4, str, (String) map2.get(str));
                        }
                        ShowProvinceHelper.c();
                    }
                }
            }).d();
            if (strArr.length > 10) {
                d2.setContentPanelHeight((context.getResources().getDisplayMetrics().heightPixels * 2) / 3);
            }
            MultipleChoiceDialogHelper.a(context, d2);
        }
    }

    public static int a(boolean z, AreaPropInfo areaPropInfo) {
        Pair pair;
        if (f) {
            a();
        }
        if (areaPropInfo == null || StringUtil.c(areaPropInfo.x) || (pair = d.get(areaPropInfo.x)) == null) {
            return -1;
        }
        if (z) {
            return ((Integer) pair.first).intValue();
        }
        return ((Integer) pair.second).intValue();
    }

    public static void a(Context context, String str) {
        c();
        g = new XQProgressDialog(context);
        g.setCancelable(true);
        g.setCanceledOnTouchOutside(false);
        g.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                SHLocationManager.a().g();
            }
        });
        g.setMessage(str);
        g.show();
    }

    public static void c() {
        if (g != null) {
            g.dismiss();
            g = null;
        }
    }

    private static List<String> a(Iterator<String> it) {
        String str;
        ArrayList arrayList = new ArrayList();
        ArrayList<Pair> arrayList2 = new ArrayList<>();
        while (it.hasNext()) {
            String next = it.next();
            try {
                if (TextUtils.equals("博爱", next)) {
                    str = "boai";
                } else {
                    str = XMStringUtils.a(next, false);
                }
                arrayList2.add(new Pair(next, str));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        for (Pair pair : arrayList2) {
            arrayList.add(pair.first);
        }
        return arrayList;
    }

    private static void a(Context context) {
        if (context == null) {
            context = SHApplication.getAppContext();
        }
        if (context != null) {
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
                                LinkedHashMap linkedHashMap = new LinkedHashMap();
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                    if (optJSONObject2 != null) {
                                        String optString = optJSONObject2.optString("name");
                                        if (!StringUtil.c(optString)) {
                                            JSONArray optJSONArray2 = optJSONObject2.optJSONArray("child");
                                            if (optJSONArray2 != null) {
                                                if (optJSONArray2.length() > 0) {
                                                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                                                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                                        JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                                                        String optString2 = optJSONObject3.optString("name");
                                                        if (!StringUtil.c(optString2)) {
                                                            String optString3 = optJSONObject3.optString("id");
                                                            if (!StringUtil.c(optString3)) {
                                                                linkedHashMap2.put(optString2, optString3);
                                                            }
                                                        }
                                                    }
                                                    if (!linkedHashMap2.isEmpty()) {
                                                        linkedHashMap.put(optString, linkedHashMap2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!linkedHashMap.isEmpty()) {
                                    f11918a.put(next, linkedHashMap);
                                }
                            }
                        }
                    }
                }
            } catch (IOException | JSONException unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.miio.areainfo.LocationData a(android.content.Context r7, android.location.Address r8) {
        /*
            boolean r0 = f
            if (r0 != 0) goto L_0x0007
            a()
        L_0x0007:
            r0 = 0
            if (r8 == 0) goto L_0x0097
            java.lang.String r2 = r8.getCountryCode()
            java.util.TreeMap<java.lang.String, java.lang.String> r1 = b
            java.lang.String r3 = r8.getAdminArea()
            java.lang.String r3 = a((java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r3)
            java.util.TreeMap<java.lang.String, java.lang.String> r1 = c
            java.lang.String r4 = r8.getLocality()
            java.lang.String r1 = a((java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r4)
            java.util.TreeMap<java.lang.String, java.lang.String> r4 = c
            java.lang.String r5 = r8.getSubLocality()
            java.lang.String r4 = a((java.util.Map<java.lang.String, java.lang.String>) r4, (java.lang.String) r5)
            boolean r5 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r4)
            if (r5 == 0) goto L_0x0041
            boolean r5 = a((java.lang.String) r3)
            if (r5 == 0) goto L_0x0041
            boolean r5 = r3.equals(r1)
            if (r5 != 0) goto L_0x0041
            r5 = r1
            r4 = r3
            goto L_0x0043
        L_0x0041:
            r5 = r4
            r4 = r1
        L_0x0043:
            java.lang.String r6 = r8.getThoroughfare()
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r8 = f11918a
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x005b
            if (r7 != 0) goto L_0x0055
            android.content.Context r7 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
        L_0x0055:
            if (r7 != 0) goto L_0x0058
            return r0
        L_0x0058:
            a((android.content.Context) r7)
        L_0x005b:
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> r7 = f11918a
            java.lang.Object r7 = r7.get(r3)
            java.util.Map r7 = (java.util.Map) r7
            if (r7 == 0) goto L_0x0087
            java.lang.Object r7 = r7.get(r4)
            java.util.Map r7 = (java.util.Map) r7
            if (r7 == 0) goto L_0x0087
            boolean r8 = r7.containsKey(r5)
            if (r8 == 0) goto L_0x007a
            java.lang.Object r7 = r7.get(r5)
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0088
        L_0x007a:
            boolean r8 = r7.containsKey(r4)
            if (r8 == 0) goto L_0x0087
            java.lang.Object r7 = r7.get(r4)
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0088
        L_0x0087:
            r7 = r0
        L_0x0088:
            boolean r8 = com.xiaomi.smarthome.library.common.util.StringUtil.c((java.lang.String) r7)
            if (r8 != 0) goto L_0x0097
            com.xiaomi.smarthome.miio.areainfo.LocationData r8 = new com.xiaomi.smarthome.miio.areainfo.LocationData
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            r8.f = r7
            return r8
        L_0x0097:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper.a(android.content.Context, android.location.Address):com.xiaomi.smarthome.miio.areainfo.LocationData");
    }

    private static String a(Map<String, String> map, String str) {
        if (StringUtil.c(str)) {
            return str;
        }
        if (map.get(str) != null) {
            return map.get(str);
        }
        return str.substring(0, str.length() - 1);
    }

    private static boolean a(String str) {
        return e.contains(str);
    }

    public static void a(Context context, String str, boolean z, boolean z2, IUpdateLocationCallback iUpdateLocationCallback) {
        Map map;
        Map map2;
        if (context == null) {
            context = SHApplication.getAppContext();
        }
        Context context2 = context;
        if (context2 != null) {
            if (!f) {
                a();
            }
            if (f11918a.isEmpty()) {
                a(context2);
            }
            boolean z3 = false;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            for (Map.Entry next : f11918a.entrySet()) {
                if (!(next == null || (map = (Map) next.getValue()) == null)) {
                    str2 = (String) next.getKey();
                    for (Map.Entry entry : map.entrySet()) {
                        if (!(entry == null || (map2 = (Map) entry.getValue()) == null)) {
                            str3 = (String) entry.getKey();
                            Iterator it = map2.entrySet().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                Map.Entry entry2 = (Map.Entry) it.next();
                                if (entry2 != null) {
                                    str4 = (String) entry2.getKey();
                                    if (TextUtils.equals(str, (CharSequence) entry2.getValue())) {
                                        z3 = true;
                                        break;
                                    }
                                }
                            }
                            if (z3) {
                                break;
                            }
                        }
                    }
                    if (z3) {
                        break;
                    }
                }
            }
            String str5 = str3;
            String str6 = str4;
            String str7 = str2;
            if (z3) {
                if (iUpdateLocationCallback != null) {
                    iUpdateLocationCallback.a(context2, str7, str5, str6, str);
                    c();
                }
                if (HomeManager.a().m() != null) {
                    return;
                }
                return;
            }
            iUpdateLocationCallback.a(context2, str7, str5, str6, str);
        }
    }

    public static String[] b(Context context, String str) {
        Map map;
        Map map2;
        if (context == null) {
            context = SHApplication.getAppContext();
        }
        String str2 = null;
        if (context == null) {
            return null;
        }
        if (!f) {
            a();
        }
        if (f11918a.isEmpty()) {
            a(context);
        }
        String str3 = null;
        String str4 = null;
        boolean z = false;
        for (Map.Entry next : f11918a.entrySet()) {
            if (!(next == null || (map = (Map) next.getValue()) == null)) {
                str2 = (String) next.getKey();
                for (Map.Entry entry : map.entrySet()) {
                    if (!(entry == null || (map2 = (Map) entry.getValue()) == null)) {
                        str3 = (String) entry.getKey();
                        Iterator it = map2.entrySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Map.Entry entry2 = (Map.Entry) it.next();
                            if (entry2 != null) {
                                str4 = (String) entry2.getKey();
                                if (TextUtils.equals(str, (CharSequence) entry2.getValue())) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        if (z) {
                            break;
                        }
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        return new String[]{str2, str3, str4};
    }
}
