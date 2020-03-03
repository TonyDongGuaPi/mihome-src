package com.taobao.weex.bridge;

import com.taobao.weex.base.CalledByNative;
import com.taobao.weex.common.WXConfig;
import com.taobao.weex.utils.WXLogUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXParams implements Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String appName;
    private String appVersion;
    private String cacheDir;
    private String crashFilePath;
    private String deviceHeight;
    private String deviceModel;
    private String deviceWidth;
    private String layoutDirection;
    private String libIcuPath;
    private String libJscPath;
    private String libJssPath;
    private String libLdPath;
    private String logLevel;
    private String needInitV8;
    private Map<String, String> options;
    private String osVersion;
    private String platform;
    private String shouldInfoCollect;
    private String useSingleProcess;
    private String weexVersion;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1203754396445924344L, "com/taobao/weex/bridge/WXParams", 66);
        $jacocoData = a2;
        return a2;
    }

    public WXParams() {
        $jacocoInit()[0] = true;
    }

    @CalledByNative
    public Object getOptions() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = this.options;
        $jacocoInit[1] = true;
        return map;
    }

    public void setOptions(Map<String, String> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.options = map;
        $jacocoInit[2] = true;
    }

    public String getShouldInfoCollect() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.shouldInfoCollect;
        $jacocoInit[3] = true;
        return str;
    }

    public void setShouldInfoCollect(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.shouldInfoCollect = str;
        $jacocoInit[4] = true;
    }

    @CalledByNative
    public String getPlatform() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.platform;
        $jacocoInit[5] = true;
        return str;
    }

    public void setPlatform(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.platform = str;
        $jacocoInit[6] = true;
    }

    public void setCacheDir(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.cacheDir = str;
        $jacocoInit[7] = true;
    }

    @CalledByNative
    public String getCacheDir() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.cacheDir;
        $jacocoInit[8] = true;
        return str;
    }

    @CalledByNative
    public String getOsVersion() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.osVersion;
        $jacocoInit[9] = true;
        return str;
    }

    public void setOsVersion(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.osVersion = str;
        $jacocoInit[10] = true;
    }

    @CalledByNative
    public String getAppVersion() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.appVersion;
        $jacocoInit[11] = true;
        return str;
    }

    public void setAppVersion(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.appVersion = str;
        $jacocoInit[12] = true;
    }

    @CalledByNative
    public String getWeexVersion() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.weexVersion;
        $jacocoInit[13] = true;
        return str;
    }

    public void setWeexVersion(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.weexVersion = str;
        $jacocoInit[14] = true;
    }

    @CalledByNative
    public String getDeviceModel() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.deviceModel;
        $jacocoInit[15] = true;
        return str;
    }

    public void setDeviceModel(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.deviceModel = str;
        $jacocoInit[16] = true;
    }

    @CalledByNative
    public String getLayoutDirection() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.layoutDirection;
        $jacocoInit[17] = true;
        return str;
    }

    public void setLayoutDirection(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.layoutDirection = str;
        $jacocoInit[18] = true;
    }

    @CalledByNative
    public String getAppName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.appName;
        $jacocoInit[19] = true;
        return str;
    }

    public void setAppName(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.appName = str;
        $jacocoInit[20] = true;
    }

    @CalledByNative
    public String getDeviceWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.deviceWidth;
        $jacocoInit[21] = true;
        return str;
    }

    @Deprecated
    public void setDeviceWidth(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.deviceWidth = str;
        $jacocoInit[22] = true;
    }

    @CalledByNative
    public String getDeviceHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.deviceHeight;
        $jacocoInit[23] = true;
        return str;
    }

    public void setDeviceHeight(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.deviceHeight = str;
        $jacocoInit[24] = true;
    }

    public String getLogLevel() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.logLevel == null) {
            $jacocoInit[25] = true;
            return "";
        }
        String str = this.logLevel;
        $jacocoInit[26] = true;
        return str;
    }

    @CalledByNative
    public String getUseSingleProcess() {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("getUseSingleProcess is running " + this.useSingleProcess);
        String str = this.useSingleProcess;
        $jacocoInit[27] = true;
        return str;
    }

    public void setUseSingleProcess(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.useSingleProcess = str;
        $jacocoInit[28] = true;
    }

    public void setLogLevel(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.logLevel = str;
        $jacocoInit[29] = true;
    }

    public String getNeedInitV8() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.needInitV8 == null) {
            $jacocoInit[30] = true;
            return "";
        }
        String str = this.needInitV8;
        $jacocoInit[31] = true;
        return str;
    }

    public void setNeedInitV8(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            this.needInitV8 = "1";
            $jacocoInit[32] = true;
        } else {
            this.needInitV8 = "0";
            $jacocoInit[33] = true;
        }
        $jacocoInit[34] = true;
    }

    public void setCrashFilePath(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("WXParams", "setCrashFilePath: " + str);
        this.crashFilePath = str;
        $jacocoInit[35] = true;
    }

    @CalledByNative
    public String getCrashFilePath() {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("WXParams", "getCrashFilePath:" + this.crashFilePath);
        String str = this.crashFilePath;
        $jacocoInit[36] = true;
        return str;
    }

    @CalledByNative
    public String getLibJssPath() {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("getLibJssPath is running " + this.libJssPath);
        String str = this.libJssPath;
        $jacocoInit[37] = true;
        return str;
    }

    @CalledByNative
    public String getLibJscPath() {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("getLibJscPath is running " + this.libJscPath);
        String str = this.libJscPath;
        $jacocoInit[38] = true;
        return str;
    }

    public void setLibJscPath(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.libJscPath = str;
        $jacocoInit[39] = true;
    }

    public void setLibJssPath(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.libJssPath = str;
        $jacocoInit[40] = true;
    }

    @CalledByNative
    public String getLibIcuPath() {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("getLibIcuPath is running " + this.libIcuPath);
        String str = this.libIcuPath;
        $jacocoInit[41] = true;
        return str;
    }

    public void setLibIcuPath(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.libIcuPath = str;
        $jacocoInit[42] = true;
    }

    @CalledByNative
    public String getLibLdPath() {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("getLibLdPath is running " + this.libLdPath);
        String str = this.libLdPath;
        $jacocoInit[43] = true;
        return str;
    }

    public void setLibLdPath(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.libLdPath = str;
        $jacocoInit[44] = true;
    }

    public Map<String, Object> toMap() {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[45] = true;
        hashMap.put("appName", this.appName);
        $jacocoInit[46] = true;
        hashMap.put("appVersion", this.appVersion);
        $jacocoInit[47] = true;
        hashMap.put(WXConfig.cacheDir, this.cacheDir);
        $jacocoInit[48] = true;
        hashMap.put("deviceHeight", this.deviceHeight);
        $jacocoInit[49] = true;
        hashMap.put("deviceModel", this.deviceModel);
        $jacocoInit[50] = true;
        hashMap.put("deviceWidth", this.deviceWidth);
        $jacocoInit[51] = true;
        hashMap.put(WXConfig.layoutDirection, this.layoutDirection);
        $jacocoInit[52] = true;
        hashMap.put("libJssPath", this.libJssPath);
        $jacocoInit[53] = true;
        hashMap.put(WXConfig.logLevel, this.logLevel);
        $jacocoInit[54] = true;
        hashMap.put("needInitV8", this.needInitV8);
        $jacocoInit[55] = true;
        hashMap.put("osVersion", this.osVersion);
        $jacocoInit[56] = true;
        hashMap.put("platform", this.platform);
        $jacocoInit[57] = true;
        hashMap.put("useSingleProcess", this.useSingleProcess);
        $jacocoInit[58] = true;
        hashMap.put("shouldInfoCollect", this.shouldInfoCollect);
        $jacocoInit[59] = true;
        hashMap.put(WXConfig.weexVersion, this.weexVersion);
        $jacocoInit[60] = true;
        hashMap.put("crashFilePath", this.crashFilePath);
        $jacocoInit[61] = true;
        hashMap.put("libJscPath", this.libJscPath);
        $jacocoInit[62] = true;
        hashMap.put("libIcuPath", this.libIcuPath);
        $jacocoInit[63] = true;
        hashMap.put("libLdPath", this.libLdPath);
        $jacocoInit[64] = true;
        hashMap.put("options", this.options);
        $jacocoInit[65] = true;
        return hashMap;
    }
}
