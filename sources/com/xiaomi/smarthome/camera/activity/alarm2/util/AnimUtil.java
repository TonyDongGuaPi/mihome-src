package com.xiaomi.smarthome.camera.activity.alarm2.util;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import com.xiaomi.smarthome.R;

public class AnimUtil {
    public static final int INTERPOLATOR_ACC = 1;
    public static final int INTERPOLATOR_AND = 3;
    public static final int INTERPOLATOR_DEC = 2;
    public static final int INTERPOLATOR_LIN = 4;

    public interface AnimEndListener {
        void animEnd();
    }

    public static ValueAnimator getValueAnimatorOfFloat(float[] fArr, int i, int i2, int i3, int i4, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        new ValueAnimator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setDuration((long) i);
        if (i2 != 0) {
            ofFloat.setRepeatCount(i2);
            ofFloat.setRepeatMode(i3);
        }
        switch (i4) {
            case 1:
                ofFloat.setInterpolator(new AccelerateInterpolator());
                break;
            case 2:
                ofFloat.setInterpolator(new DecelerateInterpolator());
                break;
            case 3:
                ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 4:
                ofFloat.setInterpolator(new LinearInterpolator());
                break;
        }
        ofFloat.addUpdateListener(animatorUpdateListener);
        return ofFloat;
    }

    public static ValueAnimator getValueAnimatorOfInt(int[] iArr, int i, int i2, int i3, int i4, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        new ValueAnimator();
        ValueAnimator ofInt = ValueAnimator.ofInt(iArr);
        ofInt.setDuration((long) i);
        if (i2 != 0) {
            ofInt.setRepeatCount(i2);
            ofInt.setRepeatMode(i3);
        }
        switch (i4) {
            case 1:
                ofInt.setInterpolator(new AccelerateInterpolator());
                break;
            case 2:
                ofInt.setInterpolator(new DecelerateInterpolator());
                break;
            case 3:
                ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 4:
                ofInt.setInterpolator(new LinearInterpolator());
                break;
        }
        if (animatorUpdateListener != null) {
            ofInt.addUpdateListener(animatorUpdateListener);
        }
        return ofInt;
    }

    public static RotateAnimation getRotateAnimRepeat(int i, int i2, int i3, View view) {
        RotateAnimation rotateAnimation = new RotateAnimation((float) i, (float) i2, (float) (view.getMeasuredWidth() / 2), (float) (view.getMeasuredHeight() / 2));
        rotateAnimation.setDuration((long) i3);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setRepeatCount(-1);
        return rotateAnimation;
    }

