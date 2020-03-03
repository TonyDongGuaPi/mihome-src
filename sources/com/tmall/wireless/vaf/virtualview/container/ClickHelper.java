package com.tmall.wireless.vaf.virtualview.container;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;

public class ClickHelper {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f9375a = 500;
    private static final String j = "ClickHelper_TMTEST";
    protected boolean b = true;
    protected boolean c = false;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected IContainer h;
    protected LongRunnable i;

    public ClickHelper(IContainer iContainer) {
        this.h = iContainer;
        this.i = new LongRunnable();
        final View holderView = iContainer.getHolderView();
        final ViewBase virtualView = iContainer.getVirtualView();
        holderView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean z;
                switch (motionEvent.getAction()) {
                    case 0:
                        ClickHelper.this.b = false;
                        ClickHelper.this.c = false;
                        ClickHelper.this.f = (int) motionEvent.getX();
                        ClickHelper.this.g = (int) motionEvent.getY();
                        ClickHelper.this.d = ClickHelper.this.f;
                        ClickHelper.this.e = ClickHelper.this.g;
                        if (!virtualView.a(ClickHelper.this.f, ClickHelper.this.g)) {
                            return false;
                        }
                        Handler handler = holderView.getHandler();
                        handler.removeCallbacks(ClickHelper.this.i);
                        ClickHelper.this.i.a(ClickHelper.this.h.getVirtualView());
                        ClickHelper.this.i.a(holderView);
                        handler.postDelayed(ClickHelper.this.i, 500);
                        virtualView.a(view, motionEvent);
                        return true;
                    case 1:
                        ViewBase virtualView = ClickHelper.this.h.getVirtualView();
                        if (virtualView == null || ClickHelper.this.c) {
                            z = false;
                        } else {
                            z = virtualView.a(ClickHelper.this.f, ClickHelper.this.g, false);
                            if (z) {
                                holderView.playSoundEffect(0);
                            }
                        }
                        virtualView.a(view, motionEvent);
                        ClickHelper.this.b = true;
                        return z;
                    case 2:
                        int x = (int) motionEvent.getX();
                        int y = (int) motionEvent.getY();
                        if (Math.sqrt(Math.pow((double) (x - ClickHelper.this.d), 2.0d) + Math.pow((double) (y - ClickHelper.this.e), 2.0d)) > ((double) VafContext.b)) {
                            holderView.removeCallbacks(ClickHelper.this.i);
                        }
                        ClickHelper.this.d = x;
                        ClickHelper.this.e = y;
                        virtualView.a(view, motionEvent);
                        return false;
                    case 3:
                        virtualView.a(view, motionEvent);
                        ClickHelper.this.b = true;
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    class LongRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        protected ViewBase f9377a;
        protected View b;

        LongRunnable() {
        }

        public void a(View view) {
            this.b = view;
        }

        public void a(ViewBase viewBase) {
            this.f9377a = viewBase;
        }

        public void run() {
            if (!ClickHelper.this.b && this.f9377a != null && this.f9377a.a(ClickHelper.this.f, ClickHelper.this.g, true) && this.b != null) {
                ClickHelper.this.c = true;
                this.b.performHapticFeedback(0);
            }
        }
    }
}
