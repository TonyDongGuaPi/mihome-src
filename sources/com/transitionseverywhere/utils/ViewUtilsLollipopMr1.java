package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.PointF;
import android.transition.ChangeBounds;
import android.util.Property;
import android.view.View;

@TargetApi(22)
class ViewUtilsLollipopMr1 extends ViewUtilsLollipop {

    /* renamed from: a  reason: collision with root package name */
    private static final Property<View, PointF> f9513a = a("POSITION_PROPERTY");
    private static final Property<View, PointF> b = a("BOTTOM_RIGHT_ONLY_PROPERTY");
    private static final PointF c = new PointF();

    ViewUtilsLollipopMr1() {
    }

    private static Property<View, PointF> a(String str) {
        Object a2 = ReflectionUtils.a((Object) null, (Object) null, ReflectionUtils.a(ChangeBounds.class, str));
        if (!(a2 instanceof Property)) {
            return null;
        }
        Property<View, PointF> property = (Property) a2;
        try {
            property.set((Object) null, new PointF());
            return property;
        } catch (NullPointerException unused) {
            return property;
        } catch (Exception unused2) {
            return null;
        }
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        if (f9513a == null || b == null) {
            super.a(view, i, i2, i3, i4);
            return;
        }
        c.set((float) i, (float) i2);
        f9513a.set(view, c);
        c.set((float) i3, (float) i4);
        b.set(view, c);
    }
}
