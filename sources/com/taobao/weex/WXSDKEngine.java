package com.taobao.weex;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.taobao.weex.InitConfig;
import com.taobao.weex.adapter.IDrawableLoader;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.adapter.IWXJsFileLoaderAdapter;
import com.taobao.weex.adapter.IWXSoLoaderAdapter;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.appfram.clipboard.WXClipboardModule;
import com.taobao.weex.appfram.navigator.IActivityNavBarSetter;
import com.taobao.weex.appfram.navigator.INavigator;
import com.taobao.weex.appfram.navigator.WXNavigatorModule;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;
import com.taobao.weex.appfram.storage.WXStorageModule;
import com.taobao.weex.appfram.websocket.WebSocketModule;
import com.taobao.weex.bridge.ModuleFactory;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.bridge.WXModuleManager;
import com.taobao.weex.bridge.WXServiceManager;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.common.TypeModuleFactory;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXInstanceWrap;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.http.WXStreamModule;
import com.taobao.weex.ui.ExternalLoaderComponentHolder;
import com.taobao.weex.ui.IExternalComponentGetter;
import com.taobao.weex.ui.IExternalModuleGetter;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.taobao.weex.ui.WXComponentRegistry;
import com.taobao.weex.ui.animation.WXAnimationModule;
import com.taobao.weex.ui.component.Textarea;
import com.taobao.weex.ui.component.WXA;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXEmbed;
import com.taobao.weex.ui.component.WXHeader;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXIndicator;
import com.taobao.weex.ui.component.WXInput;
import com.taobao.weex.ui.component.WXLoading;
import com.taobao.weex.ui.component.WXLoadingIndicator;
import com.taobao.weex.ui.component.WXRefresh;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.WXSlider;
import com.taobao.weex.ui.component.WXSliderNeighbor;
import com.taobao.weex.ui.component.WXSwitch;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.component.WXVideo;
import com.taobao.weex.ui.component.WXWeb;
import com.taobao.weex.ui.component.list.HorizontalListComponent;
import com.taobao.weex.ui.component.list.SimpleListComponent;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.component.list.WXListComponent;
import com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList;
import com.taobao.weex.ui.component.richtext.WXRichText;
import com.taobao.weex.ui.config.AutoScanConfigRegister;
import com.taobao.weex.ui.module.WXLocaleModule;
import com.taobao.weex.ui.module.WXMetaModule;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.taobao.weex.ui.module.WXTimerModule;
import com.taobao.weex.ui.module.WXWebViewModule;
import com.taobao.weex.utils.LogLevel;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXSoInstallMgrSdk;
import com.taobao.weex.utils.batch.BatchOperationHelper;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class WXSDKEngine implements Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String JS_FRAMEWORK_RELOAD = "js_framework_reload";
    private static final String TAG = "WXSDKEngine";
    private static final String V8_SO_NAME = "weexcore";
    private static volatile boolean mIsInit = false;
    private static volatile boolean mIsSoInit = false;
    private static final Object mLock = new Object();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2784265340904134838L, "com/taobao/weex/WXSDKEngine", 161);
        $jacocoData = a2;
        return a2;
    }

    public WXSDKEngine() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ boolean access$000() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = mIsSoInit;
        $jacocoInit[158] = true;
        return z;
    }

    static /* synthetic */ boolean access$002(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        mIsSoInit = z;
        $jacocoInit[157] = true;
        return z;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[159] = true;
        $jacocoInit[160] = true;
    }

    @Deprecated
    public static void init(Application application) {
        boolean[] $jacocoInit = $jacocoInit();
        init(application, (IWXUserTrackAdapter) null);
        $jacocoInit[1] = true;
    }

    @Deprecated
    public static void init(Application application, IWXUserTrackAdapter iWXUserTrackAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        init(application, iWXUserTrackAdapter, (String) null);
        $jacocoInit[2] = true;
    }

    @Deprecated
    public static void init(Application application, IWXUserTrackAdapter iWXUserTrackAdapter, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        InitConfig.Builder builder = new InitConfig.Builder();
        $jacocoInit[3] = true;
        InitConfig.Builder utAdapter = builder.setUtAdapter(iWXUserTrackAdapter);
        $jacocoInit[4] = true;
        InitConfig build = utAdapter.build();
        $jacocoInit[5] = true;
        initialize(application, build);
        $jacocoInit[6] = true;
    }

    public static boolean isInitialized() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        synchronized (mLock) {
            try {
                $jacocoInit[7] = true;
                if (!mIsInit) {
                    $jacocoInit[8] = true;
                } else if (!WXEnvironment.JsFrameworkInit) {
                    $jacocoInit[9] = true;
                } else {
                    $jacocoInit[10] = true;
                    z = true;
                }
                z = false;
                $jacocoInit[11] = true;
            } catch (Throwable th) {
                while (true) {
                    $jacocoInit[13] = true;
                    throw th;
                }
            }
        }
        $jacocoInit[12] = true;
        return z;
    }

    public static boolean isSoInitialized() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        synchronized (mLock) {
            try {
                $jacocoInit[14] = true;
                z = mIsSoInit;
            } catch (Throwable th) {
                while (true) {
                    $jacocoInit[16] = true;
                    throw th;
                }
            }
        }
        $jacocoInit[15] = true;
        return z;
    }

    public static void initialize(Application application, InitConfig initConfig) {
        boolean[] $jacocoInit = $jacocoInit();
        synchronized (mLock) {
            try {
                $jacocoInit[17] = true;
                if (!mIsInit) {
                    $jacocoInit[18] = true;
                    long currentTimeMillis = System.currentTimeMillis();
                    WXEnvironment.sSDKInitStart = currentTimeMillis;
                    $jacocoInit[20] = true;
                    if (WXEnvironment.isApkDebugable()) {
                        WXEnvironment.sLogLevel = LogLevel.DEBUG;
                        $jacocoInit[21] = true;
                    } else if (WXEnvironment.sApplication != null) {
                        WXEnvironment.sLogLevel = LogLevel.WARN;
                        $jacocoInit[22] = true;
                    } else {
                        WXLogUtils.e(TAG, "WXEnvironment.sApplication is " + WXEnvironment.sApplication);
                        $jacocoInit[23] = true;
                    }
                    doInitInternal(application, initConfig);
                    $jacocoInit[24] = true;
                    registerApplicationOptions(application);
                    $jacocoInit[25] = true;
                    WXEnvironment.sSDKInitInvokeTime = System.currentTimeMillis() - currentTimeMillis;
                    $jacocoInit[26] = true;
                    WXLogUtils.renderPerformanceLog("SDKInitInvokeTime", WXEnvironment.sSDKInitInvokeTime);
                    mIsInit = true;
                    $jacocoInit[28] = true;
                    return;
                }
                $jacocoInit[19] = true;
            } catch (Throwable th) {
                while (true) {
                    $jacocoInit[27] = true;
                    throw th;
                }
            }
        }
    }

    private static void registerApplicationOptions(Application application) {
        boolean[] $jacocoInit = $jacocoInit();
        if (application == null) {
            $jacocoInit[29] = true;
            WXLogUtils.e(TAG, "RegisterApplicationOptions application is null");
            $jacocoInit[30] = true;
            return;
        }
        Resources resources = application.getResources();
        $jacocoInit[31] = true;
        registerCoreEnv("screen_width_pixels", String.valueOf(resources.getDisplayMetrics().widthPixels));
        $jacocoInit[32] = true;
        registerCoreEnv("screen_height_pixels", String.valueOf(resources.getDisplayMetrics().heightPixels));
        $jacocoInit[33] = true;
        int identifier = resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android");
        if (identifier <= 0) {
            $jacocoInit[34] = true;
        } else {
            $jacocoInit[35] = true;
            int dimensionPixelSize = resources.getDimensionPixelSize(identifier);
            $jacocoInit[36] = true;
            registerCoreEnv(PreferenceConstantsInOpenSdk.B, String.valueOf(dimensionPixelSize));
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
    }

    private static void doInitInternal(final Application application, final InitConfig initConfig) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEnvironment.sApplication = application;
        if (application != null) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            WXLogUtils.e(TAG, " doInitInternal application is null");
            $jacocoInit[41] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT;
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT;
            $jacocoInit[42] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("WXEnvironment sApplication is null");
            String sb2 = sb.toString();
            $jacocoInit[43] = true;
            WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode, "doInitInternal", sb2, (Map<String, String>) null);
            $jacocoInit[44] = true;
        }
        WXEnvironment.JsFrameworkInit = false;
        $jacocoInit[45] = true;
        WXBridgeManager.getInstance().post(new Runnable() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1650384090391374599L, "com/taobao/weex/WXSDKEngine$1", 22);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void run() {
                IWXUserTrackAdapter iWXUserTrackAdapter;
                boolean[] $jacocoInit = $jacocoInit();
                long currentTimeMillis = System.currentTimeMillis();
                $jacocoInit[1] = true;
                WXSDKManager instance = WXSDKManager.getInstance();
                $jacocoInit[2] = true;
                instance.onSDKEngineInitialize();
                if (initConfig == null) {
                    $jacocoInit[3] = true;
                } else {
                    $jacocoInit[4] = true;
                    instance.setInitConfig(initConfig);
                    $jacocoInit[5] = true;
                }
                Application application = application;
                $jacocoInit[6] = true;
                IWXSoLoaderAdapter iWXSoLoaderAdapter = instance.getIWXSoLoaderAdapter();
                $jacocoInit[7] = true;
                IWXStatisticsListener wXStatisticsListener = instance.getWXStatisticsListener();
                $jacocoInit[8] = true;
                WXSoInstallMgrSdk.init(application, iWXSoLoaderAdapter, wXStatisticsListener);
                $jacocoInit[9] = true;
                String str = null;
                if (initConfig != null) {
                    iWXUserTrackAdapter = initConfig.getUtAdapter();
                    $jacocoInit[10] = true;
                } else {
                    $jacocoInit[11] = true;
                    iWXUserTrackAdapter = null;
                }
                WXSDKEngine.access$002(WXSoInstallMgrSdk.initSo("weexcore", 1, iWXUserTrackAdapter));
                $jacocoInit[12] = true;
                if (!WXSDKEngine.access$000()) {
                    $jacocoInit[13] = true;
                    WXErrorCode wXErrorCode = WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT;
                    StringBuilder sb = new StringBuilder();
                    WXErrorCode wXErrorCode2 = WXErrorCode.WX_KEY_EXCEPTION_SDK_INIT;
                    $jacocoInit[14] = true;
                    sb.append(wXErrorCode2.getErrorMsg());
                    sb.append("isSoInit false");
                    String sb2 = sb.toString();
                    $jacocoInit[15] = true;
                    WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode, "doInitInternal", sb2, (Map<String, String>) null);
                    $jacocoInit[16] = true;
                    return;
                }
                if (initConfig != null) {
                    str = initConfig.getFramework();
                    $jacocoInit[17] = true;
                } else {
                    $jacocoInit[18] = true;
                }
                instance.initScriptsFramework(str);
                $jacocoInit[19] = true;
                WXEnvironment.sSDKInitExecuteTime = System.currentTimeMillis() - currentTimeMillis;
                $jacocoInit[20] = true;
                WXLogUtils.renderPerformanceLog("SDKInitExecuteTime", WXEnvironment.sSDKInitExecuteTime);
                $jacocoInit[21] = true;
            }
        });
        $jacocoInit[46] = true;
        register();
        $jacocoInit[47] = true;
    }

    @Deprecated
    public static void init(Application application, String str, IWXUserTrackAdapter iWXUserTrackAdapter, IWXImgLoaderAdapter iWXImgLoaderAdapter, IWXHttpAdapter iWXHttpAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        InitConfig.Builder builder = new InitConfig.Builder();
        $jacocoInit[48] = true;
        InitConfig.Builder utAdapter = builder.setUtAdapter(iWXUserTrackAdapter);
        $jacocoInit[49] = true;
        InitConfig.Builder httpAdapter = utAdapter.setHttpAdapter(iWXHttpAdapter);
        $jacocoInit[50] = true;
        InitConfig.Builder imgAdapter = httpAdapter.setImgAdapter(iWXImgLoaderAdapter);
        $jacocoInit[51] = true;
        InitConfig build = imgAdapter.build();
        $jacocoInit[52] = true;
        initialize(application, build);
        $jacocoInit[53] = true;
    }

    public static void setJSExcetptionAdapter(IWXJSExceptionAdapter iWXJSExceptionAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager.getInstance().setIWXJSExceptionAdapter(iWXJSExceptionAdapter);
        $jacocoInit[54] = true;
    }

    private static void register() {
        boolean[] $jacocoInit = $jacocoInit();
        BatchOperationHelper batchOperationHelper = new BatchOperationHelper(WXBridgeManager.getInstance());
        try {
            $jacocoInit[55] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXText.class, new WXText.Creator()), false, "text");
            $jacocoInit[56] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXDiv.class, new WXDiv.Ceator()), false, WXBasicComponentType.CONTAINER, "div", "header", WXBasicComponentType.FOOTER);
            $jacocoInit[57] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXImage.class, new WXImage.Creator()), false, "image", "img");
            $jacocoInit[58] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXScroller.class, new WXScroller.Creator()), false, WXBasicComponentType.SCROLLER);
            $jacocoInit[59] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXSlider.class, new WXSlider.Creator()), true, WXBasicComponentType.SLIDER, WXBasicComponentType.CYCLE_SLIDER);
            $jacocoInit[60] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXSliderNeighbor.class, new WXSliderNeighbor.Creator()), true, WXBasicComponentType.SLIDER_NEIGHBOR);
            $jacocoInit[61] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXCell.class, new WXCell.Creator()), true, "cell");
            $jacocoInit[62] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXListComponent.class, new WXListComponent.Creator()), true, "list", WXBasicComponentType.VLIST, WXBasicComponentType.RECYCLER, WXBasicComponentType.WATERFALL);
            $jacocoInit[63] = true;
            registerComponent((IFComponentHolder) new SimpleComponentHolder(WXRichText.class, new WXRichText.Creator()), false, WXBasicComponentType.RICHTEXT);
            $jacocoInit[64] = true;
            registerComponent((Class<? extends WXComponent>) SimpleListComponent.class, false, "simplelist");
            $jacocoInit[65] = true;
            registerComponent((Class<? extends WXComponent>) WXRecyclerTemplateList.class, false, WXBasicComponentType.RECYCLE_LIST);
            $jacocoInit[66] = true;
            registerComponent((Class<? extends WXComponent>) HorizontalListComponent.class, false, WXBasicComponentType.HLIST);
            $jacocoInit[67] = true;
            registerComponent(WXBasicComponentType.CELL_SLOT, (Class<? extends WXComponent>) WXCell.class, true);
            $jacocoInit[68] = true;
            registerComponent(WXBasicComponentType.INDICATOR, (Class<? extends WXComponent>) WXIndicator.class, true);
            $jacocoInit[69] = true;
            registerComponent("video", (Class<? extends WXComponent>) WXVideo.class, false);
            $jacocoInit[70] = true;
            registerComponent("input", (Class<? extends WXComponent>) WXInput.class, false);
            $jacocoInit[71] = true;
            registerComponent(WXBasicComponentType.TEXTAREA, (Class<? extends WXComponent>) Textarea.class, false);
            $jacocoInit[72] = true;
            registerComponent("switch", (Class<? extends WXComponent>) WXSwitch.class, false);
            $jacocoInit[73] = true;
            registerComponent("a", (Class<? extends WXComponent>) WXA.class, false);
            $jacocoInit[74] = true;
            registerComponent(WXBasicComponentType.EMBED, (Class<? extends WXComponent>) WXEmbed.class, true);
            $jacocoInit[75] = true;
            registerComponent("web", (Class<? extends WXComponent>) WXWeb.class);
            $jacocoInit[76] = true;
            registerComponent("refresh", (Class<? extends WXComponent>) WXRefresh.class);
            $jacocoInit[77] = true;
            registerComponent("loading", (Class<? extends WXComponent>) WXLoading.class);
            $jacocoInit[78] = true;
            registerComponent(WXBasicComponentType.LOADING_INDICATOR, (Class<? extends WXComponent>) WXLoadingIndicator.class);
            $jacocoInit[79] = true;
            registerComponent("header", (Class<? extends WXComponent>) WXHeader.class);
            $jacocoInit[80] = true;
            registerModule("modal", WXModalUIModule.class);
            $jacocoInit[81] = true;
            registerModule("instanceWrap", WXInstanceWrap.class);
            $jacocoInit[82] = true;
            registerModule("animation", WXAnimationModule.class);
            $jacocoInit[83] = true;
            registerModule("webview", WXWebViewModule.class);
            $jacocoInit[84] = true;
            registerModule("navigator", WXNavigatorModule.class);
            $jacocoInit[85] = true;
            registerModule("stream", WXStreamModule.class);
            $jacocoInit[86] = true;
            registerModule("timer", WXTimerModule.class);
            $jacocoInit[87] = true;
            registerModule("storage", WXStorageModule.class);
            $jacocoInit[88] = true;
            registerModule(ShareChannel.d, WXClipboardModule.class);
            $jacocoInit[89] = true;
            registerModule("globalEvent", WXGlobalEventModule.class);
            $jacocoInit[90] = true;
            registerModule("picker", WXPickersModule.class);
            $jacocoInit[91] = true;
            registerModule("meta", WXMetaModule.class);
            $jacocoInit[92] = true;
            registerModule("webSocket", WebSocketModule.class);
            $jacocoInit[93] = true;
            registerModule("locale", WXLocaleModule.class);
            $jacocoInit[94] = true;
        } catch (WXException e) {
            $jacocoInit[95] = true;
            WXLogUtils.e("[WXSDKEngine] register:", (Throwable) e);
            $jacocoInit[96] = true;
        }
        AutoScanConfigRegister.doScanConfig();
        $jacocoInit[97] = true;
        batchOperationHelper.flush();
        $jacocoInit[98] = true;
    }

    public static boolean registerComponent(String str, Class<? extends WXComponent> cls, boolean z) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerComponent = registerComponent(cls, z, str);
        $jacocoInit[99] = true;
        return registerComponent;
    }

    public static boolean registerComponent(String str, IExternalComponentGetter iExternalComponentGetter, boolean z) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerComponent = registerComponent((IFComponentHolder) new ExternalLoaderComponentHolder(str, iExternalComponentGetter), z, str);
        $jacocoInit[100] = true;
        return registerComponent;
    }

    public static boolean registerComponent(Class<? extends WXComponent> cls, boolean z, String... strArr) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        if (cls == null) {
            $jacocoInit[101] = true;
            return false;
        }
        SimpleComponentHolder simpleComponentHolder = new SimpleComponentHolder(cls);
        $jacocoInit[102] = true;
        boolean registerComponent = registerComponent((IFComponentHolder) simpleComponentHolder, z, strArr);
        $jacocoInit[103] = true;
        return registerComponent;
    }

    public static boolean registerComponent(IFComponentHolder iFComponentHolder, boolean z, String... strArr) throws WXException {
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[104] = true;
            int length = strArr.length;
            $jacocoInit[105] = true;
            int i = 0;
            z2 = true;
            while (i < length) {
                try {
                    String str = strArr[i];
                    $jacocoInit[106] = true;
                    HashMap hashMap = new HashMap();
                    if (!z) {
                        $jacocoInit[107] = true;
                    } else {
                        $jacocoInit[108] = true;
                        hashMap.put(AgentOptions.c, "tree");
                        $jacocoInit[109] = true;
                    }
                    if (!z2) {
                        $jacocoInit[110] = true;
                    } else if (!WXComponentRegistry.registerComponent(str, iFComponentHolder, hashMap)) {
                        $jacocoInit[111] = true;
                    } else {
                        $jacocoInit[112] = true;
                        z2 = true;
                        i++;
                        $jacocoInit[114] = true;
                    }
                    $jacocoInit[113] = true;
                    z2 = false;
                    i++;
                    $jacocoInit[114] = true;
                } catch (Throwable th) {
                    th = th;
                    $jacocoInit[116] = true;
                    th.printStackTrace();
                    $jacocoInit[117] = true;
                    return z2;
                }
            }
            $jacocoInit[115] = true;
            return z2;
        } catch (Throwable th2) {
            th = th2;
            z2 = true;
            $jacocoInit[116] = true;
            th.printStackTrace();
            $jacocoInit[117] = true;
            return z2;
        }
    }

    public static <T extends WXModule> boolean registerModule(String str, Class<T> cls, boolean z) throws WXException {
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        if (cls == null) {
            $jacocoInit[118] = true;
        } else if (!registerModule(str, (ModuleFactory) new TypeModuleFactory(cls), z)) {
            $jacocoInit[119] = true;
        } else {
            $jacocoInit[120] = true;
            z2 = true;
            $jacocoInit[122] = true;
            return z2;
        }
        z2 = false;
        $jacocoInit[121] = true;
        $jacocoInit[122] = true;
        return z2;
    }

    public static <T extends WXModule> boolean registerModuleWithFactory(String str, DestroyableModuleFactory destroyableModuleFactory, boolean z) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerModule = registerModule(str, (ModuleFactory) destroyableModuleFactory, z);
        $jacocoInit[123] = true;
        return registerModule;
    }

    public static <T extends WXModule> boolean registerModuleWithFactory(String str, IExternalModuleGetter iExternalModuleGetter, boolean z) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerModule = registerModule(str, iExternalModuleGetter.getExternalModuleClass(str, WXEnvironment.getApplication()), z);
        $jacocoInit[124] = true;
        return registerModule;
    }

    public static <T extends WXModule> boolean registerModule(String str, ModuleFactory moduleFactory, boolean z) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerModule = WXModuleManager.registerModule(str, moduleFactory, z);
        $jacocoInit[125] = true;
        return registerModule;
    }

    public static boolean registerModule(String str, Class<? extends WXModule> cls) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerModule = registerModule(str, cls, false);
        $jacocoInit[126] = true;
        return registerModule;
    }

    public static boolean registerService(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerService = WXServiceManager.registerService(str, str2, map);
        $jacocoInit[127] = true;
        return registerService;
    }

    public static boolean unRegisterService(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean unRegisterService = WXServiceManager.unRegisterService(str);
        $jacocoInit[128] = true;
        return unRegisterService;
    }

    public static abstract class DestroyableModule extends WXModule implements Destroyable {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-7064542685841144163L, "com/taobao/weex/WXSDKEngine$DestroyableModule", 1);
            $jacocoData = a2;
            return a2;
        }

        public DestroyableModule() {
            $jacocoInit()[0] = true;
        }
    }

    public static abstract class DestroyableModuleFactory<T extends DestroyableModule> extends TypeModuleFactory<T> {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-8836497666681595687L, "com/taobao/weex/WXSDKEngine$DestroyableModuleFactory", 1);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DestroyableModuleFactory(Class<T> cls) {
            super(cls);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }
    }

    public static void callback(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager.getInstance().callback(str, str2, map);
        $jacocoInit[129] = true;
    }

    public static void restartBridge(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEnvironment.sDebugMode = z;
        $jacocoInit[130] = true;
        WXSDKManager.getInstance().restartBridge();
        $jacocoInit[131] = true;
    }

    public static boolean registerComponent(String str, Class<? extends WXComponent> cls) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        boolean registerComponent = WXComponentRegistry.registerComponent(str, new SimpleComponentHolder(cls), new HashMap());
        $jacocoInit[132] = true;
        return registerComponent;
    }

    public static boolean registerComponent(Map<String, Object> map, Class<? extends WXComponent> cls) throws WXException {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[133] = true;
            return false;
        }
        String str = (String) map.get("type");
        $jacocoInit[134] = true;
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[135] = true;
            return false;
        }
        boolean registerComponent = WXComponentRegistry.registerComponent(str, new SimpleComponentHolder(cls), map);
        $jacocoInit[136] = true;
        return registerComponent;
    }

    public static void addCustomOptions(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEnvironment.addCustomOptions(str, str2);
        $jacocoInit[137] = true;
    }

    public static IWXUserTrackAdapter getIWXUserTrackAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXUserTrackAdapter iWXUserTrackAdapter = WXSDKManager.getInstance().getIWXUserTrackAdapter();
        $jacocoInit[138] = true;
        return iWXUserTrackAdapter;
    }

    public static IWXImgLoaderAdapter getIWXImgLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXImgLoaderAdapter iWXImgLoaderAdapter = WXSDKManager.getInstance().getIWXImgLoaderAdapter();
        $jacocoInit[139] = true;
        return iWXImgLoaderAdapter;
    }

    public static IDrawableLoader getDrawableLoader() {
        boolean[] $jacocoInit = $jacocoInit();
        IDrawableLoader drawableLoader = WXSDKManager.getInstance().getDrawableLoader();
        $jacocoInit[140] = true;
        return drawableLoader;
    }

    public static IWXHttpAdapter getIWXHttpAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXHttpAdapter iWXHttpAdapter = WXSDKManager.getInstance().getIWXHttpAdapter();
        $jacocoInit[141] = true;
        return iWXHttpAdapter;
    }

    public static IWXStorageAdapter getIWXStorageAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStorageAdapter iWXStorageAdapter = WXSDKManager.getInstance().getIWXStorageAdapter();
        $jacocoInit[142] = true;
        return iWXStorageAdapter;
    }

    public static IWXJsFileLoaderAdapter getIWXJsFileLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJsFileLoaderAdapter iWXJsFileLoaderAdapter = WXSDKManager.getInstance().getIWXJsFileLoaderAdapter();
        $jacocoInit[143] = true;
        return iWXJsFileLoaderAdapter;
    }

    public static IActivityNavBarSetter getActivityNavBarSetter() {
        boolean[] $jacocoInit = $jacocoInit();
        IActivityNavBarSetter activityNavBarSetter = WXSDKManager.getInstance().getActivityNavBarSetter();
        $jacocoInit[144] = true;
        return activityNavBarSetter;
    }

    public static INavigator getNavigator() {
        boolean[] $jacocoInit = $jacocoInit();
        INavigator navigator = WXSDKManager.getInstance().getNavigator();
        $jacocoInit[145] = true;
        return navigator;
    }

    public static void setNavigator(INavigator iNavigator) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager.getInstance().setNavigator(iNavigator);
        $jacocoInit[146] = true;
    }

    public static void setActivityNavBarSetter(IActivityNavBarSetter iActivityNavBarSetter) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager.getInstance().setActivityNavBarSetter(iActivityNavBarSetter);
        $jacocoInit[147] = true;
    }

    public static void reload(final Context context, String str, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEnvironment.sRemoteDebugMode = z;
        $jacocoInit[148] = true;
        WXBridgeManager.getInstance().restart();
        $jacocoInit[149] = true;
        WXBridgeManager.getInstance().initScriptsFramework(str);
        $jacocoInit[150] = true;
        WXModuleManager.reload();
        $jacocoInit[151] = true;
        WXComponentRegistry.reload();
        $jacocoInit[152] = true;
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(7461721272829321072L, "com/taobao/weex/WXSDKEngine$2", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(WXSDKEngine.JS_FRAMEWORK_RELOAD));
                $jacocoInit[1] = true;
            }
        }, 0);
        $jacocoInit[153] = true;
    }

    public static void reload(Context context, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        reload(context, (String) null, z);
        $jacocoInit[154] = true;
    }

    public static void reload() {
        boolean[] $jacocoInit = $jacocoInit();
        reload(WXEnvironment.getApplication(), WXEnvironment.sRemoteDebugMode);
        $jacocoInit[155] = true;
    }

    public static void registerCoreEnv(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().registerCoreEnv(str, str2);
        $jacocoInit[156] = true;
    }
}
