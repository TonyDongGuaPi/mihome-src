package miuipub.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import miuipub.v6.R;

public class AttributeResolver {

    /* renamed from: a  reason: collision with root package name */
    private static final TypedValue f3016a = new TypedValue();
    private static final ThreadLocal<TypedValue> b = new ThreadLocal<>();
    private static ResultCache c;

    protected AttributeResolver() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    private static TypedValue b(Context context) {
        if (context.getMainLooper().getThread() == Thread.currentThread()) {
            return f3016a;
        }
        TypedValue typedValue = b.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        b.set(typedValue2);
        return typedValue2;
    }

    public static int a(Context context, int i) {
        TypedValue b2 = b(context);
        if (context.getTheme().resolveAttribute(i, b2, true)) {
            return b2.resourceId;
        }
        return -1;
    }

    public static Drawable b(Context context, int i) {
        TypedValue b2 = b(context);
        if (!context.getTheme().resolveAttribute(i, b2, true)) {
            return null;
        }
        if (b2.resourceId > 0) {
            return context.getResources().getDrawable(b2.resourceId);
        }
        if (b2.type < 28 || b2.type > 31) {
            return null;
        }
        return new ColorDrawable(b2.data);
    }

    public static int c(Context context, int i) {
        TypedValue b2 = b(context);
        if (context.getTheme().resolveAttribute(i, b2, true)) {
            if (b2.resourceId > 0) {
                return context.getResources().getColor(b2.resourceId);
            }
            if (b2.type >= 28 && b2.type <= 31) {
                return b2.data;
            }
        }
        return context.getResources().getColor(-1);
    }

    public static boolean a(Context context, int i, boolean z) {
        TypedValue b2 = b(context);
        if (context.getTheme().resolveAttribute(i, b2, true)) {
            return b2.type == 18 && b2.data != 0;
        }
        return z;
    }

    public static float d(Context context, int i) {
        return context.getResources().getDimension(a(context, i));
    }

    public static int e(Context context, int i) {
        return context.getResources().getDimensionPixelSize(a(context, i));
    }

    public static int a(Context context, int i, int i2) {
        TypedValue b2 = b(context);
        return (!context.getTheme().resolveAttribute(i, b2, true) || b2.type != 16) ? i2 : b2.data;
    }

    private static class ResultCache {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<Context> f3017a;
        public final boolean b;

        public ResultCache(Context context, boolean z) {
            this.f3017a = new WeakReference<>(context);
            this.b = z;
        }
    }

    public static boolean a(Context context) {
        int a2;
        ResultCache resultCache = c;
        if (resultCache != null && resultCache.f3017a.get() == context) {
            return resultCache.b;
        }
        boolean z = false;
        if (a(context, R.attr.v6_miui_version, 0) == 0 || (a2 = a(context, 16842845)) <= 0) {
            c = new ResultCache(context, false);
            return false;
        }
        Resources.Theme newTheme = context.getResources().newTheme();
        newTheme.applyStyle(a2, true);
        TypedArray obtainStyledAttributes = newTheme.obtainStyledAttributes(R.styleable.V6_AlertDialog);
        if (obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_layout, -1) != -1) {
            z = true;
        }
        obtainStyledAttributes.recycle();
        c = new ResultCache(context, z);
        return z;
    }
}
