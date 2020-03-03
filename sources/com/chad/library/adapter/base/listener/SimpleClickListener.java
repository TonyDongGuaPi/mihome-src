package com.chad.library.adapter.base.listener;

import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.HashSet;
import java.util.Set;

public abstract class SimpleClickListener implements RecyclerView.OnItemTouchListener {

    /* renamed from: a  reason: collision with root package name */
    public static String f5139a = "SimpleClickListener";
    protected BaseQuickAdapter b;
    private GestureDetectorCompat c;
    private RecyclerView d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public View g = null;

    private boolean b(int i) {
        return i == 1365 || i == 273 || i == 819 || i == 546;
    }

    public abstract void a(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void b(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void c(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void d(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        BaseViewHolder baseViewHolder;
        if (this.d == null) {
            this.d = recyclerView;
            this.b = (BaseQuickAdapter) this.d.getAdapter();
            this.c = new GestureDetectorCompat(this.d.getContext(), new ItemTouchHelperGestureListener(this.d));
        } else if (this.d != recyclerView) {
            this.d = recyclerView;
            this.b = (BaseQuickAdapter) this.d.getAdapter();
            this.c = new GestureDetectorCompat(this.d.getContext(), new ItemTouchHelperGestureListener(this.d));
        }
        if (!this.c.onTouchEvent(motionEvent) && motionEvent.getActionMasked() == 1 && this.f) {
            if (this.g != null && ((baseViewHolder = (BaseViewHolder) this.d.getChildViewHolder(this.g)) == null || !b(baseViewHolder.getItemViewType()))) {
                this.g.setPressed(false);
            }
            this.f = false;
            this.e = false;
        }
        return false;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.c.onTouchEvent(motionEvent);
    }

    private class ItemTouchHelperGestureListener implements GestureDetector.OnGestureListener {
        private RecyclerView b;

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            boolean unused = SimpleClickListener.this.e = true;
            View unused2 = SimpleClickListener.this.g = this.b.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            if (SimpleClickListener.this.e && SimpleClickListener.this.g != null) {
                boolean unused = SimpleClickListener.this.f = true;
            }
        }

        ItemTouchHelperGestureListener(RecyclerView recyclerView) {
            this.b = recyclerView;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (SimpleClickListener.this.e && SimpleClickListener.this.g != null) {
                if (this.b.getScrollState() != 0) {
                    return false;
                }
                View b2 = SimpleClickListener.this.g;
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.b.getChildViewHolder(b2);
                if (SimpleClickListener.this.a(baseViewHolder.getLayoutPosition())) {
                    return false;
                }
                HashSet<Integer> c = baseViewHolder.c();
                Set<Integer> a2 = baseViewHolder.a();
                if (c == null || c.size() <= 0) {
                    SimpleClickListener.this.a(motionEvent, b2);
                    SimpleClickListener.this.g.setPressed(true);
                    if (c != null && c.size() > 0) {
                        for (Integer intValue : c) {
                            View findViewById = b2.findViewById(intValue.intValue());
                            if (findViewById != null) {
                                findViewById.setPressed(false);
                            }
                        }
                    }
                    SimpleClickListener.this.a(SimpleClickListener.this.b, b2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.b.t());
                } else {
                    for (Integer next : c) {
                        View findViewById2 = b2.findViewById(next.intValue());
                        if (findViewById2 != null) {
                            if (!SimpleClickListener.this.a(findViewById2, motionEvent) || !findViewById2.isEnabled()) {
                                findViewById2.setPressed(false);
                            } else if (a2 != null && a2.contains(next)) {
                                return false;
                            } else {
                                SimpleClickListener.this.a(motionEvent, findViewById2);
                                findViewById2.setPressed(true);
                                SimpleClickListener.this.c(SimpleClickListener.this.b, findViewById2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.b.t());
                                a(findViewById2);
                                return true;
                            }
                        }
                    }
                    SimpleClickListener.this.a(motionEvent, b2);
                    SimpleClickListener.this.g.setPressed(true);
                    for (Integer intValue2 : c) {
                        View findViewById3 = b2.findViewById(intValue2.intValue());
                        if (findViewById3 != null) {
                            findViewById3.setPressed(false);
                        }
                    }
                    SimpleClickListener.this.a(SimpleClickListener.this.b, b2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.b.t());
                }
                a(b2);
            }
            return true;
        }

        private void a(final View view) {
            if (view != null) {
                view.postDelayed(new Runnable() {
                    public void run() {
                        if (view != null) {
                            view.setPressed(false);
                        }
                    }
                }, 50);
            }
            boolean unused = SimpleClickListener.this.e = false;
            View unused2 = SimpleClickListener.this.g = null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:27:0x00ab  */
        /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onLongPress(android.view.MotionEvent r10) {
            /*
                r9 = this;
                android.support.v7.widget.RecyclerView r0 = r9.b
                int r0 = r0.getScrollState()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean r0 = r0.e
                if (r0 == 0) goto L_0x0106
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = r0.g
                if (r0 == 0) goto L_0x0106
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = r0.g
                r1 = 0
                r0.performHapticFeedback(r1)
                android.support.v7.widget.RecyclerView r0 = r9.b
                com.chad.library.adapter.base.listener.SimpleClickListener r2 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r2 = r2.g
                android.support.v7.widget.RecyclerView$ViewHolder r0 = r0.getChildViewHolder(r2)
                com.chad.library.adapter.base.BaseViewHolder r0 = (com.chad.library.adapter.base.BaseViewHolder) r0
                com.chad.library.adapter.base.listener.SimpleClickListener r2 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                int r3 = r0.getLayoutPosition()
                boolean r2 = r2.a((int) r3)
                if (r2 != 0) goto L_0x0106
                java.util.HashSet r2 = r0.b()
                java.util.Set r3 = r0.a()
                r4 = 1
                if (r2 == 0) goto L_0x00a8
                int r5 = r2.size()
                if (r5 <= 0) goto L_0x00a8
                java.util.Iterator r5 = r2.iterator()
            L_0x0052:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x00a8
                java.lang.Object r6 = r5.next()
                java.lang.Integer r6 = (java.lang.Integer) r6
                com.chad.library.adapter.base.listener.SimpleClickListener r7 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r7 = r7.g
                int r8 = r6.intValue()
                android.view.View r7 = r7.findViewById(r8)
                com.chad.library.adapter.base.listener.SimpleClickListener r8 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean r8 = r8.a((android.view.View) r7, (android.view.MotionEvent) r10)
                if (r8 == 0) goto L_0x0052
                boolean r8 = r7.isEnabled()
                if (r8 == 0) goto L_0x0052
                if (r3 == 0) goto L_0x0083
                boolean r3 = r3.contains(r6)
                if (r3 == 0) goto L_0x0083
                goto L_0x00a6
            L_0x0083:
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                r3.a((android.view.MotionEvent) r10, (android.view.View) r7)
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener r5 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r5 = r5.b
                int r6 = r0.getLayoutPosition()
                com.chad.library.adapter.base.listener.SimpleClickListener r8 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r8 = r8.b
                int r8 = r8.t()
                int r6 = r6 - r8
                r3.d(r5, r7, r6)
                r7.setPressed(r4)
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean unused = r3.f = r4
            L_0x00a6:
                r3 = 1
                goto L_0x00a9
            L_0x00a8:
                r3 = 0
            L_0x00a9:
                if (r3 != 0) goto L_0x0106
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener r5 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r5 = r5.b
                com.chad.library.adapter.base.listener.SimpleClickListener r6 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r6 = r6.g
                int r0 = r0.getLayoutPosition()
                com.chad.library.adapter.base.listener.SimpleClickListener r7 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r7 = r7.b
                int r7 = r7.t()
                int r0 = r0 - r7
                r3.b(r5, r6, r0)
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r3 = r3.g
                r0.a((android.view.MotionEvent) r10, (android.view.View) r3)
                com.chad.library.adapter.base.listener.SimpleClickListener r10 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r10 = r10.g
                r10.setPressed(r4)
                if (r2 == 0) goto L_0x0101
                java.util.Iterator r10 = r2.iterator()
            L_0x00e1:
                boolean r0 = r10.hasNext()
                if (r0 == 0) goto L_0x0101
                java.lang.Object r0 = r10.next()
                java.lang.Integer r0 = (java.lang.Integer) r0
                com.chad.library.adapter.base.listener.SimpleClickListener r2 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r2 = r2.g
                int r0 = r0.intValue()
                android.view.View r0 = r2.findViewById(r0)
                if (r0 == 0) goto L_0x00e1
                r0.setPressed(r1)
                goto L_0x00e1
            L_0x0101:
                com.chad.library.adapter.base.listener.SimpleClickListener r10 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean unused = r10.f = r4
            L_0x0106:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chad.library.adapter.base.listener.SimpleClickListener.ItemTouchHelperGestureListener.onLongPress(android.view.MotionEvent):void");
        }
    }

    /* access modifiers changed from: private */
    public void a(MotionEvent motionEvent, View view) {
        if (Build.VERSION.SDK_INT >= 21 && view != null && view.getBackground() != null) {
            view.getBackground().setHotspot(motionEvent.getRawX(), motionEvent.getY() - view.getY());
        }
    }

    public boolean a(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        if (view == null || !view.isShown()) {
            return false;
        }
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        if (motionEvent.getRawX() < ((float) i) || motionEvent.getRawX() > ((float) (i + view.getWidth())) || motionEvent.getRawY() < ((float) i2) || motionEvent.getRawY() > ((float) (i2 + view.getHeight()))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean a(int i) {
        if (this.b == null) {
            if (this.d == null) {
                return false;
            }
            this.b = (BaseQuickAdapter) this.d.getAdapter();
        }
        int itemViewType = this.b.getItemViewType(i);
        if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
            return true;
        }
        return false;
    }
}
