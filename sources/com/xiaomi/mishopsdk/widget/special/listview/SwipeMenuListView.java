package com.xiaomi.mishopsdk.widget.special.listview;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SwipeMenuListView extends ListView {
    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;
    private int MAX_X = 3;
    private int MAX_Y = 5;
    private Interpolator mCloseInterpolator;
    private float mDownX;
    private float mDownY;
    /* access modifiers changed from: private */
    public SwipeMenuCreator mMenuCreator;
    /* access modifiers changed from: private */
    public OnMenuItemClickListener mOnMenuItemClickListener;
    private OnSwipeListener mOnSwipeListener;
    private Interpolator mOpenInterpolator;
    private int mTouchPosition;
    private int mTouchState;
    /* access modifiers changed from: private */
    public SwipeMenuLayout mTouchView;

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int i2);
    }

    public interface OnSwipeListener {
        void onSwipeEnd(int i);

        void onSwipeStart(int i);
    }

    public SwipeMenuListView(Context context) {
        super(context);
        init();
    }

    public SwipeMenuListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public SwipeMenuListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.MAX_X = dp2px(this.MAX_X);
        this.MAX_Y = dp2px(this.MAX_Y);
        this.mTouchState = 0;
    }

    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(new SwipeMenuAdapter(getContext(), listAdapter) {
            public void createMenu(SwipeMenu swipeMenu) {
                if (SwipeMenuListView.this.mMenuCreator != null) {
                    SwipeMenuListView.this.mMenuCreator.create(swipeMenu);
                }
            }

            public void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int i) {
                boolean onMenuItemClick = SwipeMenuListView.this.mOnMenuItemClickListener != null ? SwipeMenuListView.this.mOnMenuItemClickListener.onMenuItemClick(swipeMenuView.getPosition(), swipeMenu, i) : false;
                if (SwipeMenuListView.this.mTouchView != null && !onMenuItemClick) {
                    SwipeMenuListView.this.mTouchView.smoothCloseMenu();
                }
            }
        });
    }

    public void setCloseInterpolator(Interpolator interpolator) {
        this.mCloseInterpolator = interpolator;
    }

    public void setOpenInterpolator(Interpolator interpolator) {
        this.mOpenInterpolator = interpolator;
    }

    public Interpolator getOpenInterpolator() {
        return this.mOpenInterpolator;
    }

    public Interpolator getCloseInterpolator() {
        return this.mCloseInterpolator;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 && this.mTouchView == null) {
            return super.onTouchEvent(motionEvent);
        }
        MotionEventCompat.getActionMasked(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                int i = this.mTouchPosition;
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                this.mTouchState = 0;
                this.mTouchPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
                if (this.mTouchPosition != i || this.mTouchView == null || !this.mTouchView.isOpen()) {
                    View childAt = getChildAt(this.mTouchPosition - getFirstVisiblePosition());
                    if (this.mTouchView == null || !this.mTouchView.isOpen()) {
                        if (childAt instanceof SwipeMenuLayout) {
                            this.mTouchView = (SwipeMenuLayout) childAt;
                        }
                        if (this.mTouchView != null) {
                            this.mTouchView.onSwipe(motionEvent);
                            break;
                        }
                    } else {
                        this.mTouchView.smoothCloseMenu();
                        this.mTouchView = null;
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        obtain.setAction(3);
                        onTouchEvent(obtain);
                        return true;
                    }
                } else {
                    this.mTouchState = 1;
                    this.mTouchView.onSwipe(motionEvent);
                    return true;
                }
                break;
            case 1:
                if (this.mTouchState == 1) {
                    if (this.mTouchView != null) {
                        this.mTouchView.onSwipe(motionEvent);
                        if (!this.mTouchView.isOpen()) {
                            this.mTouchPosition = -1;
                            this.mTouchView = null;
                        }
                    }
                    if (this.mOnSwipeListener != null) {
                        this.mOnSwipeListener.onSwipeEnd(this.mTouchPosition);
                    }
                    motionEvent.setAction(3);
                    super.onTouchEvent(motionEvent);
                    return true;
                }
                break;
            case 2:
                float abs = Math.abs(motionEvent.getY() - this.mDownY);
                float abs2 = Math.abs(motionEvent.getX() - this.mDownX);
                if (this.mTouchState != 1) {
                    if (this.mTouchState == 0) {
                        if (Math.abs(abs) <= ((float) this.MAX_Y)) {
                            if (abs2 > ((float) this.MAX_X)) {
                                this.mTouchState = 1;
                                if (this.mOnSwipeListener != null) {
                                    this.mOnSwipeListener.onSwipeStart(this.mTouchPosition);
                                    break;
                                }
                            }
                        } else {
                            this.mTouchState = 2;
                            break;
                        }
                    }
                } else {
                    if (this.mTouchView != null) {
                        this.mTouchView.onSwipe(motionEvent);
                    }
                    getSelector().setState(new int[]{0});
                    motionEvent.setAction(3);
                    super.onTouchEvent(motionEvent);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void smoothOpenMenu(int i) {
        if (i >= getFirstVisiblePosition() && i <= getLastVisiblePosition()) {
            View childAt = getChildAt(i - getFirstVisiblePosition());
            if (childAt instanceof SwipeMenuLayout) {
                this.mTouchPosition = i;
                if (this.mTouchView != null && this.mTouchView.isOpen()) {
                    this.mTouchView.smoothCloseMenu();
                }
                this.mTouchView = (SwipeMenuLayout) childAt;
                this.mTouchView.smoothOpenMenu();
            }
        }
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getContext().getResources().getDisplayMetrics());
    }

    public void setMenuCreator(SwipeMenuCreator swipeMenuCreator) {
        this.mMenuCreator = swipeMenuCreator;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.mOnSwipeListener = onSwipeListener;
    }
}
