package com.mi.global.bbs.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class SlideInItemAnimator extends DefaultItemAnimator {
    private final List<RecyclerView.ViewHolder> pendingAdds;
    private final int slideFromEdge;

    public SlideInItemAnimator() {
        this(80, -1);
    }

    public SlideInItemAnimator(int i, int i2) {
        this.pendingAdds = new ArrayList();
        this.slideFromEdge = Gravity.getAbsoluteGravity(i, i2);
        setAddDuration(160);
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setAlpha(0.0f);
        int i = this.slideFromEdge;
        if (i == 3) {
            viewHolder.itemView.setTranslationX((float) ((-viewHolder.itemView.getWidth()) / 3));
        } else if (i == 5) {
            viewHolder.itemView.setTranslationX((float) (viewHolder.itemView.getWidth() / 3));
        } else if (i != 48) {
            viewHolder.itemView.setTranslationY((float) (viewHolder.itemView.getHeight() / 3));
        } else {
            viewHolder.itemView.setTranslationY((float) ((-viewHolder.itemView.getHeight()) / 3));
        }
        this.pendingAdds.add(viewHolder);
        return true;
    }

    public void runPendingAnimations() {
        super.runPendingAnimations();
        if (!this.pendingAdds.isEmpty()) {
            for (int size = this.pendingAdds.size() - 1; size >= 0; size--) {
                final RecyclerView.ViewHolder viewHolder = this.pendingAdds.get(size);
                viewHolder.itemView.animate().alpha(1.0f).translationX(0.0f).translationY(0.0f).setDuration(getAddDuration()).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        SlideInItemAnimator.this.dispatchAddStarting(viewHolder);
                    }

                    public void onAnimationEnd(Animator animator) {
                        animator.getListeners().remove(this);
                        SlideInItemAnimator.this.dispatchAddFinished(viewHolder);
                        SlideInItemAnimator.this.dispatchFinishedWhenDone();
                    }

                    public void onAnimationCancel(Animator animator) {
                        SlideInItemAnimator.this.clearAnimatedValues(viewHolder.itemView);
                    }
                }).setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(viewHolder.itemView.getContext()));
                this.pendingAdds.remove(size);
            }
        }
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.animate().cancel();
        if (this.pendingAdds.remove(viewHolder)) {
            dispatchAddFinished(viewHolder);
            clearAnimatedValues(viewHolder.itemView);
        }
        super.endAnimation(viewHolder);
    }

    public void endAnimations() {
        for (int size = this.pendingAdds.size() - 1; size >= 0; size--) {
            RecyclerView.ViewHolder viewHolder = this.pendingAdds.get(size);
            clearAnimatedValues(viewHolder.itemView);
            dispatchAddFinished(viewHolder);
            this.pendingAdds.remove(size);
        }
        super.endAnimations();
    }

    public boolean isRunning() {
        return !this.pendingAdds.isEmpty() || super.isRunning();
    }

    /* access modifiers changed from: private */
    public void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    /* access modifiers changed from: private */
    public void clearAnimatedValues(View view) {
        view.setAlpha(1.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }
}