    public static void startRotateAnim(int i, int i2, int i3, View view) {
        RotateAnimation rotateAnimation = new RotateAnimation((float) i, (float) i2, (float) (view.getMeasuredWidth() / 2), (float) (view.getMeasuredHeight() / 2));
        rotateAnimation.setDuration((long) i3);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    public static void animLayoutBottom(Context context, final boolean z, final View view, final View view2) {
        Animation animation;
        Animation animation2;
        if (z) {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_in);
        } else {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_out);
        }
        animation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view2.setVisibility(0);
                view.setVisibility(0);
                view2.setClickable(false);
                view.setClickable(false);
            }

            public void onAnimationEnd(Animation animation) {
                if (!z) {
                    view2.setVisibility(4);
                    view.setVisibility(4);
                }
                view2.setClickable(true);
                view.setClickable(true);
            }
        });
        animation.setDuration(300);
        view.startAnimation(animation);
        animation2.setDuration(300);
        view2.startAnimation(animation2);
    }

    public static void animLayoutBottom(Context context, final boolean z, final View view, final View view2, final AnimEndListener animEndListener) {
        Animation animation;
        Animation animation2;
        if (z) {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_in);
        } else {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_out);
        }
        animation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view2.setVisibility(0);
                view.setVisibility(0);
                view2.setClickable(false);
                view.setClickable(false);
            }

            public void onAnimationEnd(Animation animation) {
                if (!z) {
                    view2.setVisibility(4);
                    view.setVisibility(4);
                }
                view2.setClickable(true);
                view.setClickable(true);
                if (animEndListener != null) {
                    animEndListener.animEnd();
                }
            }
        });
        animation.setDuration(180);
        view.startAnimation(animation);
        animation2.setDuration(180);
        view2.startAnimation(animation2);
    }

    public static void animLayoutTop(Context context, boolean z, View view, View view2, View view3, AnimEndListener animEndListener) {
        Animation animation;
        Animation animation2;
        if (z) {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_top);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_in);
        } else {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_out_top);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_out);
        }
        final View view4 = view3;
        final View view5 = view2;
        final boolean z2 = z;
        final View view6 = view;
        final AnimEndListener animEndListener2 = animEndListener;
        animation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (view4 != null) {
                    view4.setVisibility(0);
                }
                if (view5 != null) {
                    view5.setVisibility(0);
                }
                if (view4 != null) {
                    view4.setClickable(false);
                }
                if (view5 != null) {
                    view5.setClickable(false);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (!z2) {
                    if (view4 != null) {
                        view4.setVisibility(4);
                    }
                    if (view5 != null) {
                        view5.setVisibility(4);
                    }
                    if (view6 != null) {
                        view6.setVisibility(4);
                    }
                }
                if (view4 != null) {
                    view4.setClickable(true);
                }
                if (view5 != null) {
                    view5.setClickable(true);
                }
                if (animEndListener2 != null) {
                    animEndListener2.animEnd();
                }
            }
        });
        animation.setDuration(180);
        if (view2 != null) {
            view2.startAnimation(animation);
        }
        if (view != null) {
            view.setVisibility(0);
            view.setAnimation(animation);
        }
        animation2.setDuration(180);
        if (view3 != null) {
            view3.startAnimation(animation2);
        }
    }

    public static void animLayoutTop(Context context, boolean z, View view, View view2, View view3, long j, AnimEndListener animEndListener) {
        Animation animation;
        Animation animation2;
        if (z) {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_top);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_in);
        } else {
            animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_out_top);
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_out);
        }
        final View view4 = view3;
        final View view5 = view2;
        final boolean z2 = z;
        final View view6 = view;
        final AnimEndListener animEndListener2 = animEndListener;
        animation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (view4 != null) {
                    view4.setVisibility(0);
                }
                if (view5 != null) {
                    view5.setVisibility(0);
                }
                if (view4 != null) {
                    view4.setClickable(false);
                }
                if (view5 != null) {
                    view5.setClickable(false);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (!z2) {
                    if (view4 != null) {
                        view4.setVisibility(4);
                    }
                    if (view5 != null) {
                        view5.setVisibility(4);
                    }
                    if (view6 != null) {
                        view6.setVisibility(4);
                    }
                }
                if (view4 != null) {
                    view4.setClickable(true);
                }
                if (view5 != null) {
                    view5.setClickable(true);
                }
                if (animEndListener2 != null) {
                    animEndListener2.animEnd();
                }
            }
        });
        animation.setDuration(j);
        if (view2 != null) {
            view2.startAnimation(animation);
        }
        if (view != null) {
            view.setVisibility(0);
            view.setAnimation(animation);
        }
        animation2.setDuration(j);
        if (view3 != null) {
            view3.startAnimation(animation2);
        }
    }

    public static void animAlpha(Context context, final View view, long j, final boolean z, final AnimEndListener animEndListener) {
        Animation animation;
        if (z) {
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_in);
        } else {
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha_out);
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                if (z) {
                    view.setVisibility(0);
                } else {
                    view.setVisibility(4);
                }
                if (animEndListener != null) {
                    animEndListener.animEnd();
                }
            }
        });
        animation.setDuration(j);
        view.startAnimation(animation);
    }

    public static void animScale(Context context, final View view, final boolean z, boolean z2) {
        Animation animation;
        if (z) {
            animation = AnimationUtils.loadAnimation(context, R.anim.scale_to_big);
        } else {
            animation = AnimationUtils.loadAnimation(context, R.anim.scale_to_small);
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                if (z) {
                    view.setVisibility(0);
                } else {
                    view.setVisibility(4);
                }
            }
        });
        if (z2) {
            animation.setInterpolator(new BounceInterpolator());
            animation.setDuration(700);
        } else {
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(400);
        }
        view.startAnimation(animation);
    }

    public static void animScale(Context context, final View view, final boolean z, boolean z2, final AnimEndListener animEndListener) {
        Animation animation;
        if (z) {
            animation = AnimationUtils.loadAnimation(context, R.anim.scale_to_big);
        } else {
            animation = AnimationUtils.loadAnimation(context, R.anim.scale_to_small);
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                if (z) {
                    view.setVisibility(0);
                } else {
                    view.setVisibility(4);
                }
                if (animEndListener != null) {
                    animEndListener.animEnd();
                }
            }
        });
        if (z2) {
            animation.setInterpolator(new BounceInterpolator());
            animation.setDuration(700);
        } else {
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(400);
        }
        view.startAnimation(animation);
    }

    public static void animAlpha(Context context, View view) {
        new AnimationUtils();
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_in_and_out));
    }
}
