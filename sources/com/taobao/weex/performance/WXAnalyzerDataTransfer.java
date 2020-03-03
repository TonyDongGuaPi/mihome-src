package com.taobao.weex.performance;

import android.util.Log;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.bridge.WXJSObject;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXJSExceptionInfo;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXUtils;
import com.xiaomi.payment.data.AnalyticsConstants;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;
import org.json.JSONObject;

public class WXAnalyzerDataTransfer {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String GROUP = "WXAnalyzer";
    public static final String INTERACTION_TAG = "wxInteractionAnalyzer";
    private static final String MODULE_ERROR = "WXError";
    private static final String MODULE_WX_APM = "wxapm";
    public static boolean isOpenPerformance = false;
    private static boolean sOpenInteractionLog;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4826161757640798574L, "com/taobao/weex/performance/WXAnalyzerDataTransfer", 64);
        $jacocoData = a2;
        return a2;
    }

    public WXAnalyzerDataTransfer() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[63] = true;
    }

    public static void transferPerformance(String str, String str2, String str3, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isOpenPerformance) {
            $jacocoInit[1] = true;
            return;
        }
        if (!sOpenInteractionLog) {
            $jacocoInit[2] = true;
        } else if (!AnalyticsConstants.bH.equals(str2)) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            Log.d(INTERACTION_TAG, "[client][stage]" + str + "," + str3 + "," + obj);
            $jacocoInit[5] = true;
        }
        List<IWXAnalyzer> wXAnalyzerList = WXSDKManager.getInstance().getWXAnalyzerList();
        $jacocoInit[6] = true;
        if (wXAnalyzerList == null) {
            $jacocoInit[7] = true;
        } else if (wXAnalyzerList.size() == 0) {
            $jacocoInit[8] = true;
        } else {
            WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getAllInstanceMap().get(str);
            if (wXSDKInstance != null) {
                $jacocoInit[10] = true;
                try {
                    String jSONObject = new JSONObject().put(str3, obj).toString();
                    $jacocoInit[14] = true;
                    for (IWXAnalyzer transfer : wXAnalyzerList) {
                        $jacocoInit[15] = true;
                        transfer.transfer(MODULE_WX_APM, wXSDKInstance.getInstanceId(), str2, jSONObject);
                        $jacocoInit[16] = true;
                    }
                    $jacocoInit[17] = true;
                    return;
                } catch (Exception e) {
                    $jacocoInit[12] = true;
                    e.printStackTrace();
                    $jacocoInit[13] = true;
                    return;
                }
            } else {
                $jacocoInit[11] = true;
                return;
            }
        }
        $jacocoInit[9] = true;
    }

    public static void transferInteractionInfo(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isOpenPerformance) {
            $jacocoInit[18] = true;
            return;
        }
        List<IWXAnalyzer> wXAnalyzerList = WXSDKManager.getInstance().getWXAnalyzerList();
        $jacocoInit[19] = true;
        if (wXAnalyzerList == null) {
            $jacocoInit[20] = true;
        } else if (wXAnalyzerList.size() == 0) {
            $jacocoInit[21] = true;
        } else {
            long fixUnixTime = WXUtils.getFixUnixTime() - wXComponent.getInstance().getWXPerformance().renderUnixTimeOrigin;
            try {
                $jacocoInit[23] = true;
                JSONObject jSONObject = new JSONObject();
                $jacocoInit[24] = true;
                JSONObject put = jSONObject.put("renderOriginDiffTime", fixUnixTime);
                $jacocoInit[25] = true;
                JSONObject put2 = put.put("type", wXComponent.getComponentType());
                $jacocoInit[26] = true;
                JSONObject put3 = put2.put("ref", wXComponent.getRef());
                $jacocoInit[27] = true;
                JSONObject put4 = put3.put("style", wXComponent.getStyles());
                $jacocoInit[28] = true;
                JSONObject put5 = put4.put("attrs", wXComponent.getAttrs());
                $jacocoInit[29] = true;
                String jSONObject2 = put5.toString();
                $jacocoInit[32] = true;
                for (IWXAnalyzer transfer : wXAnalyzerList) {
                    $jacocoInit[33] = true;
                    transfer.transfer(MODULE_WX_APM, wXComponent.getInstanceId(), "wxinteraction", jSONObject2);
                    $jacocoInit[34] = true;
                }
                $jacocoInit[35] = true;
                return;
            } catch (Exception e) {
                $jacocoInit[30] = true;
                e.printStackTrace();
                $jacocoInit[31] = true;
                return;
            }
        }
        $jacocoInit[22] = true;
    }

    public static void transferError(WXJSExceptionInfo wXJSExceptionInfo, String str) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[36] = true;
            return;
        }
        List<IWXAnalyzer> wXAnalyzerList = WXSDKManager.getInstance().getWXAnalyzerList();
        $jacocoInit[37] = true;
        if (wXAnalyzerList == null) {
            $jacocoInit[38] = true;
        } else if (wXAnalyzerList.size() == 0) {
            $jacocoInit[39] = true;
        } else {
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            if (sDKInstance == null) {
                $jacocoInit[41] = true;
                return;
            }
            WXErrorCode errCode = wXJSExceptionInfo.getErrCode();
            try {
                $jacocoInit[42] = true;
                JSONObject jSONObject = new JSONObject();
                $jacocoInit[43] = true;
                JSONObject put = jSONObject.put(BindingXConstants.p, str);
                $jacocoInit[44] = true;
                JSONObject put2 = put.put("url", sDKInstance.getBundleUrl());
                $jacocoInit[45] = true;
                JSONObject put3 = put2.put("errorCode", errCode.getErrorCode());
                $jacocoInit[46] = true;
                JSONObject put4 = put3.put("errorMsg", errCode.getErrorMsg());
                $jacocoInit[47] = true;
                JSONObject put5 = put4.put("errorGroup", errCode.getErrorGroup());
                $jacocoInit[48] = true;
                str2 = put5.toString();
                $jacocoInit[49] = true;
            } catch (Exception e) {
                $jacocoInit[50] = true;
                e.printStackTrace();
                $jacocoInit[51] = true;
                str2 = "";
            }
            $jacocoInit[52] = true;
            for (IWXAnalyzer transfer : wXAnalyzerList) {
                $jacocoInit[53] = true;
                transfer.transfer(GROUP, MODULE_ERROR, errCode.getErrorType().toString(), str2);
                $jacocoInit[54] = true;
            }
            $jacocoInit[55] = true;
            return;
        }
        $jacocoInit[40] = true;
    }

    public static void switchInteractionLog(final boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sOpenInteractionLog == z) {
            $jacocoInit[56] = true;
        } else if (!WXEnvironment.JsFrameworkInit) {
            $jacocoInit[57] = true;
        } else {
            sOpenInteractionLog = z;
            $jacocoInit[59] = true;
            WXBridgeManager.getInstance().post(new Runnable() {
                private static transient /* synthetic */ boolean[] $jacocoData;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(6225681691462793238L, "com/taobao/weex/performance/WXAnalyzerDataTransfer$1", 5);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    $jacocoInit[0] = true;
                }

                public void run() {
                    int i;
                    boolean[] $jacocoInit = $jacocoInit();
                    WXJSObject[] wXJSObjectArr = new WXJSObject[1];
                    if (z) {
                        $jacocoInit[1] = true;
                        i = 1;
                    } else {
                        $jacocoInit[2] = true;
                        i = 0;
                    }
                    wXJSObjectArr[0] = new WXJSObject(Integer.valueOf(i));
                    $jacocoInit[3] = true;
                    WXBridgeManager.getInstance().invokeExecJS("", (String) null, "switchInteractionLog", wXJSObjectArr, false);
                    $jacocoInit[4] = true;
                }
            });
            $jacocoInit[60] = true;
            WXBridgeManager.getInstance().registerCoreEnv("switchInteractionLog", String.valueOf(z));
            $jacocoInit[61] = true;
            return;
        }
        $jacocoInit[58] = true;
    }

    public static boolean isInteractionLogOpen() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = sOpenInteractionLog;
        $jacocoInit[62] = true;
        return z;
    }
}
