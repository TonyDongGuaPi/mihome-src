package com.taobao.weex.ui.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.action.ActionInvokeMethod;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public final class WXDomModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ADD_RULE = "addRule";
    public static final String BATCH_BEGIN = "beginBatchMark";
    public static final String BATCH_END = "endBatchMark";
    public static final String GET_COMPONENT_DIRECTION = "getLayoutDirection";
    public static final String GET_COMPONENT_RECT = "getComponentRect";
    public static final String INVOKE_METHOD = "invokeMethod";
    public static final String[] METHODS = {SCROLL_TO_ELEMENT, ADD_RULE, GET_COMPONENT_RECT, INVOKE_METHOD, GET_COMPONENT_DIRECTION, BATCH_BEGIN, BATCH_END};
    public static final String SCROLL_TO_ELEMENT = "scrollToElement";
    public static final String UPDATE_COMPONENT_DATA = "updateComponentData";
    public static final String WXDOM = "dom";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8076657207075478942L, "com/taobao/weex/ui/module/WXDomModule", 79);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[78] = true;
    }

    public WXDomModule(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance = wXSDKInstance;
        $jacocoInit[0] = true;
    }

    public void callDomMethod(JSONObject jSONObject, long... jArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONObject == null) {
            $jacocoInit[1] = true;
            return;
        }
        $jacocoInit[2] = true;
        $jacocoInit[3] = true;
        callDomMethod((String) jSONObject.get("method"), (JSONArray) jSONObject.get("args"), jArr);
        $jacocoInit[4] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r13[24] = true;
        r11 = new com.taobao.weex.ui.action.ActionGetLayoutDirection(r10.mWXSDKInstance, r12.getString(0), r12.getString(1));
        r13[26] = true;
        r11.executeActionOnRender();
        r13[27] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0245, code lost:
        r13[25] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0249, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        com.taobao.weex.utils.WXLogUtils.e("Unknown dom action.");
        r13[65] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0251, code lost:
        r13[66] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b8, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b9, code lost:
        switch(r0) {
            case 0: goto L_0x0224;
            case 1: goto L_0x01d6;
            case 2: goto L_0x01ab;
            case 3: goto L_0x0184;
            case 4: goto L_0x0155;
            case 5: goto L_0x011b;
            case 6: goto L_0x00e8;
            case 7: goto L_0x00c0;
            default: goto L_0x00bc;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c4, code lost:
        if (r12.size() < 1) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c6, code lost:
        r11 = r12.getString(0);
        r13[61] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cf, code lost:
        r13[62] = true;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d4, code lost:
        r13[63] = true;
        new com.taobao.weex.ui.action.GraphicActionBatchEnd(r10.mWXSDKInstance, r11).executeActionOnRender();
        r13[64] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e8, code lost:
        if (r12 == null) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ea, code lost:
        r13[55] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f2, code lost:
        if (r12.size() < 1) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f4, code lost:
        r11 = r12.getString(0);
        r13[57] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00fd, code lost:
        r13[58] = true;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0102, code lost:
        r13[59] = true;
        new com.taobao.weex.ui.action.GraphicActionBatchBegin(r10.mWXSDKInstance, r11).executeActionOnRender();
        r13[60] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0116, code lost:
        r13[56] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011a, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011b, code lost:
        if (r12 != null) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r13[50] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0126, code lost:
        if (r12.size() < 3) goto L_0x014c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0128, code lost:
        r13[51] = true;
        new com.taobao.weex.ui.action.UpdateComponentDataAction(r10.mWXSDKInstance, r12.getString(0), com.taobao.weex.dom.binding.JSONUtils.toJSON(r12.get(1)), r12.getString(2)).executeAction();
        r13[54] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x014c, code lost:
        r13[52] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0150, code lost:
        r13[53] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0154, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0155, code lost:
        if (r12 == null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r13[46] = true;
        r11 = new com.taobao.weex.ui.action.ActionInvokeMethod(r10.mWXSDKInstance.getInstanceId(), r12.getString(0), r12.getString(1), r12.getJSONArray(2));
        r13[48] = true;
        r11.executeAction();
        r13[49] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x017f, code lost:
        r13[47] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0183, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0184, code lost:
        if (r12 == null) goto L_0x01a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r13[42] = true;
        r11 = new com.taobao.weex.ui.action.ActionGetComponentRect(r10.mWXSDKInstance, r12.getString(0), r12.getString(1));
        r13[44] = true;
        r11.executeActionOnRender();
        r13[45] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01a6, code lost:
        r13[43] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01aa, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ab, code lost:
        if (r12 == null) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        r13[38] = true;
        r11 = new com.taobao.weex.ui.action.ActionAddRule(r10.mWXSDKInstance.getInstanceId(), r12.getString(0), r12.getJSONObject(1));
        r13[40] = true;
        r11.executeAction();
        r13[41] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01d1, code lost:
        r13[39] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01d5, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01d6, code lost:
        if (r12 == null) goto L_0x021f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r13[28] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01e0, code lost:
        if (r12.size() < 1) goto L_0x01eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01e2, code lost:
        r11 = r12.getString(0);
        r13[30] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01eb, code lost:
        r13[31] = true;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01f0, code lost:
        r13[32] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01f8, code lost:
        if (r12.size() < 2) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01fa, code lost:
        r12 = r12.getJSONObject(1);
        r13[33] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0203, code lost:
        r13[34] = true;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0208, code lost:
        r13[35] = true;
        r0 = new com.taobao.weex.ui.action.GraphicActionScrollToElement(r10.mWXSDKInstance, r11, r12);
        r13[36] = true;
        r0.executeActionOnRender();
        r13[37] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x021f, code lost:
        r13[29] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0223, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0224, code lost:
        if (r12 == null) goto L_0x0245;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object callDomMethod(java.lang.String r11, com.alibaba.fastjson.JSONArray r12, long... r13) {
        /*
            r10 = this;
            boolean[] r13 = $jacocoInit()
            r0 = 6
            r1 = 0
            r2 = 1
            if (r11 == 0) goto L_0x027e
            r3 = 5
            r13[r3] = r2
            r4 = -1
            int r5 = r11.hashCode()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r6 = 3
            r7 = 7
            r8 = 2
            r9 = 0
            switch(r5) {
                case -1390247284: goto L_0x00a5;
                case -1148630211: goto L_0x0092;
                case -748746828: goto L_0x007f;
                case -658126983: goto L_0x006c;
                case -276465026: goto L_0x005a;
                case 588570827: goto L_0x0046;
                case 1653342206: goto L_0x0031;
                case 1875897663: goto L_0x001c;
                default: goto L_0x0018;
            }     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
        L_0x0018:
            r13[r7] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x001c:
            java.lang.String r0 = "getLayoutDirection"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x002a
            r11 = 8
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x002a:
            r11 = 9
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 0
            goto L_0x00b9
        L_0x0031:
            java.lang.String r0 = "updateComponentData"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x003f
            r11 = 18
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x003f:
            r11 = 19
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 5
            goto L_0x00b9
        L_0x0046:
            java.lang.String r0 = "getComponentRect"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x0054
            r11 = 14
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x0054:
            r11 = 15
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 3
            goto L_0x00b9
        L_0x005a:
            java.lang.String r3 = "beginBatchMark"
            boolean r11 = r11.equals(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x0067
            r11 = 20
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x0067:
            r11 = 21
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b9
        L_0x006c:
            java.lang.String r0 = "invokeMethod"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x0079
            r11 = 16
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x0079:
            r0 = 4
            r11 = 17
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b9
        L_0x007f:
            java.lang.String r0 = "scrollToElement"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x008c
            r11 = 10
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x008c:
            r11 = 11
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 1
            goto L_0x00b9
        L_0x0092:
            java.lang.String r0 = "addRule"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x009f
            r11 = 12
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x009f:
            r11 = 13
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 2
            goto L_0x00b9
        L_0x00a5:
            java.lang.String r0 = "endBatchMark"
            boolean r11 = r11.equals(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 != 0) goto L_0x00b2
            r11 = 22
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00b8
        L_0x00b2:
            r11 = 23
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 7
            goto L_0x00b9
        L_0x00b8:
            r0 = -1
        L_0x00b9:
            switch(r0) {
                case 0: goto L_0x0224;
                case 1: goto L_0x01d6;
                case 2: goto L_0x01ab;
                case 3: goto L_0x0184;
                case 4: goto L_0x0155;
                case 5: goto L_0x011b;
                case 6: goto L_0x00e8;
                case 7: goto L_0x00c0;
                default: goto L_0x00bc;
            }     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
        L_0x00bc:
            java.lang.String r11 = "Unknown dom action."
            goto L_0x024a
        L_0x00c0:
            int r11 = r12.size()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 < r2) goto L_0x00cf
            java.lang.String r11 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = 61
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x00d4
        L_0x00cf:
            r11 = 62
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = r1
        L_0x00d4:
            r12 = 63
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.GraphicActionBatchEnd r12 = new com.taobao.weex.ui.action.GraphicActionBatchEnd     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12.<init>(r0, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12.executeActionOnRender()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 64
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x00e8:
            if (r12 == 0) goto L_0x0116
            r11 = 55
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            int r11 = r12.size()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 < r2) goto L_0x00fd
            java.lang.String r11 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = 57
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0102
        L_0x00fd:
            r11 = 58
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = r1
        L_0x0102:
            r12 = 59
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.GraphicActionBatchBegin r12 = new com.taobao.weex.ui.action.GraphicActionBatchBegin     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12.<init>(r0, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12.executeActionOnRender()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 60
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x0116:
            r11 = 56
            r13[r11] = r2
            return r1
        L_0x011b:
            if (r12 != 0) goto L_0x0122
            r11 = 50
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0150
        L_0x0122:
            int r11 = r12.size()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 < r6) goto L_0x014c
            r11 = 51
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.UpdateComponentDataAction r11 = new com.taobao.weex.ui.action.UpdateComponentDataAction     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r3 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.Object r4 = r12.get(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.alibaba.fastjson.JSONObject r4 = com.taobao.weex.dom.binding.JSONUtils.toJSON(r4)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r12 = r12.getString(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.<init>(r0, r3, r4, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.executeAction()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 54
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x014c:
            r11 = 52
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
        L_0x0150:
            r11 = 53
            r13[r11] = r2
            return r1
        L_0x0155:
            if (r12 == 0) goto L_0x017f
            r11 = 46
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.ActionInvokeMethod r11 = new com.taobao.weex.ui.action.ActionInvokeMethod     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r0 = r0.getInstanceId()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r3 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r4 = r12.getString(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.alibaba.fastjson.JSONArray r12 = r12.getJSONArray(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.<init>(r0, r3, r4, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = 48
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.executeAction()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 49
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x017f:
            r11 = 47
            r13[r11] = r2
            return r1
        L_0x0184:
            if (r12 == 0) goto L_0x01a6
            r11 = 42
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.ActionGetComponentRect r11 = new com.taobao.weex.ui.action.ActionGetComponentRect     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r3 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r12 = r12.getString(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.<init>(r0, r3, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = 44
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.executeActionOnRender()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 45
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x01a6:
            r11 = 43
            r13[r11] = r2
            return r1
        L_0x01ab:
            if (r12 == 0) goto L_0x01d1
            r11 = 38
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.ActionAddRule r11 = new com.taobao.weex.ui.action.ActionAddRule     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r0 = r0.getInstanceId()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r3 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.alibaba.fastjson.JSONObject r12 = r12.getJSONObject(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.<init>(r0, r3, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = 40
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.executeAction()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 41
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x01d1:
            r11 = 39
            r13[r11] = r2
            return r1
        L_0x01d6:
            if (r12 == 0) goto L_0x021f
            r11 = 28
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            int r11 = r12.size()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r11 < r2) goto L_0x01eb
            java.lang.String r11 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 30
            r13[r0] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x01f0
        L_0x01eb:
            r11 = 31
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = r1
        L_0x01f0:
            r0 = 32
            r13[r0] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            int r0 = r12.size()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            if (r0 < r8) goto L_0x0203
            com.alibaba.fastjson.JSONObject r12 = r12.getJSONObject(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0 = 33
            r13[r0] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0208
        L_0x0203:
            r12 = 34
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = r1
        L_0x0208:
            r0 = 35
            r13[r0] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.GraphicActionScrollToElement r0 = new com.taobao.weex.ui.action.GraphicActionScrollToElement     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r3 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0.<init>(r3, r11, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 36
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r0.executeActionOnRender()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 37
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x021f:
            r11 = 29
            r13[r11] = r2
            return r1
        L_0x0224:
            if (r12 == 0) goto L_0x0245
            r11 = 24
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.ui.action.ActionGetLayoutDirection r11 = new com.taobao.weex.ui.action.ActionGetLayoutDirection     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            com.taobao.weex.WXSDKInstance r0 = r10.mWXSDKInstance     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r3 = r12.getString(r9)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            java.lang.String r12 = r12.getString(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.<init>(r0, r3, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r12 = 26
            r13[r12] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11.executeActionOnRender()     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 27
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            goto L_0x0251
        L_0x0245:
            r11 = 25
            r13[r11] = r2
            return r1
        L_0x024a:
            com.taobao.weex.utils.WXLogUtils.e(r11)     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
            r11 = 65
            r13[r11] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0264, ClassCastException -> 0x0256 }
        L_0x0251:
            r11 = 66
            r13[r11] = r2
            goto L_0x0279
        L_0x0256:
            r11 = 70
            r13[r11] = r2
            java.lang.String r11 = "Dom module call arguments format error!!"
            com.taobao.weex.utils.WXLogUtils.e(r11)
            r11 = 71
            r13[r11] = r2
            goto L_0x0279
        L_0x0264:
            r11 = move-exception
            r12 = 67
            r13[r12] = r2
            r11.printStackTrace()
            r11 = 68
            r13[r11] = r2
            java.lang.String r11 = "Dom module call miss arguments."
            com.taobao.weex.utils.WXLogUtils.e(r11)
            r11 = 69
            r13[r11] = r2
        L_0x0279:
            r11 = 72
            r13[r11] = r2
            return r1
        L_0x027e:
            r13[r0] = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXDomModule.callDomMethod(java.lang.String, com.alibaba.fastjson.JSONArray, long[]):java.lang.Object");
    }

    public void invokeMethod(String str, String str2, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[73] = true;
        } else if (str2 == null) {
            $jacocoInit[74] = true;
        } else {
            ActionInvokeMethod actionInvokeMethod = new ActionInvokeMethod(this.mWXSDKInstance.getInstanceId(), str, str2, jSONArray);
            $jacocoInit[76] = true;
            actionInvokeMethod.executeAction();
            $jacocoInit[77] = true;
            return;
        }
        $jacocoInit[75] = true;
    }
}
