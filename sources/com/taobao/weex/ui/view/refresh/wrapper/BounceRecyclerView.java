package com.taobao.weex.ui.view.refresh.wrapper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import com.taobao.weex.ui.component.list.ListComponentView;
import com.taobao.weex.ui.component.list.StickyHeaderHelper;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.listview.adapter.RecyclerViewBaseAdapter;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BounceRecyclerView extends BaseBounceView<WXRecyclerView> implements ListComponentView, WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int DEFAULT_COLUMN_COUNT = 1;
    public static final int DEFAULT_COLUMN_GAP = 1;
    private RecyclerViewBaseAdapter adapter;
    private int mColumnCount;
    private float mColumnGap;
    private WXGesture mGesture;
    private int mLayoutType;
    private StickyHeaderHelper mStickyHeaderHelper;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3029389453998689286L, "com/taobao/weex/ui/view/refresh/wrapper/BounceRecyclerView", 33);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ WXRecyclerView getInnerView() {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerView wXRecyclerView = (WXRecyclerView) super.getInnerView();
        $jacocoInit[32] = true;
        return wXRecyclerView;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BounceRecyclerView(Context context, int i, int i2, float f, int i3) {
        super(context, i3);
        boolean[] $jacocoInit = $jacocoInit();
        this.adapter = null;
        this.mLayoutType = 1;
        this.mColumnCount = 1;
        this.mColumnGap = 1.0f;
        this.mLayoutType = i;
        this.mColumnCount = i2;
        this.mColumnGap = f;
        $jacocoInit[0] = true;
        init(context);
        $jacocoInit[1] = true;
        this.mStickyHeaderHelper = new StickyHeaderHelper(this);
        $jacocoInit[2] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BounceRecyclerView(Context context, int i, int i2) {
        this(context, i, 1, 1.0f, i2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[3] = true;
    }

    public void setRecyclerViewBaseAdapter(RecyclerViewBaseAdapter recyclerViewBaseAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.adapter = recyclerViewBaseAdapter;
        $jacocoInit[4] = true;
        if (getInnerView() == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            ((WXRecyclerView) getInnerView()).setAdapter(recyclerViewBaseAdapter);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }

    public RecyclerViewBaseAdapter getRecyclerViewBaseAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        RecyclerViewBaseAdapter recyclerViewBaseAdapter = this.adapter;
        $jacocoInit[9] = true;
        return recyclerViewBaseAdapter;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.mGesture == null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            dispatchTouchEvent |= this.mGesture.onTouch(this, motionEvent);
            $jacocoInit[12] = true;
        }
        $jacocoInit[13] = true;
        return dispatchTouchEvent;
    }

    public WXRecyclerView setInnerView(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRecyclerView wXRecyclerView = new WXRecyclerView(context);
        $jacocoInit[14] = true;
        wXRecyclerView.initView(context, this.mLayoutType, this.mColumnCount, this.mColumnGap, getOrientation());
        $jacocoInit[15] = true;
        return wXRecyclerView;
    }

    public void onRefreshingComplete() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.adapter == null) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            this.adapter.notifyDataSetChanged();
            $jacocoInit[18] = true;
        }
        $jacocoInit[19] = true;
    }

    public void onLoadmoreComplete() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.adapter == null) {
            $jacocoInit[20] = true;
        } else {
            $jacocoInit[21] = true;
            this.adapter.notifyDataSetChanged();
            $jacocoInit[22] = true;
        }
        $jacocoInit[23] = true;
    }

    public void notifyStickyShow(WXCell wXCell) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStickyHeaderHelper.notifyStickyShow(wXCell);
        $jacocoInit[24] = true;
    }

    public void updateStickyView(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStickyHeaderHelper.updateStickyView(i);
        $jacocoInit[25] = true;
    }

    public void notifyStickyRemove(WXCell wXCell) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStickyHeaderHelper.notifyStickyRemove(wXCell);
        $jacocoInit[26] = true;
    }

    public StickyHeaderHelper getStickyHeaderHelper() {
        boolean[] $jacocoInit = $jacocoInit();
        StickyHeaderHelper stickyHeaderHelper = this.mStickyHeaderHelper;
        $jacocoInit[27] = true;
        return stickyHeaderHelper;
    }

    public void registerGestureListener(@Nullable WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mGesture = wXGesture;
        $jacocoInit[28] = true;
        ((WXRecyclerView) getInnerView()).registerGestureListener(wXGesture);
        $jacocoInit[29] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.mGesture;
        $jacocoInit[30] = true;
        return wXGesture;
    }
}
