package com.taobao.weex.common;

import com.taobao.weex.WXEnvironment;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXJSExceptionInfo {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String mBundleUrl;
    private WXErrorCode mErrCode;
    private String mException;
    private Map<String, String> mExtParams;
    private String mFunction;
    private String mInstanceId;
    private String mJsFrameworkVersion = WXEnvironment.JS_LIB_SDK_VERSION;
    private String mWeexVersion = WXEnvironment.WXSDK_VERSION;
    public long time;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2574411863919855698L, "com/taobao/weex/common/WXJSExceptionInfo", 27);
        $jacocoData = a2;
        return a2;
    }

    public WXJSExceptionInfo(String str, String str2, WXErrorCode wXErrorCode, String str3, String str4, Map<String, String> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstanceId = str;
        this.mBundleUrl = str2;
        this.mErrCode = wXErrorCode;
        this.mFunction = str3;
        this.mException = str4;
        this.mExtParams = map;
        $jacocoInit[0] = true;
        this.time = System.currentTimeMillis();
        $jacocoInit[1] = true;
    }

    public String getInstanceId() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mInstanceId;
        $jacocoInit[2] = true;
        return str;
    }

    public void setInstanceId(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstanceId = str;
        $jacocoInit[3] = true;
    }

    public String getBundleUrl() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mBundleUrl;
        $jacocoInit[4] = true;
        return str;
    }

    public void setBundleUrl(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBundleUrl = str;
        $jacocoInit[5] = true;
    }

    public WXErrorCode getErrCode() {
        boolean[] $jacocoInit = $jacocoInit();
        WXErrorCode wXErrorCode = this.mErrCode;
        $jacocoInit[6] = true;
        return wXErrorCode;
    }

    public void setErrCode(WXErrorCode wXErrorCode) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mErrCode = wXErrorCode;
        $jacocoInit[7] = true;
    }

    public String getFunction() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mFunction;
        $jacocoInit[8] = true;
        return str;
    }

    public void setFunction(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mFunction = str;
        $jacocoInit[9] = true;
    }

    public String getException() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mException;
        $jacocoInit[10] = true;
        return str;
    }

    public void setException(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mException = str;
        $jacocoInit[11] = true;
    }

    public Map<String, String> getExtParams() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = this.mExtParams;
        $jacocoInit[12] = true;
        return map;
    }

    public void setExtParams(Map<String, String> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mExtParams = map;
        $jacocoInit[13] = true;
    }

    public String getWeexVersion() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mWeexVersion;
        $jacocoInit[14] = true;
        return str;
    }

    public String getJsFrameworkVersion() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mJsFrameworkVersion;
        $jacocoInit[15] = true;
        return str;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = new StringBuilder();
        $jacocoInit[16] = true;
        sb.append(" errCode:");
        if (this.mErrCode == null) {
            str = "unSetErrorCode";
            $jacocoInit[17] = true;
        } else {
            str = this.mErrCode.getErrorCode();
            $jacocoInit[18] = true;
        }
        sb.append(str);
        $jacocoInit[19] = true;
        sb.append(",function:");
        if (this.mFunction == null) {
            str2 = "unSetFuncName";
            $jacocoInit[20] = true;
        } else {
            str2 = this.mFunction;
            $jacocoInit[21] = true;
        }
        sb.append(str2);
        $jacocoInit[22] = true;
        sb.append(",exception:");
        if (this.mException == null) {
            str3 = "unSetException";
            $jacocoInit[23] = true;
        } else {
            str3 = this.mException;
            $jacocoInit[24] = true;
        }
        sb.append(str3);
        $jacocoInit[25] = true;
        String sb2 = sb.toString();
        $jacocoInit[26] = true;
        return sb2;
    }
}
