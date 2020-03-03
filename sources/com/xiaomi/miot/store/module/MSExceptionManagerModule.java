package com.xiaomi.miot.store.module;

import android.text.TextUtils;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MSExceptionManagerModule extends ExceptionsManagerModule {
    private static final Pattern mJsModuleIdPattern = Pattern.compile("(?:^|[/\\\\])(\\d+\\.js)$");
    DevSupportManager mDevSupportManager;

    public boolean canOverrideExistingModule() {
        return true;
    }

    public MSExceptionManagerModule(DevSupportManager devSupportManager) {
        super(devSupportManager);
        this.mDevSupportManager = devSupportManager;
    }

    @ReactMethod
    public void reportFatalException(String str, ReadableArray readableArray, int i) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(str, readableArray, i);
            return;
        }
        String stackTraceToString = stackTraceToString(str, readableArray);
        if (!TextUtils.isEmpty(stackTraceToString)) {
            RNAppStoreApiManager.a().j().handleException(new JavascriptException(stackTraceToString));
        }
    }

    @ReactMethod
    public void reportSoftException(String str, ReadableArray readableArray, int i) {
        super.reportSoftException(str, readableArray, i);
    }

    @ReactMethod
    public void updateExceptionMessage(String str, ReadableArray readableArray, int i) {
        super.updateExceptionMessage(str, readableArray, i);
    }

    private static String stackFrameToModuleId(ReadableMap readableMap) {
        if (!readableMap.hasKey("file") || readableMap.isNull("file") || readableMap.getType("file") != ReadableType.String) {
            return "";
        }
        Matcher matcher = mJsModuleIdPattern.matcher(readableMap.getString("file"));
        if (!matcher.find()) {
            return "";
        }
        return matcher.group(1) + ":";
    }

    private String stackTraceToString(String str, ReadableArray readableArray) {
        try {
            StringBuilder sb = new StringBuilder(str);
            sb.append(", stack:\n");
            for (int i = 0; i < readableArray.size(); i++) {
                ReadableMap map = readableArray.getMap(i);
                sb.append(map.getString("methodName"));
                sb.append("@");
                sb.append(stackFrameToModuleId(map));
                sb.append(map.getInt(StackTraceHelper.LINE_NUMBER_KEY));
                if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                    sb.append(":");
                    sb.append(map.getInt("column"));
                }
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
