package com.github.ksoichiro.android.observablescrollview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ObservableRecyclerView extends RecyclerView implements Scrollable {

    /* renamed from: a  reason: collision with root package name */
    private static int f5324a = 22;
    private int b;
    private int c = -1;
    private int d;
    private int e;
    private int f;
    private SparseIntArray g;
    private ObservableScrollViewCallbacks h;
    private ScrollState i;
    private boolean j;
    private boolean k;
    private boolean l;
    private MotionEvent m;
    private ViewGroup n;

    public ObservableRecyclerView(Context context) {
        super(context);
        a();
    }

    public ObservableRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ObservableRecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.b = savedState.b;
        this.c = savedState.c;
        this.d = savedState.d;
        this.e = savedState.e;
        this.f = savedState.f;
        this.g = savedState.g;
        super.onRestoreInstanceState(savedState.a());
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = this.b;
        savedState.c = this.c;
        savedState.d = this.d;
        savedState.e = this.e;
        savedState.f = this.f;
        savedState.g = this.g;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        super.onScrollChanged(i2, i3, i4, i5);
        if (this.h != null && getChildCount() > 0) {
            int childAdapterPosition = getChildAdapterPosition(getChildAt(0));
            int childAdapterPosition2 = getChildAdapterPosition(getChildAt(getChildCount() - 1));
            int i10 = childAdapterPosition;
            int i11 = 0;
            while (i10 <= childAdapterPosition2) {
                View childAt = getChildAt(i11);
                this.g.put(i10, (childAt == null || (this.g.indexOfKey(i10) >= 0 && childAt.getHeight() == this.g.get(i10))) ? 0 : childAt.getHeight());
                i10++;
                i11++;
            }
            View childAt2 = getChildAt(0);
            if (childAt2 != null) {
                if (this.b < childAdapterPosition) {
                    if (childAdapterPosition - this.b != 1) {
                        i8 = 0;
                        for (int i12 = childAdapterPosition - 1; i12 > this.b; i12--) {
                            if (this.g.indexOfKey(i12) > 0) {
                                i9 = this.g.get(i12);
                            } else {
                                i9 = childAt2.getHeight();
                            }
                            i8 += i9;
                        }
                    } else {
                        i8 = 0;
                    }
                    this.d += this.c + i8;
                    this.c = childAt2.getHeight();
                } else if (childAdapterPosition < this.b) {
                    if (this.b - childAdapterPosition != 1) {
                        i6 = 0;
                        for (int i13 = this.b - 1; i13 > childAdapterPosition; i13--) {
                            if (this.g.indexOfKey(i13) > 0) {
                                i7 = this.g.get(i13);
                            } else {
                                i7 = childAt2.getHeight();
                            }
                            i6 += i7;
                        }
                    } else {
                        i6 = 0;
                    }
                    this.d -= childAt2.getHeight() + i6;
                    this.c = childAt2.getHeight();
                } else if (childAdapterPosition == 0) {
                    this.c = childAt2.getHeight();
                    this.d = 0;
                }
                if (this.c < 0) {
                    this.c = 0;
                }
                this.f = this.d - childAt2.getTop();
                this.b = childAdapterPosition;
                this.h.onScrollChanged(this.f, this.j, this.k);
                if (this.j) {
                    this.j = false;
                }
                if (this.e < this.f) {
                    this.i = ScrollState.UP;
                } else if (this.f < this.e) {
                    this.i = ScrollState.DOWN;
                } else {
                    this.i = ScrollState.STOP;
                }
                this.e = this.f;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.h != null && motionEvent.getActionMasked() == 0) {
            this.k = true;
            this.j = true;
            this.h.onDownMotionEvent();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        final ViewGroup viewGroup;
        if (this.h != null) {
            switch (motionEvent.getActionMasked()) {
                case 1:
                case 3:
                    this.l = false;
                    this.k = false;
                    this.h.onUpOrCancelMotionEvent(this.i);
                    break;
                case 2:
                    if (this.m == null) {
                        this.m = motionEvent;
                    }
                    float y = motionEvent.getY() - this.m.getY();
                    this.m = MotionEvent.obtainNoHistory(motionEvent);
                    if (((float) getCurrentScrollY()) - y <= 0.0f) {
                        if (this.l) {
                            return false;
                        }
                        if (this.n == null) {
                            viewGroup = (ViewGroup) getParent();
                        } else {
                            viewGroup = this.n;
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
                        this.l = true;
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
        this.h = observableScrollViewCallbacks;
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.n = viewGroup;
    }

    public void scrollVerticallyTo(int i2) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            scrollVerticallyToPosition(i2 / childAt.getHeight());
        }
    }

    public void scrollVerticallyToPosition(int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !(layoutManager instanceof LinearLayoutManager)) {
            scrollToPosition(i2);
        } else {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i2, 0);
        }
    }

    public int getCurrentScrollY() {
        return this.f;
    }

    public int getChildAdapterPosition(View view) {
        if (22 <= f5324a) {
            return super.getChildAdapterPosition(view);
        }
        return getChildPosition(view);
    }

    private void a() {
        this.g = new SparseIntArray();
        b();
    }

    private void b() {
        try {
            super.getChildAdapterPosition((View) null);
        } catch (NoSuchMethodError unused) {
            f5324a = 21;
        }
    }

    static class SavedState implements Parcelable {
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
        public static final SavedState f5326a = new SavedState() {
        };
        int b;
        int c;
        int d;
        int e;
        int f;
        SparseIntArray g;
        Parcelable h;

        public int describeContents() {
            return 0;
        }

        private SavedState() {
            this.c = -1;
            this.h = null;
        }

        SavedState(Parcelable parcelable) {
            this.c = -1;
            this.h = parcelable == f5326a ? null : parcelable;
        }

        private SavedState(Parcel parcel) {
            this.c = -1;
            Parcelable readParcelable = parcel.readParcelable(RecyclerView.class.getClassLoader());
            this.h = readParcelable == null ? f5326a : readParcelable;
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = new SparseIntArray();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                for (int i = 0; i < readInt; i++) {
                    this.g.put(parcel.readInt(), parcel.readInt());
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.h, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            int size = this.g == null ? 0 : this.g.size();
            parcel.writeInt(size);
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    parcel.writeInt(this.g.keyAt(i2));
                    parcel.writeInt(this.g.valueAt(i2));
                }
            }
        }

        public Parcelable a() {
            return this.h;
        }
    }
}
