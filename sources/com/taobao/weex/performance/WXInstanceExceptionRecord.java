package com.taobao.weex.performance;

import android.text.TextUtils;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXJSExceptionInfo;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.WXUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXInstanceExceptionRecord {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String KEY_EXP_STAGE_LIST = "wxStageList";
    public static boolean isReportWriteScreen = false;
    public static int sErrorMsgSizeLimit = 5;
    public final List<String> errorList;
    public final AtomicBoolean hasAddView;
    public final AtomicBoolean hasDegrade;
    public final String instanceId;
    public boolean isDownLoadBundleFailed = false;
    private boolean mBeginRender = false;
    private boolean mHasReportScreenEmpty = false;
    private final Map<String, Long> mStageMap;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8505202412934546917L, "com/taobao/weex/performance/WXInstanceExceptionRecord", 70);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[69] = true;
    }

    public WXInstanceExceptionRecord(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.instanceId = str;
        $jacocoInit[0] = true;
        this.mStageMap = new ConcurrentHashMap();
        $jacocoInit[1] = true;
        this.errorList = new CopyOnWriteArrayList();
        $jacocoInit[2] = true;
        this.hasAddView = new AtomicBoolean(false);
        $jacocoInit[3] = true;
        this.hasDegrade = new AtomicBoolean(false);
        $jacocoInit[4] = true;
    }

    public void recordErrorMsg(WXJSExceptionInfo wXJSExceptionInfo) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isReportWriteScreen) {
            $jacocoInit[5] = true;
        } else if (wXJSExceptionInfo == null) {
            $jacocoInit[6] = true;
        } else if (wXJSExceptionInfo.getErrCode().getErrorType() == WXErrorCode.ErrorType.RENDER_ERROR) {
            this.mHasReportScreenEmpty = true;
            $jacocoInit[7] = true;
        } else {
            recordReportErrorMsg(wXJSExceptionInfo.toString());
            $jacocoInit[8] = true;
        }
    }

    public void recordReportErrorMsg(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isReportWriteScreen) {
            $jacocoInit[9] = true;
            return;
        }
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[10] = true;
        } else if (this.hasAddView.get()) {
            $jacocoInit[11] = true;
        } else if (this.hasDegrade.get()) {
            $jacocoInit[12] = true;
        } else {
            if (this.errorList.size() <= sErrorMsgSizeLimit) {
                $jacocoInit[14] = true;
            } else {
                $jacocoInit[15] = true;
                this.errorList.remove(0);
                $jacocoInit[16] = true;
            }
            List<String> list = this.errorList;
            StringBuilder sb = new StringBuilder();
            $jacocoInit[17] = true;
            sb.append("time ");
            sb.append(System.currentTimeMillis());
            $jacocoInit[18] = true;
            sb.append(",msg ");
            sb.append(str);
            String sb2 = sb.toString();
            $jacocoInit[19] = true;
            list.add(sb2);
            $jacocoInit[20] = true;
            return;
        }
        $jacocoInit[13] = true;
    }

    public void recordStage(String str, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXInstanceApm.KEY_PAGE_STAGES_RENDER_ORGIGIN.equals(str)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            if (WXInstanceApm.KEY_PAGE_STAGES_DOWN_BUNDLE_START.equals(str)) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                if (!WXInstanceApm.KEY_PAGE_STAGES_LOAD_BUNDLE_END.equals(str)) {
                    $jacocoInit[25] = true;
                    this.mStageMap.put(str, Long.valueOf(j));
                    $jacocoInit[28] = true;
                }
                $jacocoInit[26] = true;
            }
        }
        setBeginRender(true);
        $jacocoInit[27] = true;
        this.mStageMap.put(str, Long.valueOf(j));
        $jacocoInit[28] = true;
    }

    public void setBeginRender(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBeginRender = true;
        $jacocoInit[29] = true;
    }

    public String convertStageToStr() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStageMap.isEmpty()) {
            $jacocoInit[30] = true;
            return "noStageRecord";
        }
        ArrayList<Map.Entry> arrayList = new ArrayList<>(this.mStageMap.entrySet());
        $jacocoInit[31] = true;
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Long>>(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXInstanceExceptionRecord this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-3495272079319279603L, "com/taobao/weex/performance/WXInstanceExceptionRecord$1", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                boolean[] $jacocoInit = $jacocoInit();
                int compare = compare((Map.Entry<String, Long>) (Map.Entry) obj, (Map.Entry<String, Long>) (Map.Entry) obj2);
                $jacocoInit[2] = true;
                return compare;
            }

            public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                boolean[] $jacocoInit = $jacocoInit();
                int longValue = (int) (entry.getValue().longValue() - entry2.getValue().longValue());
                $jacocoInit[1] = true;
                return longValue;
            }
        });
        $jacocoInit[32] = true;
        StringBuilder sb = new StringBuilder();
        $jacocoInit[33] = true;
        $jacocoInit[34] = true;
        for (Map.Entry entry : arrayList) {
            $jacocoInit[35] = true;
            sb.append((String) entry.getKey());
            sb.append(Operators.CONDITION_IF_MIDDLE);
            sb.append(entry.getValue());
            sb.append("->");
            $jacocoInit[36] = true;
        }
        String sb2 = sb.toString();
        $jacocoInit[37] = true;
        return sb2;
    }

    private String convertExceptionListToString() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.errorList.isEmpty()) {
            $jacocoInit[38] = true;
            return "";
        }
        StringBuilder sb = new StringBuilder();
        $jacocoInit[39] = true;
        $jacocoInit[40] = true;
        for (String append : this.errorList) {
            $jacocoInit[41] = true;
            sb.append("error_");
            sb.append(0);
            sb.append(": ");
            sb.append(append);
            sb.append("--->");
            $jacocoInit[42] = true;
        }
        String sb2 = sb.toString();
        $jacocoInit[43] = true;
        return sb2;
    }

    public void checkEmptyScreenAndReport() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isReportWriteScreen) {
            $jacocoInit[44] = true;
            return;
        }
        if (this.isDownLoadBundleFailed) {
            $jacocoInit[45] = true;
        } else if (!this.mBeginRender) {
            $jacocoInit[46] = true;
        } else if (this.mHasReportScreenEmpty) {
            $jacocoInit[47] = true;
        } else if (this.hasAddView.get()) {
            $jacocoInit[48] = true;
        } else if (this.hasDegrade.get()) {
            $jacocoInit[49] = true;
        } else if (!this.mStageMap.containsKey(WXInstanceApm.KEY_PAGE_STAGES_CREATE_FINISH)) {
            $jacocoInit[51] = true;
            return;
        } else {
            Long l = this.mStageMap.get(WXInstanceApm.KEY_PAGE_STAGES_LOAD_BUNDLE_END);
            if (l == null) {
                $jacocoInit[52] = true;
                return;
            }
            long fixUnixTime = WXUtils.getFixUnixTime();
            $jacocoInit[53] = true;
            long longValue = fixUnixTime - l.longValue();
            if (longValue <= 4000) {
                $jacocoInit[54] = true;
                return;
            }
            if (this.errorList.isEmpty()) {
                $jacocoInit[55] = true;
            } else {
                "whiteScreen :history exception :" + convertExceptionListToString();
                $jacocoInit[56] = true;
            }
            HashMap hashMap = new HashMap(4);
            $jacocoInit[57] = true;
            hashMap.put("wxBeginRender", String.valueOf(this.mBeginRender));
            $jacocoInit[58] = true;
            hashMap.put("wxHasAddView", String.valueOf(this.hasAddView.get()));
            $jacocoInit[59] = true;
            hashMap.put("wxHasDegrade", String.valueOf(this.hasDegrade.get()));
            $jacocoInit[60] = true;
            hashMap.put("wxHasReportScreenEmpty", String.valueOf(this.mHasReportScreenEmpty));
            $jacocoInit[61] = true;
            hashMap.put("wxJSExecTime", String.valueOf(longValue));
            $jacocoInit[62] = true;
            return;
        }
        $jacocoInit[50] = true;
    }

    public Long getStageTime(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Long l = this.mStageMap.get(str);
        $jacocoInit[63] = true;
        return l;
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = new StringBuilder();
        $jacocoInit[64] = true;
        sb.append(super.toString());
        $jacocoInit[65] = true;
        sb.append("wxStageList :");
        sb.append(convertStageToStr());
        $jacocoInit[66] = true;
        sb.append("wxErrorList :");
        sb.append(convertExceptionListToString());
        $jacocoInit[67] = true;
        String sb2 = sb.toString();
        $jacocoInit[68] = true;
        return sb2;
    }
}
