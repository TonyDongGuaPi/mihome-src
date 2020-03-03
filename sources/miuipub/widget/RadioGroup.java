package miuipub.widget;

import android.content.Context;
import android.util.AttributeSet;
import miuipub.stateposition.ViewStatePosition;
import miuipub.stateposition.ViewStatePositionProxy;

public class RadioGroup extends android.widget.RadioGroup implements ViewStatePosition {
    private ViewStatePositionProxy f;

    public RadioGroup(Context context) {
        this(context, (AttributeSet) null);
    }

    public RadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new ViewStatePositionProxy(this, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.f.a();
    }

    public void setPosition(int i) {
        this.f.setPosition(i);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.f == null) {
            return super.onCreateDrawableState(i);
        }
        return this.f.a(i);
    }

    public int[] onSuperCreateDrawableState(int i) {
        return super.onCreateDrawableState(i);
    }
}
