package com.taobao.weex.utils;

import android.support.annotation.Nullable;
import com.taobao.weex.common.WXErrorCode;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXExceptionUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public static String degradeUrl = "BundleUrlDefaultDegradeUrl";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6056930155643443591L, "com/taobao/weex/utils/WXExceptionUtils", 55);
        $jacocoData = a2;
        return a2;
    }

    public WXExceptionUtils() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[54] = true;
    }

    public static void commitCriticalExceptionRT(@Nullable String str, @Nullable WXErrorCode wXErrorCode, @Nullable String str2, @Nullable String str3, @Nullable Map<String, String> map) {
        boolean[] $jacocoInit = $jacocoInit();
        commitCriticalExceptionWithDefaultUrl("BundleUrlDefault", str, wXErrorCode, str2, str3, map);
        $jacocoInit[1] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ef A[LOOP:0: B:26:0x00e9->B:28:0x00ef, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0171  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void commitCriticalExceptionWithDefaultUrl(@android.support.annotation.Nullable java.lang.String r15, @android.support.annotation.Nullable java.lang.String r16, @android.support.annotation.Nullable com.taobao.weex.common.WXErrorCode r17, @android.support.annotation.Nullable java.lang.String r18, @android.support.annotation.Nullable java.lang.String r19, @android.support.annotation.Nullable java.util.Map<java.lang.String, java.lang.String> r20) {
        /*
            r0 = r16
            boolean[] r1 = $jacocoInit()
            com.taobao.weex.WXSDKManager r2 = com.taobao.weex.WXSDKManager.getInstance()
            com.taobao.weex.adapter.IWXJSExceptionAdapter r2 = r2.getIWXJSExceptionAdapter()
            r3 = 1
            r4 = 2
            r1[r4] = r3
            boolean r4 = android.text.TextUtils.isEmpty(r15)
            if (r4 == 0) goto L_0x001e
            java.lang.String r4 = "BundleUrlDefault"
            r5 = 3
            r1[r5] = r3
            goto L_0x0022
        L_0x001e:
            r4 = 4
            r1[r4] = r3
            r4 = r15
        L_0x0022:
            java.lang.String r5 = "InstanceIdDefalut"
            if (r20 == 0) goto L_0x002c
            r7 = 5
            r1[r7] = r3
            r14 = r20
            goto L_0x0067
        L_0x002c:
            r6 = 6
            r1[r6] = r3
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r7 = 7
            r1[r7] = r3
            java.lang.String r7 = "wxSdkInitStartTime"
            long r8 = com.taobao.weex.WXEnvironment.sSDKInitStart
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r6.put(r7, r8)
            r7 = 8
            r1[r7] = r3
            java.lang.String r7 = "wxSDKInitCostTime"
            long r8 = com.taobao.weex.WXEnvironment.sSDKInitTime
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r6.put(r7, r8)
            r7 = 9
            r1[r7] = r3
            java.lang.String r7 = "wxSDKCurExceptionTime"
            long r8 = java.lang.System.currentTimeMillis()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r6.put(r7, r8)
            r7 = 10
            r1[r7] = r3
            r14 = r6
        L_0x0067:
            boolean r6 = android.text.TextUtils.isEmpty(r16)
            r7 = 0
            if (r6 != 0) goto L_0x0191
            r5 = 11
            r1[r5] = r3
            com.taobao.weex.WXSDKManager r5 = com.taobao.weex.WXSDKManager.getInstance()
            java.util.Map r5 = r5.getAllInstanceMap()
            java.lang.Object r5 = r5.get(r0)
            r7 = r5
            com.taobao.weex.WXSDKInstance r7 = (com.taobao.weex.WXSDKInstance) r7
            if (r7 != 0) goto L_0x008b
            r5 = 12
            r1[r5] = r3
        L_0x0087:
            r9 = r0
            r10 = r4
            goto L_0x01d2
        L_0x008b:
            r4 = 13
            r1[r4] = r3
            com.taobao.weex.performance.WXInstanceApm r4 = r7.getApmForInstance()
            java.lang.String r4 = r4.reportPageName
            r5 = 14
            r1[r5] = r3
            java.lang.String r5 = "templateInfo"
            java.lang.String r6 = r7.getTemplateInfo()
            r14.put(r5, r6)
            r5 = 15
            r1[r5] = r3
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x00b1
            r4 = 16
            r1[r4] = r3
            goto L_0x00c2
        L_0x00b1:
            java.lang.String r5 = "default"
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x00be
            r5 = 17
            r1[r5] = r3
            goto L_0x00d9
        L_0x00be:
            r4 = 18
            r1[r4] = r3
        L_0x00c2:
            java.lang.String r4 = degradeUrl
            java.lang.String r5 = "BundleUrlDefaultDegradeUrl"
            boolean r4 = android.text.TextUtils.equals(r4, r5)
            if (r4 != 0) goto L_0x00d3
            java.lang.String r4 = degradeUrl
            r5 = 19
            r1[r5] = r3
            goto L_0x00d9
        L_0x00d3:
            java.lang.String r4 = com.taobao.weex.WXSDKInstance.requestUrl
            r5 = 20
            r1[r5] = r3
        L_0x00d9:
            java.util.Map r5 = r7.getContainerInfo()
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
            r6 = 21
            r1[r6] = r3
        L_0x00e9:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0109
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            r8 = 22
            r1[r8] = r3
            java.lang.Object r8 = r6.getKey()
            java.lang.Object r6 = r6.getValue()
            r14.put(r8, r6)
            r6 = 23
            r1[r6] = r3
            goto L_0x00e9
        L_0x0109:
            java.lang.String r5 = "wxStageList"
            com.taobao.weex.performance.WXInstanceExceptionRecord r6 = r7.getExceptionRecorder()
            java.lang.String r6 = r6.convertStageToStr()
            r14.put(r5, r6)
            r5 = 24
            r1[r5] = r3
            java.lang.String r5 = r7.getTemplate()
            if (r5 != 0) goto L_0x0127
            java.lang.String r5 = "has recycle by gc"
            r6 = 25
            r1[r6] = r3
            goto L_0x013e
        L_0x0127:
            int r6 = r5.length()
            r8 = 26
            r1[r8] = r3
            r8 = 0
            r9 = 300(0x12c, float:4.2E-43)
            int r6 = java.lang.Math.min(r6, r9)
            java.lang.String r5 = r5.substring(r8, r6)
            r6 = 27
            r1[r6] = r3
        L_0x013e:
            java.lang.String r6 = "wxTemplateOfBundle"
            r14.put(r6, r5)
            r5 = 28
            r1[r5] = r3
            com.taobao.weex.performance.WXInstanceExceptionRecord r5 = r7.getExceptionRecorder()
            java.lang.String r6 = "wxStartDownLoadBundle"
            java.lang.Long r5 = r5.getStageTime(r6)
            if (r5 == 0) goto L_0x0158
            r6 = 29
            r1[r6] = r3
            goto L_0x016a
        L_0x0158:
            r5 = 30
            r1[r5] = r3
            com.taobao.weex.performance.WXInstanceExceptionRecord r5 = r7.getExceptionRecorder()
            java.lang.String r6 = "wxRenderTimeOrigin"
            java.lang.Long r5 = r5.getStageTime(r6)
            r6 = 31
            r1[r6] = r3
        L_0x016a:
            if (r5 != 0) goto L_0x0171
            r5 = 32
            r1[r5] = r3
            goto L_0x018b
        L_0x0171:
            r6 = 33
            r1[r6] = r3
            java.lang.String r6 = "wxUseTime"
            long r8 = com.taobao.weex.utils.WXUtils.getFixUnixTime()
            long r10 = r5.longValue()
            long r8 = r8 - r10
            java.lang.String r5 = java.lang.String.valueOf(r8)
            r14.put(r6, r5)
            r5 = 34
            r1[r5] = r3
        L_0x018b:
            r5 = 35
            r1[r5] = r3
            goto L_0x0087
        L_0x0191:
            int r6 = r14.size()
            if (r6 > 0) goto L_0x019e
            r6 = 36
            r1[r6] = r3
        L_0x019b:
            r10 = r4
            r9 = r5
            goto L_0x01d2
        L_0x019e:
            r4 = 37
            r1[r4] = r3
            java.lang.String r4 = "weexUrl"
            java.lang.Object r4 = r14.get(r4)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x01bd
            java.lang.String r4 = "weexUrl"
            java.lang.Object r4 = r14.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            r6 = 38
            r1[r6] = r3
            goto L_0x01cd
        L_0x01bd:
            java.lang.String r4 = "bundleUrl"
            r6 = 39
            r1[r6] = r3
            java.lang.Object r4 = r14.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            r6 = 40
            r1[r6] = r3
        L_0x01cd:
            r6 = 41
            r1[r6] = r3
            goto L_0x019b
        L_0x01d2:
            java.lang.String r4 = "errorCode"
            java.lang.Object r4 = r14.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            r5 = 42
            r1[r5] = r3
            if (r4 != 0) goto L_0x01e5
            r4 = 43
            r1[r4] = r3
            goto L_0x01ff
        L_0x01e5:
            int r4 = r4.length()
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 > r5) goto L_0x01f2
            r4 = 44
            r1[r4] = r3
            goto L_0x01ff
        L_0x01f2:
            r4 = 45
            r1[r4] = r3
            java.lang.String r4 = "errorCode"
            r14.remove(r4)
            r4 = 46
            r1[r4] = r3
        L_0x01ff:
            com.taobao.weex.common.WXJSExceptionInfo r4 = new com.taobao.weex.common.WXJSExceptionInfo
            r8 = r4
            r11 = r17
            r12 = r18
            r13 = r19
            r8.<init>(r9, r10, r11, r12, r13, r14)
            if (r2 != 0) goto L_0x0212
            r2 = 47
            r1[r2] = r3
            goto L_0x021d
        L_0x0212:
            r5 = 48
            r1[r5] = r3
            r2.onJSException(r4)
            r2 = 49
            r1[r2] = r3
        L_0x021d:
            if (r7 != 0) goto L_0x0224
            r2 = 50
            r1[r2] = r3
            goto L_0x0233
        L_0x0224:
            r2 = 51
            r1[r2] = r3
            com.taobao.weex.performance.WXInstanceExceptionRecord r2 = r7.getExceptionRecorder()
            r2.recordErrorMsg(r4)
            r2 = 52
            r1[r2] = r3
        L_0x0233:
            com.taobao.weex.performance.WXAnalyzerDataTransfer.transferError(r4, r0)
            r0 = 53
            r1[r0] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXExceptionUtils.commitCriticalExceptionWithDefaultUrl(java.lang.String, java.lang.String, com.taobao.weex.common.WXErrorCode, java.lang.String, java.lang.String, java.util.Map):void");
    }
}
