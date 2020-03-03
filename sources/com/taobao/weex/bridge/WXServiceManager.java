package com.taobao.weex.bridge;

import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.common.WXJSService;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXServiceManager {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static Map<String, WXJSService> sInstanceJSServiceMap = new HashMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1045552969341731739L, "com/taobao/weex/bridge/WXServiceManager", 36);
        $jacocoData = a2;
        return a2;
    }

    public WXServiceManager() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ Map access$000() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, WXJSService> map = sInstanceJSServiceMap;
        $jacocoInit[34] = true;
        return map;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[35] = true;
    }

    public static boolean registerService(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[4] = true;
            String str3 = "serviceName: \"" + str + "\"";
            $jacocoInit[5] = true;
            $jacocoInit[6] = true;
            for (String next : map.keySet()) {
                $jacocoInit[7] = true;
                Object obj = map.get(next);
                if (obj instanceof String) {
                    $jacocoInit[8] = true;
                    str3 = str3 + ", '" + next + "': '" + obj + "'";
                    $jacocoInit[9] = true;
                } else {
                    str3 = str3 + ", '" + next + "': " + obj;
                    $jacocoInit[10] = true;
                }
                $jacocoInit[11] = true;
            }
            String format = String.format(";(function(service, options){ ;%s; })({ %s }, { %s });", new Object[]{str2, "register: global.registerService, unregister: global.unregisterService", str3});
            $jacocoInit[12] = true;
            WXJSService wXJSService = new WXJSService();
            $jacocoInit[13] = true;
            wXJSService.setName(str);
            $jacocoInit[14] = true;
            wXJSService.setScript(str2);
            $jacocoInit[15] = true;
            wXJSService.setOptions(map);
            $jacocoInit[16] = true;
            sInstanceJSServiceMap.put(str, wXJSService);
            $jacocoInit[17] = true;
            WXBridgeManager.getInstance().execJSService(format);
            $jacocoInit[18] = true;
            return true;
        }
        $jacocoInit[3] = true;
        return false;
    }

    public static boolean unRegisterService(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[19] = true;
            return false;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[20] = true;
        } else {
            $jacocoInit[21] = true;
            sInstanceJSServiceMap.remove(str);
            $jacocoInit[22] = true;
        }
        String format = String.format("global.unregisterService( \"%s\" );", new Object[]{str});
        $jacocoInit[23] = true;
        WXBridgeManager.getInstance().execJSService(format);
        $jacocoInit[24] = true;
        return true;
    }

    public static void execAllCacheJsService() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[25] = true;
        for (String str : sInstanceJSServiceMap.keySet()) {
            $jacocoInit[26] = true;
            WXJSService wXJSService = sInstanceJSServiceMap.get(str);
            $jacocoInit[27] = true;
            registerService(wXJSService.getName(), wXJSService.getScript(), wXJSService.getOptions());
            $jacocoInit[28] = true;
        }
        $jacocoInit[29] = true;
    }

    public static WXJSService getService(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sInstanceJSServiceMap != null) {
            $jacocoInit[30] = true;
            WXJSService wXJSService = sInstanceJSServiceMap.get(str);
            $jacocoInit[31] = true;
            return wXJSService;
        }
        $jacocoInit[32] = true;
        return null;
    }

    public static void reload() {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().post(new Runnable() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(2379080210939606893L, "com/taobao/weex/bridge/WXServiceManager$1", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                $jacocoInit()[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[1] = true;
                for (Map.Entry value : WXServiceManager.access$000().entrySet()) {
                    $jacocoInit[2] = true;
                    WXJSService wXJSService = (WXJSService) value.getValue();
                    $jacocoInit[3] = true;
                    WXServiceManager.registerService(wXJSService.getName(), wXJSService.getScript(), wXJSService.getOptions());
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[33] = true;
    }
}
