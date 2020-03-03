package com.tmall.wireless.vaf.virtualview.container;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.view.nlayout.INativeLayoutImpl;
import java.util.List;

public class Container extends ViewGroup implements IContainer, IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9378a = "Container_TMTEST";
    protected ViewBase mView;

    public View getHolderView() {
        return this;
    }

    public int getType() {
        return 0;
    }

    public Container(Context context) {
        super(context);
    }

    public void destroy() {
        this.mView.d();
        this.mView = null;
    }

    public void attachViews() {
        attachViews(this.mView, this);
    }

    /* access modifiers changed from: protected */
    public void attachViews(ViewBase viewBase, View view) {
        List<ViewBase> b;
        viewBase.a(view);
        if (viewBase instanceof Layout) {
            View g_ = viewBase.g_();
            int i = 0;
            if (g_ != null) {
                addView(g_, new ViewGroup.LayoutParams(viewBase.ae().f9382a, viewBase.ae().b));
                if ((g_ instanceof INativeLayoutImpl) && (b = ((Layout) viewBase).b()) != null) {
                    int size = b.size();
                    while (i < size) {
                        ((INativeLayoutImpl) g_).attachViews(b.get(i), g_);
                        i++;
                    }
                    return;
                }
                return;
            }
            List<ViewBase> b2 = ((Layout) viewBase).b();
            if (b2 != null) {
                int size2 = b2.size();
                while (i < size2) {
                    attachViews(b2.get(i), view);
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

    public void detachViews() {
        removeAllViews();
    }

    public ViewBase getVirtualView() {
        return this.mView;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mView != null && this.mView.Y()) {
            this.mView.a(canvas);
        }
    }

    public void measureComponent(int i, int i2) {
        if (this.mView != null) {
            if (!this.mView.I()) {
                this.mView.measureComponent(i, i2);
            }
            setMeasuredDimension(this.mView.getComMeasuredWidth(), this.mView.getComMeasuredHeight());
        }
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        if (this.mView != null && !this.mView.I()) {
            this.mView.comLayout(0, 0, i3 - i, i4 - i2);
            layout(i, i2, i3, i4);
        }
    }

    public void onComMeasure(int i, int i2) {
        if (this.mView != null) {
            if (!this.mView.I()) {
                this.mView.onComMeasure(i, i2);
            }
            setMeasuredDimension(this.mView.getComMeasuredWidth(), this.mView.getComMeasuredHeight());
        }
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mView != null && !this.mView.I()) {
            this.mView.onComLayout(z, i, i2, i3, i4);
        }
    }

    public int getComMeasuredWidth() {
        if (this.mView != null) {
            return this.mView.getComMeasuredWidth();
        }
        return 0;
    }

    public int getComMeasuredHeight() {
        if (this.mView != null) {
            return this.mView.getComMeasuredHeight();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        onComMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onComLayout(z, 0, 0, i3 - i, i4 - i2);
    }
}
