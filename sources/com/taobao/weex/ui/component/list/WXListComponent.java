package com.taobao.weex.ui.component.list;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXBaseRefresh;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXLoading;
import com.taobao.weex.ui.component.WXRefresh;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXListComponent extends BasicListComponent<BounceRecyclerView> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String TAG;
    private boolean hasSetGapItemDecoration;
    private float mPaddingLeft;
    private float mPaddingRight;
    private Float[] mSpanOffsets;
    private String mSpanOffsetsStr;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(601605599252169558L, "com/taobao/weex/ui/component/list/WXListComponent", 163);
        $jacocoData = a2;
        return a2;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-179417440706245498L, "com/taobao/weex/ui/component/list/WXListComponent$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXListComponent wXListComponent = new WXListComponent(wXSDKInstance, wXVContainer, true, basicComponentData);
            $jacocoInit[1] = true;
            return wXListComponent;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXListComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXListComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        this.TAG = "WXListComponent";
        this.hasSetGapItemDecoration = false;
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public BounceRecyclerView generateListView(Context context, int i) {
        PagerSnapHelper pagerSnapHelper;
        boolean[] $jacocoInit = $jacocoInit();
        BounceRecyclerView bounceRecyclerView = new BounceRecyclerView(context, this.mLayoutType, this.mColumnCount, this.mColumnGap, i);
        $jacocoInit[2] = true;
        if (bounceRecyclerView.getSwipeLayout() == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            if (!WXUtils.getBoolean(getAttrs().get(Constants.Name.NEST_SCROLLING_ENABLED), false).booleanValue()) {
                $jacocoInit[5] = true;
            } else {
                $jacocoInit[6] = true;
                bounceRecyclerView.getSwipeLayout().setNestedScrollingEnabled(true);
                $jacocoInit[7] = true;
            }
        }
        if (!WXUtils.getBoolean(getAttrs().get(Constants.Name.PAGE_ENABLED), false).booleanValue()) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            String string = WXUtils.getString(getAttrs().get("pageSize"), (String) null);
            $jacocoInit[10] = true;
            if (TextUtils.isEmpty(string)) {
                $jacocoInit[11] = true;
                pagerSnapHelper = new PagerSnapHelper();
                $jacocoInit[12] = true;
            } else {
                pagerSnapHelper = new WXPagerSnapHelper();
                $jacocoInit[13] = true;
            }
            pagerSnapHelper.attachToRecyclerView((RecyclerView) bounceRecyclerView.getInnerView());
            $jacocoInit[14] = true;
        }
        $jacocoInit[15] = true;
        return bounceRecyclerView;
    }

    public void addChild(WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addChild(wXComponent, i);
        if (wXComponent == null) {
            $jacocoInit[16] = true;
        } else if (i < -1) {
            $jacocoInit[17] = true;
        } else {
            setRefreshOrLoading(wXComponent);
            $jacocoInit[19] = true;
            if (getHostView() == null) {
                $jacocoInit[20] = true;
            } else if (!hasColumnPros()) {
                $jacocoInit[21] = true;
            } else {
                $jacocoInit[22] = true;
                updateRecyclerAttr();
                $jacocoInit[23] = true;
                ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
                $jacocoInit[24] = true;
            }
            $jacocoInit[25] = true;
            return;
        }
        $jacocoInit[18] = true;
    }

    private boolean hasColumnPros() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!getAttrs().containsKey(Constants.Name.COLUMN_WIDTH)) {
            $jacocoInit[26] = true;
        } else if (this.mColumnWidth != WXUtils.parseFloat(getAttrs().get(Constants.Name.COLUMN_WIDTH))) {
            $jacocoInit[27] = true;
            $jacocoInit[37] = true;
            z = true;
            $jacocoInit[39] = true;
            return z;
        } else {
            $jacocoInit[28] = true;
        }
        $jacocoInit[29] = true;
        if (!getAttrs().containsKey(Constants.Name.COLUMN_COUNT)) {
            $jacocoInit[30] = true;
        } else if (this.mColumnCount != WXUtils.parseInt(getAttrs().get(Constants.Name.COLUMN_COUNT))) {
            $jacocoInit[31] = true;
            $jacocoInit[37] = true;
            z = true;
            $jacocoInit[39] = true;
            return z;
        } else {
            $jacocoInit[32] = true;
        }
        $jacocoInit[33] = true;
        if (!getAttrs().containsKey(Constants.Name.COLUMN_GAP)) {
            $jacocoInit[34] = true;
        } else if (this.mColumnGap == WXUtils.parseFloat(getAttrs().get(Constants.Name.COLUMN_GAP))) {
            $jacocoInit[35] = true;
        } else {
            $jacocoInit[36] = true;
            $jacocoInit[37] = true;
            z = true;
            $jacocoInit[39] = true;
            return z;
        }
        z = false;
        $jacocoInit[38] = true;
        $jacocoInit[39] = true;
        return z;
    }

    private boolean setRefreshOrLoading(final WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[40] = true;
            WXLogUtils.e(this.TAG, "setRefreshOrLoading: HostView == null !!!!!! check list attr has append =tree");
            $jacocoInit[41] = true;
            return true;
        } else if (wXComponent instanceof WXRefresh) {
            $jacocoInit[42] = true;
            ((BounceRecyclerView) getHostView()).setOnRefreshListener((WXRefresh) wXComponent);
            $jacocoInit[43] = true;
            ((BounceRecyclerView) getHostView()).postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXListComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(2705184748917078080L, "com/taobao/weex/ui/component/list/WXListComponent$1", 2);
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
            $jacocoInit[44] = true;
            return true;
        } else if (wXComponent instanceof WXLoading) {
            $jacocoInit[45] = true;
            ((BounceRecyclerView) getHostView()).setOnLoadingListener((WXLoading) wXComponent);
            $jacocoInit[46] = true;
            ((BounceRecyclerView) getHostView()).postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXListComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-6427675325963801270L, "com/taobao/weex/ui/component/list/WXListComponent$2", 2);
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
            $jacocoInit[47] = true;
            return true;
        } else {
            $jacocoInit[48] = true;
            return false;
        }
    }

    private void updateRecyclerAttr() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mColumnCount = WXUtils.parseInt(getAttrs().get(Constants.Name.COLUMN_COUNT));
        if (this.mColumnCount > 0) {
            $jacocoInit[49] = true;
        } else if (this.mLayoutType == 1) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            ArrayMap arrayMap = new ArrayMap();
            $jacocoInit[52] = true;
            arrayMap.put("componentType", getComponentType());
            $jacocoInit[53] = true;
            String instanceId = getInstanceId();
            WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_LIST_INVALID_COLUMN_COUNT;
            Locale locale = Locale.ENGLISH;
            int i = this.mColumnCount;
            $jacocoInit[54] = true;
            Object[] objArr = {Integer.valueOf(i)};
            $jacocoInit[55] = true;
            String format = String.format(locale, "You are trying to set the list/recycler/vlist/waterfall's column to %d, which is illegal. The column count should be a positive integer", objArr);
            $jacocoInit[56] = true;
            WXExceptionUtils.commitCriticalExceptionRT(instanceId, wXErrorCode, Constants.Name.COLUMN_COUNT, format, arrayMap);
            this.mColumnCount = 1;
            $jacocoInit[57] = true;
        }
        this.mColumnGap = WXUtils.parseFloat(getAttrs().get(Constants.Name.COLUMN_GAP));
        $jacocoInit[58] = true;
        this.mColumnWidth = WXUtils.parseFloat(getAttrs().get(Constants.Name.COLUMN_WIDTH));
        $jacocoInit[59] = true;
        this.mPaddingLeft = WXUtils.parseFloat(getAttrs().get("paddingLeft"));
        $jacocoInit[60] = true;
        this.mPaddingRight = WXUtils.parseFloat(getAttrs().get("paddingRight"));
        $jacocoInit[61] = true;
        this.mSpanOffsetsStr = (String) getAttrs().get(Constants.Name.SPAN_OFFSETS);
        try {
            $jacocoInit[62] = true;
            if (!TextUtils.isEmpty(this.mSpanOffsetsStr)) {
                $jacocoInit[63] = true;
                List<Float> parseArray = JSON.parseArray(this.mSpanOffsetsStr, Float.class);
                $jacocoInit[64] = true;
                int size = parseArray.size();
                if (this.mSpanOffsets == null) {
                    $jacocoInit[65] = true;
                } else if (this.mSpanOffsets.length == size) {
                    $jacocoInit[66] = true;
                    parseArray.toArray(this.mSpanOffsets);
                    $jacocoInit[69] = true;
                } else {
                    $jacocoInit[67] = true;
                }
                this.mSpanOffsets = new Float[size];
                $jacocoInit[68] = true;
                parseArray.toArray(this.mSpanOffsets);
                $jacocoInit[69] = true;
            } else {
                this.mSpanOffsets = null;
                $jacocoInit[70] = true;
            }
            $jacocoInit[71] = true;
        } catch (Throwable th) {
            $jacocoInit[72] = true;
            WXLogUtils.w("Parser SpanOffsets error ", th);
            $jacocoInit[73] = true;
        }
        if (this.hasSetGapItemDecoration) {
            $jacocoInit[74] = true;
        } else if (getSpanOffsets() == null) {
            $jacocoInit[75] = true;
        } else if (getHostView() == null) {
            $jacocoInit[76] = true;
        } else {
            $jacocoInit[77] = true;
            if (((BounceRecyclerView) getHostView()).getInnerView() == null) {
                $jacocoInit[78] = true;
            } else {
                this.hasSetGapItemDecoration = true;
                $jacocoInit[79] = true;
                ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).addItemDecoration(new GapItemDecoration(this));
                $jacocoInit[80] = true;
            }
        }
        $jacocoInit[81] = true;
    }

    @WXComponentProp(name = "spanOffsets")
    public void setSpanOffsets(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.equals(str, this.mSpanOffsetsStr)) {
            $jacocoInit[82] = true;
        } else {
            $jacocoInit[83] = true;
            markComponentUsable();
            $jacocoInit[84] = true;
            updateRecyclerAttr();
            $jacocoInit[85] = true;
            $jacocoInit[86] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[87] = true;
        }
        $jacocoInit[88] = true;
    }

    @WXComponentProp(name = "columnWidth")
    public void setColumnWidth(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (f == this.mColumnWidth) {
            $jacocoInit[89] = true;
        } else {
            $jacocoInit[90] = true;
            markComponentUsable();
            $jacocoInit[91] = true;
            updateRecyclerAttr();
            $jacocoInit[92] = true;
            $jacocoInit[93] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[94] = true;
        }
        $jacocoInit[95] = true;
    }

    @WXComponentProp(name = "columnCount")
    public void setColumnCount(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == this.mColumnCount) {
            $jacocoInit[96] = true;
        } else {
            $jacocoInit[97] = true;
            markComponentUsable();
            $jacocoInit[98] = true;
            updateRecyclerAttr();
            $jacocoInit[99] = true;
            $jacocoInit[100] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[101] = true;
        }
        $jacocoInit[102] = true;
    }

    @WXComponentProp(name = "columnGap")
    public void setColumnGap(float f) throws InterruptedException {
        boolean[] $jacocoInit = $jacocoInit();
        if (f == this.mColumnGap) {
            $jacocoInit[103] = true;
        } else {
            $jacocoInit[104] = true;
            markComponentUsable();
            $jacocoInit[105] = true;
            updateRecyclerAttr();
            $jacocoInit[106] = true;
            $jacocoInit[107] = true;
            ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
            $jacocoInit[108] = true;
        }
        $jacocoInit[109] = true;
    }

    @WXComponentProp(name = "scrollable")
    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[110] = true;
        ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).setScrollable(z);
        $jacocoInit[111] = true;
    }

    public void updateProperties(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateProperties(map);
        $jacocoInit[112] = true;
        if (!isRecycler(this)) {
            $jacocoInit[113] = true;
        } else {
            $jacocoInit[114] = true;
            if (WXBasicComponentType.WATERFALL.equals(getComponentType())) {
                this.mLayoutType = 3;
                $jacocoInit[115] = true;
            } else {
                this.mLayoutType = getAttrs().getLayoutType();
                $jacocoInit[116] = true;
            }
        }
        if (map.containsKey("padding")) {
            $jacocoInit[117] = true;
        } else {
            $jacocoInit[118] = true;
            if (map.containsKey("paddingLeft")) {
                $jacocoInit[119] = true;
            } else {
                $jacocoInit[120] = true;
                if (!map.containsKey("paddingRight")) {
                    $jacocoInit[121] = true;
                    $jacocoInit[130] = true;
                }
                $jacocoInit[122] = true;
            }
        }
        if (this.mPaddingLeft != WXUtils.parseFloat(map.get("paddingLeft"))) {
            $jacocoInit[123] = true;
        } else if (this.mPaddingRight == WXUtils.parseFloat(map.get("paddingRight"))) {
            $jacocoInit[124] = true;
            $jacocoInit[130] = true;
        } else {
            $jacocoInit[125] = true;
        }
        markComponentUsable();
        $jacocoInit[126] = true;
        updateRecyclerAttr();
        $jacocoInit[127] = true;
        $jacocoInit[128] = true;
        ((WXRecyclerView) ((BounceRecyclerView) getHostView()).getInnerView()).initView(getContext(), this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
        $jacocoInit[129] = true;
        $jacocoInit[130] = true;
    }

    public void createChildViewAt(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i >= 0) {
            $jacocoInit[131] = true;
        } else {
            $jacocoInit[132] = true;
            i = childCount() - 1;
            if (i >= 0) {
                $jacocoInit[133] = true;
            } else {
                $jacocoInit[134] = true;
                return;
            }
        }
        final WXComponent child = getChild(i);
        if (child instanceof WXBaseRefresh) {
            $jacocoInit[135] = true;
            child.createView();
            if (child instanceof WXRefresh) {
                $jacocoInit[136] = true;
                ((BounceRecyclerView) getHostView()).setOnRefreshListener((WXRefresh) child);
                $jacocoInit[137] = true;
                ((BounceRecyclerView) getHostView()).postDelayed(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXListComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(1282172731763909142L, "com/taobao/weex/ui/component/list/WXListComponent$3", 2);
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
                        ((BounceRecyclerView) this.this$0.getHostView()).setHeaderView(child);
                        $jacocoInit[1] = true;
                    }
                }, 100);
                $jacocoInit[138] = true;
            } else if (!(child instanceof WXLoading)) {
                $jacocoInit[139] = true;
            } else {
                $jacocoInit[140] = true;
                ((BounceRecyclerView) getHostView()).setOnLoadingListener((WXLoading) child);
                $jacocoInit[141] = true;
                ((BounceRecyclerView) getHostView()).postDelayed(new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXListComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-4231018772865629898L, "com/taobao/weex/ui/component/list/WXListComponent$4", 2);
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
                        ((BounceRecyclerView) this.this$0.getHostView()).setFooterView(child);
                        $jacocoInit[1] = true;
                    }
                }, 100);
                $jacocoInit[142] = true;
            }
        } else {
            super.createChildViewAt(i);
            $jacocoInit[143] = true;
        }
        $jacocoInit[144] = true;
    }

    public void remove(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        super.remove(wXComponent, z);
        $jacocoInit[145] = true;
        removeFooterOrHeader(wXComponent);
        $jacocoInit[146] = true;
    }

    private void removeFooterOrHeader(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent instanceof WXLoading) {
            $jacocoInit[147] = true;
            ((BounceRecyclerView) getHostView()).removeFooterView(wXComponent);
            $jacocoInit[148] = true;
        } else if (!(wXComponent instanceof WXRefresh)) {
            $jacocoInit[149] = true;
        } else {
            $jacocoInit[150] = true;
            ((BounceRecyclerView) getHostView()).removeHeaderView(wXComponent);
            $jacocoInit[151] = true;
        }
        $jacocoInit[152] = true;
    }

    private boolean isRecycler(WXComponent wXComponent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (WXBasicComponentType.WATERFALL.equals(wXComponent.getComponentType())) {
            $jacocoInit[153] = true;
        } else {
            $jacocoInit[154] = true;
            if (WXBasicComponentType.RECYCLE_LIST.equals(wXComponent.getComponentType())) {
                $jacocoInit[155] = true;
            } else {
                $jacocoInit[156] = true;
                if (WXBasicComponentType.RECYCLER.equals(wXComponent.getComponentType())) {
                    $jacocoInit[157] = true;
                } else {
                    z = false;
                    $jacocoInit[159] = true;
                    $jacocoInit[160] = true;
                    return z;
                }
            }
        }
        $jacocoInit[158] = true;
        z = true;
        $jacocoInit[160] = true;
        return z;
    }

    public Float[] getSpanOffsets() {
        boolean[] $jacocoInit = $jacocoInit();
        Float[] fArr = this.mSpanOffsets;
        $jacocoInit[161] = true;
        return fArr;
    }
}
