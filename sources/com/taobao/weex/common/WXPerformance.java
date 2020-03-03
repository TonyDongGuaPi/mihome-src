package com.taobao.weex.common;

import android.support.annotation.RestrictTo;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Deprecated
public class WXPerformance {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String CACHE_TYPE = "cacheType";
    public static final String DEFAULT = "default";
    public static boolean TRACE_DATA = WXEnvironment.isApkDebugable();
    public static final int VIEW_LIMIT_HEIGHT = (WXViewUtils.getScreenHeight() / 2);
    public static final int VIEW_LIMIT_WIDTH = (WXViewUtils.getScreenWidth() / 2);
    public long JSLibInitTime;
    public double JSLibSize;
    public String JSLibVersion = WXEnvironment.JS_LIB_SDK_VERSION;
    public double JSTemplateSize;
    public String WXSDKVersion = WXEnvironment.WXSDK_VERSION;
    public long actualNetworkTime;
    public String args = "";
    public long avgFPS;
    public long backImproveMemory;
    @Deprecated
    public String bizType = "weex";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String cacheType = "none";
    public long callBridgeTime;
    public long callCreateFinishTime;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public long callCreateInstanceTime;
    public int cellExceedNum;
    @Deprecated
    public long communicateTime;
    public long componentCount;
    public long componentCreateTime;
    public String connectionType;
    public long cssLayoutTime;
    public String errCode;
    @Deprecated
    public String errMsg;
    public long firstScreenJSFExecuteTime;
    public double fluency = 100.0d;
    public int fsCallEventTotalNum;
    public int fsCallJsTotalNum;
    public long fsCallJsTotalTime;
    public int fsCallNativeTotalNum;
    public long fsCallNativeTotalTime;
    public int fsComponentCount;
    public int fsComponentCreateTime;
    public long fsRenderTime;
    public int fsRequestNum;
    public long interactionTime;
    public int interactionViewAddCount;
    public int interactionViewAddLimitCount;
    public int localInteractionViewAddCount;
    public double localReadTime;
    public int mActionAddElementCount = 0;
    public int mActionAddElementSumTime = 0;
    private StringBuilder mErrMsgBuilder;
    private String mInstanceId;
    public int maxDeepVDomLayer;
    public int maxDeepViewLayer;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @Deprecated
    public long[] measureTimes = new long[5];
    public long networkTime;
    public long newFsRenderTime;
    public long packageSpendTime;
    public String pageName = "default";
    public long parseJsonTime;
    public long pureNetworkTime;
    public String renderFailedDetail;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public long renderTimeOrigin;
    public long renderUnixTimeOrigin;
    public String requestType = "other";
    public long screenRenderTime;
    public long syncTaskTime;
    public long templateLoadTime;
    @Deprecated
    public String templateUrl;
    public int timerInvokeCount;
    public double totalTime;
    public int useScroller = 0;
    public double wrongImgSizeCount;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @Deprecated
    public String[] wxDims = new String[5];
    public String zCacheInfo;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5226179563980661728L, "com/taobao/weex/common/WXPerformance", 108);
        $jacocoData = a2;
        return a2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public enum Dimension {
        JSLibVersion,
        WXSDKVersion,
        pageName,
        spm,
        scheme,
        cacheType,
        requestType,
        networkType,
        connectionType,
        zcacheInfo,
        wxContainerName,
        wxInstanceType,
        wxParentPage,
        wxdim1,
        wxdim2,
        wxdim3,
        wxdim4,
        wxdim5,
        bizType,
        templateUrl,
        useScroller;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[23] = true;
        }
    }

    public enum Measure {
        JSLibSize(0.0d, Double.MAX_VALUE),
        JSLibInitTime(0.0d, 80000.0d),
        SDKInitTime(0.0d, 120000.0d),
        SDKInitInvokeTime(0.0d, 5000.0d),
        SDKInitExecuteTime(0.0d, 5000.0d),
        JSTemplateSize(0.0d, 5000.0d),
        pureNetworkTime(0.0d, 15000.0d),
        networkTime(0.0d, 15000.0d),
        fsCreateInstanceTime(0.0d, 3000.0d),
        fsCallJsTotalTime(0.0d, 5000.0d),
        fsCallJsTotalNum(0.0d, Double.MAX_VALUE),
        fsCallNativeTotalTime(0.0d, 5000.0d),
        fsCallNativeTotalNum(0.0d, Double.MAX_VALUE),
        fsCallEventTotalNum(0.0d, Double.MAX_VALUE),
        fsComponentCount(0.0d, 100000.0d),
        fsComponentCreateTime(0.0d, Double.MAX_VALUE),
        fsRenderTime(0.0d, 5000.0d),
        fsRequestNum(0.0d, 100.0d),
        callCreateFinishTime(0.0d, 10000.0d),
        cellExceedNum(0.0d, Double.MAX_VALUE),
        communicateTotalTime(0.0d, 5000.0d),
        maxDeepViewLayer(0.0d, Double.MAX_VALUE),
        maxDeepVDomLayer(0.0d, Double.MAX_VALUE),
        componentCount(0.0d, 1000000.0d),
        componentCreateTime(0.0d, Double.MAX_VALUE),
        avgFps(0.0d, 61.0d),
        timerCount(0.0d, Double.MAX_VALUE),
        MaxImproveMemory(0.0d, Double.MAX_VALUE),
        BackImproveMemory(0.0d, Double.MAX_VALUE),
        PushImproveMemory(0.0d, Double.MAX_VALUE),
        measureTime1(0.0d, Double.MAX_VALUE),
        measureTime2(0.0d, Double.MAX_VALUE),
        measureTime3(0.0d, Double.MAX_VALUE),
        measureTime4(0.0d, Double.MAX_VALUE),
        measureTime5(0.0d, Double.MAX_VALUE),
        callBridgeTime(0.0d, Double.MAX_VALUE),
        cssLayoutTime(0.0d, Double.MAX_VALUE),
        parseJsonTime(0.0d, Double.MAX_VALUE),
        communicateTime(0.0d, 5000.0d),
        screenRenderTime(0.0d, 5000.0d),
        totalTime(0.0d, 5000.0d),
        localReadTime(0.0d, 5000.0d),
        templateLoadTime(0.0d, 5000.0d),
        packageSpendTime(0.0d, 5000.0d),
        syncTaskTime(0.0d, 5000.0d),
        actualNetworkTime(0.0d, 5000.0d),
        firstScreenJSFExecuteTime(0.0d, 5000.0d),
        fluency(0.0d, 101.0d),
        imgSizeCount(0.0d, 2000.0d),
        interactionTime(0.0d, 10000.0d),
        interactionViewAddCount(0.0d, Double.MAX_VALUE),
        interactionViewAddLimitCount(0.0d, Double.MAX_VALUE),
        newFsRenderTime(0.0d, 10000.0d);
        
        private double mMaxRange;
        private double mMinRange;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[57] = true;
        }

        private Measure(double d, double d2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mMinRange = d;
            this.mMaxRange = d2;
            $jacocoInit[2] = true;
        }

        public double getMinRange() {
            boolean[] $jacocoInit = $jacocoInit();
            double d = this.mMinRange;
            $jacocoInit[3] = true;
            return d;
        }

        public double getMaxRange() {
            boolean[] $jacocoInit = $jacocoInit();
            double d = this.mMaxRange;
            $jacocoInit[4] = true;
            return d;
        }
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[105] = true;
        $jacocoInit[106] = true;
        $jacocoInit[107] = true;
    }

    public WXPerformance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mErrMsgBuilder = new StringBuilder();
        this.mInstanceId = str;
        $jacocoInit[1] = true;
    }

    public Map<String, Double> getMeasureMap() {
        double d;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.fsRenderTime != 0) {
            d = (double) (this.fsRenderTime - this.renderTimeOrigin);
            $jacocoInit[2] = true;
        } else if (this.totalTime != 0.0d) {
            d = this.totalTime;
            $jacocoInit[3] = true;
        } else {
            d = -1.0d;
            $jacocoInit[4] = true;
        }
        HashMap hashMap = new HashMap();
        $jacocoInit[5] = true;
        hashMap.put(Measure.JSLibSize.toString(), Double.valueOf(this.JSLibSize));
        $jacocoInit[6] = true;
        hashMap.put(Measure.JSLibInitTime.toString(), Double.valueOf((double) this.JSLibInitTime));
        $jacocoInit[7] = true;
        hashMap.put(Measure.SDKInitTime.toString(), Double.valueOf((double) WXEnvironment.sSDKInitTime));
        $jacocoInit[8] = true;
        hashMap.put(Measure.SDKInitInvokeTime.toString(), Double.valueOf((double) WXEnvironment.sSDKInitInvokeTime));
        $jacocoInit[9] = true;
        hashMap.put(Measure.SDKInitExecuteTime.toString(), Double.valueOf((double) WXEnvironment.sSDKInitExecuteTime));
        $jacocoInit[10] = true;
        hashMap.put(Measure.JSTemplateSize.toString(), Double.valueOf(this.JSTemplateSize));
        $jacocoInit[11] = true;
        hashMap.put(Measure.pureNetworkTime.toString(), Double.valueOf((double) this.pureNetworkTime));
        $jacocoInit[12] = true;
        hashMap.put(Measure.networkTime.toString(), Double.valueOf((double) this.networkTime));
        $jacocoInit[13] = true;
        hashMap.put(Measure.fsCreateInstanceTime.toString(), Double.valueOf((double) this.callCreateInstanceTime));
        $jacocoInit[14] = true;
        hashMap.put(Measure.fsCallJsTotalTime.toString(), Double.valueOf((double) this.fsCallJsTotalTime));
        $jacocoInit[15] = true;
        hashMap.put(Measure.fsCallJsTotalNum.toString(), Double.valueOf((double) this.fsCallJsTotalNum));
        $jacocoInit[16] = true;
        hashMap.put(Measure.fsCallNativeTotalTime.toString(), Double.valueOf((double) this.fsCallNativeTotalTime));
        $jacocoInit[17] = true;
        hashMap.put(Measure.fsCallNativeTotalNum.toString(), Double.valueOf((double) this.fsCallNativeTotalNum));
        $jacocoInit[18] = true;
        hashMap.put(Measure.fsComponentCount.toString(), Double.valueOf((double) this.fsComponentCount));
        $jacocoInit[19] = true;
        hashMap.put(Measure.fsComponentCreateTime.toString(), Double.valueOf((double) this.fsComponentCreateTime));
        $jacocoInit[20] = true;
        hashMap.put(Measure.fsRenderTime.toString(), Double.valueOf(d));
        $jacocoInit[21] = true;
        hashMap.put(Measure.fsRequestNum.toString(), Double.valueOf((double) this.fsRequestNum));
        $jacocoInit[22] = true;
        hashMap.put(Measure.communicateTotalTime.toString(), Double.valueOf(this.totalTime));
        $jacocoInit[23] = true;
        hashMap.put(Measure.maxDeepViewLayer.toString(), Double.valueOf((double) this.maxDeepViewLayer));
        $jacocoInit[24] = true;
        hashMap.put(Measure.maxDeepVDomLayer.toString(), Double.valueOf((double) this.maxDeepVDomLayer));
        $jacocoInit[25] = true;
        hashMap.put(Measure.componentCount.toString(), Double.valueOf((double) this.componentCount));
        $jacocoInit[26] = true;
        hashMap.put(Measure.componentCreateTime.toString(), Double.valueOf((double) this.componentCreateTime));
        $jacocoInit[27] = true;
        hashMap.put(Measure.cellExceedNum.toString(), Double.valueOf((double) this.cellExceedNum));
        $jacocoInit[28] = true;
        hashMap.put(Measure.timerCount.toString(), Double.valueOf((double) this.timerInvokeCount));
        $jacocoInit[29] = true;
        hashMap.put(Measure.avgFps.toString(), Double.valueOf((double) this.avgFPS));
        $jacocoInit[30] = true;
        hashMap.put(Measure.fluency.toString(), Double.valueOf(this.fluency));
        $jacocoInit[31] = true;
        hashMap.put(Measure.MaxImproveMemory.toString(), Double.valueOf(0.0d));
        $jacocoInit[32] = true;
        hashMap.put(Measure.BackImproveMemory.toString(), Double.valueOf((double) this.backImproveMemory));
        $jacocoInit[33] = true;
        hashMap.put(Measure.PushImproveMemory.toString(), Double.valueOf(0.0d));
        $jacocoInit[34] = true;
        hashMap.put(Measure.fsCallEventTotalNum.toString(), Double.valueOf((double) this.fsCallEventTotalNum));
        $jacocoInit[35] = true;
        hashMap.put(Measure.callCreateFinishTime.toString(), Double.valueOf((double) this.callCreateFinishTime));
        $jacocoInit[36] = true;
        hashMap.put(Measure.imgSizeCount.toString(), Double.valueOf(this.wrongImgSizeCount));
        $jacocoInit[37] = true;
        hashMap.put(Measure.interactionTime.toString(), Double.valueOf((double) this.interactionTime));
        $jacocoInit[38] = true;
        hashMap.put(Measure.interactionViewAddCount.toString(), Double.valueOf((double) this.interactionViewAddCount));
        $jacocoInit[39] = true;
        hashMap.put(Measure.interactionViewAddLimitCount.toString(), Double.valueOf((double) this.interactionViewAddLimitCount));
        $jacocoInit[40] = true;
        hashMap.put(Measure.newFsRenderTime.toString(), Double.valueOf((double) this.newFsRenderTime));
        $jacocoInit[41] = true;
        hashMap.put(Measure.callBridgeTime.toString(), Double.valueOf((double) this.callBridgeTime));
        $jacocoInit[42] = true;
        hashMap.put(Measure.cssLayoutTime.toString(), Double.valueOf((double) this.cssLayoutTime));
        $jacocoInit[43] = true;
        hashMap.put(Measure.parseJsonTime.toString(), Double.valueOf((double) this.parseJsonTime));
        $jacocoInit[44] = true;
        hashMap.put(Measure.screenRenderTime.toString(), Double.valueOf((double) this.screenRenderTime));
        $jacocoInit[45] = true;
        hashMap.put(Measure.communicateTime.toString(), Double.valueOf((double) this.communicateTime));
        $jacocoInit[46] = true;
        hashMap.put(Measure.localReadTime.toString(), Double.valueOf(this.localReadTime));
        $jacocoInit[47] = true;
        hashMap.put(Measure.templateLoadTime.toString(), Double.valueOf((double) this.templateLoadTime));
        $jacocoInit[48] = true;
        hashMap.put(Measure.firstScreenJSFExecuteTime.toString(), Double.valueOf((double) this.firstScreenJSFExecuteTime));
        $jacocoInit[49] = true;
        hashMap.put(Measure.actualNetworkTime.toString(), Double.valueOf((double) this.actualNetworkTime));
        $jacocoInit[50] = true;
        hashMap.put(Measure.syncTaskTime.toString(), Double.valueOf((double) this.syncTaskTime));
        $jacocoInit[51] = true;
        hashMap.put(Measure.packageSpendTime.toString(), Double.valueOf((double) this.packageSpendTime));
        $jacocoInit[52] = true;
        hashMap.put(Measure.measureTime1.toString(), Double.valueOf((double) this.measureTimes[0]));
        $jacocoInit[53] = true;
        hashMap.put(Measure.measureTime2.toString(), Double.valueOf((double) this.measureTimes[1]));
        $jacocoInit[54] = true;
        hashMap.put(Measure.measureTime3.toString(), Double.valueOf((double) this.measureTimes[2]));
        $jacocoInit[55] = true;
        hashMap.put(Measure.measureTime4.toString(), Double.valueOf((double) this.measureTimes[3]));
        $jacocoInit[56] = true;
        hashMap.put(Measure.measureTime5.toString(), Double.valueOf((double) this.measureTimes[4]));
        $jacocoInit[57] = true;
        return hashMap;
    }

    public Map<String, String> getDimensionMap() {
        String str;
        String str2;
        String str3;
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[58] = true;
        hashMap.put(Dimension.JSLibVersion.toString(), this.JSLibVersion);
        $jacocoInit[59] = true;
        hashMap.put(Dimension.WXSDKVersion.toString(), this.WXSDKVersion);
        $jacocoInit[60] = true;
        hashMap.put(Dimension.pageName.toString(), this.pageName);
        $jacocoInit[61] = true;
        hashMap.put(Dimension.requestType.toString(), this.requestType);
        $jacocoInit[62] = true;
        hashMap.put(Dimension.networkType.toString(), "unknown");
        $jacocoInit[63] = true;
        hashMap.put(Dimension.connectionType.toString(), this.connectionType);
        $jacocoInit[64] = true;
        hashMap.put(Dimension.zcacheInfo.toString(), this.zCacheInfo);
        $jacocoInit[65] = true;
        hashMap.put(Dimension.cacheType.toString(), this.cacheType);
        $jacocoInit[66] = true;
        hashMap.put(Dimension.useScroller.toString(), String.valueOf(this.useScroller));
        $jacocoInit[67] = true;
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(this.mInstanceId);
        $jacocoInit[68] = true;
        if (sDKInstance == null) {
            str = "unKnow";
            $jacocoInit[69] = true;
        } else {
            str = sDKInstance.getContainerInfo().get(WXInstanceApm.KEY_PAGE_PROPERTIES_CONTAINER_NAME);
            $jacocoInit[70] = true;
        }
        hashMap.put(WXInstanceApm.KEY_PAGE_PROPERTIES_CONTAINER_NAME, str);
        $jacocoInit[71] = true;
        if (sDKInstance == null) {
            str2 = "unKnow";
            $jacocoInit[72] = true;
        } else {
            str2 = sDKInstance.getContainerInfo().get(WXInstanceApm.KEY_PAGE_PROPERTIES_INSTANCE_TYPE);
            $jacocoInit[73] = true;
        }
        hashMap.put(WXInstanceApm.KEY_PAGE_PROPERTIES_INSTANCE_TYPE, str2);
        $jacocoInit[74] = true;
        if (sDKInstance == null) {
            str3 = "unKnow";
            $jacocoInit[75] = true;
        } else {
            str3 = sDKInstance.getContainerInfo().get(WXInstanceApm.KEY_PAGE_PROPERTIES_PARENT_PAGE);
            $jacocoInit[76] = true;
        }
        hashMap.put(WXInstanceApm.KEY_PAGE_PROPERTIES_PARENT_PAGE, str3);
        $jacocoInit[77] = true;
        hashMap.put(Dimension.wxdim1.toString(), this.wxDims[0]);
        $jacocoInit[78] = true;
        hashMap.put(Dimension.wxdim2.toString(), this.wxDims[1]);
        $jacocoInit[79] = true;
        hashMap.put(Dimension.wxdim3.toString(), this.wxDims[2]);
        $jacocoInit[80] = true;
        hashMap.put(Dimension.wxdim4.toString(), this.wxDims[3]);
        $jacocoInit[81] = true;
        hashMap.put(Dimension.wxdim5.toString(), this.wxDims[4]);
        $jacocoInit[82] = true;
        hashMap.put(Dimension.bizType.toString(), this.bizType);
        $jacocoInit[83] = true;
        hashMap.put(Dimension.templateUrl.toString(), this.templateUrl);
        $jacocoInit[84] = true;
        return hashMap;
    }

    public static String[] getDimensions() {
        boolean[] $jacocoInit = $jacocoInit();
        LinkedList linkedList = new LinkedList();
        $jacocoInit[85] = true;
        Dimension[] values = Dimension.values();
        int length = values.length;
        $jacocoInit[86] = true;
        int i = 0;
        while (i < length) {
            Dimension dimension = values[i];
            $jacocoInit[87] = true;
            linkedList.add(dimension.toString());
            i++;
            $jacocoInit[88] = true;
        }
        String[] strArr = (String[]) linkedList.toArray(new String[linkedList.size()]);
        $jacocoInit[89] = true;
        return strArr;
    }

    public static String[] getMeasures() {
        boolean[] $jacocoInit = $jacocoInit();
        LinkedList linkedList = new LinkedList();
        $jacocoInit[90] = true;
        Measure[] values = Measure.values();
        int length = values.length;
        $jacocoInit[91] = true;
        int i = 0;
        while (i < length) {
            Measure measure = values[i];
            $jacocoInit[92] = true;
            linkedList.add(measure.toString());
            i++;
            $jacocoInit[93] = true;
        }
        String[] strArr = (String[]) linkedList.toArray(new String[linkedList.size()]);
        $jacocoInit[94] = true;
        return strArr;
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXEnvironment.isApkDebugable()) {
            $jacocoInit[95] = true;
            StringBuilder sb = new StringBuilder();
            sb.append("bizType:");
            sb.append(this.bizType);
            sb.append(",pageName:");
            sb.append(this.pageName);
            sb.append(",templateLoadTime");
            sb.append(this.templateLoadTime);
            sb.append(",localReadTime:");
            sb.append(this.localReadTime);
            sb.append(",JSLibInitTime:");
            sb.append(this.JSLibInitTime);
            sb.append(",JSLibSize:");
            sb.append(this.JSLibSize);
            sb.append(",templateUrl");
            sb.append(this.templateUrl);
            sb.append(",JSTemplateSize:");
            sb.append(this.JSTemplateSize);
            sb.append(",communicateTime:");
            sb.append(this.communicateTime);
            sb.append(",screenRenderTime:");
            sb.append(this.screenRenderTime);
            sb.append(",firstScreenJSFExecuteTime:");
            sb.append(this.firstScreenJSFExecuteTime);
            sb.append(",componentCount:");
            sb.append(this.componentCount);
            sb.append(",syncTaskTime:");
            sb.append(this.syncTaskTime);
            sb.append(",pureNetworkTime:");
            sb.append(this.pureNetworkTime);
            sb.append(",networkTime:");
            sb.append(this.networkTime);
            sb.append(",actualNetworkTime:");
            sb.append(this.actualNetworkTime);
            sb.append(",packageSpendTime:");
            sb.append(this.packageSpendTime);
            sb.append(",connectionType:");
            sb.append(this.connectionType);
            sb.append(",requestType:");
            sb.append(this.requestType);
            sb.append(",initInvokeTime:");
            sb.append(WXEnvironment.sSDKInitInvokeTime);
            sb.append(",initExecuteTime:");
            sb.append(WXEnvironment.sSDKInitExecuteTime);
            sb.append(",SDKInitTime:");
            sb.append(WXEnvironment.sSDKInitTime);
            sb.append(",totalTime:");
            sb.append(this.totalTime);
            sb.append(",JSLibVersion:");
            sb.append(this.JSLibVersion);
            sb.append(",WXSDKVersion:");
            sb.append(this.WXSDKVersion);
            sb.append(",errCode:");
            sb.append(this.errCode);
            sb.append(",renderFailedDetail:");
            sb.append(this.renderFailedDetail);
            sb.append(",arg:");
            sb.append(this.args);
            sb.append(",errMsg:");
            $jacocoInit[96] = true;
            sb.append(getErrMsg());
            String sb2 = sb.toString();
            $jacocoInit[97] = true;
            return sb2;
        }
        String obj = super.toString();
        $jacocoInit[98] = true;
        return obj;
    }

    public String getPerfData() {
        String str = "networkTime:" + this.networkTime + " actualNetworkTime:" + this.actualNetworkTime + " connectionType:" + this.connectionType + " requestType:" + this.requestType + " firstScreenRenderTime:" + this.screenRenderTime + " firstScreenJSFExecuteTime:" + this.firstScreenJSFExecuteTime + " componentCount:" + this.componentCount + " JSTemplateSize:" + this.JSTemplateSize + " SDKInitTime:" + WXEnvironment.sSDKInitTime + " totalTime:" + this.totalTime + " JSLibVersion:" + this.JSLibVersion + " WXSDKVersion:" + this.WXSDKVersion + " pageName:" + this.pageName + " useScroller:" + this.useScroller;
        $jacocoInit()[99] = true;
        return str;
    }

    public String getErrMsg() {
        boolean[] $jacocoInit = $jacocoInit();
        String sb = this.mErrMsgBuilder.toString();
        $jacocoInit[100] = true;
        return sb;
    }

    public void appendErrMsg(CharSequence charSequence) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mErrMsgBuilder.append(charSequence);
        $jacocoInit[101] = true;
    }

    public void beforeInstanceRender(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.renderTimeOrigin = System.currentTimeMillis();
        $jacocoInit[102] = true;
        this.renderUnixTimeOrigin = WXUtils.getFixUnixTime();
        $jacocoInit[103] = true;
    }

    public void afterInstanceDestroy(String str) {
        $jacocoInit()[104] = true;
    }
}
