package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;

public class MitvGestureTouchView extends FrameLayout {
    public static final int TOUCH_EVENT_DOWN = 3;
    public static final int TOUCH_EVENT_ENTER = 4;
    public static final int TOUCH_EVENT_LEFT = 0;
    public static final int TOUCH_EVENT_RIGHT = 1;
    public static final int TOUCH_EVENT_UP = 2;
    ImageView mDirectionImageView;
    GestureDetector mGestureDetector;
    IGestureTouchEvent mIGestureTouchEvent;
    GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (Math.abs(f) > Math.abs(f2)) {
                if (f > 0.0f) {
                    if (MitvGestureTouchView.this.mIGestureTouchEvent != null) {
                        MitvGestureTouchView.this.mIGestureTouchEvent.a(1);
                    }
                    MitvGestureTouchView.this.showCircle((int) motionEvent2.getX(), (int) motionEvent2.getY(), 1);
                } else {
                    if (MitvGestureTouchView.this.mIGestureTouchEvent != null) {
                        MitvGestureTouchView.this.mIGestureTouchEvent.a(0);
                    }
                    MitvGestureTouchView.this.showCircle((int) motionEvent2.getX(), (int) motionEvent2.getY(), 0);
                }
            } else if (f2 > 0.0f) {
                if (MitvGestureTouchView.this.mIGestureTouchEvent != null) {
                    MitvGestureTouchView.this.mIGestureTouchEvent.a(3);
                }
                MitvGestureTouchView.this.showCircle((int) motionEvent2.getX(), (int) motionEvent2.getY(), 3);
            } else {
                if (MitvGestureTouchView.this.mIGestureTouchEvent != null) {
                    MitvGestureTouchView.this.mIGestureTouchEvent.a(2);
                }
                MitvGestureTouchView.this.showCircle((int) motionEvent2.getX(), (int) motionEvent2.getY(), 2);
            }
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            MitvGestureTouchView.this.showCircle((int) motionEvent.getX(), (int) motionEvent.getY(), 4);
            if (MitvGestureTouchView.this.mIGestureTouchEvent == null) {
                return true;
            }
            MitvGestureTouchView.this.mIGestureTouchEvent.a(4);
            return true;
        }
    };
    int mStyle;

    public interface IGestureTouchEvent {
        void a(int i);
    }

    public void setStyle(int i) {
        this.mStyle = i;
    }

    public void setOnGestureTouchEvent(IGestureTouchEvent iGestureTouchEvent) {
        this.mIGestureTouchEvent = iGestureTouchEvent;
    }

    public MitvGestureTouchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDirectionImageView = (ImageView) findViewById(R.id.circle_view);
        this.mGestureDetector = new GestureDetector(getContext(), this.mOnGestureListener);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void showCircle(int i, int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mDirectionImageView.getLayoutParams();
        layoutParams.setMargins(i - (this.mDirectionImageView.getWidth() / 2), i2 - (this.mDirectionImageView.getHeight() / 2), 0, 0);
        this.mDirectionImageView.setLayoutParams(layoutParams);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(500);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        this.mDirectionImageView.setVisibility(0);
        if (i3 == 0) {
            if (this.mStyle == R.layout.mitv_page_white) {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_left);
            } else {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_left_black);
            }
            animationSet.addAnimation(new TranslateAnimation(0.0f, (float) -60, 0.0f, 0.0f));
        } else if (i3 == 1) {
            if (this.mStyle == R.layout.mitv_page_white) {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_right);
            } else {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_right_black);
            }
            animationSet.addAnimation(new TranslateAnimation(0.0f, (float) 60, 0.0f, 0.0f));
        } else if (i3 == 2) {
            if (this.mStyle == R.layout.mitv_page_white) {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_up);
            } else {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_up_black);
            }
            animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) -60));
        } else if (i3 == 3) {
            if (this.mStyle == R.layout.mitv_page_white) {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_down);
            } else {
                this.mDirectionImageView.setImageResource(R.drawable.mitv_down_black);
            }
            animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) 60));
        } else {
            this.mDirectionImageView.setVisibility(8);
            return;
        }
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                MitvGestureTouchView.this.mDirectionImageView.setVisibility(8);
            }
        });
        requestLayout();
        this.mDirectionImageView.startAnimation(animationSet);
    }
}
