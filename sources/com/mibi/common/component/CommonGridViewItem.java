package com.mibi.common.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.FrameLayout;

public class CommonGridViewItem extends FrameLayout implements Checkable {

    /* renamed from: a  reason: collision with root package name */
    private boolean f7483a;
    private int[] b;

    public CommonGridViewItem(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonGridViewItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7483a = false;
        this.b = new int[]{16842912};
        setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewRemoved(View view, View view2) {
            }

            public void onChildViewAdded(View view, View view2) {
                view2.setDuplicateParentStateEnabled(true);
            }
        });
    }

    public void setChecked(boolean z) {
        this.f7483a = z;
        refreshDrawableState();
    }

    public boolean isChecked() {
        return this.f7483a;
    }

    public void toggle() {
        setChecked(!this.f7483a);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (!this.f7483a) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        mergeDrawableStates(onCreateDrawableState, this.b);
        return onCreateDrawableState;
    }
}
