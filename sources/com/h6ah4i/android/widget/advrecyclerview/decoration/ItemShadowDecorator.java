package com.h6ah4i.android.widget.advrecyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemShadowDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private final NinePatchDrawable f5696a;
    private final Rect b;
    private final boolean c;

    public ItemShadowDecorator(@NonNull NinePatchDrawable ninePatchDrawable) {
        this(ninePatchDrawable, true);
    }

    public ItemShadowDecorator(@NonNull NinePatchDrawable ninePatchDrawable, boolean z) {
        this.b = new Rect();
        this.f5696a = ninePatchDrawable;
        this.f5696a.getPadding(this.b);
        this.c = z;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        if (childCount != 0) {
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (a(childAt)) {
                    int translationX = (int) (ViewCompat.getTranslationX(childAt) + 0.5f);
                    int translationY = (int) (ViewCompat.getTranslationY(childAt) + 0.5f);
                    int left = childAt.getLeft() - this.b.left;
                    int right = childAt.getRight() + this.b.right;
                    this.f5696a.setBounds(left + translationX, (childAt.getTop() - this.b.top) + translationY, right + translationX, childAt.getBottom() + this.b.bottom + translationY);
                    this.f5696a.draw(canvas);
                }
            }
        }
    }

    private boolean a(View view) {
        Drawable background;
        if (view.getVisibility() != 0 || ViewCompat.getAlpha(view) != 1.0f || (background = view.getBackground()) == null) {
            return false;
        }
        if (this.c || !(background instanceof ColorDrawable) || ((ColorDrawable) background).getAlpha() != 0) {
            return true;
        }
        return false;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
    }
}
