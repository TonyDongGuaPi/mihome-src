package com.taobao.weex.bridge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Menu;
import com.alibaba.fastjson.JSONArray;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.ui.config.ConfigModuleFactory;
import com.taobao.weex.ui.module.WXDomModule;
import com.taobao.weex.ui.module.WXTimerModule;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXModuleManager {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static Map<String, WXDomModule> sDomModuleMap = new HashMap();
    private static Map<String, WXModule> sGlobalModuleMap = new HashMap();
    private static Map<String, Map<String, WXModule>> sInstanceModuleMap = new ConcurrentHashMap();
    private static volatile ConcurrentMap<String, ModuleFactoryImpl> sModuleFactoryMap = new ConcurrentHashMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6139619321195868527L, "com/taobao/weex/bridge/WXModuleManager", 257);
        $jacocoData = a2;
        return a2;
    }

    public WXModuleManager() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ ConcurrentMap access$000() {
        boolean[] $jacocoInit = $jacocoInit();
        ConcurrentMap<String, ModuleFactoryImpl> concurrentMap = sModuleFactoryMap;
        $jacocoInit[251] = true;
        return concurrentMap;
    }

    static /* synthetic */ Map access$100() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, WXModule> map = sGlobalModuleMap;
        $jacocoInit[252] = true;
        return map;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[253] = true;
        $jacocoInit[254] = true;
        $jacocoInit[255] = true;
        $jacocoInit[256] = true;
    }

    public static boolean registerModule(final String str, final ModuleFactory moduleFactory, final boolean z) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[1] = true;
        } else if (moduleFactory == null) {
            $jacocoInit[2] = true;
        } else if (!TextUtils.equals(str, WXDomModule.WXDOM)) {
            $jacocoInit[4] = true;
            try {
                sModuleFactoryMap.put(str, new ModuleFactoryImpl(moduleFactory));
                $jacocoInit[7] = true;
            } catch (Throwable unused) {
                $jacocoInit[8] = true;
            }
            WXBridgeManager instance = WXBridgeManager.getInstance();
            AnonymousClass1 r3 = new Runnable() {
                private static transient /* synthetic */ boolean[] $jacocoData;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-3672036980973900030L, "com/taobao/weex/bridge/WXModuleManager$1", 16);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (WXModuleManager.access$000() == null) {
                        $jacocoInit[1] = true;
                    } else if (!WXModuleManager.access$000().containsKey(str)) {
                        $jacocoInit[2] = true;
                    } else {
                        $jacocoInit[3] = true;
                        WXLogUtils.w("WXComponentRegistry Duplicate the Module name: " + str);
                        try {
                            $jacocoInit[4] = true;
                        } catch (WXException e) {
                            $jacocoInit[6] = true;
                            WXLogUtils.e("registerNativeModule" + e);
                            $jacocoInit[7] = true;
                        }
                    }
                    WXModuleManager.registerNativeModule(str, moduleFactory);
                    $jacocoInit[5] = true;
                    if (!z) {
                        $jacocoInit[8] = true;
                    } else {
                        try {
                            $jacocoInit[9] = true;
                            WXModule buildInstance = moduleFactory.buildInstance();
                            $jacocoInit[10] = true;
                            buildInstance.setModuleName(str);
                            $jacocoInit[11] = true;
                            WXModuleManager.access$100().put(str, buildInstance);
                            $jacocoInit[12] = true;
                        } catch (Exception e2) {
                            $jacocoInit[13] = true;
                            WXLogUtils.e(str + " class must have a default constructor without params. ", (Throwable) e2);
                            $jacocoInit[14] = true;
                        }
                    }
                    WXModuleManager.registerJSModule(str, moduleFactory);
                    $jacocoInit[15] = true;
                }
            };
            $jacocoInit[9] = true;
            instance.post(r3);
            $jacocoInit[10] = true;
            return true;
        } else {
            $jacocoInit[5] = true;
            WXLogUtils.e("Cannot registered module with name 'dom'.");
            $jacocoInit[6] = true;
            return false;
        }
        $jacocoInit[3] = true;
        return false;
    }

    static boolean registerNativeModule(String str, ModuleFactory moduleFactory) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        if (moduleFactory != null) {
            $jacocoInit[11] = true;
            try {
                if (sModuleFactoryMap.containsKey(str)) {
                    $jacocoInit[13] = true;
                } else {
                    $jacocoInit[14] = true;
                    sModuleFactoryMap.put(str, new ModuleFactoryImpl(moduleFactory));
                    $jacocoInit[15] = true;
                }
                $jacocoInit[16] = true;
            } catch (ArrayStoreException e) {
                $jacocoInit[17] = true;
                e.printStackTrace();
                $jacocoInit[18] = true;
                WXLogUtils.e("[WXModuleManager] registerNativeModule Error moduleName:" + str + " Error:" + e.toString());
                $jacocoInit[19] = true;
            }
            $jacocoInit[20] = true;
            return true;
        }
        $jacocoInit[12] = true;
        return false;
    }

    static boolean registerJSModule(String str, ModuleFactory moduleFactory) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[21] = true;
        hashMap.put(str, moduleFactory.getMethods());
        $jacocoInit[22] = true;
        WXSDKManager.getInstance().registerModules(hashMap);
        $jacocoInit[23] = true;
        return true;
    }

    static Object callModuleMethod(String str, String str2, String str3, JSONArray jSONArray) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        boolean[] $jacocoInit = $jacocoInit();
        ModuleFactory moduleFactory = ((ModuleFactoryImpl) sModuleFactoryMap.get(str5)).mFactory;
        if (moduleFactory == null) {
            $jacocoInit[24] = true;
            WXLogUtils.e("[WXModuleManager] module factory not found.");
            $jacocoInit[25] = true;
            return null;
        }
        WXModule findModule = findModule(str4, str5, moduleFactory);
        if (findModule == null) {
            $jacocoInit[26] = true;
            return null;
        }
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str4);
        findModule.mWXSDKInstance = sDKInstance;
        $jacocoInit[27] = true;
        Invoker methodInvoker = moduleFactory.getMethodInvoker(str6);
        try {
            $jacocoInit[28] = true;
            if (sDKInstance == null) {
                $jacocoInit[29] = true;
                WXLogUtils.e("callModuleMethod >>> instance is null");
                if (findModule instanceof WXDomModule) {
                    $jacocoInit[43] = true;
                } else if (!(findModule instanceof WXTimerModule)) {
                    $jacocoInit[44] = true;
                    $jacocoInit[47] = true;
                    return null;
                } else {
                    $jacocoInit[45] = true;
                }
                findModule.mWXSDKInstance = null;
                $jacocoInit[46] = true;
                $jacocoInit[47] = true;
                return null;
            }
            $jacocoInit[30] = true;
            IWXUserTrackAdapter iWXUserTrackAdapter = WXSDKManager.getInstance().getIWXUserTrackAdapter();
            if (iWXUserTrackAdapter == null) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                HashMap hashMap = new HashMap();
                $jacocoInit[33] = true;
                hashMap.put("errCode", "101");
                $jacocoInit[34] = true;
                hashMap.put("arg", str5 + "." + str6);
                $jacocoInit[35] = true;
                hashMap.put("errMsg", sDKInstance.getBundleUrl());
                $jacocoInit[36] = true;
                iWXUserTrackAdapter.commit(sDKInstance.getContext(), (String) null, IWXUserTrackAdapter.INVOKE_MODULE, (WXPerformance) null, hashMap);
                $jacocoInit[37] = true;
            }
            Object dispatchCallModuleMethod = dispatchCallModuleMethod(sDKInstance, findModule, jSONArray, methodInvoker);
            if (findModule instanceof WXDomModule) {
                $jacocoInit[38] = true;
            } else if (!(findModule instanceof WXTimerModule)) {
                $jacocoInit[39] = true;
                $jacocoInit[42] = true;
                return dispatchCallModuleMethod;
            } else {
                $jacocoInit[40] = true;
            }
            findModule.mWXSDKInstance = null;
            $jacocoInit[41] = true;
            $jacocoInit[42] = true;
            return dispatchCallModuleMethod;
        } catch (Exception e) {
            $jacocoInit[48] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_REGISTER_CONTENT_FAILED;
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_REGISTER_CONTENT_FAILED;
            $jacocoInit[49] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("callModuleMethod >>> invoke module:");
            sb.append(str5);
            sb.append(", method:");
            sb.append(str6);
            sb.append(" failed. ");
            $jacocoInit[50] = true;
            sb.append(WXLogUtils.getStackTrace(e));
            String sb2 = sb.toString();
            $jacocoInit[51] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str4, wXErrorCode, "callModuleMethod", sb2, (Map<String, String>) null);
            $jacocoInit[52] = true;
            WXLogUtils.e("callModuleMethod >>> invoke module:" + str5 + ", method:" + str6 + " failed. ", (Throwable) e);
            if (findModule instanceof WXDomModule) {
                $jacocoInit[53] = true;
            } else if (!(findModule instanceof WXTimerModule)) {
                $jacocoInit[54] = true;
                $jacocoInit[57] = true;
                return null;
            } else {
                $jacocoInit[55] = true;
            }
            findModule.mWXSDKInstance = null;
            $jacocoInit[56] = true;
            $jacocoInit[57] = true;
            return null;
        } catch (Throwable th) {
            if (findModule instanceof WXDomModule) {
                $jacocoInit[58] = true;
            } else if (!(findModule instanceof WXTimerModule)) {
                $jacocoInit[59] = true;
                $jacocoInit[62] = true;
                throw th;
            } else {
                $jacocoInit[60] = true;
            }
            findModule.mWXSDKInstance = null;
            $jacocoInit[61] = true;
            $jacocoInit[62] = true;
            throw th;
        }
    }

    private static Object dispatchCallModuleMethod(@NonNull WXSDKInstance wXSDKInstance, @NonNull WXModule wXModule, @NonNull JSONArray jSONArray, @NonNull Invoker invoker) throws Exception {
        boolean[] $jacocoInit = $jacocoInit();
        if (!wXSDKInstance.isPreRenderMode()) {
            $jacocoInit[63] = true;
            Object invoke = wXSDKInstance.getNativeInvokeHelper().invoke(wXModule, invoker, jSONArray);
            $jacocoInit[64] = true;
            return invoke;
        } else if (invoker.isRunOnUIThread()) {
            $jacocoInit[65] = true;
            return null;
        } else {
            Object invoke2 = wXSDKInstance.getNativeInvokeHelper().invoke(wXModule, invoker, jSONArray);
            $jacocoInit[66] = true;
            return invoke2;
        }
    }

    public static boolean hasModule(String str) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (sGlobalModuleMap.containsKey(str)) {
            $jacocoInit[67] = true;
        } else if (sModuleFactoryMap.containsKey(str)) {
            $jacocoInit[68] = true;
        } else {
            z = false;
            $jacocoInit[70] = true;
            $jacocoInit[71] = true;
            return z;
        }
        $jacocoInit[69] = true;
        z = true;
        $jacocoInit[71] = true;
        return z;
    }

    private static WXModule findModule(String str, String str2, ModuleFactory moduleFactory) {
        WXModule wXModule;
        boolean[] $jacocoInit = $jacocoInit();
        WXModule wXModule2 = sGlobalModuleMap.get(str2);
        if (wXModule2 != null) {
            $jacocoInit[72] = true;
            wXModule = wXModule2;
        } else {
            $jacocoInit[73] = true;
            Map map = sInstanceModuleMap.get(str);
            if (map != null) {
                $jacocoInit[74] = true;
            } else {
                $jacocoInit[75] = true;
                map = new ConcurrentHashMap();
                $jacocoInit[76] = true;
                sInstanceModuleMap.put(str, map);
                $jacocoInit[77] = true;
            }
            WXModule wXModule3 = (WXModule) map.get(str2);
            if (wXModule3 != null) {
                $jacocoInit[78] = true;
                wXModule = wXModule3;
            } else {
                try {
                    $jacocoInit[79] = true;
                    if (moduleFactory instanceof ConfigModuleFactory) {
                        $jacocoInit[80] = true;
                        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                        $jacocoInit[81] = true;
                        wXModule = ((ConfigModuleFactory) moduleFactory).buildInstance(sDKInstance);
                        $jacocoInit[82] = true;
                    } else {
                        wXModule = moduleFactory.buildInstance();
                        $jacocoInit[83] = true;
                    }
                    wXModule.setModuleName(str2);
                    map.put(str2, wXModule);
                    $jacocoInit[86] = true;
                } catch (Exception e) {
                    $jacocoInit[84] = true;
                    WXLogUtils.e(str2 + " module build instace failed.", (Throwable) e);
                    $jacocoInit[85] = true;
                    return null;
                }
            }
        }
        $jacocoInit[87] = true;
        return wXModule;
    }

    public static void onActivityCreate(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[88] = true;
        } else {
            $jacocoInit[89] = true;
            $jacocoInit[90] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[92] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[93] = true;
                    wXModule.onActivityCreate();
                    $jacocoInit[94] = true;
                } else {
                    WXLogUtils.w("onActivityCreate can not find the " + str2 + " module");
                    $jacocoInit[95] = true;
                }
                $jacocoInit[96] = true;
            }
            $jacocoInit[91] = true;
        }
        $jacocoInit[97] = true;
    }

    public static void onActivityStart(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[98] = true;
        } else {
            $jacocoInit[99] = true;
            $jacocoInit[100] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[102] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[103] = true;
                    wXModule.onActivityStart();
                    $jacocoInit[104] = true;
                } else {
                    WXLogUtils.w("onActivityStart can not find the " + str2 + " module");
                    $jacocoInit[105] = true;
                }
                $jacocoInit[106] = true;
            }
            $jacocoInit[101] = true;
        }
        $jacocoInit[107] = true;
    }

    public static void onActivityPause(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[108] = true;
        } else {
            $jacocoInit[109] = true;
            $jacocoInit[110] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[112] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[113] = true;
                    wXModule.onActivityPause();
                    $jacocoInit[114] = true;
                } else {
                    WXLogUtils.w("onActivityPause can not find the " + str2 + " module");
                    $jacocoInit[115] = true;
                }
                $jacocoInit[116] = true;
            }
            $jacocoInit[111] = true;
        }
        $jacocoInit[117] = true;
    }

    public static void onActivityResume(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[118] = true;
        } else {
            $jacocoInit[119] = true;
            $jacocoInit[120] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[122] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[123] = true;
                    wXModule.onActivityResume();
                    $jacocoInit[124] = true;
                } else {
                    WXLogUtils.w("onActivityResume can not find the " + str2 + " module");
                    $jacocoInit[125] = true;
                }
                $jacocoInit[126] = true;
            }
            $jacocoInit[121] = true;
        }
        $jacocoInit[127] = true;
    }

    public static void onActivityStop(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[128] = true;
        } else {
            $jacocoInit[129] = true;
            $jacocoInit[130] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[132] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[133] = true;
                    wXModule.onActivityStop();
                    $jacocoInit[134] = true;
                } else {
                    WXLogUtils.w("onActivityStop can not find the " + str2 + " module");
                    $jacocoInit[135] = true;
                }
                $jacocoInit[136] = true;
            }
            $jacocoInit[131] = true;
        }
        $jacocoInit[137] = true;
    }

    public static void onActivityDestroy(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[138] = true;
        } else {
            $jacocoInit[139] = true;
            $jacocoInit[140] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[142] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[143] = true;
                    wXModule.onActivityDestroy();
                    $jacocoInit[144] = true;
                } else {
                    WXLogUtils.w("onActivityDestroy can not find the " + str2 + " module");
                    $jacocoInit[145] = true;
                }
                $jacocoInit[146] = true;
            }
            $jacocoInit[141] = true;
        }
        $jacocoInit[147] = true;
    }

    public static boolean onActivityBack(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[148] = true;
        } else {
            $jacocoInit[149] = true;
            $jacocoInit[150] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[152] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[153] = true;
                    boolean onActivityBack = wXModule.onActivityBack();
                    $jacocoInit[154] = true;
                    return onActivityBack;
                }
                WXLogUtils.w("onActivityCreate can not find the " + str2 + " module");
                $jacocoInit[155] = true;
            }
            $jacocoInit[151] = true;
        }
        $jacocoInit[156] = true;
        return false;
    }

    public static void onActivityResult(String str, int i, int i2, Intent intent) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[157] = true;
        } else {
            $jacocoInit[158] = true;
            $jacocoInit[159] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[161] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[162] = true;
                    wXModule.onActivityResult(i, i2, intent);
                    $jacocoInit[163] = true;
                } else {
                    WXLogUtils.w("onActivityResult can not find the " + str2 + " module");
                    $jacocoInit[164] = true;
                }
                $jacocoInit[165] = true;
            }
            $jacocoInit[160] = true;
        }
        $jacocoInit[166] = true;
    }

    public static boolean onCreateOptionsMenu(String str, Menu menu) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[167] = true;
        } else {
            $jacocoInit[168] = true;
            $jacocoInit[169] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[171] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[172] = true;
                    wXModule.onCreateOptionsMenu(menu);
                    $jacocoInit[173] = true;
                } else {
                    WXLogUtils.w("onActivityResult can not find the " + str2 + " module");
                    $jacocoInit[174] = true;
                }
                $jacocoInit[175] = true;
            }
            $jacocoInit[170] = true;
        }
        $jacocoInit[176] = true;
        return false;
    }

    public static void onRequestPermissionsResult(String str, int i, String[] strArr, int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        Map map = sInstanceModuleMap.get(str);
        if (map == null) {
            $jacocoInit[177] = true;
        } else {
            $jacocoInit[178] = true;
            $jacocoInit[179] = true;
            for (String str2 : map.keySet()) {
                $jacocoInit[181] = true;
                WXModule wXModule = (WXModule) map.get(str2);
                if (wXModule != null) {
                    $jacocoInit[182] = true;
                    wXModule.onRequestPermissionsResult(i, strArr, iArr);
                    $jacocoInit[183] = true;
                } else {
                    WXLogUtils.w("onActivityResult can not find the " + str2 + " module");
                    $jacocoInit[184] = true;
                }
                $jacocoInit[185] = true;
            }
            $jacocoInit[180] = true;
        }
        $jacocoInit[186] = true;
    }

    public static void destroyInstanceModules(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        sDomModuleMap.remove(str);
        $jacocoInit[187] = true;
        Map remove = sInstanceModuleMap.remove(str);
        $jacocoInit[188] = true;
        if (remove == null) {
            $jacocoInit[189] = true;
        } else if (remove.size() < 1) {
            $jacocoInit[190] = true;
        } else {
            $jacocoInit[192] = true;
            for (Map.Entry value : remove.entrySet()) {
                $jacocoInit[193] = true;
                $jacocoInit[194] = true;
                WXModule wXModule = (WXModule) value.getValue();
                if (!(wXModule instanceof Destroyable)) {
                    $jacocoInit[195] = true;
                } else {
                    $jacocoInit[196] = true;
                    ((Destroyable) wXModule).destroy();
                    $jacocoInit[197] = true;
                }
                $jacocoInit[198] = true;
            }
            $jacocoInit[199] = true;
            return;
        }
        $jacocoInit[191] = true;
    }

    public static void createDomModule(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXSDKInstance == null) {
            $jacocoInit[200] = true;
        } else {
            $jacocoInit[201] = true;
            sDomModuleMap.put(wXSDKInstance.getInstanceId(), new WXDomModule(wXSDKInstance));
            $jacocoInit[202] = true;
        }
        $jacocoInit[203] = true;
    }

    public static void destoryDomModule(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        sDomModuleMap.remove(str);
        $jacocoInit[204] = true;
    }

    public static WXDomModule getDomModule(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXDomModule wXDomModule = sDomModuleMap.get(str);
        $jacocoInit[205] = true;
        return wXDomModule;
    }

    public static void reload() {
        boolean[] $jacocoInit = $jacocoInit();
        if (sModuleFactoryMap == null) {
            $jacocoInit[206] = true;
        } else if (sModuleFactoryMap.size() <= 0) {
            $jacocoInit[207] = true;
        } else {
            $jacocoInit[208] = true;
            $jacocoInit[209] = true;
            for (Map.Entry entry : sModuleFactoryMap.entrySet()) {
                try {
                    $jacocoInit[211] = true;
                    registerJSModule((String) entry.getKey(), ((ModuleFactoryImpl) entry.getValue()).mFactory);
                    $jacocoInit[212] = true;
                } catch (Throwable unused) {
                    $jacocoInit[213] = true;
                }
                $jacocoInit[214] = true;
            }
            $jacocoInit[210] = true;
        }
        $jacocoInit[215] = true;
    }

    public static void registerWhenCreateInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        if (sModuleFactoryMap == null) {
            $jacocoInit[216] = true;
        } else if (sModuleFactoryMap.size() <= 0) {
            $jacocoInit[217] = true;
        } else {
            $jacocoInit[218] = true;
            $jacocoInit[219] = true;
            for (Map.Entry entry : sModuleFactoryMap.entrySet()) {
                try {
                    $jacocoInit[221] = true;
                    if (((ModuleFactoryImpl) entry.getValue()).hasRigster) {
                        $jacocoInit[222] = true;
                    } else {
                        $jacocoInit[223] = true;
                        registerJSModule((String) entry.getKey(), ((ModuleFactoryImpl) entry.getValue()).mFactory);
                        $jacocoInit[224] = true;
                    }
                    $jacocoInit[225] = true;
                } catch (Throwable unused) {
                    $jacocoInit[226] = true;
                }
                $jacocoInit[227] = true;
            }
            $jacocoInit[220] = true;
        }
        $jacocoInit[228] = true;
    }

    public static void resetAllModuleState() {
        boolean[] $jacocoInit = $jacocoInit();
        if (sModuleFactoryMap == null) {
            $jacocoInit[229] = true;
        } else if (sModuleFactoryMap.size() <= 0) {
            $jacocoInit[230] = true;
        } else {
            $jacocoInit[231] = true;
            $jacocoInit[232] = true;
            for (Map.Entry value : sModuleFactoryMap.entrySet()) {
                $jacocoInit[234] = true;
                ((ModuleFactoryImpl) value.getValue()).hasRigster = false;
                $jacocoInit[235] = true;
            }
            $jacocoInit[233] = true;
        }
        $jacocoInit[236] = true;
    }

    public static void resetModuleState(String str, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (sModuleFactoryMap == null) {
            $jacocoInit[237] = true;
        } else if (sModuleFactoryMap.size() <= 0) {
            $jacocoInit[238] = true;
        } else {
            $jacocoInit[239] = true;
            $jacocoInit[240] = true;
            for (Map.Entry entry : sModuleFactoryMap.entrySet()) {
                try {
                    $jacocoInit[242] = true;
                    if (entry.getKey() == null) {
                        $jacocoInit[243] = true;
                    } else if (!((String) entry.getKey()).equals(str)) {
                        $jacocoInit[244] = true;
                    } else {
                        $jacocoInit[245] = true;
                        ((ModuleFactoryImpl) entry.getValue()).hasRigster = z;
                        $jacocoInit[246] = true;
                    }
                    $jacocoInit[247] = true;
                } catch (Throwable unused) {
                    $jacocoInit[248] = true;
                }
                $jacocoInit[249] = true;
            }
            $jacocoInit[241] = true;
        }
        $jacocoInit[250] = true;
    }
}
