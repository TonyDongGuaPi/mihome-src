package com.tmall.wireless.vaf.virtualview.view.scroller;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.view.scroller.ScrollerRecyclerViewAdapter;
import org.json.JSONObject;

public class ScrollerImp extends RecyclerView implements IContainer, IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9416a = "ScrollerImp_TMTEST";
    protected ScrollerRecyclerViewAdapter mAdapter;
    protected VafContext mAppContext;
    protected RecyclerView.LayoutManager mLM;
    protected Listener mListener;
    protected int mMode;
    protected int mOrientation;
    protected Scroller mScroller;
    protected ScrollerListener mScrollerListener;
    protected boolean mSupportSticky = false;

    public interface Listener {
        void a(RecyclerView recyclerView, int i);

        void a(RecyclerView recyclerView, int i, int i2);
    }

    public void attachViews() {
    }

    public View getHolderView() {
        return null;
    }

    public int getType() {
        return -1;
    }

    public void setVirtualView(ViewBase viewBase) {
    }

    public ScrollerImp(VafContext vafContext, Scroller scroller) {
        super(vafContext.m());
        this.mAppContext = vafContext;
        this.mScroller = scroller;
        setOverScrollMode(2);
        this.mAdapter = new ScrollerRecyclerViewAdapter(vafContext, this);
        setAdapter(this.mAdapter);
        setRecyclerListener(new RecyclerView.RecyclerListener() {
            public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
                ViewBase viewBase = ((ScrollerRecyclerViewAdapter.MyViewHolder) viewHolder).b;
                if (viewBase != null) {
                    viewBase.e();
                    return;
                }
                Log.e(ScrollerImp.f9416a, "recycled failed:" + viewBase);
            }
        });
    }

    public void setModeOrientation(int i, int i2) {
        if (this.mMode != i || this.mOrientation != i2) {
            this.mMode = i;
            this.mOrientation = i2;
            switch (i) {
                case 1:
                    this.mLM = new LinearLayoutManager(this.mAppContext.m());
                    ((LinearLayoutManager) this.mLM).setOrientation(i2);
                    break;
                case 2:
                    this.mLM = new StaggeredGridLayoutManager(2, i2);
                    break;
                default:
                    Log.e(f9416a, "mode invalidate:" + i);
                    break;
            }
            setLayoutManager(this.mLM);
        }
    }

    public void setSupportSticky(boolean z) {
        if (this.mSupportSticky != z) {
            this.mSupportSticky = z;
            if (this.mSupportSticky) {
                this.mScrollerListener = new ScrollerListener();
                setOnScrollListener(this.mScrollerListener);
                return;
            }
            setOnScrollListener((RecyclerView.OnScrollListener) null);
        }
    }

    public JSONObject getData(int i) {
        if (this.mAdapter != null) {
            return this.mAdapter.b(i);
        }
        return null;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        if (this.mScrollerListener == null) {
            this.mScrollerListener = new ScrollerListener();
            setOnScrollListener(this.mScrollerListener);
        }
    }

    public void setSpan(int i) {
        this.mAdapter.a(i);
    }

    public ViewBase getVirtualView() {
        return this.mScroller;
    }

    public void destroy() {
        this.mScroller = null;
        this.mAdapter.a();
        this.mAdapter = null;
    }

    public int getMode() {
        return this.mMode;
    }

    public void setAutoRefreshThreshold(int i) {
        this.mAdapter.c(i);
    }

    public void callAutoRefresh() {
        this.mScroller.am();
    }

    public void setData(Object obj) {
        this.mAdapter.a(obj);
        this.mAdapter.notifyDataSetChanged();
    }

    public void appendData(Object obj) {
        this.mAdapter.b(obj);
    }

    public void measureComponent(int i, int i2) {
        measure(i, i2);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    public void onComMeasure(int i, int i2) {
        onMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        onLayout(z, i, i2, i3, i4);
    }

    public int getComMeasuredWidth() {
        return getMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return getMeasuredHeight();
    }

    class ScrollerListener extends RecyclerView.OnScrollListener {
        private boolean b = false;
        private int c;
        private View d;

        ScrollerListener() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (ScrollerImp.this.mListener != null) {
                ScrollerImp.this.mListener.a(recyclerView, i);
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (ScrollerImp.this.mListener != null) {
                ScrollerImp.this.mListener.a(recyclerView, i, i2);
            }
            if (ScrollerImp.this.mSupportSticky) {
                int b2 = ScrollerImp.this.mAdapter.b();
                if (!this.b) {
                    View findChildViewUnder = ScrollerImp.this.findChildViewUnder(0.0f, 0.0f);
                    if (((Integer) findChildViewUnder.getTag()).intValue() >= b2) {
                        this.b = true;
                        ViewGroup c2 = ScrollerImp.this.mAdapter.c();
                        if (c2.getChildCount() == 1) {
                            this.d = c2.getChildAt(0);
                            c2.addView(new View(ScrollerImp.this.getContext()), c2.getMeasuredWidth(), c2.getMeasuredHeight());
                        }
                        c2.removeView(this.d);
                        b();
                        this.c = findChildViewUnder.getMeasuredHeight();
                    }
                } else if (((Integer) ScrollerImp.this.findChildViewUnder(0.0f, (float) this.c).getTag()).intValue() <= b2) {
                    this.b = false;
                    a();
                    ViewGroup c3 = ScrollerImp.this.mAdapter.c();
                    c3.addView(this.d, c3.getMeasuredWidth(), c3.getMeasuredHeight());
                }
            }
        }

        private void a() {
            ((ViewGroup) ScrollerImp.this.getParent()).removeView(this.d);
        }

        private void b() {
            ((ViewGroup) ScrollerImp.this.getParent()).addView(this.d);
        }
    }
}
