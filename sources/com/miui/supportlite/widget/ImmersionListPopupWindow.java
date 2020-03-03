package com.miui.supportlite.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.miui.supportlite.R;
import com.miui.supportlite.graphics.BitmapFactory;
import com.miui.supportlite.internal.util.ContextHelper;
import com.miui.supportlite.reflect.Method;
import com.miui.supportlite.reflect.NoSuchMethodException;
import com.miui.supportlite.util.AttributeResolver;
import com.xiaomi.jr.common.utils.DeviceHelper;

public class ImmersionListPopupWindow extends PopupWindow {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8214a = "ImmersionListPopupWindo";
    private static final int b = 3;
    private Context c;
    private FrameLayout d;
    private View e;
    /* access modifiers changed from: private */
    public ListView f;
    /* access modifiers changed from: private */
    public ListAdapter g;
    /* access modifiers changed from: private */
    public AdapterView.OnItemClickListener h;
    private Runnable i;

    public ImmersionListPopupWindow(Context context) {
        super(context);
        this.c = context;
        setFocusable(true);
        setWidth(-2);
        setHeight(-2);
        this.d = new FrameLayout(context);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImmersionListPopupWindow.this.dismiss();
            }
        });
        super.setContentView(this.d);
        a((PopupWindow) this, true);
        b((PopupWindow) this, true);
    }

    public void a(ListAdapter listAdapter) {
        this.g = listAdapter;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.h = onItemClickListener;
    }

    public void setContentView(View view) {
        this.e = view;
    }

    public View getContentView() {
        return this.e;
    }

    public void a(View view, ViewGroup viewGroup) {
        if (view == null) {
            Log.e(f8214a, "show: anchor is null");
            return;
        }
        if (this.e == null) {
            this.e = new SameWidthItemListView(this.c);
            this.e.setId(16908298);
        }
        if (!(this.d.getChildCount() == 1 && this.d.getChildAt(0) == this.e)) {
            this.d.removeAllViews();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 53);
            this.e.setLayoutParams(layoutParams);
            this.d.addView(this.e, layoutParams);
        }
        this.f = (ListView) this.e.findViewById(16908298);
        if (this.f == null) {
            Log.e(f8214a, "list not found");
            return;
        }
        this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - ImmersionListPopupWindow.this.f.getHeaderViewsCount();
                if (headerViewsCount >= 0 && headerViewsCount < ImmersionListPopupWindow.this.g.getCount()) {
                    ImmersionListPopupWindow.this.h.onItemClick(adapterView, view, headerViewsCount, j);
                }
            }
        });
        this.f.setAdapter(this.g);
        setBackgroundDrawable(new ColorDrawable(0));
        a(view);
        ((InputMethodManager) this.c.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        showAtLocation(view, 53, 0, 0);
    }

    private void a(View view) {
        if (!isShowing()) {
            try {
                final Class<?> cls = Class.forName("miui.app.IActivity");
                final Activity a2 = ContextHelper.a(view);
                if (!cls.isInstance(a2)) {
                    Log.w(f8214a, "Fail to setup translucent status for unknown context: " + a2.getClass().getName());
                    this.i = null;
                    return;
                }
                final int a3 = a(cls, (Context) a2);
                b(cls, a2, AttributeResolver.a((Context) a2, R.attr.miuisupport_immersionStatusBarStyle, 0));
                this.i = new Runnable() {
                    public void run() {
                        ImmersionListPopupWindow.b(cls, a2, a3);
                    }
                };
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    public void b(View view, ViewGroup viewGroup) {
        showAtLocation(view, 53, 0, 0);
    }

    public void dismiss() {
        a();
    }

    public void a(boolean z) {
        if (z) {
            dismiss();
        } else {
            a();
        }
    }

    private void a() {
        if (this.i != null) {
            this.i.run();
            this.i = null;
        }
        super.dismiss();
    }

    /* access modifiers changed from: protected */
    public Drawable a(Context context, View view) {
        Bitmap bitmap;
        if (!DeviceHelper.c && AttributeResolver.a(context, R.attr.miuisupport_themeType, -1) == 0) {
            return new ColorDrawable(context.getResources().getColor(R.color.miuisupport_immersion_window_background_light));
        }
        Drawable b2 = AttributeResolver.b(context, R.attr.miuisupport_immersionBlurMask);
        if (b2 == null) {
            return null;
        }
        View rootView = view.getRootView();
        if (rootView == null) {
            Log.e(f8214a, "getBlurBackground: decorView is null");
            return null;
        }
        try {
            bitmap = a(rootView, Bitmap.Config.ARGB_8888, 0, false);
        } catch (OutOfMemoryError e2) {
            Log.e(f8214a, "getBlurBackground: OOM occurs while createSnapshot", e2);
            bitmap = null;
        }
        if (bitmap == null) {
            Log.e(f8214a, "getBlurBackground: snapshot is null");
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
        if (createScaledBitmap != bitmap) {
            bitmap.recycle();
            bitmap = createScaledBitmap;
        }
        Bitmap a2 = BitmapFactory.a(bitmap, context.getResources().getDimensionPixelSize(R.dimen.miuisupport_screenshot_blur_radius));
        if (a2 == null) {
            return null;
        }
        Canvas canvas = new Canvas(a2);
        b2.setBounds(0, 0, a2.getWidth(), a2.getHeight());
        b2.draw(canvas);
        canvas.setBitmap((Bitmap) null);
        return new BitmapDrawable(context.getResources(), a2);
    }

    private class SameWidthItemListView extends ListView {
        public SameWidthItemListView(Context context) {
            super(context);
        }

        public SameWidthItemListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public SameWidthItemListView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(a() + getPaddingLeft() + getPaddingRight(), 1073741824), i2);
        }

        private int a() {
            int count = getAdapter().getCount();
            View view = null;
            int i = 0;
            for (int i2 = 0; i2 < count; i2++) {
                view = getAdapter().getView(i2, view, this);
                view.measure(0, 0);
                if (view.getMeasuredWidth() > i) {
                    i = view.getMeasuredWidth();
                }
            }
            return i;
        }
    }

    private static void a(PopupWindow popupWindow, boolean z) {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            Class cls2 = Void.TYPE;
            Class[] clsArr = {Boolean.TYPE};
            Method.a(cls, "setLayoutInScreenEnabled", cls2, clsArr).j(PopupWindow.class, popupWindow, Boolean.valueOf(z));
        } catch (NoSuchMethodException unused) {
            Log.e(f8214a, "Call PopupWindow.setLayoutInScreenEnabled() failed.");
        }
    }

    private static void b(PopupWindow popupWindow, boolean z) {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            Class cls2 = Void.TYPE;
            Class[] clsArr = {Boolean.TYPE};
            Method.a(cls, "setLayoutInsetDecor", cls2, clsArr).j(PopupWindow.class, popupWindow, Boolean.valueOf(z));
        } catch (NoSuchMethodException unused) {
            Log.e(f8214a, "Call PopupWindow.setLayoutInsetDecor() failed.");
        }
    }

    private static int a(Class<?> cls, Context context) {
        try {
            return Method.a(cls, "getTranslucentStatus", Integer.TYPE, new Class[0]).f(cls, context, new Object[0]);
        } catch (NoSuchMethodException unused) {
            Log.e(f8214a, "Call IActivity.getTranslucentStatus() failed.");
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static void b(Class<?> cls, Context context, int i2) {
        try {
            Method.a(cls, "setTranslucentStatus", Void.TYPE, Integer.TYPE).a(cls, context, Integer.valueOf(i2));
        } catch (NoSuchMethodException unused) {
            Log.e(f8214a, "Call IActivity.setTranslucentStatus() failed.");
        }
    }

    private static Bitmap a(View view, Bitmap.Config config, int i2, boolean z) {
        Class<View> cls = View.class;
        Class<Bitmap> cls2 = Bitmap.class;
        try {
            Class[] clsArr = {Bitmap.Config.class, Integer.TYPE, Boolean.TYPE};
            return (Bitmap) Method.a(cls, "createSnapshot", cls2, clsArr).j(View.class, view, config, Integer.valueOf(i2), Boolean.valueOf(z));
        } catch (NoSuchMethodException unused) {
            Log.e(f8214a, "Call View.createSnapshot() failed.");
            return null;
        }
    }
}
