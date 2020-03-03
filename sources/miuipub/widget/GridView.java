package miuipub.widget;

import android.content.Context;
import android.util.AttributeSet;
import miuipub.stateposition.GridViewStatePositionProxy;

public class GridView extends android.widget.GridView {

    /* renamed from: a  reason: collision with root package name */
    private GridViewStatePositionProxy f3084a;

    public GridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3084a = new GridViewStatePositionProxy(this);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f3084a.a();
    }
}
