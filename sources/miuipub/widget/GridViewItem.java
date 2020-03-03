package miuipub.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import miuipub.stateposition.BothWayStatePosition;
import miuipub.v6.R;

public class GridViewItem extends LinearLayout implements BothWayStatePosition {
    private int j;
    private int k;
    private int l;
    private int m;

    public GridViewItem(Context context) {
        this(context, (AttributeSet) null);
    }

    public GridViewItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = 3;
        this.k = 3;
        this.l = 0;
        this.m = 0;
        initializeChildrenSequenceStates(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        if (this.l != 0 || this.m != 0) {
            invalidate();
            refreshDrawableState();
        }
    }

    public void initializeChildrenSequenceStates(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.V6_DrawableStates);
        int length = R.styleable.V6_DrawableStates.length;
        int i = R.attr.v6_state_single_h;
        int i2 = R.attr.v6_state_single_v;
        for (int i3 = 0; i3 < length; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.V6_DrawableStates_v6_state_first_h) {
                i = R.attr.v6_state_first_h;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_middle_h) {
                i = R.attr.v6_state_middle_h;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_last_h) {
                i = R.attr.v6_state_last_h;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_single_h) {
                i = R.attr.v6_state_single_h;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_first_v) {
                i2 = R.attr.v6_state_first_v;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_middle_v) {
                i2 = R.attr.v6_state_middle_v;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_last_v) {
                i2 = R.attr.v6_state_last_v;
            } else if (index == R.styleable.V6_DrawableStates_v6_state_single_v) {
                i2 = R.attr.v6_state_single_v;
            }
        }
        obtainStyledAttributes.recycle();
        setPosition(i, i2);
    }

    public void setPosition(int i, int i2) {
        setHorizontalPosition(i);
        setVerticalPosition(i2);
        invalidate();
        refreshDrawableState();
    }

    public void setState(int i, int i2) {
        setAdditionalHorizontalState(i);
        setAdditionalVerticalState(i2);
        invalidate();
        refreshDrawableState();
    }

    public int getHorizontalState() {
        return this.l;
    }

    public int getVerticalState() {
        return this.m;
    }

    public int getHorizontalPosition() {
        return this.j;
    }

    public int getVerticalPosition() {
        return this.k;
    }

    private void setHorizontalPosition(int i) {
        this.j = i;
        switch (i) {
            case -1:
                setAdditionalHorizontalState(0);
                return;
            case 0:
                setAdditionalHorizontalState(R.attr.v6_state_first_h);
                return;
            case 1:
                setAdditionalHorizontalState(R.attr.v6_state_middle_h);
                return;
            case 2:
                setAdditionalHorizontalState(R.attr.v6_state_last_h);
                return;
            case 3:
                setAdditionalHorizontalState(R.attr.v6_state_single_h);
                return;
            default:
                return;
        }
    }

    private void setVerticalPosition(int i) {
        this.k = i;
        switch (i) {
            case -1:
                setAdditionalVerticalState(0);
                return;
            case 0:
                setAdditionalVerticalState(R.attr.v6_state_first_v);
                return;
            case 1:
                setAdditionalVerticalState(R.attr.v6_state_middle_v);
                return;
            case 2:
                setAdditionalVerticalState(R.attr.v6_state_last_v);
                return;
            case 3:
                setAdditionalVerticalState(R.attr.v6_state_single_v);
                return;
            default:
                return;
        }
    }

    private void setAdditionalHorizontalState(int i) {
        this.l = i;
    }

    private void setAdditionalVerticalState(int i) {
        this.m = i;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.l == 0 && this.m == 0) {
            return super.onCreateDrawableState(i);
        }
        return mergeDrawableStates(super.onCreateDrawableState(i + 2), new int[]{this.l, this.m});
    }
}
