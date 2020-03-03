package miuipub.widget;

import android.content.Context;
import android.util.AttributeSet;
import miuipub.stateposition.ViewStatePosition;
import miuipub.stateposition.ViewStatePositionProxy;

public class Button extends android.widget.Button implements ViewStatePosition {
    private ViewStatePositionProxy mProxy;

    public Button(Context context) {
        this(context, (AttributeSet) null);
    }

    public Button(Context context, AttributeSet attributeSet) {
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
