package com.taobao.weex.appfram.storage;

import android.support.annotation.Nullable;
import com.taobao.weex.bridge.JSCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class StorageResultHandler {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String DATA = "data";
    private static final String RESULT = "result";
    private static final String RESULT_FAILED = "failed";
    private static final String RESULT_FAILED_INVALID_PARAM = "invalid_param";
    private static final String RESULT_FAILED_NO_HANDLER = "no_handler";
    private static final String RESULT_OK = "success";
    private static final String UNDEFINED = "undefined";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1263732362636455263L, "com/taobao/weex/appfram/storage/StorageResultHandler", 34);
        $jacocoData = a2;
        return a2;
    }

    private StorageResultHandler() {
        $jacocoInit()[0] = true;
    }

    public static Map<String, Object> getItemResult(String str) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(4);
        $jacocoInit[1] = true;
        if (str != null) {
            str2 = "success";
            $jacocoInit[2] = true;
        } else {
            str2 = "failed";
            $jacocoInit[3] = true;
        }
        hashMap.put("result", str2);
        $jacocoInit[4] = true;
        if (str != null) {
            $jacocoInit[5] = true;
        } else {
            str = "undefined";
            $jacocoInit[6] = true;
        }
        hashMap.put("data", str);
        $jacocoInit[7] = true;
        return hashMap;
    }

    public static Map<String, Object> setItemResult(boolean z) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(4);
        $jacocoInit[8] = true;
        if (z) {
            str = "success";
            $jacocoInit[9] = true;
        } else {
            str = "failed";
            $jacocoInit[10] = true;
        }
        hashMap.put("result", str);
        $jacocoInit[11] = true;
        hashMap.put("data", "undefined");
        $jacocoInit[12] = true;
        return hashMap;
    }

    public static Map<String, Object> removeItemResult(boolean z) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(4);
        $jacocoInit[13] = true;
        if (z) {
            str = "success";
            $jacocoInit[14] = true;
        } else {
            str = "failed";
            $jacocoInit[15] = true;
        }
        hashMap.put("result", str);
        $jacocoInit[16] = true;
        hashMap.put("data", "undefined");
        $jacocoInit[17] = true;
        return hashMap;
    }

    public static Map<String, Object> getLengthResult(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(4);
        $jacocoInit[18] = true;
        hashMap.put("result", "success");
        $jacocoInit[19] = true;
        hashMap.put("data", Long.valueOf(j));
        $jacocoInit[20] = true;
        return hashMap;
    }

    public static Map<String, Object> getAllkeysResult(List<String> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (list != null) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            list = new ArrayList<>(1);
            $jacocoInit[23] = true;
        }
        HashMap hashMap = new HashMap(4);
        $jacocoInit[24] = true;
        hashMap.put("result", "success");
        $jacocoInit[25] = true;
        hashMap.put("data", list);
        $jacocoInit[26] = true;
        return hashMap;
    }

    private static void handleResult(@Nullable JSCallback jSCallback, String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSCallback == null) {
            $jacocoInit[27] = true;
            return;
        }
        HashMap hashMap = new HashMap(4);
        $jacocoInit[28] = true;
        hashMap.put("result", str);
        $jacocoInit[29] = true;
        hashMap.put("data", obj);
        $jacocoInit[30] = true;
        jSCallback.invoke(hashMap);
        $jacocoInit[31] = true;
    }

    public static void handleNoHandlerError(@Nullable JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        handleResult(jSCallback, "failed", RESULT_FAILED_NO_HANDLER);
        $jacocoInit[32] = true;
    }

    public static void handleInvalidParam(@Nullable JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        handleResult(jSCallback, "failed", RESULT_FAILED_INVALID_PARAM);
        $jacocoInit[33] = true;
    }
}
