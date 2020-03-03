package com.taobao.weex.ui.component.list;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXViewUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GapItemDecoration extends RecyclerView.ItemDecoration {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXListComponent listComponent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1137709736691668348L, "com/taobao/weex/ui/component/list/GapItemDecoration", 21);
        $jacocoData = a2;
        return a2;
    }

    public GapItemDecoration(WXListComponent wXListComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.listComponent = wXListComponent;
        $jacocoInit[0] = true;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        Float[] spanOffsets = this.listComponent.getSpanOffsets();
        if (spanOffsets == null) {
            $jacocoInit[1] = true;
            return;
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition < 0) {
            $jacocoInit[2] = true;
            return;
        }
        if (!(view.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams)) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            $jacocoInit[5] = true;
            if (layoutParams.isFullSpan()) {
                $jacocoInit[6] = true;
                return;
            }
            WXComponent child = this.listComponent.getChild(childAdapterPosition);
            if (!(child instanceof WXCell)) {
                $jacocoInit[7] = true;
            } else {
                WXCell wXCell = (WXCell) child;
                $jacocoInit[8] = true;
                if (wXCell.isFixed()) {
                    $jacocoInit[9] = true;
                } else if (wXCell.isSticky()) {
                    $jacocoInit[10] = true;
                } else if (layoutParams.getSpanIndex() >= spanOffsets.length) {
                    $jacocoInit[12] = true;
                    return;
                } else {
                    if (this.listComponent.isLayoutRTL()) {
                        i = (spanOffsets.length - layoutParams.getSpanIndex()) - 1;
                        $jacocoInit[13] = true;
                    } else {
                        i = layoutParams.getSpanIndex();
                        $jacocoInit[14] = true;
                    }
                    $jacocoInit[15] = true;
                    float floatValue = this.listComponent.getSpanOffsets()[i].floatValue();
                    $jacocoInit[16] = true;
                    int round = Math.round(WXViewUtils.getRealPxByWidth(floatValue, this.listComponent.getViewPortWidth()));
                    $jacocoInit[17] = true;
                    if (this.listComponent.isLayoutRTL()) {
                        rect.left = -round;
                        rect.right = round;
                        $jacocoInit[18] = true;
                    } else {
                        rect.left = round;
                        rect.right = -round;
                        $jacocoInit[19] = true;
                    }
                }
                $jacocoInit[11] = true;
                return;
            }
        }
        $jacocoInit[20] = true;
    }
}
