package com.xiaomi.smarthome.newui.dragsort;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleItemDelegateTouchHelperCallback extends ItemTouchHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20649a = "SimpleItemDelegateTouchHelperCallback";
    public static final float b = 1.0f;
    public static final int c = 100;
    private int[] d = new int[2];
    private boolean e = false;
    private int[] f = new int[2];
    private Vibrator g;
    private WindowManager h;
    private WindowManager.LayoutParams i;
    private Bitmap j;
    private int k;
    private int l;
    private int m;
    private int n;
    private float o = 1.1f;
    private ImageView p;
    private FrameLayout q;
    private boolean r = false;
    private boolean s = false;
    private int t;
    private View u = null;
    private int v;
    private int w;
    private int x;
    private int y;
    private AtomicBoolean z = new AtomicBoolean(false);

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return false;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
    }

    public SimpleItemDelegateTouchHelperCallback(Context context) {
        this.g = (Vibrator) context.getSystemService("vibrator");
        this.h = (WindowManager) context.getSystemService("window");
    }

    public final boolean a(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 2 || this.q == null) {
            return false;
        }
        this.x = (int) motionEvent.getRawX();
        this.y = (int) motionEvent.getRawY();
        a((float) (this.x - this.v), (float) (this.y - this.w));
        return false;
    }

    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
        return ((int) Math.signum((float) i3)) * 10;
    }

    private void a(float f2, float f3) {
        if (this.q != null) {
            this.i.x = ((int) f2) - this.t;
            this.i.y = ((int) f3) - this.t;
            this.h.updateViewLayout(this.q, this.i);
        }
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z2) {
        viewHolder.itemView.getLocationInWindow(this.d);
        if (this.e) {
            this.e = false;
        }
        if (i2 == 1) {
            viewHolder.itemView.setAlpha(1.0f - (Math.abs(f2) / ((float) viewHolder.itemView.getWidth())));
            viewHolder.itemView.setTranslationX(f2);
            return;
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f2, f3, i2, z2);
    }

    @CallSuper
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
        String str = f20649a;
        Log.d(str, "onSelectedChanged " + i2);
        super.onSelectedChanged(viewHolder, i2);
        switch (i2) {
            case 0:
                c();
                boolean z2 = false;
                if (this.d[0] < 100) {
                    z2 = true;
                }
                this.e = z2;
                return;
            case 2:
                this.z.set(true);
                if (viewHolder instanceof ItemTouchHelperViewHolder) {
                    ((ItemTouchHelperViewHolder) viewHolder).a();
                }
                a(viewHolder);
                return;
            default:
                return;
        }
    }

    private void a(RecyclerView.ViewHolder viewHolder) {
        this.r = true;
        this.u = viewHolder.itemView;
        this.u.setTag(Float.valueOf(this.u.getAlpha()));
        this.u.getLocationOnScreen(this.f);
        this.m = 0;
        this.n = 0;
        this.u.setDrawingCacheEnabled(true);
        Bitmap drawingCache = this.u.getDrawingCache();
        this.j = Bitmap.createScaledBitmap(drawingCache, (int) (((float) drawingCache.getWidth()) * this.o), (int) (((float) drawingCache.getHeight()) * this.o), true);
        this.u.destroyDrawingCache();
        this.t = DisplayUtils.d(viewHolder.itemView.getContext(), 10.0f);
        a(viewHolder.itemView.getContext(), this.j, this.f[0] - this.t, this.f[1] - this.t);
        this.u.setAlpha(0.0f);
    }

    private void c() {
        if (this.p != null) {
            this.h.removeView(this.q);
            this.q = null;
            this.p = null;
            Object tag = this.u.getTag();
            if (tag instanceof Float) {
                this.u.setAlpha(((Float) tag).floatValue());
            } else {
                this.u.setAlpha(1.0f);
            }
        }
    }

    @CallSuper
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ((ItemTouchHelperViewHolder) viewHolder).b();
        }
        View view = this.u;
        if (view != null) {
            view.setBackgroundResource(R.drawable.selector_item);
        }
        this.z.set(false);
    }

    public void a() {
        if (this.q != null) {
            this.h.removeView(this.q);
            this.q = null;
            this.p = null;
        }
    }

    private void a(Context context, Bitmap bitmap, int i2, int i3) {
        this.i = new WindowManager.LayoutParams();
        this.i.format = -3;
        this.i.gravity = 51;
        this.i.x = i2;
        this.i.y = i3;
        Object tag = this.u.getTag();
        if (tag == null || !(tag instanceof Float)) {
            this.i.alpha = 1.0f;
        } else {
            this.i.alpha = ((Float) tag).floatValue();
        }
        this.i.width = -2;
        this.i.height = -2;
        this.i.flags = 280;
        this.p = new ImageView(context);
        this.p.setImageBitmap(bitmap);
        this.q = new FrameLayout(context);
        this.q.addView(this.p);
        this.h.addView(this.q, this.i);
    }

    public void a(int i2, int i3) {
        this.v = i2;
        this.w = i3;
    }

    public boolean b() {
        return this.z.get();
    }
}
