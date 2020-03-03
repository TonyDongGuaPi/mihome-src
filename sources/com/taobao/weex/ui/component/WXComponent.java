package com.taobao.weex.ui.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.JSONLexer;
import com.libra.virtualview.common.StringBase;
import com.taobao.weex.ComponentObserver;
import com.taobao.weex.IWXActivityStateListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXAccessibilityRoleAdapter;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.EventResult;
import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.NativeInvokeHelper;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.IWXObject;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.dom.transition.WXTransition;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.tracing.WXTracing;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.action.GraphicActionAnimation;
import com.taobao.weex.ui.action.GraphicActionUpdateStyle;
import com.taobao.weex.ui.action.GraphicPosition;
import com.taobao.weex.ui.action.GraphicSize;
import com.taobao.weex.ui.animation.WXAnimationBean;
import com.taobao.weex.ui.animation.WXAnimationModule;
import com.taobao.weex.ui.component.basic.WXBasicComponent;
import com.taobao.weex.ui.component.binding.Statements;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.component.list.template.jni.NativeRenderObjectUtils;
import com.taobao.weex.ui.component.pesudo.OnActivePseudoListner;
import com.taobao.weex.ui.component.pesudo.PesudoStatus;
import com.taobao.weex.ui.component.pesudo.TouchActivePseudoListener;
import com.taobao.weex.ui.flat.FlatComponent;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.flat.widget.AndroidViewWidget;
import com.taobao.weex.ui.flat.widget.Widget;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.taobao.weex.utils.BoxShadowUtil;
import com.taobao.weex.utils.WXDataStructureUtil;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXReflectionUtils;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.xiaomi.mistatistic.sdk.BaseService;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;
import org.apache.commons.lang.CharUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class WXComponent<T extends View> extends WXBasicComponent implements IWXActivityStateListener, IWXObject, OnActivePseudoListner {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String PROP_FIXED_SIZE = "fixedSize";
    public static final String PROP_FS_MATCH_PARENT = "m";
    public static final String PROP_FS_WRAP_CONTENT = "w";
    public static final String ROOT = "_root";
    public static final int STATE_ALL_FINISH = 2;
    public static final int STATE_DOM_FINISH = 0;
    public static final int STATE_UI_FINISH = 1;
    public static final String TYPE = "type";
    public static final int TYPE_COMMON = 0;
    public static final int TYPE_VIRTUAL = 1;
    @Nullable
    private ConcurrentLinkedQueue<Pair<String, Map<String, Object>>> animations;
    protected ContentBoxMeasurement contentBoxMeasurement;
    public int interactionAbsoluteX;
    public int interactionAbsoluteY;
    public boolean isIgnoreInteraction;
    private boolean isLastLayoutDirectionRTL;
    private boolean isUsing;
    private int mAbsoluteX;
    private int mAbsoluteY;
    private WXAnimationModule.AnimationHolder mAnimationHolder;
    private Set<String> mAppendEvents;
    private BorderDrawable mBackgroundDrawable;
    private WXComponent<T>.OnClickListenerImp mClickEventListener;
    private Context mContext;
    public int mDeepInComponentTree;
    private int mFixedProp;
    private List<OnFocusChangeListener> mFocusChangeListeners;
    protected WXGesture mGesture;
    @Nullable
    private Set<String> mGestureType;
    private IFComponentHolder mHolder;
    T mHost;
    private List<OnClickListener> mHostClickListeners;
    private WXSDKInstance mInstance;
    public boolean mIsAddElementToTree;
    private boolean mIsDestroyed;
    private boolean mIsDisabled;
    private String mLastBoxShadowId;
    private boolean mLazy;
    private boolean mNeedLayoutOnAnimation;
    private volatile WXVContainer mParent;
    private PesudoStatus mPesudoStatus;
    private int mPreRealHeight;
    private int mPreRealLeft;
    private int mPreRealRight;
    private int mPreRealTop;
    private int mPreRealWidth;
    private GraphicSize mPseudoResetGraphicSize;
    private Drawable mRippleBackground;
    private int mStickyOffset;
    public WXTracing.TraceInfo mTraceInfo;
    private WXTransition mTransition;
    private int mType;
    private String mViewTreeKey;
    private boolean waste;

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RenderState {
    }

    public interface OnClickListener {
        void onHostViewClick();
    }

    interface OnFocusChangeListener {
        void onFocusChange(boolean z);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-9121379446506992301L, "com/taobao/weex/ui/component/WXComponent", Constants.TradeCode.QUERY_NOTICE);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ WXSDKInstance access$100(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = wXComponent.mInstance;
        $jacocoInit[1307] = true;
        return wXSDKInstance;
    }

    static /* synthetic */ List access$200(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        List<OnFocusChangeListener> list = wXComponent.mFocusChangeListeners;
        $jacocoInit[1308] = true;
        return list;
    }

    static /* synthetic */ List access$300(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        List<OnClickListener> list = wXComponent.mHostClickListeners;
        $jacocoInit[1309] = true;
        return list;
    }

    static /* synthetic */ BorderDrawable access$400(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        BorderDrawable borderDrawable = wXComponent.mBackgroundDrawable;
        $jacocoInit[1310] = true;
        return borderDrawable;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WXComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, 0, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, int i, BasicComponentData basicComponentData) {
        super(basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        this.mFixedProp = 0;
        this.mAbsoluteY = 0;
        this.mAbsoluteX = 0;
        this.isLastLayoutDirectionRTL = false;
        this.mPreRealWidth = 0;
        this.mPreRealHeight = 0;
        this.mPreRealLeft = 0;
        this.mPreRealRight = 0;
        this.mPreRealTop = 0;
        this.mStickyOffset = 0;
        this.isUsing = false;
        this.mIsDestroyed = false;
        this.mIsDisabled = false;
        this.mType = 0;
        this.mNeedLayoutOnAnimation = false;
        this.mDeepInComponentTree = 0;
        this.mIsAddElementToTree = false;
        this.interactionAbsoluteX = 0;
        this.interactionAbsoluteY = 0;
        $jacocoInit[3] = true;
        this.mTraceInfo = new WXTracing.TraceInfo();
        this.waste = false;
        this.isIgnoreInteraction = false;
        this.mLazy = false;
        this.mInstance = wXSDKInstance;
        $jacocoInit[4] = true;
        this.mContext = this.mInstance.getContext();
        this.mParent = wXVContainer;
        this.mType = i;
        if (wXSDKInstance == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            setViewPortWidth(wXSDKInstance.getInstanceViewPortWidth());
            $jacocoInit[7] = true;
        }
        onCreate();
        $jacocoInit[8] = true;
        ComponentObserver componentObserver = getInstance().getComponentObserver();
        if (componentObserver == null) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
            componentObserver.onCreate(this);
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
    }

    /* access modifiers changed from: protected */
    public final void bindComponent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.bindComponent(wXComponent);
        $jacocoInit[13] = true;
        if (getInstance() == null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            setViewPortWidth(getInstance().getInstanceViewPortWidth());
            $jacocoInit[16] = true;
        }
        this.mParent = wXComponent.getParent();
        $jacocoInit[17] = true;
        this.mType = wXComponent.getType();
        $jacocoInit[18] = true;
    }

    /* access modifiers changed from: protected */
    public final void setContentBoxMeasurement(ContentBoxMeasurement contentBoxMeasurement2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.contentBoxMeasurement = contentBoxMeasurement2;
        $jacocoInit[19] = true;
        this.mInstance.addContentBoxMeasurement(getRenderObjectPtr(), contentBoxMeasurement2);
        $jacocoInit[20] = true;
        WXBridgeManager.getInstance().bindMeasurementToRenderObject(getRenderObjectPtr());
        $jacocoInit[21] = true;
    }

    public void setMarginsSupportRTL(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        marginLayoutParams.setMargins(i, i2, i3, i4);
        if (!(marginLayoutParams instanceof FrameLayout.LayoutParams)) {
            $jacocoInit[22] = true;
        } else {
            ((FrameLayout.LayoutParams) marginLayoutParams).gravity = 51;
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
    }

    public void updateStyles(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            updateProperties(wXComponent.getStyles());
            $jacocoInit[27] = true;
            applyBorder(wXComponent);
            $jacocoInit[28] = true;
        }
        $jacocoInit[29] = true;
    }

    public void updateStyles(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            updateProperties(map);
            $jacocoInit[32] = true;
            applyBorder(this);
            $jacocoInit[33] = true;
        }
        $jacocoInit[34] = true;
    }

    public void updateAttrs(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[35] = true;
        } else {
            $jacocoInit[36] = true;
            updateProperties(wXComponent.getAttrs());
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
    }

    public void updateAttrs(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            updateProperties(map);
            $jacocoInit[41] = true;
        }
        $jacocoInit[42] = true;
    }

    private void applyBorder(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        CSSShorthand border = wXComponent.getBorder();
        $jacocoInit[43] = true;
        float f = border.get(CSSShorthand.EDGE.LEFT);
        $jacocoInit[44] = true;
        float f2 = border.get(CSSShorthand.EDGE.TOP);
        $jacocoInit[45] = true;
        float f3 = border.get(CSSShorthand.EDGE.RIGHT);
        $jacocoInit[46] = true;
        float f4 = border.get(CSSShorthand.EDGE.BOTTOM);
        if (this.mHost == null) {
            $jacocoInit[47] = true;
            return;
        }
        setBorderWidth("borderLeftWidth", f);
        $jacocoInit[48] = true;
        setBorderWidth("borderTopWidth", f2);
        $jacocoInit[49] = true;
        setBorderWidth("borderRightWidth", f3);
        $jacocoInit[50] = true;
        setBorderWidth("borderBottomWidth", f4);
        $jacocoInit[51] = true;
    }

    public void setPadding(CSSShorthand cSSShorthand, CSSShorthand cSSShorthand2) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = (int) (cSSShorthand.get(CSSShorthand.EDGE.LEFT) + cSSShorthand2.get(CSSShorthand.EDGE.LEFT));
        $jacocoInit[52] = true;
        int i2 = (int) (cSSShorthand.get(CSSShorthand.EDGE.TOP) + cSSShorthand2.get(CSSShorthand.EDGE.TOP));
        $jacocoInit[53] = true;
        int i3 = (int) (cSSShorthand.get(CSSShorthand.EDGE.RIGHT) + cSSShorthand2.get(CSSShorthand.EDGE.RIGHT));
        $jacocoInit[54] = true;
        int i4 = (int) (cSSShorthand.get(CSSShorthand.EDGE.BOTTOM) + cSSShorthand2.get(CSSShorthand.EDGE.BOTTOM));
        $jacocoInit[55] = true;
        if (!(this instanceof FlatComponent)) {
            $jacocoInit[56] = true;
        } else {
            FlatComponent flatComponent = (FlatComponent) this;
            if (flatComponent.promoteToView(true)) {
                $jacocoInit[57] = true;
            } else {
                $jacocoInit[58] = true;
                flatComponent.getOrCreateFlatWidget().setContentBox(i, i2, i3, i4);
                $jacocoInit[59] = true;
                $jacocoInit[63] = true;
            }
        }
        if (this.mHost == null) {
            $jacocoInit[60] = true;
        } else {
            $jacocoInit[61] = true;
            this.mHost.setPadding(i, i2, i3, i4);
            $jacocoInit[62] = true;
        }
        $jacocoInit[63] = true;
    }

    private void applyEvents() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getEvents() == null) {
            $jacocoInit[64] = true;
        } else if (getEvents().isEmpty()) {
            $jacocoInit[65] = true;
        } else {
            WXEvent events = getEvents();
            $jacocoInit[67] = true;
            int size = events.size();
            int i = 0;
            $jacocoInit[68] = true;
            while (true) {
                if (i >= size) {
                    $jacocoInit[69] = true;
                    break;
                }
                $jacocoInit[70] = true;
                if (i >= events.size()) {
                    $jacocoInit[71] = true;
                    break;
                }
                $jacocoInit[72] = true;
                addEvent((String) events.get(i));
                i++;
                $jacocoInit[73] = true;
            }
            setActiveTouchListener();
            $jacocoInit[74] = true;
            return;
        }
        $jacocoInit[66] = true;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAppendEvents != null) {
            $jacocoInit[75] = true;
        } else {
            $jacocoInit[76] = true;
            this.mAppendEvents = new HashSet();
            $jacocoInit[77] = true;
        }
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[78] = true;
        } else if (this.mAppendEvents.contains(str)) {
            $jacocoInit[79] = true;
        } else {
            View realView = getRealView();
            $jacocoInit[81] = true;
            if (!str.equals(Constants.Event.LAYEROVERFLOW)) {
                $jacocoInit[82] = true;
            } else {
                $jacocoInit[83] = true;
                addLayerOverFlowListener(getRef());
                $jacocoInit[84] = true;
            }
            if (!str.equals("click")) {
                if (str.equals(Constants.Event.FOCUS)) {
                    $jacocoInit[90] = true;
                } else if (str.equals(Constants.Event.BLUR)) {
                    $jacocoInit[91] = true;
                } else if (!needGestureDetector(str)) {
                    Scrollable parentScroller = getParentScroller();
                    if (parentScroller == null) {
                        $jacocoInit[105] = true;
                        return;
                    } else if (str.equals(Constants.Event.APPEAR)) {
                        $jacocoInit[106] = true;
                        parentScroller.bindAppearEvent(this);
                        $jacocoInit[107] = true;
                    } else if (!str.equals(Constants.Event.DISAPPEAR)) {
                        $jacocoInit[108] = true;
                    } else {
                        $jacocoInit[109] = true;
                        parentScroller.bindDisappearEvent(this);
                        $jacocoInit[110] = true;
                    }
                } else if (realView == null) {
                    $jacocoInit[93] = true;
                    return;
                } else if (realView instanceof WXGestureObservable) {
                    if (this.mGesture != null) {
                        $jacocoInit[94] = true;
                    } else {
                        $jacocoInit[95] = true;
                        this.mGesture = new WXGesture(this, this.mContext);
                        $jacocoInit[96] = true;
                        boolean booleanValue = WXUtils.getBoolean(getAttrs().get(Constants.Name.PREVENT_MOVE_EVENT), false).booleanValue();
                        $jacocoInit[97] = true;
                        this.mGesture.setPreventMoveEvent(booleanValue);
                        $jacocoInit[98] = true;
                    }
                    if (this.mGestureType != null) {
                        $jacocoInit[99] = true;
                    } else {
                        $jacocoInit[100] = true;
                        this.mGestureType = new HashSet();
                        $jacocoInit[101] = true;
                    }
                    this.mGestureType.add(str);
                    $jacocoInit[102] = true;
                    ((WXGestureObservable) realView).registerGestureListener(this.mGesture);
                    $jacocoInit[103] = true;
                } else {
                    WXLogUtils.e(realView.getClass().getSimpleName() + " don't implement WXGestureObservable, so no gesture is supported.");
                    $jacocoInit[104] = true;
                }
                addFocusChangeListener(new OnFocusChangeListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-2953028333873190409L, "com/taobao/weex/ui/component/WXComponent$1", 6);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public void onFocusChange(boolean z) {
                        String str;
                        boolean[] $jacocoInit = $jacocoInit();
                        HashMap hashMap = new HashMap();
                        $jacocoInit[1] = true;
                        hashMap.put(BaseService.TIME_STAMP, Long.valueOf(System.currentTimeMillis()));
                        $jacocoInit[2] = true;
                        WXComponent wXComponent = this.this$0;
                        if (z) {
                            str = Constants.Event.FOCUS;
                            $jacocoInit[3] = true;
                        } else {
                            str = Constants.Event.BLUR;
                            $jacocoInit[4] = true;
                        }
                        wXComponent.fireEvent(str, hashMap);
                        $jacocoInit[5] = true;
                    }
                });
                $jacocoInit[92] = true;
            } else if (realView == null) {
                $jacocoInit[85] = true;
                return;
            } else {
                if (this.mClickEventListener != null) {
                    $jacocoInit[86] = true;
                } else {
                    $jacocoInit[87] = true;
                    this.mClickEventListener = new OnClickListenerImp(this, (AnonymousClass1) null);
                    $jacocoInit[88] = true;
                }
                addClickListener(this.mClickEventListener);
                $jacocoInit[89] = true;
            }
            this.mAppendEvents.add(str);
            $jacocoInit[111] = true;
            return;
        }
        $jacocoInit[80] = true;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        $jacocoInit()[112] = true;
    }

    public void bindHolder(IFComponentHolder iFComponentHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mHolder = iFComponentHolder;
        $jacocoInit[113] = true;
    }

    public WXSDKInstance getInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = this.mInstance;
        $jacocoInit[114] = true;
        return wXSDKInstance;
    }

    public Context getContext() {
        boolean[] $jacocoInit = $jacocoInit();
        Context context = this.mContext;
        $jacocoInit[115] = true;
        return context;
    }

    /* access modifiers changed from: protected */
    public final WXComponent findComponent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mInstance == null) {
            $jacocoInit[116] = true;
        } else if (str == null) {
            $jacocoInit[117] = true;
        } else {
            $jacocoInit[118] = true;
            WXSDKManager instance = WXSDKManager.getInstance();
            $jacocoInit[119] = true;
            WXRenderManager wXRenderManager = instance.getWXRenderManager();
            WXSDKInstance wXSDKInstance = this.mInstance;
            $jacocoInit[120] = true;
            WXComponent wXComponent = wXRenderManager.getWXComponent(wXSDKInstance.getInstanceId(), str);
            $jacocoInit[121] = true;
            return wXComponent;
        }
        $jacocoInit[122] = true;
        return null;
    }

    public String getAttrByKey(String str) {
        $jacocoInit()[123] = true;
        return "default";
    }

    public void postAnimation(WXAnimationModule.AnimationHolder animationHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAnimationHolder = animationHolder;
        $jacocoInit[124] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isFlatUIEnabled() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mParent == null) {
            $jacocoInit[125] = true;
        } else if (!this.mParent.isFlatUIEnabled()) {
            $jacocoInit[126] = true;
        } else {
            $jacocoInit[127] = true;
            z = true;
            $jacocoInit[129] = true;
            return z;
        }
        z = false;
        $jacocoInit[128] = true;
        $jacocoInit[129] = true;
        return z;
    }

    private class OnClickListenerImp implements OnClickListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        final /* synthetic */ WXComponent this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(4144191805906818919L, "com/taobao/weex/ui/component/WXComponent$OnClickListenerImp", 11);
            $jacocoData = a2;
            return a2;
        }

        private OnClickListenerImp(WXComponent wXComponent) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = wXComponent;
            $jacocoInit[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ OnClickListenerImp(WXComponent wXComponent, AnonymousClass1 r3) {
            this(wXComponent);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[10] = true;
        }

        public void onHostViewClick() {
            boolean[] $jacocoInit = $jacocoInit();
            HashMap newHashMapWithExpectedSize = WXDataStructureUtil.newHashMapWithExpectedSize(1);
            $jacocoInit[1] = true;
            HashMap newHashMapWithExpectedSize2 = WXDataStructureUtil.newHashMapWithExpectedSize(4);
            int[] iArr = new int[2];
            $jacocoInit[2] = true;
            this.this$0.mHost.getLocationOnScreen(iArr);
            $jacocoInit[3] = true;
            newHashMapWithExpectedSize2.put("x", Float.valueOf(WXViewUtils.getWebPxByWidth((float) iArr[0], WXComponent.access$100(this.this$0).getInstanceViewPortWidth())));
            $jacocoInit[4] = true;
            newHashMapWithExpectedSize2.put(Constants.Name.Y, Float.valueOf(WXViewUtils.getWebPxByWidth((float) iArr[1], WXComponent.access$100(this.this$0).getInstanceViewPortWidth())));
            $jacocoInit[5] = true;
            newHashMapWithExpectedSize2.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth(this.this$0.getLayoutWidth(), WXComponent.access$100(this.this$0).getInstanceViewPortWidth())));
            $jacocoInit[6] = true;
            newHashMapWithExpectedSize2.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth(this.this$0.getLayoutHeight(), WXComponent.access$100(this.this$0).getInstanceViewPortWidth())));
            $jacocoInit[7] = true;
            newHashMapWithExpectedSize.put("position", newHashMapWithExpectedSize2);
            $jacocoInit[8] = true;
            this.this$0.fireEvent("click", newHashMapWithExpectedSize);
            $jacocoInit[9] = true;
        }
    }

    public String getInstanceId() {
        boolean[] $jacocoInit = $jacocoInit();
        String instanceId = this.mInstance.getInstanceId();
        $jacocoInit[130] = true;
        return instanceId;
    }

    public Rect getComponentSize() {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = new Rect();
        if (this.mHost == null) {
            $jacocoInit[131] = true;
        } else {
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            $jacocoInit[132] = true;
            this.mHost.getLocationOnScreen(iArr);
            $jacocoInit[133] = true;
            this.mInstance.getContainerView().getLocationOnScreen(iArr2);
            int i = iArr[0] - iArr2[0];
            int i2 = (iArr[1] - this.mStickyOffset) - iArr2[1];
            $jacocoInit[134] = true;
            $jacocoInit[135] = true;
            $jacocoInit[136] = true;
            rect.set(i, i2, ((int) getLayoutWidth()) + i, ((int) getLayoutHeight()) + i2);
            $jacocoInit[137] = true;
        }
        $jacocoInit[138] = true;
        return rect;
    }

    public final void invoke(String str, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        Invoker methodInvoker = this.mHolder.getMethodInvoker(str);
        if (methodInvoker != null) {
            try {
                $jacocoInit[139] = true;
                WXSDKInstance instance = getInstance();
                $jacocoInit[140] = true;
                NativeInvokeHelper nativeInvokeHelper = instance.getNativeInvokeHelper();
                $jacocoInit[141] = true;
                nativeInvokeHelper.invoke(this, methodInvoker, jSONArray);
                $jacocoInit[142] = true;
            } catch (Exception e) {
                $jacocoInit[143] = true;
                WXLogUtils.e("[WXComponent] updateProperties :class:" + getClass() + "method:" + methodInvoker.toString() + " function " + WXLogUtils.getStackTrace(e));
                $jacocoInit[144] = true;
            }
        } else {
            onInvokeUnknownMethod(str, jSONArray);
            $jacocoInit[145] = true;
        }
        $jacocoInit[146] = true;
    }

    /* access modifiers changed from: protected */
    public void onInvokeUnknownMethod(String str, JSONArray jSONArray) {
        $jacocoInit()[147] = true;
    }

    public final void fireEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, (Map<String, Object>) null);
        $jacocoInit[148] = true;
    }

    public final void fireEvent(String str, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXUtils.getBoolean(getAttrs().get("fireEventSyn"), false).booleanValue()) {
            $jacocoInit[149] = true;
            fireEventWait(str, map);
            $jacocoInit[150] = true;
        } else {
            fireEvent(str, map, (Map<String, Object>) null, (EventResult) null);
            $jacocoInit[151] = true;
        }
        $jacocoInit[152] = true;
    }

    public final EventResult fireEventWait(String str, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        $jacocoInit[153] = true;
        AnonymousClass2 r3 = new EventResult(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-634695297258888846L, "com/taobao/weex/ui/component/WXComponent$2", 3);
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
            $jacocoInit[154] = true;
            fireEvent(str, map, (Map<String, Object>) null, r3);
            $jacocoInit[155] = true;
            countDownLatch.await(50, TimeUnit.MILLISECONDS);
            $jacocoInit[156] = true;
            return r3;
        } catch (Exception e) {
            $jacocoInit[157] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[158] = true;
            } else {
                $jacocoInit[159] = true;
                WXLogUtils.e("fireEventWait", (Throwable) e);
                $jacocoInit[160] = true;
            }
            $jacocoInit[161] = true;
            return r3;
        }
    }

    /* access modifiers changed from: protected */
    public final void fireEvent(String str, Map<String, Object> map, Map<String, Object> map2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent(str, map, map2, (EventResult) null);
        $jacocoInit[162] = true;
    }

    private final void fireEvent(String str, Map<String, Object> map, Map<String, Object> map2, EventResult eventResult) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mInstance == null) {
            $jacocoInit[163] = true;
        } else {
            List list = null;
            $jacocoInit[164] = true;
            if (getEvents() == null) {
                $jacocoInit[165] = true;
            } else if (getEvents().getEventBindingArgsValues() == null) {
                $jacocoInit[166] = true;
            } else {
                $jacocoInit[167] = true;
                list = getEvents().getEventBindingArgsValues().get(str);
                $jacocoInit[168] = true;
            }
            List list2 = list;
            if (map == null) {
                $jacocoInit[169] = true;
            } else {
                $jacocoInit[170] = true;
                String componentId = Statements.getComponentId(this);
                if (componentId == null) {
                    $jacocoInit[171] = true;
                } else {
                    $jacocoInit[172] = true;
                    map.put("componentId", componentId);
                    $jacocoInit[173] = true;
                }
            }
            this.mInstance.fireEvent(getRef(), str, map, map2, list2, eventResult);
            $jacocoInit[174] = true;
        }
        $jacocoInit[175] = true;
    }

    public Object findTypeParent(WXComponent wXComponent, Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getClass() == cls) {
            $jacocoInit[176] = true;
            return wXComponent;
        }
        if (wXComponent.getParent() == null) {
            $jacocoInit[177] = true;
        } else {
            $jacocoInit[178] = true;
            findTypeParent(wXComponent.getParent(), cls);
            $jacocoInit[179] = true;
        }
        $jacocoInit[180] = true;
        return null;
    }

    public boolean isLazy() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLazy) {
            $jacocoInit[181] = true;
            return true;
        }
        if (this.mParent == null) {
            $jacocoInit[182] = true;
        } else if (!this.mParent.isLazy()) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            z = true;
            $jacocoInit[186] = true;
            return z;
        }
        z = false;
        $jacocoInit[185] = true;
        $jacocoInit[186] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public final void addFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onFocusChangeListener == null) {
            $jacocoInit[187] = true;
        } else {
            View realView = getRealView();
            if (realView == null) {
                $jacocoInit[188] = true;
            } else {
                if (this.mFocusChangeListeners != null) {
                    $jacocoInit[189] = true;
                } else {
                    $jacocoInit[190] = true;
                    this.mFocusChangeListeners = new ArrayList();
                    $jacocoInit[191] = true;
                    realView.setFocusable(true);
                    $jacocoInit[192] = true;
                    realView.setOnFocusChangeListener(new View.OnFocusChangeListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ WXComponent this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(8355997773656534765L, "com/taobao/weex/ui/component/WXComponent$3", 7);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        public void onFocusChange(View view, boolean z) {
                            boolean[] $jacocoInit = $jacocoInit();
                            $jacocoInit[1] = true;
                            for (OnFocusChangeListener onFocusChangeListener : WXComponent.access$200(this.this$0)) {
                                if (onFocusChangeListener == null) {
                                    $jacocoInit[2] = true;
                                } else {
                                    $jacocoInit[3] = true;
                                    onFocusChangeListener.onFocusChange(z);
                                    $jacocoInit[4] = true;
                                }
                                $jacocoInit[5] = true;
                            }
                            $jacocoInit[6] = true;
                        }
                    });
                    $jacocoInit[193] = true;
                }
                this.mFocusChangeListeners.add(onFocusChangeListener);
                $jacocoInit[194] = true;
            }
        }
        $jacocoInit[195] = true;
    }

    /* access modifiers changed from: protected */
    public final void addClickListener(OnClickListener onClickListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onClickListener == null) {
            $jacocoInit[196] = true;
        } else {
            View realView = getRealView();
            if (realView == null) {
                $jacocoInit[197] = true;
            } else {
                if (this.mHostClickListeners != null) {
                    $jacocoInit[198] = true;
                } else {
                    $jacocoInit[199] = true;
                    this.mHostClickListeners = new ArrayList();
                    $jacocoInit[200] = true;
                    realView.setOnClickListener(new View.OnClickListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ WXComponent this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(4470548227169927132L, "com/taobao/weex/ui/component/WXComponent$4", 10);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        public void onClick(View view) {
                            boolean[] $jacocoInit = $jacocoInit();
                            if (this.this$0.mGesture == null) {
                                $jacocoInit[1] = true;
                            } else if (!this.this$0.mGesture.isTouchEventConsumedByAdvancedGesture()) {
                                $jacocoInit[2] = true;
                            } else {
                                $jacocoInit[3] = true;
                                return;
                            }
                            $jacocoInit[4] = true;
                            for (OnClickListener onClickListener : WXComponent.access$300(this.this$0)) {
                                if (onClickListener == null) {
                                    $jacocoInit[5] = true;
                                } else {
                                    $jacocoInit[6] = true;
                                    onClickListener.onHostViewClick();
                                    $jacocoInit[7] = true;
                                }
                                $jacocoInit[8] = true;
                            }
                            $jacocoInit[9] = true;
                        }
                    });
                    $jacocoInit[201] = true;
                }
                this.mHostClickListeners.add(onClickListener);
                $jacocoInit[202] = true;
            }
        }
        $jacocoInit[203] = true;
    }

    /* access modifiers changed from: protected */
    public final void removeClickListener(OnClickListener onClickListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mHostClickListeners.remove(onClickListener);
        $jacocoInit[204] = true;
    }

    public void bindData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isLazy()) {
            $jacocoInit[205] = true;
        } else {
            if (wXComponent != null) {
                $jacocoInit[206] = true;
            } else {
                $jacocoInit[207] = true;
                wXComponent = this;
            }
            bindComponent(wXComponent);
            $jacocoInit[208] = true;
            updateStyles(wXComponent);
            $jacocoInit[209] = true;
            updateAttrs(wXComponent);
            $jacocoInit[210] = true;
            updateExtra(wXComponent.getExtra());
            $jacocoInit[211] = true;
        }
        $jacocoInit[212] = true;
    }

    public void applyLayoutAndEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isLazy()) {
            $jacocoInit[213] = true;
        } else {
            if (wXComponent != null) {
                $jacocoInit[214] = true;
            } else {
                $jacocoInit[215] = true;
                wXComponent = this;
            }
            bindComponent(wXComponent);
            $jacocoInit[216] = true;
            setSafeLayout(wXComponent);
            $jacocoInit[217] = true;
            setPadding(wXComponent.getPadding(), wXComponent.getBorder());
            $jacocoInit[218] = true;
            applyEvents();
            $jacocoInit[219] = true;
        }
        $jacocoInit[220] = true;
    }

    public void setDemission(GraphicSize graphicSize, GraphicPosition graphicPosition) {
        boolean[] $jacocoInit = $jacocoInit();
        setLayoutPosition(graphicPosition);
        $jacocoInit[221] = true;
        setLayoutSize(graphicSize);
        $jacocoInit[222] = true;
    }

    public void updateDemission(float f, float f2, float f3, float f4, float f5, float f6) {
        boolean[] $jacocoInit = $jacocoInit();
        getLayoutPosition().update(f, f2, f3, f4);
        $jacocoInit[223] = true;
        getLayoutSize().update(f6, f5);
        $jacocoInit[224] = true;
    }

    public void applyLayoutOnly() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isLazy()) {
            $jacocoInit[225] = true;
        } else {
            $jacocoInit[226] = true;
            setSafeLayout(this);
            $jacocoInit[227] = true;
            setPadding(getPadding(), getBorder());
            $jacocoInit[228] = true;
        }
        $jacocoInit[229] = true;
    }

    public void refreshData(WXComponent wXComponent) {
        $jacocoInit()[230] = true;
    }

    @Deprecated
    public void updateProperties(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[231] = true;
        } else {
            if (this.mHost != null) {
                $jacocoInit[232] = true;
            } else if (isVirtualComponent()) {
                $jacocoInit[233] = true;
            } else {
                $jacocoInit[234] = true;
            }
            $jacocoInit[236] = true;
            for (Map.Entry next : map.entrySet()) {
                $jacocoInit[237] = true;
                String str = (String) next.getKey();
                $jacocoInit[238] = true;
                Object value = next.getValue();
                $jacocoInit[239] = true;
                String string = WXUtils.getString(value, (String) null);
                if (str == null) {
                    $jacocoInit[240] = true;
                    String instanceId = getInstanceId();
                    WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_NULL_KEY;
                    WXErrorCode wXErrorCode2 = WXErrorCode.WX_RENDER_ERR_NULL_KEY;
                    $jacocoInit[241] = true;
                    String errorMsg = wXErrorCode2.getErrorMsg();
                    $jacocoInit[242] = true;
                    WXExceptionUtils.commitCriticalExceptionRT(instanceId, wXErrorCode, "updateProperties", errorMsg, (Map<String, String>) null);
                    $jacocoInit[243] = true;
                } else {
                    if (!TextUtils.isEmpty(string)) {
                        $jacocoInit[244] = true;
                    } else {
                        $jacocoInit[245] = true;
                        value = convertEmptyProperty(str, string);
                        $jacocoInit[246] = true;
                    }
                    if (setProperty(str, value)) {
                        $jacocoInit[247] = true;
                    } else if (this.mHolder == null) {
                        $jacocoInit[248] = true;
                        return;
                    } else {
                        Invoker propertyInvoker = this.mHolder.getPropertyInvoker(str);
                        if (propertyInvoker == null) {
                            $jacocoInit[249] = true;
                        } else {
                            try {
                                $jacocoInit[250] = true;
                                Type[] parameterTypes = propertyInvoker.getParameterTypes();
                                if (parameterTypes.length == 1) {
                                    $jacocoInit[251] = true;
                                    Object parseArgument = WXReflectionUtils.parseArgument(parameterTypes[0], value);
                                    $jacocoInit[254] = true;
                                    propertyInvoker.invoke(this, parseArgument);
                                    $jacocoInit[255] = true;
                                } else {
                                    $jacocoInit[252] = true;
                                    WXLogUtils.e("[WXComponent] setX method only one parameter：" + propertyInvoker);
                                    $jacocoInit[253] = true;
                                    return;
                                }
                            } catch (Exception e) {
                                $jacocoInit[256] = true;
                                WXLogUtils.e("[WXComponent] updateProperties :class:" + getClass() + "method:" + propertyInvoker.toString() + " function " + WXLogUtils.getStackTrace(e));
                                $jacocoInit[257] = true;
                            }
                        }
                    }
                }
                $jacocoInit[258] = true;
            }
            readyToRender();
            if (!(this instanceof FlatComponent)) {
                $jacocoInit[259] = true;
            } else if (this.mBackgroundDrawable == null) {
                $jacocoInit[260] = true;
            } else {
                FlatComponent flatComponent = (FlatComponent) this;
                $jacocoInit[261] = true;
                if (flatComponent.promoteToView(true)) {
                    $jacocoInit[262] = true;
                } else {
                    $jacocoInit[263] = true;
                    if (flatComponent.getOrCreateFlatWidget() instanceof AndroidViewWidget) {
                        $jacocoInit[264] = true;
                    } else {
                        $jacocoInit[265] = true;
                        flatComponent.getOrCreateFlatWidget().setBackgroundAndBorder(this.mBackgroundDrawable);
                        $jacocoInit[266] = true;
                    }
                }
            }
            $jacocoInit[267] = true;
            return;
        }
        $jacocoInit[235] = true;
    }

    /* access modifiers changed from: protected */
    public boolean setProperty(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[268] = true;
            return true;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1989576717:
                if (str.equals("borderRightColor")) {
                    c = 18;
                    $jacocoInit[307] = true;
                    break;
                } else {
                    $jacocoInit[306] = true;
                    break;
                }
            case -1974639039:
                if (str.equals(Constants.Name.BORDER_RIGHT_STYLE)) {
                    c = 12;
                    $jacocoInit[295] = true;
                    break;
                } else {
                    $jacocoInit[294] = true;
                    break;
                }
            case -1971292586:
                if (str.equals("borderRightWidth")) {
                    c = '2';
                    $jacocoInit[371] = true;
                    break;
                } else {
                    $jacocoInit[370] = true;
                    break;
                }
            case StringBase.q:
                if (str.equals("paddingLeft")) {
                    c = '-';
                    $jacocoInit[361] = true;
                    break;
                } else {
                    $jacocoInit[360] = true;
                    break;
                }
            case -1470826662:
                if (str.equals("borderTopColor")) {
                    c = 17;
                    $jacocoInit[305] = true;
                    break;
                } else {
                    $jacocoInit[304] = true;
                    break;
                }
            case -1455888984:
                if (str.equals(Constants.Name.BORDER_TOP_STYLE)) {
                    c = 15;
                    $jacocoInit[301] = true;
                    break;
                } else {
                    $jacocoInit[300] = true;
                    break;
                }
            case -1452542531:
                if (str.equals("borderTopWidth")) {
                    c = '1';
                    $jacocoInit[369] = true;
                    break;
                } else {
                    $jacocoInit[368] = true;
                    break;
                }
            case -1383228885:
                if (str.equals("bottom")) {
                    c = '8';
                    $jacocoInit[383] = true;
                    break;
                } else {
                    $jacocoInit[382] = true;
                    break;
                }
            case StringBase.ai:
                if (str.equals("minWidth")) {
                    c = 27;
                    $jacocoInit[325] = true;
                    break;
                } else {
                    $jacocoInit[324] = true;
                    break;
                }
            case -1308858324:
                if (str.equals("borderBottomColor")) {
                    c = 19;
                    $jacocoInit[309] = true;
                    break;
                } else {
                    $jacocoInit[308] = true;
                    break;
                }
            case -1293920646:
                if (str.equals(Constants.Name.BORDER_BOTTOM_STYLE)) {
                    c = CharUtils.b;
                    $jacocoInit[297] = true;
                    break;
                } else {
                    $jacocoInit[296] = true;
                    break;
                }
            case -1290574193:
                if (str.equals("borderBottomWidth")) {
                    c = '3';
                    $jacocoInit[373] = true;
                    break;
                } else {
                    $jacocoInit[372] = true;
                    break;
                }
            case -1267206133:
                if (str.equals("opacity")) {
                    c = 5;
                    $jacocoInit[281] = true;
                    break;
                } else {
                    $jacocoInit[280] = true;
                    break;
                }
            case StringBase.bZ:
                if (str.equals("borderTopLeftRadius")) {
                    c = 7;
                    $jacocoInit[285] = true;
                    break;
                } else {
                    $jacocoInit[284] = true;
                    break;
                }
            case -1221029593:
                if (str.equals("height")) {
                    c = 29;
                    $jacocoInit[329] = true;
                    break;
                } else {
                    $jacocoInit[328] = true;
                    break;
                }
            case -1111969773:
                if (str.equals(Constants.Name.ARIA_HIDDEN)) {
                    c = 25;
                    $jacocoInit[321] = true;
                    break;
                } else {
                    $jacocoInit[320] = true;
                    break;
                }
            case -1081309778:
                if (str.equals("margin")) {
                    c = Typography.c;
                    $jacocoInit[347] = true;
                    break;
                } else {
                    $jacocoInit[346] = true;
                    break;
                }
            case StringBase.Z:
                if (str.equals("alignItems")) {
                    c = ' ';
                    $jacocoInit[335] = true;
                    break;
                } else {
                    $jacocoInit[334] = true;
                    break;
                }
            case -1044792121:
                if (str.equals("marginTop")) {
                    c = Operators.SINGLE_QUOTE;
                    $jacocoInit[349] = true;
                    break;
                } else {
                    $jacocoInit[348] = true;
                    break;
                }
            case StringBase.V:
                if (str.equals("flexDirection")) {
                    c = '#';
                    $jacocoInit[341] = true;
                    break;
                } else {
                    $jacocoInit[340] = true;
                    break;
                }
            case -906066005:
                if (str.equals("maxHeight")) {
                    c = 31;
                    $jacocoInit[333] = true;
                    break;
                } else {
                    $jacocoInit[332] = true;
                    break;
                }
            case -863700117:
                if (str.equals(Constants.Name.ARIA_LABEL)) {
                    c = 24;
                    $jacocoInit[319] = true;
                    break;
                } else {
                    $jacocoInit[318] = true;
                    break;
                }
            case StringBase.cf:
                if (str.equals("padding")) {
                    c = '+';
                    $jacocoInit[357] = true;
                    break;
                } else {
                    $jacocoInit[356] = true;
                    break;
                }
            case -289173127:
                if (str.equals("marginBottom")) {
                    c = '*';
                    $jacocoInit[355] = true;
                    break;
                } else {
                    $jacocoInit[354] = true;
                    break;
                }
            case -242276144:
                if (str.equals("borderLeftColor")) {
                    c = 20;
                    $jacocoInit[311] = true;
                    break;
                } else {
                    $jacocoInit[310] = true;
                    break;
                }
            case -227338466:
                if (str.equals(Constants.Name.BORDER_LEFT_STYLE)) {
                    c = 14;
                    $jacocoInit[299] = true;
                    break;
                } else {
                    $jacocoInit[298] = true;
                    break;
                }
            case -223992013:
                if (str.equals("borderLeftWidth")) {
                    c = '4';
                    $jacocoInit[375] = true;
                    break;
                } else {
                    $jacocoInit[374] = true;
                    break;
                }
            case StringBase.aj:
                if (str.equals("minHeight")) {
                    c = 30;
                    $jacocoInit[331] = true;
                    break;
                } else {
                    $jacocoInit[330] = true;
                    break;
                }
            case -4379043:
                if (str.equals("elevation")) {
                    c = 22;
                    $jacocoInit[315] = true;
                    break;
                } else {
                    $jacocoInit[314] = true;
                    break;
                }
            case 115029:
                if (str.equals("top")) {
                    c = '6';
                    $jacocoInit[379] = true;
                    break;
                } else {
                    $jacocoInit[378] = true;
                    break;
                }
            case 3145721:
                if (str.equals("flex")) {
                    c = '\"';
                    $jacocoInit[339] = true;
                    break;
                } else {
                    $jacocoInit[338] = true;
                    break;
                }
            case 3317767:
                if (str.equals("left")) {
                    c = '5';
                    $jacocoInit[377] = true;
                    break;
                } else {
                    $jacocoInit[376] = true;
                    break;
                }
            case 3506294:
                if (str.equals(Constants.Name.ROLE)) {
                    c = Operators.CONDITION_IF_MIDDLE;
                    $jacocoInit[387] = true;
                    break;
                } else {
                    $jacocoInit[386] = true;
                    break;
                }
            case StringBase.s:
                if (str.equals("paddingTop")) {
                    c = ',';
                    $jacocoInit[359] = true;
                    break;
                } else {
                    $jacocoInit[358] = true;
                    break;
                }
            case 108511772:
                if (str.equals("right")) {
                    c = '7';
                    $jacocoInit[381] = true;
                    break;
                } else {
                    $jacocoInit[380] = true;
                    break;
                }
            case 113126854:
                if (str.equals("width")) {
                    c = JSONLexer.EOI;
                    $jacocoInit[323] = true;
                    break;
                } else {
                    $jacocoInit[322] = true;
                    break;
                }
            case StringBase.t:
                if (str.equals("paddingBottom")) {
                    c = IOUtils.f15883a;
                    $jacocoInit[365] = true;
                    break;
                } else {
                    $jacocoInit[364] = true;
                    break;
                }
            case 270940796:
                if (str.equals(Constants.Name.DISABLED)) {
                    $jacocoInit[273] = true;
                    c = 1;
                    break;
                } else {
                    $jacocoInit[272] = true;
                    break;
                }
            case StringBase.ca:
                if (str.equals("borderTopRightRadius")) {
                    c = 8;
                    $jacocoInit[287] = true;
                    break;
                } else {
                    $jacocoInit[286] = true;
                    break;
                }
            case 400381634:
                if (str.equals("maxWidth")) {
                    c = 28;
                    $jacocoInit[327] = true;
                    break;
                } else {
                    $jacocoInit[326] = true;
                    break;
                }
            case StringBase.cb:
                if (str.equals("borderBottomLeftRadius")) {
                    c = 10;
                    $jacocoInit[291] = true;
                    break;
                } else {
                    $jacocoInit[290] = true;
                    break;
                }
            case StringBase.cc:
                if (str.equals("borderBottomRightRadius")) {
                    c = 9;
                    $jacocoInit[289] = true;
                    break;
                } else {
                    $jacocoInit[288] = true;
                    break;
                }
            case StringBase.r:
                if (str.equals("paddingRight")) {
                    c = '.';
                    $jacocoInit[363] = true;
                    break;
                } else {
                    $jacocoInit[362] = true;
                    break;
                }
            case 717381201:
                if (str.equals(Constants.Name.PREVENT_MOVE_EVENT)) {
                    $jacocoInit[271] = true;
                    c = 0;
                    break;
                } else {
                    $jacocoInit[270] = true;
                    break;
                }
            case StringBase.bJ:
                if (str.equals("borderColor")) {
                    c = 16;
                    $jacocoInit[303] = true;
                    break;
                } else {
                    $jacocoInit[302] = true;
                    break;
                }
            case 737768677:
                if (str.equals(Constants.Name.BORDER_STYLE)) {
                    c = 11;
                    $jacocoInit[293] = true;
                    break;
                } else {
                    $jacocoInit[292] = true;
                    break;
                }
            case StringBase.bI:
                if (str.equals("borderWidth")) {
                    c = '0';
                    $jacocoInit[367] = true;
                    break;
                } else {
                    $jacocoInit[366] = true;
                    break;
                }
            case 743055051:
                if (str.equals(Constants.Name.BOX_SHADOW)) {
                    c = '9';
                    $jacocoInit[385] = true;
                    break;
                } else {
                    $jacocoInit[384] = true;
                    break;
                }
            case 747463061:
                if (str.equals(PROP_FIXED_SIZE)) {
                    c = 23;
                    $jacocoInit[317] = true;
                    break;
                } else {
                    $jacocoInit[316] = true;
                    break;
                }
            case 747804969:
                if (str.equals("position")) {
                    c = 2;
                    $jacocoInit[275] = true;
                    break;
                } else {
                    $jacocoInit[274] = true;
                    break;
                }
            case 975087886:
                if (str.equals("marginRight")) {
                    c = Operators.BRACKET_END;
                    $jacocoInit[353] = true;
                    break;
                } else {
                    $jacocoInit[352] = true;
                    break;
                }
            case 1287124693:
                if (str.equals("backgroundColor")) {
                    c = 3;
                    $jacocoInit[277] = true;
                    break;
                } else {
                    $jacocoInit[276] = true;
                    break;
                }
            case StringBase.bv:
                if (str.equals(Constants.Name.BACKGROUND_IMAGE)) {
                    c = 4;
                    $jacocoInit[279] = true;
                    break;
                } else {
                    $jacocoInit[278] = true;
                    break;
                }
            case StringBase.bY:
                if (str.equals("borderRadius")) {
                    c = 6;
                    $jacocoInit[283] = true;
                    break;
                } else {
                    $jacocoInit[282] = true;
                    break;
                }
            case StringBase.W:
                if (str.equals("flexWrap")) {
                    c = '%';
                    $jacocoInit[345] = true;
                    break;
                } else {
                    $jacocoInit[344] = true;
                    break;
                }
            case StringBase.ab:
                if (str.equals("alignSelf")) {
                    c = '!';
                    $jacocoInit[337] = true;
                    break;
                } else {
                    $jacocoInit[336] = true;
                    break;
                }
            case StringBase.Y:
                if (str.equals("justifyContent")) {
                    c = '$';
                    $jacocoInit[343] = true;
                    break;
                } else {
                    $jacocoInit[342] = true;
                    break;
                }
            case StringBase.aU:
                if (str.equals("visibility")) {
                    c = 21;
                    $jacocoInit[313] = true;
                    break;
                } else {
                    $jacocoInit[312] = true;
                    break;
                }
            case 1970934485:
                if (str.equals("marginLeft")) {
                    c = Operators.BRACKET_START;
                    $jacocoInit[351] = true;
                    break;
                } else {
                    $jacocoInit[350] = true;
                    break;
                }
            default:
                $jacocoInit[269] = true;
                break;
        }
        switch (c) {
            case 0:
                if (this.mGesture == null) {
                    $jacocoInit[389] = true;
                } else {
                    $jacocoInit[390] = true;
                    this.mGesture.setPreventMoveEvent(WXUtils.getBoolean(obj, false).booleanValue());
                    $jacocoInit[391] = true;
                }
                $jacocoInit[392] = true;
                return true;
            case 1:
                Boolean bool = WXUtils.getBoolean(obj, (Boolean) null);
                if (bool == null) {
                    $jacocoInit[393] = true;
                } else {
                    $jacocoInit[394] = true;
                    setDisabled(bool.booleanValue());
                    $jacocoInit[395] = true;
                    setPseudoClassStatus(Constants.PSEUDO.DISABLED, bool.booleanValue());
                    $jacocoInit[396] = true;
                }
                $jacocoInit[397] = true;
                return true;
            case 2:
                String string = WXUtils.getString(obj, (String) null);
                if (string == null) {
                    $jacocoInit[398] = true;
                } else {
                    $jacocoInit[399] = true;
                    setSticky(string);
                    $jacocoInit[400] = true;
                }
                $jacocoInit[401] = true;
                return true;
            case 3:
                String string2 = WXUtils.getString(obj, (String) null);
                if (string2 == null) {
                    $jacocoInit[402] = true;
                } else {
                    $jacocoInit[403] = true;
                    setBackgroundColor(string2);
                    $jacocoInit[404] = true;
                }
                $jacocoInit[405] = true;
                return true;
            case 4:
                String string3 = WXUtils.getString(obj, (String) null);
                if (string3 == null) {
                    $jacocoInit[406] = true;
                } else if (this.mHost == null) {
                    $jacocoInit[407] = true;
                } else {
                    $jacocoInit[408] = true;
                    setBackgroundImage(string3);
                    $jacocoInit[409] = true;
                }
                $jacocoInit[410] = true;
                return true;
            case 5:
                Float f = WXUtils.getFloat(obj, (Float) null);
                if (f == null) {
                    $jacocoInit[411] = true;
                } else {
                    $jacocoInit[412] = true;
                    setOpacity(f.floatValue());
                    $jacocoInit[413] = true;
                }
                $jacocoInit[414] = true;
                return true;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                Float f2 = WXUtils.getFloat(obj, (Float) null);
                if (f2 == null) {
                    $jacocoInit[415] = true;
                } else {
                    $jacocoInit[416] = true;
                    setBorderRadius(str, f2.floatValue());
                    $jacocoInit[417] = true;
                }
                $jacocoInit[418] = true;
                return true;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                String string4 = WXUtils.getString(obj, (String) null);
                if (string4 == null) {
                    $jacocoInit[419] = true;
                } else {
                    $jacocoInit[420] = true;
                    setBorderStyle(str, string4);
                    $jacocoInit[421] = true;
                }
                $jacocoInit[422] = true;
                return true;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                String string5 = WXUtils.getString(obj, (String) null);
                if (string5 == null) {
                    $jacocoInit[423] = true;
                } else {
                    $jacocoInit[424] = true;
                    setBorderColor(str, string5);
                    $jacocoInit[425] = true;
                }
                $jacocoInit[426] = true;
                return true;
            case 21:
                String string6 = WXUtils.getString(obj, (String) null);
                if (string6 == null) {
                    $jacocoInit[427] = true;
                } else {
                    $jacocoInit[428] = true;
                    setVisibility(string6);
                    $jacocoInit[429] = true;
                }
                $jacocoInit[430] = true;
                return true;
            case 22:
                if (obj == null) {
                    $jacocoInit[431] = true;
                } else {
                    $jacocoInit[432] = true;
                    updateElevation();
                    $jacocoInit[433] = true;
                }
                $jacocoInit[434] = true;
                return true;
            case 23:
                String string7 = WXUtils.getString(obj, "m");
                $jacocoInit[435] = true;
                setFixedSize(string7);
                $jacocoInit[436] = true;
                return true;
            case 24:
                String string8 = WXUtils.getString(obj, "");
                $jacocoInit[437] = true;
                setAriaLabel(string8);
                $jacocoInit[438] = true;
                return true;
            case 25:
                boolean booleanValue = WXUtils.getBoolean(obj, false).booleanValue();
                $jacocoInit[439] = true;
                setAriaHidden(booleanValue);
                $jacocoInit[440] = true;
                return true;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
            case '!':
            case '\"':
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
                $jacocoInit[441] = true;
                return true;
            case '9':
                $jacocoInit[388] = true;
                try {
                    updateBoxShadow();
                    $jacocoInit[442] = true;
                } catch (Throwable th) {
                    $jacocoInit[443] = true;
                    th.printStackTrace();
                    $jacocoInit[444] = true;
                }
                $jacocoInit[445] = true;
                return true;
            case ':':
                setRole(WXUtils.getString(obj, ""));
                $jacocoInit[446] = true;
                return true;
            default:
                $jacocoInit[447] = true;
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public BorderDrawable getOrCreateBorder() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBackgroundDrawable != null) {
            $jacocoInit[448] = true;
        } else {
            $jacocoInit[449] = true;
            this.mBackgroundDrawable = new BorderDrawable();
            if (this.mHost == null) {
                $jacocoInit[450] = true;
            } else {
                $jacocoInit[451] = true;
                WXViewUtils.setBackGround(this.mHost, (Drawable) null, this);
                if (this.mRippleBackground == null) {
                    $jacocoInit[452] = true;
                    WXViewUtils.setBackGround(this.mHost, this.mBackgroundDrawable, this);
                    $jacocoInit[453] = true;
                } else {
                    WXViewUtils.setBackGround(this.mHost, new LayerDrawable(new Drawable[]{this.mRippleBackground, this.mBackgroundDrawable}), this);
                    $jacocoInit[454] = true;
                }
            }
        }
        BorderDrawable borderDrawable = this.mBackgroundDrawable;
        $jacocoInit[455] = true;
        return borderDrawable;
    }

    public void setSafeLayout(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(wXComponent.getComponentType())) {
            $jacocoInit[456] = true;
        } else {
            $jacocoInit[457] = true;
            if (TextUtils.isEmpty(wXComponent.getRef())) {
                $jacocoInit[458] = true;
            } else if (wXComponent.getLayoutPosition() == null) {
                $jacocoInit[459] = true;
            } else {
                $jacocoInit[460] = true;
                if (wXComponent.getLayoutSize() == null) {
                    $jacocoInit[461] = true;
                } else {
                    setLayout(wXComponent);
                    $jacocoInit[463] = true;
                    return;
                }
            }
        }
        $jacocoInit[462] = true;
    }

    public void setLayout(WXComponent wXComponent) {
        boolean z;
        CSSShorthand cSSShorthand;
        CSSShorthand cSSShorthand2;
        int i;
        int i2;
        float f;
        WXComponent wXComponent2 = wXComponent;
        boolean[] $jacocoInit = $jacocoInit();
        setLayoutSize(wXComponent.getLayoutSize());
        $jacocoInit[464] = true;
        setLayoutPosition(wXComponent.getLayoutPosition());
        $jacocoInit[465] = true;
        setPaddings(wXComponent.getPadding());
        $jacocoInit[466] = true;
        setMargins(wXComponent.getMargin());
        $jacocoInit[467] = true;
        setBorders(wXComponent.getBorder());
        $jacocoInit[468] = true;
        boolean isLayoutRTL = wXComponent.isLayoutRTL();
        $jacocoInit[469] = true;
        setIsLayoutRTL(isLayoutRTL);
        if (isLayoutRTL == wXComponent2.isLastLayoutDirectionRTL) {
            $jacocoInit[470] = true;
        } else {
            wXComponent2.isLastLayoutDirectionRTL = isLayoutRTL;
            $jacocoInit[471] = true;
            layoutDirectionDidChanged(isLayoutRTL);
            $jacocoInit[472] = true;
        }
        parseAnimation();
        int i3 = 0;
        if (this.mParent == null) {
            $jacocoInit[473] = true;
            z = true;
        } else {
            $jacocoInit[474] = true;
            z = false;
        }
        $jacocoInit[475] = true;
        if (z) {
            $jacocoInit[476] = true;
        } else {
            i3 = this.mParent.getChildrenLayoutTopOffset();
            $jacocoInit[477] = true;
        }
        $jacocoInit[478] = true;
        if (z) {
            cSSShorthand = new CSSShorthand();
            $jacocoInit[479] = true;
        } else {
            cSSShorthand = this.mParent.getPadding();
            $jacocoInit[480] = true;
        }
        $jacocoInit[481] = true;
        if (z) {
            cSSShorthand2 = new CSSShorthand();
            $jacocoInit[482] = true;
        } else {
            cSSShorthand2 = this.mParent.getBorder();
            $jacocoInit[483] = true;
        }
        $jacocoInit[484] = true;
        int width = (int) getLayoutSize().getWidth();
        $jacocoInit[485] = true;
        int height = (int) getLayoutSize().getHeight();
        $jacocoInit[486] = true;
        if (isFixed()) {
            $jacocoInit[487] = true;
            $jacocoInit[488] = true;
            $jacocoInit[489] = true;
            i = ((int) (getLayoutPosition().getTop() - ((float) getInstance().getRenderContainerPaddingTop()))) + i3;
            i2 = (int) (getLayoutPosition().getLeft() - ((float) getInstance().getRenderContainerPaddingLeft()));
        } else {
            float left = getLayoutPosition().getLeft();
            CSSShorthand.EDGE edge = CSSShorthand.EDGE.LEFT;
            $jacocoInit[490] = true;
            $jacocoInit[491] = true;
            float top = getLayoutPosition().getTop();
            CSSShorthand.EDGE edge2 = CSSShorthand.EDGE.TOP;
            $jacocoInit[492] = true;
            $jacocoInit[493] = true;
            i2 = (int) ((left - cSSShorthand.get(edge)) - cSSShorthand2.get(CSSShorthand.EDGE.LEFT));
            i = ((int) ((top - cSSShorthand.get(edge2)) - cSSShorthand2.get(CSSShorthand.EDGE.TOP))) + i3;
        }
        int i4 = (int) getMargin().get(CSSShorthand.EDGE.RIGHT);
        $jacocoInit[494] = true;
        int i5 = (int) getMargin().get(CSSShorthand.EDGE.BOTTOM);
        $jacocoInit[495] = true;
        $jacocoInit[496] = true;
        Point point = new Point((int) getLayoutPosition().getLeft(), (int) getLayoutPosition().getTop());
        if (this.mPreRealWidth != width) {
            $jacocoInit[497] = true;
        } else if (this.mPreRealHeight != height) {
            $jacocoInit[498] = true;
        } else if (this.mPreRealLeft != i2) {
            $jacocoInit[499] = true;
        } else if (this.mPreRealRight != i4) {
            $jacocoInit[500] = true;
        } else if (this.mPreRealTop != i) {
            $jacocoInit[501] = true;
        } else {
            $jacocoInit[502] = true;
            return;
        }
        if (!(this instanceof WXCell)) {
            $jacocoInit[503] = true;
        } else if (height < WXPerformance.VIEW_LIMIT_HEIGHT) {
            $jacocoInit[504] = true;
        } else if (width < WXPerformance.VIEW_LIMIT_WIDTH) {
            $jacocoInit[505] = true;
        } else {
            $jacocoInit[506] = true;
            this.mInstance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_CELL_EXCEED_NUM, 1.0d);
            $jacocoInit[507] = true;
            this.mInstance.getWXPerformance().cellExceedNum++;
            $jacocoInit[508] = true;
        }
        float f2 = 0.0f;
        if (z) {
            $jacocoInit[509] = true;
            f = 0.0f;
        } else {
            f = ((float) this.mParent.getAbsoluteY()) + getCSSLayoutTop();
            $jacocoInit[510] = true;
        }
        this.mAbsoluteY = (int) f;
        $jacocoInit[511] = true;
        if (z) {
            $jacocoInit[512] = true;
        } else {
            f2 = getCSSLayoutLeft() + ((float) this.mParent.getAbsoluteX());
            $jacocoInit[513] = true;
        }
        this.mAbsoluteX = (int) f2;
        if (this.mHost == null) {
            $jacocoInit[514] = true;
            return;
        }
        if (this.mHost instanceof ViewGroup) {
            $jacocoInit[515] = true;
        } else if (this.mAbsoluteY + height <= this.mInstance.getWeexHeight() + 1) {
            $jacocoInit[516] = true;
        } else {
            if (this.mInstance.mEnd) {
                $jacocoInit[517] = true;
            } else {
                $jacocoInit[518] = true;
                this.mInstance.onOldFsRenderTimeLogic();
                $jacocoInit[519] = true;
            }
            if (this.mInstance.isNewFsEnd) {
                $jacocoInit[520] = true;
            } else {
                this.mInstance.isNewFsEnd = true;
                $jacocoInit[521] = true;
                this.mInstance.getApmForInstance().arriveNewFsRenderTime();
                $jacocoInit[522] = true;
            }
        }
        MeasureOutput measure = measure(width, height);
        int i6 = measure.width;
        int i7 = measure.height;
        $jacocoInit[523] = true;
        setComponentLayoutParams(i6, i7, i2, i, i4, i5, point);
        $jacocoInit[524] = true;
    }

    private void setComponentLayoutParams(int i, int i2, int i3, int i4, int i5, int i6, Point point) {
        Widget widget;
        boolean[] $jacocoInit = $jacocoInit();
        if (getInstance() == null) {
            $jacocoInit[525] = true;
        } else if (getInstance().isDestroy()) {
            $jacocoInit[526] = true;
        } else {
            FlatGUIContext flatUIContext = getInstance().getFlatUIContext();
            $jacocoInit[528] = true;
            if (flatUIContext == null) {
                $jacocoInit[529] = true;
            } else if (flatUIContext.getFlatComponentAncestor(this) == null) {
                $jacocoInit[530] = true;
            } else {
                int i7 = i;
                int i8 = i2;
                int i9 = i3;
                int i10 = i4;
                int i11 = i5;
                $jacocoInit[531] = true;
                if (!(this instanceof FlatComponent)) {
                    $jacocoInit[532] = true;
                } else {
                    FlatComponent flatComponent = (FlatComponent) this;
                    if (flatComponent.promoteToView(true)) {
                        $jacocoInit[533] = true;
                    } else {
                        $jacocoInit[534] = true;
                        widget = flatComponent.getOrCreateFlatWidget();
                        $jacocoInit[535] = true;
                        setWidgetParams(widget, flatUIContext, point, i, i2, i3, i5, i4, i6);
                        $jacocoInit[537] = true;
                        $jacocoInit[547] = true;
                        return;
                    }
                }
                widget = flatUIContext.getAndroidViewWidget(this);
                $jacocoInit[536] = true;
                setWidgetParams(widget, flatUIContext, point, i, i2, i3, i5, i4, i6);
                $jacocoInit[537] = true;
                $jacocoInit[547] = true;
                return;
            }
            if (this.mHost == null) {
                $jacocoInit[538] = true;
            } else {
                $jacocoInit[539] = true;
                clearBoxShadow();
                $jacocoInit[540] = true;
                if (isFixed()) {
                    $jacocoInit[541] = true;
                    setFixedHostLayoutParams(this.mHost, i, i2, i3, i5, i4, i6);
                    $jacocoInit[542] = true;
                } else {
                    setHostLayoutParams(this.mHost, i, i2, i3, i5, i4, i6);
                    $jacocoInit[543] = true;
                }
                recordInteraction(i, i2);
                this.mPreRealWidth = i;
                this.mPreRealHeight = i2;
                this.mPreRealLeft = i3;
                this.mPreRealRight = i5;
                this.mPreRealTop = i4;
                $jacocoInit[544] = true;
                onFinishLayout();
                $jacocoInit[545] = true;
                updateBoxShadow();
                $jacocoInit[546] = true;
            }
            $jacocoInit[547] = true;
            return;
        }
        $jacocoInit[527] = true;
    }

    /* access modifiers changed from: protected */
    public void layoutDirectionDidChanged(boolean z) {
        $jacocoInit()[548] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0113  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recordInteraction(int r8, int r9) {
        /*
            r7 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = r7.mIsAddElementToTree
            r2 = 1
            if (r1 != 0) goto L_0x000e
            r8 = 549(0x225, float:7.7E-43)
            r0[r8] = r2
            return
        L_0x000e:
            r1 = 0
            r7.mIsAddElementToTree = r1
            com.taobao.weex.ui.component.WXVContainer r3 = r7.mParent
            if (r3 != 0) goto L_0x001e
            r7.interactionAbsoluteX = r1
            r7.interactionAbsoluteY = r1
            r3 = 550(0x226, float:7.71E-43)
            r0[r3] = r2
            goto L_0x0066
        L_0x001e:
            float r3 = r7.getCSSLayoutTop()
            r4 = 551(0x227, float:7.72E-43)
            r0[r4] = r2
            float r4 = r7.getCSSLayoutLeft()
            r5 = 552(0x228, float:7.74E-43)
            r0[r5] = r2
            boolean r5 = r7.isFixed()
            if (r5 == 0) goto L_0x0039
            r5 = 553(0x229, float:7.75E-43)
            r0[r5] = r2
            goto L_0x0043
        L_0x0039:
            com.taobao.weex.ui.component.WXVContainer r5 = r7.mParent
            int r5 = r5.interactionAbsoluteX
            float r5 = (float) r5
            float r4 = r4 + r5
            r5 = 554(0x22a, float:7.76E-43)
            r0[r5] = r2
        L_0x0043:
            int r4 = (int) r4
            r7.interactionAbsoluteX = r4
            r4 = 555(0x22b, float:7.78E-43)
            r0[r4] = r2
            boolean r4 = r7.isFixed()
            if (r4 == 0) goto L_0x0055
            r4 = 556(0x22c, float:7.79E-43)
            r0[r4] = r2
            goto L_0x005f
        L_0x0055:
            com.taobao.weex.ui.component.WXVContainer r4 = r7.mParent
            int r4 = r4.interactionAbsoluteY
            float r4 = (float) r4
            float r3 = r3 + r4
            r4 = 557(0x22d, float:7.8E-43)
            r0[r4] = r2
        L_0x005f:
            int r3 = (int) r3
            r7.interactionAbsoluteY = r3
            r3 = 558(0x22e, float:7.82E-43)
            r0[r3] = r2
        L_0x0066:
            com.taobao.weex.WXSDKInstance r3 = r7.getInstance()
            com.taobao.weex.performance.WXInstanceApm r3 = r3.getApmForInstance()
            android.graphics.Rect r3 = r3.instanceRect
            if (r3 == 0) goto L_0x0077
            r3 = 559(0x22f, float:7.83E-43)
            r0[r3] = r2
            goto L_0x008e
        L_0x0077:
            r3 = 560(0x230, float:7.85E-43)
            r0[r3] = r2
            com.taobao.weex.WXSDKInstance r3 = r7.getInstance()
            com.taobao.weex.performance.WXInstanceApm r3 = r3.getApmForInstance()
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r3.instanceRect = r4
            r3 = 561(0x231, float:7.86E-43)
            r0[r3] = r2
        L_0x008e:
            com.taobao.weex.WXSDKInstance r3 = r7.getInstance()
            com.taobao.weex.performance.WXInstanceApm r3 = r3.getApmForInstance()
            android.graphics.Rect r3 = r3.instanceRect
            r4 = 562(0x232, float:7.88E-43)
            r0[r4] = r2
            com.taobao.weex.WXSDKInstance r4 = r7.mInstance
            int r4 = r4.getWeexWidth()
            com.taobao.weex.WXSDKInstance r5 = r7.mInstance
            int r5 = r5.getWeexHeight()
            r3.set(r1, r1, r4, r5)
            int r4 = r7.interactionAbsoluteX
            int r5 = r7.interactionAbsoluteY
            r6 = 563(0x233, float:7.89E-43)
            r0[r6] = r2
            boolean r4 = r3.contains(r4, r5)
            if (r4 == 0) goto L_0x00be
            r8 = 564(0x234, float:7.9E-43)
            r0[r8] = r2
            goto L_0x00fa
        L_0x00be:
            int r4 = r7.interactionAbsoluteX
            int r4 = r4 + r8
            int r5 = r7.interactionAbsoluteY
            r6 = 565(0x235, float:7.92E-43)
            r0[r6] = r2
            boolean r4 = r3.contains(r4, r5)
            if (r4 == 0) goto L_0x00d2
            r8 = 566(0x236, float:7.93E-43)
            r0[r8] = r2
            goto L_0x00fa
        L_0x00d2:
            int r4 = r7.interactionAbsoluteX
            int r5 = r7.interactionAbsoluteY
            int r5 = r5 + r9
            r6 = 567(0x237, float:7.95E-43)
            r0[r6] = r2
            boolean r4 = r3.contains(r4, r5)
            if (r4 == 0) goto L_0x00e6
            r8 = 568(0x238, float:7.96E-43)
            r0[r8] = r2
            goto L_0x00fa
        L_0x00e6:
            int r4 = r7.interactionAbsoluteX
            int r4 = r4 + r8
            int r8 = r7.interactionAbsoluteY
            int r8 = r8 + r9
            r9 = 569(0x239, float:7.97E-43)
            r0[r9] = r2
            boolean r8 = r3.contains(r4, r8)
            if (r8 == 0) goto L_0x0100
            r8 = 570(0x23a, float:7.99E-43)
            r0[r8] = r2
        L_0x00fa:
            r8 = 571(0x23b, float:8.0E-43)
            r0[r8] = r2
            r8 = 1
            goto L_0x0105
        L_0x0100:
            r8 = 572(0x23c, float:8.02E-43)
            r0[r8] = r2
            r8 = 0
        L_0x0105:
            r9 = 573(0x23d, float:8.03E-43)
            r0[r9] = r2
            com.taobao.weex.WXSDKInstance r9 = r7.mInstance
            if (r8 != 0) goto L_0x0113
            r8 = 574(0x23e, float:8.04E-43)
            r0[r8] = r2
            r1 = 1
            goto L_0x0117
        L_0x0113:
            r8 = 575(0x23f, float:8.06E-43)
            r0[r8] = r2
        L_0x0117:
            r9.onChangeElement(r7, r1)
            r8 = 576(0x240, float:8.07E-43)
            r0[r8] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.recordInteraction(int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setWidgetParams(com.taobao.weex.ui.flat.widget.Widget r17, com.taobao.weex.ui.flat.FlatGUIContext r18, android.graphics.Point r19, int r20, int r21, int r22, int r23, int r24, int r25) {
        /*
            r16 = this;
            r9 = r16
            r10 = r17
            r0 = r18
            r1 = r19
            boolean[] r11 = $jacocoInit()
            android.graphics.Point r12 = new android.graphics.Point
            r12.<init>()
            com.taobao.weex.ui.component.WXVContainer r2 = r9.mParent
            r13 = 1
            if (r2 != 0) goto L_0x0020
            r0 = 577(0x241, float:8.09E-43)
            r11[r0] = r13
            r14 = r22
            r15 = r24
            goto L_0x00da
        L_0x0020:
            com.taobao.weex.ui.component.WXVContainer r2 = r9.mParent
            boolean r2 = r2 instanceof com.taobao.weex.ui.flat.FlatComponent
            if (r2 != 0) goto L_0x002f
            r1 = 578(0x242, float:8.1E-43)
            r11[r1] = r13
        L_0x002a:
            r14 = r22
            r15 = r24
            goto L_0x0051
        L_0x002f:
            com.taobao.weex.ui.component.WXVContainer r2 = r9.mParent
            r3 = 579(0x243, float:8.11E-43)
            r11[r3] = r13
            com.taobao.weex.ui.flat.WidgetContainer r2 = r0.getFlatComponentAncestor(r2)
            if (r2 != 0) goto L_0x0040
            r1 = 580(0x244, float:8.13E-43)
            r11[r1] = r13
            goto L_0x002a
        L_0x0040:
            com.taobao.weex.ui.component.WXVContainer r2 = r9.mParent
            r3 = 581(0x245, float:8.14E-43)
            r11[r3] = r13
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r2 = r0.getAndroidViewWidget(r2)
            if (r2 == 0) goto L_0x0059
            r1 = 582(0x246, float:8.16E-43)
            r11[r1] = r13
            goto L_0x002a
        L_0x0051:
            r12.set(r14, r15)
            r1 = 585(0x249, float:8.2E-43)
            r11[r1] = r13
            goto L_0x006c
        L_0x0059:
            r14 = r22
            r15 = r24
            r2 = 583(0x247, float:8.17E-43)
            r11[r2] = r13
            int r2 = r1.x
            int r1 = r1.y
            r12.set(r2, r1)
            r1 = 584(0x248, float:8.18E-43)
            r11[r1] = r13
        L_0x006c:
            com.taobao.weex.ui.component.WXVContainer r1 = r9.mParent
            boolean r1 = r1 instanceof com.taobao.weex.ui.flat.FlatComponent
            if (r1 != 0) goto L_0x0077
            r0 = 586(0x24a, float:8.21E-43)
            r11[r0] = r13
            goto L_0x00b8
        L_0x0077:
            com.taobao.weex.ui.component.WXVContainer r1 = r9.mParent
            r2 = 587(0x24b, float:8.23E-43)
            r11[r2] = r13
            com.taobao.weex.ui.flat.WidgetContainer r1 = r0.getFlatComponentAncestor(r1)
            if (r1 != 0) goto L_0x0088
            r0 = 588(0x24c, float:8.24E-43)
            r11[r0] = r13
            goto L_0x00b8
        L_0x0088:
            com.taobao.weex.ui.component.WXVContainer r1 = r9.mParent
            r2 = 589(0x24d, float:8.25E-43)
            r11[r2] = r13
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r0 = r0.getAndroidViewWidget(r1)
            if (r0 == 0) goto L_0x0099
            r0 = 590(0x24e, float:8.27E-43)
            r11[r0] = r13
            goto L_0x00b8
        L_0x0099:
            r0 = 591(0x24f, float:8.28E-43)
            r11[r0] = r13
            com.taobao.weex.ui.component.WXVContainer r0 = r9.mParent
            com.taobao.weex.ui.flat.FlatComponent r0 = (com.taobao.weex.ui.flat.FlatComponent) r0
            com.taobao.weex.ui.flat.widget.Widget r0 = r0.getOrCreateFlatWidget()
            android.graphics.Point r0 = r0.getLocInFlatContainer()
            r1 = 592(0x250, float:8.3E-43)
            r11[r1] = r13
            int r1 = r0.x
            int r0 = r0.y
            r12.offset(r1, r0)
            r0 = 593(0x251, float:8.31E-43)
            r11[r0] = r13
        L_0x00b8:
            com.taobao.weex.ui.component.WXVContainer r0 = r9.mParent
            T r2 = r9.mHost
            r1 = 594(0x252, float:8.32E-43)
            r11[r1] = r13
            r1 = r16
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r25
            android.view.ViewGroup$LayoutParams r0 = r0.getChildLayoutParams(r1, r2, r3, r4, r5, r6, r7, r8)
            boolean r1 = r0 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r1 != 0) goto L_0x00e3
            r0 = 595(0x253, float:8.34E-43)
            r11[r0] = r13
        L_0x00da:
            r1 = r20
            r2 = r21
            r4 = r23
            r0 = r25
            goto L_0x00f7
        L_0x00e3:
            int r1 = r0.width
            int r2 = r0.height
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            int r3 = r0.leftMargin
            int r4 = r0.rightMargin
            int r5 = r0.topMargin
            int r0 = r0.bottomMargin
            r6 = 596(0x254, float:8.35E-43)
            r11[r6] = r13
            r14 = r3
            r15 = r5
        L_0x00f7:
            r18 = r17
            r19 = r1
            r20 = r2
            r21 = r14
            r22 = r4
            r23 = r15
            r24 = r0
            r25 = r12
            r18.setLayout(r19, r20, r21, r22, r23, r24, r25)
            r3 = 597(0x255, float:8.37E-43)
            r11[r3] = r13
            boolean r3 = r10 instanceof com.taobao.weex.ui.flat.widget.AndroidViewWidget
            if (r3 != 0) goto L_0x0117
            r0 = 598(0x256, float:8.38E-43)
            r11[r0] = r13
            goto L_0x0148
        L_0x0117:
            r3 = r10
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r3 = (com.taobao.weex.ui.flat.widget.AndroidViewWidget) r3
            android.view.View r5 = r3.getView()
            if (r5 != 0) goto L_0x0125
            r0 = 599(0x257, float:8.4E-43)
            r11[r0] = r13
            goto L_0x0148
        L_0x0125:
            r5 = 600(0x258, float:8.41E-43)
            r11[r5] = r13
            android.view.View r3 = r3.getView()
            int r5 = r12.x
            int r6 = r12.y
            r17 = r16
            r18 = r3
            r19 = r1
            r20 = r2
            r21 = r5
            r22 = r4
            r23 = r6
            r24 = r0
            r17.setHostLayoutParams(r18, r19, r20, r21, r22, r23, r24)
            r0 = 601(0x259, float:8.42E-43)
            r11[r0] = r13
        L_0x0148:
            r0 = 602(0x25a, float:8.44E-43)
            r11[r0] = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.setWidgetParams(com.taobao.weex.ui.flat.widget.Widget, com.taobao.weex.ui.flat.FlatGUIContext, android.graphics.Point, int, int, int, int, int, int):void");
    }

    public int getLayoutTopOffsetForSibling() {
        $jacocoInit()[603] = true;
        return 0;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.view.ViewGroup$MarginLayoutParams] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setHostLayoutParams(T r13, int r14, int r15, int r16, int r17, int r18, int r19) {
        /*
            r12 = this;
            r9 = r12
            boolean[] r10 = $jacocoInit()
            com.taobao.weex.ui.component.WXVContainer r0 = r9.mParent
            r11 = 1
            if (r0 != 0) goto L_0x002b
            r0 = 604(0x25c, float:8.46E-43)
            r10[r0] = r11
            android.widget.FrameLayout$LayoutParams r6 = new android.widget.FrameLayout$LayoutParams
            r3 = r14
            r4 = r15
            r6.<init>(r14, r15)
            r0 = 605(0x25d, float:8.48E-43)
            r10[r0] = r11
            r0 = r12
            r1 = r6
            r2 = r16
            r3 = r18
            r4 = r17
            r5 = r19
            r0.setMarginsSupportRTL(r1, r2, r3, r4, r5)
            r0 = 606(0x25e, float:8.49E-43)
            r10[r0] = r11
            goto L_0x0041
        L_0x002b:
            r3 = r14
            r4 = r15
            com.taobao.weex.ui.component.WXVContainer r0 = r9.mParent
            r1 = r12
            r2 = r13
            r5 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            android.view.ViewGroup$LayoutParams r6 = r0.getChildLayoutParams(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 607(0x25f, float:8.5E-43)
            r10[r0] = r11
        L_0x0041:
            if (r6 != 0) goto L_0x0048
            r0 = 608(0x260, float:8.52E-43)
            r10[r0] = r11
            goto L_0x0054
        L_0x0048:
            r0 = 609(0x261, float:8.53E-43)
            r10[r0] = r11
            r0 = r13
            r13.setLayoutParams(r6)
            r0 = 610(0x262, float:8.55E-43)
            r10[r0] = r11
        L_0x0054:
            r0 = 611(0x263, float:8.56E-43)
            r10[r0] = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.setHostLayoutParams(android.view.View, int, int, int, int, int, int):void");
    }

    private void setFixedHostLayoutParams(T t, int i, int i2, int i3, int i4, int i5, int i6) {
        T t2 = t;
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.width = i;
        layoutParams.height = i2;
        $jacocoInit[612] = true;
        setMarginsSupportRTL(layoutParams, i3, i5, i4, i6);
        $jacocoInit[613] = true;
        t.setLayoutParams(layoutParams);
        $jacocoInit[614] = true;
        this.mInstance.moveFixedView(t);
        $jacocoInit[615] = true;
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[616] = true;
        } else {
            $jacocoInit[617] = true;
            WXLogUtils.d("Weex_Fixed_Style", "WXComponent:setLayout :" + i3 + " " + i5 + " " + i + " " + i2);
            $jacocoInit[618] = true;
            StringBuilder sb = new StringBuilder();
            sb.append("WXComponent:setLayout Left:");
            sb.append(getStyles().getLeft());
            sb.append(" ");
            sb.append((int) getStyles().getTop());
            WXLogUtils.d("Weex_Fixed_Style", sb.toString());
            $jacocoInit[619] = true;
        }
        $jacocoInit[620] = true;
    }

    /* access modifiers changed from: protected */
    public void updateBoxShadow() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!BoxShadowUtil.isBoxShadowEnabled()) {
            $jacocoInit[621] = true;
            return;
        }
        if (getStyles() != null) {
            $jacocoInit[622] = true;
            Object obj = getStyles().get(Constants.Name.BOX_SHADOW);
            $jacocoInit[623] = true;
            Object obj2 = getAttrs().get(Constants.Name.SHADOW_QUALITY);
            if (obj == null) {
                $jacocoInit[624] = true;
                return;
            }
            T t = this.mHost;
            int i = 0;
            if (!(this instanceof WXVContainer)) {
                $jacocoInit[625] = true;
            } else {
                $jacocoInit[626] = true;
                t = ((WXVContainer) this).getBoxShadowHost(false);
                $jacocoInit[627] = true;
            }
            if (t == null) {
                $jacocoInit[628] = true;
                return;
            }
            float floatValue = WXUtils.getFloat(obj2, Float.valueOf(0.5f)).floatValue();
            $jacocoInit[629] = true;
            int instanceViewPortWidth = getInstance().getInstanceViewPortWidth();
            $jacocoInit[630] = true;
            StringBuilder sb = new StringBuilder(obj.toString());
            sb.append(" / [");
            $jacocoInit[631] = true;
            sb.append(t.getMeasuredWidth());
            sb.append(",");
            $jacocoInit[632] = true;
            sb.append(t.getMeasuredHeight());
            sb.append("] / ");
            $jacocoInit[633] = true;
            sb.append(floatValue);
            String sb2 = sb.toString();
            $jacocoInit[634] = true;
            if (this.mLastBoxShadowId == null) {
                $jacocoInit[635] = true;
            } else if (!this.mLastBoxShadowId.equals(sb2)) {
                $jacocoInit[636] = true;
            } else {
                $jacocoInit[637] = true;
                WXLogUtils.d("BoxShadow", "box-shadow style was not modified. " + sb2);
                $jacocoInit[638] = true;
                return;
            }
            float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            $jacocoInit[639] = true;
            WXStyle styles = getStyles();
            if (styles == null) {
                $jacocoInit[640] = true;
            } else {
                $jacocoInit[641] = true;
                float floatValue2 = WXUtils.getFloat(styles.get("borderTopLeftRadius"), Float.valueOf(0.0f)).floatValue();
                fArr[0] = floatValue2;
                fArr[1] = floatValue2;
                $jacocoInit[642] = true;
                float floatValue3 = WXUtils.getFloat(styles.get("borderTopRightRadius"), Float.valueOf(0.0f)).floatValue();
                fArr[2] = floatValue3;
                fArr[3] = floatValue3;
                $jacocoInit[643] = true;
                float floatValue4 = WXUtils.getFloat(styles.get("borderBottomRightRadius"), Float.valueOf(0.0f)).floatValue();
                fArr[4] = floatValue4;
                fArr[5] = floatValue4;
                $jacocoInit[644] = true;
                float floatValue5 = WXUtils.getFloat(styles.get("borderBottomLeftRadius"), Float.valueOf(0.0f)).floatValue();
                fArr[6] = floatValue5;
                fArr[7] = floatValue5;
                $jacocoInit[645] = true;
                if (!styles.containsKey("borderRadius")) {
                    $jacocoInit[646] = true;
                } else {
                    $jacocoInit[647] = true;
                    float floatValue6 = WXUtils.getFloat(styles.get("borderRadius"), Float.valueOf(0.0f)).floatValue();
                    $jacocoInit[648] = true;
                    while (i < fArr.length) {
                        fArr[i] = floatValue6;
                        i++;
                        $jacocoInit[650] = true;
                    }
                    $jacocoInit[649] = true;
                }
            }
            BoxShadowUtil.setBoxShadow(t, obj.toString(), fArr, instanceViewPortWidth, floatValue);
            this.mLastBoxShadowId = sb2;
            $jacocoInit[651] = true;
        } else {
            WXLogUtils.w("Can not resolve styles");
            $jacocoInit[652] = true;
        }
        $jacocoInit[653] = true;
    }

    /* access modifiers changed from: protected */
    public void clearBoxShadow() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!BoxShadowUtil.isBoxShadowEnabled()) {
            $jacocoInit[654] = true;
            return;
        }
        if (getStyles() == null) {
            $jacocoInit[655] = true;
        } else {
            $jacocoInit[656] = true;
            if (getStyles().get(Constants.Name.BOX_SHADOW) != null) {
                $jacocoInit[657] = true;
            } else {
                $jacocoInit[658] = true;
                return;
            }
        }
        T t = this.mHost;
        if (!(this instanceof WXVContainer)) {
            $jacocoInit[659] = true;
        } else {
            $jacocoInit[660] = true;
            t = ((WXVContainer) this).getBoxShadowHost(true);
            $jacocoInit[661] = true;
        }
        if (t == null) {
            $jacocoInit[662] = true;
        } else if (Build.VERSION.SDK_INT < 18) {
            $jacocoInit[663] = true;
        } else {
            $jacocoInit[664] = true;
            ViewOverlay overlay = t.getOverlay();
            if (overlay == null) {
                $jacocoInit[665] = true;
            } else {
                $jacocoInit[666] = true;
                overlay.clear();
                $jacocoInit[667] = true;
            }
        }
        this.mLastBoxShadowId = null;
        $jacocoInit[668] = true;
    }

    /* access modifiers changed from: protected */
    public void onFinishLayout() {
        Object obj;
        boolean[] $jacocoInit = $jacocoInit();
        if (getStyles() != null) {
            obj = getStyles().get(Constants.Name.BACKGROUND_IMAGE);
            $jacocoInit[669] = true;
        } else {
            obj = null;
            $jacocoInit[670] = true;
        }
        if (obj == null) {
            $jacocoInit[671] = true;
        } else {
            $jacocoInit[672] = true;
            setBackgroundImage(obj.toString());
            $jacocoInit[673] = true;
        }
        $jacocoInit[674] = true;
    }

    /* access modifiers changed from: protected */
    public MeasureOutput measure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        MeasureOutput measureOutput = new MeasureOutput();
        if (this.mFixedProp != 0) {
            measureOutput.width = this.mFixedProp;
            measureOutput.height = this.mFixedProp;
            $jacocoInit[675] = true;
        } else {
            measureOutput.width = i;
            measureOutput.height = i2;
            $jacocoInit[676] = true;
        }
        $jacocoInit[677] = true;
        return measureOutput;
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public void setAriaHidden(boolean z) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        View hostView = getHostView();
        if (hostView == null) {
            $jacocoInit[678] = true;
        } else if (Build.VERSION.SDK_INT < 16) {
            $jacocoInit[679] = true;
        } else {
            $jacocoInit[680] = true;
            if (z) {
                i = 2;
                $jacocoInit[681] = true;
            } else {
                $jacocoInit[682] = true;
                i = 1;
            }
            hostView.setImportantForAccessibility(i);
            $jacocoInit[683] = true;
        }
        $jacocoInit[684] = true;
    }

    /* access modifiers changed from: protected */
    public void setAriaLabel(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        View hostView = getHostView();
        if (hostView == null) {
            $jacocoInit[685] = true;
        } else {
            $jacocoInit[686] = true;
            hostView.setContentDescription(str);
            $jacocoInit[687] = true;
        }
        $jacocoInit[688] = true;
    }

    /* access modifiers changed from: protected */
    public void setRole(final String str) {
        boolean[] $jacocoInit = $jacocoInit();
        View hostView = getHostView();
        $jacocoInit[689] = true;
        if (hostView == null) {
            $jacocoInit[690] = true;
        } else if (TextUtils.isEmpty(str)) {
            $jacocoInit[691] = true;
        } else {
            $jacocoInit[692] = true;
            IWXAccessibilityRoleAdapter accessibilityRoleAdapter = WXSDKManager.getInstance().getAccessibilityRoleAdapter();
            if (accessibilityRoleAdapter == null) {
                $jacocoInit[693] = true;
            } else {
                $jacocoInit[694] = true;
                str = accessibilityRoleAdapter.getRole(str);
                $jacocoInit[695] = true;
            }
            $jacocoInit[696] = true;
            AnonymousClass5 r3 = new AccessibilityDelegateCompat(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-7854892627730422349L, "com/taobao/weex/ui/component/WXComponent$5", 6);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    boolean[] $jacocoInit = $jacocoInit();
                    try {
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                        $jacocoInit[1] = true;
                        accessibilityNodeInfoCompat.setRoleDescription(str);
                        $jacocoInit[2] = true;
                    } catch (Throwable unused) {
                        $jacocoInit[3] = true;
                        WXLogUtils.e("SetRole failed!");
                        $jacocoInit[4] = true;
                    }
                    $jacocoInit[5] = true;
                }
            };
            $jacocoInit[697] = true;
            ViewCompat.setAccessibilityDelegate(hostView, r3);
            $jacocoInit[698] = true;
        }
        $jacocoInit[699] = true;
    }

    private void setFixedSize(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if ("m".equals(str)) {
            this.mFixedProp = -1;
            $jacocoInit[700] = true;
        } else if ("w".equals(str)) {
            this.mFixedProp = -2;
            $jacocoInit[701] = true;
        } else {
            this.mFixedProp = 0;
            $jacocoInit[702] = true;
            return;
        }
        if (this.mHost == null) {
            $jacocoInit[703] = true;
        } else {
            $jacocoInit[704] = true;
            ViewGroup.LayoutParams layoutParams = this.mHost.getLayoutParams();
            if (layoutParams == null) {
                $jacocoInit[705] = true;
            } else {
                layoutParams.height = this.mFixedProp;
                layoutParams.width = this.mFixedProp;
                $jacocoInit[706] = true;
                this.mHost.setLayoutParams(layoutParams);
                $jacocoInit[707] = true;
            }
        }
        $jacocoInit[708] = true;
    }

    /* access modifiers changed from: protected */
    public void appendEventToDOM(String str) {
        $jacocoInit()[709] = true;
    }

    public View getRealView() {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.mHost;
        $jacocoInit[710] = true;
        return t;
    }

    private boolean needGestureDetector(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHost == null) {
            $jacocoInit[711] = true;
        } else {
            $jacocoInit[712] = true;
            WXGestureType.LowLevelGesture[] values = WXGestureType.LowLevelGesture.values();
            int length = values.length;
            $jacocoInit[713] = true;
            int i = 0;
            while (i < length) {
                WXGestureType.LowLevelGesture lowLevelGesture = values[i];
                $jacocoInit[714] = true;
                if (str.equals(lowLevelGesture.toString())) {
                    $jacocoInit[715] = true;
                    return true;
                }
                i++;
                $jacocoInit[716] = true;
            }
            WXGestureType.HighLevelGesture[] values2 = WXGestureType.HighLevelGesture.values();
            int length2 = values2.length;
            $jacocoInit[717] = true;
            int i2 = 0;
            while (i2 < length2) {
                WXGestureType.HighLevelGesture highLevelGesture = values2[i2];
                $jacocoInit[719] = true;
                if (str.equals(highLevelGesture.toString())) {
                    $jacocoInit[720] = true;
                    return true;
                }
                i2++;
                $jacocoInit[721] = true;
            }
            $jacocoInit[718] = true;
        }
        if (WXGesture.isStopPropagation(str)) {
            $jacocoInit[722] = true;
            return true;
        }
        $jacocoInit[723] = true;
        return false;
    }

    public Scrollable getParentScroller() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[724] = true;
        WXVContainer wXVContainer = this;
        while (true) {
            WXVContainer parent = wXVContainer.getParent();
            if (parent == null) {
                $jacocoInit[725] = true;
                return null;
            } else if (parent instanceof Scrollable) {
                Scrollable scrollable = (Scrollable) parent;
                $jacocoInit[726] = true;
                return scrollable;
            } else if (parent.getRef().equals(ROOT)) {
                $jacocoInit[727] = true;
                return null;
            } else {
                $jacocoInit[728] = true;
                wXVContainer = parent;
            }
        }
    }

    @Nullable
    public Scrollable getFirstScroller() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this instanceof Scrollable) {
            Scrollable scrollable = (Scrollable) this;
            $jacocoInit[729] = true;
            return scrollable;
        }
        $jacocoInit[730] = true;
        return null;
    }

    public WXVContainer getParent() {
        boolean[] $jacocoInit = $jacocoInit();
        WXVContainer wXVContainer = this.mParent;
        $jacocoInit[731] = true;
        return wXVContainer;
    }

    public final void createView() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isLazy()) {
            $jacocoInit[732] = true;
        } else {
            $jacocoInit[733] = true;
            createViewImpl();
            $jacocoInit[734] = true;
        }
        $jacocoInit[735] = true;
    }

    /* access modifiers changed from: protected */
    public void createViewImpl() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mContext != null) {
            $jacocoInit[736] = true;
            this.mHost = initComponentHostView(this.mContext);
            $jacocoInit[737] = true;
            if (this.mHost != null) {
                $jacocoInit[738] = true;
            } else if (isVirtualComponent()) {
                $jacocoInit[739] = true;
            } else {
                $jacocoInit[740] = true;
                initView();
                $jacocoInit[741] = true;
            }
            if (this.mHost == null) {
                $jacocoInit[742] = true;
            } else {
                $jacocoInit[743] = true;
                if (this.mHost.getId() != -1) {
                    $jacocoInit[744] = true;
                } else {
                    $jacocoInit[745] = true;
                    this.mHost.setId(WXViewUtils.generateViewId());
                    $jacocoInit[746] = true;
                }
                if (!TextUtils.isEmpty(this.mHost.getContentDescription())) {
                    $jacocoInit[747] = true;
                } else if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[748] = true;
                } else {
                    $jacocoInit[749] = true;
                    this.mHost.setContentDescription(getRef());
                    $jacocoInit[750] = true;
                }
                ComponentObserver componentObserver = getInstance().getComponentObserver();
                if (componentObserver == null) {
                    $jacocoInit[751] = true;
                } else {
                    $jacocoInit[752] = true;
                    componentObserver.onViewCreated(this, this.mHost);
                    $jacocoInit[753] = true;
                }
            }
            onHostViewInitialized(this.mHost);
            $jacocoInit[754] = true;
        } else {
            WXLogUtils.e("createViewImpl", "Context is null");
            $jacocoInit[755] = true;
        }
        $jacocoInit[756] = true;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void initView() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mContext == null) {
            $jacocoInit[757] = true;
        } else {
            $jacocoInit[758] = true;
            this.mHost = initComponentHostView(this.mContext);
            $jacocoInit[759] = true;
        }
        $jacocoInit[760] = true;
    }

    /* access modifiers changed from: protected */
    public T initComponentHostView(@NonNull Context context) {
        $jacocoInit()[761] = true;
        return null;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onHostViewInitialized(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAnimationHolder == null) {
            $jacocoInit[762] = true;
        } else {
            $jacocoInit[763] = true;
            this.mAnimationHolder.execute(this.mInstance, this);
            $jacocoInit[764] = true;
        }
        setActiveTouchListener();
        $jacocoInit[765] = true;
    }

    public T getHostView() {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.mHost;
        $jacocoInit[766] = true;
        return t;
    }

    @Deprecated
    public View getView() {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.mHost;
        $jacocoInit[767] = true;
        return t;
    }

    public int getAbsoluteY() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mAbsoluteY;
        $jacocoInit[768] = true;
        return i;
    }

    public int getAbsoluteX() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mAbsoluteX;
        $jacocoInit[769] = true;
        return i;
    }

    public void removeEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[770] = true;
            return;
        }
        if (getEvents() == null) {
            $jacocoInit[771] = true;
        } else if (this.mAppendEvents == null) {
            $jacocoInit[772] = true;
        } else if (this.mGestureType == null) {
            $jacocoInit[773] = true;
        } else {
            if (!str.equals(Constants.Event.LAYEROVERFLOW)) {
                $jacocoInit[775] = true;
            } else {
                $jacocoInit[776] = true;
                removeLayerOverFlowListener(getRef());
                $jacocoInit[777] = true;
            }
            getEvents().remove(str);
            $jacocoInit[778] = true;
            this.mAppendEvents.remove(str);
            $jacocoInit[779] = true;
            this.mGestureType.remove(str);
            $jacocoInit[780] = true;
            removeEventFromView(str);
            $jacocoInit[781] = true;
            return;
        }
        $jacocoInit[774] = true;
    }

    /* access modifiers changed from: protected */
    public void removeEventFromView(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!str.equals("click")) {
            $jacocoInit[782] = true;
        } else if (getRealView() == null) {
            $jacocoInit[783] = true;
        } else if (this.mHostClickListeners == null) {
            $jacocoInit[784] = true;
        } else {
            if (this.mClickEventListener != null) {
                $jacocoInit[785] = true;
            } else {
                $jacocoInit[786] = true;
                this.mClickEventListener = new OnClickListenerImp(this, (AnonymousClass1) null);
                $jacocoInit[787] = true;
            }
            this.mHostClickListeners.remove(this.mClickEventListener);
            $jacocoInit[788] = true;
        }
        Scrollable parentScroller = getParentScroller();
        $jacocoInit[789] = true;
        if (!str.equals(Constants.Event.APPEAR)) {
            $jacocoInit[790] = true;
        } else if (parentScroller == null) {
            $jacocoInit[791] = true;
        } else {
            $jacocoInit[792] = true;
            parentScroller.unbindAppearEvent(this);
            $jacocoInit[793] = true;
        }
        if (!str.equals(Constants.Event.DISAPPEAR)) {
            $jacocoInit[794] = true;
        } else if (parentScroller == null) {
            $jacocoInit[795] = true;
        } else {
            $jacocoInit[796] = true;
            parentScroller.unbindDisappearEvent(this);
            $jacocoInit[797] = true;
        }
        $jacocoInit[798] = true;
    }

    public void removeAllEvent() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getEvents().size() < 1) {
            $jacocoInit[799] = true;
            return;
        }
        WXEvent events = getEvents();
        $jacocoInit[800] = true;
        int size = events.size();
        int i = 0;
        $jacocoInit[801] = true;
        while (true) {
            if (i >= size) {
                $jacocoInit[802] = true;
                break;
            }
            $jacocoInit[803] = true;
            if (i >= events.size()) {
                $jacocoInit[804] = true;
                break;
            }
            String str = (String) events.get(i);
            if (str == null) {
                $jacocoInit[805] = true;
            } else {
                removeEventFromView(str);
                $jacocoInit[806] = true;
            }
            i++;
            $jacocoInit[807] = true;
        }
        if (this.mAppendEvents == null) {
            $jacocoInit[808] = true;
        } else {
            $jacocoInit[809] = true;
            this.mAppendEvents.clear();
            $jacocoInit[810] = true;
        }
        if (this.mGestureType == null) {
            $jacocoInit[811] = true;
        } else {
            $jacocoInit[812] = true;
            this.mGestureType.clear();
            $jacocoInit[813] = true;
        }
        this.mGesture = null;
        $jacocoInit[814] = true;
        if (getRealView() == null) {
            $jacocoInit[815] = true;
        } else {
            $jacocoInit[816] = true;
            if (!(getRealView() instanceof WXGestureObservable)) {
                $jacocoInit[817] = true;
            } else {
                $jacocoInit[818] = true;
                ((WXGestureObservable) getRealView()).registerGestureListener((WXGesture) null);
                $jacocoInit[819] = true;
            }
        }
        if (this.mHost == null) {
            $jacocoInit[820] = true;
        } else {
            $jacocoInit[821] = true;
            this.mHost.setOnFocusChangeListener((View.OnFocusChangeListener) null);
            $jacocoInit[822] = true;
            if (this.mHostClickListeners == null) {
                $jacocoInit[823] = true;
            } else if (this.mHostClickListeners.size() <= 0) {
                $jacocoInit[824] = true;
            } else {
                $jacocoInit[825] = true;
                this.mHostClickListeners.clear();
                $jacocoInit[826] = true;
                this.mHost.setOnClickListener((View.OnClickListener) null);
                $jacocoInit[827] = true;
            }
        }
        $jacocoInit[828] = true;
    }

    public final void removeStickyStyle() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isSticky()) {
            $jacocoInit[829] = true;
        } else {
            $jacocoInit[830] = true;
            Scrollable parentScroller = getParentScroller();
            if (parentScroller == null) {
                $jacocoInit[831] = true;
            } else {
                $jacocoInit[832] = true;
                parentScroller.unbindStickStyle(this);
                $jacocoInit[833] = true;
            }
        }
        $jacocoInit[834] = true;
    }

    public boolean isSticky() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isSticky = getStyles().isSticky();
        $jacocoInit[835] = true;
        return isSticky;
    }

    public boolean isFixed() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isFixed = getStyles().isFixed();
        $jacocoInit[836] = true;
        return isFixed;
    }

    public void setDisabled(boolean z) {
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsDisabled = z;
        if (this.mHost == null) {
            $jacocoInit[837] = true;
            return;
        }
        T t = this.mHost;
        if (!z) {
            $jacocoInit[838] = true;
            z2 = true;
        } else {
            z2 = false;
            $jacocoInit[839] = true;
        }
        t.setEnabled(z2);
        $jacocoInit[840] = true;
    }

    public boolean isDisabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mIsDisabled;
        $jacocoInit[841] = true;
        return z;
    }

    public void setSticky(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[842] = true;
        } else if (!str.equals("sticky")) {
            $jacocoInit[843] = true;
        } else {
            $jacocoInit[844] = true;
            Scrollable parentScroller = getParentScroller();
            if (parentScroller == null) {
                $jacocoInit[845] = true;
            } else {
                $jacocoInit[846] = true;
                parentScroller.bindStickStyle(this);
                $jacocoInit[847] = true;
            }
        }
        $jacocoInit[848] = true;
    }

    public void setBackgroundColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[849] = true;
        } else {
            $jacocoInit[850] = true;
            int color = WXResourceUtils.getColor(str);
            $jacocoInit[851] = true;
            if (!isRippleEnabled()) {
                $jacocoInit[852] = true;
            } else if (Build.VERSION.SDK_INT < 21) {
                $jacocoInit[853] = true;
            } else {
                $jacocoInit[854] = true;
                this.mRippleBackground = prepareBackgroundRipple();
                if (this.mRippleBackground == null) {
                    $jacocoInit[855] = true;
                } else {
                    if (this.mBackgroundDrawable == null) {
                        $jacocoInit[856] = true;
                        WXViewUtils.setBackGround(this.mHost, this.mRippleBackground, this);
                        $jacocoInit[857] = true;
                    } else {
                        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.mRippleBackground, this.mBackgroundDrawable});
                        $jacocoInit[858] = true;
                        WXViewUtils.setBackGround(this.mHost, layerDrawable, this);
                        $jacocoInit[859] = true;
                    }
                    $jacocoInit[860] = true;
                    return;
                }
            }
            if (color != 0) {
                $jacocoInit[861] = true;
            } else if (this.mBackgroundDrawable == null) {
                $jacocoInit[862] = true;
            } else {
                $jacocoInit[863] = true;
            }
            getOrCreateBorder().setColor(color);
            $jacocoInit[864] = true;
        }
        $jacocoInit[865] = true;
    }

    private Drawable prepareBackgroundRipple() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (getStyles() == null) {
                $jacocoInit[866] = true;
            } else if (getStyles().getPesudoResetStyles() == null) {
                $jacocoInit[867] = true;
            } else {
                $jacocoInit[868] = true;
                Map<String, Object> pesudoResetStyles = getStyles().getPesudoResetStyles();
                $jacocoInit[869] = true;
                Object obj = pesudoResetStyles.get("backgroundColor");
                if (obj == null) {
                    $jacocoInit[870] = true;
                    i = 0;
                } else {
                    $jacocoInit[871] = true;
                    i = WXResourceUtils.getColor(obj.toString(), 0);
                    if (i != 0) {
                        $jacocoInit[872] = true;
                    } else {
                        $jacocoInit[873] = true;
                        return null;
                    }
                }
                Object obj2 = pesudoResetStyles.get("backgroundColor:active");
                if (obj2 != null) {
                    $jacocoInit[874] = true;
                    int color = WXResourceUtils.getColor(obj2.toString(), i);
                    if (Build.VERSION.SDK_INT < 21) {
                        $jacocoInit[876] = true;
                    } else {
                        $jacocoInit[877] = true;
                        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[0]}, new int[]{color});
                        $jacocoInit[878] = true;
                        AnonymousClass6 r3 = new RippleDrawable(this, colorStateList, new ColorDrawable(i), (Drawable) null) {
                            private static transient /* synthetic */ boolean[] $jacocoData;
                            final /* synthetic */ WXComponent this$0;

                            private static /* synthetic */ boolean[] $jacocoInit() {
                                boolean[] zArr = $jacocoData;
                                if (zArr != null) {
                                    return zArr;
                                }
                                boolean[] a2 = Offline.a(-7121914974701645410L, "com/taobao/weex/ui/component/WXComponent$6", 6);
                                $jacocoData = a2;
                                return a2;
                            }

                            {
                                boolean[] $jacocoInit = $jacocoInit();
                                this.this$0 = r2;
                                $jacocoInit[0] = true;
                            }

                            public void draw(@NonNull Canvas canvas) {
                                boolean[] $jacocoInit = $jacocoInit();
                                if (WXComponent.access$400(this.this$0) == null) {
                                    $jacocoInit[1] = true;
                                } else {
                                    $jacocoInit[2] = true;
                                    Path contentPath = WXComponent.access$400(this.this$0).getContentPath(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()));
                                    $jacocoInit[3] = true;
                                    canvas.clipPath(contentPath);
                                    $jacocoInit[4] = true;
                                }
                                super.draw(canvas);
                                $jacocoInit[5] = true;
                            }
                        };
                        $jacocoInit[879] = true;
                        return r3;
                    }
                } else {
                    $jacocoInit[875] = true;
                    return null;
                }
            }
            $jacocoInit[880] = true;
        } catch (Throwable th) {
            $jacocoInit[881] = true;
            WXLogUtils.w("Exception on create ripple: ", th);
            $jacocoInit[882] = true;
        }
        $jacocoInit[883] = true;
        return null;
    }

    public void setBackgroundImage(@NonNull String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if ("".equals(str.trim())) {
            $jacocoInit[884] = true;
            getOrCreateBorder().setImage((Shader) null);
            $jacocoInit[885] = true;
        } else {
            Shader shader = WXResourceUtils.getShader(str, getLayoutSize().getWidth(), getLayoutSize().getHeight());
            $jacocoInit[886] = true;
            getOrCreateBorder().setImage(shader);
            $jacocoInit[887] = true;
        }
        $jacocoInit[888] = true;
    }

    public void setOpacity(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (f < 0.0f) {
            $jacocoInit[889] = true;
        } else if (f > 1.0f) {
            $jacocoInit[890] = true;
        } else if (this.mHost.getAlpha() == f) {
            $jacocoInit[891] = true;
        } else {
            $jacocoInit[892] = true;
            if (!isLayerTypeEnabled()) {
                $jacocoInit[893] = true;
            } else {
                $jacocoInit[894] = true;
                this.mHost.setLayerType(2, (Paint) null);
                $jacocoInit[895] = true;
            }
            this.mHost.setAlpha(f);
            $jacocoInit[896] = true;
        }
        $jacocoInit[897] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        r6 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
        switch(r6) {
            case 0: goto L_0x00ea;
            case 1: goto L_0x00d2;
            case 2: goto L_0x00ba;
            case 3: goto L_0x00a2;
            case 4: goto L_0x008a;
            default: goto L_0x0084;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0084, code lost:
        r0[911(0x38f, float:1.277E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008a, code lost:
        getOrCreateBorder().setBorderRadius(com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_BOTTOM_LEFT, com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r5.mInstance.getInstanceViewPortWidth()));
        r0[918(0x396, float:1.286E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a2, code lost:
        getOrCreateBorder().setBorderRadius(com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT, com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r5.mInstance.getInstanceViewPortWidth()));
        r0[917(0x395, float:1.285E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ba, code lost:
        getOrCreateBorder().setBorderRadius(com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_TOP_RIGHT, com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r5.mInstance.getInstanceViewPortWidth()));
        r0[916(0x394, float:1.284E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d2, code lost:
        getOrCreateBorder().setBorderRadius(com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_TOP_LEFT, com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r5.mInstance.getInstanceViewPortWidth()));
        r0[915(0x393, float:1.282E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ea, code lost:
        r6 = getOrCreateBorder();
        r1 = com.taobao.weex.dom.CSSShorthand.CORNER.ALL;
        r3 = r5.mInstance;
        r0[912(0x390, float:1.278E-42)] = true;
        r7 = com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r3.getInstanceViewPortWidth());
        r0[913(0x391, float:1.28E-42)] = true;
        r6.setBorderRadius(r1, r7);
        r0[914(0x392, float:1.281E-42)] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBorderRadius(java.lang.String r6, float r7) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 0
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r2 = 1
            if (r1 >= 0) goto L_0x0010
            r6 = 898(0x382, float:1.258E-42)
            r0[r6] = r2
            goto L_0x0109
        L_0x0010:
            r1 = 899(0x383, float:1.26E-42)
            r0[r1] = r2
            r1 = -1
            int r3 = r6.hashCode()
            switch(r3) {
                case -1228066334: goto L_0x006d;
                case 333432965: goto L_0x005a;
                case 581268560: goto L_0x0047;
                case 588239831: goto L_0x0034;
                case 1349188574: goto L_0x0021;
                default: goto L_0x001c;
            }
        L_0x001c:
            r6 = 900(0x384, float:1.261E-42)
            r0[r6] = r2
            goto L_0x0080
        L_0x0021:
            java.lang.String r3 = "borderRadius"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x002e
            r6 = 901(0x385, float:1.263E-42)
            r0[r6] = r2
            goto L_0x0080
        L_0x002e:
            r6 = 0
            r1 = 902(0x386, float:1.264E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x0034:
            java.lang.String r3 = "borderBottomRightRadius"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x0041
            r6 = 907(0x38b, float:1.271E-42)
            r0[r6] = r2
            goto L_0x0080
        L_0x0041:
            r6 = 3
            r1 = 908(0x38c, float:1.272E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x0047:
            java.lang.String r3 = "borderBottomLeftRadius"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x0054
            r6 = 909(0x38d, float:1.274E-42)
            r0[r6] = r2
            goto L_0x0080
        L_0x0054:
            r6 = 4
            r1 = 910(0x38e, float:1.275E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x005a:
            java.lang.String r3 = "borderTopRightRadius"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x0067
            r6 = 905(0x389, float:1.268E-42)
            r0[r6] = r2
            goto L_0x0080
        L_0x0067:
            r6 = 2
            r1 = 906(0x38a, float:1.27E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x006d:
            java.lang.String r3 = "borderTopLeftRadius"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x007a
            r6 = 903(0x387, float:1.265E-42)
            r0[r6] = r2
            goto L_0x0080
        L_0x007a:
            r6 = 904(0x388, float:1.267E-42)
            r0[r6] = r2
            r6 = 1
            goto L_0x0081
        L_0x0080:
            r6 = -1
        L_0x0081:
            switch(r6) {
                case 0: goto L_0x00ea;
                case 1: goto L_0x00d2;
                case 2: goto L_0x00ba;
                case 3: goto L_0x00a2;
                case 4: goto L_0x008a;
                default: goto L_0x0084;
            }
        L_0x0084:
            r6 = 911(0x38f, float:1.277E-42)
            r0[r6] = r2
            goto L_0x0109
        L_0x008a:
            com.taobao.weex.ui.view.border.BorderDrawable r6 = r5.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$CORNER r1 = com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_BOTTOM_LEFT
            com.taobao.weex.WXSDKInstance r3 = r5.mInstance
            int r3 = r3.getInstanceViewPortWidth()
            float r7 = com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r3)
            r6.setBorderRadius(r1, r7)
            r6 = 918(0x396, float:1.286E-42)
            r0[r6] = r2
            goto L_0x0109
        L_0x00a2:
            com.taobao.weex.ui.view.border.BorderDrawable r6 = r5.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$CORNER r1 = com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT
            com.taobao.weex.WXSDKInstance r3 = r5.mInstance
            int r3 = r3.getInstanceViewPortWidth()
            float r7 = com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r3)
            r6.setBorderRadius(r1, r7)
            r6 = 917(0x395, float:1.285E-42)
            r0[r6] = r2
            goto L_0x0109
        L_0x00ba:
            com.taobao.weex.ui.view.border.BorderDrawable r6 = r5.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$CORNER r1 = com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_TOP_RIGHT
            com.taobao.weex.WXSDKInstance r3 = r5.mInstance
            int r3 = r3.getInstanceViewPortWidth()
            float r7 = com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r3)
            r6.setBorderRadius(r1, r7)
            r6 = 916(0x394, float:1.284E-42)
            r0[r6] = r2
            goto L_0x0109
        L_0x00d2:
            com.taobao.weex.ui.view.border.BorderDrawable r6 = r5.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$CORNER r1 = com.taobao.weex.dom.CSSShorthand.CORNER.BORDER_TOP_LEFT
            com.taobao.weex.WXSDKInstance r3 = r5.mInstance
            int r3 = r3.getInstanceViewPortWidth()
            float r7 = com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r3)
            r6.setBorderRadius(r1, r7)
            r6 = 915(0x393, float:1.282E-42)
            r0[r6] = r2
            goto L_0x0109
        L_0x00ea:
            com.taobao.weex.ui.view.border.BorderDrawable r6 = r5.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$CORNER r1 = com.taobao.weex.dom.CSSShorthand.CORNER.ALL
            com.taobao.weex.WXSDKInstance r3 = r5.mInstance
            r4 = 912(0x390, float:1.278E-42)
            r0[r4] = r2
            int r3 = r3.getInstanceViewPortWidth()
            float r7 = com.taobao.weex.utils.WXViewUtils.getRealSubPxByWidth(r7, r3)
            r3 = 913(0x391, float:1.28E-42)
            r0[r3] = r2
            r6.setBorderRadius(r1, r7)
            r6 = 914(0x392, float:1.281E-42)
            r0[r6] = r2
        L_0x0109:
            r6 = 919(0x397, float:1.288E-42)
            r0[r6] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.setBorderRadius(java.lang.String, float):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        r5 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
        switch(r5) {
            case 0: goto L_0x00c1;
            case 1: goto L_0x00b3;
            case 2: goto L_0x00a5;
            case 3: goto L_0x0097;
            case 4: goto L_0x0089;
            default: goto L_0x0084;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0084, code lost:
        r0[933(0x3a5, float:1.307E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0089, code lost:
        getOrCreateBorder().setBorderWidth(com.taobao.weex.dom.CSSShorthand.EDGE.LEFT, r6);
        r0[938(0x3aa, float:1.314E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0097, code lost:
        getOrCreateBorder().setBorderWidth(com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM, r6);
        r0[937(0x3a9, float:1.313E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a5, code lost:
        getOrCreateBorder().setBorderWidth(com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT, r6);
        r0[936(0x3a8, float:1.312E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b3, code lost:
        getOrCreateBorder().setBorderWidth(com.taobao.weex.dom.CSSShorthand.EDGE.TOP, r6);
        r0[935(0x3a7, float:1.31E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c1, code lost:
        getOrCreateBorder().setBorderWidth(com.taobao.weex.dom.CSSShorthand.EDGE.ALL, r6);
        r0[934(0x3a6, float:1.309E-42)] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBorderWidth(java.lang.String r5, float r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 0
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            r2 = 1
            if (r1 >= 0) goto L_0x0010
            r5 = 920(0x398, float:1.289E-42)
            r0[r5] = r2
            goto L_0x00ce
        L_0x0010:
            r1 = 921(0x399, float:1.29E-42)
            r0[r1] = r2
            r1 = -1
            int r3 = r5.hashCode()
            switch(r3) {
                case -1971292586: goto L_0x006d;
                case -1452542531: goto L_0x005a;
                case -1290574193: goto L_0x0047;
                case -223992013: goto L_0x0034;
                case 741115130: goto L_0x0021;
                default: goto L_0x001c;
            }
        L_0x001c:
            r5 = 922(0x39a, float:1.292E-42)
            r0[r5] = r2
            goto L_0x0080
        L_0x0021:
            java.lang.String r3 = "borderWidth"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x002e
            r5 = 923(0x39b, float:1.293E-42)
            r0[r5] = r2
            goto L_0x0080
        L_0x002e:
            r5 = 0
            r1 = 924(0x39c, float:1.295E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x0034:
            java.lang.String r3 = "borderLeftWidth"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0041
            r5 = 931(0x3a3, float:1.305E-42)
            r0[r5] = r2
            goto L_0x0080
        L_0x0041:
            r5 = 4
            r1 = 932(0x3a4, float:1.306E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x0047:
            java.lang.String r3 = "borderBottomWidth"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0054
            r5 = 929(0x3a1, float:1.302E-42)
            r0[r5] = r2
            goto L_0x0080
        L_0x0054:
            r5 = 3
            r1 = 930(0x3a2, float:1.303E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x005a:
            java.lang.String r3 = "borderTopWidth"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0067
            r5 = 925(0x39d, float:1.296E-42)
            r0[r5] = r2
            goto L_0x0080
        L_0x0067:
            r5 = 926(0x39e, float:1.298E-42)
            r0[r5] = r2
            r5 = 1
            goto L_0x0081
        L_0x006d:
            java.lang.String r3 = "borderRightWidth"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x007a
            r5 = 927(0x39f, float:1.299E-42)
            r0[r5] = r2
            goto L_0x0080
        L_0x007a:
            r5 = 2
            r1 = 928(0x3a0, float:1.3E-42)
            r0[r1] = r2
            goto L_0x0081
        L_0x0080:
            r5 = -1
        L_0x0081:
            switch(r5) {
                case 0: goto L_0x00c1;
                case 1: goto L_0x00b3;
                case 2: goto L_0x00a5;
                case 3: goto L_0x0097;
                case 4: goto L_0x0089;
                default: goto L_0x0084;
            }
        L_0x0084:
            r5 = 933(0x3a5, float:1.307E-42)
            r0[r5] = r2
            goto L_0x00ce
        L_0x0089:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            r5.setBorderWidth(r1, r6)
            r5 = 938(0x3aa, float:1.314E-42)
            r0[r5] = r2
            goto L_0x00ce
        L_0x0097:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            r5.setBorderWidth(r1, r6)
            r5 = 937(0x3a9, float:1.313E-42)
            r0[r5] = r2
            goto L_0x00ce
        L_0x00a5:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            r5.setBorderWidth(r1, r6)
            r5 = 936(0x3a8, float:1.312E-42)
            r0[r5] = r2
            goto L_0x00ce
        L_0x00b3:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            r5.setBorderWidth(r1, r6)
            r5 = 935(0x3a7, float:1.31E-42)
            r0[r5] = r2
            goto L_0x00ce
        L_0x00c1:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.ALL
            r5.setBorderWidth(r1, r6)
            r5 = 934(0x3a6, float:1.309E-42)
            r0[r5] = r2
        L_0x00ce:
            r5 = 939(0x3ab, float:1.316E-42)
            r0[r5] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.setBorderWidth(java.lang.String, float):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0081, code lost:
        r5 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0082, code lost:
        switch(r5) {
            case 0: goto L_0x00c2;
            case 1: goto L_0x00b4;
            case 2: goto L_0x00a6;
            case 3: goto L_0x0098;
            case 4: goto L_0x008a;
            default: goto L_0x0085;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0085, code lost:
        r0[953(0x3b9, float:1.335E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008a, code lost:
        getOrCreateBorder().setBorderStyle(com.taobao.weex.dom.CSSShorthand.EDGE.TOP, r6);
        r0[958(0x3be, float:1.342E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
        getOrCreateBorder().setBorderStyle(com.taobao.weex.dom.CSSShorthand.EDGE.LEFT, r6);
        r0[957(0x3bd, float:1.341E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a6, code lost:
        getOrCreateBorder().setBorderStyle(com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM, r6);
        r0[956(0x3bc, float:1.34E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b4, code lost:
        getOrCreateBorder().setBorderStyle(com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT, r6);
        r0[955(0x3bb, float:1.338E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c2, code lost:
        getOrCreateBorder().setBorderStyle(com.taobao.weex.dom.CSSShorthand.EDGE.ALL, r6);
        r0[954(0x3ba, float:1.337E-42)] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBorderStyle(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            r2 = 1
            if (r1 == 0) goto L_0x0011
            r5 = 940(0x3ac, float:1.317E-42)
            r0[r5] = r2
            goto L_0x00cf
        L_0x0011:
            r1 = 941(0x3ad, float:1.319E-42)
            r0[r1] = r2
            r1 = -1
            int r3 = r5.hashCode()
            switch(r3) {
                case -1974639039: goto L_0x006e;
                case -1455888984: goto L_0x005b;
                case -1293920646: goto L_0x0048;
                case -227338466: goto L_0x0035;
                case 737768677: goto L_0x0022;
                default: goto L_0x001d;
            }
        L_0x001d:
            r5 = 942(0x3ae, float:1.32E-42)
            r0[r5] = r2
            goto L_0x0081
        L_0x0022:
            java.lang.String r3 = "borderStyle"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x002f
            r5 = 943(0x3af, float:1.321E-42)
            r0[r5] = r2
            goto L_0x0081
        L_0x002f:
            r5 = 0
            r1 = 944(0x3b0, float:1.323E-42)
            r0[r1] = r2
            goto L_0x0082
        L_0x0035:
            java.lang.String r3 = "borderLeftStyle"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0042
            r5 = 949(0x3b5, float:1.33E-42)
            r0[r5] = r2
            goto L_0x0081
        L_0x0042:
            r5 = 3
            r1 = 950(0x3b6, float:1.331E-42)
            r0[r1] = r2
            goto L_0x0082
        L_0x0048:
            java.lang.String r3 = "borderBottomStyle"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0055
            r5 = 947(0x3b3, float:1.327E-42)
            r0[r5] = r2
            goto L_0x0081
        L_0x0055:
            r5 = 2
            r1 = 948(0x3b4, float:1.328E-42)
            r0[r1] = r2
            goto L_0x0082
        L_0x005b:
            java.lang.String r3 = "borderTopStyle"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0068
            r5 = 951(0x3b7, float:1.333E-42)
            r0[r5] = r2
            goto L_0x0081
        L_0x0068:
            r5 = 4
            r1 = 952(0x3b8, float:1.334E-42)
            r0[r1] = r2
            goto L_0x0082
        L_0x006e:
            java.lang.String r3 = "borderRightStyle"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x007b
            r5 = 945(0x3b1, float:1.324E-42)
            r0[r5] = r2
            goto L_0x0081
        L_0x007b:
            r5 = 946(0x3b2, float:1.326E-42)
            r0[r5] = r2
            r5 = 1
            goto L_0x0082
        L_0x0081:
            r5 = -1
        L_0x0082:
            switch(r5) {
                case 0: goto L_0x00c2;
                case 1: goto L_0x00b4;
                case 2: goto L_0x00a6;
                case 3: goto L_0x0098;
                case 4: goto L_0x008a;
                default: goto L_0x0085;
            }
        L_0x0085:
            r5 = 953(0x3b9, float:1.335E-42)
            r0[r5] = r2
            goto L_0x00cf
        L_0x008a:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            r5.setBorderStyle(r1, r6)
            r5 = 958(0x3be, float:1.342E-42)
            r0[r5] = r2
            goto L_0x00cf
        L_0x0098:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            r5.setBorderStyle(r1, r6)
            r5 = 957(0x3bd, float:1.341E-42)
            r0[r5] = r2
            goto L_0x00cf
        L_0x00a6:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            r5.setBorderStyle(r1, r6)
            r5 = 956(0x3bc, float:1.34E-42)
            r0[r5] = r2
            goto L_0x00cf
        L_0x00b4:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            r5.setBorderStyle(r1, r6)
            r5 = 955(0x3bb, float:1.338E-42)
            r0[r5] = r2
            goto L_0x00cf
        L_0x00c2:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.ALL
            r5.setBorderStyle(r1, r6)
            r5 = 954(0x3ba, float:1.337E-42)
            r0[r5] = r2
        L_0x00cf:
            r5 = 959(0x3bf, float:1.344E-42)
            r0[r5] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.setBorderStyle(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0093, code lost:
        r5 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0094, code lost:
        switch(r5) {
            case 0: goto L_0x00d4;
            case 1: goto L_0x00c6;
            case 2: goto L_0x00b8;
            case 3: goto L_0x00aa;
            case 4: goto L_0x009c;
            default: goto L_0x0097;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0097, code lost:
        r0[975(0x3cf, float:1.366E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009c, code lost:
        getOrCreateBorder().setBorderColor(com.taobao.weex.dom.CSSShorthand.EDGE.LEFT, r6);
        r0[980(0x3d4, float:1.373E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        getOrCreateBorder().setBorderColor(com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM, r6);
        r0[979(0x3d3, float:1.372E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b8, code lost:
        getOrCreateBorder().setBorderColor(com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT, r6);
        r0[978(0x3d2, float:1.37E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c6, code lost:
        getOrCreateBorder().setBorderColor(com.taobao.weex.dom.CSSShorthand.EDGE.TOP, r6);
        r0[977(0x3d1, float:1.369E-42)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d4, code lost:
        getOrCreateBorder().setBorderColor(com.taobao.weex.dom.CSSShorthand.EDGE.ALL, r6);
        r0[976(0x3d0, float:1.368E-42)] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBorderColor(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            r2 = 1
            if (r1 == 0) goto L_0x0011
            r5 = 960(0x3c0, float:1.345E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x0011:
            r1 = 961(0x3c1, float:1.347E-42)
            r0[r1] = r2
            int r6 = com.taobao.weex.utils.WXResourceUtils.getColor(r6)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 != r1) goto L_0x0023
            r5 = 962(0x3c2, float:1.348E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x0023:
            r1 = 963(0x3c3, float:1.35E-42)
            r0[r1] = r2
            r1 = -1
            int r3 = r5.hashCode()
            switch(r3) {
                case -1989576717: goto L_0x0080;
                case -1470826662: goto L_0x006d;
                case -1308858324: goto L_0x005a;
                case -242276144: goto L_0x0047;
                case 722830999: goto L_0x0034;
                default: goto L_0x002f;
            }
        L_0x002f:
            r5 = 964(0x3c4, float:1.351E-42)
            r0[r5] = r2
            goto L_0x0093
        L_0x0034:
            java.lang.String r3 = "borderColor"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0041
            r5 = 965(0x3c5, float:1.352E-42)
            r0[r5] = r2
            goto L_0x0093
        L_0x0041:
            r5 = 0
            r1 = 966(0x3c6, float:1.354E-42)
            r0[r1] = r2
            goto L_0x0094
        L_0x0047:
            java.lang.String r3 = "borderLeftColor"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0054
            r5 = 973(0x3cd, float:1.363E-42)
            r0[r5] = r2
            goto L_0x0093
        L_0x0054:
            r5 = 4
            r1 = 974(0x3ce, float:1.365E-42)
            r0[r1] = r2
            goto L_0x0094
        L_0x005a:
            java.lang.String r3 = "borderBottomColor"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0067
            r5 = 971(0x3cb, float:1.36E-42)
            r0[r5] = r2
            goto L_0x0093
        L_0x0067:
            r5 = 3
            r1 = 972(0x3cc, float:1.362E-42)
            r0[r1] = r2
            goto L_0x0094
        L_0x006d:
            java.lang.String r3 = "borderTopColor"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x007a
            r5 = 967(0x3c7, float:1.355E-42)
            r0[r5] = r2
            goto L_0x0093
        L_0x007a:
            r5 = 968(0x3c8, float:1.356E-42)
            r0[r5] = r2
            r5 = 1
            goto L_0x0094
        L_0x0080:
            java.lang.String r3 = "borderRightColor"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x008d
            r5 = 969(0x3c9, float:1.358E-42)
            r0[r5] = r2
            goto L_0x0093
        L_0x008d:
            r5 = 2
            r1 = 970(0x3ca, float:1.359E-42)
            r0[r1] = r2
            goto L_0x0094
        L_0x0093:
            r5 = -1
        L_0x0094:
            switch(r5) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00c6;
                case 2: goto L_0x00b8;
                case 3: goto L_0x00aa;
                case 4: goto L_0x009c;
                default: goto L_0x0097;
            }
        L_0x0097:
            r5 = 975(0x3cf, float:1.366E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x009c:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            r5.setBorderColor(r1, r6)
            r5 = 980(0x3d4, float:1.373E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x00aa:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            r5.setBorderColor(r1, r6)
            r5 = 979(0x3d3, float:1.372E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x00b8:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            r5.setBorderColor(r1, r6)
            r5 = 978(0x3d2, float:1.37E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x00c6:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            r5.setBorderColor(r1, r6)
            r5 = 977(0x3d1, float:1.369E-42)
            r0[r5] = r2
            goto L_0x00e1
        L_0x00d4:
            com.taobao.weex.ui.view.border.BorderDrawable r5 = r4.getOrCreateBorder()
            com.taobao.weex.dom.CSSShorthand$EDGE r1 = com.taobao.weex.dom.CSSShorthand.EDGE.ALL
            r5.setBorderColor(r1, r6)
            r5 = 976(0x3d0, float:1.368E-42)
            r0[r5] = r2
        L_0x00e1:
            r5 = 981(0x3d5, float:1.375E-42)
            r0[r5] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.setBorderColor(java.lang.String, java.lang.String):void");
    }

    @Nullable
    public String getVisibility() {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String str = (String) getStyles().get("visibility");
            $jacocoInit[982] = true;
            return str;
        } catch (Exception unused) {
            $jacocoInit[983] = true;
            return "visible";
        }
    }

    public void setVisibility(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        View realView = getRealView();
        if (realView == null) {
            $jacocoInit[984] = true;
        } else {
            $jacocoInit[985] = true;
            if (TextUtils.equals(str, "visible")) {
                $jacocoInit[986] = true;
                realView.setVisibility(0);
                $jacocoInit[987] = true;
            } else if (!TextUtils.equals(str, "hidden")) {
                $jacocoInit[988] = true;
            } else {
                $jacocoInit[989] = true;
                realView.setVisibility(8);
                $jacocoInit[990] = true;
            }
        }
        $jacocoInit[991] = true;
    }

    private void updateElevation() {
        boolean[] $jacocoInit = $jacocoInit();
        float elevation = getAttrs().getElevation(getInstance().getInstanceViewPortWidth());
        $jacocoInit[992] = true;
        if (Float.isNaN(elevation)) {
            $jacocoInit[993] = true;
        } else {
            $jacocoInit[994] = true;
            ViewCompat.setElevation(getHostView(), elevation);
            $jacocoInit[995] = true;
        }
        $jacocoInit[996] = true;
    }

    @Deprecated
    public void registerActivityStateListener() {
        $jacocoInit()[997] = true;
    }

    public void onActivityCreate() {
        $jacocoInit()[998] = true;
    }

    public void onActivityStart() {
        $jacocoInit()[999] = true;
    }

    public void onActivityPause() {
        $jacocoInit()[1000] = true;
    }

    public void onActivityResume() {
        $jacocoInit()[1001] = true;
    }

    public void onActivityStop() {
        $jacocoInit()[1002] = true;
    }

    public void onActivityDestroy() {
        $jacocoInit()[1003] = true;
    }

    public boolean onActivityBack() {
        $jacocoInit()[1004] = true;
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        $jacocoInit()[1005] = true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        $jacocoInit()[1006] = true;
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        $jacocoInit()[1007] = true;
    }

    public void recycled() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isFixed()) {
            $jacocoInit[1008] = true;
            return;
        }
        clearBoxShadow();
        $jacocoInit[1009] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        ComponentObserver componentObserver = getInstance().getComponentObserver();
        if (componentObserver == null) {
            $jacocoInit[1010] = true;
        } else {
            $jacocoInit[1011] = true;
            componentObserver.onPreDestory(this);
            $jacocoInit[1012] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1013] = true;
        } else if (WXUtils.isUiThread()) {
            $jacocoInit[1014] = true;
        } else {
            $jacocoInit[1015] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("[WXComponent] destroy can only be called in main thread");
            $jacocoInit[1016] = true;
            throw wXRuntimeException;
        }
        if (this.mHost == null) {
            $jacocoInit[1017] = true;
        } else if (this.mHost.getLayerType() != 2) {
            $jacocoInit[1018] = true;
        } else if (!isLayerTypeEnabled()) {
            $jacocoInit[1019] = true;
        } else {
            $jacocoInit[1020] = true;
            this.mHost.setLayerType(0, (Paint) null);
            $jacocoInit[1021] = true;
        }
        removeAllEvent();
        $jacocoInit[1022] = true;
        removeStickyStyle();
        $jacocoInit[1023] = true;
        if (!isFixed()) {
            $jacocoInit[1024] = true;
        } else {
            View hostView = getHostView();
            if (hostView == null) {
                $jacocoInit[1025] = true;
            } else {
                $jacocoInit[1026] = true;
                getInstance().removeFixedView(hostView);
                $jacocoInit[1027] = true;
            }
        }
        if (this.contentBoxMeasurement == null) {
            $jacocoInit[1028] = true;
        } else {
            $jacocoInit[1029] = true;
            this.contentBoxMeasurement.destroy();
            this.contentBoxMeasurement = null;
            $jacocoInit[1030] = true;
        }
        this.mIsDestroyed = true;
        if (this.animations == null) {
            $jacocoInit[1031] = true;
        } else {
            $jacocoInit[1032] = true;
            this.animations.clear();
            $jacocoInit[1033] = true;
        }
        $jacocoInit[1034] = true;
    }

    public boolean isDestoryed() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mIsDestroyed;
        $jacocoInit[1035] = true;
        return z;
    }

    public View detachViewAndClearPreInfo() {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.mHost;
        this.mPreRealLeft = 0;
        this.mPreRealRight = 0;
        this.mPreRealWidth = 0;
        this.mPreRealHeight = 0;
        this.mPreRealTop = 0;
        $jacocoInit[1036] = true;
        return t;
    }

    public void clearPreLayout() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPreRealLeft = 0;
        this.mPreRealRight = 0;
        this.mPreRealWidth = 0;
        this.mPreRealHeight = 0;
        this.mPreRealTop = 0;
        $jacocoInit[1037] = true;
    }

    public void computeVisiblePointInViewCoordinate(PointF pointF) {
        boolean[] $jacocoInit = $jacocoInit();
        View realView = getRealView();
        $jacocoInit[1038] = true;
        pointF.set((float) realView.getScrollX(), (float) realView.getScrollY());
        $jacocoInit[1039] = true;
    }

    public boolean containsGesture(WXGestureType wXGestureType) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mGestureType == null) {
            $jacocoInit[1040] = true;
        } else if (!this.mGestureType.contains(wXGestureType.toString())) {
            $jacocoInit[1041] = true;
        } else {
            $jacocoInit[1042] = true;
            z = true;
            $jacocoInit[1044] = true;
            return z;
        }
        z = false;
        $jacocoInit[1043] = true;
        $jacocoInit[1044] = true;
        return z;
    }

    public boolean containsEvent(String str) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (getEvents().contains(str)) {
            $jacocoInit[1045] = true;
        } else {
            if (this.mAppendEvents == null) {
                $jacocoInit[1046] = true;
            } else if (!this.mAppendEvents.contains(str)) {
                $jacocoInit[1047] = true;
            } else {
                $jacocoInit[1048] = true;
            }
            z = false;
            $jacocoInit[1050] = true;
            $jacocoInit[1051] = true;
            return z;
        }
        $jacocoInit[1049] = true;
        z = true;
        $jacocoInit[1051] = true;
        return z;
    }

    public void notifyAppearStateChange(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (containsEvent(Constants.Event.APPEAR)) {
            $jacocoInit[1052] = true;
        } else if (!containsEvent(Constants.Event.DISAPPEAR)) {
            $jacocoInit[1053] = true;
            $jacocoInit[1058] = true;
        } else {
            $jacocoInit[1054] = true;
        }
        HashMap hashMap = new HashMap();
        $jacocoInit[1055] = true;
        hashMap.put("direction", str2);
        $jacocoInit[1056] = true;
        fireEvent(str, hashMap);
        $jacocoInit[1057] = true;
        $jacocoInit[1058] = true;
    }

    public boolean isUsing() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isUsing;
        $jacocoInit[1059] = true;
        return z;
    }

    public void setUsing(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isUsing = z;
        $jacocoInit[1060] = true;
    }

    public void readyToRender() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mParent == null) {
            $jacocoInit[1061] = true;
        } else if (!getInstance().isTrackComponent()) {
            $jacocoInit[1062] = true;
        } else {
            $jacocoInit[1063] = true;
            this.mParent.readyToRender();
            $jacocoInit[1064] = true;
        }
        $jacocoInit[1065] = true;
    }

    public static class MeasureOutput {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public int height;
        public int width;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-5773623203236182472L, "com/taobao/weex/ui/component/WXComponent$MeasureOutput", 1);
            $jacocoData = a2;
            return a2;
        }

        public MeasureOutput() {
            $jacocoInit()[0] = true;
        }
    }

    public boolean isVirtualComponent() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mType == 1) {
            $jacocoInit[1066] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[1067] = true;
        }
        $jacocoInit[1068] = true;
        return z;
    }

    public void removeVirtualComponent() {
        $jacocoInit()[1069] = true;
    }

    public int getType() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mType;
        $jacocoInit[1070] = true;
        return i;
    }

    public boolean hasScrollParent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getParent() == null) {
            $jacocoInit[1071] = true;
            return true;
        } else if (wXComponent.getParent() instanceof WXScroller) {
            $jacocoInit[1072] = true;
            return false;
        } else {
            boolean hasScrollParent = hasScrollParent(wXComponent.getParent());
            $jacocoInit[1073] = true;
            return hasScrollParent;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    @android.support.annotation.CheckResult
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object convertEmptyProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case -1989576717: goto L_0x014d;
                case -1971292586: goto L_0x0139;
                case -1470826662: goto L_0x0125;
                case -1452542531: goto L_0x0112;
                case -1308858324: goto L_0x00fe;
                case -1290574193: goto L_0x00e8;
                case -1228066334: goto L_0x00d3;
                case -242276144: goto L_0x00bd;
                case -223992013: goto L_0x00a7;
                case 333432965: goto L_0x0092;
                case 581268560: goto L_0x007d;
                case 588239831: goto L_0x0068;
                case 722830999: goto L_0x0052;
                case 741115130: goto L_0x003d;
                case 1287124693: goto L_0x0028;
                case 1349188574: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            r5 = 1074(0x432, float:1.505E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0013:
            java.lang.String r1 = "borderRadius"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0021
            r5 = 1077(0x435, float:1.509E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0021:
            r5 = 1078(0x436, float:1.51E-42)
            r0[r5] = r3
            r5 = 1
            goto L_0x0162
        L_0x0028:
            java.lang.String r1 = "backgroundColor"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0036
            r5 = 1075(0x433, float:1.506E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0036:
            r5 = 1076(0x434, float:1.508E-42)
            r0[r5] = r3
            r5 = 0
            goto L_0x0162
        L_0x003d:
            java.lang.String r1 = "borderWidth"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x004b
            r5 = 1087(0x43f, float:1.523E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x004b:
            r5 = 6
            r1 = 1088(0x440, float:1.525E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0052:
            java.lang.String r1 = "borderColor"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0060
            r5 = 1097(0x449, float:1.537E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0060:
            r5 = 11
            r1 = 1098(0x44a, float:1.539E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0068:
            java.lang.String r1 = "borderBottomRightRadius"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0076
            r5 = 1081(0x439, float:1.515E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0076:
            r5 = 3
            r1 = 1082(0x43a, float:1.516E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x007d:
            java.lang.String r1 = "borderBottomLeftRadius"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x008b
            r5 = 1079(0x437, float:1.512E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x008b:
            r5 = 2
            r1 = 1080(0x438, float:1.513E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0092:
            java.lang.String r1 = "borderTopRightRadius"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x00a0
            r5 = 1085(0x43d, float:1.52E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x00a0:
            r5 = 5
            r1 = 1086(0x43e, float:1.522E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x00a7:
            java.lang.String r1 = "borderLeftWidth"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x00b5
            r5 = 1091(0x443, float:1.529E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x00b5:
            r5 = 8
            r1 = 1092(0x444, float:1.53E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x00bd:
            java.lang.String r1 = "borderLeftColor"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x00cb
            r5 = 1101(0x44d, float:1.543E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x00cb:
            r5 = 13
            r1 = 1102(0x44e, float:1.544E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x00d3:
            java.lang.String r1 = "borderTopLeftRadius"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x00e1
            r5 = 1083(0x43b, float:1.518E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x00e1:
            r5 = 4
            r1 = 1084(0x43c, float:1.519E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x00e8:
            java.lang.String r1 = "borderBottomWidth"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x00f6
            r5 = 1095(0x447, float:1.534E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x00f6:
            r5 = 10
            r1 = 1096(0x448, float:1.536E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x00fe:
            java.lang.String r1 = "borderBottomColor"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x010b
            r5 = 1105(0x451, float:1.548E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x010b:
            r5 = 15
            r1 = 1106(0x452, float:1.55E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0112:
            java.lang.String r1 = "borderTopWidth"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x011f
            r5 = 1089(0x441, float:1.526E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x011f:
            r5 = 7
            r1 = 1090(0x442, float:1.527E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0125:
            java.lang.String r1 = "borderTopColor"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0132
            r5 = 1099(0x44b, float:1.54E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0132:
            r5 = 12
            r1 = 1100(0x44c, float:1.541E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0139:
            java.lang.String r1 = "borderRightWidth"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0146
            r5 = 1093(0x445, float:1.532E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x0146:
            r5 = 9
            r1 = 1094(0x446, float:1.533E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x014d:
            java.lang.String r1 = "borderRightColor"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x015a
            r5 = 1103(0x44f, float:1.546E-42)
            r0[r5] = r3
            goto L_0x0161
        L_0x015a:
            r5 = 14
            r1 = 1104(0x450, float:1.547E-42)
            r0[r1] = r3
            goto L_0x0162
        L_0x0161:
            r5 = -1
        L_0x0162:
            switch(r5) {
                case 0: goto L_0x0183;
                case 1: goto L_0x017a;
                case 2: goto L_0x017a;
                case 3: goto L_0x017a;
                case 4: goto L_0x017a;
                case 5: goto L_0x017a;
                case 6: goto L_0x0171;
                case 7: goto L_0x0171;
                case 8: goto L_0x0171;
                case 9: goto L_0x0171;
                case 10: goto L_0x0171;
                case 11: goto L_0x016a;
                case 12: goto L_0x016a;
                case 13: goto L_0x016a;
                case 14: goto L_0x016a;
                case 15: goto L_0x016a;
                default: goto L_0x0165;
            }
        L_0x0165:
            r5 = 1111(0x457, float:1.557E-42)
            r0[r5] = r3
            return r6
        L_0x016a:
            java.lang.String r5 = "black"
            r6 = 1110(0x456, float:1.555E-42)
            r0[r6] = r3
            return r5
        L_0x0171:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            r6 = 1109(0x455, float:1.554E-42)
            r0[r6] = r3
            return r5
        L_0x017a:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            r6 = 1108(0x454, float:1.553E-42)
            r0[r6] = r3
            return r5
        L_0x0183:
            java.lang.String r5 = "transparent"
            r6 = 1107(0x453, float:1.551E-42)
            r0[r6] = r3
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.convertEmptyProperty(java.lang.String, java.lang.Object):java.lang.Object");
    }

    private void setActiveTouchListener() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsKey = getStyles().getPesudoStyles().containsKey(Constants.PSEUDO.ACTIVE);
        $jacocoInit[1112] = true;
        if (!containsKey) {
            $jacocoInit[1113] = true;
        } else {
            View realView = getRealView();
            if (realView == null) {
                $jacocoInit[1114] = true;
            } else {
                $jacocoInit[1115] = true;
                boolean isConsumeTouch = isConsumeTouch();
                $jacocoInit[1116] = true;
                if (!isConsumeTouch) {
                    $jacocoInit[1117] = true;
                    z = true;
                } else {
                    z = false;
                    $jacocoInit[1118] = true;
                }
                realView.setOnTouchListener(new TouchActivePseudoListener(this, z));
                $jacocoInit[1119] = true;
            }
        }
        $jacocoInit[1120] = true;
    }

    /* access modifiers changed from: protected */
    public boolean isConsumeTouch() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHostClickListeners == null) {
            $jacocoInit[1121] = true;
        } else if (this.mHostClickListeners.size() > 0) {
            $jacocoInit[1122] = true;
            $jacocoInit[1125] = true;
            z = true;
            $jacocoInit[1127] = true;
            return z;
        } else {
            $jacocoInit[1123] = true;
        }
        if (this.mGesture != null) {
            $jacocoInit[1124] = true;
            $jacocoInit[1125] = true;
            z = true;
            $jacocoInit[1127] = true;
            return z;
        }
        z = false;
        $jacocoInit[1126] = true;
        $jacocoInit[1127] = true;
        return z;
    }

    public void updateActivePseudo(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        setPseudoClassStatus(Constants.PSEUDO.ACTIVE, z);
        $jacocoInit[1128] = true;
    }

    /* access modifiers changed from: protected */
    public void setPseudoClassStatus(String str, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXStyle styles = getStyles();
        $jacocoInit[1129] = true;
        Map<String, Map<String, Object>> pesudoStyles = styles.getPesudoStyles();
        $jacocoInit[1130] = true;
        if (pesudoStyles == null) {
            $jacocoInit[1131] = true;
        } else if (pesudoStyles.size() == 0) {
            $jacocoInit[1132] = true;
        } else {
            if (this.mPesudoStatus != null) {
                $jacocoInit[1134] = true;
            } else {
                $jacocoInit[1135] = true;
                this.mPesudoStatus = new PesudoStatus();
                $jacocoInit[1136] = true;
            }
            PesudoStatus pesudoStatus = this.mPesudoStatus;
            $jacocoInit[1137] = true;
            Map<String, Object> pesudoResetStyles = styles.getPesudoResetStyles();
            $jacocoInit[1138] = true;
            Map<String, Object> updateStatusAndGetUpdateStyles = pesudoStatus.updateStatusAndGetUpdateStyles(str, z, pesudoStyles, pesudoResetStyles);
            if (updateStatusAndGetUpdateStyles == null) {
                $jacocoInit[1139] = true;
            } else if (z) {
                $jacocoInit[1140] = true;
                this.mPseudoResetGraphicSize = new GraphicSize(getLayoutSize().getWidth(), getLayoutSize().getHeight());
                $jacocoInit[1141] = true;
                if (updateStatusAndGetUpdateStyles.keySet().contains("width")) {
                    $jacocoInit[1142] = true;
                    getLayoutSize().setWidth(WXViewUtils.getRealPxByWidth(WXUtils.parseFloat(styles.getPesudoResetStyles().get("width:active")), getViewPortWidth()));
                    $jacocoInit[1143] = true;
                } else if (!updateStatusAndGetUpdateStyles.keySet().contains("height")) {
                    $jacocoInit[1144] = true;
                } else {
                    $jacocoInit[1145] = true;
                    getLayoutSize().setHeight(WXViewUtils.getRealPxByWidth(WXUtils.parseFloat(styles.getPesudoResetStyles().get("height:active")), getViewPortWidth()));
                    $jacocoInit[1146] = true;
                }
            } else if (this.mPseudoResetGraphicSize == null) {
                $jacocoInit[1147] = true;
            } else {
                $jacocoInit[1148] = true;
                setLayoutSize(this.mPseudoResetGraphicSize);
                $jacocoInit[1149] = true;
            }
            updateStyleByPesudo(updateStatusAndGetUpdateStyles);
            $jacocoInit[1150] = true;
            return;
        }
        $jacocoInit[1133] = true;
    }

    private void updateStyleByPesudo(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        GraphicActionUpdateStyle graphicActionUpdateStyle = new GraphicActionUpdateStyle(getInstance(), getRef(), map, getPadding(), getMargin(), getBorder(), true);
        $jacocoInit[1151] = true;
        graphicActionUpdateStyle.executeActionOnRender();
        $jacocoInit[1152] = true;
        if (getLayoutWidth() != 0.0f) {
            $jacocoInit[1153] = true;
        } else if (getLayoutWidth() != 0.0f) {
            $jacocoInit[1154] = true;
        } else {
            $jacocoInit[1155] = true;
            $jacocoInit[1158] = true;
        }
        WXBridgeManager.getInstance().setStyleWidth(getInstanceId(), getRef(), getLayoutWidth());
        $jacocoInit[1156] = true;
        WXBridgeManager.getInstance().setStyleHeight(getInstanceId(), getRef(), getLayoutHeight());
        $jacocoInit[1157] = true;
        $jacocoInit[1158] = true;
    }

    public int getStickyOffset() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mStickyOffset;
        $jacocoInit[1159] = true;
        return i;
    }

    public boolean canRecycled() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isFixed()) {
            $jacocoInit[1160] = true;
        } else if (isSticky()) {
            $jacocoInit[1161] = true;
            z = false;
            $jacocoInit[1165] = true;
            $jacocoInit[1166] = true;
            return z;
        } else {
            $jacocoInit[1162] = true;
        }
        if (!getAttrs().canRecycled()) {
            $jacocoInit[1163] = true;
            z = false;
            $jacocoInit[1165] = true;
            $jacocoInit[1166] = true;
            return z;
        }
        $jacocoInit[1164] = true;
        z = true;
        $jacocoInit[1166] = true;
        return z;
    }

    public void setStickyOffset(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStickyOffset = i;
        $jacocoInit[1167] = true;
    }

    public boolean isLayerTypeEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isLayerTypeEnabled = getInstance().isLayerTypeEnabled();
        $jacocoInit[1168] = true;
        return isLayerTypeEnabled;
    }

    public void setNeedLayoutOnAnimation(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNeedLayoutOnAnimation = z;
        $jacocoInit[1169] = true;
    }

    public void notifyNativeSizeChanged(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mNeedLayoutOnAnimation) {
            $jacocoInit[1170] = true;
            return;
        }
        WXBridgeManager instance = WXBridgeManager.getInstance();
        $jacocoInit[1171] = true;
        instance.setStyleWidth(getInstanceId(), getRef(), (float) i);
        $jacocoInit[1172] = true;
        instance.setStyleHeight(getInstanceId(), getRef(), (float) i2);
        $jacocoInit[1173] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0108  */
    @android.support.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onRenderFinish(int r10) {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = com.taobao.weex.tracing.WXTracing.isAvailable()
            r2 = 1
            if (r1 != 0) goto L_0x0011
            r10 = 1174(0x496, float:1.645E-42)
            r0[r10] = r2
            goto L_0x0122
        L_0x0011:
            r1 = 1175(0x497, float:1.647E-42)
            r0[r1] = r2
            com.taobao.weex.tracing.WXTracing$TraceInfo r1 = r9.mTraceInfo
            long r3 = r1.uiThreadNanos
            double r3 = com.taobao.weex.tracing.Stopwatch.nanosToMillis(r3)
            r1 = 2
            if (r10 != r1) goto L_0x0025
            r5 = 1176(0x498, float:1.648E-42)
            r0[r5] = r2
            goto L_0x0030
        L_0x0025:
            if (r10 == 0) goto L_0x002c
            r5 = 1177(0x499, float:1.65E-42)
            r0[r5] = r2
            goto L_0x008c
        L_0x002c:
            r5 = 1178(0x49a, float:1.651E-42)
            r0[r5] = r2
        L_0x0030:
            java.lang.String r5 = "DomExecute"
            java.lang.String r6 = r9.getInstanceId()
            com.taobao.weex.tracing.WXTracing$TraceInfo r7 = r9.mTraceInfo
            int r7 = r7.rootEventId
            com.taobao.weex.tracing.WXTracing$TraceEvent r5 = com.taobao.weex.tracing.WXTracing.newEvent(r5, r6, r7)
            java.lang.String r6 = "X"
            r5.ph = r6
            com.taobao.weex.tracing.WXTracing$TraceInfo r6 = r9.mTraceInfo
            long r6 = r6.domThreadStart
            r5.ts = r6
            java.lang.String r6 = "DOMThread"
            r5.tname = r6
            r6 = 1179(0x49b, float:1.652E-42)
            r0[r6] = r2
            java.lang.String r6 = r9.getComponentType()
            r5.name = r6
            r6 = 1180(0x49c, float:1.654E-42)
            r0[r6] = r2
            java.lang.Class r6 = r9.getClass()
            java.lang.String r6 = r6.getSimpleName()
            r5.classname = r6
            r6 = 1181(0x49d, float:1.655E-42)
            r0[r6] = r2
            com.taobao.weex.ui.component.WXVContainer r6 = r9.getParent()
            if (r6 != 0) goto L_0x0073
            r6 = 1182(0x49e, float:1.656E-42)
            r0[r6] = r2
            goto L_0x0085
        L_0x0073:
            r6 = 1183(0x49f, float:1.658E-42)
            r0[r6] = r2
            com.taobao.weex.ui.component.WXVContainer r6 = r9.getParent()
            java.lang.String r6 = r6.getRef()
            r5.parentRef = r6
            r6 = 1184(0x4a0, float:1.659E-42)
            r0[r6] = r2
        L_0x0085:
            r5.submit()
            r5 = 1185(0x4a1, float:1.66E-42)
            r0[r5] = r2
        L_0x008c:
            if (r10 != r1) goto L_0x0093
            r10 = 1186(0x4a2, float:1.662E-42)
            r0[r10] = r2
            goto L_0x009f
        L_0x0093:
            if (r10 == r2) goto L_0x009b
            r10 = 1187(0x4a3, float:1.663E-42)
            r0[r10] = r2
            goto L_0x0122
        L_0x009b:
            r10 = 1188(0x4a4, float:1.665E-42)
            r0[r10] = r2
        L_0x009f:
            com.taobao.weex.tracing.WXTracing$TraceInfo r10 = r9.mTraceInfo
            long r5 = r10.uiThreadStart
            r7 = -1
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0108
            r10 = 1189(0x4a5, float:1.666E-42)
            r0[r10] = r2
            java.lang.String r10 = "UIExecute"
            java.lang.String r1 = r9.getInstanceId()
            com.taobao.weex.tracing.WXTracing$TraceInfo r5 = r9.mTraceInfo
            int r5 = r5.rootEventId
            com.taobao.weex.tracing.WXTracing$TraceEvent r10 = com.taobao.weex.tracing.WXTracing.newEvent(r10, r1, r5)
            java.lang.String r1 = "X"
            r10.ph = r1
            r10.duration = r3
            com.taobao.weex.tracing.WXTracing$TraceInfo r1 = r9.mTraceInfo
            long r3 = r1.uiThreadStart
            r10.ts = r3
            r1 = 1190(0x4a6, float:1.668E-42)
            r0[r1] = r2
            java.lang.String r1 = r9.getComponentType()
            r10.name = r1
            r1 = 1191(0x4a7, float:1.669E-42)
            r0[r1] = r2
            java.lang.Class r1 = r9.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r10.classname = r1
            r1 = 1192(0x4a8, float:1.67E-42)
            r0[r1] = r2
            com.taobao.weex.ui.component.WXVContainer r1 = r9.getParent()
            if (r1 != 0) goto L_0x00ee
            r1 = 1193(0x4a9, float:1.672E-42)
            r0[r1] = r2
            goto L_0x0100
        L_0x00ee:
            r1 = 1194(0x4aa, float:1.673E-42)
            r0[r1] = r2
            com.taobao.weex.ui.component.WXVContainer r1 = r9.getParent()
            java.lang.String r1 = r1.getRef()
            r10.parentRef = r1
            r1 = 1195(0x4ab, float:1.675E-42)
            r0[r1] = r2
        L_0x0100:
            r10.submit()
            r10 = 1196(0x4ac, float:1.676E-42)
            r0[r10] = r2
            goto L_0x0122
        L_0x0108:
            boolean r10 = com.taobao.weex.WXEnvironment.isApkDebugable()
            if (r10 != 0) goto L_0x0113
            r10 = 1197(0x4ad, float:1.677E-42)
            r0[r10] = r2
            goto L_0x0122
        L_0x0113:
            boolean r10 = r9.isLazy()
            if (r10 == 0) goto L_0x011e
            r10 = 1198(0x4ae, float:1.679E-42)
            r0[r10] = r2
            goto L_0x0122
        L_0x011e:
            r10 = 1199(0x4af, float:1.68E-42)
            r0[r10] = r2
        L_0x0122:
            r10 = 1200(0x4b0, float:1.682E-42)
            r0[r10] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXComponent.onRenderFinish(int):void");
    }

    /* access modifiers changed from: protected */
    public boolean isRippleEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Object obj = getAttrs().get(Constants.Name.RIPPLE_ENABLED);
            $jacocoInit[1201] = true;
            boolean booleanValue = WXUtils.getBoolean(obj, false).booleanValue();
            $jacocoInit[1202] = true;
            return booleanValue;
        } catch (Throwable unused) {
            $jacocoInit[1203] = true;
            return false;
        }
    }

    public boolean isWaste() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.waste;
        $jacocoInit[1204] = true;
        return z;
    }

    public void setWaste(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.waste == z) {
            $jacocoInit[1205] = true;
        } else {
            this.waste = z;
            $jacocoInit[1206] = true;
            if (WXBasicComponentType.RECYCLE_LIST.equals(getParent().getComponentType())) {
                $jacocoInit[1207] = true;
            } else {
                $jacocoInit[1208] = true;
                NativeRenderObjectUtils.nativeRenderObjectChildWaste(getRenderObjectPtr(), z);
                $jacocoInit[1209] = true;
            }
            if (z) {
                $jacocoInit[1210] = true;
                getStyles().put("visibility", (Object) "hidden");
                $jacocoInit[1211] = true;
                if (getHostView() != null) {
                    getHostView().setVisibility(8);
                    $jacocoInit[1215] = true;
                } else if (this.mLazy) {
                    $jacocoInit[1212] = true;
                } else {
                    $jacocoInit[1213] = true;
                    lazy(true);
                    $jacocoInit[1214] = true;
                }
            } else {
                getStyles().put("visibility", (Object) "visible");
                $jacocoInit[1216] = true;
                if (getHostView() != null) {
                    getHostView().setVisibility(0);
                    $jacocoInit[1224] = true;
                } else if (!this.mLazy) {
                    $jacocoInit[1217] = true;
                } else {
                    $jacocoInit[1218] = true;
                    if (this.mParent == null) {
                        $jacocoInit[1219] = true;
                    } else if (!this.mParent.isLazy()) {
                        $jacocoInit[1220] = true;
                    } else {
                        $jacocoInit[1221] = true;
                        lazy(false);
                        $jacocoInit[1222] = true;
                    }
                    Statements.initLazyComponent(this, this.mParent);
                    $jacocoInit[1223] = true;
                }
            }
        }
        $jacocoInit[1225] = true;
    }

    public String getViewTreeKey() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mViewTreeKey != null) {
            $jacocoInit[1226] = true;
        } else {
            $jacocoInit[1227] = true;
            if (getParent() == null) {
                $jacocoInit[1228] = true;
                this.mViewTreeKey = hashCode() + JSMethod.NOT_SET + getRef();
                $jacocoInit[1229] = true;
            } else {
                this.mViewTreeKey = hashCode() + JSMethod.NOT_SET + getRef() + JSMethod.NOT_SET + getParent().indexOf(this);
                $jacocoInit[1230] = true;
            }
        }
        String str = this.mViewTreeKey;
        $jacocoInit[1231] = true;
        return str;
    }

    public WXTransition getTransition() {
        boolean[] $jacocoInit = $jacocoInit();
        WXTransition wXTransition = this.mTransition;
        $jacocoInit[1232] = true;
        return wXTransition;
    }

    public void setTransition(WXTransition wXTransition) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTransition = wXTransition;
        $jacocoInit[1233] = true;
    }

    public void addAnimationForElement(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[1234] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[1235] = true;
        } else {
            if (this.animations != null) {
                $jacocoInit[1236] = true;
            } else {
                $jacocoInit[1237] = true;
                this.animations = new ConcurrentLinkedQueue<>();
                $jacocoInit[1238] = true;
            }
            this.animations.add(new Pair(getRef(), map));
            $jacocoInit[1239] = true;
        }
        $jacocoInit[1240] = true;
    }

    private void parseAnimation() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.animations == null) {
            $jacocoInit[1241] = true;
            return;
        }
        Iterator<Pair<String, Map<String, Object>>> it = this.animations.iterator();
        $jacocoInit[1242] = true;
        while (it.hasNext()) {
            Pair next = it.next();
            $jacocoInit[1243] = true;
            if (TextUtils.isEmpty((CharSequence) next.first)) {
                $jacocoInit[1244] = true;
            } else {
                $jacocoInit[1245] = true;
                WXAnimationBean createAnimationBean = createAnimationBean((String) next.first, (Map) next.second);
                if (createAnimationBean == null) {
                    $jacocoInit[1246] = true;
                } else {
                    $jacocoInit[1247] = true;
                    GraphicActionAnimation graphicActionAnimation = new GraphicActionAnimation(getInstance(), getRef(), createAnimationBean);
                    $jacocoInit[1248] = true;
                    graphicActionAnimation.executeAction();
                    $jacocoInit[1249] = true;
                }
            }
            $jacocoInit[1250] = true;
        }
        this.animations.clear();
        $jacocoInit[1251] = true;
    }

    private WXAnimationBean createAnimationBean(String str, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[1252] = true;
        } else {
            try {
                $jacocoInit[1253] = true;
                Object obj = map.get("transform");
                $jacocoInit[1254] = true;
                if (!(obj instanceof String)) {
                    $jacocoInit[1255] = true;
                } else if (TextUtils.isEmpty((String) obj)) {
                    $jacocoInit[1256] = true;
                } else {
                    $jacocoInit[1257] = true;
                    $jacocoInit[1258] = true;
                    WXAnimationBean wXAnimationBean = new WXAnimationBean();
                    $jacocoInit[1259] = true;
                    int layoutWidth = (int) getLayoutWidth();
                    $jacocoInit[1260] = true;
                    int layoutHeight = (int) getLayoutHeight();
                    $jacocoInit[1261] = true;
                    wXAnimationBean.styles = new WXAnimationBean.Style();
                    $jacocoInit[1262] = true;
                    WXAnimationBean.Style style = wXAnimationBean.styles;
                    int instanceViewPortWidth = WXSDKManager.getInstanceViewPortWidth(getInstanceId());
                    $jacocoInit[1263] = true;
                    WXSDKInstance instance = getInstance();
                    $jacocoInit[1264] = true;
                    style.init((String) map.get(Constants.Name.TRANSFORM_ORIGIN), (String) obj, layoutWidth, layoutHeight, instanceViewPortWidth, instance);
                    $jacocoInit[1265] = true;
                    return wXAnimationBean;
                }
                $jacocoInit[1266] = true;
            } catch (RuntimeException e) {
                $jacocoInit[1267] = true;
                WXLogUtils.e("", (Throwable) e);
                $jacocoInit[1268] = true;
                return null;
            }
        }
        $jacocoInit[1269] = true;
        return null;
    }

    public void lazy(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLazy = z;
        $jacocoInit[1270] = true;
    }

    public long getRenderObjectPtr() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!getBasicComponentData().isRenderPtrEmpty()) {
            $jacocoInit[1271] = true;
        } else {
            $jacocoInit[1272] = true;
            getBasicComponentData().setRenderObjectPr(NativeRenderObjectUtils.nativeGetRenderObject(getInstanceId(), getRef()));
            $jacocoInit[1273] = true;
        }
        long renderObjectPr = getBasicComponentData().getRenderObjectPr();
        $jacocoInit[1274] = true;
        return renderObjectPr;
    }

    public void updateNativeAttr(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[1275] = true;
            return;
        }
        if (obj != null) {
            $jacocoInit[1276] = true;
        } else {
            obj = "";
            $jacocoInit[1277] = true;
        }
        getBasicComponentData().getAttrs().put(str, obj);
        $jacocoInit[1278] = true;
        NativeRenderObjectUtils.nativeUpdateRenderObjectAttr(getRenderObjectPtr(), str, obj.toString());
        $jacocoInit[1279] = true;
    }

    public void nativeUpdateAttrs(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        $jacocoInit[1280] = true;
        $jacocoInit[1281] = true;
        for (Map.Entry next : entrySet) {
            $jacocoInit[1282] = true;
            $jacocoInit[1283] = true;
            if (next.getKey() == null) {
                $jacocoInit[1284] = true;
            } else {
                updateNativeAttr((String) next.getKey(), next.getValue());
                $jacocoInit[1285] = true;
            }
        }
        $jacocoInit[1286] = true;
    }

    public void updateNativeStyle(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[1287] = true;
            return;
        }
        if (obj != null) {
            $jacocoInit[1288] = true;
        } else {
            obj = "";
            $jacocoInit[1289] = true;
        }
        getBasicComponentData().getStyles().put(str, obj);
        $jacocoInit[1290] = true;
        NativeRenderObjectUtils.nativeUpdateRenderObjectStyle(getRenderObjectPtr(), str, obj.toString());
        $jacocoInit[1291] = true;
    }

    public void updateNativeStyles(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        $jacocoInit[1292] = true;
        $jacocoInit[1293] = true;
        for (Map.Entry next : entrySet) {
            $jacocoInit[1294] = true;
            $jacocoInit[1295] = true;
            if (next.getKey() == null) {
                $jacocoInit[1296] = true;
            } else {
                updateNativeStyle((String) next.getKey(), next.getValue());
                $jacocoInit[1297] = true;
            }
        }
        $jacocoInit[1298] = true;
    }

    public void addLayerOverFlowListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getInstance() == null) {
            $jacocoInit[1299] = true;
        } else {
            $jacocoInit[1300] = true;
            getInstance().addLayerOverFlowListener(str);
            $jacocoInit[1301] = true;
        }
        $jacocoInit[1302] = true;
    }

    public void removeLayerOverFlowListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getInstance() == null) {
            $jacocoInit[1303] = true;
        } else {
            $jacocoInit[1304] = true;
            getInstance().removeLayerOverFlowListener(str);
            $jacocoInit[1305] = true;
        }
        $jacocoInit[1306] = true;
    }
}
