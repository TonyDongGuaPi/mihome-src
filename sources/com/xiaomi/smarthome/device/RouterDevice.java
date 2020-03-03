package com.xiaomi.smarthome.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthomedevice.R;
import java.text.DecimalFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterDevice extends MiioDeviceV2 {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14952a = "RouterDevice";
    static final int j = 5;
    public static final DecimalFormat o = new DecimalFormat("0.#");
    public static final DecimalFormat p = new DecimalFormat("0.##");
    private static int q = 1;
    public int b;
    public int c;
    public long d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int[] k = new int[5];
    public double[] l = new double[5];
    public boolean m = false;
    int n = 3;
    private String r;

    public boolean a(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return false;
    }

    public boolean b(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return false;
    }

    public boolean canBeShared() {
        return false;
    }

    public boolean canRename() {
        return false;
    }

    public boolean isNoneOperatableDevice() {
        return false;
    }

    public RouterDevice() {
        this.canAuth = false;
    }

    /* access modifiers changed from: protected */
    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i2 = q;
            q = i2 + 1;
            jSONObject.put("id", i2);
            jSONObject.put("method", "router_stat");
            jSONObject.put("params", new JSONArray());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public String m() {
        return this.r;
    }

    public void parseExtra(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.b = jSONObject.optInt("lanSpeed");
                this.c = jSONObject.optInt("installedPluginCount");
                this.d = jSONObject.optLong("useableSpace");
                this.e = jSONObject.optInt("wanSpeed");
                this.f = jSONObject.optInt("downloading");
                this.g = jSONObject.optInt("downloads");
                this.m = false;
                this.r = str;
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        int i2 = 0;
        if (str == null) {
            Log.e(f14952a, "result is null");
            this.n--;
            if (this.n < 0) {
                this.m = true;
                this.b = 0;
                this.c = 0;
                this.d = 0;
                this.e = 10;
                this.f = 0;
                this.g = 0;
                this.k[this.k.length - 1] = this.e;
                return;
            }
            return;
        }
        this.n = 3;
        try {
            parseExtra(str);
            if (this.e > 0) {
                int i3 = this.e;
                int i4 = this.e;
                int i5 = i3;
                int i6 = 0;
                while (i6 < this.k.length - 1) {
                    if (i4 < this.k[i6]) {
                        i4 = this.k[i6];
                    }
                    if (i5 > this.k[i6]) {
                        i5 = this.k[i6];
                    }
                    int i7 = i6 + 1;
                    this.k[i6] = this.k[i7];
                    i6 = i7;
                }
                this.k[this.k.length - 1] = this.e;
                double d2 = (double) (i4 - i5);
                if (d2 > 0.0d) {
                    while (i2 < this.k.length) {
                        double[] dArr = this.l;
                        double d3 = (double) (this.k[i2] - i5);
                        Double.isNaN(d3);
                        Double.isNaN(d2);
                        dArr[i2] = d3 / d2;
                        i2++;
                    }
                } else {
                    while (i2 < this.k.length) {
                        this.l[i2] = 1.0d;
                        i2++;
                    }
                }
            }
        } catch (Exception unused) {
        }
        notifyStateChanged();
        SmartHomeDeviceManager.a().a((Device) this);
    }

    public CharSequence getStatusDescription(Context context) {
        if (this.m) {
            return context.getString(R.string.router_speed_no_info);
        }
        if (this.e == 0) {
            return context.getString(R.string.router_no_speed);
        }
        if (this.e < 1024) {
            return context.getString(R.string.router_current_speed_format, new Object[]{String.valueOf(p.format((double) (((float) this.e) / 1024.0f)))});
        }
        return context.getString(R.string.router_current_speed_format, new Object[]{String.valueOf(o.format((double) (((float) this.e) / 1024.0f)))});
    }

    public void bindDevice(Context context, final Device.IBindDeviceCallback iBindDeviceCallback) {
        new RouterDeviceLoginHelper().a(context, this, new AsyncResponseCallback<Void>() {
            public void a(Void voidR) {
                CameraApi.getInstance().doBindCameraSuscess(this, (AsyncCallback<Void, Error>) null);
                MobclickAgent.a(CommonApplication.getAppContext(), StatUtil.c, "bind_router");
                if (iBindDeviceCallback != null) {
                    iBindDeviceCallback.b();
                }
                Toast.makeText(CommonApplication.getAppContext(), R.string.button_bind_success, 0).show();
            }

            public void a(int i) {
                if (iBindDeviceCallback != null) {
                    iBindDeviceCallback.c();
                }
            }

            public void a(int i, Object obj) {
                if (iBindDeviceCallback != null) {
                    iBindDeviceCallback.c();
                }
            }
        });
    }
}
