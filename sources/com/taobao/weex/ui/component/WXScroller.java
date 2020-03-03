package com.taobao.weex.ui.component;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.helper.ScrollStartEndHelper;
import com.taobao.weex.ui.component.helper.WXStickyHelper;
import com.taobao.weex.ui.view.IWXScroller;
import com.taobao.weex.ui.view.WXBaseRefreshLayout;
import com.taobao.weex.ui.view.WXHorizontalScrollView;
import com.taobao.weex.ui.view.WXScrollView;
import com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView;
import com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXScroller extends WXVContainer<ViewGroup> implements Scrollable, WXScrollView.WXScrollViewListener {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String DIRECTION = "direction";
    private static final int SWIPE_MIN_DISTANCE = 5;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;
    private Handler handler;
    private boolean isScrollable;
    private int mActiveFeature;
    private Map<String, AppearanceHelper> mAppearanceComponents;
    private int mChildrenLayoutOffset;
    private int mContentHeight;
    private boolean mForceLoadmoreNextTime;
    private GestureDetector mGestureDetector;
    private boolean mHasAddScrollEvent;
    private boolean mIsHostAttachedToWindow;
    private Point mLastReport;
    private int mOffsetAccuracy;
    private View.OnAttachStateChangeListener mOnAttachStateChangeListener;
    protected int mOrientation;
    private FrameLayout mRealView;
    private List<WXComponent> mRefreshs;
    private ScrollStartEndHelper mScrollStartEndHelper;
    private Map<String, Map<String, WXComponent>> mStickyMap;
    private boolean pageEnable;
    private int pageSize;
    private WXStickyHelper stickyHelper;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6265708195895870831L, "com/taobao/weex/ui/component/WXScroller", 375);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ boolean access$000(WXScroller wXScroller, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean shouldReport = wXScroller.shouldReport(i, i2);
        $jacocoInit[365] = true;
        return shouldReport;
    }

    static /* synthetic */ void access$100(WXScroller wXScroller, Rect rect, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        wXScroller.fireScrollEvent(rect, i, i2, i3, i4);
        $jacocoInit[366] = true;
    }

    static /* synthetic */ void access$200(WXScroller wXScroller, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        wXScroller.procAppear(i, i2, i3, i4);
        $jacocoInit[367] = true;
    }

    static /* synthetic */ int access$300(WXScroller wXScroller) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = wXScroller.pageSize;
        $jacocoInit[368] = true;
        return i;
    }

    static /* synthetic */ int access$302(WXScroller wXScroller, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXScroller.pageSize = i;
        $jacocoInit[369] = true;
        return i;
    }

    static /* synthetic */ GestureDetector access$400(WXScroller wXScroller) {
        boolean[] $jacocoInit = $jacocoInit();
        GestureDetector gestureDetector = wXScroller.mGestureDetector;
        $jacocoInit[370] = true;
        return gestureDetector;
    }

    static /* synthetic */ int access$500(WXScroller wXScroller) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = wXScroller.mActiveFeature;
        $jacocoInit[372] = true;
        return i;
    }

    static /* synthetic */ int access$502(WXScroller wXScroller, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXScroller.mActiveFeature = i;
        $jacocoInit[371] = true;
        return i;
    }

    static /* synthetic */ boolean access$602(WXScroller wXScroller, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXScroller.mIsHostAttachedToWindow = z;
        $jacocoInit[373] = true;
        return z;
    }

    static /* synthetic */ void access$700(WXScroller wXScroller) {
        boolean[] $jacocoInit = $jacocoInit();
        wXScroller.dispatchDisappearEvent();
        $jacocoInit[374] = true;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4765733991551106369L, "com/taobao/weex/ui/component/WXScroller$Creator", 3);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            wXSDKInstance.setUseScroller(true);
            $jacocoInit[1] = true;
            WXScroller wXScroller = new WXScroller(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[2] = true;
            return wXScroller;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXScroller(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXScroller(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        this.mOrientation = 1;
        $jacocoInit[1] = true;
        this.mRefreshs = new ArrayList();
        this.mChildrenLayoutOffset = 0;
        this.mForceLoadmoreNextTime = false;
        this.mOffsetAccuracy = 10;
        $jacocoInit[2] = true;
        this.mLastReport = new Point(-1, -1);
        this.mHasAddScrollEvent = false;
        this.mActiveFeature = 0;
        this.pageSize = 0;
        this.pageEnable = false;
        this.mIsHostAttachedToWindow = false;
        $jacocoInit[3] = true;
        this.mAppearanceComponents = new HashMap();
        $jacocoInit[4] = true;
        this.mStickyMap = new HashMap();
        this.mContentHeight = 0;
        $jacocoInit[5] = true;
        this.handler = new Handler(Looper.getMainLooper());
        this.isScrollable = true;
        $jacocoInit[6] = true;
        this.stickyHelper = new WXStickyHelper(this);
        $jacocoInit[7] = true;
        wXSDKInstance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_SCROLLER_NUM, 1.0d);
        $jacocoInit[8] = true;
    }

    public ViewGroup getRealView() {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = this.mRealView;
        $jacocoInit[9] = true;
        return frameLayout;
    }

    public void createViewImpl() {
        boolean[] $jacocoInit = $jacocoInit();
        super.createViewImpl();
        $jacocoInit[10] = true;
        $jacocoInit[11] = true;
        int i = 0;
        while (i < this.mRefreshs.size()) {
            $jacocoInit[12] = true;
            WXComponent wXComponent = this.mRefreshs.get(i);
            $jacocoInit[13] = true;
            wXComponent.createViewImpl();
            $jacocoInit[14] = true;
            checkRefreshOrLoading(wXComponent);
            i++;
            $jacocoInit[15] = true;
        }
        $jacocoInit[16] = true;
    }

    public ViewGroup getInnerView() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[17] = true;
            return null;
        } else if (getHostView() instanceof BounceScrollerView) {
            $jacocoInit[18] = true;
            ViewGroup viewGroup = (ViewGroup) ((BounceScrollerView) getHostView()).getInnerView();
            $jacocoInit[19] = true;
            return viewGroup;
        } else {
            ViewGroup viewGroup2 = (ViewGroup) getHostView();
            $jacocoInit[20] = true;
            return viewGroup2;
        }
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[21] = true;
        if (!ScrollStartEndHelper.isScrollEvent(str)) {
            $jacocoInit[22] = true;
        } else {
            $jacocoInit[23] = true;
            if (getInnerView() == null) {
                $jacocoInit[24] = true;
            } else if (this.mHasAddScrollEvent) {
                $jacocoInit[25] = true;
            } else {
                this.mHasAddScrollEvent = true;
                $jacocoInit[26] = true;
                if (getInnerView() instanceof WXScrollView) {
                    $jacocoInit[27] = true;
                    ((WXScrollView) getInnerView()).addScrollViewListener(new WXScrollView.WXScrollViewListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ WXScroller this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(7178312914123442021L, "com/taobao/weex/ui/component/WXScroller$1", 10);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        public void onScrollChanged(WXScrollView wXScrollView, int i, int i2, int i3, int i4) {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0.getScrollStartEndHelper().onScrolled(i, i2);
                            $jacocoInit[1] = true;
                            if (!this.this$0.getEvents().contains("scroll")) {
                                $jacocoInit[2] = true;
                                return;
                            }
                            if (!WXScroller.access$000(this.this$0, i, i2)) {
                                $jacocoInit[3] = true;
                            } else {
                                $jacocoInit[4] = true;
                                WXScroller.access$100(this.this$0, wXScrollView.getContentFrame(), i, i2, i3, i4);
                                $jacocoInit[5] = true;
                            }
                            $jacocoInit[6] = true;
                        }

                        public void onScrollToBottom(WXScrollView wXScrollView, int i, int i2) {
                            $jacocoInit()[7] = true;
                        }

                        public void onScrollStopped(WXScrollView wXScrollView, int i, int i2) {
                            $jacocoInit()[8] = true;
                        }

                        public void onScroll(WXScrollView wXScrollView, int i, int i2) {
                            $jacocoInit()[9] = true;
                        }
                    });
                    $jacocoInit[28] = true;
                } else if (!(getInnerView() instanceof WXHorizontalScrollView)) {
                    $jacocoInit[29] = true;
                } else {
                    $jacocoInit[30] = true;
                    ((WXHorizontalScrollView) getInnerView()).addScrollViewListener(new WXHorizontalScrollView.ScrollViewListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ WXScroller this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(-6857454512751559829L, "com/taobao/weex/ui/component/WXScroller$2", 7);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        public void onScrollChanged(WXHorizontalScrollView wXHorizontalScrollView, int i, int i2, int i3, int i4) {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0.getScrollStartEndHelper().onScrolled(i, i2);
                            $jacocoInit[1] = true;
                            if (!this.this$0.getEvents().contains("scroll")) {
                                $jacocoInit[2] = true;
                                return;
                            }
                            if (!WXScroller.access$000(this.this$0, i, i2)) {
                                $jacocoInit[3] = true;
                            } else {
                                $jacocoInit[4] = true;
                                WXScroller.access$100(this.this$0, wXHorizontalScrollView.getContentFrame(), i, i2, i3, i4);
                                $jacocoInit[5] = true;
                            }
                            $jacocoInit[6] = true;
                        }
                    });
                    $jacocoInit[31] = true;
                }
            }
        }
        $jacocoInit[32] = true;
    }

    private void fireScrollEvent(Rect rect, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent("scroll", getScrollEvent(i, i2));
        $jacocoInit[33] = true;
    }

    public Map<String, Object> getScrollEvent(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = new Rect();
        $jacocoInit[34] = true;
        if (getInnerView() instanceof WXScrollView) {
            $jacocoInit[35] = true;
            rect = ((WXScrollView) getInnerView()).getContentFrame();
            $jacocoInit[36] = true;
        } else if (!(getInnerView() instanceof WXHorizontalScrollView)) {
            $jacocoInit[37] = true;
        } else {
            $jacocoInit[38] = true;
            rect = ((WXHorizontalScrollView) getInnerView()).getContentFrame();
            $jacocoInit[39] = true;
        }
        HashMap hashMap = new HashMap(2);
        $jacocoInit[40] = true;
        HashMap hashMap2 = new HashMap(2);
        $jacocoInit[41] = true;
        HashMap hashMap3 = new HashMap(2);
        $jacocoInit[42] = true;
        int instanceViewPortWidth = getInstance().getInstanceViewPortWidth();
        $jacocoInit[43] = true;
        hashMap2.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth((float) rect.width(), instanceViewPortWidth)));
        $jacocoInit[44] = true;
        hashMap2.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth((float) rect.height(), instanceViewPortWidth)));
        $jacocoInit[45] = true;
        hashMap3.put("x", Float.valueOf(-WXViewUtils.getWebPxByWidth((float) i, instanceViewPortWidth)));
        $jacocoInit[46] = true;
        hashMap3.put(Constants.Name.Y, Float.valueOf(-WXViewUtils.getWebPxByWidth((float) i2, instanceViewPortWidth)));
        $jacocoInit[47] = true;
        hashMap.put(Constants.Name.CONTENT_SIZE, hashMap2);
        $jacocoInit[48] = true;
        hashMap.put(Constants.Name.CONTENT_OFFSET, hashMap3);
        $jacocoInit[49] = true;
        return hashMap;
    }

    private boolean shouldReport(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastReport.x != -1) {
            $jacocoInit[50] = true;
        } else if (this.mLastReport.y != -1) {
            $jacocoInit[51] = true;
        } else {
            this.mLastReport.x = i;
            this.mLastReport.y = i2;
            $jacocoInit[52] = true;
            return true;
        }
        if (this.mOrientation != 0) {
            $jacocoInit[53] = true;
        } else {
            $jacocoInit[54] = true;
            if (Math.abs(i - this.mLastReport.x) < this.mOffsetAccuracy) {
                $jacocoInit[55] = true;
            } else {
                this.mLastReport.x = i;
                this.mLastReport.y = i2;
                $jacocoInit[56] = true;
                return true;
            }
        }
        if (this.mOrientation != 1) {
            $jacocoInit[57] = true;
        } else {
            $jacocoInit[58] = true;
            if (Math.abs(i2 - this.mLastReport.y) < this.mOffsetAccuracy) {
                $jacocoInit[59] = true;
            } else {
                this.mLastReport.x = i;
                this.mLastReport.y = i2;
                $jacocoInit[60] = true;
                return true;
            }
        }
        $jacocoInit[61] = true;
        return false;
    }

    public void addSubView(View view, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[62] = true;
        } else if (getRealView() == null) {
            $jacocoInit[63] = true;
        } else if (view instanceof WXBaseRefreshLayout) {
            $jacocoInit[65] = true;
            return;
        } else {
            if (i >= getRealView().getChildCount()) {
                $jacocoInit[66] = true;
                i = -1;
            } else {
                $jacocoInit[67] = true;
            }
            if (i == -1) {
                $jacocoInit[68] = true;
                getRealView().addView(view);
                $jacocoInit[69] = true;
            } else {
                getRealView().addView(view, i);
                $jacocoInit[70] = true;
            }
            $jacocoInit[71] = true;
            return;
        }
        $jacocoInit[64] = true;
    }

    /* access modifiers changed from: protected */
    public int getChildrenLayoutTopOffset() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChildrenLayoutOffset != 0) {
            $jacocoInit[72] = true;
        } else {
            $jacocoInit[73] = true;
            int size = this.mRefreshs.size();
            if (size <= 0) {
                $jacocoInit[74] = true;
            } else {
                int i = 0;
                $jacocoInit[75] = true;
                while (i < size) {
                    $jacocoInit[77] = true;
                    $jacocoInit[78] = true;
                    this.mChildrenLayoutOffset += this.mRefreshs.get(i).getLayoutTopOffsetForSibling();
                    i++;
                    $jacocoInit[79] = true;
                }
                $jacocoInit[76] = true;
            }
        }
        int i2 = this.mChildrenLayoutOffset;
        $jacocoInit[80] = true;
        return i2;
    }

    public void addChild(WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(wXComponent instanceof WXBaseRefresh)) {
            $jacocoInit[81] = true;
        } else {
            $jacocoInit[82] = true;
            if (!checkRefreshOrLoading(wXComponent)) {
                $jacocoInit[83] = true;
            } else {
                $jacocoInit[84] = true;
                this.mRefreshs.add(wXComponent);
                $jacocoInit[85] = true;
            }
        }
        super.addChild(wXComponent, i);
        $jacocoInit[86] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkRefreshOrLoading(final com.taobao.weex.ui.component.WXComponent r7) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            r2 = 87
            r0[r2] = r1
            boolean r2 = r7 instanceof com.taobao.weex.ui.component.WXRefresh
            r3 = 100
            if (r2 != 0) goto L_0x0014
            r2 = 88
            r0[r2] = r1
            goto L_0x001e
        L_0x0014:
            android.view.View r2 = r6.getHostView()
            if (r2 != 0) goto L_0x0020
            r2 = 89
            r0[r2] = r1
        L_0x001e:
            r2 = 0
            goto L_0x004b
        L_0x0020:
            r2 = 90
            r0[r2] = r1
            android.view.View r2 = r6.getHostView()
            com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView r2 = (com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView) r2
            r5 = r7
            com.taobao.weex.ui.component.WXRefresh r5 = (com.taobao.weex.ui.component.WXRefresh) r5
            r2.setOnRefreshListener(r5)
            r2 = 91
            r0[r2] = r1
            com.taobao.weex.ui.component.WXScroller$3 r2 = new com.taobao.weex.ui.component.WXScroller$3
            r2.<init>(r6, r7)
            java.lang.Runnable r2 = com.taobao.weex.common.WXThread.secure((java.lang.Runnable) r2)
            r5 = 92
            r0[r5] = r1
            android.os.Handler r5 = r6.handler
            r5.postDelayed(r2, r3)
            r2 = 93
            r0[r2] = r1
            r2 = 1
        L_0x004b:
            boolean r5 = r7 instanceof com.taobao.weex.ui.component.WXLoading
            if (r5 != 0) goto L_0x0054
            r7 = 94
            r0[r7] = r1
            goto L_0x008a
        L_0x0054:
            android.view.View r5 = r6.getHostView()
            if (r5 != 0) goto L_0x005f
            r7 = 95
            r0[r7] = r1
            goto L_0x008a
        L_0x005f:
            r2 = 96
            r0[r2] = r1
            android.view.View r2 = r6.getHostView()
            com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView r2 = (com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView) r2
            r5 = r7
            com.taobao.weex.ui.component.WXLoading r5 = (com.taobao.weex.ui.component.WXLoading) r5
            r2.setOnLoadingListener(r5)
            r2 = 97
            r0[r2] = r1
            com.taobao.weex.ui.component.WXScroller$4 r2 = new com.taobao.weex.ui.component.WXScroller$4
            r2.<init>(r6, r7)
            java.lang.Runnable r7 = com.taobao.weex.common.WXThread.secure((java.lang.Runnable) r2)
            r2 = 98
            r0[r2] = r1
            android.os.Handler r2 = r6.handler
            r2.postDelayed(r7, r3)
            r7 = 99
            r0[r7] = r1
            r2 = 1
        L_0x008a:
            r7 = 100
            r0[r7] = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXScroller.checkRefreshOrLoading(com.taobao.weex.ui.component.WXComponent):boolean");
    }

    public void remove(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        super.remove(wXComponent, z);
        if (wXComponent instanceof WXLoading) {
            $jacocoInit[101] = true;
            ((BaseBounceView) getHostView()).removeFooterView(wXComponent);
            $jacocoInit[102] = true;
        } else if (!(wXComponent instanceof WXRefresh)) {
            $jacocoInit[103] = true;
        } else {
            $jacocoInit[104] = true;
            ((BaseBounceView) getHostView()).removeHeaderView(wXComponent);
            $jacocoInit[105] = true;
        }
        $jacocoInit[106] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        if (this.mAppearanceComponents == null) {
            $jacocoInit[107] = true;
        } else {
            $jacocoInit[108] = true;
            this.mAppearanceComponents.clear();
            $jacocoInit[109] = true;
        }
        if (this.mStickyMap == null) {
            $jacocoInit[110] = true;
        } else {
            $jacocoInit[111] = true;
            this.mStickyMap.clear();
            $jacocoInit[112] = true;
        }
        if (this.mOnAttachStateChangeListener == null) {
            $jacocoInit[113] = true;
        } else if (getInnerView() == null) {
            $jacocoInit[114] = true;
        } else {
            $jacocoInit[115] = true;
            getInnerView().removeOnAttachStateChangeListener(this.mOnAttachStateChangeListener);
            $jacocoInit[116] = true;
        }
        if (getInnerView() == null) {
            $jacocoInit[117] = true;
        } else if (!(getInnerView() instanceof IWXScroller)) {
            $jacocoInit[118] = true;
        } else {
            $jacocoInit[119] = true;
            ((IWXScroller) getInnerView()).destroy();
            $jacocoInit[120] = true;
        }
        $jacocoInit[121] = true;
    }

    public void setMarginsSupportRTL(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 17) {
            $jacocoInit[122] = true;
            marginLayoutParams.setMargins(i, i2, i3, i4);
            $jacocoInit[123] = true;
            marginLayoutParams.setMarginStart(i);
            $jacocoInit[124] = true;
            marginLayoutParams.setMarginEnd(i3);
            $jacocoInit[125] = true;
        } else if (marginLayoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) marginLayoutParams;
            $jacocoInit[126] = true;
            if (isLayoutRTL()) {
                layoutParams.gravity = 53;
                $jacocoInit[127] = true;
                marginLayoutParams.setMargins(i3, i2, i, i4);
                $jacocoInit[128] = true;
            } else {
                layoutParams.gravity = 51;
                $jacocoInit[129] = true;
                marginLayoutParams.setMargins(i, i2, i3, i4);
                $jacocoInit[130] = true;
            }
            $jacocoInit[131] = true;
        } else {
            marginLayoutParams.setMargins(i, i2, i3, i4);
            $jacocoInit[132] = true;
        }
        $jacocoInit[133] = true;
    }

    public void setLayout(WXComponent wXComponent) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(wXComponent.getComponentType())) {
            $jacocoInit[134] = true;
        } else {
            $jacocoInit[135] = true;
            if (TextUtils.isEmpty(wXComponent.getRef())) {
                $jacocoInit[136] = true;
            } else if (wXComponent.getLayoutPosition() == null) {
                $jacocoInit[137] = true;
            } else {
                $jacocoInit[138] = true;
                if (wXComponent.getLayoutSize() == null) {
                    $jacocoInit[139] = true;
                } else {
                    if (wXComponent.getHostView() == null) {
                        $jacocoInit[141] = true;
                    } else {
                        $jacocoInit[142] = true;
                        if (wXComponent.isLayoutRTL()) {
                            $jacocoInit[143] = true;
                            i = 1;
                        } else {
                            i = 0;
                            $jacocoInit[144] = true;
                        }
                        $jacocoInit[145] = true;
                        ViewCompat.setLayoutDirection(wXComponent.getHostView(), i);
                        $jacocoInit[146] = true;
                    }
                    super.setLayout(wXComponent);
                    $jacocoInit[147] = true;
                    return;
                }
            }
        }
        $jacocoInit[140] = true;
    }

    /* access modifiers changed from: protected */
    public WXComponent.MeasureOutput measure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent.MeasureOutput measureOutput = new WXComponent.MeasureOutput();
        if (this.mOrientation == 0) {
            $jacocoInit[148] = true;
            int screenWidth = WXViewUtils.getScreenWidth(WXEnvironment.sApplication);
            $jacocoInit[149] = true;
            int weexWidth = WXViewUtils.getWeexWidth(getInstanceId());
            if (weexWidth >= screenWidth) {
                $jacocoInit[150] = true;
            } else {
                $jacocoInit[151] = true;
                screenWidth = weexWidth;
            }
            if (i > screenWidth) {
                $jacocoInit[152] = true;
                i = -1;
            } else {
                $jacocoInit[153] = true;
            }
            measureOutput.width = i;
            measureOutput.height = i2;
            $jacocoInit[154] = true;
        } else {
            int screenHeight = WXViewUtils.getScreenHeight(WXEnvironment.sApplication);
            $jacocoInit[155] = true;
            int weexHeight = WXViewUtils.getWeexHeight(getInstanceId());
            if (weexHeight >= screenHeight) {
                $jacocoInit[156] = true;
            } else {
                $jacocoInit[157] = true;
                screenHeight = weexHeight;
            }
            if (i2 > screenHeight) {
                $jacocoInit[158] = true;
                i2 = -1;
            } else {
                $jacocoInit[159] = true;
            }
            measureOutput.height = i2;
            measureOutput.width = i;
            $jacocoInit[160] = true;
        }
        $jacocoInit[161] = true;
        return measureOutput;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.taobao.weex.ui.view.WXHorizontalScrollView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.ViewGroup initComponentHostView(@android.support.annotation.NonNull android.content.Context r7) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.dom.WXAttr r1 = r6.getAttrs()
            boolean r1 = r1.isEmpty()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0018
            java.lang.String r1 = "vertical"
            r4 = 162(0xa2, float:2.27E-43)
            r0[r4] = r3
            goto L_0x0092
        L_0x0018:
            com.taobao.weex.dom.WXAttr r1 = r6.getAttrs()
            java.lang.String r1 = r1.getScrollDirection()
            r4 = 163(0xa3, float:2.28E-43)
            r0[r4] = r3
            com.taobao.weex.dom.WXAttr r4 = r6.getAttrs()
            java.lang.String r5 = "pagingEnabled"
            java.lang.Object r4 = r4.get(r5)
            r5 = 164(0xa4, float:2.3E-43)
            r0[r5] = r3
            if (r4 != 0) goto L_0x0039
            r4 = 165(0xa5, float:2.31E-43)
            r0[r4] = r3
            goto L_0x0047
        L_0x0039:
            java.lang.String r4 = r4.toString()
            boolean r4 = java.lang.Boolean.parseBoolean(r4)
            if (r4 != 0) goto L_0x004d
            r4 = 166(0xa6, float:2.33E-43)
            r0[r4] = r3
        L_0x0047:
            r4 = 168(0xa8, float:2.35E-43)
            r0[r4] = r3
            r4 = 0
            goto L_0x0052
        L_0x004d:
            r4 = 167(0xa7, float:2.34E-43)
            r0[r4] = r3
            r4 = 1
        L_0x0052:
            r6.pageEnable = r4
            r4 = 169(0xa9, float:2.37E-43)
            r0[r4] = r3
            com.taobao.weex.dom.WXAttr r4 = r6.getAttrs()
            java.lang.String r5 = "pageSize"
            java.lang.Object r4 = r4.get(r5)
            if (r4 != 0) goto L_0x0069
            r4 = 170(0xaa, float:2.38E-43)
            r0[r4] = r3
            goto L_0x0092
        L_0x0069:
            r5 = 171(0xab, float:2.4E-43)
            r0[r5] = r3
            float r4 = com.taobao.weex.utils.WXUtils.getFloat(r4)
            r5 = 172(0xac, float:2.41E-43)
            r0[r5] = r3
            com.taobao.weex.WXSDKInstance r5 = r6.getInstance()
            int r5 = r5.getInstanceViewPortWidth()
            float r4 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r4, r5)
            r5 = 0
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x008b
            r4 = 173(0xad, float:2.42E-43)
            r0[r4] = r3
            goto L_0x0092
        L_0x008b:
            int r4 = (int) r4
            r6.pageSize = r4
            r4 = 174(0xae, float:2.44E-43)
            r0[r4] = r3
        L_0x0092:
            java.lang.String r4 = "horizontal"
            boolean r1 = r4.equals(r1)
            r4 = -1
            if (r1 == 0) goto L_0x011b
            r6.mOrientation = r2
            r1 = 175(0xaf, float:2.45E-43)
            r0[r1] = r3
            com.taobao.weex.ui.view.WXHorizontalScrollView r1 = new com.taobao.weex.ui.view.WXHorizontalScrollView
            r1.<init>(r7)
            r5 = 176(0xb0, float:2.47E-43)
            r0[r5] = r3
            android.widget.FrameLayout r5 = new android.widget.FrameLayout
            r5.<init>(r7)
            r6.mRealView = r5
            r7 = 177(0xb1, float:2.48E-43)
            r0[r7] = r3
            com.taobao.weex.ui.component.WXScroller$5 r7 = new com.taobao.weex.ui.component.WXScroller$5
            r7.<init>(r6)
            r1.setScrollViewListener(r7)
            r7 = 178(0xb2, float:2.5E-43)
            r0[r7] = r3
            android.widget.FrameLayout$LayoutParams r7 = new android.widget.FrameLayout$LayoutParams
            r7.<init>(r4, r4)
            r4 = 179(0xb3, float:2.51E-43)
            r0[r4] = r3
            android.widget.FrameLayout r4 = r6.mRealView
            r1.addView(r4, r7)
            r7 = 180(0xb4, float:2.52E-43)
            r0[r7] = r3
            r1.setHorizontalScrollBarEnabled(r2)
            r7 = 181(0xb5, float:2.54E-43)
            r0[r7] = r3
            com.taobao.weex.ui.component.WXScroller$6 r7 = new com.taobao.weex.ui.component.WXScroller$6
            r7.<init>(r6, r1, r6)
            r2 = 182(0xb6, float:2.55E-43)
            r0[r2] = r3
            android.widget.FrameLayout r2 = r6.mRealView
            com.taobao.weex.ui.component.WXScroller$7 r4 = new com.taobao.weex.ui.component.WXScroller$7
            r4.<init>(r6, r7)
            r2.addOnAttachStateChangeListener(r4)
            boolean r7 = r6.pageEnable
            if (r7 != 0) goto L_0x00f6
            r7 = 183(0xb7, float:2.56E-43)
            r0[r7] = r3
            goto L_0x0116
        L_0x00f6:
            r7 = 184(0xb8, float:2.58E-43)
            r0[r7] = r3
            android.view.GestureDetector r7 = new android.view.GestureDetector
            com.taobao.weex.ui.component.WXScroller$MyGestureDetector r2 = new com.taobao.weex.ui.component.WXScroller$MyGestureDetector
            r2.<init>(r6, r1)
            r7.<init>(r2)
            r6.mGestureDetector = r7
            r7 = 185(0xb9, float:2.59E-43)
            r0[r7] = r3
            com.taobao.weex.ui.component.WXScroller$8 r7 = new com.taobao.weex.ui.component.WXScroller$8
            r7.<init>(r6, r1)
            r1.setOnTouchListener(r7)
            r7 = 186(0xba, float:2.6E-43)
            r0[r7] = r3
        L_0x0116:
            r7 = 187(0xbb, float:2.62E-43)
            r0[r7] = r3
            goto L_0x018a
        L_0x011b:
            r6.mOrientation = r3
            r1 = 188(0xbc, float:2.63E-43)
            r0[r1] = r3
            com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView r1 = new com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView
            int r2 = r6.mOrientation
            r1.<init>(r7, r2, r6)
            r2 = 189(0xbd, float:2.65E-43)
            r0[r2] = r3
            android.widget.FrameLayout r2 = new android.widget.FrameLayout
            r2.<init>(r7)
            r6.mRealView = r2
            r7 = 190(0xbe, float:2.66E-43)
            r0[r7] = r3
            android.view.View r7 = r1.getInnerView()
            com.taobao.weex.ui.view.WXScrollView r7 = (com.taobao.weex.ui.view.WXScrollView) r7
            r2 = 191(0xbf, float:2.68E-43)
            r0[r2] = r3
            r7.addScrollViewListener(r6)
            r2 = 192(0xc0, float:2.69E-43)
            r0[r2] = r3
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r2.<init>(r4, r4)
            r4 = 193(0xc1, float:2.7E-43)
            r0[r4] = r3
            android.widget.FrameLayout r4 = r6.mRealView
            r7.addView(r4, r2)
            r2 = 194(0xc2, float:2.72E-43)
            r0[r2] = r3
            r7.setVerticalScrollBarEnabled(r3)
            r2 = 195(0xc3, float:2.73E-43)
            r0[r2] = r3
            com.taobao.weex.dom.WXAttr r2 = r6.getAttrs()
            java.lang.String r4 = "nestedScrollingEnabled"
            java.lang.Object r2 = r2.get(r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r2 = com.taobao.weex.utils.WXUtils.getBoolean(r2, r4)
            boolean r2 = r2.booleanValue()
            r7.setNestedScrollingEnabled(r2)
            r2 = 196(0xc4, float:2.75E-43)
            r0[r2] = r3
            com.taobao.weex.ui.component.WXScroller$9 r2 = new com.taobao.weex.ui.component.WXScroller$9
            r2.<init>(r6)
            r7.addScrollViewListener(r2)
            r7 = 197(0xc5, float:2.76E-43)
            r0[r7] = r3
        L_0x018a:
            android.view.ViewTreeObserver r7 = r1.getViewTreeObserver()
            com.taobao.weex.ui.component.WXScroller$10 r2 = new com.taobao.weex.ui.component.WXScroller$10
            r2.<init>(r6)
            r7.addOnGlobalLayoutListener(r2)
            r7 = 198(0xc6, float:2.77E-43)
            r0[r7] = r3
            com.taobao.weex.ui.component.WXScroller$11 r7 = new com.taobao.weex.ui.component.WXScroller$11
            r7.<init>(r6)
            r6.mOnAttachStateChangeListener = r7
            r7 = 199(0xc7, float:2.79E-43)
            r0[r7] = r3
            android.view.View$OnAttachStateChangeListener r7 = r6.mOnAttachStateChangeListener
            r1.addOnAttachStateChangeListener(r7)
            r7 = 200(0xc8, float:2.8E-43)
            r0[r7] = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXScroller.initComponentHostView(android.content.Context):android.view.ViewGroup");
    }

    public int getScrollY() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (getInnerView() == null) {
            i = 0;
            $jacocoInit[201] = true;
        } else {
            i = getInnerView().getScrollY();
            $jacocoInit[202] = true;
        }
        $jacocoInit[203] = true;
        return i;
    }

    public int getScrollX() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (getInnerView() == null) {
            i = 0;
            $jacocoInit[204] = true;
        } else {
            i = getInnerView().getScrollX();
            $jacocoInit[205] = true;
        }
        $jacocoInit[206] = true;
        return i;
    }

    public int getOrientation() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mOrientation;
        $jacocoInit[207] = true;
        return i;
    }

    public Map<String, Map<String, WXComponent>> getStickMap() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, Map<String, WXComponent>> map = this.mStickyMap;
        $jacocoInit[208] = true;
        return map;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = -223520855(0xfffffffff2ad57a9, float:-6.866801E30)
            r3 = 1
            if (r1 == r2) goto L_0x0043
            r2 = -5620052(0xffffffffffaa3eac, float:NaN)
            if (r1 == r2) goto L_0x0030
            r2 = 66669991(0x3f94da7, float:1.4652733E-36)
            if (r1 == r2) goto L_0x001d
            r1 = 209(0xd1, float:2.93E-43)
            r0[r1] = r3
            goto L_0x004f
        L_0x001d:
            java.lang.String r1 = "scrollable"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x002a
            r1 = 212(0xd4, float:2.97E-43)
            r0[r1] = r3
            goto L_0x004f
        L_0x002a:
            r1 = 213(0xd5, float:2.98E-43)
            r0[r1] = r3
            r1 = 1
            goto L_0x0056
        L_0x0030:
            java.lang.String r1 = "offsetAccuracy"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x003d
            r1 = 214(0xd6, float:3.0E-43)
            r0[r1] = r3
            goto L_0x004f
        L_0x003d:
            r1 = 2
            r2 = 215(0xd7, float:3.01E-43)
            r0[r2] = r3
            goto L_0x0056
        L_0x0043:
            java.lang.String r1 = "showScrollbar"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0051
            r1 = 210(0xd2, float:2.94E-43)
            r0[r1] = r3
        L_0x004f:
            r1 = -1
            goto L_0x0056
        L_0x0051:
            r1 = 0
            r2 = 211(0xd3, float:2.96E-43)
            r0[r2] = r3
        L_0x0056:
            switch(r1) {
                case 0: goto L_0x0094;
                case 1: goto L_0x007c;
                case 2: goto L_0x0062;
                default: goto L_0x0059;
            }
        L_0x0059:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 224(0xe0, float:3.14E-43)
            r0[r6] = r3
            return r5
        L_0x0062:
            r5 = 10
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Integer r5 = com.taobao.weex.utils.WXUtils.getInteger(r6, r5)
            int r5 = r5.intValue()
            r6 = 222(0xde, float:3.11E-43)
            r0[r6] = r3
            r4.setOffsetAccuracy(r5)
            r5 = 223(0xdf, float:3.12E-43)
            r0[r5] = r3
            return r3
        L_0x007c:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            boolean r5 = r5.booleanValue()
            r6 = 220(0xdc, float:3.08E-43)
            r0[r6] = r3
            r4.setScrollable(r5)
            r5 = 221(0xdd, float:3.1E-43)
            r0[r5] = r3
            return r3
        L_0x0094:
            r5 = 0
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            if (r5 != 0) goto L_0x00a0
            r5 = 216(0xd8, float:3.03E-43)
            r0[r5] = r3
            goto L_0x00af
        L_0x00a0:
            r6 = 217(0xd9, float:3.04E-43)
            r0[r6] = r3
            boolean r5 = r5.booleanValue()
            r4.setShowScrollbar(r5)
            r5 = 218(0xda, float:3.05E-43)
            r0[r5] = r3
        L_0x00af:
            r5 = 219(0xdb, float:3.07E-43)
            r0[r5] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXScroller.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "showScrollbar")
    public void setShowScrollbar(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getInnerView() == null) {
            $jacocoInit[225] = true;
            return;
        }
        if (this.mOrientation == 1) {
            $jacocoInit[226] = true;
            getInnerView().setVerticalScrollBarEnabled(z);
            $jacocoInit[227] = true;
        } else {
            getInnerView().setHorizontalScrollBarEnabled(z);
            $jacocoInit[228] = true;
        }
        $jacocoInit[229] = true;
    }

    @WXComponentProp(name = "scrollable")
    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isScrollable = z;
        $jacocoInit[230] = true;
        ViewGroup innerView = getInnerView();
        if (innerView instanceof WXHorizontalScrollView) {
            $jacocoInit[231] = true;
            ((WXHorizontalScrollView) innerView).setScrollable(z);
            $jacocoInit[232] = true;
        } else if (!(innerView instanceof WXScrollView)) {
            $jacocoInit[233] = true;
        } else {
            $jacocoInit[234] = true;
            ((WXScrollView) innerView).setScrollable(z);
            $jacocoInit[235] = true;
        }
        $jacocoInit[236] = true;
    }

    @WXComponentProp(name = "offsetAccuracy")
    public void setOffsetAccuracy(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOffsetAccuracy = (int) WXViewUtils.getRealPxByWidth((float) i, getInstance().getInstanceViewPortWidth());
        $jacocoInit[237] = true;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isScrollable;
        $jacocoInit[238] = true;
        return z;
    }

    public void bindStickStyle(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.stickyHelper.bindStickStyle(wXComponent, this.mStickyMap);
        $jacocoInit[239] = true;
    }

    public void unbindStickStyle(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.stickyHelper.unbindStickStyle(wXComponent, this.mStickyMap);
        $jacocoInit[240] = true;
    }

    public void bindAppearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setWatch(0, wXComponent, true);
        $jacocoInit[241] = true;
    }

    private void setWatch(int i, WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        AppearanceHelper appearanceHelper = this.mAppearanceComponents.get(wXComponent.getRef());
        if (appearanceHelper != null) {
            $jacocoInit[242] = true;
        } else {
            $jacocoInit[243] = true;
            appearanceHelper = new AppearanceHelper(wXComponent);
            $jacocoInit[244] = true;
            this.mAppearanceComponents.put(wXComponent.getRef(), appearanceHelper);
            $jacocoInit[245] = true;
        }
        appearanceHelper.setWatchEvent(i, z);
        $jacocoInit[246] = true;
        procAppear(0, 0, 0, 0);
        $jacocoInit[247] = true;
    }

    public void bindDisappearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setWatch(1, wXComponent, true);
        $jacocoInit[248] = true;
    }

    public void unbindAppearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setWatch(0, wXComponent, false);
        $jacocoInit[249] = true;
    }

    public void unbindDisappearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setWatch(1, wXComponent, false);
        $jacocoInit[250] = true;
    }

    public void scrollTo(WXComponent wXComponent, Map<String, Object> map) {
        boolean z;
        int i;
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        float f = 0.0f;
        if (map == null) {
            $jacocoInit[251] = true;
            z = true;
        } else {
            $jacocoInit[252] = true;
            if (map.get("offset") == null) {
                str = "0";
                $jacocoInit[253] = true;
            } else {
                str = map.get("offset").toString();
                $jacocoInit[254] = true;
            }
            $jacocoInit[255] = true;
            z = WXUtils.getBoolean(map.get(Constants.Name.ANIMATED), true).booleanValue();
            if (str == null) {
                $jacocoInit[256] = true;
            } else {
                try {
                    $jacocoInit[257] = true;
                    float realPxByWidth = WXViewUtils.getRealPxByWidth(Float.parseFloat(str), getInstance().getInstanceViewPortWidth());
                    $jacocoInit[258] = true;
                    f = realPxByWidth;
                } catch (Exception e) {
                    $jacocoInit[259] = true;
                    WXLogUtils.e("Float parseFloat error :" + e.getMessage());
                    $jacocoInit[260] = true;
                }
            }
        }
        if (!this.pageEnable) {
            $jacocoInit[261] = true;
        } else {
            $jacocoInit[262] = true;
            this.mActiveFeature = this.mChildren.indexOf(wXComponent);
            $jacocoInit[263] = true;
        }
        int absoluteY = wXComponent.getAbsoluteY() - getAbsoluteY();
        $jacocoInit[264] = true;
        if (isLayoutRTL()) {
            $jacocoInit[265] = true;
            if (getInnerView().getChildCount() > 0) {
                $jacocoInit[266] = true;
                int width = getInnerView().getChildAt(0).getWidth();
                $jacocoInit[267] = true;
                int measuredWidth = getInnerView().getMeasuredWidth();
                $jacocoInit[268] = true;
                i = (width - (wXComponent.getAbsoluteX() - getAbsoluteX())) - measuredWidth;
                $jacocoInit[269] = true;
            } else {
                i = wXComponent.getAbsoluteX() - getAbsoluteX();
                $jacocoInit[270] = true;
            }
            f = -f;
            $jacocoInit[271] = true;
        } else {
            i = wXComponent.getAbsoluteX() - getAbsoluteX();
            $jacocoInit[272] = true;
        }
        int i2 = (int) f;
        scrollBy((i - getScrollX()) + i2, (absoluteY - getScrollY()) + i2, z);
        $jacocoInit[273] = true;
    }

    public void scrollBy(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        scrollBy(i, i2, false);
        $jacocoInit[274] = true;
    }

    public void scrollBy(final int i, final int i2, final boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getInnerView() == null) {
            $jacocoInit[275] = true;
            return;
        }
        getInnerView().postDelayed(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXScroller this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8423028313357734036L, "com/taobao/weex/ui/component/WXScroller$12", 8);
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
                if (this.this$0.mOrientation == 1) {
                    if (z) {
                        $jacocoInit[1] = true;
                        ((WXScrollView) this.this$0.getInnerView()).smoothScrollBy(0, i2);
                        $jacocoInit[2] = true;
                    } else {
                        ((WXScrollView) this.this$0.getInnerView()).scrollBy(0, i2);
                        $jacocoInit[3] = true;
                    }
                } else if (z) {
                    $jacocoInit[4] = true;
                    ((WXHorizontalScrollView) this.this$0.getInnerView()).smoothScrollBy(i, 0);
                    $jacocoInit[5] = true;
                } else {
                    ((WXHorizontalScrollView) this.this$0.getInnerView()).scrollBy(i, 0);
                    $jacocoInit[6] = true;
                }
                this.this$0.getInnerView().invalidate();
                $jacocoInit[7] = true;
            }
        }, 16);
        $jacocoInit[276] = true;
    }

    public void onScrollChanged(WXScrollView wXScrollView, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        procAppear(i, i2, i3, i4);
        $jacocoInit[277] = true;
    }

    public void notifyAppearStateChange(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (containsEvent(Constants.Event.APPEAR)) {
            $jacocoInit[278] = true;
        } else if (!containsEvent(Constants.Event.DISAPPEAR)) {
            $jacocoInit[279] = true;
            $jacocoInit[284] = true;
        } else {
            $jacocoInit[280] = true;
        }
        HashMap hashMap = new HashMap();
        $jacocoInit[281] = true;
        hashMap.put("direction", str2);
        $jacocoInit[282] = true;
        fireEvent(str, hashMap);
        $jacocoInit[283] = true;
        $jacocoInit[284] = true;
    }

    private void procAppear(int i, int i2, int i3, int i4) {
        String str;
        String str2;
        String str3;
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mIsHostAttachedToWindow) {
            $jacocoInit[285] = true;
            return;
        }
        int i5 = i2 - i4;
        int i6 = i - i3;
        if (i5 > 0) {
            str = "up";
            $jacocoInit[286] = true;
        } else if (i5 < 0) {
            str = "down";
            $jacocoInit[287] = true;
        } else {
            str = null;
            $jacocoInit[288] = true;
        }
        if (this.mOrientation != 0) {
            $jacocoInit[289] = true;
        } else if (i6 == 0) {
            $jacocoInit[290] = true;
        } else {
            if (i6 > 0) {
                str3 = "right";
                $jacocoInit[291] = true;
            } else {
                str3 = "left";
                $jacocoInit[292] = true;
            }
            str = str3;
            $jacocoInit[293] = true;
        }
        $jacocoInit[294] = true;
        for (Map.Entry<String, AppearanceHelper> value : this.mAppearanceComponents.entrySet()) {
            $jacocoInit[295] = true;
            AppearanceHelper appearanceHelper = (AppearanceHelper) value.getValue();
            $jacocoInit[296] = true;
            if (!appearanceHelper.isWatch()) {
                $jacocoInit[297] = true;
            } else {
                boolean checkItemVisibleInScroller = checkItemVisibleInScroller(appearanceHelper.getAwareChild());
                $jacocoInit[298] = true;
                int appearStatus = appearanceHelper.setAppearStatus(checkItemVisibleInScroller);
                if (appearStatus == 0) {
                    $jacocoInit[299] = true;
                } else {
                    $jacocoInit[300] = true;
                    WXComponent awareChild = appearanceHelper.getAwareChild();
                    if (appearStatus == 1) {
                        str2 = Constants.Event.APPEAR;
                        $jacocoInit[301] = true;
                    } else {
                        str2 = Constants.Event.DISAPPEAR;
                        $jacocoInit[302] = true;
                    }
                    awareChild.notifyAppearStateChange(str2, str);
                    $jacocoInit[303] = true;
                }
                $jacocoInit[304] = true;
            }
        }
        $jacocoInit[305] = true;
    }

    private boolean checkItemVisibleInScroller(WXComponent wXComponent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[306] = true;
        boolean z2 = false;
        while (true) {
            if (wXComponent == null) {
                $jacocoInit[307] = true;
                break;
            } else if (wXComponent instanceof WXScroller) {
                $jacocoInit[308] = true;
                break;
            } else {
                $jacocoInit[309] = true;
                if (!(wXComponent.getParent() instanceof WXScroller)) {
                    $jacocoInit[310] = true;
                } else if (this.mOrientation == 0) {
                    $jacocoInit[311] = true;
                    $jacocoInit[312] = true;
                    float left = (float) (((int) wXComponent.getLayoutPosition().getLeft()) - getScrollX());
                    if (left <= 0.0f - wXComponent.getLayoutWidth()) {
                        $jacocoInit[313] = true;
                    } else if (left >= getLayoutWidth()) {
                        $jacocoInit[314] = true;
                    } else {
                        $jacocoInit[315] = true;
                        z2 = true;
                        $jacocoInit[317] = true;
                    }
                    $jacocoInit[316] = true;
                    z2 = false;
                    $jacocoInit[317] = true;
                } else {
                    $jacocoInit[318] = true;
                    float top = (float) (((int) wXComponent.getLayoutPosition().getTop()) - getScrollY());
                    if (top <= 0.0f - wXComponent.getLayoutHeight()) {
                        $jacocoInit[319] = true;
                    } else if (top >= getLayoutHeight()) {
                        $jacocoInit[320] = true;
                    } else {
                        $jacocoInit[321] = true;
                        z = true;
                        $jacocoInit[323] = true;
                    }
                    $jacocoInit[322] = true;
                    z = false;
                    $jacocoInit[323] = true;
                }
                wXComponent = wXComponent.getParent();
                $jacocoInit[324] = true;
            }
        }
        $jacocoInit[325] = true;
        return z2;
    }

    private void dispatchDisappearEvent() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[326] = true;
        for (Map.Entry<String, AppearanceHelper> value : this.mAppearanceComponents.entrySet()) {
            $jacocoInit[327] = true;
            AppearanceHelper appearanceHelper = (AppearanceHelper) value.getValue();
            $jacocoInit[328] = true;
            if (!appearanceHelper.isWatch()) {
                $jacocoInit[329] = true;
            } else {
                int appearStatus = appearanceHelper.setAppearStatus(false);
                if (appearStatus == 0) {
                    $jacocoInit[330] = true;
                } else {
                    $jacocoInit[331] = true;
                    WXComponent awareChild = appearanceHelper.getAwareChild();
                    if (appearStatus == 1) {
                        str = Constants.Event.APPEAR;
                        $jacocoInit[332] = true;
                    } else {
                        str = Constants.Event.DISAPPEAR;
                        $jacocoInit[333] = true;
                    }
                    awareChild.notifyAppearStateChange(str, "");
                    $jacocoInit[334] = true;
                }
                $jacocoInit[335] = true;
            }
        }
        $jacocoInit[336] = true;
    }

    public void onScrollToBottom(WXScrollView wXScrollView, int i, int i2) {
        $jacocoInit()[337] = true;
    }

    public void onScrollStopped(WXScrollView wXScrollView, int i, int i2) {
        $jacocoInit()[338] = true;
    }

    public void onScroll(WXScrollView wXScrollView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        onLoadMore(wXScrollView, i, i2);
        $jacocoInit[339] = true;
    }

    /* access modifiers changed from: protected */
    public void onLoadMore(WXScrollView wXScrollView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String loadMoreOffset = getAttrs().getLoadMoreOffset();
            $jacocoInit[340] = true;
            if (!TextUtils.isEmpty(loadMoreOffset)) {
                $jacocoInit[341] = true;
                int realPxByWidth = (int) WXViewUtils.getRealPxByWidth(Float.parseFloat(loadMoreOffset), getInstance().getInstanceViewPortWidth());
                $jacocoInit[343] = true;
                int height = wXScrollView.getChildAt(0).getHeight();
                $jacocoInit[344] = true;
                int height2 = (height - i2) - wXScrollView.getHeight();
                if (height2 >= realPxByWidth) {
                    $jacocoInit[345] = true;
                } else {
                    $jacocoInit[346] = true;
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[347] = true;
                    } else {
                        $jacocoInit[348] = true;
                        WXLogUtils.d("[WXScroller-onScroll] offScreenY :" + height2);
                        $jacocoInit[349] = true;
                    }
                    if (this.mContentHeight != height) {
                        $jacocoInit[350] = true;
                    } else if (!this.mForceLoadmoreNextTime) {
                        $jacocoInit[351] = true;
                    } else {
                        $jacocoInit[352] = true;
                    }
                    fireEvent(Constants.Event.LOADMORE);
                    this.mContentHeight = height;
                    this.mForceLoadmoreNextTime = false;
                    $jacocoInit[353] = true;
                }
                $jacocoInit[354] = true;
                $jacocoInit[357] = true;
                return;
            }
            $jacocoInit[342] = true;
        } catch (Exception e) {
            $jacocoInit[355] = true;
            WXLogUtils.d("[WXScroller-onScroll] ", (Throwable) e);
            $jacocoInit[356] = true;
        }
    }

    @JSMethod
    public void resetLoadmore() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mForceLoadmoreNextTime = true;
        $jacocoInit[358] = true;
    }

    public ScrollStartEndHelper getScrollStartEndHelper() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollStartEndHelper != null) {
            $jacocoInit[359] = true;
        } else {
            $jacocoInit[360] = true;
            this.mScrollStartEndHelper = new ScrollStartEndHelper(this);
            $jacocoInit[361] = true;
        }
        ScrollStartEndHelper scrollStartEndHelper = this.mScrollStartEndHelper;
        $jacocoInit[362] = true;
        return scrollStartEndHelper;
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private final WXHorizontalScrollView scrollView;
        final /* synthetic */ WXScroller this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(1922995344208289640L, "com/taobao/weex/ui/component/WXScroller$MyGestureDetector", 23);
            $jacocoData = a2;
            return a2;
        }

        public WXHorizontalScrollView getScrollView() {
            boolean[] $jacocoInit = $jacocoInit();
            WXHorizontalScrollView wXHorizontalScrollView = this.scrollView;
            $jacocoInit[0] = true;
            return wXHorizontalScrollView;
        }

        MyGestureDetector(WXScroller wXScroller, WXHorizontalScrollView wXHorizontalScrollView) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = wXScroller;
            this.scrollView = wXHorizontalScrollView;
            $jacocoInit[1] = true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            int i;
            boolean[] $jacocoInit = $jacocoInit();
            int size = this.this$0.mChildren.size();
            try {
                $jacocoInit[2] = true;
                if (motionEvent.getX() - motionEvent2.getX() <= 5.0f) {
                    $jacocoInit[3] = true;
                } else if (Math.abs(f) <= 300.0f) {
                    $jacocoInit[4] = true;
                } else {
                    $jacocoInit[5] = true;
                    int access$300 = WXScroller.access$300(this.this$0);
                    $jacocoInit[6] = true;
                    WXScroller wXScroller = this.this$0;
                    int i2 = size - 1;
                    if (WXScroller.access$500(this.this$0) < i2) {
                        i2 = WXScroller.access$500(this.this$0) + 1;
                        $jacocoInit[7] = true;
                    } else {
                        $jacocoInit[8] = true;
                    }
                    WXScroller.access$502(wXScroller, i2);
                    $jacocoInit[9] = true;
                    this.scrollView.smoothScrollTo(WXScroller.access$500(this.this$0) * access$300, 0);
                    $jacocoInit[10] = true;
                    return true;
                }
                if (motionEvent2.getX() - motionEvent.getX() <= 5.0f) {
                    $jacocoInit[11] = true;
                } else if (Math.abs(f) <= 300.0f) {
                    $jacocoInit[12] = true;
                } else {
                    $jacocoInit[13] = true;
                    int access$3002 = WXScroller.access$300(this.this$0);
                    $jacocoInit[14] = true;
                    WXScroller wXScroller2 = this.this$0;
                    if (WXScroller.access$500(this.this$0) > 0) {
                        i = WXScroller.access$500(this.this$0) - 1;
                        $jacocoInit[15] = true;
                    } else {
                        $jacocoInit[16] = true;
                        i = 0;
                    }
                    WXScroller.access$502(wXScroller2, i);
                    $jacocoInit[17] = true;
                    this.scrollView.smoothScrollTo(WXScroller.access$500(this.this$0) * access$3002, 0);
                    $jacocoInit[18] = true;
                    return true;
                }
                $jacocoInit[19] = true;
            } catch (Exception e) {
                $jacocoInit[20] = true;
                WXLogUtils.e("There was an error processing the Fling event:" + e.getMessage());
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
            return false;
        }
    }
}
