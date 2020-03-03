package com.xiaomi.smarthome.framework.location;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.location.AMapLocationManager;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.common.util.SystemUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import com.xiaomi.smarthome.shop.utils.NetworkUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SHLocationManager {
    static final int f = 1;
    static final int g = 2;
    static final int h = 3;
    static final int i = 4;
    static final int j = 5;
    static final int k = 6;
    static final int l = 7;
    private static SHLocationManager q;
    private static final Object r = new Object();

    /* renamed from: a  reason: collision with root package name */
    boolean f16509a = false;
    Location b = null;
    String c = "";
    List<LocationCallback> d = new ArrayList();
    Handler e = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Context m = CommonApplication.getAppContext();
    /* access modifiers changed from: private */
    public LocationListener n = new LocationListener() {
        public void onStatusChanged(String str, int i, Bundle bundle) {
            SHLocationManager.this.p.sendEmptyMessage(3);
        }

        public void onProviderEnabled(String str) {
            SHLocationManager.this.p.sendEmptyMessage(4);
        }

        public void onProviderDisabled(String str) {
            SHLocationManager.this.p.sendEmptyMessage(5);
        }

        public void onLocationChanged(Location location) {
            SHLocationManager.this.p.obtainMessage(6, location).sendToTarget();
        }
    };
    private MessageHandlerThread o = new MessageHandlerThread("LocationThread");
    /* access modifiers changed from: private */
    public LocationHandler p;

    public static class LocationCallback {
        public String object;

        public void onFailure(String str) {
        }

        public void onGetLatLngSucceed(String str, Location location) {
        }

        public void onSucceed(String str, Location location) {
        }

        public void onTimeout(String str) {
        }
    }

    class LocationHandler extends Handler {
        LocationHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (message.obj instanceof LocationCallback) {
                        LocationCallback locationCallback = (LocationCallback) message.obj;
                        if (!SHLocationManager.this.d.contains(locationCallback)) {
                            SHLocationManager.this.d.add(locationCallback);
                        }
                    }
                    SHLocationManager.this.h();
                    return;
                case 2:
                    SHLocationManager.this.a((Location) null);
                    return;
                case 3:
                case 4:
                case 5:
                    if (SHLocationManager.this.p.hasMessages(2)) {
                        SHLocationManager.this.a((Location) null);
                        return;
                    }
                    return;
                case 6:
                    if (!SHLocationManager.this.p.hasMessages(2)) {
                        return;
                    }
                    if (message.obj instanceof Location) {
                        final Location location = (Location) message.obj;
                        SHLocationManager.this.e.post(new Runnable() {
                            public void run() {
                                SHLocationManager.this.b = location;
                                SHLocationManager.this.b(SHLocationManager.this.c, location);
                            }
                        });
                        Message obtainMessage = SHLocationManager.this.p.obtainMessage();
                        obtainMessage.obj = location;
                        obtainMessage.what = 7;
                        SHLocationManager.this.p.sendMessage(obtainMessage);
                        return;
                    }
                    SHLocationManager.this.a((Location) null);
                    return;
                case 7:
                    if (message.obj instanceof Location) {
                        final Location location2 = (Location) message.obj;
                        try {
                            List<Address> fromLocation = new Geocoder(SHLocationManager.this.m).getFromLocation(location2.getLatitude(), location2.getLongitude(), 1);
                            if (fromLocation == null || fromLocation.size() <= 0) {
                                SHLocationManager.this.a(location2);
                                return;
                            }
                            SHLocationManager.this.a(location2, fromLocation.get(0), (String) null);
                            SHLocationManager.this.f16509a = false;
                            SHLocationManager.this.p.removeMessages(2);
                            ((LocationManager) SHLocationManager.this.m.getSystemService("location")).removeUpdates(SHLocationManager.this.n);
                            SHLocationManager.this.e.post(new Runnable() {
                                public void run() {
                                    SHLocationManager.this.b = location2;
                                    SHLocationManager.this.a(SHLocationManager.this.c, location2);
                                }
                            });
                            return;
                        } catch (Exception unused) {
                            SHLocationManager.this.a(location2);
                            return;
                        }
                    } else {
                        SHLocationManager.this.a((Location) null);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public static SHLocationManager a() {
        if (q == null) {
            synchronized (r) {
                if (q == null) {
                    q = new SHLocationManager();
                }
            }
        }
        return q;
    }

    private SHLocationManager() {
        this.o.start();
        this.p = new LocationHandler(this.o.getLooper());
    }

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void h() {
        if (!this.f16509a) {
            this.f16509a = true;
            LocationManager locationManager = (LocationManager) this.m.getSystemService("location");
            String str = "";
            try {
                if (locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK)) {
                    str = LogCategory.CATEGORY_NETWORK;
                } else if (locationManager.isProviderEnabled("gps")) {
                    str = "gps";
                } else if (locationManager.isProviderEnabled("passive")) {
                    str = "passive";
                }
                this.c = str;
                try {
                    locationManager.requestSingleUpdate(str, this.n, (Looper) null);
                } catch (Exception unused) {
                }
                this.p.sendEmptyMessageDelayed(2, 20000);
            } catch (Exception unused2) {
                this.f16509a = false;
                this.e.post(new Runnable() {
                    public void run() {
                        SHLocationManager.this.a("");
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Location location) {
        while (this.d.size() > 0) {
            try {
                this.d.get(0).onSucceed(str, location);
            } catch (Exception unused) {
            }
            this.d.remove(0);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str, Location location) {
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            try {
                this.d.get(i2).onGetLatLngSucceed(str, location);
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        while (this.d.size() > 0) {
            try {
                this.d.get(0).onFailure(str);
            } catch (Exception unused) {
            }
            this.d.remove(0);
        }
    }

    private void b(String str) {
        while (this.d.size() > 0) {
            try {
                this.d.get(0).onTimeout(str);
            } catch (Exception unused) {
            }
            this.d.remove(0);
        }
    }

    public Location b() {
        return this.b;
    }

    public boolean c() {
        try {
            return ((LocationManager) this.m.getSystemService("location")).isProviderEnabled(LogCategory.CATEGORY_NETWORK);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean d() {
        try {
            return ((LocationManager) this.m.getSystemService("location")).isProviderEnabled("gps");
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(LocationCallback locationCallback) {
        if (locationCallback != null) {
            StringWriter stringWriter = new StringWriter();
            new Exception().printStackTrace(new PrintWriter(stringWriter));
            locationCallback.object = stringWriter.toString();
        }
        this.p.obtainMessage(1, locationCallback).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void a(Location location, Address address, String str) {
        if (location == null) {
            return;
        }
        if (address != null || !TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            if (address != null) {
                bundle.putParcelable("address", address);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("address_str", str);
            }
            location.setExtras(bundle);
        }
    }

    private void i() {
        if (this.d != null && this.d.size() != 0) {
            for (LocationCallback next : this.d) {
                if (next != null && !TextUtils.isEmpty(next.object)) {
                    int i2 = 200;
                    if (next.object.length() <= 200) {
                        i2 = next.object.length();
                    }
                    if (next.object.contains("XmPluginHostApiImpl")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_plugin", c(next.object), (String) null, false);
                    } else if (next.object.contains("PluginService")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_PluginService", (String) null, (String) null, false);
                    } else if (next.object.contains("addGPSInfo")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_addGPSInfo", (String) null, (String) null, false);
                    } else if (next.object.contains("onStart")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_onStart", (String) null, (String) null, false);
                    } else {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get", next.object.substring(0, i2), (String) null, false);
                    }
                }
            }
        }
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        boolean z = false;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (z || readLine.contains("at com.")) {
                    z = true;
                    if (!readLine.contains("com.xiaomi.smarthome") && !readLine.contains("com.xiaomi.router")) {
                        if (!readLine.contains("com.xiaomi.shop2")) {
                            int i2 = 30;
                            if (readLine.length() <= 30) {
                                i2 = readLine.length();
                            }
                            return readLine.substring(0, i2);
                        }
                    }
                }
            } catch (IOException unused) {
            }
        }
        return null;
    }

    public void a(final Location location) {
        AMapLocationManager.a().a((AMapLocationManager.LocationCallback) new AMapLocationManager.LocationCallback() {
            public void a(AMapLocation aMapLocation) {
                Location location = location;
                if (location == null) {
                    location = new Location(SHLocationManager.this.c);
                }
                location.setLongitude(aMapLocation.getLongitude());
                location.setLatitude(aMapLocation.getLatitude());
                Address address = new Address(Locale.getDefault());
                address.setLatitude(aMapLocation.getLatitude());
                address.setLongitude(aMapLocation.getLongitude());
                if (!TextUtils.isEmpty(aMapLocation.getProvince()) && !"null".equalsIgnoreCase(aMapLocation.getProvince())) {
                    address.setAdminArea(aMapLocation.getProvince());
                }
                if (!TextUtils.isEmpty(aMapLocation.getCity()) && !"null".equalsIgnoreCase(aMapLocation.getCity())) {
                    address.setLocality(aMapLocation.getCity());
                }
                if (!TextUtils.isEmpty(aMapLocation.getDistrict()) && !"null".equalsIgnoreCase(aMapLocation.getDistrict())) {
                    address.setSubLocality(aMapLocation.getDistrict());
                }
                if (!TextUtils.isEmpty(aMapLocation.getRoad()) && !"null".equalsIgnoreCase(aMapLocation.getRoad())) {
                    address.setThoroughfare(aMapLocation.getRoad());
                }
                LocationRetrieveFromServerUtil.a(location, address, aMapLocation.getAddress());
                SHLocationManager.this.b(location);
            }

            public void a() {
                SHLocationManager.this.f16509a = false;
                SHLocationManager.this.p.removeMessages(2);
                ((LocationManager) SHLocationManager.this.m.getSystemService("location")).removeUpdates(SHLocationManager.this.n);
                SHLocationManager.this.e.post(new Runnable() {
                    public void run() {
                        SHLocationManager.this.a(SHLocationManager.this.c);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final Location location) {
        this.b = location;
        this.f16509a = false;
        this.p.removeMessages(2);
        ((LocationManager) this.m.getSystemService("location")).removeUpdates(this.n);
        this.e.post(new Runnable() {
            public void run() {
                SHLocationManager.this.a(SHLocationManager.this.c, location);
            }
        });
    }

    private String j() {
        if (NetworkUtils.b()) {
            return k();
        }
        if (Build.VERSION.SDK_INT < 18 || !NetworkUtils.c()) {
            return null;
        }
        return l();
    }

    private String k() {
        List<ScanResult> list;
        WifiManager wifiManager = (WifiManager) this.m.getSystemService("wifi");
        JSONObject jSONObject = new JSONObject();
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String bssid = connectionInfo.getBSSID();
        int rssi = connectionInfo.getRssi();
        String ssid = connectionInfo.getSSID();
        if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        String str = bssid + "," + rssi + "," + ssid;
        try {
            list = wifiManager.getScanResults();
        } catch (Exception unused) {
            list = null;
        }
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (ScanResult next : list) {
                if (!(next.BSSID == null || next.SSID == null)) {
                    jSONArray.put(next.BSSID + "," + next.level + "," + next.SSID);
                }
            }
        }
        try {
            jSONObject.put("accesstype", "1");
            jSONObject.put("imei", DeviceIdCompat.a(this.m));
            jSONObject.put("smac", SystemApi.a().j(CommonApplication.getAppContext()));
            jSONObject.put("mmac", str);
            if (jSONArray.length() > 0) {
                jSONObject.put("macs", jSONArray);
            }
        } catch (JSONException unused2) {
        }
        return jSONObject.toString();
    }

    @SuppressLint({"MissingPermission"})
    @TargetApi(18)
    private String l() {
        List<CellInfo> list;
        int i2;
        int i3;
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) this.m.getSystemService("phone");
        JSONObject jSONObject = new JSONObject();
        try {
            list = telephonyManager.getAllCellInfo();
        } catch (Exception unused) {
            list = null;
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        boolean z = false;
        CellInfo cellInfo = list.get(0);
        if (cellInfo instanceof CellInfoCdma) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
            CellSignalStrengthCdma cellSignalStrength = cellInfoCdma.getCellSignalStrength();
            int systemId = cellIdentity.getSystemId();
            int networkId = cellIdentity.getNetworkId();
            int basestationId = cellIdentity.getBasestationId();
            int longitude = cellIdentity.getLongitude();
            int latitude = cellIdentity.getLatitude();
            int dbm = cellSignalStrength.getDbm();
            if (longitude == Integer.MAX_VALUE || latitude == Integer.MAX_VALUE) {
                str = systemId + "," + networkId + "," + basestationId + ",,," + dbm;
            } else {
                str = systemId + "," + networkId + "," + basestationId + "," + longitude + "," + latitude + "," + dbm;
            }
            z = true;
            i3 = -1;
            i2 = -1;
        } else if (cellInfo instanceof CellInfoGsm) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrength2 = cellInfoGsm.getCellSignalStrength();
            i3 = cellIdentity2.getMnc();
            i2 = cellIdentity2.getMcc();
            str = i2 + "," + i3 + "," + cellIdentity2.getLac() + "," + cellIdentity2.getCid() + "," + cellSignalStrength2.getDbm();
        } else if (cellInfo instanceof CellInfoLte) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
            CellSignalStrengthLte cellSignalStrength3 = cellInfoLte.getCellSignalStrength();
            i3 = cellIdentity3.getMnc();
            i2 = cellIdentity3.getMcc();
            str = i2 + "," + i3 + "," + cellIdentity3.getTac() + "," + cellIdentity3.getCi() + "," + cellSignalStrength3.getDbm();
        } else if (!(cellInfo instanceof CellInfoWcdma)) {
            return null;
        } else {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
            CellSignalStrengthWcdma cellSignalStrength4 = cellInfoWcdma.getCellSignalStrength();
            i3 = cellIdentity4.getMnc();
            i2 = cellIdentity4.getMcc();
            str = i2 + "," + i3 + "," + cellIdentity4.getLac() + "," + cellIdentity4.getCid() + "," + cellSignalStrength4.getDbm();
        }
        if (i3 == -1 || i2 == -1) {
            return null;
        }
        if (TextUtils.isEmpty((CharSequence) null)) {
            new JSONArray();
        }
        try {
            jSONObject.put("accesstype", "0");
            jSONObject.put("imei", DeviceIdCompat.a(this.m));
            jSONObject.put("smac", SystemApi.a().j(CommonApplication.getAppContext()));
            jSONObject.put("cdma", z ? "1" : "0");
            jSONObject.put("bts", str);
            if (!TextUtils.isEmpty((CharSequence) null)) {
                jSONObject.put("nearbts", (Object) null);
            }
        } catch (JSONException unused2) {
        }
        return jSONObject.toString();
    }

    public void a(double d2, double d3, LocationCallback locationCallback) {
        if (locationCallback != null) {
            Locale I = CoreApi.a().I();
            if (I == null) {
                I = Locale.getDefault();
            }
            final Geocoder geocoder = new Geocoder(CommonApplication.getAppContext(), I);
            final double d4 = d2;
            final double d5 = d3;
            final LocationCallback locationCallback2 = locationCallback;
            this.p.post(new Runnable() {
                public void run() {
                    try {
                        List<Address> fromLocation = geocoder.getFromLocation(d4, d5, 1);
                        if (fromLocation == null || fromLocation.size() <= 0) {
                            SHLocationManager.this.b(d4, d5, locationCallback2);
                            return;
                        }
                        Address address = fromLocation.get(0);
                        Location location = new Location("");
                        location.setLatitude(d4);
                        location.setLongitude(d5);
                        Bundle bundle = new Bundle();
                        if (address != null) {
                            bundle.putParcelable("address", address);
                        }
                        location.setExtras(bundle);
                        locationCallback2.onSucceed("", location);
                    } catch (Exception unused) {
                        SHLocationManager.this.b(d4, d5, locationCallback2);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(double d2, double d3, LocationCallback locationCallback) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(CommonApplication.getAppContext());
        final LocationCallback locationCallback2 = locationCallback;
        final double d4 = d3;
        final double d5 = d2;
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            }

            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if (regeocodeResult == null) {
                    locationCallback2.onFailure("");
                    return;
                }
                Location location = new Location("");
                RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                if (regeocodeAddress == null) {
                    locationCallback2.onFailure("");
                    return;
                }
                location.setLongitude(d4);
                location.setLatitude(d5);
                Address address = new Address(Locale.getDefault());
                address.setLatitude(d5);
                address.setLongitude(d4);
                if (!TextUtils.isEmpty(regeocodeAddress.getProvince()) && !"null".equalsIgnoreCase(regeocodeAddress.getProvince())) {
                    address.setAdminArea(regeocodeAddress.getProvince());
                }
                if (!TextUtils.isEmpty(regeocodeAddress.getCity()) && !"null".equalsIgnoreCase(regeocodeAddress.getCity())) {
                    address.setLocality(regeocodeAddress.getCity());
                }
                if (!TextUtils.isEmpty(regeocodeAddress.getDistrict()) && !"null".equalsIgnoreCase(regeocodeAddress.getDistrict())) {
                    address.setSubLocality(regeocodeAddress.getDistrict());
                }
                List<RegeocodeRoad> roads = regeocodeAddress.getRoads();
                if (roads != null && roads.size() > 0) {
                    RegeocodeRoad regeocodeRoad = roads.get(0);
                    if (!TextUtils.isEmpty(regeocodeRoad.getName()) && !"null".equalsIgnoreCase(regeocodeRoad.getName())) {
                        address.setThoroughfare(regeocodeRoad.getName());
                    }
                }
                LocationRetrieveFromServerUtil.a(location, address, "");
                SHLocationManager.this.b(location);
                locationCallback2.onSucceed("", location);
            }
        });
        geocodeSearch.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(d2, d3), 1000.0f, GeocodeSearch.AMAP));
    }

    public static boolean e() {
        try {
            if (Build.VERSION.SDK_INT < 19) {
                return !TextUtils.isEmpty(Settings.Secure.getString(CommonApplication.getAppContext().getContentResolver(), "location_providers_allowed"));
            }
            try {
                if (Settings.Secure.getInt(CommonApplication.getAppContext().getContentResolver(), "location_mode") != 0) {
                    return true;
                }
                return false;
            } catch (Settings.SettingNotFoundException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static boolean f() {
        if (!SystemUtils.a(CommonApplication.getAppContext(), "android.permission.ACCESS_FINE_LOCATION") && !SystemUtils.a(CommonApplication.getAppContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
            return false;
        }
        return true;
    }

    public void g() {
        LocationManager locationManager;
        if (this.m != null && (locationManager = (LocationManager) this.m.getSystemService("location")) != null) {
            locationManager.removeUpdates(this.n);
            AMapLocationManager.a().b();
        }
    }
}
