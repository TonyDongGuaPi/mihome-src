package com.xiaomi.smarthome.scenenew.lumiscene;

import android.content.Intent;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.coloros.mcssdk.mode.CommandMessage;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalSceneBuilder {
    private static final int c = 700;
    private static final LocalSceneBuilder d = new LocalSceneBuilder();

    /* renamed from: a  reason: collision with root package name */
    private String f21951a = "miIO.xdel";
    private String b = "miIO.xset";

    /* access modifiers changed from: private */
    public static /* synthetic */ String a(String str) throws JSONException {
        return str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String b(String str) throws JSONException {
        return str;
    }

    public static LocalSceneBuilder a() {
        return d;
    }

    public void a(DeviceStat deviceStat, String str, MessageCallback messageCallback) {
        JSONArray jSONArray;
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray(CommandMessage.COMMAND);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("method", (String) null);
                        if (this.f21951a.equalsIgnoreCase(optString)) {
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("params");
                            if (optJSONArray2 != null) {
                                a(optJSONArray2, deviceStat, messageCallback);
                            }
                        } else if (this.b.equalsIgnoreCase(optString) && (jSONArray = optJSONObject.getJSONArray("params")) != null) {
                            a(jSONArray.toString(), deviceStat, messageCallback);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONArray jSONArray, DeviceStat deviceStat, final MessageCallback messageCallback) {
        LmBaseDevice lmBaseDevice = new LmBaseDevice(deviceStat);
        DeviceRequest deviceRequest = new DeviceRequest();
        deviceRequest.e = 1;
        deviceRequest.m = lmBaseDevice.getDid();
        deviceRequest.n = lmBaseDevice.getModel();
        deviceRequest.g = this.f21951a;
        deviceRequest.j = jSONArray;
        LumiHostApi.a(deviceRequest, new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                Log.d("summer", "del gateway scene>>>" + str);
                messageCallback.onSuccess(new Intent());
            }

            public void onFailure(int i, String str) {
                Log.e("summer", str);
                messageCallback.onFailure(i, str);
            }
        }, CommonBusiness.f21946a);
    }

    public void a(String str, DeviceStat deviceStat, int i, String str2, MessageCallback messageCallback) {
        int i2 = i;
        int i3 = i2 < 1 ? 700 : i2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        a(str, i3, (List<String>) arrayList);
        int size = arrayList.size();
        int nextInt = new Random().nextInt(65535);
        for (int i4 = 0; i4 < size; i4++) {
            a(deviceStat, str2, nextInt, i4, size, (String) arrayList.get(i4), arrayList2, messageCallback);
        }
    }

    private void a(DeviceStat deviceStat, String str, int i, int i2, final int i3, String str2, final List<Boolean> list, final MessageCallback messageCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("data_tkn", Integer.valueOf(i));
        hashMap.put("type", str);
        hashMap.put("cur", Integer.valueOf(i2));
        hashMap.put("total", Integer.valueOf(i3));
        hashMap.put("data", str2);
        LmBaseDevice lmBaseDevice = new LmBaseDevice(deviceStat);
        Log.d("summer", "summer send_data_frame>>" + JSON.toJSONString(hashMap));
        LumiHostApi.a(CommonRequest.a(lmBaseDevice, "send_data_frame", hashMap), new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                list.add(true);
                if (list.size() != i3) {
                    return;
                }
                if (list.contains(false)) {
                    messageCallback.onFailure(-1, "network request error...");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("result", str);
                messageCallback.onSuccess(intent);
            }

            public void onFailure(int i, String str) {
                list.add(false);
                if (list.size() != i3) {
                    return;
                }
                if (list.contains(false)) {
                    messageCallback.onFailure(i, str);
                } else {
                    messageCallback.onSuccess(new Intent());
                }
            }
        }, $$Lambda$LocalSceneBuilder$z05ft5v8tGZmaInjdZB7hVR5968.INSTANCE);
    }

    private void a(String str, DeviceStat deviceStat, MessageCallback messageCallback) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        a(str, 700, (List<String>) arrayList);
        int size = arrayList.size();
        int nextInt = new Random().nextInt(65535);
        for (int i = 0; i < size; i++) {
            a(deviceStat, nextInt, i, size, (String) arrayList.get(i), arrayList2, messageCallback);
        }
    }

    private void a(DeviceStat deviceStat, int i, int i2, final int i3, String str, final List<Boolean> list, final MessageCallback messageCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("data_tkn", Integer.valueOf(i));
        hashMap.put("type", PageUrl.j);
        hashMap.put("cur", Integer.valueOf(i2));
        hashMap.put("total", Integer.valueOf(i3));
        hashMap.put("data", str);
        LmBaseDevice lmBaseDevice = new LmBaseDevice(deviceStat);
        Log.d("summer", "summer send_data_frame>>" + JSON.toJSONString(hashMap));
        LumiHostApi.a(CommonRequest.a(lmBaseDevice, "send_data_frame", hashMap), new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                list.add(true);
                if (list.size() != i3) {
                    return;
                }
                if (list.contains(false)) {
                    messageCallback.onFailure(-1, "");
                } else {
                    messageCallback.onSuccess(new Intent());
                }
            }

            public void onFailure(int i, String str) {
                list.add(false);
                if (list.size() != i3) {
                    return;
                }
                if (list.contains(false)) {
                    messageCallback.onFailure(-1, "");
                } else {
                    messageCallback.onSuccess(new Intent());
                }
            }
        }, $$Lambda$LocalSceneBuilder$wlfTOQEaN0IftxfdSwJ4os1gMJw.INSTANCE);
    }

    private void a(String str, int i, List<String> list) {
        if (str != null && i > 0) {
            if (a(str, str.length()) <= i) {
                list.add(str);
                return;
            }
            int a2 = a(str, i);
            String substring = str.substring(0, a2);
            String substring2 = str.substring(a2);
            list.add(substring);
            a(substring2, a2, list);
        }
    }

    private int a(String str, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            i3 = str.substring(i2, i4).matches("[Α-￥]") ? i3 + 3 : i3 + 1;
            if (i3 >= i) {
                return i2;
            }
            i2 = i4;
        }
        return i;
    }
}
