package com.taobao.weex.ui.view.listview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXHeader;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ListBaseViewHolder extends RecyclerView.ViewHolder {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private boolean isRecycled;
    private WeakReference<WXComponent> mComponent;
    private int mViewType;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7844625096950889132L, "com/taobao/weex/ui/view/listview/adapter/ListBaseViewHolder", 40);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ListBaseViewHolder(WXComponent wXComponent, int i) {
        super(wXComponent.getHostView());
        boolean[] $jacocoInit = $jacocoInit();
        this.mViewType = i;
        $jacocoInit[0] = true;
        this.mComponent = new WeakReference<>(wXComponent);
        $jacocoInit[1] = true;
        this.isRecycled = wXComponent.canRecycled();
        $jacocoInit[2] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ListBaseViewHolder(WXComponent wXComponent, int i, boolean z) {
        this(wXComponent, i);
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.isRecycled) {
            $jacocoInit[3] = true;
        } else if (z) {
            $jacocoInit[4] = true;
        } else {
            z2 = false;
            $jacocoInit[6] = true;
            this.isRecycled = z2;
            $jacocoInit[7] = true;
        }
        $jacocoInit[5] = true;
        z2 = true;
        this.isRecycled = z2;
        $jacocoInit[7] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ListBaseViewHolder(View view, int i) {
        super(view);
        boolean[] $jacocoInit = $jacocoInit();
        this.mViewType = i;
        $jacocoInit[8] = true;
    }

    public boolean isRecycled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isRecycled;
        $jacocoInit[9] = true;
        return z;
    }

    public void recycled() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent == null) {
            $jacocoInit[10] = true;
        } else if (this.mComponent.get() == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            ((WXComponent) this.mComponent.get()).recycled();
            this.isRecycled = true;
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    public void bindData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent == null) {
            $jacocoInit[15] = true;
        } else if (this.mComponent.get() == null) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            ((WXComponent) this.mComponent.get()).bindData(wXComponent);
            this.isRecycled = false;
            $jacocoInit[18] = true;
        }
        $jacocoInit[19] = true;
    }

    public boolean isFullSpan() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent == null) {
            $jacocoInit[20] = true;
        } else if (!(this.mComponent.get() instanceof WXHeader)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            z = true;
            $jacocoInit[24] = true;
            return z;
        }
        z = false;
        $jacocoInit[23] = true;
        $jacocoInit[24] = true;
        return z;
    }

    public boolean canRecycled() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent == null) {
            $jacocoInit[25] = true;
        } else if (this.mComponent.get() == null) {
            $jacocoInit[26] = true;
        } else {
            $jacocoInit[27] = true;
            boolean canRecycled = ((WXComponent) this.mComponent.get()).canRecycled();
            $jacocoInit[28] = true;
            return canRecycled;
        }
        $jacocoInit[29] = true;
        return true;
    }

    public View getView() {
        boolean[] $jacocoInit = $jacocoInit();
        View view = this.itemView;
        $jacocoInit[30] = true;
        return view;
    }

    public int getViewType() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mViewType;
        $jacocoInit[31] = true;
        return i;
    }

    public void setComponentUsing(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent == null) {
            $jacocoInit[32] = true;
        } else if (this.mComponent.get() == null) {
            $jacocoInit[33] = true;
        } else {
            $jacocoInit[34] = true;
            ((WXComponent) this.mComponent.get()).setUsing(z);
            $jacocoInit[35] = true;
        }
        $jacocoInit[36] = true;
    }

    public WXComponent getComponent() {
        WXComponent wXComponent;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent != null) {
            wXComponent = (WXComponent) this.mComponent.get();
            $jacocoInit[37] = true;
        } else {
            wXComponent = null;
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
        return wXComponent;
    }
}
