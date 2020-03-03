package com.xiaomi.smarthome.miio.device;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartBulbDevice extends MiioDeviceV2 {

    /* renamed from: a  reason: collision with root package name */
    public static final int f13563a = 1;
    public static final int b = 2;
    public static final int c = 10000;
    public static final int d = 1500;
    public static final int e = 10;
    public static final int f = 100;
    public static final int g = 0;
    private static int q = 1;
    boolean h = false;
    boolean i = false;
    UIListener j;
    int k = 0;
    int l;
    int m;
    int n;
    int o;
    Handler p = new BulbHandler(Looper.getMainLooper(), this);

    public interface UIListener {
        void onGetBulbStatus();

        void onLightChanged();

        void onNameCHangeFailed();

        void onNameChangeSuccess();
    }

    public void a(String str) {
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    /* access modifiers changed from: protected */
    public JSONObject c() {
        return null;
    }

    public boolean z() {
        return false;
    }

    static class BulbHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        final WeakReference<SmartBulbDevice> f13573a;

        public BulbHandler(Looper looper, SmartBulbDevice smartBulbDevice) {
            super(looper);
            this.f13573a = new WeakReference<>(smartBulbDevice);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    SmartBulbDevice smartBulbDevice = (SmartBulbDevice) this.f13573a.get();
                    if (smartBulbDevice != null) {
                        smartBulbDevice.x();
                        smartBulbDevice.p.sendEmptyMessageDelayed(1, 10000);
                        return;
                    }
                    return;
                case 2:
                    SmartBulbDevice smartBulbDevice2 = (SmartBulbDevice) this.f13573a.get();
                    if (smartBulbDevice2 != null) {
                        smartBulbDevice2.i = false;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public SmartBulbDevice() {
    }

    public SmartBulbDevice(String str, String str2) {
        super(str, str2);
        x();
    }

    public void a() {
        this.p.removeMessages(1);
        this.p.sendEmptyMessageDelayed(1, 10000);
    }

    public void b() {
        this.p.removeMessages(1);
    }

    /* renamed from: o */
    public String getName() {
        return this.name;
    }

    public String d() {
        return this.mac;
    }

    public void a(UIListener uIListener) {
        this.j = uIListener;
    }

    public int e() {
        return Color.rgb(this.m, this.n, this.o);
    }

    public boolean r() {
        return this.l <= 0;
    }

    public int s() {
        return this.l;
    }

    public void t() {
        if (this.k > 0) {
            a(this.k);
        } else {
            a(100);
        }
    }

    public void u() {
        this.k = this.l;
        a(0);
    }

    public void v() {
        if (this.l < 100) {
            int i2 = this.l + 10;
            if (i2 > 100) {
                i2 = 100;
            }
            a(i2);
        }
    }

    public void w() {
        if (this.l != 0) {
            int i2 = this.l - 10;
            if (i2 < 0) {
                i2 = 0;
            }
            a(i2);
        }
    }

    public void a(final int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i3 = q;
            q = i3 + 1;
            jSONObject.put("id", i3);
            jSONObject.put("method", "set_bright");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(i2);
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        this.h = true;
        a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                SmartBulbDevice.this.h = false;
                SmartBulbDevice.this.p.post(new Runnable() {
                    public void run() {
                        SmartBulbDevice.this.l = i2;
                        if (SmartBulbDevice.this.j != null) {
                            SmartBulbDevice.this.j.onLightChanged();
                        }
                        SmartBulbDevice.this.notifyStateChanged();
                    }
                });
            }

            public void onFailure(Error error) {
                SmartBulbDevice.this.h = false;
                SmartBulbDevice.this.p.post(new Runnable() {
                    public void run() {
                        SmartBulbDevice.this.notifyStateChanged();
                    }
                });
            }
        });
    }

    public void a(final int i2, final int i3, final int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i5 = q;
            q = i5 + 1;
            jSONObject.put("id", i5);
            jSONObject.put("method", "set_rgb");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(Color.argb(0, i2, i3, i4));
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        this.h = true;
        a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                SmartBulbDevice.this.h = false;
                SmartBulbDevice.this.p.post(new Runnable() {
                    public void run() {
                        SmartBulbDevice.this.m = i2;
                        SmartBulbDevice.this.n = i3;
                        SmartBulbDevice.this.o = i4;
                    }
                });
            }

            public void onFailure(Error error) {
                SmartBulbDevice.this.h = false;
            }
        });
    }

    public void a(int i2, int i3, int i4, int i5) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i6 = q;
            q = i6 + 1;
            jSONObject.put("id", i6);
            jSONObject.put("method", "set_color");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(Color.argb(0, i2, i3, i4));
            jSONArray.put(i5);
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        this.i = true;
        this.p.sendEmptyMessageDelayed(2, Constants.x);
        this.h = true;
        final int i7 = i2;
        final int i8 = i3;
        final int i9 = i4;
        final int i10 = i5;
        a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                SmartBulbDevice.this.h = false;
            }

            public void onFailure(Error error) {
                SmartBulbDevice.this.h = false;
                SmartBulbDevice.this.p.postDelayed(new Runnable() {
                    public void run() {
                        SmartBulbDevice.this.m = i7;
                        SmartBulbDevice.this.n = i8;
                        SmartBulbDevice.this.o = i9;
                        SmartBulbDevice.this.l = i10;
                    }
                }, 0);
            }
        });
    }

    public void x() {
        if (!this.h) {
            JSONObject jSONObject = new JSONObject();
            try {
                int i2 = q;
                q = i2 + 1;
                jSONObject.put("id", i2);
                jSONObject.put("method", "get_prop");
                jSONObject.put("params", new JSONObject());
                JSONArray jSONArray = new JSONArray();
                jSONArray.put("bright");
                jSONArray.put("rgb");
                jSONObject.put("params", jSONArray);
            } catch (JSONException unused) {
            }
            a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        JSONArray optJSONArray = jSONObject.optJSONArray("result");
                        if (optJSONArray != null && optJSONArray.length() >= 2) {
                            SmartBulbDevice.this.l = optJSONArray.getInt(0);
                            int i = optJSONArray.getInt(1);
                            SmartBulbDevice.this.m = Color.red(i);
                            SmartBulbDevice.this.o = Color.blue(i);
                            SmartBulbDevice.this.n = Color.green(i);
                        }
                    } catch (JSONException unused) {
                    }
                    SmartBulbDevice.this.p.post(new Runnable() {
                        public void run() {
                            if (SmartBulbDevice.this.j != null) {
                                SmartBulbDevice.this.j.onGetBulbStatus();
                            }
                        }
                    });
                }
            });
        }
    }

    public void y() {
        if (r()) {
            t();
        } else {
            u();
        }
    }

    public boolean isOpen() {
        return this.l > 0;
    }

    public boolean a(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        t();
        if (deviceCallback == null) {
            return true;
        }
        deviceCallback.a(null);
        return true;
    }

    public boolean b(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        u();
        if (deviceCallback == null) {
            return true;
        }
        deviceCallback.a(null);
        return true;
    }

    public void parseProp() {
        if (this.propInfo != null && this.isOnline) {
            this.l = this.propInfo.optInt("bright");
            int optInt = this.propInfo.optInt("rgb");
            this.m = Color.red(optInt);
            this.n = Color.green(optInt);
            this.o = Color.blue(optInt);
        }
    }

    public boolean A() {
        return this.i;
    }
}
