package com.taobao.weex.bridge;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.Script;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.bridge.WXValidateProcessor;
import com.taobao.weex.common.IWXBridge;
import com.taobao.weex.common.IWXDebugConfig;
import com.taobao.weex.common.WXConfig;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXJSExceptionInfo;
import com.taobao.weex.common.WXRefreshData;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.WXComponentRegistry;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.ui.action.ActionReloadPage;
import com.taobao.weex.ui.action.GraphicActionAddElement;
import com.taobao.weex.ui.action.GraphicActionAddEvent;
import com.taobao.weex.ui.action.GraphicActionAppendTreeCreateFinish;
import com.taobao.weex.ui.action.GraphicActionCreateBody;
import com.taobao.weex.ui.action.GraphicActionCreateFinish;
import com.taobao.weex.ui.action.GraphicActionLayout;
import com.taobao.weex.ui.action.GraphicActionMoveElement;
import com.taobao.weex.ui.action.GraphicActionRefreshFinish;
import com.taobao.weex.ui.action.GraphicActionRemoveElement;
import com.taobao.weex.ui.action.GraphicActionRemoveEvent;
import com.taobao.weex.ui.action.GraphicActionRenderSuccess;
import com.taobao.weex.ui.action.GraphicActionUpdateAttr;
import com.taobao.weex.ui.action.GraphicActionUpdateStyle;
import com.taobao.weex.ui.action.GraphicPosition;
import com.taobao.weex.ui.action.GraphicSize;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.module.WXDomModule;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXJsonUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.taobao.weex.utils.WXWsonJSONSwitch;
import com.taobao.weex.utils.batch.BactchExecutor;
import com.taobao.weex.utils.batch.Interceptor;
import com.xiaomi.stat.d;
import com.xiaomi.verificationsdk.internal.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXBridgeManager implements Handler.Callback, BactchExecutor {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ARGS = "args";
    private static final boolean BRIDGE_LOG_SWITCH = false;
    private static final String BUNDLE_TYPE = "bundleType";
    public static final String COMPONENT = "component";
    private static final int CRASHREINIT = 50;
    private static String GLOBAL_CONFIG_KEY = "global_switch_config";
    public static final String INITLOGFILE = "/jsserver_start.log";
    private static final int INIT_FRAMEWORK_OK = 1;
    public static final String KEY_ARGS = "args";
    public static final String KEY_METHOD = "method";
    public static final String KEY_PARAMS = "params";
    private static long LOW_MEM_VALUE = 120;
    public static final String METHD_COMPONENT_HOOK_SYNC = "componentHook";
    public static final String METHD_FIRE_EVENT_SYNC = "fireEventSync";
    public static final String METHOD = "method";
    public static final String METHOD_CALLBACK = "callback";
    public static final String METHOD_CALL_JS = "callJS";
    public static final String METHOD_CREATE_INSTANCE = "createInstance";
    public static final String METHOD_CREATE_INSTANCE_CONTEXT = "createInstanceContext";
    public static final String METHOD_DESTROY_INSTANCE = "destroyInstance";
    public static final String METHOD_FIRE_EVENT = "fireEvent";
    public static final String METHOD_FIRE_EVENT_ON_DATA_RENDER_NODE = "fireEventOnDataRenderNode";
    public static final String METHOD_NOTIFY_SERIALIZE_CODE_CACHE = "notifySerializeCodeCache";
    public static final String METHOD_NOTIFY_TRIM_MEMORY = "notifyTrimMemory";
    public static final String METHOD_REFRESH_INSTANCE = "refreshInstance";
    public static final String METHOD_REGISTER_COMPONENTS = "registerComponents";
    public static final String METHOD_REGISTER_MODULES = "registerModules";
    public static final String METHOD_SET_TIMEOUT = "setTimeoutCallback";
    public static final String MODULE = "module";
    private static final String NON_CALLBACK = "-1";
    public static final String OPTIONS = "options";
    public static final String REF = "ref";
    private static final String RENDER_STRATEGY = "renderStrategy";
    private static final String UNDEFINED = "undefined";
    private static Class clazz_debugProxy = null;
    private static String crashUrl = null;
    private static String globalConfig = "none";
    private static volatile boolean isSandBoxContext = true;
    private static boolean isUseSingleProcess = false;
    private static long lastCrashTime = 0;
    static volatile WXBridgeManager mBridgeManager;
    private static volatile boolean mInit = false;
    private static String mRaxApi = null;
    private static Map<String, String> mWeexCoreEnvOptions = new HashMap();
    private static volatile int reInitCount = 1;
    public static StringBuilder sInitFrameWorkMsg = new StringBuilder();
    public static long sInitFrameWorkTimeOrigin;
    private HashSet<String> mDestroyedInstanceId;
    private WXParams mInitParams;
    private Interceptor mInterceptor;
    Handler mJSHandler;
    private WXThread mJSThread;
    private StringBuilder mLodBuilder;
    private boolean mMock = false;
    private WXHashMap<String, ArrayList<WXHashMap<String, Object>>> mNextTickTasks = new WXHashMap<>();
    private List<Map<String, Object>> mRegisterComponentFailList;
    private List<Map<String, Object>> mRegisterModuleFailList;
    private List<String> mRegisterServiceFailList;
    private IWXBridge mWXBridge;
    private Object mWxDebugProxy;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1395098624549852607L, "com/taobao/weex/bridge/WXBridgeManager", 1477);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(WXBridgeManager wXBridgeManager, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.setJSFrameworkInit(z);
        $jacocoInit[1454] = true;
    }

    static /* synthetic */ boolean access$100() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = isSandBoxContext;
        $jacocoInit[1455] = true;
        return z;
    }

    static /* synthetic */ String access$1000(WXBridgeManager wXBridgeManager, String str, String str2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        String invokeExecJSOnInstance = wXBridgeManager.invokeExecJSOnInstance(str, str2, i);
        $jacocoInit[1464] = true;
        return invokeExecJSOnInstance;
    }

    static /* synthetic */ List access$1100(WXBridgeManager wXBridgeManager) {
        boolean[] $jacocoInit = $jacocoInit();
        List<Map<String, Object>> list = wXBridgeManager.mRegisterModuleFailList;
        $jacocoInit[1465] = true;
        return list;
    }

    static /* synthetic */ void access$1200(WXBridgeManager wXBridgeManager, Map map, List list) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeRegisterModules(map, list);
        $jacocoInit[1466] = true;
    }

    static /* synthetic */ List access$1300(WXBridgeManager wXBridgeManager) {
        boolean[] $jacocoInit = $jacocoInit();
        List<Map<String, Object>> list = wXBridgeManager.mRegisterComponentFailList;
        $jacocoInit[1467] = true;
        return list;
    }

    static /* synthetic */ void access$1400(WXBridgeManager wXBridgeManager, List list, List list2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeRegisterComponents(list, list2);
        $jacocoInit[1468] = true;
    }

    static /* synthetic */ List access$1500(WXBridgeManager wXBridgeManager) {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = wXBridgeManager.mRegisterServiceFailList;
        $jacocoInit[1469] = true;
        return list;
    }

    static /* synthetic */ void access$1600(WXBridgeManager wXBridgeManager, String str, List list) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeExecJSService(str, list);
        $jacocoInit[1470] = true;
    }

    static /* synthetic */ boolean access$1700(WXBridgeManager wXBridgeManager) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isJSFrameworkInit = wXBridgeManager.isJSFrameworkInit();
        $jacocoInit[1471] = true;
        return isJSFrameworkInit;
    }

    static /* synthetic */ String access$1800() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = globalConfig;
        $jacocoInit[1472] = true;
        return str;
    }

    static /* synthetic */ void access$1900(WXBridgeManager wXBridgeManager, String str, String str2, String str3, WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeExecJS(str, str2, str3, wXJSObjectArr);
        $jacocoInit[1473] = true;
    }

    static /* synthetic */ void access$200(WXBridgeManager wXBridgeManager, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.initFramework(str);
        $jacocoInit[1456] = true;
    }

    static /* synthetic */ WXHashMap access$300(WXBridgeManager wXBridgeManager) {
        boolean[] $jacocoInit = $jacocoInit();
        WXHashMap<String, ArrayList<WXHashMap<String, Object>>> wXHashMap = wXBridgeManager.mNextTickTasks;
        $jacocoInit[1457] = true;
        return wXHashMap;
    }

    static /* synthetic */ void access$400(WXBridgeManager wXBridgeManager, String str, String str2, String str3, WXJSObject[] wXJSObjectArr, ResultCallback resultCallback, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeExecJSWithCallback(str, str2, str3, wXJSObjectArr, resultCallback, z);
        $jacocoInit[1458] = true;
    }

    static /* synthetic */ IWXBridge access$500(WXBridgeManager wXBridgeManager) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXBridge iWXBridge = wXBridgeManager.mWXBridge;
        $jacocoInit[1459] = true;
        return iWXBridge;
    }

    static /* synthetic */ void access$600(WXBridgeManager wXBridgeManager, String str, WXRefreshData wXRefreshData) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeRefreshInstance(str, wXRefreshData);
        $jacocoInit[1460] = true;
    }

    static /* synthetic */ void access$700(WXBridgeManager wXBridgeManager, WXSDKInstance wXSDKInstance, Script script, Map map, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeCreateInstance(wXSDKInstance, script, map, str);
        $jacocoInit[1461] = true;
    }

    static /* synthetic */ void access$800(WXBridgeManager wXBridgeManager, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.removeTaskByInstance(str);
        $jacocoInit[1462] = true;
    }

    static /* synthetic */ void access$900(WXBridgeManager wXBridgeManager, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wXBridgeManager.invokeDestroyInstance(str);
        $jacocoInit[1463] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1474] = true;
        $jacocoInit[1475] = true;
        $jacocoInit[1476] = true;
    }

    public enum BundType {
        Vue,
        Rax,
        Others;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[5] = true;
        }
    }

    private WXBridgeManager() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mRegisterComponentFailList = new ArrayList(8);
        $jacocoInit[2] = true;
        this.mRegisterModuleFailList = new ArrayList(8);
        $jacocoInit[3] = true;
        this.mRegisterServiceFailList = new ArrayList(8);
        $jacocoInit[4] = true;
        this.mDestroyedInstanceId = new HashSet<>();
        $jacocoInit[5] = true;
        this.mLodBuilder = new StringBuilder(50);
        $jacocoInit[6] = true;
        initWXBridge(WXEnvironment.sRemoteDebugMode);
        $jacocoInit[7] = true;
        this.mJSThread = new WXThread("WeexJSBridgeThread", (Handler.Callback) this);
        $jacocoInit[8] = true;
        this.mJSHandler = this.mJSThread.getHandler();
        $jacocoInit[9] = true;
    }

    public static WXBridgeManager getInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        if (mBridgeManager != null) {
            $jacocoInit[10] = true;
        } else {
            synchronized (WXBridgeManager.class) {
                try {
                    $jacocoInit[11] = true;
                    if (mBridgeManager != null) {
                        $jacocoInit[12] = true;
                    } else {
                        $jacocoInit[13] = true;
                        mBridgeManager = new WXBridgeManager();
                        $jacocoInit[14] = true;
                    }
                } catch (Throwable th) {
                    while (true) {
                        $jacocoInit[16] = true;
                        throw th;
                    }
                }
            }
            $jacocoInit[15] = true;
        }
        WXBridgeManager wXBridgeManager = mBridgeManager;
        $jacocoInit[17] = true;
        return wXBridgeManager;
    }

    public void setUseSingleProcess(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z == isUseSingleProcess) {
            $jacocoInit[18] = true;
        } else {
            isUseSingleProcess = z;
            $jacocoInit[19] = true;
        }
        $jacocoInit[20] = true;
    }

    public void setSandBoxContext(boolean z) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (z == isSandBoxContext) {
            $jacocoInit[21] = true;
        } else {
            isSandBoxContext = z;
            $jacocoInit[22] = true;
            if (isJSThread()) {
                $jacocoInit[23] = true;
                setJSFrameworkInit(false);
                $jacocoInit[24] = true;
                WXModuleManager.resetAllModuleState();
                if (!isSandBoxContext) {
                    $jacocoInit[25] = true;
                    str = WXFileUtils.loadAsset("main.js", WXEnvironment.getApplication());
                    $jacocoInit[26] = true;
                } else {
                    str = WXFileUtils.loadAsset("weex-main-jsfm.js", WXEnvironment.getApplication());
                    $jacocoInit[27] = true;
                }
                initFramework(str);
                $jacocoInit[28] = true;
                WXServiceManager.reload();
                $jacocoInit[29] = true;
                WXModuleManager.reload();
                $jacocoInit[30] = true;
                WXComponentRegistry.reload();
                $jacocoInit[31] = true;
            } else {
                post(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXBridgeManager this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(2755900786562757065L, "com/taobao/weex/bridge/WXBridgeManager$1", 10);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        String str;
                        boolean[] $jacocoInit = $jacocoInit();
                        WXBridgeManager.access$000(this.this$0, false);
                        $jacocoInit[1] = true;
                        WXModuleManager.resetAllModuleState();
                        $jacocoInit[2] = true;
                        if (!WXBridgeManager.access$100()) {
                            $jacocoInit[3] = true;
                            str = WXFileUtils.loadAsset("main.js", WXEnvironment.getApplication());
                            $jacocoInit[4] = true;
                        } else {
                            str = WXFileUtils.loadAsset("weex-main-jsfm.js", WXEnvironment.getApplication());
                            $jacocoInit[5] = true;
                        }
                        WXBridgeManager.access$200(this.this$0, str);
                        $jacocoInit[6] = true;
                        WXServiceManager.reload();
                        $jacocoInit[7] = true;
                        WXModuleManager.reload();
                        $jacocoInit[8] = true;
                        WXComponentRegistry.reload();
                        $jacocoInit[9] = true;
                    }
                }, (Object) null);
                $jacocoInit[32] = true;
            }
        }
        $jacocoInit[33] = true;
    }

    private boolean isJSFrameworkInit() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = mInit;
        $jacocoInit[34] = true;
        return z;
    }

    private void setJSFrameworkInit(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        mInit = z;
        if (!z) {
            $jacocoInit[35] = true;
        } else {
            $jacocoInit[36] = true;
            onJsFrameWorkInitSuccees();
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
    }

    private void initWXBridge(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!z) {
            $jacocoInit[39] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[40] = true;
        } else {
            WXEnvironment.sDebugServerConnectable = true;
            $jacocoInit[41] = true;
        }
        if (!WXEnvironment.sDebugServerConnectable) {
            $jacocoInit[42] = true;
        } else {
            if (WXEnvironment.isApkDebugable()) {
                $jacocoInit[43] = true;
            } else {
                $jacocoInit[44] = true;
            }
            if (WXEnvironment.getApplication() != null) {
                try {
                    $jacocoInit[45] = true;
                    if (clazz_debugProxy != null) {
                        $jacocoInit[46] = true;
                    } else {
                        $jacocoInit[47] = true;
                        clazz_debugProxy = Class.forName("com.taobao.weex.devtools.debug.DebugServerProxy");
                        $jacocoInit[48] = true;
                    }
                    if (clazz_debugProxy == null) {
                        $jacocoInit[49] = true;
                    } else {
                        $jacocoInit[50] = true;
                        Constructor constructor = clazz_debugProxy.getConstructor(new Class[]{Context.class, IWXDebugConfig.class});
                        if (constructor == null) {
                            $jacocoInit[51] = true;
                        } else {
                            $jacocoInit[52] = true;
                            this.mWxDebugProxy = constructor.newInstance(new Object[]{WXEnvironment.getApplication(), new IWXDebugConfig(this) {
                                private static transient /* synthetic */ boolean[] $jacocoData;
                                final /* synthetic */ WXBridgeManager this$0;

                                private static /* synthetic */ boolean[] $jacocoInit() {
                                    boolean[] zArr = $jacocoData;
                                    if (zArr != null) {
                                        return zArr;
                                    }
                                    boolean[] a2 = Offline.a(-5959912918094800142L, "com/taobao/weex/bridge/WXBridgeManager$2", 3);
                                    $jacocoData = a2;
                                    return a2;
                                }

                                {
                                    boolean[] $jacocoInit = $jacocoInit();
                                    this.this$0 = r3;
                                    $jacocoInit[0] = true;
                                }

                                public WXBridgeManager getWXJSManager() {
                                    boolean[] $jacocoInit = $jacocoInit();
                                    WXBridgeManager wXBridgeManager = this.this$0;
                                    $jacocoInit[1] = true;
                                    return wXBridgeManager;
                                }

                                public WXDebugJsBridge getWXDebugJsBridge() {
                                    boolean[] $jacocoInit = $jacocoInit();
                                    WXDebugJsBridge wXDebugJsBridge = new WXDebugJsBridge();
                                    $jacocoInit[2] = true;
                                    return wXDebugJsBridge;
                                }
                            }});
                            if (this.mWxDebugProxy == null) {
                                $jacocoInit[53] = true;
                            } else {
                                $jacocoInit[54] = true;
                                Method method = clazz_debugProxy.getMethod("start", new Class[0]);
                                if (method == null) {
                                    $jacocoInit[55] = true;
                                } else {
                                    $jacocoInit[56] = true;
                                    method.invoke(this.mWxDebugProxy, new Object[0]);
                                    $jacocoInit[57] = true;
                                }
                            }
                        }
                    }
                    $jacocoInit[58] = true;
                } catch (Throwable unused) {
                    $jacocoInit[59] = true;
                }
                WXServiceManager.execAllCacheJsService();
                $jacocoInit[60] = true;
            } else {
                WXLogUtils.e("WXBridgeManager", "WXEnvironment.sApplication is null, skip init Inspector");
                $jacocoInit[61] = true;
            }
        }
        if (!z) {
            $jacocoInit[62] = true;
        } else if (this.mWxDebugProxy == null) {
            $jacocoInit[63] = true;
        } else {
            try {
                $jacocoInit[64] = true;
                if (clazz_debugProxy != null) {
                    $jacocoInit[65] = true;
                } else {
                    $jacocoInit[66] = true;
                    clazz_debugProxy = Class.forName("com.taobao.weex.devtools.debug.DebugServerProxy");
                    $jacocoInit[67] = true;
                }
                if (clazz_debugProxy == null) {
                    $jacocoInit[68] = true;
                } else {
                    $jacocoInit[69] = true;
                    Method method2 = clazz_debugProxy.getMethod("getWXBridge", new Class[0]);
                    if (method2 == null) {
                        $jacocoInit[70] = true;
                    } else {
                        $jacocoInit[71] = true;
                        this.mWXBridge = (IWXBridge) method2.invoke(this.mWxDebugProxy, new Object[0]);
                        $jacocoInit[72] = true;
                    }
                }
                $jacocoInit[73] = true;
            } catch (Throwable unused2) {
                $jacocoInit[74] = true;
            }
            $jacocoInit[76] = true;
        }
        this.mWXBridge = new WXBridge();
        $jacocoInit[75] = true;
        $jacocoInit[76] = true;
    }

    public void stopRemoteDebug() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWxDebugProxy == null) {
            $jacocoInit[77] = true;
        } else {
            try {
                $jacocoInit[78] = true;
                if (clazz_debugProxy != null) {
                    $jacocoInit[79] = true;
                } else {
                    $jacocoInit[80] = true;
                    clazz_debugProxy = Class.forName("com.taobao.weex.devtools.debug.DebugServerProxy");
                    $jacocoInit[81] = true;
                }
                if (clazz_debugProxy == null) {
                    $jacocoInit[82] = true;
                } else {
                    $jacocoInit[83] = true;
                    Method method = clazz_debugProxy.getMethod("stop", new Class[]{Boolean.TYPE});
                    if (method == null) {
                        $jacocoInit[84] = true;
                    } else {
                        $jacocoInit[85] = true;
                        method.invoke(this.mWxDebugProxy, new Object[]{true});
                        $jacocoInit[86] = true;
                    }
                }
                $jacocoInit[87] = true;
            } catch (Throwable unused) {
                $jacocoInit[88] = true;
            }
        }
        $jacocoInit[89] = true;
    }

    public Object callModuleMethod(String str, String str2, String str3, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        Object callModuleMethod = callModuleMethod(str, str2, str3, jSONArray, (JSONObject) null);
        $jacocoInit[90] = true;
        return callModuleMethod;
    }

    public Object callModuleMethod(String str, String str2, String str3, JSONArray jSONArray, JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager instance = WXSDKManager.getInstance();
        $jacocoInit[91] = true;
        WXSDKInstance sDKInstance = instance.getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[92] = true;
            return null;
        }
        if (!sDKInstance.isNeedValidate()) {
            $jacocoInit[93] = true;
        } else {
            $jacocoInit[94] = true;
            if (WXSDKManager.getInstance().getValidateProcessor() == null) {
                $jacocoInit[95] = true;
            } else {
                $jacocoInit[96] = true;
                WXValidateProcessor validateProcessor = WXSDKManager.getInstance().getValidateProcessor();
                $jacocoInit[97] = true;
                WXValidateProcessor.WXModuleValidateResult onModuleValidate = validateProcessor.onModuleValidate(sDKInstance, str2, str3, jSONArray, jSONObject);
                if (onModuleValidate == null) {
                    $jacocoInit[98] = true;
                    return null;
                } else if (onModuleValidate.isSuccess) {
                    $jacocoInit[99] = true;
                    Object callModuleMethod = WXModuleManager.callModuleMethod(str, str2, str3, jSONArray);
                    $jacocoInit[100] = true;
                    return callModuleMethod;
                } else {
                    JSONObject jSONObject2 = onModuleValidate.validateInfo;
                    if (jSONObject2 == null) {
                        $jacocoInit[101] = true;
                    } else {
                        $jacocoInit[102] = true;
                        WXLogUtils.e("[WXBridgeManager] module validate fail. >>> " + jSONObject2.toJSONString());
                        $jacocoInit[103] = true;
                    }
                    $jacocoInit[104] = true;
                    return jSONObject2;
                }
            }
        }
        try {
            Object callModuleMethod2 = WXModuleManager.callModuleMethod(str, str2, str3, jSONArray);
            $jacocoInit[105] = true;
            return callModuleMethod2;
        } catch (NumberFormatException e) {
            $jacocoInit[106] = true;
            ArrayMap arrayMap = new ArrayMap();
            $jacocoInit[107] = true;
            arrayMap.put("moduleName", str2);
            $jacocoInit[108] = true;
            arrayMap.put("methodName", str3);
            $jacocoInit[109] = true;
            arrayMap.put("args", jSONArray.toJSONString());
            $jacocoInit[110] = true;
            WXLogUtils.e("[WXBridgeManager] callNative : numberFormatException when parsing string to numbers in args", arrayMap.toString());
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
            $jacocoInit[111] = true;
            String stackTrace = WXLogUtils.getStackTrace(e);
            $jacocoInit[112] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callNative", stackTrace, arrayMap);
            $jacocoInit[113] = true;
            return null;
        }
    }

    public void restart() {
        boolean[] $jacocoInit = $jacocoInit();
        setJSFrameworkInit(false);
        $jacocoInit[114] = true;
        WXModuleManager.resetAllModuleState();
        $jacocoInit[115] = true;
        initWXBridge(WXEnvironment.sRemoteDebugMode);
        $jacocoInit[116] = true;
        this.mWXBridge.resetWXBridge(WXEnvironment.sRemoteDebugMode);
        $jacocoInit[117] = true;
    }

    public synchronized void setStackTopInstance(final String str) {
        boolean[] $jacocoInit = $jacocoInit();
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5048219186351293928L, "com/taobao/weex/bridge/WXBridgeManager$3", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                WXBridgeManager.access$300(this.this$0).setStackTopInstance(str);
                $jacocoInit[1] = true;
            }
        }, str);
        $jacocoInit[118] = true;
    }

    public void post(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mInterceptor == null) {
            $jacocoInit[119] = true;
        } else if (!this.mInterceptor.take(runnable)) {
            $jacocoInit[120] = true;
        } else {
            $jacocoInit[121] = true;
            return;
        }
        if (this.mJSHandler == null) {
            $jacocoInit[122] = true;
            return;
        }
        this.mJSHandler.post(WXThread.secure(runnable));
        $jacocoInit[123] = true;
    }

    public void setInterceptor(Interceptor interceptor) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInterceptor = interceptor;
        $jacocoInit[124] = true;
    }

    public void post(Runnable runnable, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSHandler == null) {
            $jacocoInit[125] = true;
            return;
        }
        Message obtain = Message.obtain(this.mJSHandler, WXThread.secure(runnable));
        obtain.obj = obj;
        $jacocoInit[126] = true;
        obtain.sendToTarget();
        $jacocoInit[127] = true;
    }

    public void postDelay(Runnable runnable, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSHandler == null) {
            $jacocoInit[128] = true;
            return;
        }
        this.mJSHandler.postDelayed(WXThread.secure(runnable), j);
        $jacocoInit[129] = true;
    }

    /* access modifiers changed from: package-private */
    public void setTimeout(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        Message obtain = Message.obtain();
        obtain.what = 1;
        $jacocoInit[130] = true;
        TimerInfo timerInfo = new TimerInfo();
        timerInfo.callbackId = str;
        $jacocoInit[131] = true;
        timerInfo.time = (long) Float.parseFloat(str2);
        obtain.obj = timerInfo;
        $jacocoInit[132] = true;
        this.mJSHandler.sendMessageDelayed(obtain, timerInfo.time);
        $jacocoInit[133] = true;
    }

    public void sendMessageDelayed(Message message, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (message == null) {
            $jacocoInit[134] = true;
        } else if (this.mJSHandler == null) {
            $jacocoInit[135] = true;
        } else if (this.mJSThread == null) {
            $jacocoInit[136] = true;
        } else {
            WXThread wXThread = this.mJSThread;
            $jacocoInit[137] = true;
            if (!wXThread.isWXThreadAlive()) {
                $jacocoInit[138] = true;
            } else if (this.mJSThread.getLooper() == null) {
                $jacocoInit[139] = true;
            } else {
                this.mJSHandler.sendMessageDelayed(message, j);
                $jacocoInit[141] = true;
                return;
            }
        }
        $jacocoInit[140] = true;
    }

    public void removeMessage(int i, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSHandler == null) {
            $jacocoInit[142] = true;
        } else if (this.mJSThread == null) {
            $jacocoInit[143] = true;
        } else {
            WXThread wXThread = this.mJSThread;
            $jacocoInit[144] = true;
            if (!wXThread.isWXThreadAlive()) {
                $jacocoInit[145] = true;
            } else if (this.mJSThread.getLooper() == null) {
                $jacocoInit[146] = true;
            } else {
                this.mJSHandler.removeMessages(i, obj);
                $jacocoInit[148] = true;
                return;
            }
        }
        $jacocoInit[147] = true;
    }

    public Object callNativeModule(String str, String str2, String str3, JSONArray jSONArray, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[149] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[150] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[151] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[155] = true;
            } else {
                try {
                    $jacocoInit[156] = true;
                } catch (Exception e) {
                    $jacocoInit[162] = true;
                    String str4 = "[WXBridgeManager] callNative exception: " + WXLogUtils.getStackTrace(e);
                    $jacocoInit[163] = true;
                    WXLogUtils.e(str4);
                    $jacocoInit[164] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE, "callNativeModule", str4, (Map<String, String>) null);
                    $jacocoInit[165] = true;
                    return null;
                }
            }
            if (!WXDomModule.WXDOM.equals(str2)) {
                $jacocoInit[157] = true;
                Object callModuleMethod = callModuleMethod(str, str2, str3, jSONArray);
                $jacocoInit[161] = true;
                return callModuleMethod;
            }
            $jacocoInit[158] = true;
            WXDomModule domModule = WXModuleManager.getDomModule(str);
            $jacocoInit[159] = true;
            Object callDomMethod = domModule.callDomMethod(str3, jSONArray, new long[0]);
            $jacocoInit[160] = true;
            return callDomMethod;
        }
        WXLogUtils.d("[WXBridgeManager] call callNativeModule arguments is null");
        $jacocoInit[152] = true;
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callNativeModule", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[153] = true;
        $jacocoInit[154] = true;
        return 0;
    }

    public Object callNativeModule(String str, String str2, String str3, JSONArray jSONArray, JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[166] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[167] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[168] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[172] = true;
            } else {
                try {
                    $jacocoInit[173] = true;
                } catch (Exception e) {
                    $jacocoInit[180] = true;
                    String str4 = "[WXBridgeManager] callNativeModule exception: " + WXLogUtils.getStackTrace(e);
                    $jacocoInit[181] = true;
                    WXLogUtils.e(str4);
                    $jacocoInit[182] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE, "callNativeModule", str4, (Map<String, String>) null);
                    $jacocoInit[183] = true;
                }
            }
            if (WXDomModule.WXDOM.equals(str2)) {
                $jacocoInit[174] = true;
                WXDomModule domModule = WXModuleManager.getDomModule(str);
                if (domModule == null) {
                    $jacocoInit[175] = true;
                    WXModuleManager.createDomModule(WXSDKManager.getInstance().getSDKInstance(str));
                    $jacocoInit[179] = true;
                    $jacocoInit[184] = true;
                    return null;
                }
                $jacocoInit[176] = true;
                Object callDomMethod = domModule.callDomMethod(str3, jSONArray, new long[0]);
                $jacocoInit[177] = true;
                return callDomMethod;
            }
            Object callModuleMethod = callModuleMethod(str, str2, str3, jSONArray, jSONObject);
            $jacocoInit[178] = true;
            return callModuleMethod;
        }
        WXLogUtils.d("[WXBridgeManager] call callNativeModule arguments is null");
        $jacocoInit[169] = true;
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callNativeModule", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[170] = true;
        $jacocoInit[171] = true;
        return 0;
    }

    public Object callNativeComponent(String str, String str2, String str3, JSONArray jSONArray, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[185] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[186] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[187] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[191] = true;
            } else {
                try {
                    $jacocoInit[192] = true;
                } catch (Exception e) {
                    $jacocoInit[201] = true;
                    WXLogUtils.e("[WXBridgeManager] callNativeComponent exception: ", (Throwable) e);
                    WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                    $jacocoInit[202] = true;
                    String stackTrace = WXLogUtils.getStackTrace(e);
                    $jacocoInit[203] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callNativeComponent", stackTrace, (Map<String, String>) null);
                    $jacocoInit[204] = true;
                }
            }
            WXDomModule domModule = WXModuleManager.getDomModule(str);
            if (domModule != null) {
                $jacocoInit[193] = true;
                domModule.invokeMethod(str2, str3, jSONArray);
                $jacocoInit[194] = true;
            } else {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                $jacocoInit[195] = true;
                if (sDKInstance == null) {
                    $jacocoInit[196] = true;
                } else if (sDKInstance.isDestroy()) {
                    $jacocoInit[197] = true;
                } else {
                    $jacocoInit[198] = true;
                }
                WXLogUtils.e("WXBridgeManager", "callNativeComponent exception :null == dom ,method:" + str3);
                $jacocoInit[199] = true;
            }
            $jacocoInit[200] = true;
            $jacocoInit[205] = true;
            return null;
        }
        WXLogUtils.d("[WXBridgeManager] call callNativeComponent arguments is null");
        $jacocoInit[188] = true;
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callNativeComponent", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[189] = true;
        $jacocoInit[190] = true;
        return 0;
    }

    public int callNative(String str, JSONArray jSONArray, String str2) {
        int i;
        int i2;
        String str3 = str;
        JSONArray jSONArray2 = jSONArray;
        String str4 = str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[206] = true;
        } else if (jSONArray2 == null) {
            $jacocoInit[207] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[210] = true;
            } else {
                $jacocoInit[211] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[212] = true;
            } else if (!this.mDestroyedInstanceId.contains(str3)) {
                $jacocoInit[213] = true;
            } else {
                $jacocoInit[214] = true;
                return -1;
            }
            long nanoTime = System.nanoTime();
            $jacocoInit[215] = true;
            long nanoTime2 = System.nanoTime() - nanoTime;
            $jacocoInit[216] = true;
            if (jSONArray2 == null) {
                $jacocoInit[217] = true;
            } else if (jSONArray.size() <= 0) {
                $jacocoInit[218] = true;
            } else {
                $jacocoInit[219] = true;
                int size = jSONArray.size();
                try {
                    $jacocoInit[220] = true;
                    $jacocoInit[221] = true;
                    int i3 = 0;
                    while (i3 < size) {
                        $jacocoInit[222] = true;
                        JSONObject jSONObject = (JSONObject) jSONArray2.get(i3);
                        $jacocoInit[223] = true;
                        if (jSONObject == null) {
                            $jacocoInit[224] = true;
                        } else if (WXSDKManager.getInstance().getSDKInstance(str3) == null) {
                            $jacocoInit[225] = true;
                        } else {
                            $jacocoInit[226] = true;
                            Object obj = jSONObject.get(MODULE);
                            if (obj != null) {
                                $jacocoInit[227] = true;
                                if (WXDomModule.WXDOM.equals(obj)) {
                                    $jacocoInit[228] = true;
                                    WXDomModule domModule = WXModuleManager.getDomModule(str);
                                    $jacocoInit[229] = true;
                                    domModule.callDomMethod(jSONObject, nanoTime2);
                                    $jacocoInit[230] = true;
                                } else {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("options");
                                    $jacocoInit[231] = true;
                                    $jacocoInit[232] = true;
                                    i = i3;
                                    i2 = size;
                                    callModuleMethod(str, (String) obj, (String) jSONObject.get("method"), (JSONArray) jSONObject.get("args"), jSONObject2);
                                    $jacocoInit[233] = true;
                                }
                            } else {
                                i = i3;
                                i2 = size;
                                if (jSONObject.get(COMPONENT) != null) {
                                    $jacocoInit[234] = true;
                                    WXDomModule domModule2 = WXModuleManager.getDomModule(str);
                                    $jacocoInit[235] = true;
                                    domModule2.invokeMethod((String) jSONObject.get("ref"), (String) jSONObject.get("method"), (JSONArray) jSONObject.get("args"));
                                    $jacocoInit[236] = true;
                                } else {
                                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("unknown callNative");
                                    $jacocoInit[237] = true;
                                    throw illegalArgumentException;
                                }
                            }
                            i3 = i + 1;
                            $jacocoInit[238] = true;
                            size = i2;
                        }
                        i = i3;
                        i2 = size;
                        i3 = i + 1;
                        $jacocoInit[238] = true;
                        size = i2;
                    }
                    $jacocoInit[239] = true;
                } catch (Exception e) {
                    $jacocoInit[240] = true;
                    WXLogUtils.e("[WXBridgeManager] callNative exception: ", (Throwable) e);
                    WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                    $jacocoInit[241] = true;
                    String stackTrace = WXLogUtils.getStackTrace(e);
                    $jacocoInit[242] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(str3, wXErrorCode, "callNative", stackTrace, (Map<String, String>) null);
                    $jacocoInit[243] = true;
                }
            }
            if ("undefined".equals(str4)) {
                $jacocoInit[244] = true;
            } else if ("-1".equals(str4)) {
                $jacocoInit[245] = true;
            } else {
                getNextTick(str3, str4);
                $jacocoInit[247] = true;
                return 1;
            }
            $jacocoInit[246] = true;
            return 0;
        }
        WXLogUtils.d("[WXBridgeManager] call callNative arguments is null");
        $jacocoInit[208] = true;
        WXExceptionUtils.commitCriticalExceptionRT(str3, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callNative", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[209] = true;
        return 0;
    }

    public int callUpdateFinish(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[248] = true;
            WXLogUtils.d("[WXBridgeManager] call callUpdateFinish arguments is null");
            $jacocoInit[249] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callUpdateFinish", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
            $jacocoInit[250] = true;
            return 0;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[251] = true;
        } else {
            $jacocoInit[252] = true;
        }
        if (this.mDestroyedInstanceId == null) {
            $jacocoInit[253] = true;
        } else if (!this.mDestroyedInstanceId.contains(str)) {
            $jacocoInit[254] = true;
        } else {
            $jacocoInit[255] = true;
            return -1;
        }
        try {
            if (WXSDKManager.getInstance().getSDKInstance(str) == null) {
                $jacocoInit[256] = true;
            } else {
                $jacocoInit[257] = true;
            }
            $jacocoInit[258] = true;
        } catch (Exception e) {
            $jacocoInit[259] = true;
            WXLogUtils.e("[WXBridgeManager] callUpdateFinish exception: ", (Throwable) e);
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
            $jacocoInit[260] = true;
            String stackTrace = WXLogUtils.getStackTrace(e);
            $jacocoInit[261] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callUpdateFinish", stackTrace, (Map<String, String>) null);
            $jacocoInit[262] = true;
        }
        if (str2 == null) {
            $jacocoInit[263] = true;
        } else if (str2.isEmpty()) {
            $jacocoInit[264] = true;
        } else if ("undefined".equals(str2)) {
            $jacocoInit[265] = true;
        } else if ("-1".equals(str2)) {
            $jacocoInit[266] = true;
        } else {
            getNextTick(str, str2);
            $jacocoInit[268] = true;
            return 1;
        }
        $jacocoInit[267] = true;
        return 0;
    }

    public int callRefreshFinish(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[269] = true;
            WXLogUtils.d("[WXBridgeManager] call callRefreshFinish arguments is null");
            $jacocoInit[270] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callRefreshFinish", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
            $jacocoInit[271] = true;
            return 0;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[272] = true;
        } else {
            $jacocoInit[273] = true;
        }
        if (this.mDestroyedInstanceId == null) {
            $jacocoInit[274] = true;
        } else if (!this.mDestroyedInstanceId.contains(str)) {
            $jacocoInit[275] = true;
        } else {
            $jacocoInit[276] = true;
            return -1;
        }
        try {
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            if (sDKInstance == null) {
                $jacocoInit[277] = true;
            } else {
                $jacocoInit[278] = true;
                GraphicActionRefreshFinish graphicActionRefreshFinish = new GraphicActionRefreshFinish(sDKInstance);
                $jacocoInit[279] = true;
                WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(str, graphicActionRefreshFinish);
                $jacocoInit[280] = true;
            }
            $jacocoInit[281] = true;
        } catch (Exception e) {
            $jacocoInit[282] = true;
            WXLogUtils.e("[WXBridgeManager] callRefreshFinish exception: ", (Throwable) e);
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
            $jacocoInit[283] = true;
            String stackTrace = WXLogUtils.getStackTrace(e);
            $jacocoInit[284] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callRefreshFinish", stackTrace, (Map<String, String>) null);
            $jacocoInit[285] = true;
        }
        if ("undefined".equals(str2)) {
            $jacocoInit[286] = true;
        } else if ("-1".equals(str2)) {
            $jacocoInit[287] = true;
        } else {
            getNextTick(str, str2);
            $jacocoInit[289] = true;
            return 1;
        }
        $jacocoInit[288] = true;
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00aa A[Catch:{ Exception -> 0x00f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int callReportCrashReloadPage(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            boolean[] r7 = $jacocoInit()
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            r1 = 290(0x122, float:4.06E-43)
            r8 = 0
            r9 = 0
            r10 = 1
            r7[r1] = r10     // Catch:{ Exception -> 0x00f3 }
            r1 = 291(0x123, float:4.08E-43)
            r7[r1] = r10     // Catch:{ Exception -> 0x00f3 }
            com.taobao.weex.WXSDKManager r1 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Exception -> 0x00f3 }
            com.taobao.weex.WXSDKInstance r1 = r1.getSDKInstance(r12)     // Catch:{ Exception -> 0x00f3 }
            if (r1 != 0) goto L_0x0023
            r1 = 292(0x124, float:4.09E-43)
            r7[r1] = r10     // Catch:{ Exception -> 0x00f3 }
            r6 = r8
            goto L_0x0030
        L_0x0023:
            r2 = 293(0x125, float:4.1E-43)
            r7[r2] = r10     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r1 = r1.getBundleUrl()     // Catch:{ Exception -> 0x00f3 }
            r2 = 294(0x126, float:4.12E-43)
            r7[r2] = r10     // Catch:{ Exception -> 0x00f3 }
            r6 = r1
        L_0x0030:
            if (r0 != 0) goto L_0x0095
            r0 = 295(0x127, float:4.13E-43)
            r7[r0] = r10     // Catch:{ Throwable -> 0x0080 }
            android.app.Application r0 = com.taobao.weex.WXEnvironment.getApplication()     // Catch:{ Throwable -> 0x0080 }
            if (r0 != 0) goto L_0x0042
            r0 = 296(0x128, float:4.15E-43)
            r7[r0] = r10     // Catch:{ Throwable -> 0x0080 }
            r2 = r13
            goto L_0x0079
        L_0x0042:
            r0 = 297(0x129, float:4.16E-43)
            r7[r0] = r10     // Catch:{ Throwable -> 0x0080 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0080 }
            r0.<init>()     // Catch:{ Throwable -> 0x0080 }
            com.taobao.weex.bridge.WXParams r1 = r11.mInitParams     // Catch:{ Throwable -> 0x0080 }
            java.lang.String r1 = r1.getCrashFilePath()     // Catch:{ Throwable -> 0x0080 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0080 }
            r0.append(r13)     // Catch:{ Throwable -> 0x0080 }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x0080 }
            r0 = 298(0x12a, float:4.18E-43)
            r7[r0] = r10     // Catch:{ Throwable -> 0x007e }
            java.lang.String r0 = "jsengine"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x007e }
            r1.<init>()     // Catch:{ Throwable -> 0x007e }
            java.lang.String r3 = "callReportCrashReloadPage crashFile:"
            r1.append(r3)     // Catch:{ Throwable -> 0x007e }
            r1.append(r2)     // Catch:{ Throwable -> 0x007e }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x007e }
            android.util.Log.d(r0, r1)     // Catch:{ Throwable -> 0x007e }
            r0 = 299(0x12b, float:4.19E-43)
            r7[r0] = r10     // Catch:{ Throwable -> 0x007e }
        L_0x0079:
            r0 = 300(0x12c, float:4.2E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            goto L_0x008d
        L_0x007e:
            r0 = move-exception
            goto L_0x0082
        L_0x0080:
            r0 = move-exception
            r2 = r13
        L_0x0082:
            r1 = 301(0x12d, float:4.22E-43)
            r7[r1] = r10     // Catch:{ Exception -> 0x00f3 }
            r0.printStackTrace()     // Catch:{ Exception -> 0x00f3 }
            r0 = 302(0x12e, float:4.23E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
        L_0x008d:
            r11.callReportCrash(r2, r12, r6)     // Catch:{ Exception -> 0x00f3 }
            r0 = 303(0x12f, float:4.25E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            goto L_0x00a4
        L_0x0095:
            java.lang.String r2 = "jsBridge"
            com.taobao.weex.common.WXErrorCode r3 = com.taobao.weex.common.WXErrorCode.WX_ERR_RELOAD_PAGE     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r4 = "reboot jsc Engine"
            r1 = r11
            r5 = r12
            r1.commitJscCrashAlarmMonitor(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f3 }
            r0 = 304(0x130, float:4.26E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
        L_0x00a4:
            int r0 = reInitCount     // Catch:{ Exception -> 0x00f3 }
            r1 = 50
            if (r0 > r1) goto L_0x00ee
            r0 = 305(0x131, float:4.27E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            int r0 = reInitCount     // Catch:{ Exception -> 0x00f3 }
            int r0 = r0 + r10
            reInitCount = r0     // Catch:{ Exception -> 0x00f3 }
            r0 = 307(0x133, float:4.3E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            r11.setJSFrameworkInit(r9)     // Catch:{ Exception -> 0x00f3 }
            r0 = 308(0x134, float:4.32E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            com.taobao.weex.bridge.WXModuleManager.resetAllModuleState()     // Catch:{ Exception -> 0x00f3 }
            r0 = 309(0x135, float:4.33E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            java.lang.String r0 = ""
            r11.initScriptsFramework(r0)     // Catch:{ Exception -> 0x00f3 }
            r0 = 310(0x136, float:4.34E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            java.util.HashSet<java.lang.String> r0 = r11.mDestroyedInstanceId     // Catch:{ Exception -> 0x00f3 }
            if (r0 != 0) goto L_0x00d7
            r0 = 311(0x137, float:4.36E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
            goto L_0x00e3
        L_0x00d7:
            java.util.HashSet<java.lang.String> r0 = r11.mDestroyedInstanceId     // Catch:{ Exception -> 0x00f3 }
            boolean r0 = r0.contains(r12)     // Catch:{ Exception -> 0x00f3 }
            if (r0 != 0) goto L_0x00e8
            r0 = 312(0x138, float:4.37E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x00f3 }
        L_0x00e3:
            r0 = 314(0x13a, float:4.4E-43)
            r7[r0] = r10
            goto L_0x0101
        L_0x00e8:
            r0 = -1
            r1 = 313(0x139, float:4.39E-43)
            r7[r1] = r10
            return r0
        L_0x00ee:
            r0 = 306(0x132, float:4.29E-43)
            r7[r0] = r10
            return r9
        L_0x00f3:
            r0 = move-exception
            r1 = 315(0x13b, float:4.41E-43)
            r7[r1] = r10
            java.lang.String r1 = "[WXBridgeManager] callReportCrashReloadPage exception: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.Throwable) r0)
            r0 = 316(0x13c, float:4.43E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x013d }
        L_0x0101:
            com.taobao.weex.WXSDKManager r0 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Exception -> 0x013d }
            com.taobao.weex.WXSDKInstance r0 = r0.getSDKInstance(r12)     // Catch:{ Exception -> 0x013d }
            if (r0 != 0) goto L_0x0110
            r0 = 317(0x13d, float:4.44E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x013d }
            goto L_0x0138
        L_0x0110:
            r0 = 318(0x13e, float:4.46E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x013d }
            com.taobao.weex.WXSDKManager r0 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Exception -> 0x013d }
            com.taobao.weex.WXSDKInstance r0 = r0.getSDKInstance(r12)     // Catch:{ Exception -> 0x013d }
            java.lang.String r0 = r0.getBundleUrl()     // Catch:{ Exception -> 0x013d }
            r1 = 319(0x13f, float:4.47E-43)
            r7[r1] = r10     // Catch:{ Exception -> 0x013d }
            boolean r0 = r11.shouReloadCurrentInstance(r0)     // Catch:{ Exception -> 0x013d }
            r1 = 320(0x140, float:4.48E-43)
            r7[r1] = r10     // Catch:{ Exception -> 0x013d }
            com.taobao.weex.ui.action.ActionReloadPage r1 = new com.taobao.weex.ui.action.ActionReloadPage     // Catch:{ Exception -> 0x013d }
            r1.<init>(r12, r0)     // Catch:{ Exception -> 0x013d }
            r1.executeAction()     // Catch:{ Exception -> 0x013d }
            r0 = 321(0x141, float:4.5E-43)
            r7[r0] = r10     // Catch:{ Exception -> 0x013d }
        L_0x0138:
            r0 = 322(0x142, float:4.51E-43)
            r7[r0] = r10
            goto L_0x015e
        L_0x013d:
            r0 = move-exception
            r1 = 323(0x143, float:4.53E-43)
            r7[r1] = r10
            java.lang.String r1 = "[WXBridgeManager] callReloadPage exception: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.Throwable) r0)
            com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE
            java.lang.String r2 = "callReportCrashReloadPage"
            r3 = 324(0x144, float:4.54E-43)
            r7[r3] = r10
            java.lang.String r0 = com.taobao.weex.utils.WXLogUtils.getStackTrace(r0)
            r3 = 325(0x145, float:4.55E-43)
            r7[r3] = r10
            com.taobao.weex.utils.WXExceptionUtils.commitCriticalExceptionRT(r12, r1, r2, r0, r8)
            r0 = 326(0x146, float:4.57E-43)
            r7[r0] = r10
        L_0x015e:
            r0 = 327(0x147, float:4.58E-43)
            r7[r0] = r10
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.bridge.WXBridgeManager.callReportCrashReloadPage(java.lang.String, java.lang.String):int");
    }

    public boolean shouReloadCurrentInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        long currentTimeMillis = System.currentTimeMillis();
        if (crashUrl == null) {
            $jacocoInit[328] = true;
        } else {
            if (crashUrl == null) {
                $jacocoInit[329] = true;
            } else {
                String str2 = crashUrl;
                $jacocoInit[330] = true;
                if (!str2.equals(str)) {
                    $jacocoInit[331] = true;
                } else {
                    $jacocoInit[332] = true;
                }
            }
            if (currentTimeMillis - lastCrashTime > 15000) {
                $jacocoInit[333] = true;
            } else {
                lastCrashTime = currentTimeMillis;
                $jacocoInit[335] = true;
                return false;
            }
        }
        crashUrl = str;
        lastCrashTime = currentTimeMillis;
        $jacocoInit[334] = true;
        return true;
    }

    public void callReportCrash(String str, final String str2, final String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        Date date = new Date();
        $jacocoInit[336] = true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        $jacocoInit[337] = true;
        String format = simpleDateFormat.format(date);
        $jacocoInit[338] = true;
        final String str4 = str + "." + format;
        $jacocoInit[339] = true;
        File file = new File(str);
        $jacocoInit[340] = true;
        File file2 = new File(str4);
        $jacocoInit[341] = true;
        if (!file.exists()) {
            $jacocoInit[342] = true;
        } else {
            $jacocoInit[343] = true;
            file.renameTo(file2);
            $jacocoInit[344] = true;
        }
        Thread thread = new Thread(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-833530015048069378L, "com/taobao/weex/bridge/WXBridgeManager$4", 21);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                try {
                    File file = new File(str4);
                    $jacocoInit[1] = true;
                    if (!file.exists()) {
                        $jacocoInit[2] = true;
                    } else {
                        $jacocoInit[3] = true;
                        if (file.length() > 0) {
                            $jacocoInit[4] = true;
                            StringBuilder sb = new StringBuilder();
                            $jacocoInit[5] = true;
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(str4));
                            $jacocoInit[6] = true;
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                $jacocoInit[7] = true;
                                if ("".equals(readLine)) {
                                    $jacocoInit[8] = true;
                                } else {
                                    sb.append(readLine + "\n");
                                    $jacocoInit[9] = true;
                                }
                            }
                            this.this$0.commitJscCrashAlarmMonitor(IWXUserTrackAdapter.JS_BRIDGE, WXErrorCode.WX_ERR_JSC_CRASH, sb.toString(), str2, str3);
                            $jacocoInit[10] = true;
                            bufferedReader.close();
                            $jacocoInit[11] = true;
                            $jacocoInit[14] = true;
                        } else {
                            WXLogUtils.e("[WXBridgeManager] callReportCrash crash file is empty");
                            $jacocoInit[15] = true;
                        }
                        file.delete();
                        $jacocoInit[16] = true;
                    }
                } catch (Exception e) {
                    $jacocoInit[12] = true;
                    e.printStackTrace();
                    $jacocoInit[13] = true;
                } catch (Throwable th) {
                    $jacocoInit[18] = true;
                    WXLogUtils.e("[WXBridgeManager] callReportCrash exception: ", th);
                    $jacocoInit[19] = true;
                }
                $jacocoInit[17] = true;
                $jacocoInit[20] = true;
            }
        });
        $jacocoInit[345] = true;
        thread.start();
        $jacocoInit[346] = true;
    }

    private void getNextTick(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        addJSTask("callback", str, str2, "{}");
        $jacocoInit[347] = true;
        sendMessage(str, 6);
        $jacocoInit[348] = true;
    }

    private void getNextTick(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        addJSTask("callback", str, "", "{}");
        $jacocoInit[349] = true;
        sendMessage(str, 6);
        $jacocoInit[350] = true;
    }

    public String syncExecJsOnInstanceWithResult(String str, String str2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        $jacocoInit[351] = true;
        AnonymousClass5 r3 = new EventResult(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-8614174429320173348L, "com/taobao/weex/bridge/WXBridgeManager$5", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onCallback(Object obj) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onCallback(obj);
                $jacocoInit[1] = true;
                countDownLatch.countDown();
                $jacocoInit[2] = true;
            }
        };
        try {
            $jacocoInit[352] = true;
            execJSOnInstance(r3, str, str2, i);
            $jacocoInit[353] = true;
            countDownLatch.await(100, TimeUnit.MILLISECONDS);
            $jacocoInit[354] = true;
            if (r3.getResult() == null) {
                $jacocoInit[356] = true;
                $jacocoInit[359] = true;
                return "";
            }
            $jacocoInit[357] = true;
            String obj = r3.getResult().toString();
            $jacocoInit[358] = true;
            return obj;
        } catch (Throwable th) {
            $jacocoInit[360] = true;
            WXLogUtils.e("syncCallExecJsOnInstance", th);
            $jacocoInit[361] = true;
            return "";
        }
    }

    public EventResult syncCallJSEventWithResult(String str, String str2, List<Object> list, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        $jacocoInit[362] = true;
        AnonymousClass6 r3 = new EventResult(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-3671637672591211293L, "com/taobao/weex/bridge/WXBridgeManager$6", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onCallback(Object obj) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onCallback(obj);
                $jacocoInit[1] = true;
                countDownLatch.countDown();
                $jacocoInit[2] = true;
            }
        };
        try {
            $jacocoInit[363] = true;
            asyncCallJSEventWithResult(r3, str, str2, list, objArr);
            $jacocoInit[364] = true;
            countDownLatch.await(100, TimeUnit.MILLISECONDS);
            $jacocoInit[365] = true;
            return r3;
        } catch (Exception e) {
            $jacocoInit[366] = true;
            WXLogUtils.e("syncCallJSEventWithResult", (Throwable) e);
            $jacocoInit[367] = true;
            return r3;
        }
    }

    public void asyncCallJSEventVoidResult(String str, String str2, List<Object> list, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        final Object[] objArr2 = objArr;
        final List<Object> list2 = list;
        final String str3 = str;
        final String str4 = str2;
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(4121570286459212238L, "com/taobao/weex/bridge/WXBridgeManager$7", 22);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                try {
                    if (objArr2 == null) {
                        $jacocoInit[1] = true;
                    } else if (objArr2.length != 0) {
                        $jacocoInit[2] = true;
                        ArrayList arrayList = new ArrayList();
                        Object[] objArr = objArr2;
                        int length = objArr.length;
                        $jacocoInit[5] = true;
                        int i = 0;
                        while (i < length) {
                            Object obj = objArr[i];
                            $jacocoInit[6] = true;
                            arrayList.add(obj);
                            i++;
                            $jacocoInit[7] = true;
                        }
                        if (list2 == null) {
                            $jacocoInit[8] = true;
                        } else {
                            $jacocoInit[9] = true;
                            ArrayMap arrayMap = new ArrayMap(4);
                            $jacocoInit[10] = true;
                            arrayMap.put("params", list2);
                            $jacocoInit[11] = true;
                            arrayList.add(arrayMap);
                            $jacocoInit[12] = true;
                        }
                        WXHashMap wXHashMap = new WXHashMap();
                        $jacocoInit[13] = true;
                        wXHashMap.put("method", str3);
                        $jacocoInit[14] = true;
                        wXHashMap.put("args", arrayList);
                        $jacocoInit[15] = true;
                        $jacocoInit[16] = true;
                        WXJSObject[] wXJSObjectArr = {new WXJSObject(2, str4), WXWsonJSONSwitch.toWsonOrJsonWXJSObject(new Object[]{wXHashMap})};
                        $jacocoInit[17] = true;
                        this.this$0.invokeExecJS(String.valueOf(str4), (String) null, WXBridgeManager.METHOD_CALL_JS, wXJSObjectArr, true);
                        wXJSObjectArr[0] = null;
                        $jacocoInit[18] = true;
                        $jacocoInit[21] = true;
                        return;
                    } else {
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                } catch (Exception e) {
                    $jacocoInit[19] = true;
                    WXLogUtils.e("asyncCallJSEventVoidResult", (Throwable) e);
                    $jacocoInit[20] = true;
                }
            }
        });
        $jacocoInit[368] = true;
    }

    private void asyncCallJSEventWithResult(EventResult eventResult, String str, String str2, List<Object> list, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        final Object[] objArr2 = objArr;
        final List<Object> list2 = list;
        final String str3 = str;
        final String str4 = str2;
        final EventResult eventResult2 = eventResult;
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1347188334394161970L, "com/taobao/weex/bridge/WXBridgeManager$8", 25);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                AnonymousClass1 r13;
                boolean[] $jacocoInit = $jacocoInit();
                try {
                    $jacocoInit[1] = true;
                    if (objArr2 == null) {
                        $jacocoInit[2] = true;
                    } else if (objArr2.length != 0) {
                        $jacocoInit[3] = true;
                        ArrayList arrayList = new ArrayList();
                        Object[] objArr = objArr2;
                        int length = objArr.length;
                        $jacocoInit[6] = true;
                        int i = 0;
                        while (i < length) {
                            Object obj = objArr[i];
                            $jacocoInit[7] = true;
                            arrayList.add(obj);
                            i++;
                            $jacocoInit[8] = true;
                        }
                        if (list2 == null) {
                            $jacocoInit[9] = true;
                        } else {
                            $jacocoInit[10] = true;
                            ArrayMap arrayMap = new ArrayMap(4);
                            $jacocoInit[11] = true;
                            arrayMap.put("params", list2);
                            $jacocoInit[12] = true;
                            arrayList.add(arrayMap);
                            $jacocoInit[13] = true;
                        }
                        WXHashMap wXHashMap = new WXHashMap();
                        $jacocoInit[14] = true;
                        wXHashMap.put("method", str3);
                        $jacocoInit[15] = true;
                        wXHashMap.put("args", arrayList);
                        $jacocoInit[16] = true;
                        $jacocoInit[17] = true;
                        WXJSObject[] wXJSObjectArr = {new WXJSObject(2, str4), WXWsonJSONSwitch.toWsonOrJsonWXJSObject(new Object[]{wXHashMap})};
                        if (eventResult2 == null) {
                            $jacocoInit[18] = true;
                            r13 = null;
                        } else {
                            $jacocoInit[19] = true;
                            AnonymousClass1 r2 = new ResultCallback<byte[]>(this) {
                                private static transient /* synthetic */ boolean[] $jacocoData;
                                final /* synthetic */ AnonymousClass8 this$1;

                                private static /* synthetic */ boolean[] $jacocoInit() {
                                    boolean[] zArr = $jacocoData;
                                    if (zArr != null) {
                                        return zArr;
                                    }
                                    boolean[] a2 = Offline.a(-5382088022800575256L, "com/taobao/weex/bridge/WXBridgeManager$8$1", 8);
                                    $jacocoData = a2;
                                    return a2;
                                }

                                {
                                    boolean[] $jacocoInit = $jacocoInit();
                                    this.this$1 = r3;
                                    $jacocoInit[0] = true;
                                }

                                public /* synthetic */ void onReceiveResult(Object obj) {
                                    boolean[] $jacocoInit = $jacocoInit();
                                    onReceiveResult((byte[]) obj);
                                    $jacocoInit[7] = true;
                                }

                                public void onReceiveResult(byte[] bArr) {
                                    boolean[] $jacocoInit = $jacocoInit();
                                    JSONArray jSONArray = (JSONArray) WXWsonJSONSwitch.parseWsonOrJSON(bArr);
                                    $jacocoInit[1] = true;
                                    if (jSONArray == null) {
                                        $jacocoInit[2] = true;
                                    } else if (jSONArray.size() <= 0) {
                                        $jacocoInit[3] = true;
                                    } else {
                                        $jacocoInit[4] = true;
                                        eventResult2.onCallback(jSONArray.get(0));
                                        $jacocoInit[5] = true;
                                    }
                                    $jacocoInit[6] = true;
                                }
                            };
                            $jacocoInit[20] = true;
                            r13 = r2;
                        }
                        WXBridgeManager.access$400(this.this$0, String.valueOf(str4), (String) null, WXBridgeManager.METHOD_CALL_JS, wXJSObjectArr, r13, true);
                        wXJSObjectArr[0] = null;
                        $jacocoInit[21] = true;
                        $jacocoInit[24] = true;
                        return;
                    } else {
                        $jacocoInit[4] = true;
                    }
                    $jacocoInit[5] = true;
                } catch (Exception e) {
                    $jacocoInit[22] = true;
                    WXLogUtils.e("asyncCallJSEventWithResult", (Throwable) e);
                    $jacocoInit[23] = true;
                }
            }
        });
        $jacocoInit[369] = true;
    }

    private void addJSEventTask(String str, String str2, List<Object> list, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        final Object[] objArr2 = objArr;
        final List<Object> list2 = list;
        final String str3 = str;
        final String str4 = str2;
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-6706916479145041515L, "com/taobao/weex/bridge/WXBridgeManager$9", 21);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                if (objArr2 == null) {
                    $jacocoInit[1] = true;
                } else if (objArr2.length == 0) {
                    $jacocoInit[2] = true;
                } else {
                    ArrayList arrayList = new ArrayList();
                    Object[] objArr = objArr2;
                    int length = objArr.length;
                    int i = 0;
                    $jacocoInit[4] = true;
                    while (i < length) {
                        Object obj = objArr[i];
                        $jacocoInit[5] = true;
                        arrayList.add(obj);
                        i++;
                        $jacocoInit[6] = true;
                    }
                    if (list2 == null) {
                        $jacocoInit[7] = true;
                    } else {
                        $jacocoInit[8] = true;
                        ArrayMap arrayMap = new ArrayMap(4);
                        $jacocoInit[9] = true;
                        arrayMap.put("params", list2);
                        $jacocoInit[10] = true;
                        arrayList.add(arrayMap);
                        $jacocoInit[11] = true;
                    }
                    WXHashMap wXHashMap = new WXHashMap();
                    $jacocoInit[12] = true;
                    wXHashMap.put("method", str3);
                    $jacocoInit[13] = true;
                    wXHashMap.put("args", arrayList);
                    $jacocoInit[14] = true;
                    if (WXBridgeManager.access$300(this.this$0).get(str4) == null) {
                        $jacocoInit[15] = true;
                        ArrayList arrayList2 = new ArrayList();
                        $jacocoInit[16] = true;
                        arrayList2.add(wXHashMap);
                        $jacocoInit[17] = true;
                        WXBridgeManager.access$300(this.this$0).put(str4, arrayList2);
                        $jacocoInit[18] = true;
                    } else {
                        ((ArrayList) WXBridgeManager.access$300(this.this$0).get(str4)).add(wXHashMap);
                        $jacocoInit[19] = true;
                    }
                    $jacocoInit[20] = true;
                    return;
                }
                $jacocoInit[3] = true;
            }
        });
        $jacocoInit[370] = true;
    }

    private void addJSTask(String str, String str2, Object... objArr) {
        boolean[] $jacocoInit = $jacocoInit();
        addJSEventTask(str, str2, (List<Object>) null, objArr);
        $jacocoInit[371] = true;
    }

    private void sendMessage(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Message obtain = Message.obtain(this.mJSHandler);
        obtain.obj = str;
        obtain.what = i;
        $jacocoInit[372] = true;
        obtain.sendToTarget();
        $jacocoInit[373] = true;
    }

    public synchronized void initScriptsFramework(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Message obtainMessage = this.mJSHandler.obtainMessage();
        obtainMessage.obj = str;
        obtainMessage.what = 7;
        $jacocoInit[374] = true;
        obtainMessage.setTarget(this.mJSHandler);
        $jacocoInit[375] = true;
        obtainMessage.sendToTarget();
        $jacocoInit[376] = true;
    }

    @Deprecated
    public void fireEvent(String str, String str2, String str3, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, str3, map, (Map<String, Object>) null);
        $jacocoInit[377] = true;
    }

    @Deprecated
    public void fireEvent(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEventOnNode(str, str2, str3, map, map2);
        $jacocoInit[378] = true;
    }

    public void fireEventOnNode(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEventOnNode(str, str2, str3, map, map2, (List<Object>) null, (EventResult) null);
        $jacocoInit[379] = true;
    }

    public void fireEventOnNode(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2, List<Object> list) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEventOnNode(str, str2, str3, map, map2, list, (EventResult) null);
        $jacocoInit[380] = true;
    }

    public void fireEventOnNode(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2, List<Object> list, EventResult eventResult) {
        String str4 = str;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[381] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[382] = true;
        } else {
            $jacocoInit[383] = true;
            if (TextUtils.isEmpty(str3)) {
                $jacocoInit[384] = true;
            } else if (this.mJSHandler == null) {
                $jacocoInit[385] = true;
            } else if (checkMainThread()) {
                WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getAllInstanceMap().get(str4);
                $jacocoInit[389] = true;
                if (wXSDKInstance == null) {
                    $jacocoInit[390] = true;
                } else {
                    if (wXSDKInstance.getRenderStrategy() == WXRenderStrategy.DATA_RENDER) {
                        $jacocoInit[391] = true;
                    } else {
                        $jacocoInit[392] = true;
                        if (wXSDKInstance.getRenderStrategy() != WXRenderStrategy.DATA_RENDER_BINARY) {
                            $jacocoInit[393] = true;
                        } else {
                            $jacocoInit[394] = true;
                        }
                    }
                    fireEventOnDataRenderNode(str, str2, str3, map);
                    $jacocoInit[395] = true;
                    $jacocoInit[400] = true;
                    return;
                }
                if (eventResult == null) {
                    $jacocoInit[396] = true;
                    addJSEventTask(METHOD_FIRE_EVENT, str4, list, str2, str3, map, map2);
                    $jacocoInit[397] = true;
                    sendMessage(str4, 6);
                    $jacocoInit[398] = true;
                } else {
                    List<Object> list2 = list;
                    asyncCallJSEventWithResult(eventResult, METHD_FIRE_EVENT_SYNC, str, list, str2, str3, map, map2);
                    $jacocoInit[399] = true;
                }
                $jacocoInit[400] = true;
                return;
            } else {
                $jacocoInit[387] = true;
                WXRuntimeException wXRuntimeException = new WXRuntimeException("fireEvent must be called by main thread");
                $jacocoInit[388] = true;
                throw wXRuntimeException;
            }
        }
        $jacocoInit[386] = true;
    }

    private void fireEventOnDataRenderNode(String str, String str2, String str3, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        final String str4 = str;
        final Map<String, Object> map2 = map;
        final String str5 = str2;
        final String str6 = str3;
        this.mJSHandler.postDelayed(WXThread.secure((Runnable) new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2604017927194199544L, "com/taobao/weex/bridge/WXBridgeManager$10", 12);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                try {
                    WXSDKManager.getInstance().getSDKInstance(str4);
                    $jacocoInit[1] = true;
                    System.currentTimeMillis();
                    $jacocoInit[2] = true;
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[3] = true;
                    } else {
                        $jacocoInit[4] = true;
                        WXLogUtils.d("fireEventOnDataRenderNode >>>> instanceId:" + str4 + ", data:" + map2);
                        $jacocoInit[5] = true;
                    }
                    WXBridgeManager.access$500(this.this$0).fireEventOnDataRenderNode(str4, str5, str6, JSON.toJSONString(map2));
                    $jacocoInit[6] = true;
                } catch (Throwable th) {
                    $jacocoInit[7] = true;
                    String str = "[WXBridgeManager] fireEventOnDataRenderNode " + WXLogUtils.getStackTrace(th);
                    $jacocoInit[8] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(str4, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE, WXBridgeManager.METHOD_FIRE_EVENT_ON_DATA_RENDER_NODE, str, (Map<String, String>) null);
                    $jacocoInit[9] = true;
                    WXLogUtils.e(str);
                    $jacocoInit[10] = true;
                }
                $jacocoInit[11] = true;
            }
        }), 0);
        $jacocoInit[401] = true;
    }

    private boolean checkMainThread() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            $jacocoInit[402] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[403] = true;
        }
        $jacocoInit[404] = true;
        return z;
    }

    @Deprecated
    public void callback(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        callback(str, str2, str3, false);
        $jacocoInit[405] = true;
    }

    @Deprecated
    public void callback(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        callback(str, str2, map, false);
        $jacocoInit[406] = true;
    }

    @Deprecated
    public void callback(String str, String str2, Object obj, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        callbackJavascript(str, str2, obj, z);
        $jacocoInit[407] = true;
    }

    /* access modifiers changed from: package-private */
    public void callbackJavascript(String str, String str2, Object obj, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[408] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[409] = true;
        } else if (this.mJSHandler == null) {
            $jacocoInit[410] = true;
        } else {
            addJSTask("callback", str, str2, obj, Boolean.valueOf(z));
            $jacocoInit[412] = true;
            sendMessage(str, 6);
            $jacocoInit[413] = true;
            return;
        }
        $jacocoInit[411] = true;
    }

    public void refreshInstance(final String str, final WXRefreshData wXRefreshData) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[414] = true;
        } else if (wXRefreshData == null) {
            $jacocoInit[415] = true;
        } else {
            this.mJSHandler.postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXBridgeManager this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(1089939083840097143L, "com/taobao/weex/bridge/WXBridgeManager$11", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    WXBridgeManager.access$600(this.this$0, str, wXRefreshData);
                    $jacocoInit[1] = true;
                }
            }), 0);
            $jacocoInit[417] = true;
            return;
        }
        $jacocoInit[416] = true;
    }

    private void invokeRefreshInstance(String str, WXRefreshData wXRefreshData) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            $jacocoInit[418] = true;
            if (isJSFrameworkInit()) {
                $jacocoInit[419] = true;
                System.currentTimeMillis();
                $jacocoInit[427] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[428] = true;
                } else {
                    $jacocoInit[429] = true;
                    WXLogUtils.d("refreshInstance >>>> instanceId:" + str + ", data:" + wXRefreshData.data + ", isDirty:" + wXRefreshData.isDirty);
                    $jacocoInit[430] = true;
                }
                if (!wXRefreshData.isDirty) {
                    $jacocoInit[431] = true;
                    WXJSObject wXJSObject = new WXJSObject(2, str);
                    $jacocoInit[433] = true;
                    if (wXRefreshData.data == null) {
                        str2 = "{}";
                        $jacocoInit[434] = true;
                    } else {
                        str2 = wXRefreshData.data;
                        $jacocoInit[435] = true;
                    }
                    $jacocoInit[436] = true;
                    this.mWXBridge.refreshInstance(str, (String) null, METHOD_REFRESH_INSTANCE, new WXJSObject[]{wXJSObject, new WXJSObject(3, str2)});
                    $jacocoInit[437] = true;
                    $jacocoInit[442] = true;
                    return;
                }
                $jacocoInit[432] = true;
                return;
            }
            if (sDKInstance == null) {
                $jacocoInit[420] = true;
            } else {
                WXErrorCode wXErrorCode = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                $jacocoInit[421] = true;
                String errorCode = wXErrorCode.getErrorCode();
                StringBuilder sb = new StringBuilder();
                WXErrorCode wXErrorCode2 = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                $jacocoInit[422] = true;
                sb.append(wXErrorCode2.getErrorMsg());
                sb.append("invokeRefreshInstance FAILED for JSFrameworkInit FAILED, intance will invoke instance.onRenderError");
                String sb2 = sb.toString();
                $jacocoInit[423] = true;
                sDKInstance.onRenderError(errorCode, sb2);
                $jacocoInit[424] = true;
            }
            $jacocoInit[425] = true;
            WXLogUtils.e("[WXBridgeManager] invokeRefreshInstance: framework.js uninitialized.");
            $jacocoInit[426] = true;
        } catch (Throwable th) {
            $jacocoInit[438] = true;
            String str3 = "[WXBridgeManager] invokeRefreshInstance " + WXLogUtils.getStackTrace(th);
            $jacocoInit[439] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE, "invokeRefreshInstance", str3, (Map<String, String>) null);
            $jacocoInit[440] = true;
            WXLogUtils.e(str3);
            $jacocoInit[441] = true;
        }
    }

    public void commitJscCrashAlarmMonitor(String str, WXErrorCode wXErrorCode, String str2, String str3, String str4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[443] = true;
        } else if (wXErrorCode == null) {
            $jacocoInit[444] = true;
        } else {
            Log.d("ReportCrash", " commitJscCrashAlarmMonitor errMsg " + str2);
            $jacocoInit[446] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[447] = true;
            hashMap.put("jscCrashStack", str2);
            $jacocoInit[448] = true;
            IWXJSExceptionAdapter iWXJSExceptionAdapter = WXSDKManager.getInstance().getIWXJSExceptionAdapter();
            if (iWXJSExceptionAdapter == null) {
                $jacocoInit[449] = true;
            } else {
                $jacocoInit[450] = true;
                WXJSExceptionInfo wXJSExceptionInfo = new WXJSExceptionInfo(str3, str4, wXErrorCode, "callReportCrash", "weex core process crash and restart exception", hashMap);
                $jacocoInit[451] = true;
                iWXJSExceptionAdapter.onJSException(wXJSExceptionInfo);
                $jacocoInit[452] = true;
                WXLogUtils.e(wXJSExceptionInfo.toString());
                $jacocoInit[453] = true;
            }
            $jacocoInit[454] = true;
            return;
        }
        $jacocoInit[445] = true;
    }

    public void createInstance(String str, String str2, Map<String, Object> map, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        createInstance(str, new Script(str2), map, str3);
        $jacocoInit[455] = true;
    }

    public void createInstance(String str, Script script, Map<String, Object> map, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        final WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[456] = true;
            WXLogUtils.e("WXBridgeManager", "createInstance failed, SDKInstance does not exist");
            $jacocoInit[457] = true;
            return;
        }
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[458] = true;
        } else if (script == null) {
            $jacocoInit[459] = true;
        } else if (script.isEmpty()) {
            $jacocoInit[460] = true;
        } else if (this.mJSHandler == null) {
            $jacocoInit[461] = true;
        } else {
            if (isJSFrameworkInit()) {
                $jacocoInit[467] = true;
            } else if (reInitCount != 1) {
                $jacocoInit[468] = true;
            } else if (WXEnvironment.sDebugServerConnectable) {
                $jacocoInit[469] = true;
            } else {
                WXErrorCode wXErrorCode = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                $jacocoInit[470] = true;
                String errorCode = wXErrorCode.getErrorCode();
                StringBuilder sb = new StringBuilder();
                WXErrorCode wXErrorCode2 = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                $jacocoInit[471] = true;
                sb.append(wXErrorCode2.getErrorMsg());
                sb.append(" isJSFrameworkInit==");
                $jacocoInit[472] = true;
                sb.append(isJSFrameworkInit());
                sb.append(" reInitCount == 1");
                String sb2 = sb.toString();
                $jacocoInit[473] = true;
                sDKInstance.onRenderError(errorCode, sb2);
                $jacocoInit[474] = true;
                post(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXBridgeManager this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-2846745091161928048L, "com/taobao/weex/bridge/WXBridgeManager$12", 2);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        WXBridgeManager.access$200(this.this$0, "");
                        $jacocoInit[1] = true;
                    }
                }, str);
                $jacocoInit[475] = true;
                return;
            }
            WXModuleManager.createDomModule(sDKInstance);
            $jacocoInit[476] = true;
            final Script script2 = script;
            final Map<String, Object> map2 = map;
            final String str3 = str2;
            post(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXBridgeManager this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-7154972800759203412L, "com/taobao/weex/bridge/WXBridgeManager$13", 7);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    long currentTimeMillis = System.currentTimeMillis();
                    $jacocoInit[1] = true;
                    sDKInstance.getApmForInstance().onStage(WXInstanceApm.KEY_PAGE_STAGES_LOAD_BUNDLE_START);
                    $jacocoInit[2] = true;
                    WXBridgeManager.access$700(this.this$0, sDKInstance, script2, map2, str3);
                    $jacocoInit[3] = true;
                    long currentTimeMillis2 = System.currentTimeMillis();
                    $jacocoInit[4] = true;
                    sDKInstance.getWXPerformance().callCreateInstanceTime = currentTimeMillis2 - currentTimeMillis;
                    $jacocoInit[5] = true;
                    sDKInstance.getWXPerformance().communicateTime = sDKInstance.getWXPerformance().callCreateInstanceTime;
                    $jacocoInit[6] = true;
                }
            }, str);
            $jacocoInit[477] = true;
            return;
        }
        WXErrorCode wXErrorCode3 = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
        $jacocoInit[462] = true;
        String errorCode2 = wXErrorCode3.getErrorCode();
        StringBuilder sb3 = new StringBuilder();
        WXErrorCode wXErrorCode4 = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
        $jacocoInit[463] = true;
        sb3.append(wXErrorCode4.getErrorMsg());
        sb3.append(" instanceId==");
        sb3.append(str);
        sb3.append(" template ==");
        sb3.append(script);
        sb3.append(" mJSHandler== ");
        Handler handler = this.mJSHandler;
        $jacocoInit[464] = true;
        sb3.append(handler.toString());
        String sb4 = sb3.toString();
        $jacocoInit[465] = true;
        sDKInstance.onRenderError(errorCode2, sb4);
        $jacocoInit[466] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x02b1 A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x035f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c9 A[Catch:{ Throwable -> 0x015b }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d0 A[Catch:{ Throwable -> 0x015b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e6 A[Catch:{ Throwable -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00eb A[Catch:{ Throwable -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0139 A[Catch:{ Throwable -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013e A[Catch:{ Throwable -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0175 A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x017a A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x019d A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01a4 A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01c6 A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01cd A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01da A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x022d A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0245 A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0259 A[Catch:{ Throwable -> 0x00b8, Throwable -> 0x0375 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void invokeCreateInstance(@android.support.annotation.NonNull com.taobao.weex.WXSDKInstance r16, com.taobao.weex.Script r17, java.util.Map<java.lang.String, java.lang.Object> r18, java.lang.String r19) {
        /*
            r15 = this;
            r7 = r15
            r8 = r16
            boolean[] r9 = $jacocoInit()
            java.lang.String r0 = ""
            r15.initFramework(r0)
            boolean r0 = r7.mMock
            r10 = 1
            if (r0 == 0) goto L_0x0022
            r0 = 478(0x1de, float:6.7E-43)
            r9[r0] = r10
            java.lang.String r0 = r16.getInstanceId()
            r15.mock(r0)
            r0 = 479(0x1df, float:6.71E-43)
            r9[r0] = r10
            goto L_0x03cf
        L_0x0022:
            boolean r0 = r15.isJSFrameworkInit()
            if (r0 != 0) goto L_0x0051
            java.lang.String r0 = "[WXBridgeManager] invokeCreateInstance: framework.js uninitialized."
            com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED
            r2 = 480(0x1e0, float:6.73E-43)
            r9[r2] = r10
            java.lang.String r1 = r1.getErrorCode()
            com.taobao.weex.common.WXErrorCode r2 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED
            r3 = 481(0x1e1, float:6.74E-43)
            r9[r3] = r10
            java.lang.String r2 = r2.getErrorMsg()
            r3 = 482(0x1e2, float:6.75E-43)
            r9[r3] = r10
            r8.onRenderError(r1, r2)
            r1 = 483(0x1e3, float:6.77E-43)
            r9[r1] = r10
            com.taobao.weex.utils.WXLogUtils.e(r0)
            r0 = 484(0x1e4, float:6.78E-43)
            r9[r0] = r10
            return
        L_0x0051:
            com.taobao.weex.bridge.WXModuleManager.registerWhenCreateInstance()
            r0 = 485(0x1e5, float:6.8E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXBridgeManager$BundType r1 = com.taobao.weex.bridge.WXBridgeManager.BundType.Others     // Catch:{ Throwable -> 0x0375 }
            r0 = 486(0x1e6, float:6.81E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x00ba }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00ba }
            r0 = 487(0x1e7, float:6.82E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x00ba }
            java.lang.String r0 = r16.getBundleUrl()     // Catch:{ Throwable -> 0x00ba }
            java.lang.String r4 = r17.getContent()     // Catch:{ Throwable -> 0x00ba }
            com.taobao.weex.bridge.WXBridgeManager$BundType r4 = r15.getBundleType(r0, r4)     // Catch:{ Throwable -> 0x00ba }
            r0 = 488(0x1e8, float:6.84E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x00b8 }
            boolean r0 = com.taobao.weex.WXEnvironment.isOpenDebugLog()     // Catch:{ Throwable -> 0x00b8 }
            if (r0 != 0) goto L_0x0081
            r0 = 489(0x1e9, float:6.85E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x00b8 }
            goto L_0x00b3
        L_0x0081:
            r0 = 490(0x1ea, float:6.87E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x00b8 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00b8 }
            r5 = 491(0x1eb, float:6.88E-43)
            r9[r5] = r10     // Catch:{ Throwable -> 0x00b8 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b8 }
            r5.<init>()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r6 = "end getBundleType type:"
            r5.append(r6)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r6 = r4.toString()     // Catch:{ Throwable -> 0x00b8 }
            r5.append(r6)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r6 = " time:"
            r5.append(r6)     // Catch:{ Throwable -> 0x00b8 }
            r6 = 0
            long r0 = r0 - r2
            r5.append(r0)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r0 = r5.toString()     // Catch:{ Throwable -> 0x00b8 }
            com.taobao.weex.utils.WXLogUtils.e(r0)     // Catch:{ Throwable -> 0x00b8 }
            r0 = 492(0x1ec, float:6.9E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x00b8 }
        L_0x00b3:
            r0 = 493(0x1ed, float:6.91E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x00c7
        L_0x00b8:
            r0 = move-exception
            goto L_0x00bc
        L_0x00ba:
            r0 = move-exception
            r4 = r1
        L_0x00bc:
            r1 = 494(0x1ee, float:6.92E-43)
            r9[r1] = r10     // Catch:{ Throwable -> 0x0375 }
            r0.printStackTrace()     // Catch:{ Throwable -> 0x0375 }
            r0 = 495(0x1ef, float:6.94E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x015b }
        L_0x00c7:
            if (r18 == 0) goto L_0x00d0
            r0 = 496(0x1f0, float:6.95E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x015b }
            r1 = r18
            goto L_0x00de
        L_0x00d0:
            r0 = 497(0x1f1, float:6.96E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x015b }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Throwable -> 0x015b }
            r2.<init>()     // Catch:{ Throwable -> 0x015b }
            r0 = 498(0x1f2, float:6.98E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0158 }
            r1 = r2
        L_0x00de:
            java.lang.String r0 = "bundleType"
            java.lang.Object r0 = r1.get(r0)     // Catch:{ Throwable -> 0x0156 }
            if (r0 == 0) goto L_0x00eb
            r0 = 499(0x1f3, float:6.99E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            goto L_0x0131
        L_0x00eb:
            com.taobao.weex.bridge.WXBridgeManager$BundType r0 = com.taobao.weex.bridge.WXBridgeManager.BundType.Vue     // Catch:{ Throwable -> 0x0156 }
            if (r4 != r0) goto L_0x00ff
            r0 = 500(0x1f4, float:7.0E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            java.lang.String r0 = "bundleType"
            java.lang.String r2 = "Vue"
            r1.put(r0, r2)     // Catch:{ Throwable -> 0x0156 }
            r0 = 501(0x1f5, float:7.02E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            goto L_0x011e
        L_0x00ff:
            com.taobao.weex.bridge.WXBridgeManager$BundType r0 = com.taobao.weex.bridge.WXBridgeManager.BundType.Rax     // Catch:{ Throwable -> 0x0156 }
            if (r4 != r0) goto L_0x0113
            r0 = 502(0x1f6, float:7.03E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            java.lang.String r0 = "bundleType"
            java.lang.String r2 = "Rax"
            r1.put(r0, r2)     // Catch:{ Throwable -> 0x0156 }
            r0 = 503(0x1f7, float:7.05E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            goto L_0x011e
        L_0x0113:
            java.lang.String r0 = "bundleType"
            java.lang.String r2 = "Others"
            r1.put(r0, r2)     // Catch:{ Throwable -> 0x0156 }
            r0 = 504(0x1f8, float:7.06E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
        L_0x011e:
            com.taobao.weex.performance.WXInstanceApm r0 = r16.getApmForInstance()     // Catch:{ Throwable -> 0x0156 }
            java.lang.String r2 = "wxBundleType"
            java.lang.String r3 = "bundleType"
            java.lang.Object r3 = r1.get(r3)     // Catch:{ Throwable -> 0x0156 }
            r0.addProperty(r2, r3)     // Catch:{ Throwable -> 0x0156 }
            r0 = 505(0x1f9, float:7.08E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
        L_0x0131:
            java.lang.String r0 = "env"
            java.lang.Object r0 = r1.get(r0)     // Catch:{ Throwable -> 0x0156 }
            if (r0 == 0) goto L_0x013e
            r0 = 506(0x1fa, float:7.09E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            goto L_0x0151
        L_0x013e:
            r0 = 507(0x1fb, float:7.1E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
            java.lang.String r0 = "env"
            com.taobao.weex.bridge.WXParams r2 = r7.mInitParams     // Catch:{ Throwable -> 0x0156 }
            java.util.Map r2 = r2.toMap()     // Catch:{ Throwable -> 0x0156 }
            r1.put(r0, r2)     // Catch:{ Throwable -> 0x0156 }
            r0 = 508(0x1fc, float:7.12E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0156 }
        L_0x0151:
            r0 = 509(0x1fd, float:7.13E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0169
        L_0x0156:
            r0 = move-exception
            goto L_0x015e
        L_0x0158:
            r0 = move-exception
            r1 = r2
            goto L_0x015e
        L_0x015b:
            r0 = move-exception
            r1 = r18
        L_0x015e:
            r2 = 510(0x1fe, float:7.15E-43)
            r9[r2] = r10     // Catch:{ Throwable -> 0x0375 }
            r0.printStackTrace()     // Catch:{ Throwable -> 0x0375 }
            r0 = 511(0x1ff, float:7.16E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x0169:
            r8.bundleType = r4     // Catch:{ Throwable -> 0x0375 }
            r0 = 512(0x200, float:7.175E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            boolean r0 = com.taobao.weex.WXEnvironment.isApkDebugable()     // Catch:{ Throwable -> 0x0375 }
            if (r0 != 0) goto L_0x017a
            r0 = 513(0x201, float:7.19E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x017e
        L_0x017a:
            r0 = 514(0x202, float:7.2E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x017e:
            com.taobao.weex.bridge.WXJSObject r0 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            r2 = 515(0x203, float:7.22E-43)
            r9[r2] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r2 = r16.getInstanceId()     // Catch:{ Throwable -> 0x0375 }
            r3 = 2
            r0.<init>(r3, r2)     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXJSObject r2 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            r5 = 516(0x204, float:7.23E-43)
            r9[r5] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r5 = r17.getContent()     // Catch:{ Throwable -> 0x0375 }
            r2.<init>(r3, r5)     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXJSObject r5 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            if (r1 != 0) goto L_0x01a4
            java.lang.String r1 = "{}"
            r6 = 517(0x205, float:7.24E-43)
            r9[r6] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x01b0
        L_0x01a4:
            r6 = 518(0x206, float:7.26E-43)
            r9[r6] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r1 = com.taobao.weex.utils.WXJsonUtils.fromObjectToJSONString(r1)     // Catch:{ Throwable -> 0x0375 }
            r6 = 519(0x207, float:7.27E-43)
            r9[r6] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x01b0:
            r6 = 3
            r5.<init>(r6, r1)     // Catch:{ Throwable -> 0x0375 }
            r1 = 520(0x208, float:7.29E-43)
            r9[r1] = r10     // Catch:{ Throwable -> 0x0375 }
            boolean r1 = isSandBoxContext     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXJSObject r1 = r15.optionObjConvert(r1, r4, r5)     // Catch:{ Throwable -> 0x0375 }
            r5 = 521(0x209, float:7.3E-43)
            r9[r5] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXJSObject r5 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            if (r19 != 0) goto L_0x01cd
            java.lang.String r11 = "{}"
            r12 = 522(0x20a, float:7.31E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x01d3
        L_0x01cd:
            r12 = 523(0x20b, float:7.33E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
            r11 = r19
        L_0x01d3:
            r5.<init>(r6, r11)     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXBridgeManager$BundType r11 = com.taobao.weex.bridge.WXBridgeManager.BundType.Rax     // Catch:{ Throwable -> 0x0375 }
            if (r4 != r11) goto L_0x022d
            java.lang.String r11 = mRaxApi     // Catch:{ Throwable -> 0x0375 }
            if (r11 == 0) goto L_0x01e3
            r11 = 524(0x20c, float:7.34E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0221
        L_0x01e3:
            r11 = 525(0x20d, float:7.36E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.adapter.IWXJsFileLoaderAdapter r11 = com.taobao.weex.WXSDKEngine.getIWXJsFileLoaderAdapter()     // Catch:{ Throwable -> 0x0375 }
            if (r11 != 0) goto L_0x01f2
            r11 = 526(0x20e, float:7.37E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0200
        L_0x01f2:
            r12 = 527(0x20f, float:7.38E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r11 = r11.loadRaxApi()     // Catch:{ Throwable -> 0x0375 }
            mRaxApi = r11     // Catch:{ Throwable -> 0x0375 }
            r11 = 528(0x210, float:7.4E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x0200:
            java.lang.String r11 = mRaxApi     // Catch:{ Throwable -> 0x0375 }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ Throwable -> 0x0375 }
            if (r11 != 0) goto L_0x020d
            r11 = 529(0x211, float:7.41E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0221
        L_0x020d:
            r11 = 530(0x212, float:7.43E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r11 = "weex-rax-api.js"
            android.app.Application r12 = com.taobao.weex.WXEnvironment.getApplication()     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r11 = com.taobao.weex.utils.WXFileUtils.loadAsset(r11, r12)     // Catch:{ Throwable -> 0x0375 }
            mRaxApi = r11     // Catch:{ Throwable -> 0x0375 }
            r11 = 531(0x213, float:7.44E-43)
            r9[r11] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x0221:
            com.taobao.weex.bridge.WXJSObject r11 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r12 = mRaxApi     // Catch:{ Throwable -> 0x0375 }
            r11.<init>(r3, r12)     // Catch:{ Throwable -> 0x0375 }
            r12 = 532(0x214, float:7.45E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0238
        L_0x022d:
            com.taobao.weex.bridge.WXJSObject r11 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r12 = ""
            r11.<init>(r3, r12)     // Catch:{ Throwable -> 0x0375 }
            r12 = 533(0x215, float:7.47E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x0238:
            r12 = 0
            r13 = 534(0x216, float:7.48E-43)
            r9[r13] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r13 = r16.getRenderStrategy()     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r14 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER     // Catch:{ Throwable -> 0x0375 }
            if (r13 != r14) goto L_0x0259
            r12 = 535(0x217, float:7.5E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXJSObject r12 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r13 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r13 = r13.getFlag()     // Catch:{ Throwable -> 0x0375 }
            r12.<init>(r3, r13)     // Catch:{ Throwable -> 0x0375 }
            r13 = 536(0x218, float:7.51E-43)
            r9[r13] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0283
        L_0x0259:
            com.taobao.weex.common.WXRenderStrategy r13 = r16.getRenderStrategy()     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r14 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER_BINARY     // Catch:{ Throwable -> 0x0375 }
            if (r13 == r14) goto L_0x0266
            r13 = 537(0x219, float:7.52E-43)
            r9[r13] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x0283
        L_0x0266:
            r12 = 538(0x21a, float:7.54E-43)
            r9[r12] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXJSObject r12 = new com.taobao.weex.bridge.WXJSObject     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r13 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER_BINARY     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r13 = r13.getFlag()     // Catch:{ Throwable -> 0x0375 }
            r12.<init>(r3, r13)     // Catch:{ Throwable -> 0x0375 }
            r13 = 539(0x21b, float:7.55E-43)
            r9[r13] = r10     // Catch:{ Throwable -> 0x0375 }
            byte[] r13 = r17.getBinary()     // Catch:{ Throwable -> 0x0375 }
            r2.data = r13     // Catch:{ Throwable -> 0x0375 }
            r13 = 540(0x21c, float:7.57E-43)
            r9[r13] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x0283:
            r13 = 6
            com.taobao.weex.bridge.WXJSObject[] r13 = new com.taobao.weex.bridge.WXJSObject[r13]     // Catch:{ Throwable -> 0x0375 }
            r14 = 0
            r13[r14] = r0     // Catch:{ Throwable -> 0x0375 }
            r13[r10] = r2     // Catch:{ Throwable -> 0x0375 }
            r13[r3] = r1     // Catch:{ Throwable -> 0x0375 }
            r13[r6] = r5     // Catch:{ Throwable -> 0x0375 }
            r0 = 4
            r13[r0] = r11     // Catch:{ Throwable -> 0x0375 }
            r0 = 5
            r13[r0] = r12     // Catch:{ Throwable -> 0x0375 }
            r0 = 541(0x21d, float:7.58E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r0 = r17.getContent()     // Catch:{ Throwable -> 0x0375 }
            r8.setTemplate(r0)     // Catch:{ Throwable -> 0x0375 }
            r0 = 542(0x21e, float:7.6E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.performance.WXInstanceApm r0 = r16.getApmForInstance()     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r1 = "wxEndLoadBundle"
            r0.onStage(r1)     // Catch:{ Throwable -> 0x0375 }
            boolean r0 = isSandBoxContext     // Catch:{ Throwable -> 0x0375 }
            if (r0 == 0) goto L_0x035f
            r0 = 543(0x21f, float:7.61E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.bridge.WXBridgeManager$BundType r0 = com.taobao.weex.bridge.WXBridgeManager.BundType.Vue     // Catch:{ Throwable -> 0x0375 }
            if (r4 != r0) goto L_0x02be
            r0 = 546(0x222, float:7.65E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x02fe
        L_0x02be:
            com.taobao.weex.bridge.WXBridgeManager$BundType r0 = com.taobao.weex.bridge.WXBridgeManager.BundType.Rax     // Catch:{ Throwable -> 0x0375 }
            if (r4 != r0) goto L_0x02c7
            r0 = 547(0x223, float:7.67E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x02fe
        L_0x02c7:
            r0 = 548(0x224, float:7.68E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r0 = r16.getRenderStrategy()     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r1 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER     // Catch:{ Throwable -> 0x0375 }
            if (r0 != r1) goto L_0x02d8
            r0 = 549(0x225, float:7.7E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x02fe
        L_0x02d8:
            r0 = 550(0x226, float:7.71E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r0 = r16.getRenderStrategy()     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXRenderStrategy r1 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER_BINARY     // Catch:{ Throwable -> 0x0375 }
            if (r0 == r1) goto L_0x02fa
            r0 = 551(0x227, float:7.72E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r2 = r16.getInstanceId()     // Catch:{ Throwable -> 0x0375 }
            r3 = 0
            java.lang.String r4 = "createInstance"
            r6 = 0
            r1 = r15
            r5 = r13
            r1.invokeExecJS(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0375 }
            r0 = 560(0x230, float:7.85E-43)
            r9[r0] = r10
            return
        L_0x02fa:
            r0 = 552(0x228, float:7.74E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x02fe:
            java.lang.String r2 = r16.getInstanceId()     // Catch:{ Throwable -> 0x0375 }
            r3 = 0
            java.lang.String r4 = "createInstanceContext"
            r6 = 0
            r1 = r15
            r5 = r13
            int r0 = r1.invokeCreateInstanceContext(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0375 }
            if (r0 == 0) goto L_0x0313
            r0 = 553(0x229, float:7.75E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            goto L_0x035a
        L_0x0313:
            r0 = 554(0x22a, float:7.76E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0375 }
            r0.<init>()     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r1 = "[WXBridgeManager] invokeCreateInstance : "
            r0.append(r1)     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r1 = r16.getTemplateInfo()     // Catch:{ Throwable -> 0x0375 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED     // Catch:{ Throwable -> 0x0375 }
            r2 = 555(0x22b, float:7.78E-43)
            r9[r2] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r1 = r1.getErrorCode()     // Catch:{ Throwable -> 0x0375 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0375 }
            r2.<init>()     // Catch:{ Throwable -> 0x0375 }
            com.taobao.weex.common.WXErrorCode r3 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED     // Catch:{ Throwable -> 0x0375 }
            r4 = 556(0x22c, float:7.79E-43)
            r9[r4] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r3 = r3.getErrorMsg()     // Catch:{ Throwable -> 0x0375 }
            r2.append(r3)     // Catch:{ Throwable -> 0x0375 }
            r2.append(r0)     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r0 = r2.toString()     // Catch:{ Throwable -> 0x0375 }
            r2 = 557(0x22d, float:7.8E-43)
            r9[r2] = r10     // Catch:{ Throwable -> 0x0375 }
            r8.onRenderError(r1, r0)     // Catch:{ Throwable -> 0x0375 }
            r0 = 558(0x22e, float:7.82E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
        L_0x035a:
            r0 = 559(0x22f, float:7.83E-43)
            r9[r0] = r10
            return
        L_0x035f:
            r0 = 544(0x220, float:7.62E-43)
            r9[r0] = r10     // Catch:{ Throwable -> 0x0375 }
            java.lang.String r2 = r16.getInstanceId()     // Catch:{ Throwable -> 0x0375 }
            r3 = 0
            java.lang.String r4 = "createInstance"
            r6 = 0
            r1 = r15
            r5 = r13
            r1.invokeExecJS(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0375 }
            r0 = 545(0x221, float:7.64E-43)
            r9[r0] = r10
            return
        L_0x0375:
            r0 = move-exception
            r1 = 561(0x231, float:7.86E-43)
            r9[r1] = r10
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[WXBridgeManager] invokeCreateInstance "
            r1.append(r2)
            java.lang.Throwable r0 = r0.getCause()
            r1.append(r0)
            r0 = 562(0x232, float:7.88E-43)
            r9[r0] = r10
            java.lang.String r0 = r16.getTemplateInfo()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED
            r2 = 563(0x233, float:7.89E-43)
            r9[r2] = r10
            java.lang.String r1 = r1.getErrorCode()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.taobao.weex.common.WXErrorCode r3 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED
            r4 = 564(0x234, float:7.9E-43)
            r9[r4] = r10
            java.lang.String r3 = r3.getErrorMsg()
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            r3 = 565(0x235, float:7.92E-43)
            r9[r3] = r10
            r8.onRenderError(r1, r2)
            r1 = 566(0x236, float:7.93E-43)
            r9[r1] = r10
            com.taobao.weex.utils.WXLogUtils.e(r0)
            r0 = 567(0x237, float:7.95E-43)
            r9[r0] = r10
        L_0x03cf:
            r0 = 568(0x238, float:7.96E-43)
            r9[r0] = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.bridge.WXBridgeManager.invokeCreateInstance(com.taobao.weex.WXSDKInstance, com.taobao.weex.Script, java.util.Map, java.lang.String):void");
    }

    public WXJSObject optionObjConvert(boolean z, BundType bundType, WXJSObject wXJSObject) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!z) {
            $jacocoInit[569] = true;
        } else if (bundType != BundType.Others) {
            $jacocoInit[570] = true;
            try {
                String obj = wXJSObject.data.toString();
                $jacocoInit[573] = true;
                JSONObject parseObject = JSON.parseObject(obj);
                $jacocoInit[574] = true;
                if (parseObject.getJSONObject(Constants.d) == null) {
                    $jacocoInit[575] = true;
                } else {
                    $jacocoInit[576] = true;
                    JSONObject jSONObject = parseObject.getJSONObject(Constants.d);
                    if (jSONObject == null) {
                        $jacocoInit[577] = true;
                    } else {
                        $jacocoInit[578] = true;
                        JSONObject jSONObject2 = jSONObject.getJSONObject("options");
                        if (jSONObject2 == null) {
                            $jacocoInit[579] = true;
                        } else {
                            $jacocoInit[580] = true;
                            jSONObject.remove("options");
                            $jacocoInit[581] = true;
                            Set<String> keySet = jSONObject2.keySet();
                            $jacocoInit[582] = true;
                            $jacocoInit[583] = true;
                            for (String obj2 : keySet) {
                                $jacocoInit[585] = true;
                                String obj3 = obj2.toString();
                                $jacocoInit[586] = true;
                                jSONObject.put(obj3, (Object) jSONObject2.getString(obj3));
                                $jacocoInit[587] = true;
                            }
                            $jacocoInit[584] = true;
                        }
                    }
                    parseObject.remove(Constants.d);
                    $jacocoInit[588] = true;
                    parseObject.put(Constants.d, (Object) jSONObject);
                    $jacocoInit[589] = true;
                }
                WXJSObject wXJSObject2 = new WXJSObject(3, parseObject.toString());
                $jacocoInit[590] = true;
                return wXJSObject2;
            } catch (Throwable th) {
                $jacocoInit[591] = true;
                th.printStackTrace();
                $jacocoInit[592] = true;
                return wXJSObject;
            }
        } else {
            $jacocoInit[571] = true;
        }
        $jacocoInit[572] = true;
        return wXJSObject;
    }

    public BundType getBundleType(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            try {
                $jacocoInit[593] = true;
            } catch (Throwable th) {
                $jacocoInit[656] = true;
                th.printStackTrace();
                BundType bundType = BundType.Others;
                $jacocoInit[657] = true;
                return bundType;
            }
        } else {
            $jacocoInit[594] = true;
            Uri parse = Uri.parse(str);
            $jacocoInit[595] = true;
            String queryParameter = parse.getQueryParameter(BUNDLE_TYPE);
            $jacocoInit[596] = true;
            if ("Vue".equals(queryParameter)) {
                $jacocoInit[597] = true;
            } else if (!"vue".equals(queryParameter)) {
                $jacocoInit[598] = true;
                if ("Rax".equals(queryParameter)) {
                    $jacocoInit[601] = true;
                } else if (!"rax".equals(queryParameter)) {
                    $jacocoInit[602] = true;
                } else {
                    $jacocoInit[603] = true;
                }
                BundType bundType2 = BundType.Rax;
                $jacocoInit[604] = true;
                return bundType2;
            } else {
                $jacocoInit[599] = true;
            }
            BundType bundType3 = BundType.Vue;
            $jacocoInit[600] = true;
            return bundType3;
        }
        if (str2 == null) {
            $jacocoInit[605] = true;
        } else {
            $jacocoInit[606] = true;
            if (str2.startsWith("// { \"framework\": \"Vue\" }")) {
                $jacocoInit[607] = true;
            } else {
                $jacocoInit[608] = true;
                if (str2.startsWith("// { \"framework\": \"vue\" }")) {
                    $jacocoInit[609] = true;
                } else {
                    $jacocoInit[610] = true;
                    if (str2.startsWith("// {\"framework\" : \"Vue\"}")) {
                        $jacocoInit[611] = true;
                    } else {
                        $jacocoInit[612] = true;
                        if (!str2.startsWith("// {\"framework\" : \"vue\"}")) {
                            $jacocoInit[613] = true;
                            if (str2.startsWith("// { \"framework\": \"Rax\" }")) {
                                $jacocoInit[616] = true;
                            } else {
                                $jacocoInit[617] = true;
                                if (str2.startsWith("// { \"framework\": \"rax\" }")) {
                                    $jacocoInit[618] = true;
                                } else {
                                    $jacocoInit[619] = true;
                                    if (str2.startsWith("// {\"framework\" : \"Rax\"}")) {
                                        $jacocoInit[620] = true;
                                    } else {
                                        $jacocoInit[621] = true;
                                        if (!str2.startsWith("// {\"framework\" : \"rax\"}")) {
                                            $jacocoInit[622] = true;
                                            if (str2.length() <= 500) {
                                                $jacocoInit[625] = true;
                                            } else {
                                                $jacocoInit[626] = true;
                                                str2 = str2.substring(0, 500);
                                                $jacocoInit[627] = true;
                                            }
                                            String trim = str2.replaceAll("\n", "").trim();
                                            $jacocoInit[628] = true;
                                            if (trim.startsWith("// { \"framework\": \"Vue\" }")) {
                                                $jacocoInit[629] = true;
                                            } else {
                                                $jacocoInit[630] = true;
                                                if (trim.startsWith("// { \"framework\": \"vue\" }")) {
                                                    $jacocoInit[631] = true;
                                                } else {
                                                    $jacocoInit[632] = true;
                                                    if (trim.startsWith("// {\"framework\" : \"Vue\"}")) {
                                                        $jacocoInit[633] = true;
                                                    } else {
                                                        $jacocoInit[634] = true;
                                                        if (!trim.startsWith("// {\"framework\" : \"vue\"}")) {
                                                            $jacocoInit[635] = true;
                                                            if (trim.startsWith("// { \"framework\": \"Rax\" }")) {
                                                                $jacocoInit[638] = true;
                                                            } else {
                                                                $jacocoInit[639] = true;
                                                                if (trim.startsWith("// { \"framework\": \"rax\" }")) {
                                                                    $jacocoInit[640] = true;
                                                                } else {
                                                                    $jacocoInit[641] = true;
                                                                    if (trim.startsWith("// {\"framework\" : \"Rax\"}")) {
                                                                        $jacocoInit[642] = true;
                                                                    } else {
                                                                        $jacocoInit[643] = true;
                                                                        if (!trim.startsWith("// {\"framework\" : \"rax\"}")) {
                                                                            $jacocoInit[644] = true;
                                                                            $jacocoInit[647] = true;
                                                                            Pattern compile = Pattern.compile("(use)(\\s+)(weex:vue)", 2);
                                                                            $jacocoInit[648] = true;
                                                                            if (!compile.matcher(str2).find()) {
                                                                                $jacocoInit[649] = true;
                                                                                $jacocoInit[651] = true;
                                                                                Pattern compile2 = Pattern.compile("(use)(\\s+)(weex:rax)", 2);
                                                                                $jacocoInit[652] = true;
                                                                                if (!compile2.matcher(str2).find()) {
                                                                                    $jacocoInit[653] = true;
                                                                                } else {
                                                                                    BundType bundType4 = BundType.Rax;
                                                                                    $jacocoInit[654] = true;
                                                                                    return bundType4;
                                                                                }
                                                                            } else {
                                                                                BundType bundType5 = BundType.Vue;
                                                                                $jacocoInit[650] = true;
                                                                                return bundType5;
                                                                            }
                                                                        } else {
                                                                            $jacocoInit[645] = true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            BundType bundType6 = BundType.Rax;
                                                            $jacocoInit[646] = true;
                                                            return bundType6;
                                                        }
                                                        $jacocoInit[636] = true;
                                                    }
                                                }
                                            }
                                            BundType bundType7 = BundType.Vue;
                                            $jacocoInit[637] = true;
                                            return bundType7;
                                        }
                                        $jacocoInit[623] = true;
                                    }
                                }
                            }
                            BundType bundType8 = BundType.Rax;
                            $jacocoInit[624] = true;
                            return bundType8;
                        }
                        $jacocoInit[614] = true;
                    }
                }
            }
            BundType bundType9 = BundType.Vue;
            $jacocoInit[615] = true;
            return bundType9;
        }
        BundType bundType10 = BundType.Others;
        $jacocoInit[655] = true;
        return bundType10;
    }

    private void mock(String str) {
        $jacocoInit()[658] = true;
    }

    public void destroyInstance(final String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSHandler == null) {
            $jacocoInit[659] = true;
        } else {
            $jacocoInit[660] = true;
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[661] = true;
            } else {
                if (this.mDestroyedInstanceId == null) {
                    $jacocoInit[663] = true;
                } else {
                    $jacocoInit[664] = true;
                    this.mDestroyedInstanceId.add(str);
                    $jacocoInit[665] = true;
                }
                this.mJSHandler.removeCallbacksAndMessages(str);
                $jacocoInit[666] = true;
                post(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXBridgeManager this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-4450374028595941156L, "com/taobao/weex/bridge/WXBridgeManager$14", 3);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        WXBridgeManager.access$800(this.this$0, str);
                        $jacocoInit[1] = true;
                        WXBridgeManager.access$900(this.this$0, str);
                        $jacocoInit[2] = true;
                    }
                }, str);
                $jacocoInit[667] = true;
                return;
            }
        }
        $jacocoInit[662] = true;
    }

    private void removeTaskByInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNextTickTasks.removeFromMapAndStack(str);
        $jacocoInit[668] = true;
    }

    private void invokeDestroyInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[669] = true;
            } else {
                $jacocoInit[670] = true;
            }
            WXJSObject[] wXJSObjectArr = {new WXJSObject(2, str)};
            $jacocoInit[671] = true;
            if (!isJSFrameworkInit()) {
                $jacocoInit[672] = true;
            } else {
                $jacocoInit[673] = true;
                invokeDestoryInstance(str, (String) null, METHOD_DESTROY_INSTANCE, wXJSObjectArr, true);
                $jacocoInit[674] = true;
            }
            $jacocoInit[675] = true;
        } catch (Throwable th) {
            $jacocoInit[676] = true;
            String str2 = "[WXBridgeManager] invokeDestroyInstance " + th.getCause();
            $jacocoInit[677] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE, "invokeDestroyInstance", str2, (Map<String, String>) null);
            $jacocoInit[678] = true;
            WXLogUtils.e(str2);
            $jacocoInit[679] = true;
        }
        $jacocoInit[680] = true;
    }

    public boolean handleMessage(Message message) {
        boolean[] $jacocoInit = $jacocoInit();
        if (message == null) {
            $jacocoInit[681] = true;
            return false;
        }
        int i = message.what;
        if (i == 1) {
            TimerInfo timerInfo = (TimerInfo) message.obj;
            if (timerInfo == null) {
                $jacocoInit[685] = true;
            } else {
                $jacocoInit[686] = true;
                invokeExecJS("", (String) null, METHOD_SET_TIMEOUT, new WXJSObject[]{new WXJSObject(2, timerInfo.callbackId)});
                $jacocoInit[687] = true;
            }
        } else if (i != 13) {
            switch (i) {
                case 6:
                    invokeCallJSBatch(message);
                    $jacocoInit[684] = true;
                    break;
                case 7:
                    invokeInitFramework(message);
                    $jacocoInit[683] = true;
                    break;
                default:
                    $jacocoInit[682] = true;
                    break;
            }
        } else if (message.obj == null) {
            $jacocoInit[688] = true;
        } else {
            $jacocoInit[689] = true;
            this.mWXBridge.takeHeapSnapshot((String) message.obj);
            $jacocoInit[690] = true;
        }
        $jacocoInit[691] = true;
        return false;
    }

    private void invokeExecJS(String str, String str2, String str3, WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        invokeExecJS(str, str2, str3, wXJSObjectArr, true);
        $jacocoInit[692] = true;
    }

    public void invokeExecJS(String str, String str2, String str3, WXJSObject[] wXJSObjectArr, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isOpenDebugLog()) {
            $jacocoInit[693] = true;
        } else {
            $jacocoInit[694] = true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        $jacocoInit[695] = true;
        this.mWXBridge.execJS(str, str2, str3, wXJSObjectArr);
        $jacocoInit[696] = true;
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[697] = true;
        } else {
            $jacocoInit[698] = true;
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            $jacocoInit[699] = true;
            sDKInstance.getApmForInstance().updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_CALL_JS_NUM, 1.0d);
            $jacocoInit[700] = true;
            sDKInstance.getApmForInstance().updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_CALL_JS_TIME, (double) currentTimeMillis2);
            $jacocoInit[701] = true;
            sDKInstance.callJsTime(currentTimeMillis2);
            $jacocoInit[702] = true;
        }
        $jacocoInit[703] = true;
    }

    public int invokeCreateInstanceContext(String str, String str2, String str3, WXJSObject[] wXJSObjectArr, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = new StringBuilder();
        sb.append("invokeCreateInstanceContext instanceId:");
        sb.append(str);
        sb.append(" function:");
        sb.append(str3);
        sb.append(" isJSFrameworkInit：%d");
        $jacocoInit[704] = true;
        sb.append(isJSFrameworkInit());
        String sb2 = sb.toString();
        $jacocoInit[705] = true;
        WXLogUtils.d(sb2);
        $jacocoInit[706] = true;
        StringBuilder sb3 = this.mLodBuilder;
        sb3.append("createInstanceContext >>>> instanceId:");
        sb3.append(str);
        $jacocoInit[707] = true;
        sb3.append("function:");
        sb3.append(str3);
        if (!z) {
            $jacocoInit[708] = true;
        } else {
            $jacocoInit[709] = true;
            StringBuilder sb4 = this.mLodBuilder;
            sb4.append(" tasks:");
            sb4.append(WXJsonUtils.fromObjectToJSONString(wXJSObjectArr));
            $jacocoInit[710] = true;
        }
        WXLogUtils.d(this.mLodBuilder.substring(0));
        $jacocoInit[711] = true;
        this.mLodBuilder.setLength(0);
        $jacocoInit[712] = true;
        int createInstanceContext = this.mWXBridge.createInstanceContext(str, str2, str3, wXJSObjectArr);
        $jacocoInit[713] = true;
        return createInstanceContext;
    }

    public void invokeDestoryInstance(String str, String str2, String str3, WXJSObject[] wXJSObjectArr, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = this.mLodBuilder;
        sb.append("callJS >>>> instanceId:");
        sb.append(str);
        $jacocoInit[714] = true;
        sb.append("function:");
        sb.append(str3);
        if (!z) {
            $jacocoInit[715] = true;
        } else {
            $jacocoInit[716] = true;
            StringBuilder sb2 = this.mLodBuilder;
            sb2.append(" tasks:");
            sb2.append(WXJsonUtils.fromObjectToJSONString(wXJSObjectArr));
            $jacocoInit[717] = true;
        }
        WXLogUtils.d(this.mLodBuilder.substring(0));
        $jacocoInit[718] = true;
        this.mLodBuilder.setLength(0);
        $jacocoInit[719] = true;
        this.mWXBridge.destoryInstance(str, str2, str3, wXJSObjectArr);
        $jacocoInit[720] = true;
    }

    private void execJSOnInstance(EventResult eventResult, String str, String str2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        final String str3 = str;
        final String str4 = str2;
        final int i2 = i;
        final EventResult eventResult2 = eventResult;
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-4513883025007490488L, "com/taobao/weex/bridge/WXBridgeManager$15", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                String access$1000 = WXBridgeManager.access$1000(this.this$0, str3, str4, i2);
                $jacocoInit[1] = true;
                eventResult2.onCallback(access$1000);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[721] = true;
    }

    private String invokeExecJSOnInstance(String str, String str2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = this.mLodBuilder;
        sb.append("execJSOnInstance >>>> instanceId:");
        sb.append(str);
        $jacocoInit[722] = true;
        WXLogUtils.d(this.mLodBuilder.substring(0));
        $jacocoInit[723] = true;
        this.mLodBuilder.setLength(0);
        $jacocoInit[724] = true;
        if (isJSFrameworkInit()) {
            $jacocoInit[725] = true;
            String execJSOnInstance = this.mWXBridge.execJSOnInstance(str, str2, i);
            $jacocoInit[726] = true;
            return execJSOnInstance;
        }
        $jacocoInit[727] = true;
        return null;
    }

    private void invokeExecJSWithCallback(String str, String str2, String str3, WXJSObject[] wXJSObjectArr, ResultCallback resultCallback, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isOpenDebugLog()) {
            $jacocoInit[728] = true;
        } else {
            $jacocoInit[729] = true;
        }
        if (!isJSFrameworkInit()) {
            $jacocoInit[730] = true;
        } else {
            $jacocoInit[731] = true;
            this.mWXBridge.execJSWithCallback(str, str2, str3, wXJSObjectArr, resultCallback);
            $jacocoInit[732] = true;
        }
        $jacocoInit[733] = true;
    }

    @NonNull
    public static String argsToJSON(WXJSObject[] wXJSObjectArr) {
        boolean[] $jacocoInit = $jacocoInit();
        StringBuilder sb = new StringBuilder();
        $jacocoInit[734] = true;
        sb.append(Operators.ARRAY_START_STR);
        int length = wXJSObjectArr.length;
        $jacocoInit[735] = true;
        int i = 0;
        while (i < length) {
            WXJSObject wXJSObject = wXJSObjectArr[i];
            $jacocoInit[736] = true;
            sb.append(WXWsonJSONSwitch.fromObjectToJSONString(wXJSObject));
            $jacocoInit[737] = true;
            sb.append(",");
            i++;
            $jacocoInit[738] = true;
        }
        sb.append(Operators.ARRAY_END_STR);
        $jacocoInit[739] = true;
        String sb2 = sb.toString();
        $jacocoInit[740] = true;
        return sb2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void invokeInitFramework(android.os.Message r9) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            java.lang.String r1 = ""
            java.lang.Object r2 = r9.obj
            r3 = 1
            if (r2 != 0) goto L_0x0010
            r9 = 741(0x2e5, float:1.038E-42)
            r0[r9] = r3
            goto L_0x0019
        L_0x0010:
            java.lang.Object r9 = r9.obj
            r1 = r9
            java.lang.String r1 = (java.lang.String) r1
            r9 = 742(0x2e6, float:1.04E-42)
            r0[r9] = r3
        L_0x0019:
            android.app.Application r9 = com.taobao.weex.WXEnvironment.getApplication()
            long r4 = com.taobao.weex.utils.WXUtils.getAvailMemory(r9)
            long r6 = LOW_MEM_VALUE
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 > 0) goto L_0x002c
            r9 = 743(0x2e7, float:1.041E-42)
            r0[r9] = r3
            goto L_0x0037
        L_0x002c:
            r9 = 744(0x2e8, float:1.043E-42)
            r0[r9] = r3
            r8.initFramework(r1)
            r9 = 745(0x2e9, float:1.044E-42)
            r0[r9] = r3
        L_0x0037:
            r9 = 746(0x2ea, float:1.045E-42)
            r0[r9] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.bridge.WXBridgeManager.invokeInitFramework(android.os.Message):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x01db A[Catch:{ Exception -> 0x013e, Throwable -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x023a A[Catch:{ Exception -> 0x013e, Throwable -> 0x0266 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initFramework(java.lang.String r11) {
        /*
            r10 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = com.taobao.weex.WXSDKEngine.isSoInitialized()
            r2 = 1
            if (r1 != 0) goto L_0x0011
            r11 = 747(0x2eb, float:1.047E-42)
            r0[r11] = r2
            goto L_0x0294
        L_0x0011:
            boolean r1 = r10.isJSFrameworkInit()
            if (r1 == 0) goto L_0x001d
            r11 = 748(0x2ec, float:1.048E-42)
            r0[r11] = r2
            goto L_0x0294
        L_0x001d:
            r1 = 749(0x2ed, float:1.05E-42)
            r0[r1] = r2
            long r3 = java.lang.System.currentTimeMillis()
            sInitFrameWorkTimeOrigin = r3
            r1 = 750(0x2ee, float:1.051E-42)
            r0[r1] = r2
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 != 0) goto L_0x0037
            r1 = 751(0x2ef, float:1.052E-42)
            r0[r1] = r2
            goto L_0x00bd
        L_0x0037:
            r1 = 752(0x2f0, float:1.054E-42)
            r0[r1] = r2
            java.lang.String r1 = "weex JS framework from assets"
            com.taobao.weex.utils.WXLogUtils.d(r1)
            r1 = 753(0x2f1, float:1.055E-42)
            r0[r1] = r2
            com.taobao.weex.adapter.IWXJsFileLoaderAdapter r1 = com.taobao.weex.WXSDKEngine.getIWXJsFileLoaderAdapter()
            boolean r3 = isSandBoxContext
            if (r3 != 0) goto L_0x007d
            if (r1 != 0) goto L_0x0053
            r1 = 754(0x2f2, float:1.057E-42)
            r0[r1] = r2
            goto L_0x005f
        L_0x0053:
            r11 = 755(0x2f3, float:1.058E-42)
            r0[r11] = r2
            java.lang.String r11 = r1.loadJsFramework()
            r1 = 756(0x2f4, float:1.06E-42)
            r0[r1] = r2
        L_0x005f:
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 != 0) goto L_0x006a
            r1 = 757(0x2f5, float:1.061E-42)
            r0[r1] = r2
            goto L_0x00ad
        L_0x006a:
            r11 = 758(0x2f6, float:1.062E-42)
            r0[r11] = r2
            java.lang.String r11 = "main.js"
            android.app.Application r1 = com.taobao.weex.WXEnvironment.getApplication()
            java.lang.String r11 = com.taobao.weex.utils.WXFileUtils.loadAsset(r11, r1)
            r1 = 759(0x2f7, float:1.064E-42)
            r0[r1] = r2
            goto L_0x00ad
        L_0x007d:
            if (r1 != 0) goto L_0x0084
            r1 = 760(0x2f8, float:1.065E-42)
            r0[r1] = r2
            goto L_0x0090
        L_0x0084:
            r11 = 761(0x2f9, float:1.066E-42)
            r0[r11] = r2
            java.lang.String r11 = r1.loadJsFrameworkForSandBox()
            r1 = 762(0x2fa, float:1.068E-42)
            r0[r1] = r2
        L_0x0090:
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 != 0) goto L_0x009b
            r1 = 763(0x2fb, float:1.069E-42)
            r0[r1] = r2
            goto L_0x00ad
        L_0x009b:
            r11 = 764(0x2fc, float:1.07E-42)
            r0[r11] = r2
            java.lang.String r11 = "weex-main-jsfm.js"
            android.app.Application r1 = com.taobao.weex.WXEnvironment.getApplication()
            java.lang.String r11 = com.taobao.weex.utils.WXFileUtils.loadAsset(r11, r1)
            r1 = 765(0x2fd, float:1.072E-42)
            r0[r1] = r2
        L_0x00ad:
            java.lang.StringBuilder r1 = sInitFrameWorkMsg
            java.lang.String r3 = "| weex JS framework from assets, isSandBoxContext: "
            r1.append(r3)
            boolean r3 = isSandBoxContext
            r1.append(r3)
            r1 = 766(0x2fe, float:1.073E-42)
            r0[r1] = r2
        L_0x00bd:
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            r3 = 0
            if (r1 != 0) goto L_0x0299
            r1 = 767(0x2ff, float:1.075E-42)
            r0[r1] = r2
            com.taobao.weex.WXSDKManager r1 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.IWXStatisticsListener r1 = r1.getWXStatisticsListener()     // Catch:{ Throwable -> 0x0266 }
            if (r1 != 0) goto L_0x00d7
            r1 = 772(0x304, float:1.082E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
            goto L_0x014a
        L_0x00d7:
            r1 = 773(0x305, float:1.083E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0266 }
            r1 = 774(0x306, float:1.085E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.WXSDKManager r1 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.IWXStatisticsListener r1 = r1.getWXStatisticsListener()     // Catch:{ Throwable -> 0x0266 }
            r1.onJsFrameworkStart()     // Catch:{ Throwable -> 0x0266 }
            r1 = 775(0x307, float:1.086E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0266 }
            r1 = 0
            long r6 = r6 - r4
            com.taobao.weex.WXEnvironment.sJSFMStartListenerTime = r6     // Catch:{ Throwable -> 0x0266 }
            r1 = 776(0x308, float:1.087E-42)
            r0[r1] = r2     // Catch:{ Exception -> 0x013e }
            com.taobao.weex.WXSDKManager r1 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Exception -> 0x013e }
            com.taobao.weex.adapter.IWXUserTrackAdapter r4 = r1.getIWXUserTrackAdapter()     // Catch:{ Exception -> 0x013e }
            if (r4 != 0) goto L_0x010d
            r1 = 777(0x309, float:1.089E-42)
            r0[r1] = r2     // Catch:{ Exception -> 0x013e }
            goto L_0x0139
        L_0x010d:
            r1 = 778(0x30a, float:1.09E-42)
            r0[r1] = r2     // Catch:{ Exception -> 0x013e }
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ Exception -> 0x013e }
            r9.<init>(r2)     // Catch:{ Exception -> 0x013e }
            r1 = 779(0x30b, float:1.092E-42)
            r0[r1] = r2     // Catch:{ Exception -> 0x013e }
            java.lang.String r1 = "time"
            long r5 = com.taobao.weex.WXEnvironment.sJSFMStartListenerTime     // Catch:{ Exception -> 0x013e }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x013e }
            r9.put(r1, r5)     // Catch:{ Exception -> 0x013e }
            r1 = 780(0x30c, float:1.093E-42)
            r0[r1] = r2     // Catch:{ Exception -> 0x013e }
            android.app.Application r5 = com.taobao.weex.WXEnvironment.getApplication()     // Catch:{ Exception -> 0x013e }
            java.lang.String r6 = "sJSFMStartListener"
            java.lang.String r7 = "counter"
            r8 = 0
            r4.commit(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x013e }
            r1 = 781(0x30d, float:1.094E-42)
            r0[r1] = r2     // Catch:{ Exception -> 0x013e }
        L_0x0139:
            r1 = 782(0x30e, float:1.096E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
            goto L_0x014a
        L_0x013e:
            r1 = move-exception
            r4 = 783(0x30f, float:1.097E-42)
            r0[r4] = r2     // Catch:{ Throwable -> 0x0266 }
            r1.printStackTrace()     // Catch:{ Throwable -> 0x0266 }
            r1 = 784(0x310, float:1.099E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
        L_0x014a:
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r1 = ""
            r6 = 785(0x311, float:1.1E-42)
            r0[r6] = r2     // Catch:{ Exception -> 0x016a }
            android.app.Application r6 = com.taobao.weex.WXEnvironment.getApplication()     // Catch:{ Exception -> 0x016a }
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ Exception -> 0x016a }
            java.io.File r6 = r6.getCacheDir()     // Catch:{ Exception -> 0x016a }
            java.lang.String r6 = r6.getPath()     // Catch:{ Exception -> 0x016a }
            r1 = 786(0x312, float:1.101E-42)
            r0[r1] = r2     // Catch:{ Throwable -> 0x0266 }
            r1 = r6
            goto L_0x0176
        L_0x016a:
            r6 = move-exception
            r7 = 787(0x313, float:1.103E-42)
            r0[r7] = r2     // Catch:{ Throwable -> 0x0266 }
            r6.printStackTrace()     // Catch:{ Throwable -> 0x0266 }
            r6 = 788(0x314, float:1.104E-42)
            r0[r6] = r2     // Catch:{ Throwable -> 0x0266 }
        L_0x0176:
            r6 = 789(0x315, float:1.106E-42)
            r0[r6] = r2     // Catch:{ Exception -> 0x0193 }
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0193 }
            r7 = 16
            if (r6 < r7) goto L_0x0186
            r3 = 790(0x316, float:1.107E-42)
            r0[r3] = r2     // Catch:{ Exception -> 0x0193 }
            r3 = 1
            goto L_0x018a
        L_0x0186:
            r6 = 791(0x317, float:1.108E-42)
            r0[r6] = r2     // Catch:{ Exception -> 0x018f }
        L_0x018a:
            r6 = 792(0x318, float:1.11E-42)
            r0[r6] = r2     // Catch:{ Throwable -> 0x0266 }
            goto L_0x01a1
        L_0x018f:
            r6 = move-exception
            r3 = r6
            r6 = 0
            goto L_0x0195
        L_0x0193:
            r3 = move-exception
            r6 = 1
        L_0x0195:
            r7 = 793(0x319, float:1.111E-42)
            r0[r7] = r2     // Catch:{ Throwable -> 0x0266 }
            r3.printStackTrace()     // Catch:{ Throwable -> 0x0266 }
            r3 = 794(0x31a, float:1.113E-42)
            r0[r3] = r2     // Catch:{ Throwable -> 0x0266 }
            r3 = r6
        L_0x01a1:
            java.lang.StringBuilder r6 = sInitFrameWorkMsg     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r7 = " | pieSupport:"
            r6.append(r7)     // Catch:{ Throwable -> 0x0266 }
            r6.append(r3)     // Catch:{ Throwable -> 0x0266 }
            r6 = 795(0x31b, float:1.114E-42)
            r0[r6] = r2     // Catch:{ Throwable -> 0x0266 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0266 }
            r6.<init>()     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r7 = "[WXBridgeManager] initFrameworkEnv crashFile:"
            r6.append(r7)     // Catch:{ Throwable -> 0x0266 }
            r6.append(r1)     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r7 = " pieSupport:"
            r6.append(r7)     // Catch:{ Throwable -> 0x0266 }
            r6.append(r3)     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.utils.WXLogUtils.d(r6)     // Catch:{ Throwable -> 0x0266 }
            r6 = 796(0x31c, float:1.115E-42)
            r0[r6] = r2     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.common.IWXBridge r6 = r10.mWXBridge     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.bridge.WXParams r7 = r10.assembleDefaultOptions()     // Catch:{ Throwable -> 0x0266 }
            int r11 = r6.initFrameworkEnv(r11, r7, r1, r3)     // Catch:{ Throwable -> 0x0266 }
            if (r11 != r2) goto L_0x023a
            r11 = 797(0x31d, float:1.117E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0266 }
            r11 = 0
            long r6 = r6 - r4
            com.taobao.weex.WXEnvironment.sJSLibInitTime = r6     // Catch:{ Throwable -> 0x0266 }
            r11 = 798(0x31e, float:1.118E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0266 }
            long r5 = com.taobao.weex.WXEnvironment.sSDKInitStart     // Catch:{ Throwable -> 0x0266 }
            r11 = 0
            long r3 = r3 - r5
            com.taobao.weex.WXEnvironment.sSDKInitTime = r3     // Catch:{ Throwable -> 0x0266 }
            r11 = 799(0x31f, float:1.12E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            r10.setJSFrameworkInit(r2)     // Catch:{ Throwable -> 0x0266 }
            r11 = 800(0x320, float:1.121E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.WXSDKManager r11 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.IWXStatisticsListener r11 = r11.getWXStatisticsListener()     // Catch:{ Throwable -> 0x0266 }
            if (r11 != 0) goto L_0x020f
            r11 = 801(0x321, float:1.122E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            goto L_0x0222
        L_0x020f:
            r11 = 802(0x322, float:1.124E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.WXSDKManager r11 = com.taobao.weex.WXSDKManager.getInstance()     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.IWXStatisticsListener r11 = r11.getWXStatisticsListener()     // Catch:{ Throwable -> 0x0266 }
            r11.onJsFrameworkReady()     // Catch:{ Throwable -> 0x0266 }
            r11 = 803(0x323, float:1.125E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
        L_0x0222:
            r10.execRegisterFailTask()     // Catch:{ Throwable -> 0x0266 }
            com.taobao.weex.WXEnvironment.JsFrameworkInit = r2     // Catch:{ Throwable -> 0x0266 }
            r11 = 804(0x324, float:1.127E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            r10.registerDomModule()     // Catch:{ Throwable -> 0x0266 }
            r11 = 805(0x325, float:1.128E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            r10.trackComponentAndModulesTime()     // Catch:{ Throwable -> 0x0266 }
            r11 = 806(0x326, float:1.13E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            goto L_0x0261
        L_0x023a:
            java.lang.StringBuilder r11 = sInitFrameWorkMsg     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r1 = " | ExecuteJavaScript fail, reInitCount"
            r11.append(r1)     // Catch:{ Throwable -> 0x0266 }
            int r1 = reInitCount     // Catch:{ Throwable -> 0x0266 }
            r11.append(r1)     // Catch:{ Throwable -> 0x0266 }
            int r11 = reInitCount     // Catch:{ Throwable -> 0x0266 }
            if (r11 <= r2) goto L_0x0258
            r11 = 807(0x327, float:1.131E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            java.lang.String r11 = "[WXBridgeManager] invokeReInitFramework  ExecuteJavaScript fail"
            com.taobao.weex.utils.WXLogUtils.e(r11)     // Catch:{ Throwable -> 0x0266 }
            r11 = 808(0x328, float:1.132E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
            goto L_0x0261
        L_0x0258:
            java.lang.String r11 = "[WXBridgeManager] invokeInitFramework  ExecuteJavaScript fail"
            com.taobao.weex.utils.WXLogUtils.e(r11)     // Catch:{ Throwable -> 0x0266 }
            r11 = 809(0x329, float:1.134E-42)
            r0[r11] = r2     // Catch:{ Throwable -> 0x0266 }
        L_0x0261:
            r11 = 810(0x32a, float:1.135E-42)
            r0[r11] = r2
            goto L_0x0294
        L_0x0266:
            r11 = move-exception
            r1 = 811(0x32b, float:1.136E-42)
            r0[r1] = r2
            java.lang.StringBuilder r1 = sInitFrameWorkMsg
            java.lang.String r3 = " | invokeInitFramework exception "
            r1.append(r3)
            java.lang.String r3 = r11.toString()
            r1.append(r3)
            int r1 = reInitCount
            if (r1 <= r2) goto L_0x028b
            r1 = 812(0x32c, float:1.138E-42)
            r0[r1] = r2
            java.lang.String r1 = "[WXBridgeManager] invokeInitFramework "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.Throwable) r11)
            r11 = 813(0x32d, float:1.139E-42)
            r0[r11] = r2
            goto L_0x0294
        L_0x028b:
            java.lang.String r1 = "[WXBridgeManager] invokeInitFramework "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.Throwable) r11)
            r11 = 814(0x32e, float:1.14E-42)
            r0[r11] = r2
        L_0x0294:
            r11 = 815(0x32f, float:1.142E-42)
            r0[r11] = r2
            return
        L_0x0299:
            r11 = 768(0x300, float:1.076E-42)
            r0[r11] = r2
            r10.setJSFrameworkInit(r3)
            r11 = 769(0x301, float:1.078E-42)
            r0[r11] = r2
            java.lang.StringBuilder r11 = sInitFrameWorkMsg
            java.lang.String r1 = "| framework isEmpty "
            r11.append(r1)
            r11 = 770(0x302, float:1.079E-42)
            r0[r11] = r2
            com.taobao.weex.common.WXErrorCode r11 = com.taobao.weex.common.WXErrorCode.WX_ERR_JS_FRAMEWORK
            java.lang.String r1 = "initFramework"
            java.lang.String r3 = "framework is empty!! "
            r4 = 0
            com.taobao.weex.utils.WXExceptionUtils.commitCriticalExceptionRT(r4, r11, r1, r3, r4)
            r11 = 771(0x303, float:1.08E-42)
            r0[r11] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.bridge.WXBridgeManager.initFramework(java.lang.String):void");
    }

    private void trackComponentAndModulesTime() {
        boolean[] $jacocoInit = $jacocoInit();
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(4164993250032448569L, "com/taobao/weex/bridge/WXBridgeManager$16", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                WXEnvironment.sComponentsAndModulesReadyTime = System.currentTimeMillis() - WXEnvironment.sSDKInitStart;
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[816] = true;
    }

    private void invokeCallJSBatch(Message message) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mNextTickTasks.isEmpty()) {
            $jacocoInit[817] = true;
        } else if (isJSFrameworkInit()) {
            $jacocoInit[818] = true;
            try {
                Object obj = message.obj;
                $jacocoInit[824] = true;
                Stack<String> instanceStack = this.mNextTickTasks.getInstanceStack();
                $jacocoInit[825] = true;
                int size = instanceStack.size() - 1;
                $jacocoInit[826] = true;
                ArrayList<WXHashMap<String, Object>> arrayList = null;
                while (true) {
                    if (size < 0) {
                        $jacocoInit[827] = true;
                        break;
                    }
                    $jacocoInit[828] = true;
                    obj = instanceStack.get(size);
                    $jacocoInit[829] = true;
                    arrayList = this.mNextTickTasks.remove(obj);
                    $jacocoInit[830] = true;
                    if (arrayList != null) {
                        if (!arrayList.isEmpty()) {
                            $jacocoInit[833] = true;
                            break;
                        }
                        $jacocoInit[832] = true;
                    } else {
                        $jacocoInit[831] = true;
                    }
                    size--;
                    $jacocoInit[834] = true;
                }
                if (arrayList == null) {
                    $jacocoInit[835] = true;
                } else {
                    $jacocoInit[836] = true;
                    Object[] array = arrayList.toArray();
                    $jacocoInit[837] = true;
                    $jacocoInit[838] = true;
                    WXJSObject[] wXJSObjectArr = {new WXJSObject(2, obj), WXWsonJSONSwitch.toWsonOrJsonWXJSObject(array)};
                    $jacocoInit[839] = true;
                    invokeExecJS(String.valueOf(obj), (String) null, METHOD_CALL_JS, wXJSObjectArr);
                    $jacocoInit[840] = true;
                }
                $jacocoInit[841] = true;
            } catch (Throwable th) {
                $jacocoInit[842] = true;
                WXLogUtils.e("WXBridgeManager", th);
                $jacocoInit[843] = true;
                $jacocoInit[844] = true;
                WXExceptionUtils.commitCriticalExceptionRT((String) null, WXErrorCode.WX_ERR_JS_FRAMEWORK, "invokeCallJSBatch", "invokeCallJSBatch#" + WXLogUtils.getStackTrace(th), (Map<String, String>) null);
                $jacocoInit[845] = true;
            }
            if (this.mNextTickTasks.isEmpty()) {
                $jacocoInit[846] = true;
            } else {
                $jacocoInit[847] = true;
                this.mJSHandler.sendEmptyMessage(6);
                $jacocoInit[848] = true;
            }
            $jacocoInit[849] = true;
            return;
        } else {
            $jacocoInit[819] = true;
        }
        if (isJSFrameworkInit()) {
            $jacocoInit[820] = true;
        } else {
            $jacocoInit[821] = true;
            WXLogUtils.e("[WXBridgeManager] invokeCallJSBatch: framework.js uninitialized!!  message:" + message.toString());
            $jacocoInit[822] = true;
        }
        $jacocoInit[823] = true;
    }

    private WXParams assembleDefaultOptions() {
        String str;
        String str2;
        String str3;
        String str4;
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> config = WXEnvironment.getConfig();
        $jacocoInit[850] = true;
        WXParams wXParams = new WXParams();
        $jacocoInit[851] = true;
        wXParams.setPlatform(config.get("os"));
        $jacocoInit[852] = true;
        wXParams.setCacheDir(config.get(WXConfig.cacheDir));
        $jacocoInit[853] = true;
        wXParams.setOsVersion(config.get(WXConfig.sysVersion));
        $jacocoInit[854] = true;
        wXParams.setAppVersion(config.get("appVersion"));
        $jacocoInit[855] = true;
        wXParams.setWeexVersion(config.get(WXConfig.weexVersion));
        $jacocoInit[856] = true;
        wXParams.setDeviceModel(config.get(WXConfig.sysModel));
        $jacocoInit[857] = true;
        wXParams.setShouldInfoCollect(config.get("infoCollect"));
        $jacocoInit[858] = true;
        wXParams.setLogLevel(config.get(WXConfig.logLevel));
        $jacocoInit[859] = true;
        wXParams.setLayoutDirection(config.get(WXConfig.layoutDirection));
        $jacocoInit[860] = true;
        if (isUseSingleProcess) {
            str = "true";
            $jacocoInit[861] = true;
        } else {
            str = "false";
            $jacocoInit[862] = true;
        }
        wXParams.setUseSingleProcess(str);
        $jacocoInit[863] = true;
        wXParams.setCrashFilePath(WXEnvironment.getCrashFilePath(WXEnvironment.getApplication().getApplicationContext()));
        $jacocoInit[864] = true;
        wXParams.setLibJssPath(WXEnvironment.getLibJssRealPath());
        $jacocoInit[865] = true;
        wXParams.setLibIcuPath(WXEnvironment.getLibJssIcuPath());
        $jacocoInit[866] = true;
        wXParams.setLibLdPath(WXEnvironment.getLibLdPath());
        $jacocoInit[867] = true;
        String libJScRealPath = WXEnvironment.getLibJScRealPath();
        $jacocoInit[868] = true;
        if (TextUtils.isEmpty(libJScRealPath)) {
            str2 = "";
            $jacocoInit[869] = true;
        } else {
            str2 = new File(libJScRealPath).getParent();
            $jacocoInit[870] = true;
        }
        wXParams.setLibJscPath(str2);
        $jacocoInit[871] = true;
        String str5 = config.get("appName");
        $jacocoInit[872] = true;
        if (TextUtils.isEmpty(str5)) {
            $jacocoInit[873] = true;
        } else {
            $jacocoInit[874] = true;
            wXParams.setAppName(str5);
            $jacocoInit[875] = true;
        }
        if (TextUtils.isEmpty(config.get("deviceWidth"))) {
            str3 = String.valueOf(WXViewUtils.getScreenWidth(WXEnvironment.sApplication));
            $jacocoInit[876] = true;
        } else {
            str3 = config.get("deviceWidth");
            $jacocoInit[877] = true;
        }
        wXParams.setDeviceWidth(str3);
        $jacocoInit[878] = true;
        if (TextUtils.isEmpty(config.get("deviceHeight"))) {
            str4 = String.valueOf(WXViewUtils.getScreenHeight(WXEnvironment.sApplication));
            $jacocoInit[879] = true;
        } else {
            str4 = config.get("deviceHeight");
            $jacocoInit[880] = true;
        }
        wXParams.setDeviceHeight(str4);
        $jacocoInit[881] = true;
        wXParams.setOptions(WXEnvironment.getCustomOptions());
        $jacocoInit[882] = true;
        wXParams.setNeedInitV8(WXSDKManager.getInstance().needInitV8());
        this.mInitParams = wXParams;
        $jacocoInit[883] = true;
        return wXParams;
    }

    public WXParams getInitParams() {
        boolean[] $jacocoInit = $jacocoInit();
        WXParams wXParams = this.mInitParams;
        $jacocoInit[884] = true;
        return wXParams;
    }

    private void execRegisterFailTask() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRegisterModuleFailList.size() <= 0) {
            $jacocoInit[885] = true;
        } else {
            $jacocoInit[886] = true;
            ArrayList arrayList = new ArrayList();
            $jacocoInit[887] = true;
            int i = 0;
            int size = this.mRegisterModuleFailList.size();
            $jacocoInit[888] = true;
            while (i < size) {
                $jacocoInit[889] = true;
                invokeRegisterModules(this.mRegisterModuleFailList.get(i), arrayList);
                i++;
                $jacocoInit[890] = true;
            }
            this.mRegisterModuleFailList.clear();
            $jacocoInit[891] = true;
            if (arrayList.size() <= 0) {
                $jacocoInit[892] = true;
            } else {
                $jacocoInit[893] = true;
                this.mRegisterModuleFailList.addAll(arrayList);
                $jacocoInit[894] = true;
            }
        }
        if (this.mRegisterComponentFailList.size() <= 0) {
            $jacocoInit[895] = true;
        } else {
            $jacocoInit[896] = true;
            ArrayList arrayList2 = new ArrayList();
            $jacocoInit[897] = true;
            invokeRegisterComponents(this.mRegisterComponentFailList, arrayList2);
            $jacocoInit[898] = true;
            this.mRegisterComponentFailList.clear();
            $jacocoInit[899] = true;
            if (arrayList2.size() <= 0) {
                $jacocoInit[900] = true;
            } else {
                $jacocoInit[901] = true;
                this.mRegisterComponentFailList.addAll(arrayList2);
                $jacocoInit[902] = true;
            }
        }
        if (this.mRegisterServiceFailList.size() <= 0) {
            $jacocoInit[903] = true;
        } else {
            $jacocoInit[904] = true;
            ArrayList arrayList3 = new ArrayList();
            $jacocoInit[905] = true;
            $jacocoInit[906] = true;
            for (String invokeExecJSService : this.mRegisterServiceFailList) {
                $jacocoInit[907] = true;
                invokeExecJSService(invokeExecJSService, arrayList3);
                $jacocoInit[908] = true;
            }
            this.mRegisterServiceFailList.clear();
            $jacocoInit[909] = true;
            if (arrayList3.size() <= 0) {
                $jacocoInit[910] = true;
            } else {
                $jacocoInit[911] = true;
                this.mRegisterServiceFailList.addAll(arrayList3);
                $jacocoInit[912] = true;
            }
        }
        $jacocoInit[913] = true;
    }

    public void registerModules(final Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[914] = true;
        } else if (map.size() == 0) {
            $jacocoInit[915] = true;
        } else {
            $jacocoInit[916] = true;
            if (isJSThread()) {
                $jacocoInit[917] = true;
                invokeRegisterModules(map, this.mRegisterModuleFailList);
                $jacocoInit[918] = true;
            } else {
                post(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXBridgeManager this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(8945472747762520031L, "com/taobao/weex/bridge/WXBridgeManager$17", 2);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        WXBridgeManager.access$1200(this.this$0, map, WXBridgeManager.access$1100(this.this$0));
                        $jacocoInit[1] = true;
                    }
                }, (Object) null);
                $jacocoInit[919] = true;
            }
        }
        $jacocoInit[920] = true;
    }

    public void registerComponents(final List<Map<String, Object>> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSHandler == null) {
            $jacocoInit[921] = true;
        } else if (list == null) {
            $jacocoInit[922] = true;
        } else {
            $jacocoInit[923] = true;
            if (list.size() == 0) {
                $jacocoInit[924] = true;
            } else {
                AnonymousClass18 r1 = new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXBridgeManager this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-7044009175642612130L, "com/taobao/weex/bridge/WXBridgeManager$18", 2);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        WXBridgeManager.access$1400(this.this$0, list, WXBridgeManager.access$1300(this.this$0));
                        $jacocoInit[1] = true;
                    }
                };
                $jacocoInit[926] = true;
                if (!isJSThread()) {
                    $jacocoInit[927] = true;
                } else if (!isJSFrameworkInit()) {
                    $jacocoInit[928] = true;
                } else {
                    $jacocoInit[929] = true;
                    r1.run();
                    $jacocoInit[930] = true;
                    $jacocoInit[932] = true;
                    return;
                }
                post(r1, (Object) null);
                $jacocoInit[931] = true;
                $jacocoInit[932] = true;
                return;
            }
        }
        $jacocoInit[925] = true;
    }

    public void execJSService(final String str) {
        boolean[] $jacocoInit = $jacocoInit();
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-804986576936906015L, "com/taobao/weex/bridge/WXBridgeManager$19", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                WXBridgeManager.access$1600(this.this$0, str, WXBridgeManager.access$1500(this.this$0));
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[933] = true;
    }

    private void invokeExecJSService(String str, List<String> list) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (isJSFrameworkInit()) {
                $jacocoInit[934] = true;
                this.mWXBridge.execJSService(str);
                $jacocoInit[938] = true;
                $jacocoInit[947] = true;
                return;
            }
            $jacocoInit[935] = true;
            WXLogUtils.e("[WXBridgeManager] invoke execJSService: framework.js uninitialized.");
            $jacocoInit[936] = true;
            list.add(str);
            $jacocoInit[937] = true;
        } catch (Throwable th) {
            $jacocoInit[939] = true;
            WXLogUtils.e("[WXBridgeManager] invokeRegisterService:", th);
            $jacocoInit[940] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[941] = true;
            hashMap.put("inputParams", str + "||" + list.toString());
            $jacocoInit[942] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_JSSERVICE_EXECUTE;
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_JSSERVICE_EXECUTE;
            $jacocoInit[943] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("[WXBridgeManager] invokeRegisterService:");
            $jacocoInit[944] = true;
            sb.append(WXLogUtils.getStackTrace(th));
            String sb2 = sb.toString();
            $jacocoInit[945] = true;
            WXExceptionUtils.commitCriticalExceptionRT("invokeExecJSService", wXErrorCode, "invokeExecJSService", sb2, hashMap);
            $jacocoInit[946] = true;
        }
    }

    public boolean isJSThread() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSThread == null) {
            $jacocoInit[948] = true;
        } else if (this.mJSThread.getId() != Thread.currentThread().getId()) {
            $jacocoInit[949] = true;
        } else {
            $jacocoInit[950] = true;
            z = true;
            $jacocoInit[952] = true;
            return z;
        }
        z = false;
        $jacocoInit[951] = true;
        $jacocoInit[952] = true;
        return z;
    }

    private void invokeRegisterModules(Map<String, Object> map, List<Map<String, Object>> list) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[953] = true;
        } else if (!isJSFrameworkInit()) {
            $jacocoInit[954] = true;
        } else {
            WXJSObject[] wXJSObjectArr = {WXWsonJSONSwitch.toWsonOrJsonWXJSObject(map)};
            try {
                $jacocoInit[959] = true;
                if (!(this.mWXBridge instanceof WXBridge)) {
                    $jacocoInit[960] = true;
                } else {
                    $jacocoInit[961] = true;
                    ((WXBridge) this.mWXBridge).registerModuleOnDataRenderNode(WXJsonUtils.fromObjectToJSONString(map));
                    $jacocoInit[962] = true;
                }
                $jacocoInit[963] = true;
            } catch (Throwable th) {
                $jacocoInit[979] = true;
                StringBuilder sb = new StringBuilder();
                sb.append(WXErrorCode.WX_KEY_EXCEPTION_INVOKE_REGISTER_MODULES.getErrorMsg());
                sb.append(" \n ");
                $jacocoInit[980] = true;
                sb.append(th.getMessage());
                sb.append(map.entrySet().toString());
                str = sb.toString();
                $jacocoInit[981] = true;
            }
            if (this.mWXBridge.execJS("", (String) null, METHOD_REGISTER_MODULES, wXJSObjectArr) != 0) {
                $jacocoInit[966] = true;
                str = null;
            } else {
                str = "execJS error";
                try {
                    $jacocoInit[967] = true;
                } catch (Throwable th2) {
                    $jacocoInit[976] = true;
                    WXLogUtils.e("Weex [invokeRegisterModules]", th2);
                    $jacocoInit[977] = true;
                }
            }
            $jacocoInit[968] = true;
            for (String next : map.keySet()) {
                $jacocoInit[969] = true;
                if (next == null) {
                    $jacocoInit[970] = true;
                } else {
                    $jacocoInit[971] = true;
                    WXModuleManager.resetModuleState(next, true);
                    $jacocoInit[972] = true;
                    WXLogUtils.e("[WXBridgeManager]invokeRegisterModules METHOD_REGISTER_MODULES success module:" + next);
                    $jacocoInit[973] = true;
                }
                $jacocoInit[974] = true;
            }
            $jacocoInit[975] = true;
            $jacocoInit[978] = true;
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[982] = true;
            } else {
                $jacocoInit[983] = true;
                WXLogUtils.e("[WXBridgeManager] invokeRegisterModules:", str);
                $jacocoInit[984] = true;
                WXExceptionUtils.commitCriticalExceptionRT((String) null, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_REGISTER_MODULES, "invokeRegisterModules", str, (Map<String, String>) null);
                $jacocoInit[985] = true;
            }
            $jacocoInit[986] = true;
            return;
        }
        if (isJSFrameworkInit()) {
            $jacocoInit[955] = true;
        } else {
            $jacocoInit[956] = true;
            WXLogUtils.d("[WXinvokeRegisterModulesBridgeManager] invokeRegisterModules: framework.js uninitialized.");
            $jacocoInit[957] = true;
        }
        list.add(map);
        $jacocoInit[958] = true;
    }

    private void invokeRegisterComponents(List<Map<String, Object>> list, List<Map<String, Object>> list2) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (list == list2) {
            $jacocoInit[987] = true;
            RuntimeException runtimeException = new RuntimeException("Fail receiver should not use source.");
            $jacocoInit[988] = true;
            throw runtimeException;
        } else if (!isJSFrameworkInit()) {
            $jacocoInit[989] = true;
            WXLogUtils.e("[WXBridgeManager] invokeRegisterComponents: framework.js uninitialized.");
            $jacocoInit[990] = true;
            $jacocoInit[991] = true;
            for (Map<String, Object> add : list) {
                $jacocoInit[992] = true;
                list2.add(add);
                $jacocoInit[993] = true;
            }
            $jacocoInit[994] = true;
        } else if (list == null) {
            $jacocoInit[995] = true;
        } else {
            WXJSObject[] wXJSObjectArr = {WXWsonJSONSwitch.toWsonOrJsonWXJSObject(list)};
            try {
                $jacocoInit[996] = true;
                if (this.mWXBridge.execJS("", (String) null, METHOD_REGISTER_COMPONENTS, wXJSObjectArr) != 0) {
                    $jacocoInit[997] = true;
                    str = null;
                } else {
                    str = "execJS error";
                    $jacocoInit[998] = true;
                }
                $jacocoInit[999] = true;
            } catch (Throwable th) {
                $jacocoInit[1000] = true;
                StringBuilder sb = new StringBuilder();
                sb.append(WXErrorCode.WX_KEY_EXCEPTION_INVOKE_REGISTER_COMPONENT);
                $jacocoInit[1001] = true;
                sb.append(wXJSObjectArr.toString());
                $jacocoInit[1002] = true;
                sb.append(WXLogUtils.getStackTrace(th));
                str = sb.toString();
                $jacocoInit[1003] = true;
            }
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[1004] = true;
            } else {
                $jacocoInit[1005] = true;
                WXLogUtils.e("[WXBridgeManager] invokeRegisterComponents ", str);
                $jacocoInit[1006] = true;
                WXExceptionUtils.commitCriticalExceptionRT((String) null, WXErrorCode.WX_KEY_EXCEPTION_INVOKE_REGISTER_COMPONENT, METHOD_REGISTER_COMPONENTS, str, (Map<String, String>) null);
                $jacocoInit[1007] = true;
            }
            $jacocoInit[1008] = true;
        }
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSThread == null) {
            $jacocoInit[1009] = true;
        } else {
            $jacocoInit[1010] = true;
            this.mJSThread.quit();
            $jacocoInit[1011] = true;
        }
        mBridgeManager = null;
        if (this.mDestroyedInstanceId == null) {
            $jacocoInit[1012] = true;
        } else {
            $jacocoInit[1013] = true;
            this.mDestroyedInstanceId.clear();
            $jacocoInit[1014] = true;
        }
        $jacocoInit[1015] = true;
    }

    public void reportJSException(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        WXLogUtils.e("reportJSException >>>> instanceId:" + str + ", exception function:" + str2 + ", exception:" + str3);
        WXErrorCode wXErrorCode = WXErrorCode.WX_ERR_JS_EXECUTE;
        $jacocoInit[1016] = true;
        if (str == null) {
            $jacocoInit[1017] = true;
        } else {
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            if (sDKInstance == null) {
                $jacocoInit[1018] = true;
            } else {
                $jacocoInit[1019] = true;
                sDKInstance.setHasException(true);
                $jacocoInit[1020] = true;
                str3 = str3 + "\n getTemplateInfo==" + sDKInstance.getTemplateInfo();
                $jacocoInit[1021] = true;
                if (METHOD_CREATE_INSTANCE.equals(str2)) {
                    $jacocoInit[1022] = true;
                } else if (sDKInstance.isContentMd5Match()) {
                    $jacocoInit[1023] = true;
                    if (!METHOD_CREATE_INSTANCE.equals(str2)) {
                        $jacocoInit[1047] = true;
                    } else if (sDKInstance.getExceptionRecorder().hasAddView.get()) {
                        $jacocoInit[1048] = true;
                    } else {
                        wXErrorCode = WXErrorCode.WX_RENDER_ERR_JS_CREATE_INSTANCE;
                        $jacocoInit[1049] = true;
                        sDKInstance.onJSException(wXErrorCode.getErrorCode(), str2, str3);
                        $jacocoInit[1053] = true;
                    }
                    if (!METHOD_CREATE_INSTANCE_CONTEXT.equals(str2)) {
                        $jacocoInit[1050] = true;
                    } else if (sDKInstance.getExceptionRecorder().hasAddView.get()) {
                        $jacocoInit[1051] = true;
                    } else {
                        wXErrorCode = WXErrorCode.WX_RENDER_ERR_JS_CREATE_INSTANCE_CONTEXT;
                        $jacocoInit[1052] = true;
                    }
                    sDKInstance.onJSException(wXErrorCode.getErrorCode(), str2, str3);
                    $jacocoInit[1053] = true;
                } else {
                    try {
                        $jacocoInit[1024] = true;
                    } catch (Exception e) {
                        $jacocoInit[1045] = true;
                        e.printStackTrace();
                        $jacocoInit[1046] = true;
                    }
                }
                if (!isJSFrameworkInit()) {
                    $jacocoInit[1025] = true;
                } else if (reInitCount <= 1) {
                    $jacocoInit[1026] = true;
                } else if (reInitCount >= 10) {
                    $jacocoInit[1027] = true;
                } else if (sDKInstance.isNeedReLoad()) {
                    $jacocoInit[1028] = true;
                } else {
                    $jacocoInit[1029] = true;
                    new ActionReloadPage(str, true).executeAction();
                    $jacocoInit[1030] = true;
                    sDKInstance.setNeedLoad(true);
                    $jacocoInit[1031] = true;
                    return;
                }
                StringBuilder sb = new StringBuilder();
                WXErrorCode wXErrorCode2 = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                $jacocoInit[1032] = true;
                sb.append(wXErrorCode2.getErrorMsg());
                $jacocoInit[1033] = true;
                sb.append(", reportJSException >>>> instanceId:");
                sb.append(str);
                $jacocoInit[1034] = true;
                sb.append(", exception function:");
                sb.append(str2);
                $jacocoInit[1035] = true;
                sb.append(", exception:");
                sb.append(str3);
                $jacocoInit[1036] = true;
                sb.append(", extInitTime:");
                sb.append(System.currentTimeMillis() - sInitFrameWorkTimeOrigin);
                sb.append(d.H);
                $jacocoInit[1037] = true;
                sb.append(", extInitErrorMsg:");
                sb.append(sInitFrameWorkMsg.toString());
                $jacocoInit[1038] = true;
                String sb2 = sb.toString();
                WXErrorCode wXErrorCode3 = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                $jacocoInit[1039] = true;
                String errorCode = wXErrorCode3.getErrorCode();
                $jacocoInit[1040] = true;
                sDKInstance.onRenderError(errorCode, sb2);
                if (WXEnvironment.sInAliWeex) {
                    $jacocoInit[1041] = true;
                } else {
                    $jacocoInit[1042] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_JS_CREATE_INSTANCE, str2, str3, (Map<String, String>) null);
                    $jacocoInit[1043] = true;
                }
                $jacocoInit[1044] = true;
                return;
            }
        }
        doReportJSException(str, str2, wXErrorCode, str3);
        $jacocoInit[1054] = true;
    }

    private void doReportJSException(String str, String str2, WXErrorCode wXErrorCode, String str3) {
        String str4;
        String str5;
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getAllInstanceMap().get(str);
        $jacocoInit[1055] = true;
        if (WXSDKManager.getInstance().getIWXJSExceptionAdapter() == null) {
            $jacocoInit[1056] = true;
        } else {
            $jacocoInit[1057] = true;
            if (!TextUtils.isEmpty(str)) {
                $jacocoInit[1058] = true;
            } else {
                str = "instanceIdisNull";
                $jacocoInit[1059] = true;
            }
            if (wXSDKInstance != null) {
                $jacocoInit[1060] = true;
            } else {
                $jacocoInit[1061] = true;
                if (!IWXUserTrackAdapter.INIT_FRAMEWORK.equals(str2)) {
                    $jacocoInit[1062] = true;
                } else {
                    try {
                        $jacocoInit[1063] = true;
                        if (WXEnvironment.getApplication() == null) {
                            $jacocoInit[1064] = true;
                            str4 = null;
                        } else {
                            $jacocoInit[1065] = true;
                            String str6 = WXEnvironment.getApplication().getApplicationContext().getCacheDir().getPath() + INITLOGFILE;
                            try {
                                $jacocoInit[1066] = true;
                                File file = new File(str6);
                                $jacocoInit[1067] = true;
                                if (!file.exists()) {
                                    $jacocoInit[1068] = true;
                                    str4 = null;
                                } else {
                                    $jacocoInit[1069] = true;
                                    if (file.length() <= 0) {
                                        $jacocoInit[1070] = true;
                                        str4 = null;
                                    } else {
                                        $jacocoInit[1071] = true;
                                        StringBuilder sb = new StringBuilder();
                                        try {
                                            $jacocoInit[1072] = true;
                                            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                                            $jacocoInit[1073] = true;
                                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                            $jacocoInit[1074] = true;
                                            while (true) {
                                                String readLine = bufferedReader.readLine();
                                                if (readLine == null) {
                                                    break;
                                                }
                                                $jacocoInit[1075] = true;
                                                sb.append(readLine + "\n");
                                                $jacocoInit[1076] = true;
                                            }
                                            str4 = sb.toString();
                                            try {
                                                $jacocoInit[1077] = true;
                                                bufferedReader.close();
                                            } catch (Exception e) {
                                                Exception exc = e;
                                                str5 = str4;
                                                e = exc;
                                                try {
                                                    $jacocoInit[1079] = true;
                                                    e.printStackTrace();
                                                    $jacocoInit[1080] = true;
                                                    str4 = str5;
                                                    file.delete();
                                                    $jacocoInit[1081] = true;
                                                    $jacocoInit[1082] = true;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    try {
                                                        $jacocoInit[1083] = true;
                                                        th.printStackTrace();
                                                        $jacocoInit[1084] = true;
                                                        str4 = str5;
                                                        $jacocoInit[1085] = true;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        $jacocoInit[1086] = true;
                                                        th.printStackTrace();
                                                        $jacocoInit[1087] = true;
                                                        str4 = str5;
                                                        str3 = str3 + "\n" + str4;
                                                        $jacocoInit[1088] = true;
                                                        WXLogUtils.e("reportJSException:" + str3);
                                                        $jacocoInit[1089] = true;
                                                        StringBuilder sb2 = new StringBuilder();
                                                        $jacocoInit[1090] = true;
                                                        sb2.append(wXErrorCode.getErrorMsg());
                                                        sb2.append(str3);
                                                        String sb3 = sb2.toString();
                                                        $jacocoInit[1091] = true;
                                                        WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb3, (Map<String, String>) null);
                                                        $jacocoInit[1092] = true;
                                                        $jacocoInit[1093] = true;
                                                    }
                                                    str3 = str3 + "\n" + str4;
                                                    $jacocoInit[1088] = true;
                                                    WXLogUtils.e("reportJSException:" + str3);
                                                    $jacocoInit[1089] = true;
                                                    StringBuilder sb22 = new StringBuilder();
                                                    $jacocoInit[1090] = true;
                                                    sb22.append(wXErrorCode.getErrorMsg());
                                                    sb22.append(str3);
                                                    String sb32 = sb22.toString();
                                                    $jacocoInit[1091] = true;
                                                    WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb32, (Map<String, String>) null);
                                                    $jacocoInit[1092] = true;
                                                    $jacocoInit[1093] = true;
                                                }
                                                $jacocoInit[1085] = true;
                                                str3 = str3 + "\n" + str4;
                                                $jacocoInit[1088] = true;
                                                WXLogUtils.e("reportJSException:" + str3);
                                                $jacocoInit[1089] = true;
                                                StringBuilder sb222 = new StringBuilder();
                                                $jacocoInit[1090] = true;
                                                sb222.append(wXErrorCode.getErrorMsg());
                                                sb222.append(str3);
                                                String sb322 = sb222.toString();
                                                $jacocoInit[1091] = true;
                                                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb322, (Map<String, String>) null);
                                                $jacocoInit[1092] = true;
                                                $jacocoInit[1093] = true;
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            str5 = null;
                                            $jacocoInit[1079] = true;
                                            e.printStackTrace();
                                            $jacocoInit[1080] = true;
                                            str4 = str5;
                                            file.delete();
                                            $jacocoInit[1081] = true;
                                            $jacocoInit[1082] = true;
                                            $jacocoInit[1085] = true;
                                            str3 = str3 + "\n" + str4;
                                            $jacocoInit[1088] = true;
                                            WXLogUtils.e("reportJSException:" + str3);
                                            $jacocoInit[1089] = true;
                                            StringBuilder sb2222 = new StringBuilder();
                                            $jacocoInit[1090] = true;
                                            sb2222.append(wXErrorCode.getErrorMsg());
                                            sb2222.append(str3);
                                            String sb3222 = sb2222.toString();
                                            $jacocoInit[1091] = true;
                                            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb3222, (Map<String, String>) null);
                                            $jacocoInit[1092] = true;
                                            $jacocoInit[1093] = true;
                                        }
                                        try {
                                            $jacocoInit[1078] = true;
                                        } catch (Throwable th3) {
                                            str5 = str4;
                                            th = th3;
                                        }
                                    }
                                    file.delete();
                                    $jacocoInit[1081] = true;
                                }
                                try {
                                    $jacocoInit[1082] = true;
                                } catch (Throwable th4) {
                                    str5 = str4;
                                    th = th4;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                str5 = null;
                                $jacocoInit[1083] = true;
                                th.printStackTrace();
                                $jacocoInit[1084] = true;
                                str4 = str5;
                                $jacocoInit[1085] = true;
                                str3 = str3 + "\n" + str4;
                                $jacocoInit[1088] = true;
                                WXLogUtils.e("reportJSException:" + str3);
                                $jacocoInit[1089] = true;
                                StringBuilder sb22222 = new StringBuilder();
                                $jacocoInit[1090] = true;
                                sb22222.append(wXErrorCode.getErrorMsg());
                                sb22222.append(str3);
                                String sb32222 = sb22222.toString();
                                $jacocoInit[1091] = true;
                                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb32222, (Map<String, String>) null);
                                $jacocoInit[1092] = true;
                                $jacocoInit[1093] = true;
                            }
                        }
                        $jacocoInit[1085] = true;
                    } catch (Throwable th6) {
                        th = th6;
                        str5 = null;
                        $jacocoInit[1086] = true;
                        th.printStackTrace();
                        $jacocoInit[1087] = true;
                        str4 = str5;
                        str3 = str3 + "\n" + str4;
                        $jacocoInit[1088] = true;
                        WXLogUtils.e("reportJSException:" + str3);
                        $jacocoInit[1089] = true;
                        StringBuilder sb222222 = new StringBuilder();
                        $jacocoInit[1090] = true;
                        sb222222.append(wXErrorCode.getErrorMsg());
                        sb222222.append(str3);
                        String sb322222 = sb222222.toString();
                        $jacocoInit[1091] = true;
                        WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb322222, (Map<String, String>) null);
                        $jacocoInit[1092] = true;
                        $jacocoInit[1093] = true;
                    }
                    str3 = str3 + "\n" + str4;
                    $jacocoInit[1088] = true;
                    WXLogUtils.e("reportJSException:" + str3);
                    $jacocoInit[1089] = true;
                }
            }
            StringBuilder sb2222222 = new StringBuilder();
            $jacocoInit[1090] = true;
            sb2222222.append(wXErrorCode.getErrorMsg());
            sb2222222.append(str3);
            String sb3222222 = sb2222222.toString();
            $jacocoInit[1091] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, str2, sb3222222, (Map<String, String>) null);
            $jacocoInit[1092] = true;
        }
        $jacocoInit[1093] = true;
    }

    private void registerDomModule() throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[1094] = true;
        hashMap.put(WXDomModule.WXDOM, WXDomModule.METHODS);
        $jacocoInit[1095] = true;
        registerModules(hashMap);
        $jacocoInit[1096] = true;
    }

    @Deprecated
    public void notifyTrimMemory() {
        $jacocoInit()[1097] = true;
    }

    public static void updateGlobalConfig(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[1098] = true;
        } else {
            str = "none";
            $jacocoInit[1099] = true;
        }
        if (TextUtils.equals(str, globalConfig)) {
            $jacocoInit[1100] = true;
        } else {
            globalConfig = str;
            $jacocoInit[1101] = true;
            WXEnvironment.getCustomOptions().put(GLOBAL_CONFIG_KEY, globalConfig);
            $jacocoInit[1102] = true;
            AnonymousClass20 r4 = new Runnable() {
                private static transient /* synthetic */ boolean[] $jacocoData;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(6174854979659630354L, "com/taobao/weex/bridge/WXBridgeManager$20", 12);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    $jacocoInit()[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (WXBridgeManager.mBridgeManager == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        if (!WXBridgeManager.access$1700(WXBridgeManager.mBridgeManager)) {
                            $jacocoInit[3] = true;
                        } else {
                            $jacocoInit[4] = true;
                            if (!(WXBridgeManager.access$500(WXBridgeManager.mBridgeManager) instanceof WXBridge)) {
                                $jacocoInit[5] = true;
                            } else {
                                $jacocoInit[6] = true;
                                $jacocoInit[7] = true;
                                ((WXBridge) WXBridgeManager.access$500(WXBridgeManager.mBridgeManager)).nativeUpdateGlobalConfig(WXBridgeManager.access$1800());
                                $jacocoInit[8] = true;
                            }
                        }
                    }
                    if (WXBridgeManager.access$1800().contains(WXWsonJSONSwitch.WSON_OFF)) {
                        WXWsonJSONSwitch.USE_WSON = false;
                        $jacocoInit[9] = true;
                    } else {
                        WXWsonJSONSwitch.USE_WSON = true;
                        $jacocoInit[10] = true;
                    }
                    $jacocoInit[11] = true;
                }
            };
            $jacocoInit[1103] = true;
            if (mBridgeManager == null) {
                $jacocoInit[1104] = true;
            } else if (!mBridgeManager.isJSFrameworkInit()) {
                $jacocoInit[1105] = true;
            } else {
                $jacocoInit[1106] = true;
                mBridgeManager.post(r4);
                $jacocoInit[1107] = true;
            }
            r4.run();
            $jacocoInit[1108] = true;
        }
        $jacocoInit[1109] = true;
    }

    @Nullable
    public Looper getJSLooper() {
        Looper looper;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mJSThread == null) {
            $jacocoInit[1110] = true;
            looper = null;
        } else {
            $jacocoInit[1111] = true;
            looper = this.mJSThread.getLooper();
            $jacocoInit[1112] = true;
        }
        $jacocoInit[1113] = true;
        return looper;
    }

    public void notifySerializeCodeCache() {
        boolean[] $jacocoInit = $jacocoInit();
        post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXBridgeManager this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(876925851585315681L, "com/taobao/weex/bridge/WXBridgeManager$21", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXBridgeManager.access$1700(this.this$0)) {
                    $jacocoInit[1] = true;
                    return;
                }
                WXBridgeManager.access$1900(this.this$0, "", (String) null, WXBridgeManager.METHOD_NOTIFY_SERIALIZE_CODE_CACHE, new WXJSObject[0]);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[1114] = true;
    }

    public void takeJSHeapSnapshot(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Message obtainMessage = this.mJSHandler.obtainMessage();
        obtainMessage.obj = str;
        obtainMessage.what = 13;
        $jacocoInit[1115] = true;
        obtainMessage.setTarget(this.mJSHandler);
        $jacocoInit[1116] = true;
        obtainMessage.sendToTarget();
        $jacocoInit[1117] = true;
    }

    public static class TimerInfo {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public String callbackId;
        public String instanceId;
        public long time;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4183615263021159893L, "com/taobao/weex/bridge/WXBridgeManager$TimerInfo", 1);
            $jacocoData = a2;
            return a2;
        }

        public TimerInfo() {
            $jacocoInit()[0] = true;
        }
    }

    public int callCreateBody(String str, String str2, String str3, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashSet<String> hashSet, float[] fArr, float[] fArr2, float[] fArr3) {
        String str4 = str;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1118] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1119] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[1120] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1123] = true;
            } else {
                $jacocoInit[1124] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1125] = true;
            } else if (!this.mDestroyedInstanceId.contains(str4)) {
                $jacocoInit[1126] = true;
            } else {
                $jacocoInit[1127] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str4);
                if (sDKInstance == null) {
                    $jacocoInit[1128] = true;
                } else {
                    $jacocoInit[1129] = true;
                    GraphicActionCreateBody graphicActionCreateBody = new GraphicActionCreateBody(sDKInstance, str3, str2, hashMap, hashMap2, hashSet, fArr, fArr2, fArr3);
                    $jacocoInit[1130] = true;
                    WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionCreateBody.getPageId(), graphicActionCreateBody);
                    $jacocoInit[1131] = true;
                }
                $jacocoInit[1132] = true;
            } catch (Exception e) {
                $jacocoInit[1133] = true;
                WXLogUtils.e("[WXBridgeManager] callCreateBody exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1134] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1135] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str4, wXErrorCode, "callCreateBody", stackTrace, (Map<String, String>) null);
                $jacocoInit[1136] = true;
            }
            $jacocoInit[1137] = true;
            return 1;
        }
        WXLogUtils.d("[WXBridgeManager] call callCreateBody arguments is null");
        $jacocoInit[1121] = true;
        WXExceptionUtils.commitCriticalExceptionRT(str4, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callCreateBody", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1122] = true;
        return 0;
    }

    public int callAddElement(String str, String str2, String str3, int i, String str4, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashSet<String> hashSet, float[] fArr, float[] fArr2, float[] fArr3, boolean z) {
        String str5 = str;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1138] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1139] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[1140] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1145] = true;
            } else {
                $jacocoInit[1146] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1147] = true;
            } else if (!this.mDestroyedInstanceId.contains(str5)) {
                $jacocoInit[1148] = true;
            } else {
                $jacocoInit[1149] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str5);
                if (sDKInstance == null) {
                    $jacocoInit[1150] = true;
                } else {
                    $jacocoInit[1151] = true;
                    GraphicActionAddElement graphicActionAddElement = r6;
                    GraphicActionAddElement graphicActionAddElement2 = new GraphicActionAddElement(sDKInstance, str3, str2, str4, i, hashMap, hashMap2, hashSet, fArr, fArr2, fArr3);
                    if (z) {
                        $jacocoInit[1152] = true;
                        sDKInstance.addInActiveAddElementAction(str3, graphicActionAddElement);
                        $jacocoInit[1153] = true;
                    } else {
                        WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(str5, graphicActionAddElement);
                        $jacocoInit[1154] = true;
                    }
                }
                $jacocoInit[1155] = true;
            } catch (Exception e) {
                $jacocoInit[1156] = true;
                WXLogUtils.e("[WXBridgeManager] callAddElement exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1157] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1158] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str5, wXErrorCode, "callAddElement", stackTrace, (Map<String, String>) null);
                $jacocoInit[1159] = true;
            }
            $jacocoInit[1160] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1141] = true;
        } else {
            $jacocoInit[1142] = true;
            WXLogUtils.d("[WXBridgeManager] call callAddElement arguments is null");
            $jacocoInit[1143] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str5, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callAddElement", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1144] = true;
        return 0;
    }

    public int callRemoveElement(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1161] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1162] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1167] = true;
            } else {
                $jacocoInit[1168] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1169] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1170] = true;
            } else {
                $jacocoInit[1171] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1172] = true;
                } else {
                    $jacocoInit[1173] = true;
                    GraphicActionRemoveElement graphicActionRemoveElement = new GraphicActionRemoveElement(sDKInstance, str2);
                    $jacocoInit[1174] = true;
                    if (sDKInstance.getInActiveAddElementAction(str2) != null) {
                        $jacocoInit[1175] = true;
                        sDKInstance.removeInActiveAddElmentAction(str2);
                        $jacocoInit[1176] = true;
                    } else {
                        WXRenderManager wXRenderManager = WXSDKManager.getInstance().getWXRenderManager();
                        $jacocoInit[1177] = true;
                        wXRenderManager.postGraphicAction(graphicActionRemoveElement.getPageId(), graphicActionRemoveElement);
                        $jacocoInit[1178] = true;
                    }
                }
                $jacocoInit[1179] = true;
            } catch (Exception e) {
                $jacocoInit[1180] = true;
                WXLogUtils.e("[WXBridgeManager] callRemoveElement exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1181] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1182] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callRemoveElement", stackTrace, (Map<String, String>) null);
                $jacocoInit[1183] = true;
            }
            $jacocoInit[1184] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1163] = true;
        } else {
            $jacocoInit[1164] = true;
            WXLogUtils.d("[WXBridgeManager] call callRemoveElement arguments is null");
            $jacocoInit[1165] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callRemoveElement", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1166] = true;
        return 0;
    }

    public int callMoveElement(String str, String str2, String str3, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1185] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1186] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[1187] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1192] = true;
            } else {
                $jacocoInit[1193] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1194] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1195] = true;
            } else {
                $jacocoInit[1196] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1197] = true;
                } else {
                    $jacocoInit[1198] = true;
                    GraphicActionMoveElement graphicActionMoveElement = new GraphicActionMoveElement(sDKInstance, str2, str3, i);
                    $jacocoInit[1199] = true;
                    WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionMoveElement.getPageId(), graphicActionMoveElement);
                    $jacocoInit[1200] = true;
                }
                $jacocoInit[1201] = true;
            } catch (Exception e) {
                $jacocoInit[1202] = true;
                WXLogUtils.e("[WXBridgeManager] callMoveElement exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1203] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1204] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callMoveElement", stackTrace, (Map<String, String>) null);
                $jacocoInit[1205] = true;
            }
            $jacocoInit[1206] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1188] = true;
        } else {
            $jacocoInit[1189] = true;
            WXLogUtils.d("[WXBridgeManager] call callMoveElement arguments is null");
            $jacocoInit[1190] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callMoveElement", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1191] = true;
        return 0;
    }

    public int callAddEvent(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1207] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1208] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[1209] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1214] = true;
            } else {
                $jacocoInit[1215] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1216] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1217] = true;
            } else {
                $jacocoInit[1218] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1219] = true;
                } else {
                    $jacocoInit[1220] = true;
                    new GraphicActionAddEvent(sDKInstance, str2, str3).executeActionOnRender();
                    $jacocoInit[1221] = true;
                }
                $jacocoInit[1222] = true;
            } catch (Exception e) {
                $jacocoInit[1223] = true;
                WXLogUtils.e("[WXBridgeManager] callAddEvent exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1224] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1225] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callAddEvent", stackTrace, (Map<String, String>) null);
                $jacocoInit[1226] = true;
            }
            getNextTick(str);
            $jacocoInit[1227] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1210] = true;
        } else {
            $jacocoInit[1211] = true;
            WXLogUtils.d("[WXBridgeManager] call callAddEvent arguments is null");
            $jacocoInit[1212] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callAddEvent", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1213] = true;
        return 0;
    }

    public int callRemoveEvent(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1228] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1229] = true;
        } else if (TextUtils.isEmpty(str3)) {
            $jacocoInit[1230] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1235] = true;
            } else {
                $jacocoInit[1236] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1237] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1238] = true;
            } else {
                $jacocoInit[1239] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1240] = true;
                } else {
                    $jacocoInit[1241] = true;
                    new GraphicActionRemoveEvent(sDKInstance, str2, str3).executeActionOnRender();
                    $jacocoInit[1242] = true;
                }
                $jacocoInit[1243] = true;
            } catch (Exception e) {
                $jacocoInit[1244] = true;
                WXLogUtils.e("[WXBridgeManager] callRemoveEvent exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1245] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1246] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callRemoveEvent", stackTrace, (Map<String, String>) null);
                $jacocoInit[1247] = true;
            }
            getNextTick(str);
            $jacocoInit[1248] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1231] = true;
        } else {
            $jacocoInit[1232] = true;
            WXLogUtils.d("[WXBridgeManager] call callRemoveEvent arguments is null");
            $jacocoInit[1233] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callRemoveEvent", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1234] = true;
        return 0;
    }

    public int callUpdateStyle(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, HashMap<String, String> hashMap4) {
        String str3 = str;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1249] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1250] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1255] = true;
            } else {
                $jacocoInit[1256] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1257] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1258] = true;
            } else {
                $jacocoInit[1259] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1260] = true;
                } else {
                    $jacocoInit[1261] = true;
                    GraphicActionUpdateStyle graphicActionUpdateStyle = new GraphicActionUpdateStyle(sDKInstance, str2, hashMap, hashMap2, hashMap3, hashMap4);
                    $jacocoInit[1262] = true;
                    WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionUpdateStyle.getPageId(), graphicActionUpdateStyle);
                    $jacocoInit[1263] = true;
                }
                $jacocoInit[1264] = true;
            } catch (Exception e) {
                $jacocoInit[1265] = true;
                WXLogUtils.e("[WXBridgeManager] callUpdateStyle exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1266] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1267] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callUpdateStyle", stackTrace, (Map<String, String>) null);
                $jacocoInit[1268] = true;
            }
            $jacocoInit[1269] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1251] = true;
        } else {
            $jacocoInit[1252] = true;
            WXLogUtils.d("[WXBridgeManager] call callUpdateStyle arguments is null");
            $jacocoInit[1253] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callUpdateStyle", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1254] = true;
        return 0;
    }

    public int callUpdateAttrs(String str, String str2, HashMap<String, String> hashMap) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1270] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1271] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1276] = true;
            } else {
                $jacocoInit[1277] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1278] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1279] = true;
            } else {
                $jacocoInit[1280] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1281] = true;
                } else {
                    $jacocoInit[1282] = true;
                    GraphicActionUpdateAttr graphicActionUpdateAttr = new GraphicActionUpdateAttr(sDKInstance, str2, hashMap);
                    $jacocoInit[1283] = true;
                    WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionUpdateAttr.getPageId(), graphicActionUpdateAttr);
                    $jacocoInit[1284] = true;
                }
                $jacocoInit[1285] = true;
            } catch (Exception e) {
                $jacocoInit[1286] = true;
                WXLogUtils.e("[WXBridgeManager] callUpdateAttrs exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1287] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1288] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callUpdateAttrs", stackTrace, (Map<String, String>) null);
                $jacocoInit[1289] = true;
            }
            $jacocoInit[1290] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1272] = true;
        } else {
            $jacocoInit[1273] = true;
            WXLogUtils.d("[WXBridgeManager] call callUpdateAttrs arguments is null");
            $jacocoInit[1274] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callUpdateAttrs", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1275] = true;
        return 0;
    }

    public int callLayout(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1291] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1292] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1297] = true;
            } else {
                $jacocoInit[1298] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1299] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1300] = true;
            } else {
                $jacocoInit[1301] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                if (sDKInstance == null) {
                    $jacocoInit[1302] = true;
                } else {
                    $jacocoInit[1303] = true;
                    GraphicSize graphicSize = new GraphicSize((float) i6, (float) i5);
                    $jacocoInit[1304] = true;
                    GraphicPosition graphicPosition = new GraphicPosition((float) i3, (float) i, (float) i4, (float) i2);
                    $jacocoInit[1305] = true;
                    GraphicActionAddElement inActiveAddElementAction = sDKInstance.getInActiveAddElementAction(str2);
                    if (inActiveAddElementAction != null) {
                        $jacocoInit[1306] = true;
                        inActiveAddElementAction.setRTL(z);
                        $jacocoInit[1307] = true;
                        inActiveAddElementAction.setSize(graphicSize);
                        $jacocoInit[1308] = true;
                        inActiveAddElementAction.setPosition(graphicPosition);
                        $jacocoInit[1309] = true;
                        if (TextUtils.equals(str2, WXComponent.ROOT)) {
                            $jacocoInit[1310] = true;
                        } else {
                            $jacocoInit[1311] = true;
                            inActiveAddElementAction.setIndex(i7);
                            $jacocoInit[1312] = true;
                        }
                        WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(str, inActiveAddElementAction);
                        $jacocoInit[1313] = true;
                        sDKInstance.removeInActiveAddElmentAction(str2);
                        $jacocoInit[1314] = true;
                    } else {
                        GraphicActionLayout graphicActionLayout = new GraphicActionLayout(sDKInstance, str2, graphicPosition, graphicSize, z);
                        $jacocoInit[1315] = true;
                        WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionLayout.getPageId(), graphicActionLayout);
                        $jacocoInit[1316] = true;
                    }
                }
                $jacocoInit[1317] = true;
            } catch (Exception e) {
                $jacocoInit[1318] = true;
                WXLogUtils.e("[WXBridgeManager] callLayout exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1319] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1320] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callLayout", stackTrace, (Map<String, String>) null);
                $jacocoInit[1321] = true;
            }
            $jacocoInit[1322] = true;
            return 1;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1293] = true;
        } else {
            $jacocoInit[1294] = true;
            WXLogUtils.d("[WXBridgeManager] call callLayout arguments is null");
            $jacocoInit[1295] = true;
        }
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callLayout", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1296] = true;
        return 0;
    }

    public int callAppendTreeCreateFinish(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1323] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[1324] = true;
        } else {
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[1327] = true;
            } else {
                $jacocoInit[1328] = true;
            }
            if (this.mDestroyedInstanceId == null) {
                $jacocoInit[1329] = true;
            } else if (!this.mDestroyedInstanceId.contains(str)) {
                $jacocoInit[1330] = true;
            } else {
                $jacocoInit[1331] = true;
                return -1;
            }
            try {
                WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
                $jacocoInit[1332] = true;
                GraphicActionAppendTreeCreateFinish graphicActionAppendTreeCreateFinish = new GraphicActionAppendTreeCreateFinish(sDKInstance, str2);
                $jacocoInit[1333] = true;
                WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(str, graphicActionAppendTreeCreateFinish);
                $jacocoInit[1334] = true;
            } catch (Exception e) {
                $jacocoInit[1335] = true;
                WXLogUtils.e("[WXBridgeManager] callAppendTreeCreateFinish exception: ", (Throwable) e);
                WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
                $jacocoInit[1336] = true;
                String stackTrace = WXLogUtils.getStackTrace(e);
                $jacocoInit[1337] = true;
                WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callAppendTreeCreateFinish", stackTrace, (Map<String, String>) null);
                $jacocoInit[1338] = true;
            }
            $jacocoInit[1339] = true;
            return 1;
        }
        WXLogUtils.d("[WXBridgeManager] call callAppendTreeCreateFinish arguments is null");
        $jacocoInit[1325] = true;
        WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callAppendTreeCreateFinish", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
        $jacocoInit[1326] = true;
        return 0;
    }

    public int callCreateFinish(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1340] = true;
            WXLogUtils.d("[WXBridgeManager] call callCreateFinish arguments is null");
            $jacocoInit[1341] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callCreateFinish", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
            $jacocoInit[1342] = true;
            return 0;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1343] = true;
        } else {
            $jacocoInit[1344] = true;
        }
        if (this.mDestroyedInstanceId == null) {
            $jacocoInit[1345] = true;
        } else if (!this.mDestroyedInstanceId.contains(str)) {
            $jacocoInit[1346] = true;
        } else {
            $jacocoInit[1347] = true;
            return -1;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            $jacocoInit[1348] = true;
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            if (sDKInstance == null) {
                $jacocoInit[1349] = true;
            } else {
                $jacocoInit[1350] = true;
                sDKInstance.firstScreenCreateInstanceTime(currentTimeMillis);
                $jacocoInit[1351] = true;
                GraphicActionCreateFinish graphicActionCreateFinish = new GraphicActionCreateFinish(sDKInstance);
                $jacocoInit[1352] = true;
                WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(str, graphicActionCreateFinish);
                $jacocoInit[1353] = true;
            }
            $jacocoInit[1354] = true;
        } catch (Exception e) {
            $jacocoInit[1355] = true;
            WXLogUtils.e("[WXBridgeManager] callCreateFinish exception: ", (Throwable) e);
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
            $jacocoInit[1356] = true;
            String stackTrace = WXLogUtils.getStackTrace(e);
            $jacocoInit[1357] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callCreateFinish", stackTrace, (Map<String, String>) null);
            $jacocoInit[1358] = true;
        }
        $jacocoInit[1359] = true;
        return 1;
    }

    public int callRenderSuccess(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1360] = true;
            WXLogUtils.d("[WXBridgeManager] call callRenderSuccess arguments is null");
            $jacocoInit[1361] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, WXErrorCode.WX_RENDER_ERR_BRIDGE_ARG_NULL, "callRenderSuccess", "arguments is empty, INSTANCE_RENDERING_ERROR will be set", (Map<String, String>) null);
            $jacocoInit[1362] = true;
            return 0;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1363] = true;
        } else {
            $jacocoInit[1364] = true;
        }
        if (this.mDestroyedInstanceId == null) {
            $jacocoInit[1365] = true;
        } else if (!this.mDestroyedInstanceId.contains(str)) {
            $jacocoInit[1366] = true;
        } else {
            $jacocoInit[1367] = true;
            return -1;
        }
        try {
            WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
            if (sDKInstance == null) {
                $jacocoInit[1368] = true;
            } else {
                $jacocoInit[1369] = true;
                GraphicActionRenderSuccess graphicActionRenderSuccess = new GraphicActionRenderSuccess(sDKInstance);
                $jacocoInit[1370] = true;
                WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(str, graphicActionRenderSuccess);
                $jacocoInit[1371] = true;
            }
            $jacocoInit[1372] = true;
        } catch (Exception e) {
            $jacocoInit[1373] = true;
            WXLogUtils.e("[WXBridgeManager] callRenderSuccess exception: ", (Throwable) e);
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_INVOKE_BRIDGE;
            $jacocoInit[1374] = true;
            String stackTrace = WXLogUtils.getStackTrace(e);
            $jacocoInit[1375] = true;
            WXExceptionUtils.commitCriticalExceptionRT(str, wXErrorCode, "callRenderSuccess", stackTrace, (Map<String, String>) null);
            $jacocoInit[1376] = true;
        }
        $jacocoInit[1377] = true;
        return 1;
    }

    public ContentBoxMeasurement getMeasurementFunc(String str, long j) {
        ContentBoxMeasurement contentBoxMeasurement;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1378] = true;
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance == null) {
            $jacocoInit[1379] = true;
            contentBoxMeasurement = null;
        } else {
            $jacocoInit[1380] = true;
            contentBoxMeasurement = sDKInstance.getContentBoxMeasurement(j);
            $jacocoInit[1381] = true;
        }
        $jacocoInit[1382] = true;
        return contentBoxMeasurement;
    }

    public void bindMeasurementToRenderObject(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1383] = true;
        } else {
            $jacocoInit[1384] = true;
            this.mWXBridge.bindMeasurementToRenderObject(j);
            $jacocoInit[1385] = true;
        }
        $jacocoInit[1386] = true;
    }

    @UiThread
    public boolean notifyLayout(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isJSFrameworkInit()) {
            $jacocoInit[1387] = true;
            boolean notifyLayout = this.mWXBridge.notifyLayout(str);
            $jacocoInit[1388] = true;
            return notifyLayout;
        }
        $jacocoInit[1389] = true;
        return false;
    }

    @UiThread
    public void forceLayout(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1390] = true;
        } else {
            $jacocoInit[1391] = true;
            this.mWXBridge.forceLayout(str);
            $jacocoInit[1392] = true;
        }
        $jacocoInit[1393] = true;
    }

    public void onInstanceClose(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1394] = true;
        } else {
            $jacocoInit[1395] = true;
            this.mWXBridge.onInstanceClose(str);
            $jacocoInit[1396] = true;
        }
        $jacocoInit[1397] = true;
    }

    public void setDefaultRootSize(String str, float f, float f2, boolean z, boolean z2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1398] = true;
        } else {
            $jacocoInit[1399] = true;
            this.mWXBridge.setDefaultHeightAndWidthIntoRootDom(str, f, f2, z, z2);
            $jacocoInit[1400] = true;
        }
        $jacocoInit[1401] = true;
    }

    public void setRenderContentWrapContentToCore(boolean z, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1402] = true;
        } else {
            $jacocoInit[1403] = true;
            this.mWXBridge.setRenderContainerWrapContent(z, str);
            $jacocoInit[1404] = true;
        }
        $jacocoInit[1405] = true;
    }

    public void setStyleWidth(String str, String str2, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1406] = true;
        } else {
            $jacocoInit[1407] = true;
            this.mWXBridge.setStyleWidth(str, str2, f);
            $jacocoInit[1408] = true;
        }
        $jacocoInit[1409] = true;
    }

    public void setStyleHeight(String str, String str2, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1410] = true;
        } else {
            $jacocoInit[1411] = true;
            this.mWXBridge.setStyleHeight(str, str2, f);
            $jacocoInit[1412] = true;
        }
        $jacocoInit[1413] = true;
    }

    public long[] getFirstScreenRenderTime(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isJSFrameworkInit()) {
            $jacocoInit[1414] = true;
            long[] firstScreenRenderTime = this.mWXBridge.getFirstScreenRenderTime(str);
            $jacocoInit[1415] = true;
            return firstScreenRenderTime;
        }
        long[] jArr = {0, 0, 0};
        $jacocoInit[1416] = true;
        return jArr;
    }

    public long[] getRenderFinishTime(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isJSFrameworkInit()) {
            $jacocoInit[1417] = true;
            long[] renderFinishTime = this.mWXBridge.getRenderFinishTime(str);
            $jacocoInit[1418] = true;
            return renderFinishTime;
        }
        long[] jArr = {0, 0, 0};
        $jacocoInit[1419] = true;
        return jArr;
    }

    public void setMargin(String str, String str2, CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1420] = true;
        } else {
            $jacocoInit[1421] = true;
            this.mWXBridge.setMargin(str, str2, edge, f);
            $jacocoInit[1422] = true;
        }
        $jacocoInit[1423] = true;
    }

    public void setPadding(String str, String str2, CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1424] = true;
        } else {
            $jacocoInit[1425] = true;
            this.mWXBridge.setPadding(str, str2, edge, f);
            $jacocoInit[1426] = true;
        }
        $jacocoInit[1427] = true;
    }

    public void setPosition(String str, String str2, CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1428] = true;
        } else {
            $jacocoInit[1429] = true;
            this.mWXBridge.setPosition(str, str2, edge, f);
            $jacocoInit[1430] = true;
        }
        $jacocoInit[1431] = true;
    }

    public void markDirty(String str, String str2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isJSFrameworkInit()) {
            $jacocoInit[1432] = true;
        } else {
            $jacocoInit[1433] = true;
            this.mWXBridge.markDirty(str, str2, z);
            $jacocoInit[1434] = true;
        }
        $jacocoInit[1435] = true;
    }

    public int callHasTransitionPros(String str, String str2, HashMap<String, String> hashMap) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(str, str2);
        $jacocoInit[1436] = true;
        if (wXComponent == null) {
            $jacocoInit[1437] = true;
        } else if (wXComponent.getTransition() == null) {
            $jacocoInit[1438] = true;
        } else if (wXComponent.getTransition().getProperties() == null) {
            $jacocoInit[1439] = true;
        } else {
            $jacocoInit[1441] = true;
            for (String containsKey : wXComponent.getTransition().getProperties()) {
                $jacocoInit[1442] = true;
                if (hashMap.containsKey(containsKey)) {
                    $jacocoInit[1443] = true;
                    return 1;
                }
                $jacocoInit[1444] = true;
            }
            $jacocoInit[1445] = true;
            return 0;
        }
        $jacocoInit[1440] = true;
        return -1;
    }

    public void registerCoreEnv(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isJSFrameworkInit()) {
            $jacocoInit[1446] = true;
            this.mWXBridge.registerCoreEnv(str, str2);
            $jacocoInit[1447] = true;
        } else {
            mWeexCoreEnvOptions.put(str, str2);
            $jacocoInit[1448] = true;
        }
        $jacocoInit[1449] = true;
    }

    private void onJsFrameWorkInitSuccees() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1450] = true;
        for (Map.Entry next : mWeexCoreEnvOptions.entrySet()) {
            $jacocoInit[1451] = true;
            this.mWXBridge.registerCoreEnv((String) next.getKey(), (String) next.getValue());
            $jacocoInit[1452] = true;
        }
        mWeexCoreEnvOptions.clear();
        $jacocoInit[1453] = true;
    }
}
