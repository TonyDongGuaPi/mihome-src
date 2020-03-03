package com.taobao.weex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.alibaba.fastjson.JSONObject;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.taobao.weex.adapter.IDrawableLoader;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.adapter.IWXJscProcessManager;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.bridge.EventResult;
import com.taobao.weex.bridge.NativeInvokeHelper;
import com.taobao.weex.bridge.SimpleJSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.bridge.WXModuleManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.common.IWXDebugProxy;
import com.taobao.weex.common.OnWXScrollListener;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRefreshData;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.http.WXHttpUtil;
import com.taobao.weex.instance.InstanceOnFireEventInterceptor;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.performance.WXInstanceExceptionRecord;
import com.taobao.weex.tracing.WXTracing;
import com.taobao.weex.ui.action.GraphicActionAddElement;
import com.taobao.weex.ui.component.NestedContainer;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXEmbed;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.view.WXScrollView;
import com.taobao.weex.utils.Trace;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXJsonUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXReflectionUtils;
import com.taobao.weex.utils.WXUtils;
import com.xiaomi.infra.galaxy.fds.Common;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import miuipub.reflect.Field;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSDKInstance implements View.OnLayoutChangeListener, IWXActivityStateListener {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static String ACTION_DEBUG_INSTANCE_REFRESH = IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH;
    public static String ACTION_INSTANCE_RELOAD = IWXDebugProxy.ACTION_INSTANCE_RELOAD;
    public static final String BUNDLE_URL = "bundleUrl";
    private static final String SOURCE_TEMPLATE_BASE64_MD5 = "templateSourceBase64MD5";
    public static String requestUrl = "requestUrl";
    static int sScreenHeight = -1;
    public WXBridgeManager.BundType bundleType;
    private boolean enableLayerType = true;
    private boolean hasException = false;
    public PriorityQueue<WXEmbed> hiddenEmbeds;
    private Map<String, GraphicActionAddElement> inactiveAddElementAction;
    private boolean isCommit = false;
    private boolean isDestroy = false;
    public boolean isNewFsEnd;
    private volatile boolean isPreRenderMode;
    private boolean isRenderSuccess = false;
    private boolean isViewDisAppear;
    private WXInstanceApm mApmForInstance;
    private String mBundleUrl = "";
    private ComponentObserver mComponentObserver;
    private Map<String, String> mContainerInfo;
    private Map<Long, ContentBoxMeasurement> mContentBoxMeasurements;
    Context mContext;
    private boolean mCreateInstance;
    private boolean mCurrentGround;
    private CustomFontNetworkHandler mCustomFontNetworkHandler;
    public boolean mEnd = false;
    private WXInstanceExceptionRecord mExceptionRecorder;
    public int mExecJSTraceId;
    @NonNull
    private FlatGUIContext mFlatGUIContext;
    private WXGlobalEventReceiver mGlobalEventReceiver = null;
    private HashMap<String, List<String>> mGlobalEvents;
    public boolean mHasCreateFinish = false;
    private ImageNetworkHandler mImageNetworkHandler;
    private final String mInstanceId;
    private List<InstanceOnFireEventInterceptor> mInstanceOnFireEventInterceptorList;
    private int mInstanceViewPortWidth = 750;
    private WXRefreshData mLastRefreshData;
    private List<String> mLayerOverFlowListeners;
    private int mMaxDeepLayer;
    private NativeInvokeHelper mNativeInvokeHelper;
    private boolean mNeedReLoad = false;
    private boolean mNeedValidate = false;
    private NestedInstanceInterceptor mNestedInstanceInterceptor;
    private long mRefreshStartTime;
    private RenderContainer mRenderContainer;
    private IWXRenderListener mRenderListener;
    public long mRenderStartNanos;
    public long mRenderStartTime;
    private WXRenderStrategy mRenderStrategy;
    private boolean mRendered;
    private WXComponent mRootComp;
    private ScrollView mScrollView;
    private IWXStatisticsListener mStatisticsListener;
    private StreamNetworkHandler mStreamNetworkHandler;
    private boolean mUseScroller = false;
    private IWXUserTrackAdapter mUserTrackAdapter;
    private Map<String, Serializable> mUserTrackParams;
    private List<OnInstanceVisibleListener> mVisibleListeners;
    private WXPerformance mWXPerformance;
    private List<OnWXScrollListener> mWXScrollListeners;
    private WXScrollView.WXScrollViewListener mWXScrollViewListener;
    private int maxHiddenEmbedsNum;
    public long[] measureTimes;
    public String[] mwxDims;
    public Map<String, List<String>> responseHeaders;
    public WeakReference<String> templateRef;
    private boolean trackComponent;

    public interface CustomFontNetworkHandler {
        String fetchLocal(String str);
    }

    public interface ImageNetworkHandler {
        String fetchLocal(String str);
    }

    public interface NestedInstanceInterceptor {
        void onCreateNestInstance(WXSDKInstance wXSDKInstance, NestedContainer nestedContainer);
    }

    public interface OnInstanceVisibleListener {
        void onAppear();

        void onDisappear();
    }

    public interface StreamNetworkHandler {
        String fetchLocal(String str);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5083225219435703055L, "com/taobao/weex/WXSDKInstance", 794);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ boolean access$000(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXSDKInstance.isDestroy;
        $jacocoInit[780] = true;
        return z;
    }

    static /* synthetic */ boolean access$100(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXSDKInstance.hasException;
        $jacocoInit[781] = true;
        return z;
    }

    static /* synthetic */ WXInstanceApm access$1000(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        WXInstanceApm wXInstanceApm = wXSDKInstance.mApmForInstance;
        $jacocoInit[789] = true;
        return wXInstanceApm;
    }

    static /* synthetic */ boolean access$1100(WXSDKInstance wXSDKInstance, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isNet = wXSDKInstance.isNet(str);
        $jacocoInit[790] = true;
        return isNet;
    }

    static /* synthetic */ IWXUserTrackAdapter access$1200(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXUserTrackAdapter iWXUserTrackAdapter = wXSDKInstance.mUserTrackAdapter;
        $jacocoInit[791] = true;
        return iWXUserTrackAdapter;
    }

    static /* synthetic */ String access$1300(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = wXSDKInstance.mBundleUrl;
        $jacocoInit[792] = true;
        return str;
    }

    static /* synthetic */ boolean access$200(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXSDKInstance.isRenderSuccess;
        $jacocoInit[782] = true;
        return z;
    }

    static /* synthetic */ String access$300(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = wXSDKInstance.mInstanceId;
        $jacocoInit[783] = true;
        return str;
    }

    static /* synthetic */ boolean access$502(WXSDKInstance wXSDKInstance, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSDKInstance.isPreRenderMode = z;
        $jacocoInit[784] = true;
        return z;
    }

    static /* synthetic */ IWXRenderListener access$600(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXRenderListener iWXRenderListener = wXSDKInstance.mRenderListener;
        $jacocoInit[785] = true;
        return iWXRenderListener;
    }

    static /* synthetic */ IWXStatisticsListener access$700(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStatisticsListener iWXStatisticsListener = wXSDKInstance.mStatisticsListener;
        $jacocoInit[786] = true;
        return iWXStatisticsListener;
    }

    static /* synthetic */ Map access$800(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, GraphicActionAddElement> map = wXSDKInstance.inactiveAddElementAction;
        $jacocoInit[787] = true;
        return map;
    }

    static /* synthetic */ WXPerformance access$900(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        WXPerformance wXPerformance = wXSDKInstance.mWXPerformance;
        $jacocoInit[788] = true;
        return wXPerformance;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[793] = true;
    }

    public List<String> getLayerOverFlowListeners() {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = this.mLayerOverFlowListeners;
        $jacocoInit[0] = true;
        return list;
    }

    public void addLayerOverFlowListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayerOverFlowListeners != null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            this.mLayerOverFlowListeners = new ArrayList();
            $jacocoInit[3] = true;
        }
        this.mLayerOverFlowListeners.add(str);
        $jacocoInit[4] = true;
    }

    public void removeLayerOverFlowListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayerOverFlowListeners == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            this.mLayerOverFlowListeners.remove(str);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }

    public ImageNetworkHandler getImageNetworkHandler() {
        boolean[] $jacocoInit = $jacocoInit();
        ImageNetworkHandler imageNetworkHandler = this.mImageNetworkHandler;
        $jacocoInit[9] = true;
        return imageNetworkHandler;
    }

    public void setImageNetworkHandler(ImageNetworkHandler imageNetworkHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mImageNetworkHandler = imageNetworkHandler;
        $jacocoInit[10] = true;
    }

    public StreamNetworkHandler getStreamNetworkHandler() {
        boolean[] $jacocoInit = $jacocoInit();
        StreamNetworkHandler streamNetworkHandler = this.mStreamNetworkHandler;
        $jacocoInit[11] = true;
        return streamNetworkHandler;
    }

    public void setStreamNetworkHandler(StreamNetworkHandler streamNetworkHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStreamNetworkHandler = streamNetworkHandler;
        $jacocoInit[12] = true;
    }

    public CustomFontNetworkHandler getCustomFontNetworkHandler() {
        boolean[] $jacocoInit = $jacocoInit();
        CustomFontNetworkHandler customFontNetworkHandler = this.mCustomFontNetworkHandler;
        $jacocoInit[13] = true;
        return customFontNetworkHandler;
    }

    public void setCustomFontNetworkHandler(CustomFontNetworkHandler customFontNetworkHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCustomFontNetworkHandler = customFontNetworkHandler;
        $jacocoInit[14] = true;
    }

    public void setUseSingleProcess(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().setUseSingleProcess(z);
        $jacocoInit[15] = true;
    }

    public void setUseSandBox(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().setSandBoxContext(z);
        $jacocoInit[16] = true;
    }

    public int getMaxHiddenEmbedsNum() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.maxHiddenEmbedsNum;
        $jacocoInit[17] = true;
        return i;
    }

    public void setMaxHiddenEmbedsNum(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.maxHiddenEmbedsNum = i;
        $jacocoInit[18] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void addInActiveAddElementAction(String str, GraphicActionAddElement graphicActionAddElement) {
        boolean[] $jacocoInit = $jacocoInit();
        this.inactiveAddElementAction.put(str, graphicActionAddElement);
        $jacocoInit[19] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void removeInActiveAddElmentAction(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.inactiveAddElementAction.remove(str);
        $jacocoInit[20] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public GraphicActionAddElement getInActiveAddElementAction(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        GraphicActionAddElement graphicActionAddElement = this.inactiveAddElementAction.get(str);
        $jacocoInit[21] = true;
        return graphicActionAddElement;
    }

    public void setRenderContainer(RenderContainer renderContainer) {
        boolean[] $jacocoInit = $jacocoInit();
        if (renderContainer == null) {
            $jacocoInit[22] = true;
        } else {
            $jacocoInit[23] = true;
            renderContainer.setSDKInstance(this);
            $jacocoInit[24] = true;
            renderContainer.addOnLayoutChangeListener(this);
            $jacocoInit[25] = true;
        }
        this.mRenderContainer = renderContainer;
        $jacocoInit[26] = true;
        if (this.mRenderContainer == null) {
            $jacocoInit[27] = true;
        } else if (this.mRenderContainer.getLayoutParams() == null) {
            $jacocoInit[28] = true;
        } else {
            RenderContainer renderContainer2 = this.mRenderContainer;
            $jacocoInit[29] = true;
            if (renderContainer2.getLayoutParams().width != -2) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                WXBridgeManager.getInstance().post(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXSDKInstance this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-5937126052413177991L, "com/taobao/weex/WXSDKInstance$1", 2);
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
                        WXBridgeManager.getInstance().setRenderContentWrapContentToCore(true, this.this$0.getInstanceId());
                        $jacocoInit[1] = true;
                    }
                });
                $jacocoInit[32] = true;
                $jacocoInit[34] = true;
            }
        }
        WXBridgeManager.getInstance().post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSDKInstance this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-3418959387479797012L, "com/taobao/weex/WXSDKInstance$2", 2);
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
                WXBridgeManager.getInstance().setRenderContentWrapContentToCore(false, this.this$0.getInstanceId());
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[33] = true;
        $jacocoInit[34] = true;
    }

    public boolean isTrackComponent() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.trackComponent;
        $jacocoInit[35] = true;
        return z;
    }

    public void setTrackComponent(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.trackComponent = z;
        $jacocoInit[36] = true;
    }

    public boolean isLayerTypeEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.enableLayerType;
        $jacocoInit[37] = true;
        return z;
    }

    public void enableLayerType(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.enableLayerType = z;
        $jacocoInit[38] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @NonNull
    public FlatGUIContext getFlatUIContext() {
        boolean[] $jacocoInit = $jacocoInit();
        FlatGUIContext flatGUIContext = this.mFlatGUIContext;
        $jacocoInit[39] = true;
        return flatGUIContext;
    }

    public boolean isNeedValidate() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mNeedValidate;
        $jacocoInit[40] = true;
        return z;
    }

    public boolean isNeedReLoad() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mNeedReLoad;
        $jacocoInit[41] = true;
        return z;
    }

    public void setNeedLoad(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNeedReLoad = z;
        $jacocoInit[42] = true;
    }

    public boolean isUseScroller() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mUseScroller;
        $jacocoInit[43] = true;
        return z;
    }

    public void setUseScroller(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mUseScroller = z;
        $jacocoInit[44] = true;
    }

    public void setInstanceViewPortWidth(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstanceViewPortWidth = i;
        $jacocoInit[45] = true;
    }

    public int getInstanceViewPortWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mInstanceViewPortWidth;
        $jacocoInit[46] = true;
        return i;
    }

    public WXSDKInstance(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[47] = true;
        this.mFlatGUIContext = new FlatGUIContext();
        this.isNewFsEnd = false;
        $jacocoInit[48] = true;
        this.mExecJSTraceId = WXTracing.nextId();
        this.isViewDisAppear = false;
        this.mwxDims = new String[5];
        this.measureTimes = new long[5];
        $jacocoInit[49] = true;
        this.responseHeaders = new HashMap();
        this.mRenderStrategy = WXRenderStrategy.APPEND_ASYNC;
        this.mCurrentGround = false;
        $jacocoInit[50] = true;
        this.inactiveAddElementAction = new ArrayMap();
        $jacocoInit[51] = true;
        this.mContentBoxMeasurements = new ArrayMap();
        this.maxHiddenEmbedsNum = -1;
        $jacocoInit[52] = true;
        this.mVisibleListeners = new ArrayList();
        this.mCreateInstance = true;
        $jacocoInit[53] = true;
        this.mGlobalEvents = new HashMap<>();
        $jacocoInit[54] = true;
        this.mInstanceId = WXSDKManager.getInstance().generateInstanceId();
        $jacocoInit[55] = true;
        init(context);
        $jacocoInit[56] = true;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    WXSDKInstance(Context context, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[57] = true;
        this.mFlatGUIContext = new FlatGUIContext();
        this.isNewFsEnd = false;
        $jacocoInit[58] = true;
        this.mExecJSTraceId = WXTracing.nextId();
        this.isViewDisAppear = false;
        this.mwxDims = new String[5];
        this.measureTimes = new long[5];
        $jacocoInit[59] = true;
        this.responseHeaders = new HashMap();
        this.mRenderStrategy = WXRenderStrategy.APPEND_ASYNC;
        this.mCurrentGround = false;
        $jacocoInit[60] = true;
        this.inactiveAddElementAction = new ArrayMap();
        $jacocoInit[61] = true;
        this.mContentBoxMeasurements = new ArrayMap();
        this.maxHiddenEmbedsNum = -1;
        $jacocoInit[62] = true;
        this.mVisibleListeners = new ArrayList();
        this.mCreateInstance = true;
        $jacocoInit[63] = true;
        this.mGlobalEvents = new HashMap<>();
        this.mInstanceId = str;
        $jacocoInit[64] = true;
        init(context);
        $jacocoInit[65] = true;
    }

    public WXComponent getRootComponent() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = this.mRootComp;
        $jacocoInit[66] = true;
        return wXComponent;
    }

    public void setNestedInstanceInterceptor(NestedInstanceInterceptor nestedInstanceInterceptor) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNestedInstanceInterceptor = nestedInstanceInterceptor;
        $jacocoInit[67] = true;
    }

    public final WXSDKInstance createNestedInstance(NestedContainer nestedContainer) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance newNestedInstance = newNestedInstance();
        if (this.mNestedInstanceInterceptor == null) {
            $jacocoInit[68] = true;
        } else {
            $jacocoInit[69] = true;
            this.mNestedInstanceInterceptor.onCreateNestInstance(newNestedInstance, nestedContainer);
            $jacocoInit[70] = true;
        }
        if (newNestedInstance == null) {
            $jacocoInit[71] = true;
        } else {
            $jacocoInit[72] = true;
            newNestedInstance.setComponentObserver(getComponentObserver());
            $jacocoInit[73] = true;
        }
        $jacocoInit[74] = true;
        return newNestedInstance;
    }

    /* access modifiers changed from: protected */
    public WXSDKInstance newNestedInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = new WXSDKInstance(this.mContext);
        $jacocoInit[75] = true;
        return wXSDKInstance;
    }

    public boolean isHasException() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.hasException;
        $jacocoInit[76] = true;
        return z;
    }

    public void setHasException(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.hasException = z;
        $jacocoInit[77] = true;
    }

    public void addOnInstanceVisibleListener(OnInstanceVisibleListener onInstanceVisibleListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mVisibleListeners.add(onInstanceVisibleListener);
        $jacocoInit[78] = true;
    }

    public void removeOnInstanceVisibleListener(OnInstanceVisibleListener onInstanceVisibleListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mVisibleListeners.remove(onInstanceVisibleListener);
        $jacocoInit[79] = true;
    }

    public void init(Context context) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        this.mContext = context;
        $jacocoInit[80] = true;
        this.mContainerInfo = new HashMap(4);
        $jacocoInit[81] = true;
        this.mNativeInvokeHelper = new NativeInvokeHelper(this.mInstanceId);
        $jacocoInit[82] = true;
        this.mWXPerformance = new WXPerformance(this.mInstanceId);
        $jacocoInit[83] = true;
        this.mApmForInstance = new WXInstanceApm(this.mInstanceId);
        $jacocoInit[84] = true;
        this.mExceptionRecorder = new WXInstanceExceptionRecord(this.mInstanceId);
        this.mWXPerformance.WXSDKVersion = WXEnvironment.WXSDK_VERSION;
        this.mWXPerformance.JSLibInitTime = WXEnvironment.sJSLibInitTime;
        $jacocoInit[85] = true;
        this.mUserTrackAdapter = WXSDKManager.getInstance().getIWXUserTrackAdapter();
        $jacocoInit[86] = true;
        WXSDKManager.getInstance().getAllInstanceMap().put(this.mInstanceId, this);
        Map<String, String> map = this.mContainerInfo;
        if (context instanceof Activity) {
            $jacocoInit[87] = true;
            str = context.getClass().getSimpleName();
            $jacocoInit[88] = true;
        } else {
            str = "unKnowContainer";
            $jacocoInit[89] = true;
        }
        map.put(WXInstanceApm.KEY_PAGE_PROPERTIES_CONTAINER_NAME, str);
        $jacocoInit[90] = true;
        this.mContainerInfo.put(WXInstanceApm.KEY_PAGE_PROPERTIES_INSTANCE_TYPE, "page");
        $jacocoInit[91] = true;
    }

    public void setComponentObserver(ComponentObserver componentObserver) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponentObserver = componentObserver;
        $jacocoInit[92] = true;
    }

    public ComponentObserver getComponentObserver() {
        boolean[] $jacocoInit = $jacocoInit();
        ComponentObserver componentObserver = this.mComponentObserver;
        $jacocoInit[93] = true;
        return componentObserver;
    }

    public NativeInvokeHelper getNativeInvokeHelper() {
        boolean[] $jacocoInit = $jacocoInit();
        NativeInvokeHelper nativeInvokeHelper = this.mNativeInvokeHelper;
        $jacocoInit[94] = true;
        return nativeInvokeHelper;
    }

    @Deprecated
    public void setBizType(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[95] = true;
        } else {
            this.mWXPerformance.bizType = str;
            $jacocoInit[96] = true;
        }
        $jacocoInit[97] = true;
    }

    public ScrollView getScrollView() {
        boolean[] $jacocoInit = $jacocoInit();
        ScrollView scrollView = this.mScrollView;
        $jacocoInit[98] = true;
        return scrollView;
    }

    public void setRootScrollView(ScrollView scrollView) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollView = scrollView;
        if (this.mWXScrollViewListener == null) {
            $jacocoInit[99] = true;
        } else if (!(this.mScrollView instanceof WXScrollView)) {
            $jacocoInit[100] = true;
        } else {
            $jacocoInit[101] = true;
            ((WXScrollView) this.mScrollView).addScrollViewListener(this.mWXScrollViewListener);
            $jacocoInit[102] = true;
        }
        $jacocoInit[103] = true;
    }

    @Deprecated
    public void registerScrollViewListener(WXScrollView.WXScrollViewListener wXScrollViewListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXScrollViewListener = wXScrollViewListener;
        $jacocoInit[104] = true;
    }

    @Deprecated
    public WXScrollView.WXScrollViewListener getScrollViewListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXScrollView.WXScrollViewListener wXScrollViewListener = this.mWXScrollViewListener;
        $jacocoInit[105] = true;
        return wXScrollViewListener;
    }

    @Deprecated
    public void setIWXUserTrackAdapter(IWXUserTrackAdapter iWXUserTrackAdapter) {
        $jacocoInit()[106] = true;
    }

    public void setContainerInfo(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mContainerInfo.put(str, str2);
        $jacocoInit[107] = true;
    }

    public Map<String, String> getContainerInfo() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = this.mContainerInfo;
        $jacocoInit[108] = true;
        return map;
    }

    public void render(String str, Map<String, Object> map, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        render(str, map, str2, WXRenderStrategy.APPEND_ASYNC);
        $jacocoInit[109] = true;
    }

    @Deprecated
    public void render(String str, Map<String, Object> map, String str2, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        render("default", str, map, str2, wXRenderStrategy);
        $jacocoInit[110] = true;
    }

    public void render(String str, String str2, Map<String, Object> map, String str3, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        render(str, new Script(str2), map, str3, wXRenderStrategy);
        $jacocoInit[111] = true;
    }

    public void render(String str, Script script, Map<String, Object> map, String str2, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXPerformance.beforeInstanceRender(this.mInstanceId);
        $jacocoInit[112] = true;
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[113] = true;
        } else if (!"default".equals(str)) {
            $jacocoInit[114] = true;
        } else {
            $jacocoInit[115] = true;
            if (getUIContext() == null) {
                $jacocoInit[116] = true;
            } else {
                $jacocoInit[117] = true;
                AlertDialog.Builder builder = new AlertDialog.Builder(getUIContext());
                $jacocoInit[118] = true;
                AlertDialog.Builder title = builder.setTitle("Error: Missing pageName");
                $jacocoInit[119] = true;
                AlertDialog.Builder message = title.setMessage("We highly recommend you to set pageName. Call\nWXSDKInstance#render(String pageName, String template, Map<String, Object> options, String jsonInitData, WXRenderStrategy flag)\nto fix it.");
                $jacocoInit[120] = true;
                message.show();
                $jacocoInit[121] = true;
            }
            $jacocoInit[122] = true;
            return;
        }
        renderInternal(str, script, map, str2, wXRenderStrategy);
        $jacocoInit[123] = true;
    }

    public void render(String str, byte[] bArr, Map<String, Object> map, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        render(str, new Script(bArr), map, str2, WXRenderStrategy.DATA_RENDER_BINARY);
        $jacocoInit[124] = true;
    }

    private void ensureRenderArchor() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer != null) {
            $jacocoInit[125] = true;
        } else {
            $jacocoInit[126] = true;
            if (getContext() == null) {
                $jacocoInit[127] = true;
            } else {
                $jacocoInit[128] = true;
                setRenderContainer(new RenderContainer(getContext()));
                $jacocoInit[129] = true;
                this.mRenderContainer.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                $jacocoInit[130] = true;
                this.mRenderContainer.setBackgroundColor(0);
                $jacocoInit[131] = true;
                this.mRenderContainer.setSDKInstance(this);
                $jacocoInit[132] = true;
                this.mRenderContainer.addOnLayoutChangeListener(this);
                $jacocoInit[133] = true;
            }
        }
        $jacocoInit[134] = true;
    }

    private void renderInternal(String str, String str2, Map<String, Object> map, String str3, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRendered) {
            $jacocoInit[135] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[136] = true;
        } else {
            renderInternal(str, new Script(str2), map, str3, wXRenderStrategy);
            $jacocoInit[138] = true;
            return;
        }
        $jacocoInit[137] = true;
    }

    private void renderInternal(String str, Script script, Map<String, Object> map, String str2, WXRenderStrategy wXRenderStrategy) {
        String str3;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRendered) {
            $jacocoInit[139] = true;
        } else if (script == null) {
            $jacocoInit[140] = true;
        } else if (script.isEmpty()) {
            $jacocoInit[141] = true;
        } else {
            this.mRenderStrategy = wXRenderStrategy;
            $jacocoInit[143] = true;
            if (this.mApmForInstance.hasInit()) {
                $jacocoInit[144] = true;
            } else {
                $jacocoInit[145] = true;
                this.mApmForInstance.doInit();
                $jacocoInit[146] = true;
            }
            this.mApmForInstance.setPageName(str);
            $jacocoInit[147] = true;
            this.mApmForInstance.onStage(WXInstanceApm.KEY_PAGE_STAGES_RENDER_ORGIGIN);
            $jacocoInit[148] = true;
            WXPerformance wXPerformance = this.mWXPerformance;
            if (TextUtils.isEmpty(str)) {
                str3 = "defaultBundleUrl";
                $jacocoInit[149] = true;
            } else {
                $jacocoInit[150] = true;
                str3 = str;
            }
            wXPerformance.pageName = str3;
            $jacocoInit[151] = true;
            if (!TextUtils.isEmpty(this.mBundleUrl)) {
                $jacocoInit[152] = true;
            } else {
                this.mBundleUrl = this.mWXPerformance.pageName;
                $jacocoInit[153] = true;
            }
            if (!WXTracing.isAvailable()) {
                $jacocoInit[154] = true;
            } else {
                $jacocoInit[155] = true;
                WXTracing.TraceEvent newEvent = WXTracing.newEvent("executeBundleJS", this.mInstanceId, -1);
                newEvent.traceId = this.mExecJSTraceId;
                newEvent.iid = this.mInstanceId;
                newEvent.tname = "JSThread";
                newEvent.ph = Field.b;
                $jacocoInit[156] = true;
                newEvent.submit();
                $jacocoInit[157] = true;
                this.mRenderStartNanos = System.nanoTime();
                $jacocoInit[158] = true;
            }
            ensureRenderArchor();
            if (map != null) {
                $jacocoInit[159] = true;
            } else {
                $jacocoInit[160] = true;
                map = new HashMap<>();
                $jacocoInit[161] = true;
            }
            Map<String, Object> map2 = map;
            if (!WXEnvironment.sDynamicMode) {
                $jacocoInit[162] = true;
            } else if (TextUtils.isEmpty(WXEnvironment.sDynamicUrl)) {
                $jacocoInit[163] = true;
            } else if (map2.get("dynamicMode") != null) {
                $jacocoInit[164] = true;
            } else {
                $jacocoInit[165] = true;
                map2.put("dynamicMode", "true");
                $jacocoInit[166] = true;
                renderByUrl(str, WXEnvironment.sDynamicUrl, map2, str2, wXRenderStrategy);
                $jacocoInit[167] = true;
                return;
            }
            this.mWXPerformance.JSTemplateSize = (double) (((float) script.length()) / 1024.0f);
            $jacocoInit[168] = true;
            this.mApmForInstance.addStats(WXInstanceApm.KEY_PAGE_STATS_BUNDLE_SIZE, this.mWXPerformance.JSTemplateSize);
            $jacocoInit[169] = true;
            this.mRenderStartTime = System.currentTimeMillis();
            $jacocoInit[170] = true;
            WXSDKManager.getInstance().setCrashInfo(WXEnvironment.WEEX_CURRENT_KEY, str);
            $jacocoInit[171] = true;
            WXSDKManager.getInstance().createInstance(this, script, map2, str2);
            this.mRendered = true;
            $jacocoInit[172] = true;
            final IWXJscProcessManager wXJscProcessManager = WXSDKManager.getInstance().getWXJscProcessManager();
            $jacocoInit[173] = true;
            if (wXJscProcessManager == null) {
                $jacocoInit[174] = true;
            } else if (!wXJscProcessManager.shouldReboot()) {
                $jacocoInit[175] = true;
            } else {
                $jacocoInit[176] = true;
                WXSDKManager instance = WXSDKManager.getInstance();
                AnonymousClass3 r12 = new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXSDKInstance this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-8984993925713110987L, "com/taobao/weex/WXSDKInstance$3", 14);
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
                        if (WXSDKInstance.access$000(this.this$0)) {
                            $jacocoInit[1] = true;
                        } else if (WXSDKInstance.access$100(this.this$0)) {
                            $jacocoInit[2] = true;
                        } else if (WXSDKInstance.access$200(this.this$0)) {
                            $jacocoInit[3] = true;
                        } else {
                            View containerView = this.this$0.getContainerView();
                            if (!(containerView instanceof ViewGroup)) {
                                $jacocoInit[5] = true;
                            } else {
                                $jacocoInit[6] = true;
                                if (((ViewGroup) containerView).getChildCount() != 0) {
                                    $jacocoInit[7] = true;
                                } else {
                                    $jacocoInit[8] = true;
                                    if (!wXJscProcessManager.withException(this.this$0)) {
                                        $jacocoInit[9] = true;
                                    } else {
                                        $jacocoInit[10] = true;
                                        this.this$0.onJSException(String.valueOf(WXErrorCode.WX_ERR_RELOAD_PAGE), "jsc reboot", "jsc reboot");
                                        $jacocoInit[11] = true;
                                    }
                                    WXBridgeManager.getInstance().callReportCrashReloadPage(WXSDKInstance.access$300(this.this$0), (String) null);
                                    $jacocoInit[12] = true;
                                }
                            }
                            $jacocoInit[13] = true;
                            return;
                        }
                        $jacocoInit[4] = true;
                    }
                };
                $jacocoInit[177] = true;
                long rebootTimeout = wXJscProcessManager.rebootTimeout();
                $jacocoInit[178] = true;
                instance.postOnUiThread(r12, rebootTimeout);
                $jacocoInit[179] = true;
            }
            $jacocoInit[180] = true;
            return;
        }
        $jacocoInit[142] = true;
    }

    private void renderByUrlInternal(String str, String str2, Map<String, Object> map, String str3, WXRenderStrategy wXRenderStrategy) {
        HashMap hashMap;
        String str4 = str2;
        boolean[] $jacocoInit = $jacocoInit();
        ensureRenderArchor();
        $jacocoInit[181] = true;
        String wrapPageName = wrapPageName(str, str2);
        this.mBundleUrl = str4;
        WXRenderStrategy wXRenderStrategy2 = wXRenderStrategy;
        this.mRenderStrategy = wXRenderStrategy2;
        $jacocoInit[182] = true;
        if (WXSDKManager.getInstance().getValidateProcessor() == null) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            this.mNeedValidate = WXSDKManager.getInstance().getValidateProcessor().needValidate(this.mBundleUrl);
            $jacocoInit[185] = true;
        }
        if (map != null) {
            $jacocoInit[186] = true;
            hashMap = map;
        } else {
            $jacocoInit[187] = true;
            HashMap hashMap2 = new HashMap();
            $jacocoInit[188] = true;
            hashMap = hashMap2;
        }
        if (hashMap.containsKey("bundleUrl")) {
            $jacocoInit[189] = true;
        } else {
            $jacocoInit[190] = true;
            hashMap.put("bundleUrl", str4);
            $jacocoInit[191] = true;
        }
        getWXPerformance().pageName = wrapPageName;
        $jacocoInit[192] = true;
        this.mApmForInstance.doInit();
        $jacocoInit[193] = true;
        this.mApmForInstance.setPageName(wrapPageName);
        $jacocoInit[194] = true;
        Uri parse = Uri.parse(str2);
        $jacocoInit[195] = true;
        if (parse == null) {
            $jacocoInit[196] = true;
        } else if (!TextUtils.equals(parse.getScheme(), "file")) {
            $jacocoInit[197] = true;
        } else {
            $jacocoInit[198] = true;
            this.mApmForInstance.onStage(WXInstanceApm.KEY_PAGE_STAGES_DOWN_BUNDLE_START);
            $jacocoInit[199] = true;
            String loadFileOrAsset = WXFileUtils.loadFileOrAsset(assembleFilePath(parse), this.mContext);
            $jacocoInit[200] = true;
            this.mApmForInstance.onStage(WXInstanceApm.KEY_PAGE_STAGES_DOWN_BUNDLE_END);
            $jacocoInit[201] = true;
            render(wrapPageName, loadFileOrAsset, hashMap, str3, wXRenderStrategy);
            $jacocoInit[202] = true;
            return;
        }
        boolean z = false;
        $jacocoInit[203] = true;
        if (parse == null) {
            $jacocoInit[204] = true;
        } else if (parse.getPath() == null) {
            $jacocoInit[205] = true;
        } else {
            $jacocoInit[206] = true;
            if (!parse.getPath().endsWith(".wlasm")) {
                $jacocoInit[207] = true;
            } else {
                $jacocoInit[208] = true;
                z = true;
            }
        }
        if (!z) {
            $jacocoInit[209] = true;
        } else {
            WXRenderStrategy wXRenderStrategy3 = WXRenderStrategy.DATA_RENDER_BINARY;
            $jacocoInit[210] = true;
            wXRenderStrategy2 = wXRenderStrategy3;
        }
        IWXHttpAdapter iWXHttpAdapter = WXSDKManager.getInstance().getIWXHttpAdapter();
        $jacocoInit[211] = true;
        WXRequest wXRequest = new WXRequest();
        $jacocoInit[212] = true;
        wXRequest.url = rewriteUri(Uri.parse(str2), URIAdapter.BUNDLE).toString();
        $jacocoInit[213] = true;
        if (TextUtils.isEmpty(wXRequest.url)) {
            $jacocoInit[215] = true;
            requestUrl = wrapPageName;
            $jacocoInit[217] = true;
        } else {
            requestUrl = wXRequest.url;
            $jacocoInit[216] = true;
        }
        if (wXRequest.paramMap != null) {
            $jacocoInit[218] = true;
        } else {
            $jacocoInit[219] = true;
            wXRequest.paramMap = new HashMap();
            $jacocoInit[220] = true;
        }
        wXRequest.instanceId = getInstanceId();
        $jacocoInit[221] = true;
        wXRequest.paramMap.put(WXHttpUtil.KEY_USER_AGENT, WXHttpUtil.assembleUserAgent(this.mContext, WXEnvironment.getConfig()));
        $jacocoInit[222] = true;
        wXRequest.paramMap.put("isBundleRequest", "true");
        $jacocoInit[223] = true;
        WXHttpListener wXHttpListener = new WXHttpListener(this, wrapPageName, hashMap, str3, wXRenderStrategy2, System.currentTimeMillis(), (AnonymousClass1) null);
        $jacocoInit[224] = true;
        wXHttpListener.setSDKInstance(this);
        $jacocoInit[225] = true;
        this.mApmForInstance.onStage(WXInstanceApm.KEY_PAGE_STAGES_DOWN_BUNDLE_START);
        $jacocoInit[226] = true;
        iWXHttpAdapter.sendRequest(wXRequest, wXHttpListener);
        $jacocoInit[227] = true;
    }

    @Deprecated
    public void render(String str, String str2, Map<String, Object> map, String str3, int i, int i2, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        render(str, str2, map, str3, wXRenderStrategy);
        $jacocoInit[228] = true;
    }

    public void render(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        render("default", str, (Map<String, Object>) null, (String) null, this.mRenderStrategy);
        $jacocoInit[229] = true;
    }

    @Deprecated
    public void render(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        render(str);
        $jacocoInit[230] = true;
    }

    @Deprecated
    public void renderByUrl(String str, String str2, Map<String, Object> map, String str3, int i, int i2, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        renderByUrl(str, str2, map, str3, wXRenderStrategy);
        $jacocoInit[231] = true;
    }

    public void renderByUrl(String str, String str2, Map<String, Object> map, String str3, WXRenderStrategy wXRenderStrategy) {
        boolean[] $jacocoInit = $jacocoInit();
        renderByUrlInternal(str, str2, map, str3, wXRenderStrategy);
        $jacocoInit[232] = true;
    }

    private String wrapPageName(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.equals(str, "default")) {
            $jacocoInit[233] = true;
        } else {
            WXExceptionUtils.degradeUrl = str2;
            try {
                $jacocoInit[234] = true;
                Uri parse = Uri.parse(str2);
                if (parse == null) {
                    $jacocoInit[235] = true;
                    str = str2;
                } else {
                    $jacocoInit[236] = true;
                    Uri.Builder builder = new Uri.Builder();
                    $jacocoInit[237] = true;
                    builder.scheme(parse.getScheme());
                    $jacocoInit[238] = true;
                    builder.authority(parse.getAuthority());
                    $jacocoInit[239] = true;
                    builder.path(parse.getPath());
                    $jacocoInit[240] = true;
                    str = builder.toString();
                    try {
                        $jacocoInit[241] = true;
                    } catch (Exception unused) {
                        $jacocoInit[243] = true;
                        $jacocoInit[244] = true;
                        return str;
                    }
                }
                $jacocoInit[242] = true;
            } catch (Exception unused2) {
                str = str2;
                $jacocoInit[243] = true;
                $jacocoInit[244] = true;
                return str;
            }
        }
        $jacocoInit[244] = true;
        return str;
    }

    private String assembleFilePath(Uri uri) {
        boolean[] $jacocoInit = $jacocoInit();
        if (uri == null) {
            $jacocoInit[245] = true;
        } else if (uri.getPath() == null) {
            $jacocoInit[246] = true;
        } else {
            $jacocoInit[247] = true;
            String replaceFirst = uri.getPath().replaceFirst("/", "");
            $jacocoInit[248] = true;
            return replaceFirst;
        }
        $jacocoInit[249] = true;
        return "";
    }

    public void reloadPage(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKEngine.reload();
        if (!z) {
            $jacocoInit[250] = true;
        } else if (this.mContext == null) {
            $jacocoInit[251] = true;
        } else {
            $jacocoInit[252] = true;
            Intent intent = new Intent();
            $jacocoInit[253] = true;
            intent.setAction(ACTION_INSTANCE_RELOAD);
            $jacocoInit[254] = true;
            intent.putExtra("url", this.mBundleUrl);
            $jacocoInit[255] = true;
            this.mContext.sendBroadcast(intent);
            $jacocoInit[256] = true;
        }
        $jacocoInit[257] = true;
    }

    public void refreshInstance(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[258] = true;
            return;
        }
        refreshInstance(WXJsonUtils.fromObjectToJSONString(map));
        $jacocoInit[259] = true;
    }

    public void refreshInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[260] = true;
            return;
        }
        this.mRefreshStartTime = System.currentTimeMillis();
        if (this.mLastRefreshData == null) {
            $jacocoInit[261] = true;
        } else {
            this.mLastRefreshData.isDirty = true;
            $jacocoInit[262] = true;
        }
        this.mLastRefreshData = new WXRefreshData(str, false);
        $jacocoInit[263] = true;
        WXSDKManager.getInstance().refreshInstance(this.mInstanceId, this.mLastRefreshData);
        $jacocoInit[264] = true;
    }

    public WXRenderStrategy getRenderStrategy() {
        boolean[] $jacocoInit = $jacocoInit();
        WXRenderStrategy wXRenderStrategy = this.mRenderStrategy;
        $jacocoInit[265] = true;
        return wXRenderStrategy;
    }

    public Context getUIContext() {
        boolean[] $jacocoInit = $jacocoInit();
        Context context = this.mContext;
        $jacocoInit[266] = true;
        return context;
    }

    public String getInstanceId() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mInstanceId;
        $jacocoInit[267] = true;
        return str;
    }

    public Context getContext() {
        boolean[] $jacocoInit = $jacocoInit();
        Context context = this.mContext;
        $jacocoInit[268] = true;
        return context;
    }

    public int getWeexHeight() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer == null) {
            i = 0;
            $jacocoInit[269] = true;
        } else {
            i = this.mRenderContainer.getHeight();
            $jacocoInit[270] = true;
        }
        $jacocoInit[271] = true;
        return i;
    }

    public int getWeexWidth() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer == null) {
            i = 0;
            $jacocoInit[272] = true;
        } else {
            i = this.mRenderContainer.getWidth();
            $jacocoInit[273] = true;
        }
        $jacocoInit[274] = true;
        return i;
    }

    public IWXImgLoaderAdapter getImgLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXImgLoaderAdapter iWXImgLoaderAdapter = WXSDKManager.getInstance().getIWXImgLoaderAdapter();
        $jacocoInit[275] = true;
        return iWXImgLoaderAdapter;
    }

    public IDrawableLoader getDrawableLoader() {
        boolean[] $jacocoInit = $jacocoInit();
        IDrawableLoader drawableLoader = WXSDKManager.getInstance().getDrawableLoader();
        $jacocoInit[276] = true;
        return drawableLoader;
    }

    public URIAdapter getURIAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        URIAdapter uRIAdapter = WXSDKManager.getInstance().getURIAdapter();
        $jacocoInit[277] = true;
        return uRIAdapter;
    }

    public Uri rewriteUri(Uri uri, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Uri rewrite = getURIAdapter().rewrite(this, str, uri);
        $jacocoInit[278] = true;
        return rewrite;
    }

    public IWXHttpAdapter getWXHttpAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXHttpAdapter iWXHttpAdapter = WXSDKManager.getInstance().getIWXHttpAdapter();
        $jacocoInit[279] = true;
        return iWXHttpAdapter;
    }

    public IWXStatisticsListener getWXStatisticsListener() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStatisticsListener iWXStatisticsListener = this.mStatisticsListener;
        $jacocoInit[280] = true;
        return iWXStatisticsListener;
    }

    @Nullable
    public IWebSocketAdapter getWXWebSocketAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWebSocketAdapter iWXWebSocketAdapter = WXSDKManager.getInstance().getIWXWebSocketAdapter();
        $jacocoInit[281] = true;
        return iWXWebSocketAdapter;
    }

    @Deprecated
    public void reloadImages() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollView == null) {
            $jacocoInit[282] = true;
        } else {
            $jacocoInit[283] = true;
        }
    }

    public boolean isPreRenderMode() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isPreRenderMode;
        $jacocoInit[284] = true;
        return z;
    }

    public void setPreRenderMode(final boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager.getInstance().getWXRenderManager().postOnUiThread((Runnable) new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSDKInstance this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8081144205243082959L, "com/taobao/weex/WXSDKInstance$4", 2);
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
                WXSDKInstance.access$502(this.this$0, z);
                $jacocoInit[1] = true;
            }
        }, 0);
        $jacocoInit[285] = true;
    }

    public void setContext(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mContext = context;
        $jacocoInit[286] = true;
    }

    public void registerRenderListener(IWXRenderListener iWXRenderListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRenderListener = iWXRenderListener;
        $jacocoInit[287] = true;
    }

    @Deprecated
    public void registerActivityStateListener(IWXActivityStateListener iWXActivityStateListener) {
        $jacocoInit()[288] = true;
    }

    public void registerStatisticsListener(IWXStatisticsListener iWXStatisticsListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStatisticsListener = iWXStatisticsListener;
        $jacocoInit[289] = true;
    }

    public void setRenderStartTime(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRenderStartTime = j;
        $jacocoInit[290] = true;
    }

    public void onActivityCreate() {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityCreate(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[291] = true;
            this.mRootComp.onActivityCreate();
            $jacocoInit[292] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[293] = true;
        } else {
            $jacocoInit[294] = true;
            WXLogUtils.w("Warning :Component tree has not build completely,onActivityCreate can not be call!");
            $jacocoInit[295] = true;
        }
        this.mGlobalEventReceiver = new WXGlobalEventReceiver(this);
        try {
            $jacocoInit[296] = true;
            getContext().registerReceiver(this.mGlobalEventReceiver, new IntentFilter(WXGlobalEventReceiver.EVENT_ACTION));
            $jacocoInit[297] = true;
        } catch (Throwable th) {
            $jacocoInit[298] = true;
            WXLogUtils.e(th.getMessage());
            this.mGlobalEventReceiver = null;
            $jacocoInit[299] = true;
        }
        $jacocoInit[300] = true;
    }

    public void onActivityStart() {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityStart(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[301] = true;
            this.mRootComp.onActivityStart();
            $jacocoInit[302] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[303] = true;
        } else {
            $jacocoInit[304] = true;
            WXLogUtils.w("Warning :Component tree has not build completely,onActivityStart can not be call!");
            $jacocoInit[305] = true;
        }
        $jacocoInit[306] = true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onCreateOptionsMenu(getInstanceId(), menu);
        if (this.mRootComp != null) {
            $jacocoInit[307] = true;
            this.mRootComp.onCreateOptionsMenu(menu);
            $jacocoInit[308] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[309] = true;
        } else {
            $jacocoInit[310] = true;
            WXLogUtils.w("Warning :Component tree has not build completely,onActivityStart can not be call!");
            $jacocoInit[311] = true;
        }
        $jacocoInit[312] = true;
        return true;
    }

    public void onActivityPause() {
        boolean[] $jacocoInit = $jacocoInit();
        onViewDisappear();
        if (this.isCommit) {
            $jacocoInit[313] = true;
        } else {
            if (!this.mUseScroller) {
                $jacocoInit[314] = true;
            } else {
                this.mWXPerformance.useScroller = 1;
                $jacocoInit[315] = true;
            }
            this.mWXPerformance.maxDeepViewLayer = getMaxDeepLayer();
            this.mWXPerformance.wxDims = this.mwxDims;
            this.mWXPerformance.measureTimes = this.measureTimes;
            if (this.mUserTrackAdapter == null) {
                $jacocoInit[316] = true;
            } else {
                $jacocoInit[317] = true;
                this.mUserTrackAdapter.commit(this.mContext, (String) null, "load", this.mWXPerformance, getUserTrackParams());
                $jacocoInit[318] = true;
            }
            this.isCommit = true;
            $jacocoInit[319] = true;
        }
        WXModuleManager.onActivityPause(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[320] = true;
            this.mRootComp.onActivityPause();
            $jacocoInit[321] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[322] = true;
        } else {
            $jacocoInit[323] = true;
            WXLogUtils.w("Warning :Component tree has not build completely,onActivityPause can not be call!");
            $jacocoInit[324] = true;
        }
        if (this.mCurrentGround) {
            $jacocoInit[325] = true;
        } else {
            $jacocoInit[326] = true;
            WXLogUtils.i("Application to be in the backround");
            $jacocoInit[327] = true;
            Intent intent = new Intent(WXGlobalEventReceiver.EVENT_ACTION);
            $jacocoInit[328] = true;
            intent.putExtra(WXGlobalEventReceiver.EVENT_NAME, Constants.Event.PAUSE_EVENT);
            $jacocoInit[329] = true;
            intent.putExtra(WXGlobalEventReceiver.EVENT_WX_INSTANCEID, getInstanceId());
            if (this.mContext != null) {
                $jacocoInit[330] = true;
                this.mContext.sendBroadcast(intent);
                $jacocoInit[331] = true;
            } else {
                WXEnvironment.getApplication().sendBroadcast(intent);
                $jacocoInit[332] = true;
            }
            this.mCurrentGround = true;
            $jacocoInit[333] = true;
        }
        $jacocoInit[334] = true;
    }

    public void onActivityResume() {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityResume(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[335] = true;
            this.mRootComp.onActivityResume();
            $jacocoInit[336] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[337] = true;
        } else {
            $jacocoInit[338] = true;
            WXLogUtils.w("Warning :Component tree has not build completely, onActivityResume can not be call!");
            $jacocoInit[339] = true;
        }
        if (!this.mCurrentGround) {
            $jacocoInit[340] = true;
        } else {
            $jacocoInit[341] = true;
            WXLogUtils.i("Application  to be in the foreground");
            $jacocoInit[342] = true;
            Intent intent = new Intent(WXGlobalEventReceiver.EVENT_ACTION);
            $jacocoInit[343] = true;
            intent.putExtra(WXGlobalEventReceiver.EVENT_NAME, Constants.Event.RESUME_EVENT);
            $jacocoInit[344] = true;
            intent.putExtra(WXGlobalEventReceiver.EVENT_WX_INSTANCEID, getInstanceId());
            if (this.mContext != null) {
                $jacocoInit[345] = true;
                this.mContext.sendBroadcast(intent);
                $jacocoInit[346] = true;
            } else {
                WXEnvironment.getApplication().sendBroadcast(intent);
                $jacocoInit[347] = true;
            }
            this.mCurrentGround = false;
            $jacocoInit[348] = true;
        }
        onViewAppear();
        $jacocoInit[349] = true;
    }

    public void onActivityStop() {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityStop(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[350] = true;
            this.mRootComp.onActivityStop();
            $jacocoInit[351] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[352] = true;
        } else {
            $jacocoInit[353] = true;
            WXLogUtils.w("Warning :Component tree has not build completely, onActivityStop can not be call!");
            $jacocoInit[354] = true;
        }
        $jacocoInit[355] = true;
    }

    public void onActivityDestroy() {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityDestroy(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[356] = true;
            this.mRootComp.onActivityDestroy();
            $jacocoInit[357] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[358] = true;
        } else {
            $jacocoInit[359] = true;
            WXLogUtils.w("Warning :Component tree has not build completely, onActivityDestroy can not be call!");
            $jacocoInit[360] = true;
        }
        destroy();
        $jacocoInit[361] = true;
    }

    public boolean onActivityBack() {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityBack(getInstanceId());
        if (this.mRootComp != null) {
            $jacocoInit[362] = true;
            boolean onActivityBack = this.mRootComp.onActivityBack();
            $jacocoInit[363] = true;
            return onActivityBack;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[364] = true;
        } else {
            $jacocoInit[365] = true;
            WXLogUtils.w("Warning :Component tree has not build completely, onActivityBack can not be call!");
            $jacocoInit[366] = true;
        }
        $jacocoInit[367] = true;
        return false;
    }

    public boolean onBackPressed() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent rootComponent = getRootComponent();
        if (rootComponent != null) {
            $jacocoInit[368] = true;
            WXEvent events = rootComponent.getEvents();
            $jacocoInit[369] = true;
            if (!events.contains(Constants.Event.NATIVE_BACK)) {
                $jacocoInit[370] = true;
            } else {
                $jacocoInit[371] = true;
                EventResult fireEventWait = rootComponent.fireEventWait(Constants.Event.NATIVE_BACK, (Map<String, Object>) null);
                $jacocoInit[372] = true;
                if (!WXUtils.getBoolean(fireEventWait.getResult(), false).booleanValue()) {
                    $jacocoInit[373] = true;
                } else {
                    $jacocoInit[374] = true;
                    return true;
                }
            }
            boolean contains = events.contains(Constants.Event.CLICKBACKITEM);
            if (!contains) {
                $jacocoInit[375] = true;
            } else {
                $jacocoInit[376] = true;
                fireEvent(rootComponent.getRef(), Constants.Event.CLICKBACKITEM, (Map<String, Object>) null, (Map<String, Object>) null);
                $jacocoInit[377] = true;
            }
            $jacocoInit[378] = true;
            return contains;
        }
        $jacocoInit[379] = true;
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onActivityResult(getInstanceId(), i, i2, intent);
        if (this.mRootComp != null) {
            $jacocoInit[380] = true;
            this.mRootComp.onActivityResult(i, i2, intent);
            $jacocoInit[381] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[382] = true;
        } else {
            $jacocoInit[383] = true;
            WXLogUtils.w("Warning :Component tree has not build completely, onActivityResult can not be call!");
            $jacocoInit[384] = true;
        }
        $jacocoInit[385] = true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        WXModuleManager.onRequestPermissionsResult(getInstanceId(), i, strArr, iArr);
        if (this.mRootComp != null) {
            $jacocoInit[386] = true;
            this.mRootComp.onRequestPermissionsResult(i, strArr, iArr);
            $jacocoInit[387] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[388] = true;
        } else {
            $jacocoInit[389] = true;
            WXLogUtils.w("Warning :Component tree has not build completely, onRequestPermissionsResult can not be call!");
            $jacocoInit[390] = true;
        }
        $jacocoInit[391] = true;
    }

    public void onViewDisappear() {
        boolean[] $jacocoInit = $jacocoInit();
        this.isViewDisAppear = false;
        $jacocoInit[392] = true;
        this.mApmForInstance.onDisAppear();
        $jacocoInit[393] = true;
        WXComponent rootComponent = getRootComponent();
        if (rootComponent == null) {
            $jacocoInit[394] = true;
        } else {
            $jacocoInit[395] = true;
            fireEvent(rootComponent.getRef(), Constants.Event.VIEWDISAPPEAR, (Map<String, Object>) null, (Map<String, Object>) null);
            $jacocoInit[396] = true;
            $jacocoInit[397] = true;
            for (OnInstanceVisibleListener onDisappear : this.mVisibleListeners) {
                $jacocoInit[399] = true;
                onDisappear.onDisappear();
                $jacocoInit[400] = true;
            }
            $jacocoInit[398] = true;
        }
        $jacocoInit[401] = true;
    }

    public boolean isViewDisAppear() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isViewDisAppear;
        $jacocoInit[402] = true;
        return z;
    }

    public void onViewAppear() {
        boolean[] $jacocoInit = $jacocoInit();
        this.isViewDisAppear = true;
        $jacocoInit[403] = true;
        this.mApmForInstance.onAppear();
        $jacocoInit[404] = true;
        WXComponent rootComponent = getRootComponent();
        if (rootComponent == null) {
            $jacocoInit[405] = true;
        } else {
            $jacocoInit[406] = true;
            fireEvent(rootComponent.getRef(), Constants.Event.VIEWAPPEAR, (Map<String, Object>) null, (Map<String, Object>) null);
            $jacocoInit[407] = true;
            $jacocoInit[408] = true;
            for (OnInstanceVisibleListener onAppear : this.mVisibleListeners) {
                $jacocoInit[410] = true;
                onAppear.onAppear();
                $jacocoInit[411] = true;
            }
            $jacocoInit[409] = true;
        }
        $jacocoInit[412] = true;
    }

    public void onCreateFinish() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mContext == null) {
            $jacocoInit[413] = true;
        } else {
            $jacocoInit[414] = true;
            onViewAppear();
            RenderContainer renderContainer = this.mRenderContainer;
            if (this.mRenderListener == null) {
                $jacocoInit[415] = true;
            } else {
                $jacocoInit[416] = true;
                this.mRenderListener.onViewCreated(this, renderContainer);
                $jacocoInit[417] = true;
            }
            if (this.mStatisticsListener == null) {
                $jacocoInit[418] = true;
            } else {
                $jacocoInit[419] = true;
                this.mStatisticsListener.onFirstView();
                $jacocoInit[420] = true;
            }
        }
        $jacocoInit[421] = true;
    }

    public void onUpdateFinish() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[422] = true;
        } else {
            $jacocoInit[423] = true;
            WXLogUtils.d("Instance onUpdateSuccess");
            $jacocoInit[424] = true;
        }
        $jacocoInit[425] = true;
    }

    public void runOnUiThread(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager.getInstance().postOnUiThread(runnable, 0);
        $jacocoInit[426] = true;
    }

    public void onRenderSuccess(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isRenderSuccess = true;
        if (this.isNewFsEnd) {
            $jacocoInit[427] = true;
        } else {
            $jacocoInit[428] = true;
            getApmForInstance().arriveNewFsRenderTime();
            $jacocoInit[429] = true;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.mRenderStartTime;
        $jacocoInit[430] = true;
        long[] renderFinishTime = WXBridgeManager.getInstance().getRenderFinishTime(getInstanceId());
        this.mWXPerformance.callBridgeTime = renderFinishTime[0];
        this.mWXPerformance.cssLayoutTime = renderFinishTime[1];
        this.mWXPerformance.parseJsonTime = renderFinishTime[2];
        this.mWXPerformance.totalTime = (double) currentTimeMillis;
        if (((double) this.mWXPerformance.screenRenderTime) >= 0.001d) {
            $jacocoInit[431] = true;
        } else {
            this.mWXPerformance.screenRenderTime = currentTimeMillis;
            $jacocoInit[432] = true;
        }
        if (this.mRenderListener == null) {
            $jacocoInit[433] = true;
        } else if (this.mContext == null) {
            $jacocoInit[434] = true;
        } else {
            $jacocoInit[435] = true;
            this.mRenderListener.onRenderSuccess(this, i, i2);
            if (this.mUserTrackAdapter == null) {
                $jacocoInit[436] = true;
            } else {
                $jacocoInit[437] = true;
                WXPerformance wXPerformance = new WXPerformance(this.mInstanceId);
                $jacocoInit[438] = true;
                wXPerformance.errCode = WXErrorCode.WX_SUCCESS.getErrorCode();
                $jacocoInit[439] = true;
                wXPerformance.args = getBundleUrl();
                $jacocoInit[440] = true;
                this.mUserTrackAdapter.commit(this.mContext, (String) null, IWXUserTrackAdapter.JS_BRIDGE, wXPerformance, getUserTrackParams());
                $jacocoInit[441] = true;
            }
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[442] = true;
            } else {
                $jacocoInit[443] = true;
                WXLogUtils.d(WXLogUtils.WEEX_PERF_TAG, this.mWXPerformance.toString());
                $jacocoInit[444] = true;
            }
        }
        if (!WXEnvironment.isPerf()) {
            $jacocoInit[445] = true;
        } else {
            $jacocoInit[446] = true;
            WXLogUtils.e(WXLogUtils.WEEX_PERF_TAG, this.mWXPerformance.getPerfData());
            $jacocoInit[447] = true;
        }
        $jacocoInit[448] = true;
    }

    public void onRefreshSuccess(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderListener == null) {
            $jacocoInit[449] = true;
        } else if (this.mContext == null) {
            $jacocoInit[450] = true;
        } else {
            $jacocoInit[451] = true;
            this.mRenderListener.onRefreshSuccess(this, i, i2);
            $jacocoInit[452] = true;
        }
        $jacocoInit[453] = true;
    }

    public void onChangeElement(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isDestroy()) {
            $jacocoInit[454] = true;
        } else if (this.mRenderContainer == null) {
            $jacocoInit[455] = true;
        } else if (this.mWXPerformance == null) {
            $jacocoInit[456] = true;
        } else {
            if (wXComponent == null) {
                $jacocoInit[458] = true;
            } else if (wXComponent.isIgnoreInteraction) {
                $jacocoInit[459] = true;
            } else if (this.mRenderContainer.hasConsumeEvent()) {
                $jacocoInit[461] = true;
                return;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                if (!this.mHasCreateFinish) {
                    $jacocoInit[462] = true;
                } else if (currentTimeMillis - this.mWXPerformance.renderTimeOrigin <= 8000) {
                    $jacocoInit[463] = true;
                } else {
                    $jacocoInit[464] = true;
                    return;
                }
                if (!wXComponent.mIsAddElementToTree) {
                    $jacocoInit[465] = true;
                } else {
                    $jacocoInit[466] = true;
                    getWXPerformance().localInteractionViewAddCount++;
                    if (z) {
                        $jacocoInit[467] = true;
                    } else {
                        $jacocoInit[468] = true;
                        getWXPerformance().interactionViewAddLimitCount++;
                        $jacocoInit[469] = true;
                    }
                    wXComponent.mIsAddElementToTree = false;
                    $jacocoInit[470] = true;
                }
                if (z) {
                    $jacocoInit[471] = true;
                } else {
                    $jacocoInit[472] = true;
                    this.mApmForInstance.arriveInteraction(wXComponent);
                    $jacocoInit[473] = true;
                }
                $jacocoInit[474] = true;
                return;
            }
            $jacocoInit[460] = true;
            return;
        }
        $jacocoInit[457] = true;
    }

    public void onRenderError(final String str, final String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXInstanceExceptionRecord exceptionRecorder = getExceptionRecorder();
        exceptionRecorder.recordReportErrorMsg(Operators.ARRAY_START_STR + str + ",onRenderError," + str2 + Operators.ARRAY_END_STR);
        if (this.mRenderListener == null) {
            $jacocoInit[475] = true;
        } else if (this.mContext == null) {
            $jacocoInit[476] = true;
        } else {
            $jacocoInit[477] = true;
            runOnUiThread(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSDKInstance this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(4186849146156863521L, "com/taobao/weex/WXSDKInstance$5", 6);
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
                    if (WXSDKInstance.access$600(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else if (this.this$0.mContext == null) {
                        $jacocoInit[2] = true;
                    } else {
                        $jacocoInit[3] = true;
                        WXSDKInstance.access$600(this.this$0).onException(this.this$0, str, str2);
                        $jacocoInit[4] = true;
                    }
                    $jacocoInit[5] = true;
                }
            });
            $jacocoInit[478] = true;
        }
        $jacocoInit[479] = true;
    }

    public void onJSException(final String str, final String str2, final String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        WXInstanceExceptionRecord exceptionRecorder = getExceptionRecorder();
        exceptionRecorder.recordReportErrorMsg(Operators.ARRAY_START_STR + str + "," + str2 + "," + str3 + Operators.ARRAY_END_STR);
        this.hasException = true;
        if (this.mRenderListener == null) {
            $jacocoInit[480] = true;
        } else if (this.mContext == null) {
            $jacocoInit[481] = true;
        } else {
            $jacocoInit[482] = true;
            runOnUiThread(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSDKInstance this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(1120354907592109591L, "com/taobao/weex/WXSDKInstance$6", 9);
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
                    if (WXSDKInstance.access$600(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else if (this.this$0.mContext == null) {
                        $jacocoInit[2] = true;
                    } else {
                        $jacocoInit[3] = true;
                        StringBuilder sb = new StringBuilder();
                        $jacocoInit[4] = true;
                        sb.append(str2);
                        $jacocoInit[5] = true;
                        sb.append(str3);
                        $jacocoInit[6] = true;
                        WXSDKInstance.access$600(this.this$0).onException(this.this$0, str, sb.toString());
                        $jacocoInit[7] = true;
                    }
                    $jacocoInit[8] = true;
                }
            });
            $jacocoInit[483] = true;
        }
        $jacocoInit[484] = true;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i != i5) {
            $jacocoInit[485] = true;
        } else if (i2 != i6) {
            $jacocoInit[486] = true;
        } else if (i3 != i7) {
            $jacocoInit[487] = true;
        } else if (i4 == i8) {
            $jacocoInit[488] = true;
            $jacocoInit[491] = true;
        } else {
            $jacocoInit[489] = true;
        }
        onLayoutChange(view);
        $jacocoInit[490] = true;
        $jacocoInit[491] = true;
    }

    public void onLayoutChange(View view) {
        $jacocoInit()[492] = true;
    }

    public void firstScreenCreateInstanceTime(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mCreateInstance) {
            $jacocoInit[493] = true;
        } else {
            this.mWXPerformance.firstScreenJSFExecuteTime = j - this.mRenderStartTime;
            this.mCreateInstance = false;
            $jacocoInit[494] = true;
        }
        $jacocoInit[495] = true;
    }

    public void callJsTime(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEnd) {
            $jacocoInit[496] = true;
        } else {
            this.mWXPerformance.fsCallJsTotalTime += j;
            this.mWXPerformance.fsCallJsTotalNum++;
            $jacocoInit[497] = true;
        }
        $jacocoInit[498] = true;
    }

    public void onComponentCreate(WXComponent wXComponent, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXPerformance.mActionAddElementCount++;
        WXPerformance wXPerformance = this.mWXPerformance;
        wXPerformance.mActionAddElementSumTime = (int) (((long) wXPerformance.mActionAddElementSumTime) + j);
        if (this.mEnd) {
            $jacocoInit[499] = true;
        } else {
            WXPerformance wXPerformance2 = this.mWXPerformance;
            wXPerformance2.fsComponentCreateTime = (int) (((long) wXPerformance2.fsComponentCreateTime) + j);
            this.mWXPerformance.fsComponentCount++;
            $jacocoInit[500] = true;
        }
        this.mWXPerformance.componentCount++;
        this.mWXPerformance.componentCreateTime += j;
        $jacocoInit[501] = true;
    }

    public void callActionAddElementTime(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        WXPerformance wXPerformance = this.mWXPerformance;
        wXPerformance.mActionAddElementSumTime = (int) (((long) wXPerformance.mActionAddElementSumTime) + j);
        $jacocoInit[502] = true;
    }

    public void onOldFsRenderTimeLogic() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEnd) {
            $jacocoInit[503] = true;
            return;
        }
        this.mEnd = true;
        if (this.mStatisticsListener == null) {
            $jacocoInit[504] = true;
        } else if (this.mContext == null) {
            $jacocoInit[505] = true;
        } else {
            $jacocoInit[506] = true;
            runOnUiThread(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSDKInstance this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(1250021136643961772L, "com/taobao/weex/WXSDKInstance$7", 8);
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
                    if (WXSDKInstance.access$700(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else if (this.this$0.mContext == null) {
                        $jacocoInit[2] = true;
                    } else {
                        $jacocoInit[3] = true;
                        Trace.beginSection("onFirstScreen");
                        $jacocoInit[4] = true;
                        WXSDKInstance.access$700(this.this$0).onFirstScreen();
                        $jacocoInit[5] = true;
                        Trace.endSection();
                        $jacocoInit[6] = true;
                    }
                    $jacocoInit[7] = true;
                }
            });
            $jacocoInit[507] = true;
        }
        this.mApmForInstance.arriveFSRenderTime();
        $jacocoInit[508] = true;
        this.mWXPerformance.fsRenderTime = System.currentTimeMillis();
        $jacocoInit[509] = true;
        this.mWXPerformance.screenRenderTime = System.currentTimeMillis() - this.mRenderStartTime;
        $jacocoInit[510] = true;
    }

    private void destroyView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (!(view instanceof ViewGroup)) {
                $jacocoInit[511] = true;
            } else {
                ViewGroup viewGroup = (ViewGroup) view;
                $jacocoInit[512] = true;
                $jacocoInit[513] = true;
                int i = 0;
                while (i < viewGroup.getChildCount()) {
                    $jacocoInit[514] = true;
                    destroyView(viewGroup.getChildAt(i));
                    i++;
                    $jacocoInit[515] = true;
                }
                viewGroup.removeViews(0, ((ViewGroup) view).getChildCount());
                $jacocoInit[516] = true;
                WXReflectionUtils.setValue(view, "mChildrenCount", 0);
                $jacocoInit[517] = true;
            }
            if (!(view instanceof Destroyable)) {
                $jacocoInit[518] = true;
            } else {
                $jacocoInit[519] = true;
                ((Destroyable) view).destroy();
                $jacocoInit[520] = true;
            }
            $jacocoInit[521] = true;
        } catch (Exception e) {
            $jacocoInit[522] = true;
            WXLogUtils.e("WXSDKInstance destroyView Exception: ", (Throwable) e);
            $jacocoInit[523] = true;
        }
        $jacocoInit[524] = true;
    }

    public synchronized void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isDestroy()) {
            $jacocoInit[525] = true;
        } else {
            $jacocoInit[526] = true;
            this.mApmForInstance.onEnd();
            $jacocoInit[527] = true;
            getExceptionRecorder().checkEmptyScreenAndReport();
            if (!this.mRendered) {
                $jacocoInit[528] = true;
            } else {
                $jacocoInit[529] = true;
                WXSDKManager.getInstance().destroyInstance(this.mInstanceId);
                $jacocoInit[530] = true;
            }
            if (this.mGlobalEventReceiver == null) {
                $jacocoInit[531] = true;
            } else {
                $jacocoInit[532] = true;
                getContext().unregisterReceiver(this.mGlobalEventReceiver);
                this.mGlobalEventReceiver = null;
                $jacocoInit[533] = true;
            }
            if (this.mRootComp == null) {
                $jacocoInit[534] = true;
            } else {
                $jacocoInit[535] = true;
                this.mRootComp.destroy();
                $jacocoInit[536] = true;
                destroyView(this.mRenderContainer);
                this.mRootComp = null;
                $jacocoInit[537] = true;
            }
            if (this.mGlobalEvents == null) {
                $jacocoInit[538] = true;
            } else {
                $jacocoInit[539] = true;
                this.mGlobalEvents.clear();
                $jacocoInit[540] = true;
            }
            if (this.mComponentObserver == null) {
                $jacocoInit[541] = true;
            } else {
                this.mComponentObserver = null;
                $jacocoInit[542] = true;
            }
            if (this.mLayerOverFlowListeners == null) {
                $jacocoInit[543] = true;
            } else {
                $jacocoInit[544] = true;
                this.mLayerOverFlowListeners.clear();
                $jacocoInit[545] = true;
            }
            getFlatUIContext().destroy();
            this.mFlatGUIContext = null;
            this.mInstanceOnFireEventInterceptorList = null;
            this.mWXScrollListeners = null;
            this.mRenderContainer = null;
            this.mNestedInstanceInterceptor = null;
            this.mUserTrackAdapter = null;
            this.mScrollView = null;
            this.mContext = null;
            this.mRenderListener = null;
            this.isDestroy = true;
            this.mStatisticsListener = null;
            if (this.responseHeaders == null) {
                $jacocoInit[546] = true;
            } else {
                $jacocoInit[547] = true;
                this.responseHeaders.clear();
                $jacocoInit[548] = true;
            }
            if (this.templateRef == null) {
                $jacocoInit[549] = true;
            } else {
                this.templateRef = null;
                $jacocoInit[550] = true;
            }
            if (this.mContentBoxMeasurements == null) {
                $jacocoInit[551] = true;
            } else {
                $jacocoInit[552] = true;
                this.mContentBoxMeasurements.clear();
                $jacocoInit[553] = true;
            }
            this.mWXPerformance.afterInstanceDestroy(this.mInstanceId);
            $jacocoInit[554] = true;
            WXBridgeManager.getInstance().post(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSDKInstance this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-597634702767859446L, "com/taobao/weex/WXSDKInstance$8", 3);
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
                    WXBridgeManager.getInstance().onInstanceClose(this.this$0.getInstanceId());
                    $jacocoInit[1] = true;
                    WXSDKInstance.access$800(this.this$0).clear();
                    $jacocoInit[2] = true;
                }
            });
            $jacocoInit[555] = true;
            WXBridgeManager.getInstance().postDelay(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSDKInstance this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-366341016640828647L, "com/taobao/weex/WXSDKInstance$9", 2);
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
                    WXSDKManager.getInstance().getAllInstanceMap().remove(WXSDKInstance.access$300(this.this$0));
                    $jacocoInit[1] = true;
                }
            }, 5000);
            $jacocoInit[556] = true;
        }
        $jacocoInit[557] = true;
    }

    public boolean isDestroy() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isDestroy;
        $jacocoInit[558] = true;
        return z;
    }

    @Nullable
    public String getBundleUrl() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mBundleUrl;
        $jacocoInit[559] = true;
        return str;
    }

    public View getRootView() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRootComp == null) {
            $jacocoInit[560] = true;
            return null;
        }
        View realView = this.mRootComp.getRealView();
        $jacocoInit[561] = true;
        return realView;
    }

    public View getContainerView() {
        boolean[] $jacocoInit = $jacocoInit();
        RenderContainer renderContainer = this.mRenderContainer;
        $jacocoInit[562] = true;
        return renderContainer;
    }

    @Deprecated
    public void setBundleUrl(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBundleUrl = str;
        $jacocoInit[563] = true;
        if (WXSDKManager.getInstance().getValidateProcessor() == null) {
            $jacocoInit[564] = true;
        } else {
            $jacocoInit[565] = true;
            this.mNeedValidate = WXSDKManager.getInstance().getValidateProcessor().needValidate(this.mBundleUrl);
            $jacocoInit[566] = true;
        }
        $jacocoInit[567] = true;
    }

    public void onRootCreated(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRootComp = wXComponent;
        this.mRootComp.mDeepInComponentTree = 1;
        $jacocoInit[568] = true;
        this.mRenderContainer.addView(wXComponent.getHostView());
        $jacocoInit[569] = true;
        setSize(this.mRenderContainer.getWidth(), this.mRenderContainer.getHeight());
        $jacocoInit[570] = true;
    }

    public void moveFixedView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer == null) {
            $jacocoInit[571] = true;
        } else {
            $jacocoInit[572] = true;
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup == null) {
                this.mRenderContainer.addView(view);
                $jacocoInit[577] = true;
            } else if (viewGroup == this.mRenderContainer) {
                $jacocoInit[573] = true;
            } else {
                $jacocoInit[574] = true;
                viewGroup.removeView(view);
                $jacocoInit[575] = true;
                this.mRenderContainer.addView(view);
                $jacocoInit[576] = true;
            }
        }
        $jacocoInit[578] = true;
    }

    public void removeFixedView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer == null) {
            $jacocoInit[579] = true;
        } else {
            $jacocoInit[580] = true;
            this.mRenderContainer.removeView(view);
            $jacocoInit[581] = true;
        }
        $jacocoInit[582] = true;
    }

    public int getRenderContainerPaddingLeft() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer != null) {
            $jacocoInit[583] = true;
            int paddingLeft = this.mRenderContainer.getPaddingLeft();
            $jacocoInit[584] = true;
            return paddingLeft;
        }
        $jacocoInit[585] = true;
        return 0;
    }

    public int getRenderContainerPaddingRight() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer != null) {
            $jacocoInit[586] = true;
            int paddingRight = this.mRenderContainer.getPaddingRight();
            $jacocoInit[587] = true;
            return paddingRight;
        }
        $jacocoInit[588] = true;
        return 0;
    }

    public int getRenderContainerPaddingTop() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContainer != null) {
            $jacocoInit[589] = true;
            int paddingTop = this.mRenderContainer.getPaddingTop();
            $jacocoInit[590] = true;
            return paddingTop;
        }
        $jacocoInit[591] = true;
        return 0;
    }

    public synchronized List<OnWXScrollListener> getWXScrollListeners() {
        List<OnWXScrollListener> list;
        boolean[] $jacocoInit = $jacocoInit();
        list = this.mWXScrollListeners;
        $jacocoInit[592] = true;
        return list;
    }

    public synchronized void registerOnWXScrollListener(OnWXScrollListener onWXScrollListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXScrollListeners != null) {
            $jacocoInit[593] = true;
        } else {
            $jacocoInit[594] = true;
            this.mWXScrollListeners = new ArrayList();
            $jacocoInit[595] = true;
        }
        this.mWXScrollListeners.add(onWXScrollListener);
        $jacocoInit[596] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSize(int r9, int r10) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r9 > 0) goto L_0x000d
            r9 = 597(0x255, float:8.37E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x000d:
            r2 = 0
            if (r10 <= 0) goto L_0x0016
            r3 = 598(0x256, float:8.38E-43)
            r0[r3] = r1
            r3 = 1
            goto L_0x001b
        L_0x0016:
            r3 = 599(0x257, float:8.4E-43)
            r0[r3] = r1
            r3 = 0
        L_0x001b:
            boolean r4 = r8.isDestroy
            if (r4 != 0) goto L_0x0025
            r4 = 600(0x258, float:8.41E-43)
            r0[r4] = r1
            r4 = 1
            goto L_0x002a
        L_0x0025:
            r4 = 601(0x259, float:8.42E-43)
            r0[r4] = r1
            r4 = 0
        L_0x002a:
            r3 = r3 & r4
            if (r3 != 0) goto L_0x0033
            r9 = 602(0x25a, float:8.44E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x0033:
            boolean r3 = r8.mRendered
            if (r3 != 0) goto L_0x003d
            r9 = 603(0x25b, float:8.45E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x003d:
            com.taobao.weex.RenderContainer r3 = r8.mRenderContainer
            if (r3 != 0) goto L_0x0047
            r9 = 604(0x25c, float:8.46E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x0047:
            int r3 = sScreenHeight
            if (r3 < 0) goto L_0x0050
            r3 = 605(0x25d, float:8.48E-43)
            r0[r3] = r1
            goto L_0x0062
        L_0x0050:
            r3 = 606(0x25e, float:8.49E-43)
            r0[r3] = r1
            android.content.Context r3 = r8.getContext()
            int r3 = com.taobao.weex.utils.WXViewUtils.getScreenHeight(r3)
            sScreenHeight = r3
            r3 = 607(0x25f, float:8.5E-43)
            r0[r3] = r1
        L_0x0062:
            int r3 = sScreenHeight
            if (r3 > 0) goto L_0x006b
            r3 = 608(0x260, float:8.52E-43)
            r0[r3] = r1
            goto L_0x0095
        L_0x006b:
            double r3 = (double) r10
            int r5 = sScreenHeight
            double r5 = (double) r5
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r5)
            double r3 = r3 / r5
            r5 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r3 = r3 * r5
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0083
            r5 = 609(0x261, float:8.53E-43)
            r0[r5] = r1
            goto L_0x0088
        L_0x0083:
            r3 = 610(0x262, float:8.55E-43)
            r0[r3] = r1
            r3 = r5
        L_0x0088:
            com.taobao.weex.performance.WXInstanceApm r5 = r8.getApmForInstance()
            java.lang.String r6 = "wxBodyRatio"
            r5.addStats(r6, r3)
            r3 = 611(0x263, float:8.56E-43)
            r0[r3] = r1
        L_0x0095:
            com.taobao.weex.RenderContainer r3 = r8.mRenderContainer
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            if (r3 != 0) goto L_0x00a3
            r9 = 612(0x264, float:8.58E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x00a3:
            float r4 = (float) r9
            float r5 = (float) r10
            r6 = 613(0x265, float:8.59E-43)
            r0[r6] = r1
            com.taobao.weex.RenderContainer r6 = r8.mRenderContainer
            int r6 = r6.getWidth()
            if (r6 == r9) goto L_0x00b6
            r6 = 614(0x266, float:8.6E-43)
            r0[r6] = r1
            goto L_0x00c7
        L_0x00b6:
            com.taobao.weex.RenderContainer r6 = r8.mRenderContainer
            int r6 = r6.getHeight()
            if (r6 != r10) goto L_0x00c3
            r9 = 615(0x267, float:8.62E-43)
            r0[r9] = r1
            goto L_0x00d8
        L_0x00c3:
            r6 = 616(0x268, float:8.63E-43)
            r0[r6] = r1
        L_0x00c7:
            r3.width = r9
            r3.height = r10
            r9 = 617(0x269, float:8.65E-43)
            r0[r9] = r1
            com.taobao.weex.RenderContainer r9 = r8.mRenderContainer
            r9.setLayoutParams(r3)
            r9 = 618(0x26a, float:8.66E-43)
            r0[r9] = r1
        L_0x00d8:
            com.taobao.weex.ui.component.WXComponent r9 = r8.mRootComp
            if (r9 != 0) goto L_0x00e1
            r9 = 619(0x26b, float:8.67E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x00e1:
            if (r3 != 0) goto L_0x00e8
            r9 = 620(0x26c, float:8.69E-43)
            r0[r9] = r1
            goto L_0x011d
        L_0x00e8:
            int r9 = r3.width
            r10 = -2
            if (r9 != r10) goto L_0x00f3
            r9 = 621(0x26d, float:8.7E-43)
            r0[r9] = r1
            r6 = 1
            goto L_0x00f8
        L_0x00f3:
            r9 = 622(0x26e, float:8.72E-43)
            r0[r9] = r1
            r6 = 0
        L_0x00f8:
            int r9 = r3.height
            if (r9 != r10) goto L_0x0102
            r9 = 623(0x26f, float:8.73E-43)
            r0[r9] = r1
            r7 = 1
            goto L_0x0107
        L_0x0102:
            r9 = 624(0x270, float:8.74E-43)
            r0[r9] = r1
            r7 = 0
        L_0x0107:
            r9 = 625(0x271, float:8.76E-43)
            r0[r9] = r1
            com.taobao.weex.bridge.WXBridgeManager r9 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
            com.taobao.weex.WXSDKInstance$10 r10 = new com.taobao.weex.WXSDKInstance$10
            r2 = r10
            r3 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            r9.post(r10)
            r9 = 626(0x272, float:8.77E-43)
            r0[r9] = r1
        L_0x011d:
            r9 = 627(0x273, float:8.79E-43)
            r0[r9] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.WXSDKInstance.setSize(int, int):void");
    }

    public void fireGlobalEventCallback(String str, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = this.mGlobalEvents.get(str);
        if (list == null) {
            $jacocoInit[628] = true;
        } else {
            $jacocoInit[629] = true;
            $jacocoInit[630] = true;
            for (String callback : list) {
                $jacocoInit[632] = true;
                WXSDKManager.getInstance().callback(this.mInstanceId, callback, map, true);
                $jacocoInit[633] = true;
            }
            $jacocoInit[631] = true;
        }
        $jacocoInit[634] = true;
    }

    public void fireEvent(String str, String str2, Map<String, Object> map, Map<String, Object> map2, List<Object> list) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, map, map2, list, (EventResult) null);
        $jacocoInit[635] = true;
    }

    public void fireEvent(String str, String str2, Map<String, Object> map, Map<String, Object> map2, List<Object> list, EventResult eventResult) {
        boolean[] $jacocoInit = $jacocoInit();
        onInterceptInstanceEvent(getInstanceId(), str, str2, map, map2);
        if (this.mWXPerformance == null) {
            $jacocoInit[636] = true;
        } else if (this.mWXPerformance.fsCallEventTotalNum >= Integer.MAX_VALUE) {
            $jacocoInit[637] = true;
        } else {
            this.mWXPerformance.fsCallEventTotalNum++;
            $jacocoInit[638] = true;
        }
        this.mApmForInstance.updateFSDiffStats(WXInstanceApm.KEY_PAGE_STATS_FS_CALL_EVENT_NUM, 1.0d);
        $jacocoInit[639] = true;
        WXBridgeManager.getInstance().fireEventOnNode(getInstanceId(), str, str2, map, map2, list, eventResult);
        $jacocoInit[640] = true;
    }

    public void fireEvent(String str, String str2, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, map, map2, (List<Object>) null);
        $jacocoInit[641] = true;
    }

    public void fireEvent(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, map, (Map<String, Object>) null);
        $jacocoInit[642] = true;
    }

    public void fireEvent(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, str2, new HashMap());
        $jacocoInit[643] = true;
    }

    /* access modifiers changed from: protected */
    public void addEventListener(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[644] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[645] = true;
        } else {
            List list = this.mGlobalEvents.get(str);
            if (list != null) {
                $jacocoInit[647] = true;
            } else {
                $jacocoInit[648] = true;
                list = new ArrayList();
                $jacocoInit[649] = true;
                this.mGlobalEvents.put(str, list);
                $jacocoInit[650] = true;
            }
            list.add(str2);
            $jacocoInit[651] = true;
            return;
        }
        $jacocoInit[646] = true;
    }

    /* access modifiers changed from: protected */
    public void removeEventListener(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[652] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[653] = true;
        } else {
            List list = this.mGlobalEvents.get(str);
            if (list == null) {
                $jacocoInit[655] = true;
            } else {
                $jacocoInit[656] = true;
                list.remove(str2);
                $jacocoInit[657] = true;
            }
            $jacocoInit[658] = true;
            return;
        }
        $jacocoInit[654] = true;
    }

    /* access modifiers changed from: protected */
    public void removeEventListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[659] = true;
            return;
        }
        this.mGlobalEvents.remove(str);
        $jacocoInit[660] = true;
    }

    public void fireModuleEvent(String str, WXModule wXModule, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[661] = true;
        } else if (wXModule == null) {
            $jacocoInit[662] = true;
        } else {
            HashMap hashMap = new HashMap();
            $jacocoInit[664] = true;
            hashMap.put("type", str);
            $jacocoInit[665] = true;
            hashMap.put(WXBridgeManager.MODULE, wXModule.getModuleName());
            $jacocoInit[666] = true;
            hashMap.put("data", map);
            $jacocoInit[667] = true;
            List<String> eventCallbacks = wXModule.getEventCallbacks(str);
            if (eventCallbacks == null) {
                $jacocoInit[668] = true;
            } else {
                $jacocoInit[669] = true;
                $jacocoInit[670] = true;
                for (String next : eventCallbacks) {
                    $jacocoInit[672] = true;
                    SimpleJSCallback simpleJSCallback = new SimpleJSCallback(this.mInstanceId, next);
                    $jacocoInit[673] = true;
                    if (wXModule.isOnce(next)) {
                        $jacocoInit[674] = true;
                        simpleJSCallback.invoke(hashMap);
                        $jacocoInit[675] = true;
                    } else {
                        simpleJSCallback.invokeAndKeepAlive(hashMap);
                        $jacocoInit[676] = true;
                    }
                    $jacocoInit[677] = true;
                }
                $jacocoInit[671] = true;
            }
            $jacocoInit[678] = true;
            return;
        }
        $jacocoInit[663] = true;
    }

    public boolean checkModuleEventRegistered(String str, WXModule wXModule) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXModule == null) {
            $jacocoInit[679] = true;
        } else {
            $jacocoInit[680] = true;
            List<String> eventCallbacks = wXModule.getEventCallbacks(str);
            $jacocoInit[681] = true;
            if (eventCallbacks == null) {
                $jacocoInit[682] = true;
            } else if (eventCallbacks.size() <= 0) {
                $jacocoInit[683] = true;
            } else {
                $jacocoInit[684] = true;
                return true;
            }
        }
        $jacocoInit[685] = true;
        return false;
    }

    public WXPerformance getWXPerformance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXPerformance wXPerformance = this.mWXPerformance;
        $jacocoInit[686] = true;
        return wXPerformance;
    }

    public WXInstanceApm getApmForInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXInstanceApm wXInstanceApm = this.mApmForInstance;
        $jacocoInit[687] = true;
        return wXInstanceApm;
    }

    public WXInstanceExceptionRecord getExceptionRecorder() {
        boolean[] $jacocoInit = $jacocoInit();
        WXInstanceExceptionRecord wXInstanceExceptionRecord = this.mExceptionRecorder;
        $jacocoInit[688] = true;
        return wXInstanceExceptionRecord;
    }

    public Map<String, Serializable> getUserTrackParams() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, Serializable> map = this.mUserTrackParams;
        $jacocoInit[689] = true;
        return map;
    }

    public void addUserTrackParameter(String str, Serializable serializable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mUserTrackParams != null) {
            $jacocoInit[690] = true;
        } else {
            $jacocoInit[691] = true;
            this.mUserTrackParams = new ConcurrentHashMap();
            $jacocoInit[692] = true;
        }
        this.mUserTrackParams.put(str, serializable);
        $jacocoInit[693] = true;
    }

    public void clearUserTrackParameters() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mUserTrackParams == null) {
            $jacocoInit[694] = true;
        } else {
            $jacocoInit[695] = true;
            this.mUserTrackParams.clear();
            $jacocoInit[696] = true;
        }
        $jacocoInit[697] = true;
    }

    public void removeUserTrackParameter(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mUserTrackParams == null) {
            $jacocoInit[698] = true;
        } else {
            $jacocoInit[699] = true;
            this.mUserTrackParams.remove(str);
            $jacocoInit[700] = true;
        }
        $jacocoInit[701] = true;
    }

    public int getMaxDeepLayer() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mMaxDeepLayer;
        $jacocoInit[702] = true;
        return i;
    }

    public void setMaxDeepLayer(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMaxDeepLayer = i;
        $jacocoInit[703] = true;
        this.mApmForInstance.updateMaxStats(WXInstanceApm.KEY_PAGE_STATS_MAX_DEEP_VIEW, (double) i);
        $jacocoInit[704] = true;
    }

    public void setMaxDomDeep(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mApmForInstance.updateMaxStats(WXInstanceApm.KEY_PAGE_STATS_MAX_DEEP_DOM, (double) i);
        if (this.mWXPerformance == null) {
            $jacocoInit[705] = true;
            return;
        }
        if (this.mWXPerformance.maxDeepVDomLayer > i) {
            $jacocoInit[706] = true;
        } else {
            this.mWXPerformance.maxDeepVDomLayer = i;
            $jacocoInit[707] = true;
        }
        $jacocoInit[708] = true;
    }

    public void onHttpStart() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mEnd) {
            $jacocoInit[709] = true;
        } else {
            this.mWXPerformance.fsRequestNum++;
            $jacocoInit[710] = true;
        }
        $jacocoInit[711] = true;
    }

    class WXHttpListener implements IWXHttpAdapter.OnHttpListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private WXRenderStrategy flag;
        private WXSDKInstance instance;
        private String jsonInitData;
        private Map<String, Object> options;
        private String pageName;
        private long startRequestTime;
        final /* synthetic */ WXSDKInstance this$0;
        private int traceId;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(5073533233132177593L, "com/taobao/weex/WXSDKInstance$WXHttpListener", 134);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ WXHttpListener(WXSDKInstance wXSDKInstance, String str, Map map, String str2, WXRenderStrategy wXRenderStrategy, long j, AnonymousClass1 r8) {
            this(wXSDKInstance, str, map, str2, wXRenderStrategy, j);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[133] = true;
        }

        private WXHttpListener(WXSDKInstance wXSDKInstance, String str, Map<String, Object> map, String str2, WXRenderStrategy wXRenderStrategy, long j) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = wXSDKInstance;
            this.pageName = str;
            this.options = map;
            this.jsonInitData = str2;
            this.flag = wXRenderStrategy;
            this.startRequestTime = j;
            $jacocoInit[0] = true;
            this.traceId = WXTracing.nextId();
            $jacocoInit[1] = true;
            if (!WXTracing.isAvailable()) {
                $jacocoInit[2] = true;
            } else {
                $jacocoInit[3] = true;
                WXTracing.TraceEvent newEvent = WXTracing.newEvent("downloadBundleJS", WXSDKInstance.access$300(wXSDKInstance), -1);
                $jacocoInit[4] = true;
                newEvent.iid = WXSDKInstance.access$300(wXSDKInstance);
                newEvent.tname = "Network";
                newEvent.ph = Field.b;
                newEvent.traceId = this.traceId;
                $jacocoInit[5] = true;
                newEvent.submit();
                $jacocoInit[6] = true;
            }
            $jacocoInit[7] = true;
        }

        public void setSDKInstance(WXSDKInstance wXSDKInstance) {
            boolean[] $jacocoInit = $jacocoInit();
            this.instance = wXSDKInstance;
            $jacocoInit[8] = true;
        }

        public void onHttpStart() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.instance == null) {
                $jacocoInit[9] = true;
            } else {
                WXSDKInstance wXSDKInstance = this.instance;
                $jacocoInit[10] = true;
                if (wXSDKInstance.getWXStatisticsListener() == null) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    this.instance.getWXStatisticsListener().onHttpStart();
                    $jacocoInit[13] = true;
                }
            }
            $jacocoInit[14] = true;
        }

        public void onHeadersReceived(int i, Map<String, List<String>> map) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.instance == null) {
                $jacocoInit[15] = true;
            } else {
                WXSDKInstance wXSDKInstance = this.instance;
                $jacocoInit[16] = true;
                if (wXSDKInstance.getWXStatisticsListener() == null) {
                    $jacocoInit[17] = true;
                } else {
                    $jacocoInit[18] = true;
                    this.instance.getWXStatisticsListener().onHeadersReceived();
                    $jacocoInit[19] = true;
                    this.instance.onHttpStart();
                    $jacocoInit[20] = true;
                }
            }
            if (this.instance == null) {
                $jacocoInit[21] = true;
            } else if (this.instance.responseHeaders == null) {
                $jacocoInit[22] = true;
            } else if (map == null) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                this.instance.responseHeaders.putAll(map);
                $jacocoInit[25] = true;
            }
            $jacocoInit[26] = true;
        }

        public void onHttpUploadProgress(int i) {
            $jacocoInit()[27] = true;
        }

        public void onHttpResponseProgress(int i) {
            $jacocoInit()[28] = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:118:0x049a  */
        /* JADX WARNING: Removed duplicated region for block: B:119:0x049f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onHttpFinish(com.taobao.weex.common.WXResponse r10) {
            /*
                r9 = this;
                boolean[] r0 = $jacocoInit()
                com.taobao.weex.WXSDKInstance r1 = r9.instance
                r2 = 1
                if (r1 != 0) goto L_0x000e
                r1 = 29
                r0[r1] = r2
                goto L_0x0030
            L_0x000e:
                com.taobao.weex.WXSDKInstance r1 = r9.instance
                r3 = 30
                r0[r3] = r2
                com.taobao.weex.IWXStatisticsListener r1 = r1.getWXStatisticsListener()
                if (r1 != 0) goto L_0x001f
                r1 = 31
                r0[r1] = r2
                goto L_0x0030
            L_0x001f:
                r1 = 32
                r0[r1] = r2
                com.taobao.weex.WXSDKInstance r1 = r9.instance
                com.taobao.weex.IWXStatisticsListener r1 = r1.getWXStatisticsListener()
                r1.onHttpFinish()
                r1 = 33
                r0[r1] = r2
            L_0x0030:
                boolean r1 = com.taobao.weex.tracing.WXTracing.isAvailable()
                if (r1 != 0) goto L_0x003b
                r1 = 34
                r0[r1] = r2
                goto L_0x0090
            L_0x003b:
                r1 = 35
                r0[r1] = r2
                java.lang.String r1 = "downloadBundleJS"
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                java.lang.String r3 = com.taobao.weex.WXSDKInstance.access$300(r3)
                r4 = -1
                com.taobao.weex.tracing.WXTracing$TraceEvent r1 = com.taobao.weex.tracing.WXTracing.newEvent(r1, r3, r4)
                int r3 = r9.traceId
                r1.traceId = r3
                java.lang.String r3 = "Network"
                r1.tname = r3
                java.lang.String r3 = "E"
                r1.ph = r3
                r3 = 36
                r0[r3] = r2
                java.util.HashMap r3 = new java.util.HashMap
                r3.<init>()
                r1.extParams = r3
                if (r10 != 0) goto L_0x006a
                r3 = 37
                r0[r3] = r2
                goto L_0x0089
            L_0x006a:
                byte[] r3 = r10.originalData
                if (r3 != 0) goto L_0x0073
                r3 = 38
                r0[r3] = r2
                goto L_0x0089
            L_0x0073:
                r3 = 39
                r0[r3] = r2
                java.util.Map<java.lang.String, java.lang.Object> r3 = r1.extParams
                java.lang.String r4 = "BundleSize"
                byte[] r5 = r10.originalData
                int r5 = r5.length
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r3.put(r4, r5)
                r3 = 40
                r0[r3] = r2
            L_0x0089:
                r1.submit()
                r1 = 41
                r0[r1] = r2
            L_0x0090:
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.common.WXPerformance r1 = com.taobao.weex.WXSDKInstance.access$900(r1)
                long r3 = java.lang.System.currentTimeMillis()
                long r5 = r9.startRequestTime
                long r3 = r3 - r5
                r1.networkTime = r3
                if (r10 != 0) goto L_0x00a7
                r1 = 42
                r0[r1] = r2
                goto L_0x0336
            L_0x00a7:
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                if (r1 != 0) goto L_0x00b1
                r1 = 43
                r0[r1] = r2
                goto L_0x0336
            L_0x00b1:
                r1 = 44
                r0[r1] = r2
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.performance.WXInstanceApm r1 = com.taobao.weex.WXSDKInstance.access$1000(r1)
                java.util.Map<java.lang.String, java.lang.Object> r3 = r10.extendParams
                r1.updateRecordInfo(r3)
                r1 = 45
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "actualNetworkTime"
                java.lang.Object r1 = r1.get(r3)
                r3 = 46
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.Long
                r5 = 0
                if (r4 == 0) goto L_0x00e7
                java.lang.Long r1 = (java.lang.Long) r1
                long r7 = r1.longValue()
                r1 = 47
                r0[r1] = r2
                goto L_0x00ec
            L_0x00e7:
                r1 = 48
                r0[r1] = r2
                r7 = r5
            L_0x00ec:
                r3.actualNetworkTime = r7
                r1 = 49
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "pureNetworkTime"
                java.lang.Object r1 = r1.get(r3)
                r3 = 50
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.Long
                if (r4 == 0) goto L_0x0113
                java.lang.Long r1 = (java.lang.Long) r1
                long r7 = r1.longValue()
                r1 = 51
                r0[r1] = r2
                goto L_0x0118
            L_0x0113:
                r1 = 52
                r0[r1] = r2
                r7 = r5
            L_0x0118:
                r3.pureNetworkTime = r7
                r1 = 53
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "connectionType"
                java.lang.Object r1 = r1.get(r3)
                r3 = 54
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.String
                if (r4 == 0) goto L_0x013b
                java.lang.String r1 = (java.lang.String) r1
                r4 = 55
                r0[r4] = r2
                goto L_0x0141
            L_0x013b:
                java.lang.String r1 = ""
                r4 = 56
                r0[r4] = r2
            L_0x0141:
                r3.connectionType = r1
                r1 = 57
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "packageSpendTime"
                java.lang.Object r1 = r1.get(r3)
                r3 = 58
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.Long
                if (r4 == 0) goto L_0x0168
                java.lang.Long r1 = (java.lang.Long) r1
                long r7 = r1.longValue()
                r1 = 59
                r0[r1] = r2
                goto L_0x016d
            L_0x0168:
                r1 = 60
                r0[r1] = r2
                r7 = r5
            L_0x016d:
                r3.packageSpendTime = r7
                r1 = 61
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "syncTaskTime"
                java.lang.Object r1 = r1.get(r3)
                r3 = 62
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.Long
                if (r4 == 0) goto L_0x0194
                java.lang.Long r1 = (java.lang.Long) r1
                long r5 = r1.longValue()
                r1 = 63
                r0[r1] = r2
                goto L_0x0198
            L_0x0194:
                r1 = 64
                r0[r1] = r2
            L_0x0198:
                r3.syncTaskTime = r5
                r1 = 65
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "requestType"
                java.lang.Object r1 = r1.get(r3)
                r3 = 66
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.String
                if (r4 == 0) goto L_0x01bb
                java.lang.String r1 = (java.lang.String) r1
                r4 = 67
                r0[r4] = r2
                goto L_0x01c1
            L_0x01bb:
                java.lang.String r1 = "none"
                r4 = 68
                r0[r4] = r2
            L_0x01c1:
                r3.requestType = r1
                r1 = 69
                r0[r1] = r2
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                com.taobao.weex.common.WXPerformance$Dimension r3 = com.taobao.weex.common.WXPerformance.Dimension.cacheType
                java.lang.String r3 = r3.toString()
                java.lang.Object r1 = r1.get(r3)
                boolean r3 = r1 instanceof java.lang.String
                if (r3 != 0) goto L_0x01dc
                r1 = 70
                r0[r1] = r2
                goto L_0x01ee
            L_0x01dc:
                r3 = 71
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                java.lang.String r1 = (java.lang.String) r1
                r3.cacheType = r1
                r1 = 72
                r0[r1] = r2
            L_0x01ee:
                java.util.Map<java.lang.String, java.lang.Object> r1 = r10.extendParams
                java.lang.String r3 = "zCacheInfo"
                java.lang.Object r1 = r1.get(r3)
                r3 = 73
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                boolean r4 = r1 instanceof java.lang.String
                if (r4 == 0) goto L_0x020b
                java.lang.String r1 = (java.lang.String) r1
                r4 = 74
                r0[r4] = r2
                goto L_0x0211
            L_0x020b:
                java.lang.String r1 = ""
                r4 = 75
                r0[r4] = r2
            L_0x0211:
                r3.zCacheInfo = r1
                r1 = 76
                r0[r1] = r2
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.common.WXPerformance r3 = com.taobao.weex.WXSDKInstance.access$900(r3)
                java.lang.String r3 = r3.requestType
                boolean r1 = com.taobao.weex.WXSDKInstance.access$1100(r1, r3)
                if (r1 != 0) goto L_0x022d
                r1 = 77
                r0[r1] = r2
                goto L_0x0336
            L_0x022d:
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.adapter.IWXUserTrackAdapter r1 = com.taobao.weex.WXSDKInstance.access$1200(r1)
                if (r1 != 0) goto L_0x023b
                r1 = 78
                r0[r1] = r2
                goto L_0x0336
            L_0x023b:
                r1 = 79
                r0[r1] = r2
                com.taobao.weex.common.WXPerformance r7 = new com.taobao.weex.common.WXPerformance
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                java.lang.String r1 = com.taobao.weex.WXSDKInstance.access$300(r1)
                r7.<init>(r1)
                r1 = 80
                r0[r1] = r2
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                java.lang.String r1 = com.taobao.weex.WXSDKInstance.access$1300(r1)
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L_0x025f
                r1 = 81
                r0[r1] = r2
                goto L_0x0288
            L_0x025f:
                r1 = 82
                r0[r1] = r2     // Catch:{ Exception -> 0x0280 }
                com.taobao.weex.WXSDKInstance r1 = r9.this$0     // Catch:{ Exception -> 0x0280 }
                java.lang.String r1 = com.taobao.weex.WXSDKInstance.access$1300(r1)     // Catch:{ Exception -> 0x0280 }
                android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x0280 }
                android.net.Uri$Builder r1 = r1.buildUpon()     // Catch:{ Exception -> 0x0280 }
                android.net.Uri$Builder r1 = r1.clearQuery()     // Catch:{ Exception -> 0x0280 }
                java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0280 }
                r7.args = r1     // Catch:{ Exception -> 0x0280 }
                r1 = 83
                r0[r1] = r2
                goto L_0x0288
            L_0x0280:
                java.lang.String r1 = r9.pageName
                r7.args = r1
                r1 = 84
                r0[r1] = r2
            L_0x0288:
                java.lang.String r1 = "200"
                java.lang.String r3 = r10.statusCode
                boolean r1 = r1.equals(r3)
                if (r1 != 0) goto L_0x02be
                r1 = 85
                r0[r1] = r2
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_ERR_JSBUNDLE_DOWNLOAD
                java.lang.String r1 = r1.getErrorCode()
                r7.errCode = r1
                r1 = 86
                r0[r1] = r2
                java.lang.String r1 = r10.errorCode
                r7.appendErrMsg(r1)
                r1 = 87
                r0[r1] = r2
                java.lang.String r1 = "|"
                r7.appendErrMsg(r1)
                r1 = 88
                r0[r1] = r2
                java.lang.String r1 = r10.errorMsg
                r7.appendErrMsg(r1)
                r1 = 89
                r0[r1] = r2
                goto L_0x030e
            L_0x02be:
                java.lang.String r1 = "200"
                java.lang.String r3 = r10.statusCode
                boolean r1 = r1.equals(r3)
                if (r1 != 0) goto L_0x02cd
                r1 = 90
                r0[r1] = r2
                goto L_0x02df
            L_0x02cd:
                byte[] r1 = r10.originalData
                if (r1 != 0) goto L_0x02d6
                r1 = 91
                r0[r1] = r2
                goto L_0x02f0
            L_0x02d6:
                byte[] r1 = r10.originalData
                int r1 = r1.length
                if (r1 <= 0) goto L_0x02ec
                r1 = 92
                r0[r1] = r2
            L_0x02df:
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_SUCCESS
                java.lang.String r1 = r1.getErrorCode()
                r7.errCode = r1
                r1 = 97
                r0[r1] = r2
                goto L_0x030e
            L_0x02ec:
                r1 = 93
                r0[r1] = r2
            L_0x02f0:
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_ERR_JSBUNDLE_DOWNLOAD
                java.lang.String r1 = r1.getErrorCode()
                r7.errCode = r1
                r1 = 94
                r0[r1] = r2
                java.lang.String r1 = r10.statusCode
                r7.appendErrMsg(r1)
                r1 = 95
                r0[r1] = r2
                java.lang.String r1 = "|template is null!"
                r7.appendErrMsg(r1)
                r1 = 96
                r0[r1] = r2
            L_0x030e:
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.adapter.IWXUserTrackAdapter r1 = com.taobao.weex.WXSDKInstance.access$1200(r1)
                if (r1 != 0) goto L_0x031b
                r1 = 98
                r0[r1] = r2
                goto L_0x0336
            L_0x031b:
                r1 = 99
                r0[r1] = r2
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.adapter.IWXUserTrackAdapter r3 = com.taobao.weex.WXSDKInstance.access$1200(r1)
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                android.content.Context r4 = r1.getContext()
                r5 = 0
                java.lang.String r6 = "jsDownload"
                r8 = 0
                r3.commit(r4, r5, r6, r7, r8)
                r1 = 100
                r0[r1] = r2
            L_0x0336:
                java.lang.String r1 = "0"
                r3 = 101(0x65, float:1.42E-43)
                r0[r3] = r2
                if (r10 != 0) goto L_0x0343
                r1 = 102(0x66, float:1.43E-43)
                r0[r1] = r2
                goto L_0x035a
            L_0x0343:
                byte[] r3 = r10.originalData
                if (r3 != 0) goto L_0x034c
                r1 = 103(0x67, float:1.44E-43)
                r0[r1] = r2
                goto L_0x035a
            L_0x034c:
                java.lang.String r3 = "200"
                java.lang.String r4 = r10.statusCode
                boolean r3 = android.text.TextUtils.equals(r3, r4)
                if (r3 != 0) goto L_0x044b
                r1 = 104(0x68, float:1.46E-43)
                r0[r1] = r2
            L_0x035a:
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_BUNDLE_CONTENTTYPE_ERROR
                java.lang.String r1 = r1.getErrorCode()
                java.lang.String r3 = r10.statusCode
                boolean r1 = android.text.TextUtils.equals(r1, r3)
                if (r1 == 0) goto L_0x03c5
                r1 = 110(0x6e, float:1.54E-43)
                r0[r1] = r2
                java.lang.String r1 = "user intercept: WX_DEGRAD_ERR_BUNDLE_CONTENTTYPE_ERROR"
                com.taobao.weex.utils.WXLogUtils.e(r1)
                r1 = 111(0x6f, float:1.56E-43)
                r0[r1] = r2
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_BUNDLE_CONTENTTYPE_ERROR
                java.lang.String r1 = r1.getErrorCode()
                r3 = 112(0x70, float:1.57E-43)
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "|response.errorMsg=="
                r4.append(r5)
                java.lang.String r10 = r10.errorMsg
                r4.append(r10)
                java.lang.String r10 = "|instance bundleUrl = \n"
                r4.append(r10)
                com.taobao.weex.WXSDKInstance r10 = r9.instance
                r5 = 113(0x71, float:1.58E-43)
                r0[r5] = r2
                java.lang.String r10 = r10.getBundleUrl()
                r4.append(r10)
                java.lang.String r10 = "|instance requestUrl = \n"
                r4.append(r10)
                java.lang.String r10 = com.taobao.weex.WXSDKInstance.requestUrl
                r5 = 114(0x72, float:1.6E-43)
                r0[r5] = r2
                java.lang.String r10 = android.net.Uri.decode(r10)
                r4.append(r10)
                java.lang.String r10 = r4.toString()
                r4 = 115(0x73, float:1.61E-43)
                r0[r4] = r2
                r3.onRenderError(r1, r10)
                r10 = 116(0x74, float:1.63E-43)
                r0[r10] = r2
                goto L_0x0492
            L_0x03c5:
                if (r10 != 0) goto L_0x03cc
                r1 = 117(0x75, float:1.64E-43)
                r0[r1] = r2
                goto L_0x03e3
            L_0x03cc:
                byte[] r1 = r10.originalData
                if (r1 != 0) goto L_0x03d5
                r1 = 118(0x76, float:1.65E-43)
                r0[r1] = r2
                goto L_0x03e3
            L_0x03d5:
                java.lang.String r1 = "-206"
                java.lang.String r3 = r10.statusCode
                boolean r1 = android.text.TextUtils.equals(r1, r3)
                if (r1 != 0) goto L_0x0406
                r1 = 119(0x77, float:1.67E-43)
                r0[r1] = r2
            L_0x03e3:
                com.taobao.weex.WXSDKInstance r1 = r9.this$0
                com.taobao.weex.performance.WXInstanceExceptionRecord r1 = r1.getExceptionRecorder()
                r1.isDownLoadBundleFailed = r2
                r1 = 126(0x7e, float:1.77E-43)
                r0[r1] = r2
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_NETWORK_BUNDLE_DOWNLOAD_FAILED
                java.lang.String r1 = r1.getErrorCode()
                r3 = 127(0x7f, float:1.78E-43)
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                java.lang.String r10 = r10.errorMsg
                r3.onRenderError(r1, r10)
                r10 = 128(0x80, float:1.794E-43)
                r0[r10] = r2
                goto L_0x0492
            L_0x0406:
                r1 = 120(0x78, float:1.68E-43)
                r0[r1] = r2
                java.lang.String r1 = "user intercept: WX_DEGRAD_ERR_NETWORK_CHECK_CONTENT_LENGTH_FAILED"
                com.taobao.weex.utils.WXLogUtils.e(r1)
                r1 = 121(0x79, float:1.7E-43)
                r0[r1] = r2
                com.taobao.weex.common.WXErrorCode r1 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_NETWORK_CHECK_CONTENT_LENGTH_FAILED
                java.lang.String r1 = r1.getErrorCode()
                r3 = 122(0x7a, float:1.71E-43)
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                com.taobao.weex.common.WXErrorCode r5 = com.taobao.weex.common.WXErrorCode.WX_DEGRAD_ERR_NETWORK_CHECK_CONTENT_LENGTH_FAILED
                r6 = 123(0x7b, float:1.72E-43)
                r0[r6] = r2
                java.lang.String r5 = r5.getErrorCode()
                r4.append(r5)
                java.lang.String r5 = "|response.errorMsg=="
                r4.append(r5)
                java.lang.String r10 = r10.errorMsg
                r4.append(r10)
                java.lang.String r10 = r4.toString()
                r4 = 124(0x7c, float:1.74E-43)
                r0[r4] = r2
                r3.onRenderError(r1, r10)
                r10 = 125(0x7d, float:1.75E-43)
                r0[r10] = r2
                goto L_0x0492
            L_0x044b:
                r3 = 105(0x69, float:1.47E-43)
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                com.taobao.weex.performance.WXInstanceApm r3 = com.taobao.weex.WXSDKInstance.access$1000(r3)
                java.lang.String r4 = "wxEndDownLoadBundle"
                r3.onStage(r4)
                com.taobao.weex.common.WXRenderStrategy r3 = r9.flag
                com.taobao.weex.common.WXRenderStrategy r4 = com.taobao.weex.common.WXRenderStrategy.DATA_RENDER_BINARY
                if (r3 != r4) goto L_0x0476
                r3 = 106(0x6a, float:1.49E-43)
                r0[r3] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                java.lang.String r4 = r9.pageName
                byte[] r10 = r10.originalData
                java.util.Map<java.lang.String, java.lang.Object> r5 = r9.options
                java.lang.String r6 = r9.jsonInitData
                r3.render((java.lang.String) r4, (byte[]) r10, (java.util.Map<java.lang.String, java.lang.Object>) r5, (java.lang.String) r6)
                r10 = 107(0x6b, float:1.5E-43)
                r0[r10] = r2
                goto L_0x0492
            L_0x0476:
                java.lang.String r5 = new java.lang.String
                byte[] r10 = r10.originalData
                r5.<init>(r10)
                r10 = 108(0x6c, float:1.51E-43)
                r0[r10] = r2
                com.taobao.weex.WXSDKInstance r3 = r9.this$0
                java.lang.String r4 = r9.pageName
                java.util.Map<java.lang.String, java.lang.Object> r6 = r9.options
                java.lang.String r7 = r9.jsonInitData
                com.taobao.weex.common.WXRenderStrategy r8 = r9.flag
                r3.render((java.lang.String) r4, (java.lang.String) r5, (java.util.Map<java.lang.String, java.lang.Object>) r6, (java.lang.String) r7, (com.taobao.weex.common.WXRenderStrategy) r8)
                r10 = 109(0x6d, float:1.53E-43)
                r0[r10] = r2
            L_0x0492:
                java.lang.String r10 = "0"
                boolean r10 = r10.equals(r1)
                if (r10 == 0) goto L_0x049f
                r10 = 129(0x81, float:1.81E-43)
                r0[r10] = r2
                goto L_0x04b2
            L_0x049f:
                r10 = 130(0x82, float:1.82E-43)
                r0[r10] = r2
                com.taobao.weex.WXSDKInstance r10 = r9.this$0
                com.taobao.weex.performance.WXInstanceApm r10 = com.taobao.weex.WXSDKInstance.access$1000(r10)
                java.lang.String r3 = "wxErrorCode"
                r10.addProperty(r3, r1)
                r10 = 131(0x83, float:1.84E-43)
                r0[r10] = r2
            L_0x04b2:
                r10 = 132(0x84, float:1.85E-43)
                r0[r10] = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.WXSDKInstance.WXHttpListener.onHttpFinish(com.taobao.weex.common.WXResponse):void");
        }
    }

    private boolean isNet(String str) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (LogCategory.CATEGORY_NETWORK.equals(str)) {
            $jacocoInit[712] = true;
        } else if ("2g".equals(str)) {
            $jacocoInit[713] = true;
        } else if ("3g".equals(str)) {
            $jacocoInit[714] = true;
        } else {
            $jacocoInit[715] = true;
            if ("4g".equals(str)) {
                $jacocoInit[716] = true;
            } else if ("wifi".equals(str)) {
                $jacocoInit[717] = true;
            } else if ("other".equals(str)) {
                $jacocoInit[718] = true;
            } else {
                $jacocoInit[719] = true;
                if ("unknown".equals(str)) {
                    $jacocoInit[720] = true;
                } else {
                    z = false;
                    $jacocoInit[722] = true;
                    $jacocoInit[723] = true;
                    return z;
                }
            }
        }
        $jacocoInit[721] = true;
        z = true;
        $jacocoInit[723] = true;
        return z;
    }

    public String getTemplateInfo() {
        boolean[] $jacocoInit = $jacocoInit();
        String template = getTemplate();
        if (template == null) {
            $jacocoInit[724] = true;
            String str = " template md5 null ,httpHeader:" + JSONObject.toJSONString(this.responseHeaders);
            $jacocoInit[725] = true;
            return str;
        } else if (!TextUtils.isEmpty(template)) {
            $jacocoInit[726] = true;
            try {
                byte[] bytes = template.getBytes("UTF-8");
                $jacocoInit[729] = true;
                String md5 = WXFileUtils.md5(bytes);
                $jacocoInit[730] = true;
                String base64Md5 = WXFileUtils.base64Md5(bytes);
                $jacocoInit[731] = true;
                ArrayList arrayList = new ArrayList();
                $jacocoInit[732] = true;
                ArrayList arrayList2 = new ArrayList();
                $jacocoInit[733] = true;
                arrayList.add(md5);
                $jacocoInit[734] = true;
                arrayList2.add(base64Md5);
                $jacocoInit[735] = true;
                this.responseHeaders.put("templateSourceMD5", arrayList);
                $jacocoInit[736] = true;
                this.responseHeaders.put(SOURCE_TEMPLATE_BASE64_MD5, arrayList2);
                $jacocoInit[737] = true;
                StringBuilder sb = new StringBuilder();
                sb.append(" template md5 ");
                sb.append(md5);
                sb.append(" length ");
                sb.append(bytes.length);
                sb.append(" base64 md5 ");
                sb.append(base64Md5);
                sb.append(" response header ");
                Map<String, List<String>> map = this.responseHeaders;
                $jacocoInit[738] = true;
                sb.append(JSONObject.toJSONString(map));
                String sb2 = sb.toString();
                $jacocoInit[739] = true;
                return sb2;
            } catch (Exception unused) {
                $jacocoInit[740] = true;
                return "template md5 getBytes error";
            }
        } else {
            $jacocoInit[727] = true;
            String str2 = " template md5  length 0 ,httpHeader" + JSONObject.toJSONString(this.responseHeaders);
            $jacocoInit[728] = true;
            return str2;
        }
    }

    public boolean isContentMd5Match() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.responseHeaders == null) {
            $jacocoInit[741] = true;
            return true;
        }
        List list = this.responseHeaders.get("Content-Md5");
        if (list != null) {
            $jacocoInit[742] = true;
        } else {
            $jacocoInit[743] = true;
            list = this.responseHeaders.get(Common.k);
            $jacocoInit[744] = true;
        }
        if (list == null) {
            $jacocoInit[745] = true;
        } else if (list.size() <= 0) {
            $jacocoInit[746] = true;
        } else {
            String str = (String) list.get(0);
            $jacocoInit[748] = true;
            List list2 = this.responseHeaders.get(SOURCE_TEMPLATE_BASE64_MD5);
            if (list2 != null) {
                $jacocoInit[749] = true;
            } else {
                $jacocoInit[750] = true;
                getTemplateInfo();
                $jacocoInit[751] = true;
                list2 = this.responseHeaders.get(SOURCE_TEMPLATE_BASE64_MD5);
                $jacocoInit[752] = true;
            }
            if (list2 == null) {
                $jacocoInit[753] = true;
            } else if (list2.size() == 0) {
                $jacocoInit[754] = true;
            } else {
                boolean equals = str.equals(list2.get(0));
                $jacocoInit[756] = true;
                return equals;
            }
            $jacocoInit[755] = true;
            return true;
        }
        $jacocoInit[747] = true;
        return true;
    }

    public String getTemplate() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.templateRef == null) {
            $jacocoInit[757] = true;
            return null;
        }
        String str = (String) this.templateRef.get();
        $jacocoInit[758] = true;
        return str;
    }

    public void setTemplate(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.templateRef = new WeakReference<>(str);
        $jacocoInit[759] = true;
    }

    public void OnVSync() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXBridgeManager.getInstance().notifyLayout(getInstanceId())) {
            $jacocoInit[760] = true;
        } else {
            $jacocoInit[761] = true;
            WXBridgeManager.getInstance().post(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXSDKInstance this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-1691839287105236823L, "com/taobao/weex/WXSDKInstance$11", 2);
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
                    WXBridgeManager.getInstance().forceLayout(this.this$0.getInstanceId());
                    $jacocoInit[1] = true;
                }
            });
            $jacocoInit[762] = true;
        }
        $jacocoInit[763] = true;
    }

    public void addContentBoxMeasurement(long j, ContentBoxMeasurement contentBoxMeasurement) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mContentBoxMeasurements.put(Long.valueOf(j), contentBoxMeasurement);
        $jacocoInit[764] = true;
    }

    public ContentBoxMeasurement getContentBoxMeasurement(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        ContentBoxMeasurement contentBoxMeasurement = this.mContentBoxMeasurements.get(Long.valueOf(j));
        $jacocoInit[765] = true;
        return contentBoxMeasurement;
    }

    private void onInterceptInstanceEvent(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mInstanceOnFireEventInterceptorList == null) {
            $jacocoInit[766] = true;
            return;
        }
        $jacocoInit[767] = true;
        for (InstanceOnFireEventInterceptor onInterceptFireEvent : this.mInstanceOnFireEventInterceptorList) {
            $jacocoInit[768] = true;
            onInterceptFireEvent.onInterceptFireEvent(str, str2, str3, map, map2);
            $jacocoInit[769] = true;
        }
        $jacocoInit[770] = true;
    }

    public List<InstanceOnFireEventInterceptor> getInstanceOnFireEventInterceptorList() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mInstanceOnFireEventInterceptorList != null) {
            $jacocoInit[771] = true;
        } else {
            $jacocoInit[772] = true;
            this.mInstanceOnFireEventInterceptorList = new ArrayList();
            $jacocoInit[773] = true;
        }
        List<InstanceOnFireEventInterceptor> list = this.mInstanceOnFireEventInterceptorList;
        $jacocoInit[774] = true;
        return list;
    }

    public void addInstanceOnFireEventInterceptor(InstanceOnFireEventInterceptor instanceOnFireEventInterceptor) {
        boolean[] $jacocoInit = $jacocoInit();
        if (instanceOnFireEventInterceptor == null) {
            $jacocoInit[775] = true;
            return;
        }
        if (getInstanceOnFireEventInterceptorList().contains(instanceOnFireEventInterceptor)) {
            $jacocoInit[776] = true;
        } else {
            $jacocoInit[777] = true;
            getInstanceOnFireEventInterceptorList().add(instanceOnFireEventInterceptor);
            $jacocoInit[778] = true;
        }
        $jacocoInit[779] = true;
    }
}
