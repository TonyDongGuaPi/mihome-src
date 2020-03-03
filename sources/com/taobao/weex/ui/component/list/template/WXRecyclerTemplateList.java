package com.taobao.weex.ui.component.list.template;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.ICheckBindingScroller;
import com.taobao.weex.common.OnWXScrollListener;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.el.parse.ArrayStack;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.AppearanceHelper;
import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXBaseRefresh;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXLoading;
import com.taobao.weex.ui.component.WXRefresh;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.binding.Layouts;
import com.taobao.weex.ui.component.binding.Statements;
import com.taobao.weex.ui.component.helper.ScrollStartEndHelper;
import com.taobao.weex.ui.component.list.RecyclerTransform;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.listview.adapter.IOnLoadMoreListener;
import com.taobao.weex.ui.view.listview.adapter.IRecyclerAdapterListener;
import com.taobao.weex.ui.view.listview.adapter.RecyclerViewBaseAdapter;
import com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXRecyclerTemplateList extends WXVContainer<BounceRecyclerView> implements Scrollable, IOnLoadMoreListener, IRecyclerAdapterListener<TemplateViewHolder> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final long APPEAR_CHANGE_RUNNABLE_DELAY = 50;
    private static final String EMPTY_HOLDER_TEMPLATE_KEY = "";
    public static final boolean ENABLE_TRACE_LOG = false;
    private static final String NAME_HAS_FIXED_SIZE = "hasFixedSize";
    private static final String NAME_ITEM_VIEW_CACHE_SIZE = "itemViewCacheSize";
    private static final String NAME_TEMPLATE_CACHE_SIZE = "templateCacheSize";
    public static final String TAG = "WXRecyclerTemplateList";
    private CellDataManager cellDataManager;
    private CellRenderContext cellRenderContext;
    private WXCell defaultTemplateCell;
    private String defaultTemplateKey;
    private boolean hasAppendTreeDone;
    private boolean hasLayoutDone;
    private boolean isScrollable;
    private String listDataIndexKey;
    private String listDataItemKey;
    private String listDataKey;
    private String listDataTemplateKey;
    private Runnable listUpdateRunnable;
    private Runnable mAppearChangeRunnable;
    private ArrayMap<Integer, List<AppearanceHelper>> mAppearHelpers;
    protected int mColumnCount = 1;
    protected float mColumnGap = 0.0f;
    protected float mColumnWidth = 0.0f;
    private ArrayMap<Integer, Map<String, Map<Integer, List<Object>>>> mDisAppearWatchList;
    private boolean mForceLoadmoreNextTime;
    private boolean mHasAddScrollEvent;
    private RecyclerView.ItemAnimator mItemAnimator;
    private Point mLastReport;
    protected int mLayoutType = 1;
    private int mListCellCount;
    private int mOffsetAccuracy;
    private float mPaddingLeft;
    private float mPaddingRight;
    private ScrollStartEndHelper mScrollStartEndHelper;
    private TemplateStickyHelper mStickyHelper;
    private Map<String, WXCell> mTemplateSources;
    private ArrayMap<String, Integer> mTemplateViewTypes;
    private ConcurrentHashMap<String, TemplateCache> mTemplatesCache;
    private WXRecyclerViewOnScrollListener mViewOnScrollListener;
    private int orientation;
    private int templateCacheSize;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7193824982255423811L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList", 976);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        onBindViewHolder((TemplateViewHolder) viewHolder, i);
        $jacocoInit[967] = true;
    }

    public /* synthetic */ boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onFailedToRecycleView = onFailedToRecycleView((TemplateViewHolder) viewHolder);
        $jacocoInit[965] = true;
        return onFailedToRecycleView;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onHostViewInitialized(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        onHostViewInitialized((BounceRecyclerView) view);
        $jacocoInit[962] = true;
    }

    public /* synthetic */ void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        onViewRecycled((TemplateViewHolder) viewHolder);
        $jacocoInit[968] = true;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void setHostLayoutParams(View view, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        setHostLayoutParams((BounceRecyclerView) view, i, i2, i3, i4, i5, i6);
        $jacocoInit[964] = true;
    }

    static /* synthetic */ WXRecyclerViewOnScrollListener access$000(WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerViewOnScrollListener wXRecyclerViewOnScrollListener = wXRecyclerTemplateList.mViewOnScrollListener;
        $jacocoInit[969] = true;
        return wXRecyclerViewOnScrollListener;
    }

    static /* synthetic */ TemplateStickyHelper access$100(WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        TemplateStickyHelper templateStickyHelper = wXRecyclerTemplateList.mStickyHelper;
        $jacocoInit[970] = true;
        return templateStickyHelper;
    }

    static /* synthetic */ CellDataManager access$200(WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        CellDataManager cellDataManager2 = wXRecyclerTemplateList.cellDataManager;
        $jacocoInit[971] = true;
        return cellDataManager2;
    }

    static /* synthetic */ Runnable access$300(WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        Runnable runnable = wXRecyclerTemplateList.mAppearChangeRunnable;
        $jacocoInit[972] = true;
        return runnable;
    }

    static /* synthetic */ void access$400(WXRecyclerTemplateList wXRecyclerTemplateList, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXRecyclerTemplateList.checkAppendDone(z);
        $jacocoInit[973] = true;
    }

    static /* synthetic */ boolean access$500(WXRecyclerTemplateList wXRecyclerTemplateList, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean shouldReport = wXRecyclerTemplateList.shouldReport(i, i2);
        $jacocoInit[974] = true;
        return shouldReport;
    }

    static /* synthetic */ void access$600(WXRecyclerTemplateList wXRecyclerTemplateList, RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXRecyclerTemplateList.fireScrollEvent(recyclerView, i, i2);
        $jacocoInit[975] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRecyclerTemplateList(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mViewOnScrollListener = new WXRecyclerViewOnScrollListener(this);
        this.mListCellCount = 0;
        this.mForceLoadmoreNextTime = false;
        this.orientation = 1;
        this.isScrollable = true;
        this.mOffsetAccuracy = 10;
        $jacocoInit[1] = true;
        this.mLastReport = new Point(-1, -1);
        this.mHasAddScrollEvent = false;
        this.listDataKey = Constants.Name.Recycler.LIST_DATA;
        this.listDataItemKey = null;
        this.listDataIndexKey = null;
        this.listDataTemplateKey = Constants.Name.Recycler.SLOT_TEMPLATE_CASE;
        this.templateCacheSize = 2;
        this.defaultTemplateKey = "@default_template_cell";
        $jacocoInit[2] = true;
        this.mAppearHelpers = new ArrayMap<>();
        $jacocoInit[3] = true;
        this.mDisAppearWatchList = new ArrayMap<>();
        $jacocoInit[4] = true;
        this.cellRenderContext = new CellRenderContext();
        this.mAppearChangeRunnable = null;
        this.hasAppendTreeDone = false;
        this.hasLayoutDone = false;
        $jacocoInit[5] = true;
        initRecyclerTemplateList(wXSDKInstance, basicComponentData, wXVContainer);
        $jacocoInit[6] = true;
    }

    private void initRecyclerTemplateList(WXSDKInstance wXSDKInstance, BasicComponentData basicComponentData, WXVContainer wXVContainer) {
        boolean[] $jacocoInit = $jacocoInit();
        updateRecyclerAttr();
        $jacocoInit[7] = true;
        this.mTemplateViewTypes = new ArrayMap<>();
        $jacocoInit[8] = true;
        this.mTemplateViewTypes.put("", 0);
        $jacocoInit[9] = true;
        this.mTemplateSources = new HashMap();
        $jacocoInit[10] = true;
        this.mTemplatesCache = new ConcurrentHashMap<>();
        $jacocoInit[11] = true;
        this.mStickyHelper = new TemplateStickyHelper(this);
        $jacocoInit[12] = true;
        this.orientation = basicComponentData.getAttrs().getOrientation();
        $jacocoInit[13] = true;
        this.listDataTemplateKey = WXUtils.getString(getAttrs().get("switch"), Constants.Name.Recycler.SLOT_TEMPLATE_CASE);
        $jacocoInit[14] = true;
        this.listDataItemKey = WXUtils.getString(getAttrs().get("alias"), this.listDataItemKey);
        $jacocoInit[15] = true;
        this.listDataIndexKey = WXUtils.getString(getAttrs().get("index"), this.listDataIndexKey);
        $jacocoInit[16] = true;
        this.cellDataManager = new CellDataManager(this);
        $jacocoInit[17] = true;
        this.cellDataManager.listData = parseListDataToJSONArray(getAttrs().get(Constants.Name.Recycler.LIST_DATA));
        $jacocoInit[18] = true;
    }

    /* access modifiers changed from: protected */
    public BounceRecyclerView initComponentHostView(@NonNull Context context) {
        int i;
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        BounceRecyclerView bounceRecyclerView = new BounceRecyclerView(context, this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
        $jacocoInit[19] = true;
        WXAttr attrs = getAttrs();
        $jacocoInit[20] = true;
        String str = (String) attrs.get("transform");
        if (str == null) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            ((WXRecyclerView) bounceRecyclerView.getInnerView()).addItemDecoration(RecyclerTransform.parseTransforms(getOrientation(), str));
            $jacocoInit[23] = true;
        }
        this.mItemAnimator = ((WXRecyclerView) bounceRecyclerView.getInnerView()).getItemAnimator();
        $jacocoInit[24] = true;
        if (attrs.get(NAME_TEMPLATE_CACHE_SIZE) == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            this.templateCacheSize = WXUtils.getInteger(attrs.get(NAME_TEMPLATE_CACHE_SIZE), Integer.valueOf(this.templateCacheSize)).intValue();
            $jacocoInit[27] = true;
        }
        $jacocoInit[28] = true;
        if (attrs.get(NAME_ITEM_VIEW_CACHE_SIZE) == null) {
            $jacocoInit[29] = true;
            i = 2;
        } else {
            $jacocoInit[30] = true;
            i = WXUtils.getNumberInt(getAttrs().get(NAME_ITEM_VIEW_CACHE_SIZE), 2);
            $jacocoInit[31] = true;
        }
        if (attrs.get("hasFixedSize") == null) {
            $jacocoInit[32] = true;
            z = false;
        } else {
            $jacocoInit[33] = true;
            z = WXUtils.getBoolean(attrs.get("hasFixedSize"), false).booleanValue();
            $jacocoInit[34] = true;
        }
        RecyclerViewBaseAdapter recyclerViewBaseAdapter = new RecyclerViewBaseAdapter(this);
        $jacocoInit[35] = true;
        recyclerViewBaseAdapter.setHasStableIds(true);
        $jacocoInit[36] = true;
        ((WXRecyclerView) bounceRecyclerView.getInnerView()).setItemAnimator((RecyclerView.ItemAnimator) null);
        if (i == 2) {
            $jacocoInit[37] = true;
        } else {
            $jacocoInit[38] = true;
            ((WXRecyclerView) bounceRecyclerView.getInnerView()).setItemViewCacheSize(i);
            $jacocoInit[39] = true;
        }
        if (bounceRecyclerView.getSwipeLayout() == null) {
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            if (!WXUtils.getBoolean(getAttrs().get(Constants.Name.NEST_SCROLLING_ENABLED), false).booleanValue()) {
                $jacocoInit[42] = true;
            } else {
                $jacocoInit[43] = true;
                bounceRecyclerView.getSwipeLayout().setNestedScrollingEnabled(true);
                $jacocoInit[44] = true;
            }
        }
        ((WXRecyclerView) bounceRecyclerView.getInnerView()).setHasFixedSize(z);
        $jacocoInit[45] = true;
        bounceRecyclerView.setRecyclerViewBaseAdapter(recyclerViewBaseAdapter);
        $jacocoInit[46] = true;
        bounceRecyclerView.setOverScrollMode(2);
        $jacocoInit[47] = true;
        ((WXRecyclerView) bounceRecyclerView.getInnerView()).clearOnScrollListeners();
        $jacocoInit[48] = true;
        ((WXRecyclerView) bounceRecyclerView.getInnerView()).addOnScrollListener(this.mViewOnScrollListener);
        $jacocoInit[49] = true;
        ((WXRecyclerView) bounceRecyclerView.getInnerView()).addOnScrollListener(new RecyclerView.OnScrollListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXRecyclerTemplateList this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6998384678564918207L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$1", 33);
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
                } else if (wXScrollListeners.size() <= 0) {
                    $jacocoInit[4] = true;
                } else {
                    $jacocoInit[5] = true;
                    $jacocoInit[6] = true;
                    for (OnWXScrollListener next : wXScrollListeners) {
                        if (next == null) {
                            $jacocoInit[8] = true;
                        } else {
                            $jacocoInit[9] = true;
                            View childAt = recyclerView.getChildAt(0);
                            if (childAt == null) {
                                $jacocoInit[10] = true;
                            } else {
                                $jacocoInit[11] = true;
                                int top = childAt.getTop();
                                $jacocoInit[12] = true;
                                next.onScrollStateChanged(recyclerView, 0, top, i);
                                $jacocoInit[13] = true;
                            }
                        }
                        $jacocoInit[14] = true;
                    }
                    $jacocoInit[7] = true;
                }
                $jacocoInit[15] = true;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onScrolled(recyclerView, i, i2);
                $jacocoInit[16] = true;
                List<OnWXScrollListener> wXScrollListeners = this.this$0.getInstance().getWXScrollListeners();
                $jacocoInit[17] = true;
                if (wXScrollListeners == null) {
                    $jacocoInit[18] = true;
                } else if (wXScrollListeners.size() <= 0) {
                    $jacocoInit[19] = true;
                } else {
                    try {
                        $jacocoInit[20] = true;
                        $jacocoInit[21] = true;
                        for (OnWXScrollListener next : wXScrollListeners) {
                            if (next == null) {
                                $jacocoInit[22] = true;
                            } else if (next instanceof ICheckBindingScroller) {
                                $jacocoInit[23] = true;
                                if (!((ICheckBindingScroller) next).isNeedScroller(this.this$0.getRef(), (Object) null)) {
                                    $jacocoInit[24] = true;
                                } else {
                                    $jacocoInit[25] = true;
                                    next.onScrolled(recyclerView, i, i2);
                                    $jacocoInit[26] = true;
                                }
                            } else {
                                next.onScrolled(recyclerView, i, i2);
                                $jacocoInit[27] = true;
                            }
                            $jacocoInit[28] = true;
                        }
                        $jacocoInit[29] = true;
                    } catch (Exception e) {
                        $jacocoInit[30] = true;
                        e.printStackTrace();
                        $jacocoInit[31] = true;
                    }
                }
                $jacocoInit[32] = true;
            }
        });
        $jacocoInit[50] = true;
        bounceRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXRecyclerTemplateList this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-71235819879430615L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$2", 6);
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
                BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) this.this$0.getHostView();
                if (bounceRecyclerView == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                WXRecyclerTemplateList.access$000(this.this$0).onScrolled((RecyclerView) bounceRecyclerView.getInnerView(), 0, 0);
                if (Build.VERSION.SDK_INT >= 16) {
                    $jacocoInit[2] = true;
                    bounceRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    $jacocoInit[3] = true;
                } else {
                    bounceRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[51] = true;
        this.listUpdateRunnable = new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXRecyclerTemplateList this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(3846755481087802922L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$3", 23);
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
                if (WXRecyclerTemplateList.access$100(this.this$0) == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    if (WXRecyclerTemplateList.access$100(this.this$0).getStickyTypes().size() <= 0) {
                        $jacocoInit[3] = true;
                    } else {
                        $jacocoInit[4] = true;
                        WXRecyclerTemplateList.access$100(this.this$0).getStickyPositions().clear();
                        $jacocoInit[5] = true;
                        if (WXRecyclerTemplateList.access$200(this.this$0).listData == null) {
                            $jacocoInit[6] = true;
                        } else {
                            $jacocoInit[7] = true;
                            int i = 0;
                            $jacocoInit[8] = true;
                            while (i < WXRecyclerTemplateList.access$200(this.this$0).listData.size()) {
                                $jacocoInit[10] = true;
                                WXCell sourceTemplate = this.this$0.getSourceTemplate(i);
                                if (sourceTemplate == null) {
                                    $jacocoInit[11] = true;
                                } else if (!sourceTemplate.isSticky()) {
                                    $jacocoInit[12] = true;
                                } else {
                                    $jacocoInit[13] = true;
                                    WXRecyclerTemplateList.access$100(this.this$0).getStickyPositions().add(Integer.valueOf(i));
                                    $jacocoInit[14] = true;
                                }
                                i++;
                                $jacocoInit[15] = true;
                            }
                            $jacocoInit[9] = true;
                        }
                    }
                }
                if (this.this$0.getHostView() == null) {
                    $jacocoInit[16] = true;
                } else if (((BounceRecyclerView) this.this$0.getHostView()).getRecyclerViewBaseAdapter() == null) {
                    $jacocoInit[17] = true;
                } else {
                    $jacocoInit[18] = true;
                    ((BounceRecyclerView) this.this$0.getHostView()).getRecyclerViewBaseAdapter().notifyDataSetChanged();
                    $jacocoInit[19] = true;
                }
                if (!WXEnvironment.isOpenDebugLog()) {
                    $jacocoInit[20] = true;
                } else {
                    $jacocoInit[21] = true;
                }
                $jacocoInit[22] = true;
            }
        };
        $jacocoInit[52] = true;
        return bounceRecyclerView;
    }

    /* access modifiers changed from: protected */
    public void onHostViewInitialized(BounceRecyclerView bounceRecyclerView) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onHostViewInitialized(bounceRecyclerView);
        $jacocoInit[53] = true;
        WXRecyclerView wXRecyclerView = (WXRecyclerView) bounceRecyclerView.getInnerView();
        $jacocoInit[54] = true;
        if (wXRecyclerView == null) {
            $jacocoInit[55] = true;
        } else if (wXRecyclerView.getAdapter() == null) {
            $jacocoInit[56] = true;
        } else {
            $jacocoInit[58] = true;
            return;
        }
        WXLogUtils.e(TAG, "RecyclerView is not found or Adapter is not bound");
        $jacocoInit[57] = true;
    }

    /* access modifiers changed from: protected */
    public WXComponent.MeasureOutput measure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        int screenHeight = WXViewUtils.getScreenHeight(WXEnvironment.sApplication);
        $jacocoInit[59] = true;
        int weexHeight = WXViewUtils.getWeexHeight(getInstanceId());
        $jacocoInit[60] = true;
        if (weexHeight >= screenHeight) {
            $jacocoInit[61] = true;
        } else {
            $jacocoInit[62] = true;
            screenHeight = weexHeight;
        }
        if (i2 > screenHeight) {
            i2 = weexHeight - getAbsoluteY();
            $jacocoInit[63] = true;
        } else {
            $jacocoInit[64] = true;
        }
        $jacocoInit[65] = true;
        WXComponent.MeasureOutput measure = super.measure(i, i2);
        $jacocoInit[66] = true;
        return measure;
    }

    public void bindStickStyle(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent findParentType = findParentType(wXComponent, WXCell.class);
        if (findParentType == null) {
            $jacocoInit[67] = true;
        } else if (this.mStickyHelper == null) {
            $jacocoInit[68] = true;
        } else {
            if (this.mStickyHelper.getStickyTypes().contains(findParentType.getRef())) {
                $jacocoInit[69] = true;
            } else {
                $jacocoInit[70] = true;
                this.mStickyHelper.getStickyTypes().add(findParentType.getRef());
                $jacocoInit[71] = true;
                notifyUpdateList();
                $jacocoInit[72] = true;
            }
            $jacocoInit[73] = true;
        }
    }

    public void unbindStickStyle(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent findParentType = findParentType(wXComponent, WXCell.class);
        if (findParentType == null) {
            $jacocoInit[74] = true;
        } else if (this.mStickyHelper == null) {
            $jacocoInit[75] = true;
        } else {
            if (!this.mStickyHelper.getStickyTypes().contains(findParentType.getRef())) {
                $jacocoInit[77] = true;
            } else {
                $jacocoInit[78] = true;
                this.mStickyHelper.getStickyTypes().remove(findParentType.getRef());
                $jacocoInit[79] = true;
                notifyUpdateList();
                $jacocoInit[80] = true;
            }
            $jacocoInit[81] = true;
            return;
        }
        $jacocoInit[76] = true;
    }

    @Nullable
    private WXCell findCell(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent instanceof WXCell) {
            WXCell wXCell = (WXCell) wXComponent;
            $jacocoInit[82] = true;
            return wXCell;
        }
        if (wXComponent == null) {
            $jacocoInit[83] = true;
        } else {
            WXVContainer parent = wXComponent.getParent();
            if (parent == null) {
                $jacocoInit[84] = true;
            } else {
                WXCell findCell = findCell(parent);
                $jacocoInit[86] = true;
                return findCell;
            }
        }
        $jacocoInit[85] = true;
        return null;
    }

    private void setAppearanceWatch(WXComponent wXComponent, int i, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.cellDataManager.listData == null) {
            $jacocoInit[87] = true;
        } else if (this.mAppearHelpers == null) {
            $jacocoInit[88] = true;
        } else {
            $jacocoInit[89] = true;
            if (TextUtils.isEmpty(wXComponent.getRef())) {
                $jacocoInit[90] = true;
            } else {
                WXCell findCell = findCell(wXComponent);
                $jacocoInit[92] = true;
                int cellTemplateItemType = getCellTemplateItemType(findCell);
                if (cellTemplateItemType < 0) {
                    $jacocoInit[93] = true;
                    return;
                }
                List list = this.mAppearHelpers.get(Integer.valueOf(cellTemplateItemType));
                if (list != null) {
                    $jacocoInit[94] = true;
                } else {
                    $jacocoInit[95] = true;
                    list = new ArrayList();
                    $jacocoInit[96] = true;
                    this.mAppearHelpers.put(Integer.valueOf(cellTemplateItemType), list);
                    $jacocoInit[97] = true;
                }
                AppearanceHelper appearanceHelper = null;
                $jacocoInit[98] = true;
                Iterator it = list.iterator();
                $jacocoInit[99] = true;
                while (true) {
                    if (!it.hasNext()) {
                        $jacocoInit[100] = true;
                        break;
                    }
                    AppearanceHelper appearanceHelper2 = (AppearanceHelper) it.next();
                    $jacocoInit[101] = true;
                    if (wXComponent.getRef().equals(appearanceHelper2.getAwareChild().getRef())) {
                        $jacocoInit[102] = true;
                        appearanceHelper = appearanceHelper2;
                        break;
                    }
                    $jacocoInit[103] = true;
                }
                if (appearanceHelper != null) {
                    $jacocoInit[104] = true;
                    appearanceHelper.setWatchEvent(i, z);
                    $jacocoInit[105] = true;
                } else {
                    AppearanceHelper appearanceHelper3 = new AppearanceHelper(wXComponent, cellTemplateItemType);
                    $jacocoInit[106] = true;
                    appearanceHelper3.setWatchEvent(i, z);
                    $jacocoInit[107] = true;
                    list.add(appearanceHelper3);
                    $jacocoInit[108] = true;
                }
                $jacocoInit[109] = true;
                return;
            }
        }
        $jacocoInit[91] = true;
    }

    public void bindAppearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 0, true);
        if (this.mAppearChangeRunnable != null) {
            $jacocoInit[110] = true;
        } else {
            $jacocoInit[111] = true;
            this.mAppearChangeRunnable = new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXRecyclerTemplateList this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-1406761005027558481L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$4", 5);
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
                    if (WXRecyclerTemplateList.access$300(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        this.this$0.notifyAppearStateChange(0, 0, 0, 0);
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                }
            };
            $jacocoInit[112] = true;
        }
        if (getHostView() == null) {
            $jacocoInit[113] = true;
        } else {
            $jacocoInit[114] = true;
            ((BounceRecyclerView) getHostView()).removeCallbacks(this.mAppearChangeRunnable);
            $jacocoInit[115] = true;
            ((BounceRecyclerView) getHostView()).postDelayed(this.mAppearChangeRunnable, APPEAR_CHANGE_RUNNABLE_DELAY);
            $jacocoInit[116] = true;
        }
        $jacocoInit[117] = true;
    }

    public void bindDisappearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 1, true);
        if (this.mAppearChangeRunnable != null) {
            $jacocoInit[118] = true;
        } else {
            $jacocoInit[119] = true;
            this.mAppearChangeRunnable = new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXRecyclerTemplateList this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-758084289750878325L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$5", 5);
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
                    if (WXRecyclerTemplateList.access$300(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        this.this$0.notifyAppearStateChange(0, 0, 0, 0);
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                }
            };
            $jacocoInit[120] = true;
        }
        if (getHostView() == null) {
            $jacocoInit[121] = true;
        } else {
            $jacocoInit[122] = true;
            ((BounceRecyclerView) getHostView()).removeCallbacks(this.mAppearChangeRunnable);
            $jacocoInit[123] = true;
            ((BounceRecyclerView) getHostView()).postDelayed(this.mAppearChangeRunnable, APPEAR_CHANGE_RUNNABLE_DELAY);
            $jacocoInit[124] = true;
        }
        $jacocoInit[125] = true;
    }

    public void unbindAppearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 0, false);
        $jacocoInit[126] = true;
    }

    public void unbindDisappearEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setAppearanceWatch(wXComponent, 1, false);
        $jacocoInit[127] = true;
    }

    @JSMethod(uiThread = true)
    public void queryElement(String str, String str2, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String[] split = str.split("@");
            String str3 = split[0];
            $jacocoInit[128] = true;
            int parseInt = Integer.parseInt(split[1]);
            $jacocoInit[129] = true;
            WXComponent findVirtualComponentByVRef = TemplateDom.findVirtualComponentByVRef(getInstanceId(), str);
            if (findVirtualComponentByVRef != null) {
                $jacocoInit[130] = true;
                if (getHostView() == null) {
                    $jacocoInit[132] = true;
                } else if (((BounceRecyclerView) getHostView()).getInnerView() != null) {
                    $jacocoInit[133] = true;
                    ArrayList arrayList = new ArrayList(4);
                    $jacocoInit[136] = true;
                    Selector.queryElementAll(findVirtualComponentByVRef, str2, arrayList);
                    $jacocoInit[137] = true;
                    if (arrayList.size() > 0) {
                        $jacocoInit[138] = true;
                        jSCallback.invoke(TemplateDom.toMap(str3, parseInt, (WXComponent) arrayList.get(0)));
                        $jacocoInit[139] = true;
                    } else {
                        jSCallback.invoke(new HashMap(4));
                        $jacocoInit[140] = true;
                    }
                    $jacocoInit[141] = true;
                    $jacocoInit[145] = true;
                    return;
                } else {
                    $jacocoInit[134] = true;
                }
                $jacocoInit[135] = true;
                return;
            }
            $jacocoInit[131] = true;
        } catch (Exception e) {
            $jacocoInit[142] = true;
            jSCallback.invoke(new HashMap(4));
            $jacocoInit[143] = true;
            WXLogUtils.e(TAG, (Throwable) e);
            $jacocoInit[144] = true;
        }
    }

    @JSMethod(uiThread = true)
    public void queryElementAll(String str, String str2, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList();
        try {
            $jacocoInit[146] = true;
            String[] split = str.split("@");
            String str3 = split[0];
            $jacocoInit[147] = true;
            int parseInt = Integer.parseInt(split[1]);
            $jacocoInit[148] = true;
            WXComponent findVirtualComponentByVRef = TemplateDom.findVirtualComponentByVRef(getInstanceId(), str);
            if (findVirtualComponentByVRef != null) {
                $jacocoInit[149] = true;
                if (getHostView() == null) {
                    $jacocoInit[151] = true;
                } else if (((BounceRecyclerView) getHostView()).getInnerView() != null) {
                    $jacocoInit[152] = true;
                    ArrayList<WXComponent> arrayList2 = new ArrayList<>(4);
                    $jacocoInit[155] = true;
                    Selector.queryElementAll(findVirtualComponentByVRef, str2, arrayList2);
                    $jacocoInit[156] = true;
                    $jacocoInit[157] = true;
                    for (WXComponent map : arrayList2) {
                        $jacocoInit[158] = true;
                        arrayList.add(TemplateDom.toMap(str3, parseInt, map));
                        $jacocoInit[159] = true;
                    }
                    jSCallback.invoke(arrayList);
                    $jacocoInit[160] = true;
                    $jacocoInit[164] = true;
                    return;
                } else {
                    $jacocoInit[153] = true;
                }
                $jacocoInit[154] = true;
                return;
            }
            $jacocoInit[150] = true;
        } catch (Exception e) {
            $jacocoInit[161] = true;
            jSCallback.invoke(arrayList);
            $jacocoInit[162] = true;
            WXLogUtils.e(TAG, (Throwable) e);
            $jacocoInit[163] = true;
        }
    }

    @JSMethod(uiThread = true)
    public void closest(String str, String str2, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String[] split = str.split("@");
            String str3 = split[0];
            $jacocoInit[165] = true;
            int parseInt = Integer.parseInt(split[1]);
            $jacocoInit[166] = true;
            WXComponent findVirtualComponentByVRef = TemplateDom.findVirtualComponentByVRef(getInstanceId(), str);
            if (findVirtualComponentByVRef != null) {
                $jacocoInit[167] = true;
                if (getHostView() == null) {
                    $jacocoInit[169] = true;
                } else if (((BounceRecyclerView) getHostView()).getInnerView() != null) {
                    $jacocoInit[170] = true;
                    ArrayList arrayList = new ArrayList(4);
                    $jacocoInit[173] = true;
                    Selector.closest(findVirtualComponentByVRef, str2, arrayList);
                    $jacocoInit[174] = true;
                    if (arrayList.size() > 0) {
                        $jacocoInit[175] = true;
                        jSCallback.invoke(TemplateDom.toMap(str3, parseInt, (WXComponent) arrayList.get(0)));
                        $jacocoInit[176] = true;
                    } else {
                        jSCallback.invoke(new HashMap(4));
                        $jacocoInit[177] = true;
                    }
                    $jacocoInit[178] = true;
                    $jacocoInit[182] = true;
                    return;
                } else {
                    $jacocoInit[171] = true;
                }
                $jacocoInit[172] = true;
                return;
            }
            $jacocoInit[168] = true;
        } catch (Exception e) {
            $jacocoInit[179] = true;
            jSCallback.invoke(new HashMap(4));
            $jacocoInit[180] = true;
            WXLogUtils.e(TAG, (Throwable) e);
            $jacocoInit[181] = true;
        }
    }

    @JSMethod(uiThread = true)
    public void scrollToElement(String str, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        scrollTo(str, map);
        $jacocoInit[183] = true;
    }

    @JSMethod(uiThread = true)
    public void scrollTo(String str, Map<String, Object> map) {
        int i;
        boolean z;
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[184] = true;
            if (str.indexOf(64) > 0) {
                $jacocoInit[185] = true;
                String[] split = str.split("@");
                $jacocoInit[186] = true;
                i = Integer.parseInt(split[0]);
                $jacocoInit[187] = true;
            } else {
                i = (int) Float.parseFloat(str);
                $jacocoInit[188] = true;
            }
            if (i < 0) {
                $jacocoInit[189] = true;
            } else {
                float f = 0.0f;
                if (map == null) {
                    $jacocoInit[190] = true;
                    z = true;
                } else {
                    $jacocoInit[191] = true;
                    WXUtils.getBoolean(map.get(Constants.Name.ANIMATED), true).booleanValue();
                    $jacocoInit[192] = true;
                    if (map.get("offset") == null) {
                        str2 = "0";
                        $jacocoInit[193] = true;
                    } else {
                        str2 = map.get("offset").toString();
                        $jacocoInit[194] = true;
                    }
                    $jacocoInit[195] = true;
                    z = WXUtils.getBoolean(map.get(Constants.Name.ANIMATED), true).booleanValue();
                    if (str2 == null) {
                        $jacocoInit[196] = true;
                    } else {
                        try {
                            $jacocoInit[197] = true;
                            float realPxByWidth = WXViewUtils.getRealPxByWidth(Float.parseFloat(str2), getInstance().getInstanceViewPortWidth());
                            $jacocoInit[198] = true;
                            f = realPxByWidth;
                        } catch (Exception e) {
                            $jacocoInit[199] = true;
                            WXLogUtils.e("Float parseFloat error :" + e.getMessage());
                            $jacocoInit[200] = true;
                        }
                    }
                }
                int i2 = (int) f;
                $jacocoInit[201] = true;
                BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) getHostView();
                if (bounceRecyclerView != null) {
                    $jacocoInit[202] = true;
                    $jacocoInit[204] = true;
                    ((WXRecyclerView) bounceRecyclerView.getInnerView()).scrollTo(z, i, i2, getOrientation());
                    $jacocoInit[205] = true;
                } else {
                    $jacocoInit[203] = true;
                    return;
                }
            }
            $jacocoInit[206] = true;
        } catch (Exception e2) {
            $jacocoInit[207] = true;
            WXLogUtils.e(TAG, (Throwable) e2);
            $jacocoInit[208] = true;
        }
        $jacocoInit[209] = true;
    }

    public void scrollTo(WXComponent wXComponent, Map<String, Object> map) {
        boolean z;
        int i;
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        int i2 = -1;
        float f = 0.0f;
        if (map == null) {
            $jacocoInit[210] = true;
            i = -1;
            z = true;
        } else {
            $jacocoInit[211] = true;
            if (map.get("offset") == null) {
                str = "0";
                $jacocoInit[212] = true;
            } else {
                str = map.get("offset").toString();
                $jacocoInit[213] = true;
            }
            $jacocoInit[214] = true;
            z = WXUtils.getBoolean(map.get(Constants.Name.ANIMATED), true).booleanValue();
            if (str == null) {
                $jacocoInit[215] = true;
            } else {
                try {
                    $jacocoInit[216] = true;
                    float realPxByWidth = WXViewUtils.getRealPxByWidth(Float.parseFloat(str), getInstance().getInstanceViewPortWidth());
                    $jacocoInit[217] = true;
                    f = realPxByWidth;
                } catch (Exception e) {
                    $jacocoInit[218] = true;
                    WXLogUtils.e("Float parseFloat error :" + e.getMessage());
                    $jacocoInit[219] = true;
                }
            }
            i = WXUtils.getNumberInt(map.get(Constants.Name.Recycler.CELL_INDEX), -1);
            $jacocoInit[220] = true;
            i2 = WXUtils.getNumberInt(map.get(Constants.Name.Recycler.TYPE_INDEX), -1);
            $jacocoInit[221] = true;
        }
        WXCell findCell = findCell(wXComponent);
        if (i2 < 0) {
            $jacocoInit[222] = true;
        } else {
            $jacocoInit[223] = true;
            if (this.cellDataManager.listData == null) {
                $jacocoInit[224] = true;
            } else if (wXComponent.getRef() == null) {
                $jacocoInit[225] = true;
            } else {
                $jacocoInit[226] = true;
                $jacocoInit[227] = true;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i3 >= this.cellDataManager.listData.size()) {
                        $jacocoInit[228] = true;
                        break;
                    }
                    $jacocoInit[229] = true;
                    WXCell sourceTemplate = getSourceTemplate(i3);
                    if (sourceTemplate != null) {
                        if (!findCell.getRef().equals(sourceTemplate.getRef())) {
                            $jacocoInit[231] = true;
                        } else {
                            i4++;
                            $jacocoInit[232] = true;
                        }
                        if (i4 > i2) {
                            $jacocoInit[234] = true;
                            i = i3;
                            break;
                        }
                        $jacocoInit[233] = true;
                    } else {
                        $jacocoInit[230] = true;
                    }
                    i3++;
                    $jacocoInit[235] = true;
                }
                if (i >= 0) {
                    $jacocoInit[236] = true;
                } else {
                    $jacocoInit[237] = true;
                    i = this.cellDataManager.listData.size() - 1;
                    $jacocoInit[238] = true;
                }
            }
        }
        int i5 = (int) f;
        $jacocoInit[239] = true;
        BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) getHostView();
        if (bounceRecyclerView == null) {
            $jacocoInit[240] = true;
            return;
        }
        if (i < 0) {
            $jacocoInit[241] = true;
        } else {
            $jacocoInit[242] = true;
            $jacocoInit[243] = true;
            ((WXRecyclerView) bounceRecyclerView.getInnerView()).scrollTo(z, i, i5, getOrientation());
            $jacocoInit[244] = true;
        }
        $jacocoInit[245] = true;
    }

    public int getScrollY() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) getHostView();
        $jacocoInit[246] = true;
        if (bounceRecyclerView == null) {
            i = 0;
            $jacocoInit[247] = true;
        } else {
            i = ((WXRecyclerView) bounceRecyclerView.getInnerView()).getScrollY();
            $jacocoInit[248] = true;
        }
        $jacocoInit[249] = true;
        return i;
    }

    public int getScrollX() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) getHostView();
        $jacocoInit[250] = true;
        if (bounceRecyclerView == null) {
            i = 0;
            $jacocoInit[251] = true;
        } else {
            i = ((WXRecyclerView) bounceRecyclerView.getInnerView()).getScrollX();
            $jacocoInit[252] = true;
        }
        $jacocoInit[253] = true;
        return i;
    }

    public int getOrientation() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.orientation;
        $jacocoInit[254] = true;
        return i;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isScrollable;
        $jacocoInit[255] = true;
        return z;
    }

    public void addChild(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        addChild(wXComponent, -1);
        $jacocoInit[256] = true;
    }

    /* access modifiers changed from: protected */
    public int getChildrenLayoutTopOffset() {
        $jacocoInit()[257] = true;
        return 0;
    }

    public void addChild(WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXComponent instanceof WXCell;
        if (z) {
            $jacocoInit[258] = true;
        } else {
            $jacocoInit[259] = true;
            super.addChild(wXComponent, i);
            $jacocoInit[260] = true;
        }
        if (wXComponent instanceof WXBaseRefresh) {
            $jacocoInit[261] = true;
            return;
        }
        if (!z) {
            $jacocoInit[262] = true;
        } else {
            $jacocoInit[263] = true;
            if (wXComponent.getAttrs() == null) {
                $jacocoInit[264] = true;
            } else {
                $jacocoInit[265] = true;
                Object obj = wXComponent.getAttrs().get(Constants.Name.Recycler.SLOT_TEMPLATE_CASE);
                $jacocoInit[266] = true;
                String string = WXUtils.getString(obj, (String) null);
                $jacocoInit[267] = true;
                if (!getAttrs().containsKey("switch")) {
                    if (this.defaultTemplateCell == null) {
                        $jacocoInit[273] = true;
                    } else {
                        $jacocoInit[274] = true;
                        if (!wXComponent.getAttrs().containsKey("default")) {
                            $jacocoInit[275] = true;
                        } else {
                            $jacocoInit[276] = true;
                        }
                    }
                    this.defaultTemplateCell = (WXCell) wXComponent;
                    $jacocoInit[277] = true;
                    if (!TextUtils.isEmpty(string)) {
                        this.defaultTemplateKey = string;
                        $jacocoInit[278] = true;
                    } else {
                        string = this.defaultTemplateKey;
                        $jacocoInit[279] = true;
                        wXComponent.getAttrs().put(Constants.Name.Recycler.SLOT_TEMPLATE_CASE, (Object) string);
                        $jacocoInit[280] = true;
                    }
                } else if (this.defaultTemplateCell != null) {
                    $jacocoInit[268] = true;
                } else {
                    this.defaultTemplateCell = (WXCell) wXComponent;
                    $jacocoInit[269] = true;
                    if (!TextUtils.isEmpty(string)) {
                        this.defaultTemplateKey = string;
                        $jacocoInit[270] = true;
                    } else {
                        string = this.defaultTemplateKey;
                        $jacocoInit[271] = true;
                        wXComponent.getAttrs().put(Constants.Name.Recycler.SLOT_TEMPLATE_CASE, (Object) string);
                        $jacocoInit[272] = true;
                    }
                }
                if (string == null) {
                    $jacocoInit[281] = true;
                } else {
                    $jacocoInit[282] = true;
                    this.mTemplateSources.put(string, (WXCell) wXComponent);
                    $jacocoInit[283] = true;
                    if (this.mTemplateViewTypes.get(string) != null) {
                        $jacocoInit[284] = true;
                    } else {
                        $jacocoInit[285] = true;
                        this.mTemplateViewTypes.put(string, Integer.valueOf(this.mTemplateViewTypes.size()));
                        $jacocoInit[286] = true;
                    }
                }
            }
            ((WXCell) wXComponent).setCellAppendTreeListener(new WXCell.CellAppendTreeListener(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXRecyclerTemplateList this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(2689010826979377060L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$6", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void onAppendTreeDone() {
                    boolean[] $jacocoInit = $jacocoInit();
                    WXRecyclerTemplateList.access$400(this.this$0, false);
                    $jacocoInit[1] = true;
                }
            });
            $jacocoInit[287] = true;
        }
        $jacocoInit[288] = true;
    }

    private void checkAppendDone(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mTemplateSources.size() == 0) {
            $jacocoInit[289] = true;
            return;
        }
        Set<Map.Entry<String, WXCell>> entrySet = this.mTemplateSources.entrySet();
        $jacocoInit[290] = true;
        $jacocoInit[291] = true;
        for (Map.Entry<String, WXCell> value : entrySet) {
            $jacocoInit[292] = true;
            if (!((WXCell) value.getValue()).isAppendTreeDone()) {
                $jacocoInit[293] = true;
                return;
            }
            $jacocoInit[294] = true;
        }
        this.hasAppendTreeDone = true;
        if (!this.hasLayoutDone) {
            $jacocoInit[295] = true;
        } else {
            $jacocoInit[296] = true;
            notifyUpdateList();
            $jacocoInit[297] = true;
        }
        $jacocoInit[298] = true;
    }

    /* access modifiers changed from: protected */
    public void setHostLayoutParams(BounceRecyclerView bounceRecyclerView, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setHostLayoutParams(bounceRecyclerView, i, i2, i3, i4, i5, i6);
        if (this.hasLayoutDone) {
            $jacocoInit[299] = true;
        } else {
            this.hasLayoutDone = true;
            this.hasAppendTreeDone = true;
            $jacocoInit[300] = true;
            notifyUpdateList();
            $jacocoInit[301] = true;
        }
        $jacocoInit[302] = true;
    }

    public void addSubView(View view, int i) {
        $jacocoInit()[303] = true;
    }

    public void createChildViewAt(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i >= 0) {
            $jacocoInit[304] = true;
        } else {
            $jacocoInit[305] = true;
            i = childCount() - 1;
            if (i >= 0) {
                $jacocoInit[306] = true;
            } else {
                $jacocoInit[307] = true;
                return;
            }
        }
        WXComponent child = getChild(i);
        if (!(child instanceof WXBaseRefresh)) {
            $jacocoInit[308] = true;
        } else {
            $jacocoInit[309] = true;
            child.createView();
            $jacocoInit[310] = true;
            setRefreshOrLoading(child);
            $jacocoInit[311] = true;
        }
        $jacocoInit[312] = true;
    }

    public void remove(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        removeFooterOrHeader(wXComponent);
        $jacocoInit[313] = true;
        super.remove(wXComponent, z);
        $jacocoInit[314] = true;
    }

    public void computeVisiblePointInViewCoordinate(PointF pointF) {
        boolean[] $jacocoInit = $jacocoInit();
        RecyclerView recyclerView = (RecyclerView) ((BounceRecyclerView) getHostView()).getInnerView();
        $jacocoInit[315] = true;
        pointF.set((float) recyclerView.computeHorizontalScrollOffset(), (float) recyclerView.computeVerticalScrollOffset());
        $jacocoInit[316] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r6.hashCode()
            r2 = 10
            r3 = 1
            switch(r1) {
                case -889473228: goto L_0x00f6;
                case -713683669: goto L_0x00e2;
                case -338674661: goto L_0x00ce;
                case -223520855: goto L_0x00ba;
                case -112563826: goto L_0x00a7;
                case -5620052: goto L_0x0092;
                case 3046192: goto L_0x007d;
                case 66669991: goto L_0x0068;
                case 92902992: goto L_0x0053;
                case 100346066: goto L_0x003e;
                case 1345164648: goto L_0x0029;
                case 1614714674: goto L_0x0014;
                default: goto L_0x000e;
            }
        L_0x000e:
            r1 = 317(0x13d, float:4.44E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x0014:
            java.lang.String r1 = "scrollDirection"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0022
            r1 = 332(0x14c, float:4.65E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x0022:
            r1 = 7
            r4 = 333(0x14d, float:4.67E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x0029:
            java.lang.String r1 = "listData"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0037
            r1 = 318(0x13e, float:4.46E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x0037:
            r1 = 0
            r4 = 319(0x13f, float:4.47E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x003e:
            java.lang.String r1 = "index"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x004c
            r1 = 322(0x142, float:4.51E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x004c:
            r1 = 2
            r4 = 323(0x143, float:4.53E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x0053:
            java.lang.String r1 = "alias"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0061
            r1 = 320(0x140, float:4.48E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x0061:
            r1 = 321(0x141, float:4.5E-43)
            r0[r1] = r3
            r1 = 1
            goto L_0x010a
        L_0x0068:
            java.lang.String r1 = "scrollable"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0076
            r1 = 330(0x14a, float:4.62E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x0076:
            r1 = 6
            r4 = 331(0x14b, float:4.64E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x007d:
            java.lang.String r1 = "case"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x008b
            r1 = 326(0x146, float:4.57E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x008b:
            r1 = 4
            r4 = 327(0x147, float:4.58E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x0092:
            java.lang.String r1 = "offsetAccuracy"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00a0
            r1 = 340(0x154, float:4.76E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x00a0:
            r1 = 11
            r4 = 341(0x155, float:4.78E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x00a7:
            java.lang.String r1 = "loadmoreoffset"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00b4
            r1 = 328(0x148, float:4.6E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x00b4:
            r1 = 5
            r4 = 329(0x149, float:4.61E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x00ba:
            java.lang.String r1 = "showScrollbar"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00c7
            r1 = 334(0x14e, float:4.68E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x00c7:
            r1 = 8
            r4 = 335(0x14f, float:4.7E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x00ce:
            java.lang.String r1 = "hasFixedSize"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00db
            r1 = 338(0x152, float:4.74E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x00db:
            r1 = 339(0x153, float:4.75E-43)
            r0[r1] = r3
            r1 = 10
            goto L_0x010a
        L_0x00e2:
            java.lang.String r1 = "itemViewCacheSize"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00ef
            r1 = 336(0x150, float:4.71E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x00ef:
            r1 = 9
            r4 = 337(0x151, float:4.72E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x00f6:
            java.lang.String r1 = "switch"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0103
            r1 = 324(0x144, float:4.54E-43)
            r0[r1] = r3
            goto L_0x0109
        L_0x0103:
            r1 = 3
            r4 = 325(0x145, float:4.55E-43)
            r0[r4] = r3
            goto L_0x010a
        L_0x0109:
            r1 = -1
        L_0x010a:
            switch(r1) {
                case 0: goto L_0x01b7;
                case 1: goto L_0x01aa;
                case 2: goto L_0x019d;
                case 3: goto L_0x0190;
                case 4: goto L_0x0190;
                case 5: goto L_0x018b;
                case 6: goto L_0x0173;
                case 7: goto L_0x0158;
                case 8: goto L_0x0138;
                case 9: goto L_0x0133;
                case 10: goto L_0x012e;
                case 11: goto L_0x0116;
                default: goto L_0x010d;
            }
        L_0x010d:
            boolean r6 = super.setProperty(r6, r7)
            r7 = 361(0x169, float:5.06E-43)
            r0[r7] = r3
            return r6
        L_0x0116:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r6)
            int r6 = r6.intValue()
            r7 = 359(0x167, float:5.03E-43)
            r0[r7] = r3
            r5.setOffsetAccuracy(r6)
            r6 = 360(0x168, float:5.04E-43)
            r0[r6] = r3
            return r3
        L_0x012e:
            r6 = 358(0x166, float:5.02E-43)
            r0[r6] = r3
            return r3
        L_0x0133:
            r6 = 357(0x165, float:5.0E-43)
            r0[r6] = r3
            return r3
        L_0x0138:
            r6 = 0
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            if (r6 != 0) goto L_0x0144
            r6 = 353(0x161, float:4.95E-43)
            r0[r6] = r3
            goto L_0x0153
        L_0x0144:
            r7 = 354(0x162, float:4.96E-43)
            r0[r7] = r3
            boolean r6 = r6.booleanValue()
            r5.setShowScrollbar(r6)
            r6 = 355(0x163, float:4.97E-43)
            r0[r6] = r3
        L_0x0153:
            r6 = 356(0x164, float:4.99E-43)
            r0[r6] = r3
            return r3
        L_0x0158:
            if (r7 != 0) goto L_0x015f
            r6 = 349(0x15d, float:4.89E-43)
            r0[r6] = r3
            goto L_0x016e
        L_0x015f:
            r6 = 350(0x15e, float:4.9E-43)
            r0[r6] = r3
            java.lang.String r6 = r7.toString()
            r5.setScrollDirection(r6)
            r6 = 351(0x15f, float:4.92E-43)
            r0[r6] = r3
        L_0x016e:
            r6 = 352(0x160, float:4.93E-43)
            r0[r6] = r3
            return r3
        L_0x0173:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r7 = 347(0x15b, float:4.86E-43)
            r0[r7] = r3
            r5.setScrollable(r6)
            r6 = 348(0x15c, float:4.88E-43)
            r0[r6] = r3
            return r3
        L_0x018b:
            r6 = 346(0x15a, float:4.85E-43)
            r0[r6] = r3
            return r3
        L_0x0190:
            java.lang.String r6 = "case"
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r6)
            r5.listDataTemplateKey = r6
            r6 = 345(0x159, float:4.83E-43)
            r0[r6] = r3
            return r3
        L_0x019d:
            java.lang.String r6 = r5.listDataIndexKey
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r6)
            r5.listDataIndexKey = r6
            r6 = 344(0x158, float:4.82E-43)
            r0[r6] = r3
            return r3
        L_0x01aa:
            java.lang.String r6 = r5.listDataItemKey
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r6)
            r5.listDataItemKey = r6
            r6 = 343(0x157, float:4.8E-43)
            r0[r6] = r3
            return r3
        L_0x01b7:
            r5.setListData(r7)
            r6 = 342(0x156, float:4.79E-43)
            r0[r6] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "offsetAccuracy")
    public void setOffsetAccuracy(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOffsetAccuracy = (int) WXViewUtils.getRealPxByWidth((float) i, getInstance().getInstanceViewPortWidth());
        $jacocoInit[362] = true;
    }

    private void updateRecyclerAttr() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLayoutType = getAttrs().getLayoutType();
        $jacocoInit[363] = true;
        this.mColumnCount = getAttrs().getColumnCount();
        if (this.mColumnCount > 0) {
            $jacocoInit[364] = true;
        } else if (this.mLayoutType == 1) {
            $jacocoInit[365] = true;
        } else {
            $jacocoInit[366] = true;
            ArrayMap arrayMap = new ArrayMap();
            $jacocoInit[367] = true;
            arrayMap.put("componentType", getComponentType());
            $jacocoInit[368] = true;
            String instanceId = getInstanceId();
            WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_LIST_INVALID_COLUMN_COUNT;
            Locale locale = Locale.ENGLISH;
            int i = this.mColumnCount;
            $jacocoInit[369] = true;
            Object[] objArr = {Integer.valueOf(i)};
            $jacocoInit[370] = true;
            String format = String.format(locale, "You are trying to set the list/recycler/vlist/waterfall's column to %d, which is illegal. The column count should be a positive integer", objArr);
            $jacocoInit[371] = true;
            WXExceptionUtils.commitCriticalExceptionRT(instanceId, wXErrorCode, Constants.Name.COLUMN_COUNT, format, arrayMap);
            this.mColumnCount = 1;
            $jacocoInit[372] = true;
        }
        this.mColumnGap = getAttrs().getColumnGap();
        $jacocoInit[373] = true;
        this.mColumnWidth = getAttrs().getColumnWidth();
        $jacocoInit[374] = true;
        this.mPaddingLeft = getPadding().get(CSSShorthand.EDGE.LEFT);
        $jacocoInit[375] = true;
        this.mPaddingRight = getPadding().get(CSSShorthand.EDGE.RIGHT);
        $jacocoInit[376] = true;
    }

    @WXComponentProp(name = "scrollDirection")
    public void setScrollDirection(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.orientation == getAttrs().getOrientation()) {
            $jacocoInit[377] = true;
        } else {
            $jacocoInit[378] = true;
            this.orientation = getAttrs().getOrientation();
            $jacocoInit[379] = true;
            updateRecyclerAttr();
            $jacocoInit[380] = true;
            $jacocoInit[381] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[382] = true;
        }
        $jacocoInit[383] = true;
    }

    @WXComponentProp(name = "columnWidth")
    public void setColumnWidth(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getAttrs().getColumnWidth() == this.mColumnWidth) {
            $jacocoInit[384] = true;
        } else {
            $jacocoInit[385] = true;
            updateRecyclerAttr();
            $jacocoInit[386] = true;
            $jacocoInit[387] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[388] = true;
        }
        $jacocoInit[389] = true;
    }

    @WXComponentProp(name = "showScrollbar")
    public void setShowScrollbar(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[390] = true;
        } else if (((BounceRecyclerView) getHostView()).getInnerView() == null) {
            $jacocoInit[391] = true;
        } else {
            if (getOrientation() == 1) {
                $jacocoInit[393] = true;
                ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).setVerticalScrollBarEnabled(z);
                $jacocoInit[394] = true;
            } else {
                ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).setHorizontalScrollBarEnabled(z);
                $jacocoInit[395] = true;
            }
            $jacocoInit[396] = true;
            return;
        }
        $jacocoInit[392] = true;
    }

    @WXComponentProp(name = "columnCount")
    public void setColumnCount(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getAttrs().getColumnCount() == this.mColumnCount) {
            $jacocoInit[397] = true;
        } else {
            $jacocoInit[398] = true;
            updateRecyclerAttr();
            $jacocoInit[399] = true;
            $jacocoInit[400] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[401] = true;
        }
        $jacocoInit[402] = true;
    }

    @WXComponentProp(name = "columnGap")
    public void setColumnGap(float f) throws InterruptedException {
        boolean[] $jacocoInit = $jacocoInit();
        if (getAttrs().getColumnGap() == this.mColumnGap) {
            $jacocoInit[403] = true;
        } else {
            $jacocoInit[404] = true;
            updateRecyclerAttr();
            $jacocoInit[405] = true;
            $jacocoInit[406] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[407] = true;
        }
        $jacocoInit[408] = true;
    }

    @WXComponentProp(name = "scrollable")
    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[409] = true;
        ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).setScrollable(z);
        $jacocoInit[410] = true;
    }

    @JSMethod
    public void setListData(Object obj) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        JSONArray parseListDataToJSONArray = parseListDataToJSONArray(obj);
        if (this.cellDataManager.listData != parseListDataToJSONArray) {
            $jacocoInit[411] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[412] = true;
        }
        if (!(parseListDataToJSONArray instanceof JSONArray)) {
            $jacocoInit[413] = true;
        } else if (!z) {
            $jacocoInit[414] = true;
        } else {
            $jacocoInit[415] = true;
            this.cellDataManager.setListData(parseListDataToJSONArray);
            $jacocoInit[416] = true;
            notifyUpdateList();
            $jacocoInit[417] = true;
        }
        $jacocoInit[418] = true;
    }

    @JSMethod
    public void appendData(JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONArray == null) {
            $jacocoInit[419] = true;
        } else if (jSONArray.size() == 0) {
            $jacocoInit[420] = true;
        } else {
            if (this.cellDataManager.listData != null) {
                $jacocoInit[422] = true;
            } else {
                $jacocoInit[423] = true;
                this.cellDataManager.listData = new JSONArray();
                $jacocoInit[424] = true;
            }
            int size = this.cellDataManager.listData.size();
            if (size >= 0) {
                $jacocoInit[425] = true;
            } else {
                size = 0;
                $jacocoInit[426] = true;
            }
            if (!(jSONArray instanceof JSONArray)) {
                $jacocoInit[427] = true;
            } else {
                $jacocoInit[428] = true;
                this.cellDataManager.listData.addAll(jSONArray);
                $jacocoInit[429] = true;
            }
            ((BounceRecyclerView) getHostView()).getRecyclerViewBaseAdapter().notifyItemRangeInserted(size, jSONArray.size());
            $jacocoInit[430] = true;
            return;
        }
        $jacocoInit[421] = true;
    }

    @JSMethod
    public void insertData(int i, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[431] = true;
            return;
        }
        if (this.cellDataManager.listData == null) {
            $jacocoInit[432] = true;
        } else if (i > this.cellDataManager.listData.size()) {
            $jacocoInit[433] = true;
        } else {
            if (this.cellDataManager.insertData(i, obj)) {
                $jacocoInit[435] = true;
                notifyUpdateList();
                $jacocoInit[436] = true;
            } else {
                ((BounceRecyclerView) getHostView()).getRecyclerViewBaseAdapter().notifyItemInserted(i);
                $jacocoInit[437] = true;
            }
            $jacocoInit[438] = true;
            return;
        }
        $jacocoInit[434] = true;
    }

    @JSMethod
    public void appendRange(int i, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        insertRange(i, jSONArray);
        $jacocoInit[439] = true;
    }

    @JSMethod
    public void insertRange(int i, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONArray == null) {
            $jacocoInit[440] = true;
        } else if (jSONArray.size() == 0) {
            $jacocoInit[441] = true;
        } else {
            if (this.cellDataManager.listData == null) {
                $jacocoInit[443] = true;
            } else if (i > this.cellDataManager.listData.size()) {
                $jacocoInit[444] = true;
            } else {
                if (this.cellDataManager.insertRange(i, jSONArray)) {
                    $jacocoInit[446] = true;
                    notifyUpdateList();
                    $jacocoInit[447] = true;
                } else {
                    ((BounceRecyclerView) getHostView()).getRecyclerViewBaseAdapter().notifyItemRangeInserted(i, jSONArray.size());
                    $jacocoInit[448] = true;
                }
                $jacocoInit[449] = true;
                return;
            }
            $jacocoInit[445] = true;
            return;
        }
        $jacocoInit[442] = true;
    }

    @JSMethod
    public void updateData(int i, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[450] = true;
            return;
        }
        if (this.cellDataManager.listData == null) {
            $jacocoInit[451] = true;
        } else if (i >= this.cellDataManager.listData.size()) {
            $jacocoInit[452] = true;
        } else {
            if (this.cellDataManager.updateData(obj, i)) {
                $jacocoInit[454] = true;
                ((BounceRecyclerView) getHostView()).getRecyclerViewBaseAdapter().notifyItemChanged(i, obj);
                $jacocoInit[455] = true;
            } else {
                notifyUpdateList();
                $jacocoInit[456] = true;
            }
            $jacocoInit[457] = true;
            return;
        }
        $jacocoInit[453] = true;
    }

    @JSMethod
    public void removeData(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.cellDataManager.listData == null) {
            $jacocoInit[458] = true;
        } else {
            JSONArray jSONArray = this.cellDataManager.listData;
            $jacocoInit[459] = true;
            if (i >= jSONArray.size()) {
                $jacocoInit[460] = true;
            } else {
                if (i2 > 0) {
                    $jacocoInit[462] = true;
                } else {
                    $jacocoInit[463] = true;
                    i2 = 1;
                }
                int i3 = 0;
                $jacocoInit[464] = true;
                while (true) {
                    if (i2 <= 0) {
                        $jacocoInit[465] = true;
                        break;
                    } else if (i >= this.cellDataManager.listData.size()) {
                        $jacocoInit[466] = true;
                        break;
                    } else {
                        $jacocoInit[467] = true;
                        this.cellDataManager.removeData(Integer.valueOf(i));
                        i2--;
                        i3++;
                        $jacocoInit[468] = true;
                    }
                }
                if (i3 <= 0) {
                    $jacocoInit[469] = true;
                } else {
                    $jacocoInit[470] = true;
                    notifyUpdateList();
                    $jacocoInit[471] = true;
                }
                $jacocoInit[472] = true;
                return;
            }
        }
        $jacocoInit[461] = true;
    }

    @JSMethod
    public void resetLoadmore() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mForceLoadmoreNextTime = true;
        this.mListCellCount = 0;
        $jacocoInit[473] = true;
    }

    public void updateProperties(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateProperties(map);
        $jacocoInit[474] = true;
        if (map.containsKey("padding")) {
            $jacocoInit[475] = true;
        } else {
            $jacocoInit[476] = true;
            if (map.containsKey("paddingLeft")) {
                $jacocoInit[477] = true;
            } else {
                $jacocoInit[478] = true;
                if (!map.containsKey("paddingRight")) {
                    $jacocoInit[479] = true;
                    $jacocoInit[488] = true;
                }
                $jacocoInit[480] = true;
            }
        }
        if (this.mPaddingLeft != getPadding().get(CSSShorthand.EDGE.LEFT)) {
            $jacocoInit[481] = true;
        } else {
            float f = this.mPaddingRight;
            $jacocoInit[482] = true;
            if (f == getPadding().get(CSSShorthand.EDGE.RIGHT)) {
                $jacocoInit[483] = true;
                $jacocoInit[488] = true;
            }
            $jacocoInit[484] = true;
        }
        updateRecyclerAttr();
        $jacocoInit[485] = true;
        $jacocoInit[486] = true;
        ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
        $jacocoInit[487] = true;
        $jacocoInit[488] = true;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[489] = true;
        if (!ScrollStartEndHelper.isScrollEvent(str)) {
            $jacocoInit[490] = true;
        } else {
            $jacocoInit[491] = true;
            if (getHostView() == null) {
                $jacocoInit[492] = true;
            } else {
                $jacocoInit[493] = true;
                if (((BounceRecyclerView) getHostView()).getInnerView() == null) {
                    $jacocoInit[494] = true;
                } else if (this.mHasAddScrollEvent) {
                    $jacocoInit[495] = true;
                } else {
                    this.mHasAddScrollEvent = true;
                    $jacocoInit[496] = true;
                    $jacocoInit[497] = true;
                    ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).addOnScrollListener(new RecyclerView.OnScrollListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        private boolean mFirstEvent = true;
                        private int offsetXCorrection;
                        private int offsetYCorrection;
                        final /* synthetic */ WXRecyclerTemplateList this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(2403687225624388106L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$7", 16);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        /* JADX WARNING: Removed duplicated region for block: B:13:0x0063  */
                        /* JADX WARNING: Removed duplicated region for block: B:15:0x0068  */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void onScrolled(android.support.v7.widget.RecyclerView r6, int r7, int r8) {
                            /*
                                r5 = this;
                                boolean[] r0 = $jacocoInit()
                                super.onScrolled(r6, r7, r8)
                                r1 = 1
                                r0[r1] = r1
                                android.support.v7.widget.RecyclerView$LayoutManager r2 = r6.getLayoutManager()
                                r3 = 2
                                r0[r3] = r1
                                boolean r2 = r2.canScrollVertically()
                                if (r2 != 0) goto L_0x001b
                                r6 = 3
                                r0[r6] = r1
                                return
                            L_0x001b:
                                int r2 = r6.computeHorizontalScrollOffset()
                                r3 = 4
                                r0[r3] = r1
                                int r3 = r6.computeVerticalScrollOffset()
                                r4 = 0
                                if (r7 == 0) goto L_0x002d
                                r7 = 5
                                r0[r7] = r1
                                goto L_0x0032
                            L_0x002d:
                                if (r8 == 0) goto L_0x003f
                                r7 = 6
                                r0[r7] = r1
                            L_0x0032:
                                int r7 = r5.offsetXCorrection
                                int r7 = r2 - r7
                                int r8 = r5.offsetYCorrection
                                int r8 = r3 - r8
                                r2 = 8
                                r0[r2] = r1
                                goto L_0x0048
                            L_0x003f:
                                r5.offsetXCorrection = r2
                                r5.offsetYCorrection = r3
                                r7 = 7
                                r0[r7] = r1
                                r7 = 0
                                r8 = 0
                            L_0x0048:
                                com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r2 = r5.this$0
                                com.taobao.weex.ui.component.helper.ScrollStartEndHelper r2 = r2.getScrollStartEndHelper()
                                r2.onScrolled(r7, r8)
                                r2 = 9
                                r0[r2] = r1
                                com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r2 = r5.this$0
                                com.taobao.weex.dom.WXEvent r2 = r2.getEvents()
                                java.lang.String r3 = "scroll"
                                boolean r2 = r2.contains(r3)
                                if (r2 != 0) goto L_0x0068
                                r6 = 10
                                r0[r6] = r1
                                return
                            L_0x0068:
                                boolean r2 = r5.mFirstEvent
                                if (r2 == 0) goto L_0x0073
                                r5.mFirstEvent = r4
                                r6 = 11
                                r0[r6] = r1
                                return
                            L_0x0073:
                                com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r2 = r5.this$0
                                boolean r2 = com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.access$500(r2, r7, r8)
                                if (r2 != 0) goto L_0x0080
                                r6 = 12
                                r0[r6] = r1
                                goto L_0x008d
                            L_0x0080:
                                r2 = 13
                                r0[r2] = r1
                                com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r2 = r5.this$0
                                com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.access$600(r2, r6, r7, r8)
                                r6 = 14
                                r0[r6] = r1
                            L_0x008d:
                                r6 = 15
                                r0[r6] = r1
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.AnonymousClass7.onScrolled(android.support.v7.widget.RecyclerView, int, int):void");
                        }
                    });
                    $jacocoInit[498] = true;
                }
            }
        }
        $jacocoInit[499] = true;
    }

    private void fireScrollEvent(RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        fireEvent("scroll", getScrollEvent(recyclerView, i, i2));
        $jacocoInit[500] = true;
    }

    public Map<String, Object> getScrollEvent(RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[501] = true;
        int measuredWidth = recyclerView.getMeasuredWidth() + recyclerView.computeHorizontalScrollRange();
        $jacocoInit[502] = true;
        int calcContentSize = calcContentSize();
        $jacocoInit[503] = true;
        HashMap hashMap = new HashMap(2);
        $jacocoInit[504] = true;
        HashMap hashMap2 = new HashMap(2);
        $jacocoInit[505] = true;
        HashMap hashMap3 = new HashMap(2);
        $jacocoInit[506] = true;
        hashMap2.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth((float) measuredWidth, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[507] = true;
        hashMap2.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth((float) calcContentSize, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[508] = true;
        hashMap3.put("x", Float.valueOf(-WXViewUtils.getWebPxByWidth((float) i, getInstance().getInstanceViewPortWidth())));
        $jacocoInit[509] = true;
        hashMap3.put(Constants.Name.Y, Float.valueOf(-WXViewUtils.getWebPxByWidth((float) (-calcContentOffset(recyclerView)), getInstance().getInstanceViewPortWidth())));
        $jacocoInit[510] = true;
        hashMap.put(Constants.Name.CONTENT_SIZE, hashMap2);
        $jacocoInit[511] = true;
        hashMap.put(Constants.Name.CONTENT_OFFSET, hashMap3);
        $jacocoInit[512] = true;
        return hashMap;
    }

    private boolean shouldReport(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastReport.x != -1) {
            $jacocoInit[513] = true;
        } else if (this.mLastReport.y != -1) {
            $jacocoInit[514] = true;
        } else {
            this.mLastReport.x = i;
            this.mLastReport.y = i2;
            $jacocoInit[515] = true;
            return true;
        }
        int abs = Math.abs(this.mLastReport.x - i);
        $jacocoInit[516] = true;
        int abs2 = Math.abs(this.mLastReport.y - i2);
        if (abs >= this.mOffsetAccuracy) {
            $jacocoInit[517] = true;
        } else if (abs2 >= this.mOffsetAccuracy) {
            $jacocoInit[518] = true;
        } else {
            $jacocoInit[520] = true;
            return false;
        }
        this.mLastReport.x = i;
        this.mLastReport.y = i2;
        $jacocoInit[519] = true;
        return true;
    }

    private boolean setRefreshOrLoading(final WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(wXComponent instanceof WXRefresh)) {
            $jacocoInit[521] = true;
        } else if (getHostView() == null) {
            $jacocoInit[522] = true;
        } else {
            $jacocoInit[523] = true;
            ((BounceRecyclerView) getHostView()).setOnRefreshListener((WXRefresh) wXComponent);
            $jacocoInit[524] = true;
            ((BounceRecyclerView) getHostView()).postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXRecyclerTemplateList this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-4175431226289815245L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$8", 2);
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
                    ((BounceRecyclerView) this.this$0.getHostView()).setHeaderView(wXComponent);
                    $jacocoInit[1] = true;
                }
            }), 100);
            $jacocoInit[525] = true;
            return true;
        }
        if (!(wXComponent instanceof WXLoading)) {
            $jacocoInit[526] = true;
        } else if (getHostView() == null) {
            $jacocoInit[527] = true;
        } else {
            $jacocoInit[528] = true;
            ((BounceRecyclerView) getHostView()).setOnLoadingListener((WXLoading) wXComponent);
            $jacocoInit[529] = true;
            ((BounceRecyclerView) getHostView()).postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXRecyclerTemplateList this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(2114060293715927775L, "com/taobao/weex/ui/component/list/template/WXRecyclerTemplateList$9", 2);
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
                    ((BounceRecyclerView) this.this$0.getHostView()).setFooterView(wXComponent);
                    $jacocoInit[1] = true;
                }
            }), 100);
            $jacocoInit[530] = true;
            return true;
        }
        $jacocoInit[531] = true;
        return false;
    }

    private void removeFooterOrHeader(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent instanceof WXLoading) {
            $jacocoInit[532] = true;
            ((BounceRecyclerView) getHostView()).removeFooterView(wXComponent);
            $jacocoInit[533] = true;
        } else if (!(wXComponent instanceof WXRefresh)) {
            $jacocoInit[534] = true;
        } else {
            $jacocoInit[535] = true;
            ((BounceRecyclerView) getHostView()).removeHeaderView(wXComponent);
            $jacocoInit[536] = true;
        }
        $jacocoInit[537] = true;
    }

    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent wXComponent, View view, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (!(wXComponent instanceof WXBaseRefresh)) {
            $jacocoInit[538] = true;
        } else if (marginLayoutParams != null) {
            $jacocoInit[539] = true;
        } else {
            $jacocoInit[540] = true;
            marginLayoutParams = new LinearLayout.LayoutParams(i, i2);
            $jacocoInit[541] = true;
            $jacocoInit[546] = true;
            return marginLayoutParams;
        }
        if (marginLayoutParams == null) {
            $jacocoInit[542] = true;
            marginLayoutParams = new RecyclerView.LayoutParams(i, i2);
            $jacocoInit[543] = true;
        } else {
            marginLayoutParams.width = i;
            marginLayoutParams.height = i2;
            $jacocoInit[544] = true;
            setMarginsSupportRTL(marginLayoutParams, i3, 0, i4, 0);
            $jacocoInit[545] = true;
        }
        $jacocoInit[546] = true;
        return marginLayoutParams;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        synchronized (this) {
            try {
                $jacocoInit[547] = true;
                if (getHostView() == null) {
                    $jacocoInit[548] = true;
                } else {
                    if (this.mAppearChangeRunnable == null) {
                        $jacocoInit[549] = true;
                    } else {
                        $jacocoInit[550] = true;
                        ((BounceRecyclerView) getHostView()).removeCallbacks(this.mAppearChangeRunnable);
                        this.mAppearChangeRunnable = null;
                        $jacocoInit[551] = true;
                    }
                    ((BounceRecyclerView) getHostView()).removeCallbacks(this.listUpdateRunnable);
                    $jacocoInit[552] = true;
                    if (((BounceRecyclerView) getHostView()).getInnerView() == null) {
                        $jacocoInit[553] = true;
                    } else {
                        $jacocoInit[554] = true;
                        ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).setAdapter((RecyclerView.Adapter) null);
                        $jacocoInit[555] = true;
                    }
                }
                if (this.cellDataManager.listData == null) {
                    $jacocoInit[556] = true;
                } else {
                    $jacocoInit[557] = true;
                    this.cellDataManager.setListData((JSONArray) null);
                    $jacocoInit[558] = true;
                }
                if (this.mStickyHelper == null) {
                    $jacocoInit[559] = true;
                } else {
                    this.mStickyHelper = null;
                    $jacocoInit[560] = true;
                }
                if (this.mTemplateViewTypes == null) {
                    $jacocoInit[561] = true;
                } else {
                    $jacocoInit[562] = true;
                    this.mTemplateViewTypes.clear();
                    $jacocoInit[563] = true;
                }
                if (this.mTemplateSources == null) {
                    $jacocoInit[564] = true;
                } else {
                    $jacocoInit[565] = true;
                    this.mTemplateSources.clear();
                    $jacocoInit[566] = true;
                }
                if (this.mAppearHelpers == null) {
                    $jacocoInit[567] = true;
                } else {
                    $jacocoInit[568] = true;
                    this.mAppearHelpers.clear();
                    $jacocoInit[569] = true;
                }
                if (this.mDisAppearWatchList == null) {
                    $jacocoInit[570] = true;
                } else {
                    $jacocoInit[571] = true;
                    this.mDisAppearWatchList.clear();
                    $jacocoInit[572] = true;
                }
                super.destroy();
            } catch (Throwable th) {
                while (true) {
                    $jacocoInit[573] = true;
                    throw th;
                }
            }
        }
        $jacocoInit[574] = true;
    }

    public void onViewRecycled(TemplateViewHolder templateViewHolder) {
        $jacocoInit()[575] = true;
    }

    public void onBindViewHolder(TemplateViewHolder templateViewHolder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (templateViewHolder == null) {
            $jacocoInit[576] = true;
            return;
        }
        WXCell template = templateViewHolder.getTemplate();
        if (template == null) {
            $jacocoInit[577] = true;
            return;
        }
        if (templateViewHolder.getHolderPosition() < 0) {
            $jacocoInit[578] = true;
        } else {
            $jacocoInit[579] = true;
            fireEvent(TemplateDom.DETACH_CELL_SLOT, TemplateDom.findAllComponentRefs(getRef(), i, template));
            $jacocoInit[580] = true;
        }
        System.currentTimeMillis();
        $jacocoInit[581] = true;
        templateViewHolder.setHolderPosition(i);
        $jacocoInit[582] = true;
        Object obj = this.cellDataManager.listData.get(i);
        $jacocoInit[583] = true;
        CellRenderState renderState = this.cellDataManager.getRenderState(i);
        $jacocoInit[584] = true;
        if (template.getRenderData() != obj) {
            $jacocoInit[585] = true;
        } else {
            if (renderState == null) {
                $jacocoInit[586] = true;
            } else if (renderState.isDirty()) {
                $jacocoInit[587] = true;
            } else {
                $jacocoInit[588] = true;
            }
            if (!WXEnvironment.isOpenDebugLog()) {
                $jacocoInit[589] = true;
            } else {
                $jacocoInit[590] = true;
            }
            fireEvent(TemplateDom.ATTACH_CELL_SLOT, TemplateDom.findAllComponentRefs(getRef(), i, template));
            $jacocoInit[591] = true;
            return;
        }
        List<WXComponent> doRenderTemplate = doRenderTemplate(template, i);
        $jacocoInit[592] = true;
        Statements.doInitCompontent(doRenderTemplate);
        $jacocoInit[593] = true;
        template.setRenderData(obj);
        $jacocoInit[594] = true;
        Layouts.doLayoutAsync(templateViewHolder, true);
        $jacocoInit[595] = true;
        if (!WXEnvironment.isOpenDebugLog()) {
            $jacocoInit[596] = true;
        } else {
            $jacocoInit[597] = true;
        }
        $jacocoInit[598] = true;
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [com.taobao.weex.ui.component.WXComponent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.taobao.weex.ui.component.list.template.TemplateViewHolder onCreateViewHolder(android.view.ViewGroup r7, int r8) {
        /*
            r6 = this;
            boolean[] r7 = $jacocoInit()
            android.support.v4.util.ArrayMap<java.lang.String, java.lang.Integer> r0 = r6.mTemplateViewTypes
            java.lang.Object r0 = r0.keyAt(r8)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 1
            r2 = 599(0x257, float:8.4E-43)
            r7[r2] = r1
            java.util.Map<java.lang.String, com.taobao.weex.ui.component.list.WXCell> r2 = r6.mTemplateSources
            java.lang.Object r2 = r2.get(r0)
            com.taobao.weex.ui.component.list.WXCell r2 = (com.taobao.weex.ui.component.list.WXCell) r2
            r3 = 0
            if (r2 != 0) goto L_0x0043
            r0 = 600(0x258, float:8.41E-43)
            r7[r0] = r1
            android.widget.FrameLayout r0 = new android.widget.FrameLayout
            android.content.Context r2 = r6.getContext()
            r0.<init>(r2)
            r2 = 601(0x259, float:8.42E-43)
            r7[r2] = r1
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r2.<init>(r3, r3)
            r0.setLayoutParams(r2)
            r2 = 602(0x25a, float:8.44E-43)
            r7[r2] = r1
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r2 = new com.taobao.weex.ui.component.list.template.TemplateViewHolder
            r2.<init>((com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList) r6, (android.view.View) r0, (int) r8)
            r8 = 603(0x25b, float:8.45E-43)
            r7[r8] = r1
            return r2
        L_0x0043:
            com.taobao.weex.ui.component.list.WXCell r4 = r6.getCellTemplateFromCache(r0)
            if (r4 == 0) goto L_0x004e
            r5 = 604(0x25c, float:8.46E-43)
            r7[r5] = r1
            goto L_0x007f
        L_0x004e:
            r5 = 605(0x25d, float:8.48E-43)
            r7[r5] = r1
            boolean r5 = r2.isSourceUsed()
            if (r5 == 0) goto L_0x005d
            r5 = 606(0x25e, float:8.49E-43)
            r7[r5] = r1
            goto L_0x007f
        L_0x005d:
            r4 = 607(0x25f, float:8.5E-43)
            r7[r4] = r1
            r2.setSourceUsed(r1)
            r4 = 608(0x260, float:8.52E-43)
            r7[r4] = r1
            r6.renderTemplateCellWithData(r2)
            r4 = 609(0x261, float:8.53E-43)
            r7[r4] = r1
            boolean r4 = com.taobao.weex.WXEnvironment.isOpenDebugLog()
            if (r4 != 0) goto L_0x007a
            r4 = 610(0x262, float:8.55E-43)
            r7[r4] = r1
            goto L_0x007e
        L_0x007a:
            r4 = 611(0x263, float:8.56E-43)
            r7[r4] = r1
        L_0x007e:
            r4 = r2
        L_0x007f:
            if (r4 == 0) goto L_0x0086
            r2 = 612(0x264, float:8.58E-43)
            r7[r2] = r1
            goto L_0x00ab
        L_0x0086:
            r4 = 613(0x265, float:8.59E-43)
            r7[r4] = r1
            java.lang.System.currentTimeMillis()
            r4 = 614(0x266, float:8.6E-43)
            r7[r4] = r1
            com.taobao.weex.ui.component.WXComponent r2 = r6.copyComponentFromSourceCell(r2)
            r4 = r2
            com.taobao.weex.ui.component.list.WXCell r4 = (com.taobao.weex.ui.component.list.WXCell) r4
            r2 = 615(0x267, float:8.62E-43)
            r7[r2] = r1
            boolean r2 = com.taobao.weex.WXEnvironment.isOpenDebugLog()
            if (r2 != 0) goto L_0x00a7
            r2 = 616(0x268, float:8.63E-43)
            r7[r2] = r1
            goto L_0x00ab
        L_0x00a7:
            r2 = 617(0x269, float:8.65E-43)
            r7[r2] = r1
        L_0x00ab:
            boolean r2 = r4.isLazy()
            if (r2 == 0) goto L_0x00b6
            r2 = 618(0x26a, float:8.66E-43)
            r7[r2] = r1
            goto L_0x00c0
        L_0x00b6:
            android.view.View r2 = r4.getHostView()
            if (r2 != 0) goto L_0x00d7
            r2 = 619(0x26b, float:8.67E-43)
            r7[r2] = r1
        L_0x00c0:
            doCreateCellViewBindData(r4, r0, r3)
            r0 = 620(0x26c, float:8.69E-43)
            r7[r0] = r1
            boolean r0 = com.taobao.weex.WXEnvironment.isOpenDebugLog()
            if (r0 != 0) goto L_0x00d2
            r0 = 621(0x26d, float:8.7E-43)
            r7[r0] = r1
            goto L_0x00e6
        L_0x00d2:
            r0 = 622(0x26e, float:8.72E-43)
            r7[r0] = r1
            goto L_0x00e6
        L_0x00d7:
            boolean r0 = com.taobao.weex.WXEnvironment.isOpenDebugLog()
            if (r0 != 0) goto L_0x00e2
            r0 = 623(0x26f, float:8.73E-43)
            r7[r0] = r1
            goto L_0x00e6
        L_0x00e2:
            r0 = 624(0x270, float:8.74E-43)
            r7[r0] = r1
        L_0x00e6:
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r0 = new com.taobao.weex.ui.component.list.template.TemplateViewHolder
            r0.<init>((com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList) r6, (com.taobao.weex.ui.component.list.WXCell) r4, (int) r8)
            r8 = 625(0x271, float:8.76E-43)
            r7[r8] = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.onCreateViewHolder(android.view.ViewGroup, int):com.taobao.weex.ui.component.list.template.TemplateViewHolder");
    }

    public int getItemViewType(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        String templateKey = getTemplateKey(i);
        $jacocoInit[626] = true;
        int indexOfKey = this.mTemplateViewTypes.indexOfKey(templateKey);
        if (indexOfKey >= 0) {
            $jacocoInit[627] = true;
        } else {
            $jacocoInit[628] = true;
            indexOfKey = this.mTemplateViewTypes.indexOfKey("");
            $jacocoInit[629] = true;
        }
        $jacocoInit[630] = true;
        return indexOfKey;
    }

    private List<WXComponent> doRenderTemplate(WXCell wXCell, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.cellRenderContext.clear();
        $jacocoInit[631] = true;
        Object obj = this.cellDataManager.listData.get(i);
        $jacocoInit[632] = true;
        CellRenderState renderState = this.cellDataManager.getRenderState(i);
        this.cellRenderContext.renderState = renderState;
        this.cellRenderContext.templateList = this;
        this.cellRenderContext.position = i;
        ArrayStack arrayStack = this.cellRenderContext.stack;
        Map map = this.cellRenderContext.map;
        if (this.cellDataManager.listData == null) {
            $jacocoInit[633] = true;
        } else {
            $jacocoInit[634] = true;
            arrayStack.push(map);
            $jacocoInit[635] = true;
            map.put(this.listDataKey, this.cellDataManager.listData);
            $jacocoInit[636] = true;
            if (TextUtils.isEmpty(this.listDataIndexKey)) {
                $jacocoInit[637] = true;
            } else {
                $jacocoInit[638] = true;
                map.put(this.listDataIndexKey, new PositionRef(renderState));
                $jacocoInit[639] = true;
            }
            if (!TextUtils.isEmpty(this.listDataItemKey)) {
                $jacocoInit[640] = true;
                map.put(this.listDataItemKey, obj);
                $jacocoInit[641] = true;
            } else {
                arrayStack.push(obj);
                $jacocoInit[642] = true;
            }
        }
        if (renderState.itemId > 0) {
            $jacocoInit[643] = true;
        } else {
            $jacocoInit[644] = true;
            getItemId(i);
            $jacocoInit[645] = true;
        }
        List<WXComponent> doRender = Statements.doRender(wXCell, this.cellRenderContext);
        $jacocoInit[646] = true;
        if (!renderState.isDirty()) {
            $jacocoInit[647] = true;
        } else {
            $jacocoInit[648] = true;
            renderState.resetDirty();
            $jacocoInit[649] = true;
        }
        $jacocoInit[650] = true;
        return doRender;
    }

    public ArrayStack copyStack(CellRenderContext cellRenderContext2, ArrayStack arrayStack) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayStack arrayStack2 = new ArrayStack();
        $jacocoInit[651] = true;
        $jacocoInit[652] = true;
        int i = 0;
        while (i < arrayStack.size()) {
            $jacocoInit[653] = true;
            Object obj = arrayStack.get(i);
            if (!(obj instanceof Map)) {
                $jacocoInit[654] = true;
            } else {
                $jacocoInit[655] = true;
                HashMap hashMap = new HashMap((Map) obj);
                $jacocoInit[656] = true;
                obj = hashMap;
            }
            arrayStack2.push(obj);
            i++;
            $jacocoInit[657] = true;
        }
        $jacocoInit[658] = true;
        return arrayStack2;
    }

    public String getTemplateKey(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Object safeGetListData = safeGetListData(i);
        $jacocoInit[659] = true;
        String templateKey = getTemplateKey(safeGetListData);
        $jacocoInit[660] = true;
        return templateKey;
    }

    public String getTemplateKey(Object obj) {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (!(obj instanceof JSONObject)) {
            $jacocoInit[661] = true;
            str = null;
        } else {
            $jacocoInit[662] = true;
            str = ((JSONObject) obj).getString(this.listDataTemplateKey);
            $jacocoInit[663] = true;
        }
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[664] = true;
        } else if (this.defaultTemplateCell != null) {
            str = this.defaultTemplateKey;
            $jacocoInit[665] = true;
        } else {
            str = "";
            $jacocoInit[666] = true;
        }
        $jacocoInit[667] = true;
        return str;
    }

    public WXCell getSourceTemplate(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        String templateKey = getTemplateKey(i);
        $jacocoInit[668] = true;
        WXCell wXCell = this.mTemplateSources.get(templateKey);
        $jacocoInit[669] = true;
        return wXCell;
    }

    private int getCellTemplateItemType(WXCell wXCell) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXCell == null) {
            $jacocoInit[670] = true;
            return -1;
        } else if (wXCell.getAttrs() != null) {
            $jacocoInit[671] = true;
            Object obj = wXCell.getAttrs().get(Constants.Name.Recycler.SLOT_TEMPLATE_CASE);
            $jacocoInit[672] = true;
            String string = WXUtils.getString(obj, (String) null);
            if (wXCell != this.defaultTemplateCell) {
                $jacocoInit[673] = true;
            } else {
                string = this.defaultTemplateKey;
                $jacocoInit[674] = true;
            }
            int indexOfKey = this.mTemplateViewTypes.indexOfKey(string);
            if (indexOfKey < 0) {
                $jacocoInit[675] = true;
                return -1;
            }
            $jacocoInit[676] = true;
            return indexOfKey;
        } else {
            $jacocoInit[677] = true;
            return 0;
        }
    }

    public int getItemCount() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.hasLayoutDone) {
            $jacocoInit[678] = true;
            return 0;
        } else if (!this.hasAppendTreeDone) {
            $jacocoInit[679] = true;
            return 0;
        } else if (this.cellDataManager.listData == null) {
            $jacocoInit[680] = true;
            return 0;
        } else {
            if (this.mTemplateViewTypes == null) {
                $jacocoInit[681] = true;
            } else if (this.mTemplateViewTypes.size() <= 1) {
                $jacocoInit[682] = true;
            } else {
                if (this.mTemplateSources == null) {
                    $jacocoInit[684] = true;
                } else if (this.mTemplateSources.size() == 0) {
                    $jacocoInit[685] = true;
                } else {
                    int size = this.cellDataManager.listData.size();
                    $jacocoInit[687] = true;
                    return size;
                }
                $jacocoInit[686] = true;
                return 0;
            }
            $jacocoInit[683] = true;
            return 0;
        }
    }

    public boolean onFailedToRecycleView(TemplateViewHolder templateViewHolder) {
        $jacocoInit()[688] = true;
        return false;
    }

    public long getItemId(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        CellRenderState renderState = this.cellDataManager.getRenderState(i);
        if (renderState.itemId > 0) {
            $jacocoInit[689] = true;
        } else {
            $jacocoInit[690] = true;
            String templateKey = getTemplateKey(i);
            $jacocoInit[691] = true;
            if (TextUtils.isEmpty(templateKey)) {
                $jacocoInit[692] = true;
                return -1;
            }
            Object safeGetListData = safeGetListData(i);
            $jacocoInit[693] = true;
            if (!(safeGetListData instanceof JSONObject)) {
                $jacocoInit[694] = true;
            } else {
                JSONObject jSONObject = (JSONObject) safeGetListData;
                if (!jSONObject.containsKey("keyItemId")) {
                    $jacocoInit[695] = true;
                } else {
                    $jacocoInit[696] = true;
                    renderState.itemId = jSONObject.getLongValue("keyItemId");
                    $jacocoInit[697] = true;
                }
            }
            renderState.itemId = (((long) Math.abs(safeGetListData.hashCode())) << 24) + ((long) i);
            $jacocoInit[698] = true;
        }
        long j = renderState.itemId;
        $jacocoInit[699] = true;
        return j;
    }

    public void onBeforeScroll(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStickyHelper == null) {
            $jacocoInit[700] = true;
        } else {
            $jacocoInit[701] = true;
            this.mStickyHelper.onBeforeScroll(i, i2);
            $jacocoInit[702] = true;
        }
        $jacocoInit[703] = true;
    }

    public void onLoadMore(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String loadMoreOffset = getAttrs().getLoadMoreOffset();
            $jacocoInit[704] = true;
            if (!TextUtils.isEmpty(loadMoreOffset)) {
                $jacocoInit[705] = true;
            } else {
                loadMoreOffset = "0";
                $jacocoInit[706] = true;
            }
            if (((float) i) > WXViewUtils.getRealPxByWidth((float) Integer.parseInt(loadMoreOffset), getInstance().getInstanceViewPortWidth())) {
                $jacocoInit[707] = true;
            } else if (this.cellDataManager.listData == null) {
                $jacocoInit[708] = true;
            } else {
                $jacocoInit[709] = true;
                if (this.mListCellCount != this.cellDataManager.listData.size()) {
                    $jacocoInit[710] = true;
                } else if (!this.mForceLoadmoreNextTime) {
                    $jacocoInit[711] = true;
                } else {
                    $jacocoInit[712] = true;
                }
                fireEvent(Constants.Event.LOADMORE);
                $jacocoInit[713] = true;
                this.mListCellCount = this.cellDataManager.listData.size();
                this.mForceLoadmoreNextTime = false;
                $jacocoInit[714] = true;
            }
            $jacocoInit[715] = true;
        } catch (Exception e) {
            $jacocoInit[716] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[717] = true;
            } else {
                $jacocoInit[718] = true;
                WXLogUtils.d("WXRecyclerTemplateList onLoadMore : ", (Throwable) e);
                $jacocoInit[719] = true;
            }
        }
        $jacocoInit[720] = true;
    }

    public void notifyAppearStateChange(int i, int i2, int i3, int i4) {
        String str;
        int i5;
        List list;
        String str2;
        int i6 = i2;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAppearHelpers == null) {
            $jacocoInit[721] = true;
        } else {
            ArrayMap<Integer, List<AppearanceHelper>> arrayMap = this.mAppearHelpers;
            $jacocoInit[722] = true;
            if (arrayMap.size() <= 0) {
                $jacocoInit[723] = true;
            } else {
                if (this.mAppearChangeRunnable == null) {
                    $jacocoInit[725] = true;
                } else {
                    $jacocoInit[726] = true;
                    ((BounceRecyclerView) getHostView()).removeCallbacks(this.mAppearChangeRunnable);
                    this.mAppearChangeRunnable = null;
                    $jacocoInit[727] = true;
                }
                if (i4 > 0) {
                    str = "up";
                    $jacocoInit[728] = true;
                } else if (i4 < 0) {
                    str = "down";
                    $jacocoInit[729] = true;
                } else {
                    $jacocoInit[730] = true;
                    str = null;
                }
                $jacocoInit[731] = true;
                if (getOrientation() != 0) {
                    $jacocoInit[732] = true;
                } else if (i3 == 0) {
                    $jacocoInit[733] = true;
                } else {
                    if (i3 > 0) {
                        str2 = "left";
                        $jacocoInit[734] = true;
                    } else {
                        str2 = "right";
                        $jacocoInit[735] = true;
                    }
                    str = str2;
                    $jacocoInit[736] = true;
                }
                RecyclerView recyclerView = (RecyclerView) ((BounceRecyclerView) getHostView()).getInnerView();
                $jacocoInit[737] = true;
                int i7 = i;
                while (true) {
                    i5 = 0;
                    if (i7 > i6) {
                        break;
                    }
                    $jacocoInit[738] = true;
                    int itemViewType = getItemViewType(i7);
                    $jacocoInit[739] = true;
                    List list2 = this.mAppearHelpers.get(Integer.valueOf(itemViewType));
                    if (list2 == null) {
                        $jacocoInit[740] = true;
                    } else {
                        Iterator it = list2.iterator();
                        $jacocoInit[741] = true;
                        while (true) {
                            if (!it.hasNext()) {
                                $jacocoInit[742] = true;
                                break;
                            }
                            AppearanceHelper appearanceHelper = (AppearanceHelper) it.next();
                            $jacocoInit[743] = true;
                            if (!appearanceHelper.isWatch()) {
                                $jacocoInit[744] = true;
                            } else {
                                TemplateViewHolder templateViewHolder = (TemplateViewHolder) recyclerView.findViewHolderForAdapterPosition(i7);
                                $jacocoInit[745] = true;
                                if (templateViewHolder == null) {
                                    $jacocoInit[746] = true;
                                    break;
                                } else if (templateViewHolder.getComponent() == null) {
                                    $jacocoInit[747] = true;
                                    break;
                                } else {
                                    List<WXComponent> findChildListByRef = findChildListByRef(templateViewHolder.getComponent(), appearanceHelper.getAwareChild().getRef());
                                    $jacocoInit[748] = true;
                                    if (findChildListByRef == null) {
                                        $jacocoInit[749] = true;
                                        break;
                                    } else if (findChildListByRef.size() == 0) {
                                        $jacocoInit[750] = true;
                                        break;
                                    } else {
                                        Map map = this.mDisAppearWatchList.get(Integer.valueOf(i7));
                                        if (map != null) {
                                            $jacocoInit[751] = true;
                                        } else {
                                            $jacocoInit[752] = true;
                                            map = new ArrayMap();
                                            $jacocoInit[753] = true;
                                            this.mDisAppearWatchList.put(Integer.valueOf(i7), map);
                                            $jacocoInit[754] = true;
                                        }
                                        Map map2 = (Map) map.get(appearanceHelper.getAwareChild().getRef());
                                        if (map2 != null) {
                                            $jacocoInit[755] = true;
                                        } else {
                                            $jacocoInit[756] = true;
                                            map2 = new ArrayMap();
                                            $jacocoInit[757] = true;
                                            map.put(appearanceHelper.getAwareChild().getRef(), map2);
                                            $jacocoInit[758] = true;
                                        }
                                        $jacocoInit[759] = true;
                                        int i8 = 0;
                                        while (i8 < findChildListByRef.size()) {
                                            $jacocoInit[760] = true;
                                            WXComponent wXComponent = findChildListByRef.get(i8);
                                            $jacocoInit[761] = true;
                                            if (wXComponent.getHostView() == null) {
                                                $jacocoInit[762] = true;
                                            } else {
                                                boolean isViewVisible = appearanceHelper.isViewVisible(wXComponent.getHostView());
                                                $jacocoInit[763] = true;
                                                int hashCode = wXComponent.getHostView().hashCode();
                                                if (isViewVisible) {
                                                    $jacocoInit[764] = true;
                                                    if (map2.containsKey(Integer.valueOf(hashCode))) {
                                                        $jacocoInit[765] = true;
                                                    } else {
                                                        $jacocoInit[766] = true;
                                                        wXComponent.notifyAppearStateChange(Constants.Event.APPEAR, str);
                                                        $jacocoInit[767] = true;
                                                        if (wXComponent.getEvents() == null) {
                                                            $jacocoInit[768] = true;
                                                        } else {
                                                            $jacocoInit[769] = true;
                                                            if (wXComponent.getEvents().getEventBindingArgsValues() == null) {
                                                                $jacocoInit[770] = true;
                                                            } else {
                                                                $jacocoInit[771] = true;
                                                                if (wXComponent.getEvents().getEventBindingArgsValues().get(Constants.Event.DISAPPEAR) == null) {
                                                                    $jacocoInit[772] = true;
                                                                } else {
                                                                    $jacocoInit[773] = true;
                                                                    list = wXComponent.getEvents().getEventBindingArgsValues().get(Constants.Event.DISAPPEAR);
                                                                    $jacocoInit[774] = true;
                                                                    map2.put(Integer.valueOf(hashCode), list);
                                                                    $jacocoInit[775] = true;
                                                                }
                                                            }
                                                        }
                                                        list = null;
                                                        map2.put(Integer.valueOf(hashCode), list);
                                                        $jacocoInit[775] = true;
                                                    }
                                                } else if (!map2.containsKey(Integer.valueOf(hashCode))) {
                                                    $jacocoInit[776] = true;
                                                } else {
                                                    $jacocoInit[777] = true;
                                                    wXComponent.notifyAppearStateChange(Constants.Event.DISAPPEAR, str);
                                                    $jacocoInit[778] = true;
                                                    map2.remove(Integer.valueOf(hashCode));
                                                    $jacocoInit[779] = true;
                                                }
                                            }
                                            i8++;
                                            $jacocoInit[780] = true;
                                        }
                                        $jacocoInit[781] = true;
                                    }
                                }
                            }
                        }
                    }
                    i7++;
                    $jacocoInit[782] = true;
                }
                int itemCount = getItemCount();
                $jacocoInit[783] = true;
                while (i5 < itemCount) {
                    if (i5 < i) {
                        $jacocoInit[784] = true;
                    } else if (i5 > i6) {
                        $jacocoInit[785] = true;
                    } else {
                        i5 = i6 + 1;
                        $jacocoInit[786] = true;
                        i5++;
                        $jacocoInit[805] = true;
                    }
                    Map map3 = this.mDisAppearWatchList.get(Integer.valueOf(i5));
                    if (map3 == null) {
                        $jacocoInit[787] = true;
                    } else {
                        WXCell wXCell = this.mTemplateSources.get(getTemplateKey(i5));
                        if (wXCell == null) {
                            $jacocoInit[788] = true;
                            return;
                        }
                        Set<Map.Entry> entrySet = map3.entrySet();
                        $jacocoInit[789] = true;
                        $jacocoInit[790] = true;
                        for (Map.Entry entry : entrySet) {
                            $jacocoInit[791] = true;
                            $jacocoInit[792] = true;
                            WXComponent findChildByRef = findChildByRef(wXCell, (String) entry.getKey());
                            if (findChildByRef == null) {
                                $jacocoInit[793] = true;
                            } else {
                                Map map4 = (Map) entry.getValue();
                                $jacocoInit[794] = true;
                                if (map4 == null) {
                                    $jacocoInit[795] = true;
                                } else if (map4.size() == 0) {
                                    $jacocoInit[796] = true;
                                } else {
                                    WXEvent events = findChildByRef.getEvents();
                                    $jacocoInit[797] = true;
                                    Set<Map.Entry> entrySet2 = map4.entrySet();
                                    $jacocoInit[798] = true;
                                    $jacocoInit[799] = true;
                                    for (Map.Entry value : entrySet2) {
                                        $jacocoInit[800] = true;
                                        events.putEventBindingArgsValue(Constants.Event.DISAPPEAR, (List) value.getValue());
                                        $jacocoInit[801] = true;
                                        findChildByRef.notifyAppearStateChange(Constants.Event.DISAPPEAR, str);
                                        $jacocoInit[802] = true;
                                    }
                                    map4.clear();
                                    $jacocoInit[803] = true;
                                }
                            }
                        }
                        this.mDisAppearWatchList.remove(Integer.valueOf(i5));
                        $jacocoInit[804] = true;
                    }
                    i5++;
                    $jacocoInit[805] = true;
                }
                $jacocoInit[806] = true;
                return;
            }
        }
        $jacocoInit[724] = true;
    }

    private Object safeGetListData(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Object obj = this.cellDataManager.listData.get(i);
            $jacocoInit[807] = true;
            return obj;
        } catch (Exception unused) {
            JSONObject parseObject = JSONObject.parseObject("{}");
            $jacocoInit[808] = true;
            return parseObject;
        }
    }

    public void notifyUpdateList() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[809] = true;
        } else {
            $jacocoInit[810] = true;
            if (((BounceRecyclerView) getHostView()).getInnerView() == null) {
                $jacocoInit[811] = true;
            } else if (this.listUpdateRunnable == null) {
                $jacocoInit[812] = true;
            } else {
                if (Looper.getMainLooper().getThread().getId() != Thread.currentThread().getId()) {
                    $jacocoInit[814] = true;
                    ((BounceRecyclerView) getHostView()).removeCallbacks(this.listUpdateRunnable);
                    $jacocoInit[815] = true;
                    ((BounceRecyclerView) getHostView()).post(this.listUpdateRunnable);
                    $jacocoInit[816] = true;
                } else {
                    this.listUpdateRunnable.run();
                    $jacocoInit[817] = true;
                }
                $jacocoInit[818] = true;
                return;
            }
        }
        $jacocoInit[813] = true;
    }

    private int calcContentSize() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = 0;
        if (this.cellDataManager.listData == null) {
            $jacocoInit[819] = true;
            return 0;
        }
        $jacocoInit[820] = true;
        int i2 = 0;
        while (i < this.cellDataManager.listData.size()) {
            $jacocoInit[821] = true;
            WXCell sourceTemplate = getSourceTemplate(i);
            if (sourceTemplate == null) {
                $jacocoInit[822] = true;
            } else {
                $jacocoInit[823] = true;
                i2 = (int) (((float) i2) + sourceTemplate.getLayoutHeight());
                $jacocoInit[824] = true;
            }
            i++;
            $jacocoInit[825] = true;
        }
        $jacocoInit[826] = true;
        return i2;
    }

    public int calcContentOffset(RecyclerView recyclerView) {
        boolean[] $jacocoInit = $jacocoInit();
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i = 0;
        if (layoutManager instanceof LinearLayoutManager) {
            $jacocoInit[827] = true;
            int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            $jacocoInit[828] = true;
            int i2 = 0;
            while (i < findFirstVisibleItemPosition) {
                $jacocoInit[829] = true;
                WXCell sourceTemplate = getSourceTemplate(i);
                if (sourceTemplate == null) {
                    $jacocoInit[830] = true;
                } else {
                    i2 = (int) (((float) i2) - sourceTemplate.getLayoutHeight());
                    $jacocoInit[831] = true;
                }
                i++;
                $jacocoInit[832] = true;
            }
            if (!(layoutManager instanceof GridLayoutManager)) {
                $jacocoInit[833] = true;
            } else {
                $jacocoInit[834] = true;
                i2 /= ((GridLayoutManager) layoutManager).getSpanCount();
                $jacocoInit[835] = true;
            }
            View findViewByPosition = layoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition == null) {
                $jacocoInit[836] = true;
            } else {
                $jacocoInit[837] = true;
                i2 += findViewByPosition.getTop();
                $jacocoInit[838] = true;
            }
            $jacocoInit[839] = true;
            return i2;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            $jacocoInit[840] = true;
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int spanCount = staggeredGridLayoutManager.getSpanCount();
            $jacocoInit[841] = true;
            int i3 = staggeredGridLayoutManager.findFirstVisibleItemPositions((int[]) null)[0];
            $jacocoInit[842] = true;
            int i4 = 0;
            while (i < i3) {
                $jacocoInit[843] = true;
                WXCell sourceTemplate2 = getSourceTemplate(i);
                if (sourceTemplate2 == null) {
                    $jacocoInit[844] = true;
                } else {
                    i4 = (int) (((float) i4) - sourceTemplate2.getLayoutHeight());
                    $jacocoInit[845] = true;
                }
                i++;
                $jacocoInit[846] = true;
            }
            int i5 = i4 / spanCount;
            $jacocoInit[847] = true;
            View findViewByPosition2 = layoutManager.findViewByPosition(i3);
            if (findViewByPosition2 == null) {
                $jacocoInit[848] = true;
            } else {
                $jacocoInit[849] = true;
                i5 += findViewByPosition2.getTop();
                $jacocoInit[850] = true;
            }
            $jacocoInit[851] = true;
            return i5;
        } else {
            $jacocoInit[852] = true;
            return -1;
        }
    }

    public WXComponent findParentType(WXComponent wXComponent, Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        if (cls.isAssignableFrom(wXComponent.getClass())) {
            $jacocoInit[853] = true;
            return wXComponent;
        }
        if (wXComponent.getParent() == null) {
            $jacocoInit[854] = true;
        } else {
            $jacocoInit[855] = true;
            findTypeParent(wXComponent.getParent(), cls);
            $jacocoInit[856] = true;
        }
        $jacocoInit[857] = true;
        return null;
    }

    public WXComponent findChildByRef(WXComponent wXComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str.equals(wXComponent.getRef())) {
            $jacocoInit[858] = true;
            return wXComponent;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[859] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[860] = true;
            int i = 0;
            $jacocoInit[861] = true;
            while (i < wXVContainer.getChildCount()) {
                $jacocoInit[863] = true;
                WXComponent findChildByRef = findChildByRef(wXVContainer.getChild(i), str);
                if (findChildByRef != null) {
                    $jacocoInit[864] = true;
                    return findChildByRef;
                }
                i++;
                $jacocoInit[865] = true;
            }
            $jacocoInit[862] = true;
        }
        $jacocoInit[866] = true;
        return null;
    }

    public List<WXComponent> findChildListByRef(WXComponent wXComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent findChildByRef = findChildByRef(wXComponent, str);
        if (findChildByRef == null) {
            $jacocoInit[867] = true;
            return null;
        }
        ArrayList arrayList = new ArrayList();
        $jacocoInit[868] = true;
        WXVContainer parent = findChildByRef.getParent();
        if (parent == null) {
            $jacocoInit[869] = true;
        } else if (parent instanceof WXRecyclerTemplateList) {
            $jacocoInit[870] = true;
        } else {
            $jacocoInit[871] = true;
            int i = 0;
            $jacocoInit[872] = true;
            while (i < parent.getChildCount()) {
                $jacocoInit[873] = true;
                WXComponent child = parent.getChild(i);
                $jacocoInit[874] = true;
                if (!str.equals(child.getRef())) {
                    $jacocoInit[875] = true;
                } else {
                    $jacocoInit[876] = true;
                    arrayList.add(child);
                    $jacocoInit[877] = true;
                }
                i++;
                $jacocoInit[878] = true;
            }
            $jacocoInit[879] = true;
            $jacocoInit[881] = true;
            return arrayList;
        }
        arrayList.add(findChildByRef);
        $jacocoInit[880] = true;
        $jacocoInit[881] = true;
        return arrayList;
    }

    public WXComponent findChildByAttrsRef(WXComponent wXComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getAttrs() == null) {
            $jacocoInit[882] = true;
        } else if (!str.equals(wXComponent.getAttrs().get("ref"))) {
            $jacocoInit[883] = true;
        } else {
            $jacocoInit[884] = true;
            return wXComponent;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[885] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[886] = true;
            int i = 0;
            $jacocoInit[887] = true;
            while (i < wXVContainer.getChildCount()) {
                $jacocoInit[889] = true;
                WXComponent findChildByAttrsRef = findChildByAttrsRef(wXVContainer.getChild(i), str);
                if (findChildByAttrsRef != null) {
                    $jacocoInit[890] = true;
                    return findChildByAttrsRef;
                }
                i++;
                $jacocoInit[891] = true;
            }
            $jacocoInit[888] = true;
        }
        $jacocoInit[892] = true;
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.taobao.weex.ui.component.list.WXCell getCellTemplateFromCache(java.lang.String r7) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.taobao.weex.ui.component.list.template.TemplateCache> r1 = r6.mTemplatesCache
            java.lang.Object r1 = r1.get(r7)
            com.taobao.weex.ui.component.list.template.TemplateCache r1 = (com.taobao.weex.ui.component.list.template.TemplateCache) r1
            r2 = 1
            r3 = 893(0x37d, float:1.251E-42)
            r0[r3] = r2
            if (r1 != 0) goto L_0x0018
            r3 = 894(0x37e, float:1.253E-42)
            r0[r3] = r2
            goto L_0x002d
        L_0x0018:
            java.util.concurrent.ConcurrentLinkedQueue<com.taobao.weex.ui.component.list.WXCell> r3 = r1.cells
            if (r3 != 0) goto L_0x0021
            r3 = 895(0x37f, float:1.254E-42)
            r0[r3] = r2
            goto L_0x002d
        L_0x0021:
            java.util.concurrent.ConcurrentLinkedQueue<com.taobao.weex.ui.component.list.WXCell> r3 = r1.cells
            int r3 = r3.size()
            if (r3 > 0) goto L_0x002f
            r3 = 896(0x380, float:1.256E-42)
            r0[r3] = r2
        L_0x002d:
            r3 = 0
            goto L_0x003f
        L_0x002f:
            r3 = 897(0x381, float:1.257E-42)
            r0[r3] = r2
            java.util.concurrent.ConcurrentLinkedQueue<com.taobao.weex.ui.component.list.WXCell> r3 = r1.cells
            java.lang.Object r3 = r3.poll()
            com.taobao.weex.ui.component.list.WXCell r3 = (com.taobao.weex.ui.component.list.WXCell) r3
            r4 = 898(0x382, float:1.258E-42)
            r0[r4] = r2
        L_0x003f:
            if (r1 != 0) goto L_0x0046
            r4 = 899(0x383, float:1.26E-42)
            r0[r4] = r2
            goto L_0x0053
        L_0x0046:
            boolean r4 = r1.isLoadIng
            if (r4 == 0) goto L_0x004f
            r7 = 900(0x384, float:1.261E-42)
            r0[r7] = r2
            goto L_0x00ba
        L_0x004f:
            r4 = 901(0x385, float:1.263E-42)
            r0[r4] = r2
        L_0x0053:
            if (r1 == 0) goto L_0x005a
            r4 = 902(0x386, float:1.264E-42)
            r0[r4] = r2
            goto L_0x0070
        L_0x005a:
            r1 = 903(0x387, float:1.265E-42)
            r0[r1] = r2
            com.taobao.weex.ui.component.list.template.TemplateCache r1 = new com.taobao.weex.ui.component.list.template.TemplateCache
            r1.<init>()
            r4 = 904(0x388, float:1.267E-42)
            r0[r4] = r2
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.taobao.weex.ui.component.list.template.TemplateCache> r4 = r6.mTemplatesCache
            r4.put(r7, r1)
            r4 = 905(0x389, float:1.268E-42)
            r0[r4] = r2
        L_0x0070:
            r1.isLoadIng = r2
            r1 = 906(0x38a, float:1.27E-42)
            r0[r1] = r2
            java.util.Map<java.lang.String, com.taobao.weex.ui.component.list.WXCell> r1 = r6.mTemplateSources
            java.lang.Object r1 = r1.get(r7)
            com.taobao.weex.ui.component.list.WXCell r1 = (com.taobao.weex.ui.component.list.WXCell) r1
            if (r1 != 0) goto L_0x0085
            r7 = 907(0x38b, float:1.271E-42)
            r0[r7] = r2
            goto L_0x00ba
        L_0x0085:
            r4 = 908(0x38c, float:1.272E-42)
            r0[r4] = r2
            com.taobao.weex.dom.WXAttr r4 = r1.getAttrs()
            java.lang.String r5 = "preload"
            java.lang.Object r4 = r4.get(r5)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r4 = com.taobao.weex.utils.WXUtils.getBoolean(r4, r5)
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L_0x00a6
            r7 = 909(0x38d, float:1.274E-42)
            r0[r7] = r2
            goto L_0x00ba
        L_0x00a6:
            r4 = 910(0x38e, float:1.275E-42)
            r0[r4] = r2
            com.taobao.weex.ui.component.list.template.AsyncCellLoadTask r4 = new com.taobao.weex.ui.component.list.template.AsyncCellLoadTask
            r4.<init>(r7, r1, r6)
            r7 = 911(0x38f, float:1.277E-42)
            r0[r7] = r2
            r4.startTask()
            r7 = 912(0x390, float:1.278E-42)
            r0[r7] = r2
        L_0x00ba:
            r7 = 913(0x391, float:1.28E-42)
            r0[r7] = r2
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.getCellTemplateFromCache(java.lang.String):com.taobao.weex.ui.component.list.WXCell");
    }

    public WXComponent copyComponentFromSourceCell(WXCell wXCell) {
        boolean[] $jacocoInit = $jacocoInit();
        renderTemplateCellWithData(wXCell);
        $jacocoInit[914] = true;
        WXCell wXCell2 = (WXCell) Statements.copyComponentTree(wXCell);
        $jacocoInit[915] = true;
        return wXCell2;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private synchronized void renderTemplateCellWithData(com.taobao.weex.ui.component.list.WXCell r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean[] r0 = $jacocoInit()     // Catch:{ all -> 0x00b0 }
            java.lang.Object r1 = r6.getRenderData()     // Catch:{ all -> 0x00b0 }
            r2 = 1
            if (r1 == 0) goto L_0x0012
            r6 = 916(0x394, float:1.284E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00b0 }
            goto L_0x009c
        L_0x0012:
            r1 = 917(0x395, float:1.285E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00b0 }
            com.taobao.weex.ui.component.list.template.CellDataManager r1 = r5.cellDataManager     // Catch:{ all -> 0x00b0 }
            com.alibaba.fastjson.JSONArray r1 = r1.listData     // Catch:{ all -> 0x00b0 }
            if (r1 != 0) goto L_0x0022
            r6 = 918(0x396, float:1.286E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00b0 }
            goto L_0x009c
        L_0x0022:
            com.taobao.weex.ui.component.list.template.CellDataManager r1 = r5.cellDataManager     // Catch:{ all -> 0x00b0 }
            com.alibaba.fastjson.JSONArray r1 = r1.listData     // Catch:{ all -> 0x00b0 }
            int r1 = r1.size()     // Catch:{ all -> 0x00b0 }
            if (r1 > 0) goto L_0x0031
            r6 = 919(0x397, float:1.288E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00b0 }
            goto L_0x009c
        L_0x0031:
            monitor-enter(r5)     // Catch:{ all -> 0x00b0 }
            r1 = 920(0x398, float:1.289E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00a9 }
            java.lang.Object r1 = r6.getRenderData()     // Catch:{ all -> 0x00a9 }
            if (r1 == 0) goto L_0x0041
            r6 = 921(0x399, float:1.29E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00a9 }
            goto L_0x0097
        L_0x0041:
            r1 = 922(0x39a, float:1.292E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00a9 }
            com.taobao.weex.ui.component.binding.Statements.parseStatementsToken(r6)     // Catch:{ all -> 0x00a9 }
            r1 = 923(0x39b, float:1.293E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00a9 }
            r1 = 0
            r3 = 924(0x39c, float:1.295E-42)
            r0[r3] = r2     // Catch:{ all -> 0x00a9 }
        L_0x0051:
            com.taobao.weex.ui.component.list.template.CellDataManager r3 = r5.cellDataManager     // Catch:{ all -> 0x00a9 }
            com.alibaba.fastjson.JSONArray r3 = r3.listData     // Catch:{ all -> 0x00a9 }
            int r3 = r3.size()     // Catch:{ all -> 0x00a9 }
            if (r1 < r3) goto L_0x0060
            r6 = 925(0x39d, float:1.296E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00a9 }
            goto L_0x0097
        L_0x0060:
            r3 = 926(0x39e, float:1.298E-42)
            r0[r3] = r2     // Catch:{ all -> 0x00a9 }
            com.taobao.weex.ui.component.list.WXCell r3 = r5.getSourceTemplate(r1)     // Catch:{ all -> 0x00a9 }
            if (r6 != r3) goto L_0x00a2
            r3 = 927(0x39f, float:1.299E-42)
            r0[r3] = r2     // Catch:{ all -> 0x00a9 }
            com.taobao.weex.ui.component.list.template.CellDataManager r3 = r5.cellDataManager     // Catch:{ all -> 0x00a9 }
            com.alibaba.fastjson.JSONArray r3 = r3.listData     // Catch:{ all -> 0x00a9 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x00a9 }
            r4 = 928(0x3a0, float:1.3E-42)
            r0[r4] = r2     // Catch:{ all -> 0x00a9 }
            r5.doRenderTemplate(r6, r1)     // Catch:{ all -> 0x00a9 }
            r1 = 929(0x3a1, float:1.302E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00a9 }
            float r1 = r5.getLayoutWidth()     // Catch:{ all -> 0x00a9 }
            float r4 = r5.getLayoutHeight()     // Catch:{ all -> 0x00a9 }
            com.taobao.weex.ui.component.binding.Layouts.doLayoutSync(r6, r1, r4)     // Catch:{ all -> 0x00a9 }
            r1 = 930(0x3a2, float:1.303E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00a9 }
            r6.setRenderData(r3)     // Catch:{ all -> 0x00a9 }
            r6 = 931(0x3a3, float:1.305E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00a9 }
        L_0x0097:
            monitor-exit(r5)     // Catch:{ all -> 0x00a9 }
            r6 = 933(0x3a5, float:1.307E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00b0 }
        L_0x009c:
            r6 = 935(0x3a7, float:1.31E-42)
            r0[r6] = r2     // Catch:{ all -> 0x00b0 }
            monitor-exit(r5)
            return
        L_0x00a2:
            int r1 = r1 + 1
            r3 = 932(0x3a4, float:1.306E-42)
            r0[r3] = r2     // Catch:{ all -> 0x00a9 }
            goto L_0x0051
        L_0x00a9:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00a9 }
            r1 = 934(0x3a6, float:1.309E-42)
            r0[r1] = r2     // Catch:{ all -> 0x00b0 }
            throw r6     // Catch:{ all -> 0x00b0 }
        L_0x00b0:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList.renderTemplateCellWithData(com.taobao.weex.ui.component.list.WXCell):void");
    }

    public static void doCreateCellViewBindData(WXCell wXCell, String str, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXCell.isLazy()) {
            $jacocoInit[936] = true;
        } else if (wXCell.getHostView() != null) {
            $jacocoInit[937] = true;
            $jacocoInit[943] = true;
        } else {
            $jacocoInit[938] = true;
        }
        System.currentTimeMillis();
        $jacocoInit[939] = true;
        Statements.initLazyComponent(wXCell, (WXVContainer) null);
        $jacocoInit[940] = true;
        if (!WXEnvironment.isOpenDebugLog()) {
            $jacocoInit[941] = true;
        } else {
            $jacocoInit[942] = true;
        }
        $jacocoInit[943] = true;
    }

    public ScrollStartEndHelper getScrollStartEndHelper() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mScrollStartEndHelper != null) {
            $jacocoInit[944] = true;
        } else {
            $jacocoInit[945] = true;
            this.mScrollStartEndHelper = new ScrollStartEndHelper(this);
            $jacocoInit[946] = true;
        }
        ScrollStartEndHelper scrollStartEndHelper = this.mScrollStartEndHelper;
        $jacocoInit[947] = true;
        return scrollStartEndHelper;
    }

    public int getTemplateCacheSize() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.templateCacheSize;
        $jacocoInit[948] = true;
        return i;
    }

    public ConcurrentHashMap<String, TemplateCache> getTemplatesCache() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mTemplatesCache != null) {
            $jacocoInit[949] = true;
        } else {
            $jacocoInit[950] = true;
            this.mTemplatesCache = new ConcurrentHashMap<>();
            $jacocoInit[951] = true;
        }
        ConcurrentHashMap<String, TemplateCache> concurrentHashMap = this.mTemplatesCache;
        $jacocoInit[952] = true;
        return concurrentHashMap;
    }

    public CellDataManager getCellDataManager() {
        boolean[] $jacocoInit = $jacocoInit();
        CellDataManager cellDataManager2 = this.cellDataManager;
        $jacocoInit[953] = true;
        return cellDataManager2;
    }

    private JSONArray parseListDataToJSONArray(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (!(obj instanceof JSONArray)) {
                $jacocoInit[954] = true;
                if (obj instanceof String) {
                    $jacocoInit[956] = true;
                    JSONArray parseArray = JSONArray.parseArray(getAttrs().get(Constants.Name.Recycler.LIST_DATA).toString());
                    $jacocoInit[957] = true;
                    return parseArray;
                }
                $jacocoInit[958] = true;
                JSONArray jSONArray = new JSONArray();
                $jacocoInit[961] = true;
                return jSONArray;
            }
            JSONArray jSONArray2 = (JSONArray) obj;
            $jacocoInit[955] = true;
            return jSONArray2;
        } catch (Exception e) {
            $jacocoInit[959] = true;
            WXLogUtils.e(TAG, "parseListDataException" + e.getMessage());
            $jacocoInit[960] = true;
        }
    }
}
