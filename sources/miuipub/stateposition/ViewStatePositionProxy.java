package miuipub.stateposition;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import miuipub.v6.R;

public class ViewStatePositionProxy implements StatePosition {
    private View f;
    private int g = -1;

    public ViewStatePositionProxy(View view, AttributeSet attributeSet) {
        this.f = view;
        a(view, attributeSet);
    }

    public void a() {
        if (this.g != -1) {
            this.f.invalidate();
            this.f.refreshDrawableState();
        }
    }

    public void a(View view, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.V6_ViewStatePosition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.V6_ViewStatePosition_v6_state_first) {
                if (obtainStyledAttributes.getBoolean(index, false)) {
                    this.g = R.attr.v6_state_first;
                }
            } else if (index == R.styleable.V6_ViewStatePosition_v6_state_middle) {
                if (obtainStyledAttributes.getBoolean(index, false)) {
                    this.g = R.attr.v6_state_middle;
                }
            } else if (index == R.styleable.V6_ViewStatePosition_v6_state_last) {
                if (obtainStyledAttributes.getBoolean(index, false)) {
                    this.g = R.attr.v6_state_last;
                }
            } else if (index == R.styleable.V6_ViewStatePosition_v6_state_single && obtainStyledAttributes.getBoolean(index, false)) {
                this.g = R.attr.v6_state_single;
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void setPosition(int i) {
        switch (i) {
            case -1:
                b(0);
                return;
            case 0:
                b(R.attr.v6_state_first);
                return;
            case 1:
                b(R.attr.v6_state_middle);
                return;
            case 2:
                b(R.attr.v6_state_last);
                return;
            case 3:
                b(R.attr.v6_state_single);
                return;
            default:
                return;
        }
    }

    private void b(int i) {
        if (i != this.g) {
            this.g = i;
            this.f.invalidate();
            this.f.refreshDrawableState();
        }
    }

    public int[] a(int i) {
        if (this.g == 0) {
            return ((ViewStatePosition) this.f).onSuperCreateDrawableState(i);
        }
        return a(((ViewStatePosition) this.f).onSuperCreateDrawableState(i + 1), new int[]{this.g});
    }

    private int[] a(int[] iArr, int[] iArr2) {
        int length = iArr.length - 1;
        while (length >= 0 && iArr[length] == 0) {
            length--;
        }
        System.arraycopy(iArr2, 0, iArr, length + 1, iArr2.length);
        return iArr;
    }
}
