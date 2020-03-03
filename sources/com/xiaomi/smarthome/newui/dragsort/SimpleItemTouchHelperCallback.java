package com.xiaomi.smarthome.newui.dragsort;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.adapter.DeviceMainGridAdapterV2;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    /* renamed from: a  reason: collision with root package name */
    public static final float f20650a = 1.0f;
    public static final int b = 100;
    private static final String e = "SimpleItemTouchHelperCallback";
    /* access modifiers changed from: private */
    public View A = null;
    /* access modifiers changed from: private */
    public int B;
    /* access modifiers changed from: private */
    public int C;
    /* access modifiers changed from: private */
    public int D;
    /* access modifiers changed from: private */
    public int E;
    private Runnable F = new Runnable() {
        public void run() {
            boolean unused = SimpleItemTouchHelperCallback.this.x = true;
            if (SimpleItemTouchHelperCallback.this.y) {
                SimpleItemTouchHelperCallback.this.l.vibrate(50);
            }
            SimpleItemTouchHelperCallback.this.A.setVisibility(4);
            SimpleItemTouchHelperCallback.this.a((Context) SimpleItemTouchHelperCallback.this.g.get(), SimpleItemTouchHelperCallback.this.o, SimpleItemTouchHelperCallback.this.B, SimpleItemTouchHelperCallback.this.C);
        }
    };
    private AtomicBoolean G = new AtomicBoolean(false);
    private AtomicBoolean H = new AtomicBoolean(false);
    Rect c = new Rect();
    int[] d = new int[2];
    private ItemTouchHelperAdapter f;
    /* access modifiers changed from: private */
    public WeakReference<Context> g;
    private int h = -1;
    private int i = -1;
    private int[] j = new int[2];
    private boolean k = false;
    /* access modifiers changed from: private */
    public Vibrator l;
    private WindowManager m;
    private WindowManager.LayoutParams n;
    /* access modifiers changed from: private */
    public Bitmap o;
    private int p;
    private int q;
    private int r;
    private int s;
    private float t = 1.1f;
    private int u;
    private ImageView v;
    private FrameLayout w;
    /* access modifiers changed from: private */
    public boolean x = false;
    /* access modifiers changed from: private */
    public boolean y = false;
    private int z;

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public SimpleItemTouchHelperCallback(RecyclerView recyclerView) {
        b(SHApplication.getAppContext());
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 2) {
                    return false;
                }
                int unused = SimpleItemTouchHelperCallback.this.D = (int) motionEvent.getRawX();
                int unused2 = SimpleItemTouchHelperCallback.this.E = (int) motionEvent.getRawY();
                SimpleItemTouchHelperCallback.this.a((float) (SimpleItemTouchHelperCallback.this.D - SimpleItemTouchHelperCallback.this.B), (float) (SimpleItemTouchHelperCallback.this.E - SimpleItemTouchHelperCallback.this.C));
                return false;
            }
        });
    }

    public void a(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.f = itemTouchHelperAdapter;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return makeMovementFlags(15, 0);
        }
        return makeMovementFlags(3, 0);
    }

    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
        return ((int) Math.signum((float) i3)) * 10;
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Log.d(e, "onMove");
        if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        if (this.h == -1) {
            this.h = viewHolder.getAdapterPosition();
        }
        this.i = viewHolder2.getAdapterPosition();
        this.f.a(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    /* access modifiers changed from: private */
    public void a(float f2, float f3) {
        if (this.w != null) {
            int i2 = (int) f2;
            this.n.x = i2 - this.z;
            int i3 = (int) f3;
            this.n.y = i3 - this.z;
            this.m.updateViewLayout(this.w, this.n);
            this.f.a(new int[]{i2, i3});
        }
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
        this.f.a(viewHolder.getAdapterPosition());
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z2) {
        viewHolder.itemView.getLocationInWindow(this.j);
        if (this.k) {
            this.k = false;
        }
        if (i2 == 1) {
            viewHolder.itemView.setAlpha(1.0f - (Math.abs(f2) / ((float) viewHolder.itemView.getWidth())));
            viewHolder.itemView.setTranslationX(f2);
            return;
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f2, f3, i2, z2);
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
        String str = e;
        Log.d(str, "onSelectedChanged " + i2);
        super.onSelectedChanged(viewHolder, i2);
        switch (i2) {
            case 0:
                d();
                boolean z2 = false;
                if (this.j[0] < 100) {
                    z2 = true;
                }
                this.k = z2;
                return;
            case 2:
                this.H.set(true);
                if (viewHolder instanceof ItemTouchHelperViewHolder) {
                    ((ItemTouchHelperViewHolder) viewHolder).a();
                }
                a(viewHolder);
                if (this.f != null) {
                    this.f.b();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(RecyclerView.ViewHolder viewHolder) {
        this.x = true;
        this.A = viewHolder.itemView;
        this.A.setTag(Float.valueOf(this.A.getAlpha()));
        this.A.getLocationOnScreen(this.d);
        this.r = 0;
        this.s = 0;
        this.A.setDrawingCacheEnabled(true);
        Bitmap drawingCache = this.A.getDrawingCache();
        this.o = Bitmap.createScaledBitmap(drawingCache, (int) (((float) drawingCache.getWidth()) * this.t), (int) (((float) drawingCache.getHeight()) * this.t), true);
        this.A.destroyDrawingCache();
        this.z = DisplayUtils.d((Context) this.g.get(), 10.0f);
        a((Context) this.g.get(), this.o, this.d[0] - this.z, this.d[1] - this.z);
        this.A.setAlpha(0.0f);
    }

    private void d() {
        if (this.v != null) {
            this.m.removeView(this.w);
            this.w = null;
            this.v = null;
            Object tag = this.A.getTag();
            if (tag == null || !(tag instanceof Float)) {
                this.A.setAlpha(1.0f);
            } else {
                this.A.setAlpha(((Float) tag).floatValue());
            }
        }
        if (this.f != null) {
            this.f.a();
        }
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ((ItemTouchHelperViewHolder) viewHolder).b();
        }
        View view = this.A;
        if (view != null) {
            view.setBackgroundResource(R.drawable.selector_item);
        }
        if (this.f != null && this.G.getAndSet(false) && (this.f instanceof DeviceMainGridAdapterV2) && recyclerView.getScrollState() == 0) {
            recyclerView.isComputingLayout();
        }
        this.H.set(false);
    }

    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
        super.onMoved(recyclerView, viewHolder, i2, viewHolder2, i3, i4, i5);
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z2) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f2, f3, i2, z2);
    }

    public void a() {
        if (this.w != null) {
            this.m.removeView(this.w);
            this.w = null;
            this.v = null;
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, Bitmap bitmap, int i2, int i3) {
        this.n = new WindowManager.LayoutParams();
        this.n.format = -3;
        this.n.gravity = 51;
        this.n.x = i2;
        this.n.y = i3;
        Object tag = this.A.getTag();
        if (tag == null || !(tag instanceof Float)) {
            this.n.alpha = 1.0f;
        } else {
            this.n.alpha = ((Float) tag).floatValue();
        }
        this.n.width = -2;
        this.n.height = -2;
        this.n.flags = 280;
        this.v = new ImageView(context);
        this.v.setImageBitmap(bitmap);
        this.w = new FrameLayout(context);
        this.w.addView(this.v);
        this.m.addView(this.w, this.n);
    }

    private static int a(Context context) {
        new Rect();
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private void b(Context context) {
        this.l = (Vibrator) context.getSystemService("vibrator");
        this.m = (WindowManager) context.getSystemService("window");
        this.u = a(context);
    }

    public void a(int i2, int i3) {
        this.B = i2;
        this.C = i3;
    }

    public void b() {
        this.G.set(true);
    }

    public boolean c() {
        return this.H.get();
    }
}
