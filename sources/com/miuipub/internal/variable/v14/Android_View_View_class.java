package com.miuipub.internal.variable.v14;

import android.graphics.Bitmap;
import android.view.View;
import miuipub.reflect.Field;
import miuipub.reflect.Method;

public class Android_View_View_class extends com.miuipub.internal.variable.Android_View_View_class {
    protected static Method createSnapshot;
    private static final Field mLeft = Field.a((Class<?>) View.class, "mLeft", Field.e);
    private static final Field mRight = Field.a((Class<?>) View.class, "mRight", Field.e);
    private static final Field mScrollX = Field.a((Class<?>) View.class, "mScrollX", Field.e);
    private static final Field mScrollY = Field.a((Class<?>) View.class, "mScrollY", Field.e);

    static {
        Class<View> cls = View.class;
        Class<Bitmap> cls2 = Bitmap.class;
        try {
            createSnapshot = Method.a(cls, "createSnapshot", cls2, Bitmap.Config.class, Integer.TYPE, Boolean.TYPE);
        } catch (Exception unused) {
            createSnapshot = null;
        }
    }

    public void setScrollXDirectly(View view, int i) {
        try {
            mScrollX.a((Object) view, i);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void setScrollYDirectly(View view, int i) {
        try {
            mScrollY.a((Object) view, i);
        } catch (RuntimeException unused) {
        }
    }

    public void setLeftDirectly(View view, int i) {
        try {
            mLeft.a((Object) view, i);
        } catch (RuntimeException unused) {
        }
    }

    public void setRightDirectly(View view, int i) {
        try {
            mRight.a((Object) view, i);
        } catch (RuntimeException unused) {
        }
    }

    public Bitmap createSnapshot(View view, Bitmap.Config config, int i, boolean z) {
        if (createSnapshot == null) {
            return null;
        }
        try {
            return (Bitmap) createSnapshot.j(View.class, view, config, Integer.valueOf(i), Boolean.valueOf(z));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
