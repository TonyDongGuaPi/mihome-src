package com.mi.global.bbs.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewUtils {

    public static class DefaultAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    public static class DefaultOnMoveListener implements OnMoveListener {
        public void onAnimationEnd() {
        }

        public void onAnimationStart() {
        }
    }

    public interface OnMoveListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

    public static ViewGroup getMoveViewGroup(Activity activity) {
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        ((ViewGroup) activity.getWindow().getDecorView()).addView(linearLayout);
        return linearLayout;
    }

    public static ImageView getViewMirror(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageBitmap(createBitmap);
        return imageView;
    }

    public static View getMoveView(ViewGroup viewGroup, View view, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        view.setLayoutParams(layoutParams);
        return view;
    }

    public static void moveView(Activity activity, View view, View view2, final OnMoveListener onMoveListener) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        ImageView viewMirror = getViewMirror(view);
        int[] iArr3 = new int[2];
        viewMirror.getLocationInWindow(iArr3);
        final ViewGroup moveViewGroup = getMoveViewGroup(activity);
        final View moveView = getMoveView(moveViewGroup, viewMirror, iArr3);
        TranslateAnimation translateAnimation = new TranslateAnimation((float) iArr[0], (float) iArr2[0], (float) iArr[1], (float) iArr2[1]);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(false);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(500);
        moveView.startAnimation(animationSet);
        animationSet.setAnimationListener(new DefaultAnimationListener() {
            public void onAnimationStart(Animation animation) {
                if (onMoveListener != null) {
                    onMoveListener.onAnimationStart();
                }
            }

            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(moveView);
                if (onMoveListener != null) {
                    onMoveListener.onAnimationEnd();
                }
            }
        });
    }

    public static void setMargins(View view, int i, int i2, int i3, int i4) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(i, i2, i3, i4);
            view.requestLayout();
        }
    }

    public static Bitmap getBitmapBy(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }
}
