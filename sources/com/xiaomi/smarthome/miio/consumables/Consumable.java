package com.xiaomi.smarthome.miio.consumables;

import android.text.TextUtils;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class Consumable {
    private static Pattern i = Pattern.compile("[0-9]*");

    /* renamed from: a  reason: collision with root package name */
    public double f13545a;
    public String b;
    public int c = -999;
    public String d = null;
    public String e;
    public String f;
    public boolean g;
    public String h;

    public static Consumable a(JSONObject jSONObject) {
        Consumable consumable = new Consumable();
        if (jSONObject == null) {
            return consumable;
        }
        if (jSONObject.has("description")) {
            consumable.d = jSONObject.optString("description");
        }
        if (jSONObject.has("value")) {
            consumable.b = jSONObject.optString("value");
            if (TextUtils.isEmpty(consumable.b)) {
                consumable.f13545a = 0.0d;
            } else if (a(consumable.b)) {
                try {
                    consumable.f13545a = Double.parseDouble(consumable.b);
                } catch (Exception unused) {
                    consumable.f13545a = -1.0d;
                }
            } else if (consumable.b.startsWith("-")) {
                consumable.f13545a = -9999.0d;
            } else if (TextUtils.equals("LOW", consumable.b)) {
                consumable.f13545a = -9998.0d;
            } else if (TextUtils.equals("ok", consumable.b.toLowerCase())) {
                consumable.f13545a = 9999.0d;
            }
        }
        if (jSONObject.has("days")) {
            String optString = jSONObject.optString("days");
            if (!TextUtils.isEmpty(optString) && a(optString)) {
                try {
                    consumable.c = Integer.parseInt(optString);
                } catch (Exception unused2) {
                    consumable.c = -999;
                }
            }
        }
        if (jSONObject.has("extra")) {
            consumable.e = jSONObject.optString("extra");
        }
        if (jSONObject.has(XmBluetoothRecord.TYPE_PROP)) {
            consumable.f = jSONObject.optString(XmBluetoothRecord.TYPE_PROP);
        }
        if (jSONObject.has("reset")) {
            consumable.g = jSONObject.optBoolean("reset");
        }
        if (jSONObject.has(DeviceTagInterface.f)) {
            consumable.h = jSONObject.optString(DeviceTagInterface.f);
        }
        if (a(consumable.b)) {
            if (consumable.f13545a < 0.0d) {
                consumable.f13545a = 0.0d;
            }
            if (consumable.f13545a > 100.0d) {
                consumable.f13545a = 100.0d;
            }
        }
        if (consumable.d == null) {
            consumable.d = "";
        }
        return consumable;
    }

    public static JSONObject a(Consumable consumable) {
        JSONObject jSONObject = new JSONObject();
        if (consumable == null) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(consumable.b)) {
                jSONObject.put("value", consumable.b);
            }
            if (!TextUtils.isEmpty(consumable.d)) {
                jSONObject.put("description", consumable.d);
            }
            if (!TextUtils.isEmpty(consumable.e)) {
                jSONObject.put("extra", consumable.e);
            }
            if (consumable.c >= 0) {
                jSONObject.put("days", consumable.c);
            }
            if (!TextUtils.isEmpty(consumable.f)) {
                jSONObject.put(XmBluetoothRecord.TYPE_PROP, consumable.f);
            }
            if (!TextUtils.isEmpty(consumable.h)) {
                jSONObject.put(DeviceTagInterface.f, consumable.h);
            }
            jSONObject.put("reset", consumable.g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && i.matcher(str).matches()) {
            return true;
        }
        return false;
    }
}
