package com.tmall.wireless.vaf.virtualview.view.scroller;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.cm.ContainerService;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScrollerRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9419a = "ScrRecyAdapter_TMTEST";
    private static final String b = "waterfall";
    private static final String c = "stickyTop";
    private int d = 5;
    private VafContext e;
    private JSONArray f;
    private ContainerService g;
    private ScrollerImp h;
    private AtomicInteger i = new AtomicInteger(0);
    private String j;
    private int k = 1000000;
    private ViewGroup l;
    private int m = 0;
    private ArrayMap<String, Integer> n = new ArrayMap<>();
    private SparseArrayCompat<String> o = new SparseArrayCompat<>();

    public ScrollerRecyclerViewAdapter(VafContext vafContext, ScrollerImp scrollerImp) {
        this.e = vafContext;
        this.h = scrollerImp;
        this.g = this.e.q();
    }

    public void a(int i2) {
        this.m = i2;
    }

    public JSONObject b(int i2) {
        if (this.f == null || i2 >= this.f.length()) {
            return null;
        }
        try {
            return this.f.getJSONObject(i2);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a() {
        this.h = null;
        this.f = null;
        this.e = null;
        this.g = null;
    }

    public int b() {
        return this.k;
    }

    public ViewGroup c() {
        return this.l;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public void a(Object obj) {
        if (obj == null || !(obj instanceof JSONArray)) {
            Log.e(f9419a, "setData failed:" + obj);
        } else {
            this.f = (JSONArray) obj;
        }
        this.k = 1000000;
    }

    public void b(Object obj) {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            if (this.f == null) {
                this.f = jSONArray;
                notifyDataSetChanged();
                return;
            }
            int length = this.f.length();
            int length2 = jSONArray.length();
            for (int i2 = 0; i2 < length2; i2++) {
                try {
                    this.f.put(jSONArray.get(i2));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            notifyItemRangeChanged(length, length2);
            return;
        }
        Log.e(f9419a, "appendData failed:" + obj);
    }

    /* renamed from: a */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        View view;
        StaggeredGridLayoutManager.LayoutParams layoutParams;
        View view2;
        String str = this.o.get(i2);
        if (2 == this.h.mMode) {
            view = this.g.a(str, false);
            Layout.Params ae = ((IContainer) view).getVirtualView().ae();
            layoutParams = new StaggeredGridLayoutManager.LayoutParams(ae.f9382a, ae.b);
            view.setLayoutParams(layoutParams);
        } else {
            view = this.g.a(str);
            layoutParams = null;
        }
        if (str == this.j) {
            Layout.Params ae2 = ((IContainer) view).getVirtualView().ae();
            this.l = new FrameLayout(this.e.m());
            if (2 == this.h.mMode) {
                StaggeredGridLayoutManager.LayoutParams layoutParams2 = new StaggeredGridLayoutManager.LayoutParams(ae2.f9382a, ae2.b);
                this.l.setLayoutParams(layoutParams2);
                layoutParams = layoutParams2;
            }
            this.l.addView(view, ae2.f9382a, ae2.b);
            view2 = this.l;
        } else {
            view2 = view;
        }
        if (!(layoutParams == null || this.m == 0)) {
            int i3 = this.m >> 1;
            if (this.h.mLM.canScrollVertically()) {
                layoutParams.setMargins(i3, 0, i3, 0);
            } else {
                layoutParams.setMargins(0, i3, 0, i3);
            }
        }
        return new MyViewHolder(view2, ((IContainer) view).getVirtualView());
    }

    /* renamed from: a */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i2) {
        try {
            Object obj = this.f.get(i2);
            myViewHolder.itemView.setTag(Integer.valueOf(i2));
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                if (2 == this.h.mMode) {
                    StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) myViewHolder.itemView.getLayoutParams();
                    if (jSONObject.optInt("waterfall", -1) <= 0) {
                        layoutParams.setFullSpan(true);
                    } else {
                        layoutParams.setFullSpan(false);
                    }
                }
                if (jSONObject.optInt(c, -1) > 0) {
                    myViewHolder.f9420a = true;
                    this.k = i2;
                } else {
                    myViewHolder.f9420a = false;
                }
                myViewHolder.b.b(obj);
                if (myViewHolder.b.E()) {
                    this.e.e().a(1, EventData.a(this.e, myViewHolder.b));
                }
                myViewHolder.b.c();
            } else {
                Log.e(f9419a, "failed");
            }
            int i3 = this.d;
            if (this.f.length() < this.d) {
                i3 = 2;
            }
            if (i2 + i3 == this.f.length()) {
                this.h.callAutoRefresh();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            Log.e(f9419a, "onBindViewHolder:" + e2);
        }
    }

    public int getItemViewType(int i2) {
        if (this.f != null) {
            try {
                JSONObject jSONObject = this.f.getJSONObject(i2);
                String optString = jSONObject.optString("type");
                if (jSONObject.optInt(c, -1) > 0) {
                    this.j = optString;
                }
                if (this.n.containsKey(optString)) {
                    return this.n.get(optString).intValue();
                }
                int andIncrement = this.i.getAndIncrement();
                this.n.put(optString, Integer.valueOf(andIncrement));
                this.o.put(andIncrement, optString);
                return andIncrement;
            } catch (JSONException e2) {
                Log.e(f9419a, "getItemViewType:" + e2);
            }
        } else {
            Log.e(f9419a, "getItemViewType data is null");
            return -1;
        }
    }

    public int getItemCount() {
        if (this.f != null) {
            return this.f.length();
        }
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f9420a = false;
        public ViewBase b;

        public MyViewHolder(View view, ViewBase viewBase) {
            super(view);
            this.b = viewBase;
        }
    }
}
