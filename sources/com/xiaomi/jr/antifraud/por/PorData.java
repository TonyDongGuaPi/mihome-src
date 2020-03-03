package com.xiaomi.jr.antifraud.por;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.DeviceHelper;
import com.xiaomi.jr.common.utils.FileSystemInfo;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PorData {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10304a = "device";
    private static final String b = "network";
    private static final String c = "timeline";
    private Context d;
    private JSONObject e = new JSONObject();

    public PorData(Context context) {
        this.d = context.getApplicationContext();
    }

    public String toString() {
        return this.e.toString();
    }

    public PorData a(String str, String str2, Object obj) {
        if (str == null) {
            try {
                this.e.put(str2, obj);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            JSONObject optJSONObject = this.e.optJSONObject(str);
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
                this.e.put(str, optJSONObject);
            }
            optJSONObject.put(str2, obj);
        }
        return this;
    }

    public PorData a() {
        try {
            this.e.put("timeline", EventTracker.a().d());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public PorData b() {
        a("device", "deviceIdMd5", Client.c(this.d));
        a("device", "deviceIdSha1", Client.d(this.d));
        a("device", "model", Build.MODEL);
        a("device", "device", Build.DEVICE);
        a("device", "tablet", Boolean.valueOf(DeviceHelper.f10365a));
        a("device", "serial", Client.a());
        a("device", "androidId", Client.a(this.d));
        a("device", "iccid", Client.q(this.d));
        a("device", "cpuId", Client.b());
        a("device", MipayConstants.ap, Long.valueOf(Client.c()));
        a("device", "mac", Client.l(this.d));
        a("device", "system", String.valueOf(1));
        a("device", "sdk", Integer.valueOf(Build.VERSION.SDK_INT));
        a("device", "imsi", Client.o(this.d));
        a("device", "phoneState", Integer.valueOf(Client.s(this.d)));
        Location a2 = Utils.a(this.d);
        if (a2 != null) {
            a("device", "longitude", Double.valueOf(a2.getLongitude()));
            a("device", "latitude", Double.valueOf(a2.getLatitude()));
            a("device", "altitude", Double.valueOf(a2.getAltitude()));
        }
        FileSystemInfo h = FileUtils.h("/system");
        a("device", "sysFree", Long.valueOf(h.b));
        a("device", "sysTotal", Long.valueOf(h.f1413a));
        int i = 0;
        List<String> a3 = MaskedPhoneNumHelper.a();
        if (a3 != null && a3.size() > 0) {
            for (String a4 : a3) {
                StringBuilder sb = new StringBuilder();
                sb.append("maskedPhoneNum");
                int i2 = i + 1;
                sb.append(i);
                a("device", sb.toString(), a4);
                i = i2;
            }
        }
        String d2 = Client.d();
        if (!TextUtils.isEmpty(d2)) {
            a("device", "pushRegId", d2);
        }
        return this;
    }

    public PorData c() {
        a("network", "type", NetworkUtils.c(this.d));
        a("network", "localIP", NetworkUtils.a(true));
        String m = Client.m(this.d);
        if (!TextUtils.isEmpty(m) && !m.equals("<unknown ssid>")) {
            a("network", DeviceTagInterface.e, m);
        }
        String n = Client.n(this.d);
        if (!TextUtils.isEmpty(n)) {
            a("network", "bssid", n);
        }
        return this;
    }
}
