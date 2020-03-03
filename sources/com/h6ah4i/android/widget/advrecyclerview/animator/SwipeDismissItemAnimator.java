package com.h6ah4i.android.widget.advrecyclerview.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.AddAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ChangeAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemAddAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemMoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.MoveAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.RemoveAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder;

public class SwipeDismissItemAnimator extends GeneralItemAnimator {

    /* renamed from: a  reason: collision with root package name */
    public static final Interpolator f5688a = new AccelerateDecelerateInterpolator();

    /* access modifiers changed from: protected */
    public void c() {
        a((ItemAddAnimationManager) new DefaultItemAddAnimationManager(this));
        a((ItemRemoveAnimationManager) new SwipeDismissItemRemoveAnimationManager(this));
        a((ItemChangeAnimationManager) new SwipeDismissItemChangeAnimationManager(this));
        a((ItemMoveAnimationManager) new SwipeDismissItemMoveAnimationManager(this));
        setRemoveDuration(150);
        setMoveDuration(150);
    }

    /* access modifiers changed from: protected */
    public void j() {
        k();
    }

    /* access modifiers changed from: protected */
    public void g(RecyclerView.ViewHolder viewHolder) {
        super.g(viewHolder);
    }

    private static class DefaultItemAddAnimationManager extends ItemAddAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(AddAnimationInfo addAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(addAnimationInfo.f5689a.itemView);
            animate.alpha(1.0f);
            animate.setDuration(e());
            a(addAnimationInfo, addAnimationInfo.f5689a, animate);
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

    private static class SwipeDismissItemRemoveAnimationManager extends ItemRemoveAnimationManager {
        private static final Interpolator e = new AccelerateDecelerateInterpolator();

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public SwipeDismissItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(RemoveAnimationInfo removeAnimationInfo) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
            if (f(removeAnimationInfo.f5695a)) {
                viewPropertyAnimatorCompat = ViewCompat.animate(removeAnimationInfo.f5695a.itemView);
                viewPropertyAnimatorCompat.setDuration(e());
            } else {
                viewPropertyAnimatorCompat = ViewCompat.animate(removeAnimationInfo.f5695a.itemView);
                viewPropertyAnimatorCompat.setDuration(e());
                viewPropertyAnimatorCompat.setInterpolator(e);
                viewPropertyAnimatorCompat.alpha(0.0f);
            }
            a(removeAnimationInfo, removeAnimationInfo.f5695a, viewPropertyAnimatorCompat);
        }

        private static boolean f(RecyclerView.ViewHolder viewHolder) {
            if (!(viewHolder instanceof SwipeableItemViewHolder)) {
                return false;
            }
            SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
            int c = swipeableItemViewHolder.c();
            int d = swipeableItemViewHolder.d();
            if ((c == 2 || c == 3 || c == 4 || c == 5) && d == 1) {
                return true;
            }
            return false;
        }

        private static boolean b(RemoveAnimationInfo removeAnimationInfo) {
            return removeAnimationInfo instanceof SwipeDismissRemoveAnimationInfo;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (b(removeAnimationInfo)) {
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setTranslationY(view, 0.0f);
                return;
            }
            ViewCompat.setAlpha(view, 1.0f);
        }

        /* access modifiers changed from: protected */
        public void b(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (b(removeAnimationInfo)) {
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setTranslationY(view, 0.0f);
                return;
            }
            ViewCompat.setAlpha(view, 1.0f);
        }

        public boolean a(RecyclerView.ViewHolder viewHolder) {
            if (f(viewHolder)) {
                View view = viewHolder.itemView;
                e(viewHolder);
                ViewCompat.setTranslationX(view, (float) ((int) (ViewCompat.getTranslationX(view) + 0.5f)));
                ViewCompat.setTranslationY(view, (float) ((int) (ViewCompat.getTranslationY(view) + 0.5f)));
                c(new SwipeDismissRemoveAnimationInfo(viewHolder));
                return true;
            }
            e(viewHolder);
            c(new RemoveAnimationInfo(viewHolder));
            return true;
        }
    }

    private static class SwipeDismissRemoveAnimationInfo extends RemoveAnimationInfo {
        public SwipeDismissRemoveAnimationInfo(RecyclerView.ViewHolder viewHolder) {
            super(viewHolder);
        }
    }

    private static class SwipeDismissItemChangeAnimationManager extends ItemChangeAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public SwipeDismissItemChangeAnimationManager(BaseItemAnimator baseItemAnimator) {
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
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.f5693a.itemView);
            animate.translationX(0.0f);
            animate.translationY(0.0f);
            animate.setDuration(e());
            animate.alpha(1.0f);
            a(changeAnimationInfo, changeAnimationInfo.f5693a, animate);
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

    private static class SwipeDismissItemMoveAnimationManager extends ItemMoveAnimationManager {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public SwipeDismissItemMoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        /* access modifiers changed from: protected */
        public void a(MoveAnimationInfo moveAnimationInfo) {
            View view = moveAnimationInfo.f5694a.itemView;
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
            animate.setInterpolator(SwipeDismissItemAnimator.f5688a);
            a(moveAnimationInfo, moveAnimationInfo.f5694a, animate);
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
                e(moveAnimationInfo, moveAnimationInfo.f5694a);
                moveAnimationInfo.a(moveAnimationInfo.f5694a);
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
