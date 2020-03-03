package com.amap.openapi;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.RequiresPermission;
import com.amap.location.common.log.ALLog;
import java.util.List;

public class da implements cz {

    /* renamed from: a  reason: collision with root package name */
    private LocationManager f4681a;

    public da(Context context) {
        this.f4681a = (LocationManager) context.getSystemService("location");
    }

    public GpsStatus a(GpsStatus gpsStatus) {
        if (this.f4681a == null) {
            return null;
        }
        try {
            return this.f4681a.getGpsStatus(gpsStatus);
        } catch (SecurityException unused) {
            ALLog.d("@_24_1_@", "@_24_1_5_@");
            return null;
        }
    }

    public List<String> a() {
        if (this.f4681a == null) {
            return null;
        }
        return this.f4681a.getAllProviders();
    }

    public void a(GpsStatus.NmeaListener nmeaListener) {
        if (this.f4681a != null) {
            this.f4681a.removeNmeaListener(nmeaListener);
        }
    }

    public void a(LocationListener locationListener) {
        if (this.f4681a != null) {
            try {
                this.f4681a.removeUpdates(locationListener);
            } catch (Exception unused) {
                ALLog.d("@_24_1_@", "@_24_1_6_@");
            }
        }
    }

    public void a(OnNmeaMessageListener onNmeaMessageListener) {
        if (Build.VERSION.SDK_INT >= 24 && this.f4681a != null) {
            this.f4681a.removeNmeaListener(onNmeaMessageListener);
        }
    }

    public void a(String str, long j, float f, LocationListener locationListener, Looper looper) {
        try {
            if (this.f4681a != null) {
                this.f4681a.requestLocationUpdates(str, j, f, locationListener, looper);
            }
        } catch (SecurityException unused) {
            ALLog.d("@_24_1_@", "@_24_2_1_@");
        }
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public boolean a(GnssStatus.Callback callback) {
        if (this.f4681a != null && Build.VERSION.SDK_INT >= 24) {
            try {
                return this.f4681a.registerGnssStatusCallback(callback);
            } catch (SecurityException e) {
                ALLog.a("@_24_1_@", "@_24_1_7_@", (Exception) e);
            }
        }
        return false;
    }

    public boolean a(GpsStatus.Listener listener) {
        if (this.f4681a == null) {
            return false;
        }
        try {
            return this.f4681a.addGpsStatusListener(listener);
        } catch (SecurityException unused) {
            ALLog.d("@_24_1_@", "@_24_1_3_@");
            return false;
        }
    }

    public boolean a(GpsStatus.NmeaListener nmeaListener, Looper looper) {
        if (this.f4681a == null) {
            return false;
        }
        try {
            return this.f4681a.addNmeaListener(nmeaListener);
        } catch (SecurityException unused) {
            ALLog.d("@_24_1_@", "@_24_1_2_@");
            return false;
        }
    }

    public boolean a(OnNmeaMessageListener onNmeaMessageListener, Looper looper) {
        if (this.f4681a == null) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                return this.f4681a.addNmeaListener(onNmeaMessageListener);
            }
            return false;
        } catch (SecurityException unused) {
            ALLog.d("@_24_1_@", "@_24_1_2_@");
            return false;
        }
    }

    public boolean a(String str) {
        if (this.f4681a == null) {
            return false;
        }
        try {
            return this.f4681a.isProviderEnabled(str);
        } catch (Exception e) {
            ALLog.a("@_24_1_@", "@_24_1_4_@", e);
            return false;
        }
    }

    public void b(GnssStatus.Callback callback) {
        if (this.f4681a != null && Build.VERSION.SDK_INT >= 24) {
            this.f4681a.unregisterGnssStatusCallback(callback);
        }
    }

    public void b(GpsStatus.Listener listener) {
        if (this.f4681a != null) {
            this.f4681a.removeGpsStatusListener(listener);
        }
    }
}
