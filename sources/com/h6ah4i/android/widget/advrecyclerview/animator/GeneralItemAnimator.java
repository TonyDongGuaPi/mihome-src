package com.h6ah4i.android.widget.advrecyclerview.animator;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemAddAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemMoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager;
import com.taobao.weex.el.parse.Operators;

public abstract class GeneralItemAnimator extends BaseItemAnimator {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5687a = "ARVGeneralItemAnimator";
    private boolean b;
    private ItemRemoveAnimationManager c;
    private ItemAddAnimationManager d;
    private ItemChangeAnimationManager e;
    private ItemMoveAnimationManager f;

    /* access modifiers changed from: protected */
    public abstract void c();

    protected GeneralItemAnimator() {
        l();
    }

    private void l() {
        c();
        if (this.c == null || this.d == null || this.e == null || this.f == null) {
            throw new IllegalStateException("setup incomplete");
        }
    }

    public void runPendingAnimations() {
        if (d()) {
            j();
        }
    }

    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        if (this.b) {
            Log.d(f5687a, "animateRemove(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + Operators.BRACKET_END_STR);
        }
        return this.c.a(viewHolder);
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        if (this.b) {
            Log.d(f5687a, "animateAdd(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + Operators.BRACKET_END_STR);
        }
        return this.d.a(viewHolder);
    }

    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        if (this.b) {
            Log.d(f5687a, "animateMove(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ", fromX = " + i + ", fromY = " + i2 + ", toX = " + i3 + ", toY = " + i4 + Operators.BRACKET_END_STR);
        }
        return this.f.a(viewHolder, i, i2, i3, i4);
    }

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        RecyclerView.ViewHolder viewHolder3 = viewHolder;
        RecyclerView.ViewHolder viewHolder4 = viewHolder2;
        if (viewHolder3 == viewHolder4) {
            return this.f.a(viewHolder, i, i2, i3, i4);
        }
        if (this.b) {
            String l = viewHolder3 != null ? Long.toString(viewHolder.getItemId()) : "-";
            String l2 = viewHolder3 != null ? Long.toString((long) viewHolder.getLayoutPosition()) : "-";
            String l3 = viewHolder4 != null ? Long.toString(viewHolder2.getItemId()) : "-";
            String l4 = viewHolder4 != null ? Long.toString((long) viewHolder2.getLayoutPosition()) : "-";
            StringBuilder sb = new StringBuilder();
            sb.append("animateChange(old.id = ");
            sb.append(l);
            sb.append(", old.position = ");
            sb.append(l2);
            sb.append(", new.id = ");
            sb.append(l3);
            sb.append(", new.position = ");
            sb.append(l4);
            sb.append(", fromX = ");
            int i5 = i;
            sb.append(i);
            sb.append(", fromY = ");
            int i6 = i2;
            sb.append(i2);
            sb.append(", toX = ");
            int i7 = i3;
            sb.append(i3);
            sb.append(", toY = ");
            sb.append(i4);
            sb.append(Operators.BRACKET_END_STR);
            Log.d(f5687a, sb.toString());
        } else {
            int i8 = i;
            int i9 = i2;
            int i10 = i3;
            int i11 = i4;
        }
        return this.e.a(viewHolder, viewHolder2, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void g(RecyclerView.ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).cancel();
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        g(viewHolder);
        this.f.c(viewHolder);
        this.e.c(viewHolder);
        this.c.c(viewHolder);
        this.d.c(viewHolder);
        this.f.d(viewHolder);
        this.e.d(viewHolder);
        this.c.d(viewHolder);
        this.d.d(viewHolder);
        if (this.c.b(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [remove]");
        } else if (this.d.b(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [add]");
        } else if (this.e.b(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [change]");
        } else if (!this.f.b(viewHolder) || !this.b) {
            a();
        } else {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [move]");
        }
    }

    public boolean isRunning() {
        return this.c.c() || this.d.c() || this.e.c() || this.f.c();
    }

    public void endAnimations() {
        this.f.f();
        this.c.f();
        this.d.f();
        this.e.f();
        if (isRunning()) {
            this.f.g();
            this.d.g();
            this.e.g();
            this.c.d();
            this.f.d();
            this.d.d();
            this.e.d();
            dispatchAnimationsFinished();
        }
    }

    public boolean b() {
        return this.b;
    }

    public boolean a() {
        if (this.b && !isRunning()) {
            Log.d(f5687a, "dispatchFinishedWhenDone()");
        }
        return super.a();
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.c.b() || this.f.b() || this.e.b() || this.d.b();
    }

    /* access modifiers changed from: protected */
    public ItemRemoveAnimationManager e() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void a(ItemRemoveAnimationManager itemRemoveAnimationManager) {
        this.c = itemRemoveAnimationManager;
    }

    /* access modifiers changed from: protected */
    public ItemAddAnimationManager f() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void a(ItemAddAnimationManager itemAddAnimationManager) {
        this.d = itemAddAnimationManager;
    }

    /* access modifiers changed from: protected */
    public ItemChangeAnimationManager g() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void a(ItemChangeAnimationManager itemChangeAnimationManager) {
        this.e = itemChangeAnimationManager;
    }

    /* access modifiers changed from: protected */
    public ItemMoveAnimationManager h() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void a(ItemMoveAnimationManager itemMoveAnimationManager) {
        this.f = itemMoveAnimationManager;
    }

    public boolean i() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    /* access modifiers changed from: protected */
    public void j() {
        k();
    }

    /* access modifiers changed from: protected */
    public void k() {
        boolean b2 = this.c.b();
        boolean b3 = this.f.b();
        boolean b4 = this.e.b();
        boolean b5 = this.d.b();
        long removeDuration = b2 ? getRemoveDuration() : 0;
        long moveDuration = b3 ? getMoveDuration() : 0;
        long changeDuration = b4 ? getChangeDuration() : 0;
        boolean z = false;
        if (b2) {
            this.c.a(false, 0);
        }
        if (b3) {
            this.f.a(b2, removeDuration);
        }
        if (b4) {
            this.e.a(b2, removeDuration);
        }
        if (b5) {
            if (b2 || b3 || b4) {
                z = true;
            }
            long max = Math.max(moveDuration, changeDuration) + removeDuration;
            if (!z) {
                max = 0;
            }
            this.d.a(z, max);
        }
    }
}
