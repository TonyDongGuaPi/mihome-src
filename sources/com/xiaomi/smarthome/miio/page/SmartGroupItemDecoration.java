package com.xiaomi.smarthome.miio.page;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.smarthome.R;

public class SmartGroupItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private final Drawable f19701a;
    private final Drawable b;
    private final int c;
    private final int d;
    private boolean e;
    private Paint f;

    public void a(Canvas canvas, View view) {
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
    }

    public SmartGroupItemDecoration(@Nullable Drawable drawable, boolean z) {
        this(drawable, (Drawable) null, z);
    }

    public SmartGroupItemDecoration(@Nullable Drawable drawable, @Nullable Drawable drawable2, boolean z) {
        this.f19701a = drawable;
        this.b = drawable2;
        int i = 0;
        this.c = this.f19701a != null ? this.f19701a.getIntrinsicHeight() : 0;
        this.d = this.b != null ? this.b.getIntrinsicWidth() : i;
        this.e = z;
        this.f = new Paint();
        this.f.setColor(436207616);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        int i2;
        Canvas canvas2 = canvas;
        RecyclerView recyclerView2 = recyclerView;
        int childCount = recyclerView.getChildCount();
        float f2 = 1.0f;
        float f3 = this.e ? 1.0f : ((float) this.d) + 1.0f;
        float f4 = this.e ? 1.0f : ((float) this.c) + 1.0f;
        int i3 = 0;
        while (i3 < childCount - 1) {
            View childAt = recyclerView2.getChildAt(i3);
            int i4 = i3 + 1;
            View childAt2 = recyclerView2.getChildAt(i4);
            if (i3 <= 0 || childAt.getId() != R.id.smart_group_header_root) {
                i = i4;
                i2 = R.id.smart_group_header_root;
            } else {
                float x = childAt.getX();
                float y = childAt.getY();
                float width = ((float) childAt.getWidth()) + childAt.getX();
                float y2 = childAt.getY();
                Paint paint = this.f;
                i2 = R.id.smart_group_header_root;
                float f5 = y2;
                i = i4;
                canvas.drawLine(x, y, width, f5, paint);
            }
            if (childAt.getId() == i2 && i3 > 0) {
                a(canvas2, childAt);
            }
            if (childAt.getVisibility() == 0 && childAt2.getVisibility() == 0) {
                float bottom = ((float) childAt.getBottom()) + ViewCompat.getTranslationY(childAt);
                float top = ((float) childAt2.getTop()) + ViewCompat.getTranslationY(childAt2);
                float right = ((float) childAt.getRight()) + ViewCompat.getTranslationX(childAt);
                float left = ((float) childAt2.getLeft()) + ViewCompat.getTranslationX(childAt2);
                if ((this.c != 0 && Math.abs(top - bottom) < f4) || (this.d != 0 && Math.abs(left - right) < f3)) {
                    if (Math.abs((ViewCompat.getTranslationZ(childAt2) + ViewCompat.getElevation(childAt2)) - (ViewCompat.getTranslationZ(childAt) + ViewCompat.getElevation(childAt))) < f2) {
                        float alpha = ViewCompat.getAlpha(childAt);
                        float alpha2 = ViewCompat.getAlpha(childAt2);
                        int translationX = (int) (ViewCompat.getTranslationX(childAt) + 0.5f);
                        int translationY = (int) (ViewCompat.getTranslationY(childAt) + 0.5f);
                        if (this.c != 0) {
                            int left2 = childAt.getLeft();
                            int right2 = childAt.getRight();
                            int bottom2 = childAt.getBottom() - (this.e ? this.c : 0);
                            this.f19701a.setAlpha((int) (((alpha + alpha2) * 127.5f) + 0.5f));
                            this.f19701a.setBounds(left2 + translationX, bottom2 + translationY, right2 + translationX, bottom2 + this.c + translationY);
                            this.f19701a.draw(canvas2);
                        }
                        if (this.d != 0) {
                            int right3 = childAt.getRight() - (this.e ? this.d : 0);
                            int i5 = this.d + right3;
                            int top2 = childAt.getTop();
                            int bottom3 = childAt.getBottom();
                            this.b.setAlpha((int) (((alpha + alpha2) * 127.5f) + 0.5f));
                            this.b.setBounds(right3 + translationX, top2 + translationY, i5 + translationX, bottom3 + translationY);
                            this.b.draw(canvas2);
                        }
                    }
                }
            }
            i3 = i;
            f2 = 1.0f;
        }
    }
}
