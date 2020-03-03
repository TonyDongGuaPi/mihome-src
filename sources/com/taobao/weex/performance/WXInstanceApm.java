package com.taobao.weex.performance;

import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXUtils;
import com.xiaomi.payment.data.AnalyticsConstants;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXInstanceApm {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String KEY_PAGE_ANIM_BACK_NUM = "wxAnimationInBackCount";
    public static final String KEY_PAGE_PROPERTIES_BIZ_ID = "wxBizID";
    public static final String KEY_PAGE_PROPERTIES_BUBDLE_URL = "wxBundleUrl";
    public static final String KEY_PAGE_PROPERTIES_BUNDLE_TYPE = "wxBundleType";
    public static final String KEY_PAGE_PROPERTIES_CACHE_INFO = "wxZCacheInfo";
    public static final String KEY_PAGE_PROPERTIES_CACHE_TYPE = "wxCacheType";
    public static final String KEY_PAGE_PROPERTIES_CONTAINER_NAME = "wxContainerName";
    public static final String KEY_PAGE_PROPERTIES_INSTANCE_TYPE = "wxInstanceType";
    public static final String KEY_PAGE_PROPERTIES_JSLIB_VERSION = "wxJSLibVersion";
    public static final String KEY_PAGE_PROPERTIES_JS_FM_INI = "wxJsFrameworkInit";
    public static final String KEY_PAGE_PROPERTIES_PARENT_PAGE = "wxParentPage";
    public static final String KEY_PAGE_PROPERTIES_RENDER_TYPE = "wxRenderType";
    public static final String KEY_PAGE_PROPERTIES_REQUEST_TYPE = "wxRequestType";
    public static final String KEY_PAGE_PROPERTIES_WEEX_VERSION = "wxSDKVersion";
    public static final String KEY_PAGE_STAGES_CREATE_FINISH = "wxJSBundleCreateFinish";
    public static final String KEY_PAGE_STAGES_DESTROY = "wxDestroy";
    public static final String KEY_PAGE_STAGES_DOWN_BUNDLE_END = "wxEndDownLoadBundle";
    public static final String KEY_PAGE_STAGES_DOWN_BUNDLE_START = "wxStartDownLoadBundle";
    public static final String KEY_PAGE_STAGES_FIRST_INTERACTION_VIEW = "wxFirstInteractionView";
    public static final String KEY_PAGE_STAGES_FSRENDER = "wxFsRender";
    public static final String KEY_PAGE_STAGES_INTERACTION = "wxInteraction";
    public static final String KEY_PAGE_STAGES_LOAD_BUNDLE_END = "wxEndLoadBundle";
    public static final String KEY_PAGE_STAGES_LOAD_BUNDLE_START = "wxStartLoadBundle";
    public static final String KEY_PAGE_STAGES_NEW_FSRENDER = "wxNewFsRender";
    public static final String KEY_PAGE_STAGES_RENDER_ORGIGIN = "wxRenderTimeOrigin";
    public static final String KEY_PAGE_STATS_ACTUAL_DOWNLOAD_TIME = "wxActualNetworkTime";
    public static final String KEY_PAGE_STATS_BODY_RATIO = "wxBodyRatio";
    public static final String KEY_PAGE_STATS_BUNDLE_SIZE = "wxBundleSize";
    public static final String KEY_PAGE_STATS_CELL_DATA_UN_RECYCLE_NUM = "wxCellDataUnRecycleCount";
    public static final String KEY_PAGE_STATS_CELL_EXCEED_NUM = "wxCellExceedNum";
    public static final String KEY_PAGE_STATS_CELL_UN_RE_USE_NUM = "wxCellUnReUseCount";
    public static final String KEY_PAGE_STATS_EMBED_COUNT = "wxEmbedCount";
    public static final String KEY_PAGE_STATS_FS_CALL_EVENT_NUM = "wxFSCallEventTotalNum";
    public static final String KEY_PAGE_STATS_FS_CALL_JS_NUM = "wxFSCallJsTotalNum";
    public static final String KEY_PAGE_STATS_FS_CALL_JS_TIME = "wxFSCallJsTotalTime";
    public static final String KEY_PAGE_STATS_FS_CALL_NATIVE_NUM = "wxFSCallNativeTotalNum";
    public static final String KEY_PAGE_STATS_FS_CALL_NATIVE_TIME = "wxFSCallNativeTotalTime";
    public static final String KEY_PAGE_STATS_FS_REQUEST_NUM = "wxFSRequestNum";
    public static final String KEY_PAGE_STATS_FS_TIMER_NUM = "wxFSTimerCount";
    public static final String KEY_PAGE_STATS_IMG_LOAD_FAIL_NUM = "wxImgLoadFailCount";
    public static final String KEY_PAGE_STATS_IMG_LOAD_NUM = "wxImgLoadCount";
    public static final String KEY_PAGE_STATS_IMG_LOAD_SUCCESS_NUM = "wxImgLoadSuccessCount";
    public static final String KEY_PAGE_STATS_IMG_UN_RECYCLE_NUM = "wxImgUnRecycleCount";
    public static final String KEY_PAGE_STATS_I_ALL_VIEW_COUNT = "wxInteractionAllViewCount";
    public static final String KEY_PAGE_STATS_I_COMPONENT_CREATE_COUNT = "wxInteractionComponentCreateCount";
    public static final String KEY_PAGE_STATS_I_SCREEN_VIEW_COUNT = "wxInteractionScreenViewCount";
    public static final String KEY_PAGE_STATS_JSLIB_INIT_TIME = "wxJSLibInitTime";
    public static final String KEY_PAGE_STATS_LARGE_IMG_COUNT = "wxLargeImgMaxCount";
    public static final String KEY_PAGE_STATS_MAX_COMPONENT_NUM = "wxMaxComponentCount";
    public static final String KEY_PAGE_STATS_MAX_DEEP_DOM = "wxMaxDeepVDomLayer";
    public static final String KEY_PAGE_STATS_MAX_DEEP_VIEW = "wxMaxDeepViewLayer";
    public static final String KEY_PAGE_STATS_NET_FAIL_NUM = "wxNetworkRequestFailCount";
    public static final String KEY_PAGE_STATS_NET_NUM = "wxNetworkRequestCount";
    public static final String KEY_PAGE_STATS_NET_SUCCESS_NUM = "wxNetworkRequestSuccessCount";
    public static final String KEY_PAGE_STATS_SCROLLER_NUM = "wxScrollerCount";
    public static final String KEY_PAGE_STATS_WRONG_IMG_SIZE_COUNT = "wxWrongImgSizeCount";
    public static final String KEY_PAGE_TIMER_BACK_NUM = "wxTimerInBackCount";
    public static final String KEY_PROPERTIES_ERROR_CODE = "wxErrorCode";
    public static final String VALUE_ERROR_CODE_DEFAULT = "0";
    public static final String WEEX_PAGE_TOPIC = "weex_page";
    private IWXApmMonitorAdapter apmInstance;
    public final Map<String, Object> extInfo;
    public boolean forceStopRecordInteraction = false;
    private boolean hasRecordFistInteractionView = false;
    public boolean hasReportLayerOverDraw = false;
    public Rect instanceRect;
    private boolean isFSEnd;
    private boolean mEnd = false;
    private boolean mHasInit = false;
    private String mInstanceId;
    private Map<String, Double> recordStatsMap;
    public String reportPageName;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7120290604818425161L, "com/taobao/weex/performance/WXInstanceApm", 166);
        $jacocoData = a2;
        return a2;
    }

    public WXInstanceApm(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstanceId = str;
        $jacocoInit[0] = true;
        this.extInfo = new ConcurrentHashMap();
        $jacocoInit[1] = true;
        IApmGenerator apmGenerater = WXSDKManager.getInstance().getApmGenerater();
        if (apmGenerater == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            this.apmInstance = apmGenerater.generateApmInstance(WEEX_PAGE_TOPIC);
            $jacocoInit[4] = true;
            this.recordStatsMap = new ConcurrentHashMap();
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    public void onEvent(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[7] = true;
            return;
        }
        this.apmInstance.onEvent(str, obj);
        $jacocoInit[8] = true;
    }

    public void onStage(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        long fixUnixTime = WXUtils.getFixUnixTime();
        $jacocoInit[9] = true;
        onStageWithTime(str, fixUnixTime);
        $jacocoInit[10] = true;
    }

    public void onStageWithTime(String str, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getAllInstanceMap().get(this.mInstanceId);
        if (wXSDKInstance == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            wXSDKInstance.getExceptionRecorder().recordStage(str, j);
            $jacocoInit[13] = true;
        }
        if (this.mEnd) {
            $jacocoInit[14] = true;
            return;
        }
        if (!WXAnalyzerDataTransfer.isOpenPerformance) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            WXAnalyzerDataTransfer.transferPerformance(this.mInstanceId, AnalyticsConstants.bH, str, Long.valueOf(j));
            $jacocoInit[17] = true;
        }
        if (this.apmInstance == null) {
            $jacocoInit[18] = true;
            return;
        }
        this.apmInstance.onStage(str, j);
        $jacocoInit[19] = true;
    }

    public void addProperty(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEnd) {
            $jacocoInit[20] = true;
            return;
        }
        if (!WXAnalyzerDataTransfer.isOpenPerformance) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            WXAnalyzerDataTransfer.transferPerformance(this.mInstanceId, "properties", str, obj);
            $jacocoInit[23] = true;
        }
        if (this.apmInstance == null) {
            $jacocoInit[24] = true;
            return;
        }
        this.apmInstance.addProperty(str, obj);
        $jacocoInit[25] = true;
    }

    public void addStats(String str, double d) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEnd) {
            $jacocoInit[26] = true;
            return;
        }
        if (!WXAnalyzerDataTransfer.isOpenPerformance) {
            $jacocoInit[27] = true;
        } else {
            $jacocoInit[28] = true;
            WXAnalyzerDataTransfer.transferPerformance(this.mInstanceId, "stats", str, Double.valueOf(d));
            $jacocoInit[29] = true;
        }
        if (this.apmInstance == null) {
            $jacocoInit[30] = true;
            return;
        }
        this.apmInstance.addStats(str, d);
        $jacocoInit[31] = true;
    }

    public boolean hasInit() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mHasInit;
        $jacocoInit[32] = true;
        return z;
    }

    public void doInit() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHasInit) {
            $jacocoInit[33] = true;
            return;
        }
        this.mHasInit = true;
        if (this.apmInstance == null) {
            $jacocoInit[34] = true;
            return;
        }
        this.apmInstance.onStart(this.mInstanceId);
        $jacocoInit[35] = true;
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getAllInstanceMap().get(this.mInstanceId);
        $jacocoInit[36] = true;
        if (wXSDKInstance == null) {
            str = "unKnowUrl";
            $jacocoInit[37] = true;
        } else {
            str = wXSDKInstance.getBundleUrl();
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
        addProperty(KEY_PAGE_PROPERTIES_BUBDLE_URL, str);
        $jacocoInit[40] = true;
        addProperty(KEY_PROPERTIES_ERROR_CODE, "0");
        $jacocoInit[41] = true;
        addProperty(KEY_PAGE_PROPERTIES_JSLIB_VERSION, WXEnvironment.JS_LIB_SDK_VERSION);
        $jacocoInit[42] = true;
        addProperty(KEY_PAGE_PROPERTIES_WEEX_VERSION, WXEnvironment.WXSDK_VERSION);
        $jacocoInit[43] = true;
        if (wXSDKInstance == null) {
            $jacocoInit[44] = true;
        } else {
            if (wXSDKInstance.getRenderStrategy() == WXRenderStrategy.DATA_RENDER) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                if (wXSDKInstance.getRenderStrategy() != WXRenderStrategy.DATA_RENDER_BINARY) {
                    $jacocoInit[47] = true;
                } else {
                    $jacocoInit[48] = true;
                }
            }
            addProperty(KEY_PAGE_PROPERTIES_RENDER_TYPE, WXEnvironment.EAGLE);
            $jacocoInit[49] = true;
        }
        if (wXSDKInstance == null) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            $jacocoInit[52] = true;
            for (Map.Entry next : wXSDKInstance.getContainerInfo().entrySet()) {
                $jacocoInit[54] = true;
                addProperty((String) next.getKey(), next.getValue());
                $jacocoInit[55] = true;
            }
            $jacocoInit[53] = true;
        }
        $jacocoInit[56] = true;
    }

    public void setPageName(String str) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[57] = true;
        } else {
            $jacocoInit[58] = true;
            WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getAllInstanceMap().get(this.mInstanceId);
            if (wXSDKInstance == null) {
                $jacocoInit[59] = true;
            } else {
                $jacocoInit[60] = true;
                str = wXSDKInstance.getContainerInfo().get(KEY_PAGE_PROPERTIES_CONTAINER_NAME);
                $jacocoInit[61] = true;
            }
        }
        if (this.apmInstance == null) {
            $jacocoInit[62] = true;
        } else {
            str = this.apmInstance.parseReportUrl(str);
            $jacocoInit[63] = true;
        }
        this.reportPageName = str;
        $jacocoInit[64] = true;
        if (TextUtils.isEmpty(this.reportPageName)) {
            str2 = "emptyPageName";
            $jacocoInit[65] = true;
        } else {
            str2 = this.reportPageName;
            $jacocoInit[66] = true;
        }
        this.reportPageName = str2;
        $jacocoInit[67] = true;
        addProperty(KEY_PAGE_PROPERTIES_BIZ_ID, this.reportPageName);
        $jacocoInit[68] = true;
    }

    public void onAppear() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[69] = true;
            return;
        }
        this.apmInstance.onAppear();
        $jacocoInit[70] = true;
    }

    public void onDisAppear() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[71] = true;
            return;
        }
        this.apmInstance.onDisappear();
        $jacocoInit[72] = true;
    }

    public void onEnd() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[73] = true;
        } else if (this.mEnd) {
            $jacocoInit[74] = true;
        } else {
            onStage(KEY_PAGE_STAGES_DESTROY);
            $jacocoInit[76] = true;
            this.apmInstance.onEnd();
            this.mEnd = true;
            $jacocoInit[77] = true;
            return;
        }
        $jacocoInit[75] = true;
    }

    public void arriveNewFsRenderTime() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[78] = true;
            return;
        }
        onStage(KEY_PAGE_STAGES_NEW_FSRENDER);
        $jacocoInit[79] = true;
    }

    public void arriveFSRenderTime() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[80] = true;
            return;
        }
        this.isFSEnd = true;
        $jacocoInit[81] = true;
        onStage(KEY_PAGE_STAGES_FSRENDER);
        $jacocoInit[82] = true;
    }

    public void arriveInteraction(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[83] = true;
        } else if (wXComponent == null) {
            $jacocoInit[84] = true;
        } else if (wXComponent.getInstance() == null) {
            $jacocoInit[85] = true;
        } else {
            if (!WXAnalyzerDataTransfer.isOpenPerformance) {
                $jacocoInit[87] = true;
            } else {
                $jacocoInit[88] = true;
                WXAnalyzerDataTransfer.transferInteractionInfo(wXComponent);
                $jacocoInit[89] = true;
            }
            if (this.apmInstance == null) {
                $jacocoInit[90] = true;
                return;
            }
            WXPerformance wXPerformance = wXComponent.getInstance().getWXPerformance();
            if (wXPerformance == null) {
                $jacocoInit[91] = true;
                return;
            }
            long fixUnixTime = WXUtils.getFixUnixTime();
            $jacocoInit[92] = true;
            if (!WXAnalyzerDataTransfer.isInteractionLogOpen()) {
                $jacocoInit[93] = true;
            } else {
                $jacocoInit[94] = true;
                StringBuilder sb = new StringBuilder();
                sb.append("[client][wxinteraction]");
                $jacocoInit[95] = true;
                sb.append(wXComponent.getInstance().getInstanceId());
                sb.append(",");
                $jacocoInit[96] = true;
                sb.append(wXComponent.getComponentType());
                sb.append(",");
                $jacocoInit[97] = true;
                sb.append(wXComponent.getRef());
                sb.append(",");
                $jacocoInit[98] = true;
                sb.append(wXComponent.getStyles());
                sb.append(",");
                $jacocoInit[99] = true;
                sb.append(wXComponent.getAttrs());
                String sb2 = sb.toString();
                $jacocoInit[100] = true;
                Log.d(WXAnalyzerDataTransfer.INTERACTION_TAG, sb2);
                $jacocoInit[101] = true;
            }
            if (this.hasRecordFistInteractionView) {
                $jacocoInit[102] = true;
            } else {
                $jacocoInit[103] = true;
                onStage(KEY_PAGE_STAGES_FIRST_INTERACTION_VIEW);
                this.hasRecordFistInteractionView = true;
                $jacocoInit[104] = true;
            }
            if (this.forceStopRecordInteraction) {
                $jacocoInit[105] = true;
                return;
            }
            wXPerformance.interactionTime = fixUnixTime - wXPerformance.renderUnixTimeOrigin;
            $jacocoInit[106] = true;
            onStageWithTime(KEY_PAGE_STAGES_INTERACTION, fixUnixTime);
            $jacocoInit[107] = true;
            updateDiffStats(KEY_PAGE_STATS_I_SCREEN_VIEW_COUNT, 1.0d);
            $jacocoInit[108] = true;
            updateMaxStats(KEY_PAGE_STATS_I_ALL_VIEW_COUNT, (double) wXPerformance.localInteractionViewAddCount);
            $jacocoInit[109] = true;
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(this.mInstanceId);
            if (sDKInstance == null) {
                $jacocoInit[110] = true;
            } else {
                $jacocoInit[111] = true;
                updateMaxStats(KEY_PAGE_STATS_I_COMPONENT_CREATE_COUNT, (double) sDKInstance.getWXPerformance().componentCount);
                $jacocoInit[112] = true;
            }
            $jacocoInit[113] = true;
            return;
        }
        $jacocoInit[86] = true;
    }

    public void updateFSDiffStats(String str, double d) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[114] = true;
        } else if (this.isFSEnd) {
            $jacocoInit[115] = true;
        } else {
            updateDiffStats(str, d);
            $jacocoInit[117] = true;
            return;
        }
        $jacocoInit[116] = true;
    }

    public void updateDiffStats(String str, double d) {
        Double d2;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[118] = true;
            return;
        }
        if (this.recordStatsMap.containsKey(str)) {
            d2 = this.recordStatsMap.get(str);
            $jacocoInit[119] = true;
        } else {
            d2 = Double.valueOf(0.0d);
            $jacocoInit[120] = true;
        }
        if (d2 == null) {
            $jacocoInit[121] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_ERR_HASH_MAP_TMP;
            WXExceptionUtils.commitCriticalExceptionRT("", wXErrorCode, "updateDiffStats", "key : " + str, (Map<String, String>) null);
            $jacocoInit[122] = true;
            return;
        }
        double doubleValue = d2.doubleValue() + d;
        $jacocoInit[123] = true;
        this.recordStatsMap.put(str, Double.valueOf(doubleValue));
        $jacocoInit[124] = true;
        addStats(str, doubleValue);
        $jacocoInit[125] = true;
    }

    public void updateMaxStats(String str, double d) {
        Double d2;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[126] = true;
            return;
        }
        if (this.recordStatsMap.containsKey(str)) {
            d2 = this.recordStatsMap.get(str);
            $jacocoInit[127] = true;
        } else {
            d2 = Double.valueOf(0.0d);
            $jacocoInit[128] = true;
        }
        if (d2 == null) {
            $jacocoInit[129] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_ERR_HASH_MAP_TMP;
            WXExceptionUtils.commitCriticalExceptionRT("", wXErrorCode, "updateMaxStats", "key : " + str, (Map<String, String>) null);
            $jacocoInit[130] = true;
            return;
        }
        if (d2.doubleValue() >= d) {
            $jacocoInit[131] = true;
        } else {
            $jacocoInit[132] = true;
            Double valueOf = Double.valueOf(d);
            $jacocoInit[133] = true;
            this.recordStatsMap.put(str, Double.valueOf(d));
            $jacocoInit[134] = true;
            addStats(str, valueOf.doubleValue());
            $jacocoInit[135] = true;
        }
        $jacocoInit[136] = true;
    }

    public void updateRecordInfo(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.apmInstance == null) {
            $jacocoInit[137] = true;
        } else if (map == null) {
            $jacocoInit[138] = true;
        } else {
            addPropeyFromExtParms(KEY_PAGE_PROPERTIES_REQUEST_TYPE, KEY_PAGE_PROPERTIES_REQUEST_TYPE, map);
            $jacocoInit[140] = true;
            addPropeyFromExtParms(WXPerformance.CACHE_TYPE, KEY_PAGE_PROPERTIES_CACHE_TYPE, map);
            $jacocoInit[141] = true;
            addPropeyFromExtParms("zCacheInfo", KEY_PAGE_PROPERTIES_CACHE_INFO, map);
            $jacocoInit[142] = true;
            addStats(KEY_PAGE_STATS_JSLIB_INIT_TIME, (double) WXEnvironment.sJSLibInitTime);
            $jacocoInit[143] = true;
            addProperty(KEY_PAGE_PROPERTIES_JS_FM_INI, Boolean.valueOf(WXEnvironment.JsFrameworkInit));
            $jacocoInit[144] = true;
            Object obj = map.get("actualNetworkTime");
            if (!(obj instanceof Long)) {
                $jacocoInit[145] = true;
            } else {
                $jacocoInit[146] = true;
                updateDiffStats(KEY_PAGE_STATS_ACTUAL_DOWNLOAD_TIME, ((Long) obj).doubleValue());
                $jacocoInit[147] = true;
            }
            $jacocoInit[148] = true;
            return;
        }
        $jacocoInit[139] = true;
    }

    private void addPropeyFromExtParms(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = map.get(str);
        if (!(obj instanceof String)) {
            $jacocoInit[149] = true;
        } else {
            $jacocoInit[150] = true;
            addProperty(str2, obj);
            $jacocoInit[151] = true;
        }
        $jacocoInit[152] = true;
    }

    public void actionNetRequest() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.isFSEnd) {
            $jacocoInit[153] = true;
        } else {
            $jacocoInit[154] = true;
            updateFSDiffStats(KEY_PAGE_STATS_FS_REQUEST_NUM, 1.0d);
            $jacocoInit[155] = true;
        }
        updateDiffStats(KEY_PAGE_STATS_NET_NUM, 1.0d);
        $jacocoInit[156] = true;
    }

    public void actionNetResult(boolean z, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            $jacocoInit[157] = true;
            updateDiffStats(KEY_PAGE_STATS_NET_SUCCESS_NUM, 1.0d);
            $jacocoInit[158] = true;
        } else {
            updateDiffStats(KEY_PAGE_STATS_NET_FAIL_NUM, 1.0d);
            $jacocoInit[159] = true;
        }
        $jacocoInit[160] = true;
    }

    public void actionLoadImg() {
        boolean[] $jacocoInit = $jacocoInit();
        updateDiffStats(KEY_PAGE_STATS_IMG_LOAD_NUM, 1.0d);
        $jacocoInit[161] = true;
    }

    public void actionLoadImgResult(boolean z, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            $jacocoInit[162] = true;
            updateDiffStats(KEY_PAGE_STATS_IMG_LOAD_SUCCESS_NUM, 1.0d);
            $jacocoInit[163] = true;
        } else {
            updateDiffStats(KEY_PAGE_STATS_IMG_LOAD_FAIL_NUM, 1.0d);
            $jacocoInit[164] = true;
        }
        $jacocoInit[165] = true;
    }
}
