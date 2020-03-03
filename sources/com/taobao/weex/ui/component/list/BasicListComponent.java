package com.taobao.weex.ui.component.list;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.ICheckBindingScroller;
import com.taobao.weex.common.OnWXScrollListener;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.AppearanceHelper;
import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXBaseRefresh;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXHeader;
import com.taobao.weex.ui.component.WXLoading;
import com.taobao.weex.ui.component.WXRefresh;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.helper.ScrollStartEndHelper;
import com.taobao.weex.ui.component.helper.WXStickyHelper;
import com.taobao.weex.ui.component.list.ListComponentView;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.listview.adapter.IOnLoadMoreListener;
import com.taobao.weex.ui.view.listview.adapter.IRecyclerAdapterListener;
import com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder;
import com.taobao.weex.ui.view.listview.adapter.RecyclerViewBaseAdapter;
import com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.xiaomi.stat.d;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class BasicListComponent<T extends ViewGroup & ListComponentView> extends WXVContainer<T> implements Scrollable, IOnLoadMoreListener, IRecyclerAdapterListener<ListBaseViewHolder> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final boolean DEFAULT_EXCLUDED = false;
    private static final String DEFAULT_TRIGGER_TYPE = "longpress";
    private static final String DRAG_ANCHOR = "dragAnchor";
    private static final String DRAG_TRIGGER_TYPE = "dragTriggerType";
    private static final String EXCLUDED = "dragExcluded";
    public static final String LOADMOREOFFSET = "loadmoreoffset";
    private static final int MAX_VIEWTYPE_ALLOW_CACHE = 9;
    public static final String TRANSFORM = "transform";
    private static boolean mAllowCacheViewHolder = true;
    private static boolean mDownForBidCacheViewHolder = false;
    private static final Pattern transformPattern = Pattern.compile("([a-z]+)\\(([0-9\\.]+),?([0-9\\.]+)?\\)");
    private String TAG = "BasicListComponent";
    private boolean isScrollable;
    private WXComponent keepPositionCell;
    private Runnable keepPositionCellRunnable;
    private long keepPositionLayoutDelay;
    private Runnable mAppearChangeRunnable;
    private long mAppearChangeRunnableDelay;
    private Map<String, AppearanceHelper> mAppearComponents;
    protected int mColumnCount;
    protected float mColumnGap;
    protected float mColumnWidth;
    private DragHelper mDragHelper;
    private boolean mForceLoadmoreNextTime = false;
    private boolean mHasAddScrollEvent;
    private RecyclerView.ItemAnimator mItemAnimator;
    private Point mLastReport;
    protected int mLayoutType;
    protected float mLeftGap;
    private int mListCellCount = 0;
    private int mOffsetAccuracy;
    private ArrayMap<String, Long> mRefToViewType;
    protected float mRightGap;
    private ScrollStartEndHelper mScrollStartEndHelper;
    private Map<String, Map<String, WXComponent>> mStickyMap;
    private String mTriggerType;
    private WXRecyclerViewOnScrollListener mViewOnScrollListener;
    private SparseArray<ArrayList<WXComponent>> mViewTypes;
    private WXStickyHelper stickyHelper;

    interface DragTriggerType {
        public static final String LONG_PRESS = "longpress";
        public static final String PAN = "pan";
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4154680689403087278L, "com/taobao/weex/ui/component/list/BasicListComponent", 705);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: package-private */
    public abstract T generateListView(Context context, int i);

    static /* synthetic */ WXRecyclerViewOnScrollListener access$000(BasicListComponent basicListComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerViewOnScrollListener wXRecyclerViewOnScrollListener = basicListComponent.mViewOnScrollListener;
        $jacocoInit[696] = true;
        return wXRecyclerViewOnScrollListener;
    }

    static /* synthetic */ Runnable access$100(BasicListComponent basicListComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        Runnable runnable = basicListComponent.mAppearChangeRunnable;
        $jacocoInit[697] = true;
        return runnable;
    }

    static /* synthetic */ WXComponent access$200(BasicListComponent basicListComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = basicListComponent.keepPositionCell;
        $jacocoInit[698] = true;
        return wXComponent;
    }

    static /* synthetic */ WXComponent access$202(BasicListComponent basicListComponent, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        basicListComponent.keepPositionCell = wXComponent;
        $jacocoInit[699] = true;
        return wXComponent;
    }

    static /* synthetic */ Runnable access$302(BasicListComponent basicListComponent, Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        basicListComponent.keepPositionCellRunnable = runnable;
        $jacocoInit[700] = true;
        return runnable;
    }

    static /* synthetic */ DragHelper access$400(BasicListComponent basicListComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        DragHelper dragHelper = basicListComponent.mDragHelper;
        $jacocoInit[701] = true;
        return dragHelper;
    }

    static /* synthetic */ boolean access$500(BasicListComponent basicListComponent, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean shouldReport = basicListComponent.shouldReport(i, i2);
        $jacocoInit[702] = true;
        return shouldReport;
    }

    static /* synthetic */ void access$600(BasicListComponent basicListComponent, RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        basicListComponent.fireScrollEvent(recyclerView, i, i2);
        $jacocoInit[703] = true;
    }

    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        onBindViewHolder((ListBaseViewHolder) viewHolder, i);
        $jacocoInit[694] = true;
    }

    public /* synthetic */ boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onFailedToRecycleView = onFailedToRecycleView((ListBaseViewHolder) viewHolder);
        $jacocoInit[692] = true;
        return onFailedToRecycleView;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onHostViewInitialized(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        onHostViewInitialized((ViewGroup) view);
        $jacocoInit[690] = true;
    }

    public /* synthetic */ void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        onViewRecycled((ListBaseViewHolder) viewHolder);
        $jacocoInit[695] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[704] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicListComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mAppearComponents = new HashMap();
        this.mAppearChangeRunnable = null;
        this.mAppearChangeRunnableDelay = 50;
        this.isScrollable = true;
        $jacocoInit[1] = true;
        this.mViewOnScrollListener = new WXRecyclerViewOnScrollListener(this);
        this.mLayoutType = 1;
        this.mColumnCount = 1;
        this.mColumnGap = 0.0f;
        this.mColumnWidth = 0.0f;
        this.mLeftGap = 0.0f;
        this.mRightGap = 0.0f;
        this.mOffsetAccuracy = 10;
        $jacocoInit[2] = true;
        this.mLastReport = new Point(-1, -1);
        this.mHasAddScrollEvent = false;
        $jacocoInit[3] = true;
        this.mStickyMap = new HashMap();
        this.keepPositionCell = null;
        this.keepPositionCellRunnable = null;
        this.keepPositionLayoutDelay = 150;
        $jacocoInit[4] = true;
        this.stickyHelper = new WXStickyHelper(this);
        $jacocoInit[5] = true;
    }

    public void setMarginsSupportRTL(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 17) {
            $jacocoInit[6] = true;
            marginLayoutParams.setMargins(i, i2, i3, i4);
            $jacocoInit[7] = true;
            marginLayoutParams.setMarginStart(i);
            $jacocoInit[8] = true;
            marginLayoutParams.setMarginEnd(i3);
            $jacocoInit[9] = true;
        } else if (marginLayoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) marginLayoutParams;
            $jacocoInit[10] = true;
            if (isLayoutRTL()) {
                layoutParams.gravity = 53;
                $jacocoInit[11] = true;
                marginLayoutParams.setMargins(i3, i2, i, i4);
                $jacocoInit[12] = true;
            } else {
                layoutParams.gravity = 51;
                $jacocoInit[13] = true;
                marginLayoutParams.setMargins(i, i2, i3, i4);
                $jacocoInit[14] = true;
            }
            $jacocoInit[15] = true;
        } else {
            marginLayoutParams.setMargins(i, i2, i3, i4);
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
    }

    public void setLayout(WXComponent wXComponent) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getHostView() == null) {
            $jacocoInit[18] = true;
        } else {
            $jacocoInit[19] = true;
            if (wXComponent.isLayoutRTL()) {
                $jacocoInit[20] = true;
                i = 1;
            } else {
                i = 0;
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
            ViewCompat.setLayoutDirection(wXComponent.getHostView(), i);
            $jacocoInit[23] = true;
        }
        super.setLayout(wXComponent);
        $jacocoInit[24] = true;
    }

    /* access modifiers changed from: protected */
    public void onHostViewInitialized(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onHostViewInitialized(t);
        $jacocoInit[25] = true;
        WXRecyclerView innerView = ((ListComponentView) t).getInnerView();
        $jacocoInit[26] = true;
        if (innerView == null) {
            $jacocoInit[27] = true;
        } else if (innerView.getAdapter() == null) {
            $jacocoInit[28] = true;
        } else {
            if (!WXUtils.getBoolean(getAttrs().get("prefetchGapDisable"), false).booleanValue()) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                if (innerView.getLayoutManager() == null) {
                    $jacocoInit[32] = true;
                } else {
                    $jacocoInit[33] = true;
                    innerView.getLayoutManager().setItemPrefetchEnabled(false);
                    $jacocoInit[34] = true;
                }
            }
            if (this.mChildren == null) {
                $jacocoInit[35] = true;
                WXLogUtils.e(this.TAG, "children is null");
                $jacocoInit[36] = true;
                return;
            }
            this.mDragHelper = new DefaultDragHelper(this.mChildren, innerView, new EventTrigger(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ BasicListComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-4757725572047250899L, "com/taobao/weex/ui/component/list/BasicListComponent$1", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void triggerEvent(String str, Map<String, Object> map) {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0.fireEvent(str, map);
                    $jacocoInit[1] = true;
                }
            });
            $jacocoInit[37] = true;
            this.mTriggerType = getTriggerType(this);
            $jacocoInit[38] = true;
            return;
        }
        WXLogUtils.e(this.TAG, "RecyclerView is not found or Adapter is not bound");
        $jacocoInit[29] = true;
    }

    /* access modifiers changed from: protected */
    public WXComponent.MeasureOutput measure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        int screenHeight = WXViewUtils.getScreenHeight(WXEnvironment.sApplication);
        $jacocoInit[39] = true;
        int weexHeight = WXViewUtils.getWeexHeight(getInstanceId());
        $jacocoInit[40] = true;
        if (weexHeight >= screenHeight) {
            $jacocoInit[41] = true;
        } else {
            $jacocoInit[42] = true;
            screenHeight = weexHeight;
        }
        if (i2 > screenHeight) {
            i2 = weexHeight - getAbsoluteY();
            $jacocoInit[43] = true;
        } else {
            $jacocoInit[44] = true;
        }
        $jacocoInit[45] = true;
        WXComponent.MeasureOutput measure = super.measure(i, i2);
        $jacocoInit[46] = true;
        return measure;
    }

    public int getOrientation() {
        $jacocoInit()[47] = true;
        return 1;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAppearChangeRunnable == null) {
            $jacocoInit[48] = true;
        } else if (getHostView() == null) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
            ((ViewGroup) getHostView()).removeCallbacks(this.mAppearChangeRunnable);
            this.mAppearChangeRunnable = null;
            $jacocoInit[51] = true;
        }
        super.destroy();
        if (this.mStickyMap == null) {
            $jacocoInit[52] = true;
        } else {
            $jacocoInit[53] = true;
            this.mStickyMap.clear();
            $jacocoInit[54] = true;
        }
        if (this.mViewTypes == null) {
            $jacocoInit[55] = true;
        } else {
            $jacocoInit[56] = true;
            this.mViewTypes.clear();
            $jacocoInit[57] = true;
        }
        if (this.mRefToViewType == null) {
            $jacocoInit[58] = true;
        } else {
            $jacocoInit[59] = true;
            this.mRefToViewType.clear();
            $jacocoInit[60] = true;
        }
        $jacocoInit[61] = true;
    }

    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent wXComponent, View view, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (!(wXComponent instanceof WXBaseRefresh)) {
            $jacocoInit[62] = true;
        } else if (marginLayoutParams != null) {
            $jacocoInit[63] = true;
        } else {
            $jacocoInit[64] = true;
            marginLayoutParams = new LinearLayout.LayoutParams(i, i2);
            $jacocoInit[65] = true;
            $jacocoInit[70] = true;
            return marginLayoutParams;
        }
        if (marginLayoutParams == null) {
            $jacocoInit[66] = true;
            marginLayoutParams = new RecyclerView.LayoutParams(i, i2);
            $jacocoInit[67] = true;
        } else {
            marginLayoutParams.width = i;
            marginLayoutParams.height = i2;
            $jacocoInit[68] = true;
            setMarginsSupportRTL(marginLayoutParams, i3, 0, i4, 0);
            $jacocoInit[69] = true;
        }
        $jacocoInit[70] = true;
        return marginLayoutParams;
    }

    /* access modifiers changed from: protected */
    public T initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        T generateListView = generateListView(context, getOrientation());
        $jacocoInit[71] = true;
        String attrByKey = getAttrByKey("transform");
        if (attrByKey == null) {
            $jacocoInit[72] = true;
        } else {
            $jacocoInit[73] = true;
            ((ListComponentView) generateListView).getInnerView().addItemDecoration(RecyclerTransform.parseTransforms(getOrientation(), attrByKey));
            $jacocoInit[74] = true;
        }
        if (getAttrs().get(Constants.Name.KEEP_POSITION_LAYOUT_DELAY) == null) {
            $jacocoInit[75] = true;
        } else {
            $jacocoInit[76] = true;
            this.keepPositionLayoutDelay = (long) WXUtils.getNumberInt(getAttrs().get(Constants.Name.KEEP_POSITION_LAYOUT_DELAY), (int) this.keepPositionLayoutDelay);
            $jacocoInit[77] = true;
        }
        if (getAttrs().get("appearActionDelay") == null) {
            $jacocoInit[78] = true;
        } else {
            $jacocoInit[79] = true;
            this.mAppearChangeRunnableDelay = (long) WXUtils.getNumberInt(getAttrs().get("appearActionDelay"), (int) this.mAppearChangeRunnableDelay);
            $jacocoInit[80] = true;
        }
        ListComponentView listComponentView = (ListComponentView) generateListView;
        this.mItemAnimator = listComponentView.getInnerView().getItemAnimator();
        $jacocoInit[81] = true;
        RecyclerViewBaseAdapter recyclerViewBaseAdapter = new RecyclerViewBaseAdapter(this);
        $jacocoInit[82] = true;
        recyclerViewBaseAdapter.setHasStableIds(true);
        $jacocoInit[83] = true;
        listComponentView.setRecyclerViewBaseAdapter(recyclerViewBaseAdapter);
        $jacocoInit[84] = true;
        generateListView.setOverScrollMode(2);
        $jacocoInit[85] = true;
        listComponentView.getInnerView().addOnScrollListener(this.mViewOnScrollListener);
        $jacocoInit[86] = true;
        if (getAttrs().get(Constants.Name.HAS_FIXED_SIZE) == null) {
            $jacocoInit[87] = true;
        } else {
            $jacocoInit[88] = true;
            boolean booleanValue = WXUtils.getBoolean(getAttrs().get(Constants.Name.HAS_FIXED_SIZE), false).booleanValue();
            $jacocoInit[89] = true;
            listComponentView.getInnerView().setHasFixedSize(booleanValue);
            $jacocoInit[90] = true;
        }
        listComponentView.getInnerView().addOnScrollListener(new RecyclerView.OnScrollListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ BasicListComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8659714092161690214L, "com/taobao/weex/ui/component/list/BasicListComponent$2", 34);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onScrollStateChanged(recyclerView, i);
                $jacocoInit[1] = true;
                List<OnWXScrollListener> wXScrollListeners = this.this$0.getInstance().getWXScrollListeners();
                $jacocoInit[2] = true;
                if (wXScrollListeners == null) {
                    $jacocoInit[3] = true;
                } else {
                    int size = wXScrollListeners.size();
                    if (size <= 0) {
                        $jacocoInit[4] = true;
                    } else {
                        $jacocoInit[5] = true;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= size) {
                                $jacocoInit[6] = true;
                                break;
                            }
                            $jacocoInit[7] = true;
                            if (i2 >= wXScrollListeners.size()) {
                                $jacocoInit[8] = true;
                                break;
                            }
                            OnWXScrollListener onWXScrollListener = wXScrollListeners.get(i2);
                            if (onWXScrollListener == null) {
                                $jacocoInit[9] = true;
                            } else {
                                $jacocoInit[10] = true;
                                View childAt = recyclerView.getChildAt(0);
                                if (childAt == null) {
                                    $jacocoInit[11] = true;
                                } else {
                                    $jacocoInit[12] = true;
                                    int top = childAt.getTop();
                                    $jacocoInit[13] = true;
                                    onWXScrollListener.onScrollStateChanged(recyclerView, 0, top, i);
                                    $jacocoInit[14] = true;
                                }
                            }
                            i2++;
                            $jacocoInit[15] = true;
                        }
                    }
                }
                $jacocoInit[16] = true;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onScrolled(recyclerView, i, i2);
                $jacocoInit[17] = true;
                List<OnWXScrollListener> wXScrollListeners = this.this$0.getInstance().getWXScrollListeners();
                $jacocoInit[18] = true;
                if (wXScrollListeners == null) {
                    $jacocoInit[19] = true;
                } else if (wXScrollListeners.size() <= 0) {
                    $jacocoInit[20] = true;
                } else {
                    try {
                        $jacocoInit[21] = true;
                        $jacocoInit[22] = true;
                        for (OnWXScrollListener next : wXScrollListeners) {
                            if (next == null) {
                                $jacocoInit[23] = true;
                            } else if (next instanceof ICheckBindingScroller) {
                                $jacocoInit[24] = true;
                                if (!((ICheckBindingScroller) next).isNeedScroller(this.this$0.getRef(), (Object) null)) {
                                    $jacocoInit[25] = true;
                                } else {
                                    $jacocoInit[26] = true;
                                    next.onScrolled(recyclerView, i, i2);
                                    $jacocoInit[27] = true;
                                }
                            } else {
                                next.onScrolled(recyclerView, i, i2);
                                $jacocoInit[28] = true;
                            }
                            $jacocoInit[29] = true;
                        }
                        $jacocoInit[30] = true;
                    } catch (Exception e) {
                        $jacocoInit[31] = true;
                        e.printStackTrace();
                        $jacocoInit[32] = true;
                    }
                }
                $jacocoInit[33] = true;
            }
        });
        $jacocoInit[91] = true;
        generateListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ BasicListComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2565960054254432508L, "com/taobao/weex/ui/component/list/BasicListComponent$3", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            @TargetApi(16)
            public void onGlobalLayout() {
                boolean[] $jacocoInit = $jacocoInit();
                ViewGroup viewGroup = (ViewGroup) this.this$0.getHostView();
                if (viewGroup == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                BasicListComponent.access$000(this.this$0).onScrolled(((ListComponentView) viewGroup).getInnerView(), 0, 0);
                if (Build.VERSION.SDK_INT >= 16) {
                    $jacocoInit[2] = true;
                    viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    $jacocoInit[3] = true;
                } else {
                    viewGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[92] = true;
        return generateListView;
    }

    public void bindStickStyle(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.stickyHelper.bindStickStyle(wXComponent, this.mStickyMap);
        $jacocoInit[93] = true;
    }

    public void unbindStickStyle(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.stickyHelper.unbindStickStyle(wXComponent, this.mStickyMap);
        $jacocoInit[94] = true;
        WXHeader wXHeader = (WXHeader) findTypeParent(wXComponent, WXHeader.class);
        $jacocoInit[95] = true;
        if (wXHeader == null) {
            $jacocoInit[96] = true;
        } else if (getHostView() == null) {
            $jacocoInit[97] = true;
        } else {
            $jacocoInit[98] = true;
            ((ListComponentView) ((ViewGroup) getHostView())).notifyStickyRemove(wXHeader);
            $jacocoInit[99] = true;
        }
        $jacocoInit[100] = true;
    }

    @Nullable
    private WXComponent findDirectListChild(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[101] = true;
        } else {
            WXVContainer parent = wXComponent.getParent();
            if (parent == null) {
                $jacocoInit[102] = true;
            } else if (parent instanceof BasicListComponent) {
                $jacocoInit[104] = true;
                return wXComponent;
            } else {
                WXComponent findDirectListChild = findDirectListChild(parent);
                $jacocoInit[105] = true;
                return findDirectListChild;
            }
        }
        $jacocoInit[103] = true;
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r6.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case -304480883: goto L_0x005e;
                case -223520855: goto L_0x004b;
                case -112563826: goto L_0x0038;
                case -5620052: goto L_0x0025;
                case 66669991: goto L_0x0012;
                default: goto L_0x000d;
            }
        L_0x000d:
            r1 = 106(0x6a, float:1.49E-43)
            r0[r1] = r3
            goto L_0x0071
        L_0x0012:
            java.lang.String r1 = "scrollable"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x001f
            r1 = 109(0x6d, float:1.53E-43)
            r0[r1] = r3
            goto L_0x0071
        L_0x001f:
            r1 = 110(0x6e, float:1.54E-43)
            r0[r1] = r3
            r1 = 1
            goto L_0x0072
        L_0x0025:
            java.lang.String r1 = "offsetAccuracy"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0032
            r1 = 111(0x6f, float:1.56E-43)
            r0[r1] = r3
            goto L_0x0071
        L_0x0032:
            r1 = 2
            r4 = 112(0x70, float:1.57E-43)
            r0[r4] = r3
            goto L_0x0072
        L_0x0038:
            java.lang.String r1 = "loadmoreoffset"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0045
            r1 = 107(0x6b, float:1.5E-43)
            r0[r1] = r3
            goto L_0x0071
        L_0x0045:
            r1 = 108(0x6c, float:1.51E-43)
            r0[r1] = r3
            r1 = 0
            goto L_0x0072
        L_0x004b:
            java.lang.String r1 = "showScrollbar"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0058
            r1 = 115(0x73, float:1.61E-43)
            r0[r1] = r3
            goto L_0x0071
        L_0x0058:
            r1 = 4
            r4 = 116(0x74, float:1.63E-43)
            r0[r4] = r3
            goto L_0x0072
        L_0x005e:
            java.lang.String r1 = "draggable"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x006b
            r1 = 113(0x71, float:1.58E-43)
            r0[r1] = r3
            goto L_0x0071
        L_0x006b:
            r1 = 3
            r4 = 114(0x72, float:1.6E-43)
            r0[r4] = r3
            goto L_0x0072
        L_0x0071:
            r1 = -1
        L_0x0072:
            switch(r1) {
                case 0: goto L_0x00e8;
                case 1: goto L_0x00d0;
                case 2: goto L_0x00b6;
                case 3: goto L_0x009e;
                case 4: goto L_0x007e;
                default: goto L_0x0075;
            }
        L_0x0075:
            boolean r6 = super.setProperty(r6, r7)
            r7 = 128(0x80, float:1.794E-43)
            r0[r7] = r3
            return r6
        L_0x007e:
            r6 = 0
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            if (r6 != 0) goto L_0x008a
            r6 = 124(0x7c, float:1.74E-43)
            r0[r6] = r3
            goto L_0x0099
        L_0x008a:
            r7 = 125(0x7d, float:1.75E-43)
            r0[r7] = r3
            boolean r6 = r6.booleanValue()
            r5.setShowScrollbar(r6)
            r6 = 126(0x7e, float:1.77E-43)
            r0[r6] = r3
        L_0x0099:
            r6 = 127(0x7f, float:1.78E-43)
            r0[r6] = r3
            return r3
        L_0x009e:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r7 = 122(0x7a, float:1.71E-43)
            r0[r7] = r3
            r5.setDraggable(r6)
            r6 = 123(0x7b, float:1.72E-43)
            r0[r6] = r3
            return r3
        L_0x00b6:
            r6 = 10
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r6)
            int r6 = r6.intValue()
            r7 = 120(0x78, float:1.68E-43)
            r0[r7] = r3
            r5.setOffsetAccuracy(r6)
            r6 = 121(0x79, float:1.7E-43)
            r0[r6] = r3
            return r3
        L_0x00d0:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r7 = 118(0x76, float:1.65E-43)
            r0[r7] = r3
            r5.setScrollable(r6)
            r6 = 119(0x77, float:1.67E-43)
            r0[r6] = r3
            return r3
        L_0x00e8:
            r6 = 117(0x75, float:1.64E-43)
            r0[r6] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.BasicListComponent.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "scrollable")
    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isScrollable = z;
        $jacocoInit[129] = true;
        WXRecyclerView innerView = ((ListComponentView) ((ViewGroup) getHostView())).getInnerView();
        if (innerView == null) {
            $jacocoInit[130] = true;
        } else {
            $jacocoInit[131] = true;
            innerView.setScrollable(z);
            $jacocoInit[132] = true;
        }
        $jacocoInit[133] = true;
    }

    @WXComponentProp(name = "offsetAccuracy")
    public void setOffsetAccuracy(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOffsetAccuracy = (int) WXViewUtils.getRealPxByWidth((float) i, getInstance().getInstanceViewPortWidth());
        $jacocoInit[134] = true;
    }

    @WXComponentProp(name = "draggable")
    public void setDraggable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mDragHelper == null) {
            $jacocoInit[135] = true;
        } else {
            $jacocoInit[136] = true;
            this.mDragHelper.setDraggable(z);
            $jacocoInit[137] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[138] = true;
        } else {
            $jacocoInit[139] = true;
            WXLogUtils.d("set draggable : " + z);
            $jacocoInit[140] = true;
        }
        $jacocoInit[141] = true;
    }

    @WXComponentProp(name = "showScrollbar")
    public void setShowScrollbar(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[142] = true;
        } else if (((ListComponentView) ((ViewGroup) getHostView())).getInnerView() == null) {
            $jacocoInit[143] = true;
        } else {
            if (getOrientation() == 1) {
                $jacocoInit[145] = true;
                ((ListComponentView) ((ViewGroup) getHostView())).getInnerView().setVerticalScrollBarEnabled(z);
                $jacocoInit[146] = true;
            } else {
                ((ListComponentView) ((ViewGroup) getHostView())).getInnerView().setHorizontalScrollBarEnabled(z);
                $jacocoInit[147] = true;
            }
            $jacocoInit[148] = true;
            return;
        }
        $jacocoInit[144] = true;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isScrollable;
        $jacocoInit[149] = true;
        return z;
    }

    private void setAppearanceWatch(WXComponent wXComponent, int i, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        AppearanceHelper appearanceHelper = this.mAppearComponents.get(wXComponent.getRef());
        if (appearanceHelper != null) {
            $jacocoInit[150] = true;
            appearanceHelper.setWatchEvent(i, z);
            $jacocoInit[151] = true;
        } else if (!z) {
            $jacocoInit[152] = true;
        } else {
            WXComponent findDirectListChild = findDirectListChild(wXComponent);
            $jacocoInit[153] = true;
            int indexOf = this.mChildren.indexOf(findDirectListChild);
            if (indexOf == -1) {
                $jacocoInit[154] = true;
            } else {
                $jacocoInit[155] = true;
                AppearanceHelper appearanceHelper2 = new AppearanceHelper(wXComponent, indexOf);
                $jacocoInit[156] = true;
                appearanceHelper2.setWatchEvent(i, true);
                $jacocoInit[157] = true;
                this.mAppearComponents.put(wXComponent.getRef(), appearanceHelper2);
                $jacocoInit[158] = true;
            }
        }
        $jacocoInit[159] = true;
    }

    public void bindAppearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 0, true);
        if (this.mAppearChangeRunnable != null) {
            $jacocoInit[160] = true;
        } else {
            $jacocoInit[161] = true;
            this.mAppearChangeRunnable = new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ BasicListComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(5228066024807436306L, "com/taobao/weex/ui/component/list/BasicListComponent$4", 5);
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
                    if (BasicListComponent.access$100(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        this.this$0.notifyAppearStateChange(0, 0, 0, 0);
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                }
            };
            $jacocoInit[162] = true;
        }
        if (getHostView() == null) {
            $jacocoInit[163] = true;
        } else {
            $jacocoInit[164] = true;
            ((ViewGroup) getHostView()).removeCallbacks(this.mAppearChangeRunnable);
            $jacocoInit[165] = true;
            ((ViewGroup) getHostView()).postDelayed(this.mAppearChangeRunnable, this.mAppearChangeRunnableDelay);
            $jacocoInit[166] = true;
        }
        $jacocoInit[167] = true;
    }

    public void bindDisappearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 1, true);
        $jacocoInit[168] = true;
    }

    public void unbindAppearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 0, false);
        $jacocoInit[169] = true;
    }

    public void unbindDisappearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 1, false);
        $jacocoInit[170] = true;
    }

    public void scrollTo(WXComponent wXComponent, Map<String, Object> map) {
        boolean z;
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        float f = 0.0f;
        if (map == null) {
            $jacocoInit[171] = true;
            z = true;
        } else {
            $jacocoInit[172] = true;
            if (map.get("offset") == null) {
                str = "0";
                $jacocoInit[173] = true;
            } else {
                str = map.get("offset").toString();
                $jacocoInit[174] = true;
            }
            $jacocoInit[175] = true;
            z = WXUtils.getBoolean(map.get(Constants.Name.ANIMATED), true).booleanValue();
            if (str == null) {
                $jacocoInit[176] = true;
            } else {
                try {
                    $jacocoInit[177] = true;
                    float realPxByWidth = WXViewUtils.getRealPxByWidth(Float.parseFloat(str), getInstance().getInstanceViewPortWidth());
                    $jacocoInit[178] = true;
                    f = realPxByWidth;
                } catch (Exception e) {
                    $jacocoInit[179] = true;
                    WXLogUtils.e("Float parseFloat error :" + e.getMessage());
                    $jacocoInit[180] = true;
                }
            }
        }
        int i = (int) f;
        $jacocoInit[181] = true;
        ViewGroup viewGroup = (ViewGroup) getHostView();
        if (viewGroup == null) {
            $jacocoInit[182] = true;
            return;
        }
        WXCell wXCell = null;
        $jacocoInit[183] = true;
        while (true) {
            if (wXComponent == null) {
                $jacocoInit[184] = true;
                break;
            } else if (wXComponent instanceof WXCell) {
                wXCell = (WXCell) wXComponent;
                $jacocoInit[185] = true;
                break;
            } else {
                wXComponent = wXComponent.getParent();
                $jacocoInit[186] = true;
            }
        }
        if (wXCell == null) {
            $jacocoInit[187] = true;
        } else {
            $jacocoInit[188] = true;
            int indexOf = this.mChildren.indexOf(wXCell);
            if (indexOf == -1) {
                $jacocoInit[189] = true;
                return;
            }
            WXRecyclerView innerView = ((ListComponentView) viewGroup).getInnerView();
            $jacocoInit[190] = true;
            innerView.scrollTo(z, indexOf, i, getOrientation());
            $jacocoInit[191] = true;
        }
        $jacocoInit[192] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0221  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBeforeScroll(int r12, int r13) {
        /*
            r11 = this;
            boolean[] r12 = $jacocoInit()
            android.view.View r0 = r11.getHostView()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.taobao.weex.ui.component.WXComponent>> r1 = r11.mStickyMap
            r2 = 1
            if (r1 != 0) goto L_0x0014
            r13 = 193(0xc1, float:2.7E-43)
            r12[r13] = r2
            goto L_0x001a
        L_0x0014:
            if (r0 != 0) goto L_0x001f
            r13 = 194(0xc2, float:2.72E-43)
            r12[r13] = r2
        L_0x001a:
            r13 = 195(0xc3, float:2.73E-43)
            r12[r13] = r2
            return
        L_0x001f:
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.taobao.weex.ui.component.WXComponent>> r1 = r11.mStickyMap
            java.lang.String r3 = r11.getRef()
            java.lang.Object r1 = r1.get(r3)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 != 0) goto L_0x0032
            r13 = 196(0xc4, float:2.75E-43)
            r12[r13] = r2
            return
        L_0x0032:
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
            r3 = -1
            r4 = 197(0xc5, float:2.76E-43)
            r12[r4] = r2
        L_0x003f:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0247
            r4 = 198(0xc6, float:2.77E-43)
            r12[r4] = r2
            java.lang.Object r4 = r1.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r5 = 199(0xc7, float:2.79E-43)
            r12[r5] = r2
            java.lang.Object r4 = r4.getValue()
            com.taobao.weex.ui.component.WXComponent r4 = (com.taobao.weex.ui.component.WXComponent) r4
            if (r4 != 0) goto L_0x0060
            r4 = 200(0xc8, float:2.8E-43)
            r12[r4] = r2
            goto L_0x003f
        L_0x0060:
            boolean r5 = r4 instanceof com.taobao.weex.ui.component.list.WXCell
            if (r5 != 0) goto L_0x0069
            r4 = 201(0xc9, float:2.82E-43)
            r12[r4] = r2
            goto L_0x003f
        L_0x0069:
            r5 = r4
            com.taobao.weex.ui.component.list.WXCell r5 = (com.taobao.weex.ui.component.list.WXCell) r5
            r6 = 202(0xca, float:2.83E-43)
            r12[r6] = r2
            android.view.View r6 = r5.getHostView()
            if (r6 != 0) goto L_0x007b
            r13 = 203(0xcb, float:2.84E-43)
            r12[r13] = r2
            return
        L_0x007b:
            r6 = 2
            int[] r7 = new int[r6]
            r8 = 204(0xcc, float:2.86E-43)
            r12[r8] = r2
            android.view.View r8 = r4.getHostView()
            r8.getLocationOnScreen(r7)
            int[] r6 = new int[r6]
            r8 = 205(0xcd, float:2.87E-43)
            r12[r8] = r2
            com.taobao.weex.ui.component.Scrollable r4 = r4.getParentScroller()
            android.view.ViewGroup r4 = r4.getView()
            r4.getLocationOnScreen(r6)
            r4 = r7[r2]
            r6 = r6[r2]
            int r4 = r4 - r6
            r6 = 206(0xce, float:2.89E-43)
            r12[r6] = r2
            android.view.View r6 = r11.getHostView()
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            com.taobao.weex.ui.component.list.ListComponentView r6 = (com.taobao.weex.ui.component.list.ListComponentView) r6
            com.taobao.weex.ui.view.listview.WXRecyclerView r6 = r6.getInnerView()
            android.support.v7.widget.RecyclerView$LayoutManager r6 = r6.getLayoutManager()
            boolean r7 = r6 instanceof android.support.v7.widget.LinearLayoutManager
            r8 = 0
            if (r7 == 0) goto L_0x00bd
            r7 = 207(0xcf, float:2.9E-43)
            r12[r7] = r2
            goto L_0x00c5
        L_0x00bd:
            boolean r7 = r6 instanceof android.support.v7.widget.GridLayoutManager
            if (r7 == 0) goto L_0x0138
            r7 = 208(0xd0, float:2.91E-43)
            r12[r7] = r2
        L_0x00c5:
            android.support.v7.widget.LinearLayoutManager r6 = (android.support.v7.widget.LinearLayoutManager) r6
            int r7 = r6.findFirstVisibleItemPosition()
            r9 = 209(0xd1, float:2.93E-43)
            r12[r9] = r2
            int r6 = r6.findLastVisibleItemPosition()
            r9 = 210(0xd2, float:2.94E-43)
            r12[r9] = r2
            java.util.ArrayList r9 = r11.mChildren
            int r9 = r9.indexOf(r5)
            r10 = 211(0xd3, float:2.96E-43)
            r12[r10] = r2
            r5.setScrollPositon(r9)
            if (r9 > r7) goto L_0x00eb
            r6 = 212(0xd4, float:2.97E-43)
            r12[r6] = r2
            goto L_0x0122
        L_0x00eb:
            r10 = 213(0xd5, float:2.98E-43)
            r12[r10] = r2
            int r10 = r5.getStickyOffset()
            if (r10 > 0) goto L_0x00fa
            r6 = 214(0xd6, float:3.0E-43)
            r12[r6] = r2
            goto L_0x0116
        L_0x00fa:
            if (r7 < r9) goto L_0x0101
            r6 = 215(0xd7, float:3.01E-43)
            r12[r6] = r2
            goto L_0x0116
        L_0x0101:
            if (r9 <= r6) goto L_0x0108
            r6 = 216(0xd8, float:3.03E-43)
            r12[r6] = r2
            goto L_0x0116
        L_0x0108:
            r6 = 217(0xd9, float:3.04E-43)
            r12[r6] = r2
            int r6 = r5.getStickyOffset()
            if (r4 <= r6) goto L_0x011e
            r6 = 218(0xda, float:3.05E-43)
            r12[r6] = r2
        L_0x0116:
            r6 = 222(0xde, float:3.11E-43)
            r12[r6] = r2
            r9 = r3
            r3 = 0
            r6 = 1
            goto L_0x0131
        L_0x011e:
            r6 = 219(0xdb, float:3.07E-43)
            r12[r6] = r2
        L_0x0122:
            if (r9 > r3) goto L_0x012c
            r6 = 220(0xdc, float:3.08E-43)
            r12[r6] = r2
            r9 = r3
        L_0x0129:
            r3 = 1
            r6 = 0
            goto L_0x0131
        L_0x012c:
            r3 = 221(0xdd, float:3.1E-43)
            r12[r3] = r2
            goto L_0x0129
        L_0x0131:
            r7 = 223(0xdf, float:3.12E-43)
            r12[r7] = r2
            r7 = r9
            goto L_0x01b4
        L_0x0138:
            boolean r7 = r6 instanceof android.support.v7.widget.StaggeredGridLayoutManager
            if (r7 != 0) goto L_0x0145
            r6 = 224(0xe0, float:3.14E-43)
            r12[r6] = r2
            r7 = r3
            r3 = 0
        L_0x0142:
            r6 = 0
            goto L_0x01b4
        L_0x0145:
            r7 = 3
            int[] r7 = new int[r7]
            r9 = 225(0xe1, float:3.15E-43)
            r12[r9] = r2
            android.support.v7.widget.StaggeredGridLayoutManager r6 = (android.support.v7.widget.StaggeredGridLayoutManager) r6
            int[] r9 = r6.findFirstVisibleItemPositions(r7)
            r9 = r9[r8]
            r10 = 226(0xe2, float:3.17E-43)
            r12[r10] = r2
            int[] r6 = r6.findLastVisibleItemPositions(r7)
            r6 = r6[r8]
            r7 = 227(0xe3, float:3.18E-43)
            r12[r7] = r2
            java.util.ArrayList r7 = r11.mChildren
            int r7 = r7.indexOf(r5)
            r10 = 228(0xe4, float:3.2E-43)
            r12[r10] = r2
            if (r7 > r9) goto L_0x0173
            r6 = 229(0xe5, float:3.21E-43)
            r12[r6] = r2
            goto L_0x01a6
        L_0x0173:
            int r10 = r5.getStickyOffset()
            if (r10 > 0) goto L_0x017e
            r6 = 230(0xe6, float:3.22E-43)
            r12[r6] = r2
            goto L_0x019a
        L_0x017e:
            if (r9 < r7) goto L_0x0185
            r6 = 231(0xe7, float:3.24E-43)
            r12[r6] = r2
            goto L_0x019a
        L_0x0185:
            if (r7 <= r6) goto L_0x018c
            r6 = 232(0xe8, float:3.25E-43)
            r12[r6] = r2
            goto L_0x019a
        L_0x018c:
            r6 = 233(0xe9, float:3.27E-43)
            r12[r6] = r2
            int r6 = r5.getStickyOffset()
            if (r4 <= r6) goto L_0x01a2
            r6 = 234(0xea, float:3.28E-43)
            r12[r6] = r2
        L_0x019a:
            r6 = 238(0xee, float:3.34E-43)
            r12[r6] = r2
            r7 = r3
            r3 = 0
            r6 = 1
            goto L_0x01b4
        L_0x01a2:
            r6 = 235(0xeb, float:3.3E-43)
            r12[r6] = r2
        L_0x01a6:
            if (r7 > r3) goto L_0x01af
            r6 = 236(0xec, float:3.31E-43)
            r12[r6] = r2
            r7 = r3
        L_0x01ad:
            r3 = 1
            goto L_0x0142
        L_0x01af:
            r3 = 237(0xed, float:3.32E-43)
            r12[r3] = r2
            goto L_0x01ad
        L_0x01b4:
            if (r3 != 0) goto L_0x01bb
            r3 = 239(0xef, float:3.35E-43)
            r12[r3] = r2
            goto L_0x01d7
        L_0x01bb:
            int r3 = r5.getLocationFromStart()
            if (r3 >= 0) goto L_0x01c6
            r3 = 240(0xf0, float:3.36E-43)
            r12[r3] = r2
            goto L_0x01d7
        L_0x01c6:
            int r3 = r5.getStickyOffset()
            if (r4 <= r3) goto L_0x01d1
            r3 = 241(0xf1, float:3.38E-43)
            r12[r3] = r2
            goto L_0x01d7
        L_0x01d1:
            if (r13 >= 0) goto L_0x01dd
            r3 = 242(0xf2, float:3.39E-43)
            r12[r3] = r2
        L_0x01d7:
            r3 = 244(0xf4, float:3.42E-43)
            r12[r3] = r2
            r3 = 0
            goto L_0x01e2
        L_0x01dd:
            r3 = 243(0xf3, float:3.4E-43)
            r12[r3] = r2
            r3 = 1
        L_0x01e2:
            r9 = 245(0xf5, float:3.43E-43)
            r12[r9] = r2
            int r9 = r5.getLocationFromStart()
            int r10 = r5.getStickyOffset()
            if (r9 <= r10) goto L_0x01f5
            r9 = 246(0xf6, float:3.45E-43)
            r12[r9] = r2
            goto L_0x0206
        L_0x01f5:
            int r9 = r5.getStickyOffset()
            if (r4 > r9) goto L_0x0200
            r9 = 247(0xf7, float:3.46E-43)
            r12[r9] = r2
            goto L_0x0206
        L_0x0200:
            if (r13 <= 0) goto L_0x020b
            r9 = 248(0xf8, float:3.48E-43)
            r12[r9] = r2
        L_0x0206:
            r9 = 250(0xfa, float:3.5E-43)
            r12[r9] = r2
            goto L_0x0210
        L_0x020b:
            r8 = 249(0xf9, float:3.49E-43)
            r12[r8] = r2
            r8 = 1
        L_0x0210:
            if (r3 == 0) goto L_0x0221
            r3 = 251(0xfb, float:3.52E-43)
            r12[r3] = r2
            r3 = r0
            com.taobao.weex.ui.component.list.ListComponentView r3 = (com.taobao.weex.ui.component.list.ListComponentView) r3
            r3.notifyStickyShow(r5)
            r3 = 252(0xfc, float:3.53E-43)
            r12[r3] = r2
            goto L_0x023d
        L_0x0221:
            if (r8 == 0) goto L_0x0228
            r3 = 253(0xfd, float:3.55E-43)
            r12[r3] = r2
            goto L_0x0233
        L_0x0228:
            if (r6 != 0) goto L_0x022f
            r3 = 254(0xfe, float:3.56E-43)
            r12[r3] = r2
            goto L_0x023d
        L_0x022f:
            r3 = 255(0xff, float:3.57E-43)
            r12[r3] = r2
        L_0x0233:
            r3 = r0
            com.taobao.weex.ui.component.list.ListComponentView r3 = (com.taobao.weex.ui.component.list.ListComponentView) r3
            r3.notifyStickyRemove(r5)
            r3 = 256(0x100, float:3.59E-43)
            r12[r3] = r2
        L_0x023d:
            r5.setLocationFromStart(r4)
            r3 = 257(0x101, float:3.6E-43)
            r12[r3] = r2
            r3 = r7
            goto L_0x003f
        L_0x0247:
            if (r3 < 0) goto L_0x0257
            r13 = 258(0x102, float:3.62E-43)
            r12[r13] = r2
            com.taobao.weex.ui.component.list.ListComponentView r0 = (com.taobao.weex.ui.component.list.ListComponentView) r0
            r0.updateStickyView(r3)
            r13 = 259(0x103, float:3.63E-43)
            r12[r13] = r2
            goto L_0x0271
        L_0x0257:
            boolean r13 = r0 instanceof com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView
            if (r13 != 0) goto L_0x0260
            r13 = 260(0x104, float:3.64E-43)
            r12[r13] = r2
            goto L_0x0271
        L_0x0260:
            r13 = 261(0x105, float:3.66E-43)
            r12[r13] = r2
            com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView r0 = (com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView) r0
            com.taobao.weex.ui.component.list.StickyHeaderHelper r13 = r0.getStickyHeaderHelper()
            r13.clearStickyHeaders()
            r13 = 262(0x106, float:3.67E-43)
            r12[r13] = r2
        L_0x0271:
            r13 = 263(0x107, float:3.69E-43)
            r12[r13] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.BasicListComponent.onBeforeScroll(int, int):void");
    }

    public int getScrollY() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) getHostView();
        $jacocoInit[264] = true;
        if (viewGroup == null) {
            i = 0;
            $jacocoInit[265] = true;
        } else {
            i = ((ListComponentView) viewGroup).getInnerView().getScrollY();
            $jacocoInit[266] = true;
        }
        $jacocoInit[267] = true;
        return i;
    }

    public int getScrollX() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) getHostView();
        $jacocoInit[268] = true;
        if (viewGroup == null) {
            i = 0;
            $jacocoInit[269] = true;
        } else {
            i = ((ListComponentView) viewGroup).getInnerView().getScrollX();
            $jacocoInit[270] = true;
        }
        $jacocoInit[271] = true;
        return i;
    }

    public void addChild(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        addChild(wXComponent, -1);
        $jacocoInit[272] = true;
    }

    /* access modifiers changed from: protected */
    public int getChildrenLayoutTopOffset() {
        $jacocoInit()[273] = true;
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addChild(com.taobao.weex.ui.component.WXComponent r9, int r10) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            super.addChild(r9, r10)
            r1 = 1
            if (r9 != 0) goto L_0x000f
            r9 = 274(0x112, float:3.84E-43)
            r0[r9] = r1
            goto L_0x0016
        L_0x000f:
            r2 = -1
            if (r10 >= r2) goto L_0x001b
            r9 = 275(0x113, float:3.85E-43)
            r0[r9] = r1
        L_0x0016:
            r9 = 276(0x114, float:3.87E-43)
            r0[r9] = r1
            return
        L_0x001b:
            java.util.ArrayList r3 = r8.mChildren
            int r3 = r3.size()
            if (r10 < r3) goto L_0x0029
            r10 = 277(0x115, float:3.88E-43)
            r0[r10] = r1
            r10 = -1
            goto L_0x002d
        L_0x0029:
            r3 = 278(0x116, float:3.9E-43)
            r0[r3] = r1
        L_0x002d:
            r3 = 279(0x117, float:3.91E-43)
            r0[r3] = r1
            r8.bindViewType(r9)
            r3 = 280(0x118, float:3.92E-43)
            r0[r3] = r1
            if (r10 != r2) goto L_0x0046
            java.util.ArrayList r3 = r8.mChildren
            int r3 = r3.size()
            int r3 = r3 - r1
            r4 = 281(0x119, float:3.94E-43)
            r0[r4] = r1
            goto L_0x004b
        L_0x0046:
            r3 = 282(0x11a, float:3.95E-43)
            r0[r3] = r1
            r3 = r10
        L_0x004b:
            r4 = 283(0x11b, float:3.97E-43)
            r0[r4] = r1
            android.view.View r4 = r8.getHostView()
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            if (r4 != 0) goto L_0x005d
            r9 = 284(0x11c, float:3.98E-43)
            r0[r9] = r1
            goto L_0x0204
        L_0x005d:
            r5 = 285(0x11d, float:4.0E-43)
            r0[r5] = r1
            com.taobao.weex.ui.action.BasicComponentData r5 = r8.getBasicComponentData()
            r6 = 0
            if (r5 != 0) goto L_0x006d
            r5 = 286(0x11e, float:4.01E-43)
            r0[r5] = r1
            goto L_0x008b
        L_0x006d:
            r5 = 287(0x11f, float:4.02E-43)
            r0[r5] = r1
            com.taobao.weex.dom.WXAttr r5 = r8.getAttrs()
            java.lang.String r7 = "insertAnimation"
            java.lang.Object r5 = r5.get(r7)
            r7 = 288(0x120, float:4.04E-43)
            r0[r7] = r1
            java.lang.String r7 = "default"
            boolean r5 = r7.equals(r5)
            if (r5 != 0) goto L_0x008d
            r5 = 289(0x121, float:4.05E-43)
            r0[r5] = r1
        L_0x008b:
            r5 = 0
            goto L_0x0092
        L_0x008d:
            r5 = 290(0x122, float:4.06E-43)
            r0[r5] = r1
            r5 = 1
        L_0x0092:
            if (r5 == 0) goto L_0x00a9
            r5 = 291(0x123, float:4.08E-43)
            r0[r5] = r1
            r5 = r4
            com.taobao.weex.ui.component.list.ListComponentView r5 = (com.taobao.weex.ui.component.list.ListComponentView) r5
            com.taobao.weex.ui.view.listview.WXRecyclerView r5 = r5.getInnerView()
            android.support.v7.widget.RecyclerView$ItemAnimator r7 = r8.mItemAnimator
            r5.setItemAnimator(r7)
            r5 = 292(0x124, float:4.09E-43)
            r0[r5] = r1
            goto L_0x00b8
        L_0x00a9:
            r5 = r4
            com.taobao.weex.ui.component.list.ListComponentView r5 = (com.taobao.weex.ui.component.list.ListComponentView) r5
            com.taobao.weex.ui.view.listview.WXRecyclerView r5 = r5.getInnerView()
            r7 = 0
            r5.setItemAnimator(r7)
            r5 = 293(0x125, float:4.1E-43)
            r0[r5] = r1
        L_0x00b8:
            r5 = 294(0x126, float:4.12E-43)
            r0[r5] = r1
            com.taobao.weex.ui.action.BasicComponentData r5 = r9.getBasicComponentData()
            if (r5 != 0) goto L_0x00c7
            r9 = 295(0x127, float:4.13E-43)
            r0[r9] = r1
            goto L_0x0103
        L_0x00c7:
            r5 = 296(0x128, float:4.15E-43)
            r0[r5] = r1
            com.taobao.weex.dom.WXAttr r9 = r9.getAttrs()
            java.lang.String r5 = "keepScrollPosition"
            java.lang.Object r9 = r9.get(r5)
            r5 = 297(0x129, float:4.16E-43)
            r0[r5] = r1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            java.lang.Boolean r9 = com.taobao.weex.utils.WXUtils.getBoolean(r9, r5)
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L_0x00ec
            r9 = 298(0x12a, float:4.18E-43)
            r0[r9] = r1
            goto L_0x0103
        L_0x00ec:
            int r9 = r8.getChildCount()
            if (r10 <= r9) goto L_0x00f7
            r9 = 299(0x12b, float:4.19E-43)
            r0[r9] = r1
            goto L_0x0103
        L_0x00f7:
            if (r10 > r2) goto L_0x00fe
            r9 = 300(0x12c, float:4.2E-43)
            r0[r9] = r1
            goto L_0x0103
        L_0x00fe:
            r9 = 301(0x12d, float:4.22E-43)
            r0[r9] = r1
            r6 = 1
        L_0x0103:
            if (r6 == 0) goto L_0x01f7
            r9 = 302(0x12e, float:4.23E-43)
            r0[r9] = r1
            r9 = r4
            com.taobao.weex.ui.component.list.ListComponentView r9 = (com.taobao.weex.ui.component.list.ListComponentView) r9
            com.taobao.weex.ui.view.listview.WXRecyclerView r10 = r9.getInnerView()
            android.support.v7.widget.RecyclerView$LayoutManager r10 = r10.getLayoutManager()
            boolean r10 = r10 instanceof android.support.v7.widget.LinearLayoutManager
            if (r10 != 0) goto L_0x011e
            r10 = 303(0x12f, float:4.25E-43)
            r0[r10] = r1
            goto L_0x01ce
        L_0x011e:
            r10 = 304(0x130, float:4.26E-43)
            r0[r10] = r1
            com.taobao.weex.ui.view.listview.WXRecyclerView r10 = r9.getInnerView()
            boolean r10 = r10.isLayoutFrozen()
            if (r10 == 0) goto L_0x0131
            r10 = 305(0x131, float:4.27E-43)
            r0[r10] = r1
            goto L_0x0140
        L_0x0131:
            r10 = 306(0x132, float:4.29E-43)
            r0[r10] = r1
            com.taobao.weex.ui.view.listview.WXRecyclerView r10 = r9.getInnerView()
            r10.setLayoutFrozen(r1)
            r10 = 307(0x133, float:4.3E-43)
            r0[r10] = r1
        L_0x0140:
            com.taobao.weex.ui.component.WXComponent r10 = r8.keepPositionCell
            if (r10 == 0) goto L_0x0149
            r10 = 308(0x134, float:4.32E-43)
            r0[r10] = r1
            goto L_0x01a8
        L_0x0149:
            r10 = 309(0x135, float:4.33E-43)
            r0[r10] = r1
            com.taobao.weex.ui.view.listview.WXRecyclerView r10 = r9.getInnerView()
            android.support.v7.widget.RecyclerView$LayoutManager r10 = r10.getLayoutManager()
            android.support.v7.widget.LinearLayoutManager r10 = (android.support.v7.widget.LinearLayoutManager) r10
            int r10 = r10.findLastCompletelyVisibleItemPosition()
            r2 = 310(0x136, float:4.34E-43)
            r0[r2] = r1
            com.taobao.weex.ui.view.listview.WXRecyclerView r2 = r9.getInnerView()
            android.support.v7.widget.RecyclerView$ViewHolder r10 = r2.findViewHolderForAdapterPosition(r10)
            com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder r10 = (com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder) r10
            if (r10 != 0) goto L_0x0170
            r10 = 311(0x137, float:4.36E-43)
            r0[r10] = r1
            goto L_0x017e
        L_0x0170:
            r2 = 312(0x138, float:4.37E-43)
            r0[r2] = r1
            com.taobao.weex.ui.component.WXComponent r10 = r10.getComponent()
            r8.keepPositionCell = r10
            r10 = 313(0x139, float:4.39E-43)
            r0[r10] = r1
        L_0x017e:
            com.taobao.weex.ui.component.WXComponent r10 = r8.keepPositionCell
            if (r10 != 0) goto L_0x0187
            r10 = 314(0x13a, float:4.4E-43)
            r0[r10] = r1
            goto L_0x01a8
        L_0x0187:
            java.lang.Runnable r10 = r8.keepPositionCellRunnable
            if (r10 != 0) goto L_0x0190
            r10 = 315(0x13b, float:4.41E-43)
            r0[r10] = r1
            goto L_0x019d
        L_0x0190:
            r10 = 316(0x13c, float:4.43E-43)
            r0[r10] = r1
            java.lang.Runnable r10 = r8.keepPositionCellRunnable
            r4.removeCallbacks(r10)
            r10 = 317(0x13d, float:4.44E-43)
            r0[r10] = r1
        L_0x019d:
            com.taobao.weex.ui.component.list.BasicListComponent$5 r10 = new com.taobao.weex.ui.component.list.BasicListComponent$5
            r10.<init>(r8, r4)
            r8.keepPositionCellRunnable = r10
            r10 = 318(0x13e, float:4.46E-43)
            r0[r10] = r1
        L_0x01a8:
            java.lang.Runnable r10 = r8.keepPositionCellRunnable
            if (r10 == 0) goto L_0x01b1
            r10 = 319(0x13f, float:4.47E-43)
            r0[r10] = r1
            goto L_0x01ce
        L_0x01b1:
            r10 = 320(0x140, float:4.48E-43)
            r0[r10] = r1
            com.taobao.weex.ui.view.listview.WXRecyclerView r10 = r9.getInnerView()
            com.taobao.weex.ui.view.listview.WXRecyclerView r2 = r9.getInnerView()
            android.support.v7.widget.RecyclerView$LayoutManager r2 = r2.getLayoutManager()
            android.support.v7.widget.LinearLayoutManager r2 = (android.support.v7.widget.LinearLayoutManager) r2
            int r2 = r2.findLastVisibleItemPosition()
            r10.scrollToPosition(r2)
            r10 = 321(0x141, float:4.5E-43)
            r0[r10] = r1
        L_0x01ce:
            com.taobao.weex.ui.view.listview.adapter.RecyclerViewBaseAdapter r9 = r9.getRecyclerViewBaseAdapter()
            r9.notifyItemInserted(r3)
            java.lang.Runnable r9 = r8.keepPositionCellRunnable
            if (r9 != 0) goto L_0x01de
            r9 = 322(0x142, float:4.51E-43)
            r0[r9] = r1
            goto L_0x0204
        L_0x01de:
            r9 = 323(0x143, float:4.53E-43)
            r0[r9] = r1
            java.lang.Runnable r9 = r8.keepPositionCellRunnable
            r4.removeCallbacks(r9)
            r9 = 324(0x144, float:4.54E-43)
            r0[r9] = r1
            java.lang.Runnable r9 = r8.keepPositionCellRunnable
            long r2 = r8.keepPositionLayoutDelay
            r4.postDelayed(r9, r2)
            r9 = 325(0x145, float:4.55E-43)
            r0[r9] = r1
            goto L_0x0204
        L_0x01f7:
            com.taobao.weex.ui.component.list.ListComponentView r4 = (com.taobao.weex.ui.component.list.ListComponentView) r4
            com.taobao.weex.ui.view.listview.adapter.RecyclerViewBaseAdapter r9 = r4.getRecyclerViewBaseAdapter()
            r9.notifyItemChanged(r3)
            r9 = 326(0x146, float:4.57E-43)
            r0[r9] = r1
        L_0x0204:
            r8.relocateAppearanceHelper()
            r9 = 327(0x147, float:4.58E-43)
            r0[r9] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.BasicListComponent.addChild(com.taobao.weex.ui.component.WXComponent, int):void");
    }

    private void relocateAppearanceHelper() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[328] = true;
        for (Map.Entry<String, AppearanceHelper> value : this.mAppearComponents.entrySet()) {
            $jacocoInit[329] = true;
            $jacocoInit[330] = true;
            AppearanceHelper appearanceHelper = (AppearanceHelper) value.getValue();
            $jacocoInit[331] = true;
            WXComponent findDirectListChild = findDirectListChild(appearanceHelper.getAwareChild());
            $jacocoInit[332] = true;
            int indexOf = this.mChildren.indexOf(findDirectListChild);
            $jacocoInit[333] = true;
            appearanceHelper.setCellPosition(indexOf);
            $jacocoInit[334] = true;
        }
        $jacocoInit[335] = true;
    }

    public void addSubView(View view, int i) {
        $jacocoInit()[336] = true;
    }

    public void remove(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        int indexOf = this.mChildren.indexOf(wXComponent);
        if (!z) {
            $jacocoInit[337] = true;
        } else {
            $jacocoInit[338] = true;
            wXComponent.detachViewAndClearPreInfo();
            $jacocoInit[339] = true;
        }
        unBindViewType(wXComponent);
        $jacocoInit[340] = true;
        ViewGroup viewGroup = (ViewGroup) getHostView();
        if (viewGroup == null) {
            $jacocoInit[341] = true;
            return;
        }
        boolean z2 = false;
        $jacocoInit[342] = true;
        Object obj = getAttrs().get(Constants.Name.DELETE_CELL_ANIMATION);
        $jacocoInit[343] = true;
        if (!"default".equals(obj)) {
            $jacocoInit[344] = true;
        } else {
            $jacocoInit[345] = true;
            z2 = true;
        }
        if (z2) {
            $jacocoInit[346] = true;
            ((ListComponentView) viewGroup).getInnerView().setItemAnimator(this.mItemAnimator);
            $jacocoInit[347] = true;
        } else {
            ((ListComponentView) viewGroup).getInnerView().setItemAnimator((RecyclerView.ItemAnimator) null);
            $jacocoInit[348] = true;
        }
        ((ListComponentView) viewGroup).getRecyclerViewBaseAdapter().notifyItemRemoved(indexOf);
        $jacocoInit[349] = true;
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[350] = true;
        } else {
            $jacocoInit[351] = true;
            String str = this.TAG;
            WXLogUtils.d(str, "removeChild child at " + indexOf);
            $jacocoInit[352] = true;
        }
        super.remove(wXComponent, z);
        $jacocoInit[353] = true;
    }

    public void computeVisiblePointInViewCoordinate(PointF pointF) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerView innerView = ((ListComponentView) ((ViewGroup) getHostView())).getInnerView();
        $jacocoInit[354] = true;
        pointF.set((float) innerView.computeHorizontalScrollOffset(), (float) innerView.computeVerticalScrollOffset());
        $jacocoInit[355] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onViewRecycled(com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder r8) {
        /*
            r7 = this;
            boolean[] r0 = $jacocoInit()
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 1
            r4 = 356(0x164, float:4.99E-43)
            r0[r4] = r3
            r4 = 0
            r8.setComponentUsing(r4)
            if (r8 != 0) goto L_0x0018
            r8 = 357(0x165, float:5.0E-43)
            r0[r8] = r3
            goto L_0x0048
        L_0x0018:
            r4 = 358(0x166, float:5.02E-43)
            r0[r4] = r3
            boolean r4 = r8.canRecycled()
            if (r4 != 0) goto L_0x0027
            r8 = 359(0x167, float:5.03E-43)
            r0[r8] = r3
            goto L_0x0048
        L_0x0027:
            r4 = 360(0x168, float:5.04E-43)
            r0[r4] = r3
            com.taobao.weex.ui.component.WXComponent r4 = r8.getComponent()
            if (r4 != 0) goto L_0x0036
            r8 = 361(0x169, float:5.06E-43)
            r0[r8] = r3
            goto L_0x0048
        L_0x0036:
            r4 = 362(0x16a, float:5.07E-43)
            r0[r4] = r3
            com.taobao.weex.ui.component.WXComponent r4 = r8.getComponent()
            boolean r4 = r4.isUsing()
            if (r4 == 0) goto L_0x0054
            r8 = 363(0x16b, float:5.09E-43)
            r0[r8] = r3
        L_0x0048:
            java.lang.String r8 = r7.TAG
            java.lang.String r4 = "this holder can not be allowed to  recycled"
            com.taobao.weex.utils.WXLogUtils.w((java.lang.String) r8, (java.lang.String) r4)
            r8 = 366(0x16e, float:5.13E-43)
            r0[r8] = r3
            goto L_0x005f
        L_0x0054:
            r4 = 364(0x16c, float:5.1E-43)
            r0[r4] = r3
            r8.recycled()
            r8 = 365(0x16d, float:5.11E-43)
            r0[r8] = r3
        L_0x005f:
            boolean r8 = com.taobao.weex.WXEnvironment.isApkDebugable()
            if (r8 != 0) goto L_0x006a
            r8 = 367(0x16f, float:5.14E-43)
            r0[r8] = r3
            goto L_0x009d
        L_0x006a:
            r8 = 368(0x170, float:5.16E-43)
            r0[r8] = r3
            java.lang.String r8 = r7.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Recycle holder "
            r4.append(r5)
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r1
            r4.append(r5)
            java.lang.String r1 = "  Thread:"
            r4.append(r1)
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.String r1 = r1.getName()
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.taobao.weex.utils.WXLogUtils.d((java.lang.String) r8, (java.lang.String) r1)
            r8 = 369(0x171, float:5.17E-43)
            r0[r8] = r3
        L_0x009d:
            r8 = 370(0x172, float:5.18E-43)
            r0[r8] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.BasicListComponent.onViewRecycled(com.taobao.weex.ui.view.listview.adapter.ListBaseViewHolder):void");
    }

    public void onBindViewHolder(final ListBaseViewHolder listBaseViewHolder, int i) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (listBaseViewHolder == null) {
            $jacocoInit[371] = true;
            return;
        }
        listBaseViewHolder.setComponentUsing(true);
        $jacocoInit[372] = true;
        WXComponent child = getChild(i);
        if (child == null) {
            $jacocoInit[373] = true;
        } else if (child instanceof WXRefresh) {
            $jacocoInit[374] = true;
        } else if (child instanceof WXLoading) {
            $jacocoInit[375] = true;
        } else {
            $jacocoInit[376] = true;
            if (child.isFixed()) {
                $jacocoInit[377] = true;
            } else {
                if (listBaseViewHolder.getComponent() == null) {
                    $jacocoInit[393] = true;
                } else if (!(listBaseViewHolder.getComponent() instanceof WXCell)) {
                    $jacocoInit[394] = true;
                } else {
                    $jacocoInit[395] = true;
                    if (!listBaseViewHolder.isRecycled()) {
                        $jacocoInit[396] = true;
                    } else {
                        $jacocoInit[397] = true;
                        listBaseViewHolder.bindData(child);
                        $jacocoInit[398] = true;
                        child.onRenderFinish(1);
                        $jacocoInit[399] = true;
                    }
                    if (this.mDragHelper == null) {
                        $jacocoInit[400] = true;
                    } else if (!this.mDragHelper.isDraggable()) {
                        $jacocoInit[401] = true;
                    } else {
                        if (this.mTriggerType == null) {
                            str = "longpress";
                            $jacocoInit[403] = true;
                        } else {
                            str = this.mTriggerType;
                            $jacocoInit[404] = true;
                        }
                        this.mTriggerType = str;
                        $jacocoInit[405] = true;
                        WXCell wXCell = (WXCell) listBaseViewHolder.getComponent();
                        $jacocoInit[406] = true;
                        WXAttr attrs = wXCell.getAttrs();
                        $jacocoInit[407] = true;
                        boolean booleanValue = WXUtils.getBoolean(attrs.get(EXCLUDED), false).booleanValue();
                        $jacocoInit[408] = true;
                        this.mDragHelper.setDragExcluded(listBaseViewHolder, booleanValue);
                        $jacocoInit[409] = true;
                        if ("pan".equals(this.mTriggerType)) {
                            $jacocoInit[410] = true;
                            this.mDragHelper.setLongPressDragEnabled(false);
                            $jacocoInit[411] = true;
                            WXComponent findComponentByAnchorName = findComponentByAnchorName(wXCell, DRAG_ANCHOR);
                            $jacocoInit[412] = true;
                            if (findComponentByAnchorName == null) {
                                $jacocoInit[413] = true;
                            } else if (findComponentByAnchorName.getHostView() == null) {
                                $jacocoInit[414] = true;
                            } else if (booleanValue) {
                                $jacocoInit[415] = true;
                            } else {
                                $jacocoInit[416] = true;
                                View hostView = findComponentByAnchorName.getHostView();
                                $jacocoInit[417] = true;
                                hostView.setOnTouchListener(new View.OnTouchListener(this) {
                                    private static transient /* synthetic */ boolean[] $jacocoData;
                                    final /* synthetic */ BasicListComponent this$0;

                                    private static /* synthetic */ boolean[] $jacocoInit() {
                                        boolean[] zArr = $jacocoData;
                                        if (zArr != null) {
                                            return zArr;
                                        }
                                        boolean[] a2 = Offline.a(-5040582275871990515L, "com/taobao/weex/ui/component/list/BasicListComponent$6", 5);
                                        $jacocoData = a2;
                                        return a2;
                                    }

                                    {
                                        boolean[] $jacocoInit = $jacocoInit();
                                        this.this$0 = r2;
                                        $jacocoInit[0] = true;
                                    }

                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        boolean[] $jacocoInit = $jacocoInit();
                                        if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                                            $jacocoInit[1] = true;
                                        } else {
                                            $jacocoInit[2] = true;
                                            BasicListComponent.access$400(this.this$0).startDrag(listBaseViewHolder);
                                            $jacocoInit[3] = true;
                                        }
                                        $jacocoInit[4] = true;
                                        return true;
                                    }
                                });
                                $jacocoInit[418] = true;
                                $jacocoInit[423] = true;
                            }
                            if (!WXEnvironment.isApkDebugable()) {
                                $jacocoInit[419] = true;
                            } else if (!booleanValue) {
                                $jacocoInit[420] = true;
                                WXLogUtils.e(this.TAG, "[error] onBindViewHolder: the anchor component or view is not found");
                                $jacocoInit[421] = true;
                            } else {
                                String str2 = this.TAG;
                                WXLogUtils.d(str2, "onBindViewHolder: position " + i + " is drag excluded");
                                $jacocoInit[422] = true;
                            }
                            $jacocoInit[423] = true;
                        } else if (!"longpress".equals(this.mTriggerType)) {
                            $jacocoInit[424] = true;
                        } else {
                            $jacocoInit[425] = true;
                            this.mDragHelper.setLongPressDragEnabled(true);
                            $jacocoInit[426] = true;
                        }
                    }
                    $jacocoInit[402] = true;
                    return;
                }
                $jacocoInit[427] = true;
                return;
            }
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[378] = true;
        } else {
            $jacocoInit[379] = true;
            String str3 = this.TAG;
            WXLogUtils.d(str3, "Bind WXRefresh & WXLoading " + listBaseViewHolder);
            $jacocoInit[380] = true;
        }
        if (!(child instanceof WXBaseRefresh)) {
            $jacocoInit[381] = true;
        } else {
            $jacocoInit[382] = true;
            if (listBaseViewHolder.getView() == null) {
                $jacocoInit[383] = true;
            } else {
                $jacocoInit[384] = true;
                if (child.getAttrs().get("holderBackground") == null) {
                    $jacocoInit[385] = true;
                } else {
                    $jacocoInit[386] = true;
                    Object obj = child.getAttrs().get("holderBackground");
                    $jacocoInit[387] = true;
                    int color = WXResourceUtils.getColor(obj.toString(), -1);
                    $jacocoInit[388] = true;
                    listBaseViewHolder.getView().setBackgroundColor(color);
                    $jacocoInit[389] = true;
                    listBaseViewHolder.getView().setVisibility(0);
                    $jacocoInit[390] = true;
                    listBaseViewHolder.getView().postInvalidate();
                    $jacocoInit[391] = true;
                }
            }
        }
        $jacocoInit[392] = true;
    }

    /* access modifiers changed from: protected */
    public void markComponentUsable() {
        boolean[] $jacocoInit = $jacocoInit();
        Iterator it = this.mChildren.iterator();
        $jacocoInit[428] = true;
        while (it.hasNext()) {
            $jacocoInit[429] = true;
            ((WXComponent) it.next()).setUsing(false);
            $jacocoInit[430] = true;
        }
        $jacocoInit[431] = true;
    }

    public ListBaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChildren == null) {
            $jacocoInit[432] = true;
        } else if (this.mViewTypes == null) {
            $jacocoInit[433] = true;
            ListBaseViewHolder createVHForFakeComponent = createVHForFakeComponent(i);
            $jacocoInit[434] = true;
            return createVHForFakeComponent;
        } else {
            ArrayList arrayList = this.mViewTypes.get(i);
            $jacocoInit[435] = true;
            checkRecycledViewPool(i);
            if (arrayList == null) {
                $jacocoInit[436] = true;
                ListBaseViewHolder createVHForFakeComponent2 = createVHForFakeComponent(i);
                $jacocoInit[437] = true;
                return createVHForFakeComponent2;
            }
            int size = arrayList.size();
            $jacocoInit[438] = true;
            int i2 = 0;
            while (i2 < size) {
                $jacocoInit[440] = true;
                WXComponent wXComponent = (WXComponent) arrayList.get(i2);
                if (wXComponent == null) {
                    $jacocoInit[441] = true;
                } else {
                    $jacocoInit[442] = true;
                    if (!wXComponent.isUsing()) {
                        $jacocoInit[443] = true;
                    } else if (i2 == size - 1) {
                        $jacocoInit[444] = true;
                    } else {
                        $jacocoInit[445] = true;
                    }
                    if (!wXComponent.isUsing()) {
                        $jacocoInit[446] = true;
                    } else {
                        $jacocoInit[447] = true;
                        String str = this.TAG;
                        WXLogUtils.w(str, wXComponent + " is using, force use!");
                        $jacocoInit[448] = true;
                    }
                    if (wXComponent.isFixed()) {
                        $jacocoInit[449] = true;
                        ListBaseViewHolder createVHForFakeComponent3 = createVHForFakeComponent(i);
                        $jacocoInit[450] = true;
                        return createVHForFakeComponent3;
                    } else if (wXComponent instanceof WXCell) {
                        $jacocoInit[451] = true;
                        if (wXComponent.getRealView() != null) {
                            $jacocoInit[452] = true;
                            ListBaseViewHolder listBaseViewHolder = new ListBaseViewHolder(wXComponent, i);
                            $jacocoInit[453] = true;
                            return listBaseViewHolder;
                        }
                        wXComponent.lazy(false);
                        $jacocoInit[454] = true;
                        wXComponent.createView();
                        $jacocoInit[455] = true;
                        wXComponent.applyLayoutAndEvent(wXComponent);
                        $jacocoInit[456] = true;
                        ListBaseViewHolder listBaseViewHolder2 = new ListBaseViewHolder(wXComponent, i, true);
                        $jacocoInit[457] = true;
                        return listBaseViewHolder2;
                    } else if (wXComponent instanceof WXBaseRefresh) {
                        $jacocoInit[458] = true;
                        ListBaseViewHolder createVHForRefreshComponent = createVHForRefreshComponent(i);
                        $jacocoInit[459] = true;
                        return createVHForRefreshComponent;
                    } else {
                        WXLogUtils.e(this.TAG, "List cannot include element except cell、header、fixed、refresh and loading");
                        $jacocoInit[460] = true;
                        ListBaseViewHolder createVHForFakeComponent4 = createVHForFakeComponent(i);
                        $jacocoInit[461] = true;
                        return createVHForFakeComponent4;
                    }
                }
                i2++;
                $jacocoInit[462] = true;
            }
            $jacocoInit[439] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[463] = true;
        } else {
            $jacocoInit[464] = true;
            String str2 = this.TAG;
            WXLogUtils.e(str2, "Cannot find request viewType: " + i);
            $jacocoInit[465] = true;
        }
        ListBaseViewHolder createVHForFakeComponent5 = createVHForFakeComponent(i);
        $jacocoInit[466] = true;
        return createVHForFakeComponent5;
    }

    private void checkRecycledViewPool(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (this.mViewTypes.size() <= 9) {
                $jacocoInit[467] = true;
            } else {
                mAllowCacheViewHolder = false;
                $jacocoInit[468] = true;
            }
            if (!mDownForBidCacheViewHolder) {
                $jacocoInit[469] = true;
            } else {
                $jacocoInit[470] = true;
                if (getHostView() == null) {
                    $jacocoInit[471] = true;
                } else if (((ListComponentView) ((ViewGroup) getHostView())).getInnerView() == null) {
                    $jacocoInit[472] = true;
                } else {
                    $jacocoInit[473] = true;
                    ((ListComponentView) ((ViewGroup) getHostView())).getInnerView().getRecycledViewPool().setMaxRecycledViews(i, 0);
                    $jacocoInit[474] = true;
                }
            }
            if (mDownForBidCacheViewHolder) {
                $jacocoInit[475] = true;
            } else if (mAllowCacheViewHolder) {
                $jacocoInit[476] = true;
            } else {
                $jacocoInit[477] = true;
                if (getHostView() == null) {
                    $jacocoInit[478] = true;
                } else if (((ListComponentView) ((ViewGroup) getHostView())).getInnerView() == null) {
                    $jacocoInit[479] = true;
                } else {
                    $jacocoInit[480] = true;
                    $jacocoInit[481] = true;
                    int i2 = 0;
                    while (i2 < this.mViewTypes.size()) {
                        $jacocoInit[482] = true;
                        ((ListComponentView) ((ViewGroup) getHostView())).getInnerView().getRecycledViewPool().setMaxRecycledViews(this.mViewTypes.keyAt(i2), 0);
                        i2++;
                        $jacocoInit[483] = true;
                    }
                    mDownForBidCacheViewHolder = true;
                    $jacocoInit[484] = true;
                }
            }
            $jacocoInit[485] = true;
        } catch (Exception unused) {
            $jacocoInit[486] = true;
            WXLogUtils.e(this.TAG, "Clear recycledViewPool error!");
            $jacocoInit[487] = true;
        }
        $jacocoInit[488] = true;
    }

    public int getItemViewType(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int generateViewType = generateViewType(getChild(i));
        $jacocoInit[489] = true;
        return generateViewType;
    }

    @Nullable
    private WXComponent findComponentByAnchorName(@NonNull WXComponent wXComponent, @NonNull String str) {
        long j;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[490] = true;
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[491] = true;
            j = 0;
        } else {
            $jacocoInit[492] = true;
            j = System.currentTimeMillis();
            $jacocoInit[493] = true;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        $jacocoInit[494] = true;
        arrayDeque.add(wXComponent);
        $jacocoInit[495] = true;
        while (!arrayDeque.isEmpty()) {
            $jacocoInit[496] = true;
            WXComponent wXComponent2 = (WXComponent) arrayDeque.removeFirst();
            if (wXComponent2 == null) {
                $jacocoInit[497] = true;
            } else {
                $jacocoInit[498] = true;
                String string = WXUtils.getString(wXComponent2.getAttrs().get(str), (String) null);
                $jacocoInit[499] = true;
                if (string == null) {
                    $jacocoInit[500] = true;
                } else if (!string.equals("true")) {
                    $jacocoInit[501] = true;
                } else {
                    $jacocoInit[502] = true;
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[503] = true;
                    } else {
                        $jacocoInit[504] = true;
                        WXLogUtils.d("dragPerf", "findComponentByAnchorName time: " + (System.currentTimeMillis() - j) + d.H);
                        $jacocoInit[505] = true;
                    }
                    $jacocoInit[506] = true;
                    return wXComponent2;
                }
            }
            if (!(wXComponent2 instanceof WXVContainer)) {
                $jacocoInit[507] = true;
            } else {
                WXVContainer wXVContainer = (WXVContainer) wXComponent2;
                $jacocoInit[508] = true;
                int i = 0;
                int childCount = wXVContainer.childCount();
                $jacocoInit[509] = true;
                while (i < childCount) {
                    $jacocoInit[511] = true;
                    WXComponent child = wXVContainer.getChild(i);
                    $jacocoInit[512] = true;
                    arrayDeque.add(child);
                    i++;
                    $jacocoInit[513] = true;
                }
                $jacocoInit[510] = true;
            }
            $jacocoInit[514] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[515] = true;
        } else {
            $jacocoInit[516] = true;
            WXLogUtils.d("dragPerf", "findComponentByAnchorName elapsed time: " + (System.currentTimeMillis() - j) + d.H);
            $jacocoInit[517] = true;
        }
        $jacocoInit[518] = true;
        return null;
    }

    private String getTriggerType(@Nullable WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[519] = true;
            return "longpress";
        }
        String string = WXUtils.getString(wXComponent.getAttrs().get(DRAG_TRIGGER_TYPE), "longpress");
        $jacocoInit[520] = true;
        if ("longpress".equals(string)) {
            $jacocoInit[521] = true;
        } else if ("pan".equals(string)) {
            $jacocoInit[522] = true;
        } else {
            string = "longpress";
            $jacocoInit[523] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[524] = true;
        } else {
            $jacocoInit[525] = true;
            String str = this.TAG;
            WXLogUtils.d(str, "trigger type is " + string);
            $jacocoInit[526] = true;
        }
        $jacocoInit[527] = true;
        return string;
    }

    private void bindViewType(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        int generateViewType = generateViewType(wXComponent);
        if (this.mViewTypes != null) {
            $jacocoInit[528] = true;
        } else {
            $jacocoInit[529] = true;
            this.mViewTypes = new SparseArray<>();
            $jacocoInit[530] = true;
        }
        ArrayList arrayList = this.mViewTypes.get(generateViewType);
        if (arrayList != null) {
            $jacocoInit[531] = true;
        } else {
            $jacocoInit[532] = true;
            arrayList = new ArrayList();
            $jacocoInit[533] = true;
            this.mViewTypes.put(generateViewType, arrayList);
            $jacocoInit[534] = true;
        }
        arrayList.add(wXComponent);
        $jacocoInit[535] = true;
    }

    private void unBindViewType(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        int generateViewType = generateViewType(wXComponent);
        if (this.mViewTypes == null) {
            $jacocoInit[536] = true;
            return;
        }
        ArrayList arrayList = this.mViewTypes.get(generateViewType);
        if (arrayList == null) {
            $jacocoInit[537] = true;
            return;
        }
        arrayList.remove(wXComponent);
        $jacocoInit[538] = true;
    }

    private int generateViewType(WXComponent wXComponent) {
        long j;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            j = (long) Integer.parseInt(wXComponent.getRef());
            $jacocoInit[539] = true;
            String scope = wXComponent.getAttrs().getScope();
            $jacocoInit[540] = true;
            if (TextUtils.isEmpty(scope)) {
                $jacocoInit[541] = true;
            } else {
                if (this.mRefToViewType != null) {
                    $jacocoInit[542] = true;
                } else {
                    $jacocoInit[543] = true;
                    this.mRefToViewType = new ArrayMap<>();
                    $jacocoInit[544] = true;
                }
                if (this.mRefToViewType.containsKey(scope)) {
                    $jacocoInit[545] = true;
                } else {
                    $jacocoInit[546] = true;
                    this.mRefToViewType.put(scope, Long.valueOf(j));
                    $jacocoInit[547] = true;
                }
                j = this.mRefToViewType.get(scope).longValue();
                $jacocoInit[548] = true;
            }
            $jacocoInit[549] = true;
        } catch (RuntimeException e) {
            $jacocoInit[550] = true;
            WXLogUtils.eTag(this.TAG, e);
            j = -1;
            $jacocoInit[551] = true;
            WXLogUtils.e(this.TAG, "getItemViewType: NO ID, this will crash the whole render system of WXListRecyclerView");
            $jacocoInit[552] = true;
        }
        int i = (int) j;
        $jacocoInit[553] = true;
        return i;
    }

    public int getItemCount() {
        boolean[] $jacocoInit = $jacocoInit();
        int childCount = getChildCount();
        $jacocoInit[554] = true;
        return childCount;
    }

    public boolean onFailedToRecycleView(ListBaseViewHolder listBaseViewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[555] = true;
        } else {
            $jacocoInit[556] = true;
            String str = this.TAG;
            WXLogUtils.d(str, "Failed to recycle " + listBaseViewHolder);
            $jacocoInit[557] = true;
        }
        $jacocoInit[558] = true;
        return false;
    }

    public long getItemId(int i) {
        long j;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            j = Long.parseLong(getChild(i).getRef());
            $jacocoInit[559] = true;
        } catch (RuntimeException e) {
            $jacocoInit[560] = true;
            WXLogUtils.e(this.TAG, WXLogUtils.getStackTrace(e));
            j = -1;
            $jacocoInit[561] = true;
        }
        $jacocoInit[562] = true;
        return j;
    }

    public void onLoadMore(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String loadMoreOffset = getAttrs().getLoadMoreOffset();
            $jacocoInit[563] = true;
            if (!TextUtils.isEmpty(loadMoreOffset)) {
                $jacocoInit[564] = true;
            } else {
                loadMoreOffset = "0";
                $jacocoInit[565] = true;
            }
            if (((float) i) >= WXViewUtils.getRealPxByWidth((float) Integer.parseInt(loadMoreOffset), getInstance().getInstanceViewPortWidth())) {
                $jacocoInit[566] = true;
            } else {
                $jacocoInit[567] = true;
                if (this.mListCellCount != this.mChildren.size()) {
                    $jacocoInit[568] = true;
                } else if (!this.mForceLoadmoreNextTime) {
                    $jacocoInit[569] = true;
                } else {
                    $jacocoInit[570] = true;
                }
                fireEvent(Constants.Event.LOADMORE);
                $jacocoInit[571] = true;
                this.mListCellCount = this.mChildren.size();
                this.mForceLoadmoreNextTime = false;
                $jacocoInit[572] = true;
            }
            $jacocoInit[573] = true;
        } catch (Exception e) {
            $jacocoInit[574] = true;
            WXLogUtils.d(this.TAG + "onLoadMore :", (Throwable) e);
            $jacocoInit[575] = true;
        }
        $jacocoInit[576] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyAppearStateChange(int r7, int r8, int r9, int r10) {
        /*
            r6 = this;
            boolean[] r7 = $jacocoInit()
            java.lang.Runnable r8 = r6.mAppearChangeRunnable
            r0 = 0
            r1 = 1
            if (r8 != 0) goto L_0x000f
            r8 = 577(0x241, float:8.09E-43)
            r7[r8] = r1
            goto L_0x0024
        L_0x000f:
            r8 = 578(0x242, float:8.1E-43)
            r7[r8] = r1
            android.view.View r8 = r6.getHostView()
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            java.lang.Runnable r2 = r6.mAppearChangeRunnable
            r8.removeCallbacks(r2)
            r6.mAppearChangeRunnable = r0
            r8 = 579(0x243, float:8.11E-43)
            r7[r8] = r1
        L_0x0024:
            java.util.Map<java.lang.String, com.taobao.weex.ui.component.AppearanceHelper> r8 = r6.mAppearComponents
            java.util.Collection r8 = r8.values()
            java.util.Iterator r8 = r8.iterator()
            if (r10 <= 0) goto L_0x0037
            java.lang.String r0 = "up"
            r10 = 580(0x244, float:8.13E-43)
            r7[r10] = r1
            goto L_0x0044
        L_0x0037:
            if (r10 >= 0) goto L_0x0040
            java.lang.String r0 = "down"
            r10 = 581(0x245, float:8.14E-43)
            r7[r10] = r1
            goto L_0x0044
        L_0x0040:
            r10 = 582(0x246, float:8.16E-43)
            r7[r10] = r1
        L_0x0044:
            r10 = 583(0x247, float:8.17E-43)
            r7[r10] = r1
            int r10 = r6.getOrientation()
            if (r10 == 0) goto L_0x0053
            r9 = 584(0x248, float:8.18E-43)
            r7[r9] = r1
            goto L_0x006f
        L_0x0053:
            if (r9 != 0) goto L_0x005a
            r9 = 585(0x249, float:8.2E-43)
            r7[r9] = r1
            goto L_0x006f
        L_0x005a:
            if (r9 <= 0) goto L_0x0064
            java.lang.String r9 = "left"
            r10 = 586(0x24a, float:8.21E-43)
            r7[r10] = r1
        L_0x0062:
            r0 = r9
            goto L_0x006b
        L_0x0064:
            java.lang.String r9 = "right"
            r10 = 587(0x24b, float:8.23E-43)
            r7[r10] = r1
            goto L_0x0062
        L_0x006b:
            r9 = 588(0x24c, float:8.24E-43)
            r7[r9] = r1
        L_0x006f:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x012e
            r9 = 589(0x24d, float:8.25E-43)
            r7[r9] = r1
            java.lang.Object r9 = r8.next()
            com.taobao.weex.ui.component.AppearanceHelper r9 = (com.taobao.weex.ui.component.AppearanceHelper) r9
            r10 = 590(0x24e, float:8.27E-43)
            r7[r10] = r1
            com.taobao.weex.ui.component.WXComponent r10 = r9.getAwareChild()
            r2 = 591(0x24f, float:8.28E-43)
            r7[r2] = r1
            boolean r2 = r9.isWatch()
            if (r2 != 0) goto L_0x0096
            r9 = 592(0x250, float:8.3E-43)
            r7[r9] = r1
            goto L_0x006f
        L_0x0096:
            android.view.View r2 = r10.getHostView()
            if (r2 != 0) goto L_0x00a1
            r9 = 593(0x251, float:8.31E-43)
            r7[r9] = r1
            goto L_0x006f
        L_0x00a1:
            boolean r2 = android.support.v4.view.ViewCompat.isAttachedToWindow(r2)
            r3 = 0
            if (r2 != 0) goto L_0x00ae
            r2 = 594(0x252, float:8.32E-43)
            r7[r2] = r1
            r2 = 1
            goto L_0x00b3
        L_0x00ae:
            r2 = 595(0x253, float:8.34E-43)
            r7[r2] = r1
            r2 = 0
        L_0x00b3:
            r4 = 596(0x254, float:8.35E-43)
            r7[r4] = r1
            if (r2 == 0) goto L_0x00be
            r2 = 597(0x255, float:8.37E-43)
            r7[r2] = r1
            goto L_0x00c8
        L_0x00be:
            boolean r2 = r9.isViewVisible((boolean) r1)
            if (r2 != 0) goto L_0x00cd
            r2 = 598(0x256, float:8.38E-43)
            r7[r2] = r1
        L_0x00c8:
            r2 = 600(0x258, float:8.41E-43)
            r7[r2] = r1
            goto L_0x00d2
        L_0x00cd:
            r2 = 599(0x257, float:8.4E-43)
            r7[r2] = r1
            r3 = 1
        L_0x00d2:
            r2 = 601(0x259, float:8.42E-43)
            r7[r2] = r1
            int r2 = r9.setAppearStatus(r3)
            if (r2 != 0) goto L_0x00e1
            r9 = 602(0x25a, float:8.44E-43)
            r7[r9] = r1
            goto L_0x006f
        L_0x00e1:
            boolean r3 = com.taobao.weex.WXEnvironment.isApkDebugable()
            if (r3 != 0) goto L_0x00ec
            r9 = 603(0x25b, float:8.45E-43)
            r7[r9] = r1
            goto L_0x0116
        L_0x00ec:
            r3 = 604(0x25c, float:8.46E-43)
            r7[r3] = r1
            java.lang.String r3 = "appear"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "item "
            r4.append(r5)
            int r9 = r9.getCellPositionINScollable()
            r4.append(r9)
            java.lang.String r9 = " result "
            r4.append(r9)
            r4.append(r2)
            java.lang.String r9 = r4.toString()
            com.taobao.weex.utils.WXLogUtils.d((java.lang.String) r3, (java.lang.String) r9)
            r9 = 605(0x25d, float:8.48E-43)
            r7[r9] = r1
        L_0x0116:
            if (r2 != r1) goto L_0x011f
            java.lang.String r9 = "appear"
            r2 = 606(0x25e, float:8.49E-43)
            r7[r2] = r1
            goto L_0x0125
        L_0x011f:
            java.lang.String r9 = "disappear"
            r2 = 607(0x25f, float:8.5E-43)
            r7[r2] = r1
        L_0x0125:
            r10.notifyAppearStateChange(r9, r0)
            r9 = 608(0x260, float:8.52E-43)
            r7[r9] = r1
            goto L_0x006f
        L_0x012e:
            r8 = 609(0x261, float:8.53E-43)
            r7[r8] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.BasicListComponent.notifyAppearStateChange(int, int, int, int):void");
    }

    @NonNull
    private ListBaseViewHolder createVHForFakeComponent(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = new FrameLayout(getContext());
        $jacocoInit[610] = true;
        frameLayout.setBackgroundColor(-1);
        $jacocoInit[611] = true;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
        $jacocoInit[612] = true;
        ListBaseViewHolder listBaseViewHolder = new ListBaseViewHolder((View) frameLayout, i);
        $jacocoInit[613] = true;
        return listBaseViewHolder;
    }

    private ListBaseViewHolder createVHForRefreshComponent(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = new FrameLayout(getContext());
        $jacocoInit[614] = true;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
        $jacocoInit[615] = true;
        ListBaseViewHolder listBaseViewHolder = new ListBaseViewHolder((View) frameLayout, i);
        $jacocoInit[616] = true;
        return listBaseViewHolder;
    }

    @JSMethod
    public void resetLoadmore() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mForceLoadmoreNextTime = true;
        this.mListCellCount = 0;
        $jacocoInit[617] = true;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[618] = true;
        if (!ScrollStartEndHelper.isScrollEvent(str)) {
            $jacocoInit[619] = true;
        } else {
            $jacocoInit[620] = true;
            if (getHostView() == null) {
                $jacocoInit[621] = true;
            } else {
                $jacocoInit[622] = true;
                if (((ListComponentView) ((ViewGroup) getHostView())).getInnerView() == null) {
                    $jacocoInit[623] = true;
                } else if (this.mHasAddScrollEvent) {
                    $jacocoInit[624] = true;
                } else {
                    this.mHasAddScrollEvent = true;
                    $jacocoInit[625] = true;
                    WXRecyclerView innerView = ((ListComponentView) ((ViewGroup) getHostView())).getInnerView();
                    $jacocoInit[626] = true;
                    innerView.addOnScrollListener(new RecyclerView.OnScrollListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        private boolean mFirstEvent = true;
                        private int offsetXCorrection;
                        private int offsetYCorrection;
                        final /* synthetic */ BasicListComponent this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(4249453384098283663L, "com/taobao/weex/ui/component/list/BasicListComponent$7", 16);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        /* JADX WARNING: Removed duplicated region for block: B:11:0x0055  */
                        /* JADX WARNING: Removed duplicated region for block: B:9:0x0050  */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void onScrolled(android.support.v7.widget.RecyclerView r6, int r7, int r8) {
                            /*
                                r5 = this;
                                boolean[] r0 = $jacocoInit()
                                super.onScrolled(r6, r7, r8)
                                r1 = 1
                                r0[r1] = r1
                                int r2 = r6.computeHorizontalScrollOffset()
                                r3 = 2
                                r0[r3] = r1
                                int r3 = r6.computeVerticalScrollOffset()
                                r4 = 0
                                if (r7 == 0) goto L_0x001c
                                r7 = 3
                                r0[r7] = r1
                                goto L_0x0021
                            L_0x001c:
                                if (r8 == 0) goto L_0x002d
                                r7 = 4
                                r0[r7] = r1
                            L_0x0021:
                                int r7 = r5.offsetXCorrection
                                int r7 = r2 - r7
                                int r8 = r5.offsetYCorrection
                                int r8 = r3 - r8
                                r2 = 6
                                r0[r2] = r1
                                goto L_0x0036
                            L_0x002d:
                                r5.offsetXCorrection = r2
                                r5.offsetYCorrection = r3
                                r7 = 5
                                r0[r7] = r1
                                r7 = 0
                                r8 = 0
                            L_0x0036:
                                com.taobao.weex.ui.component.list.BasicListComponent r2 = r5.this$0
                                com.taobao.weex.ui.component.helper.ScrollStartEndHelper r2 = r2.getScrollStartEndHelper()
                                r2.onScrolled(r7, r8)
                                r2 = 7
                                r0[r2] = r1
                                com.taobao.weex.ui.component.list.BasicListComponent r2 = r5.this$0
                                com.taobao.weex.dom.WXEvent r2 = r2.getEvents()
                                java.lang.String r3 = "scroll"
                                boolean r2 = r2.contains(r3)
                                if (r2 != 0) goto L_0x0055
                                r6 = 8
                                r0[r6] = r1
                                return
                            L_0x0055:
                                boolean r2 = r5.mFirstEvent
                                if (r2 == 0) goto L_0x0060
                                r5.mFirstEvent = r4
                                r6 = 9
                                r0[r6] = r1
                                return
                            L_0x0060:
                                android.support.v7.widget.RecyclerView$LayoutManager r2 = r6.getLayoutManager()
                                r3 = 10
                                r0[r3] = r1
                                boolean r2 = r2.canScrollVertically()
                                if (r2 != 0) goto L_0x0073
                                r6 = 11
                                r0[r6] = r1
                                return
                            L_0x0073:
                                com.taobao.weex.ui.component.list.BasicListComponent r2 = r5.this$0
                                boolean r2 = com.taobao.weex.ui.component.list.BasicListComponent.access$500(r2, r7, r8)
                                if (r2 != 0) goto L_0x0080
                                r6 = 12
                                r0[r6] = r1
                                goto L_0x008d
                            L_0x0080:
                                r2 = 13
                                r0[r2] = r1
                                com.taobao.weex.ui.component.list.BasicListComponent r2 = r5.this$0
                                com.taobao.weex.ui.component.list.BasicListComponent.access$600(r2, r6, r7, r8)
                                r6 = 14
                                r0[r6] = r1
                            L_0x008d:
                                r6 = 15
                                r0[r6] = r1
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.BasicListComponent.AnonymousClass7.onScrolled(android.support.v7.widget.RecyclerView, int, int):void");
                        }
                    });
                    $jacocoInit[627] = true;
                }
            }
        }
        $jacocoInit[628] = true;
    }

    private void fireScrollEvent(RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent("scroll", getScrollEvent(recyclerView, i, i2));
        $jacocoInit[629] = true;
    }

    public Map<String, Object> getScrollEvent(RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getOrientation() != 1) {
            $jacocoInit[630] = true;
        } else {
            $jacocoInit[631] = true;
            i2 = -calcContentOffset(recyclerView);
            $jacocoInit[632] = true;
        }
        int measuredWidth = recyclerView.getMeasuredWidth() + recyclerView.computeHorizontalScrollRange();
        $jacocoInit[633] = true;
        $jacocoInit[634] = true;
        int i3 = 0;
        int i4 = 0;
        while (i3 < getChildCount()) {
            $jacocoInit[635] = true;
            WXComponent child = getChild(i3);
            if (child == null) {
                $jacocoInit[636] = true;
            } else {
                $jacocoInit[637] = true;
                i4 = (int) (((float) i4) + child.getLayoutHeight());
                $jacocoInit[638] = true;
            }
            i3++;
            $jacocoInit[639] = true;
        }
        HashMap hashMap = new HashMap(2);
        $jacocoInit[640] = true;
        HashMap hashMap2 = new HashMap(2);
        $jacocoInit[641] = true;
        HashMap hashMap3 = new HashMap(2);
        $jacocoInit[642] = true;
        hashMap2.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth((float) measuredWidth, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[643] = true;
        hashMap2.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth((float) i4, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[644] = true;
        hashMap3.put("x", Float.valueOf(-WXViewUtils.getWebPxByWidth((float) i, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[645] = true;
        hashMap3.put(Constants.Name.Y, Float.valueOf(-WXViewUtils.getWebPxByWidth((float) i2, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[646] = true;
        hashMap.put(Constants.Name.CONTENT_SIZE, hashMap2);
        $jacocoInit[647] = true;
        hashMap.put(Constants.Name.CONTENT_OFFSET, hashMap3);
        $jacocoInit[648] = true;
        return hashMap;
    }

    private boolean shouldReport(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastReport.x != -1) {
            $jacocoInit[649] = true;
        } else if (this.mLastReport.y != -1) {
            $jacocoInit[650] = true;
        } else {
            this.mLastReport.x = i;
            this.mLastReport.y = i2;
            $jacocoInit[651] = true;
            return true;
        }
        int abs = Math.abs(this.mLastReport.x - i);
        $jacocoInit[652] = true;
        int abs2 = Math.abs(this.mLastReport.y - i2);
        if (abs >= this.mOffsetAccuracy) {
            $jacocoInit[653] = true;
        } else if (abs2 >= this.mOffsetAccuracy) {
            $jacocoInit[654] = true;
        } else {
            $jacocoInit[656] = true;
            return false;
        }
        this.mLastReport.x = i;
        this.mLastReport.y = i2;
        $jacocoInit[655] = true;
        return true;
    }

    public int calcContentOffset(RecyclerView recyclerView) {
        int i;
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i3 = 0;
        if (layoutManager instanceof LinearLayoutManager) {
            $jacocoInit[657] = true;
            int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition == -1) {
                $jacocoInit[658] = true;
                return 0;
            }
            View findViewByPosition = layoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition == null) {
                $jacocoInit[659] = true;
                i2 = 0;
            } else {
                $jacocoInit[660] = true;
                i2 = findViewByPosition.getTop();
                $jacocoInit[661] = true;
            }
            $jacocoInit[662] = true;
            int i4 = 0;
            while (i3 < findFirstVisibleItemPosition) {
                $jacocoInit[663] = true;
                WXComponent child = getChild(i3);
                if (child == null) {
                    $jacocoInit[664] = true;
                } else {
                    $jacocoInit[665] = true;
                    i4 = (int) (((float) i4) - child.getLayoutHeight());
                    $jacocoInit[666] = true;
                }
                i3++;
                $jacocoInit[667] = true;
            }
            if (!(layoutManager instanceof GridLayoutManager)) {
                $jacocoInit[668] = true;
            } else {
                $jacocoInit[669] = true;
                i4 /= ((GridLayoutManager) layoutManager).getSpanCount();
                $jacocoInit[670] = true;
            }
            int i5 = i4 + i2;
            $jacocoInit[671] = true;
            return i5;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            $jacocoInit[672] = true;
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int spanCount = staggeredGridLayoutManager.getSpanCount();
            $jacocoInit[673] = true;
            int i6 = staggeredGridLayoutManager.findFirstVisibleItemPositions((int[]) null)[0];
            if (i6 == -1) {
                $jacocoInit[674] = true;
                return 0;
            }
            View findViewByPosition2 = layoutManager.findViewByPosition(i6);
            if (findViewByPosition2 == null) {
                $jacocoInit[675] = true;
                i = 0;
            } else {
                $jacocoInit[676] = true;
                i = findViewByPosition2.getTop();
                $jacocoInit[677] = true;
            }
            $jacocoInit[678] = true;
            int i7 = 0;
            while (i3 < i6) {
                $jacocoInit[679] = true;
                WXComponent child2 = getChild(i3);
                if (child2 == null) {
                    $jacocoInit[680] = true;
                } else {
                    $jacocoInit[681] = true;
                    i7 = (int) (((float) i7) - child2.getLayoutHeight());
                    $jacocoInit[682] = true;
                }
                i3++;
                $jacocoInit[683] = true;
            }
            int i8 = (i7 / spanCount) + i;
            $jacocoInit[684] = true;
            return i8;
        } else {
            $jacocoInit[685] = true;
            return -1;
        }
    }

    public ScrollStartEndHelper getScrollStartEndHelper() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollStartEndHelper != null) {
            $jacocoInit[686] = true;
        } else {
            $jacocoInit[687] = true;
            this.mScrollStartEndHelper = new ScrollStartEndHelper(this);
            $jacocoInit[688] = true;
        }
        ScrollStartEndHelper scrollStartEndHelper = this.mScrollStartEndHelper;
        $jacocoInit[689] = true;
        return scrollStartEndHelper;
    }
}
