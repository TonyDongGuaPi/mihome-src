package com.tmall.wireless.vaf.virtualview.view.nlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import com.tmall.wireless.vaf.virtualview.Helper.VirtualViewUtils;
import com.tmall.wireless.vaf.virtualview.container.ClickHelper;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import java.util.List;

public class NativeLayoutImpl extends ViewGroup implements IContainer, INativeLayoutImpl {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9408a = "NativeLayoutImpl_TMTEST";
    protected ViewBase mView;

    public void destroy() {
    }

    public View getHolderView() {
        return this;
    }

    public int getType() {
        return -1;
    }

    public NativeLayoutImpl(Context context) {
        super(context);
    }

    public void attachViews(ViewBase viewBase, View view) {
        List<ViewBase> b;
        viewBase.a(view);
        if (viewBase instanceof Layout) {
            View g_ = viewBase.g_();
            int i = 0;
            if (g_ == null || g_ == this) {
                viewBase.a(view);
                List<ViewBase> b2 = ((Layout) viewBase).b();
                if (b2 != null) {
                    int size = b2.size();
                    while (i < size) {
                        attachViews(b2.get(i), view);
                        i++;
                    }
                    return;
                }
                return;
            }
            addView(g_, new ViewGroup.LayoutParams(viewBase.ae().f9382a, viewBase.ae().b));
            if ((g_ instanceof INativeLayoutImpl) && (b = ((Layout) viewBase).b()) != null) {
                int size2 = b.size();
                while (i < size2) {
                    ((INativeLayoutImpl) g_).attachViews(b.get(i), g_);
                    i++;
                }
                return;
            }
            return;
        }
        View g_2 = viewBase.g_();
        if (g_2 != null) {
            addView(g_2, new ViewGroup.LayoutParams(viewBase.ae().f9382a, viewBase.ae().b));
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        a(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        a(z, 0, 0, i3 - i, i4 - i2);
    }

    public void draw(Canvas canvas) {
        if (this.mView != null) {
            VirtualViewUtils.a((View) this, canvas, getMeasuredWidth(), getMeasuredHeight(), this.mView.o(), this.mView.q(), this.mView.r(), this.mView.s(), this.mView.t());
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mView != null) {
            VirtualViewUtils.a((View) this, canvas, this.mView.getComMeasuredWidth(), this.mView.getComMeasuredHeight(), this.mView.o(), this.mView.q(), this.mView.r(), this.mView.s(), this.mView.t());
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!(this.mView == null || this.mView.n() == 0)) {
            VirtualViewUtils.b(canvas, this.mView.n(), this.mView.getComMeasuredWidth(), this.mView.getComMeasuredHeight(), this.mView.o(), this.mView.q(), this.mView.r(), this.mView.s(), this.mView.t());
        }
        super.onDraw(canvas);
        if (this.mView != null && this.mView.Y() && (this.mView instanceof INativeLayout)) {
            ((INativeLayout) this.mView).a_(canvas);
            this.mView.c(canvas);
        }
    }

    public void attachViews() {
        attachViews(this.mView, this);
    }

    public void setVirtualView(ViewBase viewBase) {
        if (viewBase != null) {
            this.mView = viewBase;
            this.mView.b((View) this);
            if (this.mView.Y()) {
                setWillNotDraw(false);
            }
            new ClickHelper(this);
        }
    }

    public ViewBase getVirtualView() {
        return this.mView;
    }

    private void a(int i, int i2) {
        if (this.mView != null && (this.mView instanceof INativeLayout)) {
            if (!this.mView.I()) {
                ((INativeLayout) this.mView).c_(i, i2);
            }
            setMeasuredDimension(this.mView.getComMeasuredWidth(), this.mView.getComMeasuredHeight());
        }
    }

    private void a(boolean z, int i, int i2, int i3, int i4) {
        if (this.mView != null && (this.mView instanceof INativeLayout) && !this.mView.I()) {
            ((INativeLayout) this.mView).a(z, i, i2, i3, i4);
        }
    }
}
