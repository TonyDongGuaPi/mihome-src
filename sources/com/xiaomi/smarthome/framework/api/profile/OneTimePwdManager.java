package com.xiaomi.smarthome.framework.api.profile;

import android.text.TextUtils;
import android.util.Log;
import com.sina.weibo.sdk.statistic.LogBuilder;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ByteUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.api.model.OneTimePasswordInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneTimePwdManager {

    /* renamed from: a  reason: collision with root package name */
    public static TreeMap<Long, OneTimePasswordInfo> f16465a = new TreeMap<>();
    private static final String b = "OneTimePwdManager";

    public static void a(Device device, JSONArray jSONArray, OneTimePasswordInfo oneTimePasswordInfo) {
        JSONArray jSONArray2;
        if (device != null && oneTimePasswordInfo != null) {
            JSONArray jSONArray3 = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("maketime", oneTimePasswordInfo.a());
                jSONObject.put(LogBuilder.i, oneTimePasswordInfo.b());
                jSONObject.put("expiretime", oneTimePasswordInfo.c());
                jSONObject.put("password", oneTimePasswordInfo.d());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONArray == null || jSONArray.length() == 0) {
                jSONArray3.put(jSONObject);
            } else {
                int i = 0;
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                    if (jSONObject2 != null) {
                        String optString = jSONObject2.optString("value", "");
                        if (!TextUtils.isEmpty(optString)) {
                            jSONArray3 = new JSONArray(optString);
                        }
                        if (jSONArray3.length() >= 0) {
                            while (i < jSONArray3.length()) {
                                JSONObject jSONObject3 = jSONArray3.getJSONObject(i);
                                long optLong = jSONObject3.optLong("expiretime");
                                if (optLong <= oneTimePasswordInfo.a()) {
                                    jSONArray3.remove(i);
                                    i--;
                                } else {
                                    String optString2 = jSONObject3.optString("password");
                                    if (!TextUtils.isEmpty(optString2) && optString2.length() <= 8) {
                                        jSONObject3.put("password", a(optString2, String.valueOf(optLong), device.mac, device.userId));
                                    }
                                }
                                i++;
                            }
                            jSONArray2 = jSONArray3;
                        } else {
                            jSONArray2 = new JSONArray();
                        }
                        try {
                            jSONArray2.put(jSONObject);
                            jSONArray3 = jSONArray2;
                        } catch (JSONException e2) {
                            JSONException jSONException = e2;
                            jSONArray3 = jSONArray2;
                            e = jSONException;
                            e.printStackTrace();
                            XmPluginHostApi.instance().setUserDeviceData(device.model, device.did, XmBluetoothRecord.TYPE_PROP, "device_lock", oneTimePasswordInfo.a(), jSONArray3, new Callback<JSONArray>() {
                                /* renamed from: a */
                                public void onSuccess(JSONArray jSONArray) {
                                    Log.e(OneTimePwdManager.b, " upload onetime pwd successfully.");
                                }

                                public void onFailure(int i, String str) {
                                    Log.e(OneTimePwdManager.b, "onFailure, upload onetime pwd failed, error = " + i + ", msg = " + str);
                                }
                            });
                        }
                    }
                } catch (JSONException e3) {
                    e = e3;
                    e.printStackTrace();
                    XmPluginHostApi.instance().setUserDeviceData(device.model, device.did, XmBluetoothRecord.TYPE_PROP, "device_lock", oneTimePasswordInfo.a(), jSONArray3, new Callback<JSONArray>() {
                        /* renamed from: a */
                        public void onSuccess(JSONArray jSONArray) {
                            Log.e(OneTimePwdManager.b, " upload onetime pwd successfully.");
                        }

                        public void onFailure(int i, String str) {
                            Log.e(OneTimePwdManager.b, "onFailure, upload onetime pwd failed, error = " + i + ", msg = " + str);
                        }
                    });
                }
            }
            XmPluginHostApi.instance().setUserDeviceData(device.model, device.did, XmBluetoothRecord.TYPE_PROP, "device_lock", oneTimePasswordInfo.a(), jSONArray3, new Callback<JSONArray>() {
                /* renamed from: a */
                public void onSuccess(JSONArray jSONArray) {
                    Log.e(OneTimePwdManager.b, " upload onetime pwd successfully.");
                }

                public void onFailure(int i, String str) {
                    Log.e(OneTimePwdManager.b, "onFailure, upload onetime pwd failed, error = " + i + ", msg = " + str);
                }
            });
        }
    }

    public static TreeMap<Long, OneTimePasswordInfo> a(Device device, JSONArray jSONArray, long j) {
        Device device2 = device;
        JSONArray jSONArray2 = jSONArray;
        TreeMap<Long, OneTimePasswordInfo> treeMap = new TreeMap<>();
        if (jSONArray2 == null) {
            return treeMap;
        }
        try {
            JSONObject jSONObject = jSONArray2.getJSONObject(0);
            if (jSONObject != null) {
                String optString = jSONObject.optString("value", "");
                if (TextUtils.isEmpty(optString)) {
                    return treeMap;
                }
                JSONArray jSONArray3 = new JSONArray(optString);
                if (jSONArray3.length() == 0) {
                    return treeMap;
                }
                for (int i = 0; i < jSONArray3.length(); i++) {
                    JSONObject jSONObject2 = jSONArray3.getJSONObject(i);
                    long optLong = jSONObject2.optLong("maketime", -1);
                    if (optLong < 0) {
                        optLong = jSONObject2.optLong("time", -1);
                    }
                    long optLong2 = jSONObject2.optLong(LogBuilder.i);
                    long optLong3 = jSONObject2.optLong("expiretime");
                    String optString2 = jSONObject2.optString("password");
                    if (optLong3 >= j) {
                        if (device2 != null && optString2.length() <= 8) {
                            optString2 = a(optString2, String.valueOf(optLong3), device2.mac, device2.userId);
                        }
                        treeMap.put(Long.valueOf(optLong), new OneTimePasswordInfo(optLong, optLong2, optLong3, optString2));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return treeMap;
    }

    public static String a(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(ByteUtils.c(str.getBytes()));
        if (str2 == null) {
            str2 = "";
        }
        sb.append(ByteUtils.c(str2.getBytes()));
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3.replace(":", ""));
        if (str4 == null) {
            str4 = "";
        }
        sb.append(ByteUtils.c(str4.getBytes()));
        try {
            String c = ByteUtils.c(MessageDigest.getInstance("SHA-256").digest(sb.toString().getBytes()));
            return c.substring(2, c.length() - 2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
