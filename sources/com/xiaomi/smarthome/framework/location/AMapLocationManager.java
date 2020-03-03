package com.xiaomi.smarthome.framework.location;

import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.util.ArrayList;
import java.util.List;

public class AMapLocationManager {
    private static volatile AMapLocationManager c;

    /* renamed from: a  reason: collision with root package name */
    private AMapLocationClientOption f16498a = null;
    /* access modifiers changed from: private */
    public AMapLocationClient b;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public Object e = new Object();
    /* access modifiers changed from: private */
    public List<LocationCallback> f = new ArrayList();

    public interface LocationCallback {
        void a();

        void a(AMapLocation aMapLocation);
    }

    private AMapLocationManager() {
    }

    /* access modifiers changed from: private */
    public AMapLocationClientOption d() {
        if (this.f16498a != null) {
            return this.f16498a;
        }
        this.f16498a = new AMapLocationClientOption();
        this.f16498a.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        this.f16498a.setNeedAddress(true);
        this.f16498a.setOnceLocation(true);
        this.f16498a.setWifiActiveScan(true);
        this.f16498a.setMockEnable(false);
        return this.f16498a;
    }

    public static AMapLocationManager a() {
        if (c == null) {
            synchronized (AMapLocationManager.class) {
                if (c == null) {
                    c = new AMapLocationManager();
                }
            }
        }
        return c;
    }

    private void e() {
        if (this.b != null) {
            this.b.onDestroy();
        }
        this.b = new AMapLocationClient(CommonApplication.getAppContext());
    }

    public void a(LocationCallback locationCallback) {
        if (locationCallback != null) {
            if (ServerCompact.g(CommonApplication.getAppContext())) {
                locationCallback.a();
                return;
            }
            synchronized (this.e) {
                this.f.add(locationCallback);
                if (!this.d) {
                    e();
                    this.b.setLocationOption(d());
                    this.d = true;
                    this.b.setLocationListener(new AMapLocationListener() {
                        public void onLocationChanged(AMapLocation aMapLocation) {
                            ArrayList<LocationCallback> arrayList = new ArrayList<>();
                            synchronized (AMapLocationManager.this.e) {
                                arrayList.addAll(AMapLocationManager.this.f);
                                AMapLocationManager.this.f.clear();
                                if (AMapLocationManager.this.d().isOnceLocation()) {
                                    AMapLocationManager.this.b();
                                    AMapLocationManager.this.b.onDestroy();
                                    AMapLocationClient unused = AMapLocationManager.this.b = null;
                                }
                                boolean unused2 = AMapLocationManager.this.d = false;
                            }
                            if (aMapLocation != null) {
                                for (LocationCallback a2 : arrayList) {
                                    a2.a(aMapLocation);
                                }
                            } else {
                                Log.w("AMapLocationManager", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                                for (LocationCallback a3 : arrayList) {
                                    a3.a();
                                }
                            }
                            arrayList.clear();
                        }
                    });
                    this.b.startLocation();
                }
            }
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.stopLocation();
        }
    }

    public void c() {
        c = null;
        try {
            this.b.stopLocation();
            this.b.onDestroy();
        } catch (Exception unused) {
        }
    }
}
