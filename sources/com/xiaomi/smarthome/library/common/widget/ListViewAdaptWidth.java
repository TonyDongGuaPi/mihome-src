package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class ListViewAdaptWidth extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private Context f18879a;

    public ListViewAdaptWidth(Context context) {
        super(context);
        this.f18879a = context;
    }

    public ListViewAdaptWidth(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f18879a = context;
    }

    public ListViewAdaptWidth(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f18879a = context;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(getMaxWidthOfChildren() + getPaddingLeft() + getPaddingRight(), 1073741824), i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getMaxWidthOfChildren() {
        /*
            r6 = this;
            r0 = 0
            android.widget.ListAdapter r1 = r6.getAdapter()     // Catch:{ Exception -> 0x002a }
            int r1 = r1.getCount()     // Catch:{ Exception -> 0x002a }
            r2 = 0
            r4 = r2
            r2 = 0
            r3 = 0
        L_0x000d:
            if (r2 >= r1) goto L_0x0030
            android.widget.ListAdapter r5 = r6.getAdapter()     // Catch:{ Exception -> 0x0028 }
            android.view.View r4 = r5.getView(r2, r4, r6)     // Catch:{ Exception -> 0x0028 }
            r4.measure(r0, r0)     // Catch:{ Exception -> 0x0028 }
            int r5 = r4.getMeasuredWidth()     // Catch:{ Exception -> 0x0028 }
            if (r5 <= r3) goto L_0x0025
            int r5 = r4.getMeasuredWidth()     // Catch:{ Exception -> 0x0028 }
            r3 = r5
        L_0x0025:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x0028:
            r0 = move-exception
            goto L_0x002d
        L_0x002a:
            r1 = move-exception
            r0 = r1
            r3 = 0
        L_0x002d:
            r0.printStackTrace()
        L_0x0030:
            android.content.Context r0 = r6.f18879a
            r1 = 1095761920(0x41500000, float:13.0)
            int r0 = com.xiaomi.smarthome.shop.utils.DisplayUtils.b((android.content.Context) r0, (float) r1)
            int r3 = r3 + r0
            android.content.res.Resources r0 = r6.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r0 = r0.widthPixels
            android.content.Context r1 = r6.f18879a
            r2 = 1099431936(0x41880000, float:17.0)
            int r1 = com.xiaomi.smarthome.shop.utils.DisplayUtils.b((android.content.Context) r1, (float) r2)
            int r1 = r1 * 2
            int r1 = r0 - r1
            if (r3 < r1) goto L_0x005b
            android.content.Context r1 = r6.f18879a
            int r1 = com.xiaomi.smarthome.shop.utils.DisplayUtils.b((android.content.Context) r1, (float) r2)
            int r1 = r1 * 2
            int r3 = r0 - r1
        L_0x005b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.ListViewAdaptWidth.getMaxWidthOfChildren():int");
    }
}
