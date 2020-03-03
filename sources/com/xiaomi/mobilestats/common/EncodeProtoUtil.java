package com.xiaomi.mobilestats.common;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.mobilestats.data.BasicStoreTools;
import com.xiaomi.mobilestats.data.DataCore;
import com.xiaomi.mobilestats.data.ProtoMsg;
import com.xiaomi.mobilestats.object.GSMCell;
import com.xiaomi.mobilestats.object.LatitudeAndLongitude;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EncodeProtoUtil {
    public static ProtoMsg.StatsMsg.ClientMsg getClientDataInfo(Context context, boolean z, String str, HashMap hashMap, String str2) {
        String str3;
        String str4;
        String cRKey = BasicStoreTools.getInstance().getCRKey(context);
        try {
            ProtoMsg.StatsMsg.ClientMsg.Builder newBuilder = ProtoMsg.StatsMsg.ClientMsg.newBuilder();
            ProtoMsg.StatsMsg.Client.Builder newBuilder2 = ProtoMsg.StatsMsg.Client.newBuilder();
            newBuilder2.setType(OpenSdkPlayStatisticUpload.r).setAppKey(DataCore.getAppkey(context) + "").setAppChannel(DataCore.getAppChannel(context) + "").setSessionId(DataCore.getVMIMEI(context, true) + "").setTime(System.currentTimeMillis()).setDeviceId(DeviceUtil.getInstance().getUniqueId(context) + "").setUserId(TextUtils.isEmpty(str) ? "" : str).setVersionCode(DataCore.getAppVersionCode(context)).setVersionName(DataCore.getAppVersionName(context) + "").setSdkVerson(DataCore.getSDKVersion() + "").setOsVersion(DataCore.getOSSysVersion() + "").setPlatform("android").setLanguage(Locale.getDefault().getLanguage()).setResolution(CommonUtil.getResolution(context)).setDeviceName(CommonUtil.getDeviceName()).setMacAddress(CommonUtil.getMacAddress(context)).setHasBluetooth(CommonUtil.isHasBluetooth()).setHasWifi(CommonUtil.isWiFiActive(context)).setHasGravity(CommonUtil.isHaveGravity(context)).setPhoneType(CommonUtil.getPhoneType(context)).setNetwork(CommonUtil.getNetworkType2G3GWifi(context)).setNetworkDetail(CommonUtil.getNetworkTypeDetail(context) + "").setMiuiVersion(CommonUtil.getMiuiVerson(context) + "").setKey(TextUtils.isEmpty(str2) ? "" : AESUtils.encrpt(str2.getBytes(), cRKey)).setPackageName(DataCore.getPackageName(context)).setImei(DataCore.getDeviceId(context)).setSimOperator(CommonUtil.getSimOperator(context)).setLogVersionApp(HostManager.LOG_VERSION);
            BasicStoreTools.getInstance().setUserId(context, str);
            if (z) {
                try {
                    GSMCell cellInfo = CommonUtil.getCellInfo(context);
                    newBuilder2.setMccMnc(cellInfo != null ? cellInfo.MCCMNC : "");
                    if (cellInfo != null) {
                        str3 = cellInfo.CID + "";
                    } else {
                        str3 = "";
                    }
                    newBuilder2.setCellId(str3);
                    if (cellInfo != null) {
                        str4 = cellInfo.LAC + "";
                    } else {
                        str4 = "";
                    }
                    newBuilder2.setLac(str4);
                    LatitudeAndLongitude latitudeAndLongitude = CommonUtil.getLatitudeAndLongitude(context);
                    newBuilder2.setHasGps(CommonUtil.isHasGps(context));
                    newBuilder2.setLatitude(latitudeAndLongitude.latitude);
                    newBuilder2.setLongitude(latitudeAndLongitude.longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            HashMap initMap = StatService.getInstance().getInitMap();
            if (initMap != null && !initMap.isEmpty()) {
                for (Map.Entry entry : initMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addClientMap(newBuilder3);
                }
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder4 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder4.setKey((String) entry2.getKey());
                    newBuilder4.setValue((String) entry2.getValue());
                    newBuilder2.addClientMap(newBuilder4);
                }
            }
            newBuilder.addClient(newBuilder2);
            return newBuilder.build();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.CrashMsg getCrashProtoInfo(Context context, String str) {
        try {
            ProtoMsg.StatsMsg.CrashMsg.Builder newBuilder = ProtoMsg.StatsMsg.CrashMsg.newBuilder();
            ProtoMsg.StatsMsg.Crash.Builder newBuilder2 = ProtoMsg.StatsMsg.Crash.newBuilder();
            ProtoMsg.StatsMsg.Crash.Builder appChannel = newBuilder2.setType("crash").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context));
            appChannel.setSessionId(DataCore.getVMIMEI(context) + "").setTime(System.currentTimeMillis()).setDeviceId(DeviceUtil.getInstance().getUniqueId(context)).setVersionCode(DataCore.getAppVersionCode(context)).setVersionName(DataCore.getAppVersionName(context)).setThrowable(str).setUserId(DataCore.getUserId(context)).setDeviceName(CommonUtil.getDeviceName()).setPlatform("android").setMemoryClass(CommonUtil.getMemoryClass(context)).setLargeMemoryClass(CommonUtil.getLargeMemoryClass(context)).setCrashMd5(StrUtil.md5(str)).setCrashNum(1).setPackageName(DataCore.getPackageName(context)).setLogVersionApp(HostManager.LOG_VERSION);
            HashMap initMap = StatService.getInstance().getInitMap();
            if (initMap != null && !initMap.isEmpty()) {
                for (Map.Entry entry : initMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addCrashMap(newBuilder3);
                }
            }
            newBuilder.addCrash(newBuilder2);
            return newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.EventMsg getEventProtoInfo(Context context, String str, String str2, HashMap hashMap) {
        try {
            ProtoMsg.StatsMsg.EventMsg.Builder newBuilder = ProtoMsg.StatsMsg.EventMsg.newBuilder();
            ProtoMsg.StatsMsg.Event.Builder newBuilder2 = ProtoMsg.StatsMsg.Event.newBuilder();
            ProtoMsg.StatsMsg.Event.Builder type = newBuilder2.setType("event");
            ProtoMsg.StatsMsg.Event.Builder userId = type.setUserId(DataCore.getUserId(context) + "");
            ProtoMsg.StatsMsg.Event.Builder time = userId.setSessionId(DataCore.getVMIMEI(context) + "").setTime(System.currentTimeMillis());
            ProtoMsg.StatsMsg.Event.Builder deviceId = time.setDeviceId(DeviceUtil.getInstance().getUniqueId(context) + "");
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            ProtoMsg.StatsMsg.Event.Builder eventId = deviceId.setEventId(str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            eventId.setLabel(str2).setPlatform("android").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context)).setPackageName(DataCore.getPackageName(context)).setLogVersionApp(HostManager.LOG_VERSION);
            HashMap initMap = StatService.getInstance().getInitMap();
            if (initMap != null && !initMap.isEmpty()) {
                for (Map.Entry entry : initMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addEventMap(newBuilder3);
                }
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder4 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder4.setKey((String) entry2.getKey());
                    newBuilder4.setValue((String) entry2.getValue());
                    newBuilder2.addEventMap(newBuilder4);
                }
            }
            newBuilder.addEvent(newBuilder2);
            return newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.ErrorMsg getExtensibleErrorProtoInfo(Context context, Exception exc, HashMap hashMap) {
        String str;
        try {
            ProtoMsg.StatsMsg.ErrorMsg.Builder newBuilder = ProtoMsg.StatsMsg.ErrorMsg.newBuilder();
            ProtoMsg.StatsMsg.ProtoError.Builder newBuilder2 = ProtoMsg.StatsMsg.ProtoError.newBuilder();
            ProtoMsg.StatsMsg.ProtoError.Builder appChannel = newBuilder2.setType("error").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context));
            appChannel.setSessionId(DataCore.getVMIMEI(context) + "").setTime(System.currentTimeMillis()).setDeviceId(DeviceUtil.getInstance().getUniqueId(context)).setVersionCode(DataCore.getAppVersionCode(context)).setVersionName(DataCore.getAppVersionName(context)).setUserId(DataCore.getUserId(context)).setDeviceName(CommonUtil.getDeviceName()).setPlatform("android").setLogVersionApp(HostManager.LOG_VERSION);
            if (exc != null) {
                Throwable cause = exc.getCause();
                if (cause != null) {
                    str = cause.toString();
                    if (str.contains(":")) {
                        str = str.split(":")[0];
                    }
                } else {
                    str = "UnKown";
                }
                newBuilder2.setException(str);
                newBuilder2.setExceptionDetail(exc.toString());
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addErrorMap(newBuilder3);
                }
            }
            newBuilder.addError(newBuilder2);
            return newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.ErrorMsg getNetErrorProtoInfo(Context context, String str, Exception exc, HashMap hashMap) {
        String str2;
        try {
            ProtoMsg.StatsMsg.ErrorMsg.Builder newBuilder = ProtoMsg.StatsMsg.ErrorMsg.newBuilder();
            ProtoMsg.StatsMsg.ProtoError.Builder newBuilder2 = ProtoMsg.StatsMsg.ProtoError.newBuilder();
            ProtoMsg.StatsMsg.ProtoError.Builder appChannel = newBuilder2.setType("error").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context));
            ProtoMsg.StatsMsg.ProtoError.Builder networkType = appChannel.setSessionId(DataCore.getVMIMEI(context) + "").setTime(System.currentTimeMillis()).setDeviceId(DeviceUtil.getInstance().getUniqueId(context)).setNetwork(CommonUtil.getNetworkTypeWIFIOther(context)).setNetworkType(CommonUtil.getNetworkType(context));
            if (StrUtil.isEmpty(str)) {
                str = "";
            }
            networkType.setUrl(str).setVersionCode(DataCore.getAppVersionCode(context)).setVersionName(DataCore.getAppVersionName(context)).setUserId(DataCore.getUserId(context)).setDeviceName(CommonUtil.getDeviceName()).setPlatform("android").setLogVersionApp(HostManager.LOG_VERSION);
            if (context != null) {
                String name = context.getClass().getName();
                if (!StrUtil.isEmpty(name)) {
                    newBuilder2.setActivity(name);
                }
            }
            if (exc != null) {
                Throwable cause = exc.getCause();
                if (cause != null) {
                    str2 = cause.toString();
                    if (str2.contains(":")) {
                        str2 = str2.split(":")[0];
                    }
                } else {
                    str2 = "UnKown";
                }
                newBuilder2.setException(str2);
                newBuilder2.setExceptionDetail(exc.toString());
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addErrorMap(newBuilder3);
                }
            }
            newBuilder.addError(newBuilder2);
            return newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.ErrorMsg getNetErrorProtoInfo(Context context, String str, Exception exc, HashMap hashMap, String str2) {
        String str3;
        try {
            ProtoMsg.StatsMsg.ErrorMsg.Builder newBuilder = ProtoMsg.StatsMsg.ErrorMsg.newBuilder();
            ProtoMsg.StatsMsg.ProtoError.Builder newBuilder2 = ProtoMsg.StatsMsg.ProtoError.newBuilder();
            ProtoMsg.StatsMsg.ProtoError.Builder appChannel = newBuilder2.setType("error").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context));
            ProtoMsg.StatsMsg.ProtoError.Builder networkType = appChannel.setSessionId(DataCore.getVMIMEI(context) + "").setTime(System.currentTimeMillis()).setDeviceId(DeviceUtil.getInstance().getUniqueId(context)).setNetwork(CommonUtil.getNetworkTypeWIFIOther(context)).setNetworkType(CommonUtil.getNetworkType(context));
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            networkType.setUrl(str).setVersionCode(DataCore.getAppVersionCode(context)).setVersionName(DataCore.getAppVersionName(context)).setUserId(DataCore.getUserId(context)).setDeviceName(CommonUtil.getDeviceName()).setPlatform("android").setPackageName(DataCore.getPackageName(context)).setLogVersionApp(HostManager.LOG_VERSION);
            if (!TextUtils.isEmpty(str2)) {
                newBuilder2.setActivity(str2);
            }
            if (exc != null) {
                Throwable cause = exc.getCause();
                if (cause != null) {
                    str3 = cause.toString();
                    if (str3.contains(":")) {
                        str3 = str3.split(":")[0];
                    }
                } else {
                    str3 = "UnKown";
                }
                newBuilder2.setException(str3);
                newBuilder2.setExceptionDetail(exc.toString());
            }
            HashMap initMap = StatService.getInstance().getInitMap();
            if (initMap != null && !initMap.isEmpty()) {
                for (Map.Entry entry : initMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addErrorMap(newBuilder3);
                }
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder4 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder4.setKey((String) entry2.getKey());
                    newBuilder4.setValue((String) entry2.getValue());
                    newBuilder2.addErrorMap(newBuilder4);
                }
            }
            newBuilder.addError(newBuilder2);
            return newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.ViewMsg getViewProtoInfo(Context context, String str, String str2, HashMap hashMap) {
        try {
            ProtoMsg.StatsMsg.ViewMsg.Builder newBuilder = ProtoMsg.StatsMsg.ViewMsg.newBuilder();
            ProtoMsg.StatsMsg.View.Builder newBuilder2 = ProtoMsg.StatsMsg.View.newBuilder();
            ProtoMsg.StatsMsg.View.Builder type = newBuilder2.setType("view");
            ProtoMsg.StatsMsg.View.Builder userId = type.setUserId(DataCore.getUserId(context) + "");
            ProtoMsg.StatsMsg.View.Builder time = userId.setSessionId(DataCore.getVMIMEI(context) + "").setTime(System.currentTimeMillis());
            ProtoMsg.StatsMsg.View.Builder deviceId = time.setDeviceId(DeviceUtil.getInstance().getUniqueId(context) + "");
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            ProtoMsg.StatsMsg.View.Builder viewId = deviceId.setViewId(str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            viewId.setLabel(str2).setPlatform("android").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context)).setPackageName(DataCore.getPackageName(context)).setLogVersionApp(HostManager.LOG_VERSION);
            HashMap initMap = StatService.getInstance().getInitMap();
            if (initMap != null && !initMap.isEmpty()) {
                for (Map.Entry entry : initMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder3 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder3.setKey((String) entry.getKey());
                    newBuilder3.setValue((String) entry.getValue());
                    newBuilder2.addViewMap(newBuilder3);
                }
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder4 = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                    newBuilder4.setKey((String) entry2.getKey());
                    newBuilder4.setValue((String) entry2.getValue());
                    newBuilder2.addViewMap(newBuilder4);
                }
            }
            newBuilder.addView(newBuilder2);
            return newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProtoMsg.StatsMsg.Crash updateCrash(ProtoMsg.StatsMsg.Crash crash, int i) {
        ProtoMsg.StatsMsg.Crash crash2;
        if (crash == null) {
            return crash;
        }
        try {
            ProtoMsg.StatsMsg.Crash.Builder newBuilder = ProtoMsg.StatsMsg.Crash.newBuilder();
            newBuilder.setType("crash").setAppKey(crash.getAppKey()).setAppChannel(crash.getAppChannel()).setSessionId(crash.getSessionId()).setTime(crash.getTime()).setDeviceId(crash.getDeviceId()).setVersionCode(crash.getVersionCode()).setVersionName(crash.getVersionName()).setThrowable(crash.getThrowable()).setUserId(crash.getUserId()).setDeviceName(crash.getDeviceName()).setPlatform("android").setMemoryClass(crash.getMemoryClass()).setLargeMemoryClass(crash.getLargeMemoryClass()).setCrashMd5(crash.getCrashMd5()).setCrashNum(i).setPackageName(crash.getPackageName()).setLogVersionApp(HostManager.LOG_VERSION);
            List<ProtoMsg.StatsMsg.ProtoMap> crashMapList = crash.getCrashMapList();
            if (crashMapList != null && crashMapList.size() > 0) {
                for (ProtoMsg.StatsMsg.ProtoMap addCrashMap : crashMapList) {
                    newBuilder.addCrashMap(addCrashMap);
                }
            }
            crash2 = newBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            crash2 = null;
        }
        return crash2 == null ? crash : crash2;
    }
}
