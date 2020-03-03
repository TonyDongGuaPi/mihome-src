package com.xiaomi.smarthome.newui.amappoi;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class ALocationClientFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20428a = "Default";

    public static AMapLocationClientOption a() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setInterval(1000);
        return aMapLocationClientOption;
    }

    public static AMapLocationClientOption b() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setOnceLocationLatest(true);
        return aMapLocationClientOption;
    }

    public static AMapLocationClient a(Context context, AMapLocationClientOption aMapLocationClientOption, AMapLocationListener aMapLocationListener) {
        AMapLocationClient aMapLocationClient = new AMapLocationClient(context);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.setLocationListener(aMapLocationListener);
        return aMapLocationClient;
    }

    public static AMapLocationClient a(Context context) {
        return a(context, a(), new AMapLocationListener() {
            public void onLocationChanged(AMapLocation aMapLocation) {
            }
        });
    }
}
