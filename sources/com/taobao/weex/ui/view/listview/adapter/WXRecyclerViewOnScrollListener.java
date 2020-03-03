package com.taobao.weex.ui.view.listview.adapter;

import android.support.v7.widget.RecyclerView;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    private static transient /* synthetic */ boolean[] $jacocoData;
    protected LAYOUT_MANAGER_TYPE layoutManagerType;
    private WeakReference<IOnLoadMoreListener> listener;
    private int mCurrentScrollState = 0;
    private int[] mFirstPositions;
    private int mFirstVisibleItemPosition;
    private int[] mLastPositions;
    private int mLastVisibleItemPosition;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1747445728997325785L, "com/taobao/weex/ui/view/listview/adapter/WXRecyclerViewOnScrollListener", 56);
        $jacocoData = a2;
        return a2;
    }

    public WXRecyclerViewOnScrollListener(IOnLoadMoreListener iOnLoadMoreListener) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.listener = new WeakReference<>(iOnLoadMoreListener);
        $jacocoInit[1] = true;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onScrollStateChanged(recyclerView, i);
        this.mCurrentScrollState = i;
        $jacocoInit[2] = true;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        $jacocoInit[3] = true;
        int childCount = layoutManager.getChildCount();
        $jacocoInit[4] = true;
        int itemCount = layoutManager.getItemCount();
        if (childCount == 0) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            int height = (((itemCount - this.mLastVisibleItemPosition) - 1) * recyclerView.getHeight()) / childCount;
            if (childCount <= 0) {
                $jacocoInit[7] = true;
            } else if (this.mCurrentScrollState != 0) {
                $jacocoInit[8] = true;
            } else {
                $jacocoInit[9] = true;
                if (this.listener == null) {
                    $jacocoInit[10] = true;
                } else if (this.listener.get() == null) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    ((IOnLoadMoreListener) this.listener.get()).onLoadMore(height);
                    $jacocoInit[13] = true;
                }
            }
        }
        $jacocoInit[14] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScrolled(android.support.v7.widget.RecyclerView r6, int r7, int r8) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            super.onScrolled(r6, r7, r8)
            r1 = 1
            r2 = 15
            r0[r2] = r1
            android.support.v7.widget.RecyclerView$LayoutManager r6 = r6.getLayoutManager()
            java.lang.ref.WeakReference<com.taobao.weex.ui.view.listview.adapter.IOnLoadMoreListener> r2 = r5.listener
            if (r2 != 0) goto L_0x0019
            r6 = 16
            r0[r6] = r1
            return
        L_0x0019:
            java.lang.ref.WeakReference<com.taobao.weex.ui.view.listview.adapter.IOnLoadMoreListener> r2 = r5.listener
            java.lang.Object r2 = r2.get()
            com.taobao.weex.ui.view.listview.adapter.IOnLoadMoreListener r2 = (com.taobao.weex.ui.view.listview.adapter.IOnLoadMoreListener) r2
            if (r2 != 0) goto L_0x0029
            r6 = 17
            r0[r6] = r1
            goto L_0x0123
        L_0x0029:
            r3 = 18
            r0[r3] = r1
            r2.onBeforeScroll(r7, r8)
            boolean r3 = r6 instanceof android.support.v7.widget.LinearLayoutManager
            if (r3 == 0) goto L_0x005b
            com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener$LAYOUT_MANAGER_TYPE r3 = com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener.LAYOUT_MANAGER_TYPE.LINEAR
            r5.layoutManagerType = r3
            android.support.v7.widget.LinearLayoutManager r6 = (android.support.v7.widget.LinearLayoutManager) r6
            r3 = 19
            r0[r3] = r1
            int r3 = r6.findLastVisibleItemPosition()
            r5.mLastVisibleItemPosition = r3
            r3 = 20
            r0[r3] = r1
            int r6 = r6.findFirstVisibleItemPosition()
            r3 = 21
            r0[r3] = r1
            int r3 = r5.mLastVisibleItemPosition
            r2.notifyAppearStateChange(r6, r3, r7, r8)
            r6 = 22
            r0[r6] = r1
            goto L_0x0123
        L_0x005b:
            boolean r3 = r6 instanceof android.support.v7.widget.GridLayoutManager
            if (r3 == 0) goto L_0x0082
            com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener$LAYOUT_MANAGER_TYPE r3 = com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener.LAYOUT_MANAGER_TYPE.GRID
            r5.layoutManagerType = r3
            android.support.v7.widget.GridLayoutManager r6 = (android.support.v7.widget.GridLayoutManager) r6
            r3 = 23
            r0[r3] = r1
            int r3 = r6.findLastVisibleItemPosition()
            r5.mLastVisibleItemPosition = r3
            r3 = 24
            r0[r3] = r1
            int r6 = r6.findFirstVisibleItemPosition()
            int r3 = r5.mLastVisibleItemPosition
            r2.notifyAppearStateChange(r6, r3, r7, r8)
            r6 = 25
            r0[r6] = r1
            goto L_0x0123
        L_0x0082:
            boolean r3 = r6 instanceof android.support.v7.widget.StaggeredGridLayoutManager
            if (r3 == 0) goto L_0x0128
            com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener$LAYOUT_MANAGER_TYPE r3 = com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener.LAYOUT_MANAGER_TYPE.STAGGERED_GRID
            r5.layoutManagerType = r3
            android.support.v7.widget.StaggeredGridLayoutManager r6 = (android.support.v7.widget.StaggeredGridLayoutManager) r6
            r3 = 26
            r0[r3] = r1
            int r3 = r6.getSpanCount()
            int[] r4 = r5.mLastPositions
            if (r4 != 0) goto L_0x009d
            r4 = 27
            r0[r4] = r1
            goto L_0x00ab
        L_0x009d:
            int[] r4 = r5.mLastPositions
            int r4 = r4.length
            if (r3 != r4) goto L_0x00a7
            r4 = 28
            r0[r4] = r1
            goto L_0x00b3
        L_0x00a7:
            r4 = 29
            r0[r4] = r1
        L_0x00ab:
            int[] r4 = new int[r3]
            r5.mLastPositions = r4
            r4 = 30
            r0[r4] = r1
        L_0x00b3:
            int[] r4 = r5.mFirstPositions
            if (r4 != 0) goto L_0x00bc
            r4 = 31
            r0[r4] = r1
            goto L_0x00ca
        L_0x00bc:
            int[] r4 = r5.mFirstPositions
            int r4 = r4.length
            if (r3 != r4) goto L_0x00c6
            r3 = 32
            r0[r3] = r1
            goto L_0x00d2
        L_0x00c6:
            r4 = 33
            r0[r4] = r1
        L_0x00ca:
            int[] r3 = new int[r3]
            r5.mFirstPositions = r3
            r3 = 34
            r0[r3] = r1     // Catch:{ Exception -> 0x0108 }
        L_0x00d2:
            int[] r3 = r5.mFirstPositions     // Catch:{ Exception -> 0x0108 }
            r6.findFirstVisibleItemPositions(r3)     // Catch:{ Exception -> 0x0108 }
            r3 = 35
            r0[r3] = r1     // Catch:{ Exception -> 0x0108 }
            int[] r3 = r5.mFirstPositions     // Catch:{ Exception -> 0x0108 }
            int r3 = r5.findMin(r3)     // Catch:{ Exception -> 0x0108 }
            r5.mFirstVisibleItemPosition = r3     // Catch:{ Exception -> 0x0108 }
            r3 = 36
            r0[r3] = r1     // Catch:{ Exception -> 0x0108 }
            int[] r3 = r5.mLastPositions     // Catch:{ Exception -> 0x0108 }
            r6.findLastVisibleItemPositions(r3)     // Catch:{ Exception -> 0x0108 }
            r6 = 37
            r0[r6] = r1     // Catch:{ Exception -> 0x0108 }
            int[] r6 = r5.mLastPositions     // Catch:{ Exception -> 0x0108 }
            int r6 = r5.findMax(r6)     // Catch:{ Exception -> 0x0108 }
            r5.mLastVisibleItemPosition = r6     // Catch:{ Exception -> 0x0108 }
            r6 = 38
            r0[r6] = r1     // Catch:{ Exception -> 0x0108 }
            int r6 = r5.mFirstVisibleItemPosition     // Catch:{ Exception -> 0x0108 }
            int r3 = r5.mLastVisibleItemPosition     // Catch:{ Exception -> 0x0108 }
            r2.notifyAppearStateChange(r6, r3, r7, r8)     // Catch:{ Exception -> 0x0108 }
            r6 = 39
            r0[r6] = r1
            goto L_0x011f
        L_0x0108:
            r6 = move-exception
            r7 = 40
            r0[r7] = r1
            r6.printStackTrace()
            r7 = 41
            r0[r7] = r1
            java.lang.String r6 = r6.toString()
            com.taobao.weex.utils.WXLogUtils.e(r6)
            r6 = 42
            r0[r6] = r1
        L_0x011f:
            r6 = 43
            r0[r6] = r1
        L_0x0123:
            r6 = 45
            r0[r6] = r1
            return
        L_0x0128:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager"
            r6.<init>(r7)
            r7 = 44
            r0[r7] = r1
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.listview.adapter.WXRecyclerViewOnScrollListener.onScrolled(android.support.v7.widget.RecyclerView, int, int):void");
    }

    private int findMax(int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = 0;
        int i2 = iArr[0];
        int length = iArr.length;
        $jacocoInit[46] = true;
        while (i < length) {
            int i3 = iArr[i];
            if (i3 <= i2) {
                $jacocoInit[47] = true;
            } else {
                $jacocoInit[48] = true;
                i2 = i3;
            }
            i++;
            $jacocoInit[49] = true;
        }
        $jacocoInit[50] = true;
        return i2;
    }

    private int findMin(int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = 0;
        int i2 = iArr[0];
        int length = iArr.length;
        $jacocoInit[51] = true;
        while (i < length) {
            int i3 = iArr[i];
            if (i3 >= i2) {
                $jacocoInit[52] = true;
            } else {
                $jacocoInit[53] = true;
                i2 = i3;
            }
            i++;
            $jacocoInit[54] = true;
        }
        $jacocoInit[55] = true;
        return i2;
    }

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[5] = true;
        }
    }
}
