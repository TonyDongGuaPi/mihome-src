package com.taobao.weex.ui.config;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.JavascriptInvokable;
import com.taobao.weex.bridge.ModuleFactory;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXLogUtils;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class AutoScanConfigRegister {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String TAG = "WeexScanConfigRegister";
    private static ConcurrentLinkedQueue<JavascriptInvokable> autoLoadClass = new ConcurrentLinkedQueue<>();
    private static long scanDelay = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4209934759864911253L, "com/taobao/weex/ui/config/AutoScanConfigRegister", 67);
        $jacocoData = a2;
        return a2;
    }

    public AutoScanConfigRegister() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ void access$000() {
        boolean[] $jacocoInit = $jacocoInit();
        doScanConfigSync();
        $jacocoInit[63] = true;
    }

    static /* synthetic */ ConcurrentLinkedQueue access$100() {
        boolean[] $jacocoInit = $jacocoInit();
        ConcurrentLinkedQueue<JavascriptInvokable> concurrentLinkedQueue = autoLoadClass;
        $jacocoInit[64] = true;
        return concurrentLinkedQueue;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[65] = true;
        $jacocoInit[66] = true;
    }

    public static void preLoad(JavascriptInvokable javascriptInvokable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (javascriptInvokable instanceof ConfigModuleFactory) {
            $jacocoInit[1] = true;
        } else if (javascriptInvokable instanceof ConfigComponentHolder) {
            $jacocoInit[2] = true;
        } else {
            autoLoadClass.add(javascriptInvokable);
            $jacocoInit[3] = true;
        }
    }

    public static void doScanConfig() {
        boolean[] $jacocoInit = $jacocoInit();
        if (scanDelay > 0) {
            $jacocoInit[4] = true;
            WXSDKManager.getInstance().getWXRenderManager().postOnUiThread((Runnable) new Runnable() {
                private static transient /* synthetic */ boolean[] $jacocoData;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-2683478314499318234L, "com/taobao/weex/ui/config/AutoScanConfigRegister$1", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    $jacocoInit()[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    AutoScanConfigRegister.doScanConfigAsync();
                    $jacocoInit[1] = true;
                }
            }, scanDelay);
            $jacocoInit[5] = true;
        } else {
            doScanConfigAsync();
            $jacocoInit[6] = true;
        }
        $jacocoInit[7] = true;
    }

    public static void doScanConfigAsync() {
        boolean[] $jacocoInit = $jacocoInit();
        Thread thread = new Thread(new Runnable() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(5957675365744800226L, "com/taobao/weex/ui/config/AutoScanConfigRegister$2", 10);
                $jacocoData = a2;
                return a2;
            }

            {
                $jacocoInit()[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                AutoScanConfigRegister.access$000();
                $jacocoInit[1] = true;
                JavascriptInvokable javascriptInvokable = (JavascriptInvokable) AutoScanConfigRegister.access$100().poll();
                $jacocoInit[2] = true;
                int i = 0;
                while (javascriptInvokable != null) {
                    $jacocoInit[3] = true;
                    javascriptInvokable.getMethods();
                    $jacocoInit[4] = true;
                    javascriptInvokable = (JavascriptInvokable) AutoScanConfigRegister.access$100().poll();
                    i++;
                    $jacocoInit[5] = true;
                }
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[6] = true;
                } else {
                    $jacocoInit[7] = true;
                    WXLogUtils.d("WeexScanConfigRegister", "auto preload class's methods count " + i);
                    $jacocoInit[8] = true;
                }
                $jacocoInit[9] = true;
            }
        });
        $jacocoInit[8] = true;
        thread.setName("AutoScanConfigRegister");
        $jacocoInit[9] = true;
        thread.start();
        $jacocoInit[10] = true;
    }

    private static void doScanConfigSync() {
        String[] strArr;
        boolean[] $jacocoInit = $jacocoInit();
        if (WXEnvironment.sApplication != null) {
            $jacocoInit[11] = true;
            try {
                AssetManager assets = WXEnvironment.sApplication.getApplicationContext().getAssets();
                String[] strArr2 = new String[0];
                try {
                    $jacocoInit[13] = true;
                    strArr = assets.list("");
                    $jacocoInit[14] = true;
                } catch (IOException e) {
                    $jacocoInit[15] = true;
                    WXLogUtils.e("WeexScanConfigRegister", (Throwable) e);
                    $jacocoInit[16] = true;
                    strArr = strArr2;
                }
                if (strArr == null) {
                    $jacocoInit[17] = true;
                } else if (strArr.length != 0) {
                    $jacocoInit[18] = true;
                    int length = strArr.length;
                    $jacocoInit[21] = true;
                    int i = 0;
                    while (i < length) {
                        String str = strArr[i];
                        $jacocoInit[22] = true;
                        if (TextUtils.isEmpty(str)) {
                            $jacocoInit[23] = true;
                        } else if (!str.startsWith("weex_config_")) {
                            $jacocoInit[24] = true;
                        } else if (!str.endsWith(".json")) {
                            $jacocoInit[25] = true;
                        } else {
                            $jacocoInit[26] = true;
                            if (!TextUtils.isEmpty(str)) {
                                $jacocoInit[27] = true;
                                try {
                                    String loadAsset = WXFileUtils.loadAsset(str, WXEnvironment.getApplication());
                                    $jacocoInit[29] = true;
                                    if (!TextUtils.isEmpty(loadAsset)) {
                                        $jacocoInit[30] = true;
                                        if (!WXEnvironment.isApkDebugable()) {
                                            $jacocoInit[32] = true;
                                        } else {
                                            $jacocoInit[33] = true;
                                            WXLogUtils.d("WeexScanConfigRegister", str + " find config " + loadAsset);
                                            $jacocoInit[34] = true;
                                        }
                                        JSONObject parseObject = JSON.parseObject(loadAsset);
                                        $jacocoInit[35] = true;
                                        if (!parseObject.containsKey("modules")) {
                                            $jacocoInit[36] = true;
                                        } else {
                                            $jacocoInit[37] = true;
                                            JSONArray jSONArray = parseObject.getJSONArray("modules");
                                            $jacocoInit[38] = true;
                                            $jacocoInit[39] = true;
                                            int i2 = 0;
                                            while (i2 < jSONArray.size()) {
                                                $jacocoInit[41] = true;
                                                ConfigModuleFactory fromConfig = ConfigModuleFactory.fromConfig(jSONArray.getJSONObject(i2));
                                                if (fromConfig == null) {
                                                    $jacocoInit[42] = true;
                                                } else {
                                                    WXSDKEngine.registerModule(fromConfig.getName(), (ModuleFactory) fromConfig, false);
                                                    $jacocoInit[43] = true;
                                                }
                                                i2++;
                                                $jacocoInit[44] = true;
                                            }
                                            $jacocoInit[40] = true;
                                        }
                                        if (!parseObject.containsKey("components")) {
                                            $jacocoInit[45] = true;
                                        } else {
                                            $jacocoInit[46] = true;
                                            JSONArray jSONArray2 = parseObject.getJSONArray("components");
                                            $jacocoInit[47] = true;
                                            $jacocoInit[48] = true;
                                            int i3 = 0;
                                            while (i3 < jSONArray2.size()) {
                                                $jacocoInit[50] = true;
                                                ConfigComponentHolder fromConfig2 = ConfigComponentHolder.fromConfig(jSONArray2.getJSONObject(i3));
                                                if (fromConfig2 != null) {
                                                    $jacocoInit[51] = true;
                                                    WXSDKEngine.registerComponent((IFComponentHolder) fromConfig2, fromConfig2.isAppendTree(), fromConfig2.getType());
                                                    i3++;
                                                    $jacocoInit[53] = true;
                                                } else {
                                                    $jacocoInit[52] = true;
                                                    return;
                                                }
                                            }
                                            $jacocoInit[49] = true;
                                        }
                                        $jacocoInit[54] = true;
                                    } else {
                                        $jacocoInit[31] = true;
                                    }
                                } catch (Throwable th) {
                                    $jacocoInit[55] = true;
                                    WXLogUtils.e("WeexScanConfigRegister", th);
                                    $jacocoInit[56] = true;
                                }
                            } else {
                                $jacocoInit[28] = true;
                                return;
                            }
                        }
                        i++;
                        $jacocoInit[57] = true;
                    }
                    $jacocoInit[58] = true;
                    $jacocoInit[61] = true;
                    return;
                } else {
                    $jacocoInit[19] = true;
                }
                $jacocoInit[20] = true;
            } catch (Exception e2) {
                $jacocoInit[59] = true;
                WXLogUtils.e("WeexScanConfigRegister", (Throwable) e2);
                $jacocoInit[60] = true;
            }
        } else {
            $jacocoInit[12] = true;
        }
    }

    public static void setScanDelay(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        scanDelay = j;
        $jacocoInit[62] = true;
    }
}
