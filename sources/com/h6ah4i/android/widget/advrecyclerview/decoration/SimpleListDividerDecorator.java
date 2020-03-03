package com.h6ah4i.android.widget.advrecyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleListDividerDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private final Drawable f5697a;
    private final Drawable b;
    private final int c;
    private final int d;
    private final boolean e;

    public SimpleListDividerDecorator(@Nullable Drawable drawable, boolean z) {
        this(drawable, (Drawable) null, z);
    }

    public SimpleListDividerDecorator(@Nullable Drawable drawable, @Nullable Drawable drawable2, boolean z) {
        this.f5697a = drawable;
        this.b = drawable2;
        int i = 0;
        this.c = this.f5697a != null ? this.f5697a.getIntrinsicHeight() : 0;
        this.d = this.b != null ? this.b.getIntrinsicWidth() : i;
        this.e = z;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Canvas canvas2 = canvas;
        RecyclerView recyclerView2 = recyclerView;
        int childCount = recyclerView.getChildCount();
        if (childCount != 0) {
            float f = 1.0f;
            float f2 = this.e ? 1.0f : ((float) this.d) + 1.0f;
            float f3 = this.e ? 1.0f : ((float) this.c) + 1.0f;
            int i = 0;
            while (i < childCount - 1) {
                View childAt = recyclerView2.getChildAt(i);
                i++;
                View childAt2 = recyclerView2.getChildAt(i);
                if (childAt.getVisibility() == 0 && childAt2.getVisibility() == 0) {
                    float bottom = ((float) childAt.getBottom()) + ViewCompat.getTranslationY(childAt);
                    float top = ((float) childAt2.getTop()) + ViewCompat.getTranslationY(childAt2);
                    float right = ((float) childAt.getRight()) + ViewCompat.getTranslationX(childAt);
                    float left = ((float) childAt2.getLeft()) + ViewCompat.getTranslationX(childAt2);
                    if ((this.c != 0 && Math.abs(top - bottom) < f3) || (this.d != 0 && Math.abs(left - right) < f2)) {
                        if (Math.abs((ViewCompat.getTranslationZ(childAt2) + ViewCompat.getElevation(childAt2)) - (ViewCompat.getTranslationZ(childAt) + ViewCompat.getElevation(childAt))) < f) {
                            float alpha = ViewCompat.getAlpha(childAt);
                            float alpha2 = ViewCompat.getAlpha(childAt2);
                            int translationX = (int) (ViewCompat.getTranslationX(childAt) + 0.5f);
                            int translationY = (int) (ViewCompat.getTranslationY(childAt) + 0.5f);
                            if (this.c != 0) {
                                int left2 = childAt.getLeft();
                                int right2 = childAt.getRight();
                                int bottom2 = childAt.getBottom() - (this.e ? this.c : 0);
                                this.f5697a.setAlpha((int) (((alpha + alpha2) * 127.5f) + 0.5f));
                                this.f5697a.setBounds(left2 + translationX, bottom2 + translationY, right2 + translationX, bottom2 + this.c + translationY);
                                this.f5697a.draw(canvas2);
                            }
                            if (this.d != 0) {
                                int right3 = childAt.getRight() - (this.e ? this.d : 0);
                                int i2 = this.d + right3;
                                int top2 = childAt.getTop();
                                int bottom3 = childAt.getBottom();
                                this.b.setAlpha((int) (((alpha + alpha2) * 127.5f) + 0.5f));
                                this.b.setBounds(right3 + translationX, top2 + translationY, i2 + translationX, bottom3 + translationY);
                                this.b.draw(canvas2);
                            }
                        }
                    }
                }
                recyclerView2 = recyclerView;
                f = 1.0f;
            }
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.e) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(0, 0, this.d, this.c);
        }
    }
}
