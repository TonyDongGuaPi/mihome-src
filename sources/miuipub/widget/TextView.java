package miuipub.widget;

import android.content.Context;
import android.util.AttributeSet;
import miuipub.stateposition.ViewStatePosition;
import miuipub.stateposition.ViewStatePositionProxy;

public class TextView extends android.widget.TextView implements ViewStatePosition {
    private ViewStatePositionProxy mProxy;

    public TextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProxy = new ViewStatePositionProxy(this, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mProxy.a();
    }

    public void setPosition(int i) {
        this.mProxy.setPosition(i);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.mProxy == null) {
            return super.onCreateDrawableState(i);
        }
        return this.mProxy.a(i);
    }

    public int[] onSuperCreateDrawableState(int i) {
        return super.onCreateDrawableState(i);
    }
}
