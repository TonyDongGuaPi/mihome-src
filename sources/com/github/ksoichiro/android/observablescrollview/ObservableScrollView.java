package com.github.ksoichiro.android.observablescrollview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView implements Scrollable {

    /* renamed from: a  reason: collision with root package name */
    private int f5327a;
    private int b;
    private ObservableScrollViewCallbacks c;
    private ScrollState d;
    private boolean e;
    private boolean f;
    private boolean g;
    private MotionEvent h;
    private ViewGroup i;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.f5327a = savedState.f5329a;
        this.b = savedState.b;
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5329a = this.f5327a;
        savedState.b = this.b;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (this.c != null) {
            this.b = i3;
            this.c.onScrollChanged(i3, this.e, this.f);
            if (this.e) {
                this.e = false;
            }
            if (this.f5327a < i3) {
                this.d = ScrollState.UP;
            } else if (i3 < this.f5327a) {
                this.d = ScrollState.DOWN;
            }
            this.f5327a = i3;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.c != null && motionEvent.getActionMasked() == 0) {
            this.f = true;
            this.e = true;
            this.c.onDownMotionEvent();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        final ViewGroup viewGroup;
        if (this.c != null) {
            switch (motionEvent.getActionMasked()) {
                case 1:
                case 3:
                    this.g = false;
                    this.f = false;
                    this.c.onUpOrCancelMotionEvent(this.d);
                    break;
                case 2:
                    if (this.h == null) {
                        this.h = motionEvent;
                    }
                    float y = motionEvent.getY() - this.h.getY();
                    this.h = MotionEvent.obtainNoHistory(motionEvent);
                    if (((float) getCurrentScrollY()) - y <= 0.0f) {
                        if (this.g) {
                            return false;
                        }
                        if (this.i == null) {
                            viewGroup = (ViewGroup) getParent();
                        } else {
                            viewGroup = this.i;
                        }
                        float f2 = 0.0f;
                        float f3 = 0.0f;
                        View view = this;
                        while (view != null && view != viewGroup) {
                            f2 += (float) (view.getLeft() - view.getScrollX());
                            f3 += (float) (view.getTop() - view.getScrollY());
                            view = (View) view.getParent();
                        }
                        final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        obtainNoHistory.offsetLocation(f2, f3);
                        if (!viewGroup.onInterceptTouchEvent(obtainNoHistory)) {
                            return super.onTouchEvent(motionEvent);
                        }
                        this.g = true;
                        obtainNoHistory.setAction(0);
                        post(new Runnable() {
                            public void run() {
                                viewGroup.dispatchTouchEvent(obtainNoHistory);
                            }
                        });
                        return false;
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setScrollViewCallbacks(ObservableScrollViewCallbacks observableScrollViewCallbacks) {
        this.c = observableScrollViewCallbacks;
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.i = viewGroup;
    }

    public void scrollVerticallyTo(int i2) {
        scrollTo(0, i2);
    }

    public int getCurrentScrollY() {
        return this.b;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f5329a;
        int b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f5329a = parcel.readInt();
            this.b = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f5329a);
            parcel.writeInt(this.b);
        }
    }
}
