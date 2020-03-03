package com.xiaomi.dragdrop.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.dragdrop.animator.impl.AddAnimationInfo;
import com.xiaomi.dragdrop.animator.impl.ChangeAnimationInfo;
import com.xiaomi.dragdrop.animator.impl.ItemAddAnimationManager;
import com.xiaomi.dragdrop.animator.impl.ItemChangeAnimationManager;
import com.xiaomi.dragdrop.animator.impl.ItemMoveAnimationManager;
import com.xiaomi.dragdrop.animator.impl.ItemRemoveAnimationManager;
import com.xiaomi.dragdrop.animator.impl.MoveAnimationInfo;
import com.xiaomi.dragdrop.animator.impl.RemoveAnimationInfo;

public class RefactoredDefaultItemAnimator extends GeneralItemAnimator {
    /* access modifiers changed from: protected */
    public void c() {
        a((ItemAddAnimationManager) new DefaultItemAddAnimationManager(this));
        a((ItemRemoveAnimationManager) new DefaultItemRemoveAnimationManager(this));
        a((ItemChangeAnimationManager) new DefaultItemChangeAnimationManager(this));
        a((ItemMoveAnimationManager) new DefaultItemMoveAnimationManager(this));
    }

    /* access modifiers changed from: protected */
    public void j() {
        k();
    }

    protected static class DefaultItemAddAnimationManager extends ItemAddAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(AddAnimationInfo addAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(addAnimationInfo.f10117a.itemView);
            animate.alpha(1.0f);
            animate.setDuration(e());
            a(addAnimationInfo, addAnimationInfo.f10117a, animate);
        }

        /* access modifiers changed from: protected */
        public void b(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        }

        public boolean a(RecyclerView.ViewHolder viewHolder) {
            e(viewHolder);
            ViewCompat.setAlpha(viewHolder.itemView, 0.0f);
            c(new AddAnimationInfo(viewHolder));
            return true;
        }
    }

    protected static class DefaultItemRemoveAnimationManager extends ItemRemoveAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(RemoveAnimationInfo removeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(removeAnimationInfo.f10123a.itemView);
            animate.setDuration(e());
            animate.alpha(0.0f);
            a(removeAnimationInfo, removeAnimationInfo.f10123a, animate);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        }

        /* access modifiers changed from: protected */
        public void b(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        }

        public boolean a(RecyclerView.ViewHolder viewHolder) {
            e(viewHolder);
            c(new RemoveAnimationInfo(viewHolder));
            return true;
        }
    }

    protected static class DefaultItemChangeAnimationManager extends ItemChangeAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemChangeAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(ChangeAnimationInfo changeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.b.itemView);
            animate.setDuration(e());
            animate.translationX((float) (changeAnimationInfo.e - changeAnimationInfo.c));
            animate.translationY((float) (changeAnimationInfo.f - changeAnimationInfo.d));
            animate.alpha(0.0f);
            a(changeAnimationInfo, changeAnimationInfo.b, animate);
        }

        /* access modifiers changed from: protected */
        public void b(ChangeAnimationInfo changeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.f10121a.itemView);
            animate.translationX(0.0f);
            animate.translationY(0.0f);
            animate.setDuration(e());
            animate.alpha(1.0f);
            a(changeAnimationInfo, changeAnimationInfo.f10121a, animate);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            ViewCompat.setAlpha(view, 1.0f);
            ViewCompat.setTranslationX(view, 0.0f);
            ViewCompat.setTranslationY(view, 0.0f);
        }

        /* access modifiers changed from: protected */
        public void b(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            ViewCompat.setAlpha(view, 1.0f);
            ViewCompat.setTranslationX(view, 0.0f);
            ViewCompat.setTranslationY(view, 0.0f);
        }

        public boolean a(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            float translationX = ViewCompat.getTranslationX(viewHolder.itemView);
            float translationY = ViewCompat.getTranslationY(viewHolder.itemView);
            float alpha = ViewCompat.getAlpha(viewHolder.itemView);
            e(viewHolder);
            int i5 = (int) (((float) (i3 - i)) - translationX);
            int i6 = (int) (((float) (i4 - i2)) - translationY);
            ViewCompat.setTranslationX(viewHolder.itemView, translationX);
            ViewCompat.setTranslationY(viewHolder.itemView, translationY);
            ViewCompat.setAlpha(viewHolder.itemView, alpha);
            if (viewHolder2 != null) {
                e(viewHolder2);
                ViewCompat.setTranslationX(viewHolder2.itemView, (float) (-i5));
                ViewCompat.setTranslationY(viewHolder2.itemView, (float) (-i6));
                ViewCompat.setAlpha(viewHolder2.itemView, 0.0f);
            }
            c(new ChangeAnimationInfo(viewHolder, viewHolder2, i, i2, i3, i4));
            return true;
        }
    }

    protected static class DefaultItemMoveAnimationManager extends ItemMoveAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemMoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(MoveAnimationInfo moveAnimationInfo) {
            View view = moveAnimationInfo.f10122a.itemView;
            int i = moveAnimationInfo.d - moveAnimationInfo.b;
            int i2 = moveAnimationInfo.e - moveAnimationInfo.c;
            if (i != 0) {
                ViewCompat.animate(view).translationX(0.0f);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(0.0f);
            }
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
            animate.setDuration(e());
            a(moveAnimationInfo, moveAnimationInfo.f10122a, animate);
        }

        /* access modifiers changed from: protected */
        public void b(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            ViewCompat.setTranslationY(view, 0.0f);
            ViewCompat.setTranslationX(view, 0.0f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            int i = moveAnimationInfo.d - moveAnimationInfo.b;
            int i2 = moveAnimationInfo.e - moveAnimationInfo.c;
            if (i != 0) {
                ViewCompat.animate(view).translationX(0.0f);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(0.0f);
            }
            if (i != 0) {
                ViewCompat.setTranslationX(view, 0.0f);
            }
            if (i2 != 0) {
                ViewCompat.setTranslationY(view, 0.0f);
            }
        }

        public boolean a(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            View view = viewHolder.itemView;
            int translationX = (int) (((float) i) + ViewCompat.getTranslationX(viewHolder.itemView));
            int translationY = (int) (((float) i2) + ViewCompat.getTranslationY(viewHolder.itemView));
            e(viewHolder);
            int i5 = i3 - translationX;
            int i6 = i4 - translationY;
            MoveAnimationInfo moveAnimationInfo = new MoveAnimationInfo(viewHolder, translationX, translationY, i3, i4);
            if (i5 == 0 && i6 == 0) {
                e(moveAnimationInfo, moveAnimationInfo.f10122a);
                moveAnimationInfo.a(moveAnimationInfo.f10122a);
                return false;
            }
            if (i5 != 0) {
                ViewCompat.setTranslationX(view, (float) (-i5));
            }
            if (i6 != 0) {
                ViewCompat.setTranslationY(view, (float) (-i6));
            }
            c(moveAnimationInfo);
            return true;
        }
    }
}
