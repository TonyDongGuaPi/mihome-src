package com.xiaomi.smarthome.library.common.widget;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.xiaomi.smarthome.library.common.widget.DragSortListView;

public class SimpleFloatViewManager implements DragSortListView.FloatViewManager {

    /* renamed from: a  reason: collision with root package name */
    private Bitmap f18929a;
    private ImageView b;
    private int c = -16777216;
    private ListView d;

    public void a(View view, Point point, Point point2) {
    }

    public SimpleFloatViewManager(ListView listView) {
        this.d = listView;
    }

    public void g(int i) {
        this.c = i;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:3|4|5|6|7|10|(1:12)|13|(1:15)|16|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004c, code lost:
        r6.f18929a = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View f(int r7) {
        /*
            r6 = this;
            android.widget.ListView r0 = r6.d
            android.widget.ListView r1 = r6.d
            int r1 = r1.getHeaderViewsCount()
            int r7 = r7 + r1
            android.widget.ListView r1 = r6.d
            int r1 = r1.getFirstVisiblePosition()
            int r7 = r7 - r1
            android.view.View r7 = r0.getChildAt(r7)
            r0 = 0
            if (r7 != 0) goto L_0x0018
            return r0
        L_0x0018:
            r1 = 0
            r7.setPressed(r1)
            r2 = 524288(0x80000, float:7.34684E-40)
            r7.setDrawingCacheQuality(r2)
            r2 = 1
            r7.setDrawingCacheEnabled(r2)
            r7.buildDrawingCache(r2)
            android.graphics.Bitmap r2 = r7.getDrawingCache()
            int r3 = r2.getWidth()
            float r3 = (float) r3
            r4 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 * r4
            int r3 = (int) r3
            int r5 = r2.getHeight()
            float r5 = (float) r5
            float r5 = r5 * r4
            int r4 = (int) r5
            android.graphics.Bitmap r5 = android.graphics.Bitmap.createScaledBitmap(r2, r3, r4, r1)     // Catch:{ OutOfMemoryError -> 0x0045 }
            r6.f18929a = r5     // Catch:{ OutOfMemoryError -> 0x0045 }
            goto L_0x004e
        L_0x0045:
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createScaledBitmap(r2, r3, r4, r1)     // Catch:{ Exception -> 0x004c }
            r6.f18929a = r2     // Catch:{ Exception -> 0x004c }
            goto L_0x004e
        L_0x004c:
            r6.f18929a = r0
        L_0x004e:
            r7.setDrawingCacheEnabled(r1)
            android.widget.ImageView r7 = r6.b
            if (r7 != 0) goto L_0x0062
            android.widget.ImageView r7 = new android.widget.ImageView
            android.widget.ListView r0 = r6.d
            android.content.Context r0 = r0.getContext()
            r7.<init>(r0)
            r6.b = r7
        L_0x0062:
            android.widget.ImageView r7 = r6.b
            r0 = 2130840730(0x7f020c9a, float:1.7286507E38)
            r7.setBackgroundResource(r0)
            android.graphics.Bitmap r7 = r6.f18929a
            if (r7 == 0) goto L_0x0075
            android.widget.ImageView r7 = r6.b
            android.graphics.Bitmap r0 = r6.f18929a
            r7.setImageBitmap(r0)
        L_0x0075:
            android.widget.ImageView r7 = r6.b
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams
            r1 = -2
            r0.<init>(r1, r1)
            r7.setLayoutParams(r0)
            android.widget.ImageView r7 = r6.b
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.SimpleFloatViewManager.f(int):android.view.View");
    }

    public void a(View view) {
        ((ImageView) view).setImageDrawable((Drawable) null);
        if (this.f18929a != null) {
            this.f18929a.recycle();
        }
        this.f18929a = null;
    }
}
