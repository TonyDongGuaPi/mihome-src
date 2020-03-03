package com.xiaomi.dragdrop;

import android.support.v7.widget.RecyclerView;

class TopBottomEdgeEffectDecorator extends BaseEdgeEffectDecorator {
    public TopBottomEdgeEffectDecorator(RecyclerView recyclerView) {
        super(recyclerView);
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 3;
            default:
                throw new IllegalArgumentException();
        }
    }
}
