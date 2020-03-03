package com.xiaomi.smarthome.framework.location;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
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
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import com.xiaomi.smarthome.shop.utils.NetworkUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.cybergarage.http.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationRetrieveFromServerUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16507a = "LocationRetrieveFromServerUtil";
    /* access modifiers changed from: private */
    public static Location b = null;
    /* access modifiers changed from: private */
    public static long c = 0;
    private static final int d = 5000;
    private static final int e = 0;
    private static final int f = 1;
    private static Set<String> g = new HashSet();
    private static Object h = null;
    private static int i = -1;

    public static void a(Context context, AsyncCallback<Location, Error> asyncCallback, String str) {
        if (CoreApi.a().D()) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), "Network locating is not supported"));
            }
        } else if (b == null) {
            b(context, asyncCallback, str);
        } else if (SystemClock.elapsedRealtime() - c > 5000) {
            b(context, asyncCallback, str);
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess(b);
        }
    }

    public static void b(Context context, AsyncCallback<Location, Error> asyncCallback, String str) {
        try {
            String a2 = a(context);
            if (!TextUtils.isEmpty(a2)) {
                a(context, asyncCallback, str, a2);
                return;
            }
            Log.d(f16507a, "cannot retrieve location parameters for location/get");
            if (asyncCallback == null) {
                return;
            }
            if (b != null) {
                asyncCallback.onSuccess(b);
            } else {
                asyncCallback.onFailure(new Error(0, "cannot retrieve location parameters for location"));
            }
        } catch (LocationParamNotChangedException unused) {
            Log.d(f16507a, "LocationParamNotChangedException: location param almost the same");
            if (asyncCallback != null) {
                asyncCallback.onSuccess(b);
            }
        }
    }

    public static void a(Context context, AsyncCallback<Location, Error> asyncCallback, final String str, String str2) {
        LocationSdkApi.a(context, str2, new JsonParser<Location>() {
            /* renamed from: a */
            public Location parse(JSONObject jSONObject) throws JSONException {
                Double valueOf = Double.valueOf(jSONObject.optDouble("longitude"));
                Double valueOf2 = Double.valueOf(jSONObject.optDouble("latitude"));
                if (valueOf.isNaN() || valueOf2.isNaN()) {
                    return null;
                }
                String optString = jSONObject.optString("address");
                Location unused = LocationRetrieveFromServerUtil.b = new Location(str);
                LocationRetrieveFromServerUtil.b.setLatitude(valueOf2.doubleValue());
                LocationRetrieveFromServerUtil.b.setLongitude(valueOf.doubleValue());
                LocationRetrieveFromServerUtil.a(LocationRetrieveFromServerUtil.b, (Address) null, optString);
                long unused2 = LocationRetrieveFromServerUtil.c = SystemClock.elapsedRealtime();
                return LocationRetrieveFromServerUtil.b;
            }
        }, asyncCallback);
        try {
            a(SHLocationManager.a().d);
        } catch (Throwable unused) {
        }
    }

    private static void a(List<SHLocationManager.LocationCallback> list) {
        if (list != null && list.size() != 0) {
            for (SHLocationManager.LocationCallback next : list) {
                if (next != null && !TextUtils.isEmpty(next.object)) {
                    int i2 = 200;
                    if (next.object.length() <= 200) {
                        i2 = next.object.length();
                    }
                    if (next.object.contains("XmPluginHostApiImpl")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_plugin", a(next.object), (String) null, false);
                    } else if (next.object.contains("PluginService")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_PluginService", (String) null, (String) null, false);
                    } else if (next.object.contains("addGPSInfo")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_addGPSInfo", (String) null, (String) null, false);
                    } else if (next.object.contains("onStart")) {
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get_from_onStart", (String) null, (String) null, false);
                    } else {
                        String substring = next.object.substring(0, i2);
                        if (!TextUtils.isEmpty(substring)) {
                            substring = substring.replace(HTTP.TAB, "");
                        }
                        CoreApi.a().a(StatType.EVENT, "stacktrace_location_get", substring, (String) null, false);
                    }
                }
            }
        }
    }

    private static String a(String str) {
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

    public static void a(Location location, Address address, String str) {
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

    private static String a(Context context) throws LocationParamNotChangedException {
        if (NetworkUtils.b()) {
            return b(context);
        }
        if (Build.VERSION.SDK_INT < 18 || !NetworkUtils.c()) {
            return null;
        }
        return c(context);
    }

    private static String b(Context context) throws LocationParamNotChangedException {
        int size;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String bssid = connectionInfo.getBSSID();
        int rssi = connectionInfo.getRssi();
        String ssid = connectionInfo.getSSID();
        if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        String str = bssid + "," + rssi + "," + ssid;
        List<ScanResult> scanResults = wifiManager.getScanResults();
        if (scanResults == null) {
            scanResults = new ArrayList<>();
        }
        ArrayList<String> arrayList = new ArrayList<>(scanResults.size());
        JSONArray jSONArray = new JSONArray();
        for (ScanResult scanResult : scanResults) {
            jSONArray.put(scanResult.BSSID + "," + scanResult.level + "," + scanResult.SSID);
            arrayList.add(scanResult.BSSID);
        }
        if (i == 0 && (size = g.size()) > 0) {
            int i2 = 0;
            for (String str2 : arrayList) {
                if (!TextUtils.isEmpty(str2) && g.contains(str2)) {
                    i2++;
                }
            }
            if ((i2 * 100) / size > 50) {
                throw new LocationParamNotChangedException();
            }
        }
        g.clear();
        g.addAll(arrayList);
        i = 0;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("accesstype", "1");
            jSONObject.put("imei", DeviceIdCompat.a(context));
            jSONObject.put("smac", SystemApi.a().j(CommonApplication.getAppContext()));
            jSONObject.put("mmac", str);
            if (jSONArray.length() > 0) {
                jSONObject.put("macs", jSONArray);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    @SuppressLint({"MissingPermission"})
    @TargetApi(18)
    private static String c(Context context) throws LocationParamNotChangedException {
        List<CellInfo> list;
        int i2;
        int i3;
        String str;
        try {
            list = ((TelephonyManager) context.getSystemService("phone")).getAllCellInfo();
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
            if (i == 1 && h != null && (h instanceof CellInfoCdma)) {
                cellIdentity = ((CellInfoCdma) h).getCellIdentity();
                if (cellIdentity.getSystemId() == systemId && cellIdentity.getNetworkId() == networkId && cellIdentity.getBasestationId() == basestationId) {
                    throw new LocationParamNotChangedException();
                }
            }
            h = cellIdentity;
            i = 1;
            z = true;
            i3 = -1;
            i2 = -1;
        } else if (cellInfo instanceof CellInfoGsm) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrength2 = cellInfoGsm.getCellSignalStrength();
            i3 = cellIdentity2.getMnc();
            i2 = cellIdentity2.getMcc();
            int lac = cellIdentity2.getLac();
            int cid = cellIdentity2.getCid();
            str = i2 + "," + i3 + "," + lac + "," + cid + "," + cellSignalStrength2.getDbm();
            if (i == 1 && h != null && (h instanceof CellInfoGsm)) {
                cellIdentity2 = ((CellInfoGsm) h).getCellIdentity();
                if (cellIdentity2.getMnc() == i3 && cellIdentity2.getMcc() == i2 && cellIdentity2.getCid() == cid && cellIdentity2.getLac() == lac) {
                    throw new LocationParamNotChangedException();
                }
            }
            h = cellIdentity2;
            i = 1;
        } else if (cellInfo instanceof CellInfoLte) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
            CellSignalStrengthLte cellSignalStrength3 = cellInfoLte.getCellSignalStrength();
            i3 = cellIdentity3.getMnc();
            i2 = cellIdentity3.getMcc();
            int tac = cellIdentity3.getTac();
            int ci = cellIdentity3.getCi();
            str = i2 + "," + i3 + "," + tac + "," + ci + "," + cellSignalStrength3.getDbm();
            if (i == 1 && h != null && (h instanceof CellInfoLte)) {
                cellIdentity3 = ((CellInfoLte) h).getCellIdentity();
                if (cellIdentity3.getMnc() == i3 && cellIdentity3.getMcc() == i2 && cellIdentity3.getCi() == ci && cellIdentity3.getTac() == tac) {
                    throw new LocationParamNotChangedException();
                }
            }
            h = cellIdentity3;
            i = 1;
        } else if (!(cellInfo instanceof CellInfoWcdma)) {
            return null;
        } else {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
            CellSignalStrengthWcdma cellSignalStrength4 = cellInfoWcdma.getCellSignalStrength();
            i3 = cellIdentity4.getMnc();
            i2 = cellIdentity4.getMcc();
            int lac2 = cellIdentity4.getLac();
            int cid2 = cellIdentity4.getCid();
            str = i2 + "," + i3 + "," + lac2 + "," + cid2 + "," + cellSignalStrength4.getDbm();
            if (i == 1 && h != null && (h instanceof CellInfoWcdma)) {
                cellIdentity4 = ((CellInfoWcdma) h).getCellIdentity();
                if (cellIdentity4.getMnc() == i3 && cellIdentity4.getMcc() == i2 && cellIdentity4.getLac() == lac2 && cellIdentity4.getCid() == cid2) {
                    throw new LocationParamNotChangedException();
                }
            }
            h = cellIdentity4;
            i = 1;
        }
        if (i3 == -1 || i2 == -1) {
            return null;
        }
        if (TextUtils.isEmpty((CharSequence) null)) {
            new JSONArray();
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("accesstype", "0");
            jSONObject.put("imei", DeviceIdCompat.a(context));
            jSONObject.put("smac", SystemApi.a().j(CommonApplication.getAppContext()));
            jSONObject.put("cdma", z ? "1" : "0");
            jSONObject.put("bts", str);
            if (!TextUtils.isEmpty((CharSequence) null)) {
                jSONObject.put("nearbts", (Object) null);
            }
            i = 1;
            return jSONObject.toString();
        } catch (JSONException unused2) {
            return null;
        }
    }

    private static class LocationParamNotChangedException extends Exception {
        private LocationParamNotChangedException() {
        }
    }
}
