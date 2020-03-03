package com.taobao.weex.bridge;

import android.content.Context;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.base.CalledByNative;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.IWXBridge;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXWsonJSONSwitch;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXBridge implements IWXBridge {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final boolean MULTIPROCESS = true;
    public static final String TAG = "WXBridge";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5171088176741959520L, "com/taobao/weex/bridge/WXBridge", 242);
        $jacocoData = a2;
        return a2;
    }

    private native void nativeBindMeasurementToRenderObject(long j);

    private native int nativeExecJS(String str, String str2, String str3, WXJSObject[] wXJSObjectArr);

    private native int nativeExecJSService(String str);

    private native void nativeForceLayout(String str);

    private native int nativeInitFramework(String str, WXParams wXParams);

    private native int nativeInitFrameworkEnv(String str, WXParams wXParams, String str2, boolean z);

    private native void nativeMarkDirty(String str, String str2, boolean z);

    private native boolean nativeNotifyLayout(String str);

    private native void nativeOnInstanceClose(String str);

    private native void nativeRefreshInstance(String str, String str2, String str3, WXJSObject[] wXJSObjectArr);

    private native void nativeRegisterCoreEnv(String str, String str2);

    private native void nativeResetWXBridge(Object obj, String str);

    private native void nativeSetDefaultHeightAndWidthIntoRootDom(String str, float f, float f2, boolean z, boolean z2);

    private native void nativeSetMargin(String str, String str2, int i, float f);

    private native void nativeSetPadding(String str, String str2, int i, float f);

    private native void nativeSetPosition(String str, String str2, int i, float f);

    private native void nativeSetRenderContainerWrapContent(boolean z, String str);

    private native void nativeSetStyleHeight(String str, String str2, float f);

    private native void nativeSetStyleWidth(String str, String str2, float f);

    private native void nativeTakeHeapSnapshot(String str);

    public native int nativeCreateInstanceContext(String str, String str2, String str3, WXJSObject[] wXJSObjectArr);

    public native int nativeDestoryInstance(String str, String str2, String str3, WXJSObject[] wXJSObjectArr);

    public native String nativeExecJSOnInstance(String str, String str2, int i);

    public native void nativeExecJSWithCallback(String str, String str2, String str3, WXJSObject[] wXJSObjectArr, long j);

    public native byte[] nativeExecJSWithResult(String str, String str2, String str3, WXJSObject[] wXJSObjectArr);

    public native void nativeFireEventOnDataRenderNode(String str, String str2, String str3, String str4);

    public native long[] nativeGetFirstScreenRenderTime(String str);

    public native long[] nativeGetRenderFinishTime(String str);

    public native void nativeRegisterModuleOnDataRenderNode(String str);

    public native void nativeUpdateGlobalConfig(String str);

    public WXBridge() {
        $jacocoInit()[0] = true;
    }

    public int initFramework(String str, WXParams wXParams) {
        boolean[] $jacocoInit = $jacocoInit();
        int nativeInitFramework = nativeInitFramework(str, wXParams);
        $jacocoInit[1] = true;
        return nativeInitFramework;
    }

    public int initFrameworkEnv(String str, WXParams wXParams, String str2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        int nativeInitFrameworkEnv = nativeInitFrameworkEnv(str, wXParams, str2, z);
        $jacocoInit[2] = true;
        return nativeInitFrameworkEnv;
    }

    public void refreshInstance(String str, String str2, String str3, WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeRefreshInstance(str, str2, str3, wXJSObjectArr);
        $jacocoInit[3] = true;
    }

    public int execJS(String str, String str2, String str3, WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        int nativeExecJS = nativeExecJS(str, str2, str3, wXJSObjectArr);
        $jacocoInit[4] = true;
        return nativeExecJS;
    }

    public void execJSWithCallback(String str, String str2, String str3, WXJSObject[] wXJSObjectArr, ResultCallback resultCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (resultCallback != null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            execJS(str, str2, str3, wXJSObjectArr);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        long generateCallbackId = ResultCallbackManager.generateCallbackId(resultCallback);
        $jacocoInit[9] = true;
        nativeExecJSWithCallback(str, str2, str3, wXJSObjectArr, generateCallbackId);
        $jacocoInit[10] = true;
    }

    @CalledByNative
    public void onReceivedResult(long j, byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        ResultCallback removeCallbackById = ResultCallbackManager.removeCallbackById(j);
        if (removeCallbackById == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            removeCallbackById.onReceiveResult(bArr);
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    public int execJSService(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        int nativeExecJSService = nativeExecJSService(str);
        $jacocoInit[15] = true;
        return nativeExecJSService;
    }

    public void takeHeapSnapshot(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeTakeHeapSnapshot(str);
        $jacocoInit[16] = true;
    }

    public int createInstanceContext(String str, String str2, String str3, WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        int nativeCreateInstanceContext = nativeCreateInstanceContext(str, str2, str3, wXJSObjectArr);
        $jacocoInit[17] = true;
        return nativeCreateInstanceContext;
    }

    public int destoryInstance(String str, String str2, String str3, WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        int nativeDestoryInstance = nativeDestoryInstance(str, str2, str3, wXJSObjectArr);
        $jacocoInit[18] = true;
        return nativeDestoryInstance;
    }

    public String execJSOnInstance(String str, String str2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        String nativeExecJSOnInstance = nativeExecJSOnInstance(str, str2, i);
        $jacocoInit[19] = true;
        return nativeExecJSOnInstance;
    }

    @CalledByNative
    public int callNative(String str, byte[] bArr, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        int callNative = callNative(str, JSON.parseArray(new String(bArr)), str2);
        $jacocoInit[20] = true;
        return callNative;
    }

    public int callNative(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            int callNative = callNative(str, JSONArray.parseArray(str2), str3);
            $jacocoInit[21] = true;
            return callNative;
        } catch (Exception e) {
            $jacocoInit[22] = true;
            WXLogUtils.e(TAG, "callNative throw exception: " + e.getMessage());
            $jacocoInit[23] = true;
            return 1;
        }
    }

    private int callNative(String str, JSONArray jSONArray, String str2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        long currentTimeMillis = System.currentTimeMillis();
        $jacocoInit[24] = true;
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            sDKInstance.firstScreenCreateInstanceTime(currentTimeMillis);
            $jacocoInit[27] = true;
        }
        try {
            $jacocoInit[28] = true;
            i = WXBridgeManager.getInstance().callNative(str, jSONArray, str2);
            $jacocoInit[29] = true;
        } catch (Throwable th) {
            $jacocoInit[30] = true;
            WXLogUtils.e(TAG, "callNative throw exception:" + th.getMessage());
            $jacocoInit[31] = true;
            i = 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[32] = true;
        } else if (i != -1) {
            $jacocoInit[33] = true;
        } else {
            $jacocoInit[34] = true;
            WXLogUtils.w("destroyInstance :" + str + " JSF must stop callNative");
            $jacocoInit[35] = true;
        }
        $jacocoInit[36] = true;
        return i;
    }

    @CalledByNative
    public void reportJSException(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().reportJSException(str, str2, str3);
        $jacocoInit[37] = true;
    }

    @CalledByNative
    public Object callNativeModule(String str, String str2, String str3, byte[] bArr, byte[] bArr2) {
        JSONArray jSONArray;
        JSONObject jSONObject;
        JSONArray jSONArray2;
        String str4 = str;
        byte[] bArr3 = bArr;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            long fixUnixTime = WXUtils.getFixUnixTime();
            $jacocoInit[38] = true;
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            if (bArr3 == null) {
                $jacocoInit[39] = true;
                jSONArray = null;
            } else {
                $jacocoInit[40] = true;
                if (sDKInstance == null) {
                    $jacocoInit[41] = true;
                } else {
                    if (sDKInstance.getRenderStrategy() == WXRenderStrategy.DATA_RENDER) {
                        $jacocoInit[42] = true;
                    } else {
                        $jacocoInit[43] = true;
                        if (sDKInstance.getRenderStrategy() != WXRenderStrategy.DATA_RENDER_BINARY) {
                            $jacocoInit[44] = true;
                        } else {
                            $jacocoInit[45] = true;
                        }
                    }
                    jSONArray2 = (JSONArray) JSON.parse(new String(bArr3, "UTF-8"));
                    $jacocoInit[46] = true;
                    jSONArray = jSONArray2;
                }
                jSONArray2 = (JSONArray) WXWsonJSONSwitch.parseWsonOrJSON(bArr);
                $jacocoInit[47] = true;
                jSONArray = jSONArray2;
            }
            if (bArr2 != null) {
                $jacocoInit[48] = true;
                $jacocoInit[49] = true;
                jSONObject = (JSONObject) WXWsonJSONSwitch.parseWsonOrJSON(bArr2);
            } else {
                if (jSONArray == null) {
                    $jacocoInit[50] = true;
                } else {
                    $jacocoInit[51] = true;
                    WXSDKInstance sDKInstance2 = WXSDKManager.getInstance().getSDKInstance(str);
                    if (sDKInstance2 == null) {
                        $jacocoInit[52] = true;
                    } else {
                        $jacocoInit[53] = true;
                        if (!WXBridgeManager.BundType.Rax.equals(sDKInstance2.bundleType)) {
                            $jacocoInit[54] = true;
                        } else {
                            $jacocoInit[55] = true;
                            Iterator<Object> it = jSONArray.iterator();
                            $jacocoInit[56] = true;
                            Object obj = null;
                            while (it.hasNext()) {
                                Object next = it.next();
                                $jacocoInit[57] = true;
                                if (!(next instanceof JSONObject)) {
                                    $jacocoInit[58] = true;
                                } else if (!((JSONObject) next).containsKey("__weex_options__")) {
                                    $jacocoInit[59] = true;
                                } else {
                                    $jacocoInit[60] = true;
                                    obj = ((JSONObject) next).get("__weex_options__");
                                    $jacocoInit[61] = true;
                                }
                                $jacocoInit[62] = true;
                            }
                            if (!(obj instanceof JSONObject)) {
                                $jacocoInit[63] = true;
                            } else {
                                $jacocoInit[64] = true;
                                jSONObject = (JSONObject) obj;
                            }
                        }
                    }
                }
                jSONObject = null;
            }
            Object callNativeModule = WXBridgeManager.getInstance().callNativeModule(str, str2, str3, jSONArray, jSONObject);
            if (sDKInstance == null) {
                $jacocoInit[65] = true;
            } else {
                $jacocoInit[66] = true;
                sDKInstance.getApmForInstance().updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_CALL_NATIVE_NUM, 1.0d);
                $jacocoInit[67] = true;
                WXInstanceApm apmForInstance = sDKInstance.getApmForInstance();
                $jacocoInit[68] = true;
                $jacocoInit[69] = true;
                apmForInstance.updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_CALL_NATIVE_TIME, (double) (WXUtils.getFixUnixTime() - fixUnixTime));
                $jacocoInit[70] = true;
            }
            WXJSObject wsonOrJsonWXJSObject = WXWsonJSONSwitch.toWsonOrJsonWXJSObject(callNativeModule);
            $jacocoInit[71] = true;
            return wsonOrJsonWXJSObject;
        } catch (Exception e) {
            $jacocoInit[72] = true;
            WXLogUtils.e(TAG, (Throwable) e);
            $jacocoInit[73] = true;
            WXJSObject wXJSObject = new WXJSObject((Object) null);
            $jacocoInit[74] = true;
            return wXJSObject;
        }
    }

    @CalledByNative
    public void callNativeComponent(String str, String str2, String str3, byte[] bArr, byte[] bArr2) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[75] = true;
            Object parseWsonOrJSON = WXWsonJSONSwitch.parseWsonOrJSON(bArr2);
            $jacocoInit[76] = true;
            WXBridgeManager.getInstance().callNativeComponent(str, str2, str3, (JSONArray) WXWsonJSONSwitch.parseWsonOrJSON(bArr), parseWsonOrJSON);
            $jacocoInit[77] = true;
        } catch (Exception e) {
            $jacocoInit[78] = true;
            WXLogUtils.e(TAG, (Throwable) e);
            $jacocoInit[79] = true;
        }
        $jacocoInit[80] = true;
    }

    @CalledByNative
    public void setTimeoutNative(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().setTimeout(str, str2);
        $jacocoInit[81] = true;
    }

    @CalledByNative
    public void setJSFrmVersion(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[82] = true;
        } else {
            WXEnvironment.JS_LIB_SDK_VERSION = str;
            $jacocoInit[83] = true;
        }
        $jacocoInit[84] = true;
    }

    public void resetWXBridge(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        String replace = getClass().getName().replace('.', IOUtils.f15883a);
        $jacocoInit[85] = true;
        nativeResetWXBridge(this, replace);
        $jacocoInit[86] = true;
    }

    @CalledByNative
    public int callUpdateFinish(String str, byte[] bArr, String str2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[87] = true;
            i = WXBridgeManager.getInstance().callUpdateFinish(str, str2);
            $jacocoInit[88] = true;
        } catch (Throwable th) {
            $jacocoInit[89] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[90] = true;
            } else {
                $jacocoInit[91] = true;
                WXLogUtils.e(TAG, "callCreateBody throw exception:" + th.getMessage());
                $jacocoInit[92] = true;
            }
            i = 1;
        }
        $jacocoInit[93] = true;
        return i;
    }

    @CalledByNative
    public int callRefreshFinish(String str, byte[] bArr, String str2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[94] = true;
            i = WXBridgeManager.getInstance().callRefreshFinish(str, str2);
            $jacocoInit[95] = true;
        } catch (Throwable th) {
            $jacocoInit[96] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[97] = true;
            } else {
                $jacocoInit[98] = true;
                WXLogUtils.e(TAG, "callCreateFinish throw exception:" + th.getMessage());
                $jacocoInit[99] = true;
            }
            i = 1;
        }
        $jacocoInit[100] = true;
        return i;
    }

    @CalledByNative
    public void reportServerCrash(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e(TAG, "reportServerCrash instanceId:" + str + " crashFile: " + str2);
        try {
            $jacocoInit[101] = true;
            WXBridgeManager.getInstance().callReportCrashReloadPage(str, str2);
            $jacocoInit[102] = true;
        } catch (Throwable th) {
            $jacocoInit[103] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[104] = true;
            } else {
                $jacocoInit[105] = true;
                WXLogUtils.e(TAG, "reloadPageNative throw exception:" + th.getMessage());
                $jacocoInit[106] = true;
            }
        }
        $jacocoInit[107] = true;
    }

    @CalledByNative
    public int callCreateBody(String str, String str2, String str3, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashSet<String> hashSet, float[] fArr, float[] fArr2, float[] fArr3) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[108] = true;
            i = WXBridgeManager.getInstance().callCreateBody(str, str2, str3, hashMap, hashMap2, hashSet, fArr, fArr2, fArr3);
            $jacocoInit[109] = true;
        } catch (Throwable th) {
            $jacocoInit[110] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[111] = true;
            } else {
                $jacocoInit[112] = true;
                WXLogUtils.e(TAG, "callCreateBody throw exception:" + th.getMessage());
                $jacocoInit[113] = true;
            }
            i = 1;
        }
        $jacocoInit[114] = true;
        return i;
    }

    @CalledByNative
    public int callAddElement(String str, String str2, String str3, int i, String str4, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashSet<String> hashSet, float[] fArr, float[] fArr2, float[] fArr3, boolean z) {
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[115] = true;
            i2 = WXBridgeManager.getInstance().callAddElement(str, str2, str3, i, str4, hashMap, hashMap2, hashSet, fArr, fArr2, fArr3, z);
            $jacocoInit[116] = true;
        } catch (Throwable th) {
            $jacocoInit[117] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[118] = true;
            } else {
                $jacocoInit[119] = true;
                th.printStackTrace();
                $jacocoInit[120] = true;
                WXLogUtils.e(TAG, "callAddElement throw error:" + th.getMessage());
                $jacocoInit[121] = true;
            }
            i2 = 1;
        }
        $jacocoInit[122] = true;
        return i2;
    }

    @CalledByNative
    public int callRemoveElement(String str, String str2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[123] = true;
            i = WXBridgeManager.getInstance().callRemoveElement(str, str2);
            $jacocoInit[124] = true;
        } catch (Throwable th) {
            $jacocoInit[125] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[126] = true;
            } else {
                $jacocoInit[127] = true;
                WXLogUtils.e(TAG, "callRemoveElement throw exception:" + th.getMessage());
                $jacocoInit[128] = true;
            }
            i = 1;
        }
        $jacocoInit[129] = true;
        return i;
    }

    @CalledByNative
    public int callMoveElement(String str, String str2, String str3, int i) {
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[130] = true;
            i2 = WXBridgeManager.getInstance().callMoveElement(str, str2, str3, i);
            $jacocoInit[131] = true;
        } catch (Throwable th) {
            $jacocoInit[132] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[133] = true;
            } else {
                $jacocoInit[134] = true;
                WXLogUtils.e(TAG, "callMoveElement throw exception:" + th.getMessage());
                $jacocoInit[135] = true;
            }
            i2 = 1;
        }
        $jacocoInit[136] = true;
        return i2;
    }

    @CalledByNative
    public int callAddEvent(String str, String str2, String str3) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[137] = true;
            i = WXBridgeManager.getInstance().callAddEvent(str, str2, str3);
            $jacocoInit[138] = true;
        } catch (Throwable th) {
            $jacocoInit[139] = true;
            WXLogUtils.e(TAG, "callAddEvent throw exception:" + th.getMessage());
            $jacocoInit[140] = true;
            i = 1;
        }
        $jacocoInit[141] = true;
        return i;
    }

    @CalledByNative
    public int callRemoveEvent(String str, String str2, String str3) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[142] = true;
            i = WXBridgeManager.getInstance().callRemoveEvent(str, str2, str3);
            $jacocoInit[143] = true;
        } catch (Throwable th) {
            $jacocoInit[144] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[145] = true;
            } else {
                $jacocoInit[146] = true;
                WXLogUtils.e(TAG, "callRemoveEvent throw exception:" + th.getMessage());
                $jacocoInit[147] = true;
            }
            i = 1;
        }
        $jacocoInit[148] = true;
        return i;
    }

    @CalledByNative
    public int callUpdateStyle(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, HashMap<String, String> hashMap4) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[149] = true;
            i = WXBridgeManager.getInstance().callUpdateStyle(str, str2, hashMap, hashMap2, hashMap3, hashMap4);
            $jacocoInit[150] = true;
        } catch (Throwable th) {
            $jacocoInit[151] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[152] = true;
            } else {
                $jacocoInit[153] = true;
                WXLogUtils.e(TAG, "callUpdateStyle throw exception:" + th.getMessage());
                $jacocoInit[154] = true;
            }
            i = 1;
        }
        $jacocoInit[155] = true;
        return i;
    }

    @CalledByNative
    public int callUpdateAttrs(String str, String str2, HashMap<String, String> hashMap) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[156] = true;
            i = WXBridgeManager.getInstance().callUpdateAttrs(str, str2, hashMap);
            $jacocoInit[157] = true;
        } catch (Throwable th) {
            $jacocoInit[158] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[159] = true;
            } else {
                $jacocoInit[160] = true;
                WXLogUtils.e(TAG, "callUpdateAttr throw exception:" + th.getMessage());
                $jacocoInit[161] = true;
            }
            i = 1;
        }
        $jacocoInit[162] = true;
        return i;
    }

    @CalledByNative
    public int callLayout(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7) {
        int i8;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[163] = true;
            i8 = WXBridgeManager.getInstance().callLayout(str, str2, i, i2, i3, i4, i5, i6, z, i7);
            $jacocoInit[164] = true;
        } catch (Throwable th) {
            $jacocoInit[165] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[166] = true;
            } else {
                $jacocoInit[167] = true;
                WXLogUtils.e(TAG, "callLayout throw exception:" + th.getMessage());
                $jacocoInit[168] = true;
            }
            i8 = 1;
        }
        $jacocoInit[169] = true;
        return i8;
    }

    @CalledByNative
    public int callCreateFinish(String str) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[170] = true;
            i = WXBridgeManager.getInstance().callCreateFinish(str);
            $jacocoInit[171] = true;
        } catch (Throwable th) {
            $jacocoInit[172] = true;
            WXLogUtils.e(TAG, "callCreateFinish throw exception:" + th.getMessage());
            $jacocoInit[173] = true;
            i = 1;
        }
        $jacocoInit[174] = true;
        return i;
    }

    @CalledByNative
    public int callRenderSuccess(String str) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[175] = true;
            i = WXBridgeManager.getInstance().callRenderSuccess(str);
            $jacocoInit[176] = true;
        } catch (Throwable th) {
            $jacocoInit[177] = true;
            WXLogUtils.e(TAG, "callCreateFinish throw exception:" + th.getMessage());
            $jacocoInit[178] = true;
            i = 1;
        }
        $jacocoInit[179] = true;
        return i;
    }

    @CalledByNative
    public int callAppendTreeCreateFinish(String str, String str2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[180] = true;
            i = WXBridgeManager.getInstance().callAppendTreeCreateFinish(str, str2);
            $jacocoInit[181] = true;
        } catch (Throwable th) {
            $jacocoInit[182] = true;
            WXLogUtils.e(TAG, "callAppendTreeCreateFinish throw exception:" + th.getMessage());
            $jacocoInit[183] = true;
            i = 1;
        }
        $jacocoInit[184] = true;
        return i;
    }

    @CalledByNative
    public int callHasTransitionPros(String str, String str2, HashMap<String, String> hashMap) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[185] = true;
            i = WXBridgeManager.getInstance().callHasTransitionPros(str, str2, hashMap);
            $jacocoInit[186] = true;
        } catch (Throwable th) {
            $jacocoInit[187] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[188] = true;
            } else {
                $jacocoInit[189] = true;
                WXLogUtils.e(TAG, "callHasTransitionPros throw exception:" + th.getMessage());
                $jacocoInit[190] = true;
            }
            i = 1;
        }
        $jacocoInit[191] = true;
        return i;
    }

    @CalledByNative
    public ContentBoxMeasurement getMeasurementFunc(String str, long j) {
        ContentBoxMeasurement contentBoxMeasurement;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[192] = true;
            contentBoxMeasurement = WXBridgeManager.getInstance().getMeasurementFunc(str, j);
            $jacocoInit[193] = true;
        } catch (Throwable th) {
            $jacocoInit[194] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[195] = true;
            } else {
                $jacocoInit[196] = true;
                WXLogUtils.e(TAG, "getMeasurementFunc throw exception:" + th.getMessage());
                $jacocoInit[197] = true;
            }
            contentBoxMeasurement = null;
        }
        $jacocoInit[198] = true;
        return contentBoxMeasurement;
    }

    public void bindMeasurementToRenderObject(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeBindMeasurementToRenderObject(j);
        $jacocoInit[199] = true;
    }

    public void setRenderContainerWrapContent(boolean z, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetRenderContainerWrapContent(z, str);
        $jacocoInit[200] = true;
    }

    public long[] getFirstScreenRenderTime(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        long[] nativeGetFirstScreenRenderTime = nativeGetFirstScreenRenderTime(str);
        $jacocoInit[201] = true;
        return nativeGetFirstScreenRenderTime;
    }

    public long[] getRenderFinishTime(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        long[] nativeGetRenderFinishTime = nativeGetRenderFinishTime(str);
        $jacocoInit[202] = true;
        return nativeGetRenderFinishTime;
    }

    public void setDefaultHeightAndWidthIntoRootDom(String str, float f, float f2, boolean z, boolean z2) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetDefaultHeightAndWidthIntoRootDom(str, f, f2, z, z2);
        $jacocoInit[203] = true;
    }

    public void onInstanceClose(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeOnInstanceClose(str);
        $jacocoInit[204] = true;
    }

    public void forceLayout(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeForceLayout(str);
        $jacocoInit[205] = true;
    }

    public boolean notifyLayout(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean nativeNotifyLayout = nativeNotifyLayout(str);
        $jacocoInit[206] = true;
        return nativeNotifyLayout;
    }

    public void setStyleWidth(String str, String str2, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetStyleWidth(str, str2, f);
        $jacocoInit[207] = true;
    }

    public void setMargin(String str, String str2, CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetMargin(str, str2, edge.ordinal(), f);
        $jacocoInit[208] = true;
    }

    public void setPadding(String str, String str2, CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetPadding(str, str2, edge.ordinal(), f);
        $jacocoInit[209] = true;
    }

    public void setPosition(String str, String str2, CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetPosition(str, str2, edge.ordinal(), f);
        $jacocoInit[210] = true;
    }

    public void markDirty(String str, String str2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeMarkDirty(str, str2, z);
        $jacocoInit[211] = true;
    }

    public void setStyleHeight(String str, String str2, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeSetStyleHeight(str, str2, f);
        $jacocoInit[212] = true;
    }

    public void registerCoreEnv(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeRegisterCoreEnv(str, str2);
        $jacocoInit[213] = true;
    }

    @CalledByNative
    public void reportNativeInitStatus(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXErrorCode.WX_JS_FRAMEWORK_INIT_SINGLE_PROCESS_SUCCESS.getErrorCode().equals(str)) {
            $jacocoInit[214] = true;
        } else {
            WXErrorCode wXErrorCode = WXErrorCode.WX_JS_FRAMEWORK_INIT_FAILED;
            $jacocoInit[215] = true;
            if (wXErrorCode.getErrorCode().equals(str)) {
                $jacocoInit[216] = true;
            } else if (WXErrorCode.WX_JS_FRAMEWORK_INIT_FAILED_PARAMS_NULL.getErrorCode().equals(str)) {
                $jacocoInit[226] = true;
                WXErrorCode wXErrorCode2 = WXErrorCode.WX_JS_FRAMEWORK_INIT_FAILED_PARAMS_NULL;
                StringBuilder sb = new StringBuilder();
                WXErrorCode wXErrorCode3 = WXErrorCode.WX_JS_FRAMEWORK_INIT_FAILED_PARAMS_NULL;
                $jacocoInit[227] = true;
                sb.append(wXErrorCode3.getErrorMsg());
                sb.append(": ");
                sb.append(str2);
                String sb2 = sb.toString();
                $jacocoInit[228] = true;
                WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode2, "WeexProxy::initFromParam()", sb2, (Map<String, String>) null);
                $jacocoInit[229] = true;
                return;
            } else {
                WXErrorCode[] values = WXErrorCode.values();
                int length = values.length;
                int i = 0;
                $jacocoInit[230] = true;
                while (true) {
                    if (i >= length) {
                        $jacocoInit[231] = true;
                        break;
                    }
                    WXErrorCode wXErrorCode4 = values[i];
                    $jacocoInit[232] = true;
                    if (wXErrorCode4.getErrorType().equals(WXErrorCode.ErrorType.NATIVE_ERROR)) {
                        $jacocoInit[234] = true;
                        if (wXErrorCode4.getErrorCode().equals(str)) {
                            $jacocoInit[236] = true;
                            WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode4, IWXUserTrackAdapter.INIT_FRAMEWORK, str2, (Map<String, String>) null);
                            $jacocoInit[237] = true;
                            break;
                        }
                        $jacocoInit[235] = true;
                    } else {
                        $jacocoInit[233] = true;
                    }
                    i++;
                    $jacocoInit[238] = true;
                }
                $jacocoInit[239] = true;
                return;
            }
        }
        IWXUserTrackAdapter iWXUserTrackAdapter = WXSDKManager.getInstance().getIWXUserTrackAdapter();
        if (iWXUserTrackAdapter == null) {
            $jacocoInit[217] = true;
        } else {
            $jacocoInit[218] = true;
            HashMap hashMap = new HashMap(3);
            $jacocoInit[219] = true;
            hashMap.put("errCode", str);
            $jacocoInit[220] = true;
            hashMap.put("arg", "InitFrameworkNativeError");
            $jacocoInit[221] = true;
            hashMap.put("errMsg", str2);
            $jacocoInit[222] = true;
            Log.e("Dyy", "reportNativeInitStatus is running and errorCode is " + str + " And errorMsg is " + str2);
            $jacocoInit[223] = true;
            iWXUserTrackAdapter.commit((Context) null, (String) null, IWXUserTrackAdapter.INIT_FRAMEWORK, (WXPerformance) null, hashMap);
            $jacocoInit[224] = true;
        }
        $jacocoInit[225] = true;
    }

    public void fireEventOnDataRenderNode(String str, String str2, String str3, String str4) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeFireEventOnDataRenderNode(str, str2, str3, str4);
        $jacocoInit[240] = true;
    }

    public void registerModuleOnDataRenderNode(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        nativeRegisterModuleOnDataRenderNode(str);
        $jacocoInit[241] = true;
    }
}
