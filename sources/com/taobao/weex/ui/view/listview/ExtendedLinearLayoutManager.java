package com.taobao.weex.ui.view.listview;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ExtendedLinearLayoutManager extends LinearLayoutManager {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private OnSmoothScrollEndListener onScrollEndListener;
    private RecyclerView.SmoothScroller smoothScroller;

    public interface OnSmoothScrollEndListener {
        void onStop();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3761034097990837760L, "com/taobao/weex/ui/view/listview/ExtendedLinearLayoutManager", 18);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ OnSmoothScrollEndListener access$000(ExtendedLinearLayoutManager extendedLinearLayoutManager) {
        boolean[] $jacocoInit = $jacocoInit();
        OnSmoothScrollEndListener onSmoothScrollEndListener = extendedLinearLayoutManager.onScrollEndListener;
        $jacocoInit[16] = true;
        return onSmoothScrollEndListener;
    }

    static /* synthetic */ OnSmoothScrollEndListener access$002(ExtendedLinearLayoutManager extendedLinearLayoutManager, OnSmoothScrollEndListener onSmoothScrollEndListener) {
        boolean[] $jacocoInit = $jacocoInit();
        extendedLinearLayoutManager.onScrollEndListener = onSmoothScrollEndListener;
        $jacocoInit[17] = true;
        return onSmoothScrollEndListener;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtendedLinearLayoutManager(Context context) {
        super(context, 1, false);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtendedLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    public boolean supportsPredictiveItemAnimations() {
        $jacocoInit()[2] = true;
        return false;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            super.onLayoutChildren(recycler, state);
            $jacocoInit[3] = true;
        } catch (IndexOutOfBoundsException e) {
            $jacocoInit[4] = true;
            e.printStackTrace();
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            int scrollVerticallyBy = super.scrollVerticallyBy(i, recycler, state);
            $jacocoInit[7] = true;
            return scrollVerticallyBy;
        } catch (Exception e) {
            $jacocoInit[8] = true;
            e.printStackTrace();
            $jacocoInit[9] = true;
            return 0;
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.smoothScroller != null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            this.smoothScroller = new TopSnappedSmoothScroller(this, recyclerView.getContext());
            $jacocoInit[12] = true;
        }
        this.smoothScroller.setTargetPosition(i);
        $jacocoInit[13] = true;
        startSmoothScroll(this.smoothScroller);
        $jacocoInit[14] = true;
    }

    private class TopSnappedSmoothScroller extends LinearSmoothScroller {
        private static transient /* synthetic */ boolean[] $jacocoData;
        final /* synthetic */ ExtendedLinearLayoutManager this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(3726794460658586355L, "com/taobao/weex/ui/view/listview/ExtendedLinearLayoutManager$TopSnappedSmoothScroller", 11);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TopSnappedSmoothScroller(ExtendedLinearLayoutManager extendedLinearLayoutManager, Context context) {
            super(context);
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = extendedLinearLayoutManager;
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
        }

        public PointF computeScrollVectorForPosition(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            ExtendedLinearLayoutManager extendedLinearLayoutManager = this.this$0;
            $jacocoInit[2] = true;
            PointF computeScrollVectorForPosition = extendedLinearLayoutManager.computeScrollVectorForPosition(i);
            $jacocoInit[3] = true;
            return computeScrollVectorForPosition;
        }

        /* access modifiers changed from: protected */
        public int getVerticalSnapPreference() {
            $jacocoInit()[4] = true;
            return -1;
        }

        /* access modifiers changed from: protected */
        public void onStop() {
            boolean[] $jacocoInit = $jacocoInit();
            super.onStop();
            $jacocoInit[5] = true;
            if (ExtendedLinearLayoutManager.access$000(this.this$0) == null) {
                $jacocoInit[6] = true;
            } else {
                $jacocoInit[7] = true;
                ExtendedLinearLayoutManager.access$000(this.this$0).onStop();
                $jacocoInit[8] = true;
                ExtendedLinearLayoutManager.access$002(this.this$0, (OnSmoothScrollEndListener) null);
                $jacocoInit[9] = true;
            }
            $jacocoInit[10] = true;
        }
    }

    public void setOnScrollEndListener(OnSmoothScrollEndListener onSmoothScrollEndListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.onScrollEndListener = onSmoothScrollEndListener;
        $jacocoInit[15] = true;
    }
}
