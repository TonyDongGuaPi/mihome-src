package com.xiaomi.smarthome.device.utils;

import android.content.BroadcastReceiver;
import android.text.TextUtils;
import android.util.Pair;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.miio.page.DeviceGroup;
import com.xiaomi.smarthome.miio.page.GroupType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceSortUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15432a = "device_sort_configs";
    public static final String b = "group_sort_order";
    public static final String c = "device_list_sort";
    public static final String d = "device_list_sort_timestamp";
    public static final String e = "device_list_first_login";
    public static final String f = "device_list_sort_user_id";
    public static final GroupType[] g = {GroupType.DeviceList};
    public static final GroupType[] h = {GroupType.DeviceList, GroupType.ExpCenter, GroupType.WebInfo};
    public static final GroupType[] i = {GroupType.PhoneIR, GroupType.DeviceList, GroupType.ExpCenter, GroupType.WebInfo};
    public static BroadcastReceiver j;
    public static String k = "";

    public static GroupType[] a() {
        if (CoreApi.a().D()) {
            return g;
        }
        return IRDeviceUtil.c() ? i : h;
    }

    public static class GroupOrderInfo implements Cloneable {

        /* renamed from: a  reason: collision with root package name */
        public GroupType f15433a;
        public boolean b;

        public GroupOrderInfo(GroupType groupType, boolean z) {
            this.f15433a = groupType;
            this.b = z;
        }

        public GroupOrderInfo(GroupOrderInfo groupOrderInfo) {
            this.f15433a = groupOrderInfo.f15433a;
            this.b = groupOrderInfo.b;
        }

        /* access modifiers changed from: protected */
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public boolean a(GroupOrderInfo groupOrderInfo) {
            return this.f15433a.ordinal() == groupOrderInfo.f15433a.ordinal() && this.b == groupOrderInfo.b;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("t", this.f15433a.ordinal());
            jSONObject.put("e", this.b);
            return jSONObject;
        }

        public static GroupOrderInfo a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            try {
                int i = jSONObject.getInt("t");
                if (i >= 0) {
                    if (i < GroupType.values().length) {
                        GroupType groupType = GroupType.values()[i];
                        if (groupType.ordinal() == GroupType.WebInfo.ordinal() || groupType.ordinal() == GroupType.ExpCenter.ordinal() || groupType.ordinal() == GroupType.LocationInfo.ordinal()) {
                            return null;
                        }
                        return new GroupOrderInfo(groupType, jSONObject.getBoolean("e"));
                    }
                }
                return null;
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public static UserConfig.UserConfigAttr a(UserConfig userConfig, String str) {
        if (userConfig == null || userConfig.D == null || userConfig.D.size() <= 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<UserConfig.UserConfigAttr> it = userConfig.D.iterator();
        while (it.hasNext()) {
            UserConfig.UserConfigAttr next = it.next();
            if (str.equals(next.f16437a)) {
                return next;
            }
        }
        return null;
    }

    @Deprecated
    public static void a(UserConfig userConfig, AsyncResponseCallback asyncResponseCallback) {
        if (userConfig.D != null && userConfig.D.size() > 1) {
            try {
                long longValue = Long.valueOf(userConfig.D.get(0).b).longValue();
                long a2 = SharePrefsManager.a(CommonApplication.getAppContext(), f15432a, d, -1);
                if (a2 <= 0 || longValue - a2 >= 10000) {
                    try {
                        JSONObject jSONObject = new JSONObject(userConfig.D.get(1).b);
                        Iterator<String> keys = jSONObject.keys();
                        ArrayList arrayList = new ArrayList();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            arrayList.add(new Pair(next, Integer.valueOf(jSONObject.getInt(next))));
                        }
                        Collections.sort(arrayList, new Comparator<Pair<String, Integer>>() {
                            /* renamed from: a */
                            public int compare(Pair<String, Integer> pair, Pair<String, Integer> pair2) {
                                return ((Integer) pair.second).intValue() - ((Integer) pair2.second).intValue();
                            }
                        });
                        d(arrayList);
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(null);
                        }
                    } catch (JSONException unused) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(ErrorCode.ERROR_JSON_PARSER_EXCEPTION.getCode());
                        }
                    }
                } else if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(null);
                }
            } catch (Exception unused2) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_MSG_FORMAT_ERROR.getCode());
                }
            }
        } else if (asyncResponseCallback != null) {
            asyncResponseCallback.a(ErrorCode.ERROR_MSG_FORMAT_ERROR.getCode());
        }
    }

    public static JSONArray a(List<DeviceGroup> list) {
        JSONArray jSONArray = new JSONArray();
        if (list == null) {
            return jSONArray;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            DeviceGroup deviceGroup = list.get(i2);
            if (!(deviceGroup == null || deviceGroup.c == null)) {
                jSONArray.put("bssid_" + deviceGroup.f19530a);
                List<Device> list2 = deviceGroup.c;
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    Device device = list2.get(i3);
                    if (device != null) {
                        String str = device.did;
                        if (device instanceof BleDevice) {
                            str = str + "|" + device.model + "|" + device.name + "|" + device.token;
                        }
                        jSONArray.put(str);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static void a(List<DeviceGroup> list, AsyncCallback<Void, Error> asyncCallback) {
        if (CoreApi.a().q() && list != null && list.size() > 0) {
            UserConfig userConfig = new UserConfig();
            userConfig.B = 0;
            userConfig.C = "0";
            userConfig.D = new ArrayList<>();
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList<UserConfig.UserConfigAttr> arrayList = userConfig.D;
            arrayList.add(new UserConfig.UserConfigAttr("time", "" + currentTimeMillis));
            userConfig.D.add(new UserConfig.UserConfigAttr("value", a(list).toString()));
            UserConfigApi.a().a(CommonApplication.getAppContext(), userConfig, asyncCallback);
            SharePrefsManager.b(CommonApplication.getAppContext(), f15432a, d, currentTimeMillis);
            b(list);
        }
    }

    public static void b() {
        String c2 = SharePrefsManager.c(CommonApplication.getAppContext(), f15432a, c, (String) null);
        k = c2 + "";
    }

    public static boolean c() {
        return !TextUtils.isEmpty(k);
    }

    public static int d() {
        if (TextUtils.isEmpty(k)) {
            return 0;
        }
        if (k.contains(",")) {
            return k.split(",").length;
        }
        return 1;
    }

    public static String a(int i2) {
        String b2 = b(i2);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        if (!b2.contains("|")) {
            return b2;
        }
        String[] split = b2.split(PaymentOptionsDecoder.pipeSeparator);
        if (split == null || split.length <= 0) {
            return null;
        }
        return split[0];
    }

    protected static int a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || d() <= 0) {
            return -1;
        }
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String str3 = split[i2];
            if (!TextUtils.isEmpty(str3)) {
                if (str3.contains("|")) {
                    str3 = str3.split(PaymentOptionsDecoder.pipeSeparator)[0];
                }
                if (str2.equals(str3)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    protected static String b(String str, String str2) {
        int a2 = a(str, str2);
        if (a2 < 0) {
            return str;
        }
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < split.length; i2++) {
            if (i2 != a2) {
                arrayList.add(split[i2]);
            }
        }
        String str3 = "";
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            str3 = str3 + arrayList + ",";
        }
        return str3;
    }

    protected static String a(String str, String str2, String str3) {
        int a2 = a(str, str2);
        if (a2 < 0) {
            return str;
        }
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < split.length; i2++) {
            if (i2 == a2) {
                String str4 = split[i2];
                if (!TextUtils.isEmpty(str4)) {
                    if (str4.contains("|")) {
                        String[] split2 = str4.split(PaymentOptionsDecoder.pipeSeparator);
                        if (split2.length >= 3) {
                            split2[2] = str3;
                            String str5 = "";
                            for (int i3 = 0; i3 < split2.length; i3++) {
                                str5 = str5 + split2[i3];
                                if (i3 < split2.length - 1) {
                                    str5 = str5 + "|";
                                }
                            }
                            split[i2] = str5;
                        }
                    }
                }
            }
            arrayList.add(split[i2]);
        }
        String str6 = "";
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            str6 = str6 + arrayList + ",";
        }
        return str6;
    }

    public static void a(String str) {
        b();
        k = b(k, str);
        b(k);
    }

    public static void c(String str, String str2) {
        b();
        k = a(k, str, str2);
        b(k);
    }

    public static String b(int i2) {
        if (d() <= 0 || i2 < 0) {
            return null;
        }
        String[] split = k.split(",");
        if (i2 >= split.length) {
            return null;
        }
        return split[i2];
    }

    public static List<String> e() {
        int d2 = d();
        if (d2 <= 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(d2);
        for (int i2 = 0; i2 < d2; i2++) {
            String b2 = b(i2);
            if (!TextUtils.isEmpty(b2) && b2.contains("|")) {
                arrayList.add(b2);
            }
        }
        return arrayList;
    }

    public static void b(List<DeviceGroup> list) {
        if (CoreApi.a().q()) {
            String str = "";
            if (list != null) {
                String str2 = str;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    DeviceGroup deviceGroup = list.get(i2);
                    if (!(deviceGroup == null || deviceGroup.c == null)) {
                        String str3 = str2 + "bssid_" + deviceGroup.f19530a + ",";
                        List<Device> list2 = deviceGroup.c;
                        String str4 = str3;
                        for (int i3 = 0; i3 < list2.size(); i3++) {
                            Device device = list2.get(i3);
                            if (device != null) {
                                String str5 = device.did;
                                if (device instanceof BleDevice) {
                                    str5 = str5 + "|" + device.model + "|" + device.name + "|" + device.token;
                                }
                                str4 = str4 + str5 + ",";
                            }
                        }
                        str2 = str4;
                    }
                }
                str = str2;
            }
            k = str + "";
            b(str);
        }
    }

    public static void c(List<String> list) {
        if (CoreApi.a().q()) {
            String str = "";
            if (list != null) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    String str2 = list.get(i2);
                    if (!TextUtils.isEmpty(str2)) {
                        str = str + str2 + ",";
                    }
                }
            }
            k = str + "";
            b(str);
        }
    }

    @Deprecated
    public static void d(List<Pair<String, Integer>> list) {
        if (CoreApi.a().q()) {
            String str = "";
            if (list != null) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    String str2 = (String) list.get(i2).first;
                    Device b2 = SmartHomeDeviceManager.a().b(str2);
                    if (b2 != null) {
                        if (b2 instanceof BleDevice) {
                            str2 = str2 + "|" + b2.model + "|" + b2.name + "|" + b2.token;
                        }
                        str = str + str2 + ",";
                    }
                }
            }
            k = str + "";
            b(str);
        }
    }

    public static void b(String str) {
        if (CoreApi.a().q()) {
            SharePrefsManager.a(CommonApplication.getAppContext(), f15432a, c, str);
        }
    }

    public static int c(String str) {
        String str2 = k;
        return a(str2, "bssid_" + str);
    }
}
