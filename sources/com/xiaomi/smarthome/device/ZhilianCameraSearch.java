package com.xiaomi.smarthome.device;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ZhilianCameraSearch extends DeviceSearch<Device> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15010a = "refresh_xiaofang_dialog";
    private static ZhilianCameraSearch c;
    private ScanResult b;
    private List<Device> d = new ArrayList();

    public void a(Collection<? extends Device> collection, DeviceSearch.SearchDeviceListener searchDeviceListener) {
    }

    private ZhilianCameraSearch() {
    }

    public static ZhilianCameraSearch a() {
        if (c == null) {
            synchronized (ZhilianCameraSearch.class) {
                if (c == null) {
                    c = new ZhilianCameraSearch();
                }
            }
        }
        return c;
    }

    private boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.contains("isa-camera-isc5");
    }

    private boolean b(String str) {
        return a(str);
    }

    private Context k() {
        return CommonApplication.getAppContext();
    }

    private ScanResult c(String str) {
        for (ScanResult next : ((WifiManager) k().getSystemService("wifi")).getScanResults()) {
            if (WifiSettingUtils.a(next.SSID, str)) {
                return next;
            }
        }
        return null;
    }

    public void i() {
        String c2 = WifiUtil.c(k());
        if (b(c2)) {
            a(c(c2));
            l();
        }
    }

    private void a(ScanResult scanResult) {
        this.b = scanResult;
    }

    public ScanResult j() {
        return this.b;
    }

    private void l() {
        SmartHomeDeviceManager.a().c();
        LocalBroadcastManager.getInstance(k()).sendBroadcast(new Intent("refresh_xiaofang_dialog"));
    }

    public void a(Intent intent) {
        if (intent != null && intent.hasExtra(StatType.NETWORKINFO)) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(StatType.NETWORKINFO);
            NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
            WifiInfo connectionInfo = ((WifiManager) k().getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getSSID()) || connectionInfo.getSSID().contains("<unknown ssid>")) {
                a((ScanResult) null);
                l();
                return;
            }
            if (detailedState != NetworkInfo.DetailedState.CONNECTED || !networkInfo.isConnected()) {
                a((ScanResult) null);
            } else if (b(connectionInfo.getSSID())) {
                a(c(connectionInfo.getSSID()));
            } else {
                a((ScanResult) null);
            }
            l();
        }
    }

    public void c() {
        this.h = true;
    }

    public List<Device> d() {
        this.d.clear();
        if (this.b != null) {
            ScanResult scanResult = this.b;
            ZhilianCameraDevice zhilianCameraDevice = new ZhilianCameraDevice(scanResult);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(DeviceTagInterface.e, scanResult.SSID);
                jSONObject.put("bssid", scanResult.BSSID);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            zhilianCameraDevice.extra = jSONArray.toString();
            this.d.add(zhilianCameraDevice);
        }
        return this.d;
    }

    public void e() {
        this.d.clear();
    }
}
