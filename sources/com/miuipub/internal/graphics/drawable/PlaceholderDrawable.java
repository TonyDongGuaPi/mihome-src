package com.miuipub.internal.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import miuipub.v6.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PlaceholderDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public int f8244a = -1;
    public int b = -1;
    private Rect c;

    public void draw(Canvas canvas) {
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next == 3) {
                    return;
                }
                if (next == 2 && depth2 <= depth) {
                    String name = xmlPullParser.getName();
                    if (name.equals("size")) {
                        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.V6_PlaceholderDrawableSize);
                        this.f8244a = obtainAttributes.getDimensionPixelSize(R.styleable.V6_PlaceholderDrawableSize_android_width, -1);
                        this.b = obtainAttributes.getDimensionPixelSize(R.styleable.V6_PlaceholderDrawableSize_android_height, -1);
                        obtainAttributes.recycle();
                    } else if (name.equals("padding")) {
                        TypedArray obtainAttributes2 = resources.obtainAttributes(attributeSet, R.styleable.V6_PlaceholderDrawablePadding);
                        this.c = new Rect(obtainAttributes2.getDimensionPixelOffset(R.styleable.V6_PlaceholderDrawablePadding_android_left, 0), obtainAttributes2.getDimensionPixelOffset(R.styleable.V6_PlaceholderDrawablePadding_android_top, 0), obtainAttributes2.getDimensionPixelOffset(R.styleable.V6_PlaceholderDrawablePadding_android_right, 0), obtainAttributes2.getDimensionPixelOffset(R.styleable.V6_PlaceholderDrawablePadding_android_bottom, 0));
                        obtainAttributes2.recycle();
                    } else {
                        Log.w("drawable", "Bad element under <placeholder>: " + name);
                    }
                }
            } else {
                return;
            }
        }
    }

    public int getIntrinsicWidth() {
        return this.f8244a;
    }

    public int getIntrinsicHeight() {
        return this.b;
    }

    public boolean getPadding(Rect rect) {
        if (this.c == null) {
            return super.getPadding(rect);
        }
        rect.set(this.c);
        return true;
    }
}
