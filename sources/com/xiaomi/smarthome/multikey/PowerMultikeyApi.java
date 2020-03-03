package com.xiaomi.smarthome.multikey;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.model.RoomConfig;
import com.xiaomi.smarthome.light.group.LightGroupMemberUpdateActivity;
import com.xiaomi.smarthome.multikey.PowerMultikeyBean;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PowerMultikeyApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20185a = "PowerMultikeyApi";
    private static final String b = "pref_recommend_config_name";
    private static final String c = "pref_recommend_config_last_url";

    public void a(String str, AsyncCallback<HashMap<String, PowerMultikeyBean>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("get_sub_relation", true);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("dids", jSONArray);
        } catch (JSONException e) {
            Log.e(f20185a, "requestDeviceinfo", e);
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/deviceinfo").b((List<KeyValuePair>) arrayList).a(), new JsonParser<HashMap<String, PowerMultikeyBean>>() {
            /* renamed from: a */
            public HashMap<String, PowerMultikeyBean> parse(JSONObject jSONObject) throws JSONException {
                JSONObject optJSONObject;
                HashMap<String, PowerMultikeyBean> hashMap = new HashMap<>();
                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                if (!(optJSONArray == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0).optJSONObject("member_ship")) == null)) {
                    Iterator<String> keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(next, PowerMultikeyBean.a(optJSONObject.optJSONObject(next)));
                    }
                }
                return hashMap;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void a(String str, ArrayList<PowerMultikeyBean> arrayList, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList2 = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<PowerMultikeyBean> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().c());
            }
            jSONObject.put(LightGroupMemberUpdateActivity.KEY_MEMBER, jSONArray);
            jSONObject.put("did", str);
        } catch (JSONException e) {
            Log.e(f20185a, "requestInit", e);
        }
        arrayList2.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/init_membership").b((List<KeyValuePair>) arrayList2).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void b(String str, ArrayList<PowerMultikeyBean> arrayList, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList2 = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<PowerMultikeyBean> it = arrayList.iterator();
            while (it.hasNext()) {
                PowerMultikeyBean next = it.next();
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("id", Long.parseLong(next.f20191a));
                } catch (NumberFormatException e) {
                    Log.e(f20185a, "requestUpdate", e);
                }
                jSONObject2.put("field", next.c());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("update_fields", jSONArray);
            jSONObject.put("did", str);
        } catch (JSONException e2) {
            Log.e(f20185a, "requestUpdate", e2);
        }
        arrayList2.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/update_membership").b((List<KeyValuePair>) arrayList2).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void b(String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
        } catch (JSONException e) {
            Log.e(f20185a, "requestClear", e);
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/clear_membership").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void a(Device device, AsyncCallback<DeviceImageApi.DeviceImageEntity, Error> asyncCallback) {
        DeviceImageApi.a(device.model, asyncCallback);
    }

    public void b(Device device, AsyncCallback<PowerMultikeyBean.PowerMultikeyList, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", device.model);
            jSONObject.put("did", device.did);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/multi_button_template").b((List<KeyValuePair>) arrayList).a(), new JsonParser<PowerMultikeyBean.PowerMultikeyList>() {
            /* renamed from: a */
            public PowerMultikeyBean.PowerMultikeyList parse(JSONObject jSONObject) throws JSONException {
                return new PowerMultikeyBean.PowerMultikeyList(PowerMultikeyBean.a(jSONObject.optJSONArray(LightGroupMemberUpdateActivity.KEY_MEMBER)), jSONObject.optString("page_pic"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static String a(List<PowerMultikeyBean> list, int i) {
        int size = list.size();
        if (size == 2) {
            return SHApplication.getAppContext().getResources().getStringArray(R.array.multikey_keyname_double)[i];
        }
        if (size == 3) {
            return SHApplication.getAppContext().getResources().getStringArray(R.array.multikey_keyname_three)[i];
        }
        int i2 = i + 1;
        return SHApplication.getAppContext().getResources().getQuantityString(R.plurals.multikey_keyname_more, i2, new Object[]{Integer.valueOf(i2)});
    }

    public static String b(List<PowerMultikeyBean> list, int i) {
        Room i2 = HomeManager.a().i(list.get(i).b);
        if (i2 == null) {
            return SHApplication.getAppContext().getString(R.string.room_default);
        }
        String e = i2.e();
        return TextUtils.isEmpty(e) ? SHApplication.getAppContext().getString(R.string.room_default) : e;
    }

    @NonNull
    public static List<String> a() {
        List<RoomConfig> A = ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).A();
        if (A.size() > 0) {
            A.remove(A.size() - 1);
        }
        ArrayList arrayList = new ArrayList();
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        for (RoomConfig next : A) {
            if (!TextUtils.isEmpty(next.a(I))) {
                arrayList.add(next.a(I));
            }
        }
        return arrayList;
    }

    @NonNull
    public static List<String> b() {
        List<Room> a2 = HomeManager.a().a(HomeManager.a().l());
        ArrayList arrayList = new ArrayList();
        for (Room e : a2) {
            arrayList.add(e.e());
        }
        return arrayList;
    }
}
