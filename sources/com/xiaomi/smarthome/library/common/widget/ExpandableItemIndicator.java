package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;

public class ExpandableItemIndicator extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private Impl f18835a;

    static abstract class Impl {
        public abstract void a(Context context, AttributeSet attributeSet, int i, ExpandableItemIndicator expandableItemIndicator);

        public abstract void a(boolean z, boolean z2);

        Impl() {
        }
    }

    public ExpandableItemIndicator(Context context) {
        super(context);
        onInit(context, (AttributeSet) null, 0);
    }

    public ExpandableItemIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        onInit(context, attributeSet, 0);
    }

    public ExpandableItemIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        onInit(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onInit(Context context, AttributeSet attributeSet, int i) {
        this.f18835a = new ExpandableItemIndicatorImplImageView();
        this.f18835a.a(context, attributeSet, i, this);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        super.dispatchFreezeSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        super.dispatchThawSelfOnly(sparseArray);
    }

    public void setExpandedState(boolean z, boolean z2) {
        this.f18835a.a(z, z2);
    }
}
