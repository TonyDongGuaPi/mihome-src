package com.miuipub.internal.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OutsideCompoundDrawable extends Drawable implements Drawable.Callback {
    private static int d;

    /* renamed from: a  reason: collision with root package name */
    private Drawable f8243a;
    private Drawable b;
    private Drawable c;

    public OutsideCompoundDrawable() {
        if (d == 0) {
            try {
                d = Class.forName("com.android.internal.R$attr").getDeclaredField("drawable").getInt((Object) null);
            } catch (Exception unused) {
            }
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        char c2;
        Drawable drawable;
        int next;
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next2 = xmlPullParser.next();
            if (next2 != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next2 == 3) {
                    return;
                }
                if (next2 == 2 && depth2 <= depth) {
                    String name = xmlPullParser.getName();
                    int i = 0;
                    if (name.equals("item-top")) {
                        c2 = 0;
                    } else if (name.equals("item-base")) {
                        c2 = 1;
                    } else if (name.equals("item-bottom")) {
                        c2 = 2;
                    } else {
                        continue;
                    }
                    int attributeCount = attributeSet.getAttributeCount();
                    int i2 = 0;
                    while (true) {
                        if (i2 < attributeCount) {
                            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
                            if (attributeNameResource == 0) {
                                break;
                            } else if (attributeNameResource == d) {
                                i = attributeSet.getAttributeResourceValue(i2, 0);
                                break;
                            } else {
                                i2++;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i != 0) {
                        drawable = resources.getDrawable(i);
                    } else {
                        do {
                            next = xmlPullParser.next();
                        } while (next == 4);
                        if (next == 2) {
                            drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
                        } else {
                            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or " + "child tag defining a drawable");
                        }
                    }
                    if (drawable != null) {
                        if (c2 == 0) {
                            this.f8243a = drawable;
                            this.f8243a.setCallback(this);
                        } else if (c2 == 1) {
                            this.b = drawable;
                            this.b.setCallback(this);
                        } else if (c2 == 2) {
                            this.c = drawable;
                            this.c.setCallback(this);
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
        }
        if (this.f8243a != null) {
            this.f8243a.draw(canvas);
        }
        if (this.c != null) {
            this.c.draw(canvas);
        }
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        if (this.b != null) {
            this.b.setBounds(i, i2, i3, i4);
        }
        if (this.f8243a != null) {
            int intrinsicHeight = this.f8243a.getIntrinsicHeight();
            if (intrinsicHeight <= 0) {
                intrinsicHeight = 1;
            }
            this.f8243a.setBounds(i, i2 - intrinsicHeight, i3, i2);
        }
        if (this.c != null) {
            int intrinsicHeight2 = this.c.getIntrinsicHeight();
            if (intrinsicHeight2 <= 0) {
                intrinsicHeight2 = 1;
            }
            this.c.setBounds(i, i4, i3, intrinsicHeight2 + i4);
        }
    }

    public boolean isStateful() {
        return (this.f8243a != null && this.f8243a.isStateful()) || (this.b != null && this.b.isStateful()) || (this.c != null && this.c.isStateful());
    }

    public void setAlpha(int i) {
        if (this.f8243a != null) {
            this.f8243a.setAlpha(i);
        }
        if (this.b != null) {
            this.b.setAlpha(i);
        }
        if (this.c != null) {
            this.c.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f8243a != null) {
            this.f8243a.setColorFilter(colorFilter);
        }
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        }
        if (this.c != null) {
            this.c.setColorFilter(colorFilter);
        }
    }

    public boolean onStateChange(int[] iArr) {
        boolean z = this.f8243a != null && this.f8243a.isStateful() && this.f8243a.setState(iArr);
        if (this.b != null && this.b.isStateful()) {
            z = this.b.setState(iArr) || z;
        }
        if (this.c == null || !this.c.isStateful()) {
            return z;
        }
        return this.c.setState(iArr) || z;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        boolean z = this.f8243a != null && this.f8243a.setLevel(i);
        if (this.b != null) {
            z = this.b.setLevel(i) || z;
        }
        if (this.c != null) {
            return this.c.setLevel(i) || z;
        }
        return z;
    }

    public void jumpToCurrentState() {
        if (this.f8243a != null) {
            this.f8243a.jumpToCurrentState();
        }
        if (this.b != null) {
            this.b.jumpToCurrentState();
        }
        if (this.c != null) {
            this.c.jumpToCurrentState();
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.f8243a != null) {
            this.f8243a.setVisible(z, z2);
        }
        if (this.b != null) {
            this.b.setVisible(z, z2);
        }
        if (this.c != null) {
            this.c.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public int getOpacity() {
        if (this.b == null || !this.b.isVisible()) {
            return -2;
        }
        return this.b.getOpacity();
    }

    public int getIntrinsicWidth() {
        if (this.b == null) {
            return -1;
        }
        return this.b.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.b == null) {
            return -1;
        }
        return this.b.getIntrinsicHeight();
    }

    public boolean getPadding(Rect rect) {
        if (this.b != null) {
            return this.b.getPadding(rect);
        }
        rect.set(0, 0, 0, 0);
        return false;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }
}
