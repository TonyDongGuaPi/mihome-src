package com.github.ksoichiro.android.observablescrollview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

public class ObservableListView extends ListView implements Scrollable {

    /* renamed from: a  reason: collision with root package name */
    private int f5320a;
    private int b = -1;
    private int c;
    private int d;
    private int e;
    private SparseIntArray f;
    private ObservableScrollViewCallbacks g;
    private ScrollState h;
    private boolean i;
    private boolean j;
    private boolean k;
    private MotionEvent l;
    private ViewGroup m;
    /* access modifiers changed from: private */
    public AbsListView.OnScrollListener n;
    private AbsListView.OnScrollListener o = new AbsListView.OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (ObservableListView.this.n != null) {
                ObservableListView.this.n.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ObservableListView.this.n != null) {
                ObservableListView.this.n.onScroll(absListView, i, i2, i3);
            }
            ObservableListView.this.b();
        }
    };

    public ObservableListView(Context context) {
        super(context);
        a();
    }

    public ObservableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ObservableListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.f5320a = savedState.f5323a;
        this.b = savedState.b;
        this.c = savedState.c;
        this.d = savedState.d;
        this.e = savedState.e;
        this.f = savedState.f;
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5323a = this.f5320a;
        savedState.b = this.b;
        savedState.c = this.c;
        savedState.d = this.d;
        savedState.e = this.e;
        savedState.f = this.f;
        return savedState;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.g != null && motionEvent.getActionMasked() == 0) {
            this.j = true;
            this.i = true;
            this.g.onDownMotionEvent();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        final ViewGroup viewGroup;
        if (this.g != null) {
            switch (motionEvent.getActionMasked()) {
                case 1:
                case 3:
                    this.k = false;
                    this.j = false;
                    this.g.onUpOrCancelMotionEvent(this.h);
                    break;
                case 2:
                    if (this.l == null) {
                        this.l = motionEvent;
                    }
                    float y = motionEvent.getY() - this.l.getY();
                    this.l = MotionEvent.obtainNoHistory(motionEvent);
                    if (((float) getCurrentScrollY()) - y <= 0.0f) {
                        if (this.k) {
                            return false;
                        }
                        if (this.m == null) {
                            viewGroup = (ViewGroup) getParent();
                        } else {
                            viewGroup = this.m;
                        }
                        float f2 = 0.0f;
                        float f3 = 0.0f;
                        View view = this;
                        while (view != null && view != viewGroup) {
                            f2 += (float) (view.getLeft() - view.getScrollX());
                            f3 += (float) (view.getTop() - view.getScrollY());
                            try {
                                view = (View) view.getParent();
                            } catch (ClassCastException unused) {
                            }
                        }
                        final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        obtainNoHistory.offsetLocation(f2, f3);
                        if (!viewGroup.onInterceptTouchEvent(obtainNoHistory)) {
                            return super.onTouchEvent(motionEvent);
                        }
                        this.k = true;
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

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.n = onScrollListener;
    }

    public void setScrollViewCallbacks(ObservableScrollViewCallbacks observableScrollViewCallbacks) {
        this.g = observableScrollViewCallbacks;
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.m = viewGroup;
    }

    public void scrollVerticallyTo(int i2) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            setSelection(i2 / childAt.getHeight());
        }
    }

    public int getCurrentScrollY() {
        return this.e;
    }

    private void a() {
        this.f = new SparseIntArray();
        super.setOnScrollListener(this.o);
    }

    /* access modifiers changed from: private */
    public void b() {
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.g != null && getChildCount() > 0) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int firstVisiblePosition2 = getFirstVisiblePosition();
            int i6 = 0;
            while (firstVisiblePosition2 <= getLastVisiblePosition()) {
                if (this.f.indexOfKey(firstVisiblePosition2) < 0 || getChildAt(i6).getHeight() != this.f.get(firstVisiblePosition2)) {
                    this.f.put(firstVisiblePosition2, getChildAt(i6).getHeight());
                }
                firstVisiblePosition2++;
                i6++;
            }
            View childAt = getChildAt(0);
            if (childAt != null) {
                if (this.f5320a < firstVisiblePosition) {
                    if (firstVisiblePosition - this.f5320a != 1) {
                        i4 = 0;
                        for (int i7 = firstVisiblePosition - 1; i7 > this.f5320a; i7--) {
                            if (this.f.indexOfKey(i7) > 0) {
                                i5 = this.f.get(i7);
                            } else {
                                i5 = childAt.getHeight();
                            }
                            i4 += i5;
                        }
                    } else {
                        i4 = 0;
                    }
                    this.c += this.b + i4;
                    this.b = childAt.getHeight();
                } else if (firstVisiblePosition < this.f5320a) {
                    if (this.f5320a - firstVisiblePosition != 1) {
                        i2 = 0;
                        for (int i8 = this.f5320a - 1; i8 > firstVisiblePosition; i8--) {
                            if (this.f.indexOfKey(i8) > 0) {
                                i3 = this.f.get(i8);
                            } else {
                                i3 = childAt.getHeight();
                            }
                            i2 += i3;
                        }
                    } else {
                        i2 = 0;
                    }
                    this.c -= childAt.getHeight() + i2;
                    this.b = childAt.getHeight();
                } else if (firstVisiblePosition == 0) {
                    this.b = childAt.getHeight();
                }
                if (this.b < 0) {
                    this.b = 0;
                }
                this.e = this.c - childAt.getTop();
                this.f5320a = firstVisiblePosition;
                this.g.onScrollChanged(this.e, this.i, this.j);
                if (this.i) {
                    this.i = false;
                }
                if (this.d < this.e) {
                    this.h = ScrollState.UP;
                } else if (this.e < this.d) {
                    this.h = ScrollState.DOWN;
                } else {
                    this.h = ScrollState.STOP;
                }
                this.d = this.e;
            }
        }
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
        int f5323a;
        int b;
        int c;
        int d;
        int e;
        SparseIntArray f;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.b = -1;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.b = -1;
            this.f5323a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = new SparseIntArray();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                for (int i = 0; i < readInt; i++) {
                    this.f.put(parcel.readInt(), parcel.readInt());
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f5323a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            int size = this.f == null ? 0 : this.f.size();
            parcel.writeInt(size);
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    parcel.writeInt(this.f.keyAt(i2));
                    parcel.writeInt(this.f.valueAt(i2));
                }
            }
        }
    }
}
