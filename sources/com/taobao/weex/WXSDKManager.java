package com.taobao.weex;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.taobao.weex.adapter.ClassLoaderAdapter;
import com.taobao.weex.adapter.DefaultUriAdapter;
import com.taobao.weex.adapter.DefaultWXHttpAdapter;
import com.taobao.weex.adapter.ICrashInfoReporter;
import com.taobao.weex.adapter.IDrawableLoader;
import com.taobao.weex.adapter.ITracingAdapter;
import com.taobao.weex.adapter.IWXAccessibilityRoleAdapter;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.adapter.IWXJsFileLoaderAdapter;
import com.taobao.weex.adapter.IWXJscProcessManager;
import com.taobao.weex.adapter.IWXSoLoaderAdapter;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.appfram.navigator.IActivityNavBarSetter;
import com.taobao.weex.appfram.navigator.INavigator;
import com.taobao.weex.appfram.storage.DefaultWXStorage;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapterFactory;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.bridge.WXModuleManager;
import com.taobao.weex.bridge.WXValidateProcessor;
import com.taobao.weex.common.WXRefreshData;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.common.WXWorkThreadManager;
import com.taobao.weex.performance.IApmGenerator;
import com.taobao.weex.performance.IWXAnalyzer;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSDKManager {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int DEFAULT_VIEWPORT_WIDTH = 750;
    private static AtomicInteger sInstanceId = new AtomicInteger(0);
    private static volatile WXSDKManager sManager;
    private IActivityNavBarSetter mActivityNavBarSetter;
    private Map<String, WXSDKInstance> mAllInstanceMap;
    private IApmGenerator mApmGenerater;
    private WXBridgeManager mBridgeManager;
    private ClassLoaderAdapter mClassLoaderAdapter;
    private ICrashInfoReporter mCrashInfo;
    private IDrawableLoader mDrawableLoader;
    private IWXHttpAdapter mIWXHttpAdapter;
    private IWXImgLoaderAdapter mIWXImgLoaderAdapter;
    private IWXJSExceptionAdapter mIWXJSExceptionAdapter;
    private IWXSoLoaderAdapter mIWXSoLoaderAdapter;
    private IWXStorageAdapter mIWXStorageAdapter;
    private IWXUserTrackAdapter mIWXUserTrackAdapter;
    private IWebSocketAdapterFactory mIWebSocketAdapterFactory;
    private List<InstanceLifeCycleCallbacks> mLifeCycleCallbacks;
    private INavigator mNavigator;
    private boolean mNeedInitV8;
    private IWXAccessibilityRoleAdapter mRoleAdapter;
    private IWXStatisticsListener mStatisticsListener;
    private ITracingAdapter mTracingAdapter;
    private URIAdapter mURIAdapter;
    private List<IWXAnalyzer> mWXAnalyzerList;
    private IWXJsFileLoaderAdapter mWXJsFileLoaderAdapter;
    private IWXJscProcessManager mWXJscProcessManager;
    WXRenderManager mWXRenderManager;
    private WXValidateProcessor mWXValidateProcessor;
    private final WXWorkThreadManager mWXWorkThreadManager;

    public interface InstanceLifeCycleCallbacks {
        void onInstanceCreated(String str);

        void onInstanceDestroyed(String str);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8817549406731329558L, "com/taobao/weex/WXSDKManager", 156);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[155] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private WXSDKManager() {
        this(new WXRenderManager());
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    private WXSDKManager(WXRenderManager wXRenderManager) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNeedInitV8 = true;
        this.mWXRenderManager = wXRenderManager;
        $jacocoInit[1] = true;
        this.mBridgeManager = WXBridgeManager.getInstance();
        $jacocoInit[2] = true;
        this.mWXWorkThreadManager = new WXWorkThreadManager();
        $jacocoInit[3] = true;
        this.mWXAnalyzerList = new ArrayList();
        $jacocoInit[4] = true;
        this.mAllInstanceMap = new HashMap();
        $jacocoInit[5] = true;
    }

    static void initInstance(WXRenderManager wXRenderManager) {
        boolean[] $jacocoInit = $jacocoInit();
        sManager = new WXSDKManager(wXRenderManager);
        $jacocoInit[6] = true;
    }

    public void registerStatisticsListener(IWXStatisticsListener iWXStatisticsListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStatisticsListener = iWXStatisticsListener;
        $jacocoInit[7] = true;
    }

    public IWXStatisticsListener getWXStatisticsListener() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStatisticsListener iWXStatisticsListener = this.mStatisticsListener;
        $jacocoInit[8] = true;
        return iWXStatisticsListener;
    }

    public void onSDKEngineInitialize() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStatisticsListener == null) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
            this.mStatisticsListener.onSDKEngineInitialize();
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
    }

    public void setNeedInitV8(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNeedInitV8 = z;
        $jacocoInit[13] = true;
    }

    public boolean needInitV8() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mNeedInitV8;
        $jacocoInit[14] = true;
        return z;
    }

    public void takeJSHeapSnapshot(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        File file = new File(str);
        $jacocoInit[15] = true;
        if (file.exists()) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            if (file.mkdir()) {
                $jacocoInit[18] = true;
            } else {
                $jacocoInit[19] = true;
                return;
            }
        }
        String valueOf = String.valueOf(sInstanceId.get());
        $jacocoInit[20] = true;
        if (str.endsWith(File.separator)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            str = str + File.separator;
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
        $jacocoInit[25] = true;
        this.mBridgeManager.takeJSHeapSnapshot((str + valueOf) + ".heapsnapshot");
        $jacocoInit[26] = true;
    }

    public static WXSDKManager getInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        if (sManager != null) {
            $jacocoInit[27] = true;
        } else {
            synchronized (WXSDKManager.class) {
                try {
                    $jacocoInit[28] = true;
                    if (sManager != null) {
                        $jacocoInit[29] = true;
                    } else {
                        $jacocoInit[30] = true;
                        sManager = new WXSDKManager();
                        $jacocoInit[31] = true;
                    }
                } catch (Throwable th) {
                    while (true) {
                        $jacocoInit[33] = true;
                        throw th;
                    }
                }
            }
            $jacocoInit[32] = true;
        }
        WXSDKManager wXSDKManager = sManager;
        $jacocoInit[34] = true;
        return wXSDKManager;
    }

    public static int getInstanceViewPortWidth(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = getInstance().getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[35] = true;
            return DEFAULT_VIEWPORT_WIDTH;
        }
        int instanceViewPortWidth = sDKInstance.getInstanceViewPortWidth();
        $jacocoInit[36] = true;
        return instanceViewPortWidth;
    }

    static void setInstance(WXSDKManager wXSDKManager) {
        boolean[] $jacocoInit = $jacocoInit();
        sManager = wXSDKManager;
        $jacocoInit[37] = true;
    }

    public IActivityNavBarSetter getActivityNavBarSetter() {
        boolean[] $jacocoInit = $jacocoInit();
        IActivityNavBarSetter iActivityNavBarSetter = this.mActivityNavBarSetter;
        $jacocoInit[38] = true;
        return iActivityNavBarSetter;
    }

    public void setActivityNavBarSetter(IActivityNavBarSetter iActivityNavBarSetter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mActivityNavBarSetter = iActivityNavBarSetter;
        $jacocoInit[39] = true;
    }

    public void restartBridge() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.restart();
        $jacocoInit[40] = true;
    }

    public WXBridgeManager getWXBridgeManager() {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager wXBridgeManager = this.mBridgeManager;
        $jacocoInit[41] = true;
        return wXBridgeManager;
    }

    public WXRenderManager getWXRenderManager() {
        boolean[] $jacocoInit = $jacocoInit();
        WXRenderManager wXRenderManager = this.mWXRenderManager;
        $jacocoInit[42] = true;
        return wXRenderManager;
    }

    public IWXJscProcessManager getWXJscProcessManager() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJscProcessManager iWXJscProcessManager = this.mWXJscProcessManager;
        $jacocoInit[43] = true;
        return iWXJscProcessManager;
    }

    public WXWorkThreadManager getWXWorkThreadManager() {
        boolean[] $jacocoInit = $jacocoInit();
        WXWorkThreadManager wXWorkThreadManager = this.mWXWorkThreadManager;
        $jacocoInit[44] = true;
        return wXWorkThreadManager;
    }

    @Nullable
    public WXSDKInstance getSDKInstance(String str) {
        WXSDKInstance wXSDKInstance;
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            wXSDKInstance = null;
            $jacocoInit[45] = true;
        } else {
            wXSDKInstance = this.mWXRenderManager.getWXSDKInstance(str);
            $jacocoInit[46] = true;
        }
        $jacocoInit[47] = true;
        return wXSDKInstance;
    }

    public void postOnUiThread(Runnable runnable, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXRenderManager.postOnUiThread(WXThread.secure(runnable), j);
        $jacocoInit[48] = true;
    }

    public Map<String, WXSDKInstance> getAllInstanceMap() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, WXSDKInstance> map = this.mAllInstanceMap;
        $jacocoInit[49] = true;
        return map;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXWorkThreadManager == null) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            this.mWXWorkThreadManager.destroy();
            $jacocoInit[52] = true;
        }
        this.mAllInstanceMap.clear();
        $jacocoInit[53] = true;
    }

    @Deprecated
    public void callback(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.callback(str, str2, map);
        $jacocoInit[54] = true;
    }

    @Deprecated
    public void callback(String str, String str2, Map<String, Object> map, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.callback(str, str2, map, z);
        $jacocoInit[55] = true;
    }

    public void initScriptsFramework(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.initScriptsFramework(str);
        $jacocoInit[56] = true;
    }

    public void registerComponents(List<Map<String, Object>> list) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.registerComponents(list);
        $jacocoInit[57] = true;
    }

    public void registerModules(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.registerModules(map);
        $jacocoInit[58] = true;
    }

    @Deprecated
    public void fireEvent(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, str3, new HashMap());
        $jacocoInit[59] = true;
    }

    @Deprecated
    public void fireEvent(String str, String str2, String str3, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, str3, map, (Map<String, Object>) null);
        $jacocoInit[60] = true;
    }

    @Deprecated
    public void fireEvent(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[61] = true;
        } else if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
            $jacocoInit[62] = true;
        } else {
            $jacocoInit[63] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("[WXSDKManager]  fireEvent error");
            $jacocoInit[64] = true;
            throw wXRuntimeException;
        }
        this.mBridgeManager.fireEventOnNode(str, str2, str3, map, map2);
        $jacocoInit[65] = true;
    }

    /* access modifiers changed from: package-private */
    public void createInstance(WXSDKInstance wXSDKInstance, Script script, Map<String, Object> map, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXRenderManager.registerInstance(wXSDKInstance);
        $jacocoInit[66] = true;
        this.mBridgeManager.createInstance(wXSDKInstance.getInstanceId(), script, map, str);
        if (this.mLifeCycleCallbacks == null) {
            $jacocoInit[67] = true;
        } else {
            $jacocoInit[68] = true;
            $jacocoInit[69] = true;
            for (InstanceLifeCycleCallbacks onInstanceCreated : this.mLifeCycleCallbacks) {
                $jacocoInit[71] = true;
                onInstanceCreated.onInstanceCreated(wXSDKInstance.getInstanceId());
                $jacocoInit[72] = true;
            }
            $jacocoInit[70] = true;
        }
        $jacocoInit[73] = true;
    }

    /* access modifiers changed from: package-private */
    public void refreshInstance(String str, WXRefreshData wXRefreshData) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.refreshInstance(str, wXRefreshData);
        $jacocoInit[74] = true;
    }

    /* access modifiers changed from: package-private */
    public void destroyInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        setCrashInfo(WXEnvironment.WEEX_CURRENT_KEY, "");
        $jacocoInit[75] = true;
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[76] = true;
        } else if (WXUtils.isUiThread()) {
            if (this.mLifeCycleCallbacks == null) {
                $jacocoInit[79] = true;
            } else {
                $jacocoInit[80] = true;
                $jacocoInit[81] = true;
                for (InstanceLifeCycleCallbacks onInstanceDestroyed : this.mLifeCycleCallbacks) {
                    $jacocoInit[83] = true;
                    onInstanceDestroyed.onInstanceDestroyed(str);
                    $jacocoInit[84] = true;
                }
                $jacocoInit[82] = true;
            }
            this.mWXRenderManager.removeRenderStatement(str);
            $jacocoInit[85] = true;
            this.mBridgeManager.destroyInstance(str);
            $jacocoInit[86] = true;
            WXModuleManager.destroyInstanceModules(str);
            $jacocoInit[87] = true;
        } else {
            $jacocoInit[77] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("[WXSDKManager] destroyInstance error");
            $jacocoInit[78] = true;
            throw wXRuntimeException;
        }
    }

    /* access modifiers changed from: package-private */
    public String generateInstanceId() {
        boolean[] $jacocoInit = $jacocoInit();
        String valueOf = String.valueOf(sInstanceId.incrementAndGet());
        $jacocoInit[88] = true;
        return valueOf;
    }

    public IWXUserTrackAdapter getIWXUserTrackAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXUserTrackAdapter iWXUserTrackAdapter = this.mIWXUserTrackAdapter;
        $jacocoInit[89] = true;
        return iWXUserTrackAdapter;
    }

    public IWXImgLoaderAdapter getIWXImgLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXImgLoaderAdapter iWXImgLoaderAdapter = this.mIWXImgLoaderAdapter;
        $jacocoInit[90] = true;
        return iWXImgLoaderAdapter;
    }

    public IWXJsFileLoaderAdapter getIWXJsFileLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJsFileLoaderAdapter iWXJsFileLoaderAdapter = this.mWXJsFileLoaderAdapter;
        $jacocoInit[91] = true;
        return iWXJsFileLoaderAdapter;
    }

    public IDrawableLoader getDrawableLoader() {
        boolean[] $jacocoInit = $jacocoInit();
        IDrawableLoader iDrawableLoader = this.mDrawableLoader;
        $jacocoInit[92] = true;
        return iDrawableLoader;
    }

    public IWXJSExceptionAdapter getIWXJSExceptionAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJSExceptionAdapter iWXJSExceptionAdapter = this.mIWXJSExceptionAdapter;
        $jacocoInit[93] = true;
        return iWXJSExceptionAdapter;
    }

    public void setIWXJSExceptionAdapter(IWXJSExceptionAdapter iWXJSExceptionAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIWXJSExceptionAdapter = iWXJSExceptionAdapter;
        $jacocoInit[94] = true;
    }

    @NonNull
    public IWXHttpAdapter getIWXHttpAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mIWXHttpAdapter != null) {
            $jacocoInit[95] = true;
        } else {
            $jacocoInit[96] = true;
            this.mIWXHttpAdapter = new DefaultWXHttpAdapter();
            $jacocoInit[97] = true;
        }
        IWXHttpAdapter iWXHttpAdapter = this.mIWXHttpAdapter;
        $jacocoInit[98] = true;
        return iWXHttpAdapter;
    }

    public IApmGenerator getApmGenerater() {
        boolean[] $jacocoInit = $jacocoInit();
        IApmGenerator iApmGenerator = this.mApmGenerater;
        $jacocoInit[99] = true;
        return iApmGenerator;
    }

    @NonNull
    public URIAdapter getURIAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mURIAdapter != null) {
            $jacocoInit[100] = true;
        } else {
            $jacocoInit[101] = true;
            this.mURIAdapter = new DefaultUriAdapter();
            $jacocoInit[102] = true;
        }
        URIAdapter uRIAdapter = this.mURIAdapter;
        $jacocoInit[103] = true;
        return uRIAdapter;
    }

    public ClassLoaderAdapter getClassLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mClassLoaderAdapter != null) {
            $jacocoInit[104] = true;
        } else {
            $jacocoInit[105] = true;
            this.mClassLoaderAdapter = new ClassLoaderAdapter();
            $jacocoInit[106] = true;
        }
        ClassLoaderAdapter classLoaderAdapter = this.mClassLoaderAdapter;
        $jacocoInit[107] = true;
        return classLoaderAdapter;
    }

    public IWXSoLoaderAdapter getIWXSoLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXSoLoaderAdapter iWXSoLoaderAdapter = this.mIWXSoLoaderAdapter;
        $jacocoInit[108] = true;
        return iWXSoLoaderAdapter;
    }

    public List<IWXAnalyzer> getWXAnalyzerList() {
        boolean[] $jacocoInit = $jacocoInit();
        List<IWXAnalyzer> list = this.mWXAnalyzerList;
        $jacocoInit[109] = true;
        return list;
    }

    public void addWXAnalyzer(IWXAnalyzer iWXAnalyzer) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXAnalyzerList.contains(iWXAnalyzer)) {
            $jacocoInit[110] = true;
        } else {
            $jacocoInit[111] = true;
            this.mWXAnalyzerList.add(iWXAnalyzer);
            $jacocoInit[112] = true;
        }
        $jacocoInit[113] = true;
    }

    public void rmWXAnalyzer(IWXAnalyzer iWXAnalyzer) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXAnalyzerList.remove(iWXAnalyzer);
        $jacocoInit[114] = true;
    }

    /* access modifiers changed from: package-private */
    public void setInitConfig(InitConfig initConfig) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIWXHttpAdapter = initConfig.getHttpAdapter();
        $jacocoInit[115] = true;
        this.mIWXImgLoaderAdapter = initConfig.getImgAdapter();
        $jacocoInit[116] = true;
        this.mDrawableLoader = initConfig.getDrawableLoader();
        $jacocoInit[117] = true;
        this.mIWXStorageAdapter = initConfig.getStorageAdapter();
        $jacocoInit[118] = true;
        this.mIWXUserTrackAdapter = initConfig.getUtAdapter();
        $jacocoInit[119] = true;
        this.mURIAdapter = initConfig.getURIAdapter();
        $jacocoInit[120] = true;
        this.mIWebSocketAdapterFactory = initConfig.getWebSocketAdapterFactory();
        $jacocoInit[121] = true;
        this.mIWXJSExceptionAdapter = initConfig.getJSExceptionAdapter();
        $jacocoInit[122] = true;
        this.mIWXSoLoaderAdapter = initConfig.getIWXSoLoaderAdapter();
        $jacocoInit[123] = true;
        this.mClassLoaderAdapter = initConfig.getClassLoaderAdapter();
        $jacocoInit[124] = true;
        this.mApmGenerater = initConfig.getApmGenerater();
        $jacocoInit[125] = true;
        this.mWXJsFileLoaderAdapter = initConfig.getJsFileLoaderAdapter();
        $jacocoInit[126] = true;
        this.mWXJscProcessManager = initConfig.getJscProcessManager();
        $jacocoInit[127] = true;
    }

    public IWXStorageAdapter getIWXStorageAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mIWXStorageAdapter != null) {
            $jacocoInit[128] = true;
        } else if (WXEnvironment.sApplication != null) {
            $jacocoInit[129] = true;
            this.mIWXStorageAdapter = new DefaultWXStorage(WXEnvironment.sApplication);
            $jacocoInit[130] = true;
        } else {
            WXLogUtils.e("WXStorageModule", "No Application context found,you should call WXSDKEngine#initialize() method in your application");
            $jacocoInit[131] = true;
        }
        IWXStorageAdapter iWXStorageAdapter = this.mIWXStorageAdapter;
        $jacocoInit[132] = true;
        return iWXStorageAdapter;
    }

    public void notifyTrimMemory() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.notifyTrimMemory();
        $jacocoInit[133] = true;
    }

    public void notifySerializeCodeCache() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBridgeManager.notifySerializeCodeCache();
        $jacocoInit[134] = true;
    }

    @Nullable
    public IWebSocketAdapter getIWXWebSocketAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mIWebSocketAdapterFactory != null) {
            $jacocoInit[135] = true;
            IWebSocketAdapter createWebSocketAdapter = this.mIWebSocketAdapterFactory.createWebSocketAdapter();
            $jacocoInit[136] = true;
            return createWebSocketAdapter;
        }
        $jacocoInit[137] = true;
        return null;
    }

    public void registerValidateProcessor(WXValidateProcessor wXValidateProcessor) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXValidateProcessor = wXValidateProcessor;
        $jacocoInit[138] = true;
    }

    public WXValidateProcessor getValidateProcessor() {
        boolean[] $jacocoInit = $jacocoInit();
        WXValidateProcessor wXValidateProcessor = this.mWXValidateProcessor;
        $jacocoInit[139] = true;
        return wXValidateProcessor;
    }

    public void setCrashInfoReporter(ICrashInfoReporter iCrashInfoReporter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCrashInfo = iCrashInfoReporter;
        $jacocoInit[140] = true;
    }

    public void setCrashInfo(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCrashInfo == null) {
            $jacocoInit[141] = true;
        } else {
            $jacocoInit[142] = true;
            this.mCrashInfo.addCrashInfo(str, str2);
            $jacocoInit[143] = true;
        }
        $jacocoInit[144] = true;
    }

    public void setTracingAdapter(ITracingAdapter iTracingAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTracingAdapter = iTracingAdapter;
        $jacocoInit[145] = true;
    }

    public ITracingAdapter getTracingAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        ITracingAdapter iTracingAdapter = this.mTracingAdapter;
        $jacocoInit[146] = true;
        return iTracingAdapter;
    }

    public void registerInstanceLifeCycleCallbacks(InstanceLifeCycleCallbacks instanceLifeCycleCallbacks) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLifeCycleCallbacks != null) {
            $jacocoInit[147] = true;
        } else {
            $jacocoInit[148] = true;
            this.mLifeCycleCallbacks = new ArrayList();
            $jacocoInit[149] = true;
        }
        this.mLifeCycleCallbacks.add(instanceLifeCycleCallbacks);
        $jacocoInit[150] = true;
    }

    public void setAccessibilityRoleAdapter(IWXAccessibilityRoleAdapter iWXAccessibilityRoleAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRoleAdapter = iWXAccessibilityRoleAdapter;
        $jacocoInit[151] = true;
    }

    public IWXAccessibilityRoleAdapter getAccessibilityRoleAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXAccessibilityRoleAdapter iWXAccessibilityRoleAdapter = this.mRoleAdapter;
        $jacocoInit[152] = true;
        return iWXAccessibilityRoleAdapter;
    }

    public INavigator getNavigator() {
        boolean[] $jacocoInit = $jacocoInit();
        INavigator iNavigator = this.mNavigator;
        $jacocoInit[153] = true;
        return iNavigator;
    }

    public void setNavigator(INavigator iNavigator) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNavigator = iNavigator;
        $jacocoInit[154] = true;
    }
}
