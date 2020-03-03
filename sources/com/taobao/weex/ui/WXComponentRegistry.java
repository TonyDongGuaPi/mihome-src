package com.taobao.weex.ui;

import android.text.TextUtils;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.config.AutoScanConfigRegister;
import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXComponentRegistry {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static ArrayList<Map<String, Object>> sComponentInfos = new ArrayList<>();
    private static Map<String, IFComponentHolder> sTypeComponentMap = new ConcurrentHashMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6681860475287583097L, "com/taobao/weex/ui/WXComponentRegistry", 22);
        $jacocoData = a2;
        return a2;
    }

    public WXComponentRegistry() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ boolean access$000(String str, IFComponentHolder iFComponentHolder) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerNativeComponent = registerNativeComponent(str, iFComponentHolder);
        $jacocoInit[17] = true;
        return registerNativeComponent;
    }

    static /* synthetic */ boolean access$100(Map map) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerJSComponent = registerJSComponent(map);
        $jacocoInit[18] = true;
        return registerJSComponent;
    }

    static /* synthetic */ ArrayList access$200() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList<Map<String, Object>> arrayList = sComponentInfos;
        $jacocoInit[19] = true;
        return arrayList;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[20] = true;
        $jacocoInit[21] = true;
    }

    public static synchronized boolean registerComponent(final String str, final IFComponentHolder iFComponentHolder, final Map<String, Object> map) throws WXException {
        synchronized (WXComponentRegistry.class) {
            boolean[] $jacocoInit = $jacocoInit();
            if (iFComponentHolder == null) {
                $jacocoInit[1] = true;
            } else if (TextUtils.isEmpty(str)) {
                $jacocoInit[2] = true;
            } else {
                AutoScanConfigRegister.preLoad(iFComponentHolder);
                $jacocoInit[4] = true;
                WXBridgeManager instance = WXBridgeManager.getInstance();
                AnonymousClass1 r4 = new Runnable() {
                    private static transient /* synthetic */ boolean[] $jacocoData;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-7201494647578073638L, "com/taobao/weex/ui/WXComponentRegistry$1", 12);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        try {
                            Map map = map;
                            if (map != null) {
                                $jacocoInit[1] = true;
                            } else {
                                $jacocoInit[2] = true;
                                map = new HashMap();
                                $jacocoInit[3] = true;
                            }
                            map.put("type", str);
                            $jacocoInit[4] = true;
                            map.put("methods", iFComponentHolder.getMethods());
                            $jacocoInit[5] = true;
                            WXComponentRegistry.access$000(str, iFComponentHolder);
                            $jacocoInit[6] = true;
                            WXComponentRegistry.access$100(map);
                            $jacocoInit[7] = true;
                            WXComponentRegistry.access$200().add(map);
                            $jacocoInit[8] = true;
                        } catch (WXException e) {
                            $jacocoInit[9] = true;
                            WXLogUtils.e("register component error:", (Throwable) e);
                            $jacocoInit[10] = true;
                        }
                        $jacocoInit[11] = true;
                    }
                };
                $jacocoInit[5] = true;
                instance.post(r4);
                $jacocoInit[6] = true;
                return true;
            }
            $jacocoInit[3] = true;
            return false;
        }
    }

    private static boolean registerNativeComponent(String str, IFComponentHolder iFComponentHolder) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            iFComponentHolder.loadIfNonLazy();
            $jacocoInit[7] = true;
            sTypeComponentMap.put(str, iFComponentHolder);
            $jacocoInit[8] = true;
        } catch (ArrayStoreException e) {
            $jacocoInit[9] = true;
            e.printStackTrace();
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
        return true;
    }

    private static boolean registerJSComponent(Map<String, Object> map) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList();
        $jacocoInit[12] = true;
        arrayList.add(map);
        $jacocoInit[13] = true;
        WXSDKManager.getInstance().registerComponents(arrayList);
        $jacocoInit[14] = true;
        return true;
    }

    public static IFComponentHolder getComponent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        IFComponentHolder iFComponentHolder = sTypeComponentMap.get(str);
        $jacocoInit[15] = true;
        return iFComponentHolder;
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
                boolean[] a2 = Offline.a(4386828756176166611L, "com/taobao/weex/ui/WXComponentRegistry$2", 8);
                $jacocoData = a2;
                return a2;
            }

            {
                $jacocoInit()[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                try {
                    Iterator it = WXComponentRegistry.access$200().iterator();
                    $jacocoInit[1] = true;
                    while (it.hasNext()) {
                        $jacocoInit[2] = true;
                        WXComponentRegistry.access$100((Map) it.next());
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                } catch (WXException e) {
                    $jacocoInit[5] = true;
                    WXLogUtils.e("", (Throwable) e);
                    $jacocoInit[6] = true;
                }
                $jacocoInit[7] = true;
            }
        });
        $jacocoInit[16] = true;
    }
}
