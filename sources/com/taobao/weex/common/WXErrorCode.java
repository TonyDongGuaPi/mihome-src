package com.taobao.weex.common;

import com.taobao.weex.ui.module.WXDomModule;

public enum WXErrorCode {
    WX_ERR_LOAD_SO("-2001", "load so error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_LOAD_JSLIB("-2002", "unzip JSLib error!", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_BAD_SO("-2003", "so size error, to reload apk so", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_COPY_FROM_APK("-2007", "system load so error，copy from APK also error!", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSFUNC_PARAM("-2009", "JS params error!", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSON_OBJECT("-2008", "transform JSON->OBJ  error!", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_INVOKE_NATIVE("-2012", "Native-> Call ->JS  error!", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JS_EXECUTE("-2013", "JavaScript execute error!", ErrorType.JS_ERROR, ErrorGroup.JS),
    WX_SUCCESS("0", "successful", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_CREATEBODY("-2100", "createBody error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_UPDATEATTRS("-2101", "updateAttrs error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_UPDATESTYLE("-2102", "updateStyle error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_ADDELEMENT("-2103", "addElement error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_REMOVEELEMENT("-2104", "removeElement error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_MOVEELEMENT("-2105", "moveElement error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_ADDEVENT("-2106", "addEvent error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_REMOVEEVENT("-2107", "removeEvent error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERROR_DOM_CREATEFINISH("-2108", "createFinish error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERROR_DOM_REFRESHFINISH("-2109", "refreshFinish error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_DOM_SCROLLTO("-2110", WXDomModule.SCROLL_TO_ELEMENT, ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_RELOAD_PAGE("-2111", "reloadPage", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSC_CRASH("-2112", "weexjscCrash", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_FIRST_DOM_ACTION_EXCEPTION("-2113", "dom action is invalid ", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSDOWNLOAD_START("-2201", "js bundle download start", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSBUNDLE_DOWNLOAD("-2299", "js bundle download err", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSBUNDLE_EMPTY("-2203", "js bundle empty", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JSDOWNLOAD_END("-2299", "js bundle download end", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_INIT_SUCCESS("-1000", "js framework success", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_REINIT_SUCCESS("-1001", "js framework reinit success", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JS_FRAMEWORK("-1002", "js framework error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_JS_REINIT_FRAMEWORK("-1003", "js reinit framework error", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_SINGLE_PROCESS_DLOPEN_FILE_NOT_EXIST("-1004", "so file does not exist", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_SINGLE_PROCESS_DLOPEN_FLAIED("-1005", "dlopen so file failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_SINGLE_PROCESS_DLSYM_FAILED("-1006", "find function from so file failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_SINGLE_PROCESS_JS_FRAMEWORK("-1007", "js framework  init singleProcess failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_INIT_MULPROCESS_FAILED("-1008", "js framework init multiProcess failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_REINIT_MULPROCESS_FAILED("-1009", "js framework reinit multiProcess failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_INIT_FAILED("-1010", "js framework init failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_INIT_SINGLE_PROCESS_SUCCESS("-1011", "js framework init success in single process", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_INIT_FAILED_PARAMS_NULL("-1012", "js framework init failed due to params null", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_JS_FRAMEWORK_INIT_FAILED_FIND_ICU_TIMEOUT("-1013", "find icu failed", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_SDK_INIT("-9000", "[WX_KEY_EXCEPTION_SDK_INIT]", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT("-9001", "[WX_KEY_EXCEPTION_SDK_INIT_CPU_NOT_SUPPORT] for android cpu is x86", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_SDK_INIT_TABLE_NOT_SUPPORT("-9002", "[WX_KEY_EXCEPTION_SDK_INIT_TABLE_NOT_SUPPORT] for device isTabletDevice", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_SDK_INIT_JSFM_INIT_FAILED("-9003", "[WX_KEY_EXCEPTION_SDK_INIT_JSFM_INIT_FAILED] for jsfm init failed|detail error is:", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_INVOKE_BRIDGE("-9100", "[WX_KEY_EXCEPTION_INVOKE_BRIDGE]", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_INVOKE_REGISTER_CONTENT_FAILED("-9101", "[WX_KEY_EXCEPTION_INVOKE_REGISTER_CONTENT_FAILED] details", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_INVOKE_JSSERVICE_EXECUTE("-9102", "[WX_KEY_EXCEPTION_INVOKE_JSSERVICE_EXECUTE] details", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_INVOKE_REGISTER_MODULES("-9103", "[WX_KEY_EXCEPTION_INVOKE_REGISTER_MODULES] details", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_INVOKE_REGISTER_COMPONENT("-9104", "[WX_KEY_EXCEPTION_INVOKE_REGISTER_COMPONENT] details", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_JS_DOWNLOAD("-9200", "[WX_KEY_EXCEPTION_JS_DOWNLOAD]|", ErrorType.DOWN_LOAD_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_JS_DOWNLOAD_FAILED("-9201", "[WX_KEY_EXCEPTION_JS_DOWNLOAD_FAILED] | details", ErrorType.DOWN_LOAD_ERROR, ErrorGroup.NATIVE),
    WX_KEY_EXCEPTION_WXBRIDGE("-9400", "[js excute runtime error] detail js stack -> ", ErrorType.JS_ERROR, ErrorGroup.JS),
    WX_KEY_EXCEPTION_WXBRIDGE_EXCEPTION("-9401", "[js excute runtime error] detail js stack -> ", ErrorType.JS_ERROR, ErrorGroup.JS),
    WX_RENDER_ERR_JS_CREATE_INSTANCE("-9600", "white screen cause create instance failed,check js stack ->", ErrorType.RENDER_ERROR, ErrorGroup.JS),
    WX_RENDER_ERR_JS_CREATE_INSTANCE_CONTEXT("-9601", "white screen cause create instanceContext failed,check js stack ->", ErrorType.RENDER_ERROR, ErrorGroup.JS),
    WX_RENDER_ERR_LAYER_OVERFLOW("-9602", "WX_RENDER_ERR_LAYER_OVERFLOW", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_RENDER_ERR_NULL_KEY("-9603", "WX_RENDER_ERR_NULL_KEY", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_RENDER_ERR_NATIVE_RUNTIME("-9604", "WX_RENDER_ERR for js error", ErrorType.RENDER_ERROR, ErrorGroup.NATIVE),
    WX_RENDER_ERR_COMPONENT_NOT_REGISTER("-9605", "WX_RENDER_ERR_COMPONENT_NOT_REGISTER", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_RENDER_ERR_BRIDGE_ARG_NULL("-9610", "WX_RENDER_ERR_BRIDGE_ARG_NULL", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_RENDER_ERR_CONTAINER_TYPE("-9611", "WX_RENDER_ERR_CONTAINER_TYPE", ErrorType.JS_ERROR, ErrorGroup.JS),
    WX_RENDER_ERR_TRANSITION("-9616", "WX_RENDER_ERR_TRANSITION", ErrorType.JS_ERROR, ErrorGroup.JS),
    WX_RENDER_ERR_INSTANCE_ID_NULL("-9618", "WX_RENDER_ERR_INSTANCE_ID_NULL", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_RENDER_ERR_LIST_INVALID_COLUMN_COUNT("-9619", "WX_RENDER_ERR_LIST_INVALID_COLUMNJ_CONUNT", ErrorType.JS_ERROR, ErrorGroup.JS),
    WX_RENDER_ERR_TEXTURE_SETBACKGROUND("-9620", "WX_RENDER_ERR_TEXTURE_SETBACKGROUND", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_DEGRAD_ERR("-1000", "degradeToH5|Weex DegradPassivity ->", ErrorType.DEGRAD_ERROR, ErrorGroup.JS),
    WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED("-1001", "degradeToH5|createInstance fail|wx_create_instance_error", ErrorType.DEGRAD_ERROR, ErrorGroup.NATIVE),
    WX_DEGRAD_ERR_NETWORK_BUNDLE_DOWNLOAD_FAILED("-1002", "|wx_network_error|js bundle download failed", ErrorType.DOWN_LOAD_ERROR, ErrorGroup.NATIVE),
    WX_DEGRAD_ERR_NETWORK_CHECK_CONTENT_LENGTH_FAILED("-1003", "degradeToH5|wx_network_error|js bundle content-length check failed", ErrorType.DEGRAD_ERROR, ErrorGroup.NATIVE),
    WX_DEGRAD_ERR_BUNDLE_CONTENTTYPE_ERROR("-1004", "degradeToH5|wx_user_intercept_error |Content-Type is not application/javascript, Weex render template must be javascript, please check your request!", ErrorType.DEGRAD_ERROR, ErrorGroup.NATIVE),
    WX_DEGRAD_ERR_OTHER_CAUSE_DEGRADTOH5("-1005", "degradeToH5|for other reason|", ErrorType.DEGRAD_ERROR, ErrorGroup.NATIVE),
    WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED_JS("-1006", "degradeToH5|createInstance fail|wx_create_instance_error", ErrorType.DEGRAD_ERROR, ErrorGroup.JS),
    WX_ERR_HASH_MAP_TMP("-10010", "WX_ERR_HASH_MAP_TMP", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE),
    WX_ERR_TEST("test", "test", ErrorType.NATIVE_ERROR, ErrorGroup.NATIVE);
    
    private String appendMsg;
    private String args;
    private String errorCode;
    private String errorMsg;
    private ErrorGroup mErrorGroup;
    private ErrorType mErrorType;

    static {
        boolean[] $jacocoInit;
        $jacocoInit[86] = true;
    }

    private WXErrorCode(String str, String str2, ErrorType errorType, ErrorGroup errorGroup) {
        boolean[] $jacocoInit = $jacocoInit();
        this.appendMsg = "";
        this.errorCode = str;
        this.errorMsg = str2;
        this.mErrorType = errorType;
        this.mErrorGroup = errorGroup;
        $jacocoInit[2] = true;
    }

    public void appendErrMsg(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.appendMsg = str;
        $jacocoInit[3] = true;
    }

    public String getErrorCode() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.errorCode;
        $jacocoInit[4] = true;
        return str;
    }

    public String getErrorMsg() {
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = new StringBuilder(this.errorMsg);
        $jacocoInit[5] = true;
        sb.append(this.appendMsg);
        $jacocoInit[6] = true;
        String sb2 = sb.toString();
        $jacocoInit[7] = true;
        return sb2;
    }

    public ErrorType getErrorType() {
        boolean[] $jacocoInit = $jacocoInit();
        ErrorType errorType = this.mErrorType;
        $jacocoInit[8] = true;
        return errorType;
    }

    public ErrorGroup getErrorGroup() {
        boolean[] $jacocoInit = $jacocoInit();
        ErrorGroup errorGroup = this.mErrorGroup;
        $jacocoInit[9] = true;
        return errorGroup;
    }

    public String getArgs() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.args;
        $jacocoInit[10] = true;
        return str;
    }

    public void setArgs(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.args = str;
        $jacocoInit[11] = true;
    }

    public enum ErrorType {
        JS_ERROR,
        NATIVE_ERROR,
        RENDER_ERROR,
        DEGRAD_ERROR,
        DOWN_LOAD_ERROR;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[7] = true;
        }
    }

    public enum ErrorGroup {
        JS,
        NATIVE;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[4] = true;
        }
    }
}